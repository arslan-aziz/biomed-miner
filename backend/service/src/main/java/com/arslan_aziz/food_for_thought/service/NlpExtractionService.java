package com.arslan_aziz.food_for_thought.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.arslan_aziz.food_for_thought.dao.NlpExtractionDao;
import com.arslan_aziz.food_for_thought.fs.dao.ProcessedArticleFsDao;
import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;
import com.arslan_aziz.food_for_thought.model.graph.ArticleGraph;
import com.arslan_aziz.food_for_thought.model.graph.ArticleGraphEdgeProperties;
import com.arslan_aziz.food_for_thought.model.graph.ArticleGraphVertex;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcApiService;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEfetchResponse;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEsearchResponse;
import com.arslan_aziz.food_for_thought.service.dto.NlpExtractionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.arslan_aziz.food_for_thought.fs.dao.ProcessedArticleFsDao;

/*
 * Service class to support creation and retrieval of NlpExtraction entities. 
 */
@Service
public class NlpExtractionService {
	
	private final PmcApiService pmcApiService;
	private final NlpExtractionDao nlpExtractionDao;
	private final Logger logger = LoggerFactory.getLogger(NlpExtractionService.class);
	private final ProcessedArticleFsDao fsArticleLoader;
	
	@Autowired
	public NlpExtractionService(NlpExtractionDao nlpExtractionDao, PmcApiService pmcApiService, ProcessedArticleFsDao fsArticleLoader) {
		this.nlpExtractionDao = nlpExtractionDao;
		this.pmcApiService = pmcApiService;
		this.fsArticleLoader = fsArticleLoader;
	}
	
	public NlpExtractionDto getNlpExtractionDtoById(int id) {
		NlpExtractionEntity nlpExtractionEntity = nlpExtractionDao.getNlpExtractionEntityById((long) id);
		NlpExtractionDto nlpExtractionDto = convertEntityToDto(nlpExtractionEntity);
		
		return nlpExtractionDto;
	}
	
	public NlpExtractionDto getNlpExtractionDtoByKey(String key) {
		NlpExtractionEntity nlpExtractionEntity = nlpExtractionDao.getNlpExtractionEntityByQueryName(key);
		NlpExtractionDto nlpExtractionDto = convertEntityToDto(nlpExtractionEntity);
		
		return nlpExtractionDto;
	}
	
	// handle query processing and store result in db
	@Async
	public void createNlpExtraction(String keyword, Integer queryNodeId, Integer maxNodeId)
			throws IOException, InterruptedException, SAXException, ParserConfigurationException {
		logger.info("Received service query " + keyword + " with node id " + queryNodeId);
		
		// normalize query
		keyword = NlpExtractionService.normalizeQuery(keyword);
		
		// check database if entity already exists
		NlpExtractionEntity entity = nlpExtractionDao.getNlpExtractionEntityByQueryName(keyword);
		if (entity != null) {
			return;
		}
		
		logger.info("Executing api calls");
		PmcEsearchResponse pmcEsearchResponse =  pmcApiService.createAndGetPmcEsearchResponse(keyword);
		PmcEfetchResponse firstPmcEfetchResponse = pmcApiService.createAndGetPmcEfetchResponse(pmcEsearchResponse.getArticleIdArray()[0]);
		
		int contentLength = firstPmcEfetchResponse.getContent().length();
		
		/*
		 * BEGIN MOCK ENTITY EXTRACTION AND GRAPH GENERATION
		 * IF queryNodeId invalid, then search was performed. Return a new mock graph.
		 * ELSE, generate graph extension
		 */
		
		ArticleGraph articleGraph;
		
		if (queryNodeId == -1) {
			articleGraph = fsArticleLoader.getProcessedArticleFromId("1234").getGraph();
		}
		else {
			articleGraph = new ArticleGraph();
			
			// add new node to current graph
			articleGraph
			.putVertex(
				new ArticleGraphVertex
				.ArticleGraphVertexBuilder()
				.withNameId(maxNodeId + 1)
				.withNomenclature("This is a new node.")
				.build());
			
			// add query node to current graph so it can be linked with new node
			articleGraph
				.putVertex(
					new ArticleGraphVertex
					.ArticleGraphVertexBuilder()
					.withNameId(queryNodeId)
					.withNomenclature(keyword)
					.build());
			
			// add edge between query node and new node
			articleGraph
				.addEdge(
					articleGraph.getVertex(queryNodeId).get(),
					articleGraph.getVertex(maxNodeId + 1).get(),
					new ArticleGraphEdgeProperties
						.ArticleGraphEdgePropertiesBuilder()
						.withNameId(1)
						.build());	
		}
		
		// serialize graph to json string
		ObjectMapper objectMapper = new ObjectMapper();
		String articleGraphAsString = objectMapper.writeValueAsString(articleGraph);

		logger.info("Loading into database");
		// create JPA entity and persist to database
		entity = new NlpExtractionEntity
				.NlpExtractionEntityBuilder()
				.setQueryName(keyword)
				.setContentLength(contentLength)
				.setEntityGraph(articleGraphAsString)
				.build();

		nlpExtractionDao.addNlpExtraction(entity);
	}
	
	private static NlpExtractionDto convertEntityToDto(NlpExtractionEntity nlpExtractionEntity) {
		if (nlpExtractionEntity == null) {
			return null;
		}
		else {
			return new NlpExtractionDto(nlpExtractionEntity.getQueryName(), nlpExtractionEntity.getContentLength(), nlpExtractionEntity.getEntityGraph());	
		}
	}
	
	public static String normalizeQuery(String query) {
		// TODO: use SNOMED or UMLS for search suggestion, deterministic
		return query;
	}
}

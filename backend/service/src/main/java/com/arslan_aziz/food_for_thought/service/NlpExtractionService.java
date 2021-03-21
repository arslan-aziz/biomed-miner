package com.arslan_aziz.food_for_thought.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.arslan_aziz.food_for_thought.dao.NlpExtractionDao;
import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcApiService;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEfetchResponse;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEsearchResponse;
import com.arslan_aziz.food_for_thought.service.dto.NlpExtractionDto;

/*
 * Service class to support creation and retrieval of NlpExtraction entities. 
 */
@Service
public class NlpExtractionService {
	
	private final PmcApiService pmcApiService;
	private final NlpExtractionDao nlpExtractionDao;
	private final Logger logger = LoggerFactory.getLogger(NlpExtractionService.class);
	
	@Autowired
	public NlpExtractionService(NlpExtractionDao nlpExtractionDao, PmcApiService pmcApiService) {
		this.nlpExtractionDao = nlpExtractionDao;
		this.pmcApiService = pmcApiService;
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
	public CompletableFuture<Integer> createNlpExtraction(String keyword) throws IOException, InterruptedException, SAXException, ParserConfigurationException {
		logger.info("Received service query " + keyword);
		// normalize query
		keyword = NlpExtractionService.normalizeQuery(keyword);
		
		// check database if entity already exists
		NlpExtractionEntity entity = nlpExtractionDao.getNlpExtractionEntityByQueryName(keyword);
		if (entity != null) {
			return CompletableFuture.completedFuture(0);
		}
		
		logger.info("Executing api calls");
		PmcEsearchResponse pmcEsearchResponse =  pmcApiService.createAndGetPmcEsearchResponse(keyword);
		PmcEfetchResponse firstPmcEfetchResponse = pmcApiService.createAndGetPmcEfetchResponse(pmcEsearchResponse.getArticleIdArray()[0]);
		
		int contentLength = firstPmcEfetchResponse.getContent().length();
		
		logger.info("Loading into database");
		// create JPA entity and persist to database
		entity = new NlpExtractionEntity.NlpExtractionEntityBuilder().setQueryName(keyword).setMockValue(contentLength).build();
		nlpExtractionDao.addNlpExtraction(entity);
		
		return CompletableFuture.completedFuture(0);
	}
	
	private static NlpExtractionDto convertEntityToDto(NlpExtractionEntity nlpExtractionEntity) {
		if (nlpExtractionEntity == null) {
			return null;
		}
		else {
			return new NlpExtractionDto(nlpExtractionEntity.getQueryName(), nlpExtractionEntity.getMockValue());	
		}
	}
	
	public static String normalizeQuery(String query) {
		// TODO: use SNOMED or UMLS for search suggestion, deterministic
		return query;
	}
}

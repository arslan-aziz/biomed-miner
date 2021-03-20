package com.arslan_aziz.food_for_thought.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arslan_aziz.food_for_thought.dao.NlpExtractionDao;
import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;
import com.arslan_aziz.food_for_thought.service.dto.NlpExtractionDto;

/*
 * Service class to support creation and retrieval of NlpExtraction entities. 
 */
@Service
public class NlpExtractionService {
	
	private HttpClient httpClient;
	private NlpExtractionDao nlpExtractionDao;
	private Logger logger = LoggerFactory.getLogger(NlpExtractionService.class);
	
	@Autowired
	public NlpExtractionService(NlpExtractionDao nlpExtractionDao, HttpClient httpClient) {
		this.nlpExtractionDao = nlpExtractionDao;
		this.httpClient = httpClient;
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
	public CompletableFuture<Integer> createNlpExtraction(String keyword) {
		logger.info("Received service query " + keyword);
		// normalize query
		keyword = NlpExtractionService.normalizeQuery(keyword);
		// check database
		NlpExtractionEntity entity = nlpExtractionDao.getNlpExtractionEntityByQueryName(keyword);
		if (entity != null) {
			return CompletableFuture.completedFuture(-1);
		}
		
		logger.info("Generating api call");
		// generate API call
		HttpRequest ncbiRequest = HttpRequest.newBuilder()
				.uri(URI.create("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pmc"))
				.build();
		HttpResponse<String> ncbiResponse = null;
		try {
			ncbiResponse = httpClient.send(ncbiRequest, BodyHandlers.ofString());	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		// preprocess files using Python service
		HttpRequest prepServiceRequest = HttpRequest.newBuilder()
				.uri(URI.create("localhost:8080"))
				.build();
		HttpResponse<String> prepServiceResponse = null;
		try {
			prepServiceResponse = httpClient.send(prepServiceRequest, BodyHandlers.ofString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		// entity extraction from text (analytics)
//		int contentLength = prepServiceResponse.body().length();
		int contentLength = 44;
		
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

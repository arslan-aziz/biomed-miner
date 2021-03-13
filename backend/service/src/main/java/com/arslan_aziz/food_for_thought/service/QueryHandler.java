package com.arslan_aziz.food_for_thought.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arslan_aziz.food_for_thought.dao.NlpExtractionDao;
import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;

/*
 * Retrieves corpus of biomedical articles from NCBI and performs parsing operations.
 */
@Service
public class QueryHandler {
	
	private HttpClient httpClient;
	private NlpExtractionDao nlpExtractionDao;
	
	@Autowired
	public QueryHandler(HttpClient httpClient, NlpExtractionDao nlpExtractionDao) {
		this.httpClient = httpClient;
		this.nlpExtractionDao = nlpExtractionDao;
	}
	
	public static String normalizeQuery(String query) {
		// TODO: use SNOMED or UMLS for search suggestion, deterministic
		return query;
	}
	
	// handle query processing and store result in db
	@Async
	public int processQuery(String query) {
		// normalize query
		query = QueryHandler.normalizeQuery(query);
		// check database
		NlpExtractionEntity entity = nlpExtractionDao.getNlpExtractionEntityByQueryName(query);
		if (entity != null) {
			return entity.getMockValue();
		}
		
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
		
		// entity extraction from text (analytics)
		int contentLength = prepServiceResponse.body().length();
		
		// create JPA entity and persist to database
		entity = new NlpExtractionEntity.NlpExtractionEntityBuilder().setQueryName(query).setMockValue(contentLength).build();
		nlpExtractionDao.addNlpExtraction(entity);
		
		return 0;
	}
}

package com.arslan_aziz.food_for_thought.service.api.pmc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEfetchResponse;
import com.arslan_aziz.food_for_thought.service.api.pmc.PmcEsearchResponse;
import com.arslan_aziz.food_for_thought.service.api.HttpMethods;

@Service
public class PmcApiService {
	/*
	 * Service to interface w/ NCBI PMC API and parse response.
	 */
	
	private final HttpMethods httpMethods;
	
	@Autowired
	public PmcApiService(HttpMethods httpMethods) {
		this.httpMethods = httpMethods;
	}
	
	/*
	 * Execute API call to NCBI PMC search API and parse XML response.
	 */
	public PmcEsearchResponse createAndGetPmcEsearchResponse(String searchTerm) throws IOException, InterruptedException, SAXException, ParserConfigurationException {
		// search for PMC by keyword
		 HttpResponse<String> response = 
			httpMethods
			.createAndExecuteGetRequest (
				"https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi",
				Map.ofEntries(
					Map.entry("db", "pmc"),
					Map.entry("term", searchTerm)
				)
			)
			.orElseThrow(IllegalStateException::new);
		
		// parse response XML
		Document doc = null;
		doc = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(new InputSource(new StringReader(response.body())));	
		
		doc.getDocumentElement().normalize();
		
		// extract list of article id's
		NodeList idNodeList = doc.getElementsByTagName("Id");
		int[] articleIdArray = new int[idNodeList.getLength()];
		for (int i=0; i < idNodeList.getLength(); i++) {
			articleIdArray[i]  = Integer.parseInt(idNodeList.item(i).getTextContent());
		}
		
		// create data transfer object
		PmcEsearchResponse pmcEsearchResponse = new PmcEsearchResponse();
		pmcEsearchResponse.setSearchTerm(searchTerm);
		pmcEsearchResponse.setArticleIdArray(articleIdArray);
		
		return pmcEsearchResponse;
	}
	
	/*
	 * Execute API call to NCBI PMC fetch API and parse XML response.
	 */
	public PmcEfetchResponse createAndGetPmcEfetchResponse(int articleId) throws IOException, InterruptedException {
		// fetch from PMC by article id
		HttpResponse<String> response =
			httpMethods
			.createAndExecuteGetRequest (
				"https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi",
				Map.ofEntries(
					Map.entry("db", "pmc"),
					Map.entry("id", "" + articleId)
				)
			)
			.orElseThrow(IllegalStateException::new);

		HttpResponse<String> prepServiceResponse =
			httpMethods
			.createAndExecutePostRequest (
					"http://127.0.0.1:5000/pubmed_parser",
					response.body()
			)
			.orElseThrow(IllegalStateException::new);
		
		// create data transfer object
		PmcEfetchResponse pmcEfetchResponse = new PmcEfetchResponse();
		pmcEfetchResponse.setArticleId(articleId);
		pmcEfetchResponse.setContent(prepServiceResponse.body());
		
		return pmcEfetchResponse;
	}
	

}

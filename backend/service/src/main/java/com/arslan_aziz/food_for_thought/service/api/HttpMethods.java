package com.arslan_aziz.food_for_thought.service.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpMethods {

	private final HttpClient httpClient;
	
	@Autowired
	public HttpMethods(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	public Optional<HttpResponse<String>> createAndExecutePostRequest(String uri, String body) throws IOException, InterruptedException {
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.POST(BodyPublishers.ofString(body))
				.build();
		HttpResponse<String> httpResponse = null;
		httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
		
		return Optional.ofNullable(httpResponse);
	}
	
	public Optional<HttpResponse<String>> createAndExecuteGetRequest(String uri, Map<String, String> queryParams) throws IOException, InterruptedException {
		
		// input validation
		if (uri.length() == 0) {
			return null;
		}
		
		// append queryparams to uri
		StringBuilder uriWithParamsBuilder = new StringBuilder();
		uriWithParamsBuilder.append(uri);
		if (queryParams.size() > 0) {
			uriWithParamsBuilder.append("?");
			for(Map.Entry<String, String> entry: queryParams.entrySet()) {
				uriWithParamsBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
			uriWithParamsBuilder.deleteCharAt(uriWithParamsBuilder.length() - 1);
		}
		
		String uriWithParams = uriWithParamsBuilder.toString();
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(uriWithParams))
				.build();
		HttpResponse<String> httpResponse = null;
		httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
		
		return Optional.ofNullable(httpResponse);
	}
}

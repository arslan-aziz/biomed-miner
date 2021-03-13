package com.arslan_aziz.food_for_thought.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;

/*
 * Beans for service layer classes.
 * TODO: consider how this can be split up among service functions. 
 */
@Configuration
public class ServiceConfig {

	@Bean()
	public HttpClient getHttpClient() {
		HttpClient client = HttpClient.newBuilder()
				.version(Version.HTTP_2)
				.followRedirects(Redirect.NORMAL)
				.connectTimeout(Duration.ofSeconds(20))
				.build();
		
		return client;
		
	}
	
}

package com.arslan_aziz.food_for_thought.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FileSystemLoaderConfiguration {
	
	@Value("$(path.mapping.processed)")
	private String processedFilesMapPath;
	@Value("$(path.mapping.raw)")
	private String rawFilesMapPath;
	
	private ObjectMapper mapper;
	
	@Autowired
	public FileSystemLoaderConfiguration(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean(name="ProcessedArticleIdToPathMap")
	public Map<String, String> getProcessedArticleIdToPathMap() {
		
		TypeReference<Map<String, String>> stringToStringMapType = new TypeReference<Map<String, String>>() {};
		Map<String, String> configMap = null;
		try {
			configMap = mapper.readValue(processedFilesMapPath, stringToStringMapType);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		 
 		return configMap;
	}
	
	@Bean(name="RawArticleIdToPathMap")
	public Map<String, String> getRawArticleIdToPathMap() {
		
		TypeReference<Map<String, String>> stringToStringMapType = new TypeReference<Map<String, String>>() {};
		Map<String, String> configMap = null;
		try {
			configMap = mapper.readValue(rawFilesMapPath, stringToStringMapType);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return configMap;
	}
}







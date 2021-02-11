package com.arslan_aziz.food_for_thought.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.arslan_aziz.food_for_thought.models.graph.ArticleGraph;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ConfigurationProperties(prefix = "path")
public class FileSystemLoaderConfiguration implements ResourceLoaderAware {
	
//	TODO: load from application.properties
	private String processedMap = "config/processedFilesMapPath.json";
//	TODO: load from application.properties
	private String rawMap = "config/rawFilesMapPath.json";
	
	private ResourceLoader resourceLoader;
	private ObjectMapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessedArticleLoader.class);
	
	@Autowired
	public FileSystemLoaderConfiguration(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean(name="ProcessedArticleIdToPathMap")
	public Map<String, String> getProcessedArticleIdToPathMap() {
		
//		File pathFile = new File(processedMap);
//		if (!pathFile.exists()) {
//			logger.info("processed map path DNE");
//			return null;
//		}
		
//		Resource resource = resourceLoader.getResource("classpath:" + processedMap);
		// THIS WORKS!!!!!!!
		ClassPathResource resource = new ClassPathResource("config/processedFilesMapPath.json");
		
		TypeReference<Map<String, String>> stringToStringMapType = new TypeReference<Map<String, String>>() {};
		Map<String, String> configMap = null;
		
		InputStream inStream = null;
		try {
			inStream = resource.getInputStream();
			configMap = mapper.readValue(inStream, stringToStringMapType);	
			inStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info(configMap.toString());
		
 		return configMap;
	}
	
	@Bean(name="RawArticleIdToPathMap")
	public Map<String, String> getRawArticleIdToPathMap() {
		File pathFile = new File(rawMap);
		if (!pathFile.exists()) {
			return null;
		}
		
		Resource resource = resourceLoader.getResource("file:" + rawMap);
		
		TypeReference<Map<String, String>> stringToStringMapType = new TypeReference<Map<String, String>>() {};
		Map<String, String> configMap = null;
		
		InputStream inStream = null;
		try {
			inStream = resource.getInputStream();
			configMap = mapper.readValue(inStream, stringToStringMapType);	
			inStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

 		return configMap;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		
	}
}







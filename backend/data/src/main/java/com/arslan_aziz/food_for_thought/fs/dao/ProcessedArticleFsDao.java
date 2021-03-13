package com.arslan_aziz.food_for_thought.fs.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.arslan_aziz.food_for_thought.fs.model.ProcessedArticle;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Load processed article JSON into ArticleGraph object.
 */

@Component
public class ProcessedArticleFsDao implements ResourceLoaderAware {
	
	private ObjectMapper mapper;
	private ResourceLoader resourceLoader;
	private Map<String, String> idToPathMap;
	private static final Logger logger = LoggerFactory.getLogger(ProcessedArticleFsDao.class);
	
	@Autowired
	public ProcessedArticleFsDao(ObjectMapper mapper, @Qualifier("ProcessedArticleIdToPathMap") Map<String, String> idToPathMap) {
		this.mapper = mapper;
		this.idToPathMap = idToPathMap;
	}
	
	public ProcessedArticle getProcessedArticleFromId(String articleId) {
		
		logger.info(idToPathMap.toString());
		
		// TODO: validate that the id exists in the mapping (if missing returns null resulting in null ptr exception in new File(path))
		String path = idToPathMap.get(articleId);
		
		ClassPathResource resource = new ClassPathResource(path);
		
		ProcessedArticle processedArticle = null;
		InputStream inStream = null;
		try {
			inStream = resource.getInputStream();
			processedArticle = mapper.readValue(inStream, ProcessedArticle.class);
			inStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return processedArticle;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		
	}
	
}

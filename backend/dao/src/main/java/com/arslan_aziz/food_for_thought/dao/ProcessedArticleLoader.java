package com.arslan_aziz.food_for_thought.dao;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.arslan_aziz.food_for_thought.models.graph.ArticleGraph;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Load processed article JSON into ArticleGraph object.
 */

@Component
public class ProcessedArticleLoader {
	
	private ObjectMapper mapper;
	private Map<String, String> idToPathMap;
	
	@Autowired
	public ProcessedArticleLoader(ObjectMapper mapper, @Qualifier("ProcessedArticleIdToPathMap") Map<String, String> idToPathMap) {
		this.mapper = mapper;
		this.idToPathMap = idToPathMap;
	}
	
	public ArticleGraph getProcessedArticleFromId(String articleId) {
		
		// validate that the id exists in the mapping
		String path = idToPathMap.get(articleId);
		
		File pathFile = new File(path);
		
		if (!pathFile.exists()) {
			return null;
		}
		
		ArticleGraph articleGraph = null;
		try {
			articleGraph = mapper.readValue(pathFile, ArticleGraph.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return articleGraph;
	}
	
}

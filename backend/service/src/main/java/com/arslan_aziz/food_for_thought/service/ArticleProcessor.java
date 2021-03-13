package com.arslan_aziz.food_for_thought.service;

import org.springframework.stereotype.Service;

import com.arslan_aziz.food_for_thought.fs.dao.ProcessedArticleFsDao;
import com.arslan_aziz.food_for_thought.fs.model.ProcessedArticle;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * Perform parsing operations on a natural language biomedical article.
 */
@Service
public class ArticleProcessor {

	// Hold onto an instance of ProcessedArticleLoader to load articles that have already been processed
	private ProcessedArticleFsDao loader;
	
	@Autowired
	public ArticleProcessor(ProcessedArticleFsDao loader) {
		this.loader = loader;
	}
	
	public ProcessedArticle processArticle(String articleId) {
		
		ProcessedArticle article = loader.getProcessedArticleFromId(articleId);
		// not apply any further analytic/business logic
		return article;
	}
	
}

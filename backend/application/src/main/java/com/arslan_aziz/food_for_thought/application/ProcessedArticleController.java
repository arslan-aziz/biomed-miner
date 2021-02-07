package com.arslan_aziz.food_for_thought.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.arslan_aziz.food_for_thought.service.ArticleProcessor;
import com.arslan_aziz.food_for_thought.models.ProcessedArticle;

@RestController
public class ProcessedArticleController {
	
	private ArticleProcessor articleProcessor;
	
	@Autowired
	public ProcessedArticleController(ArticleProcessor articleProcessor) {
		this.articleProcessor = articleProcessor;
	}
	
	@GetMapping(value="/article/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ProcessedArticle getProcessedArticle(@PathVariable String id) {
		// TODO: check persistence layer for already processed document before running document processor
		return articleProcessor.processArticle();
	}
}

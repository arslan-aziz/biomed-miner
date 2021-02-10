package com.arslan_aziz.food_for_thought.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.arslan_aziz.food_for_thought.service.ArticleProcessor;
import com.arslan_aziz.food_for_thought.models.ProcessedArticle;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class ProcessedArticleController {
	
	private ArticleProcessor articleProcessor;
	
	@Autowired
	public ProcessedArticleController(ArticleProcessor articleProcessor) {
		this.articleProcessor = articleProcessor;
	}
	
	@GetMapping(value="/article/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProcessedArticle(@PathVariable String id) {
		// TODO: check persistence layer for already processed document before running document processor
		ProcessedArticle result = articleProcessor.processArticle(id);
		
		if (result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.ok(result);
		}
	}
}

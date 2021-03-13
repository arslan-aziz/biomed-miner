package com.arslan_aziz.food_for_thought.fs.model;

import com.arslan_aziz.food_for_thought.model.graph.ArticleGraph;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonDeserialize(builder = ProcessedArticle.ProcessedArticleBuilder.class)
public class ProcessedArticle {
	
	// members
	private final String name;
	private final int id;
	private final ArticleGraph graph;
	
	
	private ProcessedArticle(ProcessedArticleBuilder builder) {
		this.name = builder.name;
		this.id = builder.id;
		this.graph = builder.graph;
	}
	
	// No setters to provide immutability
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public ArticleGraph getGraph() {
		return graph;
	}
	
	@Override
	public String toString() {
		return "Article: " + name + "; id: " + id;
	}
	
	public static class ProcessedArticleBuilder {
		private String name;
		private int id;
		private ArticleGraph graph;
		
		public ProcessedArticleBuilder() {}
		
		public ProcessedArticleBuilder withName(String name) {
			this.name = name;
			return this;
		}
		public ProcessedArticleBuilder withId(int id) {
			this.id = id;
			return this;
		}
		public ProcessedArticleBuilder withGraph(ArticleGraph graph) {
			this.graph = graph;
			return this;
		}
		
		// Return created object
		public ProcessedArticle build() {
			ProcessedArticle newArticle = new ProcessedArticle(this);
			validateObject(newArticle);
			return newArticle;
		}
		
		public void validateObject(ProcessedArticle article) {
			// perform validations on created object
		}
		
	}
}

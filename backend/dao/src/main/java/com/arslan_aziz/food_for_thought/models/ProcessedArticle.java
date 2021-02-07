package com.arslan_aziz.food_for_thought.models;

import com.arslan_aziz.food_for_thought.models.graph.ArticleGraph;

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
		
		public ProcessedArticleBuilder name(String name) {
			this.name = name;
			return this;
		}
		public ProcessedArticleBuilder id(int id) {
			this.id = id;
			return this;
		}
		public ProcessedArticleBuilder graph(ArticleGraph graph) {
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

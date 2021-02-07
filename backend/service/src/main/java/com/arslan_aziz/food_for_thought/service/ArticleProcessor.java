package com.arslan_aziz.food_for_thought.service;

import org.springframework.stereotype.Service;

import com.arslan_aziz.food_for_thought.models.ProcessedArticle;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraph;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraphEdgeProperties;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraphVertex;

/*
 * Perform parsing operations on a natural language biomedical article.
 */
@Service
public class ArticleProcessor {
	
	public ProcessedArticle processArticle() {
		ArticleGraph graph = new ArticleGraph();
		// Test with mock graph
		ArticleGraphVertex vertex1 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("1").nomenclature("entity1").build();
		ArticleGraphVertex vertex2 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("2").nomenclature("entity2").build();
		ArticleGraphVertex vertex3 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("3").nomenclature("entity3").build();
		graph.putVertex(vertex1);
		graph.putVertex(vertex2);
		graph.putVertex(vertex3);
		graph.addEdge(vertex1, vertex2, new ArticleGraphEdgeProperties.ArticleGraphEdgePropertiesBuilder().edgeWeight(3.2).build());
		graph.addEdge(vertex1, vertex3, new ArticleGraphEdgeProperties.ArticleGraphEdgePropertiesBuilder().edgeWeight(4.67).build());
		
		ProcessedArticle newArticle = new ProcessedArticle.ProcessedArticleBuilder().name("Hello World!").id(3).graph(graph).build();
		
		return newArticle;
	}
	
}

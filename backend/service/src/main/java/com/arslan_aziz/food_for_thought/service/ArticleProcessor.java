package com.arslan_aziz.food_for_thought.service;

import org.springframework.stereotype.Service;

import com.arslan_aziz.food_for_thought.dao.ProcessedArticleLoader;
import com.arslan_aziz.food_for_thought.models.ProcessedArticle;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraph;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraphEdgeProperties;
import com.arslan_aziz.food_for_thought.models.graph.ArticleGraphVertex;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Perform parsing operations on a natural language biomedical article.
 */
@Service
public class ArticleProcessor {

	// Hold onto an instance of ProcessedArticleLoader to load articles that have already been processed
	private ProcessedArticleLoader loader;
	
	@Autowired
	public ArticleProcessor(ProcessedArticleLoader loader) {
		this.loader = loader;
	}
	
	public ProcessedArticle processArticle(String articleId) {
		
		ArticleGraph graph = loader.getProcessedArticleFromId(articleId);
		
		ProcessedArticle article = new ProcessedArticle.ProcessedArticleBuilder().name("Hello World!").id(2).graph(graph).build();
		/* Mock data
		if (articleId.equals("1234")) {
			graph = new ArticleGraph();
			ArticleGraphVertex vertex1 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("1").nomenclature("entity1").build();
			ArticleGraphVertex vertex2 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("2").nomenclature("entity2").build();
			ArticleGraphVertex vertex3 = new ArticleGraphVertex.ArticleGraphVertexBuilder().nameId("3").nomenclature("entity3").build();
			graph.putVertex(vertex1);
			graph.putVertex(vertex2);
			graph.putVertex(vertex3);
			graph.addEdge(vertex1, vertex2, new ArticleGraphEdgeProperties.ArticleGraphEdgePropertiesBuilder().edgeWeight(3.2).build());
			graph.addEdge(vertex1, vertex3, new ArticleGraphEdgeProperties.ArticleGraphEdgePropertiesBuilder().edgeWeight(4.67).build());
			
			article = new ProcessedArticle.ProcessedArticleBuilder().name("Hello World!").id(3).graph(graph).build();
		}
		else if (articleId.equals("5678")) {
			graph = new ArticleGraph();
			article = new ProcessedArticle.ProcessedArticleBuilder().name("Hello Jove!").id(4).graph(graph).build();
		}
		else if (articleId.equals("3423")) {
			graph = new ArticleGraph();
			article = new ProcessedArticle.ProcessedArticleBuilder().name("Hello Music!").id(5).graph(graph).build();
		}
		
		*/
		
		return article;
	}
	
}

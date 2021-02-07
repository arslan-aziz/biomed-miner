package com.arslan_aziz.food_for_thought.models.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class ArticleGraph {
	
	// maybe use a list of tuples instead to represent edges
	private Map<ArticleGraphVertex, List<Map<ArticleGraphVertex, ArticleGraphEdgeProperties>>> adjVertices;
	
	public ArticleGraph() {
		adjVertices = new HashMap<ArticleGraphVertex, List<Map<ArticleGraphVertex, ArticleGraphEdgeProperties>>>();
	}
	
	public void putVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.putIfAbsent(articleGraphVertex, new ArrayList<Map<ArticleGraphVertex, ArticleGraphEdgeProperties>>());
	}
	
	public void removeVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.remove(articleGraphVertex);
	}
	
	public void addEdge(ArticleGraphVertex v1, ArticleGraphVertex v2, ArticleGraphEdgeProperties edgeProps) {
		if (adjVertices.containsKey(v1) && adjVertices.containsKey(v2)) {
			// add undirected edge from v1 to v2
			Map<ArticleGraphVertex, ArticleGraphEdgeProperties> edgeMap = new HashMap<ArticleGraphVertex, ArticleGraphEdgeProperties>();
			edgeMap.put(v2, edgeProps);
			adjVertices.get(v1).add(edgeMap);
			
			// add undirected edge from v2 to v1
			Map<ArticleGraphVertex, ArticleGraphEdgeProperties> edgeMapOther = new HashMap<ArticleGraphVertex, ArticleGraphEdgeProperties>();
			edgeMapOther.put(v1, edgeProps);
			adjVertices.get(v2).add(edgeMapOther);
		}
	}
	
	public void removeEdge(ArticleGraphVertex v1, ArticleGraphVertex v2) {
		// remove undirected edge v1 <--> v2 from v1
		adjVertices.get(v1).removeIf(map -> map.containsKey(v2));
		// remove undirected edge v1 <--> v2 from v2
		adjVertices.get(v2).removeIf(map -> map.containsKey(v1));
	}
	
	List<Map<ArticleGraphVertex, ArticleGraphEdgeProperties>> getAdjVertices(ArticleGraphVertex v) {
		if (adjVertices.containsKey(v)) {
			return adjVertices.get(v);
		}
		else {
			return null;
		}
	}
	
	// TODO: to json string or can jackson handle this?
}

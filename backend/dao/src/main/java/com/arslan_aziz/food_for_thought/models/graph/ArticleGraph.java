package com.arslan_aziz.food_for_thought.models.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class ArticleGraph {
	
	// maybe use a list of tuples instead to represent edges
	private Map<String, List<Map<String, String>>> adjVertices;
	private Map<String, ArticleGraphVertex> idToVertexMap;
	private Map<String, ArticleGraphEdgeProperties> idToEdgePropsMap;
	
	public ArticleGraph() {
		adjVertices = new HashMap<String, List<Map<String, String>>>();
	}
	
	public void putVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.putIfAbsent(articleGraphVertex.getNameId(), new ArrayList<Map<String, String>>());
		idToVertexMap.putIfAbsent(articleGraphVertex.getNameId(), articleGraphVertex);
	}
	
	public void removeVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.remove(articleGraphVertex.getNameId());
		idToVertexMap.remove(articleGraphVertex.getNameId());
	}
	
	public void addEdge(ArticleGraphVertex v1, ArticleGraphVertex v2, ArticleGraphEdgeProperties edgeProps) {
		if (adjVertices.containsKey(v1.getNameId()) && adjVertices.containsKey(v2.getNameId())) {
			// add undirected edge from v1 to v2
			Map<String, String> edgeMap = new HashMap<String, String>();
			edgeMap.put(v2.getNameId(), edgeProps.getNameId());
			adjVertices.get(v1.getNameId()).add(edgeMap);
			idToEdgePropsMap.put(edgeProps.getNameId(), edgeProps);
			
			
			
			// add undirected edge from v2 to v1
			Map<String, String> edgeMapOther = new HashMap<String, String>();
			edgeMapOther.put(v1.getNameId(), edgeProps.getNameId());
			adjVertices.get(v2.getNameId()).add(edgeMapOther);
		}
	}
	
	public void removeEdge(ArticleGraphVertex v1, ArticleGraphVertex v2) {
		// remove undirected edge v1 <--> v2 from v1
		adjVertices.get(v1.getNameId()).removeIf(map -> map.containsKey(v2.getNameId()));
		// remove undirected edge v1 <--> v2 from v2
		adjVertices.get(v2.getNameId()).removeIf(map -> map.containsKey(v1.getNameId()));
	}
	
	List<Map<String, String>> getAdjVertices(ArticleGraphVertex v) {
		if (adjVertices.containsKey(v.getNameId())) {
			return adjVertices.get(v.getNameId());
		}
		else {
			return null;
		}
	}

}

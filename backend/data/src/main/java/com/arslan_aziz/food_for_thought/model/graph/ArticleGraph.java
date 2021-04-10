package com.arslan_aziz.food_for_thought.model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class ArticleGraph {
	
	private Map<Integer, List<Map<Integer, Integer>>> adjVertices;
	private Map<Integer, ArticleGraphVertex> idToVertexMap;
	private Map<Integer, ArticleGraphEdgeProperties> idToEdgePropsMap;
	
	public ArticleGraph() {
		adjVertices = new HashMap<Integer, List<Map<Integer, Integer>>>();
		idToVertexMap = new HashMap<Integer, ArticleGraphVertex>();
		idToEdgePropsMap = new HashMap<Integer, ArticleGraphEdgeProperties>();
	}
	
	public void putVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.putIfAbsent(articleGraphVertex.getNameId(), new ArrayList<Map<Integer, Integer>>());
		idToVertexMap.putIfAbsent(articleGraphVertex.getNameId(), articleGraphVertex);
	}
	
	public void removeVertex(ArticleGraphVertex articleGraphVertex) {
		adjVertices.remove(articleGraphVertex.getNameId());
		idToVertexMap.remove(articleGraphVertex.getNameId());
	}
	
	public void addEdge(ArticleGraphVertex v1, ArticleGraphVertex v2, ArticleGraphEdgeProperties edgeProps) {
		if (adjVertices.containsKey(v1.getNameId()) && adjVertices.containsKey(v2.getNameId())) {
			// add undirected edge from v1 to v2
			Map<Integer, Integer> edgeMap = new HashMap<Integer, Integer>();
			edgeMap.put(v2.getNameId(), edgeProps.getNameId());
			adjVertices.get(v1.getNameId()).add(edgeMap);
			idToEdgePropsMap.put(edgeProps.getNameId(), edgeProps);
			
			// add undirected edge from v2 to v1
			Map<Integer, Integer> edgeMapOther = new HashMap<Integer, Integer>();
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
	
	public Optional<ArticleGraphVertex> getVertex(Integer nameId) {
			return Optional.ofNullable(idToVertexMap.getOrDefault(nameId, null));
	}
	
	List<Map<Integer, Integer>> getAdjVertices(ArticleGraphVertex v) {
		if (adjVertices.containsKey(v.getNameId())) {
			return adjVertices.get(v.getNameId());
		}
		else {
			return null;
		}
	}

}

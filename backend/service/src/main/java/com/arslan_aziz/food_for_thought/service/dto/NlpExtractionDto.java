package com.arslan_aziz.food_for_thought.service.dto;

public class NlpExtractionDto {

	private String queryName;
	private Integer mockValue;
	private String entityGraph;
	
	public NlpExtractionDto() {};
	
	public NlpExtractionDto(String queryName, Integer mockValue, String entityGraph) {
		this.queryName = queryName;
		this.mockValue = mockValue;
		this.entityGraph = entityGraph;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Integer getMockValue() {
		return mockValue;
	}

	public void setMockValue(Integer mockValue) {
		this.mockValue = mockValue;
	}
	
	public String getEntityGraph() {
		return entityGraph;
	}
	
	public void setEntityGraph(String entityGraph) {
		this.entityGraph = entityGraph;
	}
}

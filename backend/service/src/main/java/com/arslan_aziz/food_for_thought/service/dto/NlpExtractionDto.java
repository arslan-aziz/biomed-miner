package com.arslan_aziz.food_for_thought.service.dto;

public class NlpExtractionDto {

	private String queryName;
	private Integer mockValue;
	
	public NlpExtractionDto() {};
	
	public NlpExtractionDto(String queryName, Integer mockValue) {
		this.queryName = queryName;
		this.mockValue = mockValue;
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
}

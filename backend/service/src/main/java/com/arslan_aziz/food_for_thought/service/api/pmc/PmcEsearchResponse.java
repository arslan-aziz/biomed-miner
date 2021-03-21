package com.arslan_aziz.food_for_thought.service.api.pmc;

public class PmcEsearchResponse {

	private String searchTerm;
	private int[] articleIdArray;
	
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public int[] getArticleIdArray() {
		return articleIdArray;
	}
	public void setArticleIdArray(int[] articleIdArray) {
		this.articleIdArray = articleIdArray;
	}
	
}

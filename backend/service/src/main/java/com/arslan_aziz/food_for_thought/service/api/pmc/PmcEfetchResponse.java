package com.arslan_aziz.food_for_thought.service.api.pmc;

public class PmcEfetchResponse {
	
	private int articleId;
	private String content;

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

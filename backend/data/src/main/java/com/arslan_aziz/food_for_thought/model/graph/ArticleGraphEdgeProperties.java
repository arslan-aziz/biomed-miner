package com.arslan_aziz.food_for_thought.model.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonDeserialize(builder = ArticleGraphEdgeProperties.ArticleGraphEdgePropertiesBuilder.class)
public class ArticleGraphEdgeProperties {

	private final Integer nameId;
	private final double edgeWeight;
	
	private ArticleGraphEdgeProperties(ArticleGraphEdgePropertiesBuilder builder) {
		this.nameId = builder.nameId;
		this.edgeWeight = builder.edgeWeight;
	}
	
	public Integer getNameId() {
		return nameId;
	}
	public double getEdgeWeight() {
		return edgeWeight;
	}
	
	public static class ArticleGraphEdgePropertiesBuilder {
		
		private Integer nameId;
		private double edgeWeight;
		
		public ArticleGraphEdgePropertiesBuilder() {}
		
		public ArticleGraphEdgePropertiesBuilder withNameId(Integer nameId) {
			this.nameId = nameId;
			return this;
		}
		public ArticleGraphEdgePropertiesBuilder withEdgeWeight(double edgeWeight) {
			this.edgeWeight = edgeWeight;
			return this;
		}
		public ArticleGraphEdgeProperties build() {
			ArticleGraphEdgeProperties articleGraphEdgeProperties = new ArticleGraphEdgeProperties(this);
			validateObject(articleGraphEdgeProperties);
			return articleGraphEdgeProperties;
		}
		public void validateObject(ArticleGraphEdgeProperties articleGraphEdgeProperties) {
			
		}
	}
}

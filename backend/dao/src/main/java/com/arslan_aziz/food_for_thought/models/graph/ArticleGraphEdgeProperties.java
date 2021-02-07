package com.arslan_aziz.food_for_thought.models.graph;

public class ArticleGraphEdgeProperties {

	private final double edgeWeight;
	
	private ArticleGraphEdgeProperties(ArticleGraphEdgePropertiesBuilder builder) {
		this.edgeWeight = builder.edgeWeight;
	}
	
	public double getEdgeWeight() {
		return edgeWeight;
	}
	
	public static class ArticleGraphEdgePropertiesBuilder {
		
		private double edgeWeight;
		
		public ArticleGraphEdgePropertiesBuilder() {}
		
		public ArticleGraphEdgePropertiesBuilder edgeWeight(double edgeWeight) {
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

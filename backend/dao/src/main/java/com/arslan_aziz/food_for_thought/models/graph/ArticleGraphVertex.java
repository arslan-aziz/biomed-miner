package com.arslan_aziz.food_for_thought.models.graph;

// Consider an abstract vertex for different entity types extracted from article.
public class ArticleGraphVertex {
	
	private final String nameId;
	private final String nomenclature;
	
	private ArticleGraphVertex(ArticleGraphVertexBuilder builder) {
		this.nameId = builder.nameId;
		this.nomenclature = builder.nomenclature;
	}
	
	public String getNameId() {
		return nameId;
	}
	public String getNomenclature() {
		return nomenclature;
	}
	
	public static class ArticleGraphVertexBuilder {
		private String nameId;
		private String nomenclature;
		
		public ArticleGraphVertexBuilder() { }
		public ArticleGraphVertexBuilder nameId(String nameId) {
			this.nameId = nameId;
			return this;
		}
		public ArticleGraphVertexBuilder nomenclature(String nomenclature) {
			this.nomenclature = nomenclature;
			return this;
		}
		public ArticleGraphVertex build() {
			ArticleGraphVertex articleGraphVertex = new ArticleGraphVertex(this);
			validateObject(articleGraphVertex);
			return articleGraphVertex;
		}
		public void validateObject(ArticleGraphVertex articleGraphVertex) {
			
		}
	}

	// Assumes that the character ':' is not contained in nameId or nomenclature
	@Override
	public String toString() {
		return nameId + ":" + nomenclature;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof ArticleGraphVertex) {
			ArticleGraphVertex that = (ArticleGraphVertex) other;
			result = this.toString().equals(that.toString());
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	
}

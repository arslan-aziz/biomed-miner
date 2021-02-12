package com.arslan_aziz.food_for_thought.models.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// Consider an abstract vertex for different entity types extracted from article.
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonDeserialize(builder = ArticleGraphVertex.ArticleGraphVertexBuilder.class)
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
		public ArticleGraphVertexBuilder withNameId(String nameId) {
			this.nameId = nameId;
			return this;
		}
		public ArticleGraphVertexBuilder withNomenclature(String nomenclature) {
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
	
	
}

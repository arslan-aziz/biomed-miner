package com.arslan_aziz.food_for_thought.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * NlpExtraction entity represents results of an NLP extraction task on a corpus of documents.
 */
@Entity
@Table(name="NLP_EXTRACTION")
@NamedQueries({
	@NamedQuery(
		name="NlpExtractionEntity_getByQueryName",
		query="from NlpExtractionEntity where queryName = :queryname"
	)
})
public class NlpExtractionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="QUERYNAME", length=50, nullable=false, unique=false)
	private String queryName;
	
	@Column(name="CONTENTLENGTH")
	private Integer contentLength;
	
	@Column(name="ENTITYGRAPH")
	private String entityGraph;
	
	protected NlpExtractionEntity() {};
	
	private NlpExtractionEntity(String queryName, Integer contentLength, String entityGraph, Long id) {
		this.contentLength = contentLength;
		this.queryName = queryName;
		this.entityGraph = entityGraph;
		if (id != -1L) {
			this.id = id;
		}
	}
	
	public Long getId() {
		return id;
	}
	
	public String getQueryName() {
		return queryName;
	}
	
	public Integer getContentLength() {
		return contentLength;
	}
	
	public String getEntityGraph() {
		return entityGraph;
	}
	
	public static class NlpExtractionEntityBuilder {
		private String queryName;
		private Integer contentLength;
		private String entityGraph;
		private Long id = -1L;
		
		public NlpExtractionEntityBuilder setQueryName(final String queryName) {
			this.queryName = queryName;
			return this;
		}
		
		public NlpExtractionEntityBuilder setContentLength(final Integer contentLength) {
			this.contentLength = contentLength;
			return this;
		}
		
		public NlpExtractionEntityBuilder setEntityGraph(final String entityGraph) {
			this.entityGraph = entityGraph;
			return this;
		}
		
		// Setting of id is optional depending on use case (managed vs. transient entity).
		public NlpExtractionEntityBuilder setId(final Long id) {
			this.id = id;
			return this;
		}
		
		public NlpExtractionEntity build() {
			return new NlpExtractionEntity(queryName, contentLength, entityGraph, id);
		}
	}
	
	@Override
	public String toString() {
		return queryName + contentLength.toString();
	}
}

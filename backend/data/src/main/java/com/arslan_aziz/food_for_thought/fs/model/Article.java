package com.arslan_aziz.food_for_thought.fs.model;

import java.net.URL;

/*
 * Represents article document and metadata.
 */
public class Article {
	
	private final String filename;
	private final URL url;
	private final long lastModified;
	private final long contentLength;
	private final String content;
	
	public Article(ArticleBuilder builder) {
		this.filename = builder.filename;
		this.url = builder.url;
		this.lastModified = builder.lastModified;
		this.contentLength = builder.contentLength;
		this.content = builder.content;
	}
	
	public String getFilename() {
		return filename;
	}
	public URL getUrl() {
		return url;
	}
	public long getLastModified() {
		return lastModified;
	}
	public long getContentLength() {
		return contentLength;
	}
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return filename + "; length: " + contentLength;
	}
	
	public static class ArticleBuilder {
		
		private String filename;
		private URL url;
		private long lastModified;
		private long contentLength;
		private String content;
		
		public ArticleBuilder() {}
		
		public ArticleBuilder filename(String filename) {
			this.filename = filename;
			return this;
		}
		public ArticleBuilder url(URL url) {
			this.url = url;
			return this;
		}
		public ArticleBuilder lastModified(long lastModified) {
			this.lastModified = lastModified;
			return this;
		}
		public ArticleBuilder contentLength(long contentLength) {
			this.contentLength = contentLength;
			return this;
		}
		public ArticleBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public Article build() {
			return new Article(this);
		}
	}
	
	
}

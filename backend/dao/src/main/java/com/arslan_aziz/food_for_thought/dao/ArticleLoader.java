package com.arslan_aziz.food_for_thought.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.arslan_aziz.food_for_thought.models.Article;

@Component
public class ArticleLoader implements ResourceLoaderAware {
	
	private ResourceLoader resourceLoader;
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public Article getArticleFromPath(String path) throws IOException {
		Resource resource = resourceLoader.getResource("file:" + path);
		
		InputStream in = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		StringBuilder allLines = new StringBuilder();
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			allLines.append(line);
		}
		reader.close();
		
		// create Article from resource
		Article article = new Article.ArticleBuilder()
				.filename(resource.getFilename())
				.url(resource.getURL())
				.lastModified(resource.lastModified())
				.contentLength(allLines.length())
				.content(allLines.toString())
				.build();
		
		return article;
	}

}

package com.arslan_aziz.food_for_thought.models.graph;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class ArticleGraphDeserializer extends StdDeserializer<ArticleGraph>{
	
	public ArticleGraphDeserializer() {
		this(null);
	}
	
	public ArticleGraphDeserializer(Class<?> vc) {
		super(vc);
	}
	
	@Override
	public ArticleGraph deserialize(JsonParser jp, DeserializationContext ctxt)
		throws IOException, JsonProcessingException {
			
			JsonNode root = jp.getCodec().readTree(jp);
			
			// parse ArticleGraph name field
			String name = (String) ((TextNode) root.get("name")).textValue();
			// parse ArticleGraph id field
			String id = (String) ((TextNode) root.get("id")).textValue();
			// traverse to map of objects
			JsonNode vertices = root.get("graph").get("adjVertices");
			Iterator<Map.Entry<String, JsonNode>> fields = vertices.fields();
			
//			while (fields.hasNext()) {
//				Map.Entry<String, JsonNode> next = fields.next();
//				String vertexSerialized = field.getKey();
//				JsonNode fieldValue = field.getValue();
//			}
			
			return null;
			
	}

}

package com.arslan_aziz.food_for_thought.dao;

import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;

public interface NlpExtractionDao {

	public void addNlpExtraction(NlpExtractionEntity entity);
	public NlpExtractionEntity getNlpExtractionEntityById(Long id);
	public NlpExtractionEntity getNlpExtractionEntityByQueryName(String queryName);
	public void deleteNlpExtractionEntity(Long id);
}

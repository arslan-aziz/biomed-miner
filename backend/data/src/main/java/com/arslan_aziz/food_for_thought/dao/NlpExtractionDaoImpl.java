package com.arslan_aziz.food_for_thought.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arslan_aziz.food_for_thought.model.NlpExtractionEntity;

@Repository
public class NlpExtractionDaoImpl implements NlpExtractionDao{
	
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger(NlpExtractionDaoImpl.class);
	
	@Autowired
	public NlpExtractionDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addNlpExtraction(NlpExtractionEntity entity) {
		// TODO: Consider using AOP to stay DRY!
		logger.info("Saving entity" + entity.toString());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// BEGIN TRANSACTION

		session.save(entity);
		
		// END TRANSACTION
		transaction.commit();
		session.flush();
		session.clear();
		
		
		session.close();
		
	}

	@Override
	public NlpExtractionEntity getNlpExtractionEntityById(Long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// BEGIN TRANSACTION
		
		NlpExtractionEntity entity = (NlpExtractionEntity) session.get(NlpExtractionEntity.class, id);
		
		// END TRANSACTION
		session.flush();
		session.clear();
		transaction.commit();
		
		session.close();
		
		return entity;
	}

	@Override
	public void deleteNlpExtractionEntity(Long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// BEGIN TRANSACTION
		
		session.delete(new NlpExtractionEntity.NlpExtractionEntityBuilder().setId(id).build());
		
		// END TRANSACTION
		session.flush();
		session.clear();
		transaction.commit();
		
		session.close();		
	}

	@Override
	public NlpExtractionEntity getNlpExtractionEntityByQueryName(String queryName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// BEGIN TRANSACTION
		
		Query selectQuery = session.createNamedQuery("NlpExtractionEntity_getByQueryName");
		selectQuery.setParameter("queryname", queryName);
		
		NlpExtractionEntity entity = (NlpExtractionEntity) selectQuery.getResultList().stream()
				.findFirst()
				.orElse(null);
		
		// END TRANSACTION
		session.flush();
		session.clear();
		transaction.commit();
		
		session.close();
		
		return entity;
	}

}

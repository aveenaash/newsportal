package com.cubic.repositories;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.entities.NewsEntity;

@Repository
public class NewsRepository extends AbstractRepository {

	@Transactional
	public void save(NewsEntity newsEntity) {
		entityManager.persist(newsEntity);
	}
	
	public NewsEntity findById(Long id) {
		return entityManager.find(NewsEntity.class, id);
	}
	
	public List<NewsEntity> findByCreator(String creator) {
		Query query = (Query) entityManager.createQuery("select n from NewsEntity n where "
				+ "n.creatorName = :creatorName");
		query.setParameter("creatorName", creator);
		return query.getResultList();
	}
}

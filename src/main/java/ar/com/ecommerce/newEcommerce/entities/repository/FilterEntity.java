package ar.com.ecommerce.newEcommerce.entities.repository;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class FilterEntity<T> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public FilterEntity(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public Collection<T> itemsFiltered(Map<String, String> params, String entity){
		System.out.println(params);
		String queryParams = "";
		
		for(String key : params.keySet()) {
			if (params.get(key) != null && !params.get(key).isEmpty()) {
				queryParams += "cast(p."+key+" as string) LIKE CONCAT(:"+key+",'%') AND ";
			}
		}
		System.out.println("FROM " +entity+ " p WHERE "+queryParams+ " 1 = 1");
		
		
		Query q = entityManager.createQuery("FROM " +entity+ " p WHERE "+queryParams+ " 1 = 1");
		for(String key : params.keySet()) {
			if (params.get(key) != null && !params.get(key).isEmpty()) {
				q.setParameter(key, params.get(key));
			}
		}
		
		return q.getResultList();
	}
	
}

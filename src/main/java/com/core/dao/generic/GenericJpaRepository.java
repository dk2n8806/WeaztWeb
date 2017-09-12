package com.core.dao.generic;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class
        GenericJpaRepository<E, I extends Serializable>
    extends GenericBaseRepository<E, I>
{
	
	// Batch Size
	private final int BATCH_SIZE = 50;

	
    @PersistenceContext
    protected EntityManager entityManager;

    
    @Override
    public int getBatchSize() {    	return BATCH_SIZE;    }
    
    
    

	protected List<E> getResultList(CriteriaQuery<E> query, Pageable pageable) {
		if(pageable != null) {
			return entityManager.createQuery(query)
								.setFirstResult(pageable.getOffset())
								.setMaxResults(pageable.getPageSize())
								.getResultList();
		}
		return entityManager.createQuery(query).getResultList();
	}
	
	
	

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#getIterable()}
 */
    @Override
    public Iterable<E> getIterable()
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(this.entityClass);

        return this.entityManager.createQuery(
                query.select(query.from(this.entityClass))
        ).getResultList();
    }

    

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#getList()}
 */
    @Override
    public List<E> getList() {
    	CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<E> query = builder.createQuery(this.entityClass);
    	return this.entityManager.createQuery(
    										query.select(query.from(this.entityClass))
    										).getResultList();
    }
   
    


   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#findById(Serializable)}
 */
    @Override
    public E findById(I id)
    {
        return this.entityManager.find(this.entityClass, id);
    }

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#persist(Object)}
 */
    @Override
    public void persist(E entity)
    {
        this.entityManager.persist(entity);
    }

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#update(Object)}
 */
    @Override
    public void update(E entity)
    {
        this.entityManager.merge(entity);
    }

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#delete(Object)}
 */
    @Override
    public void delete(E entity)
    {
        this.entityManager.remove(this.entityManager.contains(entity) 
        								? entity : this.entityManager.merge(entity));
    }

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#delete(Object)}
 */
    @Override
    public void deleteById(I id)
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaDelete<E> query = builder.createCriteriaDelete(this.entityClass);

        this.entityManager.createQuery(query.where(
                builder.equal(query.from(this.entityClass).get("id"), id)
        )).executeUpdate();
    }
    

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#getRowCount()}
 */
    @Override
    public  Long getRowCount() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery< Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(this.entityClass)));
		return entityManager.createQuery(query).getSingleResult();
    }
    

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#getReference(Serializable)}
 */
    @Override
    public 
    E getReference(I id) {
    	return entityManager.getReference(this.entityClass, id);
    }
    
    

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#persist(Collection)}
 */
    @Override
    public 
    Collection<E> persist(Collection<E> entities) {
    	final List<E> list = new ArrayList<E>(entities.size());
    	int i = 0;
    	for(E entity : entities) {
    		this.entityManager.persist(entity);
    		list.add(entity);
    		i++;
    		if(i % BATCH_SIZE == 0) {
    			this.entityManager.flush();
    			this.entityManager.clear();
    		}
    	}
    	return list;
    }

   
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#update(Collection)}
 */
    @Override
    public void update(Collection<E> entities) {
    	int i = 0;
    	for(E entity : entities) {
    		this.entityManager.merge(entity);
    		i++;
    		if(i % BATCH_SIZE == 0) {
    			this.entityManager.flush();
    			this.entityManager.clear();
    		}
    	}
    }
    
    
    
    
    
/**********************************************************************
 * 
 * {@link GenericRepository#delete(Collection)}
 */
    @Override
    public void delete(Collection<E> entities) {
    	int i = 0;
    	for(E entity : entities) {
            this.entityManager.remove(this.entityManager.contains(entity) 
					? entity : this.entityManager.merge(entity));
    		i++;
    		if(i % BATCH_SIZE == 0) {
    			this.entityManager.flush();
    			this.entityManager.clear();
    		}
    	}
    }
}

package com.core.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


import org.springframework.validation.annotation.Validated;

@Validated
public interface GenericRepository<E, I extends Serializable>
{
	
	/** :::
	 * Retrieve a proxy object  by a given object_id
	 * {@link GenericJpaRepository#getReference(Serializable)} 
	 * ::: */
	E getReference(I id);
	
	
	/** {@link GenericJpaRepository#getIterable()} */
    Iterable<E> getIterable();
    
    
    /** {@link GenericJpaRepository#getList()} */
    List<E> getList();
   
    /** {@link GenericJpaRepository#findById(Serializable)} */
    E findById(I id);

    
    /** {@link GenericJpaRepository#persist(Object)} */
    void persist(E entity);

    
    /** {@link GenericJpaRepository#persist(Collection)} */
    Collection<E> persist(Collection<E> entities);
    
    
    /** {@link GenericJpaRepository#update(Collection)} */
    @Deprecated    void update(Collection<E> entities);

    
    /** {@link GenericJpaRepository#update(Object)} */
    @Deprecated    void update(E entity);

    
    /** {@link GenericJpaRepository#delete(Collection)} */
    void delete(Collection<E> entities);
    

    
    /** {@link GenericJpaRepository#delete(Object)} */
    void delete(E entity);

    
    /** {@link GenericJpaRepository#deleteById(Serializable)} */
    void deleteById(I id);
    
    /** {@link GenericJpaRepository#getRowCount()} */
    Long getRowCount();
  
    /** {@link GenericJpaRepository#getBatchSize()} */
    int getBatchSize();
}


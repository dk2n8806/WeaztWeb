package com.core.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericBaseRepository<E, I extends Serializable>
implements GenericRepository<E, I>
{
    protected final Class<I> idClass;
    protected final Class<E> entityClass;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public GenericBaseRepository()
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        while(!(genericSuperclass instanceof ParameterizedType))
        {
            if(!(genericSuperclass instanceof Class))
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because generic superclass neither " +
                        "parameterized type nor class.");
            if(genericSuperclass == GenericBaseRepository.class)
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because no parameterized generic superclass " +
                        "found.");

            genericSuperclass = ((Class)genericSuperclass).getGenericSuperclass();
        }

        ParameterizedType type = (ParameterizedType)genericSuperclass;
        Type[] arguments = type.getActualTypeArguments();
        	this.entityClass = (Class<E>)arguments[0];
        	this.idClass = (Class<I>)arguments[1];
    }
}

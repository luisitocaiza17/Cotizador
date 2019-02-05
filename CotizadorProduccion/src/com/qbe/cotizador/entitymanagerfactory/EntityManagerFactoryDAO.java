package com.qbe.cotizador.entitymanagerfactory;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class EntityManagerFactoryDAO<T>{
	private Class<T> entityClass;

	protected abstract EntityManager getEntityManager();
	
	public EntityManagerFactoryDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
	 public T crear(T entity) {
	    getEntityManager().persist(entity);
	    return entity;
	 }

	 public T editar(T entity) {
	    getEntityManager().merge(entity);
	    return entity;
	 }

	 public void eliminar(T entity) {	
	     getEntityManager().remove(getEntityManager().merge(entity));
	 }
	 	
}

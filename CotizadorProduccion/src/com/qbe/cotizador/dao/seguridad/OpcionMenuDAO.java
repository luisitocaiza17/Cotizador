package com.qbe.cotizador.dao.seguridad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.OpcionMenu;

public class OpcionMenuDAO extends EntityManagerFactoryDAO<OpcionMenu>{
	
	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				em = (javax.persistence.EntityManager) initCtx.lookup("java:comp/env/CotizadorWebPC");
			} catch (NamingException e) { 
				e.printStackTrace();
			}		
		}
		return em;
	}

	public OpcionMenuDAO() {
	    super(OpcionMenu.class);
	}

	public List<OpcionMenu> buscarTodos(){
		return getEntityManager().createNamedQuery("OpcionMenu.buscarTodos").getResultList();
	}
	
	public OpcionMenu buscarXId(String id)
	{
		Query q = getEntityManager().createNamedQuery("OpcionMenu.buscarId");
		q.setParameter("id", id);
		return (OpcionMenu) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpcionMenu> buscarPadres()
	{
		Query q = getEntityManager().createQuery("Select o From OpcionMenu o where o.padreId is null");
		return q.getResultList();
		
	}
	
	public List<OpcionMenu> buscarHijos()
	{
		Query q = getEntityManager().createQuery("Select o From OpcionMenu o where o.padreId is not null");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpcionMenu> buscarHijos(String padre)
	{
		Query q = getEntityManager().createQuery("Select o From OpcionMenu o where o.padreId = :padre");
		q.setParameter("padre", padre);
		return q.getResultList();
	}
}

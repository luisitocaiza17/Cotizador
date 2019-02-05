package com.qbe.cotizador.dao.entidad;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Parroquia;

public class ParroquiaDAO extends EntityManagerFactoryDAO<Parroquia>{
	
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
	
	public ParroquiaDAO() {
        super(Parroquia.class);
    }
	
	public List<Parroquia> buscarTodos(){
		return getEntityManager().createNamedQuery("Parroquia.buscarTodos").getResultList();
	}
		
	public Parroquia buscarPorId(String id){
		Parroquia parroquia = new Parroquia();
		List<Parroquia> query = getEntityManager().createNamedQuery("Parroquia.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			parroquia =  query.get(0);
		return parroquia;
	}
	
	public List<Parroquia> buscarPorCanton(Canton canton){
		return getEntityManager().createNamedQuery("Parroquia.buscarPorCanton").setParameter("canton", canton).getResultList();
	}
	
	public Parroquia buscarPorNombre(String nombre){
		Parroquia parroquia = new Parroquia();
		List<Parroquia> query = getEntityManager().createNamedQuery("Parroquia.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			parroquia =  query.get(0);
		return parroquia;
	}
	
	public Parroquia buscarPorNombreYCanton(String nombre, Canton canton){
		Parroquia parroquia = new Parroquia();
		List<Parroquia> query = getEntityManager().createNamedQuery("Parroquia.buscarPorNombreYCanton").setParameter("nombre", nombre).setParameter("canton", canton).getResultList();
		if(!query.isEmpty())
			parroquia =  query.get(0);
		return parroquia;
	}
	
	public Parroquia buscarPorCodigoSBS(String id){
		Parroquia parroquia = new Parroquia();
		List<Parroquia> query = getEntityManager().createNamedQuery("Parroquia.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			parroquia =  query.get(0);
		return parroquia;
	}
}
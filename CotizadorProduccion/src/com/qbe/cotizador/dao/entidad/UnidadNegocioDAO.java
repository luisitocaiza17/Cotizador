package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.UnidadNegocio;

public class UnidadNegocioDAO extends EntityManagerFactoryDAO<UnidadNegocio>{	
	
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
	
	public UnidadNegocioDAO() {
        super(UnidadNegocio.class);
    }
	
	public UnidadNegocio buscarPorId(String id){
		UnidadNegocio unidad = new UnidadNegocio();
		List<UnidadNegocio> query = getEntityManager().createNamedQuery("UnidadNegocio.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);	
		return unidad;
	}
	
	public UnidadNegocio buscarPorIdEnsurance(String unEnsurance){
		UnidadNegocio unidad = new UnidadNegocio();
		List<UnidadNegocio> query = getEntityManager().createNamedQuery("UnidadNegocio.buscarPorIdEnsurance").setParameter("unEnsurance", unEnsurance).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);
		return unidad;
	}
	
	public List<UnidadNegocio> buscarTodos(){
		return getEntityManager().createNamedQuery("UnidadNegocio.buscarTodos").getResultList();		
	}

	public List<UnidadNegocio> buscarActivos(){
		return getEntityManager().createNamedQuery("UnidadNegocio.buscarActivos").setParameter("valorActivo", true).getResultList();	
	}			


	public UnidadNegocio buscarPorNombre(String nombre){
		UnidadNegocio unidad = new UnidadNegocio();
		List<UnidadNegocio> query = getEntityManager().createNamedQuery("UnidadNegocio.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);
		return unidad;
		
	}

}

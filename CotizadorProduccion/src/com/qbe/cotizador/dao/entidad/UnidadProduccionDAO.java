package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UnidadProduccion;

public class UnidadProduccionDAO extends EntityManagerFactoryDAO<UnidadProduccion>{
	
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
	
	public UnidadProduccionDAO() {
        super(UnidadProduccion.class);
    }	
	
	public UnidadProduccion buscarPorId(String id){
		UnidadProduccion unidad = new UnidadProduccion();
		List<UnidadProduccion> query = getEntityManager().createNamedQuery("UnidadProduccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);
		return unidad;
	}
	
	public UnidadProduccion buscarPorIdEnsurance(String upEnsurance){
		UnidadProduccion unidad = new UnidadProduccion();
		List<UnidadProduccion> query = getEntityManager().createNamedQuery("UnidadProduccion.buscarPorIdEnsurance").setParameter("upEnsurance", upEnsurance).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);
		return unidad;
	}
	
	public List<UnidadProduccion> buscarTodos(){
		return getEntityManager().createNamedQuery("UnidadProduccion.buscarTodos").getResultList();
	}

	public List<UnidadProduccion> buscarActivos(){
		return getEntityManager().createNamedQuery("UnidadProduccion.buscarActivos").setParameter("valorActivo", true).getResultList();
	}		

	public UnidadProduccion buscarPorNombre(String nombre){
		UnidadProduccion unidad = new UnidadProduccion();
		List<UnidadProduccion> query = getEntityManager().createNamedQuery("UnidadProduccion.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			unidad =  query.get(0);
		return unidad;
	}
	
	public List<UnidadProduccion> buscarPorUnidadNegocio(UnidadNegocio unidadNegocio){
		return getEntityManager().createNamedQuery("UnidadProduccion.buscarPorUnidadNegocio").setParameter("unidadNegocio", unidadNegocio).getResultList();
	}
}

package com.qbe.cotizador.dao.producto.pymes;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoOcupacion;

public class TipoOcupacionDAO extends EntityManagerFactoryDAO<TipoOcupacion>{
	
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

	public TipoOcupacionDAO() {
	    super(TipoOcupacion.class);
	}

	public List<TipoOcupacion> buscarTodos(){   
		return getEntityManager().createNamedQuery("TipoOcupacion.buscarTodos").getResultList();
	}
	
	public TipoOcupacion buscarPorId(String id){  
		TipoOcupacion tipo = new TipoOcupacion();
		List<TipoOcupacion> query = getEntityManager().createNamedQuery("TipoOcupacion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);		
		return tipo;
	}
	
	public TipoOcupacion buscarPorNombre(String nombre){
		TipoOcupacion tipo = new TipoOcupacion();
		List<TipoOcupacion> query = getEntityManager().createNamedQuery("TipoOcupacion.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);		
		return tipo;
	}
}

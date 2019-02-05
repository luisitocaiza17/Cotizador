package com.qbe.cotizador.dao.inspeccion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoAdicional;
import com.qbe.cotizador.model.Ramo;

public class TipoAdicionalDAO extends EntityManagerFactoryDAO<TipoAdicional>{
	
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

	public TipoAdicionalDAO() {
	    super(TipoAdicional.class);
	}
	
	public List<TipoAdicional> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoAdicional.buscarTodos").getResultList();
	}
	
	public TipoAdicional buscarPorId(String id){
		TipoAdicional tipo = new TipoAdicional();
		List<TipoAdicional> query = getEntityManager().createNamedQuery("TipoAdicional.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public  List<TipoAdicional> buscarPorRamo(Ramo ramo){
		TipoAdicional tipo = new TipoAdicional();
		List<TipoAdicional> query = getEntityManager().createNamedQuery("TipoAdicional.buscarPorRamo").setParameter("ramo", ramo).getResultList();
		
		return query;				
	}
	
	public List<TipoAdicional> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoAdicional.buscarActivos").setParameter("valorActivo", true).getResultList();
	}	
	
	public List<TipoAdicional> buscarActivosPorRamo(Ramo ramo){
		return getEntityManager().createNamedQuery("TipoAdicional.buscarActivosRamo").setParameter("valorActivo", true).setParameter("ramo", ramo).getResultList();
	}
}

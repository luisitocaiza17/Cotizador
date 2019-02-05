package com.qbe.cotizador.dao.inspeccion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoComponente;
import com.qbe.cotizador.model.Ramo;

public class TipoComponenteDAO extends EntityManagerFactoryDAO<TipoComponente>{
	
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

	public TipoComponenteDAO() {
	    super(TipoComponente.class);
	}
	
	public List<TipoComponente> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoComponente.buscarTodos").getResultList();
	}
	
	public TipoComponente buscarPorId(String id){
		TipoComponente tipo = new TipoComponente();
		List<TipoComponente> query = getEntityManager().createNamedQuery("TipoComponente.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public  List<TipoComponente> buscarPorRamo(Ramo ramo){
		TipoComponente tipo = new TipoComponente();
		List<TipoComponente> query = getEntityManager().createNamedQuery("TipoComponente.buscarPorRamo").setParameter("ramo", ramo).getResultList();
		
		return query;				
	}
	
	public List<TipoComponente> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoComponente.buscarActivos").setParameter("valorActivo", true).getResultList();
	}	
	
	public List<TipoComponente> buscarActivosPorRamo(Ramo ramo){
		return getEntityManager().createNamedQuery("TipoComponente.buscarActivosRamo").setParameter("valorActivo", true).setParameter("ramo", ramo).getResultList();
	}
}

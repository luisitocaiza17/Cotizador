package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoInspector;

public class TipoInspectorDAO extends EntityManagerFactoryDAO<TipoInspector>{

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
	
	public TipoInspectorDAO() {
        super(TipoInspector.class);
    }
	
	public TipoInspector buscarPorId(String id){
		TipoInspector tipo = new TipoInspector();
		List<TipoInspector> query = getEntityManager().createNamedQuery("TipoInspector.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;		
	}
	
	public List<TipoInspector> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoInspector.buscarTodos").getResultList();
	}
	
	public List<TipoInspector> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoInspector.buscarActivos").setParameter("valorActivo", true).getResultList();						
	}	
}

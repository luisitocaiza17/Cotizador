package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoUso;

public class TipoUsoDAO extends EntityManagerFactoryDAO<TipoUso>{
	
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
	
	public TipoUsoDAO() {
        super(TipoUso.class);
    }
	
	public List<TipoUso> buscarTodos(){ 
		return getEntityManager().createNamedQuery("TipoUso.buscarTodos").getResultList();
	}
	
	public TipoUso buscarPorId(String id){
		TipoUso tipo = new TipoUso();
		List<TipoUso> query = getEntityManager().createNamedQuery("TipoUso.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);		
		return tipo;
	}
	
	public List<TipoUso> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoUso.buscarTodos").getResultList();
	}

}

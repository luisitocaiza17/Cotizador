package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoDireccion;

public class TipoDireccionDAO extends EntityManagerFactoryDAO<TipoDireccion>{
	
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
	
	public TipoDireccionDAO() {
        super(TipoDireccion.class);
    }
	
	public List<TipoDireccion> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoDireccion.buscarTodos").getResultList();	
	}
		
	public TipoDireccion buscarPorId(String id){
		TipoDireccion tipo = new TipoDireccion();
		List<TipoDireccion> query = getEntityManager().createNamedQuery("TipoDireccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}	
}
	
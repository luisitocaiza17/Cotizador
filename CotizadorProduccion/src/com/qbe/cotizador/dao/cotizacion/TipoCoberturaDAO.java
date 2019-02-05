package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoCobertura;

public class TipoCoberturaDAO extends EntityManagerFactoryDAO<TipoCobertura>{
	
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
	
	public TipoCoberturaDAO() {
        super(TipoCobertura.class);
    }
	
	public List<TipoCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoCobertura.buscarTodos").getResultList();
	}
		
	public TipoCobertura buscarPorId(String id){
		TipoCobertura tipo = new TipoCobertura();
		List<TipoCobertura> query = getEntityManager().createNamedQuery("TipoCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);			
		return tipo;
	}
}
	
package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ConjuntoCobertura;

public class ConjuntoCoberturaDAO extends EntityManagerFactoryDAO<ConjuntoCobertura>{
	
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
	
	public ConjuntoCoberturaDAO() {
		super(ConjuntoCobertura.class);
	}
	
	public List<ConjuntoCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("ConjuntoCobertura.buscarTodos").getResultList();
	}
		
	public ConjuntoCobertura buscarPorId(String id){
		ConjuntoCobertura cobertura = new ConjuntoCobertura();
		List<ConjuntoCobertura> query = getEntityManager().createNamedQuery("ConjuntoCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cobertura =  query.get(0);
		return cobertura;
	}
	
	public void eliminarTodos(){
	  List<ConjuntoCobertura> query = getEntityManager().createNamedQuery("ConjuntoCobertura.eliminarTodos").getResultList();
	}
	
}

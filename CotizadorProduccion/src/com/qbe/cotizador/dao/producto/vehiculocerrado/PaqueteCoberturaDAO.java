package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PaqueteCobertura;

public class PaqueteCoberturaDAO extends EntityManagerFactoryDAO<PaqueteCobertura>{
	
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

	public PaqueteCoberturaDAO() {
	    super(PaqueteCobertura.class);
	}
	
	public List<PaqueteCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("PaqueteCobertura.buscarTodos").getResultList();
	}
	
	public PaqueteCobertura buscarPorId(String id){
		PaqueteCobertura paquete = new PaqueteCobertura();
		List<PaqueteCobertura> query = getEntityManager().createNamedQuery("PaqueteCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			paquete =  query.get(0);	
		return paquete;
	}

}

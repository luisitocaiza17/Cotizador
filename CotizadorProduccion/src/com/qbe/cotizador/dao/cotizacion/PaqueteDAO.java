package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Paquete;

public class PaqueteDAO extends EntityManagerFactoryDAO<Paquete> {
	
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
	
	public PaqueteDAO() {
        super(Paquete.class);
    }

	public List<Paquete> buscarTodos(){
		return getEntityManager().createNamedQuery("Paquete.buscarTodos").getResultList();		
	}
	
	public Paquete buscarPorId(String id){
		Paquete paquete = new Paquete();
		List<Paquete> query = getEntityManager().createNamedQuery("Paquete.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			paquete =  query.get(0);
		return paquete;
	}
	
	public Paquete buscarPorNombre(String nombre){   
		Paquete paquete = new Paquete();
		List<Paquete> query = getEntityManager().createNamedQuery("Paquete.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			paquete =  query.get(0);
		return paquete;		
	}

	public List<Cobertura> buscarCoberturasPorPaquete(Paquete paquete){		 		
	    List<Cobertura> results = null;	    	
	    TypedQuery<Cobertura> query = getEntityManager().createQuery("SELECT c FROM Cobertura c join c.paquetes p WHERE p.id = :id", Cobertura.class).setParameter("id", paquete.getId());    	
	    results = query.getResultList(); 		    	
	      return results;
	}
}

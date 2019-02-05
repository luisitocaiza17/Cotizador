package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Ramo;

public class RamoDAO extends EntityManagerFactoryDAO<Ramo>{

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
	
	public RamoDAO() {
        super(Ramo.class);
    }
	
	public Ramo buscarPorId(String id){
		Ramo ramo = new Ramo();
		List<Ramo> query = getEntityManager().createNamedQuery("Ramo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			ramo =  query.get(0);			
		return ramo;
	}
	
	public List<Ramo> buscarTodos(){
		return getEntityManager().createNamedQuery("Ramo.buscarTodos").getResultList();		
	}

	public List<Ramo> buscarActivos(){
		return getEntityManager().createNamedQuery("Ramo.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}		
	
	public Ramo buscarPorNemotecnico(String nemotecnico){
		Ramo ramo = new Ramo();
		List<Ramo> query = getEntityManager().createNamedQuery("Ramo.buscarPorNemotecnico").setParameter("nemotecnico", nemotecnico).getResultList();
		if(!query.isEmpty())
			ramo =  query.get(0);
		return ramo;		
	}
}

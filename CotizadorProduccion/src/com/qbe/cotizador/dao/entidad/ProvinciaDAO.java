package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Provincia;

public class ProvinciaDAO extends EntityManagerFactoryDAO<Provincia>{
	
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
	
	public ProvinciaDAO() {
        super(Provincia.class);
    }
	
	public List<Provincia> buscarTodos(){
		return getEntityManager().createNamedQuery("Provincia.buscarTodos").getResultList();
	}
		
	public Provincia buscarPorId(String id){
		Provincia provincia = new Provincia();
		List<Provincia> query = getEntityManager().createNamedQuery("Provincia.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			provincia =  query.get(0);
		return provincia;		
	}
	
	public Provincia buscarPorNombre(String nombre){
		Provincia provincia = new Provincia();
		List<Provincia> query = getEntityManager().createNamedQuery("Provincia.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			provincia =  query.get(0);
		return provincia;		
	}
	public Provincia buscarPorCodigoSBS(String id){
		Provincia provincia = new Provincia();
		List<Provincia> query = getEntityManager().createNamedQuery("Provincia.buscarCodigoSBS").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			provincia =  query.get(0);
		return provincia;		
	}
}
package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Zona;

public class ZonaDAO extends EntityManagerFactoryDAO<Zona>{
	
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
	
	public ZonaDAO() {
        super(Zona.class);
    }
	
	public List<Zona> buscarTodos(){
		return getEntityManager().createNamedQuery("Zona.buscarTodos").getResultList();
	}
	
	public Zona buscarPorId(String id){
		Zona zona = new Zona();
		List<Zona> query = getEntityManager().createNamedQuery("Zona.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			zona =  query.get(0);
		return zona;
	}	
	
	public Zona buscarPorNombre(String nombre){
		Zona zona = new Zona();
		List<Zona> query = getEntityManager().createNamedQuery("Zona.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			zona =  query.get(0);
		return zona;
	}
}
	
package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Modulo;

public class ModuloDAO extends EntityManagerFactoryDAO<Modulo>{

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

	public ModuloDAO() {
	    super(Modulo.class);
	}
		
	public List<Modulo> buscarTodos(){
		return getEntityManager().createNamedQuery("Modulo.buscarTodos").getResultList();	
	}

	public Modulo buscarPorId(String id){
		Modulo modulo = new Modulo();
		List<Modulo> query = getEntityManager().createNamedQuery("Modulo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			modulo =  query.get(0);
		return modulo;
	}
	
	public List<Modulo> buscarActivos(){
		return getEntityManager().createNamedQuery("Modulo.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public Modulo buscarPorNombre(String nombre){
		Modulo modulo = new Modulo();
		List<Modulo> query = getEntityManager().createNamedQuery("Modulo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			modulo =  query.get(0);
		return modulo;
	}
}

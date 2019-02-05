package com.qbe.cotizador.dao.seguridad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Modulo;
import com.qbe.cotizador.model.NivelMenu;

public class NivelMenuDAO extends EntityManagerFactoryDAO<NivelMenu>{
	
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

	public NivelMenuDAO() {
	    super(NivelMenu.class);
	}
	
	public List<NivelMenu> buscarTodos(){
		return getEntityManager().createNamedQuery("NivelMenu.buscarTodos").getResultList();
	}
	
	public NivelMenu buscarPorId(String id){
		NivelMenu nivel = new NivelMenu();
		List<NivelMenu> query = getEntityManager().createNamedQuery("NivelMenu.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			nivel =  query.get(0);
		return nivel;
	}
	
}

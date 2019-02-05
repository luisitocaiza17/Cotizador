package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AutorizacionSri;;

public class AutorizacionSriDAO extends EntityManagerFactoryDAO<AutorizacionSri>{
	
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
	
	public AutorizacionSriDAO() {
		super(AutorizacionSri.class);
	}
	
	public List<AutorizacionSri> buscarTodos(){
		return getEntityManager().createNamedQuery("AutorizacionSri.buscarTodos").getResultList();
	}
	
	public AutorizacionSri buscarPorId(String id){
		AutorizacionSri autorizacion = new AutorizacionSri();
		List<AutorizacionSri> query = getEntityManager().createNamedQuery("AutorizacionSri.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);
		return autorizacion;
	}
	
	public AutorizacionSri buscarPorIdEnsurance(String idEnsurance){
		AutorizacionSri autorizacion = new AutorizacionSri();
		List<AutorizacionSri> query = getEntityManager().createNamedQuery("AutorizacionSri.buscarPorIdEnsurance").setParameter("idEnsurance", idEnsurance).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);
		return autorizacion;
	}
	
	public AutorizacionSri buscarActivo(){
		AutorizacionSri autorizacion = new AutorizacionSri();
		List<AutorizacionSri> query = getEntityManager().createNamedQuery("AutorizacionSri.buscarActivos").setParameter("valorActivo", true).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);
		return autorizacion;
	}
}

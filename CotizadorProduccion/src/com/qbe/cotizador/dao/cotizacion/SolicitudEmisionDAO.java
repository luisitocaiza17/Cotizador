package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.SolicitudEmision;

public class SolicitudEmisionDAO extends EntityManagerFactoryDAO<SolicitudEmision>{
	
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
	
	public SolicitudEmisionDAO() {
        super(SolicitudEmision.class);
    }
	
	public List<SolicitudEmision> buscarTodos(){
		return getEntityManager().createNamedQuery("SolicitudEmision.buscarTodos").getResultList();				
	}
	
	public SolicitudEmision buscarPorId(String id){
		SolicitudEmision solicitud = new SolicitudEmision();
		List<SolicitudEmision> query = getEntityManager().createNamedQuery("SolicitudEmision.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			solicitud =  query.get(0);		
		return solicitud;		
	}

	public List<SolicitudEmision> buscarPorCotizacionId(String cotizacionId){
		return getEntityManager().createNamedQuery("SolicitudEmision.buscarPorCotizacionId").setParameter("cotizacionId", cotizacionId).getResultList();
	}
}
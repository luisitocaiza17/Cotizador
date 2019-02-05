package com.qbe.cotizador.dao.inspeccion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ComentarioSolicitudInspeccion;
import com.qbe.cotizador.model.SolicitudInspeccion;

public class ComentarioSolicitudInspeccionDAO extends EntityManagerFactoryDAO <ComentarioSolicitudInspeccion>{
	
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
	
	public ComentarioSolicitudInspeccionDAO() {
        super(ComentarioSolicitudInspeccion.class);
    }
	
	public List<ComentarioSolicitudInspeccion> buscarTodos(){
		return getEntityManager().createNamedQuery("ComentarioSolicitudInspeccion.buscarTodos").getResultList();
	}
	
	public ComentarioSolicitudInspeccion buscarPorId(String id){
		ComentarioSolicitudInspeccion comentario = new ComentarioSolicitudInspeccion();
		List<ComentarioSolicitudInspeccion> query = getEntityManager().createNamedQuery("ComentarioSolicitudInspeccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			comentario =  query.get(0);
		return comentario;
	}
	
	public List<ComentarioSolicitudInspeccion> buscarPorSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion){
		return getEntityManager().createNamedQuery("ComentarioSolicitudInspeccion.buscarPorSolicitudInspeccion").setParameter("solicitudInspeccion", solicitudInspeccion).getResultList();
	}
}

package com.qbe.cotizador.dao.inspeccion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ArchivoSolicitudInspeccion;
import com.qbe.cotizador.model.SolicitudInspeccion;

public class ArchivoSolicitudInspeccionDAO extends EntityManagerFactoryDAO<ArchivoSolicitudInspeccion>{
	
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
	
	public ArchivoSolicitudInspeccionDAO() {
        super(ArchivoSolicitudInspeccion.class);
    } 
	
	public List<ArchivoSolicitudInspeccion> buscarTodos(){
		return getEntityManager().createNamedQuery("ArchivoSolicitudInspeccion.buscarTodos").getResultList();
	}
	
	
	public ArchivoSolicitudInspeccion buscarPorId(String id){
		ArchivoSolicitudInspeccion inspeccion = new ArchivoSolicitudInspeccion();
		List<ArchivoSolicitudInspeccion> query = getEntityManager().createNamedQuery("ArchivoSolicitudInspeccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			inspeccion =  query.get(0);		
		return inspeccion;
	}
	
	public List<ArchivoSolicitudInspeccion> buscarPorSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion){
		return getEntityManager().createNamedQuery("ArchivoSolicitudInspeccion.buscarPorSolicitudInspeccion").setParameter("solicitudInspeccion", solicitudInspeccion).getResultList();
	}
}

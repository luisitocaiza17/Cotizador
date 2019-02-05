package com.qbe.cotizador.dao.inspeccion;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.model.UsuarioInspector;

public class SolicitudInspeccionDAO extends EntityManagerFactoryDAO<SolicitudInspeccion>{
	
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
	
	public SolicitudInspeccionDAO() {
        super(SolicitudInspeccion.class);
    }
	
	public List<SolicitudInspeccion> buscarTodos(){
		return getEntityManager().createNamedQuery("SolicitudInspeccion.buscarTodos").getResultList();
	}
	
	public SolicitudInspeccion buscarPorId(String id){
		SolicitudInspeccion inspeccion = new SolicitudInspeccion();
		List<SolicitudInspeccion> query = getEntityManager().createNamedQuery("SolicitudInspeccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			inspeccion =  query.get(0);
		return inspeccion;
	}
	
	public SolicitudInspeccion buscarPorCotizacion(Cotizacion cotizacion){
		SolicitudInspeccion inspeccion = new SolicitudInspeccion();
		List<SolicitudInspeccion> query = getEntityManager().createNamedQuery("SolicitudInspeccion.buscarPorCotizacion").setParameter("cotizacion", cotizacion).getResultList();
		if(!query.isEmpty())
			inspeccion =  query.get(0);
		return inspeccion;
	}
	
	public List<SolicitudInspeccion> buscarPorEstado(Estado estado){		
		List<SolicitudInspeccion> results = new ArrayList<SolicitudInspeccion>();		
		    
		TypedQuery<SolicitudInspeccion> query =  getEntityManager().createNamedQuery("SolicitudInspeccion.buscarPorEstado", SolicitudInspeccion.class).setParameter("estado", estado);
		results = query.getResultList(); 	 					
		return results;
	}
	
	public List<SolicitudInspeccion> buscarPorEstadoCotizacion(Estado estado,Cotizacion cotizacion){		
		List<SolicitudInspeccion> results = new ArrayList<SolicitudInspeccion>();		
		Date utilDate = new Date();
		Timestamp fechaValidacion = new Timestamp(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaValidacion);
		calendar.add(7, -30); // Solicitudes de descuento mayores a treinta dias de caducidad
		fechaValidacion.setTime(calendar.getTime().getTime());
		    
		TypedQuery<SolicitudInspeccion> query =  getEntityManager().createQuery("SELECT c FROM SolicitudInspeccion c where c.cotizacion=:cotizacion and c.estado=:estado", SolicitudInspeccion.class).setParameter("estado", estado).setParameter("cotizacion", cotizacion);
		results = query.getResultList(); 	 					
		return results;
	}
	
	public List<SolicitudInspeccion> buscarPorEstadoDias(Estado estado, int dias){		
		List<SolicitudInspeccion> results = new ArrayList<SolicitudInspeccion>();		
		Date utilDate = new Date();
		Timestamp fechaValidacion = new Timestamp(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaValidacion);
		calendar.add(7, -dias); // Solicitudes de descuento mayores a treinta dias de caducidad
		fechaValidacion.setTime(calendar.getTime().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp formatoFecha = null;
		formatoFecha = fechaValidacion;
		    
		TypedQuery<SolicitudInspeccion> query =  getEntityManager().createQuery("SELECT c FROM SolicitudInspeccion c where c.estado = :estado and c.cotizacion.fechaElaboracion > :fechaElaboracion ", SolicitudInspeccion.class).setParameter("estado", estado).setParameter("fechaElaboracion", formatoFecha);
		results = query.getResultList(); 	 					
		return results;
	}
	
//	public List<SolicitudInspeccion> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<SolicitudInspeccion> results = null;
//		try{	
//			TypedQuery<SolicitudInspeccion> query = em.createQuery("SELECT c FROM SolicitudInspeccion c WHERE c.activo =:valorActivo", SolicitudInspeccion.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}

	public List<SolicitudInspeccion> buscarEstadoInspector(Estado estado, Inspector inspector){
		return getEntityManager().createNamedQuery("SolicitudInspeccion.buscarEstadoInspector").setParameter("estado", estado).setParameter("inspector", inspector).getResultList();
	}
	
	public List<SolicitudInspeccion> buscarEstadoUsuarioInspector(Estado estado, UsuarioInspector usuarioInspector){
		return getEntityManager().createNamedQuery("SolicitudInspeccion.buscarEstadoUsuarioInspector").setParameter("estado", estado).setParameter("usuarioInspector", usuarioInspector).getResultList();
	}
}

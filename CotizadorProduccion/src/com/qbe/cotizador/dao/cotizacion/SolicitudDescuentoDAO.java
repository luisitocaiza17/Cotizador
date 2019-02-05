package com.qbe.cotizador.dao.cotizacion;

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
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.SolicitudDescuento;

public class SolicitudDescuentoDAO extends EntityManagerFactoryDAO<SolicitudDescuento>{
	
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
	
	public SolicitudDescuentoDAO() {
        super(SolicitudDescuento.class);
    }
	
	public SolicitudDescuento actualizarEstado(String codigo,String nombreEstado){
		UserTransaction ut = null;
		SolicitudDescuento solicitud = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			SolicitudDescuentoDAO solicitudDAO = new SolicitudDescuentoDAO(); 
			EstadoDAO estadoDAO= new EstadoDAO() ;
			Estado estado = estadoDAO.buscarPorNombre(nombreEstado, "Descuento");
			solicitud= solicitudDAO.buscarPorId(codigo);
			if(solicitud!= null){
				solicitud.setEstado(estado);			
				getEntityManager().merge(solicitud);
			}
			ut.commit();
			}catch(Exception e) {
				try {
					ut.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();			    
			}					
		return solicitud;							
	}  
	
	public List<SolicitudDescuento> buscarTodos(){   
		return getEntityManager().createNamedQuery("SolicitudDescuento.buscarTodos").getResultList();
	}
	
	public List<SolicitudDescuento> buscarTodosPendientes(){		
		List<SolicitudDescuento> results = new ArrayList<SolicitudDescuento>();		
		Date utilDate = new Date();
		Timestamp fechaValidacion = new Timestamp(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaValidacion);
		calendar.add(7, -60); // Solicitudes de descuento mayores a treinta dias de caducidad
		fechaValidacion.setTime(calendar.getTime().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date formatoFecha = null;
		try {
			formatoFecha = format.parse(fechaValidacion.toString());
		} catch (ParseException e) {			
			e.printStackTrace();
		}		    
		TypedQuery<SolicitudDescuento> query = getEntityManager().createQuery("SELECT c FROM SolicitudDescuento c WHERE c.cotizacion.fechaElaboracion > '" + format.format(formatoFecha) + "'", SolicitudDescuento.class);
		results = query.getResultList(); 	 			
		
		return results;
	}
		
	public SolicitudDescuento buscarPorId(String id){
		SolicitudDescuento descuento = new SolicitudDescuento();
		List<SolicitudDescuento> query = getEntityManager().createNamedQuery("SolicitudDescuento.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;		
	}
	
	public SolicitudDescuento buscarPorCotizacion(Cotizacion cotizacion, Estado estado){
		SolicitudDescuento descuento = new SolicitudDescuento();
		List<SolicitudDescuento> query = getEntityManager().createNamedQuery("SolicitudDescuento.buscarPorCotizacionEstado").setParameter("cotizacion", cotizacion).setParameter("estado", estado).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;		
	}
	
	public SolicitudDescuento buscarPorCotizacion(Cotizacion cotizacion){
		SolicitudDescuento descuento = new SolicitudDescuento();
		List<SolicitudDescuento> query = getEntityManager().createNamedQuery("SolicitudDescuento.buscarPorCotizacion").setParameter("cotizacion", cotizacion).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;		
	}
	
	public void eliminarPorCotizacion(Cotizacion cotizacion){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			getEntityManager().createQuery("DELETE FROM SolicitudDescuento c WHERE c.cotizacion =:cotizacion", SolicitudDescuento.class).setParameter("cotizacion", cotizacion).executeUpdate();
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}			
	}
	
	public List<SolicitudDescuento> buscarTodosPendientesXCargo(List<String> cargos){		
		List<SolicitudDescuento> results = new ArrayList<SolicitudDescuento>();		
		Date utilDate = new Date();
		Timestamp fechaValidacion = new Timestamp(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaValidacion);
		calendar.add(7, -30); // Solicitudes de descuento mayores a treinta dias de caducidad
		fechaValidacion.setTime(calendar.getTime().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date formatoFecha = null;
		try {
			formatoFecha = format.parse(fechaValidacion.toString());
		} catch (ParseException e) {			
			e.printStackTrace();
		}		    
		TypedQuery<SolicitudDescuento> query = getEntityManager().createQuery("SELECT c FROM SolicitudDescuento c WHERE c.descuento.cargoAutoriza in :cargos and c.cotizacion.fechaElaboracion > '" + format.format(formatoFecha) + "'", SolicitudDescuento.class);
		query.setParameter("cargos", cargos);
		results = query.getResultList(); 	 			
		
		return results;
	}
	
}

package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCotizacionResumen;
import com.qbe.cotizador.model.Estado;

public class CotizacionPymeResumenDAO extends EntityManagerFactoryDAO<PymeCotizacionResumen>{
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
	public CotizacionPymeResumenDAO() {
        super(PymeCotizacionResumen.class);
    }
	
	public PymeCotizacionResumen buscarPorId(String id){		
		PymeCotizacionResumen cotizacion = new PymeCotizacionResumen();
		List<PymeCotizacionResumen> query = getEntityManager().createNamedQuery("PymeCotizacionResumen.buscarCotizacionId").setParameter("id", new BigInteger(id)).getResultList();
		if(!query.isEmpty())
			cotizacion =  query.get(0);
		return cotizacion;
	}
	
	
	
	
	
	public List<PymeCotizacionResumen> consultarPorEstado(String fInicio,
			String fFinal, String cotizacionId,
			String puntoVentaId, String agenteId, String identificacion,
			String usuarioId,  String apellidos,  Estado estadoCotizacion,int Skip, int Take){ 
		
		String f1 = fInicio + " 00:05:00";
		String f2 = fFinal + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
				
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fInicio.length()>0 && fFinal.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
	
			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
		}
		Estado estado = new Estado();
		Estado estado2 = new Estado();
		
		if(estadoCotizacion.getId()!=null){
			estado = estadoCotizacion;
			estado2 = estadoCotizacion;			
		}
		
		
		TypedQuery<PymeCotizacionResumen> query = null;
		
		String stringQuery= "SELECT c FROM PymeCotizacionResumen c where c.tipoObjetoId = '3'";					
		String valoresWhereQuery = "";
		if(fInicio.length()>0 && fFinal.length()>0)
			valoresWhereQuery += " and c.fechaCotizacion BETWEEN :startDate AND :endDate ";
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){
			valoresWhereQuery += " AND c.id=:cotiID ";
		}
		if(puntoVentaId.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
		if(agenteId.length()>0)
			valoresWhereQuery += " AND c.agenteId =:agenteId ";
		if(identificacion.length()>0)
			valoresWhereQuery += " AND c.identificacionCliente =:Identificacion ";
		if(apellidos.length()>0)
			valoresWhereQuery += " AND c.nombreCliente LIKE :ApellidoCliente ";
		if(usuarioId.length()>0)
			valoresWhereQuery += " AND c.usuarioId =:usuarioId ";
		if(estadoCotizacion.getId() != null)
			valoresWhereQuery += " AND (c.estadoId =:estadoId ) ";
					
		stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.id, c.fechaCotizacion DESC ";
		
		query = getEntityManager().createQuery(stringQuery, PymeCotizacionResumen.class);
		if(estadoCotizacion.getId() != null){
			query.setParameter("estadoId",new BigInteger(estado.getId()));
			
		}
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){			
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));			
		}

		if(fInicio.length()>0 && fFinal.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		
		if(puntoVentaId.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVentaId));
		}		
		if(agenteId.length()>0)
			query.setParameter("agenteId", new BigInteger(agenteId));
		if(identificacion.length()>0)
			query.setParameter("Identificacion", identificacion);
		if(apellidos.length()>0)
			query.setParameter("ApellidoCliente", "%"+apellidos+"%");
		if(usuarioId.length()>0)
			query.setParameter("usuarioId", new BigInteger(usuarioId));
		
			
		return query.setMaxResults(Take).setFirstResult(Skip).getResultList();
	}
	
	public long consultarPorEstadoContador(String fInicio,
			String fFinal, String cotizacionId,
			String puntoVentaId, String agenteId, String identificacion,
			String usuarioId, String tieneImpresion,String nroTramite, String apellidos, String canalId, Estado estadoCotizacion,int Skip, int Take){ 
						
		String f1 = fInicio + " 00:05:00";
		String f2 = fFinal + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
				
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fInicio.length()>0 && fFinal.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
	
			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
		}
		Estado estado = new Estado();
		Estado estado2 = new Estado();		
		
		if(estadoCotizacion.getId() != null){
			estado = estadoCotizacion;
			estado2 = estadoCotizacion;
		}
		
		Query query = null;	
				
		String stringQuery= "SELECT count(c.id) FROM PymeCotizacionResumen c where c.tipoObjetoId = '3'";					
		String valoresWhereQuery = "";
		if(fInicio.length()>0 && fFinal.length()>0)
			valoresWhereQuery += " AND c.fechaCotizacion BETWEEN :startDate AND :endDate ";
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){			
			valoresWhereQuery += " AND c.id=:cotiID ";
		}
		if(puntoVentaId.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
		if(agenteId.length()>0)
			valoresWhereQuery += " AND c.agenteId =:agenteId ";
		
		if(identificacion.length()>0)
			valoresWhereQuery += " AND c.identificacionCliente =:Identificacion ";
		if(apellidos.length()>0)
			valoresWhereQuery += " AND c.nombreCliente LIKE :ApellidoCliente ";
		if(usuarioId.length()>0)
			valoresWhereQuery += " AND c.usuarioId =:usuarioId ";
		
		
		if(estadoCotizacion.getId() != null)
			valoresWhereQuery += " AND (c.estadoId =:estadoId) ";
				
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery);	
		
		if(estadoCotizacion.getId() != null){
			query.setParameter("estadoId",new BigInteger(estado.getId()));
			
		}
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));			
		}

		if(fInicio.length()>0 && fFinal.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		
		if(puntoVentaId.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVentaId));
		}		
		if(agenteId.length()>0)
			query.setParameter("agenteId", new BigInteger(agenteId));
		
		if(identificacion.length()>0)
			query.setParameter("Identificacion", identificacion);
		if(apellidos.length()>0)
			query.setParameter("ApellidoCliente", "%"+apellidos+"%");
		if(usuarioId.length()>0)
			query.setParameter("usuarioId", new BigInteger(usuarioId));
			
		
		long total = (long) query.getSingleResult();
		return total;
	}
	
	public List<PymeCotizacionResumen> consultarCotizacion(String fInicio,
			String fFinal, String cotizacionId,
			String puntoVentaId, String agenteId, String identificacion,
			String usuarioId, String tieneImpresion,String nroTramite, String apellidos, String canalId, String estadoCotizacion, String tieneObservaciones,int Skip, int Take){ 
		
		String f1 = fInicio + " 00:05:00";
		String f2 = fFinal + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fInicio.length()>0 && fFinal.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
	
			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
		}
		Estado estado = new Estado();
		Estado estado2 = new Estado();
		EstadoDAO estadoDAO = new EstadoDAO();
		
		if(estadoCotizacion.equals("CPA"))
		{
			estado = estadoDAO.buscarPorNombreClase("Pendiente Aprobar","Cotizacion");
		}
		else if(estadoCotizacion.equals("CPE"))
		{
			estado = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
		}else if(estadoCotizacion.equals("CA"))		{
			estado = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
		}else if(estadoCotizacion.equals("CEPE"))		{
			estado = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
		}else if(estadoCotizacion.equalsIgnoreCase("CE")){
			estado = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
		}
		
		
		TypedQuery<PymeCotizacionResumen> query = null;
		
		String stringQuery= "SELECT c FROM PymeCotizacionResumen c where (c.estadoId =:estadoId) ";					
		String valoresWhereQuery = "";
		
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){
			cotiID = new BigInteger (cotizacionId.toString());
			//cotiID = cotizacionId;
			valoresWhereQuery += " AND c.id=:cotiID ";
			}
		if(fInicio.length()>0 && fFinal.length()>0)
			valoresWhereQuery += " AND c.fechaCotizacion BETWEEN :startDate AND :endDate ";
		
		if(puntoVentaId.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
		if(agenteId.length()>0)
			valoresWhereQuery += " AND c.agenteId =:agenteId ";
		
		if(identificacion.length()>0)
			valoresWhereQuery += " AND c.identificacionCliente =:Identificacion ";
		if(apellidos.length()>0)
			valoresWhereQuery += " AND c.nombreCliente LIKE :ApellidoCliente ";
		if(usuarioId.length()>0)
			valoresWhereQuery += " AND c.UsuarioId =:usuarioId ";
		
		
		
		
		stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaSiembra DESC ";
		
		query = getEntityManager().createQuery(stringQuery, PymeCotizacionResumen.class);
		query.setParameter("estadoId",new BigInteger(estado.getId()));
		
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));
			
		}
		
		if(fInicio.length()>0 && fFinal.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		
		if(puntoVentaId.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVentaId));
		}
		
		if(agenteId.length()>0)
			query.setParameter("agenteId", new BigInteger(agenteId));
		
		if(identificacion.length()>0)
			query.setParameter("Identificacion", identificacion);
		if(apellidos.length()>0)
			query.setParameter("ApellidoCliente", "%"+apellidos+"%");
		if(usuarioId.length()>0)
			query.setParameter("usuarioId", new BigInteger(usuarioId));
		
		
		return query.setMaxResults(Take).setFirstResult(Skip).getResultList();
	}	
	
	public long consultarCotizacionContador(String fInicio,
			String fFinal, String cotizacionId,
			String puntoVentaId, String agenteId, String identificacion,
			String usuarioId, String tieneImpresion,String nroTramite, String apellidos, String canalId, String estadoCotizacion, String tieneObservaciones,int Skip, int Take){ 
		
		String f1 = fInicio + " 00:05:00";
		String f2 = fFinal + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
				
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fInicio.length()>0 && fFinal.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
	
			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
		}
		Estado estado = new Estado();
		Estado estado2 = new Estado();
		EstadoDAO estadoDAO = new EstadoDAO();
		
		if(estadoCotizacion.equals("CPA"))
		{
			estado = estadoDAO.buscarPorNombreClase("Pendiente Aprobar","Cotizacion");
		}
		else if(estadoCotizacion.equals("CPE"))
		{
			estado = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
		}else if(estadoCotizacion.equals("CA"))		{
			estado = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
		}else if(estadoCotizacion.equals("CEPE"))		{
			estado = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
		}else if(estadoCotizacion.equalsIgnoreCase("CE")){
			estado = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			estado2 = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
		}
		
		Query query = null;
		
		String stringQuery= "SELECT count(c.id) FROM PymeCotizacionResumen c where (c.estadoId =:estadoId) ";
		
		String valoresWhereQuery = "";
		
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){			
			valoresWhereQuery += " AND c.id=:cotiID ";
		}
		if(fInicio.length()>0 && fFinal.length()>0)
			valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate ";
		
		if(puntoVentaId.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
		if(agenteId.length()>0)
			valoresWhereQuery += " AND c.agenteId =:agenteId ";
		
		if(identificacion.length()>0)
			valoresWhereQuery += " AND c.identificacionCliente =:Identificacion ";
		if(apellidos.length()>0)
			valoresWhereQuery += " AND c.nombreCliente LIKE :ApellidoCliente ";
		if(usuarioId.length()>0)
			valoresWhereQuery += " AND c.usuarioId =:usuarioId ";
		
		
		
		stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaCotizacion DESC ";
		
		query = getEntityManager().createQuery(stringQuery, PymeCotizacionResumen.class);
		query.setParameter("estadoId",new BigInteger(estado.getId()));
		
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			//cotiID = new BigInteger (cotizacionId);
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));
			//query.setParameter("cotiID", cotizacionId.toString());
		}
		
		if(fInicio.length()>0 && fFinal.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		
		if(puntoVentaId.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVentaId));
		}
		
		if(agenteId.length()>0)
			query.setParameter("agenteId", new BigInteger(agenteId));
		
		if(identificacion.length()>0)
			query.setParameter("Identificacion", identificacion);
		if(apellidos.length()>0)
			query.setParameter("ApellidoCliente", "%"+apellidos+"%");
		if(usuarioId.length()>0)
			query.setParameter("usuarioId", new BigInteger(usuarioId));
			
		
		long results = (long)query.getSingleResult();
		return results;
	}	
}

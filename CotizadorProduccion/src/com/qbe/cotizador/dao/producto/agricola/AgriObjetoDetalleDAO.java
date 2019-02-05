package com.qbe.cotizador.dao.producto.agricola;

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
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriAgencia;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoDetalle;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;

public class AgriObjetoDetalleDAO extends EntityManagerFactoryDAO<AgriObjetoDetalle>{
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
	public AgriObjetoDetalleDAO() {
		// TODO Auto-generated constructor stub
		super(AgriObjetoDetalle.class);
	}
	
	public List<AgriObjetoDetalle> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriObjetoDetalle.findAll", AgriObjetoDetalle.class).getResultList();
	}

	public AgriObjetoDetalle buscarPorId(int id){
		AgriObjetoDetalle objetoAgencia = new AgriObjetoDetalle();
		List <AgriObjetoDetalle>results = getEntityManager().createNamedQuery("AgriObjetoDetalle.buscarId", AgriObjetoDetalle.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			objetoAgencia = results.get(0);
		return objetoAgencia;
	}
	
	public List<BigInteger> buscarPorCotizacionesIds(String fecha1, String fecha2,String cotizacionId,String puntoVenta, 
			String NroTramite,String Identificacion,String CanalId, int Skip, int Take){ 
		
		EntidadDAO entidadDAO = new EntidadDAO();
		Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(Identificacion.trim());
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente= clienteDAO.buscarPorEntidadId(entidad);
		
		
		String f1 = fecha1 + " 00:05:00";
		String f2 = fecha2 + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fecha1.length()>0 && fecha1.length()>0){
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
		
		List<BigInteger> results = new ArrayList<BigInteger>();
		TypedQuery<BigInteger> query = null;
		
		String stringQuery= "SELECT DISTINCT(c.cotizacionId) FROM AgriObjetoDetalle c where (c.estado IS NOT NULL)";					
		
		String valoresWhereQuery = "";
		
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){
			cotiID = new BigInteger (cotizacionId.toString());
			//cotiID = cotizacionId;
			valoresWhereQuery += " AND c.cotizacionId=:cotiID";
			}
		if(fecha1.length()>0 && fecha2.length()>0)
			valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate";
		if(CanalId.length()>0)
			valoresWhereQuery += " AND c.canalId =:CanalId";
		if(puntoVenta.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta";
		if(NroTramite.length()>0)
			valoresWhereQuery += " AND c.numeroTramite =:NroTramite";
		if(Identificacion.length()>0)
			valoresWhereQuery += " AND c.clienteId =:Identificacion";
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery, BigInteger.class);
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			//cotiID = new BigInteger (cotizacionId);
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));
			//query.setParameter("cotiID", cotizacionId.toString());
		}
		
		if(fecha1.length()>0 && fecha2.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(CanalId.length()>0)
			query.setParameter("CanalId", new BigInteger(CanalId));
		if(puntoVenta.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVenta));
		}
		
		if(NroTramite.length()>0)
			query.setParameter("NroTramite", NroTramite);
		if(Identificacion.length()>0)
			query.setParameter("Identificacion", new BigInteger(cliente.getId()));
			
		results = query.getResultList();
		return results;
	}
	
	public List<AgriObjetoDetalle> buscarPorCotizaciones(String fecha1, String fecha2,String cotizacionId,String puntoVenta, 
			String NroTramite,String Identificacion,String CanalId, int Skip, int Take){ 
		
		EntidadDAO entidadDAO = new EntidadDAO();
		Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(Identificacion.trim());
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente= clienteDAO.buscarPorEntidadId(entidad);
		
		
		String f1 = fecha1 + " 00:05:00";
		String f2 = fecha2 + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fecha1.length()>0 && fecha1.length()>0){
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
		
		List<AgriObjetoDetalle> results = new ArrayList<AgriObjetoDetalle>();
		TypedQuery<AgriObjetoDetalle> query = null;
		
		String stringQuery= "SELECT c FROM AgriObjetoDetalle c where (c.estado IS NOT NULL)";					
		
		String valoresWhereQuery = "";
		
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){
			cotiID = new BigInteger (cotizacionId.toString());
			//cotiID = cotizacionId;
			valoresWhereQuery += " AND c.cotizacionId=:cotiID";
			}
		if(fecha1.length()>0 && fecha2.length()>0)
			valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate";
		if(CanalId.length()>0)
			valoresWhereQuery += " AND c.canalId =:CanalId";
		if(puntoVenta.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta";
		if(NroTramite.length()>0)
			valoresWhereQuery += " AND c.numeroTramite =:NroTramite";
		if(Identificacion.length()>0)
			valoresWhereQuery += " AND c.clienteId =:Identificacion";
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery, AgriObjetoDetalle.class);
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			//cotiID = new BigInteger (cotizacionId);
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));
			//query.setParameter("cotiID", cotizacionId.toString());
		}
		
		if(fecha1.length()>0 && fecha2.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(CanalId.length()>0)
			query.setParameter("CanalId", new BigInteger(CanalId));
		if(puntoVenta.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVenta));
		}
		
		if(NroTramite.length()>0)
			query.setParameter("NroTramite", NroTramite);
		if(Identificacion.length()>0)
			query.setParameter("Identificacion", new BigInteger(cliente.getId()));
			
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		return results;
	}
	
	public long buscarPorCotizacionesNum(String fecha1, String fecha2,String cotizacionId,String puntoVenta, 
			String NroTramite,String Identificacion,String CanalId, int Skip, int Take){ 
		
		EntidadDAO entidadDAO = new EntidadDAO();
		Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(Identificacion.trim());
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente= clienteDAO.buscarPorEntidadId(entidad);
		
		
		String f1 = fecha1 + " 00:05:00";
		String f2 = fecha2 + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(fecha1.length()>0 && fecha1.length()>0){
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
		EstadoDAO estadoDAO = new EstadoDAO();
		
		String estadoId = estado.getId();
		Query query = null;
		
		String stringQuery= "SELECT count(c.id)FROM AgriObjetoDetalle c where (c.estado IS NOT NULL) ";					
		
		String valoresWhereQuery = "";
	
		// Agregamos la parte del where las opciones de busqueda
		if(cotizacionId.length()>0){
			cotiID = new BigInteger (cotizacionId.toString());
			//cotiID = cotizacionId;
			valoresWhereQuery += " AND c.cotizacionId=:cotiID";
			}
		if(fecha1.length()>0 && fecha2.length()>0)
			valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate";
		if(CanalId.length()>0)
			valoresWhereQuery += " AND c.canalId =:CanalId";
		if(puntoVenta.length()>0)
			valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta";
		if(NroTramite.length()>0)
			valoresWhereQuery += " AND c.numeroTramite =:NroTramite";
		if(Identificacion.length()>0)
			valoresWhereQuery += " AND c.clienteId =:Identificacion";
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery, AgriObjetoDetalle.class);
		
		// Agregamos los parametros del buscador
		if(cotizacionId.length()>0){
			//cotiID = new BigInteger (cotizacionId);
			query.setParameter("cotiID", new BigInteger(cotizacionId.toString()));
			//query.setParameter("cotiID", cotizacionId.toString());
		}
		
		if(fecha1.length()>0 && fecha2.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(CanalId.length()>0)
			query.setParameter("CanalId", new BigInteger(CanalId));
		if(puntoVenta.length()>0){
			query.setParameter("puntoVenta", new BigInteger(puntoVenta));
		}
		
		if(NroTramite.length()>0)
			query.setParameter("NroTramite", NroTramite);
		if(Identificacion.length()>0)
			query.setParameter("Identificacion",  new BigInteger(cliente.getId()));
	
		long results = (long)query.getSingleResult();
		return results;
	}
	
	
}
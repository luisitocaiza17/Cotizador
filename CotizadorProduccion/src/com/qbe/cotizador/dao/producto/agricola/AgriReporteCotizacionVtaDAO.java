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
import javax.persistence.TypedQuery;

import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriReporteCotizacionVta;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoObjeto;

public class AgriReporteCotizacionVtaDAO extends EntityManagerFactoryDAO<AgriReporteCotizacionVta>{
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

	public AgriReporteCotizacionVtaDAO() {
		super(AgriReporteCotizacionVta.class);
	}
	
	public List<AgriReporteCotizacionVta> BuscarTodos(){
		return getEntityManager().createNamedQuery("AgriReporteCotizacionVta.buscarTodos").getResultList();
	} 
	
	public List<AgriReporteCotizacionVta> BuscarXParametros(AgriReporteCotizacionVta reporteCotizacionVta, java.sql.Timestamp fechaDesde, java.sql.Timestamp fechaHasta){
		return getEntityManager().createNamedQuery("AgriReporteCotizacionVta.buscarPorParametros")
				.setParameter("productoId",reporteCotizacionVta.getProductoId())
				.setParameter("estadoId",reporteCotizacionVta.getEstadoId())
				.setParameter("tipoObjetoId",reporteCotizacionVta.getTipoObjetoId())
				.setParameter("fechaDesde",fechaDesde)
				.setParameter("fechaHasta",fechaHasta).getResultList();
	}
	
	public List<AgriReporteCotizacionVta> BuscarXParametrosPV(AgriReporteCotizacionVta reporteCotizacionVta, java.sql.Timestamp fechaDesde, java.sql.Timestamp fechaHasta){
		return getEntityManager().createNamedQuery("AgriReporteCotizacionVta.buscarPorParametrosPV")
				.setParameter("puntoVentaId",reporteCotizacionVta.getPuntoVentaId())
				.setParameter("productoId",reporteCotizacionVta.getProductoId())
				.setParameter("estadoId",reporteCotizacionVta.getEstadoId())
				.setParameter("tipoObjetoId",reporteCotizacionVta.getTipoObjetoId())
				.setParameter("fechaDesde",fechaDesde)
				.setParameter("fechaHasta",fechaHasta).getResultList();
	}
	
	
	//Cotizaciones No Emitidas x Fecha
		public List<AgriReporteCotizacionVta> buscarPorTipoObjetoParaCanal(String fecha1, String fecha2,TipoObjeto tipoObjeto,String cotizacionId,String puntoVenta,String agenteId,String identificacion,String usuarioSession, String EstadoFiltro, String pendientesImprimir, String numeroTramite){   
			
			String f1 = fecha1 + " 00:05:00";
			String f2 = fecha2 + " 23:55:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			java.sql.Timestamp timestamp1 = null;
			java.sql.Timestamp timestamp2 = null;
			String clienteId = "";
			
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
			Estado estado2 = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			EstadoDAO estadoDAO2 = new EstadoDAO();
			
			if(EstadoFiltro.equals("SPB"))
			{
				estado = estadoDAO.buscarPorNombreClase("Borrador","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Pendiente","Cotizacion");
			}
			else if(EstadoFiltro.equals("SPINS"))
			{
				estado = estadoDAO.buscarPorNombreClase("Pendiente de Inspeccion","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Pendiente de Inspeccion","Cotizacion");
			}
			else if(EstadoFiltro.equals("SPE"))
			{
				estado = estadoDAO.buscarPorNombreClase("Pendiente","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Pendiente","Cotizacion");
			}
			else if(EstadoFiltro.equals("SRARV"))
			{
				estado = estadoDAO.buscarPorNombreClase("Revision Aprobada","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Revision Negada","Cotizacion");
			}
			else if(EstadoFiltro.equalsIgnoreCase("Archivada")) {
				estado = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
				estado2 = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
			}
			else if(EstadoFiltro.equalsIgnoreCase("SPEP")) {
				estado = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
				estado2 = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			}
				
			List<AgriReporteCotizacionVta> results = new ArrayList<AgriReporteCotizacionVta>();
			TypedQuery<AgriReporteCotizacionVta> query = null;
			
			List<String> listadoTiposObjeto = new ArrayList<String>();
			listadoTiposObjeto.add("2");
			listadoTiposObjeto.add("4");
			listadoTiposObjeto.add("5");
			listadoTiposObjeto.add("8");
			
		
				String stringQuery= "SELECT c FROM AgriReporteCotizacionVta c where (c.estadoId =:estado OR c.estadoId =:estado2)";					
				String valoresWhereQuery = "";
				
				// Agregamos la parte del where las opciones de busqueda
				if(cotizacionId.length()>0)
					valoresWhereQuery += " AND c.id=:cotizacionId ";
				if(puntoVenta.length()>0)
					valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
				if(identificacion.length()>0)
					valoresWhereQuery += " AND c.clienteId =:clienteId ";
				if(tipoObjeto != null)
					valoresWhereQuery += " AND c.tipoObjetoId = :listTipos ";
				if(tipoObjeto == null)
					valoresWhereQuery += " AND c.tipoObjetoId IN :listTipos ";				
				if(fecha1.length()>0 && fecha2.length()>0)
					valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate ";				
				if(agenteId.length()>0)
					valoresWhereQuery += " AND c.agenteId =:agenteId ";
				
				if(usuarioSession.length()>0)
					valoresWhereQuery += " AND c.usuarioId =:usuario ";
				
				if(pendientesImprimir.equals("0"))
					valoresWhereQuery += " AND c.numeroImpresion =:numeroImpresion ";
				else if(pendientesImprimir.equals("1"))
					valoresWhereQuery += " AND NOT (c.numeroImpresion ='0') ";
				
				if(numeroTramite.length()>0)
					valoresWhereQuery += " AND c.numeroTramite =:numeroTramite ";
				
				stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaElaboracion ASC";
				
				query = getEntityManager().createQuery(stringQuery, AgriReporteCotizacionVta.class);
				query.setParameter("estado", new BigInteger(estado.getId()));
				query.setParameter("estado2", new BigInteger(estado2.getId()));
				
				// Agregamos los parametros del buscador
				if(cotizacionId.length()>0)
					query.setParameter("cotizacionId", new BigInteger(cotizacionId));
				if(tipoObjeto == null)
					query.setParameter("listTipos", listadoTiposObjeto);
				if(tipoObjeto != null)
					query.setParameter("listTipos", new BigInteger(tipoObjeto.getId()));
				
				if(fecha1.length()>0 && fecha2.length()>0){
					query.setParameter("startDate", timestamp1);
					query.setParameter("endDate", timestamp2);
				}
				if(puntoVenta.length()>0){					
					query.setParameter("puntoVentaId", new BigInteger(puntoVenta));
				}
				if(agenteId.length()>0)
					query.setParameter("agenteId", new BigInteger(agenteId));
				if(identificacion.length()>0){
					EntidadDAO entidadDAO = new EntidadDAO();
					Entidad entidadCliente = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
					if(entidadCliente.getId() != null)
						query.setParameter("clienteId", new BigInteger(entidadCliente.getClientes().get(0).getId()));
					else
						query.setParameter("clienteId", new BigInteger("0"));
				}
				if(usuarioSession.length()>0){
					query.setParameter("usuario", new BigInteger(usuarioSession));
				}
				
				if(pendientesImprimir.equals("0")){
					query.setParameter("numeroImpresion", Integer.parseInt(pendientesImprimir));
				}
				if(numeroTramite.length()>0)
					query.setParameter("numeroTramite", numeroTramite);
				
				results = query.getResultList();
				return results;
		}
		
		
		//Cotizaciones No Emitidas x Fecha
		public List<AgriReporteCotizacionVta> buscarPorTipoObjetoxFechaPuntoVenta(String fecha1, String fecha2,TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String cotizacionId,String agenteId,String identificacion,String usuarioSession, String FiltroEstado, String pendientesImprimir, String numeroTramite){   
			
			String f1 = fecha1 + " 00:05:00";
			String f2 = fecha2 + " 23:55:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			java.sql.Timestamp timestamp1 = null;
			java.sql.Timestamp timestamp2 = null;
			String clienteId = "";
			
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
			Estado estado2 = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			EstadoDAO estadoDAO2 = new EstadoDAO();
			if(FiltroEstado.equals("SPB"))
			{
				estado = estadoDAO.buscarPorNombreClase("Borrador","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Pendiente","Cotizacion");
			}
			else if(FiltroEstado.equals("SRARV"))
			{
				estado = estadoDAO.buscarPorNombreClase("Revision Aprobada","Cotizacion");
				estado2 = estadoDAO2.buscarPorNombreClase("Revision Negada","Cotizacion");
			}
			else if(FiltroEstado.equalsIgnoreCase("Archivada")) {
				estado = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
				estado2 = estadoDAO.buscarPorNombreClase("Archivada","Cotizacion");
			}
			else if(FiltroEstado.equalsIgnoreCase("SPEP")) {
				estado = estadoDAO.buscarPorNombreClase("Pendiente de Emitir","Cotizacion");
				estado2 = estadoDAO.buscarPorNombreClase("Emitido","Cotizacion");
			}
			List<AgriReporteCotizacionVta> results = new ArrayList<AgriReporteCotizacionVta>();
			TypedQuery<AgriReporteCotizacionVta> query = null;
			
			List<String> listadoTiposObjeto = new ArrayList<String>();
			listadoTiposObjeto.add("2");
			listadoTiposObjeto.add("4");
			listadoTiposObjeto.add("5");
			listadoTiposObjeto.add("6");
			listadoTiposObjeto.add("8");
			
		
				String stringQuery= "SELECT c FROM AgriReporteCotizacionVta c where (c.estadoId =:estado OR c.estadoId =:estado2)";					
				String valoresWhereQuery = "";
				
				// Agregamos la parte del where las opciones de busqueda
				if(cotizacionId.length()>0)
					valoresWhereQuery += " AND c.id=:cotizacionId";
				if(puntoVenta!= null)
					valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta";
				if(identificacion.length()>0)
					valoresWhereQuery += " AND c.clienteId =:clienteId";
				if(tipoObjeto != null)
					valoresWhereQuery += " AND c.tipoObjetoId = :listTipos";
				if(tipoObjeto == null)
					valoresWhereQuery += " AND c.tipoObjetoId IN :listTipos";				
				if(fecha1.length()>0 && fecha2.length()>0)
					valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate";				
				if(agenteId.length()>0)
					valoresWhereQuery += " AND c.agenteId =:agenteId";
				
				if(usuarioSession.length()>0)
					valoresWhereQuery += " AND c.usuarioId =:usuario";
				
				if(pendientesImprimir.equals("0"))
					valoresWhereQuery += " AND c.numeroImpresion =:numeroImpresion ";
				else if(pendientesImprimir.equals("1"))
					valoresWhereQuery += " AND NOT (c.numeroImpresion ='0') ";
				
				if(numeroTramite.length()>0)
					valoresWhereQuery += " AND c.numeroTramite =:numeroTramite ";
				
				stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaElaboracion ASC";
				
				query = getEntityManager().createQuery(stringQuery, AgriReporteCotizacionVta.class);
				query.setParameter("estado", new BigInteger(estado.getId()));
				query.setParameter("estado2", new BigInteger(estado2.getId()));
				
				// Agregamos los parametros del buscador
				if(cotizacionId.length()>0)
					query.setParameter("cotizacionId", new BigInteger(cotizacionId));
				if(tipoObjeto == null)
					query.setParameter("listTipos", listadoTiposObjeto);
				if(tipoObjeto != null)
					query.setParameter("listTipos", new BigInteger(tipoObjeto.getId()));
				
				if(fecha1.length()>0 && fecha2.length()>0){
					query.setParameter("startDate", timestamp1);
					query.setParameter("endDate", timestamp2);
				}
				if(puntoVenta != null){					
					query.setParameter("puntoVenta", new BigInteger(puntoVenta.getId()));
				}
				if(agenteId.length()>0)
					query.setParameter("agenteId", new BigInteger(agenteId));
				if(identificacion.length()>0){
					EntidadDAO entidadDAO = new EntidadDAO();
					Entidad entidadCliente = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
					if(entidadCliente.getId() != null)
						query.setParameter("clienteId", new BigInteger(entidadCliente.getClientes().get(0).getId()));
					else
						query.setParameter("clienteId", new BigInteger("0"));
				}
				if(usuarioSession.length()>0){
					query.setParameter("usuario", new BigInteger(usuarioSession));
				}
				
				if(pendientesImprimir.equals("0")){
					query.setParameter("numeroImpresion", Integer.parseInt(pendientesImprimir));
				}
				
				if(numeroTramite.length()>0)
					query.setParameter("numeroTramite", numeroTramite);
				
				results = query.getResultList();
				return results;
		}
		
		//Cotizaciones No Emitidas x Fecha
				public List<AgriReporteCotizacionVta> buscarPorTipoObjetoParaCanalyEstado(String fecha1, String fecha2,TipoObjeto tipoObjeto,String cotizacionId,String puntoVenta,String agenteId,String identificacion,String usuarioSession, Estado EstadoFiltro, String pendientesImprimir, String numeroTramite){   
					
					String f1 = fecha1 + " 00:05:00";
					String f2 = fecha2 + " 23:55:00";
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					java.sql.Timestamp timestamp1 = null;
					java.sql.Timestamp timestamp2 = null;
					String clienteId = "";
					
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
					
						
					List<AgriReporteCotizacionVta> results = new ArrayList<AgriReporteCotizacionVta>();
					TypedQuery<AgriReporteCotizacionVta> query = null;
					
					List<String> listadoTiposObjeto = new ArrayList<String>();
					listadoTiposObjeto.add("2");
					listadoTiposObjeto.add("4");
					listadoTiposObjeto.add("5");
					listadoTiposObjeto.add("8");
					
				
						String stringQuery= "SELECT c FROM AgriReporteCotizacionVta c where (c.estadoId =:estado OR c.estadoId =:estado2)";					
						String valoresWhereQuery = "";
						
						// Agregamos la parte del where las opciones de busqueda
						if(cotizacionId.length()>0)
							valoresWhereQuery += " AND c.id=:cotizacionId ";
						if(puntoVenta.length()>0)
							valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta ";
						if(identificacion.length()>0)
							valoresWhereQuery += " AND c.clienteId =:clienteId ";
						if(tipoObjeto != null)
							valoresWhereQuery += " AND c.tipoObjetoId = :listTipos ";
						if(tipoObjeto == null)
							valoresWhereQuery += " AND c.tipoObjetoId IN :listTipos ";				
						if(fecha1.length()>0 && fecha2.length()>0)
							valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate ";				
						if(agenteId.length()>0)
							valoresWhereQuery += " AND c.agenteId =:agenteId ";
						
						if(usuarioSession.length()>0)
							valoresWhereQuery += " AND c.usuarioId =:usuario ";
						
						if(pendientesImprimir.equals("0"))
							valoresWhereQuery += " AND c.numeroImpresion =:numeroImpresion ";
						else if(pendientesImprimir.equals("1"))
							valoresWhereQuery += " AND NOT (c.numeroImpresion ='0') ";
						
						if(numeroTramite.length()>0)
							valoresWhereQuery += " AND c.numeroTramite =:numeroTramite ";
						
						stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaElaboracion ASC";
						
						query = getEntityManager().createQuery(stringQuery, AgriReporteCotizacionVta.class);
						query.setParameter("estado", new BigInteger(EstadoFiltro.getId()));
						query.setParameter("estado2", new BigInteger(EstadoFiltro.getId().toString()));
						
						// Agregamos los parametros del buscador
						if(cotizacionId.length()>0)
							query.setParameter("cotizacionId", new BigInteger(cotizacionId));
						if(tipoObjeto == null)
							query.setParameter("listTipos", listadoTiposObjeto);
						if(tipoObjeto != null)
							query.setParameter("listTipos", new BigInteger(tipoObjeto.getId()));
						
						if(fecha1.length()>0 && fecha2.length()>0){
							query.setParameter("startDate", timestamp1);
							query.setParameter("endDate", timestamp2);
						}
						if(puntoVenta.length()>0){					
							query.setParameter("puntoVentaId", new BigInteger(puntoVenta));
						}
						if(agenteId.length()>0)
							query.setParameter("agenteId", new BigInteger(agenteId));
						if(identificacion.length()>0){
							EntidadDAO entidadDAO = new EntidadDAO();
							Entidad entidadCliente = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
							if(entidadCliente.getId() != null)
								query.setParameter("clienteId", new BigInteger(entidadCliente.getClientes().get(0).getId()));
							else
								query.setParameter("clienteId", new BigInteger("0"));
						}
						if(usuarioSession.length()>0){
							query.setParameter("usuario", new BigInteger(usuarioSession));
						}
						
						if(pendientesImprimir.equals("0")){
							query.setParameter("numeroImpresion", Integer.parseInt(pendientesImprimir));
						}
						
						if(numeroTramite.length()>0){
							query.setParameter("numeroTramite", numeroTramite);
						}						
												
						results = query.getResultList();
						return results;
				}
				
				
				//Cotizaciones No Emitidas x Fecha
				public List<AgriReporteCotizacionVta> buscarPorTipoObjetoxFechaPuntoVentaYEstado(String fecha1, String fecha2,TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String cotizacionId,String agenteId,String identificacion,String usuarioSession, Estado FiltroEstado, String pendientesImprimir, String numeroTramite){   
					
					String f1 = fecha1 + " 00:05:00";
					String f2 = fecha2 + " 23:55:00";
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					java.sql.Timestamp timestamp1 = null;
					java.sql.Timestamp timestamp2 = null;
					String clienteId = "";
					
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
										
					List<AgriReporteCotizacionVta> results = new ArrayList<AgriReporteCotizacionVta>();
					TypedQuery<AgriReporteCotizacionVta> query = null;
					
					List<String> listadoTiposObjeto = new ArrayList<String>();
					listadoTiposObjeto.add("2");
					listadoTiposObjeto.add("4");
					listadoTiposObjeto.add("5");
					listadoTiposObjeto.add("6");
					listadoTiposObjeto.add("8");
					
				
						String stringQuery= "SELECT c FROM AgriReporteCotizacionVta c where (c.estadoId =:estado OR c.estadoId =:estado2)";					
						String valoresWhereQuery = "";
						
						// Agregamos la parte del where las opciones de busqueda
						if(cotizacionId.length()>0)
							valoresWhereQuery += " AND c.id=:cotizacionId";
						if(puntoVenta!= null)
							valoresWhereQuery += " AND c.puntoVentaId =:puntoVenta";
						if(identificacion.length()>0)
							valoresWhereQuery += " AND c.clienteId =:clienteId";
						if(tipoObjeto != null)
							valoresWhereQuery += " AND c.tipoObjetoId = :listTipos";
						if(tipoObjeto == null)
							valoresWhereQuery += " AND c.tipoObjetoId IN :listTipos";				
						if(fecha1.length()>0 && fecha2.length()>0)
							valoresWhereQuery += " AND c.fechaElaboracion BETWEEN :startDate AND :endDate";				
						if(agenteId.length()>0)
							valoresWhereQuery += " AND c.agenteId =:agenteId";
						
						if(usuarioSession.length()>0)
							valoresWhereQuery += " AND c.usuarioId =:usuario";
						
						if(pendientesImprimir.equals("0"))
							valoresWhereQuery += " AND c.numeroImpresion =:numeroImpresion ";
						else if(pendientesImprimir.equals("1"))
							valoresWhereQuery += " AND NOT (c.numeroImpresion ='0') ";
						
						if(numeroTramite.length()>0)
							valoresWhereQuery += " AND c.numeroTramite =:numeroTramite";
						
						stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.fechaElaboracion ASC";
						
						query = getEntityManager().createQuery(stringQuery, AgriReporteCotizacionVta.class);
						query.setParameter("estado", new BigInteger(FiltroEstado.getId()));
						query.setParameter("estado2", new BigInteger(FiltroEstado.getId()));
						
						// Agregamos los parametros del buscador
						if(cotizacionId.length()>0)
							query.setParameter("cotizacionId", new BigInteger(cotizacionId));
						if(tipoObjeto == null)
							query.setParameter("listTipos", listadoTiposObjeto);
						if(tipoObjeto != null)
							query.setParameter("listTipos", new BigInteger(tipoObjeto.getId()));
						
						if(fecha1.length()>0 && fecha2.length()>0){
							query.setParameter("startDate", timestamp1);
							query.setParameter("endDate", timestamp2);
						}
						if(puntoVenta != null){					
							query.setParameter("puntoVenta", new BigInteger(puntoVenta.getId()));
						}
						if(agenteId.length()>0)
							query.setParameter("agenteId", new BigInteger(agenteId));
						if(identificacion.length()>0){
							EntidadDAO entidadDAO = new EntidadDAO();
							Entidad entidadCliente = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
							if(entidadCliente.getId() != null)
								query.setParameter("clienteId", new BigInteger(entidadCliente.getClientes().get(0).getId()));
							else
								query.setParameter("clienteId", new BigInteger("0"));
						}
						if(usuarioSession.length()>0){
							query.setParameter("usuario", new BigInteger(usuarioSession));
						}
						
						if(pendientesImprimir.equals("0")){
							query.setParameter("numeroImpresion", Integer.parseInt(pendientesImprimir));
						}
						
						if(numeroTramite.equals("0")){
							query.setParameter("numeroTramite", numeroTramite);
						}
						
						results = query.getResultList();
						return results;
				}
		

}

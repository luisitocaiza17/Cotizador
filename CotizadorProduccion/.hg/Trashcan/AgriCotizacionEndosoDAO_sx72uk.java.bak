package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriAgencia;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriCotizacionEndoso;

public class AgriCotizacionEndosoDAO extends EntityManagerFactoryDAO<AgriCotizacionEndoso>{
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
	public AgriCotizacionEndosoDAO() {
		// TODO Auto-generated constructor stub
		super(AgriCotizacionEndoso.class);
	}
	
	public List<AgriCotizacionEndoso> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriCotizacionEndoso.findAll", AgriCotizacionEndoso.class).getResultList();
		
	}

	public AgriCotizacionEndoso buscarPorId(BigInteger id){
		AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
		List <AgriCotizacionEndoso>results = getEntityManager().createNamedQuery("AgriCotizacionEndoso.buscarPorId", AgriCotizacionEndoso.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			agriCotizacionEndoso = results.get(0);
		return agriCotizacionEndoso;
	}
	
	public List <String> buscarPorCotizacionId(String cotizacionId, String fechaInicio, String fechaFin, String tramite, String canal,String puntoVenta,String cliente){
		String f1 = fechaInicio + " 00:05:00";
		String f2 = fechaFin + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		if(fechaInicio.length()>0 && fechaFin.length()>0){
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
		TypedQuery<String> query = null;
		
		String stringQuery= "SELECT DISTINCT(a.cotizacionId) FROM AgriCotizacionEndoso a where (a.id IS NOT NULL) ";					
		String valoresWhereQuery = "";
		if(fechaInicio.length()>0 && fechaFin.length()>0)
			valoresWhereQuery += " AND a.fecha_Proceso BETWEEN :startDate AND :endDate ";
		if(!cotizacionId.equals("")){
			valoresWhereQuery += " AND a.cotizacionId =:cotizacionId";
		}
		if(!tramite.equals("")){
			valoresWhereQuery += " AND a.tramite =:tramite";
		}
		if(!canal.equals("")){
			valoresWhereQuery += " AND a.canal =:canal";
		}
		if(!puntoVenta.equals("")){
			valoresWhereQuery += " AND a.puntoVenta =:puntoVenta";
		}
		if(!cliente.equals("")){
			valoresWhereQuery += " AND a.cliente =:cliente";
		}
		
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery,String.class);
		
		if(fechaInicio.length()>0 && fechaFin.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(!cotizacionId.equals("")){
			BigInteger cotizacion = new BigInteger(cotizacionId);
			query.setParameter("cotizacionId", cotizacion);			
		}
		if(!tramite.equals("")){
			query.setParameter("tramite", tramite);			
		}
		if(!canal.equals("")){
			query.setParameter("canal", canal);
		}
		if(!puntoVenta.equals("")){
			query.setParameter("puntoVenta", puntoVenta);
		}
		if(!cliente.equals("")){
			query.setParameter("cliente", cliente);
		}
		//return query.setMaxResults(take).setFirstResult(skip).getResultList();
		return query.getResultList();
	}
	
	public long buscarPorCotizacionIdNum(BigInteger cotizacionId,int take, int skip, String fechaInicio, String fechaFin){
		String f1 = fechaInicio + " 00:05:00";
		String f2 = fechaFin + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		if(fechaInicio.length()>0 && fechaFin.length()>0){
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
		
		Query query = null;	
		
		String stringQuery= "SELECT  count(a.id) FROM AgriCotizacionEndoso a where (a.id IS NOT NULL) ";					
		String valoresWhereQuery = "";
		if(fechaInicio.length()>0 && fechaFin.length()>0)
			valoresWhereQuery += " AND a.fecha_Proceso BETWEEN :startDate AND :endDate ";
		if(cotizacionId!=null){
			valoresWhereQuery += " AND a.cotizacionId =:cotizacionId";
		}
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery);	
		
		if(fechaInicio.length()>0 && fechaFin.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(cotizacionId!=null){
			query.setParameter("cotizacionId", cotizacionId);			
		}
		long total = (long) query.getSingleResult();
		return total;	
	}
	
	public List <AgriCotizacionEndoso> buscarPorCotizacionIdAnexo(BigInteger cotizacionId,String anexo){
		AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
		List <AgriCotizacionEndoso>results = getEntityManager().createNamedQuery("AgriCotizacionEndoso.buscarCotizacionIdAnexo", AgriCotizacionEndoso.class).setParameter("cotizacionId", cotizacionId).setParameter("anexo", anexo).getResultList();
		return results;
	}
	public int buscarNumAnexoPorCotizacionId(BigInteger cotizacionId){
		AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
		List <AgriCotizacionEndoso>results = getEntityManager().createNamedQuery("AgriCotizacionEndoso.buscarCotizacionId", AgriCotizacionEndoso.class).setParameter("cotizacionId", cotizacionId).getResultList();
		return results.size();	
	}
}

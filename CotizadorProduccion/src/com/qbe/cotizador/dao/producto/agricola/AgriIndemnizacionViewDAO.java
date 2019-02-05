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
import com.qbe.cotizador.model.AgriCotizacionMovimiento;
import com.qbe.cotizador.model.AgriIndemnizacion;
import com.qbe.cotizador.model.AgriIndemnizacionView;

public class AgriIndemnizacionViewDAO extends EntityManagerFactoryDAO<AgriIndemnizacionView>{
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
	public AgriIndemnizacionViewDAO() {
		// TODO Auto-generated constructor stub
		super(AgriIndemnizacionView.class);
	}
	public List<AgriIndemnizacionView> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriIndemnizacionView.findAll", AgriIndemnizacionView.class).getResultList();
	}	
	
	public List <AgriIndemnizacionView> buscarPorParametros(String cotizacionId,String numeroTramite
			,int take, int skip, String fechaInicio, String fechaFin,String canalId, String puntoVentaId,String identificacion){
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
		
		TypedQuery<AgriIndemnizacionView> query = null;
		
		String stringQuery= "SELECT a FROM AgriIndemnizacionView a where (a.id IS NOT NULL) ";					
		String valoresWhereQuery = "";
		if(fechaInicio.length()>0 && fechaFin.length()>0)
			valoresWhereQuery += " AND a.fechaProceso BETWEEN :startDate AND :endDate ";
		if(!cotizacionId.equals("")){
			valoresWhereQuery += " AND a.cotizacionId =:cotizacionId";
		}
		if(!numeroTramite.equals("")){
			valoresWhereQuery += " AND a.tramite =:numeroTramite";
		}
		if(!canalId.equals("")){
			valoresWhereQuery += " AND a.canalId =:canalId";
		}
		if(!puntoVentaId.equals("")){
			valoresWhereQuery += " AND a.puntoVentaId =:puntoVenta_id";
		}
		if(!identificacion.equals("")){
			valoresWhereQuery += " AND a.identificacion =:identificacion";
		}
				stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery, AgriIndemnizacionView.class);
		
		if(fechaInicio.length()>0 && fechaFin.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(!cotizacionId.equals("")){
			query.setParameter("cotizacionId", new BigInteger(cotizacionId));			
		}
		if(!numeroTramite.equals("")){
			query.setParameter("numeroTramite", numeroTramite);
		}
		if(!canalId.equals("")){
			query.setParameter("canalId", new BigInteger(canalId));
		}
		if(!puntoVentaId.equals("")){
			query.setParameter("puntoVenta_id", new BigInteger(puntoVentaId));
		}
		if(!identificacion.equals("")){
			query.setParameter("identificacion", identificacion);
		}
		
		return query.setMaxResults(take).setFirstResult(skip).getResultList();		
	}
	
	public long buscarPorParametrosNum(String cotizacionId,String numeroTramite
			,int take, int skip, String fechaInicio, String fechaFin,String canalId, String puntoVentaId,String identificacion){
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
		
		String stringQuery= "SELECT  count(a.cotizacionId) FROM AgriIndemnizacionView a where (a.cotizacionId IS NOT NULL) ";					
		String valoresWhereQuery = "";
		if(fechaInicio.length()>0 && fechaFin.length()>0)
			valoresWhereQuery += " AND a.fechaProceso BETWEEN :startDate AND :endDate ";
		if(!cotizacionId.equals("")){
			valoresWhereQuery += " AND a.cotizacionId =:cotizacionId";
		}
		if(!numeroTramite.equals("")){
			valoresWhereQuery += " AND a.tramite =:numeroTramite";
		}
		if(!canalId.equals("")){
			valoresWhereQuery += " AND a.canalId =:canalId";
		}
		if(!puntoVentaId.equals("")){
			valoresWhereQuery += " AND a.puntoVentaId =:puntoVenta_id";
		}
		if(!identificacion.equals("")){
			valoresWhereQuery += " AND a.identificacion =:identificacion";
		}
		if(!numeroTramite.equals("")){
			valoresWhereQuery+= " AND a.tramite =:numeroTramite";
		}
		
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery);	
		
		if(fechaInicio.length()>0 && fechaFin.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		if(!cotizacionId.equals("")){
			query.setParameter("cotizacionId", new BigInteger(cotizacionId));			
		}
		if(!numeroTramite.equals("")){
			query.setParameter("numeroTramite", numeroTramite);
		}
		if(!canalId.equals("")){
			query.setParameter("canalId", new BigInteger(canalId));
		}
		if(!puntoVentaId.equals("")){
			query.setParameter("puntoVenta_id", new BigInteger(puntoVentaId));
		}
		if(!identificacion.equals("")){
			query.setParameter("identificacion", identificacion);
		}
		
		long total = (long) query.getSingleResult();
		return total;	
	}	
	
}

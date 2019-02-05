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

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;

public class PymeAuditoriaGeneralDAO extends EntityManagerFactoryDAO<PymeAuditoriaGeneral>{
	
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
	
	public PymeAuditoriaGeneralDAO(){
		super(PymeAuditoriaGeneral.class);
	}
	
	
	public List<PymeAuditoriaGeneral> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeAuditoriaGeneral.buscarTodos").getResultList();
	}
	
	public PymeAuditoriaGeneral buscarPorId(int id) {
		PymeAuditoriaGeneral pymeAuditoriaGeneral=new PymeAuditoriaGeneral();
		List<PymeAuditoriaGeneral> results = getEntityManager().createNamedQuery("PymeAuditoriaGeneral.buscarPorId").setParameter("id", id).getResultList();			
		if(results.size()>0)
			pymeAuditoriaGeneral = results.get(0);
		return pymeAuditoriaGeneral;
	}
	
public List<PymeAuditoriaGeneral> buscarLog(String fecha1, String fecha2,String NroTramite, int Skip, int Take){ 
		
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
		
		List<PymeAuditoriaGeneral> results = new ArrayList<PymeAuditoriaGeneral>();
		TypedQuery<PymeAuditoriaGeneral> query = null;
		
		String stringQuery= " SELECT p FROM PymeAuditoriaGeneral p  where p.id IS NOT NULL ";					
		
		String valoresWhereQuery = "";
		
		
		if(fecha1.length()>0 && fecha2.length()>0)
			valoresWhereQuery += " AND p.fecha BETWEEN :startDate AND :endDate";
		
		if(NroTramite.length()>0)
			valoresWhereQuery += " AND p.tramite =:tramite";
		
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery, PymeAuditoriaGeneral.class);
		
		if(fecha1.length()>0 && fecha2.length()>0){
			query.setParameter("startDate", timestamp1);
			query.setParameter("endDate", timestamp2);
		}
		
		if(NroTramite.length()>0)
			query.setParameter("tramite", NroTramite);
		
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		return results;
	}
public long buscarLogNum(String fecha1, String fecha2,String NroTramite, int Skip, int Take){ 

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
	
	Query query = null;
	
	String stringQuery= " SELECT count(p.id) FROM PymeAuditoriaGeneral p where p.id IS NOT NULL  ";					
	
	String valoresWhereQuery = "";
	
	
	if(fecha1.length()>0 && fecha2.length()>0)
		valoresWhereQuery += " AND p.fecha BETWEEN :startDate AND :endDate";
	
	if(NroTramite.length()>0)
		valoresWhereQuery += " AND p.tramite =:tramite";
	
	stringQuery = stringQuery+valoresWhereQuery;
	
	query = getEntityManager().createQuery(stringQuery);
	
	
	if(fecha1.length()>0 && fecha2.length()>0){
		query.setParameter("startDate", timestamp1);
		query.setParameter("endDate", timestamp2);
	}
	
	if(NroTramite.length()>0)
		query.setParameter("tramite", NroTramite);

	long results = (long)query.getSingleResult();
	return results;
}
	
}


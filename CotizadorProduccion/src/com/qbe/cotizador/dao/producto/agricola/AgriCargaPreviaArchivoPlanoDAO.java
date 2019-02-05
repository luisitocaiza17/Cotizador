package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;

public class AgriCargaPreviaArchivoPlanoDAO extends EntityManagerFactoryDAO<AgriCargaPreviaArchivoPlano>{
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
	
	public AgriCargaPreviaArchivoPlanoDAO() {
		super(AgriCargaPreviaArchivoPlano.class);
	}
	
	public List<AgriCargaPreviaArchivoPlano> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriCargaPreviaArchivoPlano.buscarTodos", AgriCargaPreviaArchivoPlano.class).getResultList();
	}
	
	public AgriCargaPreviaArchivoPlano buscarPorId(BigInteger id){
		AgriCargaPreviaArchivoPlano objeto = new AgriCargaPreviaArchivoPlano();
		List <AgriCargaPreviaArchivoPlano>results = getEntityManager().createNamedQuery("AgriCargaPreviaArchivoPlano.buscarPorId", AgriCargaPreviaArchivoPlano.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			objeto = results.get(0);
		return objeto;
	}	
	
	public List<AgriCargaPreviaArchivoPlano> buscarPorIdentificacion(String identificacion, int estado){		
		return getEntityManager().createNamedQuery("AgriCargaPreviaArchivoPlano.buscarPorIdentificacion", AgriCargaPreviaArchivoPlano.class).setParameter("clienteIdentificacion", identificacion).setParameter("estado", estado).getResultList();
	}
	
	public long cotizacionesExistentesPorNumero(String desde, String hasta, String Identifacion, String Nombre){
		
		String f1 = desde + " 00:05:00";
		String f2 = hasta + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
				if(desde.length()>0 && desde.length()>0){
					java.util.Date parsedDate = null;
					try {
						parsedDate = dateFormat.parse(f1);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
				}
				if(hasta.length()>0 && hasta.length()>0){
					java.util.Date parsedDate = null;
					try {
						parsedDate = dateFormat.parse(f2);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
				}
		
		Query query = null;
		
		String stringQuery= "SELECT count(c.id) FROM AgriCargaPreviaArchivoPlano c ";	
		
		String valoresWhereQuery = " WHERE c.estado=:estado AND c.activo=:activo ";
		
		//clausulas where para verificar procesos entre fechas
		
		if(desde.length()>0 && desde.length()>0)
			valoresWhereQuery += " AND c.cargaFecha >=:desde ";
		if(hasta.length()>0 && hasta.length()>0)
			valoresWhereQuery += " AND c.cargaFecha <=:hasta ";
		if(!Identifacion.equals(""))
			valoresWhereQuery += " AND c.clienteIdentificacion =:Identifacion ";
		if(!Nombre.equals(""))
			valoresWhereQuery += " AND c.nombre_Completo LIKE :Nombre ";
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery);
		query.setParameter("estado",1);
		query.setParameter("activo",true);
		if(desde.length()>0 && desde.length()>0)
			query.setParameter("desde",timestamp1);
		if(hasta.length()>0 && hasta.length()>0)
			query.setParameter("hasta",timestamp2);
		if(!Identifacion.equals(""))
			query.setParameter("Identifacion",Identifacion);
		if(!Nombre.equals(""))
			query.setParameter("Nombre",Nombre+"%");
		
		long results = (long)query.getSingleResult();
		
		return results;
	}
	
	public List<AgriCargaPreviaArchivoPlano> cotizacionesExistentes(String desde, String hasta, String Identifacion, String Nombre,
			int Skip, int Take){
		
		String f1 = desde + " 00:05:00";
		String f2 = hasta + " 23:55:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		
		BigInteger cotiID;
		
		// Validacion para cuando se ingrese la fecha en la búsqueda
		if(desde.length()>0 && desde.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
		}
		if(hasta.length()>0 && hasta.length()>0){
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());			
		}
		
		Query query = null;
		
		String stringQuery= "SELECT c FROM AgriCargaPreviaArchivoPlano c ";	
		
		String valoresWhereQuery = " WHERE c.estado=:estado AND c.activo=:activo ";
		
		//clausulas where para verificar procesos entre fechas
		
		if(desde.length()>0 && desde.length()>0)
			valoresWhereQuery += " AND c.cargaFecha >=:desde ";
		if(hasta.length()>0 && hasta.length()>0)
			valoresWhereQuery += " AND c.cargaFecha <=:hasta ";
		if(!Identifacion.equals(""))
			valoresWhereQuery += " AND c.clienteIdentificacion =:Identifacion ";
		if(!Nombre.equals(""))
			valoresWhereQuery += " AND c.nombre_Completo LIKE :Nombre ";
		
		stringQuery = stringQuery+valoresWhereQuery;
		
		query = getEntityManager().createQuery(stringQuery);
		query.setParameter("estado",1);
		query.setParameter("activo",true);
		if(desde.length()>0 && desde.length()>0)
			query.setParameter("desde",timestamp1);
		if(hasta.length()>0 && hasta.length()>0)
			query.setParameter("hasta",timestamp2);
		if(!Identifacion.equals(""))
			query.setParameter("Identifacion",Identifacion);
		if(!Nombre.equals(""))
			query.setParameter("Nombre",Nombre+"%");
		List<AgriCargaPreviaArchivoPlano> results = new ArrayList<AgriCargaPreviaArchivoPlano>();
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		
		return results;
	}
	
}

package com.qbe.cotizador.dao.inspeccion;

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
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.DistanciaInspector;
import com.qbe.cotizador.model.Inspector;

public class DistanciaInspectorDAO extends EntityManagerFactoryDAO<DistanciaInspector>{
	
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
	
	public DistanciaInspectorDAO() {
        super(DistanciaInspector.class);
    }
	
	public List<DistanciaInspector> buscarTodos(){ 
		return getEntityManager().createNamedQuery("DistanciaInspector.buscarTodos").getResultList();
	}
	
	public DistanciaInspector buscarPorId(String id){
		DistanciaInspector distancia = new DistanciaInspector();
		List<DistanciaInspector> query = getEntityManager().createNamedQuery("DistanciaInspector.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			distancia =  query.get(0);
		return distancia;
	}
	
	public List<DistanciaInspector> buscarCiudadOrigen(){ 
		return getEntityManager().createNamedQuery("DistanciaInspector.buscarCiudadOrigen").getResultList();
	}
	
	public List<DistanciaInspector> buscarCiudadOrigenPorDestino(String origen_id){ 
		return getEntityManager().createNamedQuery("DistanciaInspector.buscarCiudadOrigenPorDestino").setParameter("origen_id", origen_id).getResultList();
	}	

	public List<DistanciaInspector> buscarDistanciasPorInspector(String origen_id, String destino_id, Inspector inspector){ 
		return getEntityManager().createNamedQuery("DistanciaInspector.buscarDistanciasPorInspector").setParameter("origen_id", origen_id).setParameter("destino_id", destino_id).setParameter("inspector", inspector).getResultList();
	}		
	
	public List<String> buscarDistintoOrigen(){
	  List<String> results = new ArrayList<String>();
      Query query = null;
      
      String stringQuery= "SELECT distinct c.origen FROM DISTANCIA_INSPECTOR c order by c.origen asc";                   
      query = getEntityManager().createNativeQuery(stringQuery);
      results = query.getResultList();
      return results;
	
	}
}

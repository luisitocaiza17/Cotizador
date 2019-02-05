package com.qbe.cotizador.dao.entidad;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ActividadEconomica;

public class ActividadEconomicaDAO extends EntityManagerFactoryDAO<ActividadEconomica>{

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
	
	public ActividadEconomicaDAO() {
        super(ActividadEconomica.class);
    }
	
	public ActividadEconomica buscarPorId(String id){
		ActividadEconomica actividad = new ActividadEconomica();
		List<ActividadEconomica> query = getEntityManager().createNamedQuery("ActividadEconomica.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			actividad =  query.get(0);
		return actividad;		
	}
	
	public List<ActividadEconomica> buscarTodos(){
		return getEntityManager().createNamedQuery("ActividadEconomica.buscarTodos").getResultList();		
	}
	
	public List<ActividadEconomica> buscarPorGrupoProductoId(BigInteger grupoProductoId){
		return getEntityManager().createNamedQuery("ActividadEconomica.buscarPorGrupoProductoId").setParameter("grupoProductoId", grupoProductoId).getResultList();		
	}

	public ActividadEconomica buscarPorNombre(String nombre){
		ActividadEconomica actividad = new ActividadEconomica();
		List<ActividadEconomica> query = getEntityManager().createNamedQuery("ActividadEconomica.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			actividad =  query.get(0);
		return actividad;			
	}
}

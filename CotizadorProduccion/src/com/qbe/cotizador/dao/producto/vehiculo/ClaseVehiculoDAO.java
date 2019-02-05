package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ClaseVehiculo;

public class ClaseVehiculoDAO extends EntityManagerFactoryDAO<ClaseVehiculo>{
	
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
	
	public ClaseVehiculoDAO() {
        super(ClaseVehiculo.class);
    }
	
	public List<ClaseVehiculo> buscarTodos(){   
		return getEntityManager().createNamedQuery("ClaseVehiculo.buscarTodos").getResultList();
	}
	
	public ClaseVehiculo buscarPorId(String id){   
		ClaseVehiculo clase = new ClaseVehiculo();
		List<ClaseVehiculo> query = getEntityManager().createNamedQuery("ClaseVehiculo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			clase =  query.get(0);
		return clase;
	}
	
	public List<ClaseVehiculo> buscarActivos(){
		return getEntityManager().createNamedQuery("ClaseVehiculo.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public ClaseVehiculo buscarPorNombre(String nombre){
		ClaseVehiculo clase = new ClaseVehiculo();
		List<ClaseVehiculo> query = getEntityManager().createNamedQuery("ClaseVehiculo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			clase =  query.get(0);	
		return clase;
	}
}

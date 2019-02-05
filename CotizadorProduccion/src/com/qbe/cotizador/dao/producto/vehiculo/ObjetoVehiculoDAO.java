package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.ObjetoVehiculo;

public class ObjetoVehiculoDAO extends EntityManagerFactoryDAO<ObjetoVehiculo>{
	
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
	
	public ObjetoVehiculoDAO() {
        super(ObjetoVehiculo.class);
    }
	
	public List<ObjetoVehiculo> buscarTodos(){   
		return getEntityManager().createNamedQuery("ObjetoVehiculo.buscarTodos").getResultList();
	}
	
	public ObjetoVehiculo buscarPorId(String id){
		ObjetoVehiculo objeto = new ObjetoVehiculo();
		List<ObjetoVehiculo> query = getEntityManager().createNamedQuery("ObjetoVehiculo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);
		return objeto;
	}
	
	public ObjetoVehiculo buscarPorPlaca(String placa){
		ObjetoVehiculo objeto = new ObjetoVehiculo();
		List<ObjetoVehiculo> query = getEntityManager().createNamedQuery("ObjetoVehiculo.buscarPorPlaca").setParameter("placa", placa).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);
		return objeto;
	}

	public ObjetoVehiculo buscarPorChasis(String chasis){
		ObjetoVehiculo objeto = new ObjetoVehiculo();
		List<ObjetoVehiculo> query = getEntityManager().createNamedQuery("ObjetoVehiculo.buscarPorChasis").setParameter("chasis", chasis).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);	
		return objeto;
	}
	
	public ObjetoVehiculo buscarPorMotor(String motor){
		ObjetoVehiculo objeto = new ObjetoVehiculo();
		List<ObjetoVehiculo> query = getEntityManager().createNamedQuery("ObjetoVehiculo.buscarPorMotor").setParameter("motor", motor).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);	
		return objeto;
	}
	
//	public List<ObjetoVehiculo> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<ObjetoVehiculo> results = null;
//		try{	
//			TypedQuery<ObjetoVehiculo> query = em.createQuery("SELECT c FROM ObjetoVehiculo c WHERE c.activo =:valorActivo", ObjetoVehiculo.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
	
	public List<ObjetoVehiculo> buscarObjetoVehiculoPorCotizacionDetalle(List<CotizacionDetalle> listadoCotizacionDetalle){
		List<ObjetoVehiculo> results = new ArrayList<ObjetoVehiculo>();
			
		for(int i=0; i<listadoCotizacionDetalle.size();i++){	
			List<ObjetoVehiculo> query = getEntityManager().createNamedQuery("ObjetoVehiculo.buscarPorId").setParameter("id", listadoCotizacionDetalle.get(i).getObjetoId()).getResultList();
			results.add(query.get(0));
		}						 			
		return results;
	}
}

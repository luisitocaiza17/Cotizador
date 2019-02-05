package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoVehiculo;

public class TipoVehiculoDAO extends EntityManagerFactoryDAO<TipoVehiculo>{
	
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
	
	public TipoVehiculoDAO() {
        super(TipoVehiculo.class);
    }
	
	public List<TipoVehiculo> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarTodos").getResultList();
	}
	
	public TipoVehiculo buscarPorId(String id){
		TipoVehiculo tipo = new TipoVehiculo();
		List<TipoVehiculo> query = getEntityManager().createNamedQuery("TipoVehiculo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public List<TipoVehiculo> buscarPorIds(List<String> ids){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarPorIds").setParameter("ids", ids).getResultList();
	}
	
	public List<TipoVehiculo> buscarPorNombres(List<String> nombresListado){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarPorNombres").setParameter("grupos", nombresListado).getResultList();
	}
	
	public List<TipoVehiculo> buscarPorGrupo(String nombreGrupo){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarPorGrupo").setParameter("grupo", nombreGrupo).getResultList();
	}
	
	public List<TipoVehiculo> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public List<TipoVehiculo> buscarPorGrupoCargaOPasajeros(String nombreGrupo,String cargaPasajero){
		return getEntityManager().createNamedQuery("TipoVehiculo.buscarPorGrupoCargaOPasajeros").setParameter("grupo", nombreGrupo).setParameter("cargaPasajeros", cargaPasajero).getResultList();
	}

	public TipoVehiculo buscarPorNombre(String nombre){
		TipoVehiculo tipo = new TipoVehiculo();
		List<TipoVehiculo> query = getEntityManager().createNamedQuery("TipoVehiculo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);	
		return tipo;
	}

}

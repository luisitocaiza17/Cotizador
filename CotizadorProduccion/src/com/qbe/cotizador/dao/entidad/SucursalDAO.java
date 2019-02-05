package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Sucursal;

public class SucursalDAO extends EntityManagerFactoryDAO<Sucursal>{
	
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
	
	public SucursalDAO() {
        super(Sucursal.class);
    }
	
	public List<Sucursal> buscarTodos(){
		return getEntityManager().createNamedQuery("Sucursal.buscarTodos").getResultList();
	}
	
	public Sucursal buscarPorNombre(String nombre){
		Sucursal sucursal = new Sucursal();
		List<Sucursal> query = getEntityManager().createNamedQuery("Sucursal.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			sucursal =  query.get(0);	
		return sucursal;
	}	
	
	public Sucursal buscarPorId(String id){
		Sucursal sucursal = new Sucursal();
		List<Sucursal> query = getEntityManager().createNamedQuery("Sucursal.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			sucursal =  query.get(0);
		return sucursal;				
	}
		
	public Sucursal buscarPorIdEnsurance(String sucEnsurance){
		Sucursal sucursal = new Sucursal();
		List<Sucursal> query = getEntityManager().createNamedQuery("Sucursal.buscarPorIdEnsurance").setParameter("sucEnsurance", sucEnsurance).getResultList();
		if(!query.isEmpty())
			sucursal = null;
		else
			sucursal =  query.get(0);
		return sucursal;
	}
	
	
	public List<Sucursal> buscarActivos(){
		return getEntityManager().createNamedQuery("Sucursal.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
}
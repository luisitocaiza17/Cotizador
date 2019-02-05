package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Estado;

public class EstadoDAO extends EntityManagerFactoryDAO<Estado>{
	
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
	
	public EstadoDAO() {
        super(Estado.class);
    }
	
	public List<Estado> buscarTodos(){   
		return getEntityManager().createNamedQuery("Estado.buscarTodos").getResultList();
	}
	
	
	public Estado buscarPorId(String id){
		Estado estado = new Estado();
		List<Estado> query = getEntityManager().createNamedQuery("Estado.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			estado =  query.get(0);
		return estado;
	}
	
	public Estado buscarPorClase(String clase){
		Estado estado = new Estado();
		List<Estado> query = getEntityManager().createNamedQuery("Estado.buscarPorClase").setParameter("clase", clase).getResultList();
		if(!query.isEmpty())
			estado =  query.get(0);
		return estado;		
	}
		
	public List<Estado> buscarActivos(){
		return getEntityManager().createNamedQuery("Estado.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
	
    public Estado buscarPorNombre(String nombreEstado, String clase){
    	Estado estado = new Estado();
		List<Estado> query = getEntityManager().createNamedQuery("Estado.buscarPorNombre").setParameter("nombre", nombreEstado).setParameter("clase", clase).getResultList();
		if(!query.isEmpty())
			estado =  query.get(0);
		return estado;
       }
    //Buscar por nombre y clase
    public Estado buscarPorNombreClase(String nombreEstado, String clase){      
    	Estado estado = new Estado();
		List<Estado> query = getEntityManager().createNamedQuery("Estado.buscarPorNombre").setParameter("nombre", nombreEstado).setParameter("clase", clase).getResultList();
		if(!query.isEmpty())
			estado =  query.get(0);
		return estado;
       }
    
    //Buscar todos por clase
    public List<Estado> buscarPorClaseTodos(String clase){			
		return getEntityManager().createNamedQuery("Estado.buscarPorClase").setParameter("clase", clase).getResultList();		
	}
}


package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ModeloGenerico;

public class ModeloGenericoDAO extends EntityManagerFactoryDAO<ModeloGenerico>{
	
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
	
	public ModeloGenericoDAO() {
        super(ModeloGenerico.class);
    }
	
	public List<ModeloGenerico> buscarTodos(){   
		return getEntityManager().createNamedQuery("ModeloGenerico.buscarTodos").getResultList();
	}
	
	public ModeloGenerico buscarPorId(String id){
		ModeloGenerico modelo = new ModeloGenerico();
		List<ModeloGenerico> query = getEntityManager().createNamedQuery("ModeloGenerico.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);	
		return modelo;
	}
	
	public List<ModeloGenerico> buscarActivos(){
		return getEntityManager().createNamedQuery("ModeloGenerico.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public ModeloGenerico buscarPorNombre(String nombre){
		ModeloGenerico modelo = new ModeloGenerico();
		List<ModeloGenerico> query = getEntityManager().createNamedQuery("ModeloGenerico.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);
		return modelo;
	}
}

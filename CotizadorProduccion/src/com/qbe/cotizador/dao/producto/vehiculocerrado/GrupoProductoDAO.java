package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.GrupoProducto;

public class GrupoProductoDAO extends EntityManagerFactoryDAO<GrupoProducto>{

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

	public GrupoProductoDAO() {
	    super(GrupoProducto.class);
	}
	
	public List<GrupoProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("GrupoProducto.buscarTodos").getResultList();
	}
	
	public GrupoProducto buscarPorId(String id){
		GrupoProducto grupo = new GrupoProducto();
		List<GrupoProducto> query = getEntityManager().createNamedQuery("GrupoProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			grupo =  query.get(0);
		return grupo;
	}
	
	public GrupoProducto buscarPorNombre (String nombre){
		GrupoProducto grupo = new GrupoProducto();
		List<GrupoProducto> query = getEntityManager().createNamedQuery("GrupoProducto.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			grupo =  query.get(0);
		return grupo;
	}
	
}

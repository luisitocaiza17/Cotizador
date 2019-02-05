package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.Producto;

public class ConfiguracionProductoDAO extends EntityManagerFactoryDAO<ConfiguracionProducto>{

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
	
	public ConfiguracionProductoDAO() {
        super(ConfiguracionProducto.class);
    }
	
	public ConfiguracionProducto buscarPorId(String id){
		ConfiguracionProducto configuracion = new ConfiguracionProducto();
		List<ConfiguracionProducto> query = getEntityManager().createNamedQuery("ConfiguracionProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			configuracion =  query.get(0);
		return configuracion;		
	}
	
	public List<ConfiguracionProducto> buscarPorProductosIN(List<Producto> producto){
		ConfiguracionProducto configuracion = new ConfiguracionProducto();
		List<ConfiguracionProducto> query = getEntityManager().createNamedQuery("ConfiguracionProducto.buscarPorProductosIN").setParameter("listProductos", producto).getResultList();
		return query;		
	}
	
	public List<ConfiguracionProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("ConfiguracionProducto.buscarTodos").getResultList();		
	}

	public ConfiguracionProducto buscarPorProducto(Producto producto){
		ConfiguracionProducto configuracion = new ConfiguracionProducto();
		List<ConfiguracionProducto> query = getEntityManager().createNamedQuery("ConfiguracionProducto.buscarPorProducto").setParameter("producto", producto).getResultList();
		if(!query.isEmpty())
			configuracion =  query.get(0);
		return configuracion;		
	}
}

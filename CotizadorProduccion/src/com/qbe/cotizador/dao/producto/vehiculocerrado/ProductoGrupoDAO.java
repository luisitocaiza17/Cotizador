package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ProductoGrupo;

public class ProductoGrupoDAO extends EntityManagerFactoryDAO<ProductoGrupo>{

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
	
	public ProductoGrupoDAO() {
        super(ProductoGrupo.class);
    }
	
	public List<ProductoGrupo> buscarTodos(){
		return getEntityManager().createNamedQuery("ProductoGrupo.buscarTodos").getResultList();
	}
	
	public ProductoGrupo buscarPorId(String id){
		ProductoGrupo producto = new ProductoGrupo();
		List<ProductoGrupo> query = getEntityManager().createNamedQuery("ProductoGrupo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			producto =  query.get(0);
		return producto;
	}
	
	public ProductoGrupo buscarPorNombre (String nombre){
		ProductoGrupo producto = new ProductoGrupo();
		List<ProductoGrupo> query = getEntityManager().createNamedQuery("ProductoGrupo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			producto =  query.get(0);
		return producto;
	}
	
	
}

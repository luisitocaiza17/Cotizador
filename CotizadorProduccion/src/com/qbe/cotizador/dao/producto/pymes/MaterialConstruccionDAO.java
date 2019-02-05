package com.qbe.cotizador.dao.producto.pymes;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.MaterialConstruccion;

public class MaterialConstruccionDAO extends EntityManagerFactoryDAO<MaterialConstruccion>{
	
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

	public MaterialConstruccionDAO() {
	    super(MaterialConstruccion.class);
	}

	public List<MaterialConstruccion> buscarTodos(){   
		return getEntityManager().createNamedQuery("MaterialConstruccion.buscarTodos").getResultList();
	}
	
	public MaterialConstruccion buscarPorId(String id){   
		MaterialConstruccion tipo = new MaterialConstruccion();
		List<MaterialConstruccion> query = getEntityManager().createNamedQuery("MaterialConstruccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
}

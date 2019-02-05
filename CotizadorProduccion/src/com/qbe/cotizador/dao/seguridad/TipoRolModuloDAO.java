package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoRolModulo;

public class TipoRolModuloDAO extends EntityManagerFactoryDAO<TipoRolModulo>{
	
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

	public TipoRolModuloDAO() {
	    super(TipoRolModulo.class);
	}
	
	public List<TipoRolModulo> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoRolModulo.buscarTodos").getResultList();
	}
	
	public TipoRolModulo buscarPorId(String id){
		TipoRolModulo tipoRol = new TipoRolModulo();
		List<TipoRolModulo> query = getEntityManager().createNamedQuery("TipoRolModulo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipoRol =  query.get(0);
		return tipoRol;
	}
}

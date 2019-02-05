package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoGrupo;
	
public class TipoGrupoDAO extends EntityManagerFactoryDAO<TipoGrupo>{

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

	public TipoGrupoDAO() {
	    super(TipoGrupo.class);
	}
		
	public List<TipoGrupo> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoGrupo.buscarTodos").getResultList();
	}
	
	public TipoGrupo buscarPorId(String id){
		TipoGrupo tipo = new TipoGrupo();
		List<TipoGrupo> query = getEntityManager().createNamedQuery("TipoGrupo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
		
	public TipoGrupo buscarPorNombre(String nombre){
		TipoGrupo tipo = new TipoGrupo();
		List<TipoGrupo> query = getEntityManager().createNamedQuery("TipoGrupo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}	
}

package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoEntidad;

public class TipoEntidadDAO extends EntityManagerFactoryDAO<TipoEntidad>{

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
	
	public TipoEntidadDAO() {
        super(TipoEntidad.class);
    }

	public TipoEntidad buscarPorId(String id){
		TipoEntidad tipo = new TipoEntidad();
		List<TipoEntidad> query = getEntityManager().createNamedQuery("TipoEntidad.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public List<TipoEntidad> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoEntidad.buscarTodos").getResultList();		
	}	

}

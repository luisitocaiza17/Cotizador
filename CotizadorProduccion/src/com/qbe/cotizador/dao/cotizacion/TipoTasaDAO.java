package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoTasa;

public class TipoTasaDAO  extends EntityManagerFactoryDAO<TipoTasa>{

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
	
	public TipoTasaDAO() {
        super(TipoTasa.class);
    }
	
	public TipoTasa buscarPorId(String id){
		TipoTasa tipo = new TipoTasa();
		List<TipoTasa> query = getEntityManager().createNamedQuery("TipoTasa.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo = query.get(0);
		return tipo;
	}
			
	public List<TipoTasa> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoTasa.buscarTodos").getResultList();		
	}

}
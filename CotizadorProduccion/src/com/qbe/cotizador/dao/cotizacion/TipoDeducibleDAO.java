package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoDeducible;

public class TipoDeducibleDAO  extends EntityManagerFactoryDAO<TipoDeducible>{

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
	
	public TipoDeducibleDAO() {
        super(TipoDeducible.class);
    }

	public TipoDeducible buscarPorId(String id){
		TipoDeducible tipo = new TipoDeducible();
		List<TipoDeducible> query = getEntityManager().createNamedQuery("TipoDeducible.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public List<TipoDeducible> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoDeducible.buscarTodos").getResultList();		
	}
		
}
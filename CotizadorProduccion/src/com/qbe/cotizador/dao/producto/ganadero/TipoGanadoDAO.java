package com.qbe.cotizador.dao.producto.ganadero;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoGanado;

public class TipoGanadoDAO extends EntityManagerFactoryDAO<TipoGanado>{
	
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
	
	public TipoGanadoDAO() {
		super(TipoGanado.class);
	}

	public List<TipoGanado> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoGanado.buscarTodos").getResultList();
	}
	
	public TipoGanado buscarPorId(String id){
		TipoGanado tipo = new TipoGanado();
		List<TipoGanado> query = getEntityManager().createNamedQuery("TipoGanado.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo = query.get(0);	
		return tipo;
	}

}

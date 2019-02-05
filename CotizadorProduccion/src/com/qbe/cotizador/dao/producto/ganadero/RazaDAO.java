package com.qbe.cotizador.dao.producto.ganadero;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Raza;

public class RazaDAO extends EntityManagerFactoryDAO<Raza>{
	
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
	
	public RazaDAO() {
		super(Raza.class);
	}
	
	public List<Raza> buscarTodos(){
		return getEntityManager().createNamedQuery("Raza.buscarTodos").getResultList();
	}
	
	public Raza buscarPorId(String id){
		Raza raza = new Raza();
		List<Raza> query = getEntityManager().createNamedQuery("Raza.buscarPorId").setParameter("id", id).getResultList();
		if(query.isEmpty())
			raza =  query.get(0);
		return raza;
		
	}

}

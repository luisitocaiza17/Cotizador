package com.qbe.cotizador.dao.producto.pymes;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Auditoriacoberturapyme;
import com.qbe.cotizador.model.CotizacionRespuesta;
import com.qbe.cotizador.model.UsuariosOffline;

public class AuditoriacoberturapymeDAO extends EntityManagerFactoryDAO<Auditoriacoberturapyme>{
	
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

	public AuditoriacoberturapymeDAO() {
	    super(Auditoriacoberturapyme.class);
	}
	
	public List<Auditoriacoberturapyme> cargarTodosKendo(int Skip, int Take){
		
		Query query = null;
		
		String stringQuery= "SELECT a FROM Auditoriacoberturapyme a";	
				
		query = getEntityManager().createQuery(stringQuery);
		
		List<Auditoriacoberturapyme> results = new ArrayList<Auditoriacoberturapyme>();
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		
		return results;
	}
    
    public long cargarTodosKendoPorNumero(){
		
		Query query = null;
		
		String stringQuery= "SELECT count(a.id) FROM Auditoriacoberturapyme a ";	
		
		query = getEntityManager().createQuery(stringQuery);
		
		long results = (long)query.getSingleResult();
		
		return results;
	}
}

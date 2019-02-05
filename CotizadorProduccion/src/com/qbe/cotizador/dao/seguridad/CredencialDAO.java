package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Credencial;
import com.qbe.cotizador.model.Usuario;

public class CredencialDAO extends EntityManagerFactoryDAO<Credencial>{
	
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

	public CredencialDAO() {
	    super(Credencial.class);
	}

	public List<Credencial> buscarTodos(){ 
		return getEntityManager().createNamedQuery("Credencial.buscarTodos").getResultList();		
	}
	
	public Credencial buscarPorId(String id){
		Credencial credencial = new Credencial();
		List<Credencial> query = getEntityManager().createNamedQuery("Credencial.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			credencial =  query.get(0);
		return credencial;
	}
	
	public Credencial buscarPorUsuarioId(Usuario usuario){
		Credencial credencial = new  Credencial();
		List<Credencial> query = getEntityManager().createNamedQuery("Credencial.buscarPorUsuarioId").setParameter("usuario", usuario).getResultList();
		if(!query.isEmpty())
			credencial =  query.get(0);
		return credencial;
  }
	
//	public List<Credencial> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<Credencial> results = null;
//		try{	
//			TypedQuery<Credencial> query = em.createQuery("SELECT c FROM Credencial c WHERE c.activo =:valorActivo", Credencial.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
	}
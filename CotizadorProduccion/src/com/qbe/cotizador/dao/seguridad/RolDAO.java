package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Rol;

public class RolDAO extends EntityManagerFactoryDAO<Rol>{
	
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

	public RolDAO() {
	    super(Rol.class);
	}
	
	public List<Rol> buscarTodosActivos(){
		return getEntityManager().createNamedQuery("Rol.buscarTodosActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public List<Rol> buscarTodos(){
		return getEntityManager().createNamedQuery("Rol.buscarTodos").getResultList();
	}
	
	public Rol buscarPorId(String id){
		Rol rol = new Rol();
		List<Rol> query = getEntityManager().createNamedQuery("Rol.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			rol =  query.get(0);
		return rol;
	}

	public Rol buscarRolPorDefecto(){
		Rol rol = new Rol();
		List<Rol> query = getEntityManager().createNamedQuery("Rol.buscarRolPorDefecto").setParameter("nombre", "USUARIO_WEB").getResultList();
		if(!query.isEmpty())
			rol =  query.get(0);
		return rol;
	}
}

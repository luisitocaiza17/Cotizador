package com.qbe.cotizador.dao.seguridad;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.OpcionMenuRol;

public class OpcionMenuRolDAO extends EntityManagerFactoryDAO<OpcionMenuRol>{
	
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
	
	public OpcionMenuRolDAO() {
	    super(OpcionMenuRol.class);
	}
	
	public OpcionMenuRol buscarPorId(String id)
	{
		try {
			Query q = getEntityManager().createQuery("Select omr From OpcionMenuRol omr Where omr.id = :id");
			q.setParameter("id", id);
			return (OpcionMenuRol) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public OpcionMenuRol buscarXRolXOpcion(String rol, String opcion)
	{
		try {
			Query q = getEntityManager().createQuery("Select omr From OpcionMenuRol omr where omr.rol.id = :rol and omr.opcionMenu.id = :opcion");
			q.setParameter("rol", rol);
			q.setParameter("opcion", opcion);
			return (OpcionMenuRol) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean verificarPermiso(String rol, String pantalla)
	{
		Query q = getEntityManager().createQuery("Select omr From OpcionMenuRol omr, ItemMenu im where omr.opcionMenu.id = im.opcionMenu.id and omr.rol.id = :rol and im.nombre = :pantalla");
		q.setParameter("rol", rol);
		q.setParameter("pantalla", pantalla);
		if(q.getResultList().isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

}

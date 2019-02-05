package com.qbe.cotizador.dao.seguridad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.OpcionPantalla;
import com.qbe.cotizador.model.OpcionPantallaUsuario;
import com.qbe.cotizador.model.Usuario;

public class OpcionPantallaDAO extends EntityManagerFactoryDAO<OpcionPantalla>{
	
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
	
	public OpcionPantallaDAO() {
        super(OpcionPantalla.class);
    }
	
	public List<OpcionPantallaUsuario> buscarOpcionesPantallaUsuario(Usuario usuario){
		return getEntityManager().createNamedQuery("OpcionPantallaUsuario.buscarOpcionesPantallaUsuario").setParameter("usuario", usuario).getResultList();		
	}
	
	public OpcionPantalla buscarId(String id)
	{
		Query q = getEntityManager().createNamedQuery("OpcionPantalla.findId");
		q.setParameter("id", id);
		return (OpcionPantalla) q.getSingleResult();
	}
	
}

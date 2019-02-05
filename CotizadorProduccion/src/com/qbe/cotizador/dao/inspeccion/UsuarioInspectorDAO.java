package com.qbe.cotizador.dao.inspeccion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.UsuarioInspector;
import com.qbe.cotizador.model.Usuario;

public class UsuarioInspectorDAO extends EntityManagerFactoryDAO<UsuarioInspector>{
	
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
	
	public UsuarioInspectorDAO() {
        super(UsuarioInspector.class);
    }
	
	public List<UsuarioInspector> buscarTodos(){ 
		return getEntityManager().createNamedQuery("UsuarioInspector.buscarTodos").getResultList();
	}

	public UsuarioInspector buscarPorId(String id){
		UsuarioInspector UsuarioInspector = new UsuarioInspector();
		List<UsuarioInspector> query = getEntityManager().createNamedQuery("UsuarioInspector.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			UsuarioInspector =  query.get(0);			
		return UsuarioInspector;
	}
	
	public UsuarioInspector buscarPorUsuario(Usuario usuario){
		UsuarioInspector UsuarioInspector = new UsuarioInspector();
		List<UsuarioInspector> query = getEntityManager().createNamedQuery("UsuarioInspector.buscarPorUsuario").setParameter("usuario", usuario).getResultList();
		if(!query.isEmpty())
			UsuarioInspector =  query.get(0);
		return UsuarioInspector;
	}

}

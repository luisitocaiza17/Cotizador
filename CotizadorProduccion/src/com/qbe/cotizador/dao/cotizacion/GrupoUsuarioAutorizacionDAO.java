package com.qbe.cotizador.dao.cotizacion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.GrupoAutorizacion;
import com.qbe.cotizador.model.GrupoUsuarioAutorizacion;

public class GrupoUsuarioAutorizacionDAO extends EntityManagerFactoryDAO<GrupoUsuarioAutorizacion>{
	
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
	
	public GrupoUsuarioAutorizacionDAO() {
        super(GrupoUsuarioAutorizacion.class);
    }
	
	public List<GrupoUsuarioAutorizacion> buscarTodos(){
		return getEntityManager().createNamedQuery("GrupoUsuarioAutorizacion.buscarTodos").getResultList();		
	}
	
	public GrupoUsuarioAutorizacion buscarPorId(String id){
		GrupoUsuarioAutorizacion autorizacion = new GrupoUsuarioAutorizacion();
		List<GrupoUsuarioAutorizacion> query = getEntityManager().createNamedQuery("GrupoUsuarioAutorizacion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);
		return autorizacion;
	}
	
	public List<GrupoUsuarioAutorizacion> buscarPorEmpleado(Empleado empleado){
		return getEntityManager().createNamedQuery("GrupoUsuarioAutorizacion.buscarPorEmpleado").setParameter("empleado", empleado).getResultList();		
	}
	
	public List<GrupoUsuarioAutorizacion> buscarPorGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion){
		return getEntityManager().createNamedQuery("GrupoUsuarioAutorizacion.buscarPorGrupoAutorizacion").setParameter("grupoAutorizacion", grupoAutorizacion).getResultList();		
	}	

	public List<GrupoUsuarioAutorizacion> buscarPorEmpleadoYGrupo(Empleado empleado, GrupoAutorizacion grupoAutorizacion){
		return getEntityManager().createNamedQuery("GrupoUsuarioAutorizacion.buscarPorEmpleadoYGrupo").setParameter("id", grupoAutorizacion).setParameter("empleado", empleado).getResultList();		
	}
}

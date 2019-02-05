package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Notificacion;

public class NotificacionDAO extends EntityManagerFactoryDAO<Notificacion>{
	
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
	
	public NotificacionDAO(){
		super(Notificacion.class);
	}
	
	public Notificacion buscarPorId(BigInteger notificacionId) {
		Notificacion pymeExtRamo=new Notificacion();
		List<Notificacion> results = getEntityManager().createNamedQuery("Notificacion.buscarPorId").setParameter("notificacionId", notificacionId).getResultList();			
		if(results.size()>0)
			pymeExtRamo = results.get(0);
		return pymeExtRamo;
	}
	
	public Notificacion buscarPorProceso(String proceso) {
		Notificacion pymeExtRamo=new Notificacion();
		List<Notificacion> results = getEntityManager().createNamedQuery("Notificacion.buscarPorProceso").setParameter("proceso", proceso).getResultList();			
		if(results.size()>0)
			pymeExtRamo = results.get(0);
		return pymeExtRamo;
	}
	
	public List<Notificacion> buscarTodos() {		
		return getEntityManager().createNamedQuery("Notificacion.buscarTodos").getResultList();	
	}
}

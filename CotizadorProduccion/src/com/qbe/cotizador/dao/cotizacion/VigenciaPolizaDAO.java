package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VigenciaPoliza;

public class VigenciaPolizaDAO extends EntityManagerFactoryDAO<VigenciaPoliza>{
	
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
	
	public VigenciaPolizaDAO() {
        super(VigenciaPoliza.class);
    }
	
	public List<VigenciaPoliza> buscarTodos(){
		return getEntityManager().createNamedQuery("VigenciaPoliza.buscarTodos").getResultList();		
	}
		
	public VigenciaPoliza buscarPorId(String id){
		VigenciaPoliza vigencia = new VigenciaPoliza();
		List<VigenciaPoliza> query = getEntityManager().createNamedQuery("VigenciaPoliza.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			vigencia =  query.get(0);
		return vigencia;		
	}
		
	public List<VigenciaPoliza> buscarActivos(){
		return getEntityManager().createNamedQuery("VigenciaPoliza.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
}

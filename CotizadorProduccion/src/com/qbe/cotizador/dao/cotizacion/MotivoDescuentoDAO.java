package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.MotivoDescuento;

public class MotivoDescuentoDAO extends EntityManagerFactoryDAO<MotivoDescuento>{
	
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
	
	public MotivoDescuentoDAO() {
        super(MotivoDescuento.class);
    }
	
	public MotivoDescuento buscarPorId(String id){
		MotivoDescuento descuento = new MotivoDescuento();
		List<MotivoDescuento> query = getEntityManager().createNamedQuery("MotivoDescuento.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;
	}
	
	public List<MotivoDescuento> buscarTodos(){
		return getEntityManager().createNamedQuery("MotivoDescuento.buscarTodos").getResultList();		
	}

	public MotivoDescuento buscarPorNombre(String nombre){ 		
		MotivoDescuento descuento = new MotivoDescuento();
		List<MotivoDescuento> query = getEntityManager().createNamedQuery("MotivoDescuento.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;						
	}
	
	public List<MotivoDescuento> buscarActivos(){
		return getEntityManager().createNamedQuery("MotivoDescuento.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
}

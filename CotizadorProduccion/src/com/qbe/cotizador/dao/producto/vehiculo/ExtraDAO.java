package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Extra;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Plan;

public class ExtraDAO extends EntityManagerFactoryDAO<Extra>{
	
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
	
	public ExtraDAO() {
        super(Extra.class);
    }
	
	public List<Extra> buscarPorObjetoVehiculo(ObjetoVehiculo objeto){
		return getEntityManager().createNamedQuery("Extra.buscarPorObjetoVehiculo").setParameter("objetoVehiculo", objeto).getResultList();
	}
	
	public Extra buscarPorId(String id){
		Extra extra = new Extra();
		List<Extra> query = getEntityManager().createNamedQuery("Extra.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			extra =  query.get(0);	
		return extra;
	}
}

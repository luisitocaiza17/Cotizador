package com.qbe.cotizador.dao.producto.ganadero;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.DeducibleGanadero;

public class DeducibleGanaderoDAO extends EntityManagerFactoryDAO<DeducibleGanadero>{
	
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
	
	public DeducibleGanaderoDAO() {
		super(DeducibleGanadero.class);
	}

	public List<DeducibleGanadero> buscarTodos(){
		return getEntityManager().createNamedQuery("DeducibleGanadero.buscarTodos").getResultList();
	}
	
	public DeducibleGanadero buscarPorId(String id){
		DeducibleGanadero deducible = new DeducibleGanadero();
		List<DeducibleGanadero> query = getEntityManager().createNamedQuery("DeducibleGanadero.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			deducible =  query.get(0);			
		return deducible;
	}	
	
	public List<DeducibleGanadero> buscarPorNumeroAnimale(int numeroAnimales, String TipoProduccion){
		return getEntityManager().createNamedQuery("DeducibleGanadero.buscarPorNumeroAnimale").setParameter("tipoProduccionId", TipoProduccion).getResultList();
	}
}

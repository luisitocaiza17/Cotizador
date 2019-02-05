package com.qbe.cotizador.dao.producto.ganadero;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PrecioReferencialGanadero;

public class PrecioReferencialGanaderoDAO extends EntityManagerFactoryDAO<PrecioReferencialGanadero>{
	
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
	
	public PrecioReferencialGanaderoDAO() {
		super(PrecioReferencialGanadero.class);
	}
	
	public PrecioReferencialGanadero buscarPorParametros(String tipoGanadoId, String tipoProduccion, String region){
		PrecioReferencialGanadero precio = new PrecioReferencialGanadero();
		List<PrecioReferencialGanadero> query = getEntityManager().createNamedQuery("PrecioReferencialGanadero.buscarPorParametros").setParameter("tipoGanadoId", tipoGanadoId).setParameter("tipoProduccion", tipoProduccion).setParameter("region", region).getResultList();
		if(!query.isEmpty())
			precio =  query.get(0);	
		return precio;
	}
	
	public List<PrecioReferencialGanadero> buscarTodos(){
		return getEntityManager().createNamedQuery("PrecioReferencialGanadero.buscarTodos").getResultList();
	}
	
	public PrecioReferencialGanadero buscarPorId(int precioReferencialGanaderoId){
		PrecioReferencialGanadero precio = new PrecioReferencialGanadero();
		List<PrecioReferencialGanadero> query = getEntityManager().createNamedQuery("PrecioReferencialGanadero.buscarPorId").setParameter("id", precioReferencialGanaderoId).getResultList();
		if(!query.isEmpty())
			precio =  query.get(0);	
		return precio;
	}

}

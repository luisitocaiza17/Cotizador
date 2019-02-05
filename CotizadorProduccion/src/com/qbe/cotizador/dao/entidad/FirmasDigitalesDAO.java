package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.FirmasDigitales;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.Sucursal;

public class FirmasDigitalesDAO extends EntityManagerFactoryDAO<FirmasDigitales>{
	
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
	
	public FirmasDigitalesDAO() {
        super(FirmasDigitales.class);
    }
	
	public List<FirmasDigitales> buscarTodos(){
		return getEntityManager().createNamedQuery("FirmasDigitales.buscarTodos").getResultList();
	}
		
	public FirmasDigitales buscarPorId(String id){
		FirmasDigitales firma = new FirmasDigitales();
		List<FirmasDigitales> query = getEntityManager().createNamedQuery("FirmasDigitales.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			firma =  query.get(0);					
		return firma;
	}
	
	public FirmasDigitales buscarPorRamoSucursalEntidad(Ramo ramo,Sucursal sucursal, Entidad entidad){
		FirmasDigitales firma = new FirmasDigitales();
		List<FirmasDigitales> query = getEntityManager().createNamedQuery("FirmasDigitales.buscarPorRamoSucursalEntidad").setParameter("ramo",ramo).setParameter("sucursal", sucursal).setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			firma =  query.get(0);
		return firma;
	}
}
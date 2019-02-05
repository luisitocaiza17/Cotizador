package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.FirmasSucursal;
import com.qbe.cotizador.model.Sucursal;

public class FirmasSucursalDAO extends EntityManagerFactoryDAO<FirmasSucursal>{
	
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
	
	public FirmasSucursalDAO() {
        super(FirmasSucursal.class);
    }
	
	public List<FirmasSucursal> buscarTodos(){
		return getEntityManager().createNamedQuery("FirmasSucursal.buscarTodos").getResultList();
	}
	
	public FirmasSucursal buscarPorSucursal(Sucursal sucursal, String defecto){
		FirmasSucursal firma = new FirmasSucursal();
		List<FirmasSucursal> query = getEntityManager().createNamedQuery("FirmasSucursal.buscarPorSucursal").setParameter("sucursal", sucursal).setParameter("defecto", defecto).getResultList();
		if(!query.isEmpty())
			firma =  query.get(0);
		return firma;
	}
}
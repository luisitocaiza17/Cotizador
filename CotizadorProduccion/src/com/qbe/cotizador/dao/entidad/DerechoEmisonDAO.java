package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.DerechoEmision;
import com.qbe.cotizador.model.Entidad;

public class DerechoEmisonDAO extends EntityManagerFactoryDAO<DerechoEmision>{
	
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
	
	public DerechoEmisonDAO() {
        super(DerechoEmision.class);
    }
	
	public List<DerechoEmision> buscarTodos(){
		return getEntityManager().createNamedQuery("DerechoEmision.BuscarTodos").getResultList();		
	}	
}


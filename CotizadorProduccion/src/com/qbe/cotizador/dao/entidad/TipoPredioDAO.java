package com.qbe.cotizador.dao.entidad;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.TipoPredio;

public class TipoPredioDAO extends EntityManagerFactoryDAO<TipoPredio>{

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
	
	public TipoPredioDAO() {
        super(TipoPredio.class);
    }
	
	public List<TipoPredio> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoPredio.findAll").getResultList();
	}
	
	public TipoPredio buscarPorId(String id){
		TipoPredio tipoPredio = new TipoPredio();
		List<TipoPredio> query = getEntityManager().createNamedQuery("TipoPredio.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipoPredio =  query.get(0);
		return tipoPredio;		
	}
	
	public List<TipoPredio> buscarPorActividad( BigInteger  actividadEconomicaId){
		return getEntityManager().createNamedQuery("TipoPredio.buscarPorActividadId").setParameter("actividadEconomicaId", actividadEconomicaId).getResultList();		
	}
}

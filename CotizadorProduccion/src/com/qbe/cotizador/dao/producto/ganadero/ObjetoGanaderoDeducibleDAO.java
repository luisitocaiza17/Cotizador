package com.qbe.cotizador.dao.producto.ganadero;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ObjetoGanaderoDeducible;

public class ObjetoGanaderoDeducibleDAO extends EntityManagerFactoryDAO<ObjetoGanaderoDeducible>{

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
	
	public ObjetoGanaderoDeducibleDAO() {
		super(ObjetoGanaderoDeducible.class);
	}
	
	public List<ObjetoGanaderoDeducible> buscarPorObjetoGanadero(BigInteger objetoGanaderoId){
		return getEntityManager().createNamedQuery("ObjetoGanaderoDeducible.buscarPorObjetoGanadero").setParameter("id", objetoGanaderoId).getResultList();
	}
	 
}

package com.qbe.cotizador.dao.pagos;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.InstitucionFinanciera;

public class InstitucionFinancieraDAO extends EntityManagerFactoryDAO<InstitucionFinanciera>{
	
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
	
	public InstitucionFinancieraDAO() {
        super(InstitucionFinanciera.class);
    }
	
	public List<InstitucionFinanciera> buscarTodos(){
		return getEntityManager().createNamedQuery("InstitucionFinanciera.buscarTodos").getResultList();
	}
	
	public InstitucionFinanciera buscarPorId(String id){
		InstitucionFinanciera institucion = new InstitucionFinanciera();
		List<InstitucionFinanciera> query = getEntityManager().createNamedQuery("InstitucionFinanciera.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			institucion =  query.get(0);
		return institucion;
	}
	
	
	public List<InstitucionFinanciera> buscarInstitucionFinancierasDebito(){
		return getEntityManager().createNamedQuery("InstitucionFinanciera.buscarInstitucionFinancierasDebito").getResultList();
	}
	
	public List<InstitucionFinanciera> buscarActivos(){
		return getEntityManager().createNamedQuery("InstitucionFinanciera.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
}

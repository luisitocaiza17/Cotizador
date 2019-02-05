package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.EndosoBeneficiario;

public class EndosoBeneficiarioDAO extends EntityManagerFactoryDAO<EndosoBeneficiario>{
	
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
	
	public EndosoBeneficiarioDAO() {
        super(EndosoBeneficiario.class);
    }
	
	public List<EndosoBeneficiario> buscarTodos(){
		return getEntityManager().createNamedQuery("EndosoBeneficiario.buscarTodos").getResultList();		
	}	
	
	public EndosoBeneficiario buscarPorId(String id){
		EndosoBeneficiario endoso = new EndosoBeneficiario();
		List<EndosoBeneficiario> query = getEntityManager().createNamedQuery("EndosoBeneficiario.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			endoso =  query.get(0);
		return endoso;
	}

    public EndosoBeneficiario buscarPorCotizacion(Cotizacion cotizacion){
    	EndosoBeneficiario endoso= null;
		List<EndosoBeneficiario> query = getEntityManager().createNamedQuery("EndosoBeneficiario.buscarPorCotizacion").setParameter("cotizacion", cotizacion).getResultList();
		if(!query.isEmpty())
			endoso =  query.get(0);
		return endoso;
       }
}

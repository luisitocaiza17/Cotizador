package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.PymeEndosoBeneficiario;

public class PymeEndosoBeneficiarioDAO extends EntityManagerFactoryDAO<PymeEndosoBeneficiario>{
	
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
	
	public PymeEndosoBeneficiarioDAO() {
        super(PymeEndosoBeneficiario.class);
    }
	
	public List<PymeEndosoBeneficiario> buscarTodos(){
		return getEntityManager().createNamedQuery("PymeEndosoBeneficiario.buscarTodos").getResultList();		
	}	
	
	public PymeEndosoBeneficiario buscarPorId(BigInteger id){
		PymeEndosoBeneficiario endoso = new PymeEndosoBeneficiario();
		List<PymeEndosoBeneficiario> query = getEntityManager().createNamedQuery("PymeEndosoBeneficiario.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			endoso =  query.get(0);
		return endoso;
	}

    public List<PymeEndosoBeneficiario> buscarPorCotizacion(BigInteger cotizacionId){
    	PymeEndosoBeneficiario endoso= null;
		List<PymeEndosoBeneficiario> query = getEntityManager().createNamedQuery("PymeEndosoBeneficiario.buscarPorCotizacion").setParameter("cotizacionId", cotizacionId).getResultList();
		return query;
       }
}

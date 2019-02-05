package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCobertura;
import com.qbe.cotizador.model.PymeObjetoCotizacionCobertura;

public class PymeObjetoCotizacionCoberturaDAO extends EntityManagerFactoryDAO<PymeObjetoCotizacionCobertura>{
	
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
	
	public PymeObjetoCotizacionCoberturaDAO() {
		super(PymeObjetoCotizacionCobertura.class);
	}
	
	public List<PymeObjetoCotizacionCobertura> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeObjetoCotizacionCobertura p", PymeObjetoCotizacionCobertura.class).getResultList();
	}
	
	public PymeObjetoCotizacionCobertura buscarPorId(BigInteger id){
		PymeObjetoCotizacionCobertura pymeObjetoCotizacionCobertura = new PymeObjetoCotizacionCobertura();
		List <PymeObjetoCotizacionCobertura>results = getEntityManager().createQuery("SELECT c FROM PymeObjetoCotizacionCobertura c where c.objetoPymesCoberturaId=:id", PymeObjetoCotizacionCobertura.class).setParameter("id", id).getResultList();
		if(!results.isEmpty())
			pymeObjetoCotizacionCobertura = results.get(0);
		return pymeObjetoCotizacionCobertura;
	}
	
	public List<PymeObjetoCotizacionCobertura> buscarPorObjetoPymeId(BigInteger id){
		return getEntityManager().createQuery("SELECT c FROM PymeObjetoCotizacionCobertura c where c.objetoPymesId=:id order by c.objetoPymesId", PymeObjetoCotizacionCobertura.class).setParameter("id", id).getResultList();
	}
	
	
	public List<PymeObjetoCotizacionCobertura> buscarPorCotizacionId(BigInteger id, String tipoOrigen){
		return getEntityManager().createQuery("SELECT c FROM PymeObjetoCotizacionCobertura c where c.objetoPymesId=:id and c.tipoOrigen=:tipoOrigen", PymeObjetoCotizacionCobertura.class).setParameter("id", id).setParameter("tipoOrigen", tipoOrigen).getResultList();
	}
	
	public List<PymeObjetoCotizacionCobertura> buscarPorCotizacionTipoOrigenId(BigInteger id, String tipoOrigen,BigInteger objetoOrigenId){
		return getEntityManager().createQuery("SELECT c FROM PymeObjetoCotizacionCobertura c where c.objetoPymesId=:id and c.tipoOrigen=:tipoOrigen and c.objetoOrigenId=:objetoOrigenId", PymeObjetoCotizacionCobertura.class).setParameter("id", id).setParameter("tipoOrigen", tipoOrigen).setParameter("objetoOrigenId", objetoOrigenId).getResultList();
	}
}

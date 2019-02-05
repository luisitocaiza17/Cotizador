package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeDerechoEmision;
import com.qbe.cotizador.model.PymeTextoGrupoCobertura;

public class PymeTextoGrupoCoberturaDAO extends EntityManagerFactoryDAO<PymeTextoGrupoCobertura> {
	@PersistenceContext(name = "CotizadorWebPC", unitName = "CotizadorWebPU")
	private EntityManager em;	
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null) {
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
	
	public PymeTextoGrupoCoberturaDAO() {
		super(PymeTextoGrupoCobertura.class);
	}
	
	public List<PymeTextoGrupoCobertura> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeTextoGrupoCobertura.buscarTodos").getResultList();		
	}
	
	public PymeTextoGrupoCobertura buscarPorId(String textoGrupoCoberturaId) {
		PymeTextoGrupoCobertura pymeTextoGrupoCobertura = new PymeTextoGrupoCobertura();
		List<PymeTextoGrupoCobertura> results = getEntityManager().createNamedQuery("PymeTextoGrupoCobertura.buscarPorId").setParameter("textoGrupoCobertura",textoGrupoCoberturaId).getResultList();
		if(results.size() > 0)
			pymeTextoGrupoCobertura = results.get(0);
		return pymeTextoGrupoCobertura;
	}
	
	public List<PymeTextoGrupoCobertura> buscarPorRamoId(String ramoId) {		
		return getEntityManager().createNamedQuery("PymeTextoGrupoCobertura.buscarPorRamoId").setParameter("ramoId",ramoId).getResultList();		
		 		
	}
	
	public List<PymeTextoGrupoCobertura> buscarPorGrupoCoberturaId(String grupoCoberturaId) {
		return getEntityManager().createNamedQuery("PymeTextoGrupoCobertura.buscarGrupoCoberturaId").setParameter("grupoCoberturaId",grupoCoberturaId).getResultList();	
	}
	
	
}

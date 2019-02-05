package com.qbe.cotizador.dao.producto.agricola;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgriSucreDetalle;

public class AgriSucreDetalleDAO extends EntityManagerFactoryDAO<AgriSucreDetalle>{

	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	public AgriSucreDetalleDAO(Class<AgriSucreDetalle> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
	public AgriSucreDetalleDAO(){
		super(AgriSucreDetalle.class);
	}

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
	
	public AgriSucreDetalle buscarPorObjetoId(String id){
		AgriSucreDetalle agriSucreDetalle = new AgriSucreDetalle();
		List<AgriSucreDetalle> query = getEntityManager().createNamedQuery("AgriSucreDetalle.buscarObjetoAgricolaId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			agriSucreDetalle =  query.get(0);
		else return null;
		return agriSucreDetalle;		
	}

}

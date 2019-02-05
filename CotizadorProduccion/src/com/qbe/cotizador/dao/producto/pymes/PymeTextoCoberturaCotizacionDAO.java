package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeTextoGrupoCobertura;
import com.qbe.cotizador.model.PymeTextoGrupoCoberturaCotizacion;

public class PymeTextoCoberturaCotizacionDAO extends EntityManagerFactoryDAO<PymeTextoGrupoCoberturaCotizacion>{
	
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
	
	public PymeTextoCoberturaCotizacionDAO(){
		super(PymeTextoGrupoCoberturaCotizacion.class);
	}
	
	public List<PymeTextoGrupoCoberturaCotizacion> buscarTextoCoberturaCotizacionPorCotizacionId(BigInteger cotizacionId, BigInteger grupoPorProductoId) {
		return getEntityManager().createQuery("SELECT c FROM PymeTextoGrupoCoberturaCotizacion c where c.cotizacionId=:objetoId and c.grupoPorProductoId=:grupoPorProductoId order by c.ramoOrden", PymeTextoGrupoCoberturaCotizacion.class).setParameter("objetoId", cotizacionId).setParameter("grupoPorProductoId", grupoPorProductoId).getResultList();
	}

	/* Obtenemos las coberturas ejecutando un sp */
	public List<PymeTextoGrupoCoberturaCotizacion> buscarTextoCoberturaCotizacionPorCotizacionIdSP(String cotizacionId, String grupoPorProductoId){
		
		try{
			StoredProcedureQuery storedProcedure = getEntityManager().createStoredProcedureQuery("SP_PYME_COBERTURA_TEXTOS");
			
			storedProcedure.registerStoredProcedureParameter("cotizacionId", String.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("grupoProducto", String.class, ParameterMode.IN);
			
			storedProcedure.setParameter("cotizacionId", cotizacionId);
			storedProcedure.setParameter("grupoProducto", grupoPorProductoId);
			
			List<PymeTextoGrupoCoberturaCotizacion> listadoCoberturas = new ArrayList<PymeTextoGrupoCoberturaCotizacion>();			
			List rs=storedProcedure.getResultList();
			PymeTextoGrupoCobertura pymeTextoGrupoCobertura = new PymeTextoGrupoCobertura();
			PymeTextoGrupoCoberturaDAO pymeTextoGrupoCoberturaDAO = new PymeTextoGrupoCoberturaDAO();			
			for(int i=0; i< rs.size(); i++) {
				PymeTextoGrupoCoberturaCotizacion objetoCoberturas= new PymeTextoGrupoCoberturaCotizacion();
				Object row[] = (Object[])rs.get(i);				
				 for (int col=0; col<row.length; ++col ) {
					 if(col==0)
						 objetoCoberturas.setId(new BigInteger(""+row[col]));						 
					 if(col==1)
						 objetoCoberturas.setNombre(""+row[col]);	 
					 if(col==2)
						 objetoCoberturas.setCotizacionId(new BigInteger(""+row[col]));	
					 if(col==3){
						 pymeTextoGrupoCobertura=pymeTextoGrupoCoberturaDAO.buscarPorId(""+row[col]);
						 objetoCoberturas.setTexto(pymeTextoGrupoCobertura.getTexto());
					 }
					 if(col==4)
						 objetoCoberturas.setGrupoPorProductoId(new BigInteger(""+row[col])); 
					 if(col==5)
						 objetoCoberturas.setRamoId(new BigInteger(""+row[col]));
					 if(col==6)
						 objetoCoberturas.setRamoOrden(Integer.parseInt(""+row[col]));
					 
			       //System.out.printf(""+row[col]);
			    }
				listadoCoberturas.add(objetoCoberturas);				
	        }	
			//System.out.println("sin problema");
			return  listadoCoberturas;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
		
	}
	
}

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
import com.qbe.cotizador.model.PymeCoberturaCotizacionValor;

public class PymeCoberturaCotizacionValorDAO extends EntityManagerFactoryDAO<PymeCoberturaCotizacionValor>{
	
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
	
	public PymeCoberturaCotizacionValorDAO(){
		super(PymeCoberturaCotizacionValor.class);
	}
	
	public List<PymeCoberturaCotizacionValor> buscarPorGrupoCoberturaId(BigInteger grupoCoberturaId, BigInteger cotizacionId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.grupoProductoId=:grupoCoberturaId and c.cotizacionId=:cotizacionId").setParameter("grupoCoberturaId", grupoCoberturaId).setParameter("cotizacionId", cotizacionId).getResultList();
	}

	public List<PymeCoberturaCotizacionValor> buscarPorCotizacionDetalleId(BigInteger cotizacionDetalleId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.cotizacionDetalleId=:cotizacionDetalleId").setParameter("cotizacionDetalleId", cotizacionDetalleId).getResultList();
	}
	
	public List<PymeCoberturaCotizacionValor> buscarPorCotizacionDetalleIdRamoId(BigInteger cotizacionDetalleId, BigInteger ramoId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.cotizacionDetalleId=:cotizacionDetalleId and c.ramoId=:ramoId").setParameter("cotizacionDetalleId", cotizacionDetalleId).setParameter("ramoId", ramoId).getResultList();
	}
	
	public List<PymeCoberturaCotizacionValor> buscarPorCotizacionCoberturaId(BigInteger cotizacionId, BigInteger coberturaPymesId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.cotizacionId=:cotizacionId and c.coberturaPymesId=:coberturaPymesId").setParameter("cotizacionId", cotizacionId).setParameter("coberturaPymesId", coberturaPymesId).getResultList();
	}
	
	public List<PymeCoberturaCotizacionValor> buscarPorCotizacionRamoId(BigInteger cotizacionId, BigInteger ramoId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.cotizacionId=:cotizacionId and c.ramoId=:ramoId").setParameter("cotizacionId", cotizacionId).setParameter("ramoId", ramoId).getResultList();
	}
	
	public List<PymeCoberturaCotizacionValor> buscarPorCotizacionId(BigInteger cotizacionId) {
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaCotizacionValor c where c.cotizacionId=:cotizacionId").setParameter("cotizacionId", cotizacionId).getResultList();
	}
	/* Obtenemos las coberturas ejecutando un sp */
	public List<PymeCoberturaCotizacionValor> buscarValorCoberturaCotizacionPorCotizacionIdSP(String cotizacionId, String ramoId){
		
		try{
			StoredProcedureQuery storedProcedure = getEntityManager().createStoredProcedureQuery("SP_PYME_COBERTURA_VALOR");
			
			storedProcedure.registerStoredProcedureParameter("cotizacionId", String.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("ramoId", String.class, ParameterMode.IN);
			
			storedProcedure.setParameter("cotizacionId", cotizacionId);
			storedProcedure.setParameter("ramoId", ramoId);
			
			List<PymeCoberturaCotizacionValor> listadoCoberturas = new ArrayList<PymeCoberturaCotizacionValor>();			
			List rs=storedProcedure.getResultList();
			
			for(int i=0; i< rs.size(); i++) {
				PymeCoberturaCotizacionValor objetoCoberturas= new PymeCoberturaCotizacionValor();
				Object row[] = (Object[])rs.get(i);				
				 for (int col=0; col<row.length; ++col ) {
					 if(col==0)
						 objetoCoberturas.setGrupoProductoId(new BigInteger(""+row[col]));						 
					 if(col==1)
						 objetoCoberturas.setRamoId(new BigInteger(""+row[col]));
					 if(col==2){
						 if((""+row[col]).equals("NULL")||(""+row[col]).equals("null"))
							 objetoCoberturas.setCoberturaEnsMultiId(null);
						 else
							 objetoCoberturas.setCoberturaEnsMultiId(""+row[col]);
					 }
					 if(col==3){
						 if((""+row[col]).equals("NULL")||(""+row[col]).equals("null"))
							 objetoCoberturas.setCoberturaEnsProgrId(null);
						 else
							 objetoCoberturas.setCoberturaEnsProgrId(""+row[col]);
					 }
					 if(col==4)
						 objetoCoberturas.setNombre(""+row[col]); 
					 if(col==5)
						 objetoCoberturas.setTasaSugerida(Double.parseDouble(""+row[col]));
					 if(col==6)
						 objetoCoberturas.setTasaIngresada(Double.parseDouble(""+row[col]));
					 if(col==7)
						 objetoCoberturas.setValorIngresado(Double.parseDouble(""+row[col]));
					 if(col==8)
						 objetoCoberturas.setPrimaCalculada(Double.parseDouble(""+row[col]));
					 if(col==9)
						 objetoCoberturas.setCotizacionId(new BigInteger(""+row[col]));
					 if(col==10)
						 objetoCoberturas.setObjetoPymesCoberturaId(new BigInteger(""+row[col]));
					 if(col==11)
						 objetoCoberturas.setCotizacionDetalleId(new BigInteger(""+row[col]));
					 if(col==12)
						 objetoCoberturas.setCoberturaPymesId(new BigInteger(""+row[col]));
					 if(col==13)
						 objetoCoberturas.setTipoOrigen(""+row[col]);
					 if(col==14)
						 objetoCoberturas.setValorMaximoLimiteAsegurado(Double.parseDouble(""+row[col]));
					 if(col==15)
						 objetoCoberturas.setPorcentajeLimiteAsegurado(Double.parseDouble(""+row[col]));
					 if(col==16)
						 objetoCoberturas.setTipoCoberturaId(new BigInteger(""+row[col]));
					 
			      // System.out.printf(""+row[col]);
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

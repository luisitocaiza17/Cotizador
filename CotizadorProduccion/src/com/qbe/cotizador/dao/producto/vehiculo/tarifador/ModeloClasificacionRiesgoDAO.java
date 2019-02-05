package com.qbe.cotizador.dao.producto.vehiculo.tarifador;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ModeloClasificaRiesgo;

public class ModeloClasificacionRiesgoDAO extends EntityManagerFactoryDAO<ModeloClasificaRiesgo>{

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

	public ModeloClasificacionRiesgoDAO() {
	    super(ModeloClasificaRiesgo.class);
	}
	
	
	public ModeloClasificaRiesgo buscarPorClasificacionRiesgoPorModelo(Modelo modelo, Cobertura cobertura){
		ModeloClasificaRiesgo modeloRiesgo = new ModeloClasificaRiesgo();
		List<ModeloClasificaRiesgo> query = getEntityManager().createNamedQuery("ModeloClasificaRiesgo.buscarPorClasificacionRiesgoPorModelo").setParameter("modelo", modelo).setParameter("cobertura", cobertura).getResultList();
		if(!query.isEmpty())
			modeloRiesgo =  query.get(0);
		return modeloRiesgo;
	}
	
}

package com.qbe.cotizador.dao.entidad;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cargo;
import com.qbe.cotizador.model.SolicitudDescuento;

public class CargoDAO extends EntityManagerFactoryDAO<Cargo>{
	
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
	
	public CargoDAO() {
        super(Cargo.class);
    } 
	
	public List<Cargo> buscarTodos(){
		return getEntityManager().createNamedQuery("Cargo.buscarTodos").getResultList();		
	}
		
	public Cargo buscarPorId(String id){
		Cargo cargo = new Cargo();
		List<Cargo> query = getEntityManager().createNamedQuery("Cargo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cargo =  query.get(0);
		return cargo;		
	}
		
	public List<Cargo> buscarActivos(){
		return getEntityManager().createNamedQuery("Cargo.buscarActivos").setParameter("valorActivo", true).getResultList();			
	}

	public List<Cargo> buscarPorCargoGenerico(String cargoGenerico){
		return getEntityManager().createNamedQuery("Cargo.buscarPorCargoGenerico").setParameter("cargoGenerico", cargoGenerico).getResultList();		
	}
    
    public Cargo buscarPorCargo(String cargo){
        Cargo cargoObtenido = new Cargo();
        List<Cargo> query = getEntityManager().createNamedQuery("Cargo.buscarPorCargo").setParameter("cargo", cargo).getResultList();
        if(!query.isEmpty())
            cargoObtenido =  query.get(0);
        return cargoObtenido;       
    }   
    
    public List<String> buscarDiferenteCargoGenerico(){
        List<String> results = new ArrayList<String>();    
        Query query = getEntityManager().createNativeQuery("SELECT distinct c.nombre_generico FROM CARGO c ");
        results = query.getResultList();
        //results = getEntityManager().createNativeQuery("SELECT distinct c.nombre_generico FROM CARGO c ");
        
        return results;
    }   	
}

package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriParroquiasDetalle;
import com.qbe.cotizador.model.AgriReglaVta;
import com.qbe.cotizador.model.UsuariosOffline;

public class AgriParroquiasDetalleDAO 
extends EntityManagerFactoryDAO<UsuariosOffline> {
    @PersistenceContext(name="CotizadorWebPC", unitName="CotizadorWebPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        if (this.em == null) {
            InitialContext initCtx = null;
            try {
                initCtx = new InitialContext();
                this.em = (EntityManager)initCtx.lookup("java:comp/env/CotizadorWebPC");
            }
            catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return this.em;
    }

    public AgriParroquiasDetalleDAO() {
        super((Class)AgriParroquiasDetalle.class);
    }
    
    public List<AgriParroquiasDetalle> cargarTodosKendo(int Skip, int Take, String provinciaId, String cantonId){
				
		Query query = null;
		
		String stringQuery= "SELECT c FROM AgriParroquiasDetalle c WHERE c.id IS NOT NULL";	
				
		String valoresWhereQuery = "";
				
		if(!provinciaId.equals(""))
			valoresWhereQuery +=" AND c.provinciaId =:provinciaId ";
		if(!cantonId.equals(""))
			valoresWhereQuery +=" AND c.cantonId =:cantonId ";
		
		stringQuery = stringQuery + valoresWhereQuery ;
		
		query = getEntityManager().createQuery(stringQuery, AgriParroquiasDetalle.class);
			
		if(!provinciaId.equals(""))
			query.setParameter("provinciaId", new BigInteger(provinciaId));
		if(!cantonId.equals(""))
			query.setParameter("cantonId", new BigInteger(cantonId));
		
		List<AgriParroquiasDetalle> results = new ArrayList<AgriParroquiasDetalle>();
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		
		return results;
	}
    
    public long cargarTodosKendoPorNumero(int Skip, int Take, String provinciaId, String cantonId){
		
		Query query = null;
		
		String stringQuery= "SELECT  count(c.id) FROM AgriParroquiasDetalle c WHERE c.id IS NOT NULL";	
				
		String valoresWhereQuery = "";
				
		if(!provinciaId.equals(""))
			valoresWhereQuery +=" AND c.provinciaId =:provinciaId ";
		if(!cantonId.equals(""))
			valoresWhereQuery +=" AND c.cantonId =:cantonId ";
		
		stringQuery = stringQuery + valoresWhereQuery ;
		
		query = getEntityManager().createQuery(stringQuery, AgriParroquiasDetalle.class);
			
		if(!provinciaId.equals(""))
			query.setParameter("provinciaId", new BigInteger(provinciaId));
		if(!cantonId.equals(""))
			query.setParameter("cantonId", new BigInteger(cantonId));
		
		long results = (long)query.getSingleResult();
		
		return results;
	}
        
}
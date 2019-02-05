package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.MovimientoCotizacion;

public class MovimientoCotizacionDAO extends EntityManagerFactoryDAO<MovimientoCotizacion>{
    
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
    
    public MovimientoCotizacionDAO() {
        super(MovimientoCotizacion.class);
    }
    
    public List<MovimientoCotizacion> buscarTodos(){   
        return getEntityManager().createNamedQuery("MovimientoCotizacion.buscarTodos").getResultList();
    }
    
    
    public MovimientoCotizacion buscarPorId(String id){
      MovimientoCotizacion movimientoCotizacion = new MovimientoCotizacion();
        List<MovimientoCotizacion> query = getEntityManager().createNamedQuery("MovimientoCotizacion.buscarPorId").setParameter("id", id).getResultList();
        if(!query.isEmpty())
          movimientoCotizacion =  query.get(0);
        return movimientoCotizacion;
    }
    
    public MovimientoCotizacion buscarPorCotizacion(String cotizacionId){
      MovimientoCotizacion estado = new MovimientoCotizacion();
        List<MovimientoCotizacion> query = getEntityManager().createNamedQuery("MovimientoCotizacion.buscarPorCotizacion").setParameter("cotizacionId", cotizacionId).getResultList();
        if(!query.isEmpty())
            estado =  query.get(0);
        return estado;
       }
}

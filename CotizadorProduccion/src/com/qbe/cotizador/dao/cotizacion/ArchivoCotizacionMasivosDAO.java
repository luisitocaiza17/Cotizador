package com.qbe.cotizador.dao.cotizacion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ArchivoCotizacionMasivo;

public class ArchivoCotizacionMasivosDAO  extends EntityManagerFactoryDAO <ArchivoCotizacionMasivo>{
	
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
	
	public ArchivoCotizacionMasivosDAO() {
        super(ArchivoCotizacionMasivo.class);
    }
	
	public List<ArchivoCotizacionMasivo> buscarRegistrosArchivo(String archivo){   
		return getEntityManager().createNamedQuery("ArchivoCotizacionMasivo.buscarRegistrosArchivo").setParameter("nombreArchivo", archivo).getResultList();
	}
    
    public ArchivoCotizacionMasivo buscarPorId(String id){
        ArchivoCotizacionMasivo archivo = new ArchivoCotizacionMasivo();
        List<ArchivoCotizacionMasivo> query = getEntityManager().createNamedQuery("ArchivoCotizacionMasivo.buscarPorId").setParameter("id", id).getResultList();
        if(!query.isEmpty())
            archivo =  query.get(0);
        return archivo;
    }   
    
    public ArchivoCotizacionMasivo buscarPorCotizacionId(String cotizacionId){
      ArchivoCotizacionMasivo archivo = new ArchivoCotizacionMasivo();
      List<ArchivoCotizacionMasivo> query = getEntityManager().createNamedQuery("ArchivoCotizacionMasivo.buscarPorCotizacionId").setParameter("cotizacionId", cotizacionId).getResultList();
      if(!query.isEmpty())
          archivo =  query.get(0);
      return archivo;
  }
}

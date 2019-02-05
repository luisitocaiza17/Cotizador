package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.TipoDocumento;

public class DocumentoVisadoDAO extends EntityManagerFactoryDAO<DocumentoVisado>{
	
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
	
	public DocumentoVisadoDAO() {
        super(DocumentoVisado.class);
    }
	
	public List<DocumentoVisado> buscarTodos(){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarTodos").getResultList();
	}
		
	public DocumentoVisado buscarPorId(String id){
		DocumentoVisado documento = new DocumentoVisado();
		List<DocumentoVisado> query = getEntityManager().createNamedQuery("DocumentoVisado.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			documento =  query.get(0);
		return documento;		
	}
	
	public List<DocumentoVisado> buscarPorEntidad(String entidad){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorEntidad").setParameter("entidad", entidad).getResultList();
	}
	
	public List<DocumentoVisado> buscarPorObjetoId(String objetoId){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorObjetoId").setParameter("objetoId", objetoId).getResultList();
	}
	
	public List<DocumentoVisado> buscarPorCotizacionId(Cotizacion cotizacion){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorCotizacionId").setParameter("cotizacion", cotizacion).getResultList();
	}
	
	public List<DocumentoVisado> buscarPorCotizacionTipoDocumento(Cotizacion cotizacion, TipoDocumento tipoDocumento){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorCotizacionTipoDocumento").setParameter("cotizacion", cotizacion).setParameter("tipoDocumento", tipoDocumento).getResultList();
	}
	
	public List<DocumentoVisado> buscarPorObjetoIdTipoDocumento(String objetoId, TipoDocumento tipoDocumento){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorCotizacionTipoDocumento").setParameter("objetoId", objetoId).setParameter("tipoDocumento", tipoDocumento).getResultList();		
	}
	
	public List<DocumentoVisado> buscarPorEntidadTipoDocumento(Entidad entidad, TipoDocumento tipoDocumento){
		return getEntityManager().createNamedQuery("DocumentoVisado.buscarPorEntidadTipoDocumento").setParameter("entidad", entidad).setParameter("tipoDocumento", tipoDocumento).getResultList();		
	}
}

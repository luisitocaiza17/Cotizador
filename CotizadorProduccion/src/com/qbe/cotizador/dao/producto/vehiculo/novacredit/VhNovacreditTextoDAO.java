package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditTexto;

public class VhNovacreditTextoDAO extends EntityManagerFactoryDAO<VhNovacreditTexto>{
	
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
	
	public VhNovacreditTextoDAO() {
        super(VhNovacreditTexto.class);
    }
	
	public List<VhNovacreditTexto> buscarTodos(){
		return getEntityManager().createNamedQuery("VhNovacreditTexto.buscarTodos").getResultList();		
	}
		
	public VhNovacreditTexto buscarPorId(String id){
		VhNovacreditTexto novaTexto = new VhNovacreditTexto();
		List<VhNovacreditTexto> query = getEntityManager().createNamedQuery("VhNovacreditTexto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			novaTexto =  query.get(0);
		return novaTexto;		
	}
		
}

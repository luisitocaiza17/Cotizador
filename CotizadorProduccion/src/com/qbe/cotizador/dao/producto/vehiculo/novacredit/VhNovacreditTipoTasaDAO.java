package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditTipoTasa;

public class VhNovacreditTipoTasaDAO extends EntityManagerFactoryDAO<VhNovacreditTipoTasa>{
	
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
	
	public VhNovacreditTipoTasaDAO() {
        super(VhNovacreditTipoTasa.class);
    }
	
	public List<VhNovacreditTipoTasa> buscarTodos(){
		return getEntityManager().createNamedQuery("VhNovacreditTipoTasa.buscarTodos").getResultList();		
	}
		
	public VhNovacreditTipoTasa buscarPorId(String id){
		VhNovacreditTipoTasa tipoCobertura = new VhNovacreditTipoTasa();
		List<VhNovacreditTipoTasa> query = getEntityManager().createNamedQuery("VhNovacreditTipoTasa.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipoCobertura =  query.get(0);
		return tipoCobertura;		
	}
		
}
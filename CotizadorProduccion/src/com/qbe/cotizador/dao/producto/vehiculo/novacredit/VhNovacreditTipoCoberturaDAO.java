package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditTipoCobertura;

public class VhNovacreditTipoCoberturaDAO extends EntityManagerFactoryDAO<VhNovacreditTipoCobertura>{
	
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
	
	public VhNovacreditTipoCoberturaDAO() {
        super(VhNovacreditTipoCobertura.class);
    }
	
	public List<VhNovacreditTipoCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("VhNovacreditTipoCobertura.buscarTodos").getResultList();		
	}
		
	public VhNovacreditTipoCobertura buscarPorId(String id){
		VhNovacreditTipoCobertura tipoCobertura = new VhNovacreditTipoCobertura();
		List<VhNovacreditTipoCobertura> query = getEntityManager().createNamedQuery("VhNovacreditTipoCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipoCobertura =  query.get(0);
		return tipoCobertura;		
	}
		
}

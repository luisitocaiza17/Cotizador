package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditCotizacion;
import com.qbe.cotizador.model.VhNovacreditCotizacionXPeriodo;
import com.qbe.cotizador.model.VhNovacreditPeriodo;
import com.qbe.cotizador.transaction.producto.novacredit.VhNovacreditCotizacionTransaction;

public class VhNovacreditCotizacionXPeriodoDAO extends EntityManagerFactoryDAO<VhNovacreditCotizacionXPeriodo>{
	
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
	
	public VhNovacreditCotizacionXPeriodoDAO() {
        super(VhNovacreditCotizacionXPeriodo.class);
    }
	
	public List<VhNovacreditCotizacionXPeriodo> buscarTodos(){
		return getEntityManager().createNamedQuery("VhNovacreditCotizacionXPeriodo.buscarTodos").getResultList();		
	}
	
public VhNovacreditCotizacionXPeriodo buscarPorId(String id){
	VhNovacreditCotizacionXPeriodo novaCotizacion = new VhNovacreditCotizacionXPeriodo();
	List<VhNovacreditCotizacionXPeriodo> query = getEntityManager().createNamedQuery("VhNovacreditCotizacionXPeriodo.buscarPorId").setParameter("id", id).getResultList();
	if(!query.isEmpty())
		novaCotizacion =  query.get(0);
	return novaCotizacion;		
}

public VhNovacreditCotizacionXPeriodo buscarPorCotizacionPeriodo(VhNovacreditCotizacion vhNovacreditCotizacion, VhNovacreditPeriodo vhNovacreditPeriodo){
VhNovacreditCotizacionXPeriodo novaCotizacion = new VhNovacreditCotizacionXPeriodo();
List<VhNovacreditCotizacionXPeriodo> query = getEntityManager().createNamedQuery("VhNovacreditCotizacionXPeriodo.buscarPorCotizacionPeriodo").setParameter("vhNovacreditCotizacion", vhNovacreditCotizacion).setParameter("vhNovacreditPeriodo", vhNovacreditPeriodo).getResultList();
if(!query.isEmpty())
	novaCotizacion =  query.get(0);
return novaCotizacion;		
}
public List<VhNovacreditCotizacionXPeriodo> buscarPorCotazacion(VhNovacreditCotizacion vhNovacreditCotizacion){
	return getEntityManager().createNamedQuery("VhNovacreditCotizacionXPeriodo.buscarPorCotizacion").setParameter("vhNovacreditCotizacion", vhNovacreditCotizacion).getResultList();		
}


}

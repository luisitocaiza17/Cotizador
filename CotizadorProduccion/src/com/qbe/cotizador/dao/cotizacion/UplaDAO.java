package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Upla;

public class UplaDAO extends EntityManagerFactoryDAO<Upla>{

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
	
	public UplaDAO() {
        super(Upla.class);
    }
	
	public List<Upla> buscarTodos(){
		return getEntityManager().createNamedQuery("Upla.buscarTodos").getResultList();		
	}
	
	public Upla buscarPorId(String id){
		Upla upla = new Upla();
		List<Upla> query = getEntityManager().createNamedQuery("Upla.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			upla =  query.get(0);					
		return upla;
	}
	
	public Upla buscarPorCliente(Cliente cliente){
		Upla upla = new Upla();
		List<Upla> query = getEntityManager().createNamedQuery("Upla.buscarPorCliente").setParameter("cliente", cliente).getResultList();
		if(!query.isEmpty())
			upla =  query.get(0);
		return upla;		
	}
	
}

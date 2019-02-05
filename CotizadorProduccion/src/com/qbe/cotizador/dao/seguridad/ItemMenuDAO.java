package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ItemMenu;
import com.qbe.cotizador.model.OpcionMenu;

public class ItemMenuDAO extends EntityManagerFactoryDAO<ItemMenu>{
	
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

	public ItemMenuDAO() {
	    super(ItemMenu.class);
	}
	
	public List<ItemMenu> buscarTodos(){
		return getEntityManager().createNamedQuery("ItemMenu.buscarTodos").getResultList();
	}
		
	public List<ItemMenu> buscarPorOpcionMenu(OpcionMenu opcionMenu){
		return getEntityManager().createNamedQuery("ItemMenu.buscarPorOpcionMenu").setParameter("opcionMenu", opcionMenu).setParameter("activo", true).getResultList();
	}
}

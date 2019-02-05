package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEndosoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.model.AgriCotizacionEndoso;
import com.qbe.cotizador.model.AgriRegla;

public class AgriCotizacionEndosoTransaction {

	public AgriCotizacionEndosoTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public AgriCotizacionEndoso crear (AgriCotizacionEndoso agriCotizacionEndoso){
		
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizacionEndosoDAO agriCotizacionEndosoDAO = new AgriCotizacionEndosoDAO();
			agriCotizacionEndoso = agriCotizacionEndosoDAO.crear(agriCotizacionEndoso);
			ut.commit();
		}
		catch(Exception e)
		{
			try{
				ut.rollback();
			}
			catch(IllegalStateException | SecurityException | SystemException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return agriCotizacionEndoso;
	}
	public AgriCotizacionEndoso editar(AgriCotizacionEndoso agriCotizacionEndoso){
		UserTransaction ut= null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizacionEndosoDAO agriCotizacionEndosoDAO = new AgriCotizacionEndosoDAO();
			agriCotizacionEndoso = agriCotizacionEndosoDAO.editar(agriCotizacionEndoso);
			ut.commit();
		}
		catch(Exception e)
		{
			try{
				ut.rollback();
			}
			catch(IllegalStateException | SecurityException | SystemException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return agriCotizacionEndoso;
	}
	public void eliminar(AgriCotizacionEndoso agriCotizacionEndoso){
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizacionEndosoDAO agriCotizacionEndosoDAO = new AgriCotizacionEndosoDAO();
			AgriCotizacionEndoso agriCotizacionEndoso2 = new AgriCotizacionEndoso();
			agriCotizacionEndoso2 = agriCotizacionEndosoDAO.buscarPorId(agriCotizacionEndoso.getId());
			if (agriCotizacionEndoso2!=null)
			{
				agriCotizacionEndosoDAO.eliminar(agriCotizacionEndoso);
				ut.commit();
			}
		}
		catch(Exception e)
		{
			try 
			{
				ut.rollback();
			}
			catch (IllegalStateException | SecurityException | SystemException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}

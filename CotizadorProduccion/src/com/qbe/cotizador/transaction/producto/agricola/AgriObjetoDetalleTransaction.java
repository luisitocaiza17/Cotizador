package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriObjetoDetalleDAO;
import com.qbe.cotizador.model.AgriObjetoDetalle;

public class AgriObjetoDetalleTransaction {
	public AgriObjetoDetalle crear(AgriObjetoDetalle agriObjetoDetalle)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoDetalleDAO AgriObjetoDAO = new AgriObjetoDetalleDAO();
			agriObjetoDetalle = AgriObjetoDAO.crear(agriObjetoDetalle);
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
		return agriObjetoDetalle;
		}
	public AgriObjetoDetalle editar(AgriObjetoDetalle agriObjetoDetalle)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoDetalleDAO AgriObjetoDetalleDAO = new AgriObjetoDetalleDAO();
			agriObjetoDetalle = AgriObjetoDetalleDAO.editar(agriObjetoDetalle);
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
		return agriObjetoDetalle;
		}
	public void eliminar(AgriObjetoDetalle agriObjetoDetalle)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoDetalleDAO AgriObjetoDetalleDAO = new AgriObjetoDetalleDAO();
			AgriObjetoDetalle AgriObjetoDetalle = new AgriObjetoDetalle();
			AgriObjetoDetalle = AgriObjetoDetalleDAO.buscarPorId(AgriObjetoDetalle.getId());
			if (AgriObjetoDetalle!=null)
			{
				AgriObjetoDetalleDAO.eliminar(AgriObjetoDetalle);
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
package com.qbe.cotizador.transaction.producto.agricola;

import java.math.BigInteger;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreDetalleDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriSucreDetalle;

public class AgriSucreDetalleTransaction {
public AgriSucreDetalleTransaction(){}
	
	public AgriSucreDetalle crear(AgriSucreDetalle agriSucreDetalle)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriSucreDetalleDAO AgriObjetoDAO = new AgriSucreDetalleDAO();
			agriSucreDetalle = AgriObjetoDAO.crear(agriSucreDetalle);
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
		return agriSucreDetalle;
		}
	public AgriSucreDetalle editar(AgriSucreDetalle agriSucreDetalle)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriSucreDetalleDAO AgriObjetoDAO = new AgriSucreDetalleDAO();
			agriSucreDetalle = AgriObjetoDAO.editar(agriSucreDetalle);
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
		return agriSucreDetalle;
		}
	public void eliminar(AgriSucreDetalle agriSucreDetalle)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCicloDAO AgriObjetoDAO = new AgriCicloDAO();
			AgriCiclo ObjetoAgriBuscado = new AgriCiclo();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(new BigInteger(agriSucreDetalle.getId()));
			if (ObjetoAgriBuscado!=null)
			{
				AgriObjetoDAO.eliminar(ObjetoAgriBuscado);
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

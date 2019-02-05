package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.model.AgriCiclo;

public class AgriCicloTransaction {
	
	public AgriCicloTransaction(){}
	
	public AgriCiclo crear(AgriCiclo agriCiclo)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCicloDAO AgriObjetoDAO = new AgriCicloDAO();
			agriCiclo = AgriObjetoDAO.crear(agriCiclo);
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
		return agriCiclo;
		}
	public AgriCiclo editar(AgriCiclo agriCiclo)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCicloDAO AgriObjetoDAO = new AgriCicloDAO();
			agriCiclo = AgriObjetoDAO.editar(agriCiclo);
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
		return agriCiclo;
		}
	public void eliminar(AgriCiclo agriCiclo)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCicloDAO AgriObjetoDAO = new AgriCicloDAO();
			AgriCiclo ObjetoAgriBuscado = new AgriCiclo();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriCiclo.getClicloId());
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

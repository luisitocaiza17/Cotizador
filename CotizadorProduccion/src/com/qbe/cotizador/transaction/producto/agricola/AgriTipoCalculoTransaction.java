package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.model.AgriTipoCalculo;

public class AgriTipoCalculoTransaction {
	
	public AgriTipoCalculoTransaction(){}
	
	public AgriTipoCalculo crear(AgriTipoCalculo agriTipoCalculo)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriTipoCalculoDAO AgriObjetoDAO = new AgriTipoCalculoDAO();
			agriTipoCalculo = AgriObjetoDAO.crear(agriTipoCalculo);
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
		return agriTipoCalculo;
		}
	public AgriTipoCalculo editar(AgriTipoCalculo agriTipoCalculo)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriTipoCalculoDAO AgriObjetoDAO = new AgriTipoCalculoDAO();
			agriTipoCalculo = AgriObjetoDAO.editar(agriTipoCalculo);
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
		return agriTipoCalculo;
		}
	public void eliminar(AgriTipoCalculo agriTipoCalculo)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriTipoCalculoDAO AgriObjetoDAO = new AgriTipoCalculoDAO();
			AgriTipoCalculo ObjetoAgriBuscado = new AgriTipoCalculo();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriTipoCalculo.getTipoCalculoId());
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

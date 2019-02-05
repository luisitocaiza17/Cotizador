package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.model.AgriRegla;

public class AgriReglaTransaction {

	public AgriReglaTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public AgriRegla crear (AgriRegla agriRegla){
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriReglaDAO AgriObjetoDAO = new AgriReglaDAO();
			agriRegla = AgriObjetoDAO.crear(agriRegla);
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
		return agriRegla;
		}
	public AgriRegla editar(AgriRegla agriRegla){
		UserTransaction ut= null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriReglaDAO AgriObjetoDAO = new AgriReglaDAO();
			agriRegla = AgriObjetoDAO.editar(agriRegla);
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
		return agriRegla;
	}
	public void eliminar(AgriRegla agriRegla){
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriReglaDAO AgriObjetoDAO = new AgriReglaDAO();
			AgriRegla ObjetoAgriBuscado = new AgriRegla();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriRegla.getReglaId());
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

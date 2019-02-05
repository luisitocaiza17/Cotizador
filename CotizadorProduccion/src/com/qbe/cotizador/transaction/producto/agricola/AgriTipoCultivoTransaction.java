package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriTipoCultivo;

public class AgriTipoCultivoTransaction {
public AgriTipoCultivoTransaction(){}

public AgriTipoCultivo crear(AgriTipoCultivo agriTipoCultivo)
{
	UserTransaction ut = null; 
	try {
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriTipoCultivoDAO AgriObjetoDAO = new AgriTipoCultivoDAO();
		agriTipoCultivo = AgriObjetoDAO.crear(agriTipoCultivo);
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
	return agriTipoCultivo;
	}
public AgriTipoCultivo editar(AgriTipoCultivo agriTipoCultivo)
{
	UserTransaction ut = null; 
	try {
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriTipoCultivoDAO AgriObjetoDAO = new AgriTipoCultivoDAO();
		agriTipoCultivo = AgriObjetoDAO.editar(agriTipoCultivo);
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
	return agriTipoCultivo;
	}
public void eliminar(AgriTipoCultivo agriTipoCultivo)
{
	UserTransaction ut= null;
	try
	{
		ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriTipoCultivoDAO AgriObjetoDAO = new AgriTipoCultivoDAO();
		AgriTipoCultivo ObjetoAgriBuscado = new AgriTipoCultivo();
		ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriTipoCultivo.getTipoCultivoId());
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

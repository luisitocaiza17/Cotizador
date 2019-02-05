package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;

public class AgriParroquiaTransaction{
	// TODO Auto-generated constructor stub
public AgriParroquia crear (AgriParroquia AgriParroquia){
	UserTransaction ut = null;
	try {
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParroquiaDAO AgriObjetoDAO = new AgriParroquiaDAO();
		AgriParroquia = AgriObjetoDAO.crear(AgriParroquia);
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
	return AgriParroquia;
	}
public AgriParroquia editar(AgriParroquia AgriParroquia){
	UserTransaction ut= null;
	try {
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParroquiaDAO AgriObjetoDAO = new AgriParroquiaDAO();
		AgriParroquia = AgriObjetoDAO.editar(AgriParroquia);
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
	return AgriParroquia;
}
public void eliminar(AgriParroquia AgriParroquia){
	UserTransaction ut= null;
	try
	{
		ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParroquiaDAO AgriObjetoDAO = new AgriParroquiaDAO();
		AgriParroquia ObjetoAgriBuscado = new AgriParroquia();
		ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(AgriParroquia.getId());
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
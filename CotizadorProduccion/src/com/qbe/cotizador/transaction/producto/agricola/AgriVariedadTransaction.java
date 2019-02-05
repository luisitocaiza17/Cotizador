package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriVariedadDAO;
import com.qbe.cotizador.model.AgriVariedad;;

public class AgriVariedadTransaction {

	public AgriVariedadTransaction() {
		// TODO Auto-generated constructor stub
	}
	public AgriVariedad crear(AgriVariedad agriVariedad)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriVariedadDAO AgriObjetoDAO = new AgriVariedadDAO();
			agriVariedad = AgriObjetoDAO.crear(agriVariedad);
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
		return agriVariedad;
		}
	public AgriVariedad editar(AgriVariedad agriVariedad)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriVariedadDAO AgriObjetoDAO = new AgriVariedadDAO();
			agriVariedad = AgriObjetoDAO.editar(agriVariedad);
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
		return agriVariedad;
		}
	public void eliminar(AgriVariedad agriVariedad)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriVariedadDAO AgriObjetoDAO = new AgriVariedadDAO();
			AgriVariedad ObjetoAgriBuscado = new AgriVariedad();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriVariedad.getVariedadId());
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

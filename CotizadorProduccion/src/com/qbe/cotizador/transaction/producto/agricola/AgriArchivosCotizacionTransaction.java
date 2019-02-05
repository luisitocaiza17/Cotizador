package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriArchivosCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.model.AgriArchivosCotizacion;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;

public class AgriArchivosCotizacionTransaction {

	public AgriArchivosCotizacionTransaction() {
		// TODO Auto-generated constructor stub
	}
	public AgriArchivosCotizacion crear (AgriArchivosCotizacion agriArchivosCotizacion)
	{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriArchivosCotizacionDAO AgriObjetoDAO = new AgriArchivosCotizacionDAO();
			agriArchivosCotizacion = AgriObjetoDAO.crear(agriArchivosCotizacion);
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
		return agriArchivosCotizacion;
	}
	public AgriArchivosCotizacion editar (AgriArchivosCotizacion agriArchivosCotizacion)
	{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriArchivosCotizacionDAO AgriObjetoDAO = new AgriArchivosCotizacionDAO();
			agriArchivosCotizacion = AgriObjetoDAO.editar(agriArchivosCotizacion);
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
		return agriArchivosCotizacion;
	}
	public void eliminar(AgriArchivosCotizacion agriArchivosCotizacion)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriArchivosCotizacionDAO AgriObjetoDAO = new AgriArchivosCotizacionDAO();
			AgriArchivosCotizacion ObjetoAgriBuscado = new AgriArchivosCotizacion();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriArchivosCotizacion.getId());
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

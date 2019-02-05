package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.UsuariosOffline;

public class usuarioOfflineTransaction {
	public UsuariosOffline crear(UsuariosOffline usuariosOffline)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
			usuariosOffline = usuariosOfflineDAO.crear(usuariosOffline);
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
		return usuariosOffline;
		}
	public UsuariosOffline editar(UsuariosOffline usuariosOffline)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
			usuariosOffline = usuariosOfflineDAO.editar(usuariosOffline);
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
		return usuariosOffline;
		}
	public void eliminar(UsuariosOffline usuariosOffline)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
			UsuariosOffline usuariosOfflineBuscado = new UsuariosOffline();
			usuariosOfflineBuscado = usuariosOfflineDAO.BuscarPorId(usuariosOffline.getId());
			if (usuariosOfflineBuscado!=null)
			{
				usuariosOfflineDAO.eliminar(usuariosOfflineBuscado);
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

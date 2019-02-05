package com.qbe.cotizador.transaction.producto;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.GrupoProducto;

public class GrupoProductoTransaction {

	public GrupoProducto crear(GrupoProducto grupoProducto) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
			grupoProducto = grupoProductoDAO.crear(grupoProducto);
			ut.commit();
		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return grupoProducto;
	}

	public GrupoProducto editar(GrupoProducto grupoProducto) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
			GrupoProducto grupoProductoBuscada = grupoProductoDAO
					.buscarPorId(grupoProducto.getId());
			if (grupoProductoBuscada != null) {
				grupoProducto = grupoProductoDAO.editar(grupoProducto);
				ut.commit();
			}
		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return grupoProducto;
	}

	public void eliminar(GrupoProducto grupoProducto) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
			GrupoProducto grupoProductoBuscada = new GrupoProducto();
			grupoProductoBuscada = grupoProductoDAO
					.buscarPorId(grupoProducto.getId());
			if (grupoProductoBuscada != null) {
				grupoProductoDAO.eliminar(grupoProductoBuscada);
				ut.commit();
			}
		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}

package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO;
import com.qbe.cotizador.model.OpcionMenu;
import com.qbe.cotizador.model.OpcionMenuRol;

public class OpcionMenuRolTransaction {

	public OpcionMenuRol crear(OpcionMenuRol opcMenuRol) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			OpcionMenuRolDAO opcionMenuRolDAO = new OpcionMenuRolDAO();
			opcMenuRol = opcionMenuRolDAO.crear(opcMenuRol);
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
		return opcMenuRol;
	}

	public OpcionMenuRol editar(OpcionMenuRol opcMenuRol) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			OpcionMenuRolDAO opcionMenuRolDAO = new OpcionMenuRolDAO();
			OpcionMenuRol opcionBuscada = opcionMenuRolDAO.buscarPorId(opcMenuRol
					.getId());
			if (opcionBuscada != null) {
				opcMenuRol = opcionMenuRolDAO.editar(opcMenuRol);
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
		return opcMenuRol;
	}
		
	public void eliminar(OpcionMenuRol opcMenuRol) {
		UserTransaction ut = null;
		try {
			ut = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			ut.begin();
			OpcionMenuRolDAO opcionMenuRolDAO = new OpcionMenuRolDAO();
			OpcionMenu opcionBuscado = new OpcionMenu();
			OpcionMenuRol opcionBuscada = opcionMenuRolDAO.buscarPorId(opcMenuRol
					.getId());
			if (opcionBuscado != null) {
				opcionMenuRolDAO.eliminar(opcionBuscada);
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

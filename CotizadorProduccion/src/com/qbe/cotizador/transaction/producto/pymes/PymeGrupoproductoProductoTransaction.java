package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeExtRamoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeGrupoproductoProductoDAO;
import com.qbe.cotizador.model.PymeExtRamo;
import com.qbe.cotizador.model.PymeGrupoproductoProducto;

public class PymeGrupoproductoProductoTransaction {

	public PymeGrupoproductoProductoTransaction() {		
	}
	
	
	public PymeGrupoproductoProducto crear(PymeGrupoproductoProducto PymeGrupoproductoProducto){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeGrupoproductoProductoDAO PymeGrupoproductoProductoDAO = new PymeGrupoproductoProductoDAO();
			PymeGrupoproductoProducto = PymeGrupoproductoProductoDAO.crear(PymeGrupoproductoProducto);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return PymeGrupoproductoProducto;	
	}

	public PymeGrupoproductoProducto editar(PymeGrupoproductoProducto PymeGrupoproductoProducto){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeGrupoproductoProductoDAO PymeGrupoproductoProductoDAO = new PymeGrupoproductoProductoDAO();
			PymeGrupoproductoProducto = PymeGrupoproductoProductoDAO.editar(PymeGrupoproductoProducto);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return PymeGrupoproductoProducto;
	}

	public void eliminar(PymeGrupoproductoProducto PymeGrupoproductoProducto){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeGrupoproductoProductoDAO PymeGrupoproductoProductoDAO = new PymeGrupoproductoProductoDAO();
			PymeGrupoproductoProducto ObjetoPymesBuscado = new PymeGrupoproductoProducto();
			ObjetoPymesBuscado = PymeGrupoproductoProductoDAO.buscarPorId(PymeGrupoproductoProducto.getId());
			if(ObjetoPymesBuscado !=null){
				PymeGrupoproductoProductoDAO.eliminar(ObjetoPymesBuscado);
				ut.commit();
			}
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}
	}
	

}

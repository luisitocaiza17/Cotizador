package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeExtRamoDAO;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeExtRamo;

public class PymeExtRamoTransaction {

	public PymeExtRamoTransaction() {		
	}
	
	
	public PymeExtRamo crear(PymeExtRamo pymeExtRamo) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeExtRamoDAO pymeObjetoDAO = new PymeExtRamoDAO();
			pymeExtRamo = pymeObjetoDAO.crear(pymeExtRamo);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}				    
		}					
		return pymeExtRamo;	
	}

	public PymeExtRamo editar(PymeExtRamo pymeExtRamo) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeExtRamoDAO pymeObjetoDAO = new PymeExtRamoDAO();
			pymeExtRamo = pymeObjetoDAO.editar(pymeExtRamo);
			ut.commit();
			throw new Exception("error en base");
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}
		}					
		//return pymeExtRamo;
	}

	public void eliminar(PymeExtRamo pymeExtRamo) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeExtRamoDAO pymeObjetoDAO = new PymeExtRamoDAO();
			PymeExtRamo ObjetoPymesBuscado = new PymeExtRamo();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeExtRamo.getRamoId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(ObjetoPymesBuscado);
				ut.commit();
			}
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}			    
		}
	}
	

}

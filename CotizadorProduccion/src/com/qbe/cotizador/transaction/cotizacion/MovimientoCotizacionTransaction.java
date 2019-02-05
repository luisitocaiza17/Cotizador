package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.MovimientoCotizacionDAO;
import com.qbe.cotizador.model.MovimientoCotizacion;;

public class MovimientoCotizacionTransaction {
    
    public MovimientoCotizacionTransaction() {       
    }

    public MovimientoCotizacion crear(MovimientoCotizacion MovimientoCotizacion){        
    UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        MovimientoCotizacionDAO MovimientoCotizacionDAO = new MovimientoCotizacionDAO();
        MovimientoCotizacion = MovimientoCotizacionDAO.crear(MovimientoCotizacion);
        ut.commit();
    }catch(Exception e) {
        try {
            ut.rollback();
        } catch (IllegalStateException | SecurityException | SystemException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();                
    }                   
    return MovimientoCotizacion;   
    }
    
    public MovimientoCotizacion editar(MovimientoCotizacion MovimientoCotizacion){
        UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        MovimientoCotizacionDAO MovimientoCotizacionDAO = new MovimientoCotizacionDAO();
        MovimientoCotizacion MovimientoCotizacionBuscada = MovimientoCotizacionDAO.buscarPorId(MovimientoCotizacion.getId());
        if(MovimientoCotizacionBuscada!=null){
            MovimientoCotizacion = MovimientoCotizacionDAO.editar(MovimientoCotizacion);
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
    return MovimientoCotizacion;
    }
    
    public void eliminar(MovimientoCotizacion MovimientoCotizacion){  
    UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        MovimientoCotizacionDAO MovimientoCotizacionDAO = new MovimientoCotizacionDAO();
        MovimientoCotizacion MovimientoCotizacionBuscado = new MovimientoCotizacion();
        MovimientoCotizacionBuscado = MovimientoCotizacionDAO.buscarPorId(MovimientoCotizacion.getId());
        if(MovimientoCotizacionBuscado !=null){
            MovimientoCotizacionDAO.eliminar(MovimientoCotizacion);
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

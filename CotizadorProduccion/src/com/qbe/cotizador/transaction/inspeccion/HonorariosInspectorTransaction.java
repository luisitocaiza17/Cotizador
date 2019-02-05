package com.qbe.cotizador.transaction.inspeccion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.inspeccion.HonorariosInspectorDAO;
import com.qbe.cotizador.model.HonorariosInspector;

public class HonorariosInspectorTransaction {
    
    public HonorariosInspectorTransaction() {       
    }

    public HonorariosInspector crear(HonorariosInspector honorariosInspector){        
    UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        HonorariosInspectorDAO HonorariosInspectorDAO = new HonorariosInspectorDAO();
        honorariosInspector = HonorariosInspectorDAO.crear(honorariosInspector);
        ut.commit();
    }catch(Exception e) {
        try {
            ut.rollback();
        } catch (IllegalStateException | SecurityException | SystemException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();                
    }                   
    return honorariosInspector;   
    }
    
    public HonorariosInspector editar(HonorariosInspector honorariosInspector){
        UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        HonorariosInspectorDAO HonorariosInspectorDAO = new HonorariosInspectorDAO();
        HonorariosInspector HonorariosInspectorBuscada = HonorariosInspectorDAO.buscarPorId(honorariosInspector.getId());
        if(HonorariosInspectorBuscada!=null){
          honorariosInspector = HonorariosInspectorDAO.editar(honorariosInspector);
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
    return honorariosInspector;
    }
    
    public void eliminar(HonorariosInspector HonorariosInspector){  
    UserTransaction ut = null;
    try{
        ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();
        HonorariosInspectorDAO HonorariosInspectorDAO = new HonorariosInspectorDAO();
        HonorariosInspector HonorariosInspectorBuscado = new HonorariosInspector();
        HonorariosInspectorBuscado = HonorariosInspectorDAO.buscarPorId(HonorariosInspector.getId());
        if(HonorariosInspectorBuscado !=null){
            HonorariosInspectorDAO.eliminar(HonorariosInspector);
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

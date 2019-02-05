package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.model.Beneficiario;

public class BeneficiarioTransaction {
	
	public BeneficiarioTransaction() {       
    }

	public Beneficiario crear(Beneficiario Beneficiario){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		BeneficiarioDAO BeneficiarioDAO = new BeneficiarioDAO();
		Beneficiario = BeneficiarioDAO.crear(Beneficiario);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Beneficiario;	
	}
	
	public Beneficiario editar(Beneficiario Beneficiario){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		BeneficiarioDAO BeneficiarioDAO = new BeneficiarioDAO();
		Beneficiario BeneficiarioBuscada = BeneficiarioDAO.buscarPorId(Beneficiario.getId());
		if(BeneficiarioBuscada!=null){
			Beneficiario = BeneficiarioDAO.editar(Beneficiario);
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
	return Beneficiario;
	}
	
	public void eliminar(Beneficiario Beneficiario){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		BeneficiarioDAO BeneficiarioDAO = new BeneficiarioDAO();
		Beneficiario BeneficiarioBuscado = new Beneficiario();
		BeneficiarioBuscado = BeneficiarioDAO.buscarPorId(Beneficiario.getId());
		if(BeneficiarioBuscado !=null){
			BeneficiarioDAO.eliminar(Beneficiario);
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

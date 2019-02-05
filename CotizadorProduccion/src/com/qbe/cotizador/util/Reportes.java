package com.qbe.cotizador.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;


public class Reportes {
	
	public Reportes() {        
    }
	
	private static Connection conn = null;	
	
	static{
		try {
			DataSource dataSource = (DataSource) new InitialContext().lookup("jdbc/MySQLResource");
			conn = dataSource.getConnection();
		} catch (SQLException | NamingException e) {			
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection(){
		if(Reportes.conn==null)
			Reportes.inicializarConexion();
		return conn;
	}
	
	//evaldez metodo que devuelve la conexión a la base 
	private static void inicializarConexion() {
		
		try {
			DataSource dataSource = (DataSource) new InitialContext().lookup("jdbc/MySQLResource");
			conn = dataSource.getConnection();
		} catch (SQLException | NamingException e) {			
			e.printStackTrace();
		}		
	} 
	
	
	public static void cerrarConexion(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn=null;
	} 
	
}

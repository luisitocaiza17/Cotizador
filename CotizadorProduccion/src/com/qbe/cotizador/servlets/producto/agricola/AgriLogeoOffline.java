package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.producto.agricola.User;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.model.UsuariosOffline;

/**
 * Servlet implementation class AgriLogeoOffline
 */
@WebServlet("/AgriLogeoOffline")
public class AgriLogeoOffline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriLogeoOffline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario") == null ? "" : request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia") == null ? "" : request.getParameter("contrasenia");
		
		//verificamos si existe
		List<UsuariosOffline> usuariosOffline=verificador();
		String result=generaData(usuariosOffline);
		
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(result.getBytes("UTF-8").length);
		response.setHeader("Content-Encoding", "none");
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=" + "security.config");//fileName);

		
		ServletOutputStream  o = response.getOutputStream();
		o.write(result.getBytes("UTF-8")); 
		o.flush(); 
		o.close(); 
		return;
		
	}
	//Solo para buscar un usuario existente
	public UsuariosOffline verificador(String usuario, String clave ){
		//buscamos si el usuario existe
		UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
		UsuariosOffline usuariosOffline= usuariosOfflineDAO.BuscarUsuario(usuario, clave);
		return usuariosOffline;
	}
	//Traemos todos los usuarios que hay dentro del cotizador
	public List<UsuariosOffline> verificador(){
		//buscamos si el usuario existe
		UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
		List<UsuariosOffline> usuariosOffline= usuariosOfflineDAO.BuscarTodos();
		return usuariosOffline;
	}
	
	//TRAEMOS TODOS LOS USUARIOS Y LOS ALMACENAMOS EN EL GSON
	public String generaData(List<UsuariosOffline> usuariosOffline ){
		TransportDataUser dataUser = new TransportDataUser();
		List<User> usuarios = new ArrayList<User>();
		
		for(UsuariosOffline userActual:usuariosOffline){
			User user = new User();
			user.setUserID(Integer.parseInt(""+userActual.getId())); 
			user.setAdmin(false);
			user.setAgencia(userActual.getAgencia());
			user.setCIUser(userActual.getIdentificacion());
			user.setLastName(userActual.getApellidos());
			user.setName(userActual.getNombres());
			user.setPassword(userActual.getClave());
			user.setPuntoVentaId(userActual.getPuntoVenta());
			user.setUserName(userActual.getUsuario());
			usuarios.add(user);
		}
		//Agregamos al objeto que se desea transportar
		dataUser.setUsers(usuarios);
		try{
			Gson g=new Gson();
			String data=g.toJson(dataUser);
			String dataPreparada=com.qbe.cotizador.util.AES_Helper.padString(data);
			String dataSerializada= com.qbe.cotizador.util.AES_Helper.encrypt(dataPreparada);
			return dataSerializada;
		}
		catch(Exception ex){
			return "";	
		}		
	}
	
	//llevamos solo un usuario
	public String generaData(UsuariosOffline usuariosOffline ){
		TransportDataUser dataUser = new TransportDataUser();
		List<User> usuarios = new ArrayList<User>();
		User user = new User();
		if(usuariosOffline.getIdentificacion()!=null){
			user.setUserID(Integer.parseInt(""+usuariosOffline.getId())); 
			user.setAdmin(false);
			user.setAgencia(usuariosOffline.getAgencia());
			user.setCIUser(usuariosOffline.getIdentificacion());
			user.setLastName(usuariosOffline.getApellidos());
			user.setName(usuariosOffline.getNombres());
			user.setPassword(usuariosOffline.getClave());
			user.setPuntoVentaId(usuariosOffline.getPuntoVenta());
			user.setUserName(usuariosOffline.getUsuario());
			}
		else{
			user.setUserID(Integer.parseInt("0")); 
			user.setAdmin(false);
		}
		//Agregamos a una lista de usurios el usuario creado
		usuarios.add(user);
		//Agregamos al objeto que se desea transportar
		dataUser.setUsers(usuarios);
		try{
			Gson g=new Gson();
			String data=g.toJson(dataUser);
			String dataPreparada=com.qbe.cotizador.util.AES_Helper.padString(data);
			String dataSerializada= com.qbe.cotizador.util.AES_Helper.encrypt(dataPreparada);
			return dataSerializada;
		}
		catch(Exception ex){
			return "";	
		}		
	}
}

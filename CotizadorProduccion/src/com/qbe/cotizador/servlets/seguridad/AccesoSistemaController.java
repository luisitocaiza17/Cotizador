package com.qbe.cotizador.servlets.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.seguridad.CredencialDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Credencial;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.util.Utilitarios;


/**
 * Servlet implementation class AccesoSistemaController
 */
@WebServlet("/AccesoSistemaController")
public class AccesoSistemaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccesoSistemaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response) ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		String name = request.getParameter("username") == null ? "" : request.getParameter("username");
		String password = request.getParameter("password") == null ? "" : request.getParameter("password");
		String redir = request.getParameter("redir") == null ? "" : request.getParameter("redir");			
				
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorLogin(name);
		
		if(usuario.getId() == null){ // No existe el usuario
			response.sendRedirect("/CotizadorWeb?error=usuario");
		}else{// Existe el usuario
			// Validamos que la cuenta haya sido activada por medio del mail
			//String validarCuenta = usuario.getValidacionMail();
			//if(validarCuenta.isEmpty()){ // Si es vacio se activo el mail							
			// Verificamos que la clave sea correcta
			CredencialDAO credencialDAO = new CredencialDAO();
			String encriptarClave = Utilitarios.encriptacionClave(password);
			Credencial credencial = credencialDAO.buscarPorUsuarioId(usuario);
			
				if(credencial.getClave().equalsIgnoreCase(encriptarClave)){
					// Activacion de la sesion y agregamos 
					HttpSession session = request.getSession();
					
					session.setAttribute("usuario", usuario);
					session.setAttribute("login", usuario.getLogin());
					session.setAttribute("emite", usuario.getEmite());
					
					if(redir==null||redir.equals(""))
					response.sendRedirect("dashboard/Dashboard.jsp");
					else
						response.sendRedirect(redir);
				}	
				else{
					response.sendRedirect("/CotizadorWeb?error=contrasena");
				}
//			}else { // No se valido la cuenta
//				response.sendRedirect("/CotizadorWeb");
//			}				
		}
	}
}

package com.qbe.cotizador.servlets.seguridad;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;

/**
 * Servlet implementation class ConfirmarMailController
 */
@WebServlet("/ConfirmarMailController")
public class ConfirmarMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarMailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String valorConfirmar = request.getParameter("confirmar");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean verificarConfirmacion = usuarioDAO.buscarPorConfirmacionMail(valorConfirmar);
		String mensajeConfirmacion = "";
		if(verificarConfirmacion)
			mensajeConfirmacion = "Cuenta Activada."; 
		else
			mensajeConfirmacion = "Confirmación Incorrecta o la Cuenta ya se encuentra Activada";
			
		request.setAttribute("mensaje_validacion_cuenta", mensajeConfirmacion);
		 RequestDispatcher view=request.getRequestDispatcher("index.jsp");
		    view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

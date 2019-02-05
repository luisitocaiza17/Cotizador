package com.qbe.cotizador.servlets.pagos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.pagos.FormaPagoDAO;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.transaction.pagos.FormaPagoTransaction;

/**
 * Servlet implementation class FormaPagoController
 */
@WebServlet("/FormaPagoController")
public class FormaPagoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormaPagoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject formaPagoJSONObject = new JSONObject();
			JSONArray formaPagoJSONArray = new JSONArray();

			FormaPago formaPago = new FormaPago();
			FormaPagoDAO formaPagoDAO = new FormaPagoDAO();
			
			FormaPagoTransaction formaPagoTransaction = new FormaPagoTransaction();
/*
			if (!codigo.equals(""))
				formaPago.setId(codigo);

			if (!codigoEnsurance.equals(""))
				formaPago.setSucEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				formaPago.setNombre(nombre);

			if (matriz.equals("1"))
				formaPago.setEsMatriz(true);
			else if(!tipoConsulta.equals("eliminar"))
				formaPago.setEsMatriz(false);

			if (activa.equals("1"))
				formaPago.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				formaPago.setActivo(false);
*/
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<FormaPago> results = formaPagoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					formaPago = results.get(i);
					
					formaPagoJSONObject.put("codigo", formaPago.getId());
					formaPagoJSONObject.put("codigoEnsurance", formaPago.getCodigoEnsurance());
					formaPagoJSONObject.put("nombre", formaPago.getNombre());


					formaPagoJSONArray.add(formaPagoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoFormaPago", formaPagoJSONArray);
			}
			
//			// Encontramos los formaPagos activos ayanez
//			if(tipoConsulta.equals("encontrarFormaPagosDebito")){
//				List<FormaPago> listado = formaPagoDAO.buscarFormaPagosDebito();
//				if(listado.size() > 0) {
//					JSONObject formaPagosJSON = new JSONObject();
//					for(int i=0; i<listado.size(); i++) {
//						formaPago = (FormaPago) listado.get(i);					
//						formaPagosJSON.put("codigo", formaPago.getId());
//						formaPagosJSON.put("nombre", formaPago.getNombre());
//						formaPagoJSONArray.add(formaPagosJSON);
//					}
//				}
//				result.put("listadoFormaPago", formaPagoJSONArray);
//			}
					
			if(tipoConsulta.equals("crear"))
				formaPagoTransaction.crear(formaPago);

			if(tipoConsulta.equals("actualizar"))
				formaPagoTransaction.editar(formaPago);

			if(tipoConsulta.equals("eliminar"))
				formaPagoTransaction.eliminar(formaPago);


			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();

		}				
	}

}

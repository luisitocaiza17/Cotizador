package com.qbe.cotizador.servlets.producto.vehiculo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.model.TipoUso;
import com.qbe.cotizador.transaction.producto.vehiculo.TipoUsoTransaction;

/**
 * Servlet implementation class TipoUsoController
 */
@WebServlet("/TipoUsoController")
public class TipoUsoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoUsoController() {
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
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String tipoObjeto = request.getParameter("tipoObjeto") == null ? "" : request.getParameter("tipoObjeto");
			JSONObject tipoUsoJSONObject = new JSONObject();
			JSONArray tipoUsoJSONArray = new JSONArray();
			
			TipoUsoTransaction tipoUsoTransaction = new TipoUsoTransaction();

			TipoUso tipoUso = new TipoUso();
			TipoUsoDAO tipoUsoDAO = new TipoUsoDAO();

			if (!codigo.equals(""))
				tipoUso.setId(codigo);

			if (!codigoEnsurance.equals(""))
				tipoUso.setTipoEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				tipoUso.setNombre(nombre);

			if (activa.equals("1"))
				tipoUso.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				tipoUso.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")&&(tipoObjeto.equals("Pesados")|| tipoObjeto.equals("Publicos"))){ 
				List<TipoUso> results = tipoUsoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoUso = results.get(i);
					tipoUsoJSONObject.put("codigo", tipoUso.getId());
					tipoUsoJSONObject.put("codigoEnsurance", tipoUso.getTipoEnsurance());
					tipoUsoJSONObject.put("nombre", tipoUso.getNombre());

					if(tipoUso.getActivo())
						tipoUsoJSONObject.put("activo", "Si");
					else
						tipoUsoJSONObject.put("activo", "No");

					tipoUsoJSONArray.add(tipoUsoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoUso", tipoUsoJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarTodos")&&(tipoObjeto.equals("VHDinamico")|| tipoObjeto.equals("Livianos")|| tipoObjeto.equals("Motos"))){ 
				List<TipoUso> results = tipoUsoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoUso = results.get(i);
					if(tipoUso.getId().equals("1")||tipoUso.getId().equals("5")){
					tipoUsoJSONObject.put("codigo", tipoUso.getId());
					tipoUsoJSONObject.put("codigoEnsurance", tipoUso.getTipoEnsurance());
					tipoUsoJSONObject.put("nombre", tipoUso.getNombre());

					if(tipoUso.getActivo())
						tipoUsoJSONObject.put("activo", "Si");
					else
						tipoUsoJSONObject.put("activo", "No");
				

					tipoUsoJSONArray.add(tipoUsoJSONObject);
					}
				}
				result.put("numRegistros",i);
				result.put("listadoTipoUso", tipoUsoJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				tipoUsoTransaction.crear(tipoUso);

			if(tipoConsulta.equals("actualizar"))
				tipoUsoTransaction.editar(tipoUso);

			if(tipoConsulta.equals("eliminar"))
				tipoUsoTransaction.eliminar(tipoUso);


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

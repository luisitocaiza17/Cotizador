package com.qbe.cotizador.servlets.inspeccion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.inspeccion.TipoAdicionalDAO;
import com.qbe.cotizador.model.TipoAdicional;
import com.qbe.cotizador.model.Ramo;

/**
 * Servlet implementation class DistanciaInspectorController
 */
@WebServlet("/TipoAdicionalController")
public class TipoAdicionalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoAdicionalController() {
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
		JSONObject result = new JSONObject();
		
		try{
	
			//String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			/*
			if(tipoConsulta.equals("crear"))
				distanciaInspectorTransaction.crear(distanciaInspector);

			if(tipoConsulta.equals("actualizar"))
				distanciaInspectorTransaction.editar(distanciaInspector);

			if(tipoConsulta.equals("eliminar"))
				distanciaInspectorTransaction.eliminar(distanciaInspector);
			 */

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
	
	public static JSONArray getActivosVehiculos(){
		TipoAdicionalDAO adicionalDAO= new TipoAdicionalDAO();
		RamoDAO rDAO= new RamoDAO();
		Ramo ramo=rDAO.buscarPorNemotecnico("VH");
		List<TipoAdicional> Adicionals=adicionalDAO.buscarActivosPorRamo(ramo);
		JSONArray extrasJSON=new JSONArray();
		for(TipoAdicional AdicionalObjeto:Adicionals){
			extrasJSON.add(AdicionalObjeto.getJSON());
		}
		return extrasJSON;
	}

}

package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoCoberturaDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaDAO;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCobertura;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.TipoCobertura;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeCoberturaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeCoberturaController
 */
@WebServlet("/PymeCoberturaController")
public class PymeCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymeCoberturaController() {
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
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String coberturaPymesId = request.getParameter("coberturaPymesId") == null ? "" : request.getParameter("coberturaPymesId");
			String grupoCoberturaId = request.getParameter("grupoCoberturaId") == null ? "" : request.getParameter("grupoCoberturaId");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String ramoId = request.getParameter("ramoId") == null ? "" : request.getParameter("ramoId");
			String tipoCoberturaId = request.getParameter("tipoCoberturaId") == null ? "" : request.getParameter("tipoCoberturaId");
			//String cargarGruposCobertura = request.getParameter("cargarGruposCobertura") == null ? "" : request.getParameter("cargarGruposCobertura");

			PymeCobertura PymeCobertura = new PymeCobertura();
			PymeCoberturaDAO PymeCoberturaDAO = new PymeCoberturaDAO();
			PymeCoberturaTransaction pymeCoberturaTransaction=new PymeCoberturaTransaction(); 

			JSONObject PymeCoberturaJSONObject = new JSONObject();
			JSONArray PymeCoberturaJSONArray = new JSONArray();

			JSONObject grupoCoberturaJSONObject = new JSONObject();
			JSONArray grupoCoberturaJSONArray = new JSONArray();

			JSONObject tipoCoberturaJSONObject = new JSONObject();
			JSONArray tipoCoberturaJSONArray = new JSONArray();

			JSONObject ramoJSONObject = new JSONObject();
			JSONArray ramoJSONArray = new JSONArray();

			if(!coberturaPymesId.equals(""))
				PymeCobertura.setCoberturaPymesId(new BigInteger(coberturaPymesId));		
			if(!grupoCoberturaId.equals(""))
				PymeCobertura.setGrupoCoberturaId(new BigInteger(grupoCoberturaId));		
			if(!nombre.equals(""))
				PymeCobertura.setNombre(nombre);
			if(!ramoId.equals(""))
				PymeCobertura.setRamoId(new BigInteger(ramoId));
			if(!tipoCoberturaId.equals(""))
				PymeCobertura.setTipoCoberturaId(new BigInteger(tipoCoberturaId));	

			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeCobertura> results = PymeCoberturaDAO.buscarTodos();
				for(PymeCobertura cobertura : results){
					PymeCoberturaJSONObject.put("coberturaPymesId", cobertura.getCoberturaPymesId());				

					GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();				
					GrupoCobertura grupoCobertura = grupoCoberturaDAO.buscarPorId(cobertura.getGrupoCoberturaId().toString());

					PymeCoberturaJSONObject.put("grupoCoberturaId", grupoCobertura.getNombre());
					PymeCoberturaJSONObject.put("nombre", cobertura.getNombre());

					RamoDAO ramoDAO = new RamoDAO();
					Ramo ramo = ramoDAO.buscarPorId(cobertura.getRamoId().toString());				
					PymeCoberturaJSONObject.put("ramoId",ramo.getNombre());				

					TipoCoberturaDAO tipoCoberturaDAO = new TipoCoberturaDAO();
					TipoCobertura tipoCobertura = tipoCoberturaDAO.buscarPorId(cobertura.getTipoCoberturaId().toString());

					PymeCoberturaJSONObject.put("tipoCoberturaId", tipoCobertura.getNombre());	
					PymeCoberturaJSONArray.add(PymeCoberturaJSONObject);
				}
				result.put("listadoCoberturaPymes", PymeCoberturaJSONArray);


				RamoDAO ramoDAO = new RamoDAO();
				List<Ramo> resultR = ramoDAO.buscarTodos();
				for(Ramo ramo: resultR){
					ramoJSONObject.put("ramoId", ramo.getId());
					ramoJSONObject.put("ramoNombre", ramo.getNombre());
					ramoJSONArray.add(ramoJSONObject);
				}
				result.put("ramoArr", ramoJSONArray);
				/*GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
			List<GrupoCobertura> resultGC = grupoCoberturaDAO.buscarTodos();
			for(GrupoCobertura gCobertura : resultGC){
				grupoCoberturaJSONObject.put("gCoberturaId", gCobertura.getId());
				grupoCoberturaJSONObject.put("gCoberturaNombre", gCobertura.getNombre());
				grupoCoberturaJSONArray.add(grupoCoberturaJSONObject);
			}
			result.put("grupoCoberturaArr", grupoCoberturaJSONArray);*/


				TipoCoberturaDAO tipoCoberturaDAO = new TipoCoberturaDAO();
				List<TipoCobertura> resultTC = tipoCoberturaDAO.buscarTodos();
				for(TipoCobertura tCobertura : resultTC){
					tipoCoberturaJSONObject.put("tCoberturaId", tCobertura.getId());
					tipoCoberturaJSONObject.put("tCoberturaNombre", tCobertura.getNombre());
					tipoCoberturaJSONArray.add(tipoCoberturaJSONObject);
				};
				result.put("tipoCoberturaArr", tipoCoberturaJSONArray);			
			}

			if(tipoConsulta.equals("obtenerPorId")){
				PymeCobertura cobertura = PymeCoberturaDAO.buscarPorId(new BigInteger(coberturaPymesId));			
				result.put("coberturaPymesId", cobertura.getCoberturaPymesId());

				RamoDAO ramoDAO = new RamoDAO();
				Ramo ramo = ramoDAO.buscarPorId(cobertura.getRamoId().toString());
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				List<GrupoCobertura> resultGC = grupoCoberturaDAO.buscarPorRamoId(ramo);
				for(GrupoCobertura gCobertura : resultGC){
					grupoCoberturaJSONObject.put("gCoberturaId", gCobertura.getId());
					grupoCoberturaJSONObject.put("gCoberturaNombre", gCobertura.getNombre());
					grupoCoberturaJSONArray.add(grupoCoberturaJSONObject);
				}
				result.put("grupoCoberturaArr", grupoCoberturaJSONArray);

				result.put("grupoCoberturaId", cobertura.getGrupoCoberturaId());
				result.put("nombre", cobertura.getNombre());
				result.put("ramoId", cobertura.getRamoId());
				result.put("tipoCoberturaId", cobertura.getTipoCoberturaId());				
			}

			if(tipoConsulta.equals("cargarGruposCobertura")){
				RamoDAO ramoDAO = new RamoDAO();
				Ramo ramo = ramoDAO.buscarPorId(ramoId);
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				List<GrupoCobertura> resultGC = grupoCoberturaDAO.buscarPorRamoId(ramo);
				for(GrupoCobertura gCobertura : resultGC){
					grupoCoberturaJSONObject.put("gCoberturaId", gCobertura.getId());
					grupoCoberturaJSONObject.put("gCoberturaNombre", gCobertura.getNombre());
					grupoCoberturaJSONArray.add(grupoCoberturaJSONObject);
				}
				result.put("grupoCoberturaArr", grupoCoberturaJSONArray);
			}		


			if(tipoConsulta.equals("crear")){
				pymeCoberturaTransaction.crear(PymeCobertura);				
			}

			if(tipoConsulta.equals("actualizar")){
				pymeCoberturaTransaction.editar(PymeCobertura);
			}

			if(tipoConsulta.equals("eliminar")){
				pymeCoberturaTransaction.eliminar(PymeCobertura);
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			/***TRATAMIENTO DE ERROR***/
			Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
			String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
			/***AUDITORIA auditamos el error para el seguimiento***/
			PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
			PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral.setTramite(CodError);
			pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
			pymeAuditoriaGeneral.setProceso("PYMES");
			pymeAuditoriaGeneral.setObjeto(e.getMessage()+"||"+e.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}
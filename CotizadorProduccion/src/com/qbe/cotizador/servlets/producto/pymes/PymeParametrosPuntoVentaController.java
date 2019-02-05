package com.qbe.cotizador.servlets.producto.pymes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.dao.producto.pymes.PymePPVDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroPuntoVentaDAO;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.PYMEPPV;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;
import com.qbe.cotizador.model.UsuariosOffline;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.servlets.producto.agricola.DataSourceResult;
import com.qbe.cotizador.transaction.producto.agricola.usuarioOfflineTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeParametroPuntoVentaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeParametrosPuntoVentaController
 */
@WebServlet("/PymeParametrosPuntoVentaController")
public class PymeParametrosPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeParametrosPuntoVentaController() {
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
		JSONObject result = new JSONObject();
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
		
		try{
			if(tipoConsulta.equals("crear")){
				String plantillaEnvio = request.getParameter("plantillaEnvio") == null ? "":request.getParameter("plantillaEnvio");
				String puntoVentaEnvio = request.getParameter("puntoVentaEnvio") == null ? "":request.getParameter("puntoVentaEnvio");
				String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
				
				Entidad entidad = new Entidad();
				EntidadDAO entidadDAO = new EntidadDAO();
				entidad= entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				
				//buscamos si existe ya el punto de venta no le dejamos guardar de nuevo
				PymeParametroPuntoVentaDAO pymeParametroPuntoVentaDAO = new PymeParametroPuntoVentaDAO();
				PymeParametroPuntoVenta comprobar=pymeParametroPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(puntoVentaEnvio));
				if(comprobar.getParametroPuntoventaId()!=null)
					throw new Exception("El punto de venta ya esta configurado.");
				
					PymeParametroPuntoVenta pymeParametroPuntoVenta = new PymeParametroPuntoVenta();
					pymeParametroPuntoVenta.setEntidadId(new BigInteger(entidad.getId()));
					pymeParametroPuntoVenta.setPuntoVentaId(new BigInteger(puntoVentaEnvio));
					pymeParametroPuntoVenta.setPlantillaNombre(plantillaEnvio);
					PymeParametroPuntoVentaTransaction parametroPuntoVentaTransaction = new PymeParametroPuntoVentaTransaction();
				
					//crear el campo				
					parametroPuntoVentaTransaction.crear(pymeParametroPuntoVenta);	
		}		
		if(tipoConsulta.equals("editar")){
			String plantillaEnvio = request.getParameter("plantillaEnvio") == null ? "":request.getParameter("plantillaEnvio");
			String puntoVentaEnvio = request.getParameter("puntoVentaEnvio") == null ? "":request.getParameter("puntoVentaEnvio");
			String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
			String IdPPV = request.getParameter("IdPPV") == null ? "":request.getParameter("IdPPV");
			
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();
			entidad= entidadDAO.buscarEntidadPorIdentificacion(identificacion);
			//crear el campo
				PymeParametroPuntoVenta pymeParametroPuntoVenta = new PymeParametroPuntoVenta();
				pymeParametroPuntoVenta.setParametroPuntoventaId(new BigInteger(IdPPV));
				pymeParametroPuntoVenta.setEntidadId(new BigInteger(entidad.getId()));
				pymeParametroPuntoVenta.setPuntoVentaId(new BigInteger(puntoVentaEnvio));
				pymeParametroPuntoVenta.setPlantillaNombre(plantillaEnvio);
				PymeParametroPuntoVentaTransaction parametroPuntoVentaTransaction = new PymeParametroPuntoVentaTransaction();
				
				//buscamos si existe ya el punto de venta no le dejamos guardar de nuevo
				PymeParametroPuntoVentaDAO pymeParametroPuntoVentaDAO = new PymeParametroPuntoVentaDAO();
				PymeParametroPuntoVenta comprobar=pymeParametroPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(puntoVentaEnvio));
				parametroPuntoVentaTransaction.editar(pymeParametroPuntoVenta);			
			
		}
		
		if(tipoConsulta.equals("obtenerPorId")){
			String idPPV = request.getParameter("idPPV") == null ? "":request.getParameter("idPPV");
			PymePPVDAO pymeParametroPuntoVentaDAO = new PymePPVDAO();
			PYMEPPV pymePPV= new PYMEPPV();
			pymePPV=pymeParametroPuntoVentaDAO.buscarPorParametroId(new BigInteger(idPPV));
			
			//Buscamos la entidad
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();
			entidad=entidadDAO.buscarPorId(pymePPV.getEntidadId());
			
			result.put("idPPV", pymePPV.getParametroPuntoVentaId());
			result.put("identificacion", entidad.getIdentificacion());
			result.put("nombres", entidad.getNombreCompleto());
			result.put("Plantilla", pymePPV.getPlantillaNombre());
			result.put("PuntoVenta", pymePPV.getPuntoVentaId());
		}
		
		if(tipoConsulta.equals("eliminar")){
			String idPPV = request.getParameter("idPPV") == null ? "":request.getParameter("idPPV");
			PymeParametroPuntoVenta pymeParametroPuntoVenta = new PymeParametroPuntoVenta();
			pymeParametroPuntoVenta.setParametroPuntoventaId(new BigInteger(idPPV));
			PymeParametroPuntoVentaTransaction parametroPuntoVentaTransaction = new PymeParametroPuntoVentaTransaction();
			//crear el campo
			parametroPuntoVentaTransaction.eliminar(pymeParametroPuntoVenta);				
		}
		
		if(tipoConsulta.equals("buscarTodos")){		
			
			/*cargamos la tabla*/
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

			List<PYMEPPV> data = new ArrayList<PYMEPPV>();
			PymePPVDAO pymeParametroPuntoVentaDAO = new PymePPVDAO();
			data=pymeParametroPuntoVentaDAO.cargarTodosKendo(skip, take);
							
			long total=pymeParametroPuntoVentaDAO.cargarTodosKendoPorNumero();
					
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(data);
			
			Gson gson = new Gson(); 
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;		
			
		}
		
		if(tipoConsulta.equals("cargarUsuario")){
			String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();
			entidad= entidadDAO.buscarEntidadPorIdentificacion(identificacion);
			result.put("nombreUsuario", entidad.getNombreCompleto());
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}
		
		if(tipoConsulta.equals("buscarTodos")){		
			String idPPV = request.getParameter("idPPV") == null ? "":request.getParameter("idPPV");
			PymeParametroPuntoVentaTransaction parametroPuntoVentaTransaction = new PymeParametroPuntoVentaTransaction();
			PymeParametroPuntoVenta pymeParametroPuntoVenta = new PymeParametroPuntoVenta();
			pymeParametroPuntoVenta.setParametroPuntoventaId(new BigInteger(idPPV));
			parametroPuntoVentaTransaction.eliminar(pymeParametroPuntoVenta);				
		}
		
		if(tipoConsulta.equals("cargarCombos")){
			
			/*Cargamos las plantillas*/
			
			String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();
			rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
			String archivo=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+""
					+ ".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"plantillas"+
					File.separator+"pymes"+File.separator+"solicitudesCotizacion";
			
			JSONObject plantillaObject = new JSONObject();
			JSONArray  plantillaArray = new JSONArray();
			
			archivo=rutaPlantilla+archivo;
			File f = new File(archivo);
			File[] ficheros = f.listFiles();
			for (int x=0;x<ficheros.length;x++){				
				String nombrePlantilla=ficheros[x].getName().replace(".html", "");
				System.out.println(nombrePlantilla);
				plantillaObject.put("codigo", nombrePlantilla);
				plantillaObject.put("nombre", nombrePlantilla);
				plantillaArray.add(plantillaObject);
			  
			}			       
			result.put("plantillaArray", plantillaArray);
			/*Cargamos los puntos de venta*/
			
			JSONObject puntosVentaObject = new JSONObject();
			JSONArray  puntosVentaArray = new JSONArray();
			PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
			List<PuntoVenta> puntoSVentaS= new ArrayList<PuntoVenta>();
			puntoSVentaS=puntoVentaDAO.buscarTodos();
			for(PuntoVenta puntoVenta:puntoSVentaS){
				puntosVentaObject.put("codigo", puntoVenta.getId());
				puntosVentaObject.put("nombre", puntoVenta.getNombre());
				puntosVentaArray.add(puntosVentaObject);				
			}
			result.put("puntoVentaArray", puntosVentaArray);			
						
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

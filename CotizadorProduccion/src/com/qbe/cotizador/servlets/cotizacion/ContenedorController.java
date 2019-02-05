package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.ContenedorDAO;
import com.qbe.cotizador.model.Contenedor;
import com.qbe.cotizador.transaction.cotizacion.ContenedorTransaction;

/**
 * Servlet implementation class ContenedorController
 */
@WebServlet("/ContenedorController")
public class ContenedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContenedorController() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String numeroContenedor = request.getParameter("numeroContenedor") == null ? "" : request.getParameter("numeroContenedor");
			String vigenciaDesde = request.getParameter("vigenciaDesde") == null ? "" : request.getParameter("vigenciaDesde");
			String vigenciaHasta = request.getParameter("vigenciaHasta") == null ? "" : request.getParameter("vigenciaHasta");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject contenedorJSONObject = new JSONObject();
			JSONArray contenedorJSONArray = new JSONArray();
			ContenedorTransaction contenedorTransaction = new ContenedorTransaction();

			Contenedor contenedor = new Contenedor();
			ContenedorDAO contenedorDAO = new ContenedorDAO();
			if(tipoConsulta.equalsIgnoreCase("crear"))
			{
				BigInteger codEnsurance = new BigInteger(codigoEnsurance);
				contenedor = contenedorDAO.buscarPorNumeroActivo(numeroContenedor,codEnsurance);
				if(contenedor != null)
				{
					result.put("success", Boolean.FALSE);
				}
				else
				{
					contenedor = new Contenedor();
					if(codigoEnsurance != null && !codigoEnsurance.equals(""))
						contenedor.setIdEnsurance(new BigInteger(codigoEnsurance));
					contenedor.setNumero(numeroContenedor);
					contenedor.setDescripcion(descripcion);
					if(activo.equals("1"))
						contenedor.setActivo(true);
					else if(activo.equals("0"))
						contenedor.setActivo(false);
					
					if(vigenciaDesde != null && !vigenciaDesde.equals(""))
					{
						contenedor.setVigenciaDesde(transformarStringToFecha(vigenciaDesde));
					}
					
					if(vigenciaHasta != null && !vigenciaHasta.equals(""))
					{
						contenedor.setVigenciaHasta(transformarStringToFecha(vigenciaHasta));
					}
					result.put("success", Boolean.TRUE);
					contenedorTransaction.crear(contenedor);
				}
			}
			if(tipoConsulta.equalsIgnoreCase("buscar"))
			{
				String numero = request.getParameter("numeroCon") == null ? "" : request.getParameter("numeroCon");
				String fechaIn = request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fechaF = request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String ensurance = request.getParameter("ensurance") == null ? "" : request.getParameter("ensurance");
				Timestamp fechaInicio = transformarStringToFecha(fechaIn);
				Timestamp fechaFin = transformarStringToFecha(fechaF);
				BigInteger codEnsurance = null;
				if(!ensurance.equals(""))
				{
					codEnsurance = new BigInteger(ensurance);
				}
				
				List<Contenedor> results = contenedorDAO.buscarContenedorPorFiltros(numero, fechaInicio, fechaFin, codEnsurance);
				for(Contenedor c : results)
				{
					contenedorJSONObject.put("ensurance", c.getIdEnsurance().toString());
					contenedorJSONObject.put("numero", c.getNumero());
					contenedorJSONObject.put("descripcion", c.getDescripcion());
					contenedorJSONObject.put("vigenciaDesde", c.getVigenciaDesde().toString());
					contenedorJSONObject.put("vigenciaHasta", c.getVigenciaHasta().toString());
					contenedorJSONArray.add(contenedorJSONObject);
				}
				result.put("numRegistros", results.size());
				result.put("listadoContenedor", contenedorJSONArray);
			}
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
	
	private Timestamp transformarStringToFecha(String fecha) {
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse(fecha);
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (Exception e) {
			return null;
		}
	}
	

}

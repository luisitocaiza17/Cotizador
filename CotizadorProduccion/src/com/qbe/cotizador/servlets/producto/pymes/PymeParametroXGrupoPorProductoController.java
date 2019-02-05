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

import com.qbe.cotizador.dao.producto.pymes.PymeParametroXGrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeParametroXGrupoPorProducto;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeParametroXGrupoPorProductoTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeParametroXGrupoPorProductoController
 */
@WebServlet("/PymeParametroXGrupoPorProductoController")
public class PymeParametroXGrupoPorProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeParametroXGrupoPorProductoController() {
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
			String parametroGrupoProductoId = request.getParameter("parametroGrupoProductoId") == null ? "" : request.getParameter("parametroGrupoProductoId");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
			String mostrarMaquinaria = request.getParameter("mostrarMaquinaria") == null ? "" : request.getParameter("mostrarMaquinaria");
			String mostrarValorEquipoHerramienta = request.getParameter("mostrarValorEquipoHerramienta") == null ? "" : request.getParameter("mostrarValorEquipoHerramienta");
			String mostrarValorEstructuras = request.getParameter("mostrarValorEstructuras") == null ? "" : request.getParameter("mostrarValorEstructuras");
			String mostrarValorInsumos = request.getParameter("mostrarValorInsumos") == null ? "" : request.getParameter("mostrarValorInsumos");
			String mostrarValorMercaderia = request.getParameter("mostrarValorMercaderia") == null ? "" : request.getParameter("mostrarValorMercaderia");
			String mostrarValorMueblesEnseres = request.getParameter("mostrarValorMueblesEnseres") == null ? "" : request.getParameter("mostrarValorMueblesEnseres");
			String mostrarValorContenidos = request.getParameter("mostrarValorContenidos") == null ? "" : request.getParameter("mostrarValorContenidos");
			String limiteAsegurado = request.getParameter("limiteAsegurado") == null ? "" : request.getParameter("limiteAsegurado");
			String primaMinima = request.getParameter("primaMinima") == null ? "" : request.getParameter("primaMinima");
			
			PymeParametroXGrupoPorProducto pymeParametroXGPP = new PymeParametroXGrupoPorProducto();
			PymeParametroXGrupoPorProductoDAO pymeParametroXGPPDAO = new PymeParametroXGrupoPorProductoDAO();
			PymeParametroXGrupoPorProductoTransaction pymeParametroXGPPTransaction=new PymeParametroXGrupoPorProductoTransaction();
			
			JSONObject pymeParametroXGPPObject = new JSONObject();
			JSONArray pymeParametroXGPPArray = new JSONArray();
			
			JSONObject grupoPPJSONObject = new JSONObject();
			JSONArray grupoPPJSONArray = new JSONArray();
			
			
			if(!parametroGrupoProductoId.equals(""))
				pymeParametroXGPP.setParametroGrupoProductoId(new BigInteger(parametroGrupoProductoId));
			if(!grupoPorProductoId.equals(""))
				pymeParametroXGPP.setGrupoPorProductoId(new BigInteger(grupoPorProductoId));
			if(!mostrarMaquinaria.equals(""))
				pymeParametroXGPP.setMostrarMaquinaria(Boolean.parseBoolean(mostrarMaquinaria));
			if(!mostrarValorEquipoHerramienta.equals(""))
				pymeParametroXGPP.setMostrarValorEquipoHerramienta(Boolean.parseBoolean(mostrarValorEquipoHerramienta));
			if(!mostrarValorEstructuras.equals(""))
				pymeParametroXGPP.setMostrarValorEstructuras(Boolean.parseBoolean(mostrarValorEstructuras));
			if(!mostrarValorInsumos.equals(""))
				pymeParametroXGPP.setMostrarValorInsumos(Boolean.parseBoolean(mostrarValorInsumos));
			if(!mostrarValorMercaderia.equals(""))
				pymeParametroXGPP.setMostrarValorMercaderia(Boolean.parseBoolean(mostrarValorMercaderia));
			if(!mostrarValorMueblesEnseres.equals(""))
				pymeParametroXGPP.setMostrarValorMueblesEnseres(Boolean.parseBoolean(mostrarValorMueblesEnseres));
			if(!mostrarValorContenidos.equals(""))
				pymeParametroXGPP.setMostrarValorContenidos(Boolean.parseBoolean(mostrarValorContenidos));
			if(!limiteAsegurado.equals(""))
				pymeParametroXGPP.setLimiteAsegurado(Float.parseFloat(limiteAsegurado));
			if(!primaMinima.equals(""))
				pymeParametroXGPP.setPrimaMinima(Float.parseFloat(primaMinima));
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeParametroXGrupoPorProducto> resultList = pymeParametroXGPPDAO.buscarTodos();
				for(PymeParametroXGrupoPorProducto list : resultList){
					pymeParametroXGPPObject.put("parametroGrupoProductoId", list.getParametroGrupoProductoId());
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(list.getGrupoPorProductoId().toString());
					pymeParametroXGPPObject.put("grupoPorProductoId", grupoPorProducto.getNombreComercialProducto());
					
					if(list.getMostrarMaquinaria())
						pymeParametroXGPPObject.put("mostrarMaquinaria", "Si");
					else
						pymeParametroXGPPObject.put("mostrarMaquinaria", "No");
					
					if(list.getMostrarValorEquipoHerramienta())
						pymeParametroXGPPObject.put("mostrarValorEquipoHerramienta", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorEquipoHerramienta", "No");

					if(list.getMostrarValorEstructuras())
						pymeParametroXGPPObject.put("mostrarValorEstructuras", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorEstructuras", "No");
					
					if(list.getMostrarValorInsumos())
						pymeParametroXGPPObject.put("mostrarValorInsumos", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorInsumos", "No");
					
					if(list.getMostrarValorMercaderia())
						pymeParametroXGPPObject.put("mostrarValorMercaderia", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorMercaderia", "No");
					
					if(list.getMostrarValorMueblesEnseres())
						pymeParametroXGPPObject.put("mostrarValorMueblesEnseres", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorMueblesEnseres", "No");
					
					if(list.getMostrarValorContenidos())
						pymeParametroXGPPObject.put("mostrarValorContenidos", "Si");
					else
						pymeParametroXGPPObject.put("mostrarValorContenidos", "No");
					
					pymeParametroXGPPObject.put("limiteAsegurado", list.getLimiteAsegurado());
					pymeParametroXGPPObject.put("primaMinima", list.getPrimaMinima());
					pymeParametroXGPPArray.add(pymeParametroXGPPObject);
				}
				result.put("pymeParametroXGPPArray", pymeParametroXGPPArray);
				
				GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
				GrupoProducto grupoProducto = grupoProductoDAO.buscarPorNombre("PYMES");
				
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				List<GrupoPorProducto> grupoPorProducto = grupoPorProductoDAO.buscarTodosPorGrupo(grupoProducto);
				for(GrupoPorProducto grupoList : grupoPorProducto){
					grupoPPJSONObject.put("grupoPPId", grupoList.getId());
					grupoPPJSONObject.put("grupoPPNombre", grupoList.getNombreComercialProducto());
					grupoPPJSONArray.add(grupoPPJSONObject);
				}
				result.put("grupoPPJSONArray", grupoPPJSONArray);
				
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				PymeParametroXGrupoPorProducto list = pymeParametroXGPPDAO.buscarPorId(new BigInteger(parametroGrupoProductoId));
				
				result.put("parametroGrupoProductoId", list.getParametroGrupoProductoId());					
				result.put("grupoPorProductoId", list.getGrupoPorProductoId());
				result.put("mostrarMaquinaria", list.getMostrarMaquinaria());
				result.put("mostrarValorEquipoHerramienta", list.getMostrarValorEquipoHerramienta());
				result.put("mostrarValorEstructuras", list.getMostrarValorEstructuras());
				result.put("mostrarValorInsumos", list.getMostrarValorInsumos());
				result.put("mostrarValorMercaderia", list.getMostrarValorMercaderia());
				result.put("mostrarValorMueblesEnseres", list.getMostrarValorMueblesEnseres());
				result.put("mostrarValorContenidos", list.getMostrarValorContenidos());
				result.put("limiteAsegurado", list.getLimiteAsegurado());
				result.put("primaMinima", list.getPrimaMinima());
			}
			
			if(tipoConsulta.equals("obtenerPorProductoId")){
				PymeParametroXGrupoPorProducto list = pymeParametroXGPPDAO.buscarPorGrupoPorProductoId(new BigInteger(parametroGrupoProductoId));
				
				result.put("parametroGrupoProductoId", list.getParametroGrupoProductoId());					
				result.put("grupoPorProductoId", list.getGrupoPorProductoId());
				result.put("mostrarMaquinaria", list.getMostrarMaquinaria());
				result.put("mostrarValorEquipoHerramienta", list.getMostrarValorEquipoHerramienta());
				result.put("mostrarValorEstructuras", list.getMostrarValorEstructuras());
				result.put("mostrarValorInsumos", list.getMostrarValorInsumos());
				result.put("mostrarValorMercaderia", list.getMostrarValorMercaderia());
				result.put("mostrarValorMueblesEnseres", list.getMostrarValorMueblesEnseres());
				result.put("mostrarValorContenidos", list.getMostrarValorContenidos());
				result.put("limiteAsegurado", list.getLimiteAsegurado());
				result.put("primaMinima", list.getPrimaMinima());
			}
			
			if(tipoConsulta.equals("crear")){
				pymeParametroXGPPTransaction.crear(pymeParametroXGPP);				
			}
			
			if(tipoConsulta.equals("actualizar")){
				pymeParametroXGPPTransaction.editar(pymeParametroXGPP);
			}

			if(tipoConsulta.equals("eliminar")){
				pymeParametroXGPPTransaction.eliminar(pymeParametroXGPP);
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
package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoTasaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.PaqueteCobertura;
import com.qbe.cotizador.model.TipoCobertura;
import com.qbe.cotizador.model.TipoTasa;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CoberturaTransaction;

/**
 * Servlet implementation class CoberturaController
 */
@WebServlet("/CoberturaController")
public class CoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoberturaController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String tipoObjeto = request.getParameter("tipoObjeto") == null ? "" : request.getParameter("tipoObjeto");
			JSONObject coberturaJSONObject = new JSONObject();
			JSONArray coberturaJSONArray = new JSONArray();
			
			Cobertura cobertura = new Cobertura();
			CoberturaDAO coberturaDAO = new CoberturaDAO();
			
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String tipoTasaId = request.getParameter("tipoTasa") == null ? "" : request.getParameter("tipoTasa");
			//String esAdicional = request.getParameter("esAdicional") == null ? "" : request.getParameter("esAdicional");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoCoberturaId = request.getParameter("tipoCoberturaId") == null ? "" : request.getParameter("tipoCoberturaId");
			String grupoCoberturaId = request.getParameter("grupoCoberturaId") == null ? "" : request.getParameter("grupoCoberturaId");
			String tasaValor = request.getParameter("tasaValor") == null ? "" : request.getParameter("tasaValor");
			String afectaGrupo = request.getParameter("afectaGrupo") == null ? "" : request.getParameter("afectaGrupo");
			String afectaValorAsegurado = request.getParameter("afectaValorAsegurado") == null ? "" : request.getParameter("afectaValorAsegurado");
			String seccion = request.getParameter("seccion") == null ? "" : request.getParameter("seccion");
			String orden = request.getParameter("orden") == null ? "" : request.getParameter("orden");
			String limite = request.getParameter("limite") == null ? "" : request.getParameter("limite");
			String esPredeterminada = request.getParameter("esPredeterminada") == null ? "" : request.getParameter("esPredeterminada");
			String esPrimaFija = request.getParameter("primaFija") == null ? "" : request.getParameter("primaFija");
			String todoRiesgo = request.getParameter("todoRiesgo") == null ? "" : request.getParameter("todoRiesgo");
			String esMasivo = request.getParameter("esMasivo") == null ? "" : request.getParameter("esMasivo");
			String esPrincipal = request.getParameter("esPrincipal") == null ? "" : request.getParameter("esPrincipal");
			String rebajaValorAsegurado = request.getParameter("rebajaValorAsegurado") == null ? "" : request.getParameter("rebajaValorAsegurado");
			String generaEndosoRasa = request.getParameter("generaEndosoRasa") == null ? "" : request.getParameter("generaEndosoRasa");
			String esIndemnizable = request.getParameter("esIndemnizable") == null ? "" : request.getParameter("esIndemnizable");
			String esLimiteSuma = request.getParameter("esLimiteSuma") == null ? "" : request.getParameter("esLimiteSuma");
			String principalCobertura = request.getParameter("principalCobertura") == null ? "" : request.getParameter("tipoConsulta");
			String mostrarCotizador = request.getParameter("mostrarCotizador") == null ? "" : request.getParameter("mostrarCotizador");
			String nombreComercial = request.getParameter("nombreComercial") == null ? "" : request.getParameter("nombreComercial");
			String texto = request.getParameter("texto") == null ? "" : request.getParameter("texto");
			String textoCotizador = request.getParameter("textoCotizador") == null ? "" : request.getParameter("textoCotizador");
			String nemotecnico = request.getParameter("nemotecnico") == null ? "" : request.getParameter("nemotecnico");
			
			CoberturaTransaction coberturaTransaction = new CoberturaTransaction();
			
			if(codigo!=null&&!codigo.equals(""))
				cobertura=coberturaDAO.buscarPorId(codigo);
			
			if(tipoTasaId!=null&&!tipoTasaId.equals("")){
				TipoTasaDAO tipoTasaDAO = new TipoTasaDAO();
				TipoTasa tipoTasa=tipoTasaDAO.buscarPorId(tipoTasaId);
				if(tipoTasa.getId()!=null)
					cobertura.setTipoTasa(tipoTasa);
			}
			
			if(tipoCoberturaId!=null&&!tipoCoberturaId.equals("")){
				TipoCoberturaDAO tipoCoberturaDAO = new TipoCoberturaDAO();
				TipoCobertura tipoCobertura=tipoCoberturaDAO.buscarPorId(tipoCoberturaId);
				if(tipoCobertura.getId()!=null)
					cobertura.setTipoCobertura(tipoCobertura);
			}
			
			if(grupoCoberturaId!=null&&!grupoCoberturaId.equals("")){
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				GrupoCobertura grupoCobertura=grupoCoberturaDAO.buscarPorId(grupoCoberturaId);
				if(grupoCobertura.getId()!=null)
					cobertura.setGrupoCobertura(grupoCobertura);
			}
			
			if(esMasivo.equals("1"))
				cobertura.setEsMasivo("1");
			else
				cobertura.setEsMasivo("0");
			
			if(esPredeterminada.equals("1"))
				cobertura.setEsPredeterminada("1");
			else
				cobertura.setEsPredeterminada("0");
			
			if(afectaGrupo.equals("1"))
				cobertura.setAfectaGrupo("1");
			else
				cobertura.setAfectaGrupo("0");
			
			if(afectaValorAsegurado.equals("1"))
				cobertura.setAfectaValorAsegurado("1");
			else
				cobertura.setAfectaValorAsegurado("0");
		
			if(esPrimaFija.equals("1"))
				cobertura.setEsPrimaFija("1");
			else
				cobertura.setEsPrimaFija("0");
			
			if(todoRiesgo.equals("1"))
				cobertura.setEsTodoRiesgo("1");
			else
				cobertura.setEsTodoRiesgo("0");
			
			if(!nombre.equals(""))
				cobertura.setNombre(nombre);
			
			if(!nemotecnico.equals(""))
				cobertura.setNemotecnico(nemotecnico.toUpperCase());
			
			if(!nombre.equals(""))
				cobertura.setNombre(nombre);
			
			if(!tasaValor.equals("")){
				cobertura.setTasaValor(Double.parseDouble(tasaValor));
			}
			
			if(!seccion.equals("")){
				cobertura.setSeccion(seccion);
			}
			
			if(!orden.equals("")){
				cobertura.setOrden(Integer.parseInt(orden));
			}
						
			if(!limite.equals("")){
				cobertura.setLimite(limite);
			}
			
			if(esPrincipal.equals("1"))
				cobertura.setEsPrincipal("1");
			else
				cobertura.setEsPrincipal("0");
			
			if(rebajaValorAsegurado.equals("1"))
				cobertura.setRebajaValorAsegurado("1");
			else
				cobertura.setRebajaValorAsegurado("0");
			
			if(generaEndosoRasa.equals("1"))
				cobertura.setGeneraEndosoRasa("1");
			else
				cobertura.setGeneraEndosoRasa("0");
			
			if(esIndemnizable.equals("1"))
				cobertura.setGeneraEndosoRasa("1");
			else
				cobertura.setGeneraEndosoRasa("0");
			
			if(esLimiteSuma.equals("1"))
				cobertura.setEsLimiteSuma("1");
			else
				cobertura.setEsLimiteSuma("0");
			
			if(principalCobertura.equals("1"))
				cobertura.setPrincipalCobertura("1");
			else
				cobertura.setPrincipalCobertura("0");
			
			if(!nombreComercial.equals("")){
				cobertura.setNombreComercial(nombreComercial);
			}

			if(!nombreComercial.equals("")){
				cobertura.setNombreComercial(nombreComercial);
			}
			
			if(!texto.equals("")){
				cobertura.setTexto(texto.getBytes("UTF-8"));
			}
			
			if(!textoCotizador.equals("")){
				cobertura.setTextoCotizador(textoCotizador);
			}
			
			if(tipoConsulta.equals("crear")){
				coberturaTransaction.crear(cobertura);
			}
			
			if(tipoConsulta.equals("actualizar")){
				coberturaTransaction.editar(cobertura);
			}

			if(tipoConsulta.equals("encontrarTodos") && tipoObjeto.equalsIgnoreCase("VHDinamico")){
				
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorNombre("Vehículos Tarifa Dinámica");
				List<Cobertura> results = coberturaDAO.buscarCoberturasPorGrupoProducto(grupoPorProducto);
				List<PaqueteCobertura> resultadoCoberturas = coberturaDAO.buscarPaqueteCoberturasPorGrupoPorProductoId(grupoPorProducto);
				
				int i=0;
				for(i=0; i<results.size(); i++){
					cobertura = results.get(i);
					coberturaJSONObject.put("codigo", cobertura.getId());
					coberturaJSONObject.put("nombre", cobertura.getNombre());
					coberturaJSONObject.put("nemotecnico", cobertura.getNemotecnico());					
					coberturaJSONObject.put("tasa", cobertura.getTasaValor());
					coberturaJSONObject.put("tipoTasa", cobertura.getTipoTasa().getNombre().toLowerCase());
					coberturaJSONObject.put("es_adicional", cobertura.getTipoCobertura().getId());
					coberturaJSONObject.put("descripcion", "");
					mostrarCotizador = "0";
					if(cobertura.getMostrarCotizador())
						mostrarCotizador = "1";		
					coberturaJSONObject.put("mostrar_cotizador", mostrarCotizador);					
					coberturaJSONArray.add(coberturaJSONObject);
				}
				
				JSONObject coberturaPaquetesJSONObject = new JSONObject();
				JSONArray coberturaPaquetesJSONArray = new JSONArray();
				int j = 0;
				for (PaqueteCobertura object : resultadoCoberturas) {
						coberturaPaquetesJSONObject.put("cobertura_id", object.getCobertura().getId());
						coberturaPaquetesJSONObject.put("paquete_id", object.getPaquete().getId());
						coberturaPaquetesJSONArray.add(coberturaPaquetesJSONObject);
						j++;
				}
				
				VariableSistemaDAO vsDAO = new VariableSistemaDAO();
				VariableSistema vs=vsDAO.buscarPorNombre("LIMITE_EXCESO_RC_VH");
				
				result.put("limiteExcesoRC", vs.getValor());
				result.put("totalCoberturas", i);
				result.put("listadoCobertura", coberturaJSONArray);
				result.put("coberturasPorPaquetes", coberturaPaquetesJSONArray);
				result.put("totalCoberturasPorPaquetes", j);
				
			}

			if(tipoConsulta.equals("encontrarTodos") && !tipoObjeto.equalsIgnoreCase("VHDinamico")){
				String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();	
				GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
				List<Cobertura> results = null;
				List<PaqueteCobertura> resultadoCoberturas = null;
				
				if (grupoPorProducto != null){
					results = coberturaDAO.buscarCoberturasPorGrupoProducto(grupoPorProducto);
					resultadoCoberturas = coberturaDAO.buscarPaqueteCoberturasPorGrupoPorProductoId(grupoPorProducto);
				}
				int i=0;
				if(results != null){
					for(i=0; i<results.size(); i++){
						cobertura = results.get(i);
						coberturaJSONObject.put("codigo", cobertura.getId());
						coberturaJSONObject.put("nombre", cobertura.getNombreComercial());
						coberturaJSONObject.put("nemotecnico", cobertura.getNemotecnico());					
						coberturaJSONObject.put("tasa", cobertura.getTasaValor());
						coberturaJSONObject.put("tipoTasa", cobertura.getTipoTasa().getNombre().toLowerCase());
						coberturaJSONObject.put("es_adicional", cobertura.getTipoCobertura().getId());
						coberturaJSONObject.put("descripcion", "");
						mostrarCotizador = "0";
						if(cobertura.getMostrarCotizador())
							mostrarCotizador = "1";
						coberturaJSONObject.put("mostrar_cotizador", mostrarCotizador);
						coberturaJSONArray.add(coberturaJSONObject);
					}
				}
				
				
				if(resultadoCoberturas != null){
					JSONObject coberturaPaquetesJSONObject = new JSONObject();
					JSONArray coberturaPaquetesJSONArray = new JSONArray();
					int j = 0;
					for (PaqueteCobertura object : resultadoCoberturas) {
							coberturaPaquetesJSONObject.put("cobertura_id", object.getCobertura().getId());
							coberturaPaquetesJSONObject.put("paquete_id", object.getPaquete().getId());
							coberturaPaquetesJSONArray.add(coberturaPaquetesJSONObject);
							j++;
					}
				
					result.put("totalCoberturas", i);
					result.put("listadoCobertura", coberturaJSONArray);
					result.put("coberturasPorPaquetes", coberturaPaquetesJSONArray);
					result.put("totalCoberturasPorPaquetes", j);
				}
				result.put("totalCoberturas", i);
				result.put("listadoCobertura", coberturaJSONArray);
			}

			if(tipoConsulta.equals("encontrarPorGrupoCobertura")){
				grupoCoberturaId = request.getParameter("grupoCobertura") == null ? "" : request.getParameter("grupoCobertura");
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				GrupoCobertura grupoCobertura = grupoCoberturaDAO.buscarPorId(grupoCoberturaId);
				List<Cobertura> results=coberturaDAO.buscarCoberturasGrupoCobertura(grupoCobertura); 
				int i=0;
				for(i=0; i<results.size(); i++){
					cobertura=results.get(i);
					coberturaJSONObject.put("codigo", cobertura.getId());
					coberturaJSONObject.put("nombre", cobertura.getNombre());
					coberturaJSONObject.put("nemotecnico", cobertura.getNemotecnico());					
					coberturaJSONObject.put("tasa", cobertura.getTasaValor());
					coberturaJSONObject.put("tipoTasa", cobertura.getTipoTasa().getNombre());
					coberturaJSONObject.put("esAdicional", cobertura.getTipoCobertura().getId());
					coberturaJSONObject.put("tipoCobertura", cobertura.getTipoCobertura().getNombre());
					coberturaJSONObject.put("texto", new String(cobertura.getTexto()));
					coberturaJSONObject.put("textoCotizador", cobertura.getTextoCotizador());
					coberturaJSONObject.put("grupoCobertura", cobertura.getGrupoCobertura().getNombre());
					coberturaJSONObject.put("tasaValor", cobertura.getTasaValor());
					coberturaJSONObject.put("afectaGrupo", cobertura.getAfectaGrupo());
					coberturaJSONObject.put("afectaValorAsegurado", cobertura.getAfectaValorAsegurado());
					coberturaJSONObject.put("seccion", cobertura.getSeccion());
					coberturaJSONObject.put("orden", cobertura.getOrden());
					coberturaJSONObject.put("limite", cobertura.getLimite());
					coberturaJSONObject.put("esPredeterminada", cobertura.getEsPredeterminada());
					coberturaJSONObject.put("esPrimaFija", cobertura.getEsPrimaFija());
					coberturaJSONObject.put("esTodoRiesgo", cobertura.getEsTodoRiesgo());
					coberturaJSONObject.put("esMasivo", cobertura.getEsMasivo());
					coberturaJSONObject.put("esPrincipal", cobertura.getEsPrincipal());
					coberturaJSONObject.put("rebajaValorAsegurado", cobertura.getRebajaValorAsegurado());
					coberturaJSONObject.put("generaEndosoRasa", cobertura.getGeneraEndosoRasa());
					coberturaJSONObject.put("esIndemnizable", cobertura.getEsIndemnizable());
					coberturaJSONObject.put("esLimiteSuma", cobertura.getEsLimiteSuma());
					coberturaJSONObject.put("principalCobertura", cobertura.getPrincipalCobertura());
					coberturaJSONObject.put("mostrarCotizador", cobertura.getMostrarCotizador());
					//coberturaJSONObject.put("textoCertificado", cobertura.getTipoCobertura().getId());
					coberturaJSONObject.put("nombreComercial", cobertura.getNombreComercial());
					
					coberturaJSONArray.add(coberturaJSONObject);
				}
				
				result.put("totalCoberturas", i);
				result.put("listadoCobertura", coberturaJSONArray);
				
			}
			
			if(tipoConsulta.equals("encontrarTodos") && tipoObjeto.equalsIgnoreCase("")){										
				List<Cobertura> results = coberturaDAO.buscarMostrarCotizador();				
				int i=0;
				for(i=0; i<results.size(); i++){
					cobertura = results.get(i);
					coberturaJSONObject.put("codigo", cobertura.getId());
					coberturaJSONObject.put("nombre", cobertura.getNombre());
					coberturaJSONObject.put("nemotecnico", cobertura.getNemotecnico());					
					coberturaJSONObject.put("tasa", cobertura.getTasaValor());
					coberturaJSONObject.put("tipoTasa", cobertura.getTipoTasa().getNombre().toLowerCase());
					coberturaJSONObject.put("es_adicional", cobertura.getTipoCobertura().getId());
					coberturaJSONObject.put("descripcion", "");	
					mostrarCotizador = "0";
					if(cobertura.getMostrarCotizador())
						mostrarCotizador = "1";
					coberturaJSONObject.put("mostrar_cotizador", mostrarCotizador);
					coberturaJSONArray.add(coberturaJSONObject);
				}
				result.put("listadoCobertura", coberturaJSONArray);
			}
	
			if(tipoConsulta.equals("encontrarTodos") && tipoObjeto.equalsIgnoreCase("vehiculos")){										
				List<Cobertura> results = coberturaDAO.buscarCoberturaPorRamoNemotecnico("V%");				
				int i=0;
				for(i=0; i<results.size(); i++){
					cobertura = results.get(i);
					coberturaJSONObject.put("codigo", cobertura.getId());
					coberturaJSONObject.put("nombre", cobertura.getNombre());
					coberturaJSONObject.put("nemotecnico", cobertura.getNemotecnico());					
					coberturaJSONObject.put("tasa", cobertura.getTasaValor());
					coberturaJSONObject.put("tipoTasa", cobertura.getTipoTasa().getNombre().toLowerCase());
					coberturaJSONObject.put("es_adicional", cobertura.getTipoCobertura().getId());
					coberturaJSONObject.put("descripcion", "");	
					mostrarCotizador = "0";
					if(cobertura.getMostrarCotizador())
						mostrarCotizador = "1";
					coberturaJSONObject.put("mostrar_cotizador", mostrarCotizador);
					coberturaJSONArray.add(coberturaJSONObject);
				}
				result.put("listadoCobertura", coberturaJSONArray);
			}
			
			if(tipoConsulta.equals("verificarCoberturaOtros"))
			{
				String coberturaId = request.getParameter("coberturaId") == null ? "" : request.getParameter("coberturaId");
				boolean resultado = coberturaDAO.verificarCoberturaOtros(coberturaId);
				result.put("coberturaOtros", resultado);
			}
			
			
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

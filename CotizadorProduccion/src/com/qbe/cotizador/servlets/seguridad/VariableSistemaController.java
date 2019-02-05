package com.qbe.cotizador.servlets.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.seguridad.ModuloDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Modulo;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.seguridad.VariableSistemaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class VariableSistemaController
 */
@WebServlet("/VariableSistemaController")
public class VariableSistemaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VariableSistemaController() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject result = new JSONObject();
		try{			
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");
			String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoVariable = request.getParameter("tipoVariable") == null ? "" : request.getParameter("tipoVariable");
			String modulo = request.getParameter("modulo") == null ? "" : request.getParameter("modulo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");			
			Boolean usario_emite = (Boolean) request.getSession().getAttribute("emite");

			JSONObject variableSistemaJSONObject = new JSONObject();
			JSONArray variableSistemaJSONArray = new JSONArray();
			
			VariableSistema variable = new VariableSistema();
			VariableSistemaDAO variableDAO = new VariableSistemaDAO();
			
			TipoVariableDAO tipoVariableDAO = new TipoVariableDAO();
			TipoVariable tipoVariable1= new TipoVariable();
			
			ModuloDAO moduloDAO = new ModuloDAO();
			Modulo modulo1 = new Modulo();
			
			JSONObject tipoVariableJSONObject = new JSONObject();
			JSONArray tipoVariableJSONArray = new JSONArray();
			
			VariableSistemaTransaction variableSistemaTransaction = new VariableSistemaTransaction();
			
			if (!codigo.equals(""))
				variable.setId(Integer.parseInt(codigo));
				
			if (!nombre.equals(""))
				variable.setNombre(nombre);
			
			if (!valor.equals(""))
				variable.setValor(valor);
			
			if (!tipoVariable.equals("")) {
				variable.setTipoVariable(tipoVariableDAO.buscarPorId(tipoVariable));				
			}
			
			if (!modulo.equals(""))
				variable.setModulo(moduloDAO.buscarPorId(modulo));
			
			if (activo.equals("1"))
				variable.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				variable.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<VariableSistema> results = variableDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					variable = results.get(i);
					variableSistemaJSONObject.put("codigo", variable.getId());
					variableSistemaJSONObject.put("nombre", variable.getNombre());
					variableSistemaJSONObject.put("valor", variable.getValor());
					variableSistemaJSONObject.put("tipoVariable", variable.getTipoVariable().getNombre());
					variableSistemaJSONObject.put("modulo", variable.getModulo().getNombre());

					if(variable.getActivo())
						variableSistemaJSONObject.put("activo", "Si");
					else
						variableSistemaJSONObject.put("activo", "No");

					variableSistemaJSONArray.add(variableSistemaJSONObject);
				}
				
				result.put("numRegistros",i);
				result.put("listadoVariableSistema", variableSistemaJSONArray);
				
				List<TipoVariable> listaTipoVariable = tipoVariableDAO.buscarTodos();
				
				for(i = 0; i < listaTipoVariable.size(); i++ ){
					tipoVariable1 = listaTipoVariable.get(i);
					tipoVariableJSONObject.put("codigo", tipoVariable1.getId());
					tipoVariableJSONObject.put("nombre", tipoVariable1.getNombre());
					
					tipoVariableJSONArray.add(tipoVariableJSONObject);
				}
				
				result.put("listadoTipoVariable", tipoVariableJSONArray);
				
				JSONObject moduloJSONObject = new JSONObject();
				JSONArray moduloJSONArray = new JSONArray();
				
				List<Modulo> listadoModulo = moduloDAO.buscarTodos();
				
				for(i = 0; i < listadoModulo.size(); i++ ){
					modulo1 = listadoModulo.get(i);
					moduloJSONObject.put("codigo", modulo1.getId());
					moduloJSONObject.put("nombre", modulo1.getNombre());
					
					moduloJSONArray.add(moduloJSONObject);
				}
				
				result.put("listadoModulo", moduloJSONArray);
				
				
			}
			
			if(tipoConsulta.equals("crear"))
				variableSistemaTransaction.crear(variable);
			
			if(tipoConsulta.equals("actualizar"))
				variableSistemaTransaction.editar(variable);

			if(tipoConsulta.equals("eliminar"))
				variableSistemaTransaction.eliminar(variable);
			
			if(tipoConsulta.equals("encontrarTodosVariable") && producto.equals("productoVehiculo")){ 		
				
				List<String> listadoVariables = new ArrayList<String>();
				listadoVariables.add("ANO_FABRICACION");
				listadoVariables.add("ANTIGUEDAD_VEHICULO");				
				listadoVariables.add("CONDUCTOR_EDAD_MININA");
				listadoVariables.add("CONDUCTOR_EDAD_MAXIMA");
				listadoVariables.add("DANO_PARCIAL_MONTO_FIJO");
				listadoVariables.add("DANO_PARCIAL_PORCENTAJE_SINIESTRO");
				listadoVariables.add("DANO_PARCIAL_PORCENTAJE_SUMA_ASEGURADA");
				listadoVariables.add("MAXIMO_NUMERO_HIJOS");
				listadoVariables.add("POR_SUMA_ASEGURADA_DIF_TODO_RIESGO");
				listadoVariables.add("COTIZACION_LOCAL_ACTIVAR");
				listadoVariables.add("LIMITE_EXCESO_RC_VH");
				listadoVariables.add("PERMITE_EMISION");
				listadoVariables.add("DEDUCIBLE_PERDIDA_TOTAL");
				listadoVariables.add("DEDUCIBLE_RC");
						
				List<String> listadoValores = variableDAO.buscarPorNombres(listadoVariables);
					
				result.put("ano_fabricacion", listadoValores.get(0));
				result.put("antiguedad_vh",listadoValores.get(1));				
				result.put("edad_minima",listadoValores.get(2));
				result.put("edad_maxima",listadoValores.get(3));
				result.put("monto_fijo", listadoValores.get(4));
				result.put("porcentaje_siniestro",listadoValores.get(5));
				result.put("porcentaje_suma_asegurada",listadoValores.get(6));
				result.put("maximo_numero_hijos",listadoValores.get(7));
				result.put("por_suma_asegurada_dif_todo_riesgo",listadoValores.get(8));
				result.put("cotizacion_local",listadoValores.get(9));
				result.put("valor_excesoRC",listadoValores.get(10));
				result.put("permite_emision",listadoValores.get(11));
				result.put("usario_emite",usario_emite);
				result.put("deducible_perdida_total",listadoValores.get(12));
				result.put("deducible_rc",listadoValores.get(13));
			}
			
			if(tipoConsulta.equals("encontrarValorPorNombre")){
			  variable=variableDAO.buscarPorNombre("MENSAJE_INICIO");
			  result.put("valor", variable.getValor());
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

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

import com.qbe.cotizador.dao.cotizacion.TipoDeducibleDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaUsuarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTipoDeducibleGeneralDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTipoDeducibleGeneralRamoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeInspectorProvincia;
import com.qbe.cotizador.model.PymeInspectorProvinciaUsuario;
import com.qbe.cotizador.model.PymeTipoDeducibleGeneral;
import com.qbe.cotizador.model.PymeTipoDeducibleGeneralRamo;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.TipoDeducible;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeInspectorProvinciaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeTipoDeducibleGeneralTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class PymeInspectorProvinciaController
 */
@WebServlet("/PymeTipoDeducibleGeneralController")
public class PymeTipoDeducibleGeneralController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeTipoDeducibleGeneralController() {
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
		JSONObject result =  new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			String ramoId = request.getParameter("ramoId") == null ? "": request.getParameter("ramoId");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "": request.getParameter("grupoPorProductoId");
			String configuracionArray = request.getParameter("configuracionArray") == null ? "": request.getParameter("configuracionArray");
			/*Objetos bdd*/			
			
			PymeTipoDeducibleGeneral pymeTipoDeducibleGeneral = new PymeTipoDeducibleGeneral();
			PymeTipoDeducibleGeneralDAO pymeTipoDeducibleGeneralDAO = new PymeTipoDeducibleGeneralDAO();
			PymeTipoDeducibleGeneralTransaction pymeTransaction = new PymeTipoDeducibleGeneralTransaction();
			
			TipoDeducible tipoDeducible = new TipoDeducible();
			TipoDeducibleDAO tipoDeducibleDAO = new TipoDeducibleDAO();
			/*Objetos Json*/
			
			JSONObject ptipoDeducibleGeneralJsonObject = new JSONObject();
			JSONArray ptipoDeduciblesGeneralJsonArray = new JSONArray();
			
			JSONObject tiposDeducibleResumenJsonObject = new JSONObject();
			JSONArray tipoDeducibleResumenJsonArray = new JSONArray();
			
			/*Objetos Json*/
			
			
			if(tipoConsulta.equals("buscarTodos")){
				PymeTipoDeducibleGeneralRamoDAO pymeTipoDeducibleGeneralRamoDAO = new PymeTipoDeducibleGeneralRamoDAO();
				List<PymeTipoDeducibleGeneralRamo> usuarioList = pymeTipoDeducibleGeneralRamoDAO.buscarTodos();
				for(PymeTipoDeducibleGeneralRamo list : usuarioList){
					tiposDeducibleResumenJsonObject.put("ramoId",list.getRamoId());
					tiposDeducibleResumenJsonObject.put("ramoNombre",list.getRamoNombre());
					tiposDeducibleResumenJsonObject.put("grupoPorProductoId",list.getGrupoPorProductoId());
					tiposDeducibleResumenJsonObject.put("grupoNombre",list.getGrupoNombre());

					tipoDeducibleResumenJsonArray.add(tiposDeducibleResumenJsonObject);			
				}
				result.put("tipoDeduciblesResumen", tipoDeducibleResumenJsonArray);	
			}
			
			if(tipoConsulta.equals("buscarPorId")){
				List<PymeTipoDeducibleGeneral> pymeTipoDeducibleGeneralList = pymeTipoDeducibleGeneralDAO.buscarPorRamoGrupoProductoId(new BigInteger(ramoId), new BigInteger(grupoPorProductoId));
				if(pymeTipoDeducibleGeneralList.size() >=1 ){					
					for(PymeTipoDeducibleGeneral pIPList : pymeTipoDeducibleGeneralList){
						tipoDeducible=tipoDeducibleDAO.buscarPorId(pIPList.getTipoDeducibleId().toString());
						ptipoDeducibleGeneralJsonObject.put("tipoDeducibleId", tipoDeducible.getId());
						ptipoDeducibleGeneralJsonObject.put("tipoDeducibleNombre", tipoDeducible.getTextoDescripcion());
						ptipoDeducibleGeneralJsonObject.put("valor", pIPList.getValor());
						ptipoDeduciblesGeneralJsonArray.add(ptipoDeducibleGeneralJsonObject);						
					}
				}
				result.put("detallDeducibles", ptipoDeduciblesGeneralJsonArray);
			}

			if(tipoConsulta.equals("crear")){
				List<PymeTipoDeducibleGeneral> pymeInspectorProvinciaList = pymeTipoDeducibleGeneralDAO.buscarPorRamoGrupoProductoId(new BigInteger(ramoId), new BigInteger(grupoPorProductoId));
				if(pymeInspectorProvinciaList.size() >=1 ){					
					for(PymeTipoDeducibleGeneral pIPList : pymeInspectorProvinciaList){
						pymeTransaction.eliminar(pIPList);
					}					
				}
				JSONArray array = (JSONArray)JSONSerializer.toJSON(configuracionArray);
                //por cada objeto JSon CREADO ..
                for (Object js:array){
                	JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
                	pymeTipoDeducibleGeneral.setRamoId(new BigInteger(ramoId));
                	pymeTipoDeducibleGeneral.setGrupoPorProductoId(new BigInteger(grupoPorProductoId));
                	pymeTipoDeducibleGeneral.setTipoDeducibleId(new BigInteger(jsonStr.getString("tipoDeducibleId")));
                	pymeTipoDeducibleGeneral.setValor(new Double(jsonStr.getString("valor")));
    				
    				pymeTransaction.crear(pymeTipoDeducibleGeneral);
                }	
			}
			

			if(tipoConsulta.equals("eliminar")){
				List<PymeTipoDeducibleGeneral> pymeInspectorProvinciaList = pymeTipoDeducibleGeneralDAO.buscarPorRamoGrupoProductoId(new BigInteger(ramoId), new BigInteger(grupoPorProductoId));
				if(pymeInspectorProvinciaList.size() >=1 ){					
					for(PymeTipoDeducibleGeneral pIPList : pymeInspectorProvinciaList){
						pymeTransaction.eliminar(pIPList);
					}					
				}
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
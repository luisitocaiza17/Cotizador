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

import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaAsignadoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeInspectorProvinciaAsignado;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeInspectorProvinciaAsignado
 */
@WebServlet("/PymeInspectorProvinciaAsignadoController")
public class PymeInspectorProvinciaAsignadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeInspectorProvinciaAsignadoController() {
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
		
		JSONObject result =  new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			String cotizacionDetalleId = request.getParameter("cotizacionDetalleId") == null ? "": request.getParameter("cotizacionDetalleId");
			//String ciudadId = request.getParameter("ciudadId") == null ? "": request.getParameter("ciudadId");
			
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			CotizacionDetalle detalle= cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
			if(detalle!=null)
			{
				PymeObjetoCotizacionDAO objetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
				
				PymeInspectorProvinciaAsignadoDAO inspectorPADAO=new PymeInspectorProvinciaAsignadoDAO();
				
				JSONObject pUsuarioAsignadoJsonObject = new JSONObject();
				JSONArray pUsuarioAsignadoJsonArray = new JSONArray();
				if(tipoConsulta.equals("obtenerUsuariosPorProvincia")){
					
					List<PymeInspectorProvinciaAsignado> listado= inspectorPADAO.buscarPorProvinciaCiudad(BigInteger.valueOf(objetoCotizacion.getProvinciaId()), objetoCotizacion.getCiudadId());
					for(PymeInspectorProvinciaAsignado listProvincia: listado){
						pUsuarioAsignadoJsonObject.put("usuarioId", listProvincia.getUsuarioId());
						pUsuarioAsignadoJsonObject.put("nombre_completo", listProvincia.getNombreCompleto());
						pUsuarioAsignadoJsonArray.add(pUsuarioAsignadoJsonObject);
					}
					//Vuelvo a buscar las que no tienen asignado nada
					listado= inspectorPADAO.buscarPorProvinciaCiudad(BigInteger.valueOf(9999), BigInteger.valueOf(9999));
					for(PymeInspectorProvinciaAsignado listProvincia: listado){
						pUsuarioAsignadoJsonObject.put("usuarioId", listProvincia.getUsuarioId());
						pUsuarioAsignadoJsonObject.put("nombre_completo", listProvincia.getNombreCompleto());
						pUsuarioAsignadoJsonArray.add(pUsuarioAsignadoJsonObject);
					}
					result.put("listaUsuariosAsignado", pUsuarioAsignadoJsonArray);
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

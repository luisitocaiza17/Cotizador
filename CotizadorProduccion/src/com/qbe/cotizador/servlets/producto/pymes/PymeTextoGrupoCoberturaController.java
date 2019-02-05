package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoCoberturaDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTextoGrupoCoberturaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCobertura;
import com.qbe.cotizador.model.PymeTextoGrupoCobertura;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.TipoCobertura;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeCoberturaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeTextoGrupoCoberturaTransaction;

/**
 * Servlet implementation class PymeTextoGrupoCoberturaController
 */
@WebServlet("/PymeTextoGrupoCoberturaController")
public class PymeTextoGrupoCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeTextoGrupoCoberturaController() {
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
			String textoGrupoCobertura = request.getParameter("textoGrupoCobertura") == null ? "" : request.getParameter("textoGrupoCobertura");
			String grupoCoberturaId = request.getParameter("grupoCoberturaId") == null ? "" : request.getParameter("grupoCoberturaId");			
			String ramoId = request.getParameter("ramoId") == null ? "" : request.getParameter("ramoId");
			String texto = request.getParameter("texto") == null ? "" : request.getParameter("texto");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
			String productoEnsId = request.getParameter("productoEnsId") == null ? "" : request.getParameter("productoEnsId");

			PymeTextoGrupoCobertura pymeTextoGrupoCobertura = new PymeTextoGrupoCobertura();
			PymeTextoGrupoCoberturaDAO pymeTextoGrupoCoberturaDAO = new PymeTextoGrupoCoberturaDAO();
			PymeTextoGrupoCoberturaTransaction pymeTextoGrupoCoberturaTransaction = new PymeTextoGrupoCoberturaTransaction();
			//PymeCoberturaTransaction pymeCoberturaTransaction=new PymeCoberturaTransaction(); 

			Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
			
			JSONObject pymeTextoGrupoCoberturaJSONObject = new JSONObject();
			JSONArray pymeTextoGrupoCoberturaJSONArray = new JSONArray();

			JSONObject grupoCoberturaJSONObject = new JSONObject();
			JSONArray grupoCoberturaJSONArray = new JSONArray();	

			JSONObject ramoJSONObject = new JSONObject();
			JSONArray ramoJSONArray = new JSONArray();

			if(!textoGrupoCobertura.equals(""))
				pymeTextoGrupoCobertura.setTextoGrupoCoberturaId(textoGrupoCobertura);		
			if(!grupoCoberturaId.equals(""))
				pymeTextoGrupoCobertura.setGrupoCoberturaId(new BigInteger(grupoCoberturaId));
			if(!ramoId.equals(""))
				pymeTextoGrupoCobertura.setRamoId(ramoId);
			if(!texto.equals(""))
				pymeTextoGrupoCobertura.setTexto(texto.getBytes(Charset.forName("UTF-8")));			
			if(!grupoPorProductoId.equals(""))
				pymeTextoGrupoCobertura.setGrupoPorProductoId(new BigInteger(grupoPorProductoId));
			if(!productoEnsId.equals(""))
				pymeTextoGrupoCobertura.setProductoEnsId(productoEnsId);

			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeTextoGrupoCobertura> results = pymeTextoGrupoCoberturaDAO.buscarTodos();
				for(PymeTextoGrupoCobertura textoCobertura : results){
					pymeTextoGrupoCoberturaJSONObject.put("textoGrupoCobertura", textoCobertura.getTextoGrupoCoberturaId());

					GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();				
					GrupoCobertura grupoCobertura = grupoCoberturaDAO.buscarPorId(textoCobertura.getGrupoCoberturaId().toString());

					pymeTextoGrupoCoberturaJSONObject.put("grupoCoberturaId", grupoCobertura.getNombre());				

					RamoDAO ramoDAO = new RamoDAO();
					Ramo ramo = ramoDAO.buscarPorId(textoCobertura.getRamoId().toString());				
					pymeTextoGrupoCoberturaJSONObject.put("ramoId",ramo.getNombre());	

					GrupoPorProductoDAO grupoProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto resultGP = grupoProductoDAO.buscarPorId(textoCobertura.getGrupoPorProductoId().toString());
					pymeTextoGrupoCoberturaJSONObject.put("grupoPorProductoId",resultGP.getNombreComercialProducto());
					pymeTextoGrupoCoberturaJSONArray.add(pymeTextoGrupoCoberturaJSONObject);
				}
				result.put("listadoCoberturaPymes", pymeTextoGrupoCoberturaJSONArray);


				RamoDAO ramoDAO = new RamoDAO();
				List<Ramo> resultR = ramoDAO.buscarTodos();
				for(Ramo ramo: resultR){
					ramoJSONObject.put("ramoId", ramo.getId());
					ramoJSONObject.put("ramoNombre", ramo.getNombre());
					ramoJSONArray.add(ramoJSONObject);
				}
				result.put("ramoArr", ramoJSONArray);				
				
			}

			if(tipoConsulta.equals("obtenerPorId")){
				PymeTextoGrupoCobertura results = pymeTextoGrupoCoberturaDAO.buscarPorId(textoGrupoCobertura);	
				result.put("ramoId", results.getRamoId());
				result.put("textoGrupoCobertura", results.getTextoGrupoCoberturaId());
				result.put("gGrupoPorProductoId", results.getGrupoPorProductoId());
				result.put("gProductoEnsID", results.getProductoEnsId());
				result.put("gCoberturaId", results.getGrupoCoberturaId());
				
				//GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				//GrupoCobertura resultGC = grupoCoberturaDAO.buscarPorId(results.getGrupoCoberturaId().toString());
				//result.put("gCoberturaNombre", resultGC.getNombre());
				String valor= new String(results.getTexto(), "UTF-8");
				result.put("texto", new String(results.getTexto(), "UTF-8"));	
				
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				GrupoCobertura resultGC = grupoCoberturaDAO.buscarPorId(results.getGrupoCoberturaId().toString());
				
				grupoCoberturaJSONObject.put("gCoberturaId", resultGC.getId());
				grupoCoberturaJSONObject.put("gCoberturaNombre", resultGC.getNombre());
				
				grupoCoberturaJSONArray.add(grupoCoberturaJSONObject);
				
				result.put("grupoCoberturaArr", grupoCoberturaJSONArray);
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
				pymeTextoGrupoCoberturaTransaction.crear(pymeTextoGrupoCobertura);
			}

			if(tipoConsulta.equals("actualizar")){
				pymeTextoGrupoCobertura=pymeTextoGrupoCoberturaDAO.buscarPorId(pymeTextoGrupoCobertura.getTextoGrupoCoberturaId());
				String textoCobertura=(new String(pymeTextoGrupoCobertura.getTexto(), "UTF-8"));
				/***TRATAMIENTO DE ERROR***/
				Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
				String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
				/***AUDITORIA auditamos el error para el seguimiento***/
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setTramite(CodError);
				pymeAuditoriaGeneral.setEstado("ACTUALIZACION TEXTO");
				pymeAuditoriaGeneral.setProceso("PYMES");
				pymeAuditoriaGeneral.setObjeto("Eliminacion de Texto:"+pymeTextoGrupoCobertura.getTextoGrupoCoberturaId()+
						" GrupoPorProductoId="+pymeTextoGrupoCobertura.getGrupoPorProductoId()+
						" RamoId="+pymeTextoGrupoCobertura.getRamoId()	+" GrupoCobertura="+pymeTextoGrupoCobertura.getGrupoCoberturaId()+
						" Textos="+textoCobertura.trim()+
						" Realizado por usuario: "+usuario.getLogin()+" Nombre: "+usuario.getEntidad().getNombreCompleto());
				try {
					pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				pymeTextoGrupoCoberturaTransaction.editar(pymeTextoGrupoCobertura);
			}

			if(tipoConsulta.equals("eliminar")){
				pymeTextoGrupoCobertura=pymeTextoGrupoCoberturaDAO.buscarPorId(pymeTextoGrupoCobertura.getTextoGrupoCoberturaId());
				String textoCobertura=(new String(pymeTextoGrupoCobertura.getTexto(), "UTF-8"));
				/***TRATAMIENTO DE ERROR***/
				Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
				String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
				/***AUDITORIA auditamos el error para el seguimiento***/
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setTramite(CodError);
				pymeAuditoriaGeneral.setEstado("ELIMINACION TEXTO");
				pymeAuditoriaGeneral.setProceso("PYMES");
				pymeAuditoriaGeneral.setObjeto("Eliminacion de Texto:"+pymeTextoGrupoCobertura.getTextoGrupoCoberturaId()+
						" GrupoPorProductoId="+pymeTextoGrupoCobertura.getGrupoPorProductoId()+
						" RamoId="+pymeTextoGrupoCobertura.getRamoId()	+" GrupoCobertura="+pymeTextoGrupoCobertura.getGrupoCoberturaId()+
						" Textos="+textoCobertura.trim()+
						" Realizado por usuario: "+usuario.getLogin()+" Nombre: "+usuario.getEntidad().getNombreCompleto());
				try {
					pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				pymeTextoGrupoCoberturaTransaction.eliminar(pymeTextoGrupoCobertura);
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

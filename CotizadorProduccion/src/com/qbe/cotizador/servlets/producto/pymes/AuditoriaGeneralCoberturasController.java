package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreAuditoriaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAuditoriaGeneralDAO;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.servlets.producto.agricola.DataSourceResult;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

/**
 * Servlet implementation class AuditoriaGeneralCoberturasController
 */
@WebServlet("/AuditoriaGeneralCoberturasController")
public class AuditoriaGeneralCoberturasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuditoriaGeneralCoberturasController() {
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
		// TODO Auto-generated method stub
				JSONObject result = new JSONObject();
				try {
					String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
					
					if(tipoConsulta.equals("encontrarPorParametros") ){
						String tramite = request.getParameter("nro_tramite") == null ? "": request.getParameter("nro_tramite").trim();
						String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
						String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
						int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
						int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

							
						PymeAuditoriaGeneralDAO pymeAuditoriaGeneralDAO = new PymeAuditoriaGeneralDAO();
						
						List<PymeAuditoriaGeneral> data=pymeAuditoriaGeneralDAO.buscarLog(fInicio, fFinal, tramite, skip, take);
						long total=pymeAuditoriaGeneralDAO.buscarLogNum(fInicio, fFinal, tramite, skip, take);
						
						DataSourceResult pg = new DataSourceResult();
						pg.setTotal((int)total);
						pg.setData(data);
						
						Gson gson = new Gson();
						// convert the DataSourceReslt to JSON and write it to the response
						response.setContentType("application/json; charset=ISO-8859-1");
					    response.getWriter().print(gson.toJson(pg));
					    return;
						
					}
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

package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;






import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucursalXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriSucursalXCanal;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.transaction.producto.agricola.AgriTipoCultivoTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriTipoCultivoController
 */
@WebServlet("/AgriSucursalXCanalController")
public class AgriSucursalXCanalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriSucursalXCanalController() {
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
		JSONObject result = new JSONObject ();
		try 
		{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String canalId = request.getParameter("canalId")==null?"":request.getParameter("canalId");
			JSONObject SucursalXCanalJSONObjetc = new JSONObject();
			JSONArray SucursalXCanalJSONArray = new JSONArray();
			
			AgriSucursalXCanalDAO agriSucursalXCanalDAO = new AgriSucursalXCanalDAO();
			SucursalDAO sucursalDAO=new SucursalDAO();
			
			if (tipoConsulta.equals("encontrarPorCanal"))
			{
				List<AgriSucursalXCanal> results = agriSucursalXCanalDAO.BuscarPorCanalId(new BigInteger(canalId));
				for (AgriSucursalXCanal sucursalXCanal: results)
				{
					SucursalXCanalJSONObjetc.put("id", sucursalXCanal.getSucursalCanalId());
					SucursalXCanalJSONObjetc.put("CanalId", sucursalXCanal.getCanalId());
					SucursalXCanalJSONObjetc.put("SucursalId", sucursalXCanal.getSucursalId());
					Sucursal sucursal= sucursalDAO.buscarPorId(sucursalXCanal.getSucursalId().toString());
					if(sucursal!=null)
						SucursalXCanalJSONObjetc.put("SucursalNombre", sucursal.getNombre());
					SucursalXCanalJSONArray.add(SucursalXCanalJSONObjetc);
				}
				result.put("SucursalXCanalJSONArray", SucursalXCanalJSONArray);
			}
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}
		catch(Exception e)
		{
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}

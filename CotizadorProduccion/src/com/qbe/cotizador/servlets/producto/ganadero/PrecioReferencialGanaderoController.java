package com.qbe.cotizador.servlets.producto.ganadero;

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

import com.qbe.cotizador.dao.cotizacion.PrecioReferencialDAO;
import com.qbe.cotizador.dao.producto.ganadero.PrecioReferencialGanaderoDAO;
import com.qbe.cotizador.dao.producto.ganadero.RazaDAO;
import com.qbe.cotizador.dao.producto.ganadero.TipoGanadoDAO;
import com.qbe.cotizador.model.PrecioReferencialGanadero;
import com.qbe.cotizador.model.Raza;
import com.qbe.cotizador.model.TipoGanado;
import com.qbe.cotizador.transaction.producto.ganadero.PrecioReferencialGanaderoTransaction;

/**
 * Servlet implementation class PrecioReferencialGanaderoController
 */
@WebServlet("/PrecioReferencialGanaderoController")
public class PrecioReferencialGanaderoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrecioReferencialGanaderoController() {
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
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String tipoGanadoId = request.getParameter("tipoGanado") == null ? "" : request.getParameter("tipoGanado");
			String tipoProduccion = request.getParameter("tipoProduccion") == null ? "" : request.getParameter("tipoProduccion");
			String precioMinimo = request.getParameter("precioMinimo") == null ? "" : request.getParameter("precioMinimo");
			String precioMaximo = request.getParameter("precioMaximo") == null ? "" : request.getParameter("precioMaximo");
			String region = request.getParameter("region") == null ? "" : request.getParameter("region");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject precioReferencialJSONObject = new JSONObject();
			JSONArray precioReferencialJSONArray = new JSONArray();
			
			PrecioReferencialGanadero precioReferencial = new PrecioReferencialGanadero(); 
			PrecioReferencialGanaderoDAO precioDAO = new PrecioReferencialGanaderoDAO();
			
			PrecioReferencialGanaderoTransaction precioReferencialGanaderoTransaction = new PrecioReferencialGanaderoTransaction();
			
			if(!codigo.equals(""))
				precioReferencial.setId(Integer.parseInt(codigo));
			
			if(tipoGanadoId != null && !tipoGanadoId.equals(""))				
				precioReferencial.setTipoGanadoId(tipoGanadoId.toString());	
			
			
			if(!tipoProduccion.equals(""))
				precioReferencial.setTipoProduccion(tipoProduccion);
			
			if(!precioMinimo.equals(""))
				precioReferencial.setPrecioMinimo(Double.parseDouble(precioMinimo));
			
			if(!precioMaximo.equals(""))
				precioReferencial.setPrecioMaximo(Double.parseDouble(precioMaximo));
			
			if(!region.equals(""))
				precioReferencial.setRegion(region);
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<PrecioReferencialGanadero> results = precioDAO.buscarTodos();
				for(PrecioReferencialGanadero precioReferencial2 : results)
				{
					precioReferencialJSONObject.put("codigo", precioReferencial2.getId());
					TipoGanadoDAO tipoGanadoDAO = new TipoGanadoDAO();
					TipoGanado tipoGanado = tipoGanadoDAO.buscarPorId(precioReferencial2.getTipoGanadoId());
					if(tipoGanado.getId() != null && !tipoGanado.getId().equals(""))						
						precioReferencialJSONObject.put("tipoGanado", tipoGanado.getNombre());
					
					precioReferencialJSONObject.put("tipoProduccion", precioReferencial2.getTipoProduccion());
					precioReferencialJSONObject.put("precioMinimo", precioReferencial2.getPrecioMinimo());
					precioReferencialJSONObject.put("precioMaximo", precioReferencial2.getPrecioMaximo());
					precioReferencialJSONObject.put("region", precioReferencial2.getRegion());
					precioReferencialJSONArray.add(precioReferencialJSONObject);
				}
				result.put("numRegistros", results.size());
				result.put("listadoPrecioReferencial", precioReferencialJSONArray);
			}
			
			if(tipoConsulta.equals("obtenerPorID")){
				precioReferencial = precioDAO.buscarPorId(Integer.parseInt(codigo));
				result.put("codigo", precioReferencial.getId());
				result.put("tipoGanado", precioReferencial.getTipoGanadoId());
				result.put("tipoProduccion", precioReferencial.getTipoProduccion());
				result.put("precioMinimo", precioReferencial.getPrecioMinimo());
				result.put("precioMaximo", precioReferencial.getPrecioMaximo());
				result.put("region", precioReferencial.getRegion());
			}
			
			if(tipoConsulta.equals("crear")){
				precioReferencialGanaderoTransaction.crear(precioReferencial);
			}
			
			if(tipoConsulta.equals("actualizar")){
				precioReferencialGanaderoTransaction.editar(precioReferencial);
			}
			
			if(tipoConsulta.equals("eliminar")){
				precioReferencialGanaderoTransaction.eliminar(precioReferencial);
			}
			
			if(tipoConsulta.equals("encontrarPorParametros")){
				String tipoGanadoIdVal = request.getParameter("tipoGanadoId") == null ? "" : request.getParameter("tipoGanadoId");
				PrecioReferencialGanadero precioDelRango = precioDAO.buscarPorParametros(tipoGanadoIdVal, tipoProduccion, region);
				if(precioDelRango!=null){
					result.put("id", precioDelRango.getId());
					result.put("precioMinimo", precioDelRango.getPrecioMinimo());
					result.put("precioMaximo", precioDelRango.getPrecioMaximo());
				}
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

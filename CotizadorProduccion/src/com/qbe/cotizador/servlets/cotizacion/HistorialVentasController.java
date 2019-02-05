package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;










import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.util.Utilitarios;



/**
 * Servlet implementation class HistorialVentasController
 */
@WebServlet("/HistorialVentasController")
public class HistorialVentasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistorialVentasController() {
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
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
			String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
			String codigoTipoObjeto= request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
			
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			JSONObject cotizacionJSONObject = new JSONObject();
			JSONArray cotizacionJSONArray = new JSONArray();

			//Para el Cliente
			Cliente cliente= new Cliente();
			ClienteDAO clienteDAO= new ClienteDAO();
			JSONObject clienteJSONObject = new JSONObject();
			JSONArray clienteJSONArray = new JSONArray();
			List<Cliente> listadoCliente= clienteDAO.buscarTodos();
			int i;
			for (i = 0; i < listadoCliente.size(); i++) {
				cliente = listadoCliente.get(i);
				clienteJSONObject.put("codigo",cliente.getId());
				clienteJSONObject.put("nombre",cliente.getEntidad().getNombreCompleto());
				clienteJSONArray.add(clienteJSONObject);
			}
			result.put("listadoCliente",clienteJSONArray);
			
			//Para el Agente
			Agente agente = new Agente();
			AgenteDAO agenteDAO = new AgenteDAO();
			JSONObject agenteJSONObject = new JSONObject();
			JSONArray agenteJSONArray = new JSONArray();
			List<Agente> listadoAgente= agenteDAO.buscarTodos();			
			for (i = 0; i < listadoAgente.size(); i++) {
				agente = listadoAgente.get(i);
				agenteJSONObject.put("codigo",agente.getId());
				agenteJSONObject.put("nombre",agente.getEntidad().getNombreCompleto());
				agenteJSONArray.add(agenteJSONObject);
			}
			result.put("listadoAgente",clienteJSONArray);
			
			
			//Para el Estado
			Estado estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();

			JSONObject estadoJSONObject = new JSONObject();
			JSONArray estadoJSONArray = new JSONArray();

			List<Estado> listadoEstado= estadoDAO.buscarTodos();
			for (i = 0; i < listadoEstado.size(); i++) {
				estado = listadoEstado.get(i);
				estadoJSONObject.put("codigo",estado.getId());
				estadoJSONObject.put("nombre",estado.getNombre());
				estadoJSONArray.add(estadoJSONObject);
			}
			result.put("listadoEstado",estadoJSONArray);
			
			//Para el Producto
			Producto producto = new Producto();
			ProductoDAO productoDAO = new ProductoDAO();
			
			JSONObject productoJSONObject = new JSONObject();
			JSONArray productoJSONArray = new JSONArray();

			List<Producto> listadoProducto= productoDAO.buscarTodos();
			for (i = 0; i < listadoProducto.size(); i++) {
				producto = listadoProducto.get(i);
				productoJSONObject.put("codigo",producto.getId());
				productoJSONObject.put("nombre",producto.getNombre());
				productoJSONArray.add(productoJSONObject);
			}
			result.put("listadoProducto",productoJSONArray);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Cotizacion> results = cotizacionDAO.buscarTodos();
				for(i=0; i<results.size(); i++){
					cotizacion = results.get(i);
					
					String idcliente = cotizacion.getClienteId().toString();
					Entidad clientea = clienteDAO.buscarPorId(idcliente).getEntidad();
					String entidadc = clientea.getNombres() +" " + clientea.getApellidos();
					
					String idagente = cotizacion.getAgenteId().toString();
					Entidad agentea = agenteDAO.buscarPorId(idagente).getEntidad();
					String entidada = agentea.getNombreCompleto();
					
					String idproducto = cotizacion.getProducto().getId().toString();
					Producto productoa = productoDAO.buscarPorId(idproducto);
					String productof = productoa.getNombre();
					
					String idestado = cotizacion.getEstado().getId();
					Estado estadoa = estadoDAO.buscarPorId(idestado);
					String estadof = estadoa.getNombre();
					
					cotizacionJSONObject.put("ccliente", entidadc);
					cotizacionJSONObject.put("cagente",entidada);
					cotizacionJSONObject.put("cproducto",productof);
					cotizacionJSONObject.put("cestado",estadof);
					cotizacionJSONObject.put("csuma",cotizacion.getSumaAseguradaTotal());
					cotizacionJSONArray.add(cotizacionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoHistorialVentas", cotizacionJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarxFecha")){
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjeto = tipoObjetoDAO.buscarPorId(codigoTipoObjeto);
				
				Usuario usuario = new Usuario();
				
				if(request.getSession().getAttribute("usuario")!=null)
					 usuario = (Usuario)request.getSession().getAttribute("usuario");
				
				List<Cotizacion> results =new ArrayList<Cotizacion>();

				Rol rol=new Rol();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				
				if((Utilitarios.verificarAdministradorBusqueda(rol))) // Validacion para administradoressegun el rol
					results = cotizacionDAO.buscarCotizacionxFecha(fInicio, fFinal, tipoObjeto);
				else{
					PuntoVenta puntoVenta=new PuntoVenta();
					
						if(usuario.getUsuarioXPuntoVentas().size()>0)
							puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						
						results =cotizacionDAO.buscarCotizacionxFechaPuntoVenta(fInicio, fFinal,tipoObjeto, puntoVenta);
					}
				for(i=0; i<results.size(); i++){
					cotizacion = results.get(i);
					
					String idcliente = cotizacion.getClienteId().toString();
					Entidad clientea = clienteDAO.buscarPorId(idcliente).getEntidad();
					String entidadc = clientea.getNombres() +" " + clientea.getApellidos();
					
					String idagente = cotizacion.getAgenteId().toString();
					Entidad agentea = agenteDAO.buscarPorId(idagente).getEntidad();
					String entidada = agentea.getNombreCompleto();
					
					String idproducto = cotizacion.getProducto().getId().toString();
					Producto productoa = productoDAO.buscarPorId(idproducto);
					String productof = productoa.getNombre();
					
					String idestado = cotizacion.getEstado().getId();
					Estado estadoa = estadoDAO.buscarPorId(idestado);
					String estadof = estadoa.getNombre();
					
					cotizacionJSONObject.put("ccliente", entidadc);
					cotizacionJSONObject.put("cagente",entidada);
					cotizacionJSONObject.put("cproducto",productof);
					cotizacionJSONObject.put("cestado",estadof);
					cotizacionJSONObject.put("csuma",cotizacion.getSumaAseguradaTotal());
					cotizacionJSONArray.add(cotizacionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoHistorialVentas", cotizacionJSONArray);
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


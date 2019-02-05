package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoGrupoDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.ProductoGrupo;
import com.qbe.cotizador.transaction.cotizacion.ProductoTransaction;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet("/ProductoController")
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
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
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String nemotecnico = request.getParameter("nemotecnico") == null ? "" : request.getParameter("nemotecnico");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String estadoFiltro = request.getParameter("estadoFiltro") == null ? "" : request.getParameter("estadoFiltro");
			JSONObject productoJSONObject = new JSONObject();
			JSONArray productoJSONArray = new JSONArray();

			Producto producto = new Producto();
			ProductoDAO productoDAO = new ProductoDAO();
			
			ProductoTransaction productoTransaction = new ProductoTransaction(); 
					
			if (!codigo.equals(""))
				producto.setId(codigo);

			if(!nombre.equals(""))
				producto.setNombre(nombre);
			
			if(!nemotecnico.equals("")){
				producto.setNemotecnico(nemotecnico);
				}
			if (activo.equals("1"))
				producto.setActivo(true);
            else
            	producto.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				
				boolean estado;
				if(estadoFiltro.equals("1"))
				{
					estado = true;
				}
				else
				{
					estado = false;
				}
				List<Producto> results = productoDAO.buscarActivos(estado);
				ProductoGrupoDAO pgDAO = new ProductoGrupoDAO();
				ProductoGrupo pg = new ProductoGrupo();
				int i=0;
				for(i=0; i<results.size(); i++){
					producto = results.get(i);
					
					//pg=pgDAO.buscarPorId(producto.getGrupoProductoId().toString());
					productoJSONObject.put("codigo", producto.getId());
					productoJSONObject.put("producto_grupo",pg.getNombre() == null ? "" : pg.getNombre());
					productoJSONObject.put("nombre", producto.getNombre() == null ? "" : producto.getNombre());
					productoJSONObject.put("nemotecnico", producto.getNemotecnico() == null ? "" : producto.getNemotecnico());
					productoJSONObject.put("defecto", producto.getDefecto() );
					productoJSONObject.put("dinamico", producto.getDinamico() );
					RamoDAO ramoDAO=new RamoDAO();
					if(producto.getRamoId()!=null)
						productoJSONObject.put("ramo", ramoDAO.buscarPorId(producto.getRamoId().toString()).getNombre() );
					else
						productoJSONObject.put("ramo", "");
					
					if(producto.getActivo())
						productoJSONObject.put("activo", "Si");
					else
						productoJSONObject.put("activo", "No");
					
					
					productoJSONArray.add(productoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoProducto", productoJSONArray);
				
				//Se agregan los ProductosGrupo para cargarlos en un comboBox de donde escoja el usuario evaldez
				List<ProductoGrupo> resultsPG = pgDAO.buscarTodos();
				JSONObject productoGrupoJSONObject = new JSONObject();
				JSONArray productoGrupoJSONArray = new JSONArray();
				int j=0;
				for(j=0; j<resultsPG.size(); j++){
					pg=resultsPG.get(j);
					productoGrupoJSONObject.put("codigo", pg.getId());
					productoGrupoJSONObject.put("nombre", pg.getNombre());
					
					productoGrupoJSONArray.add(productoGrupoJSONObject);
				}
				result.put("listadoProductoGrupo", productoGrupoJSONArray);
				
				
			}
			
			if(tipoConsulta.equals("buscarPorNombreNemotecnico")){
				List<Producto> listaProductos = productoDAO.buscarProductosPorNemotecnico(nemotecnico);
								
				for(int i=0; i<listaProductos.size(); i++){
					producto = listaProductos.get(i);
					if(producto.getActivo()){
						productoJSONObject.put("codigo", producto.getId());
						productoJSONObject.put("nombre", producto.getNombre());
						productoJSONObject.put("nombreNemotecnico", producto.getNemotecnico());
						productoJSONArray.add(productoJSONObject);						
					}
				}
				result.put("listaProductos", productoJSONArray);
			}
			
			if(tipoConsulta.equals("buscarGrupoCoberturasPorProducto")){				
//				JSONObject grupoCoberturasJSON = new JSONObject();
//				JSONArray  grupoCoberturasJSONArray = new JSONArray();
//				
//				CoberturaDAO coberturaDAO = new CoberturaDAO();
//				Cobertura cobertura = new Cobertura();
//				new Cobertura();
//				
//				if(!codigo.equals(""))
//					producto = productoDAO.buscarPorId(codigo);
//				
//				List<Cobertura> listaCoberturasPorProducto = producto.getGrupoPorProductos().getCoberturas();
//				
//				JSONObject coberturasJSON = new JSONObject();
//				JSONArray  coberturasJSONArray = new JSONArray();
//				
//				if(listaCoberturasPorProducto != null)
//					for(int i=0; i < listaCoberturasPorProducto.size(); i++){
//						cobertura = listaCoberturasPorProducto.get(i);
//						if(cobertura.getDesgloseCoberturas().size() > 0){
//							List<DesgloseCobertura> listaDesgloseCoberturas = cobertura.getDesgloseCoberturas();
//							for(int j=0; j<listaDesgloseCoberturas.size(); j++){
//								DesgloseCobertura desgloceCobertura = listaDesgloseCoberturas.get(j);
//								coberturasJSON.put("codigo", desgloceCobertura.getCobertura().getId());
//								coberturasJSON.put("nombre", desgloceCobertura.getNombre().toLowerCase());
//								coberturasJSON.put("nemotecnico", cobertura.getNemotecnico());
//								coberturasJSON.put("esPrincipal", cobertura.getEsPrincipal());
//								coberturasJSON.put("esPrimaFija", cobertura.getEsPrimaFija());
//								coberturasJSON.put("codigoGrupoCobertura", cobertura.getGrupoCobertura().getId());			
//								
//								//if(!coberturasJSONArray.contains(coberturasJSON))
//									coberturasJSONArray.add(coberturasJSON);
//							}
//						}	
//						
//						String grupoCoberturaId = cobertura.getGrupoCobertura().getId().toString();
//						GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
//						
//						List<Cobertura> listaCoberturasPorGrupoCobertura = coberturaDAO.buscarPorGrupoCobertura(grupoCoberturaDAO.buscarPorId(grupoCoberturaId));
//						if(listaCoberturasPorGrupoCobertura.size() > 0)
//							for(int j=0; j<listaCoberturasPorGrupoCobertura.size(); j++){
//								cobertura = listaCoberturasPorGrupoCobertura.get(j);
//								if(cobertura.getMostrarCotizador()){
//									grupoCoberturasJSON.put("codigoGrupoCobertura", cobertura.getGrupoCobertura().getId());
//									grupoCoberturasJSON.put("nombreGrupoCobertura", cobertura.getGrupoCobertura().getNombre());			
//									
//									if(!grupoCoberturasJSONArray.contains(grupoCoberturasJSON))
//										grupoCoberturasJSONArray.add(grupoCoberturasJSON);
//									
//									coberturasJSON.put("codigo", cobertura.getId());
//									coberturasJSON.put("nombre", cobertura.getNombre().toLowerCase());
//									coberturasJSON.put("nemotecnico", cobertura.getNemotecnico());
//									coberturasJSON.put("esPrincipal", cobertura.getEsPrincipal());
//									coberturasJSON.put("esPrimaFija", cobertura.getEsPrimaFija());
//									coberturasJSON.put("codigoGrupoCobertura", cobertura.getGrupoCobertura().getId());
//									
//									if(!coberturasJSONArray.contains(coberturasJSON))
//										coberturasJSONArray.add(coberturasJSON);
//									
//								}	
//							}
//					}
//					result.put("listadoCobertura", coberturasJSONArray);
//					result.put("listadoGrupoCobertura", grupoCoberturasJSONArray);
			}		
			
	
			
			if(tipoConsulta.equals("crear"))
				productoTransaction.crear(producto);

			if(tipoConsulta.equals("actualizar"))
				productoTransaction.editar(producto);
			if(tipoConsulta.equals("cambioEstado"))
			{
				Producto p = productoDAO.buscarPorId(producto.getId());
				p.setActivo(producto.getActivo());
				productoTransaction.editar(p);
			}

			if(tipoConsulta.equals("eliminar"))
				productoTransaction.eliminar(producto);
			
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

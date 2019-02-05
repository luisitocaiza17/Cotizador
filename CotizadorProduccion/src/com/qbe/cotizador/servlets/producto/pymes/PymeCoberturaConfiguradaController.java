package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.entidad.ConfiguracionProductoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturasConfiguracionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeGrupoproductoProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCoberturasConfiguracion;import com.qbe.cotizador.model.PymeGrupoproductoProducto;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeCoberturaConfiguradaController
 */
@WebServlet("/PymeCoberturaConfiguradaController")
public class PymeCoberturaConfiguradaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeCoberturaConfiguradaController() {
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
		String observacion="";
		try {
			
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			if(tipoConsulta.equals("cargarCoberturasTodas")){
				
				String grupoProductoId = request.getParameter("grupoProductoId") == null ? "" : request.getParameter("grupoProductoId");
								
				/**TODO: REALIZAMOS LOS SIGUIENTES PASOS:
				 * 1) HALLAMAOS EL GRUPO POR PRODUCTO
				 * 2) HALLAMOS EL RAMO
				 * 3) HALLAMOS EL PRODUCTO POR RAMO
				 * 4) HALLAMOS LA CONFIGURACION DEL PRODUCTO
				 * 5) HALLAMOS LOS ID DE LAS COBERTURAS
				 * 6) DEVOLVEMOS EL LISTADO DE COBERTURAS
				 * **/
				
				/**PROGRAMA DE SEGURO**/
				JSONObject CoberturaProgramaJSONObject = new JSONObject();
				JSONArray CoberturaProgramaJSONArray = new JSONArray();
				JSONObject CoberturaMultiJSONObject = new JSONObject();
				JSONArray CoberturaMultiJSONArray = new JSONArray();
				
				/**TODO: REALIZAMOS LOS SIGUIENTES PASOS:
				 * 1) HALLAMAOS EL GRUPO POR PRODUCTO
				 * 2) HALLAMOS EL RAMO
				 * 3) HALLAMOS EL PRODUCTO POR RAMO
				 * 4) HALLAMOS LA CONFIGURACION DEL PRODUCTO
				 * 5) HALLAMOS LOS ID DE LAS COBERTURAS
				 * 6) DEVOLVEMOS EL LISTADO DE COBERTURAS
				 * **/
				
				/**PROGRAMA DE SEGURO**/
				try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					// 2) hallamos el ramo
					/*PymeCoberturaDAO pymeCoberturaDAO= new PymeCoberturaDAO();
					PymeCobertura pymeCobertura= pymeCoberturaDAO.buscarPorId(results.getCoberturaPymesId());
					GrupoCoberturaDAO grupoCoberturaDAO= new GrupoCoberturaDAO();
					GrupoCobertura grupoCobertura=grupoCoberturaDAO.buscarPorId(pymeCobertura.getGrupoCoberturaId().toString());*/
					
					// 3) hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					//Buscamos los productos por ramo
					/*Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado=grupoCobertura.getRamo().getId();
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre();				
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre());
					}*/
					
					
					// 4) hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					// 5) hallamos los ids de las coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					*/
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					
					//asigno al objeto de respuesta					
									
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaProgramaJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaProgramaJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaProgramaJSONArray.add(CoberturaProgramaJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				try{
					/**MULTIRIESGO**/
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					//Se envia por defecto ramo multiriesgo
					
					// hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					/*
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="1516276756602";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo";	
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo");
					}*/
										
					//hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdMulti, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					//tenemos el listado de coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion>pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				
				/*try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					// 2) hallamos el ramo
					PymeCoberturaDAO pymeCoberturaDAO= new PymeCoberturaDAO();
					PymeCobertura pymeCobertura= pymeCoberturaDAO.buscarPorId(new BigInteger(coberturaId));
					GrupoCoberturaDAO grupoCoberturaDAO= new GrupoCoberturaDAO();
					GrupoCobertura grupoCobertura=grupoCoberturaDAO.buscarPorId(pymeCobertura.getGrupoCoberturaId().toString());
					// 3) hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado=grupoCobertura.getRamo().getId();
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}
					
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre();				
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre());
					}
					
					// 4) hallamos la configuracion del producto
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.getNombre();
					// 5) hallamos los ids de las coberturas
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					//asigno al objeto de respuesta					
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdPrograma, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaProgramaJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaProgramaJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+")");
						CoberturaProgramaJSONArray.add(CoberturaProgramaJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				try{
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					
					// hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="1516276756602";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}
					
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo";
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo");
					}
					//hallamos la configuracion del producto
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdMulti, No existe ConfiguracionProducto para el producto "+producto.getNombre();
					//tenemos el listado de coberturas
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+")");
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
									
				}catch (Exception e) {
					System.out.println("ID de Multiriesgo no pudieron ser cargados");
					e.printStackTrace();
				}*/
				result.put("ListaCoberturasPrograma", CoberturaProgramaJSONArray);
				result.put("ListaCoberturasMulti", CoberturaMultiJSONArray);
			}
			
			if(tipoConsulta.equals("cargarCoberturasAsistenciasTodas")){
				
				String grupoProductoId = request.getParameter("grupoProductoId") == null ? "" : request.getParameter("grupoProductoId");
								
				/**TODO: REALIZAMOS LOS SIGUIENTES PASOS:
				 * 1) HALLAMAOS EL GRUPO POR PRODUCTO
				 * 2) HALLAMOS EL RAMO
				 * 3) HALLAMOS EL PRODUCTO POR RAMO
				 * 4) HALLAMOS LA CONFIGURACION DEL PRODUCTO
				 * 5) HALLAMOS LOS ID DE LAS COBERTURAS
				 * 6) DEVOLVEMOS EL LISTADO DE COBERTURAS
				 * **/
				
				/**PROGRAMA DE SEGURO**/
				JSONObject CoberturaProgramaJSONObject = new JSONObject();
				JSONArray CoberturaProgramaJSONArray = new JSONArray();
				JSONObject CoberturaMultiJSONObject = new JSONObject();
				JSONArray CoberturaMultiJSONArray = new JSONArray();
				/*try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					// 2) hallamos el ramo
					// 3) hallamos el producto en base al nombre y al ramo
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="7208961";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}
					
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Incendio";	
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Incendio");
					}
					
					// 4) hallamos la configuracion del producto
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.getNombre();
					// 5) hallamos los ids de las coberturas
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					//asigno al objeto de respuesta					
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdPrograma, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaProgramaJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaProgramaJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+")");
						CoberturaProgramaJSONArray.add(CoberturaProgramaJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				try{
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					*/
					/**MULTIRIESGO**/
					//Se envia por defecto ramo multiriesgo
					// 3) hallamos el producto en base al nombre y al ramo
					/*PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="1516276756602";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}
					
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo";
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo");
					}
								
					//hallamos la configuracion del producto
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdMulti, No existe ConfiguracionProducto para el producto "+producto.getNombre();
					//tenemos el listado de coberturas
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+")");
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
								
				}catch (Exception e) {
					System.out.println("ID de Multiriesgo no pudieron ser cargados");
					e.printStackTrace();
				}
				result.put("ListaCoberturasPrograma", CoberturaProgramaJSONArray);
				result.put("ListaCoberturasMulti", CoberturaMultiJSONArray);*/	
				
				/**TODO: REALIZAMOS LOS SIGUIENTES PASOS:
				 * 1) HALLAMAOS EL GRUPO POR PRODUCTO
				 * 2) HALLAMOS EL RAMO
				 * 3) HALLAMOS EL PRODUCTO POR RAMO
				 * 4) HALLAMOS LA CONFIGURACION DEL PRODUCTO
				 * 5) HALLAMOS LOS ID DE LAS COBERTURAS
				 * 6) DEVOLVEMOS EL LISTADO DE COBERTURAS
				 * **/
				
				/**PROGRAMA DE SEGURO**/
				try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					// 2) hallamos el ramo
					/*PymeCoberturaDAO pymeCoberturaDAO= new PymeCoberturaDAO();
					PymeCobertura pymeCobertura= pymeCoberturaDAO.buscarPorId(results.getCoberturaPymesId());
					GrupoCoberturaDAO grupoCoberturaDAO= new GrupoCoberturaDAO();
					GrupoCobertura grupoCobertura=grupoCoberturaDAO.buscarPorId(pymeCobertura.getGrupoCoberturaId().toString());*/
					
					// 3) hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					//Buscamos los productos por ramo
					/*Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado=grupoCobertura.getRamo().getId();
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre();				
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre());
					}*/
					
					
					// 4) hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					// 5) hallamos los ids de las coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					*/
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					
					//asigno al objeto de respuesta					
									
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaProgramaJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaProgramaJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaProgramaJSONArray.add(CoberturaProgramaJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				try{
					/**MULTIRIESGO**/
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(grupoProductoId);
					//Se envia por defecto ramo multiriesgo
					
					// hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					/*
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="1516276756602";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo";	
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo");
					}*/
										
					//hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdMulti, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					//tenemos el listado de coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion>pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				result.put("ListaCoberturasPrograma", CoberturaProgramaJSONArray);
				result.put("ListaCoberturasMulti", CoberturaMultiJSONArray);
				
			}
			
			result.put("observacion",observacion);	
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

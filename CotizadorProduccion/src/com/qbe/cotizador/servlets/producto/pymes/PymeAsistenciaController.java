package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
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
import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturasConfiguracionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeGrupoproductoProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCoberturasConfiguracion;
import com.qbe.cotizador.model.PymeGrupoproductoProducto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.producto.pymes.PymeAsistenciaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeAsistenciaController
 */
@WebServlet("/PymeAsistenciaController")
public class PymeAsistenciaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeAsistenciaController() {
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
			String asistenciaId = request.getParameter("asistenciaId") == null ? "" : request.getParameter("asistenciaId");
			String esPredeterminada = request.getParameter("esPredeterminada") == null ? "" : request.getParameter("esPredeterminada");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
			String coberturaEnsMultiId = request.getParameter("coberturaEnsMultiId") == null ? "" : request.getParameter("coberturaEnsMultiId");
			String coberturaEnsProgrId = request.getParameter("coberturaEnsProgrId") == null ? "" : request.getParameter("coberturaEnsProgrId");
			String texto = request.getParameter("texto") == null ? "" : request.getParameter("texto");
			
			
			JSONObject asistenciaJSONObject = new JSONObject();
			JSONArray asistenciaJSONArray = new JSONArray();
			
			PymeAsistencia pymeAsistencia = new PymeAsistencia();
			PymeAsistenciaDAO pymeAsistenciaDAO = new PymeAsistenciaDAO();
			PymeAsistenciaTransaction pymeAsistenciaTransaction=new PymeAsistenciaTransaction();
			
			JSONObject grupoPPJSONObject = new JSONObject();
			JSONArray grupoPPJSONArray = new JSONArray();
			Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
			
			if(!asistenciaId.equals(""))
				pymeAsistencia.setAsistenciaId(new BigInteger(asistenciaId));
			if(!esPredeterminada.equals("") && esPredeterminada!=null)
				pymeAsistencia.setEsPredeterminada(Boolean.parseBoolean(esPredeterminada));
			if(!grupoPorProductoId.equals("") && grupoPorProductoId!=null)
				pymeAsistencia.setGrupoPorProductoId(new BigInteger( grupoPorProductoId));
			if(!nombre.equals("") && nombre!=null)
				pymeAsistencia.setNombre(nombre);
			if(!valor.equals("") && valor!=null)
				pymeAsistencia.setValor(Double.parseDouble(valor));
			if(coberturaEnsMultiId != null && !coberturaEnsMultiId.equals(""))
				pymeAsistencia.setCoberturaEnsMultiId(coberturaEnsMultiId);
			
			if(!texto.equals(""))
				pymeAsistencia.setTexto(texto.getBytes(Charset.forName("UTF-8")));
			
 			if(coberturaEnsProgrId != null && !coberturaEnsProgrId.equals(""))
 				pymeAsistencia.setCoberturaEnsProgrId(coberturaEnsProgrId);
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeAsistencia> results = pymeAsistenciaDAO.buscarTodos();
				
				for(PymeAsistencia asistencia : results){
					asistenciaJSONObject.put("asistenciaId", asistencia.getAsistenciaId());
					if(asistencia.getEsPredeterminada())
						asistenciaJSONObject.put("esPredeterminada", "Si");
					else
						asistenciaJSONObject.put("esPredeterminada", "No");
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(asistencia.getGrupoPorProductoId().toString());
					asistenciaJSONObject.put("grupoPorProductoId", grupoPorProducto.getNombreComercialProducto());
					asistenciaJSONObject.put("nombre", asistencia.getNombre());
					asistenciaJSONObject.put("valor", asistencia.getValor());
					asistenciaJSONObject.put("coberturaEnsMultiId", asistencia.getCoberturaEnsMultiId());
					asistenciaJSONObject.put("coberturaEnsProgrId", asistencia.getCoberturaEnsProgrId());
					asistenciaJSONArray.add(asistenciaJSONObject);
				}
				result.put("asistenciaJSONArray", asistenciaJSONArray);
				
				GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
				GrupoProducto grupoProducto = grupoProductoDAO.buscarPorNombre("PYMES");
				
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				List<GrupoPorProducto> grupoPorProducto = grupoPorProductoDAO.buscarTodosPorGrupo(grupoProducto);
				for(GrupoPorProducto grupoList : grupoPorProducto){
					grupoPPJSONObject.put("grupoPPId", grupoList.getId());
					grupoPPJSONObject.put("grupoPPNombre", grupoList.getNombreComercialProducto());
					grupoPPJSONArray.add(grupoPPJSONObject);
				}
				result.put("grupoPPJSONArray", grupoPPJSONArray);
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				String observacion="";
				PymeAsistencia results = pymeAsistenciaDAO.buscarPorId(new BigInteger(asistenciaId));
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
				/*try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
					
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
							String ramoBuscado="7208961";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}
					
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo INCENDIO ";	
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo INCENDIO ");
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
				*/
				/*try{
					/**MULTIRIESGO**/
					/*GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
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
					List<PymeCoberturasConfiguracion>pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();
					
					
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+")");
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}*/
				

				/**PROGRAMA DE SEGURO**/
				try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
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
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
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
				
				
				result.put("observacion",observacion);	
				result.put("ListaCoberturasPrograma", CoberturaProgramaJSONArray);
				result.put("ListaCoberturasMulti", CoberturaMultiJSONArray);	
				result.put("asistenciaId", results.getAsistenciaId());
				result.put("esPredeterminada", results.getEsPredeterminada());
				result.put("grupoPorProductoId", results.getGrupoPorProductoId());
				result.put("nombre", results.getNombre());
				result.put("valor", results.getValor());
				result.put("coberturaEnsMultiId", results.getCoberturaEnsMultiId());
				result.put("coberturaEnsProgrId", results.getCoberturaEnsProgrId());
				result.put("texto", new String(results.getTexto(), "UTF-8"));
			}
			
			if(tipoConsulta.equals("buscarPorProductoId")){
				List<PymeAsistencia> results = pymeAsistenciaDAO.buscarGrupoPorProductoId(new BigInteger(grupoPorProductoId));
				
				for(PymeAsistencia asistencia:results){
					asistenciaJSONObject.put("asistenciaId", asistencia.getAsistenciaId());
					asistenciaJSONObject.put("esPredeterminada", asistencia.getEsPredeterminada());
					asistenciaJSONObject.put("grupoPorProductoId", asistencia.getGrupoPorProductoId());
					asistenciaJSONObject.put("nombre", asistencia.getNombre());
					asistenciaJSONObject.put("valor", asistencia.getValor());
					asistenciaJSONObject.put("coberturaEnsMultiId", asistencia.getCoberturaEnsMultiId());
					asistenciaJSONObject.put("coberturaEnsProgrId", asistencia.getCoberturaEnsProgrId());
					asistenciaJSONArray.add(asistenciaJSONObject);
				}
				result.put("asistenciaJSONArray", asistenciaJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				pymeAsistenciaTransaction.crear(pymeAsistencia);
						
			if(tipoConsulta.equals("editar"))
				pymeAsistenciaTransaction.editar(pymeAsistencia);
			
			if(tipoConsulta.equals("eliminar")){
				pymeAsistencia=pymeAsistenciaDAO.buscarPorId(pymeAsistencia.getAsistenciaId());
				
				/***TRATAMIENTO DE ERROR***/
				Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
				String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
				/***AUDITORIA auditamos el error para el seguimiento***/
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setTramite(CodError);
				pymeAuditoriaGeneral.setEstado("ELIMINACION ASISTENCIA");
				pymeAuditoriaGeneral.setProceso("PYMES");
				pymeAuditoriaGeneral.setObjeto("Eliminacion de Asistencia: AsistenciaId="+pymeAsistencia.getAsistenciaId()+" GrupoPorProductoId="
				+pymeAsistencia.getGrupoPorProductoId() +" Nombre="+pymeAsistencia.getNombre()
				+" Realizado por usuario: "+usuario.getLogin()+" Nombre: "+usuario.getEntidad().getNombreCompleto());
				try {
					pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				pymeAsistenciaTransaction.eliminar(pymeAsistencia);
				
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

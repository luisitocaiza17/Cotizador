package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMaxDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizacionMax;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriAuditoriaCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.exceptionMappingType;

/**
 * Servlet implementation class AgriCredifePacController
 */
@WebServlet("/AgriCredifePacController")
public class AgriCredifePacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCredifePacController() {
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
				JSONObject result = new JSONObject();
				try {
					String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
					String nombreArchivo = request.getParameter("nombreArchivo") == null ? "":request.getParameter("nombreArchivo");
					
					
					//primero cargamos el archivo de excel y verificamos que este en el formato .excel
					if(tipoConsulta.equals("")){
						DiskFileItemFactory factory = new DiskFileItemFactory();
						ServletFileUpload upload = new ServletFileUpload(factory);
						try {
							List<FileItem> items = upload.parseRequest(request);
							for (FileItem item : items) {
								if (!item.isFormField()) {							
									String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
											.getLocation().getPath();
									rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
									String rutaRelativaReporte=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano";
									System.out.println("rutaRelativaReporte " + rutaRelativaReporte);
									rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
									
									String fileName = item.getName();
									try {
										File saveFile = new File(rutaPlantilla,fileName);
										saveFile.createNewFile();
										item.write(saveFile);								
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						} catch (FileUploadException e) {
							try {
								throw new ServletException("Cannot parse multipart request.", e);
							} catch (ServletException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}   
					}
					
					//importamos las cotizaciones que se encuentran en el archivo excel
					if(tipoConsulta.equals("importarKendo")){
						int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
						int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

						String rutaPlantilla = this.getServletContext().getRealPath("") + ""+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano"+File.separator+""+nombreArchivo;
						System.out.println("rutaRelativaReporte almacenada: " + rutaPlantilla);
						List<CotizacionArchivoPlano> cotizacionList = new ArrayList<CotizacionArchivoPlano>();
						
						
						if(nombreArchivo.endsWith(".xlsx")){
							cotizacionList = ReadExelFile.readXLSXFileAgriPac(rutaPlantilla);		
						}else if(nombreArchivo.endsWith(".xls")){
							cotizacionList = ReadExelFile.readXLSXFileAgriPac(rutaPlantilla);					 
						}
						
						HttpSession session = request.getSession(true);
						JSONArray listCotError = procesarCotizacionesCargaPrevia(cotizacionList,session);
						
						long total =listCotError.size();
						DataSourceResult pg = new DataSourceResult();
						pg.setTotal((int)total);
						pg.setData(listCotError);
						
						Gson gson = new Gson();
						// convert the DataSourceReslt to JSON and write it to the response
						response.setContentType("application/json; charset=ISO-8859-1");
					    response.getWriter().print(gson.toJson(pg));
					    return;
						
					}
					
					
					
					
				}catch (Exception e) {
					result.put("success", Boolean.FALSE);
					result.put("mensaje", e.getLocalizedMessage());
					response.setContentType("application/json; charset=ISO-8859-1");
					result.write(response.getWriter());
					e.printStackTrace();
				}  
				
			}
			
			//Proceso de cotizaci�n Credife
			public static JSONArray procesarCotizacionesCargaPrevia(
					List<CotizacionArchivoPlano> listado, HttpSession session) {
				
				JSONObject error = new JSONObject();
				JSONArray errorList = new JSONArray();				
				JSONObject correctas = new JSONObject();	
				try {

					for (CotizacionArchivoPlano nuevaCotizacion : listado) {
						//AUDITORIA DE DATOS INGRESADOS
						AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
						AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
						auditoria.setTramite("" + nuevaCotizacion.getClienteIdentificacion());
						String DatosRecibidos = " ARCHIVO PLANO GENERA: Canal: "+nuevaCotizacion.getCanal()
								+" Canton: "+nuevaCotizacion.getCantonNombre()+" ClienteApellido: "+nuevaCotizacion.getClienteApellido()
								+" ClienteIdentificacion: " +nuevaCotizacion.getClienteIdentificacion()+ " Nombre: "+nuevaCotizacion.getClienteNombre()
								+" GpsX: "+nuevaCotizacion.getGpsX()+" GpsY: "+nuevaCotizacion.getGpsY()+ " Parroquia "+nuevaCotizacion.getParroquiaNombre()
								+" Provincia: "+nuevaCotizacion.getProvinciaNombre()+" Telefono: "+nuevaCotizacion.getTelefono()+" TipoCultivoNombre: "+nuevaCotizacion.getTipoCultivoNombre()
								+" UbicacionCultivo: "+nuevaCotizacion.getUbicacionCultivo()+" MontoAsegurado: "+nuevaCotizacion.getMontoAsegurado()
								+" NumeroHectareasAseguradas: "+nuevaCotizacion.getNumeroHectareasAseguradas()+" NumeroHectareasLote "+nuevaCotizacion.getNumeroHectareasLote()
								+" EsTecnificado: "+nuevaCotizacion.getEsTecnificado()+" FechaSiembra: "+nuevaCotizacion.getFechaSiembra()+" FechaSolicitud: "+nuevaCotizacion.getFechaSolicitud();
						
						auditoria.setObjeto(DatosRecibidos);
						java.util.Date date2 = new java.util.Date();
						java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
						auditoria.setFecha(sq2);
						auditoria.setCanal("EXCEL G");
						auditoria.setEstado("Ingreso");
						try {
							procesoAuditoria.crear(auditoria);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							/* PROCESO DE INSTANCIAS CREADO */
							EntidadDAO entidadDAO = new EntidadDAO();
							ClienteDAO clienteDAO = new ClienteDAO();
							TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
							PuntoVentaDAO pvDAO = new PuntoVentaDAO();
							VigenciaPolizaDAO vpDAO = new VigenciaPolizaDAO();
							GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
							ProductoXPuntoVentaDAO productoPorPuntoVentaDAO = new ProductoXPuntoVentaDAO();
							TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
							UsuarioDAO usuarioDAO = new UsuarioDAO();
							EstadoDAO estadoDAO = new EstadoDAO();
							TipoIdentificacionDAO tipoIDentificacion = new TipoIdentificacionDAO();
							CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
							CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
							EntidadTransaction entidadTransaction = new EntidadTransaction();
							ClienteTransaction clienteTransaction = new ClienteTransaction();
							AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
							Entidad entidad = new Entidad();
							Cliente cliente = new Cliente();
							Cotizacion cotizacion = new Cotizacion();							
							
							String entidadId = "";
							String numeroCedula = nuevaCotizacion.getClienteIdentificacion().trim();
							if(numeroCedula==null||numeroCedula.equals("")||numeroCedula.equals("NULL"))
									throw new Exception(" El n�mero de c�dula no puede estar vac�o ");							
							//verificamos si la entidad existe para actualizarla � crearla
							try {
								entidad = entidadDAO
										.buscarEntidadPorIdentificacion(numeroCedula);
								entidadId = entidad.getId();
							} catch (Exception e) {
								entidadId = "";
							}
							
							Date dateSiembra=null;
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formato de fecha de siembra
							
							/**Validacion de fechas**/
							
							try{
								dateSiembra=nuevaCotizacion.getFechaSiembra();
							}catch(Exception e){
								throw new Exception(
										"Error la fecha de siembra "+nuevaCotizacion.getFechaSiembra()+ " no esta en el formato dd/MM/yyyy");
							}
							
							Date fechaControlInicio=null;
							Date fechaControlFin=null;
							fechaControlInicio=formatter.parse("01/01/2015");
							fechaControlFin=formatter.parse("01/01/2019");
							if(dateSiembra.after(fechaControlInicio) && dateSiembra.before(fechaControlFin)){
								System.out.print("fechas correctas");
							}
							else{
								throw new Exception(
										"Error la fecha de siembra "+nuevaCotizacion.getFechaSiembra()+ " no esta dentro del rango de fechas permitidas");
							}
							
							/* PROCESO DE CREACION DE LA ENTIDAD */
							String nombre = "";
							String apellidos = "";
							entidad.setIdentificacion(numeroCedula);
							
							TipoIdentificacion identificacion = new TipoIdentificacion();
							
							if(numeroCedula.length()>13)
								 identificacion = tipoIDentificacion.buscarPorId("2");
							else{
								if(numeroCedula.length()==13){
									identificacion = tipoIDentificacion.buscarPorId("3");
									String caracterNumerico=""+numeroCedula.charAt(2);
									if(caracterNumerico.equals("9")){
										identificacion = tipoIDentificacion.buscarPorId("4");
									}
								}
								else{
									if(numeroCedula.length()==10){
										identificacion = tipoIDentificacion.buscarPorId("1");
									}else{
										if(numeroCedula.length()<10)
											numeroCedula="0"+numeroCedula;
										if(numeroCedula.length()<10)
											throw new Exception("Identificacion Invalida");	
										else
											identificacion = tipoIDentificacion.buscarPorId("1");
										
									}
								}
							}
							entidad.setTipoIdentificacion(identificacion);
							entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
							nombre = nuevaCotizacion.getClienteNombre().trim();
							apellidos = " " + nuevaCotizacion.getClienteApellido().trim();
							entidad.setApellidos(apellidos);
							entidad.setNombres(nombre);
							entidad.setNombreCompleto(nombre + " " + apellidos);
							entidad.setCelular("" + nuevaCotizacion.getTelefono().trim());
							
							if (entidadId == null || entidadId.equals("")) {
								entidad = entidadTransaction.crear(entidad);
							}else{
								entidad.setId(entidadId);
								entidad = entidadTransaction.editar(entidad);
							}
							
							if(entidad.getId()==null)
								throw new Exception("No se pudo crear la entidad");
							
							/*PROCESO DE CREACION DEL CLIENTE */
							cliente = clienteDAO.buscarPorEntidadId(entidad);
							
							ActividadEconomica actividad = new ActividadEconomica();
							ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
							actividad = actividadDAO.buscarPorNombre("Ninguno");
							Cliente clienteNuevo = new Cliente();
							clienteNuevo.setEntidad(entidad);
							clienteNuevo.setActividadEconomica(actividad);
							clienteNuevo.setActivo(true);
							
							if (cliente.getEntidad() == null) {
								cliente = clienteTransaction.crear(clienteNuevo);
							}else{
								clienteNuevo.setId(cliente.getId());
								cliente = clienteTransaction.editar(clienteNuevo);
							}
							if(cliente.getId()==null)
								throw new Exception("No se pudo crear el cliente");
							
							/*CREACION DE LA DIRECCION*/
							
							Direccion direccion=new Direccion();
							DireccionDAO direccionDAO=new DireccionDAO();
							DireccionTransaction direccionTransaction = new DireccionTransaction();
							ProvinciaDAO laProvincia = new ProvinciaDAO();
							Provincia provincia = new Provincia();
							CantonDAO elCanton = new CantonDAO();
							Canton canton = new Canton();
							TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
							ZonaDAO zonaDAO = new ZonaDAO();
							AgriParroquiaDAO laParroquia = new AgriParroquiaDAO();
							List<AgriParroquia> parroquia = new ArrayList<AgriParroquia>();
							
							if(entidad.getId()!=null){
								int numeroDirecciones=direccionDAO.buscarCobroPorEntidadId(entidad).size();
								if(numeroDirecciones>0)
									direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(numeroDirecciones-1);
							}
							//hallamos la provincia en base al codigo que llega
							String nombreProvincia =nuevaCotizacion.getProvinciaNombre().replace("_", " ").trim();
												
							provincia = laProvincia.buscarPorNombre(nombreProvincia);
							
							if (provincia.getId() == null)
								throw new Exception("Error la Provincia "
										+ nombreProvincia + "  no encontrada");
							
							//hallamos el canton en base al codigo que llega
							String nombreCanton = nuevaCotizacion.getCantonNombre().replace("_", " ").trim();
							canton = elCanton.buscarPorCantonProvincia(nombreCanton,provincia);
							
							if (canton.getId() == null)
								throw new Exception("Error el Canton "
										+ nombreCanton + " no encontrado");
							
							//Proceso de busqueda de parroquias
							String nombreParroquia=nuevaCotizacion.getParroquiaNombre().replace("_", " ").trim();
							parroquia= laParroquia.BuscarPorCanton(canton.getId());
							String parroquiaId="";
							for(AgriParroquia p :parroquia){
								if(p.getParroquiaNombre().equalsIgnoreCase(nombreParroquia))
									parroquiaId=""+p.getId();
							}
							
							if(parroquiaId.equals("")){
								throw new Exception("Error la Parroquia "+ nombreParroquia + " no encontrada ");
							}
													
							if(nuevaCotizacion.getUbicacionCultivo()!=null&&!nuevaCotizacion.getUbicacionCultivo().trim().equals(""))
								direccion.setCallePrincipal(""+nuevaCotizacion.getUbicacionCultivo());
							else
								throw new Exception("Error la ubicaci�n de la siembra no existe ");
							
							direccion.setCalleSecundaria(""+nuevaCotizacion.getUbicacionCultivo());
							direccion.setNumero(""+nuevaCotizacion.getUbicacionCultivo());
							
							if(nuevaCotizacion.getGpsX()!=null&&!nuevaCotizacion.getGpsX().equals("")||nuevaCotizacion.getGpsY()!=null&&!nuevaCotizacion.getGpsY().equals(""))
								direccion.setDatosDeReferencia("Latitud:"+nuevaCotizacion.getGpsX()+" Longitud:"+nuevaCotizacion.getGpsY());
							
							direccion.setEsCobro(true);
							direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));
							
							///hallamos la direccion del cliente
							CiudadDAO ciudadDAO = new CiudadDAO();
							Ciudad ciu = ciudadDAO.buscarPorId(provincia.getCapitalId());
							direccion.setCiudad(ciu);

							direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
							if(entidad!=null&&!entidad.getId().equals(""))
								direccion.setEntidad(entidad);
							//se almacena direccion pero si existe algun problema permite continuar
							try{
								if(direccion.getId()==null)
									direccion=direccionTransaction.crear(direccion);
								else
									direccion=direccionTransaction.editar(direccion);
							}catch(Exception e){
								System.out.println("Problemas al almacenar direccion");
							}
							
							/*****PROCESO DE COTIZACION*****/
							
							AgriParametroXPuntoVenta PPuntoVenta = new AgriParametroXPuntoVenta();
							AgriParametroXPuntoVentaDAO PPuntoVentaProceso = new AgriParametroXPuntoVentaDAO();
							
							/**BUSCAMOS A QUE CANAL LE PERTENECE LA COTIZACION**/
							
							PuntoVentaDAO puntoVentaDAO= new PuntoVentaDAO();
							PuntoVenta puntoVenta= puntoVentaDAO.buscarPorNombre(nuevaCotizacion.getCanal().trim());
							
							if(puntoVenta.getId()==null)
								throw new Exception("El Canal "+nuevaCotizacion.getCanal().trim()+" No existe, no se puede continuar con el proceso de cotizaci�n");
								
							//buscamos el punto de venta en base al canal que llega
							PPuntoVenta = PPuntoVentaProceso.buscarPorPuntoVentaId(new BigInteger(puntoVenta.getId()));
							
							GrupoPorProducto grupo = new GrupoPorProducto();
							grupo = grupoPorProductoDAO.buscarPorNombre("Agricola");
							GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupo.getId());// AGRICOLA
							
							//verifico si existe subCanal para poder tomar la configuracion especifica del mismo
							ProductoXPuntoVenta pxpv = new ProductoXPuntoVenta();
							pxpv = productoPorPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, puntoVenta);
							if (pxpv.getId() == null)
								throw new Exception("Error Producto por Punto de Venta no configurado");
							cotizacion.setPuntoVenta(puntoVenta);
							cotizacion.setProductoXPuntoVentaId(new BigInteger(pxpv.getId()));
							cotizacion.setVigenciaPoliza(vpDAO.buscarPorId("1"));
							cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
							cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
							cotizacion.setProducto(grupoPorProducto.getProducto());
							cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("Agricola"));
							
							java.util.Date date = new java.util.Date();
							java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());
											
							cotizacion.setUsuario(usuarioDAO.buscarPorLogin("e63_1708971229"));
							cotizacion.setFechaElaboracion(sq);
							
							String nombreCultivo=nuevaCotizacion.getTipoCultivoNombre().trim();
							if(nombreCultivo.equals("")||nombreCultivo==null)
								throw new Exception("El tipo de cultivo no pudo se encontrado");
								
							//Obtengo el id del tipo de canal
							AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
							AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
							agriTipoCalculo=agriTipoCalculoDAO.BuscarPorId(PPuntoVenta.getTipoCalculoId());	
							
							//Buscamos los tipos de cultivos tomamos el tipo de cultivo y la fecha para encontrar el ciclo
							AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
							AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorNombre(nombreCultivo);
							
							//encontramos el codigo del tipo de cultivo
							Double nuestroCosto = 0.0;//para verificar si existen diferencias entre costo que llega y que tenemos
							String idTipoCalculo = "";//para saber en base a que regla se calcularon los datos
							String observacion="";
							Double tasa=0.0;
							
							AgriCicloDAO cicloDAO=new AgriCicloDAO();
							if (agriTipoCultivo.getTipoCultivoId()!=null) {
								
								AgriReglaDAO regla = new AgriReglaDAO();
								List<AgriRegla> reglas = regla.BuscarPorParametros(
										new BigInteger(provincia.getId()),new BigInteger(canton.getId()),agriTipoCultivo.getTipoCultivoId(),agriTipoCalculo.getTipoCalculoId());
								if(reglas.isEmpty()){
									throw new Exception(
											"no se encontro reglas para el tipo de cultivo"); 
								}							
								for (AgriRegla rs : reglas) {
									AgriCiclo ciclo = cicloDAO.BuscarPorId(rs.getClicloId());//tomamos el ciclo para verificar a cual pertenece
									Date fechafin=ciclo.getFechaFin();//fecha inicio ciclo
									Date fechaInicio=ciclo.getFechaInicio();//fecha fin ciclo
									//si todo esta correcto asignamos constos de produccion y tasa
									if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
										if(rs.getTasa()!=0 || rs.getTasa()!=0.0 ){//si tiene tasa debe tener o costo de produccion o costo de mantenimiento
											idTipoCalculo=rs.getReglaId().toString();
											tasa = Double.parseDouble(""+rs.getTasa());
											if(rs.getCostoProduccion()!=0){
												nuestroCosto = Double.parseDouble(""+rs.getCostoProduccion());
											}else{
												nuestroCosto = Double.parseDouble(""+rs.getCostoMantenimiento());
											}
											observacion = observacion+" " + rs.getObservaciones();									
										}								
																			
									}else{
										if(rs.getTasa()!=0 || rs.getTasa()!=0.0 ){//si tiene tasa debe tener o costo de produccion o costo de mantenimiento
											idTipoCalculo=rs.getReglaId().toString();
											tasa = Double.parseDouble(""+rs.getTasa());
											if(rs.getCostoProduccion()!=0){
												nuestroCosto = Double.parseDouble(""+rs.getCostoProduccion());
											}else{
												nuestroCosto = Double.parseDouble(""+rs.getCostoMantenimiento());
											}
											observacion = observacion+" " + rs.getObservaciones();									
										}	
									}
									
								}				
							}				
							else
								throw new Exception("Tipo de cultivo no encontrado"); 
							
							if(idTipoCalculo.equals(""))
								throw new Exception("Tipo de cultivo y reglas no encontrado"); 
							//tomamos los numeros de hectareas
							double NumeroHectareasL = 0.0;
							NumeroHectareasL =nuevaCotizacion.getNumeroHectareasAseguradas();
							if(NumeroHectareasL==0)
								throw new Exception("Se debe contar con n�mero de hectareas"); 
							
							/***COMENZAMOS CON LOS CALCULOS DE LOS VALORES***/
							double valorTotaL = 0.0;
							valorTotaL = nuestroCosto * NumeroHectareasL;
							BigDecimal T = new BigDecimal(""+valorTotaL);
							BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							valorTotaL=Double.parseDouble(""+roundOffT);
							
							String variableControl=auditoria.getObjeto();//variable de control
							if(valorTotaL!=nuevaCotizacion.getMontoAsegurado()){
								observacion=observacion+"sumas aseguradas diferentes: Suma QBE:"+valorTotaL+" Suma Archivo: "+nuevaCotizacion.getMontoAsegurado();
								throw new Exception("Los montos del credito no coinciden sumas aseguradas diferentes: Suma QBE:"+valorTotaL+" Suma Archivo: "+nuevaCotizacion.getMontoAsegurado());
							}
							
							double valorPrima=valorTotaL * tasa /100;
							
							variableControl=variableControl+"|| CALCULOS:  Tasa: "+tasa;	
							variableControl=variableControl+" NHect: "+NumeroHectareasL;
							variableControl=variableControl+" CostoP: "+nuestroCosto;
							variableControl=variableControl+" valorTotaL: "+valorTotaL;
							variableControl=variableControl+" valorPrima: "+valorPrima;
							
							BigDecimal a = new BigDecimal(""+valorPrima);
							BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
							variableControl=variableControl+" Prima: "+roundOff;				
							cotizacion.setPrimaNetaTotal(""+roundOff);//enviamos prma redondeada
							
							a=new BigDecimal(""+valorTotaL);
							roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
							cotizacion.setSumaAseguradaTotal(Double.parseDouble(""+roundOff));//enviamos la suma asegurada
							
							a=new BigDecimal(""+valorPrima);
							roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
							cotizacion.setPrimaOrigen(Double.parseDouble(""+roundOff));//enviamos prima origen
							
							double valorFinalPrima = Double.parseDouble(""+roundOff);
							/* PROCESO DE CALCULO DE COMPONENTES */
							TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
							TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
							VariableSistemaDAO variableDAO = new VariableSistemaDAO();
							List<VariableSistema> variablesistema = variableDAO
									.buscarTipoVariable(tipoVariable);
							
							
							double valorBase = 0;
							double valorDerechosEmision = 0;
							double valorSeguroCampesino = 0;
							double valorSuperBancos = 0;
							double valorIva = 0;
							double valorSubTotal = 0;
							if(variablesistema.size() > 0) {
					        	for(VariableSistema variable : variablesistema) {
					        		if(variable.getNombre().equals("DERECHOS_EMISION_AGRICOLA")){
					        			 valorDerechosEmision = Double.parseDouble(variable.getValor());
					                     cotizacion.setImpDerechoEmision(valorDerechosEmision);
					                     valorBase = Double.parseDouble(variable.getValor())+valorBase;
					        		}
					        		else if(variable.getNombre().equals("SEGURO_CAMPESINO")){
					        			valorSeguroCampesino = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
					                    valorBase = valorBase + valorSeguroCampesino;
					                    cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
					        		}
					        		else if(variable.getNombre().equals("SUPER_DE_BANCOS")){
					        			valorSuperBancos = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
					                    cotizacion.setImpSuperBancos(valorSuperBancos);
					                    valorBase = valorBase + valorSuperBancos;
					        		}	
					        		else if(variable.getNombre().equals("SUBTOTAL")){
					                    valorSubTotal = Math.rint(valorBase*100)/100;
					                }
					                else if(variable.getNombre().equals("IVA")){
					                	//Proces de exoneracion del iva del 14% LEY DE SOLIDARIDAD provincia ESMERANLDAS Y MANABI
					                		                    
					                }
					        		
					        	}
					        	
					        	VariableSistema variableIva= variableDAO.buscarPorNombre("IVA");					
								valorSubTotal=valorBase+valorFinalPrima;
								//calculamos el Iva
								String PorcentajeIva="";
								//exceocion temporal Manabi y esmeraldas
								if(provincia.getNombre().trim().toUpperCase().equals("MANABI")||provincia.getNombre().trim().toUpperCase().equals("ESMERALDAS")){
									 PorcentajeIva="12";
			                	}else{
			                		 PorcentajeIva=variableIva.getValor();
			                	}	
								
								valorIva = Math.rint(Double.parseDouble(PorcentajeIva) * valorSubTotal / 100 * 100) / 100;
								a = new BigDecimal(""+valorIva);
								roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
								cotizacion.setImpIva(Double.parseDouble(""+roundOff));
								//calculamos el valor total
								double valorFactura=valorSubTotal+valorIva;
								a = new BigDecimal(""+valorFactura);
								roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
								cotizacion.setTotalFactura(Double.parseDouble(""+roundOff));
								
					        }
							cotizacion.setAgenteId(new BigInteger(PPuntoVenta.getAgenteId()));
							cotizacion.setPorcentajeComision(Double.parseDouble(PPuntoVenta.getPorcentajeComision()));
							cotizacion.setTasaProductoValor(tasa);
							cotizacion.setEtapaWizard(3);
							cotizacion.setClienteId(new BigInteger(cliente.getId()));
							
							cotizacion.setEstado(estadoDAO.buscarPorNombre(	"Pendiente Aprobar", "Cotizacion"));
							cotizacion.setFechaEmision(sq);
							cotizacion.setVigenciaDesde(sq);
																	
							cotizacion.setAsegurado(entidad);
							cotizacion = cotizacionTransaction.crear(cotizacion);
							
							/****PROCESO DE CREACION DE AGRI_OBJETO_COTIZACION*****/
							
							// Inserta el detalle de la cotizaci�n
							
							AgriObjetoCotizacion agriObjetoCotizacion = new AgriObjetoCotizacion();
							agriObjetoCotizacion.setConfirEmiCanal(false);
							agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia.getId()));
							agriObjetoCotizacion.setCantonId(new BigInteger(canton.getId()));
							agriObjetoCotizacion.setCostoProduccionQBE(Float.parseFloat(""+nuestroCosto));
							agriObjetoCotizacion.setConfirEmiCanal(true);
							try{
								agriObjetoCotizacion.setAgriParroquiaId(""+parroquiaId);
							}catch(Exception e){
								//La parroquia no se encuentra en el catalogo.
							}
							
							agriObjetoCotizacion.setTipoCultivoId(agriTipoCultivo.getTipoCultivoId());
							agriObjetoCotizacion.setVariedad(nuevaCotizacion.getTipoCultivoNombre().trim());
							agriObjetoCotizacion.setDisponeRiego(false);
							agriObjetoCotizacion.setAgricultorTecnificado(nuevaCotizacion.getEsTecnificado());
							agriObjetoCotizacion.setDireccionSiembra(nuevaCotizacion.getUbicacionCultivo());
							agriObjetoCotizacion.setTipoSeguro(0);
							agriObjetoCotizacion.setHectareasLote(Float.parseFloat(""+nuevaCotizacion.getNumeroHectareasLote()));
							try{
								agriObjetoCotizacion.setLatitud(Float.parseFloat(""+nuevaCotizacion.getGpsX()));
							}catch(Exception e){
								agriObjetoCotizacion.setLatitud(0);
							}
							
							try{
								agriObjetoCotizacion.setLongitud(Float.parseFloat(""+nuevaCotizacion.getGpsY()));
							}catch(Exception e){
								agriObjetoCotizacion.setLongitud(0);
							}
							
							
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(""+nuevaCotizacion.getNumeroHectareasAseguradas()));
							try{
								agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+valorTotaL));
							}catch(Exception e){
								//error monto asegurado
							}
							
							agriObjetoCotizacion.setFechaSiembra(dateSiembra);					
							agriObjetoCotizacion.setTipoOrigen("AGRIPAC");
							if (!observacion.equals("")||!observacion.equals(" ")) {
								agriObjetoCotizacion.setObservacion(observacion);
							}
							agriObjetoCotizacion.setTipoCalculo(idTipoCalculo);
							agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+nuestroCosto));
							
							/***Proceso de creacion de las IdCotizaciones para facturacion***/
							
							AgriCotizacionMaxDAO busquedaMax = new AgriCotizacionMaxDAO();
							AgriCotizacionMax numMaximo=busquedaMax.buscarTodos();
							int numeroActual=numMaximo.getMaximo().intValue();
							
							agriObjetoCotizacion.setIdCotizacion2(new BigInteger(""+(numeroActual+1)));
							agriObjetoCotizacion = agriObjetoCotizacionTransaction.crear(agriObjetoCotizacion);
							
							/*** PROCESO DE CREACION DE LAS COTIZACIONES DETALLE ***/
							
							CotizacionDetalle nuevoCotizacionDetalle = new CotizacionDetalle();
							nuevoCotizacionDetalle.setCotizacion(cotizacion);
							nuevoCotizacionDetalle.setNecesitaInspeccion(false);
							nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO
									.buscarPorNombre("Agricola").getId());
							nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion
									.getObjetoCotizacionId().toString());
							nuevoCotizacionDetalle.setSumaAseguradaItem(cotizacion
									.getSumaAseguradaTotal());
							nuevoCotizacionDetalle.setPrimaNetaItem(Double
									.parseDouble(cotizacion.getPrimaNetaTotal()));
							nuevoCotizacionDetalle.setTasa(tasa);
							
							
							if(!observacion.equals("")||!observacion.equals(" ")){
								cotizacion.setEstado(estadoDAO.buscarPorNombre(	"Pendiente de Emitir", "Cotizacion"));
							}else{
								cotizacion.setEstado(estadoDAO.buscarPorNombre(	"Pendiente Aprobar", "Cotizacion"));
							}
							cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
							cotizacion.setNumeroTramite(cotizacion.getId());
							cotizacion = cotizacionTransaction.editar(cotizacion);
							
							
							String EstadoAuditoria = "" + cotizacion.getEstado().getNombre();
							if (EstadoAuditoria.equals("Pendiente de Emitir")){
								AgriAuditoriaCotizacion agriAuditoriaCotizacion = new AgriAuditoriaCotizacion();
								AgriAuditoriaCotizacionTransaction agriAuditoriaCotizacionTransaction = new AgriAuditoriaCotizacionTransaction();
								agriAuditoriaCotizacion.setCotizacionId(new BigInteger(cotizacion.getId()));
								agriAuditoriaCotizacion.setFecha(sq);
								agriAuditoriaCotizacion.setTipoActividad("APROBADO REVISION");
								agriAuditoriaCotizacion.setUsuarioId(new BigInteger("0"));
								agriAuditoriaCotizacionTransaction.crear(agriAuditoriaCotizacion);
							}
							try{
								/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
								AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
								agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacion, "Registro Solicitud");
								/**FIN DE REGISTRO DE ENDOSO**/
							}catch(Exception e){
								e.printStackTrace();
							}
							auditoria.setEstado(EstadoAuditoria);
							procesoAuditoria.editar(auditoria);					
							SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 					
							correctas.put("canal", nuevaCotizacion.getCanal());
							correctas.put("nombres", nuevaCotizacion.getClienteNombre());
							correctas.put("apellidos", nuevaCotizacion.getClienteApellido());
							correctas.put("cliente", nuevaCotizacion.getClienteNombre()+ " "+ nuevaCotizacion.getClienteApellido() );
							correctas.put("identificacion",	nuevaCotizacion.getClienteIdentificacion());
							correctas.put("montoAsegurado",	nuevaCotizacion.getMontoAsegurado());
							if (nuevaCotizacion.getFechaSolicitud() == null) {
								error.put("fechaSolicitud", "fecha no valida");
							} else {
								try{
									Date fecha= new Date (nuevaCotizacion
											.getFechaSolicitud().getTime());
									String fecha2= dt.format(fecha);							
									correctas.put("fechaSolicitud", fecha2);
								}
								catch(Exception ex){
									correctas.put("fechaSolicitud", "fecha no valida");
								}
							}
							if (nuevaCotizacion.getFechaSiembra() == null) {
								correctas.put("fechaSiembra", "fecha no valida");
							} else {
								try{
									
									Date fecha= new Date (nuevaCotizacion
											.getFechaSiembra().getTime());
									String fecha2= dt.format(fecha);
									
									correctas.put("fechaSiembra", fecha2);
								}
								catch(Exception ex){
									correctas.put("fechaSiembra", "fecha no valida");
								}
							}
							correctas.put("tipoCultivo",
									nuevaCotizacion.getTipoCultivoNombre());
							correctas.put("hasAseguradas",
									nuevaCotizacion.getNumeroHectareasAseguradas());
							correctas.put("hasLote",
									nuevaCotizacion.getNumeroHectareasLote());
							if (nuevaCotizacion.getEsTecnificado()) {
								correctas.put("esTecnificado", "SI");
							} else {
								correctas.put("esTecnificado", "NO");
							}
							correctas.put("provincia",
									nuevaCotizacion.getProvinciaNombre());
							correctas.put("canton", nuevaCotizacion.getCantonNombre());
							correctas.put("parroquia",
									nuevaCotizacion.getParroquiaNombre());
							correctas.put("ubicacion",
									nuevaCotizacion.getUbicacionCultivo());
							correctas.put("telefono", nuevaCotizacion.getTelefono());
							correctas.put("gpsX", nuevaCotizacion.getGpsX());
							correctas.put("gpsY", nuevaCotizacion.getGpsY());
							correctas.put("detalle", "CORRECTO ID: "+cotizacion.getId());
							errorList.add(correctas);
							

						} catch (Exception e) {
							SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
							error.put("canal", nuevaCotizacion.getCanal());
							error.put("nombres", nuevaCotizacion.getClienteNombre());
							error.put("apellidos", nuevaCotizacion.getClienteApellido());
							error.put("cliente", nuevaCotizacion.getClienteNombre()+ " "+ nuevaCotizacion.getClienteApellido() );
							error.put("identificacion",	nuevaCotizacion.getClienteIdentificacion());
							error.put("montoAsegurado",	nuevaCotizacion.getMontoAsegurado());
							if (nuevaCotizacion.getFechaSolicitud() == null) {
								error.put("fechaSolicitud", "fecha no valida");
							} else {
								try{
									Date fecha= new Date (nuevaCotizacion
											.getFechaSolicitud().getTime());
									String fecha2= dt.format(fecha);
									
								error.put("fechaSolicitud", fecha2);
								}
								catch(Exception ex){
									error.put("fechaSolicitud", "fecha no valida");
								}
							}
							if (nuevaCotizacion.getFechaSiembra() == null) {
								error.put("fechaSiembra", "fecha no valida");
							} else {
								try{
									
									Date fecha= new Date (nuevaCotizacion
											.getFechaSiembra().getTime());
									String fecha2= dt.format(fecha);
									
								error.put("fechaSiembra", fecha2);
								}
								catch(Exception ex){
									error.put("fechaSiembra", "fecha no valida");
								}
							}
							error.put("tipoCultivo",
									nuevaCotizacion.getTipoCultivoNombre());
							error.put("hasAseguradas",
									nuevaCotizacion.getNumeroHectareasAseguradas());
							error.put("hasLote",
									nuevaCotizacion.getNumeroHectareasLote());
							if (nuevaCotizacion.getEsTecnificado()) {
								error.put("esTecnificado", "SI");
							} else {
								error.put("esTecnificado", "NO");
							}
							error.put("provincia",
									nuevaCotizacion.getProvinciaNombre());
							error.put("canton", nuevaCotizacion.getCantonNombre());
							error.put("parroquia",
									nuevaCotizacion.getParroquiaNombre());
							error.put("ubicacion",
									nuevaCotizacion.getUbicacionCultivo());
							error.put("telefono", nuevaCotizacion.getTelefono());
							error.put("gpsX", nuevaCotizacion.getGpsX());
							error.put("gpsY", nuevaCotizacion.getGpsY());
							error.put("detalle", e.getMessage());
							errorList.add(error);
							e.printStackTrace();
							auditoria.setObjeto(""+auditoria.getObjeto()+ "||ERROR: "+e.getMessage());
							auditoria.setEstado("Error");
							procesoAuditoria.editar(auditoria);
						}
										
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					return errorList;
				}

			}
}
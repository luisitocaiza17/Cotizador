package com.qbe.cotizador.servlets.mantenimiento;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.cotizacion.AutorizacionSriDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.EntidadJrDAO;
import com.qbe.cotizador.dao.entidad.FirmasDigitalesDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.model.AutorizacionSri;
import com.qbe.cotizador.model.ClaseVehiculo;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.EntidadJr;
import com.qbe.cotizador.model.FirmasDigitales;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoExtra;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorAgricolaImplService;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.transaction.cotizacion.AutorizacionSriTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ClaseVehiculoTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ColorTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.MarcaTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ModeloTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.TipoExtraTransaction;
import com.qbe.cotizador.transaction.cotizacion.ProductoTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadJrTransaction;
import com.qbe.cotizador.transaction.entidad.FirmasDigitalesTransaction;

/**
 * Servlet implementation class MantenimientoController
 */
@WebServlet("/MantenimientoController")
public class MantenimientoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MantenimientoController() {
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
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3*60*60);//(3 horas por 60 minutos por 60 segundos)
				
		ProductoTransaction productoTransaction = new ProductoTransaction();
		
		EntidadJrTransaction entidadJrTransaction = new EntidadJrTransaction();
		FirmasDigitalesTransaction firmasDigitalesTransaction = new FirmasDigitalesTransaction();
		AutorizacionSriTransaction autorizacionSriTransaction = new AutorizacionSriTransaction();
		MarcaTransaction marcaTransaction = new MarcaTransaction(); 
		ClaseVehiculoTransaction claseVehiculoTransaction = new ClaseVehiculoTransaction(); 
		ModeloTransaction modeloTransaction = new ModeloTransaction();
		ColorTransaction colorTransaction = new ColorTransaction();
		TipoExtraTransaction tipoExtraTransaction = new TipoExtraTransaction();
		
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String ramoNemotecnico = request.getParameter("ramo") == null ? "" : request.getParameter("ramo");
					
			Producto producto = new Producto();		
			Ramo ramo = new Ramo();
			ProductoDAO productoDAO = new ProductoDAO();
			RamoDAO ramoDAO = new RamoDAO();
			
			// Actualizamos los catalogos de VH - Estructura Ensurance
			if(tipoConsulta.equals("actualizarCatalogosVH")){												
				result.put("success", Boolean.TRUE);
				result.put("resultadoActualizacion",MantenimientoPYMES.mantenimientoCatalogosPYMES(ramoNemotecnico));				
			}
			
			// Actualizamos los catalogos de PYMES - Estructura Ensurance
			if(tipoConsulta.equals("actualizarCatalogosPYMES")){				
				result.put("success", Boolean.TRUE);						
				result.put("resultadoActualizacion",MantenimientoPYMES.mantenimientoCatalogosPYMES(ramoNemotecnico));
			}
			
			if(tipoConsulta.equals("actualizarEntidadJr")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				
				// Actualizacion EntidadesJr							
				String listadoEntidadesJr = webService.getWebServiceCotizadorImplPort().obtenerEntidadesJr();
				String [] listadoEntidadesJrArr=listadoEntidadesJr.split("\\><");
				EntidadJrDAO entidadJrDAO = new EntidadJrDAO();
				System.out.println("ELIMINADOS "+entidadJrDAO.eliminarTodos()+" REGISTROS DE ENTIDADJR");
				
				for(String entidadesJrFila:listadoEntidadesJrArr){
					String [] entidadesJrValores = entidadesJrFila.split("\\|");
					
					EntidadJr entidadJr=new EntidadJr();
											
					entidadJr.setId(Integer.parseInt(entidadesJrValores[0]));
					entidadJr.setNombre(entidadesJrValores[1]);
					entidadJr.setApellido(entidadesJrValores[2]);
					entidadJr.setNombreCompleto(entidadesJrValores[3]);
					entidadJr.setIdentificacion(entidadesJrValores[4]);
						
					System.out.print("EntidadJr: "+entidadJr.getId());						
					entidadJr = entidadJrTransaction.crear(entidadJr);
					System.out.println(" CREADO");																	
				} 								
			}
			

			if(tipoConsulta.equals("actualizarFirmasDigitales")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				
				String listadoFirmasDigitales = webService.getWebServiceCotizadorImplPort().obtenerFirmasDigitales();
				String [] listadoFirmasDigitalesArr=listadoFirmasDigitales.split("\\><");
				FirmasDigitalesDAO firmasDigitalesDAO = new FirmasDigitalesDAO();
				
				for(String firmasDigitalesFila:listadoFirmasDigitalesArr){
					String [] firmasDigitalesValores = firmasDigitalesFila.split("\\|");
					
					SucursalDAO sucursalDAO=new SucursalDAO();
					ramoDAO=new RamoDAO(); 
					EntidadDAO entidadDAO=new EntidadDAO();
					Entidad entidad=entidadDAO.buscarEntidadPorIdEnsurance(firmasDigitalesValores[1]);
					Sucursal sucursal=sucursalDAO.buscarPorIdEnsurance(firmasDigitalesValores[2]);
					ramo=ramoDAO.buscarPorId(firmasDigitalesValores[3]);
					FirmasDigitales firmasDigitales=new FirmasDigitales();
					String idEnsurance=firmasDigitalesValores[0];
					String firmasDigitalesEstado = "";
					
					if(sucursal!=null&&ramo!=null&&entidad!=null&&sucursal.getId()!=null&&ramo.getId()!=null&&entidad.getId()!=null){
					firmasDigitales=firmasDigitalesDAO.buscarPorRamoSucursalEntidad(ramo, sucursal, entidad);
					
					if(firmasDigitales == null||firmasDigitales.getId()==null){
						firmasDigitales = new FirmasDigitales();
						firmasDigitalesEstado = "NUEVO";
					}
									
						byte[] bytes = webService.getWebServiceCotizadorImplPort().obtenerFirmasDigitalesParametros(sucursal.getSucEnsurance(), entidad.getEntEnsurance(), ramo.getId());
						if (bytes.length > 1) {
							firmasDigitales.setFirma(bytes);
							firmasDigitales.setIdEnsurance(idEnsurance);
							firmasDigitales.setRamo(ramo);
							firmasDigitales.setEntidad(entidad);
							firmasDigitales.setSucursal(sucursal);
							if (firmasDigitalesEstado.equals("NUEVO")) {
								firmasDigitales = firmasDigitalesTransaction.crear(firmasDigitales);
								System.out.print("FirmasDigitales: "+ firmasDigitales.getId());
								System.out.println(" CREADO");
							}
							else {
								System.out.print("FirmasDigitales: "+ firmasDigitales.getId());
								firmasDigitales = firmasDigitalesTransaction.editar(firmasDigitales);
								System.out.println(" ACTUALIZADO");
							}
						}
					}
				} 
			}
			
			if(tipoConsulta.equals("actualizarAutorizacionSri")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				
				String listadoAutorizacionSRI = webService.getWebServiceCotizadorImplPort().obtenerAurotizacionesSRI();
				String [] listadoAutorizacionSRIArr=listadoAutorizacionSRI.split("\\><");
				AutorizacionSriDAO autorizacionSRIDAO = new AutorizacionSriDAO();
				
				for(String autorizacionSRIFila:listadoAutorizacionSRIArr){
					String [] autorizacionSRIValores = autorizacionSRIFila.split("\\|");
					AutorizacionSri aSRI=new AutorizacionSri(); 
					String autorizacionSriEstado ="";
					aSRI = autorizacionSRIDAO.buscarPorIdEnsurance(autorizacionSRIValores[4]);
					
					if(aSRI == null || aSRI.getId()==null){
						aSRI = new AutorizacionSri();
						autorizacionSriEstado = "NUEVO";
					}
					aSRI.setIdEnsurance(autorizacionSRIValores[4].toString());
					aSRI.setNumero(autorizacionSRIValores[0].toString());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					
					Date vigenciaDesde=sdf.parse(autorizacionSRIValores[1].toString().split(" ")[0]);
					Date vigenciaHasta=sdf.parse(autorizacionSRIValores[2].toString().split(" ")[0]);
					
					aSRI.setVigenciaDesde(vigenciaDesde);
					aSRI.setVigenciaHasta(vigenciaHasta);

					if(autorizacionSRIValores[3].toString().equals("0"))
						aSRI.setActivo(false);
					else
						aSRI.setActivo(true);
					
					if(autorizacionSriEstado.equalsIgnoreCase("NUEVO")){
						aSRI = autorizacionSriTransaction.crear(aSRI);		
						System.out.print("AutorizacionSRI: "+aSRI.getId());						
						System.out.println(" CREADO");	
					}	
					else{
						aSRI = autorizacionSriTransaction.editar(aSRI);
						System.out.print("AutorizacionSRI: "+aSRI.getId());
						System.out.println(" ACTUALIZADO");
					}	
				}
		}
			
			if(tipoConsulta.equals("actualizarMarcasModelos")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				// Actualizacion Marcas, Modelos, Clase Vehiculo 							
				String listadoMarcasModelos = webService.getWebServiceCotizadorImplPort().obtenerMarcasVehiculo();
				JSONObject marcasJSONObject = new JSONObject();
	    		JSONArray marcasJSONArray = new JSONArray();
	    		
				if(listadoMarcasModelos.length() > 0){
					String [] listadoMarcasModelosArr=listadoMarcasModelos.split("\\><");				
					if(listadoMarcasModelosArr.length>1){						
						for(String fila:listadoMarcasModelosArr){
							String [] marcasModelosValores = fila.split("\\|");					
							
							Marca marca = new Marca();
							MarcaDAO mDao= new MarcaDAO();
							ModeloDAO moDao = new ModeloDAO();
							Modelo modelo = new Modelo();
							marca = mDao.buscarPorCodigoEnsurance(marcasModelosValores[0]);
							if(marca.getId()== null){
								marca.setMarEnsurance(marcasModelosValores[0]);
								marca.setNombre(marcasModelosValores[1]);
								marca.setActivo(true);
								marcaTransaction.crear(marca);
								
								ClaseVehiculo claseVehiculo = new ClaseVehiculo();
								claseVehiculo.setNombre(marcasModelosValores[5]);
								claseVehiculo.setActivo(true);
								claseVehiculoTransaction.crear(claseVehiculo);
								
								modelo = new Modelo();
								moDao = new ModeloDAO();
								modelo.setMarca(marca);	
								modelo.setClaseVehiculo(claseVehiculo);
								modelo.setModEnsurance(marcasModelosValores[2]);
								modelo.setNombre(marcasModelosValores[3]);
								modeloTransaction.crear(modelo);
								
								marcasJSONObject.put("marca", marcasModelosValores[1]);
	    	    				marcasJSONObject.put("modelo", marcasModelosValores[5]);
	    	    				marcasJSONObject.put("clase", marcasModelosValores[3]);
							}
							else{
								modelo = new Modelo();
								modelo = moDao.buscarPorCodigoEnsurance(marcasModelosValores[2]);
								if(modelo.getId()== null){
									modelo.setMarca(marca);	
									//modelo.setClaseVehiculo(claseVehiculo);
									modelo.setModEnsurance(marcasModelosValores[2]);
									modelo.setNombre(marcasModelosValores[3]);
									modeloTransaction.crear(modelo);
									
									marcasJSONObject.put("marca", marcasModelosValores[1]);
		    	    				marcasJSONObject.put("modelo", marcasModelosValores[5]);
		    	    				marcasJSONObject.put("clase", marcasModelosValores[3]);
								}
							}
							marcasJSONArray.add(marcasJSONObject);
						}						
					}
				}		
				result.put("success", Boolean.TRUE);
	    		result.put("listadoMarcas", marcasJSONArray);
			}
			
			if(tipoConsulta.equals("actualizarColores")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				
				// Actualizacion Colores							
				String listadoColores = webService.getWebServiceCotizadorImplPort().obtenerColoresVehiculo();
				JSONObject coloresJSONObject = new JSONObject();
	    		JSONArray coloresJSONArray = new JSONArray();
	    		
				if(listadoColores.length() > 0){
					String [] listadoColoresArr=listadoColores.split("\\><");					    	    		
					if(listadoColoresArr.length>0){						
						for(String fila:listadoColoresArr){
							String [] coloresValores = fila.split("\\|");					
							
							Color color = new Color();
							ColorDAO coDao= new ColorDAO();
							color = coDao.buscarPorCodigoEnsurance(coloresValores[0]);
							if(color.getId()== null){
								color.setColEnsurance(coloresValores[0]);
								color.setNombre(coloresValores[1]);
								color.setActivo(false);
								colorTransaction.crear(color);
								System.out.println("CREADO");
								coloresJSONObject.put("color", coloresValores[1]);
							}		
							coloresJSONArray.add(coloresJSONObject);
						}												
					}	
				}
				result.put("success", Boolean.TRUE);
	    		result.put("listadoColor", coloresJSONArray);
			}
			
			if(tipoConsulta.equals("actualizarTipoExtras")){
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				
				// Actualizacion TipoExtras 							
				String listadoTipoExtras = webService.getWebServiceCotizadorImplPort().obtenerTipoExtra();
				JSONObject extrasJSONObject = new JSONObject();
	    		JSONArray extrasJSONArray = new JSONArray();
	    		
				if(listadoTipoExtras.length() > 0){
					String [] listadoTipoExtrasArr=listadoTipoExtras.split("\\><");					    	    		
					if(listadoTipoExtrasArr.length>0){
						for(String fila:listadoTipoExtrasArr){
							String [] marcasTipoExtrasValores = fila.split("\\|");					
							
							TipoExtra tipoExtra = new TipoExtra();
							TipoExtraDAO tipoExtraDAO = new TipoExtraDAO();
							tipoExtra = tipoExtraDAO.buscarPorIdEnsurance(marcasTipoExtrasValores[0]);
							if(tipoExtra.getId()== null){
								tipoExtra.setTipExtEnsurance(marcasTipoExtrasValores[0]);
								tipoExtra.setNombre(marcasTipoExtrasValores[1]);
								tipoExtra.setActivo(true);
								tipoExtraTransaction.crear(tipoExtra);
								extrasJSONObject.put("extra", marcasTipoExtrasValores[1]);
								System.out.println("CREADO");
								extrasJSONArray.add(extrasJSONObject);
							}											
						}						
					}
				}							
				result.put("success", Boolean.TRUE);
	    		result.put("listadoExtras", extrasJSONArray);
			}
			
			if(tipoConsulta.equals("actualizarProductosGanadero")){
				WebServiceCotizadorAgricolaImplService webService = new WebServiceCotizadorAgricolaImplService();
							
				// Actualizacion Productos ganadero							
				String listadoProducto = webService.getWebServiceCotizadorAgricolaImplPort().obteneProductos();
				String [] listadoProductoArr =listadoProducto.split("\\><");
				for(String productoFila:listadoProductoArr){
					String [] productoValores = productoFila.split("\\|");
					String productoEstado = "";
					producto = productoDAO.buscarPorId(productoValores[0]);
					
					if(producto == null){
						producto = new Producto();
						productoEstado = "NUEVO";
					}
					producto.setId(productoValores[0].toString());
					producto.setNombre(productoValores[1].toString());
					producto.setRamoId(new BigInteger(productoValores[2].toString()));
					producto.setDefecto(productoValores[3].toString());
					producto.setVigencia(Integer.parseInt(productoValores[4].toString()));
					producto.setDinamico(productoValores[5].toString());
					
					if(productoEstado.equalsIgnoreCase("NUEVO")){
						System.out.print("Producto: "+producto.getId());						
						producto = productoTransaction.crear(producto);	
						System.out.println(" CREADO");	
					}	
					else{
						System.out.print("Producto: "+producto.getId());
						producto = productoTransaction.editar(producto);	
						System.out.println(" ACTUALIZADO");	
					}	
				}						
			}

			
			session.setMaxInactiveInterval(1*60*60);
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			session.setMaxInactiveInterval(1*60*60);
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}	
	}

}

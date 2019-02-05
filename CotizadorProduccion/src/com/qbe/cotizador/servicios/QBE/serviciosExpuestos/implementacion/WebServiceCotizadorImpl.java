package com.qbe.cotizador.servicios.QBE.serviciosExpuestos.implementacion;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.dao.seguridad.CredencialDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Credencial;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TasaProducto;
import com.qbe.cotizador.model.TipoGrupo;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.servicios.QBE.serviciosExpuestos.enumeracion.Sistemas;
import com.qbe.cotizador.servicios.QBE.serviciosExpuestos.interfaces.WebServiceCotizadorInterface;
import com.qbe.cotizador.servicios.QBE.serviciosExpuestos.sistema.Seguros123;
import com.qbe.cotizador.servlets.archivos.CargaArchivosPlanosController;
import com.qbe.cotizador.servlets.producto.vehiculo.ObjetoVehiculoController;
import com.qbe.cotizador.util.Utilitarios;


@WebService(endpointInterface = "com.qbe.cotizador.servicios.QBE.serviciosExpuestos.interfaces.WebServiceCotizadorInterface")
public class WebServiceCotizadorImpl implements WebServiceCotizadorInterface {

	@Override
	public String creacionCotizacionVH(String xmlCotizacion)  {
				
		String textoSinSaltosDeLinea = xmlCotizacion.replaceAll("[\t\n\r]", "");
        String xmlText = textoSinSaltosDeLinea.toString();
        xmlText = xmlText.replace("<![CDATA[", "");
        xmlText = xmlText.replace("&", "&amp;");
        xmlText = xmlText.replace("]]>", "");
        
        String login="",clave="",retorno="",sistema="",tipoObjetoValor="";
		String codigoEntidadEnsurance="";		
		String nombres="",apellidos="",nombreCompleto="",tipoIdentificacion="",identificacion="";					
		String vigenciaPoliza="",puntoVentaId="",agenteId="",porcentajeComision="",productoXPuntoVentaId="",grupoPorProductoId="",tasaProductoId="",tasaProductoValor="";
		boolean generaCotizacion = true;
		
		Cotizacion cotizacion = new Cotizacion();
        	
		try {
			// Manipulacion del XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Reader reader = new CharArrayReader(xmlText.toCharArray());
			Document doc;
			doc = builder.parse(new org.xml.sax.InputSource(reader));  
			doc.getDocumentElement().normalize();
				
			// Obtenemos los datos del Nodo de Acceso
			NodeList nodoAccesoLista = doc.getDocumentElement().getElementsByTagName("acceso");
			Node nodoAcceso = nodoAccesoLista.item(0);            			
	        if (nodoAcceso.getNodeType() == Node.ELEMENT_NODE) {
	        	Element acceso = (Element) nodoAcceso;
	        	if(acceso.getElementsByTagName("login").item(0).getChildNodes().item(0)==null)
	        		login="";
	        	else	        		
	        		login = acceso.getElementsByTagName("login").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(acceso.getElementsByTagName("clave").item(0).getChildNodes().item(0)==null)
	        		clave ="";
	        	else
	        		clave = acceso.getElementsByTagName("clave").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(acceso.getElementsByTagName("sistema").item(0).getChildNodes().item(0)==null)
	        		sistema = "";
	        	else
	        		sistema = acceso.getElementsByTagName("sistema").item(0).getChildNodes().item(0).getNodeValue();
	        }
	        // Validaciones de campos requeridos de acceso
	        if(login.length()==0){
	        	generaCotizacion = false;     			
     			retorno ="2 - Ingrese el login";
     			throw new Exception("Mensaje Sistema");
	        }
	        if(clave.length()==0){
	        	generaCotizacion = false;
	        	retorno = "2 - Ingrese la clave";
     			throw new Exception("Mensaje Sistema");
	        }
	        if(sistema.length()==0){
	        	generaCotizacion = false;
	        	retorno ="2 - Ingrese el valor del sistema";
     			throw new Exception("Mensaje Sistema");
	        }	        
	        // -----------------------------------  Validacion de clave de acceso  -----------------------------------------------------
	        // Verificamos que la clave sea correcta
	        Usuario usuario = new Usuario();
	        UsuarioDAO usuarioDAO = new UsuarioDAO();	        
	        usuario = usuarioDAO.buscarPorLogin(login);
	     	CredencialDAO credencialDAO = new CredencialDAO();
	     	String encriptarClave = Utilitarios.encriptacionClave(clave);
	     	Credencial credencial = credencialDAO.buscarPorUsuarioId(usuario);	     			
	     	if(!credencial.getClave().equalsIgnoreCase(encriptarClave)){
	     		generaCotizacion = false;	     		
	     		retorno="2 - El usuario o la clave son incorrectos";
	     		throw new Exception("Mensaje Sistema");
	     	}
	     	if(!usuario.getActivo()){
	     		generaCotizacion = false;	     		
	     		retorno="2 - El usuario esta inactivo";
	     		throw new Exception("Mensaje Sistema");
	     	}
	     	String nombreSistema = Sistemas.get(sistema).toString();
	     	
	     	// -----------------------------------  Validacion de datos ingresados obligatorios   --------------------------------
	     	NodeList nodoDatosCotizacionLista = doc.getDocumentElement().getElementsByTagName("datosCotizacion");
			Node nodoDatosCotizacion = nodoDatosCotizacionLista.item(0);            			
	        if (nodoDatosCotizacion.getNodeType() == Node.ELEMENT_NODE) {
	        	Element datos = (Element) nodoDatosCotizacion;	        	
	        	if(datos.getElementsByTagName("clienteApellidos").item(0).getChildNodes().item(0)==null)
	        		nombres = "";
	        	else
	        		nombres = datos.getElementsByTagName("clienteNombres").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();	        	
	        	
	        	if(datos.getElementsByTagName("clienteApellidos").item(0).getChildNodes().item(0)==null)
	        		apellidos = "";
	        	else
	        		apellidos = datos.getElementsByTagName("clienteApellidos").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();	        		        	
	        	
	        	if(datos.getElementsByTagName("tipoIdentificacion").item(0).getChildNodes().item(0)==null)
	        		tipoIdentificacion = "";
	        	else
	        		tipoIdentificacion = datos.getElementsByTagName("tipoIdentificacion").item(0).getChildNodes().item(0).getNodeValue();	        	
	        		        	
	        	if(datos.getElementsByTagName("identificacion").item(0).getChildNodes().item(0)==null)
	        		identificacion="";
	        	else
	        		identificacion = datos.getElementsByTagName("identificacion").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(datos.getElementsByTagName("vigenciaPoliza").item(0).getChildNodes().item(0)==null)
	        		vigenciaPoliza ="";
	        	else
	        		vigenciaPoliza = datos.getElementsByTagName("vigenciaPoliza").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(datos.getElementsByTagName("puntoVentaId").item(0).getChildNodes().item(0)==null)
	        		puntoVentaId= "";
	        	else
	        		puntoVentaId= datos.getElementsByTagName("puntoVentaId").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(datos.getElementsByTagName("agenteId").item(0).getChildNodes().item(0)==null)
	        		agenteId = "";
	        	else
	        		agenteId = datos.getElementsByTagName("agenteId").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
	        		        	
	        	if(datos.getElementsByTagName("productoXPuntoVentaId").item(0).getChildNodes().item(0)==null)
	        		productoXPuntoVentaId = "";
	        	else
	        		productoXPuntoVentaId = datos.getElementsByTagName("productoXPuntoVentaId").item(0).getChildNodes().item(0).getNodeValue();
	        		        	
	        	if(datos.getElementsByTagName("grupoPorProductoId").item(0).getChildNodes().item(0)==null)	        	
	        		grupoPorProductoId ="";
	        	else	        		
	        		grupoPorProductoId = datos.getElementsByTagName("grupoPorProductoId").item(0).getChildNodes().item(0).getNodeValue();
	        	
	        	if(datos.getElementsByTagName("tasaProductoId").item(0).getChildNodes().item(0)==null)
	        		tasaProductoId="";
	        	else
	        		tasaProductoId =datos.getElementsByTagName("tasaProductoId").item(0).getChildNodes().item(0).getNodeValue();		        		        		
	        }
	     	
	        if(nombres.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese los nombres del cliente";
     			throw new Exception("Mensaje Sistema");
	        }else if(apellidos.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese los apellidos del cliente";
     			throw new Exception("Mensaje Sistema");
	        }else if(tipoIdentificacion.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese el tipo de identificacion del cliente";
     			throw new Exception("Mensaje Sistema");
	        }else if(identificacion.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese la identificacion del cliente";
     			throw new Exception("Mensaje Sistema");
	        }else if(vigenciaPoliza.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese la vigencia de la póliza";
     			throw new Exception("Mensaje Sistema");
	        }else if(agenteId.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese el id del agente";
     			throw new Exception("Mensaje Sistema");
	        }else if(productoXPuntoVentaId.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese el producto por punto de venta asignado";
     			throw new Exception("Mensaje Sistema");
	        }else if(grupoPorProductoId.length()==0){
	        	generaCotizacion = false;
     			retorno="2 - Ingrese el grupo por producto asignado";
     			throw new Exception("Mensaje Sistema");
	        }     
	        
	     	// Generación de la Cotización
	     	if(generaCotizacion == true){
	     		nombreCompleto = apellidos+" "+ nombres;
	     		// Verificación de los datos ingresados
	     		GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
	     		GrupoPorProducto grupoPorProducto = new GrupoPorProducto();
	     		grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
	        
	     		TipoObjeto tipoObjetoVehiculo = new TipoObjeto();	
	     		TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
	     		TipoGrupo tipoGrupo = new TipoGrupo();	
	     		tipoGrupo = grupoPorProducto.getTipoGrupo();
	     		String codigoTipoVehiculo="",antiguedadVh="",sucursalId="";
	     		   		
	     		// Asignacion del tipo de Objeto según el tipo de grupo
	     		if(tipoGrupo.getId().equals("5")){
	     			tipoObjetoValor="VHDinamico";
	     			tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("1");
	     			codigoTipoVehiculo="2";
	     		}
	     		if(tipoGrupo.getId().equals("1")){
	     			tipoObjetoValor="NoDinamico";
	     			tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("4");
	     			codigoTipoVehiculo="2";
	     		}
	     		if(tipoGrupo.getId().equals("2")){
	     			tipoObjetoValor="NoDinamico";
	     			tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("5");
	     			codigoTipoVehiculo="23";
	     		}
	     		if(tipoGrupo.getId().equals("3")){
	     			tipoObjetoValor="NoDinamico";
	     			tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("2");
	     			codigoTipoVehiculo="18";
	     		}
	     		if(tipoGrupo.getId().equals("4")){
	     			tipoObjetoValor="NoDinamico";
	     			tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("4");
	     			codigoTipoVehiculo="2";
	     		}	     			     			     	
	     		
	     		// Obtenemos la sucursal del punto de Venta
	     		PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
	     		PuntoVenta puntoVenta = new PuntoVenta();
	     		puntoVenta = puntoVentaDAO.buscarPorId(puntoVentaId);
	     		if(puntoVenta != null)
	     			sucursalId = puntoVenta.getSucursal().getId();
	     		else
	     			sucursalId = "1";
	     		
	     		// Asignacion de la comision del agente 
	     		AgenteDAO agenteDAO = new AgenteDAO();
	     		Agente agente = new Agente();
	     		agente = agenteDAO.buscarPorId(agenteId);
	     		if(agente.getComisionVariable()==false)
	     			porcentajeComision = String.valueOf(agente.getComisionVh());
	     		
	     		// Obtenemos la tasa del producto en el caso de que tenga
	     		TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
	     		TasaProducto tasaProducto = new TasaProducto();
	     		if(tasaProductoId.length()>0){
	     			tasaProducto = tasaProductoDAO.buscarPorId(tasaProductoId);	
	     			if(tasaProducto != null)
	     				tasaProductoValor = String.valueOf(tasaProducto.getPorcentajeCasco());
	     		}

				cotizacion = CargaArchivosPlanosController.crearCotizacion(tipoObjetoValor, usuario, puntoVentaId, codigoEntidadEnsurance, tipoIdentificacion, 
						identificacion, nombres, apellidos, nombreCompleto, vigenciaPoliza, agenteId, porcentajeComision, productoXPuntoVentaId, grupoPorProductoId, 
						tasaProductoId, tasaProductoValor, tipoObjetoVehiculo);
				
				
			     // Creamos la cotización y luego agregamos los items vehículos
		        // Recorremos la lista de los items 
		        NodeList nodoItemLista = doc.getDocumentElement().getElementsByTagName("item");		
		        
		        if(nodoItemLista.getLength()!=0){		       
					for (int i = 0; i < nodoItemLista.getLength(); i++) {
						Node nodoItem = nodoItemLista.item(i);										
						if (nodoItem.getNodeType() == Node.ELEMENT_NODE) {
							String modeloId ="",colorId="",codigoTipoUso="",vehiculoId="",codigoVehiculoEnsurance="";
							String anioFabricacion="",conductorEdad="",conductorGenero="",conductorEstadoCivil="",numeroHijos="",kmRecorridos="",kmAnualDeclarado="";
							String combustible="",tipoAdquisicionId="",tonelaje="",conDispositivoRastreo="",esCeroKilometro="",sumaAsegurada="",zona="",guardaGarage="";
							String modeloCotizadorId="",placa="",motor="",chasis="",hijosHasta25AnosConduzcan="",zonaTransito="",identificacionConductor="",conductorEntidadEnsuranceId="";
							String nombresConductor="",apellidosConductor="";
							ObjetoVehiculo objetoVehiculo = new ObjetoVehiculo();
							// Calculo de la antiguedad del vehiculo
							Calendar now = Calendar.getInstance();
							int anoActual = now.get(Calendar.YEAR);  
							
							Element itemVH = (Element) nodoItem;
							
							if(itemVH.getElementsByTagName("modeloId").item(0).getChildNodes().item(0)==null)
								modeloId="";
				        	else
				        		modeloId =itemVH.getElementsByTagName("modeloId").item(0).getChildNodes().item(0).getNodeValue();	
							
							if(itemVH.getElementsByTagName("anioFabricacion").item(0).getChildNodes().item(0)==null)
								anioFabricacion=String.valueOf(anoActual);
				        	else
				        		anioFabricacion =itemVH.getElementsByTagName("anioFabricacion").item(0).getChildNodes().item(0).getNodeValue();	
							
							if(itemVH.getElementsByTagName("sumaAsegurada").item(0).getChildNodes().item(0)==null)
								sumaAsegurada="";
				        	else
				        		sumaAsegurada =itemVH.getElementsByTagName("sumaAsegurada").item(0).getChildNodes().item(0).getNodeValue();
						
							if(itemVH.getElementsByTagName("placa").item(0).getChildNodes().item(0)==null)
								placa="";
				        	else
				        		placa =itemVH.getElementsByTagName("placa").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("motor").item(0).getChildNodes().item(0)==null)
								motor="";
				        	else
				        		motor =itemVH.getElementsByTagName("motor").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("chasis").item(0).getChildNodes().item(0)==null)
								chasis="";
				        	else
				        		chasis =itemVH.getElementsByTagName("chasis").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("conductorEdad").item(0).getChildNodes().item(0)==null)
								conductorEdad="30";
				        	else
				        		conductorEdad =itemVH.getElementsByTagName("conductorEdad").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("conductorGenero").item(0).getChildNodes().item(0)==null)
								conductorGenero="M";
				        	else
				        		conductorGenero =itemVH.getElementsByTagName("conductorGenero").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("conductorEstadoCivil").item(0).getChildNodes().item(0)==null)
								conductorEstadoCivil="S";
				        	else
				        		conductorEstadoCivil =itemVH.getElementsByTagName("conductorEstadoCivil").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("kmAnualDeclarado").item(0).getChildNodes().item(0)==null)
								kmAnualDeclarado="8000";
				        	else
				        		kmAnualDeclarado =itemVH.getElementsByTagName("kmAnualDeclarado").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("dispositivoRastreo").item(0).getChildNodes().item(0)==null)
								conDispositivoRastreo="0";
				        	else
				        		conDispositivoRastreo =itemVH.getElementsByTagName("dispositivoRastreo").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("hijosHasta25AnosConduzcan").item(0).getChildNodes().item(0)==null)
								hijosHasta25AnosConduzcan="0";
				        	else
				        		hijosHasta25AnosConduzcan =itemVH.getElementsByTagName("hijosHasta25AnosConduzcan").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("zonaTransito").item(0).getChildNodes().item(0)==null)
								zonaTransito="30";
				        	else
				        		zonaTransito =itemVH.getElementsByTagName("zonaTransito").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("identificacionConductor").item(0).getChildNodes().item(0)==null)
								identificacionConductor="";
				        	else
				        		identificacionConductor =itemVH.getElementsByTagName("identificacionConductor").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
							if(itemVH.getElementsByTagName("nombresConductor").item(0).getChildNodes().item(0)==null)
								nombresConductor="";
				        	else
				        		nombresConductor =itemVH.getElementsByTagName("nombresConductor").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
														
							if(itemVH.getElementsByTagName("apellidosConductor").item(0).getChildNodes().item(0)==null)
								apellidosConductor="";
				        	else
				        		apellidosConductor =itemVH.getElementsByTagName("apellidosConductor").item(0).getChildNodes().item(0).getNodeValue().toUpperCase();
							
																					
							if(modeloId.length()==0){
					        	generaCotizacion = false;
				     			retorno="2 - Ingrese El modeloid del item: "+i;
				     			throw new Exception("Mensaje Sistema");
					        }
							if(anioFabricacion.length()==0){
					        	generaCotizacion = false;
				     			retorno="2 - Ingrese El ano de fabricacion del item: "+i;
				     			throw new Exception("Mensaje Sistema");
					        }
							if(sumaAsegurada.length()==0){
					        	generaCotizacion = false;
				     			retorno="2 - Ingrese La suma asegurada del item: "+i;
				     			throw new Exception("Mensaje Sistema");
					        }							
							
							antiguedadVh = String.valueOf(anoActual-Integer.parseInt(anioFabricacion));
											
							
							if(nombreSistema.equalsIgnoreCase("SEGUROS123")){
								modeloCotizadorId=Seguros123.obtenerMarcaModeloSeguros123(modeloId);
								colorId="128";codigoTipoUso="1";vehiculoId="";codigoVehiculoEnsurance="";
								numeroHijos="";combustible="";tipoAdquisicionId="";kmRecorridos="";
								tonelaje="";esCeroKilometro="";zona="";guardaGarage=""; conductorEntidadEnsuranceId="";
								
								if(modeloCotizadorId.length()==0) // Modelo por defecto
									modeloCotizadorId="883";
									
								objetoVehiculo = ObjetoVehiculoController.agregarVehiculoCotizacion(modeloCotizadorId, colorId, codigoTipoVehiculo, codigoTipoUso, sucursalId, placa, vehiculoId, chasis, codigoVehiculoEnsurance,
										motor, anioFabricacion, antiguedadVh, conductorEdad, conductorGenero, conductorEstadoCivil, numeroHijos, kmRecorridos, combustible, tipoAdquisicionId, 
										tonelaje, conDispositivoRastreo, esCeroKilometro, sumaAsegurada, zona, guardaGarage,kmAnualDeclarado,hijosHasta25AnosConduzcan,zonaTransito,identificacionConductor,nombresConductor,apellidosConductor,conductorEntidadEnsuranceId);
	
								 ObjetoVehiculoController.agregarCotizacionDetalle(objetoVehiculo, cotizacion, tipoObjetoVehiculo, 0);
					     	}					     						     
						}
					}
				}
				
				retorno = "1 - "+cotizacion.getId();
		        
		        		
	     	}	           
			} catch (SAXException | IOException| ParserConfigurationException p) {				
				retorno="0 - Revise la estructura del xml formado: "+p.getMessage();
			} catch (Exception e) {
				if(retorno.length()==0){
					retorno="3 - Error general"+e.getMessage();
					e.getStackTrace();	
				}
				
			}            
		return retorno;
	}
	
}

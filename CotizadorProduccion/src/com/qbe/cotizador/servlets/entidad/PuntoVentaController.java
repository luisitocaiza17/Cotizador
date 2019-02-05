package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.UsuarioXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TipoGrupoDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoGrupo;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioXPuntoVenta;
import com.qbe.cotizador.transaction.cotizacion.PuntoVentaTransaction;
import com.qbe.cotizador.util.Utilitarios;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PuntoVenta
 */
@WebServlet("/PuntoVentaController")
public class PuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PuntoVentaController() {
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

           AgenteDAO agenteDAO = new AgenteDAO();
           SucursalDAO sucursalDAO = new SucursalDAO();
           
		JSONObject result = new JSONObject();
		
		PuntoVentaTransaction puntoVentaTransaction = new PuntoVentaTransaction();
		try{
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String matriz = request.getParameter("matriz") == null ? "" : request.getParameter("matriz");
			String aplicaIVA12 = request.getParameter("aplicaIVA12") == null ? "" : request.getParameter("aplicaIVA12");
			String sucursalf = request.getParameter("sucursal") == null ? "" : request.getParameter("sucursal");
			String agente = request.getParameter("agente") == null ? " " : request.getParameter("agente");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String tipoObjeto = request.getParameter("tipoObjeto") == null ? "" : request.getParameter("tipoObjeto");
			JSONObject PuntoVentaJSONObject = new JSONObject();
			JSONArray PuntoVentaJSONArray = new JSONArray(); 
			
			String filtroSucursal = request.getParameter("filtroSucursal") == null ? "" : request.getParameter("filtroSucursal");
			String filtroPuntoVenta = request.getParameter("filtroPuntoVenta") == null ? "" : request.getParameter("filtroPuntoVenta");
			String filtroAgente = request.getParameter("filtroAgente") == null ? "" : request.getParameter("filtroAgente");
			String nombreFiltro = request.getParameter("nombreFiltro") == null ? "" : request.getParameter("nombreFiltro");
			String activoFiltro = request.getParameter("activoFiltro") == null ? "" : request.getParameter("activoFiltro");
			String matrizFiltro = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("matrizFiltro");
			String matrizPadre = request.getParameter("matrizPadre") == null ? "" : request.getParameter("matrizPadre");

			PuntoVenta PuntoVenta = new PuntoVenta();
			PuntoVentaDAO PuntoVentaDAO = new PuntoVentaDAO();

			if (!codigo.equals(""))
				PuntoVenta.setId(codigo);

			if (!codigoEnsurance.equals(""))
				PuntoVenta.setPtoEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				PuntoVenta.setNombre(nombre);
			
			if (!descripcion.equals(""))
				PuntoVenta.setDescripcion(descripcion);	

			if (!sucursalf.equals("")){				
				Sucursal sucursalaux = new Sucursal(); 
				sucursalaux = sucursalDAO.buscarPorId(sucursalf);
				PuntoVenta.setSucursal(sucursalaux);
				}
			
			//if (!agente.equals("")){				
			PuntoVenta.setAgenteId(agente);
			//	}	

			if (activa.equals("1"))
				PuntoVenta.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				PuntoVenta.setActivo(false);
			if(tipoConsulta.equals("encontrarActivos")){
				int i = 0;
				JSONObject puntoVentaJSONObject = new JSONObject();
				JSONArray puntoVentaJSONArray = new JSONArray();
				List<PuntoVenta> listaPuntoVenta = PuntoVentaDAO
						.buscarActivos();
				for (i = 0; i < listaPuntoVenta.size(); i++) {
					PuntoVenta = listaPuntoVenta.get(i);
					puntoVentaJSONObject.put("codigo", PuntoVenta.getId());
					puntoVentaJSONObject.put("nombre", PuntoVenta.getNombre());
					puntoVentaJSONArray.add(puntoVentaJSONObject);
				}
				result.put("listaPuntoVenta", puntoVentaJSONArray);
			}
			if(tipoConsulta.equals("encontrarTodos")){
				Agente agenteAux = new Agente();
				AgenteDAO agentedao = new AgenteDAO();
				String agenteaux2 = " ";
				//boolean matrizF = matrizFiltro.equals("1") ? true : false;
				boolean activoF = activoFiltro.equals("1") ? true : false;
				List<PuntoVenta> results = PuntoVentaDAO.buscarPorFiltros(filtroSucursal, filtroPuntoVenta, filtroAgente, nombreFiltro, activoF);
				int i=0;
				for(i=0; i<results.size(); i++){
					PuntoVenta = results.get(i);					
					String idAgente = PuntoVenta.getAgenteId(); 
					
					PuntoVentaJSONObject.put("agenteNombre", PuntoVenta.getAgente()==null?"":PuntoVenta.getAgente().getEntidad().getNombreCompleto());
					PuntoVentaJSONObject.put("agente", PuntoVenta.getAgente()==null?"":PuntoVenta.getAgente().getId());
                    
					
					PuntoVentaJSONObject.put("codigo", PuntoVenta.getId());
					
					if (PuntoVenta.getPtoEnsurance().equals("99952103658")){                                               
                      PuntoVentaJSONObject.put("codigoEnsuranceNombre", "QUITO IN");                                             
                      PuntoVentaJSONObject.put("codigoEnsurance", "99952103658");
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551360")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","GUAYAQUIL");                                             
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551360");                		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551361")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","AMBATO");                                               
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551361");              		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551362")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","CUENCA");                                              
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551362");               		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551363")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre", "IBARRA");                                             
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551363");                		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551364")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","MANTA");                                              
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551364");               		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551365")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","RIOBAMBA");                                              
                        PuntoVentaJSONObject.put("codigoEnsurance", "100049551365");               		
                	}
                	if (PuntoVenta.getPtoEnsurance().equals("100049551366")){
                		PuntoVentaJSONObject.put("codigoEnsuranceNombre","SANTO DOMINGO");                                              
                        PuntoVentaJSONObject.put("codigoEnsurance","100049551366");               		
                	}

                	PuntoVentaJSONObject.put("nombre", PuntoVenta.getNombre());
					PuntoVentaJSONObject.put("descripcion", PuntoVenta.getDescripcion());
					//PuntoVentaJSONObject.put("agente", agenteaux2);
					//PuntoVentaJSONObject.put("agente", PuntoVenta.getAgenteId());
					PuntoVentaJSONObject.put("sucursal", PuntoVenta.getSucursal().getId());
					PuntoVentaJSONObject.put("sucursalNombre", PuntoVenta.getSucursal().getNombre());
                    
					if(PuntoVenta.getMatrizPuntoVentaId() == null )
					{
						PuntoVentaJSONObject.put("matrizP", "");
						PuntoVentaJSONObject.put("matrizCodigo", "");
					}
					else
					{
					  
					  if(PuntoVenta.getMatrizPuntoVentaId().equals(""))
					  {
					    PuntoVentaJSONObject.put("matrizP", "");
                        PuntoVentaJSONObject.put("matrizCodigo", "");
					  }
					  else{
						PuntoVenta p = PuntoVentaDAO.buscarPorId(PuntoVenta.getMatrizPuntoVentaId());
						PuntoVentaJSONObject.put("matrizP", p.getNombre());
						PuntoVentaJSONObject.put("matrizCodigo", p.getId());
					  }
					}
					
					
					if(PuntoVenta.getActivo())
						PuntoVentaJSONObject.put("activo", "Si");
					else
						PuntoVentaJSONObject.put("activo", "No");
					if(PuntoVenta.getEsMatriz())
						PuntoVentaJSONObject.put("esMAtriz", "Si");
					else
						PuntoVentaJSONObject.put("esMAtriz", "No");
					
					if(PuntoVenta.getAplicaIVA12())
						PuntoVentaJSONObject.put("aplicaIVA12", "Si");
					else
						PuntoVentaJSONObject.put("aplicaIVA12", "No");

					PuntoVentaJSONArray.add(PuntoVentaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoPuntoVenta", PuntoVentaJSONArray);
				
				
				
			}
			
			if(tipoConsulta.equals("buscarFiltro")){
				//Para Cargar combos
				int i = 0;
				JSONObject agenteJSONObject = new JSONObject();
                JSONArray agenteJSONArray = new JSONArray();

                List<Agente> listadoAgente= agenteDAO.buscarTodos();

                for (i = 0; i < listadoAgente.size(); i++) {
                	agenteJSONObject.put("codigo", listadoAgente.get(i).getId());
                	agenteJSONObject.put("nombre", listadoAgente.get(i).getEntidad().getNombreCompleto());

                	agenteJSONArray.add(agenteJSONObject);
                }

                result.put("listadoAgente", agenteJSONArray);
                
				JSONObject sucursalJSONObject = new JSONObject();
                JSONArray sucursalJSONArray = new JSONArray();

                List<Sucursal> listadoSucursal= sucursalDAO.buscarTodos();

                for (i = 0; i < listadoSucursal.size(); i++) {
                	sucursalJSONObject.put("codigo", listadoSucursal.get(i).getId());
                	sucursalJSONObject.put("nombre", listadoSucursal.get(i).getNombre());

                	sucursalJSONArray.add(sucursalJSONObject);
                }

                result.put("listadoSucursal", sucursalJSONArray);
                
                //Matrices
                
                JSONObject matrizJSONObject = new JSONObject();
                JSONArray matrizJSONArray = new JSONArray();
                
                List<PuntoVenta> listadoMatriz = PuntoVentaDAO.buscarMatrices();
                
                for(PuntoVenta pv : listadoMatriz)
                {
                	matrizJSONObject.put("codigo", pv.getId());
                	matrizJSONObject.put("nombre", pv.getNombre());
                	matrizJSONArray.add(matrizJSONObject);
                }
                
                result.put("listadoMatriz", matrizJSONArray);
                
                //Punto Venta Ensurance
                
                JSONObject puntoVentaEnsuranceJSONObject = new JSONObject();
                JSONArray puntoVentaEnsuranceJSONArray = new JSONArray();
                
                PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO (); 

                List<String> listadoPuntoVentaEnsurance = puntoVentaDAO.buscarPtosEnsurance();
                String Aux = null;
                String AuxListaPtoVenta = null;
                
                
                for (String pv : listadoPuntoVentaEnsurance) {
                	puntoVentaEnsuranceJSONObject.put("codigo", pv);
                	
                	AuxListaPtoVenta = Aux.valueOf(pv);
                	
                	if (AuxListaPtoVenta.equals("99952103658")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "QUITO IN");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551360")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "GUAYAQUIL");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551361")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "AMBATO");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551362")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "CUENCA");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551363")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "IBARRA");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551364")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "MANTA");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551365")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "RIOBAMBA");                		
                	}
                	if (AuxListaPtoVenta.equals("100049551366")){
                		puntoVentaEnsuranceJSONObject.put("nombre", "SANTO DOMINGO");                		
                	}
                	puntoVentaEnsuranceJSONArray.add(puntoVentaEnsuranceJSONObject);
                }

                result.put("listadoPuntoVentaEnsurance", puntoVentaEnsuranceJSONArray);
			}
			
			// Puntos de Venta por Producto según el rol del usuario
			if((tipoConsulta.equals("puntosVentaXProducto") || tipoConsulta.equals("puntosVentaXProductoSession")) && !tipoObjeto.equalsIgnoreCase("NoTiene")){ 
							
				String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
								
					Usuario usuario=new Usuario();
					if(request.getSession().getAttribute("usuario")!=null)
					 usuario = (Usuario)request.getSession().getAttribute("usuario");
					
					if(usuario==null||usuario.getId()==null)
					throw new Exception("Usuario sin sesión");

					Rol rol=new Rol();
					
					if(usuario.getUsuarioRols().size()>0)
						rol=usuario.getUsuarioRols().get(0).getRol();
					
					if(rol==null||rol.getId()==null)
						throw new Exception("Usuario sin Rol");

					PuntoVenta puntoVenta= new PuntoVenta();
									
					UsuarioXPuntoVentaDAO usuarioXPuntoVentaDAO = new UsuarioXPuntoVentaDAO(); 
					UsuarioXPuntoVenta usuarioXPuntoVenta = new UsuarioXPuntoVenta();
					
					usuarioXPuntoVenta = usuarioXPuntoVentaDAO.buscarPorUsuario(usuario);												
					puntoVenta= usuarioXPuntoVenta.getPuntoVenta();
					

					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					
					GrupoPorProducto grupoPorProducto = new GrupoPorProducto(); 
					if(tipoObjeto.equals("VHDinamico")){								
						grupoPorProducto = grupoPorProductoDAO.buscarPorId("148");
						grupoPorProductoId = grupoPorProducto.getId();
					}else{
						if(grupoPorProductoId.length()>0)
						grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
					}
						
					
					ProductoXPuntoVentaDAO pppvDAO = new ProductoXPuntoVentaDAO();
					ProductoXPuntoVenta productoXPuntoVenta = new ProductoXPuntoVenta();
					
					JSONArray sucursalesJSON=new JSONArray();
					JSONObject sucursalJSON=new JSONObject();
								
					
					List<Sucursal> sucursal = sucursalDAO.buscarActivos();
								
					List<ProductoXPuntoVenta> listado = new ArrayList<ProductoXPuntoVenta>();
					
					TipoGrupo tipoGrupo = new TipoGrupo();
					TipoGrupoDAO tipoGrupoDAO = new TipoGrupoDAO();
					List<ProductoXPuntoVenta> productoXPuntoVentaListado = new ArrayList<ProductoXPuntoVenta>();
					
					// Busqueda de los productos por punto de venta y rol
					
					if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol				
						if((grupoPorProductoId.length()==0)){
							if(tipoObjeto.equals("VHDinamico"))
								tipoObjeto= "Dinamicos";
							if(tipoObjeto.length()==0){								
								listado = pppvDAO.buscarPorProductoPuntoVentaActivos();								
							}else{
								tipoGrupo=tipoGrupoDAO.buscarPorNombre(tipoObjeto);
								listado = pppvDAO.buscarPorProductoPuntoVentaSession(tipoGrupo);
							}
						}else{
							listado = pppvDAO.buscarPorProductoPuntoVentaListado(grupoPorProducto);	
						}	
					}
					else{
						// Si es matriz se buscan todos su puntos de ventas
						if(puntoVenta.getEsMatriz()){
							// Recorremos todos los puntos de venta que pertenecen a la matriz
							PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
														
							// Obtenemos los puntos de venta de la matriz
							List<PuntoVenta> puntosVentaPoseeMatriz = puntoVentaDAO.buscarPuntosVentaPorMatriz(puntoVenta.getId());
							// Agregamos el punto de venta de la matriz al listado
							puntosVentaPoseeMatriz.add(puntoVenta);
														
							for(PuntoVenta puntoVentaDeMatriz:puntosVentaPoseeMatriz){
								if((grupoPorProductoId.length()==0)){																				
									productoXPuntoVentaListado = pppvDAO.buscarPorPuntoVenta(puntoVentaDeMatriz);											
									if(productoXPuntoVentaListado.size()>0 )
										listado.addAll(productoXPuntoVentaListado);																						
								}else{
									listado.add(pppvDAO.buscarPorGrupoPuntoVenta(grupoPorProducto,puntoVentaDeMatriz));
								}																
							}								
						}						
						else{
							// Agregamos los productos por punto de venta asignados al punto de venta (usuarios comunes)	
							if((grupoPorProductoId.length()==0)){
								productoXPuntoVentaListado = pppvDAO.buscarPorPuntoVenta(puntoVenta);											
								if(productoXPuntoVentaListado.size()>0 )
									listado.addAll(productoXPuntoVentaListado);
							}else{
								listado.add(pppvDAO.buscarPorGrupoPuntoVenta(grupoPorProducto,puntoVenta));
							}							
						}
					}										
					
					if(listado.size() > 0) {
						JSONObject puntosVentaJSON = new JSONObject();
						for(int i=0; i<listado.size(); i++) {
							
							productoXPuntoVenta = (ProductoXPuntoVenta) listado.get(i);
							// Rol Administradores pueden visualizar las cotizaciones de los diferentes puntos de venta
							if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol							 
						
								if((grupoPorProductoId.length()==0)){
									puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
									puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
									puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
									puntosVentaJSON.put("text","");
									puntosVentaJSON.put("productoPorPuntoDeVenta","");
									PuntoVentaJSONArray.add(puntosVentaJSON);
								}else{
									puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
									puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
									puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
									puntosVentaJSON.put("text",productoXPuntoVenta.getPuntoVenta().getNombre());
									puntosVentaJSON.put("productoPorPuntoDeVenta",productoXPuntoVenta.getId());
									PuntoVentaJSONArray.add(puntosVentaJSON);
								}
								
							} else {								
								if(puntoVenta.getEsMatriz()){									
									
									if((grupoPorProductoId.length()==0 && tipoObjeto.equalsIgnoreCase(""))){
										puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
										puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
									    puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
									    puntosVentaJSON.put("text","");
									    puntosVentaJSON.put("productoPorPuntoDeVenta","");
									    PuntoVentaJSONArray.add(puntosVentaJSON);
									}else{
										if(productoXPuntoVenta.getId()!=null){																					
											puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
											puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
											puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
											puntosVentaJSON.put("text",productoXPuntoVenta.getPuntoVenta().getNombre());
											puntosVentaJSON.put("productoPorPuntoDeVenta",productoXPuntoVenta.getId());
											PuntoVentaJSONArray.add(puntosVentaJSON);
										}
									}
										
								}else{
									if (puntoVenta != null&& puntoVenta.getId() != null) {
									
										if((grupoPorProductoId.length()==0 && tipoObjeto.equalsIgnoreCase(""))){
											puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
											puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
											puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
											puntosVentaJSON.put("text","");
											puntosVentaJSON.put("productoPorPuntoDeVenta","");
											PuntoVentaJSONArray.add(puntosVentaJSON);
										}else{
											if(productoXPuntoVenta.getId()!=null){
												puntosVentaJSON.put("id",productoXPuntoVenta.getPuntoVenta().getId());
												puntosVentaJSON.put("nombre",productoXPuntoVenta.getPuntoVenta().getNombre());
												puntosVentaJSON.put("sucursal",productoXPuntoVenta.getPuntoVenta().getSucursal().getId());
												puntosVentaJSON.put("text",productoXPuntoVenta.getPuntoVenta().getNombre());
												puntosVentaJSON.put("productoPorPuntoDeVenta",productoXPuntoVenta.getId());
												PuntoVentaJSONArray.add(puntosVentaJSON);
											}
										}										 																															
							     }
						   }
					   }													
				    }						
				  }
					for(int j=0;j<sucursal.size();j++){
						sucursalJSON.put("id", sucursal.get(j).getId());
						sucursalJSON.put("nombre", sucursal.get(j).getNombre());
						sucursalesJSON.add(sucursalJSON);
					}
					result.put("puntosVenta", PuntoVentaJSONArray);	
					result.put("sucursales", sucursalesJSON);					   
			}

			// Verificacion de los datos de un Punto de Venta
			if(tipoConsulta.equals("verificacionPuntoVenta")){ 
							
				String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
				
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				PuntoVenta puntoVentaVerificar = puntoVentaDAO.buscarPorId(puntoVentaId);
								
				JSONObject puntoVentaJSON = new JSONObject();
				puntoVentaJSON.put("id", puntoVentaVerificar.getId().toString());
				if(puntoVentaVerificar.getAgente()!=null&&puntoVentaVerificar.getAgente().getId()!=null)
				  puntoVentaJSON.put("agente_id", puntoVentaVerificar.getAgenteId());
				else
				  puntoVentaJSON.put("agente_id", "");							
				result.put("punto_venta", puntoVentaJSON);						
			}
			
			//Verificacion Nombre Punto de Venta -- PJacome
			if(tipoConsulta.equals("verificacionNombrePuntoVenta")){ 
										
				String nombrePuntoVenta = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
							
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				PuntoVenta puntoVentaVerificar = puntoVentaDAO.buscarPorNombre(nombrePuntoVenta);
				if (puntoVentaVerificar.getId()!= null){
					JSONObject puntoVentaJSON = new JSONObject();								
					puntoVentaJSON.put("bandera", "verdad");
					result.put("PuntoVenta", puntoVentaJSON);							
				}else{
					JSONObject puntoVentaJSON = new JSONObject();
					puntoVentaJSON.put("bandera", "falso");								
					result.put("PuntoVenta", puntoVentaJSON);								
				}
			}
			
			if(tipoConsulta.equals("crear"))
			{
				PuntoVenta.setAplicaIVA12(aplicaIVA12.equals("1"));
				if(!matrizPadre.equals(""))
				{
					PuntoVenta.setMatrizPuntoVentaId(matrizPadre);
					PuntoVenta.setEsMatriz(false);
					puntoVentaTransaction.crear(PuntoVenta);
				}
				else
				{
					PuntoVenta.setEsMatriz(matriz.equals("1"));
					PuntoVenta.setMatrizPuntoVentaId(null);
					puntoVentaTransaction.crear(PuntoVenta);
				}
			}
				
				

			if(tipoConsulta.equals("actualizar"))
			{
				PuntoVenta.setAplicaIVA12(aplicaIVA12.equals("1"));
				if(!matrizPadre.equals(""))
				{
					PuntoVenta.setMatrizPuntoVentaId(matrizPadre);
					PuntoVenta.setEsMatriz(false);
					puntoVentaTransaction.editar(PuntoVenta);
				}
				else
				{
					PuntoVenta.setEsMatriz(true);
					PuntoVenta.setMatrizPuntoVentaId(null);
					puntoVentaTransaction.editar(PuntoVenta);
				}
			}
				

			if(tipoConsulta.equals("eliminar"))
			{
				PuntoVenta = PuntoVentaDAO.buscarPorId(PuntoVenta.getId());
				puntoVentaTransaction.eliminar(PuntoVenta);
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
	
  public JSONArray encontrarPantallaConfiguracion(String filtroSucursal, String filtroPuntoVenta,
      String filtroAgente, String nombreFiltro, boolean activoF) {

    JSONArray retorno = new JSONArray();
    PuntoVentaDAO pvDAO = new PuntoVentaDAO();
    List<PuntoVenta> results = pvDAO.buscarPorFiltros(filtroSucursal, filtroPuntoVenta,
        filtroAgente, nombreFiltro, activoF);

    for (int i = 0; i < results.size(); i++) {

      JSONObject puntoVentaJSON = new JSONObject();
      PuntoVenta puntoVenta = results.get(i);
      puntoVentaJSON.put("codigo", puntoVenta.getId());
      puntoVentaJSON.put("sucursalId", puntoVenta.getSucursal().getId());
      puntoVentaJSON.put("sucursalNombre", puntoVenta.getSucursal().getNombre());
      puntoVentaJSON.put("nombre", puntoVenta.getNombre());
      puntoVentaJSON.put("descripcion", puntoVenta.getDescripcion());
      puntoVentaJSON.put("agenteId", puntoVenta.getAgente().getId());
      puntoVentaJSON.put("agenteNombre", puntoVenta.getAgente().getEntidad().getNombreCompleto());
      puntoVentaJSON.put("activo", puntoVenta.getActivo());
      puntoVentaJSON.put("puntoEnsuranceId", puntoVenta.getPtoEnsurance());

      if (puntoVenta.getPtoEnsurance().equals("99952103658")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "QUITO IN");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551360")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "GUAYAQUIL");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551361")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "AMBATO");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551362")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "CUENCA");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551363")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "IBARRA");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551364")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "MANTA");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551365")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "RIOBAMBA");
      }
      if (puntoVenta.getPtoEnsurance().equals("100049551366")) {
        puntoVentaJSON.put("puntoEnsuranceNombre", "SANTO DOMINGO");
      }

      puntoVentaJSON.put("esMatriz", puntoVenta.getEsMatriz());

      if (!puntoVenta.getEsMatriz() && puntoVenta.getMatrizPuntoVentaId() != null) {
        puntoVentaJSON.put("matrizId", puntoVenta.getMatrizPuntoVentaId());
        puntoVentaJSON.put("matrizNombre",
            pvDAO.buscarPorId(puntoVenta.getMatrizPuntoVentaId()).getNombre());
      } else {
        puntoVentaJSON.put("matrizId", "");
        puntoVentaJSON.put("matrizNombre", "");
      }
    }

    return retorno;
  }
	
}

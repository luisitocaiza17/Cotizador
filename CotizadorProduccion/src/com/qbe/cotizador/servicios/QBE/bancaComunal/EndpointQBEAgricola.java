package com.qbe.cotizador.servicios.QBE.bancaComunal;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionRespuestaDAO;
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
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMaxDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreAuditoriaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucursalXCanalDAO;
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
import com.qbe.cotizador.model.AgriSucursalXCanal;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.CotizacionRespuesta;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionDetalleProcesos;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriAuditoriaCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.qbe.cotizador.util.Utilitarios;

public class EndpointQBEAgricola {
	public String generarPrepoliza(PrepolizaSeguroAgricolaDTO in0){
		Cotizacion existeCotizacion = new Cotizacion();
		CotizacionDAO procesoExiste= new CotizacionDAO();
		String mensaje="";
		String mensajeError="";
		existeCotizacion=procesoExiste.buscarPorNumeroTramite(in0.getNumeroResolucion().trim());
		/*proceso de auditoria*/
		
			String DatosRecibidos=" SERVICIO WEB-AGRICOLA "+" NumeroResolucion "+in0.getNumeroResolucion()+" CedulaRUCCliente "+in0.getCedulaRUCCliente()+" CodigoAgenciaOSucursal "+in0.getCodigoAgenciaOSucursal()
					+" CodigoCanton "+in0.getCodigoCanton()+" CodigoEntidadFinanciera "+in0.getCodigoEntidadFinanciera()+" CodigoParroquia "+in0.getCodigoParroquia()+" CodigoProvincia "+in0.getCodigoProvincia()
					+" CodigoTipoCultivo "+in0.getCodigoTipoCultivo()+" DireccionLoteCultivo "+in0.getDireccionLoteCultivo()+" EsTecnificado "+in0.getEsTecnificado()
					+" FechaAprobacionCredito "+in0.getFechaAprobacionCredito()+" FechaTentativaSiembra "+in0.getFechaTentativaSiembra()+" MontoCredito "+in0.getMontoCredito()
					+" MontoPrima "+in0.getMontoPrima()+" NombreCliente( "+in0.getNombreCliente()+" NumeroHectareas "+in0.getNumeroHectareas()
					+" NumeroHectareasAsegurables "+in0.getNumeroHectareasAsegurables()+" NumeroTotalHectareas "+in0.getNumeroTotalHectareas()+" Tasa "+in0.getTasa()
					+" TelefonoReferencia "+in0.getTelefonoReferencia()+ " CodigoCanal "+ in0.getCodigoCanal();
			
			AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
			auditoria.setObjeto(DatosRecibidos);
			auditoria.setTramite(""+in0.getNumeroResolucion());
			java.util.Date date2= new java.util.Date();
			java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
			auditoria.setFecha(sq2);
			auditoria.setCanal("BANCACOMUNAL");
			AgriSucreAuditoriaTransaction procesoAuditoria= new AgriSucreAuditoriaTransaction();
			
		try{
			procesoAuditoria.crear(auditoria);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			Boolean Emision= true;
			if(existeCotizacion.getId()==null){
				/*Proceso de creacion de instancias a objetoS*/
				EntidadDAO entidadDAO = new EntidadDAO();
				ClienteDAO clienteDAO = new ClienteDAO();
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				PuntoVentaDAO pvDAO = new PuntoVentaDAO();
				VigenciaPolizaDAO vpDAO= new  VigenciaPolizaDAO();
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				ProductoXPuntoVentaDAO productoPorPuntoVentaDAO=new ProductoXPuntoVentaDAO();
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				UsuarioDAO usuarioDAO=new UsuarioDAO();
				EstadoDAO estadoDAO=new EstadoDAO();
				TipoIdentificacionDAO tipoIDentificacion = new TipoIdentificacionDAO();
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction(); 
				CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
				EntidadTransaction entidadTransaction = new EntidadTransaction();
				ClienteTransaction clienteTransaction = new ClienteTransaction();
				AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
				Entidad entidad = new Entidad();
				Entidad entidadBusqueda = new Entidad();
				Cliente cliente = new Cliente();
				Cotizacion cotizacion = new Cotizacion();
				ActividadEconomica actividad = new ActividadEconomica();
				ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
				AgriTipoCultivoDAO cultivo = new AgriTipoCultivoDAO();
				AgriTipoCultivo elCutlitvo = new AgriTipoCultivo();
				
				String EntidadId="";
				/*Proceso de verificacion de campos*/
				if((""+in0.getCodigoEntidadFinanciera()).equals("null")||(""+in0.getCodigoEntidadFinanciera()).equals("")){
					mensajeError="Codigo de Entidad Dinanciera vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getCodigoAgenciaOSucursal()).equals("null")||(""+in0.getCodigoAgenciaOSucursal()).equals("")){
					mensajeError="Codigo de CodigoAgenciaOSucursal vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getNumeroResolucion()==null){
					mensajeError="NumeroResolucion vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getCedulaRUCCliente()==null){
					mensaje="CedulaRUCCliente vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getNombreCliente()==null){
					mensajeError="NombreCliente vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getCodigoTipoCultivo()).equals("null")||(""+in0.getCodigoTipoCultivo()).equals("")){
					mensajeError="Codigo de CodigoTipoCultivo vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getCodigoProvincia()).equals("null")||(""+in0.getCodigoProvincia()).equals("")){
					mensajeError="Codigo de CodigoProvincia vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getCodigoCanton()).equals("null")||(""+in0.getCodigoCanton()).equals("")){
					mensajeError="Codigo de CodigoCanton vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getCodigoParroquia()).equals("null")||(""+in0.getCodigoParroquia()).equals("")){
					mensajeError="Codigo de CodigoParroquia vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getDireccionLoteCultivo()==null){
					mensajeError="DireccionLoteCultivo vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getTelefonoReferencia()==null){
					mensajeError="TelefonoReferencia vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getNumeroHectareas()).equals("null")||(""+in0.getNumeroHectareas()).equals("")){
					mensajeError="NumeroHectareas vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getFechaAprobacionCredito()==null){
					mensajeError="Codigo de FechaAprobacionCredito vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getMontoCredito()).equals("null")||(""+in0.getMontoCredito()).equals("")){
					mensajeError="MontoCredito vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getMontoPrima()).equals("null")||(""+in0.getMontoPrima()).equals("")){
					mensajeError="MontoPrima vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getTasa()).equals("null")||(""+in0.getTasa()).equals("")){
					mensajeError="Tasa vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getNumeroHectareasAsegurables()).equals("null")||(""+in0.getNumeroHectareasAsegurables()).equals("")){
					mensajeError="NumeroHectareasAsegurables vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getEsTecnificado()==null){
					mensajeError="EsTecnificado vacio o nulo";
					throw new Exception("101");
				}
				if(in0.getFechaTentativaSiembra()==null){
					mensajeError="FechaTentativaSiembra vacio o nulo";
					throw new Exception("101");
				}
				if((""+in0.getNumeroTotalHectareas()).equals("null")||(""+in0.getNumeroTotalHectareas()).equals("")){
					mensajeError="NumeroTotalHectareas vacio o nulo";
					throw new Exception("101");
				}
				/*PROCESAMIENTO DE CODIGOS PARA PROVINCIA, PARROQUIA, CANTON*/
				String codProvincia=""+in0.getCodigoProvincia();
				if(codProvincia.length()==1)
					codProvincia="0"+in0.getCodigoProvincia();
				String codCanton=""+in0.getCodigoCanton();
				if(codCanton.length()==1||codCanton.length()==3)
					codCanton="0"+in0.getCodigoCanton();
				String codParroquia=""+in0.getCodigoParroquia();
				if(codParroquia.length()<6)
					codParroquia="0"+codParroquia;
				if(codParroquia.length()>6)
					codParroquia=codParroquia.substring(0, 6);
				
				//nos envian el numero de telefono y de celular en un solo campo por lo que lo separamos
				String telefonos=in0.getTelefonoReferencia();
				String[]telefonosIndividales=telefonos.split(" ");
				String telefonoConvecional=telefonosIndividales[0];
				String telefonoCelular=telefonosIndividales[1];
				if(telefonoConvecional.length()>11){
					mensajeError="Numero de telefono Convencional no valido : "+telefonoConvecional;
					throw new Exception("101");
				}
				if(telefonoCelular.length()>11){
					mensajeError="Numero de telefono Celular no valido : "+telefonoCelular;
					throw new Exception("101");
				}
					
				/**PROCESO DE CREACION DE LA ENTIDAD, CLIENTE Y DIRECCION NECESARIOS PARA LA COTIZACION****/
				/*Verificamos si la entidad existe en la base de datos, caso contrario, la creamos*/
				entidad.setIdentificacion(in0.getCedulaRUCCliente());
				String identificacionCliente=in0.getCedulaRUCCliente().trim();
				TipoIdentificacion identificacion = new TipoIdentificacion();
				
				if(identificacionCliente.length()>13)
					 identificacion = tipoIDentificacion.buscarPorId("2");
				else{
					if(identificacionCliente.length()==13){
						identificacion = tipoIDentificacion.buscarPorId("3");
						String caracterNumerico=""+identificacionCliente.charAt(2);
						if(caracterNumerico.equals("9")){
							identificacion = tipoIDentificacion.buscarPorId("4");
						}
					}
					else{
						if(identificacionCliente.length()==10){
							identificacion = tipoIDentificacion.buscarPorId("1");
						}else{
							mensajeError="Cedula invalida : "+identificacionCliente;
							throw new Exception("101");
						}
					}
				}
				entidad.setTipoIdentificacion(identificacion);
				entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
				entidad.setNombres(in0.getNombreCliente().trim());
				entidad.setNombreCompleto(in0.getNombreCliente().trim());
				entidad.setMail("");
				try{
					entidad.setCelular(telefonoCelular);
				}catch(Exception r){
					System.out.println("telefono no valido o demasiado largo");
				}
				try{
					entidad.setTelefono(telefonoConvecional);
				}catch(Exception r){
					System.out.println("telefono no valido o demasiado largo");
				}
				
				entidadBusqueda=entidadDAO.buscarEntidadPorIdentificacion(in0.getCedulaRUCCliente().trim());
				EntidadId=entidadBusqueda.getId();
				if(EntidadId==null || EntidadId.equals(""))
					entidad=entidadTransaction.crear(entidad);
				else{
					entidad.setId(EntidadId);
					entidad=entidadTransaction.editar(entidad);
				}
				if(entidad.getId()==null){
					mensajeError="No se pudo crear la entidad";
					throw new Exception("101");
				}
				
				/*Creacion del cliente*/
				cliente = clienteDAO.buscarPorEntidadId(entidad);
				if(cliente.getId() == null){
					actividad = actividadDAO.buscarPorNombre("Ninguno");
					Cliente clienteNuevo = new Cliente();
					clienteNuevo.setEntidad(entidad);
					clienteNuevo.setActividadEconomica(actividad);
					clienteNuevo.setActivo(true);
					cliente=clienteTransaction.crear(clienteNuevo);
					//falta en codigo del ensurance
				}
				/*VERIFICACION DE DIVISION POLITICA*/
				ProvinciaDAO laProvincia = new ProvinciaDAO();
				Provincia provincia= new Provincia();
				provincia=laProvincia.buscarPorCodigoSBS(codProvincia);
				if(provincia.getId()==null){
					mensajeError="La Provincia no Existe";
					throw new IOException();
				}
				CantonDAO elCanton = new CantonDAO();
				Canton canton = new Canton();
				if(codCanton.length()>3)
					codCanton=codCanton.substring(2,4);
				canton= elCanton.buscarPorCodigoSBS(codCanton,provincia);
				if(canton.getId()==null){
					mensajeError="El Canton no Existe";
					throw new IOException();
				}
				AgriParroquiaDAO laParroquia = new AgriParroquiaDAO();
				AgriParroquia parroquia = new AgriParroquia();
				parroquia=laParroquia.BuscarPorSBS(codParroquia);
								
				
				
				/*Almacenamiento de Direccion*/
				Direccion direccion=new Direccion();
				DireccionDAO direccionDAO=new DireccionDAO();
				DireccionTransaction direccionTransaction = new DireccionTransaction();
				
				if(entidad.getId()!=null){
					int numeroDirecciones=direccionDAO.buscarCobroPorEntidadId(entidad).size();
					if(numeroDirecciones>0)
						direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(numeroDirecciones-1);
				}
				
				///hallamos la direccion del cliente
				CiudadDAO ciudadDAO = new CiudadDAO();
				Ciudad ciu = ciudadDAO.buscarPorId(provincia.getCapitalId());
				direccion.setCiudad(ciu); 
				
				TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
				ZonaDAO zonaDAO = new ZonaDAO();
				if(in0.getDireccionLoteCultivo()!=null&&!in0.getDireccionLoteCultivo().equals("")){
					direccion.setCallePrincipal(""+in0.getDireccionLoteCultivo());
					direccion.setCalleSecundaria("Lote "+in0.getDireccionLoteCultivo());
					direccion.setNumero(""+in0.getDireccionLoteCultivo());
					direccion.setDatosDeReferencia(""+in0.getDireccionLoteCultivo());
				}
				direccion.setEsCobro(true);
				direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));
				if(entidad!=null&&!entidad.getId().equals(""))
					direccion.setEntidad(entidad);
				direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
				try{
					if(direccion.getId()==null)
						direccion=direccionTransaction.crear(direccion);
					else
						direccion=direccionTransaction.editar(direccion);
				}catch(Exception e){
					Emision=false;
					System.out.println("Problemas al almacenar direccion");
				}
				
				/****PROCESO DE CREACION DE LA COTIZACION****/
				
				/*OJO POR PRODUCTO DE VENTA SE DEBE VERIFICAR*/
				AgriParametroXPuntoVenta PPuntoVenta= new AgriParametroXPuntoVenta();
				AgriParametroXPuntoVentaDAO PPuntoVentaProceso= new AgriParametroXPuntoVentaDAO();
				PPuntoVenta=PPuntoVentaProceso.buscarCodigoIntegracion(in0.getCodigoCanal().trim());
				PuntoVenta puntoVenta = new PuntoVenta();
				puntoVenta = pvDAO.buscarPorId(""+PPuntoVenta.getPuntoVentaId());
				if(puntoVenta!=null)
					cotizacion.setPuntoVenta(puntoVenta);
				else{
					mensajeError="PUNTO DE VENTA NO CONFIGURADO";
					throw new Exception("108");
				}
				GrupoPorProducto grupo= new GrupoPorProducto();
				grupo=grupoPorProductoDAO.buscarPorNombre("Agricola");				
				GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupo.getId());//AGRICOLA			
				ProductoXPuntoVenta pxpv=productoPorPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, puntoVenta);//ojo con agencia de objeto
				if(pxpv==null){
					mensajeError="PRODUCTO POR PUNTO DE VENTA NO CONFIGURADO";
					throw new Exception("108");
				}
				cotizacion.setProductoXPuntoVentaId(new BigInteger(pxpv.getId()));					
				cotizacion.setVigenciaPoliza(vpDAO.buscarPorId("1"));
				//<--OJO QUEMADO HASTA RESOLVER AGENTE REVISAR SI EL CODIGO RESIVIDO SERA EL DE LA CREDENCIAL-->
				/*if(in0.getCodigoFacilitador()!=null)
					cotizacion.setAgenteId(new BigInteger(in0.getCodigoFacilitador()));	
				else*/
						
				cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
				cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
				cotizacion.setProducto(grupoPorProducto.getProducto());			
				cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("Agricola"));
				java.util.Date date= new java.util.Date();
				java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());
				if(!PPuntoVenta.getEmisionDirecta()){
					Emision=false;
				}
				cotizacion.setUsuario(usuarioDAO.buscarPorLogin("e63_1708971229"));
				cotizacion.setFechaElaboracion(sq);
				
				/*PROCESOS DE CALCULOS PARA COTIZACION*/
				
				double tasa=in0.getTasa();
				double nuestraTasa=0.0;
				double costoProduccion=in0.getMontoCredito()/(in0.getNumeroHectareas());
				BigDecimal a = new BigDecimal(""+costoProduccion);
				BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				costoProduccion=Double.parseDouble(""+roundOff);
				String Observacion="";
				
				
				String CodigoTipoCultivoProcesado = "";//para tomar el cod.Cultivo una ves verf. costo produccion.
					
				
				//Obtengo el id del tipo de canal
				AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
				AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
				agriTipoCalculo=agriTipoCalculoDAO.BuscarPorId(PPuntoVenta.getTipoCalculoId());
				
				//verifico la fecha para comprobar el ciclo en base a la fecha de siembra
				
				Date dateSiembra=null;
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formato de fecha de siembra
				try{
					dateSiembra=formatter.parse(in0.getFechaTentativaSiembra().trim());
				}catch(Exception e){
					mensajeError="Error la fecha de siembra "+in0.getFechaTentativaSiembra()+ " no esta en el formato dd/MM/yyyy";
					throw new Exception(
							"Error la fecha de siembra "+in0.getFechaTentativaSiembra()+ " no esta en el formato dd/MM/yyyy");
				}
				
				
				/*VERIFICACION DEL TIPO DE CULTIVO*/
				String codigoTipoCultivo=""+in0.getCodigoTipoCultivo();
				String TipoCultivoSinCoincidencias="";//Se crea para que en caso de no referenciar una regla, este sirva de referencia para el costo de produccion.
				AgriCicloDAO cicloDAO=new AgriCicloDAO();
				AgriTipoCultivoDAO elCultivo = new AgriTipoCultivoDAO();
				List<AgriTipoCultivo> tipoCultivo =elCultivo.BuscarTodosIntegracion(codigoTipoCultivo);
				
				if (!tipoCultivo.isEmpty()) {
					bucle1: for (AgriTipoCultivo rs : tipoCultivo) {
						AgriReglaDAO regla = new AgriReglaDAO();
						List<AgriRegla> reglas = regla.BuscarPorParametros(
								new BigInteger(provincia.getId()),
								new BigInteger(canton.getId()),
										rs.getTipoCultivoId(),agriTipoCalculo.getTipoCalculoId());
						for (AgriRegla rs2 : reglas) {
							try {
								
								AgriCiclo ciclo = cicloDAO.BuscarPorId(rs2.getClicloId());//tomamos el ciclo para verificar a cual pertenece
								Date fechafin=ciclo.getFechaFin();//fecha inicio ciclo
								Date fechaInicio=ciclo.getFechaInicio();//fecha fin ciclo
								
								String costoP = ""+rs2.getCostoProduccion();
								String costoA = ""+costoProduccion;
								if(costoA.equals(""+rs2.getCostoProduccion())||costoA.equals(rs2.getCostoMantenimiento())){//verificamos que los costos de produccion sean iguales
									TipoCultivoSinCoincidencias=""+rs2.getTipoCultivoId();//si son iguales le ponemos como temporal, asi si no calza por fechas calsa al menos el cultivo
									if (costoP.equals(costoA)) {									
										if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
											CodigoTipoCultivoProcesado = ""
													+ rs2.getTipoCultivoId();
											break bucle1;//si se encuentra la busqueda termina										
										}
									}else{
										costoP=""+rs2.getCostoMantenimiento();
										if(costoP .equals(costoA)){
											if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
												CodigoTipoCultivoProcesado = ""
														+ rs2.getTipoCultivoId();
												break bucle1;//si se encuentra la busqueda termina										
											}
										}
									}
								}else{
									TipoCultivoSinCoincidencias=""+rs2.getTipoCultivoId();//si no son iguales los costos de produccion al menos buscame un similar.
								}
								
							} catch (Exception e) {
								continue;
							}
						}
					}
				} else {
					mensajeError="CODIGO DE TIPO DE CULTIVO INVALIDO";
					throw new Exception("106");
				}	
				
				if (CodigoTipoCultivoProcesado.equals("")) {//Si no encontraste tipo de cultivo traeme el que al menos coincida
					Observacion=" No se encontro ninguna regla de siembra para este tipo de cultivo.";
					CodigoTipoCultivoProcesado =TipoCultivoSinCoincidencias;
					if(CodigoTipoCultivoProcesado.equals("")){//si aun asi no encuentra tomamos el primero que coincidir con el codigo de integracion
						elCutlitvo = cultivo.buscarPorCodIntegracion(codigoTipoCultivo);
						if (elCutlitvo.getTipoCultivoId() == null){
							mensajeError="CODIGO DE TIPO DE CULTIVO INVALIDO";
							throw new Exception("106");
						}
						CodigoTipoCultivoProcesado = ""+ elCutlitvo.getTipoCultivoId();
					}					
				}
				
				/*FIN DEL TIPO DE CULTIVO*/
				
				AgriReglaDAO regla = new AgriReglaDAO();
				List<AgriRegla> tasas = regla.BuscarPorParametros(new BigInteger(provincia.getId()), new BigInteger(
								canton.getId()), new BigInteger(CodigoTipoCultivoProcesado),agriTipoCalculo.getTipoCalculoId());
				
				String nuestroCosto = "0.0";//para verificar si existen diferencias entre costo que llega y que tenemos
				String idTipoCalculo = "";//para saber en base a que regla se calcularon los datos
				
				//ya se tiene el cultivo, la provincia, el canton y el tipo de calculo se busca los costos de produccion
				for (AgriRegla rs : tasas) {
					if(rs.getTasa()!=0 || rs.getTasa()!=0.0 ){//si tiene tasa debe tener o costo de produccion o costo de mantenimiento
						nuestraTasa = Double.parseDouble(""+rs.getTasa());
						if(rs.getCostoProduccion()!=0){
							nuestroCosto = (""+rs.getCostoProduccion());
						}else{
							nuestroCosto = (""+rs.getCostoMantenimiento());
						}
						Observacion = Observacion+" " + rs.getObservaciones();
						idTipoCalculo = "" + rs.getReglaId();
					}
				}
				
				String variableControl=auditoria.getObjeto();
				variableControl=variableControl+"|| tasaR: "+tasa+" || CostoProduccionLLega "+costoProduccion+" || costoProduccionQBE "+costoProduccion;
				
				if(!Observacion.equals("")){
					Emision=false;
				}
				//priorizamos el valor que nos envian pero lo dejamos en estado pendiete de aprobar si no hay recla
				double CostoProduccionLLega=costoProduccion;
				
				if(!(""+costoProduccion).equals(nuestroCosto)){
					Emision=false;
					Observacion=Observacion+" Costos de Procuccion Diferentes Costo BC:"+CostoProduccionLLega+ " Costo QBE:"+costoProduccion;
				}
				if(tasa!=nuestraTasa){
					Emision=false;
					Observacion=Observacion+" Tasas Diferentes: tasa BC:"+tasa+" Tasa QBE:"+nuestraTasa;
				}
				
				//si la tasa es 0
				
				if(nuestraTasa==0){
					AgriTipoCultivoDAO agriTipoCultivoDAO= new AgriTipoCultivoDAO();
					AgriTipoCultivo agriTipoCultivo=agriTipoCultivoDAO.BuscarPorId(new BigInteger( CodigoTipoCultivoProcesado));
					tasa = agriTipoCultivo.getTasa();
				}
				
				//Calculamos el monto del credito en base la inf que nos llega el numero de hectares por nuestro costo de produccion
				double sumaAseguradaQBE= (Double.parseDouble(nuestroCosto))*in0.getNumeroHectareasAsegurables();
				variableControl=variableControl+" || SumaAsegurada QBE "+sumaAseguradaQBE;
				
				//almacenamos la suma asegurada
				a = new BigDecimal(""+sumaAseguradaQBE);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setSumaAseguradaTotal(Double.parseDouble(""+roundOff));
				if(sumaAseguradaQBE!=in0.getMontoCredito()){
					Emision=false;
					Observacion=Observacion+" SumaAsegurada Diferentes BC:"+in0.getMontoCredito()+" SumaAsegurada QBE:"+sumaAseguradaQBE;
				}
				
				//calculamos la prima en vase a la tasa, el costo de produccion y el numero de hectareas
				double valorPrimaCalculadaQBE= sumaAseguradaQBE* nuestraTasa /100;
				a = new BigDecimal(""+valorPrimaCalculadaQBE);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				//almacenamos el valor de la prima
				cotizacion.setPrimaNetaTotal(""+roundOff);	
				cotizacion.setPrimaOrigen(Double.parseDouble(""+roundOff));
				
				//valores que recibimos por banca comunal
				double valorPrima = in0.getMontoPrima();//verificar tomamos la que nos envian
				variableControl=variableControl+" || valorPrimaCalculadaQBE "+roundOff;
				variableControl=variableControl+" || valorPrima BC: "+valorPrima;
				//si el valor de las primas son diferentes las guardamos en obsevaciones
				if(valorPrima!=(Double.parseDouble(""+roundOff))){
					Emision=false;
					Observacion=Observacion+" Primas Diferente BC:"+valorPrima+" Prima QBE:"+valorPrimaCalculadaQBE;
				}
				double valorFinalPrima = valorPrimaCalculadaQBE;
				variableControl=variableControl+" || valorFinalPrima "+valorFinalPrima;
				
				/*PROCESO DE CALCULO DE COMPONENTES*/
				TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
		        TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
		        VariableSistemaDAO variableDAO= new VariableSistemaDAO();
		        List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
		        double valorBase = 0;
		        double valorDerechosEmision = 0;
		        double valorSeguroCampesino = 0;
		        double valorSuperBancos = 0;
		        double valorIva= 0;
		        double valorSubTotal = 0;
		        double valorTotal=0;
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
	        	cotizacion.setTasaProductoValor(nuestraTasa);//tasa QBE
		        cotizacion.setEtapaWizard(3);
		        cotizacion.setAsegurado(entidad);
		        cotizacion.setClienteId(new BigInteger(cliente.getId()));
		        
		        if (!PPuntoVenta.getEmisionDirecta()) 
					Emision = false;
				else
					Emision = true;
				
				if(Emision)
		        	cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente de Emitir","Cotizacion"));
		        else
		        	cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente Aprobar","Cotizacion"));
		        
				if(cotizacion.getId()!=null)
					cotizacion = cotizacionTransaction.editar(cotizacion);	
				else
					cotizacion = cotizacionTransaction.crear(cotizacion);
		        String CotizacionID =cotizacion.getId();
		        //Inserta el detalle de la cotizaci�n
		        AgriObjetoCotizacion agriObjetoCotizacion=new AgriObjetoCotizacion();
		        agriObjetoCotizacion.setConfirEmiCanal(false);
				agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia.getId()));
				agriObjetoCotizacion.setCantonId(new BigInteger(canton.getId()));
				try{
					agriObjetoCotizacion.setParroquiaId(new BigInteger(""+parroquia.getId()));
				}catch(Exception e){
					//no se pudo guardar la parroquia
				}
				agriObjetoCotizacion.setTipoCultivoId(new BigInteger(CodigoTipoCultivoProcesado));
				//agriObjetoCotizacion.setDisponeAsistencia(false);			
				agriObjetoCotizacion.setDireccionSiembra(""+in0.getDireccionLoteCultivo());
				agriObjetoCotizacion.setTipoSeguro(0);
				agriObjetoCotizacion.setHectareasLote(Float.parseFloat(""+in0.getNumeroHectareas()));
				agriObjetoCotizacion.setLatitud(0);
				agriObjetoCotizacion.setLongitud(0);
				if(in0.getEsTecnificado().trim().equalsIgnoreCase("SI")||in0.getEsTecnificado().trim().equalsIgnoreCase("S")
						||in0.getEsTecnificado().trim().equalsIgnoreCase("TRUE")){
					agriObjetoCotizacion.setAgricultorTecnificado(true);
					agriObjetoCotizacion.setDisponeRiego(true);
				}
				else{
					agriObjetoCotizacion.setAgricultorTecnificado(false);
					agriObjetoCotizacion.setDisponeRiego(false);
				}
				
				agriObjetoCotizacion.setCodEntidadFinanciera(""+in0.getCodigoEntidadFinanciera());
				agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(""+in0.getNumeroHectareasAsegurables()));
				agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+sumaAseguradaQBE));
				agriObjetoCotizacion.setCostoProduccionQBE(Float.parseFloat(nuestroCosto));
				try{
					agriObjetoCotizacion.setAgriParroquiaId(""+parroquia.getId()); 
				}catch(Exception e){
					
				}
				Date fechaAprobacion = null;
				Date fechaSiembra=null;
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				fechaSiembra = formatoDelTexto.parse(in0.getFechaTentativaSiembra());		
				try {					
					fechaAprobacion = formatoDelTexto.parse(in0.getFechaAprobacionCredito());
				} catch (Exception e) {
					fechaAprobacion = null;					
				}			
				agriObjetoCotizacion.setFechaCredito(fechaAprobacion);
				agriObjetoCotizacion.setFechaSiembra(fechaSiembra);	
				/*Proceso de busqueda de sucursal*/
				AgriSucursalXCanal agriSucursalXCanal = new AgriSucursalXCanal();
				AgriSucursalXCanalDAO agriSucursalXCanalDAO = new AgriSucursalXCanalDAO();
				agriSucursalXCanal=agriSucursalXCanalDAO.buscarCanalIntegracion(""+in0.getCodigoAgenciaOSucursal(),new BigInteger(""+in0.getCodigoEntidadFinanciera()));
				if(agriSucursalXCanal.getCanalId()!=null)
					agriObjetoCotizacion.setSucursalCanalId(agriSucursalXCanal.getSucursalCanalId());
				agriObjetoCotizacion.setTipoOrigen("BANCA COMUNAL");	
				
				if(!Observacion.equals("")){
					agriObjetoCotizacion.setObservacion(Observacion);//no tiene regla configurada
				}
				agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+nuestroCosto));
				agriObjetoCotizacion.setTipoCalculo(idTipoCalculo);
				/***Proceso de creacion de las IdCotizaciones para facturacion***/
				
				AgriCotizacionMaxDAO busquedaMax = new AgriCotizacionMaxDAO();
				AgriCotizacionMax numMaximo=busquedaMax.buscarTodos();
				int numeroActual=numMaximo.getMaximo().intValue();
				
				agriObjetoCotizacion.setIdCotizacion2(new BigInteger(""+(numeroActual+1)));
				agriObjetoCotizacion=agriObjetoCotizacionTransaction.crear(agriObjetoCotizacion);			
				//FIN AGRIOBJETOCOTIZACION 
				CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle();
				nuevoCotizacionDetalle.setCotizacion(cotizacion);
				nuevoCotizacionDetalle.setNecesitaInspeccion(false);
				nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO.buscarPorNombre("Agricola").getId());
				nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion.getObjetoCotizacionId().toString());
				nuevoCotizacionDetalle.setSumaAseguradaItem(cotizacion.getSumaAseguradaTotal());
				nuevoCotizacionDetalle.setPrimaNetaItem(Double.parseDouble(cotizacion.getPrimaNetaTotal()));
				nuevoCotizacionDetalle.setTasa(nuestraTasa);
				cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
				cotizacion.setNumeroTramite(in0.getNumeroResolucion().trim());
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
				try{
					auditoria.setObjeto(variableControl);
					auditoria.setEstado(EstadoAuditoria);
					procesoAuditoria.editar(auditoria);
				}catch(Exception e){
					e.printStackTrace();
					mensajeError=e.getMessage();
					System.out.println("Detalle de error: "+mensajeError);
				}
				
				mensaje="0";
			}else{
				mensaje="107";
			}
		}catch(Exception e){
			String detalle=mensaje;
			mensaje=""+e.getMessage();
			e.printStackTrace();
			System.out.println("Detalle de error: "+mensajeError);
			List<String> usuario = new ArrayList<>();
			usuario.add("luis.caiza@smartwork.com.ec");
			String asunto="Error en Proceso de Cotizacion BANCA COMUNAL";
			AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
			String Html=notificacionErrores.GeneradorHtml(in0.getNumeroResolucion(), e.getMessage()+ " MENSAJE "+mensaje+" Detalle: "+mensajeError, "");
			
			for(String receptor:usuario){
				//Utilitarios.envioMail(receptor, asunto, cuerpo);
				Utilitarios.envioMail(receptor, asunto, Html);
			}
			
			try{
				auditoria.setEstado("101");
				auditoria.setObjeto(""+auditoria.getObjeto()+"||"+detalle+"||"+mensajeError);
				procesoAuditoria.editar(auditoria);
				}catch(Exception e1){
					e1.printStackTrace();
					System.out.println("Detalle Error"+mensajeError);
				}			
		}
		return mensaje;
	}
	
/*Metodo para la consulta de resultado de evaluacion de una poliza solicitada anteriormente*/
	
	public PrepolizaSeguroAgricolaResponseDTO consultarPrepoliza(int codigoEntidadFinanciera, String numeroResolucion){
		
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite("" + numeroResolucion);
		String DatosRecibidos = " codigoEntidadFinanciera "+codigoEntidadFinanciera+ " numeroResolucion "+numeroResolucion;
		
		auditoria.setObjeto(DatosRecibidos);
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		auditoria.setFecha(sq2);
		auditoria.setCanal("BANCACOMUNAL_CONSULTA");
		
		try {
			procesoAuditoria.crear(auditoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrepolizaSeguroAgricolaResponseDTO respuesta = new PrepolizaSeguroAgricolaResponseDTO();
		try{
			CotizacionDAO cotizacionProceso = new CotizacionDAO();	
			Cotizacion cotizacion = new Cotizacion();
			cotizacion = cotizacionProceso.buscarPorNumeroTramite(numeroResolucion);
			CotizacionDetalleDAO cotizacionDetalleProcesos = new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle= cotizacionDetalleProcesos.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			AgriObjetoCotizacion agriObjeto = new AgriObjetoCotizacion();
			AgriObjetoCotizacionDAO procesoCotizacion = new AgriObjetoCotizacionDAO();
			agriObjeto=procesoCotizacion.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
			CotizacionRespuestaDAO cotizacionRespuestaDAO = new CotizacionRespuestaDAO();
			CotizacionRespuesta cotResp = cotizacionRespuestaDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
			AgriTipoCultivo tipoCultivo= new AgriTipoCultivo();
			AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
			tipoCultivo=tipoCultivoDAO.BuscarPorId(agriObjeto.getTipoCultivoId());
			
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/YYYY");
			String variableControl= auditoria.getObjeto();
			
			respuesta.setNumeroPoliza(""+agriObjeto.getIdCotizacion2());
			variableControl=variableControl+"|| setNumeroPoliza  "+cotizacion.getNumeroTramite();
			respuesta.setNumeroAnexo(cotizacion.getNumeroTramite());
			variableControl=variableControl+"|| setNumeroAnexo  "+cotizacion.getNumeroTramite();
			respuesta.setPrimaFinanciada(cotizacion.getPrimaOrigen());
			variableControl=variableControl+"|| setPrimaFinanciada  "+cotizacion.getPrimaOrigen();
			respuesta.setPrimaTotalConImpuestos(cotizacion.getTotalFactura());
			variableControl=variableControl+"|| setPrimaTotalConImpuestos  "+cotizacion.getTotalFactura();
			respuesta.setImpuestoSuperintendeciaBancos(cotizacion.getImpSuperBancos());
			variableControl=variableControl+"|| setImpuestoSuperintendeciaBancos  "+cotizacion.getImpSuperBancos();
			respuesta.setImpuestoSeguroCampesino(cotizacion.getImpSeguroCampesino());
			variableControl=variableControl+"|| setImpuestoSeguroCampesino "+cotizacion.getImpSeguroCampesino();
			respuesta.setDerechosEmision(cotizacion.getImpDerechoEmision());
			variableControl=variableControl+"|| setDerechosEmision "+cotizacion.getImpDerechoEmision();
			respuesta.setIva(cotizacion.getImpIva());		
			variableControl=variableControl+"|| getImpIva "+cotizacion.getImpIva();
			respuesta.setNumeroResolucion(""+cotizacion.getNumeroTramite());
			variableControl=variableControl+"|| setNumeroResolucion  "+cotizacion.getNumeroTramite();
			respuesta.setTasa(cotizacionDetalle.getTasa());
			variableControl=variableControl+"|| setTasa "+cotizacionDetalle.getTasa();
			String elEstado=cotizacion.getEstado().getNombre();
			variableControl=variableControl+"|| elEstado "+elEstado;
			/*PROCESO DE RESPUESTA DEL SERVICIO WEB*/
			respuesta.setCodigoEntidadFinanciera(codigoEntidadFinanciera);
			
			
			int estado=2;
			if(elEstado.equals("Pendiente de Emitir")){
				estado=0;
				/*Encontramos el tipo de cultivo para calcular la fecha de vigencia hasta*/
				AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
				AgriTipoCultivo agriTipoCultivo=agriTipoCultivoDAO.BuscarPorId(agriObjeto.getTipoCultivoId());
				variableControl=variableControl+"|| agriObjeto.getTipoCultivoId() "+agriObjeto.getTipoCultivoId();
				int numeroDias=agriTipoCultivo.getVigenciaDias();
				variableControl=variableControl+" || numeroDias "+numeroDias;	
				
				/**Buscamos la fecha de aprobacion del seguro para enviarla**/
				
				Date fechaFin=new Date();
				Date fechaActual=new Date();
				String fechaEvaluacion="";
				try{
					AgriAuditoriaCotizacionDAO agriAuditoriaCotizacionDAO= new AgriAuditoriaCotizacionDAO();
					AgriAuditoriaCotizacion agriAuditoriaCotizacion=agriAuditoriaCotizacionDAO.BuscarPorCotizacinId(new BigInteger(cotizacion.getId()));
				
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(agriObjeto.getFechaSiembra());
					calendar.add(Calendar.DAY_OF_YEAR, numeroDias);
					fechaFin=calendar.getTime();
					fechaEvaluacion=sm.format(agriAuditoriaCotizacion.getFecha());
				}catch(Exception e){
					auditoria.setObjeto(variableControl);
					auditoria.setEstado("Error Fechas");
					procesoAuditoria.editar(auditoria);
					e.printStackTrace();
					fechaEvaluacion=sm.format(cotizacion.getFechaElaboracion());	
					
				}	
				
				try{
					variableControl=variableControl+" || fechaEvaluacion "+fechaEvaluacion;
					respuesta.setFechaEvaluacion(fechaEvaluacion);
					String fechaVigencia=sm.format(fechaFin);
					variableControl=variableControl+" || fechaVigencia "+fechaVigencia;
					respuesta.setFechaVencimientoPoliza(""+fechaVigencia);	
					auditoria.setObjeto(variableControl);
					auditoria.setEstado("Ok");
					procesoAuditoria.editar(auditoria);
				}catch(Exception e){
					auditoria.setObjeto(variableControl);
					auditoria.setEstado("Ok");
					procesoAuditoria.editar(auditoria);
					e.printStackTrace();
				}
								
			}
			else{
				if(elEstado.equals("Pendiente Aprobar")||elEstado.equals("Pendiente")){
					estado=2;
				}else{
					estado=1;
					respuesta.setPrimaFinanciada(0);
					respuesta.setPrimaTotalConImpuestos(0);
					respuesta.setImpuestoSuperintendeciaBancos(0);
					respuesta.setImpuestoSeguroCampesino(0);
					respuesta.setDerechosEmision(0);
					respuesta.setIva(0);		
					respuesta.setTasa(0);
				}
			}
			respuesta.setEstado(estado);
			respuesta.setObservacion(agriObjeto.getObservacionCotizacion());
			respuesta.setPrimaSubsidiada(cotizacion.getValorDescuento());
		}catch(Exception e){
			e.printStackTrace();
			respuesta.setEstado(1);
			respuesta.setObservacion("LA COTIZACION NO EXISTE");
			try{
				auditoria.setObjeto(e.getMessage());
				auditoria.setEstado("Error");
				procesoAuditoria.editar(auditoria);
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		return respuesta;
	}
	
	public String generarDesenbolos(DesembolsoDTO in0){
		/*Metodo que se utiliza para confirmar cuales seran las cotizaciones que se pueden 
		 * Aprobar, ya que, no todas pasan a aprobacion*/
		String mensaje="";
		String DatosRecibidos=" SERVICIO WEB-AGRICOLA "+" NumeroResolucion "+in0.getNumeroResolucion()
			+" CodigoEntidadFinanciera "+in0.getCodigoEntidadFinanciera()+" FechaDesembolso "+in0.getFechaDesembolso()
			+" NumeroOperacion "+in0.getNumeroOperacion()+" ValorDesembolso "+in0.getValorDesembolso()+" Resultado "+in0.getResultado();
			
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		auditoria.setObjeto(DatosRecibidos);
		auditoria.setTramite(""+in0.getNumeroResolucion());
		java.util.Date date2= new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		auditoria.setFecha(sq2);
		auditoria.setCanal("BANCACOMUNAL_APROBADOS");
		AgriSucreAuditoriaTransaction procesoAuditoria= new AgriSucreAuditoriaTransaction();
		try{
			procesoAuditoria.crear(auditoria);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			String variableControl=auditoria.getObjeto();
			if(in0.getNumeroResolucion()==null||in0.getNumeroResolucion().trim().equals("")){
				mensaje="CODIGO DE NUMERO DE RESOLUCION NO VALIDO";
				throw new Exception("108");
			}
			Cotizacion existeCotizacion = new Cotizacion();
			CotizacionDAO CotizacionProcesos = new CotizacionDAO();
			existeCotizacion= CotizacionProcesos.buscarPorNumeroTramite(in0.getNumeroResolucion().trim());
			if(existeCotizacion.getId()==null){
				mensaje="NO EXISTE COTIZACION PARA EL NUMERO DE TRAMITE "+in0.getNumeroResolucion().trim();
				throw new Exception("108");
			}	
			else{
				String CotizacionId = existeCotizacion.getId();
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
				CotizacionDetalleDAO cotDetProcesos = new CotizacionDetalleDAO();
				cotizacionDetalle=cotDetProcesos.buscarCotizacionDetallePorCotizacion(existeCotizacion).get(0);
				String CotizacionDetalleId=cotizacionDetalle.getId();
				String AgriObjetoCotizacionId=cotizacionDetalle.getObjetoId();
				AgriObjetoCotizacion agriObjetoCotizacion = new AgriObjetoCotizacion();
				AgriObjetoCotizacionDAO agriObjetoCotizacionProcesos= new AgriObjetoCotizacionDAO();
				agriObjetoCotizacion=agriObjetoCotizacionProcesos.buscarPorId(new BigInteger(AgriObjetoCotizacionId));
				CotizacionTransaction procesoCotizacion = new CotizacionTransaction();
				AgriObjetoCotizacionTransaction AgriProcesos = new AgriObjetoCotizacionTransaction();
				
				variableControl=variableControl+"|| PROCESO "+in0.getResultado().toUpperCase().trim();
				
				if(in0.getResultado().toUpperCase().trim().equals("APROBADO")){
				
					
					if(!agriObjetoCotizacion.getCodEntidadFinanciera().equals(""+in0.getCodigoEntidadFinanciera())){
						mensaje="CODIGO DE ENTIDAD FINANCIERA INCORRECTO";
						throw new Exception("108");
					}
					
					if(agriObjetoCotizacion.getNumeroOperacion()!=null){
						mensaje="NOTIFICACION DE DESEMBOLSO YA REALIZADA ANTERIORMENTE";
						throw new Exception("107");
					}
					
					if(!existeCotizacion.getEstado().getNombre().equals("Pendiente de Emitir")){
						mensaje="EL NUMERO DE TRAMITE "+in0.getNumeroResolucion().trim()+" AUN NO SE ENCUENTRA APROBADO";
						throw new Exception("105");
					}
					/*Verificamos valores iguales a pagar*/
					double valorPagar=existeCotizacion.getTotalFactura();
					double valorBC=Double.parseDouble(in0.getValorDesembolso().trim());
					if(valorPagar!=valorBC){
						mensaje="EL VALOR DEL DESEMBOLSO NO COINCIDE CON EL VALOR DE LA CUOTA A PAGAR QBE:"+valorPagar+" BC: "+in0.getValorDesembolso();
						throw new Exception("104");
					}
					/*Proceso de cambio de estado de cotizacion a Pendiente de Emitir*/
					Estado estado = new Estado();
					EstadoDAO estadoProcesos = new EstadoDAO();
					estado= estadoProcesos.buscarPorNombre("Pendiente de Emitir","Cotizacion");
					existeCotizacion.setEstado(estado);
					procesoCotizacion.editar(existeCotizacion);
					/*Guardar datos de pago en AgriObjetoCotizaciones*/
					agriObjetoCotizacion.setCodEntidadFinanciera(""+in0.getCodigoEntidadFinanciera());
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yyyy");			
					Date fechaDesembolso=null;
					try {
						fechaDesembolso = formatoDelTexto.parse(in0.getFechaDesembolso().trim());
					} catch (Exception e) {
						throw new Exception("102");
					}	
					agriObjetoCotizacion.setFechaDesembolso(fechaDesembolso);
					agriObjetoCotizacion.setNumeroOperacion(in0.getNumeroOperacion());
					agriObjetoCotizacion.setConfirEmiCanal(true);
					Float valorMonetario;
					try{
						valorMonetario=Float.parseFloat(""+in0.getValorDesembolso().trim());
						agriObjetoCotizacion.setValorDesembolso(valorMonetario);
					}catch(Exception e){
						throw new Exception("103");
					}
					variableControl=variableControl+" || ValorMonetario "+valorMonetario+" || fechaDesembolso "+fechaDesembolso+" || setNumeroOperacion "+in0.getNumeroOperacion();
					AgriProcesos.editar(agriObjetoCotizacion);
					try{
						/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
						AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
						agriCotizacionDetalleProcesos.creardetalleCotizacion(existeCotizacion, "confirmacion Emision");
						/**FIN DE REGISTRO DE ENDOSO**/
					}catch(Exception e){
						e.printStackTrace();
					}					
					mensaje="0";
					try{
						auditoria.setTramite(in0.getNumeroResolucion());
						auditoria.setObjeto(variableControl);
						auditoria.setEstado("Ok");
						procesoAuditoria.editar(auditoria);
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}else{
					
					if(existeCotizacion.getEstado().getNombre().equals("RevocadoCanal")){
						mensaje="NOTIFICACION DE RECHAZO YA REALIZADA ANTERIORMENTE";
						throw new Exception("107");
					}
					variableControl=variableControl+" || REVOCADO";
					Estado estado = new Estado();
					EstadoDAO estadoProcesos = new EstadoDAO();
					estado= estadoProcesos.buscarPorNombre("RevocadoCanal","Cotizacion");
					existeCotizacion.setEstado(estado);
					procesoCotizacion.editar(existeCotizacion);
					agriObjetoCotizacion.setNumeroOperacion(in0.getNumeroOperacion());
					agriObjetoCotizacion.setConfirEmiCanal(false);
					AgriProcesos.editar(agriObjetoCotizacion);
					mensaje="0";
					try{
						auditoria.setTramite(in0.getNumeroResolucion());
						auditoria.setObjeto(variableControl);
						auditoria.setEstado("Ok");
						procesoAuditoria.editar(auditoria);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			try{
				auditoria.setTramite(in0.getNumeroResolucion());
				auditoria.setObjeto(auditoria.getObjeto()+e.getMessage()+mensaje);
				auditoria.setEstado("Error");
				procesoAuditoria.editar(auditoria);
			}catch(Exception e1){
				e1.printStackTrace();
			}
			mensaje=e.getMessage();
		}
		
		return mensaje;
	}	
}
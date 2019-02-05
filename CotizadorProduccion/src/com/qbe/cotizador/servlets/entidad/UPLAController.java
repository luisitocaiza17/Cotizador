package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.UplaTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CoberturaController
 */
@WebServlet("/UPLAController")
public class UPLAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UPLAController() {
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
		
		DireccionTransaction direccionTransaction = new DireccionTransaction();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		UplaTransaction uplaTransaction = new UplaTransaction();
		
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			if(tipoConsulta.equals("cargarDatosPorIdentificacion")){
				String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
				ClienteDAO clienteDAO=new ClienteDAO();
				EntidadDAO entidadDAO=new EntidadDAO();
				Cliente cliente = clienteDAO.buscarPorEntidadId(entidadDAO.buscarEntidadPorIdentificacion(identificacion));
				UplaDAO uplaDAO=new UplaDAO();
				Upla upla=uplaDAO.buscarPorCliente(cliente);
				if(upla.getId()!=null&&!upla.getId().equals("")){
					result.put("tieneDatosUPLA", true);
					result.put("datosUPLA", getDatosUpla(upla));
					}
				else
					result.put("tieneDatosUPLA", false);
				
			};
			
			if(tipoConsulta.equals("guardarDatosNatural")){
				String identificacion=request.getParameter("identificacion");
				String lugarNacimiento=request.getParameter("lugarNacimiento");
				String fechaNacimiento=request.getParameter("fechaNacimiento");
				String callePrincipalCliente=request.getParameter("callePrincipalCliente");
				String calleSecundariaCliente=request.getParameter("calleSecundariaCliente");
				String numeroDireccionCliente=request.getParameter("numeroDireccionCliente");
				String refenciaDireccionCliente=request.getParameter("refenciaDireccionCliente");
				String zona=request.getParameter("zonaDireccionCliente");
				String parroquia=request.getParameter("parroquia");
				String ciudad=request.getParameter("ciudad");
				String telefono=request.getParameter("telefono");
				String celular=request.getParameter("celular");
				String cargoOcupa=request.getParameter("cargoOcupa");
				String salarioMensual=request.getParameter("salarioMensual");
				String activo=request.getParameter("activo");
				String otrosIngresos=request.getParameter("otrosIngresos");
				String pasivos=request.getParameter("pasivos");
				String egresos=request.getParameter("egresos");
				String patrimonio=request.getParameter("patrimonio");
				String ingresosEgresos=request.getParameter("ingresosEgresos");
				String esAsegurado=request.getParameter("esAsegurado");
				String esBeneficiario=request.getParameter("esBeneficiario");
				String tipoIdentificacionAsegurado=request.getParameter("tipoIdentificacionAsegurado");
				String identificacionAsegurado=request.getParameter("identificacionAsegurado");
				String nombreAsegurado=request.getParameter("nombreAsegurado");
				String domicilioAsegurado=request.getParameter("domicilioAsegurado");
				String telefonoAsegurado=request.getParameter("telefonoAsegurado");
				String celularAsegurado=request.getParameter("celularAsegurado");
				String relacionAsegurado=request.getParameter("relacionAsegurado");
				String email=request.getParameter("mail");
				String cotizacion=request.getParameter("cotizacion");
				
				ClienteDAO clienteDAO=new ClienteDAO();
				EntidadDAO entidadDAO=new EntidadDAO();
				Entidad entidad=entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				Cliente cliente=new Cliente();
								
				if(entidad.getId()!=null)
					cliente=clienteDAO.buscarPorEntidadId(entidad);
				
				UplaDAO uplaDAO=new UplaDAO();
				Upla upla=new Upla();
				
				if(cliente.getId()!=null){
					upla=uplaDAO.buscarPorCliente(cliente);
				}
				
				Date fecha=null;
				if(fechaNacimiento!=null&&!fechaNacimiento.equals(""))
					fecha=new Date(Integer.parseInt(fechaNacimiento.substring(0, 4))-1900,Integer.parseInt(fechaNacimiento.substring(5, 7))-1,Integer.parseInt(fechaNacimiento.substring(8)));
				
				
				//direccion
				Direccion direccion=new Direccion();
				DireccionDAO direccionDAO=new DireccionDAO();
				
				if(entidad.getId()!=null){
					if(direccionDAO.buscarCobroPorEntidadId(entidad).size()>0)
						direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(0);
				}
				
				CiudadDAO ciudadDAO = new CiudadDAO();
				ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
				TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
				ZonaDAO zonaDAO = new ZonaDAO();
				
				if(callePrincipalCliente!=null&&!callePrincipalCliente.equals(""))
				direccion.setCallePrincipal(callePrincipalCliente);
				
				if(callePrincipalCliente!=null&&!callePrincipalCliente.equals(""))
					direccion.setCalleSecundaria(calleSecundariaCliente);
				if(numeroDireccionCliente!=null&&!numeroDireccionCliente.equals(""))
					direccion.setNumero(numeroDireccionCliente);
				if(ciudad!=null&&!ciudad.equals(""))
					direccion.setCiudad(ciudadDAO.buscarPorId(ciudad));
				
				if(refenciaDireccionCliente!=null&&!refenciaDireccionCliente.equals(""))
					direccion.setDatosDeReferencia(refenciaDireccionCliente);
				
				direccion.setEsCobro(true);

				if(callePrincipalCliente!=null&&!callePrincipalCliente.equals(""))
					direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));

				if(parroquia!=null&&!parroquia.equals(""))
					direccion.setParroquia(parroquiaDAO.buscarPorId(parroquia));

				if(entidad!=null&&!entidad.getId().equals(""))
					direccion.setEntidad(entidad);
				
				if(zona.toUpperCase().equals("U"))
					direccion.setZona(zonaDAO.buscarPorNombre("Urbana"));
				if(zona.toUpperCase().equals("R"))
					direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
				if(direccion.getId()==null)
					direccion=direccionTransaction.crear(direccion);
				else
					direccion=direccionTransaction.editar(direccion);
						
				if(direccion.getId()!=null)
				upla.setDireccion(direccion);
				if(!lugarNacimiento.equals(""))
				upla.setLugarNacimientoNatural(lugarNacimiento);
				if(fecha!=null)
				upla.setFechaNacimientoNatural(fecha);
				/*
				ActividadEconomicaDAO actividadEconomicaDAO=new ActividadEconomicaDAO ();
				
				if(cliente.getId()!=null && actividad!=null&&!actividad.equals("")){
					cliente.setActividadEconomica(actividadEconomicaDAO.buscarPorId(actividad));
					cliente=clienteDAO.editar(cliente);
					}
				*/
				if(cliente.getId()!=null)
				upla.setCliente(cliente);
				
				if(celular!=null)
				if(!celular.equals(""))
				upla.setCelularNatural(celular);
				
				if(cargoOcupa!=null)
				if(!cargoOcupa.equals(""))
				upla.setCargoOcupaNatural(cargoOcupa);
				
				/*if(!tipoActividad.equals(""))
				upla.setTipoActividadNatural(tipoActividad);
				
				RamoDAO ramoDAO = new RamoDAO();	
				Ramo ramo=new Ramo();
				if(!tipoRamoContrata.equals(""))
				ramo = ramoDAO.buscarPorId(tipoRamoContrata);
				if(ramo.getId()!=null)
				upla.setRamo(ramo);
				if(!expuesto.equals(""))
				upla.setExpuestaPoliticamenteNatural( expuesto.equals("1") ? true:false );
				if(!cargoExpuesta.equals(""))
				upla.setCargoDesempenaNatural(cargoExpuesta);
				if(!expuestoFamiliar.equals(""))
				upla.setFamiliarExpuestoPoliticamente(expuestoFamiliar.equals("1")?true:false);
				if(!parentescoFamiliar.equals(""))
				upla.setParentescoFamiliarExpuesto(parentescoFamiliar);
				if(!cargoFamiliar.equals(""))
				upla.setCargoFamiliarExpuesto(cargoFamiliar);*/
				
				//datos conyuge
				/*if(!tipoIdentificacionConyuge.equals(""))
				upla.setTipoIdentificacionIdConyuge(BigInteger.valueOf(Long.parseLong(tipoIdentificacionConyuge)));
				if(!identificacionConyuge.equals(""))
				upla.setIdentificacionConyuge(identificacionConyuge);
				if(!apellidoMaternoConyuge.equals(""))
				upla.setApellidoMaternoConyuge(apellidoMaternoConyuge);
				if(!apellidoPaternoConyuge.equals(""))
				upla.setApellidoPaternoConyuge(apellidoPaternoConyuge);
				if(!nombresConyuge.equals(""))
				upla.setNombreConyuge(nombresConyuge);*/
				
				if(salarioMensual!=null)
				if(!salarioMensual.equals(""))
					upla.setSalarioMensual(Double.parseDouble(salarioMensual));
				if(activo!=null)
				if(!activo.equals(""))
					upla.setActivos(new Double(activo));
				if(otrosIngresos!=null)
				if(!otrosIngresos.equals(""))
					upla.setOtrosIngresos(new Double(otrosIngresos));
				if(pasivos!=null)
				if(!pasivos.equals(""))
					upla.setPasivos(new Double(pasivos));
				if(egresos!=null)
				if(!egresos.equals(""))
					upla.setEgresos(new Double(egresos));
				if(patrimonio!=null)
				if(!patrimonio.equals(""))
					upla.setPatrimonio(new Double(patrimonio));
				if(ingresosEgresos!=null)
				if(!ingresosEgresos.equals(""))
					upla.setIngresosEgresos(new Double(ingresosEgresos));
				if(esAsegurado!=null)
				if(!esAsegurado.equals(""))
					upla.setEsAsegurado(esAsegurado.equals("1")?true:false);
				if(esBeneficiario!=null)
				if(!esBeneficiario.equals(""))
					upla.setEsBeneficiario(esBeneficiario.equals("1")?true:false);
				upla.setEsSolicitante(true);
				if(telefono!=null)
				if(!telefono.equals(""))
					upla.setTelefonoNatural(telefono);
				upla.setGeneroNatural("");
				if(email!=null)
				if(!email.equals(""))
					upla.setEmailNatural(email);
				
				if(tipoIdentificacionAsegurado!=null)
				if(!tipoIdentificacionAsegurado.equals(""))
					upla.setTipoIdentificacionIdAsegurado(BigInteger.valueOf(Long.parseLong(tipoIdentificacionAsegurado)));
				if(identificacionAsegurado!=null)
				if(!identificacionAsegurado.equals(""))
					upla.setIdentificacionAsegurado(identificacionAsegurado);
				if(nombreAsegurado!=null)
				if(!nombreAsegurado.equals(""))
					upla.setNombreCompletoAsegurado(nombreAsegurado);
				if(domicilioAsegurado!=null)
				if(!domicilioAsegurado.equals(""))
					upla.setDireccionAsegurado(domicilioAsegurado);
				if(telefonoAsegurado!=null)
				if(!telefonoAsegurado.equals(""))
					upla.setTelefonoAsegurado(telefonoAsegurado);
				if(celularAsegurado!=null)
				if(!celularAsegurado.equals(""))
					upla.setCelularAsegurado(celularAsegurado);
				if(relacionAsegurado!=null)
				if(!relacionAsegurado.equals(""))
					upla.setRelacionAsegurado(relacionAsegurado);
				/*
				if(!tipoIdentificacionBeneficiario.equals(""))
					upla.setTipoIdentificacionIdBeneficiario(BigInteger.valueOf(Long.parseLong(tipoIdentificacionBeneficiario)));
				if(!identificacionBeneficiario.equals(""))
					upla.setIdentificacionBeneficiario(identificacionBeneficiario);
				if(!nombreBeneficiario.equals(""))
					upla.setNombreBeneficiario(nombreBeneficiario);
				if(!domicilioBeneficiario.equals(""))
					upla.setDireccionBeneficiario(domicilioBeneficiario);
				if(!telefonoBeneficiario.equals(""))
					upla.setTelefonoBeneficiario(telefonoBeneficiario);
				if(!celularBeneficiario.equals(""))
					upla.setCelularBeneficiario(celularBeneficiario);
				if(!relacionBeneficiario.equals(""))
					upla.setRelacionBeneficiario(relacionBeneficiario);
				if(!telefono.equals(""))
					upla.setTelefonoNatural(telefono);
				//upla.setEmailNatural();
				if(!identificacionBeneficiario.equals(""))
					upla.setIdentificacionBeneficiario(identificacionBeneficiario);
				*/
				upla.setTipoCliente("N");
								
				if(upla.getId()!=null)
					uplaTransaction.editar(upla);
				else
					uplaTransaction.crear(upla);
				
				//se actualiza la etapa de la cotizacion
				Cotizacion ctzcn = new Cotizacion();
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				if(!cotizacion.equals("")) 
					ctzcn = cotizacionDAO.buscarPorId(cotizacion);
				ctzcn.setEtapaWizard(4);
				
				cotizacionTransaction.editar(ctzcn);
				
			}
			
			if(tipoConsulta.equals("guardarDatosJuridica")){
				String ciudad=request.getParameter("ciudad");
				String zonaDireccionMatriz=request.getParameter("zonaDireccionMatriz");
				String parroquiaDireccionMatriz=request.getParameter("parroquiaDireccionMatriz");
				String ciudadDireccionMatriz=request.getParameter("ciudadDireccionMatriz");
				String callePrincipalDireccion=request.getParameter("callePrincipalDireccion");
				String numeroDireccion=request.getParameter("numeroDireccion");
				String calleSecundariaDireccion=request.getParameter("calleSecundariaDireccion");
				String referenciaDireccion=request.getParameter("referenciaDireccion");
				String celularRepresentante=request.getParameter("celularRepresentante");
				/*String direccionSucursal=request.getParameter("direccionSucursal");
				String ciudadSucursal=request.getParameter("ciudadSucursal");*/
				String telefono=request.getParameter("telefono");
				String mail=request.getParameter("mail");
				/*String fax=request.getParameter("fax");
				String actividad=request.getParameter("actividad");
				String nombresRepresentante=request.getParameter("nombresRepresentante");
				String apellidosRepresentante=request.getParameter("apellidosRepresentante");
				String tipoIdentificacionRepresentante=request.getParameter("tipoIdentificacionRepresentante");
				String identificacionRepresentante=request.getParameter("identificacionRepresentante");
				String lugarNacimientoRepresentante=request.getParameter("lugarNacimientoRepresentante");
				String residenciaRepresentante=request.getParameter("residenciaRepresentante");
				String provinciaRepresentante=request.getParameter("provinciaRepresentante");
				String ciudadRepresentante=request.getParameter("ciudadRepresentante");
				String telefonoRepresentante=request.getParameter("telefonoRepresentante");
				
				String expuestoRepresentante=request.getParameter("expuestoRepresentante");
				String cargoExpuestaRepresentante=request.getParameter("cargoExpuestaRepresentante");
				String expuestoFamiliar=request.getParameter("expuestoFamiliar");
				String parentescoExpuestoFamiliar=request.getParameter("parentescoExpuestoFamiliar");
				String cargoExpuestoFamiliar=request.getParameter("cargoExpuestoFamiliar");
				String apellidoPaternoConyuge=request.getParameter("apellidoPaternoConyuge");
				String apellidoMaternoConyuge=request.getParameter("apellidoMaternoConyuge");
				String nombreConyuge=request.getParameter("nombreConyuge");
				String tipoIdentificacionConyugue=request.getParameter("tipoIdentificacionConyugue");
				String identificacionConyuge=request.getParameter("identificacionConyuge");
				String esAsegurado=request.getParameter("esAsegurado");
				String esBeneficiario=request.getParameter("esBeneficiario");
				String tipoIdentificacionAsegurado=request.getParameter("tipoIdentificacionAsegurado");
				String identificacionAsegurado=request.getParameter("identificacionAsegurado");
				String nombreAsegurado=request.getParameter("nombreAsegurado");
				String direccionAsegurado=request.getParameter("direccionAsegurado");
				String telefonoAsegurado=request.getParameter("telefonoAsegurado");
				String celularAsegurado=request.getParameter("celularAsegurado");
				String relacionAsegurado=request.getParameter("relacionAsegurado");
				String tipoIdentificacionBeneficiario=request.getParameter("tipoIdentificacionBeneficiario");
				String identificacionBeneficiario=request.getParameter("identificacionBeneficiario");
				String nombreBeneficiario=request.getParameter("nombreBeneficiario");
				String direccionBeneficiario=request.getParameter("direccionBeneficiario");
				String telefonoBeneficiario=request.getParameter("telefonoBeneficiario");
				String celularBeneficiario=request.getParameter("celularBeneficiario");
				String relacionBeneficiario=request.getParameter("relacionBeneficiario");*/
				String identificacion=request.getParameter("identificacion");
				//String fechaNacimiento=request.getParameter("fechaNacimientoRepresentante");
				String cotizacion=request.getParameter("cotizacion");
				String salarioMensual=request.getParameter("salarioMensual");
				String activos=request.getParameter("activos");
				String otrosIngresos=request.getParameter("otrosIngresos");
				String pasivos=request.getParameter("pasivos");
				String egresos=request.getParameter("egresos");
				String patrimonio=request.getParameter("patrimonio");
				String ingresosEgresos=request.getParameter("ingresosEgresos");
				
				ClienteDAO clienteDAO=new ClienteDAO();
				EntidadDAO entidadDAO=new EntidadDAO();
				
				Entidad entidad=entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				Cliente cliente=new Cliente();
								
				if(entidad.getId()!=null){
					cliente=clienteDAO.buscarPorEntidadId(entidad);
					
				}
				
				UplaDAO uplaDAO=new UplaDAO();
				Upla upla=new Upla();
				
				if(cliente.getId()!=null){
					upla=uplaDAO.buscarPorCliente(cliente);
				}
				
				Direccion direccion=new Direccion();
				DireccionDAO direccionDAO=new DireccionDAO();
				
				if(entidad.getId()!=null){
					if(direccionDAO.buscarCobroPorEntidadId(entidad).size()>0)
						direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(0);
				}
				
				CiudadDAO ciudadDAO = new CiudadDAO();
				ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
				TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
				ZonaDAO zonaDAO = new ZonaDAO();
				
				if(callePrincipalDireccion!=null)
				if(!callePrincipalDireccion.equals(""))
					direccion.setCallePrincipal(callePrincipalDireccion);
				if(calleSecundariaDireccion!=null)
				if(!calleSecundariaDireccion.equals(""))
					direccion.setCalleSecundaria(calleSecundariaDireccion);
				if(numeroDireccion!=null)
				if(!numeroDireccion.equals(""))
					direccion.setNumero(numeroDireccion);
				if(ciudadDireccionMatriz!=null)
				if(ciudadDireccionMatriz!=null&&ciudadDireccionMatriz!="")
					direccion.setCiudad(ciudadDAO.buscarPorId(ciudadDireccionMatriz));
				if(referenciaDireccion!=null)
				if(!referenciaDireccion.equals(""))
					direccion.setDatosDeReferencia(referenciaDireccion);
				direccion.setEsCobro(true);
				direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));
				if(parroquiaDireccionMatriz!=null&&parroquiaDireccionMatriz!="")
					direccion.setParroquia(parroquiaDAO.buscarPorId(parroquiaDireccionMatriz));
				if(entidad.getId()!=null&&!entidad.getId().equals(""))
					direccion.setEntidad(entidad);
				
				
				if(zonaDireccionMatriz.toUpperCase().equals("U"))
					direccion.setZona(zonaDAO.buscarPorNombre("Urbana"));
				if(zonaDireccionMatriz.toUpperCase().equals("R"))
					direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
				if(direccion.getId()==null)
					direccion=direccionTransaction.crear(direccion);
				else
					direccion=direccionTransaction.editar(direccion);
				
				if(ciudad!=null)
				if(!ciudad.equals(""))
					upla.setCiudadPaisJuridica(ciudad);
				if(cliente.getId()!=null&&!cliente.getId().equals(""))
					upla.setCliente(cliente);
				if(direccion.getId()!=null&&!direccion.getId().equals(""))
					upla.setDireccion(direccion);
				if(celularRepresentante!=null)
				if(!celularRepresentante.equals(""))
					upla.setCelularRepresentanteLegal(celularRepresentante);
				if(mail!=null)
					if(!mail.equals(""))
						upla.setEmailNatural(mail);
				/*if(!direccionSucursal.equals(""))
					upla.setSucursalDireccionJuridica(direccionSucursal);
				if(!ciudadSucursal.equals(""))
					upla.setSucursalCiudadJuridica(ciudadSucursal);
				if(!objetoSocial.equals(""))
					upla.setObjetoSocialJuridica(objetoSocial);
				
				if(!ciudadDireccionMatriz.equals(""))
					upla.setCiudadPaisJuridica(ciudadDireccionMatriz);
				if(!expuestoRepresentante.equals(""))
					upla.setExpuestaPoliticamenteNatural( expuestoRepresentante.equals("1") ? true:false );
				if(!cargoExpuestaRepresentante.equals(""))
					upla.setCargoDesempenaNatural(cargoExpuestaRepresentante);
				if(!expuestoFamiliar.equals(""))
					upla.setFamiliarExpuestoPoliticamente(expuestoFamiliar.equals("1")?true:false);
				if(!parentescoExpuestoFamiliar.equals(""))
					upla.setParentescoFamiliarExpuesto(parentescoExpuestoFamiliar);
				if(!cargoExpuestoFamiliar.equals(""))
					upla.setCargoFamiliarExpuesto(cargoExpuestoFamiliar);*/
				if(telefono!=null)
				if(!telefono.equals(""))
					upla.setTelefonoEmpresa(telefono);
				/*Date fecha=null;
				if(fechaNacimiento!=null&&!fechaNacimiento.equals(""))
					fecha=new Date(Integer.parseInt(fechaNacimiento.substring(0, 4))-1900,Integer.parseInt(fechaNacimiento.substring(5, 7))-1,Integer.parseInt(fechaNacimiento.substring(8)));
				*/
				/*
				if(!fax.equals(""))
					upla.setFaxEmpresa(fax);
				if(!nombresRepresentante.equals(""))
					upla.setNombresRepresentanteLegal(nombresRepresentante);
				if(!apellidosRepresentante.equals(""))
					upla.setApellidosRepresentanteLegal(apellidosRepresentante);
				
				TipoIdentificacionDAO tiDAO=new TipoIdentificacionDAO();
				if(!tipoIdentificacionRepresentante.equals(""))
					upla.setTipoIdentificacionIdRepresentanteLegal(BigInteger.valueOf(Long.parseLong(tipoIdentificacionRepresentante)));
				if(!identificacionRepresentante.equals(""))
					upla.setIdentificacionRepresentanteLegal(identificacionRepresentante);
				if(!lugarNacimientoRepresentante.equals(""))
					upla.setLugarNacimientoRepresentanteLegal(lugarNacimientoRepresentante);
				if(!provinciaRepresentante.equals(""))
					upla.setProvinciaIdRepresentanteLegal(BigInteger.valueOf(Long.parseLong(provinciaRepresentante)));
				if(!ciudadRepresentante.equals(""))
					upla.setCiudadIdRepresentanteLegal(BigInteger.valueOf(Long.parseLong(ciudadRepresentante)));
				if(!telefonoRepresentante.equals(""))
					upla.setTelefonoRepresentanteLegal(telefonoRepresentante);
				
				if(fecha!=null)
					upla.setFechaNacimientoRepresentanteLegal(fecha);
				
				if(!referenciaDireccion.equals(""))
					upla.setDireccionRepresentanteLegal(residenciaRepresentante);

				ActividadEconomicaDAO actividadEconomicaDAO= new ActividadEconomicaDAO();
				if(cliente.getId()!=null && actividad!=null&&actividad!=""){
					cliente.setActividadEconomica(actividadEconomicaDAO.buscarPorId(actividad));
					cliente=clienteDAO.editar(cliente);
					}
				
				if(!tipoIdentificacionConyugue.equals(""))
					upla.setTipoIdentificacionIdConyuge(BigInteger.valueOf(Long.parseLong(tipoIdentificacionConyugue)));
				if(!identificacionConyuge.equals(""))
					upla.setIdentificacionConyuge(identificacionConyuge);
				if(!apellidoMaternoConyuge.equals(""))
					upla.setApellidoMaternoConyuge(apellidoMaternoConyuge);
				if(!apellidoPaternoConyuge.equals(""))
					upla.setApellidoPaternoConyuge(apellidoPaternoConyuge);
				if(!nombreConyuge.equals(""))
					upla.setNombreConyuge(nombreConyuge);
				
				if(!esAsegurado.equals(""))
					upla.setEsAsegurado(esAsegurado.equals("1")?true:false);
				if(!esBeneficiario.equals(""))
					upla.setEsBeneficiario(esBeneficiario.equals("1")?true:false);
				upla.setEsSolicitante(true);
				
				if(!tipoIdentificacionAsegurado.equals(""))
					upla.setTipoIdentificacionIdAsegurado(BigInteger.valueOf(Long.parseLong(tipoIdentificacionAsegurado)));
				if(!identificacionAsegurado.equals(""))
					upla.setIdentificacionAsegurado(identificacionAsegurado);
				if(!nombreAsegurado.equals(""))
					upla.setNombreCompletoAsegurado(nombreAsegurado);
				if(!direccionAsegurado.equals(""))
					upla.setDireccionAsegurado(direccionAsegurado);
				if(!telefonoAsegurado.equals(""))
					upla.setTelefonoAsegurado(telefonoAsegurado);
				if(!celularAsegurado.equals(""))
					upla.setCelularAsegurado(celularAsegurado);
				if(!relacionAsegurado.equals(""))
					upla.setRelacionAsegurado(relacionAsegurado);
				
				if(!tipoIdentificacionBeneficiario.equals(""))
					upla.setTipoIdentificacionIdBeneficiario(BigInteger.valueOf(Long.parseLong(tipoIdentificacionBeneficiario)));
				if(!identificacionBeneficiario.equals(""))
					upla.setIdentificacionBeneficiario(identificacionBeneficiario);
				if(!nombreBeneficiario.equals(""))
					upla.setNombreBeneficiario(nombreBeneficiario);
				if(!direccionBeneficiario.equals(""))
					upla.setDireccionBeneficiario(direccionBeneficiario);
				if(!telefonoBeneficiario.equals(""))
					upla.setTelefonoBeneficiario(telefonoBeneficiario);
				if(!celularBeneficiario.equals(""))
					upla.setCelularBeneficiario(celularBeneficiario);
				if(!relacionBeneficiario.equals(""))
					upla.setRelacionBeneficiario(relacionBeneficiario);*/
				if(salarioMensual!=null)
				if(!salarioMensual.equals(""))
					upla.setSalarioMensual(Double.parseDouble(salarioMensual));
				if(activos!=null)
				if(!activos.equals(""))
					upla.setActivos(new Double(activos));
				if(otrosIngresos!=null)
				if(!otrosIngresos.equals(""))
					upla.setOtrosIngresos(new Double(otrosIngresos));
				if(pasivos!=null)
				if(!pasivos.equals(""))
					upla.setPasivos(new Double(pasivos));
				if(egresos!=null)
				if(!egresos.equals(""))
					upla.setEgresos(new Double(egresos));
				if(patrimonio!=null)
				if(!patrimonio.equals(""))
					upla.setPatrimonio(new Double(patrimonio));
				if(ingresosEgresos!=null)
				if(!ingresosEgresos.equals(""))
					upla.setIngresosEgresos(new Double(ingresosEgresos));
				upla.setTipoCliente("J");
				
				if(upla.getId()!=null)
					uplaTransaction.editar(upla);
				else
					uplaTransaction.crear(upla);
				//se actualiza la etapa de la cotizacion
				Cotizacion ctzcn = new Cotizacion();
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				if(!cotizacion.equals("")) 
					ctzcn = cotizacionDAO.buscarPorId(cotizacion);
				ctzcn.setEtapaWizard(4);
				
				cotizacionTransaction.editar(ctzcn);
				
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
	
	public JSONObject getDatosUpla(Upla upla){
		JSONObject datosUPLA= new JSONObject();
		
		if(upla.getTipoCliente().equals("N")){
			if(upla.getLugarNacimientoNatural()!=null)
			datosUPLA.put("lugarNacimiento", upla.getLugarNacimientoNatural());
			if(upla.getFechaNacimientoNatural()!=null)
				datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoNatural());
			if(upla.getDireccion()!=null)
				datosUPLA.put("zonaDireccionCliente",upla.getDireccion().getZona().getNombre().charAt(0));
			if(upla.getDireccion()!=null)
				if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
					if(upla.getDireccion().getParroquia()!=null){
						if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
							datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
						if(upla.getDireccion().getParroquia().getCanton()!=null)
							datosUPLA.put("cantonDireccionCliente", upla.getDireccion().getParroquia().getCanton().getId());
						if(upla.getDireccion().getParroquia()!=null)
							datosUPLA.put("parroquiaDireccionCliente", upla.getDireccion().getParroquia().getId());
					}
			}
			
			if(upla.getDireccion() != null)
				if(upla.getDireccion().getZona()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
						if(upla.getDireccion().getCiudad()!=null){
							datosUPLA.put("ciudadDireccionCliente", upla.getDireccion().getCiudad().getId());
							if(upla.getDireccion().getCiudad().getProvincia()!=null)
								datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getCiudad().getProvincia().getId());
						}
						if(upla.getDireccion().getCallePrincipal()!=null)
							datosUPLA.put("callePrincipalCliente",upla.getDireccion().getCallePrincipal());
						if(upla.getDireccion().getNumero()!=null)
							datosUPLA.put("numeroDireccionCliente",upla.getDireccion().getNumero());
						if(upla.getDireccion().getCalleSecundaria()!=null)
							datosUPLA.put("calleSecundariaCliente",upla.getDireccion().getCalleSecundaria());
						if(upla.getDireccion().getDatosDeReferencia()!=null)
							datosUPLA.put("referenciaDireccionCliente",upla.getDireccion().getDatosDeReferencia());
					}
			
			if(upla.getTelefonoNatural()!=null)
				datosUPLA.put("telefonoCliente",upla.getTelefonoNatural());
			if(upla.getCelularNatural()!=null)
				datosUPLA.put("celularCliente",upla.getCelularNatural());
			if(upla.getGeneroNatural()!=null)
				datosUPLA.put("generoCliente",upla.getGeneroNatural());
			if(upla.getEmailNatural()!=null)
				datosUPLA.put("mail",upla.getEmailNatural());
			if(upla.getCliente().getActividadEconomica()!=null)
				datosUPLA.put("actividadCliente",upla.getCliente().getActividadEconomica().getId());
			if(upla.getTipoActividadNatural()!=null)
				datosUPLA.put("tipoActividadCliente",upla.getTipoActividadNatural());
			if(upla.getCargoOcupaNatural()!=null)
				datosUPLA.put("cargoOcupaCliente",upla.getCargoOcupaNatural());
			if(upla.getRamo()!=null)
				datosUPLA.put("tipoRamoContrata",upla.getRamo().getId());
			datosUPLA.put("expuestoCliente",upla.getExpuestaPoliticamenteNatural());
			if(upla.getCargoDesempenaNatural()!=null)
				datosUPLA.put("cargoExpuestoCliente",upla.getCargoDesempenaNatural());
			datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
			if(upla.getParentescoFamiliarExpuesto()!=null)
				datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
			if(upla.getCargoFamiliarExpuesto()!=null)
				datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
			if(upla.getApellidoPaternoConyuge()!=null)
				datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
			if(upla.getApellidoMaternoConyuge()!=null)
				datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
			if(upla.getNombreConyuge()!=null)
				datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
			if(upla.getTipoIdentificacionIdConyuge()!=null)
				datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
			if(upla.getIdentificacionConyuge()!=null)
				datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
			datosUPLA.put("salarioMensual",upla.getSalarioMensual());
			datosUPLA.put("activos",upla.getActivos());
			datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
			datosUPLA.put("pasivos",upla.getPasivos());
			datosUPLA.put("egresos",upla.getEgresos());
			datosUPLA.put("patrimonio",upla.getPatrimonio());
			datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
			datosUPLA.put("esAsegurado",upla.getEsAsegurado());
			datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
			if(upla.getTipoIdentificacionIdAsegurado()!=null)
				datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
			if(upla.getIdentificacionAsegurado()!=null)
				datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
			if(upla.getNombreCompletoAsegurado()!=null)
				datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
			if(upla.getDireccionAsegurado()!=null)
				datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
			if(upla.getTelefonoAsegurado()!=null)
				datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
			if(upla.getCelularAsegurado()!=null)
				datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
			if(upla.getRelacionAsegurado()!=null)
				datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
			if(upla.getTipoIdentificacionIdBeneficiario()!=null)
				datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
			if(upla.getIdentificacionBeneficiario()!=null)
				datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
			if(upla.getNombreBeneficiario()!=null)
				datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
			if(upla.getDireccionBeneficiario()!=null)
				datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
			if(upla.getTelefonoBeneficiario()!=null)
				datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
			if(upla.getCelularBeneficiario()!=null)
				datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
			if(upla.getRelacionBeneficiario()!=null)
				datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());
			
			}
			
			if(upla.getTipoCliente().equals("J")){
				if(upla.getObjetoSocialJuridica()!=null)
					datosUPLA.put("objetoSocial", upla.getObjetoSocialJuridica());
				if(upla.getCiudadPaisJuridica()!=null)
					datosUPLA.put("ciudadJuridica", upla.getCiudadPaisJuridica());
				if(upla.getFechaNacimientoNatural()!=null)
					datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoRepresentanteLegal());
				if(upla.getDireccion().getZona()!=null)
					datosUPLA.put("zonaDireccionMatriz",upla.getDireccion().getZona().getNombre().charAt(0));
				if(upla.getDireccion().getZona()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
						if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
							datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
						if(upla.getDireccion().getParroquia().getCanton()!=null)
							datosUPLA.put("cantonDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getId());
						if(upla.getDireccion().getParroquia()!=null)
							datosUPLA.put("parroquiaDireccionMatriz", upla.getDireccion().getParroquia().getId());		
			}
				if(upla.getDireccion().getZona().getNombre()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
						if(upla.getDireccion().getCiudad().getProvincia()!=null)
							datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getCiudad().getProvincia().getId());
						if(upla.getDireccion().getCiudad()!=null)
							datosUPLA.put("ciudadDireccionMatriz", upla.getDireccion().getCiudad().getId());		
			}
				if(upla.getDireccion().getCallePrincipal()!=null)
					datosUPLA.put("callePrincipalMatriz",upla.getDireccion().getCallePrincipal());
				if(upla.getDireccion().getNumero()!=null)
					datosUPLA.put("numeroDireccionMatriz",upla.getDireccion().getNumero());
				if(upla.getDireccion().getCalleSecundaria()!=null)
					datosUPLA.put("calleSecundariaMatriz",upla.getDireccion().getCalleSecundaria());
				if(upla.getDireccion().getDatosDeReferencia()!=null)
					datosUPLA.put("referenciaDireccionMatriz",upla.getDireccion().getDatosDeReferencia());
				if(upla.getSucursalDireccionJuridica()!=null)
					datosUPLA.put("sucursalDireccion",upla.getSucursalDireccionJuridica());
				if(upla.getSucursalCiudadJuridica()!=null)
					datosUPLA.put("sucursalCiudad",upla.getSucursalCiudadJuridica());
				if(upla.getTelefonoEmpresa()!=null)
					datosUPLA.put("telefono",upla.getTelefonoEmpresa());
				if(upla.getFaxEmpresa()!=null)
					datosUPLA.put("fax",upla.getFaxEmpresa());
				if(upla.getCliente().getActividadEconomica()!=null)
					datosUPLA.put("actividadJuridica",upla.getCliente().getActividadEconomica().getId());
				if(upla.getNombresRepresentanteLegal()!=null)
					datosUPLA.put("nombresRepresentanteLegal",upla.getNombresRepresentanteLegal());
				if(upla.getApellidosRepresentanteLegal()!=null)
					datosUPLA.put("apellidosRepresentanteLegal",upla.getApellidosRepresentanteLegal());
				if(upla.getTipoIdentificacionIdRepresentanteLegal()!=null)
					datosUPLA.put("tipoIdentificacionRepresentante",upla.getTipoIdentificacionIdRepresentanteLegal());
				if(upla.getIdentificacionRepresentanteLegal()!=null)
					datosUPLA.put("identificacionRepresentante",upla.getIdentificacionRepresentanteLegal());
				if(upla.getLugarNacimientoRepresentanteLegal()!=null)
					datosUPLA.put("lugarNacimientoRepresentante",upla.getLugarNacimientoRepresentanteLegal());
				if(upla.getFechaNacimientoRepresentanteLegal()!=null)
					datosUPLA.put("fechaNacimientoRepresentante",upla.getFechaNacimientoRepresentanteLegal());
				if(upla.getDireccionRepresentanteLegal()!=null)
					datosUPLA.put("residenciaRepresentante",upla.getDireccionRepresentanteLegal());
				datosUPLA.put("paisRepresentante","");
				if(upla.getProvinciaIdRepresentanteLegal()!=null)
					datosUPLA.put("provinciaRepresentante",upla.getProvinciaIdRepresentanteLegal());
				if(upla.getCiudadIdRepresentanteLegal()!=null)
					datosUPLA.put("ciudadRepresentante",upla.getCiudadIdRepresentanteLegal());
				if(upla.getTelefonoRepresentanteLegal()!=null)
					datosUPLA.put("telefonoRepresentante",upla.getTelefonoRepresentanteLegal());
				if(upla.getCelularRepresentanteLegal()!=null)
					datosUPLA.put("celularRepresentante",upla.getCelularRepresentanteLegal());
				datosUPLA.put("expuestoRepresentante",upla.getExpuestaPoliticamenteNatural());
				if(upla.getCargoDesempenaNatural()!=null)
					datosUPLA.put("cargoExpuesta",upla.getCargoDesempenaNatural());
				datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
				if(upla.getParentescoFamiliarExpuesto()!=null)
					datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
				if(upla.getCargoFamiliarExpuesto()!=null)
					datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
				if(upla.getApellidoPaternoConyuge()!=null)
					datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
				if(upla.getApellidoMaternoConyuge()!=null)
					datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
				if(upla.getNombreConyuge()!=null)
					datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
				if(upla.getTipoIdentificacionIdConyuge()!=null)
					datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
				if(upla.getIdentificacionConyuge()!=null)
					datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
				datosUPLA.put("salarioMensual",upla.getSalarioMensual());
				datosUPLA.put("activos",upla.getActivos());
				datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
				datosUPLA.put("pasivos",upla.getPasivos());
				datosUPLA.put("egresos",upla.getEgresos());
				datosUPLA.put("patrimonio",upla.getPatrimonio());
				datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
				datosUPLA.put("esAsegurado",upla.getEsAsegurado());
				datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
				if(upla.getTipoIdentificacionIdAsegurado()!=null)
					datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
				if(upla.getIdentificacionAsegurado()!=null)
					datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
				if(upla.getNombreCompletoAsegurado()!=null)
					datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
				if(upla.getDireccionAsegurado()!=null)
					datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
				if(upla.getTelefonoAsegurado()!=null)
					datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
				if(upla.getCelularAsegurado()!=null)
					datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
				if(upla.getRelacionAsegurado()!=null)
					datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
				if(upla.getTipoIdentificacionIdBeneficiario()!=null)
					datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
				if(upla.getIdentificacionBeneficiario()!=null)
					datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
				if(upla.getNombreBeneficiario()!=null)
					datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
				if(upla.getDireccionBeneficiario()!=null)
					datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
				if(upla.getTelefonoBeneficiario()!=null)
					datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
				if(upla.getCelularBeneficiario()!=null)
					datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
				if(upla.getRelacionBeneficiario()!=null)
					datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());
			
			}
			return datosUPLA;
	}



}

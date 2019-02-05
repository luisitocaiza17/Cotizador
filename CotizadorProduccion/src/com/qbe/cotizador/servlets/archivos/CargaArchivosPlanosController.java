package com.qbe.cotizador.servlets.archivos;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.csvreader.CsvReader;
import com.qbe.cotizador.dao.cotizacion.ArchivoCotizacionMasivosDAO;
import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.PaqueteDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.ArchivoCotizacionMasivo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Paquete;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TasaProducto;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoUso;
import com.qbe.cotizador.model.TipoVehiculo;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.transaction.archivos.ArchivoCotizacionMasivoTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ObjetoVehiculoTransaction;
import com.qbe.cotizador.util.ManejoColas;
import com.qbe.cotizador.util.ManejoFTP;
import com.qbe.cotizador.util.MotorTarifador;
import com.qbe.cotizador.util.Utilitarios;


/**
 * Servlet implementation class CargaArchivosPlanosController
 */
@WebServlet("/CargaArchivosPlanosController")
public class CargaArchivosPlanosController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  // public static String curDir = System.getProperty("glassfish.home");
  // public static String PATH_WEBSERVER = curDir + File.separatorChar+"webapps";
  // public static String PATH_DOC = PATH_WEBSERVER + File.separatorChar+ "docs"+
  // File.separatorChar+ "cotizaciones"+ File.separatorChar;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CargaArchivosPlanosController() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  /**
   * @throws IOException 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    HttpSession session = request.getSession(true);
    session.setMaxInactiveInterval(3*60*60);//(3 horas por 60 minutos por 60 segundos)
    
    // TODO Auto-generated method stub
    String nombreArchivo = null;
    String path = null;
    File file = null;
    String tipo_carga =
        request.getParameter("tipo_carga") == null ? "" : request.getParameter("tipo_carga");
    String tipoConsulta =
        request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
    String archivoCargado =
        request.getParameter("archivo") == null ? "" : request.getParameter("archivo");
    JSONObject result = new JSONObject();
    // String ajaxUpdateResult = "";
    ArchivoCotizacionMasivosDAO aDAO = new ArchivoCotizacionMasivosDAO();

    ArchivoCotizacionMasivoTransaction archivoCotizacionMasivoTransaction =
        new ArchivoCotizacionMasivoTransaction();
    /*
     * ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
     * CotizacionDetalleTransaction cotizacionDetalleTransaction = new
     * CotizacionDetalleTransaction(); CotizacionCoberturaTransaction cotizacionCoberturaTransaction
     * = new CotizacionCoberturaTransaction(); EntidadTransaction entidadTransaction = new
     * EntidadTransaction(); ClienteTransaction clienteTransaction = new ClienteTransaction();
     * CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
     */

    try {
      if (tipoConsulta==null) {
        List<FileItem> itemsInicial =
            new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (int i = 0; i < itemsInicial.size(); i++) {
          nombreArchivo = itemsInicial.get(i).getName();
          // Validamos que la carpeta exista
          String PATH_DOC = verificarCarpeta();
          path = PATH_DOC + nombreArchivo;

          file = new File(path);
          String elementos[] = itemsInicial.get(i).getFieldName().split(",");
          tipo_carga = elementos[0];
          tipoConsulta = elementos[1];
          itemsInicial.get(i).write(file);
        }
      }

      if (tipoConsulta.equals("cargar")) {

        /*
         * List<FileItem> items = new ServletFileUpload(new
         * DiskFileItemFactory()).parseRequest(request); for(int i=0;i<items.size();i++){
         * nombreArchivo = items.get(i).getName(); path = PATH_DOC+nombreArchivo; file = new
         * File(path); tipo_carga = items.get(i).getFieldName(); items.get(i).write(file);
         * 
         * }
         */

        if (!nombreArchivo.endsWith(".csv")) {
          // result.put("success", Boolean.FALSE);
          // result.put("error", "Formato del archivo incorrecto!");
          throw new Exception("Formato del archivo incorrecto!");
        }


        List<ArchivoCotizacionMasivo> registrosArchivo = aDAO.buscarRegistrosArchivo(nombreArchivo);
        if (registrosArchivo != null && registrosArchivo.size() > 0) {
          // ajaxUpdateResult = "Archivo ya existe";
          // result.put("success", Boolean.FALSE);
          // result.put("error", "Archivo ya existe");
          throw new ServletException("Archivo ya existe");
          // return;
        } else
          result.put("success", Boolean.TRUE);

        if (file.exists()) {
          String registro;
          CsvReader reader = new CsvReader(path);
          reader.setDelimiter(';');
          reader.readHeaders();
          while (reader.readRecord()) {
            ArchivoCotizacionMasivo archivo = new ArchivoCotizacionMasivo();
            registro = "";
            String error = "";

            registro = reader.get("ANIO_FABRICACION") + "|" + reader.get("PLACA") + "|"
                + reader.get("MARCA") + "|" + reader.get("MODELO") + "|" + reader.get("TIPO") + "|"
                + reader.get("VALOR_ASEGURADO").replace(" ", "").replace("$", "").replace(".", "")
                    .split(",")[0]
                + "|" + reader.get("DISPOSITIVO_SEGURIDAD") + "|" + reader.get("SUCURSAL");

            archivo.setNombreArchivo(nombreArchivo);
            // Validar Placa
            ObjetoVehiculoDAO vehiculoValidar = new ObjetoVehiculoDAO();
            if (!(reader.get("PLACA")==null)) {
              ObjetoVehiculo carro = vehiculoValidar.buscarPorPlaca(reader.get("PLACA"));
              if (carro.getChasis() != null) {
                error = "PLACA YA EXISTE";
              } else {
                WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
                String resultado = webService.getWebServiceCotizadorImplPort()
                    .obtenerDatosVehiculo(reader.get("PLACA"), "placa");
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(Utilitarios.cargarParametroWS(resultado));
                // result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
                // result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
                System.out.println("dddd" + jsonArray.getJSONObject(0).get("vigencia"));
                System.out.println("resultado" + resultado);
                error = "PLACA YA EXISTE";
              }
            }
            // Validar Marca
            MarcaDAO marcaValidar = new MarcaDAO();
            Marca marca = marcaValidar.buscarPorNombre(reader.get("MARCA"));
            if (marca.getNombre() == null)
              if (!(error==null))
                error = error + "|" + "MARCA INCORRECTA";
              else
                error = "MARCA INCORRECTA";
            // Validar Modelo
            ModeloDAO modeloValidar = new ModeloDAO();
            Modelo modelo = modeloValidar.buscarPorMarcaYNombre(marca, reader.get("MODELO"));
            if (modelo.getNombre() == null)
              modelo =
                  modeloValidar.buscarPorNombreSimilar(marca.getNombre(), reader.get("MODELO"));
            if (modelo.getNombre() == null) {
              if (!(error==null))
                error = error + "|" + "MODELO INCORRECTO";
              else
                error = "MODELO INCORRECTO";
            } else {
              if (marca.getNombre() != null && modelo.getNombre() != null) {
                if (!modelo.getMarca().getId().equals(marca.getId())) {
                  if (!(error==null))
                    error = error + "|" + "MODELO NO CORRESPONDE";
                  else
                    error = "MODELO NO CORRESPONDE";
                }
              }
            }
            // Validar Tipo Vehiculo
            TipoVehiculoDAO tipoValidar = new TipoVehiculoDAO();
            TipoVehiculo tipo = tipoValidar.buscarPorNombre(reader.get("TIPO"));
            if (tipo.getNombre() == null) {
              if (!(error==null))
                error = error + "|" + "TIPO INCORRECTO";
              else
                error = "TIPO INCORRECTO";
            }
            if (tipo_carga.equals("1")) {
              // Validar identificacion
              if (!(reader.get("IDENTIFICACION")==null)) {
                String identificacion = reader.get("IDENTIFICACION");
                boolean cedula = Utilitarios.validarCedula(identificacion);
                if (!cedula) {
                  if (!Utilitarios.validaRUC(identificacion)) {
                    if (!(error==null))
                      error = error + "|" + "IDENTIFICACION INCORRECTA";
                    else
                      error = "IDENTIFICACION INCORRECTA";
                  }
                }
              }
              registro = registro.concat("|").concat(reader.get("IDENTIFICACION")).concat("|")
                  .concat(reader.get("NOMBRES")).concat("|").concat(reader.get("APELLIDOS"));
            }
            archivo.setContenidoArchivo(registro);

            archivo.setError(error);
            archivoCotizacionMasivoTransaction.crear(archivo);
          }
          reader.close();
        }
      
      }
        // response.getWriter().print(ajaxUpdateResult);



        if (tipoConsulta.equalsIgnoreCase("consultaArchivo")) {
          String imp[] = archivoCargado.split(":?\\\\");
          int lon = imp.length;
          nombreArchivo = imp[lon - 1];

          List<ArchivoCotizacionMasivo> registros = aDAO.buscarRegistrosArchivo(nombreArchivo);

          JSONObject registrosJSONObject = new JSONObject();
          JSONArray registrosJSONArray = new JSONArray();
          int i;
          for (i = 0; i < registros.size(); i++) {

            if (registros.get(i).getId() != null) {
              String[] campos = registros.get(i).getContenidoArchivo().split("\\|");
              String anio = campos[0];
              String placa = campos[1];
              String marca = campos[2];
              String modelo = campos[3];
              String tipo = campos[4];
              String valorAsegurado = campos[5];
              String dispositivo = campos[6];
              String sucursal = campos[7];
              String identificacion = "";
              String nombres = "";
              String apellidos = "";
              if (campos.length > 8) {
                identificacion = campos[8];
                nombres = campos[9];
                apellidos = campos[10];
              }
              System.out.println("Vehiculo ("+i+") : Marca: "+ marca +" Modelo: "+modelo +" Ano Fabricacion:"+anio+" Valor Asegurado:" +valorAsegurado );
              registrosJSONObject.put("id", registros.get(i).getId());
              registrosJSONObject.put("identificacion", identificacion);
              registrosJSONObject.put("nombresI", nombres);
              registrosJSONObject.put("apellidosI", apellidos);
              registrosJSONObject.put("placa", placa);
              registrosJSONObject.put("marca", marca);
              registrosJSONObject.put("modelo", modelo);
              registrosJSONObject.put("tipo", tipo);
              registrosJSONObject.put("anioFabricacion", anio);
              registrosJSONObject.put("dispositivoSeguridad", dispositivo);
              registrosJSONObject.put("valorAsegurado", valorAsegurado);
              registrosJSONObject.put("sucursal", sucursal);
              registrosJSONObject.put("error", registros.get(i).getError());
            }

            registrosJSONArray.add(registrosJSONObject);

          }
          result.put("listadoRegistros", registrosJSONArray);
        }

        if (tipoConsulta.equalsIgnoreCase("crear")) {
          // String tipo_carga = request.getParameter("tipo_carga") == null ? "" :
          // request.getParameter("tipo_carga");
          String archivo =
              request.getParameter("archivo") == null ? "" : request.getParameter("archivo");
          String tipoobjeto =
              request.getParameter("tipoobjeto") == null ? "" : request.getParameter("tipoobjeto");
          String punto_venta = request.getParameter("punto_venta") == null ? ""
              : request.getParameter("punto_venta");
          String codigoEntidadEnsurance = request.getParameter("codigoEntidadEnsurance") == null
              ? "" : request.getParameter("codigoEntidadEnsurance");
          String tipoIdentificacion = request.getParameter("tipoIdentificacion") == null ? ""
              : request.getParameter("tipoIdentificacion");
          String identificacion = request.getParameter("identificacion") == null ? ""
              : request.getParameter("identificacion");
          String nombres =
              request.getParameter("nombres") == null ? "" : request.getParameter("nombres");
          String apellidos =
              request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos");
          String nombre_completo = request.getParameter("nombre_completo") == null ? ""
              : request.getParameter("nombre_completo");
          String vigencia =
              request.getParameter("vigencia") == null ? "" : request.getParameter("vigencia");
          String agentes =
              request.getParameter("agentes") == null ? "" : request.getParameter("agentes");
          String porc_comision = request.getParameter("porc_comision") == null ? ""
              : request.getParameter("porc_comision");
          String productos =
              request.getParameter("productos") == null ? "" : request.getParameter("productos");
          String grupo_productos = request.getParameter("grupo_productos") == null ? ""
              : request.getParameter("grupo_productos");
          String tasas = request.getParameter("tasas") == null ? "" : request.getParameter("tasas");
          String tasasValor =
              request.getParameter("tasasValor") == null ? "" : request.getParameter("tasasValor");
          String coberturaTR = request.getParameter("coberturaTR");
          String coberturaDT = request.getParameter("coberturaDT");
          String coberturaRC = request.getParameter("coberturaRC");
          String porcentajeSumaAsegurada = request.getParameter("porcentajeSumaAsegurada");
          String montoFijo = request.getParameter("montoFijo");
          String valorSiniestro = request.getParameter("valorSiniestro");
          String tasaVehiculosCerrados = request.getParameter("tasaVehiculosCerrados") == null ? ""
              : request.getParameter("tasaVehiculosCerrados");
          String valorExcesoRC = request.getParameter("valorExcesoRC") == null ? ""
              : request.getParameter("valorExcesoRC");
          String coberturasAdicionalesStr = request.getParameter("coberturasAdicionales");
          //HttpSession session = request.getSession(true);
          String paquete1_check = request.getParameter("paquete1_check");
          String paquete2_check = request.getParameter("paquete2_check");
          String paquete3_check = request.getParameter("paquete3_check");
          String paquete4_check = request.getParameter("paquete4_check");
          String paquete5_check = request.getParameter("paquete5_check");
          Usuario usuario = (Usuario) session.getAttribute("usuario");
          List<Cotizacion> cotizacionesNew = new ArrayList<Cotizacion>();
          // Validar si existe errores en archivo
          String imp[] = archivo.split(":?\\\\");
          int lon = imp.length;
          nombreArchivo = imp[lon - 1];


          List<ArchivoCotizacionMasivo> registros = aDAO.buscarRegistrosArchivo(nombreArchivo);

          /*
           * JSONObject registrosJSONObject = new JSONObject(); JSONArray registrosJSONArray = new
           * JSONArray();
           */
          int erroresArchivo = 0;
          for (int j = 0; j < registros.size(); j++) {

            if (registros.get(j).getId() != null) {
              if (!(registros.get(j).getError()==null))
                erroresArchivo = erroresArchivo + 1;
            }
          }
          if (erroresArchivo > 0) {
            result.put("success", Boolean.FALSE);
            result.put("error", "Existen registros con errores");
            /// throw new ServletException("Existen registros con errores");
          } else {
            if (tipoobjeto.equals("VHDinamico") && productos==null) {
              ProductoXPuntoVentaDAO ppvDAO = new ProductoXPuntoVentaDAO();
              GrupoPorProductoDAO gppDAO = new GrupoPorProductoDAO();
              GrupoPorProducto qpp = gppDAO.buscarPorId("148");
              PuntoVentaDAO pvDAO = new PuntoVentaDAO();
              PuntoVenta pv = pvDAO.buscarPorId(punto_venta);
              ProductoXPuntoVenta prod = ppvDAO.buscarPorGrupoPuntoVenta(qpp, pv);
              productos = prod.getId();
            }
            cotizacionesNew = crearCotizacionArchivosPlanos(tipo_carga, archivo, tipoobjeto,
                usuario, punto_venta, codigoEntidadEnsurance, tipoIdentificacion, identificacion,
                nombres, apellidos, nombre_completo, vigencia, agentes, porc_comision, productos,
                grupo_productos, tasas, tasasValor, coberturaTR, coberturaDT, coberturaRC,
                porcentajeSumaAsegurada, montoFijo, valorSiniestro, tasaVehiculosCerrados,
                valorExcesoRC, paquete1_check, paquete2_check, paquete3_check, paquete4_check,
                paquete5_check, coberturasAdicionalesStr);

            JSONObject cotizacioneJSONObject = new JSONObject();
            JSONArray cotizacioneJSONArray = new JSONArray();
            int i;
            for (i = 0; i < cotizacionesNew.size(); i++) {
              if (cotizacionesNew.get(i).getId() != null) {
                cotizacioneJSONObject.put("cotizacion", cotizacionesNew.get(i).getId());
                ClienteDAO clienteConsultar = new ClienteDAO();
                Cliente cliente =
                    clienteConsultar.buscarPorId(cotizacionesNew.get(i).getClienteId().toString());
                EntidadDAO ent = new EntidadDAO();
                Entidad entidad = ent.buscarPorId(cliente.getEntidad().getId());
                cotizacioneJSONObject.put("identificacionCliente", entidad.getIdentificacion());
                cotizacioneJSONObject.put("nombreCliente", entidad.getNombreCompleto());
              }

              cotizacioneJSONArray.add(cotizacioneJSONObject);

            }
            result.put("success", Boolean.TRUE);
            result.put("listadoCotizaciones", cotizacioneJSONArray);
          }


        }

        if (tipoConsulta.equalsIgnoreCase("eliminar")) {
          ArchivoCotizacionMasivo registroArchivo = new ArchivoCotizacionMasivo();
          String archivo_id = request.getParameter("id") == null ? "" : request.getParameter("id");
          registroArchivo = aDAO.buscarPorId(archivo_id);

          if (registroArchivo.getId() != null)
            archivoCotizacionMasivoTransaction.eliminar(registroArchivo);
        }

        if (tipoConsulta.equalsIgnoreCase("actualizar")) {
          String id = request.getParameter("id") == null ? "" : request.getParameter("id");
          String identificacionI = request.getParameter("identificacionI") == null ? ""
              : request.getParameter("identificacionI");
          String nombresI =
              request.getParameter("nombresI") == null ? "" : request.getParameter("nombresI");
          String apellidosI =
              request.getParameter("apellidosI") == null ? "" : request.getParameter("apellidosI");
          String marca = request.getParameter("marca") == null ? "" : request.getParameter("marca");
          String placa = request.getParameter("placa") == null ? "" : request.getParameter("placa");
          String modelo =
              request.getParameter("modelo") == null ? "" : request.getParameter("modelo");
          String tipo = request.getParameter("tipo") == null ? "" : request.getParameter("tipo");
          String anioFabricacion = request.getParameter("anioFabricacion") == null ? ""
              : request.getParameter("anioFabricacion");
          String dispositivoSeguridad = request.getParameter("dispositivoSeguridad") == null ? ""
              : request.getParameter("dispositivoSeguridad");
          String valorAsegurado = request.getParameter("valorAsegurado") == null ? ""
              : request.getParameter("valorAsegurado");
          String sucursal =
              request.getParameter("sucursal") == null ? "" : request.getParameter("sucursal");
          String registro;
          String error = "";

          // Validar Marca
          MarcaDAO marcaValidar = new MarcaDAO();
          Marca marcaVal = marcaValidar.buscarPorNombre(marca);
          if (marcaVal.getNombre() == null)
            error = "MARCA INCORRECTA";
          // Validar Modelo
          ModeloDAO modeloValidar = new ModeloDAO();
          Modelo modeloVal = modeloValidar.buscarPorNombre(modelo);
          if (modeloVal.getNombre() == null) {
            if (!(error==null))
              error = error + "|" + "MODELO INCORRECTO";
            else
              error = "MODELO INCORRECTO";
          } else {
            if (marcaVal.getNombre() != null && modeloVal.getNombre() != null) {
              if (modeloVal.getMarca().getId() == marcaVal.getId()) {
                if (!(error==null))
                  error = error + "|" + "MODELO NO CORRESPONDE";
                else
                  error = "MODELO NO CORRESPONDE";
              }
            }
          }
          // Validar Tipo Vehiculo
          TipoVehiculoDAO tipoValidar = new TipoVehiculoDAO();
          TipoVehiculo tipoVal = tipoValidar.buscarPorNombre(tipo);
          if (tipoVal.getNombre() == null) {
            if (!(error==null))
              error = error + "|" + "TIPO INCORRECTO";
            else
              error = "TIPO INCORRECTO";
          }
          // Validar identificacion
          if (!(identificacionI==null)) {
            boolean cedula = Utilitarios.validarCedula(identificacionI);
            if (!cedula) {
              if (!Utilitarios.validaRUC(identificacionI)) {
                if (!(error==null))
                  error = error + "|" + "IDENTIFICACION INCORRECTA";
                else
                  error = "IDENTIFICACION INCORRECTA";
              }
            }
          }

          if (error==null) {
            ArchivoCotizacionMasivo registroArchivo = new ArchivoCotizacionMasivo();

            registroArchivo = aDAO.buscarPorId(id);
            registro = anioFabricacion.concat("|").concat(placa).concat("|").concat(marca)
                .concat("|").concat(modelo).concat("|").concat(tipo).concat("|")
                .concat(valorAsegurado).concat("|").concat(dispositivoSeguridad).concat("|")
                .concat(sucursal).concat("|").concat(identificacionI);
            if (!(nombresI==null) && !(apellidosI==null)) {
              registro = registro.concat("|").concat(nombresI).concat("|").concat(apellidosI);
            }
            registroArchivo.setContenidoArchivo(registro);
            registroArchivo.setError("");
            archivoCotizacionMasivoTransaction.editar(registroArchivo);
            result.put("success", Boolean.TRUE);
            // response.setContentType("application/json; charset=ISO-8859-1");
            // result.write(response.getWriter());
          } else {
            result.put("success", Boolean.FALSE);
            result.put("error", error);
            response.getWriter().print(result);
            response.setContentType("application/json; charset=ISO-8859-1");
            return;
            // result.write(response.getWriter());
          }
          // ajaxUpdateResult = error;

        }

        response.setContentType("application/json; charset=ISO-8859-1");
        response.getWriter().print(result);
        //session.setMaxInactiveInterval(1*60*60);
        
    } catch (Exception e) {
      //session.setMaxInactiveInterval(1*60*60);
      
      e.printStackTrace();
      response.setContentType("application/json; charset=ISO-8859-1");
      result.put("success", false);
      result.put("error", e.getMessage());
      // response.getWriter().print(result);
      result.write(response.getWriter());
      return;
      // throw new ServletException("Parsing file upload failed.", e);
    }

  }

  public static List<Cotizacion> crearCotizacionArchivosPlanos(String tipoCarga, String archivo,
      String tipoObjeto, Usuario usuario, String puntoVentaId, String codigoEntidadEnsurance,
      String tipoIdentificacion, String identificacion, String nombres, String apellidos,
      String nombreCompleto, String vigenciaPoliza, String agenteId, String porcentajeComision,
      String pxpv, String grupoPorProductoId, String tasaProductoId, String tasaProductoValor,
      String coberturaTR, String coberturaDT, String coberturaRC, String porcentajeSumaAsegurada,
      String montoFijo, String valorSiniestro, String tasaVehiculosCerrados, String valorExcesoRC,
      String paquete1_check, String paquete2_check, String paquete3_check, String paquete4_check,
      String paquete5_check, String coberturasAdicionalesStr) throws Exception {

    ArchivoCotizacionMasivosDAO aDAO = new ArchivoCotizacionMasivosDAO();
    TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
    TipoObjeto tipoObjetoVehiculo = new TipoObjeto();
    GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
    TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
    GrupoPorProducto grupoPorProducto = new GrupoPorProducto();

    ArchivoCotizacionMasivoTransaction archivoCotizacionMasivoTransaction =
        new ArchivoCotizacionMasivoTransaction();
    ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
    CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
    CotizacionCoberturaTransaction cotizacionCoberturaTransaction =
        new CotizacionCoberturaTransaction();

    Cotizacion cotizacion = new Cotizacion();
    // String codigoTipoVehiculo="";
    String codigoTipoUso = "";
    List<Cotizacion> cotizacionesNew = new ArrayList<Cotizacion>();

    boolean valorRastreo = false;
    if (tipoObjeto.equalsIgnoreCase("VHDinamico")) {
      tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("1");
      // codigoTipoVehiculo="7"; //Liviano
      codigoTipoUso = "1"; // Particular
    }
    if (tipoObjeto.equalsIgnoreCase("Livianos")) {
      tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("4");
      // codigoTipoVehiculo="7"; //Liviano
      codigoTipoUso = "1"; // Particular
    }
    if (tipoObjeto.equalsIgnoreCase("Motos")) {
      tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("5");
      // codigoTipoVehiculo="9"; //Motocicleta
      codigoTipoUso = "1"; // Particular
    }
    if (tipoObjeto.equalsIgnoreCase("Pesados")) {
      tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("2");
      // codigoTipoVehiculo="18"; //Camion
      codigoTipoUso = "2"; // Transporte Pesado
    }
    if (tipoObjeto.equalsIgnoreCase("Publicos")) {
      tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("6");
      // codigoTipoVehiculo="4"; //Bus
      codigoTipoUso = "3"; // Publico
    }

    // Leer archivo cargado para crear vehiculo y detalle de la cotizacion
    String imp[] = archivo.split(":?\\\\");
    int lon = imp.length;
    archivo = imp[lon - 1];

    List<ArchivoCotizacionMasivo> registros = aDAO.buscarRegistrosArchivo(archivo);

    if (tipoCarga.equals("2")) { // Carga Grupal una sola cotizacion, varios vehiculos
      cotizacion = new Cotizacion();
      cotizacion = crearCotizacion(tipoObjeto, usuario, puntoVentaId, codigoEntidadEnsurance,
          tipoIdentificacion, identificacion, nombres, apellidos, nombreCompleto, vigenciaPoliza,
          agenteId, porcentajeComision, pxpv, grupoPorProductoId, tasaProductoId, tasaProductoValor,
          tipoObjetoVehiculo);
      cotizacionesNew.add(cotizacion);
      ArchivoCotizacionMasivo registroArchivo = new ArchivoCotizacionMasivo();

      for (int r = 0; r < registros.size(); r++) {
        registroArchivo = aDAO.buscarPorId(registros.get(r).getId());
        registroArchivo.setCotizacionId(cotizacion.getId());
        archivoCotizacionMasivoTransaction.editar(registroArchivo);
      }
    }

    int i;
    String anioVehiculo = "";
    String marcaVehiculo = "";
    // String placaVehiculo = "";
    String modeloVehiculo = "";
    String valorAseguradoVehiculo = "";
    String dispositivoVehiculo = "";
    String sucursalVehiculo = "";
    String identificacionAr = "";
    String nombresAr = "";
    String apellidosAr = "";
    String tipo = "";
    for (i = 0; i < registros.size(); i++) {
      if (registros.get(i).getId() != null) {
        String[] campos = registros.get(i).getContenidoArchivo().split("\\|");
        anioVehiculo = campos[0];
        // placaVehiculo =campos[1];
        marcaVehiculo = campos[2];
        modeloVehiculo = campos[3];
        tipo = campos[4];
        valorAseguradoVehiculo = campos[5];
        dispositivoVehiculo = campos[6];
        sucursalVehiculo = campos[7];
        if (campos.length > 8) {
          identificacionAr = campos[8];
          nombresAr = campos[9];
          apellidosAr = campos[10];
        }
      }
      if (tipoCarga.equals("1")) { // Carga Individual 1 cotizacion por registro del archivo
        String tipoIdentificacionAr = "";
        if (identificacionAr.length() == 10)
          tipoIdentificacionAr = "1";
        else if (identificacionAr.length() == 13)
          tipoIdentificacionAr = "4";
        cotizacion = new Cotizacion();
        cotizacion = crearCotizacion(tipoObjeto, usuario, puntoVentaId, "", tipoIdentificacionAr,
            identificacionAr, nombresAr, apellidosAr, "", vigenciaPoliza, agenteId,
            porcentajeComision, pxpv, grupoPorProductoId, tasaProductoId, tasaProductoValor,
            tipoObjetoVehiculo);
        // Actualizar cabecera
        ArchivoCotizacionMasivo registroArchivo = new ArchivoCotizacionMasivo();
        registroArchivo = aDAO.buscarPorId(registros.get(i).getId());
        registroArchivo.setCotizacionId(cotizacion.getId());
        archivoCotizacionMasivoTransaction.editar(registroArchivo);
        //
        cotizacionesNew.add(cotizacion);
      }
      ModeloDAO modeloDAO = new ModeloDAO();
      MarcaDAO marcaDAO = new MarcaDAO();
      Marca marcaObj = marcaDAO.buscarPorNombre(marcaVehiculo);

      Modelo modelo = modeloDAO.buscarPorMarcaYNombre(marcaObj, modeloVehiculo);
      if (modelo.getNombre() == null)
        modelo = modeloDAO.buscarPorNombreSimilar(marcaVehiculo, modeloVehiculo);


      SucursalDAO sucursalDAO = new SucursalDAO();
      Sucursal sucursal = sucursalDAO.buscarPorNombre(sucursalVehiculo);

      ColorDAO colorDAO = new ColorDAO();
      Color color = colorDAO.buscarPorId("647");

      TipoVehiculoDAO tipoVehiculoDAO = new TipoVehiculoDAO();
      TipoVehiculo tipoVehiculo = null;

      tipoVehiculo = tipoVehiculoDAO.buscarPorNombre(tipo);
      TipoUsoDAO tipoUsoDAO = new TipoUsoDAO();
      TipoUso tipoUso = tipoUsoDAO.buscarPorId(codigoTipoUso);

      Calendar calendario = Calendar.getInstance();
      java.sql.Date fechaActual = new java.sql.Date(calendario.getTime().getTime());
      java.sql.Date fechaV = new java.sql.Date(
          (new SimpleDateFormat("dd/MM/yyyy")).parse("01/01/" + anioVehiculo).getTime());
      long time = (fechaActual.getTime() - fechaV.getTime()) / (24 * 365) / (60 * 60 * 1000);
      String antiguedadVh = String.valueOf(time);

      ObjetoVehiculo vehiculo = new ObjetoVehiculo();

      if (dispositivoVehiculo.equals("S")) {
        dispositivoVehiculo = "1";
        valorRastreo = true;
      } else {
        valorRastreo = false;
        dispositivoVehiculo = "0";
      }

      vehiculo.setModelo(modelo);
      vehiculo.setColor(color);
      vehiculo.setTipoVehiculo(tipoVehiculo);
      vehiculo.setAnioFabricacion(anioVehiculo);
      vehiculo.setAntiguedadVh(antiguedadVh);
      vehiculo.setSucursalId(sucursal.getId());
      vehiculo.setTipoUso(tipoUso);
      // vehiculo.setTonelajeVehiculo(tonelajeValor);
      vehiculo.setDispositivoRastreo(valorRastreo);
      Double sumaAseguradaValor = Double.parseDouble(valorAseguradoVehiculo);
      vehiculo.setSumaAsegurada(sumaAseguradaValor);
      vehiculo.setAnosSin_Siniestro(String.valueOf("0"));
      vehiculo = objetoVehiculoTransaction.crear(vehiculo);

      CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();

      cotizacionDetalle.setCotizacion(cotizacion);
      cotizacionDetalle.setTipoObjetoId(tipoObjetoVehiculo.getId());
      cotizacionDetalle.setObjetoId(vehiculo.getId());
      cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);

      // Asignación de la coberturas del vehiculo

      int dispositivoRastreo = Integer.parseInt(dispositivoVehiculo);
      // Asignacion de la prima cotizador local

      Double valorPrimaPuraRT = 0.0;
      Double valorPrimaPuraCHT = 0.0;
      Double valorPrimaPuraRC = 0.0;
      Double valorPrimaPuraDP = 0.0;
      Double primaTasa = 0.0;

      AgenteDAO agenteDAO = new AgenteDAO();
      Agente agente = new Agente();

      if (!agenteId.equals(""))
        agente = agenteDAO.buscarPorId(agenteId);

      VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
      List<String> variableSistemaList = new ArrayList<String>();
      variableSistemaList.add("TARIFICADOR_LOCAL");
      String tarificadorLocal = variableSistemaDAO.buscarPorNombres(variableSistemaList).get(0);
      Double totalTodoRisgo = 0.0;
      Double totalDanioTotal = 0.0;
      Double totalResponsabilidadCivil = 0.0;

      double primaNoAfectaMonto = 0;
      // Verificamos valores de prima
      if (tipoObjeto.equalsIgnoreCase("VHDinamico")) {

        if (tarificadorLocal.equalsIgnoreCase("1")) {
          if (coberturaTR.equalsIgnoreCase("true")) {
            VariableSistema vs =variableSistemaDAO.buscarPorNombre("ANIOS_LIMITE_TR");
            
            if(fechaActual.getYear()-(Integer.parseInt(anioVehiculo))>Integer.parseInt(vs.getValor()))
              throw new Exception("Existe un vehiculo con más de "+vs.getValor()+" años, no puede escoger Todo Riesgo");
            
            valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal,
                Integer.parseInt(anioVehiculo), dispositivoRastreo, modelo);
            valorPrimaPuraCHT =
                MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
            valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor,
                sucursal, Integer.parseInt(anioVehiculo), modelo);
            valorPrimaPuraDP = MotorTarifador.calcularPrimaDanoParcial(sumaAseguradaValor, sucursal,
                Integer.parseInt(anioVehiculo), modelo, Double.parseDouble(montoFijo),
                Double.parseDouble(valorSiniestro), Double.parseDouble(porcentajeSumaAsegurada));
            totalTodoRisgo +=
                MotorTarifador.calcularPrimaTasaTodoRiesgo(valorPrimaPuraRT, valorPrimaPuraCHT,
                    valorPrimaPuraRC, valorPrimaPuraDP, agente.getComisionVh(), sumaAseguradaValor);
            primaTasa += totalTodoRisgo;
          }

          if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("false")) {
            valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal,
                Integer.parseInt(anioVehiculo), dispositivoRastreo, modelo);
            valorPrimaPuraCHT =
                MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
            totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT,
                valorPrimaPuraCHT, agente.getComisionVh(), sumaAseguradaValor);
            primaTasa += totalDanioTotal;

          }

          if (coberturaRC.equalsIgnoreCase("true") && coberturaDT.equalsIgnoreCase("false")) {
            valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor,
                sucursal, Integer.parseInt(anioVehiculo), modelo);
            totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(
                valorPrimaPuraRC, agente.getComisionVh(), sumaAseguradaValor);
            primaTasa += totalResponsabilidadCivil;
          }

          if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")) {
            valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal,
                Integer.parseInt(anioVehiculo), dispositivoRastreo, modelo);
            valorPrimaPuraCHT =
                MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
            totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT,
                valorPrimaPuraCHT, agente.getComisionVh(), sumaAseguradaValor);

            valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor,
                sucursal, Integer.parseInt(anioVehiculo), modelo);
            totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(
                valorPrimaPuraRC, agente.getComisionVh(), sumaAseguradaValor);
            primaNoAfectaMonto += totalResponsabilidadCivil;
            primaTasa += totalDanioTotal;
          }
        } else {

          List<ObjetoVehiculo> listadoVehiculos = new ArrayList<ObjetoVehiculo>();
          listadoVehiculos.add(vehiculo);
          String xml = Utilitarios.generarEstructuraXMLVH(cotizacion, listadoVehiculos);
          ManejoColas.productorMensajes(xml);
          System.out.println(xml);
          ManejoColas.consumiMensajes();
          ManejoFTP.subirXMLFTP(xml, cotizacion.getId());
        }
      } else {
        //
        int numeroCoberturasPrincipales = 0;
        if (coberturaDT.equalsIgnoreCase("true")) {
          numeroCoberturasPrincipales += 1;
        }
        if (coberturaRC.equalsIgnoreCase("true")) {
          numeroCoberturasPrincipales += 1;
        }
        if (coberturaTR.equalsIgnoreCase("true")) {
          numeroCoberturasPrincipales += 1;
        }


        // GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
        grupoPorProducto =
            grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());
        // Verificamos si la tasa es fija, formulada o tiene tasa variable
        if (grupoPorProducto.getTasaFija() && grupoPorProducto.getFormulada() == false) { // Tasa
          // productos
          // tasa
          // fija
          primaTasa = (grupoPorProducto.getPorcentajeTasaFija() * sumaAseguradaValor) / 100;
        }
        if (grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada() == false) { // Tasa
          // productos
          // tasa
          // varios
          // valores
          // TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
          TasaProducto tasaProducto = new TasaProducto();
          tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());
          primaTasa = (tasaProducto.getPorcentajeCasco() * sumaAseguradaValor) / 100;
          // porcentajeExtras = Double.parseDouble(resultado.get(key).toString());
        }
        if (grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada()) { // Tasa
          // productos
          // formulados
          primaTasa = (Double.parseDouble(tasaVehiculosCerrados) * sumaAseguradaValor) / 100;
        }

        totalResponsabilidadCivil = primaTasa / numeroCoberturasPrincipales;
        totalDanioTotal = primaTasa / numeroCoberturasPrincipales;
        totalTodoRisgo = primaTasa / numeroCoberturasPrincipales;
      }

      /* EXTRAS */
      // double sumaExtrasTodosAnios=0.0;
      // double sumaExtras=0.0;
      double sumaVehiculoTodosAnios = 0.0;
      double sumaVehiculo = sumaAseguradaValor;
      double tasa = primaTasa / sumaAseguradaValor;
      // double primaTotalExtras=0.0;
      double primaTotalVehiculo = 0.0;

      // primaTotalExtras=sumaExtrasTodosAnios*tasa;

      // tasa minima evaldez

      Double tasaMinima =
          Double.parseDouble(variableSistemaDAO.buscarPorNombre("TASA_MINIMA").getValor());

      // entra solo cuando la tasa calculada es menor a la minima
      if (tipoObjeto.equals("VHDinamico") && tasa < tasaMinima
          && coberturaTR.equalsIgnoreCase("true")) { // solo aplica a todo riesgo de dinamicos
        // evaldez
        tasa = tasaMinima;
        Double primaTasaAnterior = primaTasa;
        primaTasa = vehiculo.getSumaAsegurada() * tasa;
        totalTodoRisgo = primaTasa * totalTodoRisgo / primaTasaAnterior;
        totalResponsabilidadCivil = primaTasa * totalResponsabilidadCivil / primaTasaAnterior;
        totalDanioTotal = primaTasa * totalDanioTotal / primaTasaAnterior;

      }

      // cuando se solicita aplicar tasa minima ssiempre que sea mayor a la tasa calculada
      if (tipoObjeto.equals("VHDinamico") && cotizacion.getTasaMinima() != 0
          && cotizacion.getTasaMinima() > tasa) {
        tasa = cotizacion.getTasaMinima();
        Double primaTasaAnterior = primaTasa;
        primaTasa = vehiculo.getSumaAsegurada() * tasa;
        totalTodoRisgo = primaTasa * totalTodoRisgo / primaTasaAnterior;
        totalResponsabilidadCivil = primaTasa * totalResponsabilidadCivil / primaTasaAnterior;
        totalDanioTotal = primaTasa * totalDanioTotal / primaTasaAnterior;
      }

      /*
       * String[] arrValoresAsegurados = sumaAseguradaArr.split("[|]",0); for(int j=1;
       * j<arrValoresAsegurados.length; j++){ Double valorAseguradoDepreciado = new
       * Double(arrValoresAsegurados[j]); sumaVehiculoTodosAnios+=valorAseguradoDepreciado; }
       */
      sumaVehiculoTodosAnios += sumaVehiculo;
      primaTotalVehiculo += sumaVehiculoTodosAnios * tasa;

      CotizacionCoberturaDAO cotizacionCoberturaDAO = new CotizacionCoberturaDAO();

      if (cotizacion.getNumeroFactura() == null)
        cotizacionCoberturaDAO.eliminarPorCotizacionDetalle(cotizacionDetalle);
      cotizacionDetalle.setCotizacionCoberturas(null);
      CoberturaDAO coberturaDAO = new CoberturaDAO();
      List<CotizacionCobertura> listaCoberturas = new ArrayList<CotizacionCobertura>();

      cotizacionDetalle.setTasa(tasa);
      // if(necesitaInspeccion!=null&&!necesitaInspeccion.equals("")){
      // if(necesitaInspeccion.equals("true"))
      // cotizacionDetalle.setNecesitaInspeccion(true);
      // else
      cotizacionDetalle.setNecesitaInspeccion(false);
      // }
      /*******************************************
       * INICIO COBERTURAS PRINCIPALES
       ****************************/

      if (coberturaTR.equalsIgnoreCase("true")) {
        CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
        cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);

        Cobertura coberturaTRObjeto = new Cobertura();
        // Grabar la cobertura de todo riesgo segun el tipo de vehiculo
        if (tipoObjeto.equalsIgnoreCase("Livianos") || tipoObjeto.equalsIgnoreCase("Motos")
            || tipoObjeto.equalsIgnoreCase("Publicos")
            || tipoObjeto.equalsIgnoreCase("VHDinamico")) {
          coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TORI");
        }
        if (tipoObjeto.equalsIgnoreCase("Pesados")) {
          coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TRCE");
        }

        cotizacionCobertura.setCobertura(coberturaTRObjeto);
        cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
        cotizacionCobertura.setMontoFijo(Double.parseDouble(montoFijo));
        cotizacionCobertura.setPorcentajeValorSiniestro(Double.parseDouble(valorSiniestro));
        // CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
        cotizacionCobertura.setValorPrima(primaTotalVehiculo * totalTodoRisgo / primaTasa);
        cotizacionCobertura.setValorPrimaOrigen(primaTotalVehiculo * totalTodoRisgo / primaTasa);
        // cotizacionCobertura.setValorMonto((sumaAseguradaValor)*((totalTodoRisgo)/primaTasa));
        listaCoberturas.add(cotizacionCobertura);
        // cotizacionCoberturaDAO.crear(cotizacionCobertura);
      }

      if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")) {
        CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
        cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);

        // perdida total es la suma de daño total y robo total
        Cobertura coberturaDTObjeto = coberturaDAO.buscarPorNemotecnico("DATO");
        cotizacionCobertura.setCobertura(coberturaDTObjeto);
        if (!porcentajeSumaAsegurada.equals(""))
          cotizacionCobertura
              .setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
        else
          cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble("1"));
        cotizacionCobertura.setMontoFijo(0);
        cotizacionCobertura.setPorcentajeValorSiniestro(0);
        // CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
        cotizacionCobertura.setValorPrima(primaTotalVehiculo * totalDanioTotal / primaTasa);
        // cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalDanioTotal)/primaTasa));
        listaCoberturas.add(cotizacionCobertura);
        // cotizacionCoberturaDAO.crear(cotizacionCobertura);
        // responsabilidad civil
        cotizacionCobertura = new CotizacionCobertura();
        cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
        Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("RECI");
        cotizacionCobertura.setCobertura(coberturaTRObjeto);
        cotizacionCobertura.setPorcentajeSumaAsegurada(0);
        cotizacionCobertura.setMontoFijo(0);
        cotizacionCobertura.setPorcentajeValorSiniestro(0);
        // CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
        cotizacionCobertura
            .setValorPrima(primaTotalVehiculo * totalResponsabilidadCivil / primaTasa);
        // cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalResponsabilidadCivil)/primaTasa));
        listaCoberturas.add(cotizacionCobertura);
        // cotizacionCoberturaDAO.crear(cotizacionCobertura);

      }

      if (coberturaDT.equalsIgnoreCase("false") && coberturaRC.equalsIgnoreCase("true")) {
        CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
        cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);

        Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("RECI");
        cotizacionCobertura.setCobertura(coberturaTRObjeto);
        cotizacionCobertura.setPorcentajeSumaAsegurada(0);
        cotizacionCobertura.setMontoFijo(0);
        cotizacionCobertura.setPorcentajeValorSiniestro(0);
        // CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
        cotizacionCobertura
            .setValorPrima(primaTotalVehiculo * totalResponsabilidadCivil / primaTasa);
        // cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalResponsabilidadCivil)/primaTasa));
        listaCoberturas.add(cotizacionCobertura);
        // cotizacionCoberturaDAO.crear(cotizacionCobertura);
      }

      if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("false")) {
        CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
        cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);

        Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("DATO");
        cotizacionCobertura.setCobertura(coberturaTRObjeto);
        cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
        cotizacionCobertura.setMontoFijo(0);
        cotizacionCobertura.setPorcentajeValorSiniestro(0);
        // CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
        cotizacionCobertura.setValorPrima(primaTotalVehiculo * totalDanioTotal / primaTasa);
        // cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalDanioTotal)/primaTasa));
        listaCoberturas.add(cotizacionCobertura);
        // cotizacionCoberturaDAO.crear(cotizacionCobertura);
      }

      /*******************************************
       * FIN COBERTURAS PRINCIPALES
       ****************************/

      /*******************************************
       * INICIO COBERTURAS ADICIONALES
       ****************************/

      // coberturas adicionales evaldez
      double primaAdicionales = 0.0;
      String[] coberturasAdicionales = coberturasAdicionalesStr.split(",");
      int vigenciaCotizacion = cotizacion.getVigenciaPoliza().getValor().intValue();
      double primaAfectaMonto = primaTotalVehiculo;
      // primaNoAfectaMonto+=primaTotalExtras;

      if (coberturasAdicionales.length >= 1 && !coberturasAdicionalesStr.equals(""))
        for (int ii = 0; ii < coberturasAdicionales.length; ii++) {
          CotizacionCobertura ccAdicional = new CotizacionCobertura();
          Cobertura adicional = coberturaDAO.buscarPorId(coberturasAdicionales[ii]);
          if (!adicional.getId().toString().equals("6348540415022")
              && !adicional.getId().toString().equals("6349173767914")) {
            if (adicional.getTipoTasa().getId().equals("1")) {
              ccAdicional.setCobertura(adicional);
              ccAdicional.setCotizacionDetalle(cotizacionDetalle);
              ccAdicional.setMontoFijo(0);
              ccAdicional.setPorcentajeSumaAsegurada(0);
              ccAdicional.setPorcentajeValorSiniestro(0);
              ccAdicional.setValorPrima(vigenciaCotizacion * adicional.getTasaValor());
              ccAdicional.setValorPrimaOrigen(vigenciaCotizacion * adicional.getTasaValor());
              if (adicional.getAfectaValorAsegurado().equals("1")) {
                primaAfectaMonto += adicional.getTasaValor() * vigenciaCotizacion;
                primaAdicionales += adicional.getTasaValor() * vigenciaCotizacion;
              } else {
                primaNoAfectaMonto += adicional.getTasaValor() * vigenciaCotizacion;
                primaAdicionales += adicional.getTasaValor() * vigenciaCotizacion;
              }

              listaCoberturas.add(ccAdicional);
              // cotizacionCoberturaDAO.crear(ccAdicional);
              // primaTasa += adicional.getTasaValor();
            }
            if (adicional.getTipoTasa().getId().equals("2")) {
              ccAdicional.setCobertura(adicional);
              ccAdicional.setCotizacionDetalle(cotizacionDetalle);
              ccAdicional.setMontoFijo(0);
              ccAdicional.setPorcentajeSumaAsegurada(0);
              ccAdicional.setPorcentajeValorSiniestro(0);
              ccAdicional.setValorPrima(vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100);
              ccAdicional.setValorPrimaOrigen(vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100);
              if (adicional.getAfectaValorAsegurado().equals("1")) {
                primaAfectaMonto +=vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100;
                primaAdicionales +=vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100;
              } else {
                primaNoAfectaMonto +=
                    vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100;
                primaAdicionales +=
                    vigenciaCotizacion * adicional.getTasaValor() * (sumaVehiculoTodosAnios) / 100;
              }
              listaCoberturas.add(ccAdicional);
              // cotizacionCoberturaDAO.crear(ccAdicional);
              // primaTasa += (adicional.getTasaValor() * vehiculo.getSumaAsegurada()/100);
            }
          } else {
            ccAdicional.setCobertura(adicional);
            ccAdicional.setCotizacionDetalle(cotizacionDetalle);
            ccAdicional.setMontoFijo(0);
            ccAdicional.setPorcentajeSumaAsegurada(0);
            ccAdicional.setPorcentajeValorSiniestro(0);
            ccAdicional.setValorPrima(adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100);
            ccAdicional.setValorPrimaOrigen(adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100);
            ccAdicional.setValorMonto(Double.parseDouble(valorExcesoRC));
            if (adicional.getAfectaValorAsegurado().equals("1")) {
              primaAfectaMonto +=adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100;
              primaAdicionales +=adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100;
            } else {
              primaNoAfectaMonto +=adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100;
              primaAdicionales +=adicional.getTasaValor() * Double.parseDouble(valorExcesoRC) / 100;
            }

            listaCoberturas.add(ccAdicional);
            // cotizacionCoberturaDAO.crear(ccAdicional);
            // primaTasa += adicional.getTasaValor();
          }
        }

      /*******************************************
       * FIN COBERTURAS ADICIONALES
       ****************************/


      for (int k = 0; k < listaCoberturas.size(); k++) {
        CotizacionCobertura ccAGrabar = listaCoberturas.get(k);
        if (!ccAGrabar.getCobertura().getId().toString().equals("6348540415022")
            && !ccAGrabar.getCobertura().getId().toString().equals("6349173767914")) {
          if (ccAGrabar.getCobertura().getAfectaValorAsegurado().equals("1"))
            // ccAGrabar.setValorMonto((sumaAseguradaTotal)*ccAGrabar.getValorPrima()/totalPrimaAfectaMonto);
            ccAGrabar.setValorMonto(
                (sumaAseguradaValor) * (ccAGrabar.getValorPrima() / (primaAfectaMonto)));
          else
            ccAGrabar.setValorMonto(0);
        }
        // ccAGrabar.setValorPrima(primaAfectaMonto*ccAGrabar.getValorPrima()/primaAfectaMonto);
        if (cotizacion.getNumeroFactura() == null)
          cotizacionCoberturaTransaction.crear(ccAGrabar);
      }

      double primaTotal = primaAfectaMonto + primaNoAfectaMonto;
      // tasa = primaTotal / sumaVehiculoTodosAnios;

      if (tipoObjeto.equalsIgnoreCase("VHDinamico")) {
        // if(tasa>cotizacion.getTasaMinima()){
        cotizacionDetalle.setPrimaNetaItem(primaTotal);
        cotizacionDetalle.setPrimaNetaItemOrigen(primaTotal);
        cotizacionDetalle.setTasa(tasa);
        cotizacionDetalle.setTasaOrigen(tasa);
        /*
         * }else{ primaTotal = cotizacion.getTasaMinima() * vehiculo.getSumaAsegurada();
         * cotizacionDetalle.setPrimaNetaItem(primaTotal);
         * cotizacionDetalle.setTasa(cotizacion.getTasaMinima()); }
         */
        cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada());
        // cotizacionDetalle.setValorExtras(sumaExtras);
      } else {
        Double sumaExtras = 0.0;
        if (grupoPorProducto.getTasaFija() == false) {
          tasaProductoDAO = new TasaProductoDAO();
          TasaProducto tasaProducto = new TasaProducto();
          Double nuevaSumaAseguradaConExtras = 0.0;
          Double nuevoPrimaConExtras = 0.0;
          Double nuevaTasaConExtras = 0.0;
          Double sumaExtrasTodosAnios = 0.0;
          // Double sumaExtras = 0.0;
          // Double nuevaTasaConExtras = 0.0;

          // Calculo de tasa para vehiculos tasa variable
          if (cotizacion.getTasaProductoId().bitLength() != 0) {
            tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());
            // Recalculo de los datos agregando los extras
            nuevaSumaAseguradaConExtras = sumaExtrasTodosAnios + sumaVehiculoTodosAnios;
            nuevoPrimaConExtras =
                ((tasaProducto.getPorcentajeExtras() / 100) * sumaExtrasTodosAnios)
                    + (tasa * sumaVehiculoTodosAnios);
            nuevaTasaConExtras = (nuevoPrimaConExtras * 100) / nuevaSumaAseguradaConExtras;
            cotizacionDetalle.setPrimaNetaItem(nuevoPrimaConExtras + primaAdicionales);
            cotizacionDetalle.setPrimaNetaItemOrigen(nuevoPrimaConExtras + primaAdicionales);
            cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada() + sumaExtras);
            cotizacionDetalle.setTasa(nuevaTasaConExtras / 100);
            cotizacionDetalle.setTasaOrigen(nuevaTasaConExtras / 100);
            cotizacionDetalle.setValorExtras(sumaExtras);
          } else {
            nuevaSumaAseguradaConExtras = sumaExtrasTodosAnios + sumaVehiculoTodosAnios;
            nuevoPrimaConExtras =
                ((tasa / 100) * sumaExtrasTodosAnios) + (tasa * sumaVehiculoTodosAnios);
            nuevaTasaConExtras = (nuevoPrimaConExtras * 100) / nuevaSumaAseguradaConExtras;
            cotizacionDetalle.setPrimaNetaItem(nuevoPrimaConExtras + primaAdicionales);
            cotizacionDetalle.setPrimaNetaItemOrigen(nuevoPrimaConExtras + primaAdicionales);
            cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada() + sumaExtras);
            cotizacionDetalle.setTasa(nuevaTasaConExtras / 100);
            cotizacionDetalle.setTasaOrigen(nuevaTasaConExtras / 100);
            cotizacionDetalle.setValorExtras(sumaExtras);
          }

        } else {
          cotizacionDetalle.setPrimaNetaItem(primaTotal);
          cotizacionDetalle.setPrimaNetaItemOrigen(primaTotal);
          cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada() + sumaExtras);
          cotizacionDetalle.setTasa(tasa);
          cotizacionDetalle.setTasaOrigen(tasa);
          cotizacionDetalle.setValorExtras(sumaExtras);
        }
      }


      // se guarda el paquete
      PaqueteDAO paqueteDAO = new PaqueteDAO();

      Paquete paquete = new Paquete();
      if (paquete1_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("1");
      }
      if (paquete2_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("2");
      }
      if (paquete3_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("3");
      }
      if (paquete4_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("4");
      }
      if (paquete5_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("5");
      }
      if (paquete5_check.equalsIgnoreCase("true")) {
        paquete = paqueteDAO.buscarPorId("5");
      }

      if (paquete.getId() != null)
        cotizacionDetalle.setPaqueteId(BigInteger.valueOf(Long.parseLong(paquete.getId())));
      else
        cotizacionDetalle.setPaqueteId(null);

      // evaldez se controla que exista

      if (cotizacionDetalle.getId() != null && cotizacionDetalle.getCotizacion() != null
          && cotizacionDetalle.getCotizacion().getId() != null) {
        // cotizacionDetalle.setId(cotizacionDetalleExiste.getId());
        if (cotizacion.getNumeroFactura() == null)
          cotizacionDetalle = cotizacionDetalleTransaction.editar(cotizacionDetalle);
      } else if (cotizacion.getNumeroFactura() == null)
        cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
    }
    // Cotizacion detalle
    return cotizacionesNew;
  }

  public static Cotizacion crearCotizacion(String tipoObjeto, Usuario usuario, String puntoVentaId,
      String codigoEntidadEnsurance, String tipoIdentificacion, String identificacion,
      String nombres, String apellidos, String nombreCompleto, String vigenciaPoliza,
      String agenteId, String porcentajeComision, String pxpv, String grupoPorProductoId,
      String tasaProductoId, String tasaProductoValor, TipoObjeto tipoObjetoVehiculo) {

    EntidadTransaction entidadTransaction = new EntidadTransaction();
    ClienteTransaction clienteTransaction = new ClienteTransaction();
    CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();

    // String error = "";

    Cotizacion cotizacion = new Cotizacion();

    EntidadDAO entidadDAO = new EntidadDAO();
    Entidad entidad = new Entidad();

    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente cliente = new Cliente();

    TipoIdentificacionDAO tipoDAO = new TipoIdentificacionDAO();

    entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);

    if (!identificacion.equals(""))
      entidad.setIdentificacion(identificacion);

    if (!codigoEntidadEnsurance.equals(""))
      entidad.setEntEnsurance(codigoEntidadEnsurance);

    if (!tipoIdentificacion.equals("")) {
      TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
      entidad.setTipoIdentificacion(tipoDAO.buscarPorId(tipoIdentificacion));
      if (tipoDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")) {
        entidad.setNombres("");
        entidad.setApellidos("");
        entidad.setNombreCompleto(nombreCompleto.toUpperCase());
        entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));
      } else {
        entidad.setNombres(nombres.toUpperCase());
        entidad.setApellidos(apellidos.toUpperCase());
        entidad.setNombreCompleto(nombres.toUpperCase() + " " + apellidos.toUpperCase());
        entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
      }
    }

    if (entidad.getId() == null)
      entidad = entidadTransaction.crear(entidad);
    else
      entidad = entidadTransaction.editar(entidad);

    cliente = clienteDAO.buscarPorEntidadId(entidad);

    if (cliente.getId() == null) {
      ActividadEconomica actividad = new ActividadEconomica();
      ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
      actividad = actividadDAO.buscarPorNombre("Ninguno");
      Cliente clienteNuevo = new Cliente();
      clienteNuevo.setEntidad(entidad);
      clienteNuevo.setActividadEconomica(actividad);
      clienteNuevo.setActivo(true);
      cliente = clienteTransaction.crear(clienteNuevo);
      // falta en codigo del ensurance
    }

    EstadoDAO estadoDAO = new EstadoDAO();
    PuntoVentaDAO pvDAO = new PuntoVentaDAO();
    VigenciaPolizaDAO vpDAO = new VigenciaPolizaDAO();
    TipoObjetoDAO toDAO = new TipoObjetoDAO();

    if (!puntoVentaId.equals(""))
      cotizacion.setPuntoVenta(pvDAO.buscarPorId(puntoVentaId));

    if (!pxpv.equals(""))
      cotizacion.setProductoXPuntoVentaId(BigInteger.valueOf(Long.parseLong(pxpv)));

    if (!vigenciaPoliza.equals(""))
      cotizacion.setVigenciaPoliza(vpDAO.buscarPorId(vigenciaPoliza));

    if (!agenteId.equals(""))
      cotizacion.setAgenteId(BigInteger.valueOf(Long.valueOf(agenteId)));

    if (!porcentajeComision.equals(""))
      cotizacion.setPorcentajeComision(Double.parseDouble(porcentajeComision)); // Porcentaje
    // comision agente

    cotizacion.setVigenciaDesde(new Date());
    cotizacion.setClienteId(BigInteger.valueOf(Long.valueOf(cliente.getId())));
    if (cotizacion.getAsegurado() == null)
      cotizacion.setAsegurado(cliente.getEntidad());

    GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
    TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();

    if(tasaProductoValor.length()==0){
    	tasaProductoValor="0";
    }
    	
    GrupoPorProducto grupoPorProducto = new GrupoPorProducto();

    if (tipoObjeto.equalsIgnoreCase("VHDinamico")) {
      grupoPorProducto = grupoPorProductoDAO.buscarPorId("148");
      cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
      cotizacion.setGrupoProductoId(
          BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
      cotizacion.setTipoObjeto(toDAO.buscarPorId("1"));
      cotizacion.setProducto(grupoPorProducto.getProducto());
    } else {
      if (!grupoPorProductoId.equals("")) {
        grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
        cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
        cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
        cotizacion.setProducto(grupoPorProducto.getProducto());
        // Agregamos la tasa
        if (tasaProductoId.length() == 0 ) {
          cotizacion.setTasaProductoId(new BigInteger("0"));
          // Valor por defecto productos formulados
          if (tasaProductoValor.equalsIgnoreCase("Formulada"))
            cotizacion.setTasaProductoValor(new Double(0));
          else
            cotizacion.setTasaProductoValor(new Double(tasaProductoValor));
        } else {
          cotizacion.setTasaProductoId(BigInteger.valueOf(Long.valueOf(tasaProductoId)));
          TasaProducto tasaProducto = tasaProductoDAO.buscarPorId(tasaProductoId);
          cotizacion.setTasaProductoValor(tasaProducto.getPorcentajeCasco());
        }
      }
    }

    cotizacion.setTipoObjeto(tipoObjetoVehiculo);
    cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador", "Cotizacion"));

    if (cotizacion.getUsuario() == null) {
      cotizacion.setUsuario(usuario);
      Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
      if (!fechaActual.equals(""))
        cotizacion.setFechaElaboracion(fechaActual);
    }

    if (cotizacion.getEtapaWizard() < 1) {
      cotizacion.setEtapaWizard(3);
    }

    return cotizacion = cotizacionTransaction.crear(cotizacion);
  }

  public String verificarCarpeta() {
    String retorno = "";
    try {
      String os = System.getProperty("os.name");
      File folder = null;

      if (os.toLowerCase().indexOf("win") >= 0) {
        folder = new File("C:\\CargaMasiva");
        retorno = "C:\\CargaMasiva\\";
      } else {
        folder = new File("/home/CargaMasiva");
        retorno = "/home/CargaMasiva/";
      }

      if (!folder.exists())
        folder.mkdir();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retorno;

  }

}

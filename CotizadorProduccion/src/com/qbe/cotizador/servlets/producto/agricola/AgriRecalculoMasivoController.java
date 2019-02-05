package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;

/**
 * Servlet implementation class AgriRecalculoMasivoController
 */
@WebServlet("/AgriRecalculoMasivoController")
public class AgriRecalculoMasivoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriRecalculoMasivoController() {
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
				String proceso = request.getParameter("proceso") == null ? "":request.getParameter("proceso");
				System.out.println("Proceso Masivo: "+proceso);
				
				String rutaPlantilla = this.getServletContext().getRealPath("") + ""+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano"+File.separator+""+nombreArchivo;
				System.out.println("rutaRelativaReporte almacenada: " + rutaPlantilla);
				List<AgriRecotizacionMasivas> cotizacionList = new ArrayList<AgriRecotizacionMasivas>();
				
				JSONArray listCotError = new JSONArray();
				
				if(proceso.equals("RecalculoMasivo")){
					if(nombreArchivo.endsWith(".xlsx")){
						cotizacionList = ReadExelFile.readXLSXFileRecMas(rutaPlantilla);		
					}else if(nombreArchivo.endsWith(".xls")){
						cotizacionList = ReadExelFile.readXLSXFileRecMas(rutaPlantilla);					 
					}
					listCotError = ProcesoRecalculo(cotizacionList);
				}else{
					if(proceso.equals("Lugares")){
						if(nombreArchivo.endsWith(".xlsx")){
							cotizacionList = ReadExelFile.readXLSXFileLug(rutaPlantilla);		
						}else if(nombreArchivo.endsWith(".xls")){
							cotizacionList = ReadExelFile.readXLSXFileLug(rutaPlantilla);				 
						}
						listCotError = ProcesoLugares(cotizacionList);
					}else{
						if(nombreArchivo.endsWith(".xlsx")){
							cotizacionList = ReadExelFile.readXLSXFileFecha(rutaPlantilla);		
						}else if(nombreArchivo.endsWith(".xls")){
							cotizacionList = ReadExelFile.readXLSXFileFecha(rutaPlantilla);				 
						}
						listCotError = ProcesoFechas(cotizacionList);
					}
					
				}
				
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
	
	//Procesamos las cotizaciones
	public JSONArray ProcesoRecalculo(List<AgriRecotizacionMasivas> cotizacionList){
		
		JSONArray resultado = new JSONArray();
		JSONObject individual= new JSONObject();
		for(AgriRecotizacionMasivas rs :cotizacionList){
			try{
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion = cotizacionDAO.buscarPorId(rs.getIdCotizacion());
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
				
				//buscamos variables de calculo
				VariableSistemaDAO  variableSistemaDAO = new VariableSistemaDAO();
				VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_AGRICOLA");
				
				double derechoEmision=0;
				if(!agriObjetoCotizacion.getTipoOrigen().equalsIgnoreCase("SUCRE"))
					derechoEmision = Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("SEGURO_CAMPESINO");
				
				double seguroCampesino = Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("SUPER_DE_BANCOS");
				
				double superBancos = Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("IVA");
				
				double PorcentajeIva=14;
				
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia=provinciaDAO.buscarPorId(agriObjetoCotizacion.getProvinciaId().toString());
				
				
				if(provincia.getNombre().equals("MANABI")||provincia.getNombre().equals("ESMERALDAS")){
					 PorcentajeIva=12;
	           	}else{
	           		 PorcentajeIva=  Double.parseDouble(variableSistema.getValor());
	           	}
				
				double costoProduccion=Double.parseDouble(rs.getCostoProduccion());
				double tasa=Double.parseDouble(rs.getTasa());
				//Calculos
				double NumHectareas=Double.parseDouble(rs.getNumeroHectareas());
				double NumHectareasTotales=agriObjetoCotizacion.getHectareasLote();
				if(NumHectareas>NumHectareasTotales)
					throw new Exception("El numero de hectareas asegurables no puedes ser mayor al número de hectareas del lote de la cotización");
				
				double SumaAsegurada=costoProduccion*NumHectareas;
				double primaNeta=SumaAsegurada*tasa/100;
				double ImpSeguroCampesino=(primaNeta*seguroCampesino/100*100)/100;
				double ImpSuperBancos=(primaNeta*superBancos/100*100)/100;
				double ImpIva=((primaNeta+ImpSeguroCampesino+ImpSuperBancos+derechoEmision)* PorcentajeIva / 100 * 100) / 100;
				double PrimaTotal=primaNeta+ImpSeguroCampesino+ImpSuperBancos+ImpIva+derechoEmision;
				
				
				/***PROCESOS DE RECALCULO DE VALORES DE LAS COTIZACIONES***/
				agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccion));
				agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(rs.getNumeroHectareas()));
				cotizacion.setTasaProductoValor(tasa);
				cotizacionDetalle.setTasa(tasa);
				cotizacionDetalle.setTasaOrigen(tasa); 
				cotizacion.setSumaAseguradaTotal(SumaAsegurada);
				cotizacion.setPrimaNetaTotal(""+primaNeta);
				cotizacion.setPrimaOrigen(primaNeta);
				cotizacion.setImpSeguroCampesino(ImpSeguroCampesino);
				cotizacion.setImpSuperBancos(ImpSuperBancos);
				cotizacion.setImpDerechoEmision(derechoEmision);
				cotizacion.setImpIva(ImpIva);
				cotizacion.setTotalFactura(PrimaTotal);
				
				//Si todo esta correcto actualizo la cotizacion
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				cotizacionTransaction.editar(cotizacion);
				CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
				cotizacionDetalleTransaction.editar(cotizacionDetalle);
				AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
				agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
				individual.put("id", rs.getIdCotizacion().toString());
				individual.put("observacion", "Proceso de Recalculo Correcto");
			}catch(Exception e){
				individual.put("id", rs.getIdCotizacion().toString());
				individual.put("observacion", e.getMessage());
			}
			finally{
				resultado.add(individual);
			}
		}		
		return resultado;
		
	}
	
	//Procesamos las cotizaciones
		public JSONArray ProcesoLugares(List<AgriRecotizacionMasivas> cotizacionList){
			
			JSONArray resultado = new JSONArray();
			JSONObject individual= new JSONObject();
			for(AgriRecotizacionMasivas rs :cotizacionList){
				try{
					CotizacionDAO cotizacionDAO= new CotizacionDAO();
					Cotizacion cotizacion = cotizacionDAO.buscarPorId(rs.getIdCotizacion());
					CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
					
					//Cambio el sitio de la Siembra
					agriObjetoCotizacion.setDireccionSiembra(rs.getLugar().trim());
					
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
					individual.put("id", rs.getIdCotizacion().toString());
					individual.put("observacion", "Proceso de cambio de lugares Correcto");
				}catch(Exception e){
					individual.put("id", rs.getIdCotizacion().toString());
					individual.put("observacion", e.getMessage());
				}
				finally{
					resultado.add(individual);
				}
			}		
			return resultado;
			
		}
		
		
		public JSONArray ProcesoFechas(List<AgriRecotizacionMasivas> cotizacionList){
			
			JSONArray resultado = new JSONArray();
			JSONObject individual= new JSONObject();
			for(AgriRecotizacionMasivas rs :cotizacionList){
				try{
					CotizacionDAO cotizacionDAO= new CotizacionDAO();
					Cotizacion cotizacion = cotizacionDAO.buscarPorId(rs.getIdCotizacion());
					CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
					
					
					String AnioSiembra=rs.getFecha();
					String[] elementosFecha = AnioSiembra.split("/");
					
					int tamañoFecha=elementosFecha.length;
					if(elementosFecha.length==1)
						throw new Exception("Fecha de siembra no valida "+rs.getFecha()+" no valida, debe estar en el formato dd/MM/yyyy la celda excel debe estar en formato texto o general no en formato fechas");
										
					int comprobacionAnio=elementosFecha[2].length();
						
					if(comprobacionAnio !=4)
						throw new Exception("Fecha de siembra no valida "+rs.getFecha()+" no valida, debe estar en el formato dd/MM/yyyy");
					
					Date dateSiembra=null;
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formato de fecha de siembra
					try{
						dateSiembra=formatter.parse(rs.getFecha());
					}catch(Exception e){
						throw new Exception(
								"Error la fecha de siembra "+rs.getFecha()+ " no esta en el formato dd/MM/yyyy");
					}
					
					//Cambio la fecha de la Siembra
					agriObjetoCotizacion.setFechaSiembra(dateSiembra);
					
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
					individual.put("id", rs.getIdCotizacion().toString());
					individual.put("observacion", "Proceso de cambio de fechas Correcto");
				}catch(Exception e){
					individual.put("id", rs.getIdCotizacion().toString());
					individual.put("observacion", e.getMessage());
				}
				finally{
					resultado.add(individual);
				}
			}		
			return resultado;
			
		}
}

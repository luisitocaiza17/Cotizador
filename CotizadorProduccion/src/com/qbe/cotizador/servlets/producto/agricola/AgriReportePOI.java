package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.Estado;

/**
 * Servlet implementation class AgriReportePOI
 */
@WebServlet("/AgriReportePOI")
public class AgriReportePOI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriReportePOI() {
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
		try {
			String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
			String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
			String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
			String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
			String AgenteId = request.getParameter("agente") == null ? ""	: request.getParameter("agente");
			String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
			String ApellidosCliente = request.getParameter("ApellidosCliente") == null ? ""	: request.getParameter("ApellidosCliente");
			String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
			String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
			String misCotizaciones = request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
			String facturacionId = request.getParameter("facturaId") == null ? "" : request.getParameter("facturaId");
							//String esImpreso = request.getParameter("esImpreso") == null ? "" : request.getParameter("esImpreso");
			String usuarioId = "";
					
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
			
			String estadoConsulta = request.getParameter("estadoConsulta") == null ? "": request.getParameter("estadoConsulta");
							
			Estado estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			estado = estadoDAO.buscarPorId(estadoConsulta);

			List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
			CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();				
			long total = 0;	
			total = cDAO.consultarPorEstadoContador(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,(int)skip,(int)take,facturacionId,false,false,"","");
						
			//TODO: Proceso de creacion de reporte en base a POI
			String rutaPlantilla = this.getServletContext().getRealPath("")
					+ "/static/plantillas/cotizacion_reporte.xlsx";
					
			response.setHeader("Content-Disposition", "attachment;filename=AgriReporte.xlsx");
            response.setContentType("application/octet-stream");
			/*PROCESO DE CREACION DEL POI*/
			 FileInputStream file;
			try {
				file = new FileInputStream(new File(rutaPlantilla));
				 /*XSSFWorkbook  workbook = new XSSFWorkbook (file);
				 XSSFSheet sheet = workbook.getSheetAt(0);*/
	             
				 XSSFWorkbook xssfWb = new XSSFWorkbook(file);
	             SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWb, 100);
	             SXSSFSheet sheet = (SXSSFSheet) workbook.getSheetAt(0);				 
				 
				 Cell cell = null;

	             
	             
	             /*Estilos*/
	             int columnas=0;
                 int fila=1;
                 int contador=0;
                 
				 
					cotizacionList = cDAO.consultarPorEstado(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,0,(int)total,facturacionId,false,false,"","");
					System.out.println("Generando reporte");
					for(AgriCotizacionAprobacion rs: cotizacionList){
					//CREACION DE CADA UNO DE LOS ELEMENTOS DEL REPORTE ("FILAS")
					Row row = sheet.createRow(fila++);
                    Cell col1 = row.createCell(0);
                    Cell col2 = row.createCell(1);
                    Cell col3 = row.createCell(2);
                    Cell col4 = row.createCell(3);
                    Cell col5 = row.createCell(4);
                    Cell col6 = row.createCell(5);
                    Cell col7 = row.createCell(6);
                    Cell col8 = row.createCell(7);
                    Cell col9 = row.createCell(8);
                    Cell col10 = row.createCell(9);
                    Cell col11 = row.createCell(10);
                    Cell col12 = row.createCell(11);
                    Cell col13 = row.createCell(12);
                    Cell col14 = row.createCell(13);
                    Cell col15 = row.createCell(14);
                    Cell col16 = row.createCell(15);
                    Cell col17 = row.createCell(16);
                    Cell col18 = row.createCell(17);
                    Cell col19 = row.createCell(18);
                    Cell col20 = row.createCell(19);
                    Cell col21 = row.createCell(20);
                    Cell col22 = row.createCell(21);
                    Cell col23 = row.createCell(22);
                    Cell col24 = row.createCell(23);
                    Cell col25 = row.createCell(24);
                    Cell col26 = row.createCell(25);
                    Cell col27 = row.createCell(26);
                    Cell col28 = row.createCell(27);
                    Cell col29 = row.createCell(28);
                    Cell col30 = row.createCell(29);
                    Cell col31 = row.createCell(30);
                    Cell col32 = row.createCell(31);
                    Cell col33 = row.createCell(32);
                    Cell col34 = row.createCell(33);
                    Cell col35 = row.createCell(34);
                    Cell col36 = row.createCell(35);
                    Cell col37 = row.createCell(36);
                    Cell col38 = row.createCell(37);
                    Cell col39 = row.createCell(38);
                    Cell col40 = row.createCell(39);
                    Cell col41 = row.createCell(40);
                    Cell col42 = row.createCell(41);
                    
                    
                    col1.setCellValue(""+rs.getId());
                    col2.setCellValue(""+rs.getIdCotizacion2());
                    col3.setCellValue(""+rs.getCiclo());
                    col4.setCellValue(""+rs.getFechaElaboracion());
                    col5.setCellValue(""+rs.getIdentificacionCliente());
                    col6.setCellValue(""+rs.getnombresCliente());
                    col7.setCellValue(""+rs.getTipoOrigen());
                    col8.setCellValue(""+rs.getPuntoVenta());
                    col9.setCellValue(""+rs.getNumeroTramite());
                    col10.setCellValue(""+rs.getVigenciaDias());
                    col11.setCellValue(""+rs.getFechaAprobacion());
                    col12.setCellValue(""+rs.getFechaVigenciaDesde());
                    col13.setCellValue(""+rs.getFechaVigenciaHasta());
                    col14.setCellValue(""+rs.getTipoCultivoNombre());
                    col15.setCellValue(""+rs.getProvinciaNombre());
                    col16.setCellValue(""+rs.getCantonNombre());
                    col17.setCellValue(""+rs.getParroquiaNombre());
                    col18.setCellValue(""+rs.getDireccionSiembra());
                    col19.setCellValue(""+rs.getTelefono());
                    col20.setCellValue(""+rs.getHectareasAsegurables()); 
                    col21.setCellValue(""+rs.getSumaAseguradaTotal()); 
                    col22.setCellValue(""+rs.getFechaSiembra()); 
                    col23.setCellValue(""+rs.getPrimaNetaTotal()); 
                    col24.setCellValue(""+rs.getCostoProduccion());
                    col25.setCellValue(""+rs.getSeguroBancos()); 
                    col26.setCellValue(""+rs.getSeguroCampesino()); 
                    col27.setCellValue(""+rs.getDerechoEmision()); 
                    col28.setCellValue(""+rs.getIva()); 
                    col29.setCellValue(""+rs.getTotalFactura()); 
                    col30.setCellValue(""+rs.getHectareasLote()); 
                    col31.setCellValue(""+rs.getLongitud()); 
                    col32.setCellValue(""+rs.getLatitud()); 
                    col33.setCellValue(""+rs.getDisponeRiego()); 
                    col34.setCellValue(""+rs.getDisponeAsistencia()); 
                    col35.setCellValue(""+rs.getAgricultorTecnificado()); 
                    col36.setCellValue(""+rs.getObservacionRegla()); 
                    col37.setCellValue(""+rs.getObservacionQBE()); 
                    col38.setCellValue(""+rs.getEstadoCotizacion());
                    col39.setCellValue(""+rs.getConfirmacion());
                    col40.setCellValue(""+rs.getMovimiento());
                    col41.setCellValue(""+rs.getTiposMovimiento());
                    col42.setCellValue(""+rs.getTieneIndemnizacion()); 
                    contador++;
					}				
					cotizacionList.clear();
								
				
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                for(Row r : sheet) {
                    for(Cell c : r) {
                        if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            evaluator.evaluateFormulaCell(c);
                        }
                    }
                }	
                ServletOutputStream outx = response.getOutputStream();
                workbook.write(outx);

                //in.close();
                file.close();
                outx.flush();
                outx.close();
                
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}		
}

package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaArchivoPlanoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaObjeto;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.Usuario;

/**
 * Servlet implementation class CotizacionesPrecargadasAgricolaController
 */
@WebServlet("/CotizacionesPrecargadasAgricolaController")
public class CotizacionesPrecargadasAgricolaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionesPrecargadasAgricolaController() {
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
		
		
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
		
		if (tipoConsulta.equalsIgnoreCase("Consulta")) {
			String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
			String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
			String NombresCliente = request.getParameter("NombresCliente") == null ? "": request.getParameter("NombresCliente");
			String identificacion = request.getParameter("identificacion") == null ? "": request.getParameter("identificacion");
				
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

			AgriCargaPreviaArchivoPlanoDAO cargaPrevia = new AgriCargaPreviaArchivoPlanoDAO();
			List<AgriCargaPreviaArchivoPlano> data =cargaPrevia.cotizacionesExistentes(fInicio,fFinal,identificacion,NombresCliente,skip,take);
			
			long total=cargaPrevia.cotizacionesExistentesPorNumero(fInicio,fFinal,NombresCliente,identificacion);
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			List<AgriCargaPreviaObjeto> data1 = new ArrayList<AgriCargaPreviaObjeto>();
			for(AgriCargaPreviaArchivoPlano rs : data){
				AgriCargaPreviaObjeto objetoRespuesta = new AgriCargaPreviaObjeto();
				objetoRespuesta.setId(rs.getId().toString());
				objetoRespuesta.setCanalNombre(rs.getCanalNombre());
				String NombreApellido="";
				if(rs.getClienteNombre()!=null)
					NombreApellido=rs.getClienteNombre();
				
				if(rs.getClienteApellido()!=null)
					NombreApellido=NombreApellido+" "+rs.getClienteApellido();
				
				objetoRespuesta.setClienteNombre(NombreApellido);
				objetoRespuesta.setClienteIdentificacion(rs.getClienteIdentificacion());
				objetoRespuesta.setMontoAsegurado(rs.getMontoAsegurado().toString());
				try{
					String fechaSolicitud = sm.format(rs.getSolicitudFecha());
					objetoRespuesta.setSolicitudFecha(fechaSolicitud);
				}catch(Exception e){
					//fecha no valida
				}
				try{
					String fechaSiembra = sm.format(rs.getSiembraFecha());
					objetoRespuesta.setSiembraFecha(fechaSiembra);
				}catch(Exception e){
					//fecha no valida
				}
				objetoRespuesta.setTipoCultivoId(rs.getTipoCultivoId().toString());
				objetoRespuesta.setTipoCultivoNombre(rs.getTipoCultivoNombre());
				objetoRespuesta.setNumerHasAseguradas(rs.getNumerHasAseguradas().toString());
				objetoRespuesta.setNumeroHasLote(rs.getNumeroHasLote().toString());
				if(rs.getEsTecnificado())
					objetoRespuesta.setEsTecnificado("SI");
				else
					objetoRespuesta.setEsTecnificado("NO");				
				objetoRespuesta.setProvinciaId(rs.getProvinciaId().toString());
				objetoRespuesta.setProvinciaNombre(rs.getProvinciaNombre());
				objetoRespuesta.setCantonId(rs.getCantonId().toString());
				objetoRespuesta.setCantonNombre(rs.getCantonNombre());
				objetoRespuesta.setParroquiaId(rs.getParroquiaId().toString());
				objetoRespuesta.setParroquiaNombre(rs.getParroquiaNombre());
				objetoRespuesta.setUbicacionCultivo(rs.getUbicacionCultivo());
				objetoRespuesta.setTelefono(rs.getTelefono());
				objetoRespuesta.setGpsX(rs.getGpsX());
				objetoRespuesta.setGpsY(rs.getGpsY());
				objetoRespuesta.setUsuarioId(rs.getUsuarioId().toString());
				try{
					String cargaSiembra = sm.format(rs.getCargaFecha());
					objetoRespuesta.setCargaFecha(cargaSiembra);
				}catch(Exception e){
					//fecha no valida
				}
				objetoRespuesta.setEstado(""+rs.getEstado());
				objetoRespuesta.setClienteApellido(rs.getClienteApellido());
				data1.add(objetoRespuesta);
			}
			
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(data1);
			
			Gson gson = new Gson(); 
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;
		}
		
		
	}

}

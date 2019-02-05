package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.PymeCiudadRiesgoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCiudadRiesgoVista;
import com.qbe.cotizador.dao.producto.pymes.PymeConfiguracionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTasaRiesgoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTipoRiesgoDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeCiudadRiesgo;
import com.qbe.cotizador.model.PymeConfiguracionCobertura;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.model.PymeTasaRiesgo;
import com.qbe.cotizador.model.PymeTipoRiesgo;
import com.qbe.cotizador.servlets.producto.agricola.DataSourceResult;
import com.qbe.cotizador.transaction.producto.pymes.PymeCiudadRiesgoTransaction;

/**
 * Servlet implementation class PymeRiesgoController
 */
@WebServlet("/PymeRiesgoController")
public class PymeRiesgoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeRiesgoController() {
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
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			//consultamos el tipo de riesgo del primer item de la cotizacion
			if(tipoConsulta.equals("hallarPrimerRiesgo")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				Cotizacion cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
				
				String zonaRiesgo="NO";
				String tipoRiesgo="0";
				String valorRiesgo="";
				int contadorDirecciones=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).size();
				if(contadorDirecciones>0){
					CotizacionDetalle cotizacionDetalle =  cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
					PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO= new PymeObjetoCotizacionDAO();
					PymeObjetoCotizacion pymeObjetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					if(pymeObjetoCotizacion.getZonaRiesgo()){
						zonaRiesgo="SI";
						if(pymeObjetoCotizacion.getTipoRiesgo()==1){//volcan
							tipoRiesgo="1";
							valorRiesgo=pymeObjetoCotizacion.getValorRiesgo();//metros
						}else{//costa
							tipoRiesgo="2";
							valorRiesgo=pymeObjetoCotizacion.getValorRiesgo();//1) daño, 2) sin daño
						}
					}
				}
				result.put("contadorDirecciones", contadorDirecciones);
				result.put("zonaRiesgo", zonaRiesgo);
				result.put("tipoRiesgo", tipoRiesgo);
				result.put("valorRiesgo", valorRiesgo);
			}
			
			
			if(tipoConsulta.equals("traerTasaCosta")){//para riesgos tipo costa
				String configuracionCoberturaId = request.getParameter("configuracionCoberturaId") == null ? "" : request.getParameter("configuracionCoberturaId");
				String tieneRiesgo = request.getParameter("tieneRiesgo") == null ? "" : request.getParameter("tieneRiesgo");
				//buscamos la tasa por el riesgo
				PymeConfiguracionCoberturaDAO pymeConfiguracionCoberturaDAO = new PymeConfiguracionCoberturaDAO();
				PymeConfiguracionCobertura pymeConfiguracionCobertura = pymeConfiguracionCoberturaDAO.buscarPorId(new BigInteger(configuracionCoberturaId));
				//traigo las tasas
				PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
				List<PymeTasaRiesgo> pymeTasaRiesgo =pymeTasaRiesgoDAO.BuscarPorCotizacion(pymeConfiguracionCobertura.getConfiguracionCoberturaId());
				//variables
				String valorTasa="0";
				int tieneReclamo=0;
				//tomo el valor del chek, si es si pongo 1, si es no pongo 0
				if(tieneRiesgo.equals("SI"))
					tieneReclamo=1;
				else
					tieneReclamo=2;
				for(PymeTasaRiesgo rs:pymeTasaRiesgo){
					if(rs.getPymeTipoRiesgoId().compareTo(new BigInteger("2"))==0){//verificamos que sea riesgo costa
						if(rs.getReclamo()==tieneReclamo){//verificamos si pertenece o no a tiende daño
							valorTasa=""+rs.getTasa();
						}						
					}
				}
				result.put("tasa", valorTasa);
			}
			if(tipoConsulta.equals("traerTasaVolcan")){//para riesgos tipo costa
				String configuracionCoberturaId = request.getParameter("configuracionCoberturaId") == null ? "" : request.getParameter("configuracionCoberturaId");
				String distancia = request.getParameter("distancia") == null ? "" : request.getParameter("distancia");
				//buscamos la tasa por el riesgo
				PymeConfiguracionCoberturaDAO pymeConfiguracionCoberturaDAO = new PymeConfiguracionCoberturaDAO();
				PymeConfiguracionCobertura pymeConfiguracionCobertura = pymeConfiguracionCoberturaDAO.buscarPorId(new BigInteger(configuracionCoberturaId));
				//traigo las tasas
				PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
				List<PymeTasaRiesgo> pymeTasaRiesgo =pymeTasaRiesgoDAO.BuscarPorCotizacion(pymeConfiguracionCobertura.getConfiguracionCoberturaId());
				//variables
				String valorTasa="0";
				double distanciaRiesgo= Double.parseDouble(distancia);
				for(PymeTasaRiesgo rs:pymeTasaRiesgo){
					if(rs.getPymeTipoRiesgoId().compareTo(new BigInteger("1"))==0){//verificamos que sea riesgo volcan
						if(distanciaRiesgo>=rs.getDesde()&&distanciaRiesgo<=rs.getHasta()){//verificamos si la distancia esta en el rango para aplicar la tasa
							valorTasa=""+rs.getTasa();
						}												
					}
				}
				result.put("tasa", valorTasa);
			}
			if(tipoConsulta.equals("verificarRiesgo")){
				//busco la ciudad para verificar que exista
				String ciudadId = request.getParameter("ciudad") == null ? "" : request.getParameter("ciudad");
				CantonDAO ciudadDAO = new CantonDAO();
				Canton ciudad = ciudadDAO.buscarPorId(ciudadId);				
				
				PymeCiudadRiesgoDAO ciudadRiesgoDAO = new PymeCiudadRiesgoDAO();
				List<PymeCiudadRiesgo> pymeCiudadRiesgoList = ciudadRiesgoDAO.BuscarPorCiudad(ciudad);
				String riesgos="";
				String tipoRiesgoErupcion="NO";
				String tipoRiesgoCosta="NO";
				if(pymeCiudadRiesgoList.size()!=0){
					riesgos="SI";
					for(PymeCiudadRiesgo rs :pymeCiudadRiesgoList){
						if(rs.getPymeTipoRiesgo().getNombre().equals("ERUPCION"))
							tipoRiesgoErupcion="SI";
						if(rs.getPymeTipoRiesgo().getNombre().equals("COSTA"))
							tipoRiesgoCosta="SI";
					}
				}
				else{
					riesgos="NO";
				}
				result.put("riesgos", riesgos);
				result.put("tipoRiesgoCosta", tipoRiesgoCosta);	
				result.put("tipoRiesgoErupcion", tipoRiesgoErupcion);
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
}
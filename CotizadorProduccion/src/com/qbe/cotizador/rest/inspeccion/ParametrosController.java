package com.qbe.cotizador.rest.inspeccion;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;

import net.sf.json.JSONObject;

@Path("/conf")

public class ParametrosController {

	@Path("/parametros")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String cargarParametrosGet() throws Exception {

		JSONObject retorno = new JSONObject();
		try {
			VariableSistemaDAO vsDAO=new VariableSistemaDAO();
			String valor=vsDAO.buscarPorNombre("SERVIDOR_RISK_INSPECTOR").getValor();
			retorno.put("server", valor);
			retorno.put("success", true);
		
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("mensaje", e.getMessage());
			e.printStackTrace();
		} 
		return retorno.toString();
	}

	@Path("/parametros")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String cargarParametrosPost() throws Exception {

		JSONObject retorno = new JSONObject();
		try {
			VariableSistemaDAO vsDAO=new VariableSistemaDAO();
			String valor=vsDAO.buscarPorNombre("SERVIDOR_RISK_INSPECTOR").getValor();
			retorno.put("server", valor);
			retorno.put("success", true);
		
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("mensaje", e.getMessage());
			e.printStackTrace();
		} 
		return retorno.toString();
	}
	
}

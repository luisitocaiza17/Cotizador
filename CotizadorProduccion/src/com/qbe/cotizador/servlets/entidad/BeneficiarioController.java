package com.qbe.cotizador.servlets.entidad;

import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.transaction.entidad.BeneficiarioTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Servlet implementation class Beneficiario
 */
@WebServlet("/BeneficiarioController")
public class BeneficiarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeneficiarioController() {
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
        try {
            String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
            String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
            String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
            String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
            String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
           
            JSONObject beneficiarioJSONObject = new JSONObject();
            JSONArray beneficiarioJSONArray = new JSONArray();

            Beneficiario beneficiario = new Beneficiario();
            BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
            
            BeneficiarioTransaction beneficiarioTransaction = new BeneficiarioTransaction(); 
            
            if(!codigo.equals(""))
            	beneficiario=beneficiarioDAO.buscarPorId(codigo);

            if (!codigoEnsurance.equals(""))
                beneficiario.setCodigoEnsurance(new BigInteger(codigoEnsurance));
            
            if (!codigo.equals("")){
            	beneficiario.setId(codigo);}
            
            if (!nombre.equals("")){
            	beneficiario.setNombre(nombre);
            	}

            if (activo.equals("1"))
            	beneficiario.setActivo(true);
            else
            	beneficiario.setActivo(false);
            
            if (tipoConsulta.equals("encontrarTodos")) {
                List<Beneficiario> results = beneficiarioDAO.buscarTodos();
                int i = 0;
                for (i = 0; i < results.size(); i++) {
                    beneficiario = results.get(i);
                    beneficiarioJSONObject.put("codigo", beneficiario.getId());
                    beneficiarioJSONObject.put("codigoEnsurance", beneficiario.getCodigoEnsurance());
                    beneficiarioJSONObject.put("nombre", beneficiario.getNombre());
                    beneficiarioJSONObject.put("activo", beneficiario.getActivo());
                   if (beneficiario.getActivo())
                        beneficiarioJSONObject.put("activo", "Si");
                    else
                        beneficiarioJSONObject.put("activo", "No");
                    
                    beneficiarioJSONArray.add(beneficiarioJSONObject);
                }
                result.put("numRegistros", i);
                result.put("listadoBeneficiario", beneficiarioJSONArray);

              }

			if (tipoConsulta.equals("crear")) {
				beneficiario = beneficiarioTransaction.crear(beneficiario);
				
			}
			if (tipoConsulta.equals("cambioEstado")) {
				beneficiario = beneficiarioDAO.buscarPorId(codigo);
				if (activo.equals("1")) {
					beneficiario.setActivo(true);
				} else if (activo.equals("0")) {
					beneficiario.setActivo(false);
				}
				beneficiarioTransaction.editar(beneficiario);
			}

			if (tipoConsulta.equals("eliminar")) {
				beneficiario = beneficiarioDAO.buscarPorId(codigo);
				beneficiarioTransaction.eliminar(beneficiario);
			}

			if (tipoConsulta.equals("actualizar")) {
				beneficiario=beneficiarioTransaction.editar(beneficiario);
            }
			
			 if (tipoConsulta.equals("cargarSelect2")) {
	                List<Beneficiario> results = beneficiarioDAO.buscarActivos();
	                int i = 0;
	                for (i = 0; i < results.size(); i++) {
	                    beneficiario = results.get(i);
	                    beneficiarioJSONObject.put("id", beneficiario.getId());
	                    beneficiarioJSONObject.put("text", beneficiario.getNombre());
	                   
	                    beneficiarioJSONArray.add(beneficiarioJSONObject);
	                }
	                result.put("numRegistros", i);
	                result.put("listadoBeneficiarios", beneficiarioJSONArray);
			 }
			 
			 if (tipoConsulta.equals("cargarSelect3")) {
	                List<Beneficiario> results = beneficiarioDAO.buscarActivos();
	                beneficiarioJSONArray= new JSONArray();
	                int i = 0;
	                for (i = 0; i < results.size(); i++) {
	                	beneficiarioJSONObject = new JSONObject();
	                    beneficiario = results.get(i);
	                    beneficiarioJSONObject.put("id", beneficiario.getId());
	                    beneficiarioJSONObject.put("text", beneficiario.getNombre()+" (Ensurance Id: "+beneficiario.getCodigoEnsurance()+")");
	                   
	                    beneficiarioJSONArray.add(beneficiarioJSONObject);
	                }
	                result.put("numRegistros", i);
	                result.put("listadoBeneficiarios", beneficiarioJSONArray);
			 }
			
            result.put("success", Boolean.TRUE);
            response.setContentType("application/json; charset=ISO-8859-1");
            result.write(response.getWriter());
        } catch (Exception e) {
            result.put("success", Boolean.FALSE);
            result.put("error", e.getLocalizedMessage());
            response.setContentType("application/json; charset=ISO-8859-1");
            result.write(response.getWriter());
            e.printStackTrace();

        }
    }

}

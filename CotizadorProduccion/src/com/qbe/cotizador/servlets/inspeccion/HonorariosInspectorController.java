package com.qbe.cotizador.servlets.inspeccion;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.inspeccion.HonorariosInspectorDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.model.HonorariosInspector;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Zona;
import com.qbe.cotizador.transaction.inspeccion.HonorariosInspectorTransaction;

/**
 * Servlet implementation class HonorariosInspectorController
 */
@WebServlet("/HonorariosInspectorController")
public class HonorariosInspectorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HonorariosInspectorController() {
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
        HonorariosInspectorTransaction HonorariosInspectorTransaction = new HonorariosInspectorTransaction(); 
        
        try{
            String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
            String inspectorId = request.getParameter("inspectorId") == null ? "" : request.getParameter("inspectorId");
            String zonaId = request.getParameter("zonaId") == null ? "" : request.getParameter("zonaId");
            String tipoObjetoId = request.getParameter("tipoObjetoId") == null ? "" : request.getParameter("tipoObjetoId");
            String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
            
            String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
            JSONObject HonorariosInspectorJSONObject = new JSONObject();
            JSONArray HonorariosInspectorJSONArray = new JSONArray();

            HonorariosInspector HonorariosInspector = new HonorariosInspector();
            HonorariosInspectorDAO HonorariosInspectorDAO = new HonorariosInspectorDAO();
            
            if (!codigo.equals(""))
                HonorariosInspector=HonorariosInspectorDAO.buscarPorId(codigo);

            if (!inspectorId.equals("")){
                InspectorDAO idao=new InspectorDAO();
                Inspector inspector=idao.buscarPorId(inspectorId);
                HonorariosInspector.setInspector(inspector);
            }
            
            if (!zonaId.equals("")){
                ZonaDAO zdao=new ZonaDAO();
                Zona zona=zdao.buscarPorId(zonaId);
                HonorariosInspector.setZona(zona);
            }

            if (!tipoObjetoId.equals("")){
              TipoObjetoDAO todao=new TipoObjetoDAO();
              TipoObjeto tipoObjeto=todao.buscarPorId(tipoObjetoId);
              HonorariosInspector.setTipoObjeto(tipoObjeto.getId());
            }
            
            if(!valor.equals(""))
              HonorariosInspector.setValor(Double.parseDouble(valor));
            
            if(tipoConsulta.equals("encontrarTodos")){ 
                List<HonorariosInspector> results = HonorariosInspectorDAO.buscarTodos();
                int i=0;
                TipoObjetoDAO todao=new TipoObjetoDAO();
                
                for(i=0; i<results.size(); i++){
                    HonorariosInspector = results.get(i);
                    Inspector inspector=HonorariosInspector.getInspector();
                    TipoObjeto tipoObjeto=todao.buscarPorId(HonorariosInspector.getTipoObjeto());
                    HonorariosInspectorJSONObject.put("codigo", HonorariosInspector.getId());
                    HonorariosInspectorJSONObject.put("inspectorId", inspector.getId());
                    HonorariosInspectorJSONObject.put("inspectorNombre", inspector.getNombre());
                    HonorariosInspectorJSONObject.put("zonaId", HonorariosInspector.getZona().getId());
                    HonorariosInspectorJSONObject.put("zonaNombre", HonorariosInspector.getZona().getNombre());
                    HonorariosInspectorJSONObject.put("tipoObjetoId", tipoObjeto.getId());
                    HonorariosInspectorJSONObject.put("tipoObjetoNombre", tipoObjeto.getNombre());
                    HonorariosInspectorJSONObject.put("valor", HonorariosInspector.getValor());
                    HonorariosInspectorJSONObject.put("valorKm", HonorariosInspector.getInspector().getValorKm());
                    HonorariosInspectorJSONArray.add(HonorariosInspectorJSONObject);
                }
                
                InspectorDAO iDAO=new InspectorDAO();
                
                JSONArray inspectoresJSON=new JSONArray();
                JSONObject inspectorJSON=new JSONObject();
                
                List<Inspector> inspectores=iDAO.buscarTodos();
                for(i=0; i<inspectores.size(); i++){
                    Inspector inspector= inspectores.get(i);
                    inspectorJSON=new JSONObject();
                    inspectorJSON.put("id", inspector.getId());
                    inspectorJSON.put("text", inspector.getNombre());
                    inspectoresJSON.add(inspectorJSON);
                }
                
                ZonaDAO zDAO=new ZonaDAO();
                
                JSONArray zonasJSON=new JSONArray();
                JSONObject zonaJSON=new JSONObject();
                
                List<Zona> zonas=zDAO.buscarTodos();
                for(i=0; i<zonas.size(); i++){
                    Zona zona= zonas.get(i);
                    zonaJSON=new JSONObject();
                    zonaJSON.put("id", zona.getId());
                    zonaJSON.put("text", zona.getNombre());
                    zonasJSON.add(zonaJSON);
                } 
                
                JSONArray tipoObjetosJSON=new JSONArray();
                JSONObject tipoObjetoJSON=new JSONObject();
                
                List<TipoObjeto> tipoObjetos=todao.buscarTodos();
                for(i=0; i<tipoObjetos.size(); i++){
                  TipoObjeto tipoObjeto= tipoObjetos.get(i);
                  tipoObjetoJSON=new JSONObject();
                  tipoObjetoJSON.put("id", tipoObjeto.getId());
                  tipoObjetoJSON.put("text", tipoObjeto.getNombre());
                  tipoObjetosJSON.add(tipoObjetoJSON);
                }
                
                result.put("numRegistros",i);
                result.put("listadoHonorariosInspector", HonorariosInspectorJSONArray);
                result.put("listadoTipoObjeto", tipoObjetosJSON);
                result.put("listadoZona", zonasJSON);
                result.put("listadoInspector", inspectoresJSON);
            }
            
            if(tipoConsulta.equals("crear"))
                HonorariosInspectorTransaction.crear(HonorariosInspector);

            if(tipoConsulta.equals("actualizar"))
                HonorariosInspectorTransaction.editar(HonorariosInspector);

            if(tipoConsulta.equals("eliminar"))
                HonorariosInspectorTransaction.eliminar(HonorariosInspector);


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

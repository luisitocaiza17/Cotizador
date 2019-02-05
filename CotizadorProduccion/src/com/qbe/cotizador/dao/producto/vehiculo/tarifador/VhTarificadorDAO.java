package com.qbe.cotizador.dao.producto.vehiculo.tarifador;

import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.VhTarificador;

public class VhTarificadorDAO extends EntityManagerFactoryDAO<VhTarificador>{
	
	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				em = (javax.persistence.EntityManager) initCtx.lookup("java:comp/env/CotizadorWebPC");
			} catch (NamingException e) { 
				e.printStackTrace();
			}		
		}
		return em;
	}
	
	public VhTarificadorDAO() {
		super(VhTarificador.class);
	}
	/* Metodo para obtener el rango de la tarificación al que pertenece la cotizacion */
	public VhTarificador buscarPorFecha (java.sql.Date fechaCotizacion){		
		VhTarificador vhTarificador = new VhTarificador();
		List<VhTarificador> listadoFechasTarificador = getEntityManager().createNamedQuery("VhTarificador.buscarTodos").getResultList();
		
		
		for(VhTarificador tarificador:listadoFechasTarificador){
			Calendar calendarioVigenciaDesde = Calendar.getInstance();
			calendarioVigenciaDesde.setTime(tarificador.getVigenciaDesde());
			calendarioVigenciaDesde.add(Calendar.DATE, -1);
			
			Calendar calendarioVigenciaHasta = Calendar.getInstance();			
			calendarioVigenciaHasta.setTime(tarificador.getVigenciaHasta());		
			calendarioVigenciaHasta.add(Calendar.DATE, 1);
			
			if(fechaCotizacion.after(calendarioVigenciaDesde.getTime()) && fechaCotizacion.before(calendarioVigenciaHasta.getTime())){
				vhTarificador=tarificador;				
			}
		}
		return vhTarificador;				
	}
	
	/* Obtenemmos las tasas del tarificador version 2*/
	public JSONObject obtenerTasasTarificador2(ObjetoVehiculo vehiculo,Cliente cliente,String comisionAgente,String montoFijo, String valorSiniestro, String porcentajeSumaAsegurada,String agenteId,
			String porcentajeSumaAseguradaDT,String deducibleRC){
		
		JSONObject retorno = new JSONObject();
		StoredProcedureQuery storedProcedure = getEntityManager().createStoredProcedureQuery("SP_VH_TARIFICADOR_1");
        // Agregamos los valores de los parametros de entrada
        storedProcedure.registerStoredProcedureParameter("v_genero", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_estado_civil", String.class, ParameterMode.IN);                
        storedProcedure.registerStoredProcedureParameter("v_edad", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_antigue_cliente", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_zona", String.class, ParameterMode.IN);        
        storedProcedure.registerStoredProcedureParameter("v_tipo_vh", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_marca_modelo", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_rastreo", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_kilometraje", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_hijos", String.class, ParameterMode.IN);                        
        storedProcedure.registerStoredProcedureParameter("v_antig_vh", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_suma_asegurada", Double.class, ParameterMode.IN);        
        storedProcedure.registerStoredProcedureParameter("v_deducible_perdida_total", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_deducible_rc", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_comision_agente", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_deducible_perdida_parcial_minimo", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_deducible_perdida_parcial_porcentaje_siniestro", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_deducible_perdida_parcial_porcentaje_valor_asegurado", Double.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_agente_id", String.class, ParameterMode.IN);
                
        // Agregamos los valores de los parametros de entrada
        String genero = vehiculo.getConductorGenero();
        if(genero.equalsIgnoreCase("M"))
        	genero = "MASCULINO";
         else if(genero.equalsIgnoreCase("F"))
        	genero = "FEMENINO";
         else if(genero.equalsIgnoreCase("J"))
        	genero = "P.JURIDICA";         
        storedProcedure.setParameter("v_genero",genero);
        
        String estadoCivil = vehiculo.getConductorEstadoCivil();
        if(estadoCivil.equalsIgnoreCase("S"))
        	estadoCivil = "SOLTERO"; 
        else if(estadoCivil.equalsIgnoreCase("C"))
        	estadoCivil = "CASADO"; 
        else if(estadoCivil.equalsIgnoreCase("D"))
        	estadoCivil = "DIVORCIADO";
        else if(estadoCivil.equalsIgnoreCase("U"))
        	estadoCivil = "CASADO"; // Revisar con rodrigo
        else if(estadoCivil.equalsIgnoreCase("V"))
        	estadoCivil = "VIUDO";     
        else if(estadoCivil.equalsIgnoreCase("J"))
        	estadoCivil = "P.JURIDICA";               
        storedProcedure.setParameter("v_estado_civil",estadoCivil);
        
        String edad = vehiculo.getConductorEdad();
        if(edad.equalsIgnoreCase("J"))
        	edad = "P.JURIDICA";        
        else if(edad.equalsIgnoreCase("80"))
        	edad = "80+";
        storedProcedure.setParameter("v_edad",edad);    	
        storedProcedure.setParameter("v_antigue_cliente",cliente.getAntiguedadCliente()==null ? "0" :cliente.getAntiguedadCliente());       
        storedProcedure.setParameter("v_zona",vehiculo.getVhTari1Zona().getNombre());
        	        	        
        //Tipo Vehículo 	     	
        String tipoVehiculo=vehiculo.getTipoVehiculo().getNombre();	
        if(tipoVehiculo.equals("AUTOMOVIL")||tipoVehiculo.equals("MOTO")|| tipoVehiculo.equals("CUADRON")||tipoVehiculo.equals("PASEO")||
        		tipoVehiculo.equals("TRICAR")||tipoVehiculo.equals("PASOLA")||tipoVehiculo.equals("LIMOUSINE") ||tipoVehiculo.equals("LIVIANO")){
        	tipoVehiculo = "AUTOMOVIL";
        }else if(tipoVehiculo.equals("CAMIONETA DOBLE CAB")||tipoVehiculo.equals("CUATRO X CUATRO")||tipoVehiculo.equals("MINI VAN")||tipoVehiculo.equals("DOBLE CABINA")||
        		tipoVehiculo.equals("CAMIONETA DOBLE CABINA")||tipoVehiculo.equals("CAMIONETA PICK UP")||tipoVehiculo.equals("PICK UP")||tipoVehiculo.equals("FURGONETA")||
        		tipoVehiculo.equals("CAMIONETA")){
            tipoVehiculo = "CAMIONETA";        	
        }else if(tipoVehiculo.equals("COUPE")||tipoVehiculo.equals("HATCH BACK")){
        	tipoVehiculo = "COUPE";
        }else if(tipoVehiculo.equals("ESPECIAL")||tipoVehiculo.equals("PORTA CONTENEDOR")||tipoVehiculo.equals("TRAILER")||tipoVehiculo.equals("TRACTO CAMION")||
        		tipoVehiculo.equals("TRACTO CAMION")||tipoVehiculo.equals("TRAILER CABEZAL")||tipoVehiculo.equals("CAMION CANASTA")||tipoVehiculo.equals("PANDILLERA")
        		||tipoVehiculo.equals("FURGON")||tipoVehiculo.equals("FURGON REFRIGERADO")||tipoVehiculo.equals("MINI CAMION")||tipoVehiculo.equals("TANQUERO")||
        		tipoVehiculo.equals("MINI BUS")||tipoVehiculo.equals("CONCRETERA")||tipoVehiculo.equals("RETROEXCAVADORA")||tipoVehiculo.equals("CHASISCABINADO")||
        		tipoVehiculo.equals("VOLQUETA")||tipoVehiculo.equals("CAMION")||tipoVehiculo.equals("BUS")||tipoVehiculo.equals("AMBULANCIA")||
        		tipoVehiculo.equals("TRACTOR") ){
            tipoVehiculo = "ESPECIAL";
        }else if(tipoVehiculo.equals("ESCOLAR")){
        	tipoVehiculo = "FURGONETA";        	
        }else if(tipoVehiculo.equals("JEEP 4X2")||tipoVehiculo.equals("JEEP")){
        	tipoVehiculo = "JEEP";        	
        }else if(tipoVehiculo.equals("PALA")||tipoVehiculo.equals("COSTA")){
        	tipoVehiculo = "PALA";        	
        }else if(tipoVehiculo.equals("SEDAN")){
        	tipoVehiculo = "SEDAN";        	
        }else if(tipoVehiculo.equals("JEEP STATION WAGON")||tipoVehiculo.equals("STATION WAGON")){
        	tipoVehiculo = "STATION WAGON";        	
        }else{
        	tipoVehiculo = "AUTOMOVIL";
        }                
        storedProcedure.setParameter("v_tipo_vh",tipoVehiculo);        
        storedProcedure.setParameter("v_marca_modelo",vehiculo.getModelo().getVhTari1MarcasModelos().getNombre());                   
        storedProcedure.setParameter("v_rastreo",vehiculo.getDispositivoRastreo() ?"SI":"NO");
        storedProcedure.setParameter("v_kilometraje",vehiculo.getKilometrajeAnual());
        storedProcedure.setParameter("v_hijos",vehiculo.getHijosConduzcan()?"SI":"NO");
        
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        int antiguedadVehiculo = anoActual -Integer.parseInt(vehiculo.getAnioFabricacion());        
        if(antiguedadVehiculo == -1){
        	antiguedadVehiculo=0;
        }
                
        String valorAntiguedad ="";
        if(antiguedadVehiculo >19){
        	valorAntiguedad ="20+";
        }else{
        	valorAntiguedad = String.valueOf(antiguedadVehiculo);
        }
                
        storedProcedure.setParameter("v_antig_vh",valorAntiguedad);
        storedProcedure.setParameter("v_suma_asegurada",vehiculo.getSumaAsegurada());
        storedProcedure.setParameter("v_deducible_perdida_total",Double.parseDouble(porcentajeSumaAseguradaDT)/100);                        
        storedProcedure.setParameter("v_deducible_rc",Double.parseDouble(deducibleRC));
        storedProcedure.setParameter("v_comision_agente",Double.parseDouble(comisionAgente));       
                
        VariableSistemaDAO variableDAO = new VariableSistemaDAO();
        if(montoFijo.length()==0)
        	montoFijo=variableDAO.buscarPorNombre("DANO_PARCIAL_MONTO_FIJO").getValor();                
        if(valorSiniestro.length()==0)
        	valorSiniestro=String.valueOf(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SINIESTRO").getValor())/100);
        if(porcentajeSumaAsegurada.length()==0)
        	porcentajeSumaAsegurada=String.valueOf(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SUMA_ASEGURADA").getValor())/100);
        storedProcedure.setParameter("v_deducible_perdida_parcial_minimo",Double.parseDouble(montoFijo));        
        storedProcedure.setParameter("v_deducible_perdida_parcial_porcentaje_siniestro",Double.parseDouble(valorSiniestro)/100);
        storedProcedure.setParameter("v_deducible_perdida_parcial_porcentaje_valor_asegurado",Double.parseDouble(porcentajeSumaAsegurada)/100);
        storedProcedure.setParameter("v_agente_id",agenteId);               
        
        storedProcedure.registerStoredProcedureParameter("rc_tasa_sin_impuestos", Double.class, ParameterMode.OUT); 
        storedProcedure.registerStoredProcedureParameter("dano_total_sin_impuestos", Double.class, ParameterMode.OUT); 
        storedProcedure.registerStoredProcedureParameter("todo_riesgo_sin_impuestos", Double.class, ParameterMode.OUT); 

        // execute SP
        storedProcedure.execute();
		
        Double rcValorSinImpuestos = (Double)storedProcedure.getOutputParameterValue("rc_tasa_sin_impuestos");
        Double danoTotalValorSinImpuestos = (Double)storedProcedure.getOutputParameterValue("dano_total_sin_impuestos");
        Double todoRiesgoValorSinImpuestos = (Double)storedProcedure.getOutputParameterValue("todo_riesgo_sin_impuestos");
        
        retorno.put("rcValorSinImpuestos", rcValorSinImpuestos == null ? 0 :rcValorSinImpuestos );
		retorno.put("danoTotalValorSinImpuestos", danoTotalValorSinImpuestos == null ? 0: danoTotalValorSinImpuestos);
		retorno.put("todoRiesgoValorSinImpuestos", todoRiesgoValorSinImpuestos== null ? 0:todoRiesgoValorSinImpuestos);
		
		return retorno;
						
	}
	
	
	
	
}

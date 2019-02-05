package com.qbe.cotizador.servlets.mantenimiento;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CoberturasConjuntoDAO;
import com.qbe.cotizador.dao.cotizacion.ConjuntoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.DeducibleDAO;
import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.PlanDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoTasaDAO;
import com.qbe.cotizador.dao.entidad.ConfiguracionProductoDAO;
import com.qbe.cotizador.dao.entidad.DetalleProductoDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.CoberturasConjunto;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.ConjuntoCobertura;
import com.qbe.cotizador.model.Deducible;
import com.qbe.cotizador.model.DetalleProducto;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.Plan;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.TipoCobertura;
import com.qbe.cotizador.model.TipoTasa;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.transaction.cotizacion.CoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CoberturasConjuntoTransaction;
import com.qbe.cotizador.transaction.cotizacion.ConfiguracionProductoTransaction;
import com.qbe.cotizador.transaction.cotizacion.ConjuntoCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.DeducibleTransaction;
import com.qbe.cotizador.transaction.cotizacion.GrupoCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.PlanTransaction;
import com.qbe.cotizador.transaction.cotizacion.ProductoTransaction;
import com.qbe.cotizador.transaction.producto.DetalleProductoTransaction;

public class MantenimientoVH {
	
	public MantenimientoVH(){
		
	}
	
	public static String mantenimientoCatalogosVH() throws Exception{
		
		Cobertura cobertura = new Cobertura();
		Plan plan = new Plan();
		Producto producto = new Producto();
		GrupoCobertura grupoCobertura = new GrupoCobertura();
		Ramo ramo = new Ramo();				
		CoberturaDAO coberturaDAO = new CoberturaDAO();
		PlanDAO planDAO = new PlanDAO();
		ProductoDAO productoDAO = new ProductoDAO();
		GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
		RamoDAO ramoDAO = new RamoDAO();
		
		PlanTransaction planTransaction = new PlanTransaction();
		ProductoTransaction productoTransaction = new ProductoTransaction();
		GrupoCoberturaTransaction grupoCoberturaTransaction = new GrupoCoberturaTransaction();
		ConfiguracionProductoTransaction configuracionProductoTransaction= new ConfiguracionProductoTransaction();
		ConjuntoCoberturaTransaction conjuntoCoberturaTransaction = new ConjuntoCoberturaTransaction();
		CoberturasConjuntoTransaction coberturasConjuntoTransaction = new CoberturasConjuntoTransaction();
		CoberturaTransaction coberturaTransaction = new CoberturaTransaction();
		DetalleProductoTransaction detalleProductoTransaction = new DetalleProductoTransaction();
		DeducibleTransaction deducibleTransaction = new DeducibleTransaction();
 
		System.out.println("Actualizacion Catalogos VH HORA INICIO: "+new Date());
		
		WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
		
		// Actualizacion Plan VH								
		String listadoPlanes = webService.getWebServiceCotizadorImplPort().obtenerPlanes();
		String [] listadoPlanesArr=listadoPlanes.split("\\¦");
		String resultadoActualizacion = "";
		int planesNuevos =0;
		int planesActualizados =0;
		for(String planFila:listadoPlanesArr){
			String [] planValores = planFila.split("\\|");
			String planEstado = "";
			plan = planDAO.buscarPorId(planValores[0]);
			
			if(plan == null || plan.getId()==null){
				plan = new Plan();
				planEstado = "NUEVO";
			}
			plan.setId(planValores[0].toString());
			plan.setNombre(planValores[1].toString());
			plan.setDescripcion(planValores[2].toString());
			plan.setSigla(planValores[3].toString());
			if(planEstado.equalsIgnoreCase("NUEVO")){												
				plan = planTransaction.crear(plan);									
				planesNuevos++;
			}	
			else{												
				plan = planTransaction.editar(plan);
				planesActualizados++;						
			}	
		}				
		resultadoActualizacion +="Planes: "+planesNuevos+" Nuevos "+planesActualizados+" Actualizados \\n";				
		
		// Actualizacion GrupoCobertura VH							
		String listadoGrupoCoberturas = webService.getWebServiceCotizadorImplPort().obtenerGruposCoberturaVH();
		int grupoCoberturaNuevos =0;
		int grupoCoberturaActualizados =0;
		String [] listadoGrupoCoberturasArr=listadoGrupoCoberturas.split("\\¦");
		for(String grupoCoberturaFila:listadoGrupoCoberturasArr){
			String [] grupoCoberturaValores = grupoCoberturaFila.split("\\|");
			String grupoCoberturaEstado = "";
			grupoCobertura = grupoCoberturaDAO.buscarPorId(grupoCoberturaValores[0]);
			
			if(grupoCobertura == null || grupoCobertura.getId()==null){
				grupoCobertura = new GrupoCobertura();
				grupoCoberturaEstado = "NUEVO";
			}
			grupoCobertura.setId(grupoCoberturaValores[0].toString());					
			grupoCobertura.setNombre(grupoCoberturaValores[1].toString());
			
			ramo = ramoDAO.buscarPorId(grupoCoberturaValores[2].toString());
			grupoCobertura.setRamo(ramo);
			
			grupoCobertura.setNombreNemotecnico(grupoCoberturaValores[3].toString());
			grupoCobertura.setCodcontable(grupoCoberturaValores[4].toString());
			grupoCobertura.setOrden(grupoCoberturaValores[5].toString());
			grupoCobertura.setSumaaltotal(Double.parseDouble(grupoCoberturaValores[6].toString()));
			grupoCobertura.setCuentapolizatotal(grupoCoberturaValores[7].toString());
												
			if(grupoCoberturaEstado.equalsIgnoreCase("NUEVO")){						
				grupoCobertura = grupoCoberturaTransaction.crear(grupoCobertura);
				grupoCoberturaNuevos++;
			}	
			else{					
				grupoCobertura = grupoCoberturaTransaction.editar(grupoCobertura);
				grupoCoberturaActualizados++;
			}	
		}
		resultadoActualizacion +="GrupoCobertura: "+grupoCoberturaNuevos+" Nuevos "+grupoCoberturaActualizados+" Actualizados \\n";
										
		// Actualizacion Cobertura VH							
		String listadoCoberturas = webService.getWebServiceCotizadorImplPort().obtenerCoberturasVH();
		String [] listadoCoberturasArr=listadoCoberturas.split("\\¦");
		int coberturasNuevos =0;
		int coberturasActualizados =0;
		for(String coberturaFila:listadoCoberturasArr){
			String [] coberturaValores = coberturaFila.split("\\|");
			String coberturaEstado = "";
			
			System.out.println(coberturaValores[0].toString());
			cobertura = coberturaDAO.buscarPorId(coberturaValores[0].toString());
			
			if(cobertura == null || cobertura.getId()==null){
				cobertura = new Cobertura();
				coberturaEstado = "NUEVO";
			}else{
				cobertura.setId(coberturaValores[0].toString());	
			}																																																					
			cobertura.setNombre(coberturaValores[1].toString());						
			cobertura.setTexto(coberturaValores[2].getBytes("UTF-8"));
			TipoCoberturaDAO tipoCoberturaDAO = new TipoCoberturaDAO();	
			TipoCobertura tipoCobertura = tipoCoberturaDAO.buscarPorId(coberturaValores[3].toString());
			cobertura.setTipoCobertura(tipoCobertura);						
			grupoCobertura = grupoCoberturaDAO.buscarPorId(coberturaValores[4].toString());
			cobertura.setGrupoCobertura(grupoCobertura);

			TipoTasaDAO ttDAO=new TipoTasaDAO();
			TipoTasa tipoTasa=ttDAO.buscarPorId(coberturaValores[11].toString().equals("1")?"1":"2");
			cobertura.setTipoTasa(tipoTasa);
			cobertura.setAfectaGrupo(coberturaValores[5].toString());
			cobertura.setAfectaValorAsegurado(coberturaValores[6].toString());
			cobertura.setSeccion(coberturaValores[7].toString());
			cobertura.setOrden(Integer.parseInt(coberturaValores[8].toString()));
			cobertura.setLimite(coberturaValores[9].toString());
			cobertura.setEsPredeterminada(coberturaValores[10].toString());						
			cobertura.setEsPrimaFija(coberturaValores[11].toString().equals("1")?"1":"0");
			cobertura.setEsTodoRiesgo(coberturaValores[12].toString());
			cobertura.setEsMasivo(coberturaValores[13].toString());
			cobertura.setEsPrincipal(coberturaValores[14].toString());
			cobertura.setRebajaValorAsegurado(coberturaValores[15].toString());
			cobertura.setGeneraEndosoRasa(coberturaValores[16].toString());
			cobertura.setEsIndemnizable(coberturaValores[17].toString());
			cobertura.setEsLimiteSuma(coberturaValores[18].toString());
			cobertura.setPrincipalCobertura(coberturaValores[19].toString());
													
			if(coberturaEstado.equalsIgnoreCase("NUEVO")){						
				cobertura = coberturaTransaction.crear(cobertura);
				coberturasNuevos++;
			}	
			else{					
				cobertura = coberturaTransaction.editar(cobertura);
				coberturasActualizados++;
			}	
		}
		resultadoActualizacion +="Cobertura: "+coberturasNuevos+" Nuevos "+coberturasActualizados+" Actualizados \\n";	
		
				
		// Actualizacion Productos VH							
		String listadoProducto = webService.getWebServiceCotizadorImplPort().obteneProductosVH();
		String [] listadoProductoArr =listadoProducto.split("\\¦");
		int productosNuevos=0;
		int productosActualizados=0;				
		for(String productoFila:listadoProductoArr){
			String [] productoValores = productoFila.split("\\|");
			String productoEstado = "";
			producto = productoDAO.buscarPorId(productoValores[0]);
			
			if(producto == null){
				producto = new Producto();
				productoEstado = "NUEVO";
			}										
			producto.setId(productoValores[0].toString());
			producto.setNombre(productoValores[1].toString());
			producto.setRamoId(new BigInteger(productoValores[2].toString()));
			producto.setDefecto(productoValores[3].toString());
			producto.setVigencia(Integer.parseInt(productoValores[4].toString()));
			producto.setDinamico(productoValores[5].toString());
			
			if(productoEstado.equalsIgnoreCase("NUEVO")){											
				producto = productoTransaction.crear(producto);	
				productosNuevos++;
			}	
			else{						
				producto = productoTransaction.editar(producto);	
				productosActualizados++;	
			}	
		}				
		resultadoActualizacion +="Productos: "+productosNuevos+" Nuevos "+productosActualizados+" Actualizados \\n";
		
		// Actualizacion ConfiguracionProducto							
		String listadoConfiguracionProducto = webService.getWebServiceCotizadorImplPort().obtenerConfiguracionProducto();
		String [] listadoConfiguracionProductoArr=listadoConfiguracionProducto.split("\\¦");
		int configuracionProductosNuevos=0;
		int configuracionProductosActualizados=0;				
		ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
		ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
		
		for(String configuracionProductoFila:listadoConfiguracionProductoArr){
			String [] configuracionProductoValores = configuracionProductoFila.split("\\|");
			String configuracionProductoEstado="";
			
			productoDAO = new ProductoDAO();
			producto=productoDAO.buscarPorId(configuracionProductoValores[1].toString());
			
			if (producto != null && producto.getId() != null) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 						
				configuracionProducto=configuracionProductoDAO.buscarPorId(configuracionProductoValores[0].toString());												
                	
				if (configuracionProducto == null || configuracionProducto.getId() == null) {
					configuracionProducto = new ConfiguracionProducto();
					configuracionProductoEstado = "NUEVO";
				}
										
				configuracionProducto.setId(configuracionProductoValores[0].toString());
				
				Date fecha=null;
				Date vigenciaDesde=null;
				Date vigenciaHasta=null;
				
				if(!configuracionProductoValores[2].toString().trim().equals(""))
					fecha=sdf.parse(configuracionProductoValores[2].toString());


				if(!configuracionProductoValores[4].toString().trim().equals(""))
					vigenciaDesde=sdf.parse(configuracionProductoValores[4].toString());
				
				if(!configuracionProductoValores[5].toString().trim().equals(""))
					vigenciaHasta=sdf.parse(configuracionProductoValores[5].toString());
				
				
				configuracionProducto.setProducto(producto);
				if(fecha!=null)
				configuracionProducto.setFecha(fecha);
				if(vigenciaDesde!=null)
				configuracionProducto.setVigenciadesde(vigenciaDesde);
				if(vigenciaHasta!=null)
				configuracionProducto.setVigenciahasta(vigenciaHasta);
				configuracionProducto.setVigente(configuracionProductoValores[3].toString());
				configuracionProducto.setModificable(configuracionProductoValores[6].toString());
				
				if(configuracionProductoEstado.equalsIgnoreCase("NUEVO")){												
					configuracionProducto = configuracionProductoTransaction.crear(configuracionProducto);
					configuracionProductosNuevos++;
				}	
				else{							
					configuracionProducto = configuracionProductoTransaction.editar(configuracionProducto);	
					configuracionProductosActualizados++;
				}							
			}
		}							
		resultadoActualizacion +="Configuracion Productos: "+configuracionProductosNuevos+" Nuevos "+configuracionProductosActualizados+" Actualizados \\n";
		
		// Actualizacion Conjunto Coberturas					
		String listadoConjuntoCoberturas = webService.getWebServiceCotizadorImplPort().obteneConjuntoCoberturasVH();
		String [] listadoConjuntoCoberturasArr =listadoConjuntoCoberturas.split("\\¦");				
		int conjuntoCoberturasNuevos=0;
		int conjuntoCoberturasActualizados=0;				
		for(String conjuntoCoberturasFila:listadoConjuntoCoberturasArr){
			String [] conjuntoCoberturasValores = conjuntoCoberturasFila.split("\\|");
			String conjuntoCoberturaEstado = "";
			ConjuntoCobertura conjuntoCobertura = new ConjuntoCobertura();
			ConjuntoCoberturaDAO conjuntoCoberturaDAO = new ConjuntoCoberturaDAO();
			conjuntoCobertura = conjuntoCoberturaDAO.buscarPorId(conjuntoCoberturasValores[0]);
			
			if(conjuntoCobertura == null||conjuntoCobertura.getId()==null){
				conjuntoCobertura = new ConjuntoCobertura();
				conjuntoCoberturaEstado = "NUEVO";
			}
			
			ConfiguracionProductoDAO cfDAO=new ConfiguracionProductoDAO();
			configuracionProducto=cfDAO.buscarPorId(conjuntoCoberturasValores[2]);
			
			if(configuracionProducto!=null&&configuracionProducto.getId()!=null){										
				conjuntoCobertura.setConfiguracionProducto(configuracionProducto);
				conjuntoCobertura.setOrden(conjuntoCoberturasValores[3]);					
				conjuntoCobertura.setId(conjuntoCoberturasValores[0]);
				conjuntoCobertura.setNombre(conjuntoCoberturasValores[1]);										
				if(conjuntoCoberturaEstado.equalsIgnoreCase("NUEVO")){											
					conjuntoCobertura = conjuntoCoberturaTransaction.crear(conjuntoCobertura);	
					conjuntoCoberturasNuevos++;	
				}	
				else{						
					conjuntoCobertura = conjuntoCoberturaTransaction.editar(conjuntoCobertura);	
					conjuntoCoberturasActualizados++;	
				}	
			}					
		}
		
		resultadoActualizacion +="Conjunto Coberturas: "+conjuntoCoberturasNuevos+" Nuevos "+conjuntoCoberturasActualizados+" Actualizados \\n";
		
		// Actualizacion CoberturasConjunto							
		ConjuntoCoberturaDAO conjuntoCoberturaDAO = new ConjuntoCoberturaDAO();
		CoberturasConjuntoDAO coberturasConjuntoDAO = new CoberturasConjuntoDAO();
        List<CoberturasConjunto> coberturasConjuntoGrabar=new ArrayList<CoberturasConjunto>();
		String listadoCoberturaConjunto = webService.getWebServiceCotizadorImplPort().obtenerCoberturasConjunto();
		String [] listadoCoberturaConjuntoArr=listadoCoberturaConjunto.split("\\¦");
		int coberturasConjuntoNuevos=0;
		int coberturasConjuntoActualizados=0;
		for(String coberturaConjuntoFila:listadoCoberturaConjuntoArr){
			String [] coberturaConjuntoValores = coberturaConjuntoFila.split("\\|");					
			ConjuntoCobertura conjuntoCobertura=conjuntoCoberturaDAO.buscarPorId(coberturaConjuntoValores[1].toString());				
			cobertura = coberturaDAO.buscarPorId(coberturaConjuntoValores[2].toString());										
			String coberturasConjuntoEstado="";
			
			if (conjuntoCobertura != null && conjuntoCobertura.getId() != null && cobertura!=null && cobertura.getId()!=null) {
				
				CoberturasConjunto coberturasConjunto=coberturasConjuntoDAO.buscarPorId(coberturaConjuntoValores[0].toString());
				
				if (coberturasConjunto == null || coberturasConjunto.getId() == null) {
					coberturasConjunto = new CoberturasConjunto();
					coberturasConjuntoEstado = "NUEVO";
				}						
				coberturasConjunto.setConjuntoCobertura(conjuntoCobertura);
				coberturasConjunto.setCobertura(cobertura);
				coberturasConjunto.setId(coberturaConjuntoValores[0].toString());						
				coberturasConjuntoGrabar.add(coberturasConjunto);
			}
		} 
		
		// Actualizacion Coberturas VH
		String listadoCoberturasTotal = webService.getWebServiceCotizadorImplPort().obtenerCoberturaDetalleProducto();
		String [] listadoCoberturasDetalleArr=listadoCoberturasTotal.split("\\¦");
		DetalleProductoDAO detalleProductoDAO= new DetalleProductoDAO();								
		List<DetalleProducto> detallesGrabar=new ArrayList<DetalleProducto>();
		for(String coberturaDetalleFila:listadoCoberturasDetalleArr){
			
			String [] coberturaValores = coberturaDetalleFila.split("\\|");
			
			DetalleProducto detalleProducto=detalleProductoDAO.buscarPorId(coberturaValores[20].toString());
			
			detalleProducto.setAfectaPrima(coberturaValores[26].toString());
			detalleProducto.setAfectaValorAsegurado(coberturaValores[27].toString());
			
			CoberturasConjunto coberturasConjunto=coberturasConjuntoDAO.buscarPorId(coberturaValores[22].toString());
			
			if(coberturasConjunto!=null&&coberturasConjunto.getId()!=null)
			detalleProducto.setCoberturasConjunto(coberturasConjunto);
			
			configuracionProductoDAO=new ConfiguracionProductoDAO(); 
			configuracionProducto=configuracionProductoDAO.buscarPorId(coberturaValores[36].toString());
			
			if(configuracionProducto!=null&&configuracionProducto.getId()!=null)
			detalleProducto.setConfiguracionProducto(configuracionProducto);
			
			detalleProducto.setDefecto(coberturaValores[31].toString());
			detalleProducto.setId(coberturaValores[20].toString());
			detalleProducto.setMonto(Double.parseDouble(coberturaValores[24].toString()));
			detalleProducto.setPeriodicidad(Integer.parseInt(coberturaValores[30].toString()));
			
			planDAO=new PlanDAO(); 
			plan=planDAO.buscarPorId(coberturaValores[21].toString());
			
			if(plan!=null&&plan.getId()!=null)
			detalleProducto.setPlan(plan);
			
			detalleProducto.setPorccomisionvendedor(Double.parseDouble(coberturaValores[34].toString()));
			detalleProducto.setPorcentajeComision(Double.parseDouble(coberturaValores[32].toString()));
			detalleProducto.setPorcotros(Double.parseDouble(coberturaValores[37].toString()));
			detalleProducto.setActivadoPorEsb(coberturaValores[38].toString());
            detalleProducto.setPorcutilidad(Double.parseDouble(coberturaValores[35].toString()));
			detalleProducto.setPrima(Double.parseDouble(coberturaValores[25].toString()));
			detalleProducto.setPrimaBasica(Double.parseDouble(coberturaValores[33].toString()));
			detalleProducto.setTasa(Double.parseDouble(coberturaValores[23].toString()));
			detalleProducto.setTexto(coberturaValores[28].toString().getBytes("UTF-8") );
			detalleProducto.setValorPeriodo(Double.parseDouble(coberturaValores[29].toString()));					
			detallesGrabar.add(detalleProducto);
		}
		
		detalleProductoDAO.eliminarTodos();
		coberturasConjuntoDAO.eliminarTodos();
		
        for (CoberturasConjunto cc : coberturasConjuntoGrabar) {
            cc = coberturasConjuntoTransaction.crear(cc);
            coberturasConjuntoNuevos++;
          
        }
        
		resultadoActualizacion +="Coberturas Conjunto: "+coberturasConjuntoNuevos+" Nuevos "+coberturasConjuntoActualizados+" Actualizados \\n";
        												
		int detalleProductoCreados=0;								
		for(DetalleProducto detalle:detallesGrabar){					
				DetalleProducto detalleGrabar = detalleProductoTransaction.crear(detalle);	
				detalleProductoCreados++;					
		}								
		resultadoActualizacion +="Detalle Productos: "+detalleProductoCreados+" Nuevos \\n";								
		detallesGrabar=null;
		
		// Actualizacion deducibles						
		String listadoDeducibles = webService.getWebServiceCotizadorImplPort().obtenerDeducibles();
		String [] listadoDeduciblesArr=listadoDeducibles.split("\\¦");
		int deduciblesCreados=0;
		int deduciblesActualizados=0;
		DeducibleDAO deducibleDAO=new DeducibleDAO();
		List<Deducible> deduciblesGrabar=new ArrayList<Deducible>();
		
		for(String deducibleFila:listadoDeduciblesArr){
			String [] deducibleValores = deducibleFila.split("\\|");
			
			Deducible deducible=deducibleDAO.buscarPorProductoDeducible(deducibleValores[0],deducibleValores[1]);
			
			if(deducible==null||deducible.getId()==null){
				deducible=deducibleDAO.buscarPorCoberturaPlanDeducible(deducibleValores[0],deducibleValores[5],deducibleValores[6],deducibleValores[1]);
			}
			
			if (deducible == null || deducible.getId() == null) {
				deducible = new Deducible();
			}
			
			if(!deducibleValores[6].equals("0"))
				deducible.setPlanId(deducibleValores[6]);
			if(!deducibleValores[5].equals("0"))
				deducible.setCoberturaId(deducibleValores[5]);
			deducible.setTipoDeducibleId(BigInteger.valueOf(Long.parseLong(deducibleValores[3])));
			deducible.setTexto(deducibleValores[2]);
			deducible.setProductoId(deducibleValores[1]);
			deducible.setValor(Double.parseDouble(deducibleValores[4]));
			deducible.setDeducibleId(deducibleValores[0]);
			deduciblesGrabar.add(deducible);	
			
			}																
		deducibleDAO.eliminarTodos();				
		
		for(Deducible deducibleGrabar:deduciblesGrabar){
			if(deducibleGrabar.getId()==null){
				deducibleGrabar = deducibleTransaction.crear(deducibleGrabar);						
				deduciblesCreados++;
			}	
			else{
				deducibleGrabar = deducibleTransaction.editar(deducibleGrabar);							
				deduciblesActualizados++;
			}
		}				
		resultadoActualizacion +="Deducibles: "+deduciblesCreados+" Nuevos "+deduciblesActualizados+" Actualizados \\n";
		System.out.println(resultadoActualizacion);
		resultadoActualizacion += " ---- HORA FIN: "+new Date()+"\\n";				
		System.out.println("Actualizacion Catalogos VH HORA FIN: "+new Date());
		
		return resultadoActualizacion;
		
	}

}

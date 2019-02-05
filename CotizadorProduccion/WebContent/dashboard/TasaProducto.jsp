<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tasa Por Producto - CotizadorQBE</title>

<!-- Core CSS - Include with every page -->
<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>
<script src="../static/js/jquery.nicescroll.min.js"></script>
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
	var codigo = "";
	var nombre = "";
	var matriz = 0;
	var activo = 0;
	var tipoConsulta = "";	
	var arrGrupoProducto = new Array();
	var arrCodigoGrupoPorProducto = new Object();
	var arrTipoUso = new Array();
	var arrCodigoTipoUso= new Object();
	var arrCombustible = new Array();
	var arrCodigoCombustible= new Object();
	
	var QueryString = function () {
		// This function is anonymous, is executed immediately and
		// the return value is assigned to QueryString!
		var query_string = {};
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			// If first entry with this name
			if (typeof query_string[pair[0]] === "undefined") {
				query_string[pair[0]] = pair[1];
			// If second entry with this name
			} else if (typeof query_string[pair[0]] === "string") {
				var arr = [query_string[pair[0]], pair[1]];
				query_string[pair[0]] = arr;
			// If third or later entry with this name
		} else {
		query_string[pair[0]].push(pair[1]);
		}
	}
	return query_string;
}
();

	$(document).ready(function() {
						//Para Nuevo desde GrupoPorProducto
						if(QueryString.id != null){								
							var auxqueryID = QueryString.id.toString();							
							if(auxqueryID != ""){
								$( "#addButton" ).trigger( "click" );
								$( "#grupoPorProducto" ).replaceWith( "<input type='text' class='form-control' id='grupoPorProducto' value='"+auxqueryID+ "' disabled>" );
							}
						}
						//Fin Nuevo desde GrupoPorProducto
						
						var nicesx = $("#tabla").niceScroll({
							touchbehavior : false,
							cursorcolor : "#0000FF",
							cursoropacitymax : 0.6,
							cursorwidth : 8
						});
						activarMenu("TasaProducto");
						$('.well').hide();
						$
								.ajax({
									url : '../TasaProductoController',
									data : {
										"tipoConsulta" : "encontrarTodos"
									},
									type : 'POST',
									datatype : 'json',
									success : function(data) {
										$("#loading").remove();
										if (data.numRegistros > 0) {
											var listadoTasaProducto = data.listadoTasaProducto;

											//Para combos
											
											var listadoTipoUso = data.listadoTipoUso;
											$
													.each(
															listadoTipoUso,
															function(index) {
																arrTipoUso.push(listadoTipoUso[index].nombre);
																arrCodigoTipoUso[listadoTipoUso[index].nombre] = listadoTipoUso[index].codigo;
																$("#tipoUsoListado")
																		.append(
																				"<option value='"+listadoTipoUso[index].nombre+"'>"
																						+ listadoTipoUso[index].nombre
																						+ "</option>");
															});

											var listadoGrupoPorProducto = data.listadoGrupoPorProducto;
											$
													.each(
															listadoGrupoPorProducto,
															function(index) {
																arrGrupoProducto
																		.push(listadoGrupoPorProducto[index].nombre);
																arrCodigoGrupoPorProducto[listadoGrupoPorProducto[index].nombre] = listadoGrupoPorProducto[index].codigo;
																$("#grupoPorProducto").append("<option value='"+listadoGrupoPorProducto[index].nombre+"'>"
																						+ listadoGrupoPorProducto[index].nombre+ "</option>");
															});
											
											var listadoCombustible = data.listadoCombustible;
											$
													.each(
															listadoCombustible,
															function(index) {
																arrCombustible
																		.push(listadoCombustible[index].nombre);
																arrCodigoCombustible[listadoCombustible[index].nombre] = listadoCombustible[index].codigo;
																$("#combustibleUtilizadoValorId").append("<option value='"+listadoCombustible[index].nombre+"'>"
																						+ listadoCombustible[index].nombre+ "</option>");
															});
											
											

														$.each(listadoTasaProducto,function(index) {
																$("#dataTableContent").append(
																						"<tr class='odd gradeX'>"																								
																						+ " <td relation='grupoPorProducto'>"
																						+ listadoTasaProducto[index].grupoPorProducto
																						+ "</td>"
																						+ " <td relation='nombre'>"
																						+ listadoTasaProducto[index].nombre
																						+ "</td>"
																						+ " <td relation='porcentajeCasco'>"
																						+ listadoTasaProducto[index].porcentajeCasco
																						+ "</td>"
																						+ " <td relation='porcentajeExtras'>"
																						+ listadoTasaProducto[index].porcentajeExtras
																						+ "</td>"
																						+ " <td relation='tieneSumaAsegurada' style='display: none'>"
																						+ listadoTasaProducto[index].tieneSumaAsegurada
																						+ "</td>"
																						+ " <td relation='sumaAseguradaInicio' style='display: none'>"
																						+ listadoTasaProducto[index].sumaAseguradaInicio
																						+ "</td>"
																						+ " <td relation='sumaAseguradaFin' style='display: none'>"
																						+ listadoTasaProducto[index].sumaAseguradaFin
																						+ "</td>"
																						+ " <td relation='tieneAntiguedadVh' style='display: none'>"
																						+ listadoTasaProducto[index].tieneAntiguedadVh
																						+ "</td>"
																						+ " <td relation='antiguedadInicio' style='display: none'>"
																						+ listadoTasaProducto[index].antiguedadInicio
																						+ "</td>"
																						+ " <td relation='antiguedadFin' style='display: none'>"
																						+ listadoTasaProducto[index].antiguedadFin
																						+ "</td>"
																						+ " <td relation='tieneDispositivoRastreo' style='display: none'>"
																						+ listadoTasaProducto[index].tieneDispositivoRastreo
																						+ "</td>"
																						+ " <td relation='tieneTipoVehiculo' style='display: none'>"
																						+ listadoTasaProducto[index].tieneTipoVehiculo
																						+ "</td>"
																						+ " <td relation='tipoVehiculoNombre' style='display: none'>"
																						+ listadoTasaProducto[index].tipoVehiculoNombre
																						+ "</td>"
																						+ " <td relation='tieneTonelaje' style='display: none'>"
																						+ listadoTasaProducto[index].tieneTonelaje
																						+ "</td>"
																						+ " <td relation='valorTonelajeInicio' style='display: none'>"
																						+ listadoTasaProducto[index].valorTonelajeInicio
																						+ "</td>"
																						+ " <td relation='valorTonelajeFin' style='display: none'>"
																						+ listadoTasaProducto[index].valorTonelajeFin
																						+ "</td>"
																						+ " <td relation='tieneRegion' style='display: none'>"
																						+ listadoTasaProducto[index].tieneRegion
																						+ "</td>"
																						+ " <td relation='valorRegion' style='display: none'>"
																						+ listadoTasaProducto[index].valorRegion
																						+ "</td>"
																						+ " <td relation='tieneDeducible' style='display: none'>"
																						+ listadoTasaProducto[index].tieneDeducible
																						+ "</td>"
																						+ " <td relation='deduciblePorcentajeSiniestro' style='display: none'>"
																						+ listadoTasaProducto[index].deduciblePorcentajeSiniestro
																						+ "</td>"
																						+ " <td relation='deduciblePorcentajeValorAsegurado' style='display: none'>"
																						+ listadoTasaProducto[index].deduciblePorcentajeValorAsegurado
																						+ "</td>"
																						+ " <td relation='deducibleMinimo' style='display: none'>"
																						+ listadoTasaProducto[index].deducibleMinimo
																						+ "</td>"
																						+ " <td relation='tieneDeduciblePerdidaTotalSiniestro' style='display: none'>"
																						+ listadoTasaProducto[index].tieneDeduciblePerdidaTotalSiniestro
																						+ "</td>"
																						+ " <td relation='deduciblePerdidaTotalSiniestro' style='display: none'>"
																						+ listadoTasaProducto[index].deduciblePerdidaTotalSiniestro
																						+ "</td>"
																						+ " <td relation='tieneAnoFabricacion' style='display: none'>"
																						+ listadoTasaProducto[index].tieneAnoFabricacion
																						+ "</td>"
																						+ " <td relation='anoFabricacionInicio' style='display: none'>"
																						+ listadoTasaProducto[index].anoFabricacionInicio
																						+ "</td>"
																						+ " <td relation='anoFabricacionFin' style='display: none'>"
																						+ listadoTasaProducto[index].anoFabricacionFin
																						+ "</td>"
																						+ " <td relation='tieneEdadConductor' style='display: none'>"
																						+ listadoTasaProducto[index].tieneEdadConductor
																						+ "</td>"
																						+ " <td relation='edadConductorInicio' style='display: none'>"
																						+ listadoTasaProducto[index].edadConductorInicio
																						+ "</td>"
																						+ " <td relation='edadConductorFin' style='display: none'>"
																						+ listadoTasaProducto[index].edadConductorFin
																						+ "</td>"
																						+ " <td relation='tieneMarca' style='display: none'>"
																						+ listadoTasaProducto[index].tieneMarca
																						+ "</td>"
																						+ " <td relation='marcaListado' style='display: none'>"
																						+ listadoTasaProducto[index].marcaListado
																						+ "</td>"
																						+ " <td relation='tieneModelo' style='display: none'>"
																						+ listadoTasaProducto[index].tieneModelo
																						+ "</td>"
																						+ " <td relation='modeloListado' style='display: none'>"
																						+ listadoTasaProducto[index].modeloListado
																						+ "</td>"
																						+ " <td relation='ceroKilometros' style='display: none'>"
																						+ listadoTasaProducto[index].ceroKilometros
																						+ "</td>"
																						+ " <td relation='tieneConductorGenero' style='display: none'>"
																						+ listadoTasaProducto[index].tieneConductorGenero
																						+ "</td>"
																						+ " <td relation='conductorGeneroValor' style='display: none'>"
																						+ listadoTasaProducto[index].conductorGeneroValor
																						+ "</td>"
																						+ " <td relation='tieneNumeroHijos' style='display: none'>"
																						+ listadoTasaProducto[index].tieneNumeroHijos
																						+ "</td>"
																						+ " <td relation='numeroHijos' style='display: none'>"
																						+ listadoTasaProducto[index].numeroHijos
																						+ "</td>"
																						+ " <td relation='tieneZona' style='display: none'>"
																						+ listadoTasaProducto[index].tieneZona
																						+ "</td>"
																						+ " <td relation='valorZona' style='display: none'>"
																						+ listadoTasaProducto[index].valorZona
																						+ "</td>"
																						+ " <td relation='tieneGuardaGarage' style='display: none'>"
																						+ listadoTasaProducto[index].tieneGuardaGarage
																						+ "</td>"
																						+ " <td relation='tieneKilometrosRecorridos' style='display: none'>"
																						+ listadoTasaProducto[index].tieneKilometrosRecorridos
																						+ "</td>"
																						+ " <td relation='kilometrosRecorridosInicio' style='display: none'>"
																						+ listadoTasaProducto[index].kilometrosRecorridosInicio
																						+ "</td>"
																						+ " <td relation='kilometrosRecorridosFin' style='display: none'>"
																						+ listadoTasaProducto[index].kilometrosRecorridosFin
																						+ "</td>"
																						+ " <td relation='tieneCombustibleUtilizado' style='display: none'>"
																						+ listadoTasaProducto[index].tieneCombustibleUtilizado
																						+ "</td>"
																						+ " <td relation='combustibleUtilizadoValorId' style='display: none'>"
																						+ listadoTasaProducto[index].combustibleUtilizadoValorId
																						+ "</td>"
																						+ " <td relation='tieneTipoUso' style='display: none'>"
																						+ listadoTasaProducto[index].tieneTipoUso
																						+ "</td>"
																						+ " <td relation='tipoUsoListado' style='display: none'>"
																						+ listadoTasaProducto[index].tipoUsoListado
																						+ "</td>"
																						+ " <td relation='tieneCargaPasajeros' style='display: none'>"
																						+ listadoTasaProducto[index].tieneCargaPasajeros
																						+ "</td>"
																						+ " <td relation='cargaPasajerosValor' style='display: none'>"
																						+ listadoTasaProducto[index].cargaPasajerosValor
																						+ "</td>"
																						+ " <td relation='tieneAdquisicion' style='display: none'>"
																						+ listadoTasaProducto[index].tieneAdquisicion
																						+ "</td>"
																						+ " <td relation='nombreAdquisicion' style='display: none'>"
																						+ listadoTasaProducto[index].nombreAdquisicion
																						+ "</td>"
																						+ " <td relation='esFlotaIndividual' style='display: none'>"
																						+ listadoTasaProducto[index].esFlotaIndividual
																						+ "</td>"
																						+ " <td relation='valorFlotaIndividual' style='display: none'>"
																						+ listadoTasaProducto[index].valorFlotaIndividual
																						+ "</td>"
																						+ " <td relation='tieneTipoObjetoVehiculo' style='display: none'>"
																						+ listadoTasaProducto[index].tieneTipoObjetoVehiculo
																						+ "</td>"
																						+ " <td relation='valorTipoObjetoVehiculo' style='display: none'>"
																						+ listadoTasaProducto[index].valorTipoObjetoVehiculo
																						+ "</td>"
																						+ " <td relation='tieneRenovacion' style='display: none'>"
																						+ listadoTasaProducto[index].tieneRenovacion
																						+ "</td>"																						
																						+ " <td width='175px'>"
																						+ " <input type='hidden' value='"+ listadoTasaProducto[index].codigo +"'/>"
																						+ " <button type='button' class='btn btn-success btn-xs actualizar-btn'>"
																						+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																						+ " </button>"
																						+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>"
																						+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																						+ " </button>"
																						+ "</td>"
																						+ "</tr>");

															});

										/* Inicio Controles Actualizar Registro*/
										$(".actualizar-btn").bind({
															click : function() {
																$("#addButton").trigger("click");
																$("#codigo").val($(this).parent().children().first().val());
																var elem = $(this).parent();
																var bandera = 1;
																do {
																	elem = elem.prev();
																	if (elem.is("td")) {
																		var elemCode = elem.attr("relation");
																		elementType(
																				elem.text(),
																				elemCode,
																				$("#" + elemCode).attr("type"));
																	} else {
																		bandera = 0;
																	}
																} while (bandera == 1);
																
																$("#tieneSumaAsegurada").trigger("change");
																$("#tieneAntiguedadVh").trigger("change");
																$("#tieneTipoVehiculo").trigger("change");
																$("#tieneTonelaje").trigger("change");
																$("#tieneRegion").trigger("change");
																$("#tieneEdadConductor").trigger("change");
																$("#tieneMarca").trigger("change");
																$("#tieneModelo").trigger("change");
																$("#tieneZona").trigger("change");
																$("#tieneKilometrosRecorridos").trigger("change");
																$("#tieneCombustibleUtilizado").trigger("change");
																$("#tieneTipoUso").trigger("change");
																$("#tieneCargaPasajeros").trigger("change");
																$("#esFlotaIndividual").trigger("change");
																
															}
														});
										/* Fin Controles Actualizar Registro*/

											/* Inicio Controles Eliminar Registro */
											$(".eliminar-btn").bind({
																click : function() {
																	var r = confirm("Seguro que desea eliminar?"
																			+ $(this).parent().parent().children().first().text());
																	if (r == true) {
																		codigo = $(this).parent().children().first().val();
																		nombre= "";
																		grupoPorProducto = "";																		
																		porcentajeCasco = "";
																		porcentajeExtras = "";
																		tieneSumaAsegurada = "";
																		sumaAseguradaInicio = "";
																		sumaAseguradaFin = "";
																		tieneAntiguedadVh = "";
																		antiguedadInicio = "";
																		antiguedadFin = "";
																		tieneDispositivoRastreo = "";
																		tieneTipoVehiculo = "";
																		tipoVehiculoNombre = "";
																		tieneTonelaje = "";
																		valorTonelajeInicio = "";
																		valorTonelajeFin = "";
																		tieneRegion = "";
																		valorRegion = "";
// 																		tieneDeducible = "";
// 																		deduciblePorcentajeSiniestro = "";
// 																		deduciblePorcentajeValorAsegurado = "";
// 																		deducibleMinimo = "";
// 																		tieneDeduciblePerdidaTotalSiniestro = "";
// 																		deduciblePerdidaTotalSiniestro = "";
// 																		tieneAnoFabricacion = "";
// 																		anoFabricacionInicio = "";
// 																		anoFabricacionFin = "";
																		tieneEdadConductor = "";
																		edadConductorInicio = "";
																		edadConductorFin = "";
																		tieneMarca = "";
																		marcaListado = "";
																		tieneModelo = "";
																		modeloListado = "";
// 																		ceroKilometros = "";
// 																		tieneConductorGenero = "";
// 																		conductorGeneroValor = "";
// 																		tieneNumeroHijos = "";
// 																		numeroHijos = "";
																		tieneZona = "";
																		valorZona = "";
																		tieneGuardaGarage = "";
																		tieneKilometrosRecorridos = "";
																		kilometrosRecorridosInicio = "";
																		kilometrosRecorridosFin = "";
																		tieneCombustibleUtilizado = "";
																		combustibleUtilizadoValorId = "";
																		tieneTipoUso = "";
																		tipoUsoListado = "";
																		tieneCargaPasajeros = "";
																		cargaPasajerosValor = "";
// 																		tieneAdquisicion = "";
// 																		nombreAdquisicion = "";
																		esFlotaIndividual = "";
																		valorFlotaIndividual = "";
// 																		tieneTipoObjetoVehiculo = "";
// 																		valorTipoObjetoVehiculo = "";
																		tieneRenovacion = "";
																		tipoConsulta = "eliminar";
																		enviarDatos(
																				nombre,
																				codigo,																				
																				grupoPorProducto,																																						
																				porcentajeCasco,
																				porcentajeExtras,
																				tieneSumaAsegurada,
																				sumaAseguradaInicio,
																				sumaAseguradaFin,
																				tieneAntiguedadVh,
																				antiguedadInicio,
																				antiguedadFin,
																				tieneDispositivoRastreo,
																				tieneTipoVehiculo,
																				tipoVehiculoNombre,
																				tieneTonelaje,
																				valorTonelajeInicio,
																				valorTonelajeFin,
																				tieneRegion,
																				valorRegion,
// 																				tieneDeducible,
// 																				deduciblePorcentajeSiniestro,
// 																				deduciblePorcentajeValorAsegurado,
// 																				deducibleMinimo,
// 																				tieneDeduciblePerdidaTotalSiniestro,
// 																				deduciblePerdidaTotalSiniestro,
// 																				tieneAnoFabricacion,
// 																				anoFabricacionInicio,
// 																				anoFabricacionFin,
																				tieneEdadConductor,
																				edadConductorInicio,
																				edadConductorFin,
																				tieneMarca,
																				marcaListado,
																				tieneModelo,
																				modeloListado,
// 																				ceroKilometros,
// 																				tieneConductorGenero,
// 																				conductorGeneroValor,
// 																				tieneNumeroHijos,
// 																				numeroHijos,
																				tieneZona,
																				valorZona,
																				tieneGuardaGarage,
																				tieneKilometrosRecorridos,
																				kilometrosRecorridosInicio,
																				kilometrosRecorridosFin,
																				tieneCombustibleUtilizado,
																				combustibleUtilizadoValorId,
																				tieneTipoUso,
																				tipoUsoListado,
																				tieneCargaPasajeros,
																				cargaPasajerosValor,
// 																				tieneAdquisicion,
// 																				nombreAdquisicion,
																				esFlotaIndividual,
																				valorFlotaIndividual,
// 																				tieneTipoObjetoVehiculo,
// 																				valorTipoObjetoVehiculo,	
																				tieneRenovacion,
																				tipoConsulta);
																		$(this).parent().parent().remove();
																	}
																}
															});
											/* Fin Controles Eliminar Registro */
										} else {
											$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
										}
										$(".hideColumn").hide();
									}
								});

						/* Inicio Controles Grabar Registro*/
						$("#save-record").click(function() {	
							var requerido = true;	
							grupoPorProducto = $("#grupoPorProducto").val();	
							if(grupoPorProducto == 0 && requerido)
							{
								alert("Por favor seleccione un grupo por producto");	
								requerido = false;
							}
											$(".required").css("border","1px solid #ccc");
											$(".required").each(function(index) {
																var cadena = $(this).val();
																if (cadena.length <= 0 && requerido) {
																	$(this).css("border","1px solid red");
																	alert("Por favor ingrese el campo requerido");
																	$(this).focus();
																	requerido = false;
																	return false;
																}
															});

										
											codigo = $("#codigo").val();
											nombre = $("#nombre").val();
																					
											porcentajeCasco = $("#porcentajeCasco").val();
											porcentajeExtras = $("#porcentajeExtras").val();
											sumaAseguradaInicio = $("#sumaAseguradaInicio").val();
											sumaAseguradaFin = $("#sumaAseguradaFin").val();
											
											
											
											if ($("#tieneSumaAsegurada").is(':checked')){
												tieneSumaAsegurada = 1;
												if((sumaAseguradaInicio == "" || sumaAseguradaFin == "") && requerido)
												{
													alert("Ingrese el rango de sumas aseguradas");	
													requerido = false;
												}
												sumaAseguradaInicio = parseFloat(sumaAseguradaInicio);
												sumaAseguradaFin = parseFloat(sumaAseguradaFin);
												if((sumaAseguradaInicio > sumaAseguradaFin) && requerido)
												{
													alert("La suma asegurada inicio no puede ser mayor a la suma asegurada fin");	
													requerido = false;
												}
											}else{
												tieneSumaAsegurada = 0;
											}
											
											antiguedadInicio = $("#antiguedadInicio").val();
											antiguedadFin = $("#antiguedadFin").val();
											
											if ($("#tieneAntiguedadVh").is(':checked')){
												tieneAntiguedadVh = 1;
												if((antiguedadInicio == "" || antiguedadFin == "") && requerido)
												{
													alert("Ingrese el rango de antiguedad");	
													requerido = false;
												}
												antiguedadInicio = parseFloat(antiguedadInicio);
												antiguedadFin = parseFloat(antiguedadFin);
												if((antiguedadInicio > antiguedadFin) && requerido)
												{
													alert("La antiguedad inicio no puede ser mayor a la antiguedad fin");	
													requerido = false;
												}
											}else{
												tieneAntiguedadVh = 0;
											}
											
											if ($("#tieneDispositivoRastreo").is(':checked')){ 
												tieneDispositivoRastreo = 1;
											}else{
												tieneDispositivoRastreo = 0;
											}
											
											tipoVehiculoNombre = $("#tipoVehiculoNombre").val();
											if ($("#tieneTipoVehiculo").is(':checked')){
												tieneTipoVehiculo = 1;
												if(tipoVehiculoNombre == "" && requerido)
												{
													alert("Ingrese los tipos de vehiculos con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneTipoVehiculo = 0;
											}
											
											valorTonelajeInicio = $("#valorTonelajeInicio").val();
											valorTonelajeFin = $("#valorTonelajeFin").val();
											if ($("#tieneTonelaje").is(':checked')){
												tieneTonelaje = 1;
												if((valorTonelajeInicio == "" || valorTonelajeFin == "") && requerido)
												{
													alert("Ingrese el rango de tonelaje");	
													requerido = false;
												}
												valorTonelajeInicio = parseFloat(valorTonelajeInicio);
												valorTonelajeFin = parseFloat(valorTonelajeFin);
												if((valorTonelajeInicio > valorTonelajeFin) && requerido)
												{
													alert("El tonelaje inicio no puede ser mayor al tonelaje fin");	
													requerido = false;
												}
											}else{
												tieneTonelaje = 0;
											}
											
											valorRegion = $("#valorRegion").val();
											if ($("#tieneRegion").is(':checked')){
												tieneRegion = 1;
												if(valorRegion == "" && requerido)
												{
													alert("Ingrese las regiones con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneRegion = 0;
											}
											
// 											if ($("#tieneDeducible").is(':checked')){
// 												tieneDeducible = 1;
// 											}else{
// 												tieneDeducible = 0;
// 											}
// 											deduciblePorcentajeSiniestro = $("#deduciblePorcentajeSiniestro").val();
// 											deduciblePorcentajeValorAsegurado = $("#deduciblePorcentajeValorAsegurado").val();
// 											deducibleMinimo = $("#deducibleMinimo").val();
// 											if ($("#tieneDeduciblePerdidaTotalSiniestro").is(':checked')){
// 												tieneDeduciblePerdidaTotalSiniestro = 1;
// 											}else{
// 												tieneDeduciblePerdidaTotalSiniestro = 0;
// 											}
// 											deduciblePerdidaTotalSiniestro = $("#deduciblePerdidaTotalSiniestro").val();
// 											if ($("#tieneAnoFabricacion").is(':checked')){
// 												tieneAnoFabricacion = 1;
// 											}else{
// 												tieneAnoFabricacion = 0;
// 											}
// 											anoFabricacionInicio = $("#anoFabricacionInicio").val();
// 											anoFabricacionFin = $("#anoFabricacionFin").val();

											edadConductorInicio = $("#edadConductorInicio").val();
											edadConductorFin = $("#edadConductorFin").val();
											if ($("#tieneEdadConductor").is(':checked')){
												tieneEdadConductor = 1;
												if((edadConductorInicio == "" || edadConductorFin == "") && requerido)
												{
													alert("Ingrese el rango de edad del conductor");	
													requerido = false;
												}
												edadConductorInicio = parseFloat(edadConductorInicio);
												edadConductorFin = parseFloat(edadConductorFin);
												if((edadConductorInicio > edadConductorFin) && requerido)
												{
													alert("La edad inicio no puede ser mayor a la edad fin");	
													requerido = false;
												}
											}else{
												tieneEdadConductor = 0;
											} 
											
											marcaListado = $("#marcaListado").val();
											if ($("#tieneMarca").is(':checked')){
												tieneMarca = 1;
												if(marcaListado == "" && requerido)
												{
													alert("Ingrese las marcas con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneMarca = 0;
											}
											
											modeloListado = $("#modeloListado").val();		
											if ($("#tieneModelo").is(':checked')){
												tieneModelo = 1;
												if(modeloListado == "" && requerido)
												{
													alert("Ingrese los modelos con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneModelo = 0;
											}
																				
// 											if ($("#ceroKilometros").is(':checked')){
// 												ceroKilometros = 1;
// 											}else{
// 												ceroKilometros = 0;
// 											} 
// 											if ($("#tieneConductorGenero").is(':checked')){
// 												tieneConductorGenero = 1;
// 											}else{
// 												tieneConductorGenero = 0;
// 											}
// 											conductorGeneroValor = $("#conductorGeneroValor").val();
// 											if ($("#tieneNumeroHijos").is(':checked')){
// 												tieneNumeroHijos = 1;
// 											}else{
// 												tieneNumeroHijos = 0;
// 											}
// 											numeroHijos = $("#numeroHijos").val();
											
											valorZona = $("#valorZona").val();
											if ($("#tieneZona").is(':checked')){
												tieneZona = 1;
												if(valorZona == "" && requerido)
												{
													alert("Ingrese las zonas con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneZona = 0;
											}
											
											if ($("#tieneGuardaGarage").is(':checked')){
												tieneGuardaGarage = 1;
											}else{
												tieneGuardaGarage = 0;
											}
											
											kilometrosRecorridosInicio = $("#kilometrosRecorridosInicio").val();
											kilometrosRecorridosFin = $("#kilometrosRecorridosFin").val();
											if ($("#tieneKilometrosRecorridos").is(':checked')){
												tieneKilometrosRecorridos = 1;
												if((kilometrosRecorridosInicio == "" || kilometrosRecorridosFin == "") && requerido)
												{
													alert("Ingrese el rango de kilometros recorridos");	
													requerido = false;
												}
												kilometrosRecorridosInicio = parseFloat(kilometrosRecorridosInicio);
												kilometrosRecorridosFin = parseFloat(kilometrosRecorridosFin);
												if((kilometrosRecorridosInicio > kilometrosRecorridosFin) && requerido)
												{
													alert("El kilometro inicio no puede ser mayor al kilometro fin");	
													requerido = false;
												}
											}else{
												tieneKilometrosRecorridos = 0;
											}
											
											if ($("#tieneCombustibleUtilizado").is(':checked')){
												tieneCombustibleUtilizado = 1;
											}else{
												tieneCombustibleUtilizado = 0;
											}
											combustibleUtilizadoValorId = $("#combustibleUtilizadoValorId").val();
											if ($("#tieneTipoUso").is(':checked')){
												tieneTipoUso = 1;
											}else{
												tieneTipoUso = 0;
											}
											tipoUsoListado = $("#tipoUsoListado").val();
											
											cargaPasajerosValor = $("#cargaPasajerosValor").val();
											if ($("#tieneCargaPasajeros").is(':checked')){
												tieneCargaPasajeros = 1;
												if(cargaPasajerosValor == "" && requerido)
												{
													alert("Ingrese carga o pasajeros con el formato adecuado");	
													requerido = false;	
												}
											}else{
												tieneCargaPasajeros = 0;
											}
											
// 											if ($("#tieneAdquisicion").is(':checked')){
// 												tieneAdquisicion = 1;
// 											}else{
// 												tieneAdquisicion = 0;
// 											}
// 											nombreAdquisicion = $("#nombreAdquisicion").val();
											
											
											valorFlotaIndividual = $("#valorFlotaIndividual").val();
											if ($("#esFlotaIndividual").is(':checked')){
												esFlotaIndividual = 1;
												if(valorFlotaIndividual == "" && requerido)
												{
													alert("Ingrese flota o individual con el formato adecuado");	
													requerido = false;	
												}
											}else{
												esFlotaIndividual = 0;
											}
											
// 											if ($("#tieneTipoObjetoVehiculo").is(':checked')){
// 												tieneTipoObjetoVehiculo = 1;
// 											}else{
// 												tieneTipoObjetoVehiculo = 0;
// 											}
// 											valorTipoObjetoVehiculo = $("#valorTipoObjetoVehiculo").val();	
											
											if ($("#tieneRenovacion").is(':checked')){
												tieneRenovacion = 1;
											}else{
												tieneRenovacion = 0;
											}
											
											if (codigo == ""){
												tipoConsulta = "crear";
											}else{
												tipoConsulta = "actualizar";
											}
											if(requerido)
												enviarDatos(
													nombre,
													codigo,																				
													grupoPorProducto,																																						
													porcentajeCasco,
													porcentajeExtras,
													tieneSumaAsegurada,
													sumaAseguradaInicio,
													sumaAseguradaFin,
													tieneAntiguedadVh,
													antiguedadInicio,
													antiguedadFin,
													tieneDispositivoRastreo,
													tieneTipoVehiculo,
													tipoVehiculoNombre,
													tieneTonelaje,
													valorTonelajeInicio,
													valorTonelajeFin,
													tieneRegion,
													valorRegion,
// 													tieneDeducible,
// 													deduciblePorcentajeSiniestro,
// 													deduciblePorcentajeValorAsegurado,
// 													deducibleMinimo,
// 													tieneDeduciblePerdidaTotalSiniestro,
// 													deduciblePerdidaTotalSiniestro,
// 													tieneAnoFabricacion,
// 													anoFabricacionInicio,
// 													anoFabricacionFin,
													tieneEdadConductor,
													edadConductorInicio,
													edadConductorFin,
													tieneMarca,
													marcaListado,
													tieneModelo,
													modeloListado,
// 													ceroKilometros,
// 													tieneConductorGenero,
// 													conductorGeneroValor,
// 													tieneNumeroHijos,
// 													numeroHijos,
													tieneZona,
													valorZona,
													tieneGuardaGarage,
													tieneKilometrosRecorridos,
													kilometrosRecorridosInicio,
													kilometrosRecorridosFin,
													tieneCombustibleUtilizado,
													combustibleUtilizadoValorId,
													tieneTipoUso,
													tipoUsoListado,
													tieneCargaPasajeros,
													cargaPasajerosValor,
// 													tieneAdquisicion,
// 													nombreAdquisicion,
													esFlotaIndividual,
													valorFlotaIndividual,
// 													tieneTipoObjetoVehiculo,
// 													valorTipoObjetoVehiculo,
													tieneRenovacion,
													tipoConsulta);
										});
						/* Fin Controles Grabar Registro*/

						$("#close-popup").click(function() {							
							location.reload();
						});

						function enviarDatos(nombre,
								codigo,																				
								grupoPorProducto,																																						
								porcentajeCasco,
								porcentajeExtras,
								tieneSumaAsegurada,
								sumaAseguradaInicio,
								sumaAseguradaFin,
								tieneAntiguedadVh,
								antiguedadInicio,
								antiguedadFin,
								tieneDispositivoRastreo,
								tieneTipoVehiculo,
								tipoVehiculoNombre,
								tieneTonelaje,
								valorTonelajeInicio,
								valorTonelajeFin,
								tieneRegion,
								valorRegion,
// 								tieneDeducible,
// 								deduciblePorcentajeSiniestro,
// 								deduciblePorcentajeValorAsegurado,
// 								deducibleMinimo,
// 								tieneDeduciblePerdidaTotalSiniestro,
// 								deduciblePerdidaTotalSiniestro,
// 								tieneAnoFabricacion,
// 								anoFabricacionInicio,
// 								anoFabricacionFin,
								tieneEdadConductor,
								edadConductorInicio,
								edadConductorFin,
								tieneMarca,
								marcaListado,
								tieneModelo,
								modeloListado,
// 								ceroKilometros,
// 								tieneConductorGenero,
// 								conductorGeneroValor,
// 								tieneNumeroHijos,
// 								numeroHijos,
								tieneZona,
								valorZona,
								tieneGuardaGarage,
								tieneKilometrosRecorridos,
								kilometrosRecorridosInicio,
								kilometrosRecorridosFin,
								tieneCombustibleUtilizado,
								combustibleUtilizadoValorId,
								tieneTipoUso,
								tipoUsoListado,
								tieneCargaPasajeros,
								cargaPasajerosValor,
// 								tieneAdquisicion,
// 								nombreAdquisicion,
								esFlotaIndividual,
								valorFlotaIndividual,
// 								tieneTipoObjetoVehiculo,
// 								valorTipoObjetoVehiculo,
								tieneRenovacion,
								tipoConsulta) {
								$.ajax({
										url : '../TasaProductoController',
										data : {
											"nombre" : nombre,
											"codigo" : codigo,											
											"grupoPorProducto" : grupoPorProducto,											
											"porcentajeCasco" : porcentajeCasco,
											"porcentajeExtras" : porcentajeExtras,
											"tieneSumaAsegurada" : tieneSumaAsegurada,
											"sumaAseguradaInicio" : sumaAseguradaInicio,
											"sumaAseguradaFin" : sumaAseguradaFin,
											"tieneAntiguedadVh" : tieneAntiguedadVh,
											"antiguedadInicio" : antiguedadInicio,
											"antiguedadFin" : antiguedadFin,
											"tieneDispositivoRastreo" : tieneDispositivoRastreo,
											"tieneTipoVehiculo" : tieneTipoVehiculo,
											"tipoVehiculoNombre" : tipoVehiculoNombre,
											"tieneTonelaje" : tieneTonelaje,
											"valorTonelajeInicio" : valorTonelajeInicio,
											"valorTonelajeFin" : valorTonelajeFin,
											"tieneRegion" : tieneRegion,
											"valorRegion" : valorRegion,
// 											"tieneDeducible" : tieneDeducible,
// 											"deduciblePorcentajeSiniestro" : deduciblePorcentajeSiniestro,
// 											"deduciblePorcentajeValorAsegurado" : deduciblePorcentajeValorAsegurado,
// 											"deducibleMinimo" : deducibleMinimo,
// 											"tieneDeduciblePerdidaTotalSiniestro" : tieneDeduciblePerdidaTotalSiniestro,
// 											"deduciblePerdidaTotalSiniestro" : deduciblePerdidaTotalSiniestro,
// 											"tieneAnoFabricacion" : tieneAnoFabricacion,
// 											"anoFabricacionInicio" : anoFabricacionInicio,
// 											"anoFabricacionFin" : anoFabricacionFin,
											"tieneEdadConductor" : tieneEdadConductor,
											"edadConductorInicio" : edadConductorInicio,
											"edadConductorFin" : edadConductorFin,
											"tieneMarca" : tieneMarca,
											"marcaListado" : marcaListado,
											"tieneModelo" : tieneModelo,
											"modeloListado" : modeloListado,
// 											"ceroKilometros" : ceroKilometros,
// 											"tieneConductorGenero" : tieneConductorGenero,
// 											"conductorGeneroValor" : conductorGeneroValor,
// 											"tieneNumeroHijos" : tieneNumeroHijos,
// 											"numeroHijos" : numeroHijos,
											"tieneZona" : tieneZona,
											"valorZona" : valorZona,
											"tieneGuardaGarage" : tieneGuardaGarage,
											"tieneKilometrosRecorridos" : tieneKilometrosRecorridos,
											"kilometrosRecorridosInicio" : kilometrosRecorridosInicio,
											"kilometrosRecorridosFin" : kilometrosRecorridosFin,
											"tieneCombustibleUtilizado" : tieneCombustibleUtilizado,
											"combustibleUtilizadoValorId" : combustibleUtilizadoValorId,
											"tieneTipoUso" : tieneTipoUso,
											"tipoUsoListado" : tipoUsoListado,
											"tieneCargaPasajeros" : tieneCargaPasajeros,
											"cargaPasajerosValor" : cargaPasajerosValor,
// 											"tieneAdquisicion" : tieneAdquisicion,
// 											"nombreAdquisicion" : nombreAdquisicion,
											"esFlotaIndividual" : esFlotaIndividual,
											"valorFlotaIndividual" : valorFlotaIndividual,
// 											"tieneTipoObjetoVehiculo" : tieneTipoObjetoVehiculo,
// 											"valorTipoObjetoVehiculo" : valorTipoObjetoVehiculo,		
											"tieneRenovacion" : tieneRenovacion,
											"tipoConsulta" : tipoConsulta
										},
										type : 'POST',
										datatype : 'json',
										success : function(data) {
											
											var validacionTipoVehiculo = data.validacionTipoVehiculo;
											var validacionregion = data.validacionregion;
											var validacionMarca = data.validacionMarca;
											var validacionModelo = data.validacionModelo;
											var validacionZona = data.validacionZona;
											var validacionCargaPasajeros = data.validacionCargaPasajeros;
											var validacionFlotaIndividual = data.validacionFlotaIndividual;
											if(validacionTipoVehiculo && validacionregion && validacionMarca && validacionModelo && validacionZona && validacionCargaPasajeros 
													&& validacionFlotaIndividual)
											{
												if (data.success){
													$("#msgPopup").show();
													//window.location = "TasaProducto.jsp";
												}else{
													alert(data.error);
												}
											}
											else if(!validacionTipoVehiculo)
											{
												alert("Los tipos de Vehiculos ingresados son incorrectos");	
											}
											else if(!validacionregion)
											{
												alert("Las regiones ingresadas son incorrectas");
											}
											else if(!validacionMarca)
											{
												alert("Las marcas ingresadas son incorrectas");	
											}
											else if(!validacionModelo)
											{
												alert("Los modelos ingresados son incorrectos");	
											}
											else if(!validacionZona)
											{
												alert("Las zonas ingresadas son incorrectas");
											}
											else if(!validacionCargaPasajeros)
											{
												alert("Los valores de carga pasajeros ingresados son incorrectos");
											}
											else if(!validacionFlotaIndividual)
											{
												alert("Los valores de flota individual ingresados son incorrectos");
											}
											
										}
									});
						}
		
					});

	// 		metodos para mostrar u ocultar opciones
	//SumaAsegurada
	$(function() {
		$('#tieneSumaAsegurada').change(function() {
			if ($("#tieneSumaAsegurada").is(
			':checked')) {
				$('#sumasAseguradas').show();
			} else {
				$('#sumasAseguradas').hide();
			}
		});
	});
	//Tasa Fija
		$(function() {
		$('#tasaFija').change(function() {
			if ($("#tasaFija").is(
			':checked')) {
				$('#tasasFijas').show();
			} else {
				$('#tasasFijas').hide();
			}
		});
	});
		//tieneAntiguedadVh
		$(function() {
		$('#tieneAntiguedadVh').change(function() {
			if ($("#tieneAntiguedadVh").is(
			':checked')) {
				$('#antiguedades').show();
			} else {
				$('#antiguedades').hide();
			}
		});
	});
		//tieneTipoVehiculo
		$(function() {
		$('#tieneTipoVehiculo').change(function() {
			if ($("#tieneTipoVehiculo").is(
			':checked')) {
				$('#vehiculos').show();
			} else {
				$('#vehiculos').hide();
			}
		});
	});
		//tieneTonelaje
		$(function() {
		$('#tieneTonelaje').change(function() {
			if ($("#tieneTonelaje").is(
			':checked')) {
				$('#tonelajes').show();
			} else {
				$('#tonelajes').hide();
			}
		});
	});
		
		//tieneRegion
		$(function() {
		$('#tieneRegion').change(function() {
			if ($("#tieneRegion").is(
			':checked')) {
				$('#regiones').show();
			} else {
				$('#regiones').hide();
			}
		});
	});
		//tieneDeducible
		$(function() {
		$('#tieneDeducible').change(function() {
			if ($("#tieneDeducible").is(
			':checked')) {
				$('#deducibles').show();
			} else {
				$('#deducibles').hide();
			}
		});
	});
		
		//tieneDeduciblePerdidaTotalSiniestro
			$(function() {
		$('#tieneDeduciblePerdidaTotalSiniestro').change(function() {
			if ($("#tieneDeduciblePerdidaTotalSiniestro").is(
			':checked')) {
				$('#perdidasTotal').show();
			} else {
				$('#perdidasTotal').hide();
			}
		});
	});
			//tieneAnoFabricacion
			$(function() {
		$('#tieneAnoFabricacion').change(function() {
			if ($("#tieneAnoFabricacion").is(
			':checked')) {
				$('#aniosFabricacion').show();
			} else {
				$('#aniosFabricacion').hide();
			}
		});
	});
			//tieneEdadConductor
			$(function() {
		$('#tieneEdadConductor').change(function() {
			if ($("#tieneEdadConductor").is(
			':checked')) {
				$('#edadesConductor').show();
			} else {
				$('#edadesConductor').hide();
			}
		});
	});
			//tieneMarca
			$(function() {
		$('#tieneMarca').change(function() {
			if ($("#tieneMarca").is(
			':checked')) {
				$('#marcas').show();
			} else {
				$('#marcas').hide();
			}
		});
	});
			//tieneModelo
			$(function() {
		$('#tieneModelo').change(function() {
			if ($("#tieneModelo").is(
			':checked')) {
				$('#modelos').show();
			} else {
				$('#modelos').hide();
			}
		});
	});
			//tieneConductorGenero
						$(function() {
		$('#tieneConductorGenero').change(function() {
			if ($("#tieneConductorGenero").is(
			':checked')) {
				$('#generos').show();
			} else {
				$('#generos').hide();
			}
		});
	});
						//tieneNumeroHijos
						$(function() {
		$('#tieneNumeroHijos').change(function() {
			if ($("#tieneNumeroHijos").is(
			':checked')) {
				$('#numerosHijos').show();
			} else {
				$('#numerosHijos').hide();
			}
		});
	});
						//tieneZona
						
$(function() {
		$('#tieneZona').change(function() {
			if ($("#tieneZona").is(
			':checked')) {
				$('#zonas').show();
			} else {
				$('#zonas').hide();
			}
		});
	});
						
//tieneKilometrosRecorridos
$(function() {
		$('#tieneKilometrosRecorridos').change(function() {
			if ($("#tieneKilometrosRecorridos").is(
			':checked')) {
				$('#kilometros').show();
			} else {
				$('#kilometros').hide();
			}
		});
	});
//tieneCombustibleUtilizado						
$(function() {
		$('#tieneCombustibleUtilizado').change(function() {
			if ($("#tieneCombustibleUtilizado").is(
			':checked')) {
				$('#combustibles').show();
			} else {
				$('#combustibles').hide();
			}
		});
	});
//tieneTipoUso						
$(function() {
		$('#tieneTipoUso').change(function() {
			if ($("#tieneTipoUso").is(
			':checked')) {
				$('#tiposUsos').show();
			} else {
				$('#tiposUsos').hide();
			}
		});
	});
//tieneCargaPasajeros
$(function() {
		$('#tieneCargaPasajeros').change(function() {
			if ($("#tieneCargaPasajeros").is(
			':checked')) {
				$('#cargasPasajeros').show();
			} else {
				$('#cargasPasajeros').hide();
			}
		});
	});
//tieneAdquisicion
$(function() {
		$('#tieneAdquisicion').change(function() {
			if ($("#tieneAdquisicion").is(
			':checked')) {
				$('#adquisiciones').show();
			} else {
				$('#adquisiciones').hide();
			}
		});
	});
	//esFlotaIndividual
$(function() {
		$('#esFlotaIndividual').change(function() {
			if ($("#esFlotaIndividual").is(
			':checked')) {
				$('#flotasIndividual').show();
			} else {
				$('#flotasIndividual').hide();
			}
		});
	});
//tieneTipoObjetoVehiculo
$(function() {
		$('#tieneTipoObjetoVehiculo').change(function() {
			if ($("#tieneTipoObjetoVehiculo").is(
			':checked')) {
				$('#tiposObjetoVehiculos').show();
			} else {
				$('#tiposObjetoVehiculos').hide();
			}
		});
	});
	
function validarNumero(e, field, tipo) {
	
	  key = e.keyCode ? e.keyCode : e.which
	  // backspace
	  if(tipo == 'decimal')
	  {
		  if (key == 8) return true
		  // 0-9
		  if (key > 47 && key < 58) {
		    if (field.value == "") return true
		    regexp1 = /^[0-9]+$/
		    if(regexp1.test(field.value))
		    	regexp = /.[0-9]{9}$/
		    else
		    	regexp = /.[0-9]{2}$/
		    
		    return !(regexp.test(field.value))
		  }
		  // .
		  if (key == 46) {
		    if (field.value == "") return false
		    regexp = /^[0-9]+$/
		    return regexp.test(field.value)
		  }
	  }
	  if(tipo == 'numerico')
	  {
		  if (key == 8) return true
		  // 0-9
		  if (key > 47 && key < 58) {
		    if (field.value == "") return true
		    regexp = /[0-9]{9}$/
		    
		    
		    return !(regexp.test(field.value))
		  }	  
	  }
	  
	  // other key
	  return false
	 
	}
	
</script>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
		else {
			com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
					.getAttribute("usuario");

			// Obtenemos el rol asociado al usuario
			com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
			com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
					.buscarPorUsuario(usuario);
			com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
			com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
			if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
					"Tasa Por Producto")) {
				response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
			}
		}
	%>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add"
			id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style = "width: 90%;">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">							
							<h4 class="modal-title" id="myModalLabel">Tasa por Producto</h4>
						</div>
						<div class="modal-body" style="overflow-y : auto;">
							<div class="alert alert-success" id="msgPopup">Grabado con
								exito.</div>
							<div class="form-group">
								<div class="col-md-4">
									<input type="hidden" class="form-control" id="codigo">
									<label>Grupo por Producto</label> <select type="select"
										class="form-control required" id="grupoPorProducto">
										<option value='0'>Seleccione una opcion</option>
									</select><label>Nombre</label> <input type="text" class="form-control required"
										id="nombre"> <label>Porcentaje Casco</label> <input
										type="text" class="form-control required" id="porcentajeCasco" onkeypress="return validarNumero(event, this, 'decimal')"/>
									<label>Porcentaje Extras</label> <input type="text"
										class="form-control required" id="porcentajeExtras" onkeypress="return validarNumero(event, this, 'decimal')"/>
									<div class="checkbox">
										<label> <input type="checkbox"
											value="tieneSumaAsegurada" id="tieneSumaAsegurada">Tiene
											Suma Asegurada
										</label>
									</div>
									<div class="well" id="sumasAseguradas">
										<label>Suma Asegurada Inicio</label> <input type="text"
											class="form-control" id="sumaAseguradaInicio"
											onkeypress="return validarNumero(event, this, 'decimal')" />
										<label>Suma Asegurada Fin</label> <input type="text"
											class="form-control" id="sumaAseguradaFin"
											onkeypress="return validarNumero(event, this, 'decimal')" />
									</div>
									<div class="checkbox">
										<label> <input type="checkbox"
											value="tieneAntiguedadVh" id="tieneAntiguedadVh">Tiene
											Antiguedad Vh
										</label>
									</div>
									<div class="well" id="antiguedades">
										<label>Antiguedad Inicio</label> <input type="text"
											class="form-control" id="antiguedadInicio"
											onkeypress="return validarNumero(event, this, 'numerico')" />
										<label>Antiguedad Fin</label> <input type="text"
											class="form-control" id="antiguedadFin"
											onkeypress="return validarNumero(event, this, 'numerico')" />
									</div>
									<div class="checkbox">
										<label> <input type="checkbox"
											value="tieneDispositivoRastreo" id="tieneDispositivoRastreo">Tiene
											Dispositivo Rastreo
										</label>
									</div>
									<div class="checkbox">
										<label> <input type="checkbox"
											value="tieneTipoVehiculo" id="tieneTipoVehiculo">Tiene
											Tipo Vehiculo
										</label>
									</div>
									<div class="well" id="vehiculos">
										<label>Tipo Vehiculo Nombre</label> <input type="text"
											class="form-control" id="tipoVehiculoNombre">
									</div>
									
									<div class="checkbox">
										<label> <input type="checkbox" value="tieneRegion"
											id="tieneRegion">Tiene Region
										</label>
									</div>
									<div class="well" id="regiones">
										<label>Valor Region</label> <input type="text"
											class="form-control" id="valorRegion">
									</div>
									<!-- 									<div class="checkbox"> -->
									<!-- 										<label> <input type="checkbox" value="tieneDeducible" -->
									<!-- 											id="tieneDeducible">Tiene Deducible -->
									<!-- 										</label> -->
									<!-- 									</div> -->
									<!-- 									<div class="well" id = "deducibles"> -->
									<!-- 									<label>Deducible Porcentaje Siniestro</label> <input -->
									<!-- 										type="text" class="form-control" -->
									<!-- 										id="deduciblePorcentajeSiniestro"> <label>Deducible -->
									<!-- 										Porcentaje Valor Asegurado</label> <input type="text" -->
									<!-- 										class="form-control" -->
									<!-- 										id="deduciblePorcentajeValorAsegurado"> <label>Deducible -->
									<!-- 										Minimo</label> <input type="text" class="form-control" -->
									<!-- 										id="deducibleMinimo"> -->
									<!-- 									</div> -->
<!-- 									<div class="checkbox"> -->
<!-- 										<label> <input type="checkbox" -->
<!-- 											value="tieneDeduciblePerdidaTotalSiniestro" -->
<!-- 											id="tieneDeduciblePerdidaTotalSiniestro">Tiene -->
<!-- 											Deducible Perdida Total Siniestro -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id="perdidasTotal"> -->
<!-- 										<label>Deducible Perdida Total Siniestro</label> <input -->
<!-- 											type="text" class="form-control" -->
<!-- 											id="deduciblePerdidaTotalSiniestro"> -->
<!-- 									</div> -->
								</div>
								<div class="col-md-4">
								<div class="checkbox">
										<label> <input type="checkbox" value="tieneTonelaje"
											id="tieneTonelaje">Tiene Tonelaje
										</label>
									</div>
									<div class="well" id="tonelajes">
										<label>Valor Tonelaje Inicio</label> <input type="text"
											class="form-control" id="valorTonelajeInicio"
											onkeypress="return validarNumero(event, this, 'decimal')" />
										<label>Valor Tonelaje Fin</label> <input type="text"
											class="form-control" id="valorTonelajeFin"
											onkeypress="return validarNumero(event, this, 'decimal')" />
									</div>
<!-- 									<div class="checkbox"> -->
<!-- 										<label> <input type="checkbox" -->
<!-- 											value="tieneAnoFabricacion" id="tieneAnoFabricacion">Tiene -->
<!-- 											Ano Fabricacion -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id="aniosFabricacion"> -->
<!-- 										<label>Ano Fabricacion Inicio</label> <input type="text" -->
<!-- 											class="form-control" id="anoFabricacionInicio"> <label>Ano -->
<!-- 											Fabricacion Fin</label> <input type="text" class="form-control" -->
<!-- 											id="anoFabricacionFin"> -->
<!-- 									</div> -->
									<div class="checkbox">
										<label> <input type="checkbox"
											value="tieneEdadConductor" id="tieneEdadConductor">Tiene
											Edad Conductor
										</label>
									</div>
									<div class="well" id="edadesConductor">
										<label>Edad Conductor Inicio</label> <input type="text"
											class="form-control" id="edadConductorInicio" onkeypress="return validarNumero(event, this, 'numerico')"/> <label>Edad
											Conductor Fin</label> <input type="text" class="form-control"
											id="edadConductorFin" onkeypress="return validarNumero(event, this, 'numerico')"/>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="tieneMarca"
											id="tieneMarca">Tiene Marca</label>
									</div>
									<div class="well" id = "marcas">
									<label>Marca</label> <input type="text"
										class="form-control" id="marcaListado">
									</div>

									<div class="checkbox">
										<label><input type="checkbox" value="tieneModelo"
											id="tieneModelo">Tiene Modelo</label>
									</div>
									<div class="well" id = "modelos">
									<label>Modelo</label> <input type="text"
										class="form-control" id="modeloListado">
									</div>

<!-- 									<div class="checkbox"> -->
<!-- 										<label><input type="checkbox" value="ceroKilometros" -->
<!-- 											id="ceroKilometros">Cero Kilometros</label> -->
<!-- 									</div> -->

<!-- 									<div class="checkbox"> -->
<!-- 										<label><input type="checkbox" -->
<!-- 											value="tieneConductorGenero" id="tieneConductorGenero">Tiene -->
<!-- 											Conductor Genero</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id = "generos"> -->
<!-- 									<label>Conductor Genero</label> <select type="select" -->
<!-- 										class="form-control" id="conductorGeneroValor"> -->
<!-- 										<option value = '0'>Seleccione una Opcion</option>										 -->
<!-- 										<option value = 'M'>M</option> -->
<!-- 										<option value = 'F'>F</option> -->
<!-- 									</select> -->
<!-- 									</div> -->
<!-- 									<div class="checkbox"> -->
<!-- 										<label><input type="checkbox" value="tieneNumeroHijos" -->
<!-- 											id="tieneNumeroHijos">Tiene Numero Hijos</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id = "numerosHijos"> -->
<!-- 									<label>Numero Hijos</label> <input type="text" -->
<!-- 										class="form-control" id="numeroHijos"> -->
<!-- 									</div> -->
									<div class="checkbox">
										<label><input type="checkbox" value="tieneZona"
											id="tieneZona">Tiene Zona</label>
									</div>
									<div class="well" id = "zonas">
									<label>Zona</label> <input type="text"
										class="form-control" id="valorZona">
									</div>
									<div class="checkbox">
										<label><input type="checkbox"
											value="tieneGuardaGarage" id="tieneGuardaGarage">Tiene
											Guarda Garage</label>
									</div>
									
									</div>
									<div class="col-md-4">
									<div class="checkbox">
										<label><input type="checkbox"
											value="tieneKilometrosRecorridos"
											id="tieneKilometrosRecorridos">Tiene Kilometros
											Recorridos</label>
									</div>
									<div class="well" id = "kilometros">
									<label>Kilometros Recorridos Inicio</label> <input type="text"
										class="form-control" id="kilometrosRecorridosInicio" onkeypress="return validarNumero(event, this, 'numerico')"/>
									<label>Kilometros Recorridos Fin</label> <input type="text"
										class="form-control" id="kilometrosRecorridosFin" onkeypress="return validarNumero(event, this, 'numerico')"/>
									</div>
									<div class="checkbox">
										<label><input type="checkbox"
											value="tieneCombustibleUtilizado"
											id="tieneCombustibleUtilizado">Tiene Combustible
											Utilizado</label>
									</div>
									<div class="well" id = "combustibles">
									<label>Combustible Utilizado</label> <select type="select"
										class="form-control" id="combustibleUtilizadoValorId">
										<option value='0'>Seleccione una opcion</option>
<!-- 										<option value="G">&nbsp;&nbsp;Gasolina </option> -->
<!-- 										<option value="D">&nbsp;&nbsp;Diesel </option> -->
<!-- 										<option value="E">&nbsp;&nbsp;Gasolina/Electricidad</option>										 -->
									</select>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="tieneTipoUso"
											id="tieneTipoUso">Tiene Tipo Uso</label>
									</div>
									<div class="well" id = "tiposUsos">
									<label>Tipo Uso</label> <select type="select"
										class="form-control" id="tipoUsoListado">
										<option value='0'>Seleccione una opcion</option>
									</select>
									</div>
									<div class="checkbox">
										<label><input type="checkbox"
											value="tieneCargaPasajeros" id="tieneCargaPasajeros">Tiene
											Carga Pasajeros</label>
									</div>
									<div class="well" id = "cargasPasajeros">
									<label>Carga Pasajeros</label> <input type="text"
										class="form-control" id="cargaPasajerosValor">
									</div>

<!-- 									<div class="checkbox"> -->
<!-- 										<label><input type="checkbox" value="tieneAdquisicion" -->
<!-- 											id="tieneAdquisicion">Tiene Adquisicion</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id = "adquisiciones"> -->
<!-- 									<label>Adquisicion</label> <input type="text" -->
<!-- 										class="form-control" id="nombreAdquisicion"> -->
<!-- 									</div> -->
									<div class="checkbox">
										<label><input type="checkbox"
											value="esFlotaIndividual" id="esFlotaIndividual">Es
											Flota Individual</label>
									</div>
									<div class="well" id = "flotasIndividual">
									<label>Valor Flota Individual</label> <input type="text"
										class="form-control" id="valorFlotaIndividual">
									</div>
<!-- 									<div class="checkbox"> -->
<!-- 										<label><input type="checkbox" -->
<!-- 											value="tieneTipoObjetoVehiculo" id="tieneTipoObjetoVehiculo">Tiene -->
<!-- 											Tipo Objeto Vehiculo</label> -->
<!-- 									</div> -->
<!-- 									<div class="well" id = "tiposObjetoVehiculos"> -->
<!-- 									<label>Tipo Objeto Vehiculo</label> <input type="text" -->
<!-- 										class="form-control" id="valorTipoObjetoVehiculo"> -->
<!-- 									</div>	 -->
									<div class="checkbox">
										<label><input type="checkbox" value="tieneRenovacion"
											id="tieneRenovacion">Tiene Renovacion</label>
									</div>									
								</div>
							</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" id="close-popup"
									data-dismiss="modal">Cerrar</button>
								<button type="button" class="btn btn-primary" id="save-record">Guardar</button>
						</div>	
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->


	<!-- Datatable -->	
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<br>
					<div class="input-group">
						<span class="input-group-addon">Filtro</span> <input id="filter"
							type="text" class="form-control"
							placeholder="Escriba la palabra a buscar...">
					</div>
					<br>
					<table class="table table-striped table-bordered table-hover"
						id="dataTable">
						<thead>
							<tr>																							
								<th>Grupo Por Producto</th>
								<th>Nombre</th>
								<th>Porcentaje Casco</th>
								<th>Porcentaje Extras</th>
								<th style="display: none">Tiene Suma Asegurada</th>
								<th style="display: none">Suma Asegurada Inicio</th>
								<th style="display: none">Suma Asegurada Fin</th>
								<th style="display: none">Tiene Antiguedad Vh</th>
								<th style="display: none">Antiguedad Inicio</th>
								<th style="display: none">Antiguedad Fin</th>
								<th style="display: none">Tiene Dispositivo Rastreo</th>
								<th style="display: none">Tiene Tipo Vehiculo</th>
								<th style="display: none">Tipo Vehiculo Nombre</th>
								<th style="display: none">Tiene Tonelaje</th>
								<th style="display: none">Valor Tonelaje Inicio</th>
								<th style="display: none">Valor Tonelaje Fin</th>
								<th style="display: none">Tiene Region</th>
								<th style="display: none">Valor Region</th>
								<th style="display: none">Tiene Deducible</th>
								<th style="display: none">Deducible Porcentaje Siniestro</th>
								<th style="display: none">Deducible Porcentaje Valor Asegurado</th>
								<th style="display: none">Deducible Minimo</th>
								<th style="display: none">Tiene Deducible Perdida Total Siniestro</th>
								<th style="display: none">Deducible Perdida Total Siniestro</th>
								<th style="display: none">Tiene Ano Fabricacion</th>
								<th style="display: none">Ano Fabricacion Inicio</th>
								<th style="display: none">Ano Fabricacion Fin</th>
								<th style="display: none">Tiene Edad Conductor</th>
								<th style="display: none">Edad Conductor Inicio</th>
								<th style="display: none">Edad Conductor Fin</th>
								<th style="display: none">Tiene Marca</th>
								<th style="display: none">Marca</th>
								<th style="display: none">Tiene Modelo</th>
								<th style="display: none">Modelo</th>
								<th style="display: none">Cero Kilometros</th>
								<th style="display: none">Tiene Conductor Genero</th>
								<th style="display: none">Conductor Genero</th>
								<th style="display: none">Tiene Numero Hijos</th>
								<th style="display: none">Numero Hijos</th>
								<th style="display: none">Tiene Zona</th>
								<th style="display: none">Zona</th>
								<th style="display: none">Tiene Guarda Garage</th>
								<th style="display: none">Tiene Kilometros Recorridos</th>
								<th style="display: none">Kilometros Recorridos Inicio</th>
								<th style="display: none">Kilometros Recorridos Fin</th>
								<th style="display: none">Tiene Combustible Utilizado</th>
								<th style="display: none">Combustible Utilizado</th>
								<th style="display: none">Tiene Tipo Uso</th>
								<th style="display: none">Tipo Uso</th>
								<th style="display: none">Tiene Carga Pasajeros</th>
								<th style="display: none">Carga Pasajeros</th>
								<th style="display: none">Tiene Adquisicion</th>
								<th style="display: none">Adquisicion</th>
								<th style="display: none">Es Flota Individual</th>
								<th style="display: none">Valor Flota Individual</th>
								<th style="display: none">Tiene Tipo Objeto Vehiculo</th>
								<th style="display: none">Tipo Objeto Vehiculo</th>
								<th style="display: none">Tiene Renovacion</th>
								<th>Acci&oacute;n</th>								
							</tr>
						</thead>
						<tbody id="dataTableContent" class="searchable">

							<div id="loading">
								<div class="loading-indicator">
									<img src="../static/images/ajax-loader.gif" /><br />
									<br /> <span id="loading-msg">Cargando...</span>
								</div>
							</div>

						</tbody>
					</table>
				</div>
			</div>
		</div>	
	<!-- Datatable -->
</body>
</html>
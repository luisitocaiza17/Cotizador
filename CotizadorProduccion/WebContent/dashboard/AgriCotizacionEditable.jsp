<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Cotizaciones Agr&iacute;cola Log - CotizadorQBE</title>
<!-- <script src="../static/js/jquery.min.js"></script> -->
<script src="../static/js/cotizador/comun.js"></script>
<link href="../static/css/loading.css" rel="stylesheet">


<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>

<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
<script src="../static/js/Kendo/jszip.min.js"></script>

<!-- Table Tools -->
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css"
	rel="stylesheet">

<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet">
<script src="../static/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="../static/css/select2/select2.css">


<script>
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
		
		/**variables globales**/
		var idCotizacion;
		var PuntosVentaList;
		var provinciaList;
		var cantonList;
		var parroquiaList;
		var cultivoList;
		var fechaSiembra;
		var hectareasAsegurables;
		var cultivoId;
		var cantonId;
		var provinciaId;
		var puntoVentaId;
		var canalList
		
		//valores generales de la cotizacion
		var gEmision;
		var gSuperBancos;
		var gSeguroCampesino;
		var giva;
		
				
		$(document).ready(function() {
				activarMenu(this);
				idCotizacion=QueryString.id;
				$("#idCotizacion").text(idCotizacion);
				
				$(".text_Currency").kendoNumericTextBox({
			        format: "c",
			        min: 0,
			        decimals: 2
			    });
				
				$(".text_Numeric").kendoNumericTextBox({
			         min: 0,
			        decimals: 2
			    });
				
				$("#sucursal_canal").kendoMultiSelect({
					dataTextField : "CanalNombre",
					dataValueField : "CanalId",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				canalList = $("#sucursal_canal").data(
				"kendoMultiSelect");				
				
				$("#punto_venta_session").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "puntoVentaId",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				PuntosVentaList = $("#punto_venta_session").data(
				"kendoMultiSelect");
				
				$("#provincia").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "codigo",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				provinciaList = $("#provincia").data(
				"kendoMultiSelect");
				
				$("#canton").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "id",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				cantonList = $("#canton").data(
				"kendoMultiSelect");
				
				$("#parroquia").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "id",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				parroquiaList = $("#parroquia").data(
				"kendoMultiSelect");
				
				$("#cultivo").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "id",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				cultivoList = $("#cultivo").data(
				"kendoMultiSelect");
				
				ConsultaValoresGenerales();
				obtenerCanal();
				ConsultarProvincia();
				ConsultarCantones();
				ConsultarParroquias();
				ConsultarCultivos();
				ConsultarPuntosVenta();
				
				
				consultaCotizacion();
				
				$( "#seguroCampesino" ).data("kendoNumericTextBox").enable(false);
				$( "#superBancos" ).data("kendoNumericTextBox").enable(false);
				$( "#derechoEmision" ).data("kendoNumericTextBox").enable(false);
				$( "#impuestos" ).data("kendoNumericTextBox").enable(false);
				
				
				//Actualizar la cotizacion al hacer clic en el boton guardar
				$('#guardar').click(function(){
					GuardarCotizacion();
		    	 });
				
				///TODO: al seleccionar el combo canal
				$("#sucursal_canal").change(function(){	
					$("#punto_venta_session").children().remove();
					ConsultarPuntosVentaId();
				});
				
				//recalculo de valores de cotizacion punto venta
				$("#punto_venta_session").change(function(){	
					recalculoTotal();
				});
				
				//cambio de provincia
				$("#provincia").change(function(){	
					cantonProvincia();
				});
				
				//recalculo en el cambio de canton
				$("#canton").change(function(){	
					recalculoTotal();
					ConsultarParroquiasCanton();
				});
				
				//recalculo en el cambio de canton
				$("#cultivo").change(function(){	
					recalculoTotal();
				});
				
				//recalculo en caso de cambio de la tasa
				$("#primaNeta").change(function(){	
					calculoValoresPrima();
				});
				
				
				//recalculo en el cambio de costoProduccion
								
				$("#costoProduccion").change(function(){	
					calculoValores();
				});
				
				//caso cambio de hectareas
				$("#hectareasAsegurables").change(function(){
					var hA=$("#hectareasAsegurables").val();
					var hT=$("#hectareasTotales").val();
					if(hA>hT){
						alert("El numero de hectareas asegurables no pueden ser mayor al numero de hectareas totales");
						return false;
					}
					else
						calculoValores();
				});
				
				//recalculo tasa
				$("#tasa").change(function(){	
					calculoValores();
				});
				
				//recalculo suma segurada
				$("#sumaAsegurada").change(function(){	
					calculoValoresSumaAsegurada();
				});
				
				//recalculo iva
				$("#iva").change(function(){	
					calculoValoresIva();
				});
				
				$('#seguroCampesino').prop('disabled', true);
				$('#superBancos').prop('disabled', true);
				$('#derechoEmision').prop('disabled', true);
				
		});
		
		$(function() {
			$('#dp1').datepicker();
		});
		
		function GuardarCotizacion(){
			var puntoVenta=$("#punto_venta_session option:selected").val();
			var idEmision=$("#idEmision").val();
			var Numtramite=$("#Numtramite").val();
			var identificacion=$("#identificacion").val();
			var provincia=$("#provincia option:selected").val();
			var canton=$("#canton option:selected").val();
			var parroquia=$("#parroquia option:selected").val();
			var cultivo=$("#cultivo option:selected").val();
			var hectareasAsegurables=$("#hectareasAsegurables").val();
			var hectareasTotales=$("#hectareasTotales").val();
			var lugarSiembra=$("#lugarSiembra").val();
			var fechaSiembra=$("#dp1").val();
			var costoProduccion=$("#costoProduccion").val();
			var tasa=$("#tasa").val();
			var sumaAsegurada=$("#sumaAsegurada").val();
			var primaNeta=$("#primaNeta").val();
			var seguroCampesino=$("#seguroCampesino").val();
			var superBancos=$("#superBancos").val();
			var derechoEmision=$("#derechoEmision").val();
			var iva=$("#iva").val();
			var impuestos=$("#impuestos").val();
			var primaTotal=$("#primaTotal").val();
			
			//validaciones para evitar valores 0
			if(hectareasAsegurables==0){
				alert("El numero de hectareas asegurables no puede ser 0");
				return false;
			}
			if(hectareasTotales==0){
				alert("El numero de hectareas Totales no puede ser 0");
				return false;
			}
			
			if(costoProduccion==0){
				alert("El costo de produccion no puede ser 0");
				return false;
			}
			if(tasa==0){
				alert("La tasa no puede ser 0");
				return false;
			}
			if(sumaAsegurada==0){
				alert("La suma asegurada no puede ser 0");
				return false;
			}
			if(primaNeta==0){
				alert("La prima neta no puede ser 0");
				return false;
			}
			if(iva==0){
				alert("El iva no puede ser 0");
				return false;
			}
			if(primaTotal==0){
				alert("La prima asegurables no puede ser 0");
				return false;
			}
			
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"EditarCotizacion",
					"idCotizacion":idCotizacion,
					"puntoVenta":puntoVenta,
					"idEmision":idEmision,
					"Numtramite":Numtramite,
					"identificacion":identificacion,
					"provincia":provincia,
					"canton":canton,
					"parroquia":parroquia,
					"cultivo":cultivo,
					"hectareasAsegurables":hectareasAsegurables,
					"hectareasTotales":hectareasTotales,
					"lugarSiembra":lugarSiembra,
					"fechaSiembra":fechaSiembra ,
					"costoProduccion" :costoProduccion ,
					"tasa":tasa,
					"sumaAsegurada":sumaAsegurada,
					"primaNeta":primaNeta,
					"seguroCampesino":seguroCampesino,
					"superBancos":superBancos,
					"derechoEmision":derechoEmision,
					"iva":iva,
					"impuestos":impuestos,
					"primaTotal":primaTotal
				},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					if(data.success==true){
						alert("Proceso Correcto la cotizacion fue actualizada con exito");
					}else{
						alert("Se produjo un error: "+data.error);
					}	
				}
			});
		}
		
		function consultaCotizacion(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"cargarCotizacion",
					"idCotizacion":idCotizacion},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					if(data.success===true){
						//cargo los valores de la cotizacion
						$("#sucursal_canal").data("kendoMultiSelect").value(data.sponsorId);
						$("#punto_venta_session").data("kendoMultiSelect").value(data.canalId);
						$("#idEmision").val(data.idEmision);
						$("#Numtramite").val(data.Numtramite);
						$("#identificacion").val(data.identificacion);
						$("#clienteId").val(data.clienteId);
						$("#nombre").val(data.nombre);
						$("#provincia").data("kendoMultiSelect").value(data.provinciaId);
						$("#canton").data("kendoMultiSelect").value(data.cantonId);
						$("#parroquia").data("kendoMultiSelect").value(data.parroquiaId);
						$("#cultivo").data("kendoMultiSelect").value(data.cultivoId);
						$("#hectareasAsegurables").data("kendoNumericTextBox").value(data.hecAseguradas);
						$("#hectareasTotales").data("kendoNumericTextBox").value(data.hecTotal);
						$("#lugarSiembra").val(data.lugarSiembra);
						$('#dp1').val(data.fechaSiembra);
						$("#costoProduccion").data("kendoNumericTextBox").value(data.costoProduccion);
						$("#sumaAsegurada").data("kendoNumericTextBox").value(data.sumaAsegurada);
						$("#primaNeta").data("kendoNumericTextBox").value(data.primaNeta);
						$("#seguroCampesino").data("kendoNumericTextBox").value(data.seguroCampesino);
						$("#superBancos").data("kendoNumericTextBox").value(data.superBancos);
						$("#derechoEmision").data("kendoNumericTextBox").value(data.derechoEmision);
						$("#iva").data("kendoNumericTextBox").value(data.iva);
						$("#impuestos").data("kendoNumericTextBox").value(data.impuestos);
						$("#primaTotal").data("kendoNumericTextBox").value(data.primaTotal);
						$("#tasa").data("kendoNumericTextBox").value(data.tasa);
						giva=data.porcentajeIva;
						gEmision=data.porcentajeDerecho;
					}else{
						alert("No se a podido cargar la cotizacion debido al siguiente error: "+data.error);
					}
					
				}
			});
		}
		
		function recalculoTotal(){
			puntoVentaId = $("#punto_venta_session option:selected").val() ? $(
			"#punto_venta_session option:selected").val() : "";
			provinciaId = $("#provincia option:selected").val() ? $(
			"#provincia option:selected").val() : "";
			cantonId = $("#canton option:selected").val() ? $(
			"#canton option:selected").val() : "";
			cultivoId = $("#cultivo option:selected").val() ? $(
			"#cultivo option:selected").val() : "";
			hectareasAsegurables=$("#hectareasAsegurables").data("kendoNumericTextBox").value();
			fechaSiembra=$("#dp1").val();
			
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"recalculoTotal",
					"puntoVentaId": puntoVentaId,
					"provinciaId": provinciaId,
					"cantonId": cantonId,
					"cultivoId": cultivoId,
					"hectareasAsegurables": hectareasAsegurables,
					"fechaSiembra":fechaSiembra
				},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					$("#costoProduccion").data("kendoNumericTextBox").value(data.costoProduccion);
					$("#sumaAsegurada").data("kendoNumericTextBox").value(data.sumaAsegurada);
					$("#primaNeta").data("kendoNumericTextBox").value(data.primaNeta);
					$("#seguroCampesino").data("kendoNumericTextBox").value(data.impSeguroCampesino);
					$("#superBancos").data("kendoNumericTextBox").value(data.impSuperBancos);
					$("#derechoEmision").data("kendoNumericTextBox").value(data.derechoEmision);
					$("#iva").data("kendoNumericTextBox").value(data.impIva);
					$("#impuestos").data("kendoNumericTextBox").value(data.impTotal);
					$("#primaTotal").data("kendoNumericTextBox").value(data.primaTotal);
					$("#tasa").data("kendoNumericTextBox").value(data.tasa);
					giva=data.PorcentajeIva;
					gEmision=data.gderechoEmision;
					if(data.success==false)
						alert(data.error);
				}
			});
			
		}
		
		function calculoValoresPrima(){
			
			var sumaNueva=Number($("#sumaAsegurada").val());
			var primaNetaNueva=Number($("#primaNeta").val());
			var tasaNueva=(primaNetaNueva/sumaNueva)*100;
			
			
			var impDerechoNuevo=gEmision;
			var impSuperBancosNuevo= (primaNetaNueva*gSuperBancos/100*100)/100;
			var impSeguroCampesinoNuevo= (primaNetaNueva*gSeguroCampesino/100*100)/100  ;
			var impIvaNueva=(primaNetaNueva+impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo)*(giva/100);
			var impTotalNueva=impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo+impIvaNueva;
			var primaTotal=impTotalNueva+primaNetaNueva;
			
			$("#seguroCampesino").data("kendoNumericTextBox").value(impSeguroCampesinoNuevo);
			$("#superBancos").data("kendoNumericTextBox").value(impSuperBancosNuevo);
			$("#derechoEmision").data("kendoNumericTextBox").value(impDerechoNuevo);
			$("#iva").data("kendoNumericTextBox").value(impIvaNueva);
			$("#impuestos").data("kendoNumericTextBox").value(impTotalNueva);
			$("#primaTotal").data("kendoNumericTextBox").value(primaTotal);
			$("#tasa").data("kendoNumericTextBox").value(tasaNueva);
		}
		
		function calculoValores(){
			var tasaNueva=Number($("#tasa").val());
			var hectareasNueva=Number($("#hectareasAsegurables").val());
			var costoProduccionNueva=Number($("#costoProduccion").val());
			
			var sumaNueva=hectareasNueva*costoProduccionNueva;
			var primaNetaNueva=sumaNueva*(tasaNueva/100);
			var impDerechoNuevo=gEmision;
			var impSuperBancosNuevo= (primaNetaNueva*gSuperBancos/100*100)/100;
			var impSeguroCampesinoNuevo= (primaNetaNueva*gSeguroCampesino/100*100)/100  ;
			var impIvaNueva=(primaNetaNueva+impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo)*(giva/100);
			var impTotalNueva=impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo+impIvaNueva;
			var primaTotal=impTotalNueva+primaNetaNueva;
			
			$("#costoProduccion").data("kendoNumericTextBox").value(costoProduccionNueva);
			$("#sumaAsegurada").data("kendoNumericTextBox").value(sumaNueva);
			$("#primaNeta").data("kendoNumericTextBox").value(primaNetaNueva);
			$("#seguroCampesino").data("kendoNumericTextBox").value(impSeguroCampesinoNuevo);
			$("#superBancos").data("kendoNumericTextBox").value(impSuperBancosNuevo);
			$("#derechoEmision").data("kendoNumericTextBox").value(impDerechoNuevo);
			$("#iva").data("kendoNumericTextBox").value(impIvaNueva);
			$("#impuestos").data("kendoNumericTextBox").value(impTotalNueva);
			$("#primaTotal").data("kendoNumericTextBox").value(primaTotal);
			$("#tasa").data("kendoNumericTextBox").value(tasaNueva);
		}
		
		function calculoValoresSumaAsegurada(){
			var tasaNueva=Number($("#tasa").val());
			var sumaNueva=Number($("#sumaAsegurada").val());
			
			var hectareasNueva=Number($("#hectareasAsegurables").val());
			
			var costoProduccionNueva=sumaNueva/hectareasNueva;
			
			var primaNetaNueva=sumaNueva*(tasaNueva/100);
			var impDerechoNuevo=gEmision;
			var impSuperBancosNuevo=(primaNetaNueva*gSuperBancos/100*100)/100;
			var impSeguroCampesinoNuevo=(primaNetaNueva*gSeguroCampesino/100*100)/100  ;
			var impIvaNueva=(primaNetaNueva+impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo)*(giva/100);
			var impTotalNueva=impDerechoNuevo+impSuperBancosNuevo+impSeguroCampesinoNuevo+impIvaNueva;
			var primaTotal=impTotalNueva+primaNetaNueva;
			
			$("#costoProduccion").data("kendoNumericTextBox").value(costoProduccionNueva);
			$("#sumaAsegurada").data("kendoNumericTextBox").value(sumaNueva);
			$("#primaNeta").data("kendoNumericTextBox").value(primaNetaNueva);
			$("#seguroCampesino").data("kendoNumericTextBox").value(impSeguroCampesinoNuevo);
			$("#superBancos").data("kendoNumericTextBox").value(impSuperBancosNuevo);
			$("#derechoEmision").data("kendoNumericTextBox").value(impDerechoNuevo);
			$("#iva").data("kendoNumericTextBox").value(impIvaNueva);
			$("#impuestos").data("kendoNumericTextBox").value(impTotalNueva);
			$("#primaTotal").data("kendoNumericTextBox").value(primaTotal);
			$("#tasa").data("kendoNumericTextBox").value(tasaNueva);
		}
		
		function calculoValoresIva(){
			var impIvaNueva=Number($("#iva").val());
			var impseguroCampesino=Number($("#seguroCampesino").val());
			var impsuperBancos=Number($("#superBancos").val());
			var impderechoEmision =Number($("#derechoEmision").val());
			var primaNetaR=Number($("#primaNeta").val());
			
			var impTotalNueva=0.0;
			impTotalNueva=impseguroCampesino+impsuperBancos+impderechoEmision+impIvaNueva;
			var primaTotal=impTotalNueva+primaNetaR;
			
			$("#impuestos").data("kendoNumericTextBox").value(impTotalNueva);
			$("#primaTotal").data("kendoNumericTextBox").value(primaTotal);
			
		}
		
		function ConsultaValoresGenerales(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"valoresGenerales"},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
						gEmision=data.derechoEmision;	
						gSuperBancos=data.superBancos;
						gSeguroCampesino=data.seguroCampesino;
						giva=data.iva;
				}
			});
		}
		
		function ConsultarProvincia(){
			$.ajax({
				url:'../ProvinciaController',
				data:{"tipoConsulta":"encontrarTodos"},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					var listadoProvincia = data.listadoProvincia;
					$("#Provincia").append("<option value =''>Seleccione una opción</option>");
					$.each(listadoProvincia, function(index){
						$("#Provincia").append("<option value='"+ listadoProvincia[index].codigo+"'>"+listadoProvincia[index].nombre+"</option>");
					});
					provinciaList.dataSource.filter({});
					provinciaList.setDataSource(data.listadoProvincia);
					
				}
			});
		}
						
		function ConsultarCantones(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorProvincia",
					"idCotizacion":idCotizacion},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					cantonList.dataSource.filter({});
					cantonList.setDataSource(data.cantonArray);
					
				}
			});
		}
		function cantonProvincia(){
			var provinciaCId = $("#provincia option:selected").val();
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorProvinciaCanton",
					"idCotizacion":idCotizacion,
					"provinciaId":provinciaCId
					},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					cantonList.dataSource.filter({});
					cantonList.setDataSource(data.cantonArray);					
				}
			});
		}
		
		function ConsultarParroquias(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorCanton",
					"idCotizacion":idCotizacion},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					parroquiaList.dataSource.filter({});
					parroquiaList.setDataSource(data.parroquiaArray);
					
				}
			});
		}
		
		function ConsultarParroquiasCanton(){
			var cantonPId=$("#canton option:selected").val();
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorParroquiaCanton",
					"idCotizacion":idCotizacion,
					"cantonId":cantonPId
					},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					parroquiaList.dataSource.filter({});
					parroquiaList.setDataSource(data.parroquiaArray);
					
				}
			});
		}
		
		function ConsultarCultivos(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarTipoCultivo"},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					cultivoList.dataSource.filter({});
					cultivoList.setDataSource(data.cultivoArray);
					
				}
			});
		}
		
		
		function ConsultarPuntosVenta(){
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorCanal",
					"idCotizacion":idCotizacion},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					PuntosVentaList.dataSource.filter({});
					PuntosVentaList.setDataSource(data.puntoArray);
					
				}
			});
		}
		
		function ConsultarPuntosVentaId(){
			var canalId=puntoVentaId = $("#sucursal_canal option:selected").val();
			$.ajax({
				url:'../AgriCotizacionEditableController',
				data:{"tipoConsulta":"encontrarPorCanalId",
					"idCotizacion":idCotizacion,
					"canalId":canalId
				},
				async: false,
				type:'post',
				datatype:'json',
				success: function(data){
					PuntosVentaList.dataSource.filter({});
					PuntosVentaList.setDataSource(data.puntoArray);
					
				}
			});
		}
		
		function obtenerCanal() {

			$.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"tipoConsulta" : "CargarCanal"
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {					
					canalList.dataSource.filter({});
					canalList.setDataSource(data.ListadoCanal);					
				}
			});
		}
		
		function CargarCombo() {
			var listPuntoVenta="";
			var puntoId = $("#sucursal_canal option:selected").val() ? $(
			"#sucursal_canal option:selected").val() : "";
			$("#punto_venta_session").children().remove();
			$.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"tipoConsulta" : "cargarCombos",
					"canalId" : puntoId,
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					PuntosVentaList.dataSource.filter({});
					PuntosVentaList.setDataSource(listPuntoVenta);
				}
			});			
		}
		
		function BusquedaCedula(){
			 var cedula=$("#identificacion").val();
			 $.ajax({
					url:'../AgriCotizacionEditableController',
					data:{
						"tipoConsulta" : "cliente",
						"identificacion" : cedula
					},
					type : 'POST',
					datatype : 'json',
					success : function(data){
						if (data.success == false){
							 notification.show({
		                           title: "ERROR",
		                           message: data.error
		                       }, "error");							
							return;
						}
						$("#nombre").val(data.nombres);	
						$("#clienteId").val(data.idCliente);	
						$("#entidadId").val(data.idEntidad);	
					}
				});
			 
		}
				
	</script>
</head>
<body>
		<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
		%>
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">DATOS DE LA COTIZACION # <b><div id="idCotizacion"></div></b></div>
					<div class="panel-body">
						 <ul class="fieldlist">
						 	<li>
				                <label for="simple-input">Sponsor</label>
				               <select id="sucursal_canal" data-placeholder="Seleccione el canal" style="width: 100%"></select> 
				            </li>
				            <li>
				                <label for="simple-input">Canal</label>
				               <select id="punto_venta_session" data-placeholder="Seleccione el punto venta" style="width: 100%"></select>
				            </li>
						 	<li>
				                <label for="simple-input">Id Emision</label>
				                <input id="idEmision" type="text" class="k-textbox" style="width: 100%;" />
				            </li>
				            <li>
				                <label for="simple-input">Número Tramite Canal</label>
				                <input id="Numtramite" type="text" class="k-textbox" style="width: 100%;" />
				            </li>
				            <li>
				                <label for="simple-input">Identificacion Cliente</label>
				                <input id="identificacion" type="text" class="k-textbox" style="width: 100%;" onchange="BusquedaCedula();" />
				                <input id="clienteId" type="hidden" class="k-textbox" style="width: 100%;" />
				                <input id="entidadId" type="hidden" class="k-textbox" style="width: 100%;" />
				            </li>
				            <li>
				                <label for="simple-input">Nombres Cliente</label>
				                <input id="nombre" type="text" class="k-textbox" style="width: 100%;" />
				            </li>
				             <li>
				                <label for="simple-input">Provincia</label>
				                <select id="provincia" data-placeholder="Seleccione una provincia" style="width: 100%"></select>
				            </li>
				             <li>
				                <label for="simple-input">Canton</label>
				               <select id="canton" data-placeholder="Seleccione un cantón" style="width: 100%"></select>
				            </li>
				            <li>
				                <label for="simple-input">Parroquia</label>
				                <select id="parroquia" data-placeholder="Seleccione una parroquia" style="width: 100%"></select>
				            </li>
				            <li>
				                <label for="simple-input">Lugar de Siembra</label>
				                <textarea id="lugarSiembra" class="k-textbox" style="width: 100%;" ></textarea>
				            </li>
				            <li>
				                <label for="simple-input">Cultivo</label>
				                <select id="cultivo" data-placeholder="Seleccione el cultivo" style="width: 100%"></select>
				            </li>
				            <li>
				                <label for="simple-input">Hectareas Aseguradas</label>
				                <input id="hectareasAsegurables" type="text" class="text_Numeric" style="width: 100%;" />
				            </li>
				            <li>
				                <label for="simple-input">Hectareas Totales</label>
				                <input id="hectareasTotales" type="text" class="text_Numeric" style="width: 100%;" />
				            </li>
				            
				            <li>
				                <label for="simple-input">Fecha de Siembra</label>
				               <input type="text" data-date-format="dd/mm/yyyy" id="dp1" style="width: 100%">
				            </li>
				            
				             <li>
				                <label for="simple-input">Costo Producción</label>
				                <input type="text" class="text_Currency"
								name="costoProduccion" id="costoProduccion"
								style="width: 100%;"								
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Tasa</label>
				                <input type="text" class="text_Currency"
								name="tasa" id="tasa"
								style="width: 100%;"
								required> </input>
				            </li>
				            
				            <li>
				                <label for="simple-input">Suma Asegurada</label>
				                <input type="text" class="text_Currency"
								name="sumaAsegurada" id="sumaAsegurada"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Prima Neta</label>
				                <input type="text" class="text_Currency"
								name="primaNeta" id="primaNeta"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Imp. Seguro Campesino</label>
				                <input type="text" class="text_Currency"
								name="seguroCampesino" id="seguroCampesino"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Imp. Super Bancos</label>
				                <input type="text" class="text_Currency"
								name="superBancos" id="superBancos"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Imp. Derecho Emision</label>
				                <input type="text" class="text_Currency"
								name="derechoEmision" id="derechoEmision"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Iva</label>
				                <input type="text" class="text_Currency"
								name="iva" id="iva"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Impuestos</label>
				                <input type="text" class="text_Currency"
								name="impuestos" id="impuestos"
								style="width: 100%;"
								required> </input>
				            </li>
				            <li>
				                <label for="simple-input">Prima Total</label>
				                <input type="text" class="text_Currency"
								name="primaTotal" id="primaTotal"
								style="width: 100%;"
								required> </input>
				            </li>
				            	  
				          </ul>
				          <div class="row">
							<div class="col-md-5">
							</div>
							<div class="col-md-1">
								<button class="btn btn-primary" id="guardar">
									<span class="glyphicon glyphicon-search"></span> &nbsp; Guardar
								</button>	
							</div>
							<div class="col-md-5">
							</div>  
						</div>
				          
					</div>
				</div>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	
</body>
</html>
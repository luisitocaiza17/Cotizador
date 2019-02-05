<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-control" content="no-cache">
	<title>Aprobacion Cotizaciones - CotizadorQBE</title>
	<!-- <script src="../static/js/jquery.min.js"></script> -->
	<link href="../static/css/loading.css" rel="stylesheet">
	
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
    <script src="../static/js/Kendo/kendo.all.min.js"></script>
    <script src="../static/js/Kendo/kendo.web.min.js"></script>
    <script src="../static/js/Kendo/jszip.min.js"></script>
    
    <!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	<!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
    
    <link rel="stylesheet" href="../static/css/select2/select2.css">
    <script src="../static/js/cotizador/comun.js"></script>
	
	<!--<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="Agricola"></script>-->
	<!-- <script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="Agricola"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	 -->

<script>
$(function() {
	$(".wrapper1").scroll(function() {
		$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	});
	$(".wrapper2").scroll(function() {
		$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	});
});
function GetQueryStringByParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	var estadoConsulta="CPA";//GetQueryStringByParameter('State');
	var codigo = "";
	var tipoConsulta = "";
	var Reglas = "";
	var VariablesCalculo="";
	var nombreArchivo = "";
	//var AniosCultivo = 0;
	var RecalSeguroCampesino=0;
	var RecalSuperBancos=0;
	var RecalDerechoEmision = 0;
	var Recalculo = false;
	var PuntosVentaList = "";
	$(document).ready(function() {
		
		
		
		
			activarMenu("CotizacionAprobacionAgricola");
			//Cargar combo 
			//$("#punto_venta").children().remove();
			//$("#punto_venta").append("<option value=''>Seleccione punto venta</option>");
			CargarComboAgente();
			obtenerCanal();
			CargarCombo();
			$("#content-Upload").hide();
			$("#loading").hide();
			$('#dataTable').hide();
			$('#btnImportar').hide();
			$("#content-cargaMasiva").hide();
			//cargarPuntosVentaSessionGanadero();
			
			$(".text_Currency").kendoNumericTextBox({
		        format: "c",
		        min: 0,
		        decimals: 2
		    });
			
			$(".text_Decimal").kendoNumericTextBox({
				format : '#.00',
		        min: 0,
		        decimals: 2
		    });
			//Carga Archivo
			$("#files").kendoUpload({
				async : {
					saveUrl : "../AgriCotizacionAprobacionController",
					removeUrl: "../AgriCotizacionAprobacionController"
				},
				multiple : false,
				upload : onUpload,
				success : onSuccess
			    //cancel: onCancel
			});
			// Evento change Suma asegurada (valores)
			$("#SumaAsegurada").kendoNumericTextBox({
                        change: onChange,
                        spin: onSpin
            });
			
			// Evento change Suma asegurada (valores)
			$("#Tasa").kendoNumericTextBox({
                        change: onChange,
                        spin: onSpin
            });
			$("#punto_venta_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "puntoVentaId",
				animation : false,
				maxSelectedItems : 1
			}); 
		    
			PuntosVentaList = $("#punto_venta_session").data(
			"kendoMultiSelect");
		    
			$('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#cotizacion_id").val("");
	    	 	$("#tipo_objeto_id").val("0");
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
	    	 	$("#punto_venta_session").val("");	    	 	
	    	 	//$("#agentes").val("");
	    	 	//$("#identificacion").val("");
	    	 	//$("#mis_cotizaciones").prop( "checked", false );
	    	 	$('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide();
	 			$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			//$('#dataTable_wrapper').hide();
	 			$("#content-cargaMasiva").hide();
	 			$("#content-Upload").hide();
	 			$("#datos_temporal").val("");
	 			nombreArchivo="";
	 			var up = $('#files').data().kendoUpload;
	 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
	 			up._removeFileEntry(allLiElementsToBeRemoved );
	 			$('#btnImportar').hide();
	 			PuntosVentaList.value("");

	    	 });
			//Consulta 
			$('#ConsultaBtn').click(function(){
				$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			//$('#dataTable_wrapper').hide();
	 			$("#content-cargaMasiva").hide();
	 			//Limpiar archivos cargados
	 			var up = $('#files').data().kendoUpload;
	 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
	 			up._removeFileEntry(allLiElementsToBeRemoved );	
	 			$('#btnImportar').hide();
	 			$("#content-Upload").hide();
				Cargar();		
					
			});
			//Boton Carga Masiva
			$('#btnAprobacionMasiva').click(function(){
				$('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide();
	 			$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			$("#content-cargaMasiva").hide();
				$("#content-Upload").show();
	    	 });
			///TODO: al seleccionar el combo canal
			$("#sucursal_canal").change(function(){	
				$("#punto_venta_session").children().remove();
				$("select option:selected").each(function(){
					CargarCombo();
				});
			});
			
	//Boton aprobar cambio de valores 
		$("#aprobar-record").bind({
			click : function() {
				var cotizacionId = $("#CotizacionId").val();
				var tasa = $("#Tasa").data("kendoNumericTextBox").value();
				var ValorAsegurado = $("#SumaAsegurada").data("kendoNumericTextBox").value();
				var Prima = $("#PrimaNetaTotal").data("kendoNumericTextBox").value();
				var Iva = $("#Iva").data("kendoNumericTextBox").value();
				var TotalFactura = $("#TotalFactura").data("kendoNumericTextBox").value();
				var observacion = $("#Observacion").val();
				if (Recalculo==true){
					EnviarAprobacion(cotizacionId,ValorAsegurado,Prima,Iva,TotalFactura,RecalDerechoEmision,
							RecalSeguroCampesino,RecalSuperBancos,tasa,observacion,true);
				}
				else {
					EnviarAprobacion(cotizacionId,"","","","","","","","","",false);
				}
			}
		});
	///Boton rechazar 
		$("#rechazar-record").bind({
			click : function() {
				var cotizacion = $("#CotizacionId").val();
				RechazarCotizacion(cotizacion);
			}
		});
	});
	function CargarCombo() {
		
		/* $.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				'tipoConsulta' : 'cargarCombos'
			},
			type : 'post',
			datatype : 'json',
			async : false,
			success : function(data) {
				var listPuntoVenta = data.listPuntoVenta;
				$("#punto_venta_session").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "puntoVentaId",
					animation : false,
					dataSource : listPuntoVenta,
					maxSelectedItems : 1
				});
				
			}
		}); */
		var listPuntoVenta="";
		var puntoId = $("#sucursal_canal option:selected").val() ? $(
		"#sucursal_canal option:selected").val() : "";
		/* var puntoId = "";
		if (puntoVentaId != null && puntoVentaId != '')
			puntoId = puntoVentaId;
		else{
			puntoId = $("#punto_venta_session option:selected").val() ? $(
			"#punto_venta_session option:selected").val() : "";
		 	puntoId = $('#punto_venta_session').val(); 
		} */
		if (puntoId==""){
			$("#punto_venta_session").children().remove();
		}
		else {
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
				
				$("#punto_venta_session").children().remove();
				$("#punto_venta_session").append("<option value=''>Seleccione una opción</option>");
				listPuntoVenta = data.listPuntoVenta;
				$.each(listPuntoVenta, function (index){								
					$("#punto_venta_session").append("<option value='"+ listPuntoVenta[index].puntoVentaId +"'>"+ listPuntoVenta[index].nombre +"</option>");
				});

				
				PuntosVentaList.dataSource.filter({});
				PuntosVentaList.setDataSource(listPuntoVenta);

				/* $("#sucursal_canal").empty();
				$("#sucursal_canal").append("<option value=''>Seleccione una opcion</option>");
				$.each(listadoSucursal, function(index) {
					$("#sucursal_canal").append("<option value='" + listadoSucursal[index].id + "' >" + listadoSucursal[index].SucursalNombre + "</option>");
				}); */
				/* $("#punto_venta_session").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "puntoVentaId",
					animation : false,
					dataSource : listPuntoVenta,
					maxSelectedItems : 1
				}); */
			}
		});
		}
	}
	function CargarComboAgente(){
		$.ajax({
			url : '../AgenteController',
			data : {
				"tipoConsulta" : "consultarAgentes",
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {	
				var listagencia = data.agentes;
				$("#agente_session").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "id",
					animation : false,
					dataSource : listagencia,
					maxSelectedItems : 1
				});
				/* agenteList.dataSource.filter({});
				agenteList.setDataSource(data.agentes);	 */			
				
			}
		}); 
	}
	
	function obtenerCanal() {

		/* var puntoId = "";
		if (puntoVentaId != null && puntoVentaId != '')
			puntoId = puntoVentaId;
		else
			puntoId = $('#punto_venta').val(); */
		$.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				"tipoConsulta" : "CargarCanal"
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var listadoCanal = data.ListadoCanal;
				/* $("#sucursal_canal").empty();
				$("#sucursal_canal").append("<option value=''>Seleccione una opcion</option>");
				$.each(listadoCanal, function(index) {
					$("#sucursal_canal").append("<option value='" + listadoCanal[index].CanalId + "' >" + listadoCanal[index].CanalNombre + "</option>");
				}); */
				$("#sucursal_canal").kendoMultiSelect({
					dataTextField : "CanalNombre",
					dataValueField : "CanalId",
					animation : false,
					dataSource : listadoCanal,
					maxSelectedItems : 1
				});
			}
		});
	}
	
	function Cargar() {
		/* $('#dataTable').hide();
		$('#dataTable_wrapper').hide();
		$("#datos_temporal").val("");*/
		$("#buscando").fadeIn("slow"); 

		// Validaciones de las busquedas	
		var cotizacionId = $("#cotizacion_id").val();
		cotizacionId = $.trim(cotizacionId);
		var NroTramite = $("#nro_tramite").val();
		var identificacion = $("#identificacion").val();
		var ApellidosCliente = $("#Apellidos_Cliente").val();
		var fechaInicio = $("#dp1").val();
		var fechaFin = $("#dp2").val();
		var tipoObjeto = "8";
		//var puntoVenta = $("#punto_venta_session").val();
		var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
				"#punto_venta_session option:selected").val() : "";
		var canalId = $("#sucursal_canal option:selected").val() ? $(
				"#sucursal_canal option:selected").val() : "";
				
		var agente = $("#agente_session option:selected").val() ? $(
		"#agente_session option:selected").val() : "";
		//var agente = "";
		//var identificacion = $("#identificacion").val();
		//var misCotizaciones = $("#mis_cotizaciones").is(":checked");

		if (tipoObjeto == "0" && cotizacionId == "" && fechaInicio == ""
				&& fechaFin == "" && puntoVenta == "" && identificacion == "") {
			alert("Ingrese al menos un campo de busqueda");
			$("#buscando").fadeOut("slow");
			return false;
		}

		if (fechaInicio != "" && fechaFin == "") {
			alert("Ingrese una fecha Fin de Busqueda");
			$("#buscando").fadeOut("slow");
			return false;
		}
		
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		$("#grid").kendoGrid({
			toolbar: ["excel"],
            excel: {
                fileName: "Listado.xlsx",
                filterable: true,
                allPages: true
            },
			dataSource: {
                type: "json",
                serverPaging: true,
                serverSorting: true,
                pageSize: 20,
                transport:{
                	read: {
                		url: "../AgriCotizacionAprobacionController",
                		data: {
    						"fInicio" : fechaInicio,
    						"fFinal" : fechaFin,
    						"numeroCotizacion" : cotizacionId,
    						"codigoTipoObjeto" : tipoObjeto,
    						"puntoVenta" : puntoVenta,
    						"agente" : agente,
    						"NroTramite":NroTramite,
    						"ApellidosCliente":ApellidosCliente,
    						"identificacion":identificacion,
    						"CanalId":canalId,
    						"tipoConsulta" : "encontrarPendientesAprobacion",
    						"estadoConsulta" : estadoConsulta
    					}
                	}
                	
                },
                schema: {
                	data: "Data",
                	total: "Total",
                }
            },
			columns: [
			{ field: "id", title: "No."},
			{ field: "IdentificacionCliente", title: "Identificacion"},
			
			{ field: "tipoCultivoNombre", title: "Tipo Cultivo"},
			{ field: "provinciaNombre", title: "Provincia"},
			{ field: "AgenteId", title: "Agente", hidden: true, exportar: true},
			{ command: { text: "Aprobar", click: fnEventoClick }, title: " ", width: "180px" }],
            height: 500,
            selectable: true,
            pageable: {
                info: true,
                numeric: false,
                previousNext: false
            },
            scrollable: {
                virtual: true
            },
        });
		
		var exportFlag=false;
		$("#grid").data("kendoGrid").bind("excelExport", function (e) {
			var columns = e.sender.columns;
			if (!exportFlag) {
	            jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.showColumn(this.field);
	                }
	            });
	            
	            //e.sender.showColumn("AgenteId");
	            e.preventDefault();
	            exportFlag = true;
	            setTimeout(function () {
	                e.sender.saveAsExcel();
	            }, 1000);
	        } else {
	        	jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.hideColumn(this.field);
	                }
	            });
	            exportFlag = false;
	        }
		});
		
		/* $.ajax({
					url : '../AgriCotizacionAprobacionController',
					data : {
						"fInicio" : fechaInicio,
						"fFinal" : fechaFin,
						"numeroCotizacion" : cotizacionId,
						"codigoTipoObjeto" : tipoObjeto,
						"puntoVenta" : puntoVenta,
						"agente" : agente,
						"NroTramite":NroTramite,
						"ApellidosCliente":ApellidosCliente,
						"identificacion":identificacion,
						"CanalId":canalId,
						"tipoConsulta" : "encontrarPendientesAprobacion",
						"estadoConsulta" : estadoConsulta
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						/* $("#grid").data("kendoGrid").dataSource.data(data.listadoCotizacion);
						var grid = $('#grid').data('kendoGrid');
						var pager = grid.pager;
						pager.bind('change', test_pagechange);
						function test_pagechange(e){
						    console.log(e);
						    alert("pagina"+e.index);
						} 
					}
				}); */
	}
	
	function fnEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        alert("Cotización id:"+dataItem.id);
    }
	
	// actualizar valores 
	function actualizar(Cotizacion) {
		$("#CotizacionId").val("");
		($("#SumaAsegurada").data("kendoNumericTextBox")).value(0);
		($("#PrimaNetaTotal").data("kendoNumericTextBox")).value(0);
		//($("#HectareasAsegurables").data("kendoNumericTextBox")).value(0);
		//($("#CostoProduccion").data("kendoNumericTextBox")).value(0);
		($("#Tasa").data("kendoNumericTextBox")).value(0);
		//($("#CostoMantenimiento").data("kendoNumericTextBox")).value(0);
		($("#Iva").data("kendoNumericTextBox")).value(0);
		($("#TotalFactura").data("kendoNumericTextBox")).value(0);
		$("#TipoSeguroId").val("");
		$("#Observacion").val("");
		
		Reglas = "";
		VariablesCalculo = "";
		Recalculo=false;
		RecalSeguroCampesino=0;
		RecalSuperBancos=0;
		RecalDerechoEmision = 0;
		//AniosCultivo = 0;
		$
				.ajax({
					url : '../AgriCotizacionAprobacionController',
					data : {
						"cotizacionId" : Cotizacion,
						"tipoConsulta" : "buscarCotizacionId"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						$("#CotizacionId").val(data.codigo);
						$("#TipoSeguroId").val(data.tipo_seguro);
						($("#SumaAsegurada").data("kendoNumericTextBox"))
								.value(data.suma_asegurada_total);
						($("#PrimaNetaTotal").data("kendoNumericTextBox"))
								.value(data.prima_neta_total);
						//($("#HectareasAsegurables").data("kendoNumericTextBox")).value(data.hectareas_asegurables);
						($("#Iva").data("kendoNumericTextBox")).value(data.iva);
						($("#TotalFactura").data("kendoNumericTextBox"))
								.value(data.totalFactura);
						//AniosCultivo = data.anios_cultivo;
						///TODO:Nuevos campos a presentar en la pantalla de actualizacion 
						
						$('#HectareasAsegurables').html(data.hectareas_asegurables);
						$('#CostoProduccion').html('$ '+data.costo_produccion);
						$('#MontoCredito').html('$ '+data.monto_credito);
						$('#FechaSiembra').html(data.fecha_siembra);
						$('#Parroquia').html(data.parroquia_nombre+" / "+data.sitio);
						$('#Provincia').html(data.provincia_nombre + " / "+data.canton_nombre);
						$('#TipoCultivo').html(data.tipo_cultivo_nombre);
						$('#Variedad').html(data.variedad);
						$('#ClienteDatos').html(data.nombres_cliente);
						$('#AgenteDatos').html(data.nombresAgente);
						$('#UsuarioDatos').html(data.nombresUsuario);
						$('#Longitud').html(data.longitud);
						$('#Latitud').html(data.latitud);
						$('#Observacion').html(data.observacion);
						$('#ClienteIdentificacion').html(data.ClienteIdentificacion);
						$('#DisponeRiego').html(data.DisponeRiego);
						$('#DisponeAsistencia').html(data.DisponeAsistencia);
						$('#AgricultorTecnificado').html(data.AgricultorTecnificado);
						Reglas = data.Regla;
						
						if (data.tasa == 0) {
							if (typeof Reglas != "undefined") {
							//($("#CostoProduccion").data("kendoNumericTextBox")).value(Reglas.CostoProduccion);
							($("#Tasa").data("kendoNumericTextBox"))
								.value(Reglas.Tasa);
							//($("#CostoMantenimiento").data("kendoNumericTextBox")).value(Reglas.CostoMantenimiento);
							}
						}else {
							($("#Tasa").data("kendoNumericTextBox")).value(data.tasa);
						}
				//Variables de calculo 
				VariablesCalculo = data.VariablesCalculo;
				//deshabilitar controles 
				($("#PrimaNetaTotal").data("kendoNumericTextBox"))
						.enable(false);
				//($("#HectareasAsegurables").data("kendoNumericTextBox")).enable(false);
				//($("#CostoProduccion").data("kendoNumericTextBox")).enable(false);
				//($("#CostoMantenimiento").data("kendoNumericTextBox")).enable(false);
				($("#Iva").data("kendoNumericTextBox")).enable(false);
				($("#TotalFactura").data("kendoNumericTextBox")).enable(false);
				//($("#Tasa").data("kendoNumericTextBox")).value(0);
				var tasa = $("#Tasa").data("kendoNumericTextBox").value();
				if (tasa == 0)
					($("#Tasa").data("kendoNumericTextBox")).enable(true);
				else
					($("#Tasa").data("kendoNumericTextBox")).enable(false);
			}
		});
	}
	//
	//Functions para el evento chage  numerictextbox
	function onChange() {
		var tasa = $("#Tasa").data("kendoNumericTextBox").value();
		if (tasa == 0) {
			alert("La tasa tiene un valor (0) debe ingresar un valor para el cálculo");
			return;
			//e.preventDefault();
		}
		CalculoValores();
	}
	function onSpin() {
		//($("#CostoMantenimiento").data("kendoNumericTextBox")).value(this.value());
		var tasa = $("#Tasa").data("kendoNumericTextBox").value();
		if (tasa == 0) {
			alert("La tasa tiene un valor (0) debe ingresar un valor para el cálculo");
			//e.preventDefault();
			return;
		}
		CalculoValores();
	}

	//Calculo de valores
	function CalculoValores() {
		//var TipoSeguro= $("#TipoSeguroId").val();
		Recalculo = true;
		var VariablesCalc = VariablesCalculo;
		var Bancos = 3.50;
		var SeguroCamp = 0.50;
		var DerechoEmision = 0.45;
		//var aniosCultivo=1;
		var Iva = 12;
		if (typeof VariablesCalc != "undefined") {
			Bancos = VariablesCalc.SuperBancos;
			SeguroCamp = VariablesCalc.SeguroCampesino;
			DerechoEmision = VariablesCalc.DerechosEmision;
			Iva = VariablesCalc.Iva;
		}
		//if (typeof AniosCultivo != "undefined")
		//aniosCultivo = AniosCultivo;

		var tasa = $("#Tasa").data("kendoNumericTextBox").value();
		//var HecAsegurables= $("#HectareasAsegurables").data("kendoNumericTextBox").value();
		//var ConstoProduccion= $("#CostoProduccion").data("kendoNumericTextBox").value();
		//var costoMantenimiento= $("#CostoMantenimiento").data("kendoNumericTextBox").value();
		var Prima = $("#PrimaNetaTotal").data("kendoNumericTextBox").value();
		var ValorAsegurado = $("#SumaAsegurada").data("kendoNumericTextBox")
				.value();

		Prima = ValorAsegurado * (tasa / 100);
		Bancos = (Bancos * Prima) / 100;
		SeguroCamp = (SeguroCamp * Prima) / 100;
		var valorBase = DerechoEmision + Bancos + SeguroCamp;
		Iva = ((Prima + valorBase) / 100 * 100 * Iva) / 100;
		var TotalFactura = Prima + valorBase;
		($("#Iva").data("kendoNumericTextBox")).value(Iva);
		($("#TotalFactura").data("kendoNumericTextBox")).value(TotalFactura);
		($("#PrimaNetaTotal").data("kendoNumericTextBox")).value(Prima);
		RecalSeguroCampesino = SeguroCamp;
		RecalSuperBancos = Bancos;
		RecalDerechoEmision = DerechoEmision;
	}
	//Carga de archivos masivo

	function onSuccess(e) {
		if (e.operation == "upload") {
			nombreArchivo = e.files[0].name;
			$('#btnImportar').show();
		} else {
			nombreArchivo = "";
			$('#btnImportar').hide();
		}
	}

	function onUpload(e) {
		// Array with information about the uploaded files
		var files = e.files;
		// Check the extension of each file and abort the upload if it is not .jpg
		$.each(files, function() {
			if (this.extension.toLowerCase() != ".xls"
					&& this.extension.toLowerCase() != ".xlsx") {
				alert("Por favor seleccionar el archivo correcto.");
				e.preventDefault();
			}
		});
	}
	//Aprobar cotizacion 
	function EnviarAprobacion(Cotizacion, ValorAsegurado, Prima, Iva,
			TotalFactura, DerechoEmsion, SeguroCampesino, SuperBancos, Tasa,observacion,
			IsRecalculo) {
		$('#rechazar-record').attr('disabled', true);
		//ReglaId = Cotizacion;
		tipoConsulta = "Aprobacion";
		$.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				"cotizacionId" : Cotizacion,
				"tipoConsulta" : tipoConsulta,
				"AprobacionStatus" : "AP",
				"ValorAsegurado" : ValorAsegurado,
				"Prima" : Prima,
				"Iva" : Iva,
				"TotalFactura" : TotalFactura,
				"DerechoEmsion" : DerechoEmsion,
				"SeguroCampesino" : SeguroCampesino,
				"SuperBancos" : SuperBancos,
				"Tasa" : Tasa,
				"Comentario" : observacion,
				"IsRecalculo" : IsRecalculo
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#cargando").fadeOut("slow");
				Cargar();
				//alert("La cotización a sido aprobada con éxito");
				Recalculo = false;
				RecalSeguroCampesino = 0;
				RecalSuperBancos = 0;
				RecalDerechoEmision = 0;
				var mensajeEstado = data.mensajeEstado;
				if (mensajeEstado!=""){
						alert(mensajeEstado);
				}
				//$("#msgPopup").show();
				//var $modal = $('#add');
				//parent.$('#add').modal('hide');
				//window.closeModal = function(){
				//    $('#add').modal('hide');
				//};
				//window.parent.closeModal();
			}
		});
	}
	function AprobarCotizacion(Cotizacion) {
		var r = confirm("Seguro que desea aprobar la cotización?");
		if (r == true) {
			$("#cargando").fadeIn("slow");
			EnviarAprobacion(Cotizacion, "", "", "", "", "", "", "", "", false);
		}

	}

	function RechazarCotizacion(Cotizacion) {
		var r = confirm("Seguro que desea rechazar la cotización?");
		if (r == true) {
			$('#aprobar-record').attr('disabled', true);
			$("#cargando").fadeIn("slow");
			ReglaId = Cotizacion;
			tipoConsulta = "Aprobacion";
			$.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"cotizacionId" : Cotizacion,
					"tipoConsulta" : tipoConsulta,
					"AprobacionStatus" : "RE"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#cargando").fadeOut("slow");
					Cargar();
					var mensajeEstado = data.mensajeEstado;
					if (mensajeEstado!="")
						alert(mensajeEstado);
					
				}
			});
		}

	}

	//para el datepicker 
	//Metodo validacion de fechas buscador
	$(function() {
		var startDate = new Date();
		var endDate = new Date();

		$('#dp1')
				.datepicker()
				.on(
						'changeDate',
						function(ev) {
							if (ev.date.valueOf() > endDate.valueOf()) {
								alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
								return false;
							} else {
								startDate = new Date(ev.date);
								$('#startDate').text($('#dp1').data('date'));
							}
							$('#dp1').datepicker('hide');
						});
		$('#dp2')
				.datepicker()
				.on(
						'changeDate',
						function(ev) {
							if (ev.date.valueOf() < startDate.valueOf()) {
								alert('La Fecha Final no puede ser menor que la Fecha Inicial');
								return false;
							} else {

								endDate = new Date(ev.date);
								$('#endDate').text($('#dp2').data('date'));
							}
							$('#dp2').datepicker('hide');
						});
	});

	///Aprobacion masiva 

	function importar() {
		if (nombreArchivo != "") {
			$("#loading").show();
			$('#dataTable').hide();
			$('#dataTableContent').html('');
			$('#dataTable_wrapper').hide();
			//$("#datos_temporal").val("");
			$.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"tipoConsulta" : "importar",
					"FileName" : nombreArchivo,
					"actividad": "aprobar"
				},
				type : 'POST', 
				datatype : 'json',
				success : function(data) {
					$("#loading").hide();
					alert(data.mensaje);
					var listDetalle = data.listDetalle;
					if (typeof listDetalle != 'undefined') {
						$('#datatableError').html('');
						$("#content-cargaMasiva").show();
						$('#dataTableMasiva').show();
						$.each(listDetalle, function(index) {
							$("#datatableError").append(
									"<tr class='odd gradeX'>" + "<td>"
											+ listDetalle[index].cotizacion
											+ "</td>" + "<td>"
											+ listDetalle[index].detalle
											+ "</td>" + "</tr>");
						});
						$("#content-Upload").hide();
					}
					//Limpiar upload
					var up = $('#files').data().kendoUpload;
		 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
		 			up._removeFileEntry(allLiElementsToBeRemoved );
		 			$('#btnImportar').hide();
				}
			});
		} else {
			alert("Seleccione un archivo.");
		}
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
<!-- ventana modificacion valores -->
<div class="modal fade"  id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Detalle de Cotización para Recálculo de valores</h4>
						</div>
						<div class="modal-body">
						<div class="alert alert-success" id="msgPopup">Los valores se grabaron con éxito.</div>
						<input type="hidden" class="form-control" id="CotizacionId">
						<input type="hidden" class="form-control" id="TipoSeguroId">
						<div class="panel panel-primary">
							<div class="panel-heading">Datos Generales</div>
							<div class="panel-body">
								<table border="1px">

									<tr>
										<td>

											<table border="1px">
												<tr bordercolor="blue">
													<td style="color: blue;">Identificación Cliente:</td>
													<td width="250px">
														<div id="ClienteIdentificacion"></div>
													</td>
													<td style="color: blue;">Cliente:</td>
													<td width="200px">
														<div id="ClienteDatos"></div>
													</td>
												</tr>
												<tr>
													<td style="color: blue;">Agente:</td>
													<td>
														<div id="AgenteDatos"></div>
													</td>
													<td style="color: blue;">Vendido Por:</td>
													<td>
														<div id="UsuarioDatos"></div>
													</td>
												</tr>
												<tr>
												<tr>
													<td style="color: blue;">Fecha Siembra:</td>
													<td>
														<div id="FechaSiembra"></div>
													</td>
													<td style="color: blue;">Tipo Cultivo:</td>

													<td>
														<div id="TipoCultivo"></div>
													</td>
												</tr>
												<tr>

													<td style="color: blue;">Variedad:</td>
													<td>
														<div id="Variedad"></div>
													</td>
													<td style="color: blue;">Provincia/ Cantón:</td>
													<td>
														<div id="Provincia"></div>
													</td>
												</tr>
												<tr>

													<!-- <td>&nbsp;</td> -->
													<td style="color: blue;">Parroquia/ Sitio:</td>
													<td>
														<div id="Parroquia"></div>
													</td>
													<td style="color: blue;">Monto Crédito:</td>
													<td>
														<div id="MontoCredito"></div>
													</td>
												</tr>


												<tr>
													<!-- <td>&nbsp;</td> -->
													<td style="color: blue;">Hectáreas Asegurables:</td>
													<td>
														<div id="HectareasAsegurables"></div> 
													</td>
													<td style="color: blue;">Costo Producción:</td>
													<td>
														<div id="CostoProduccion"></div>
													</td>
												</tr>
												
											</table>
										</td>
									</tr>
									<tr>
									<td>
									
									<tr>
										<td>
										<table>
										<tr>
										<td style="color: blue;">Latitud:</td>
													<td width="50px">
														<div id="Latitud"></div>
													</td>
													<td style="color: blue;" width="50px">Longitud:</td>
													<td width="50px">
														<div id="Longitud"></div>
													</td>
													
													<td style="color: blue;" width="50px">Dispone Riego:</td>
													<td width="50px">
														<div id="DisponeRiego"></div>
													</td>
													<td style="color: blue;" width="50px">Dispone Asistencia:</td>
													<td width="50px">
														<div id="DisponeAsistencia"></div>
													</td>
													<td style="color: blue;" width="50px">Agricultor Tecnificado:</td>
													<td width="50px">
														<div id="AgricultorTecnificado"></div>
													</td>
										</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td style="color: red;">Observación:
											<div id="Observacion"></div>
										</td>
									</tr>

								</table>
							</div>
						<!-- </div> -->
						<div class="panel-heading">Valores Recálculo</div>
							<div class="panel-body">
								<table>
									<tr>
										<td>
											<table>
												<tr>
													<td width="210px">Suma Asegurada:</td>
													<td width="210px"><input type="text"
														class="text_Currency" name="SumaAsegurada"
														id="SumaAsegurada" validationMessage="Campo requerido!!!"
														required> </input></td>
													<td>Tasa:</td>
													<td><input type="text" class="text_Decimal"
														name="Tasa" id="Tasa"
														validationMessage="Campo requerido!!!" required> </input>
													</td>
												</tr>
												<!-- <tr>
										<td>&nbsp;</td>
									</tr> -->
												<tr>
													<td width="210px">Prima Neta Total:</td>
													<td width="190px"><input type="text"
														class="text_Currency" name="PrimaNetaTotal"
														id="PrimaNetaTotal" validationMessage="Campo requerido!!!"
														required> </input></td>
													<td>Valor Iva:</td>
													<td><input type="text" class="text_Currency"
														name="Iva" id="Iva" validationMessage="Campo requerido!!!"
														required> </input></td>
													<!--  <td>Costo Producción:</td>
										<td>
										<input type="text" class="text_Currency"
										name="CostoProduccion" id="CostoProduccion"
										validationMessage="Campo requerido!!!" required>
										</input>
										</td>-->
												</tr>
												<!-- <tr>
										<td>&nbsp;</td>
									</tr> -->
												<tr>
													<td>&nbsp;</td>

													<td>Total Factura:</td>
													<td>&nbsp;</td>
													<td><input type="text" class="text_Currency"
														name="TotalFactura" id="TotalFactura"
														validationMessage="Campo requerido!!!" required> </input>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table>
												<tr>
													<td width="250px">Comentario: <!-- <textarea style="width: 400px" id="Comentario"></textarea> -->
													</td>
													<td><input style="width: 364px" type="text"
														id="Comentario"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button> 
							<button type="button" class="btn btn-primary" id="aprobar-record">Aprobar</button>
							<button type="button" class="btn btn-primary" id="rechazar-record">Rechazar</button>
						</div>
					</form>
				</div>
			</div>
		</div>


<!-- ventana consulta -->
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Cotizaciones Pendientes por Aprobar</center></td>

					</tr>
					<tr>
						<th style="width:180px">Busqueda por códigos:</th>
						<th >Cotizacion: </th>
						<th style="width:230px"> <input type="text" id="cotizacion_id"
							onkeypress="return justNumbers(event);"></th>
							<th style="width:100px">Nro. Trámite: </th>
							<th><input type="text" id="nro_tramite"
							onkeypress="return justNumbers(event);"></th>
					</tr>
					<tr>
						<th style="width:180px">Busqueda por Fechas:</th>
						<th >Fecha Creación Desde:</th>
						<th style="width:180px">  <input type="text"
							data-date-format="dd-mm-yyyy" id="dp1"></th>
						<th style="width:120px">Fecha Creación Hasta:</th>
						<th>  <input type="text"
							data-date-format="dd-mm-yyyy" id="dp2"></th>
					</tr>
					<tr>
						<th style="width:180px">Busqueda Varios:</th>
						<!--  <th style="width:100px">Mis Cotizaciones:</th>
						<th ><input type="checkbox"
							id="mis_cotizaciones"></th>-->
							<th>Canal:</th>
							<th >
							<select id="sucursal_canal" style="width: 165px" data-placeholder="Seleccione el canal"></select> 
						</th>
							<th style="width:120px">Punto Venta: </th>
						<th ><select id="punto_venta_session" data-placeholder="Seleccione el punto venta" ></select></th>
						
					</tr>
					<tr>
						<th style="width:180px">&nbsp;</th>
						<th style="width:120px">&nbsp;</th>
						<th ></th>
							<th>Agente:</th>
							<th ><select id="agente_session" data-placeholder="Seleccione el agente"></select></th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th style="width:120px">Cliente Identificación: </th>
						<th ><input type="text" onkeypress="return justNumbers(event);"
							id="identificacion"></th>
							<th>Cliente Apellidos:</th>
							<th >
							<input type="text"
							id="Apellidos_Cliente"></th>
					</tr>
					<!--<tr>
					
					<th>&nbsp;</th>
					</tr>
					  <tr>
						<th>&nbsp;</th>
						<th style="width:120px">Identificacion Cliente:</th>
						<th style="width:180px"> <input type="text"
							id="identificacion"></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>-->
				</thead>
				<tbody>
					<tr>
					<th>
				
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						  </th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
							</th>
							<th>
							<button  class="btn btn-primary" id="btnAprobacionMasiva" >Aprobación Masiva</button>
						</th>
					</tr>
					<!-- </table>
					</th> -->
						
						<!-- <th>&nbsp;</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
						
					</tr> -->
					
					<tr>
						<th colspan="4"><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th>
						<th colspan="4"><div id="cargando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg_"></span><img
										src="../static/images/ajax-loader.gif" /> Procesando la
									informacion, por favor espere...
								</div>
							</div></th>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- Button trigger modal -->

	</div>

<!-- Table -->

	<div id="grid"></div>
	

	<!-- Tabla para la carga masiva -->
	<div class="row" style="display: inline;" id="content-Upload">
		<div class="col-lg-12">
			<div class="table-responsive">
			<table width="80%">
					<tr >
							<td colspan="2" >APROVACIÓN MASIVA</td>
						</tr>
					<tr >
							<td  colspan="2"><input name="files" id="files" type="file" /></td>
							<div id="loading">
						<div class="loading-indicator">
						<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
							id="loading-msg">Procesando, espere por favor...</span>
						</div>
						</div>
						</tr>
						<td><br /> <br /></td>
						<tr align="right">
							<td colspan="2"><button id="btnImportar" class="btn btn-primary" 
								onclick="importar()">Subir Archivo</button></td>			
						</tr>
						
						</table>
			</div>
		</div>
	</div>
	<!-- Tabla con respuesta de aprovacion masiva -->
	<div class="row" style="display: none;" id="content-cargaMasiva">
		<div class="col-lg-12">
			<div class="table-responsive">
			<h3>Detalle de Cotizaciones <strong>procesadas</strong>.</h3>
				<table class="table table-striped table-bordered table-hover"
					id="dataTableMasiva">
					<thead>
						<tr>
							<th># COTIZACIÓN</th>
							<th>COMENTARIO</th>
						</tr>
					</thead>
					<tbody id="datatableError" class="searchable">
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
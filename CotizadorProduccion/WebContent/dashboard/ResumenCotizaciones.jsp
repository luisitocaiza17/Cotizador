<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Cotizaciones Agr&iacute;cola Resumen- CotizadorQBE</title>
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

<!--<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="Agricola"></script>-->
<!-- <script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="Agricola"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	 -->

<script>
	function GetQueryStringByParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	    results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	var canalId=GetQueryStringByParameter('Canal');
	var codigo = "";
	var tipoConsulta = "";
	var Reglas = "";
	var facturaId="";
	var estadoList = "";
	var canalList = "";
	var puntoVentaList = "";
	var agenteList = "";
	var nombreArchivo = "";
	
	$(document).ready(function() {
			activarMenu(this);
			
			$("#loading").hide();
			$("#cambios").hide();
			
			$("#estado").kendoMultiSelect({
 				dataTextField : "nombre",
 				dataValueField : "estadoId",
 				animation : false,
 				maxSelectedItems : 1
 			});	    	 
 	    	 
    	 	estadoList = $("#estado").data("kendoMultiSelect");
    	 	
    	 	$("#sucursal_canal").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "id",
				animation : false,
				maxSelectedItems : 1
			});
    	 	
    	 	canalList = $("#sucursal_canal").data("kendoMultiSelect");
    	 	
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
			
			$("#punto_venta_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "puntoVentaId",
				animation : false,
				maxSelectedItems : 1
			});
			
			puntoVentaList = $("#punto_venta_session").data("kendoMultiSelect");
			
			$("#agente_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "id",
				animation : false,
				maxSelectedItems : 1
			});
			
    	 	agenteList = $("#agente_session").data("kendoMultiSelect");    	 
			
    	 	$("#ConsultaBtn").click(function(){
    	 		$("#content-Upload").hide();
    	 		$("#descargarAlternativa").show();
    	 		$("#descargarAlternativa1").show();
    	 		Cargar();
    	 	});
    	 	
    	 	//Generacion de reporte masivo;
    	 	$("#descargarAlternativa").click(function(){
    	 	// Validaciones de las busquedas	
    	 		var cotizacionId = $("#cotizacion_id").val();
    			cotizacionId = $.trim(cotizacionId);
    			var NroTramite = $("#nro_tramite").val();
    			var identificacion = $("#identificacion").val();
    			var ApellidosCliente = $("#Apellidos_Cliente").val();
    			var fechaInicio = $("#dp1").val();
    			var fechaFin = $("#dp2").val();
    			var tipoObjeto = "8";
    			var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
    					"#punto_venta_session option:selected").val() : "";
    			var canalId = $("#sucursal_canal option:selected").val() ? $(
    					"#sucursal_canal option:selected").val() : "";
    					
    			var agente = $("#agente_session option:selected").val() ? $(
    			"#agente_session option:selected").val() : "";
    			var misCotizaciones = $("#mis_cotizaciones").is(":checked");
    			var pendientesImprimir = $("#imprimir").is(":checked");
    			estadoConsulta = $("#estado option:selected").val();
    			facturaId = $("#facturaId").val();
    			
    			if(canalId=="" || typeof canalId === "undefined"){
    				canalList.focus();
    				alert("Seleccione el canal.");			
    				$("#buscando").fadeOut("slow");
    				return false;
    			}
    				
    			if((cotizacionId=="" || typeof cotizacionId === "undefined")&& (fechaInicio=="" || typeof fechaInicio === "undefined") && (fechaFin=="" || typeof fechaFin === "undefined") && (puntoVenta=="" || typeof puntoVenta === "undefined") && (agente=="" || typeof agente === "undefined") && (identificacion=="" || typeof identificacion === "undefined") && (NroTramite=="" || typeof NroTramite === "undefined") && (ApellidosCliente=="" || typeof ApellidosCliente === "undefined") && (canalId=="" || typeof canalId === "undefined") && (estadoConsulta=="" || typeof estadoConsulta === "undefined")  && misCotizaciones == false && pendientesImprimir == false){
    				alert("Ingrese al menos un campo de busqueda");
    				$("#buscando").fadeOut("slow");
    				return false;
    			}

    			if (fechaInicio != "" && fechaFin == "") {
    				alert("Ingrese una fecha Fin de Busqueda");
    				$("#buscando").fadeOut("slow");
    				return false;
    			}
    			window.open('../AgriReportePOI?fInicio='+fechaInicio+'&fFinal=' + fechaFin+'&numeroCotizacion='+cotizacionId+'&codigoTipoObjeto='+tipoObjeto+'&puntoVenta='+puntoVenta+'&agente='+agente+'&NroTramite='+NroTramite+'&ApellidosCliente='+ApellidosCliente+'&identificacion='+identificacion+'&CanalId='+canalId+'&misCotizaciones='+misCotizaciones+'&esImpreso='+pendientesImprimir+'&tipoConsulta='+tipoConsulta+'&estadoConsulta='+estadoConsulta+'&facturaId='+facturaId);
    	 	});
    	 	
    	 	
    	 	
    	 	//Para Consultas Masivas
    	 	
    	 	//Boton Carga Masiva
			$('#btnConsultaMasiva').click(function(){
				$("#descargarAlternativa").hide();
				$("#descargarAlternativa1").hide();
				ProcesoMasivo="aprobacion";
				
				if ($('#grid').data().kendoGrid){
					$('#grid').data().kendoGrid.destroy();
					$('#grid').empty();
				}
	 			$('#grid').hide();
	 			$("#content-Upload").show();
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
    	 	
    	 	
			//CargarCombo();
			CargarComboAgente();
			obtenerCanal();
			CargarComboEstado();
			$("#content-Upload").hide();
			$("#descargarAlternativa").hide();
			$("#descargarAlternativa1").hide();
			$("#sucursal_canal").change(function(){
				if($("#sucursal_canal option:selected").val()!="" && typeof $("#sucursal_canal option:selected").val() != "undefined")
					CargarCombo($("#sucursal_canal option:selected").val());
			});		
						
			$('#LimpiarBtn').click(function(){
				$("#descargarAlternativa").hide();
				$("#descargarAlternativa1").hide();
	    	 	$("#cotizacion_id").val("");
	    	 	$("#nro_tramite").val("");
	    	 	$("#tipo_objeto_id").val("0");
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
				estadoList.value("");
	    	 	puntoVentaList.value("");
	    	 	agenteList.value("");
	    	 	canalList.value("");
	    	 	$("#identificacion").val("");
	    	 	$("#Apellidos_Cliente").val("");
	    	 	$("#mis_cotizaciones").attr('checked', false);	
	 			if ($('#grid').data().kendoGrid){
	    			$('#grid').data().kendoGrid.destroy();
	    			$('#grid').empty();
	    		}
	 			$('#grid').hide();
	 			$("#content-Upload").hide();
	    	 });
			
	});
		
	
	
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
			else{
				$('#btnImportar').prop('disabled', false);
			}
		});
	}
	
	function CargarCombo(sucursal_canal) {
		var sucursal_canal_Id = "";
		if (sucursal_canal != null && sucursal_canal != '')
			sucursal_canal_Id = sucursal_canal;
		else
			sucursal_canal_Id = $('#sucursal_canal').val();
		$.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				"tipoConsulta" : "cargarCombos",
				"canalId" : sucursal_canal_Id,
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				puntoVentaList.dataSource.filter({});
				puntoVentaList.setDataSource(data.listPuntoVenta);				
			}
		});	
	}
	
	function CargarComboEstado(){
		$.ajax({
			url : '../AgriResumenCotizacionesController',
			data : {
				"tipoConsulta" : "cargarCombos",
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				estadoList.dataSource.filter({});
				estadoList.setDataSource(data.listEstado);					
			}
		});
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
				agenteList.dataSource.filter({});
				agenteList.setDataSource(data.agentes);				
			}
		}); 
	}
	
	function obtenerCanal() {		
		$.ajax({
			url : '../AgriCanalController',
			data : {
				"tipoConsulta" : "encontrarPorCanalId",
				"canalId" : canalId
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				//var listadoCanal = data.canalesJSONArray;				
				canalList.dataSource.filter({});
				canalList.setDataSource(data.canalesJSONArray);
			}
		});
	}	
	function Cargar() {	
		$('#dataTable_wrapper').hide();

		// Validaciones de las busquedas	
		var cotizacionId = $("#cotizacion_id").val();
		cotizacionId = $.trim(cotizacionId);
		var NroTramite = $("#nro_tramite").val();
		var identificacion = $("#identificacion").val();
		var ApellidosCliente = $("#Apellidos_Cliente").val();
		var fechaInicio = $("#dp1").val();
		var fechaFin = $("#dp2").val();
		var tipoObjeto = "8";
		var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
				"#punto_venta_session option:selected").val() : "";
		var canalId = $("#sucursal_canal option:selected").val() ? $(
				"#sucursal_canal option:selected").val() : "";
				
		var agente = $("#agente_session option:selected").val() ? $(
		"#agente_session option:selected").val() : "";
		var misCotizaciones = $("#mis_cotizaciones").is(":checked");
		var pendientesImprimir = $("#imprimir").is(":checked");
		estadoConsulta = $("#estado option:selected").val();
		facturaId = $("#facturaId").val();
		
		if(canalId=="" || typeof canalId === "undefined"){
			canalList.focus();
			alert("Seleccione el canal.");			
			$("#buscando").fadeOut("slow");
			return false;
		}
			
		if((cotizacionId=="" || typeof cotizacionId === "undefined")&& (fechaInicio=="" || typeof fechaInicio === "undefined") && (fechaFin=="" || typeof fechaFin === "undefined") && (puntoVenta=="" || typeof puntoVenta === "undefined") && (agente=="" || typeof agente === "undefined") && (identificacion=="" || typeof identificacion === "undefined") && (NroTramite=="" || typeof NroTramite === "undefined") && (ApellidosCliente=="" || typeof ApellidosCliente === "undefined") && (canalId=="" || typeof canalId === "undefined") && (estadoConsulta=="" || typeof estadoConsulta === "undefined")  && misCotizaciones == false && pendientesImprimir == false){
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
                fileName: "QBE_Cotizaciones.xlsx",
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
    						"misCotizaciones":misCotizaciones,
    						"esImpreso":pendientesImprimir,
    						"tipoConsulta" : "encontrarPorEstado",
    						"estadoConsulta" : estadoConsulta,
    						"facturaId":facturaId
    					}
                	}
                	
                },
                schema: {
                	data: "Data",
                	total: "Total",
                }
            },
            columns: [
				{ field: "id", title: "Id Cotización", width: "60px",headerAttributes: { style: "white-space: normal"}},
				{ field: "idCotizacion2", title: "Póliza.", width: "60px",headerAttributes: { style: "white-space: normal"}},
				{ field: "Ciclo", title: "Periodo" , width: "160px",headerAttributes: { style: "white-space: normal"}},
				{ field: "FechaElaboracion", type:"date", title: "Fecha de Cotización", width: "100px",headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
				{ field: "IdentificacionCliente", title: "Identificación Cliente", width: "90px",headerAttributes: { style: "white-space: normal"}},
				{ field: "NombresCliente", title: "Cliente", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "CanalNombre", title: "Sponsor", width: "80px",headerAttributes: { style: "white-space: normal"}},
				{ field: "PuntoVenta", title: "Canal", width: "120px",headerAttributes: { style: "white-space: normal"}},
				{ field: "NumeroTramite", title: "Nro. Trámite", width: "80px",headerAttributes: { style: "white-space: normal"}},
				{ field: "VigenciaDias", title: "Vigencia en Dias", width: "120px",headerAttributes: { style: "white-space: normal"}},
				{ field: "fechaAprobacion", type:"date", title: "Fecha Aprobación", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
				{ field: "fechaVigenciaDesde", type:"date", title: "Vigencia Desde", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
				{ field: "fechaVigenciaHasta", type:"date", title: "Vigencia Hasta", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
				{ field: "tipoCultivoNombre", title: "Cultivo", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "provinciaNombre", title: "Provincia", width: "80px",headerAttributes: { style: "white-space: normal"}},
				{ field: "cantonNombre", title: "Cantón", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "parroquiaNombre", title: "Parroquia", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "DireccionSiembra", title: "Sitio Inversión", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "Telefono", title: "Telefono", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "hectareasAsegurables", title: "Has. Aseg", width: "50px",headerAttributes: { style: "white-space: normal"}},
				{ field: "sumaAseguradaTotal", title: "Monto Asegurado", width: "70px",headerAttributes: { style: "white-space: normal"}},
				{ field: "fechaSiembra", type:"date", format:"{0:dd/MM/yyyy}", title: "Fecha Siembra", width: "100px",headerAttributes: { style: "white-space: normal"}},
                { field: "primaNetaTotal", title: "Prima Neta", width: "70px",headerAttributes: { style: "white-space: normal"}},
				{ field: "costoProduccion", title: "Costo de Producción", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "seguroBancos", title: "Super Bancos", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "seguroCampesino", title: "Seguro Campesino", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "derechoEmision", title: "Derecho de Emisión", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "iva", title: "IVA", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "totalFactura", title: "Prima Total", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},                      
				{ field: "hectareasLote", title: "Has. Lote", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "Longitud", title: "Longitud", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true },
				{ field: "Latitud", title: "Latitud", width: "50px",headerAttributes: { style: "white-space: normal"},hidden: true, exportar: true},                      
				{ field: "DisponeRiego", title: "Dispone Riego", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true },
				{ field: "DisponeAsistencia", title: "Dispone Asistencia", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "AgricultorTecnificado", title: "Agricultor es Tecnificado", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "ObservacionRegla", title: "Observación", width: "160px",headerAttributes: { style: "white-space: normal"}},
				{ field: "ObservacionQBE", title: "ObservacionQBE", width: "160px",headerAttributes: { style: "white-space: normal"}},
				{ field: "tieneModificacion", title: "Advertencia", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "detalleModificacion", title: "Detalles advertencias", width: "160px",headerAttributes: { style: "white-space: normal"}},
				{ field: "Confirmacion", title: "Confirmación Canal", width: "160px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
				{ field: "EstadoCotizacion", title: "Estado", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "Confirmacion", title: "Confirmación de Emisión del Canal", width: "120px", attributes:{style:"color: red;"},headerAttributes: { style: "white-space: normal"}},
				{ field: "Movimiento", title: "Tiene Movimientos", width: "120px", attributes:{style:"color: red;"},headerAttributes: { style: "white-space: normal"}},
				{ field: "tiposMovimiento", title: "Tipos de Movimientos Realizados", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "tieneIndemnizacion", title: "Tiene pago de Indemnizaciones", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "objetoOfflineId", title: "Id Offline", width: "100px",headerAttributes: { style: "white-space: normal"},hidden: true, exportar: true}, 
				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " ", width: "100px",headerAttributes: { style: "white-space: normal"}}],
                height: 500,
                selectable: true,
                resizable: true,
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
	
	function fnEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));       
        $('#add').modal('show');
        actualizar(dataItem.id);
    }
	
	// actualizar valores 
	function actualizar(Cotizacion) {
		$("#CotizacionId").val("");
		$('#detalleCambio').html("");
		($("#SumaAsegurada").data("kendoNumericTextBox")).value(0);
		($("#PrimaNetaTotal").data("kendoNumericTextBox")).value(0);
		($("#Tasa").data("kendoNumericTextBox")).value(0);
		($("#Iva").data("kendoNumericTextBox")).value(0);
		($("#TotalFactura").data("kendoNumericTextBox")).value(0);
		$("#TipoSeguroId").val("");
		$("#Observacion").val("");
		
		//Reglas = "";
		VariablesCalculo = "";
		
		RecalSeguroCampesino=0;
		RecalSuperBancos=0;
		RecalDerechoEmision = 0;
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
						$("#cotId").html(data.codigo);
						$("#noTramite").html(data.NumeroTramite);
						$("#canal").html(data.CanalNombre);
						$("#puntoVenta").html(data.PuntoVenta);
						$("#estadoCotizacion").html(data.EstadoCotizacion);						
						$("#CotizacionId").val(data.codigo);
						$("#TipoSeguroId").val(data.tipo_seguro);
						($("#SumaAsegurada").data("kendoNumericTextBox"))
								.value(data.suma_asegurada_total);
						($("#PrimaNetaTotal").data("kendoNumericTextBox"))
								.value(data.prima_neta_total);
						($("#Iva").data("kendoNumericTextBox")).value(data.totalImpuestos);
						($("#TotalFactura").data("kendoNumericTextBox"))
								.value(data.totalFactura);
						///TODO:Nuevos campos a presentar en la pantalla de actualizacion 						
						$('#HectareasAsegurables').html(data.hectareas_asegurables);
						$('#CostoProduccion').html('$ '+data.costo_produccion);
						$('#CostoProduccionQBE').html('$ '+data.costo_produccionQBE);
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
						if(data.tieneCambios=="SI"){
							$('#cambios').show();
							$('#detalleCambio').html(data.detalleCambios);
							
						}else{
							$('#cambios').hide();
						}
						
						//Reglas = data.Regla;
						
						/*if (data.tasa == 0) {
							if (typeof Reglas != "undefined") {							
							($("#Tasa").data("kendoNumericTextBox")).value(Reglas.Tasa);							
							}
						}else {*/
							($("#Tasa").data("kendoNumericTextBox")).value(data.tasa);
						//}
				//Variables de calculo 
				VariablesCalculo = data.VariablesCalculo;
				//deshabilitar controles 
				($("#PrimaNetaTotal").data("kendoNumericTextBox")).enable(false);
				
				($("#Iva").data("kendoNumericTextBox")).enable(false);
				($("#TotalFactura").data("kendoNumericTextBox")).enable(false);
				($("#Tasa").data("kendoNumericTextBox")).enable(false);
				($("#SumaAsegurada").data("kendoNumericTextBox")).enable(false);
			}
		});
	}
	
	function importar() {
		if (nombreArchivo != "") {
			
			$('#grid').show();
			$("#content-cargaMasiva").hide();
 			var up = $('#files').data().kendoUpload;
 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
 			up._removeFileEntry(allLiElementsToBeRemoved );	
 			$('#btnImportar').hide();
 			$("#content-Upload").hide();
			
			
			$("#loading").show();
			$('#btnImportar').prop('disabled', true);
 			
 			if ($('#grid').data().kendoGrid){
 				$('#grid').data().kendoGrid.destroy();
 				$('#grid').empty();
 			}
 			
 			$("#grid").kendoGrid({
 				toolbar: ["excel"],
 	            excel: {
 	                fileName: "QBE_Cotizaciones.xlsx",
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
 	                		url: "../AgriResumenCotizacionesController",
 	                		data: {
 	                			"tipoConsulta" : "importar",
 	       						"FileName" : nombreArchivo
 	    					}
 	                	}
 	                	
 	                },
 	                schema: {
 	                	data: "Data",
 	                	total: "Total",
 	                }
 	            },
 	           columns: [
 	    				{ field: "id", title: "Id Cotización", width: "60px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "idCotizacion2", title: "Póliza.", width: "60px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "Ciclo", title: "Periodo" , width: "160px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "FechaElaboracion", type:"date", title: "Fecha de Cotización", width: "100px",headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
 	    				{ field: "IdentificacionCliente", title: "Identificación Cliente", width: "90px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "NombresCliente", title: "Cliente", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "CanalNombre", title: "Sponsor", width: "80px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "PuntoVenta", title: "Canal", width: "120px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "NumeroTramite", title: "Nro. Trámite", width: "80px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "VigenciaDias", title: "Vigencia en Dias", width: "120px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "fechaAprobacion", type:"date", title: "Fecha Aprobación", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "fechaVigenciaDesde", type:"date", title: "Vigencia Desde", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "fechaVigenciaHasta", type:"date", title: "Vigencia Hasta", width: "120px", hidden: true, exportar: true,headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "tipoCultivoNombre", title: "Cultivo", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "provinciaNombre", title: "Provincia", width: "80px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "cantonNombre", title: "Cantón", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "parroquiaNombre", title: "Parroquia", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "DireccionSiembra", title: "Sitio Inversión", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "Telefono", title: "Telefono", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "hectareasAsegurables", title: "Has. Aseg", width: "50px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "sumaAseguradaTotal", title: "Monto Asegurado", width: "70px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "fechaSiembra", type:"date", format:"{0:dd/MM/yyyy}", title: "Fecha Siembra", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	                    { field: "primaNetaTotal", title: "Prima Neta", width: "70px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "costoProduccion", title: "Costo de Producción", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "seguroBancos", title: "Super Bancos", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "seguroCampesino", title: "Seguro Campesino", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "derechoEmision", title: "Derecho de Emisión", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "iva", title: "IVA", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "totalFactura", title: "Prima Total", width: "70px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},                      
 	    				{ field: "hectareasLote", title: "Has. Lote", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "Longitud", title: "Longitud", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true },
 	    				{ field: "Latitud", title: "Latitud", width: "50px",headerAttributes: { style: "white-space: normal"},hidden: true, exportar: true},                      
 	    				{ field: "DisponeRiego", title: "Dispone Riego", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true },
 	    				{ field: "DisponeAsistencia", title: "Dispone Asistencia", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "AgricultorTecnificado", title: "Agricultor es Tecnificado", width: "50px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "ObservacionRegla", title: "Observación", width: "160px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "ObservacionQBE", title: "ObservacionQBE", width: "160px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "tieneModificacion", title: "Advertencia", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "detalleModificacion", title: "Detalles advertencias", width: "160px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "Confirmacion", title: "Confirmación Canal", width: "160px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
 	    				{ field: "EstadoCotizacion", title: "Estado", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "Confirmacion", title: "Confirmación de Emisión del Canal", width: "120px", attributes:{style:"color: red;"},headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "Movimiento", title: "Tiene Movimientos", width: "120px", attributes:{style:"color: red;"},headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "tiposMovimiento", title: "Tipos de Movimientos Realizados", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "tieneIndemnizacion", title: "Tiene pago de Indemnizaciones", width: "100px",headerAttributes: { style: "white-space: normal"}},
 	    				{ field: "objetoOfflineId", title: "Id Offline", width: "100px",headerAttributes: { style: "white-space: normal"},}, 	    				
 	    				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " ", width: "100px",headerAttributes: { style: "white-space: normal"}}],
 	                    height: 500,
 	                    selectable: true,
 	                    resizable: true,
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
													<td style="color: blue;">No:</td>
													<td width="250px">
														<div id="cotId"></div>
													</td>
													<td style="color: blue;">Nro. Trámite:</td>
													<td width="200px">
														<div id="noTramite"></div>
													</td>
												</tr>
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
													<td style="color: blue;">Sponsor:</td>
													<td>
														<div id="canal"></div>
													</td>
													<td style="color: blue;">Vendido Por:</td>
													<td>
														<div id="UsuarioDatos"></div>
													</td>
												</tr>
												<tr>
													<td style="color: blue;">Canal:</td>
													<td >
														<div id="puntoVenta"></div>
													</td>
													<td style="color: blue;">Estado:</td>
													<td >
														<div id="estadoCotizacion"></div>
													</td>													
												</tr>
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

													
													<td style="color: blue;">Provincia/ Cantón:</td>
													<td>
														<div id="Provincia"></div>
													</td>
													<td style="color: blue;">Monto Crédito:</td>
													<td>
														<div id="MontoCredito"></div>
													</td>
												</tr>
												<tr>

													<!-- <td>&nbsp;</td> -->
													<td style="color: blue;">Parroquia/ Sitio:</td>
													<td>
														<div id="Parroquia"></div>
													</td>
													<td style="color: blue;">Monto Recomendado QBE:</td>
													<td>
														<div id="CostoProduccionQBE"></div>
													</td>
													
												</tr>


												<tr>
													<!-- <td>&nbsp;</td> -->
													<td style="color: blue;">Hectáreas Asegurables:</td>
													<td>
														<div id="HectareasAsegurables"></div> 
													</td>
													<td style="color: blue;">Monto Recomendado Canal:</td>
													<td>
														<div id="CostoProduccion"></div>
													</td>
												</tr>
												
												<!-- Mostrar en pantalla los cambios de sitio y fecha de siembra -->												
												<tr id="cambios">
													<td style="color: blue;">Advertencias:</td>
													<td colspan="3">
														<div id="detalleCambio"></div>
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
													<td width="210px">Prima Neta:</td>
													<td width="190px"><input type="text"
														class="text_Currency" name="PrimaNetaTotal"
														id="PrimaNetaTotal" validationMessage="Campo requerido!!!"
														required> </input></td>
													<td>Impuestos:</td>
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

													<td>Prima Bruta:</td>
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
														id="Comentario" disabled="disabled"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="cerrar-popup"
								data-dismiss="modal">Cerrar</button> 							
						</div>
					</form>
				</div>
			</div>
		</div>


	<!-- ventana consulta -->
	<div class="row crud-nav-bar">
		<div class="well">
			<table class="table" style="width: 100%">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador Resumen de Cotizaciones.</td>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por c&oacute;digos:</th>
						<th style="width: 100px">Cotizaci&oacute;n:</th>
						<th style="width: 300px"><input type="text" id="cotizacion_id" style="width: 100%"></th>
						<th style="width: 100px">Nro. Tr&aacute;mite:</th>
						<th style="width: 300px"><input type="text" id="nro_tramite" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px"></th>
						<th style="width: 100px">Id Factura:</th>
						<th style="width: 300px"><input type="text" id="facturaId" style="width: 100%"></th>
						
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creaci&oacute;n Desde:</th>
						<th style="width: 300px"><input type="text" data-date-format="dd-mm-yyyy" id="dp1" style="width: 100%"></th>
						<th style="width: 100px">Fecha Creaci&oacute;n Hasta:</th>
						<th style="width: 300px"><input type="text" data-date-format="dd-mm-yyyy" id="dp2" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda Varios:</th>						
						<th style="width: 100px">Sponsor: *</th>
						<th style="width: 300px"><select id="sucursal_canal" data-placeholder="Seleccione una opción..."></select></th>
						<th style="width: 100px">Canal:</th>
						<th style="width: 300px"><select id="punto_venta_session" data-placeholder="Seleccione una opción..." ></select>
						</th>
					</tr>
					<tr>
						<th style="width: 180px">&nbsp;</th>
						<th style="width: 100px">Estado: </th>
						<th style="width: 300px"> <select id="estado" data-placeholder="Seleccione una opción..."></select></th>
						<th style="width: 100px">Agente:</th>
						<th style="width: 300px"><select id="agente_session" data-placeholder="Seleccione una opción..." required="required"></select></th>
					</tr>
					<tr>
						<th style="width: 180px">&nbsp;</th>
						<th style="width: 100px">Cliente Identificación:</th>
						<th style="width: 300px"><input type="text" id="identificacion"  style="width: 100%"></th>
						<th style="width: 100px">Cliente Apellidos:</th>
						<th style="width: 300px"><input type="text" id="Apellidos_Cliente" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">&nbsp;</th>
						<th style="width:100px">Mis Cotizaciones:</th>
						<th style="width: 300px"><input type="checkbox"
							id="mis_cotizaciones"></th>
						<th style="width:100px">&nbsp;</th>
						<th style="width: 300px">&nbsp;</th>						
					</tr>					
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
							<button  class="btn btn-primary" id="btnConsultaMasiva" >Consulta Masiva</button>
						</th>						
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	
	<div class="row" style="display: inline;" id="content-Upload">
		<div class="col-lg-12">
			<div class="table-responsive">
			<table width="80%">
					<tr >
							<td colspan="2" >PROCESOS MASIVOS</td>
						</tr>
					<tr>
						<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
					        <thead>
					        <tr >
					        <th colspan="4">
					        	<b>TABLA EXCEL CONSULTA MASIVAS</b>
					        </th>
					        </tr>
					        <tr>
					        	<th WIDTH="250">Id Cotización ó Número de trámite</th>					        	   	
					        </tr>
					        </thead>
					        <tbody> 
					        	<tr  class="danger">
					        		<td>####</td>
					           	</tr>
					        </tbody>
					    </table>
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
	
	<div id="descargarAlternativa1">
		<input type="button" id="descargarAlternativa" value="descarga alternativa"></input> - (Consultas superiores a 5000 cotizaciones)	
	</div>
	<div id="grid">
		
	</div>
	
	<!-- Table -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">				
				<div id="grid">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
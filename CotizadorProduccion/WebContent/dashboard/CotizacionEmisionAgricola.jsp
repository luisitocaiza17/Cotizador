<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-control" content="no-cache">
	<title>Emision Cotizaciones - CotizadorQBE</title>
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
	<script src="../static/js/util.js"></script>
	<script src="../static/js/cotizador/comun.js"></script>

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



<script>
	var estadoConsulta="CPE";//GetQueryStringByParameter('State');
	var codigo = "";
	var tipoConsulta = "";
	var nombreArchivo = "";
	var CadenaCotizaciones = new Array();
	var PuntosVentaList = "";
	$(document).ready(function() {
			activarMenu(this);
			$("#content-Upload").hide();
			//$('#btnCargaMasiva').hide();
			$("#loading").hide();
			$('#dataTable').hide();
			$('#btnImportar').hide();
			$("#content-cargaMasiva").hide();
			//cargarPuntosVentaSessionGanadero();
			//Cargar combo 
			CargarComboAgente();
			obtenerCanal();
			CargarCombo();
			
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
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
	    	 	$("#punto_venta_session").val("");	    	 	
	    	 	$("#agente_session").val("");
	    	 	$("#sucursal_canal").val("");
	    	 	$("#identificacion").val("");
	    	 	$("#Apellidos_Cliente").val("");
	    	 	$("#nro_tramite").val("");
	    	 	//$("#mis_cotizaciones").prop( "checked", false );
	    	 	/* $('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide(); */
	    	 	if ($('#grid').data().kendoGrid){
	    			$('#grid').data().kendoGrid.destroy();
	    			$('#grid').empty();
	    		}
	 			$('#grid').hide();
	 			
	 			$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			//$('#dataTable_wrapper').hide();
	 			$("#content-cargaMasiva").hide();
	 			//$("#datos_temporal").val("");
	 			nombreArchivo="";
	 			CadenaCotizaciones = new Array();
	 			var up = $('#files').data().kendoUpload;
	 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
	 			up._removeFileEntry(allLiElementsToBeRemoved );
	 			$('#btnImportar').hide();
	 			$("#content-Upload").hide();
	 			PuntosVentaList.value("");
	    	 });
			//Consulta 
			$('#ConsultaBtn').click(function(){
				$('#grid').show();
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
			
			///TODO: al seleccionar el combo canal
			$("#sucursal_canal").change(function(){	
				$("#punto_venta_session").children().remove();
				$("select option:selected").each(function(){
					CargarCombo();
				});
			});
			//Boton Carga Masiva
			$('#btnCargaMasiva').click(function(){
				/* $('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide(); */
				if ($('#grid').data().kendoGrid){
					$('#grid').data().kendoGrid.destroy();
					$('#grid').empty();
				}
	 			$('#grid').hide();
	 			$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			$("#content-cargaMasiva").hide();
				$("#content-Upload").show();
	    	 });
	});	
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
				var listadoCanal = data.ListadoCanal;
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
	function CargarCombo() {
		var listPuntoVenta="";
		var puntoId = $("#sucursal_canal option:selected").val() ? $(
		"#sucursal_canal option:selected").val() : "";
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
			}
		});
		}
	}

	function Cargar() {
		
		// Validaciones de las busquedas
		
		var cotizacionId = $("#cotizacion_id").val();
		cotizacionId = $.trim(cotizacionId);
		var NroTramite = $.trim($("#nro_tramite").val());
		var identificacion = $.trim($("#identificacion").val());
		var ApellidosCliente = $("#Apellidos_Cliente").val();
		var fechaInicio = $("#dp1").val();
		var fechaFin = $("#dp2").val();
		//var puntoVenta = $("#punto_venta_session").val();
		var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
				"#punto_venta_session option:selected").val() : "";
		var canalId = $("#sucursal_canal option:selected").val() ? $(
				"#sucursal_canal option:selected").val() : "";
				
		var agente = $("#agente_session option:selected").val() ? $(
		"#agente_session option:selected").val() : "";
		
		
		if((cotizacionId=="" || typeof cotizacionId === "undefined")&& (fechaInicio=="" || typeof fechaInicio === "undefined") && (fechaFin=="" || typeof fechaFin === "undefined") && (puntoVenta=="" || typeof puntoVenta === "undefined") && (agente=="" || typeof agente === "undefined") && (identificacion=="" || typeof identificacion === "undefined") && (NroTramite=="" || typeof NroTramite === "undefined") && (ApellidosCliente=="" || typeof ApellidosCliente === "undefined") && (canalId=="" || typeof canalId === "undefined")){
			alert("Ingrese al menos un campo de busqueda");
			return false;
		}

		if (fechaInicio != "" && fechaFin == "") {
			alert("Ingrese una fecha Fin de Busqueda");
			
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
			{ field: "id", title: "Id Cotización", width: "60px"},
			{ field: "idCotizacion2", title: "Id Agricola.", width: "60px"},
			{ field: "Ciclo", title: "Periodo" , width: "160px"},
			{ field: "FechaElaboracion", title: "Fecha Elaboración", type:"date",width: "100px",headerAttributes: { style: "white-space: normal"}, hidden: true, exportar: true},
			{ field: "IdentificacionCliente", title: "Identificación Cliente", width: "90px",headerAttributes: { style: "white-space: normal"}},
			{ field: "NombresCliente", title: "Cliente", width: "100px",headerAttributes: { style: "white-space: normal"}},
			{ field: "CanalNombre", title: "Sponsor", width: "80px"},
			{ field: "PuntoVenta", title: "Canal", width: "120px"},
			{ field: "NumeroTramite", title: "Nro. Trámite", width: "80px",headerAttributes: { style: "white-space: normal"}},
			{ field: "VigenciaDias", title: "Vigencia en Dias", width: "120px"},
			{ field: "fechaVigenciaDesde", title: "Vigencia Desde", type:"date", width: "120px", hidden: true, exportar: true},
			{ field: "fechaVigenciaHasta", title: "Vigencia Hasta", type:"date", width: "120px", hidden: true, exportar: true},
			{ field: "tipoCultivoNombre", title: "Cultivo", width: "100px"},
			{ field: "provinciaNombre", title: "Provincia", width: "80px"},
			{ field: "cantonNombre", title: "Cantón", width: "100px"},
			{ field: "parroquiaNombre", title: "Parroquia", width: "100px"},
			{ field: "DireccionSiembra", title: "Sitio Inversión", width: "100px"},
			{ field: "Telefono", title: "Telefono", width: "100px"},
			{ field: "hectareasAsegurables", title: "Has. Aseg", width: "50px",headerAttributes: { style: "white-space: normal"}},
			{ field: "sumaAseguradaTotal", title: "Monto Asegurado", width: "70px",headerAttributes: { style: "white-space: normal"}},
			{ field: "fechaSiembra", title: "Fecha Siembra", type:"date", format:"{0:dd/MM/yyyy}", width: "100px",headerAttributes: { style: "white-space: normal"}},
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
			{ field: "ObservacionRegla", title: "Observación", width: "160px"},
			{ field: "EstadoCotizacion", title: "Estado", width: "100px"}, 
			{ field: "Confirmacion", title: "Confirmación Canal", width: "120px", attributes:{style:"color: red;"}},
			{ field: "usuarioCotizador", title: "Usuario Offline", width: "80px", hidden: true, exportar: true},
			{field:  "id",width: "130px", headerTemplate: "<button type='button' name='emitir-btn' id='emitir-btn' class='btn btn-success btn-xs emitir-btn' onclick='EmitirVarios()'><span class='glyphicon glyphicon glyphicon-edit' id='emitir-record' ></span> Emitir Selección</button><br> &nbsp;<br><button type='button' name='revocar-btn' id='revocar-btn' class='btn btn-danger btn-xs revocar-btn' onclick='RevocarVarios()'><span class='glyphicon glyphicon glyphicon' id='revocar-record' ></span> Revocar Selección</button>" ,
			template: "<input type='checkbox' id='#=id#'  class='checkbox' onclick=\"MarcarCheck('#=id#')\"/>"  },
			{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " ", width: "100px"}],
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
            }
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
	function fnEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        //alert("Cotización id:"+dataItem.codigo);
        $('#add').modal('show');
        actualizar(dataItem.id);
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
	function EnviarAprobacion(Cotizacion){
		//ReglaId = Cotizacion;
		tipoConsulta = "Emitir";
		$.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				"Cotizaciones" : Cotizacion,
				"tipoConsulta" : tipoConsulta
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#cargando").fadeOut("slow");
				Cargar();
			}
		});
	}
	function AprobarCotizacion(Cotizacion) {
		var r = confirm("Seguro que desea emitir la cotización?");
		if (r == true) {
			$("#cargando").fadeIn("slow");
			EnviarAprobacion(Cotizacion);
			
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
			if ($('#grid').data().kendoGrid){
				$('#grid').data().kendoGrid.destroy();
				$('#grid').empty();
			}
 			$('#grid').hide();						
 			var tableLimpiar = $('#dataTableMasiva').DataTable();
			 
 			tableLimpiar
 			    .clear()
 			    .draw();
 			
			 $.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"tipoConsulta" : "importar",
					"FileName" : nombreArchivo,
					"actividad": "emitir"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").hide();
					//alert(data.mensaje);
					var listDetalle = data.listDetalle;
					var IdFile = data.IdFile;
     				if (typeof IdFile!= 'undefined')
     					$("#idFile").val(IdFile);
					var estado=0;
					if (typeof listDetalle != 'undefined') {
						$('#datatableError').html('');
						$("#content-cargaMasiva").show();
						$('#dataTableMasiva').show();
						
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableMasiva').dataTable();
						oTable.fnDestroy();
						
						$.each(listDetalle, function(index) {
							if (listDetalle[index].estado=="OK")
								estado++;
							$("#datatableError").append(
									"<tr class='odd gradeX'>" + "<td>"
											+ listDetalle[index].cotizacion
											+ "</td>" + "<td>"
											+ listDetalle[index].detalle
											+ "</td>" + "</tr>");
						});
						$("#content-Upload").hide();
						$('#dataTableMasiva')
						.dataTable(
								{
									"pagingType" : "full",
									"bFilter" : false,
									"bLengthChange" : false,
									"bSort" : false,
									"scrollX": true, 
									"iDisplayLength" : 10, // Limitamos el numero de filas en la paginacion
									"dom" : 'T<"clear">lfrtip',
									"tableTools" : {
										"sSwfPath" : "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
									},
									"fnDrawCallback" : ""
								});
					}
					if (estado>0)
						$('#imprimir-btn').show();
					else 
						$('#imprimir-btn').hide();
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
	function EmitirVarios() {
		
         var selected = '';
         if (CadenaCotizaciones.length>0){
        	 var r = confirm("Seguro que desea emitir las cotizaciones?");
     		if (r == true) {
        	 $("#cargando").fadeIn("slow");
        	 tipoConsulta = "Emitir";
        	 $.each(CadenaCotizaciones,function(index) {
        		 selected = selected +CadenaCotizaciones[index]+",";
        	 });
        	 
     		$.ajax({
     			url : '../AgriCotizacionAprobacionController',
     			data : {
     				"tipoConsulta" : tipoConsulta,
     				"Cotizaciones":selected
     			},
     			type : 'POST',
     			datatype : 'json',
     			success : function(data) {
     				$("#cargando").fadeOut("slow");
     				var listDetalle = data.listDetalle;
     				var IdFile = data.IdFile;
     				if (typeof IdFile!= 'undefined')
     					$("#idFile").val(IdFile);
     				var estado=0;
					if (typeof listDetalle != 'undefined') {
						/* $('#dataTable').hide();
			 			$('#dataTableContent').html('');
			 			$('#dataTable_wrapper').hide(); */
			 			//$("#grid").data("kendoGrid").dataSource.empty();
						if ($('#grid').data().kendoGrid){
							$('#grid').data().kendoGrid.destroy();
							$('#grid').empty();
						}
			 			$('#grid').hide();
						$('#datatableError').html('');
						$("#content-cargaMasiva").show();
						$('#dataTableMasiva').show();
						$.each(listDetalle, function(index) {
							if (listDetalle[index].estado=="OK")
								estado++;
							$("#datatableError").append(
									"<tr class='odd gradeX'>" + "<td>"
											+ listDetalle[index].cotizacion
											+ "</td>" + "<td>"
											+ listDetalle[index].detalle
											+ "</td>" + "</tr>");
						});
						if (estado>0)
							$('#imprimir-btn').show();
						else 
							$('#imprimir-btn').hide();
					}
     				CadenaCotizaciones = new Array();
     				//Cargar();
     			}
     		});
     		}
         }
         else {
        	 alert("Debe seleccionar almenos una cotizacion para poder emitir");
         }
	}
	function RevocarVarios() {
		
        var selected = '';
        if (CadenaCotizaciones.length>0){
       	 var r = confirm("Seguro que desea revocar las cotizaciones?");
    		if (r == true) {
       	 $("#cargando").fadeIn("slow");
       	 tipoConsulta = "Revocar";
       	 $.each(CadenaCotizaciones,function(index) {
       		 selected = selected +CadenaCotizaciones[index]+",";
       	 });
       	 
    		$.ajax({
    			url : '../AgriCotizacionAprobacionController',
    			data : {
    				"tipoConsulta" : tipoConsulta,
    				"Cotizaciones":selected
    			},
    			type : 'POST',
    			datatype : 'json',
    			success : function(data) {
    				$("#cargando").fadeOut("slow");
    				var listDetalle = data.listDetalle;
					if (typeof listDetalle != 'undefined') {
						$('#dataTable').hide();
			 			$('#dataTableContent').html('');
			 			$('#dataTable_wrapper').hide();
			 			
			 			/* $("#grid").data('kendoGrid').dataSource.data([]); */
			 			if ($('#grid').data().kendoGrid){
			 				$('#grid').data().kendoGrid.destroy();
			 				$('#grid').empty();
			 			}
			 			$('#grid').hide();
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
						
					}
    				CadenaCotizaciones = new Array();
    				//Cargar();
    			}
    		});
    		}
        }
        else {
       	 alert("Debe seleccionar almenos una cotizacion para poder revocar");
        }
	}
	function MarcarCheck(id) {
        var id_ = "#"+id;
		var marcado = $(id_).prop("checked") ? true : false; 
		if (marcado==true)
			CadenaCotizaciones.push(id);
		else {
				if (CadenaCotizaciones.length > 0) {
					var elemt = CadenaCotizaciones.indexOf(id);
					CadenaCotizaciones.splice(elemt, 1);
				}
		}

	}
	$("#enviar-record").click(function(){
		importar();
	});
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

		RecalSeguroCampesino=0;
		RecalSuperBancos=0;
		RecalDerechoEmision = 0;
		//AniosCultivo = 0;
		$.ajax({
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
						//($("#HectareasAsegurables").data("kendoNumericTextBox")).value(data.hectareas_asegurables);
						($("#Iva").data("kendoNumericTextBox")).value(data.totalImpuestos);
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
						$('#Confirmacion').html(data.confirmacion);
						$('#ClienteDatos').html(data.nombres_cliente);
						//$('#AgenteDatos').html(data.nombresAgente);
						$('#UsuarioDatos').html(data.nombresUsuario);
						$('#Longitud').html(data.longitud);
						$('#Latitud').html(data.latitud);
						$('#Observacion').html(data.observacion);
						$('#ClienteIdentificacion').html(data.ClienteIdentificacion);
						$('#DisponeRiego').html(data.DisponeRiego);
						$('#DisponeAsistencia').html(data.DisponeAsistencia);
						$('#AgricultorTecnificado').html(data.AgricultorTecnificado);
						($("#Tasa").data("kendoNumericTextBox")).value(data.tasa);
						
				($("#PrimaNetaTotal").data("kendoNumericTextBox")).enable(false);
				($("#Iva").data("kendoNumericTextBox")).enable(false);
				($("#TotalFactura").data("kendoNumericTextBox")).enable(false);
				($("#Tasa").data("kendoNumericTextBox")).enable(false);
				($("#SumaAsegurada").data("kendoNumericTextBox")).enable(false);
			}
		});
	}
	function MostrarArhivo(){
		var idFile = $("#idFile").val();
		window.open('../AgriCotizacionReporteIDsFactura?tipoConsulta=MostrarArchivo&idFile=' + idFile);
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
<!-- ventana detalle -->
<div class="modal fade"  id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Detalle de Cotización</h4>
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

													<td style="color: blue;">Confirmación Canal:</td>
													<td>
														<div id="Confirmacion" style="color: red;"></div>
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
													<td style="color: blue;">Monto Recomendado:</td>
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
						<div class="panel-heading">Datos Valores</div>
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
													<td width="210px">Prima Neta :</td>
													<td width="190px"><input type="text"
														class="text_Currency" name="PrimaNetaTotal"
														id="PrimaNetaTotal" validationMessage="Campo requerido!!!"
														required> </input></td>
													<td>Impuestos</td>
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

													<td>Prima Bruta: </td>
													<td>&nbsp;</td>
													<td><input type="text" class="text_Currency"
														name="TotalFactura" id="TotalFactura"
														validationMessage="Campo requerido!!!" required> </input>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<!-- <tr>
										<td>
											<table>
												<tr>
													<td width="250px">Comentario: <textarea style="width: 400px" id="Comentario"></textarea>
													</td>
													<td><input style="width: 364px" type="text"
														id="Comentario"></td>
												</tr>
											</table>
										</td>
									</tr> -->
								</table>
							</div>
						</div>
						</div>
						<!-- <div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button> 
							<button type="button" class="btn btn-primary" id="aprobar-record">Aprobar</button>
							<button type="button" class="btn btn-primary" id="rechazar-record">Rechazar</button>
						</div> -->
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
						<td colspan="6" style="font-weight: bold;" align="center">Buscador
								Cotizaciones Pendientes por Emitir</td>

					</tr>
					<tr>
						<th style="width:180px">Busqueda por códigos:</th>
						<th style="width: 100px">Cotización: </th>
						<th style="width:300px"> <input type="text" id="cotizacion_id"
							 style="width: 100%"></th>
							<th style="width:100px">Nro. Trámite: </th>
							<th style="width: 300px"><input type="text" id="nro_tramite" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width:180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creación Desde:</th>
						<th style="width:300px">  <input type="text"
							data-date-format="dd-mm-yyyy" id="dp1" style="width: 100%"></th>
						<th style="width:100px">Fecha Creación Hasta:</th>
						<th style="width: 300px">  <input type="text"
							data-date-format="dd-mm-yyyy" id="dp2" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width:180px">Busqueda Varios:</th>
						<!--  <th style="width:100px">Mis Cotizaciones:</th>
						<th ><input type="checkbox"
							id="mis_cotizaciones"></th>-->
							<th style="width: 100px">Sponsor:</th>
							<th style="width: 300px">
							<select id="sucursal_canal" data-placeholder="Seleccione el canal" style="width: 100%"></select> 
						</th>
							<th style="width:100px">Canal: </th>
						<th style="width: 300px"><select id="punto_venta_session" data-placeholder="Seleccione el punto venta" style="width: 100%"></select></th>
						
					</tr>
					<tr>
						<th style="width:180px">&nbsp;</th>
						<th style="width:100px">&nbsp;</th>
						<th style="width: 300px"></th>
						<th style="width: 100px">Agente:</th>
						<th style="width: 300px"><select id="agente_session" data-placeholder="Seleccione el agente" style="width: 100%"></select></th>
					</tr>
					<tr>
						<th style="width: 180px">&nbsp;</th>
						<th style="width:100px">Cliente Identificación: </th>
						<th style="width: 300px" ><input type="text" 
							id="identificacion" style="width: 100%"></th>
							<th style="width: 100px">Cliente Apellidos:</th>
							<th style="width: 300px">
							<input type="text"
							id="Apellidos_Cliente" style="width: 100%"></th>
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
					
						<th align="right">
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>
							</th>
						<th>							
						<!--  </th>
						<th>-->
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
							</th>
						<th>
							<button  class="btn btn-primary" id="btnCargaMasiva" >Emisión Masiva</button>
							

						</th>
						<th>
						</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<!-- <th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th> -->
						<th><div id="cargando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg_"></span><img
										src="../static/images/ajax-loader.gif" /> Procesando la
									informacion, por favor espere...
								</div>
							</div></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- Button trigger modal -->

	</div>

<!-- Table -->

<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<div id="grid"></div>
			</div>
		</div>
	</div>

<!-- Table -->
<!-- <div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTable" style="font-size: x-small;">
					<thead>
						<tr>
							<th># Cot.</th>
							<th>Nro. Trámite</th>
							<th>Canal</th>
							<th>Punto Venta</th>
							<th>Identificación</th>
							<th>Nombres Cliente</th>
							<th>Cultivo</th>
							<th>Variedad</th>
							<th>Provincia</th>
							<th>Canton</th>
							<th>Parroquia</th>
							<th>Sitio</th>
							<th>Fecha Siembra</th>
							<th>Prima Neta Total</th>
							<th>Nro. Hec</th>
							<th>Suma Asegurada Total</th>
							<th>Seguro Bancos</th>
							<th>Seguro Campesino</th>
							<th>Derecho Emision</th>
							<th>IVA</th>
							<th>Total Factura</th>
							<th >
							<button type="button" name="emitir-btn" id="emitir-btn" class="btn btn-success btn-xs emitir-btn" onclick="EmitirVarios()">
							<span class='glyphicon glyphicon glyphicon-edit' id='emitir-record' ></span> Emitir Selección
							</button>
							<br> &nbsp;<br>
							<button type="button" name="revocar-btn" id="revocar-btn" class="btn btn-danger btn-xs revocar-btn" onclick="RevocarVarios()">
							<span class='glyphicon glyphicon glyphicon' id='revocar-record' ></span> Revocar Selección
							</button> 
							</th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div> -->

	<!-- Tabla para la carga masiva -->
	<!-- <div id="gridMasiva"></div> -->
	<div class="row" style="display: inline;" id="content-Upload">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table style="width: 80%">
					<!--<tr>
										<td >
										<table>-->
					<tr >
						<td  colspan="2"><strong>EMISIÓN MASIVA:</strong> Seleccione un archivo para la carga masiva.</td>
					</tr>
					<tr>
						<td colspan="2"> Si las cotizaciones se Emiten luego del 1 de junio del 2017, se emitirán con 12% de iva. las cotizaciones se recalcularan para consistencia de información. </td>
					</tr>
					<tr >
						<td  colspan="2"><input name="files" id="files"
							type="file" /></td>
						<div id="loading">
							<div class="loading-indicator">
								<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
									id="loading-msg">Procesando, espere por favor...</span>
							</div>
						</div>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr align="right">
						<td colspan="2">
							<button id="btnImportar" class="btn btn-primary"
								onclick="importar();">Subir Archivo</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
		<!-- Tabla con respuesta de aprovacion masiva -->
	<div class="row" style="display: none;" id="content-cargaMasiva">
		<div class="col-lg-12">
			<div class="table-responsive">
			<h3>Detalle de Cotizaciones procesadas</strong>.</h3>
			<input type="hidden" id="idFile"  value=""/>"
			<button type="button" name="imprimir-btn" id="imprimir-btn" class="btn btn-primary" onclick="MostrarArhivo()">
							<span class='glyphicon' id='imprimir-record' ></span> Mostrar Archivo IDs
							</button>
				<table class="table table-striped table-bordered table-hover"
					id="dataTableMasiva">
					<thead>
						<tr >
							<th>
							# COTIZACIÓN</th>
							<th>COMENTARIO
							</th>
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
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
		var estadoConsulta="";//GetQueryStringByParameter('State');
		var codigo = "";
		var tipoConsulta = "";
		var Reglas = "";
		
		var estadoList = "";
		var canalList = "";
		var puntoVentaList = "";
		var agenteList = "";
		
		$(document).ready(function() {
			
				activarMenu("SeguimientoTramites");
				
				$("#cotizacionTitulo").hide();
	    	 	$("#detalleTitulo").hide();
				$("#loading").hide();
				
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
				
	    	 	$("#ConsultaBtn").click(function(){
	    	 		cargaPrincipal();
	    	 		$("#cotizacionTitulo").show();
		    	 	$("#detalleTitulo").show();
	    	 		$('#grid').show();
	    	 		$('#grid2').show();
	    	 	});			
							
	    	 	
				$('#LimpiarBtn').click(function(){	    	 	
		    	 	$("#nombre").val("");
		    	 	$("#cotizacion_id").val("");
		    	 	$("#nro_tramite").val("");
		    	 	$("#identificacion").val("");
		    	 	$("#dp1").val("");
		    	 	$("#dp2").val("");
		    	 	$("#punto_venta_session").val("");
		    	 	$("#sucursal_canal").val("");
		    	 	$("#cotizacionTitulo").hide();
		    	 	$("#detalleTitulo").hide();
		    	 	
		    	 	if ($('#grid').data().kendoGrid){
		    			$('#grid').data().kendoGrid.destroy();
		    			$('#grid').empty();
		    		}
		 			$('#grid').hide();
		 			
		 			if ($('#grid2').data().kendoGrid){
		    			$('#grid2').data().kendoGrid.destroy();
		    			$('#grid2').empty();
		    		}
		 			$('#grid2').hide();
		    	 });
				
				$("#punto_venta_session").kendoMultiSelect({
					dataTextField : "nombre",
					dataValueField : "puntoVentaId",
					animation : false,
					maxSelectedItems : 1
				}); 
			    
				PuntosVentaList = $("#punto_venta_session").data(
				"kendoMultiSelect");
				
				obtenerCanal();
				
				///TODO: al seleccionar el combo canal
				$("#sucursal_canal").change(function(){	
					$("#punto_venta_session").children().remove();
					$("select option:selected").each(function(){
						CargarCombo();
					});
				});
				
				$("#save-record").bind({click: function(){
					$("#msgPopup").hide();
					var validator = $("#formCrud").kendoValidator().data("kendoValidator"); 
					$(".required").css("border", "1px solid #ccc");
					 if (validator.validate() === false) {     
						$(".required").each(function(index) {
								var cadena = $(this).val();
								if (cadena.length <= 0) {
									$(this).css("border", "1px solid red");
									alert("Por favor ingrese el campo requerido");
									$(this).focus();
									return false;
								}		
							});
				      }else{
				    	  	identificadorCot = $("#variableId").val();
							tipoConsulta = "editar";
							
							enviarDatos(tipoConsulta);
				      }
					 cargaPrincipal();
					 
					}
				});
				
				
		});
		
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
		
		function CargarCombo(){
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
		
		function enviarDatos(tipoConsulta){
			
			 var nombreV=$("#nombreV").val();
			 var valor=$("#valor").val();
			 var variableId=$("#variableId").val();
			 
			$.ajax({
				url:'../AgriVariableController',
				data : {
					"nombreV" : nombreV,
					"valor" : valor,
					"variableId" : variableId,
					"tipoConsulta" : tipoConsulta
				},
				type : 'POST',
				async : false,
				datatype : 'json',
				success : function(data){
					var exito= data.success;
					if(exito=="true" || exito ===true){
						$("#msgPopup").show();
					}else{
						alert("Se produjo un error al guardar el usuario");
					}
				}
			});
		}
		
		function cargaPrincipal(){
			var cotizacionId = $("#cotizacion_id").val();
			cotizacionId = $.trim(cotizacionId);
			var NroTramite = $("#nro_tramite").val();
			var identificacion = $("#identificacion").val();
			var fechaInicio = $("#dp1").val();
			var fechaFin = $("#dp2").val();
			var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
					"#punto_venta_session option:selected").val() : "";
			var canalId = $("#sucursal_canal option:selected").val() ? $(
					"#sucursal_canal option:selected").val() : "";
				
			
			if ($('#grid2').data().kendoGrid){
				$('#grid2').data().kendoGrid.destroy();
				$('#grid2').empty();
			}
			
			$("#grid2").kendoGrid({
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
	                		url: "../SeguimientoTramiteController",
	                		data: {
	    						"fInicio" : fechaInicio,
	    						"fFinal" : fechaFin,
	    						"numeroCotizacion" : cotizacionId,
	    						"puntoVenta" : puntoVenta,
	    						"NroTramite":NroTramite,
	    						"identificacion":identificacion,
	    						"CanalId":canalId,
	    						"tipoConsulta" : "encontrarCotizacion",
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
						  { field: "FechaElaboracion", type:"date", title: "Fecha Elaboración", width: "100px",headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
						  { field: "IdentificacionCliente", title: "Identificación Cliente", width: "90px",headerAttributes: { style: "white-space: normal"}},
					      { field: "NombresCliente", title: "Cliente", width: "100px",headerAttributes: { style: "white-space: normal"}},
					      { field: "CanalNombre", title: "Sponsor", width: "80px"},
					      { field: "PuntoVenta", title: "Canal", width: "120px"},
					      { field: "NumeroTramite", title: "Nro. Trámite", width: "80px",headerAttributes: { style: "white-space: normal"}},
					      { field: "VigenciaDias", title: "Vigencia en Dias", width: "120px"},
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
					      { field: "EstadoCotizacion", title: "Estado", width: "100px", hidden: true, exportar: true}, 
					      { field: "usuarioCotizador", title: "Usuario Offline", width: "80px", hidden: true, exportar: true},
					      { field: "EstadoCotizacion", title: "Estado", width: "100px"},
						  { field: "polizaNumero", title: "Poliza Numero", width: "100px"},
						  { field: "polizaFechaVencimiento", title: "Poliza Fecha", width: "100px"}
	      				],
	      				height: 300,
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
			
			Cargar();
			var exportFlag=false;
			$("#grid2").data("kendoGrid").bind("excelExport", function (e) {
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
		
		
		function Cargar() {	
			
			var cotizacionId = $("#cotizacion_id").val();
			cotizacionId = $.trim(cotizacionId);
			var NroTramite = $("#nro_tramite").val();
			var identificacion = $("#identificacion").val();
			var fechaInicio = $("#dp1").val();
			var fechaFin = $("#dp2").val();
			var puntoVenta = $("#punto_venta_session option:selected").val() ? $(
					"#punto_venta_session option:selected").val() : "";
			var canalId = $("#sucursal_canal option:selected").val() ? $(
					"#sucursal_canal option:selected").val() : "";
				
			
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
	                		url: "../SeguimientoTramiteController",
	                		data: {
	    						"fInicio" : fechaInicio,
	    						"fFinal" : fechaFin,
	    						"numeroCotizacion" : cotizacionId,
	    						"puntoVenta" : puntoVenta,
	    						"NroTramite":NroTramite,
	    						"identificacion":identificacion,
	    						"CanalId":canalId,
	    						"tipoConsulta" : "encontrar",
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
						{ field: "cotizacionId", title: "Cotizacion Id.", width: "80px",headerAttributes: { style: "white-space: normal"}},
						{ field: "Id2", title: "ID agricola.", width: "80px",headerAttributes: { style: "white-space: normal"}},
						{ field: "fechaCreacion", title: "Fecha Elaboracion.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "periodo", title: "Ciclo.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "cedulaCliente", title: "Cedula", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "cliente", title: "Cliente.", width: "200px",headerAttributes: { style: "white-space: normal"}},
						{ field: "canal", title: "Canal.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "puntoVenta", title: "Punto de Venta", width: "200px",headerAttributes: { style: "white-space: normal"}},
						{ field: "numeroTramite", title: "Numero Tramite", width: "200px",headerAttributes: { style: "white-space: normal"}},
						{ field: "diasVigencia", title: "Dias Vigencia", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "vigenciaDesde", title: "vigencia Desde.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "vigenciaHasta", title: "vigencia Hasta.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "cultivo", title: "Cultivo", width: "200px",headerAttributes: { style: "white-space: normal"}},
						{ field: "provincia", title: "Provincia.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "canton", title: "Canton.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "parroquia", title: "Parroquia.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "sitioInvesion", title: "sitio Invesion", width: "300px",headerAttributes: { style: "white-space: normal"}},
						{ field: "telefono", title: "telefono.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "hectareasAseguradas", title: "Hectareas Aseguradas", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "hectareasLote", title: "Hectareas Lote", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "fechaSiembra", title: "Fecha Siembra.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "montoAsegurado", title: "Monto Asegurado.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "costroProduccion", title: "Costro Produccion.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "tasa", title: "Tasa", width: "100px",headerAttributes: { style: "white-space: normal"}},	      				
						{ field: "superBancos", title: "Super Bancos.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "derechoEmision", title: "Derecho Emision", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "iva", title: "Iva.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "primaNeta", title: "prima Neta", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "primaTotal", title: "prima Total", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "latitud", title: "latitud.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "longitud", title: "longitud.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "disponeRiego", title: "Dispone Riego.", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "disponeAsistencia", title: "Dispone Asistencia", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "agricultoTecnificado", title: "Agriculto Tecnificado", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "observacion", title: "Observacion.", width: "200px",headerAttributes: { style: "white-space: normal"}},
						{ field: "observacionQBE", title: "ObservacionQBE.", width: "200px",headerAttributes: { style: "white-space: normal"}},
						
	      				],
	      				height:300,
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
		
		function actualizar(id){
			$.ajax({
				url:'../AgriVariableController',
				data:{
					"id" : id,
					"tipoConsulta" : "obtenerPorId"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					$("#variableId").val(data.id);
					$("#nombreV").val(data.nombre);
					$("#valor").val(data.valor);					
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

	<!-- ventana consulta -->
	<div class="row crud-nav-bar">	
	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador
								Cotizaciones Pendientes por Aprobar</td>

					</tr>
					<tr>
						<th style="width: 180px">Busqueda por códigos:</th>
						<th style="width: 100px">Cotización: </th>
						<th style="width: 300px"> <input type="text" id="cotizacion_id"
							style="width: 100%"></th>
						<th style="width: 100px">Nro. Trámite: </th>
						<th style="width: 300px"><input type="text" id="nro_tramite" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creación Desde:</th>
						<th style="width: 300px">  <input type="text"
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
							<th style="width: 100px">Canal: </th>
							<th style="width: 300px"><select id="punto_venta_session" data-placeholder="Seleccione el punto venta" style="width: 100%"></select></th>						
					</tr>
					
					<tr>
						<th style="width: 180px">&nbsp;</th>
						<th style="width: 100px">Cliente Identificación: </th>
						<th style="width: 300px"><input type="text" 
							id="identificacion" style="width: 100%"></th>
							
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
						<th>&nbsp;</th>
						<!-- <th>
						<div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div>
							</th> -->
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
	
	
	<H3 id="cotizacionTitulo"> &nbsp; &nbsp;  COTIZACIONES</H3>
	<div id="grid2">
		
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<H3 id="detalleTitulo"> &nbsp; &nbsp;  DETALLES DE MOVIMIENTO DE COTIZACIONES</H3>
	<div id="grid">
		
	</div>
	<br>
	<br>
	<br>
	<br>
	<p>fin de busqueda</p>
	<br>
	
	<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Variables del Sistema</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El la variable se actualizo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="variableId">										
								<label>Variable</label>
									<input type="text" class="form-control required" id="nombreV" validationMessage="Campo requerido!!!" required>
								
								<label>Nombre</label>
									<input type="text" class="form-control required" id="valor" validationMessage="Campo requerido!!!" required>
								<br />																						
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
</body>
</html>
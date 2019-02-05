<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Cotizaciones Pymes Resumen- CotizadorQBE</title>
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
	
	var estadoList = "";
	var canalList = "";
	var puntoVentaList = "";
	var agenteList = "";
	
	$(document).ready(function() {
			activarMenu("ResumenCotizacionesPymes");
			
			$("#loading").hide();
			
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
			
			/* $("#punto_venta_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "puntoVentaId",
				animation : false,
				maxSelectedItems : 1
			});
			
			puntoVentaList = $("#punto_venta_session").data("kendoMultiSelect"); */
			
			$("#agente_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "id",
				animation : false,
				maxSelectedItems : 1
			});
			
    	 	agenteList = $("#agente_session").data("kendoMultiSelect");    	 
			
    	 	$("#ConsultaBtn").click(function(){
    	 		Cargar();
    	 	});
    	 	
			CargarComboAgente();
			CargarComboEstado();	
			cargarPuntosVentaSessionPymes();
			
			$("#sucursal_canal").change(function(){
				if($("#sucursal_canal option:selected").val()!="" && typeof $("#sucursal_canal option:selected").val() != "undefined")
					CargarCombo($("#sucursal_canal option:selected").val());
			});		
						
			$('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#cotizacion_id").val("");
	    	 	$("#nro_tramite").val("");
	    	 	$("#tipo_objeto_id").val("0");
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
				estadoList.value("");
	    	 	//puntoVentaList.value("");
	    	 	agenteList.value("");
	    	 	//canalList.value("");
	    	 	$("#identificacion").val("");
	    	 	$("#Apellidos_Cliente").val("");
	    	 	$("#mis_cotizaciones").attr('checked', false);	
	 			if ($('#grid').data().kendoGrid){
	    			$('#grid').data().kendoGrid.destroy();
	    			$('#grid').empty();
	    		}
	 			$('#grid').hide();
	    	 });
			
	});
	
	function CargarComboEstado(){
		$.ajax({
			url : '../PymeCotizacionReporteController',
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
	
	function Cargar() {	
		$('#dataTable_wrapper').hide();
		$('#grid').show();
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
		/* var canalId = $("#sucursal_canal option:selected").val() ? $(
				"#sucursal_canal option:selected").val() : ""; */
				
		var agente = $("#agente_session option:selected").val() ? $(
		"#agente_session option:selected").val() : "";
		var misCotizaciones = $("#mis_cotizaciones").is(":checked");
		var pendientesImprimir = $("#imprimir").is(":checked");
		estadoConsulta = $("#estado option:selected").val();
		
		/* if(canalId=="" || typeof canalId === "undefined"){
			canalList.focus();
			alert("Seleccione el canal.");			
			$("#buscando").fadeOut("slow");
			return false;grid
		} */
			
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
                		url: "../PymeCotizacionReporteController",
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
				{ field: "id", title: "Id.", width: "60px"},
				{ field: "sucursal", title: "Sucursal" , width: "100px"},
				{ field: "unidadProductora", title: "Unidad Productora", width: "120px",  exportar: true},
				{ field: "agenteDeSeguro", title: "Agente de Seguro", width: "200px",headerAttributes: { style: "white-space: normal"}},
				{ field: "nombreProducto", title: "Nombre producto", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "puntoVenta", title: "Punto de Venta", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "tipoPoliza", title: "Tipo Poliza", width: "100px",headerAttributes: { style: "white-space: normal"}},
				{ field: "nombreCliente", title: "Nombre del cliente", width: "80px", headerAttributes: { style: "white-space: normal"}},
				{ field: "valorAsegurado", title: "Valor Asegurado", width: "120px"},
				{ field: "primaNeta", title: "Prima Neta", width: "120px"},
				{ field: "fechaCotizacion", title: "Fecha de Cotización", type:"date", format:"{0:dd/MM/yyyy}", width: "80px",headerAttributes: { style: "white-space: normal"}},
				{ field: "fechaEmision", title: "Fecha de emisión", type:"date", format:"{0:dd/MM/yyyy}", headerAttributes: { style: "white-space: normal"}, width: "120px"},
				{ field: "fechaVigenciaDesde", title: "Vigencia Desde", width: "120px", hidden: true, exportar: true},
				{ field: "fechaVigenciaHasta", title: "Vigencia Hasta", width: "120px", hidden: true, exportar: true},
				{ field: "noPoliza", title: "No. Póliza", width: "100px"},
				{ field: "formaPago", title: "Forma de Pago", width: "80px"},
				{ field: "usuario", title: "Usuario", width: "100px"},
				{ field: "nombreProveedor", title: "Nombre Proveedor", width: "100px"},
				{ field: "fechaSolicitud", title: "Fecha de solicitud", width: "100px",type:"date", format:"{0:dd/MM/yyyy}" ,headerAttributes: { style: "white-space: normal"} },
				{ field: "fechaInspeccion", title: "Fecha de Inspeccción", width: "100px",type:"date", format:"{0:dd/MM/yyyy}" ,headerAttributes: { style: "white-space: normal"} },
				{ field: "estadoCotizacion", title: "Estado", width: "100px", hidden: true, exportar: true},                    
                ],
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
	
	function cargarPuntosVentaSessionPymes(){
		$.ajax({
			url : '../PuntoVentaController',
			data : {
				"tipoConsulta" : "puntosVentaXProductoSession",
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var sucursales = data.sucursales;
				var options = '';
				options = '<option value="">Seleccione un punto de venta</option>';
				var puntosVenta =arregloUnicoJSON(data.puntosVenta);
				var contador;
				for (var j = 0; j < sucursales.length; j++) {				
					contador =0;
					for (var i = 0; i < puntosVenta.length; i++) {
						if (puntosVenta[i].sucursal == sucursales[j].id) {
							contador++;
							if(contador ==1){
								options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
							}
							options += '<option value="'+ puntosVenta[i].id + '" >&nbsp;&nbsp;&nbsp;&nbsp;' + puntosVenta[i].nombre + '</option>';
						}
					}
				}
				
				$("#punto_venta_session").html(options);
			}
		});
	}
	//Arreglo de elementos unicos JSON
	function arregloUnicoJSON(obj) {
		var uniques = [];
		var stringify = {};
		for (var i = 0; i < obj.length; i++) {
			var keys = Object.keys(obj[i]);
			keys.sort(function(a, b) {
				return a - b;
			});
			var str = '';
			for (var j = 0; j < keys.length; j++) {
				str += JSON.stringify(keys[j]);
				str += JSON.stringify(obj[i][keys[j]]);
			}
			if (!stringify.hasOwnProperty(str)) {
				uniques.push(obj[i]);
				stringify[str] = true;
			}
		}
		return uniques;
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
						<th style="width: 300px"><input type="text" id="cotizacion_id" onkeypress="return justNumbers(event);" style="width: 100%"></th>
						<!-- <th style="width: 100px">Nro. Tr&aacute;mite:</th>
						<th style="width: 300px"><input type="text" id="nro_tramite" style="width: 100%"></th> -->
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creaci&oacute;n Desde:</th>
						<th style="width: 150px"><input type="text" data-date-format="dd-mm-yyyy" id="dp1" style="width: 100%"></th>
						<th style="width: 100px">Fecha Creaci&oacute;n Hasta:</th>
						<th style="width: 300px"><input type="text" data-date-format="dd-mm-yyyy" id="dp2" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda Varios:</th>						
					<!--  	<th style="width: 100px">Sponsor: *</th>-->
					<!--  	<th style="width: 300px"><select id="sucursal_canal" data-placeholder="Seleccione una opción..."></select></th>-->
						<th style="width: 100px">Punto de Venta:</th>
						<th colspan="3" style="width: 300px"><select id="punto_venta_session"  ></select>
						</th>
						<th style="width:100px">Mis Cotizaciones:</th>
						<th style="width: 300px"><input type="checkbox"
							id="mis_cotizaciones"></th>
						<th style="width:100px">&nbsp;</th>
						<th style="width: 300px">&nbsp;</th>
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
						<th style="width: 300px"><input type="text" id="identificacion" onkeypress="return justNumbers(event);" style="width: 100%"></th>
						<th style="width: 100px">Cliente Apellidos:</th>
						<th style="width: 300px"><input type="text" id="Apellidos_Cliente" style="width: 100%"></th>
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
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	<div id="grid"></div>
	
	<!-- Table -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">				
				<div id="grid"></div>
			</div>
		</div>
	</div>
</body>
</html>
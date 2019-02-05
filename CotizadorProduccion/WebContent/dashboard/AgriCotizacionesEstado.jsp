<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
    <script src="../static/js/Kendo/kendo.all.min.js"></script>
    <script src="../static/js/Kendo/kendo.web.min.js"></script>
    <script src="../static/js/Kendo/jszip.min.js"></script>
    <style type="text/css">
    	.tab_strip
		{
		    height: 200px;
		}
    </style>
<title>Cambio de estado de Cotizaciones</title>
<script>

var estados;
var estadosList;
$(document).ready(function(){
		activarMenu(this);	
		$("#estadoNuevo").kendoMultiSelect({
			dataTextField : "nombre",
			dataValueField : "codigo",
			animation : false,
			maxSelectedItems : 1
		});
		
		estadosList = $("#estadoNuevo").data(
		"kendoMultiSelect");
		
		//Carga Archivo
		$("#files").kendoUpload({
			async : {
				saveUrl : "../AgriCotizacionesEstadoController",
				removeUrl: "../AgriCotizacionesEstadoController"
			},
			multiple : false,
			upload : onUpload,
			success : onSuccess
		    //cancel: onCancel
		});
			
		cargarEstado();
		
		$('#content-Upload').hide();
		$('#content-cargaMasiva').hide();
		$('#btnImportar').hide();
		
		//Boton Carga Masiva
		$('#btnAprobacionMasiva').click(function(){
			$('#dataTableMasiva').hide();
 			$('#datatableError').html('');
 			$("#content-cargaMasiva").hide();
			$("#content-Upload").show();
    	 });
		$('#LimpiarBtn').click(function(){
			$('#content-Upload').hide();
			$('#content-cargaMasiva').hide();
			$('#btnImportar').hide();
    	 });
		
	});
	
	function cargarEstado(){
		$.ajax({
			url:'../AgriCotizacionesEstadoController',
			data:{
				"tipoConsulta" : "cargarEstados"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data){
				var exito= data.success;
				if(exito=="true" || exito ===true){
					var estadosListadoLlega = data.listaEstados;
					estadosList.dataSource.filter({});
					estadosList.setDataSource(estadosListadoLlega);
				}else{
					alert("No se pudo cargar los estados");
				}
			}
		});
	}
	
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
	
	function GuardarCambio(){
		
		
		var cotizacion=$("#cotizacionId").val();
		var estadoId=$("#estadoNuevo option:selected").val();
		
		$.ajax({
			url:'../AgriCotizacionesEstadoController',
			data:{
				"cotizacionId" : cotizacion,
				"estadoId" : estadoId,
				"tipoConsulta" : "actualizar"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data){
				var exito= data.success;
				if(exito=="true" || exito ===true){
					alert("Actualizado");
				}else{
					alert("Se produjo un error : "+data.error);
				}
			}
		});
		
	} 
	
	function cargaCotizacion(){
		var cotizacion=$("#idCotizacion").val();
		var tramite=$("#numeroTramite").val();
		if(cotizacion=='' && tramite=='')
			alert('debe ingresar un campo para buscar');
		else
			Cargar();
	}
	
	function Cargar() {	
		$('#dataTable_wrapper').hide();
	
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		var cotizacion=$("#idCotizacion").val();
		var tramite=$("#numeroTramite").val();
		
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
    						"numeroCotizacion" : cotizacion,
    						"NroTramite":tramite,
    						"tipoConsulta" : "encontrarPorEstado"
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
                      { field: "EstadoCotizacion", title: "Estado", width: "100px"}, 
                      
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
	
	function fnEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        //alert("Cotización id:"+dataItem.codigo);
        $('#add').modal('show');
        actualizar(dataItem.id,dataItem.EstadoCotizacion);
    }
	
	function EliminarEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        
        eliminar(dataItem.id);
    }
	
	
	
function actualizar(cotizacion,estado){	
	$("#cotizacionId").val(cotizacion);
	$("#estadoActual").val(estado);
}


function importar() {
	if (nombreArchivo != "") {
			$('#btnImportar').prop('disabled', true);
			var tableLimpiar = $('#dataTableMasiva').DataTable();
			 
			tableLimpiar
			    .clear()
			    .draw();			
			
		$.ajax({
			url : '../AgriCotizacionesEstadoController',
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
					
					// Evitar Reinicializacion datatable
					var oTable = $('#dataTableMasiva').dataTable();
					oTable.fnDestroy();
					
					$.each(listDetalle, function(index) {
						$("#datatableError").append(
								"<tr class='odd gradeX'>" + "<td>"
										+ listDetalle[index].cotizacion
										+ "</td>" + "<td>"
										+ listDetalle[index].detalle
										+ "</td>" + "</tr>");
					});
					$("#content-Upload").hide();
					//$('#dataTable tbody').html(auxiliar);

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



	
function enviarDatos(tipoConsulta){
	 var agenciaEnvio=$("#agencia option:selected").val();
	 var puntoVentaEnvio=$("#PuntoVenta option:selected").val();
	 var unidadEnvio=$("#UnidadNegocio option:selected").val();
	 var usuarioEnvio=$("#Usuario").val();
	 var claveEnvio=$("#Clave").val();
	 var identificacionEnvio=$("#Identificacion").val();
	 var nombresEnvio=$("#Nombres").val();
	 var apellidosEnvio=$("#Apellidos").val();
	 var correoEnvio=$("#Correo").val();
	 var UsuarioId=$("#UsuarioId").val();
	 
	$.ajax({
		url:'../AgriUsuarioOffline',
		data : {
			
			"agenciaEnvio" : agenciaEnvio,
			"puntoVentaEnvio" : puntoVentaEnvio,
			"unidadEnvio" : unidadEnvio,
			"usuarioEnvio" : usuarioEnvio,
			"claveEnvio" : claveEnvio,
			"identificacionEnvio" : identificacionEnvio,
			"nombresEnvio" : nombresEnvio,
			"apellidosEnvio" : apellidosEnvio,
			"correoEnvio" : correoEnvio,
			"tipoConsulta" : tipoConsulta,
			"UsuarioId" : UsuarioId
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

</script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
	%>
<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

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
							<h4 class="modal-title" id="myModalLabel">Estados Agr&iacute;cola</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La cotizaci&oacute;n agr&iacute;cola se grabo con &eacute;xito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="cotizacionId" disabled="disabled">										
								<label>Estado Actual</label>
									<input type="text" class="form-control required" id="estadoActual" validationMessage="Campo requerido!!!" required>
								
								<label>Estado a Cambiar</label>
									<select id="estadoNuevo" name="estadoNuevo" style ="width: 450px"
											data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required>											
									</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record" onclick="GuardarCambio();">Guardar Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	
	
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador
								Cotizaciones por estados</td>

					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Parametros:</th>
						<th style="width: 100px">Id Cotizacion: </th>
						<th style="width: 300px"> <input type="text" id="idCotizacion"
							style="width: 100%"></th>
						<th style="width: 100px">Numero Tramite: </th>
						<th style="width: 300px"><input type="text" id="numeroTramite" style="width: 100%"></th>
					</tr>				
				</thead>
				<tbody>
					<tr>
					<th>
				
							<button class="btn btn-primary" id="ConsultaBtn" onclick="cargaCotizacion();">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						  </th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
							</th>
							<th>
								<button  class="btn btn-primary" id="btnAprobacionMasiva" >Cambios masivos</button>
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
	
	
	
	<!-- Datatable -->
	<div id="grid"></div>
	</div>
	<!-- Datatable -->
	
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-8">
			<div class="row" style="display: inline;" id="content-Upload">
				<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
			        <thead>
			        <tr >
			        <th colspan="4">
			        	<b>TABLA CAMBIO DE ESTADO</b>
			        </th>
			        </tr>
			        <tr>
			        	<th>Id Cotización</th>
			        	<th>Estado</th>			        	
			        </tr>
			        </thead>
			        <tbody> 
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Pendiente Aprobar</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Pendiente de Emitir</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Revocado</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Rechazado</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Emitida</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Facturado</td>			        		
			        	</tr>
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>Borrador</td>			        		
			        	</tr>
			        </tbody>
		    	</table>
				<div class="col-lg-12">
					<div class="table-responsive">
					<table width="80%">
							<tr >
									<td colspan="2" >CAMBIOS DE ESTADO MASIVO</td>
								</tr>
							<tr >
								<td  colspan="2"><input name="files" id="files" type="file" /></td>
								
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
			</div>
			<div class="col-md-1">
			</div>
		</div>
	<style>	
	 .confirm {
                    padding-top: 1em;
                }

                .valid {
                    color: green;
                }

                .invalid {
                    color: red;
                }
	</style>
</body>
</html>
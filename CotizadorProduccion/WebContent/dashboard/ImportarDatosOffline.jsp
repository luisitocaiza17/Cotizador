<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos Offline - QBE</title>
<!-- 
<script src="../static/js/jquery.min.js"></script> -->

<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<script src="../static/js/cotizador/comun.js"></script>
	<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<script type="text/javascript">
var tipoConsulta= "";
var nombreArchivo = "";
	function importar() {
		if(nombreArchivo!==""){
			tipoConsulta = "importar";
			$.ajax({
				url : '../AgriImportarDatosOfflineController',
				data : {
					"tipoConsulta" : "importar",
					"nombreArchivo" : nombreArchivo
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					alert(data.mensaje);
					var listDetalle = data.listadoCotizaciones;
					if (typeof listDetalle != 'undefined') {
						$('#datatableError').html('');
						$("#content-cargaMasiva").show();
						$('#dataTableMasiva').show();
						//$('#dataTable_wrapper').show();
						//$('#datatableError').html('');
						$.each(listDetalle, function(index) {
							var id = "";
							var type = "";
							if (listDetalle[index].cotizacionID=="0"){
								id = listDetalle[index].objetoCotizacionID;
								type = "hidden";
							}
							else {
								id = listDetalle[index].cotizacionID;
								type = "button";
							}
							$("#datatableError").append(
									"<tr class='odd gradeX'>" + "<td>"
											+ id
											+ "</td>" + "<td>"
											+ listDetalle[index].comentario
											+ "</td>" 
											+ " <td width='250px' align='center'>"
											+ " <button  type='button' name='imprimir-btn' id='"+ id +"' class='btn btn-success btn-xs imprimir-btn' onclick='generarReporte("
											+ id
											+ ");'>"
											+ "<span class='glyphicon glyphicon glyphicon-edit' id='imprimir-record' ></span> Mostrar Reporte"
											+ " </button>"
											+ "</td>"
											+ "</tr>");
							if (type=="hidden"){
								var bs = "#"+id;
								$(bs).hide();
							}
						});
						//Limpiar upload
						var up = $('#files').data().kendoUpload;
			 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
			 			up._removeFileEntry(allLiElementsToBeRemoved );
			 			$('#btnImportar').hide();
			 			nombreArchivo="";
					}
				}
			});			
		}else{
			alert("Seleccione un archivo");
		}
		
	}
    
    function onUpload(e) {
        // Array with information about the uploaded files
        var files = e.files;

        // Check the extension of each file and abort the upload if it is not .jpg
        $.each(files, function () {
            if (this.extension.toLowerCase() != ".config") {
                alert("Por favor seleccioanar el achivo correcto.");
                e.preventDefault();
            }
        });
    }
    
    function onSuccess(e) {
    	if (e.operation == "upload") {
			nombreArchivo = e.files[0].name;
		} else {
			nombreArchivo = "";
		}
    }

	$(document).ready(function() {
		activarMenu("ImportarDatosOffline");	
		//$("#content-cargaMasiva").show();    	 	
	    	 $('#LimpiarBtn').click(function(){	   
	 			$('#dataTableMasiva').hide();
	 			$('#datatableError').html('');
	 			$("#content-cargaMasiva").hide();
	 			nombreArchivo="";
	 			var up = $('#files').data().kendoUpload;
	 			var allLiElementsToBeRemoved = up.wrapper.find('.k-file');
	 			up._removeFileEntry(allLiElementsToBeRemoved );
	 			$('#btnImportar').hide();
	    	 });
		
		$("#files").kendoUpload({
			async: {
	            saveUrl: "../AgriImportarDatosOfflineController",
	            removeUrl: "../AgriImportarDatosOfflineController"
	        },
	        multiple: false,
	        upload: onUpload,
	        success: onSuccess
	        //cancel: onCancel
		});
	});
	//Imprimir
function generarReporte(id){
		//var a = "37327";
		window.open('../AgriCotizacionReporte?tipoConsulta=generarReporte&codigoCotizacion=' + id);
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

	<table>
		<tr>
			<td colspan="2">Está opción le permite importar las cotizaciones
				realizadas en el cotizador Off-Line.</td>
		</tr>
		<tr>
			<td><br />
			<br /></td>
		</tr>
		<tr>
			<td colspan="2">Informe de Inspección:</td>
		</tr>
		<tr>
			<td colspan="2"><input name="files" id="files" type="file"/></td>
		</tr>
		<!-- <tr>
		<td>Seleccione el punto de venta al que usted pertence:</td>
		<td><select name="punto_venta" id="punto_venta" required="required"></select></td>
	</tr> -->
		<tr>
			<td><br />
			<br /></td>
		</tr>
		<tr>
			<td colspan="2">
			<button id="btnExportar" class="btn btn-primary" onclick="importar()">Importar
					Ahora</button>
			<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
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
							<th></th>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<title>Archivos Planos- QBE</title>
<script>
	var tipoConsulta = "";
	var nombreArchivo = "";

	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});

	function importar() {
		
		if(nombreArchivo!==""){
			tipoConsulta = "importar";
			$.ajax({
				url : '../AgriSubirVersionOfflineController',
				data : {
					"tipoConsulta" : "importar",
					"nombreArchivo" : nombreArchivo
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var alerta=data.mensaje;
					if(data.estado){
						alert(alerta);
					}else{
						alert(alerta);
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
		$.each(files, function() {
			if (this.extension.toLowerCase() != ".zip"
					&& this.extension.toLowerCase() != ".ZIP") {
				alert("Por favor seleccioanar el achivo correcto.");
				e.preventDefault();
			}
		});
	}

	function onSuccess(e) {
		nombreArchivo = e.files[0].name;
	}

	$(document).ready(function() {

		activarMenu("SubirVersionOffline");

		$("#loading").hide();
		$("#files").kendoUpload({
			async : {
				saveUrl : "../AgriSubirVersionOfflineController"
			},
			multiple : false,
			upload : onUpload,
			success : onSuccess
		//cancel: onCancel
		});
	});
</script>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>

	<table>
		<br>
		<br>
		<br>
		<br>
		<tr>
			<td colspan="2">Está opción le permite subir el .zip que contiene el instalador offline</strong>.
			</td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2">Subir Instalador:</td>
		</tr>
		<tr>
			<td colspan="2"><input name="files" id="files" type="file" /></td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2"><button id="btnImportar" class="btn btn-primary"
					onclick="importar()">Subir Ahora</button></td>			
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<div id="loading">
			<div class="loading-indicator">
				<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
					id="loading-msg">Procesando, espere por favor...</span>
			</div>
		</div>
	</table>
	
</body>
</html>
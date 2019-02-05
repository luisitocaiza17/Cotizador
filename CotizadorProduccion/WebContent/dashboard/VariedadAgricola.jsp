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
<title>Variedad Agrícola</title>
<script>
$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	

var VariedadId="";
var Nombre = "";
var TipoCultivoId = "";

$(document).ready(function(){
	activarMenu("VariedadAgricola");	
	Cargar();
	ConsultarTipoCultivo ();
	$("#save-record").bind({click: function(){
		
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
	    	    VariedadId=$("#VariedadId").val();
	    	  	Nombre = $("#Nombre").val();
	    	  	TipoCultivoId = $("#TipoCultivoId").val();
				
				
				if(VariedadId == "")
					tipoConsulta = "crear";
				else
					tipoConsulta = "editar";
				
				enviarDatos(VariedadId,Nombre,TipoCultivoId,tipoConsulta);
	      }
		}
	});
	});
	
function enviarDatos(VariedadId,Nombre,TipoCultivoId,tipoConsulta){
	$.ajax({
		url:'../AgriVariedadController',
		data : {
			"VariedadId" : VariedadId,
			"Nombre" : Nombre,
			"TipoCultivoId" : TipoCultivoId,
			"tipoConsulta" : tipoConsulta
		},
		type : 'POST',
		async : false,
		datatype : 'json',
		success : function(data){
			$("#msgPopup").show();
		}
	});
}

function actualizar(Variedad) {
	$.ajax({
		url : '../AgriVariedadController',
		data : {
			"VariedadId" : Variedad,
			"tipoConsulta" : "obtenerPorId"
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			$("#VariedadId").val(data.VariedadId);
			$("#Nombre").val(data.Nombre);
			$("#TipoCultivoId").children().remove();
			ConsultarTipoCultivo ();
			$("#TipoCultivoId").val(data.TipoCultivoId);
		}
	});
}
function eliminar(Variedad) {
	var r = confirm("Seguro que desea eliminar");
	if (r == true) {
		VariedadId = Variedad;
		tipoConsulta = "eliminar";
		enviarDatos(VariedadId, "", "", tipoConsulta);
	}
	Cargar();
	$("#msgPopup").hide();
}
function Cargar(){
	$('#configuracionVariedad').children().remove();
	$.ajax({
		url:'../AgriVariedadController',
		data:{'tipoConsulta':'encontrarTodos'},
		type:'post',
		datatype:'json',
		async: false,
		success: function(data){
			var VariedadJSONArray = data.VariedadJSONArray;
			$.each(VariedadJSONArray, function(index){
				$('#configuracionVariedad').append("<tr class='odd gradeX'>"+
						"<td relation='Nombre'>"+VariedadJSONArray[index].Nombre+"</td>"+
						"<td relation='TipoCultivo'>"+VariedadJSONArray[index].TipoCultivo+"</td>"+
						
						"<td width='175px'>"+									
						"<input type='hidden' id='VariedadId' value='" + VariedadJSONArray[index].VariedadId + "'/>"+	
						" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+VariedadJSONArray[index].VariedadId+");'>" +
							" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
						" </button>" +									
						" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+VariedadJSONArray[index].VariedadId+");'>" +
						  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record' ></span> Eliminar" +
						" </button>" +									
					"</td>" +
				"</tr>");
			});
			$("#loading").remove();	
		}
	});
}

function ConsultarTipoCultivo (){
	//Consultar los tipo de cultivos
	$.ajax({
		url : '../AgriTipoCultivoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},	
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTiposCultivo = data.TipoCultivoJSONArray;	 
			$("#TipoCultivoId").append("<option value=''>Seleccione una opcion</option>");
			$.each(listadoTiposCultivo, function (index) {
				$("#TipoCultivoId").append("<option value='" + listadoTiposCultivo[index].TipoCultivoId + "'>" + listadoTiposCultivo[index].Nombre + "</option>");
			});		
			//$("#cobertura_copiada").append("<option value=''>Seleccione una opcion</option>");
			//$.each(listadoTiposCultivo, function (index) {
			//	$("#cobertura_copiada").append("<option value='" + listadoTiposCultivo[index].coberturaPymesId + "'>" + listadoTiposCultivo[index].nombre + "</option>");
			//});
			
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
							<h4 class="modal-title" id="myModalLabel">Variedad Agrícola</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La variedad agrícola se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden" class="form-control" id="VariedadId">										
								<label>Nombre</label>
								<input type="text" class="form-control required" id="Nombre" name="Nombre" validationMessage="Campo requerido!!!" required>
								<br />	
								<label>Tipo Cultivo</label>		
								<select  name="TipoCultivoId" id="TipoCultivoId" class="form-control required" validationMessage="Campo requerido!!!" required></select>
								<br />	
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
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">					
				<div class="panel panel-primary">
						<div class="panel-body">
						<div class="input-group"> <span class="input-group-addon">Filtro</span>
						    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
						</div>
						 <table class="table table-striped table-bordered table-hover"
							id="dataTable">
								<thead>
									<tr>
										<th>Nombre</th>	
										<th>Tipo Cultivo</th>																
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionVariedad" class="searchable">
					                 		<div id="loading">
										<div class="loading-indicator">
											<img src="../static/images/ajax-loader.gif"/><br /><br />
											<span id="loading-msg">Cargando...</span>
										</div>					
									</div> 
								</tbody>                     
			                </table>
						</div>
					</div>	
			</div>
		</div>

	<!-- Datatable -->	
</body>
</html>
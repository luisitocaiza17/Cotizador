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
    
    <style type="text/css">
    	.tab_strip
		{
		    height: 200px;
		}
    </style>
<title>Tipo Calculo Agricola</title>
<script>
$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	
	
var TipoCalculoId = "";
var Nombre = "";

$(document).ready(function(){
	activarMenu("TipoCanalAgricola");	
	Cargar();
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
	    	  TipoCalculoId = $("#TipoCalculoId").val();
				Nombre = $("#Nombre").val();
				
				if(TipoCalculoId == "")
					tipoConsulta = "crear";
				else
					tipoConsulta = "editar";
				
				enviarDatos(TipoCalculoId,Nombre,tipoConsulta);
	      }
		}
	});
	});
	
function actualizar(TipoCalculo){
	$.ajax({
		url:'../AgriTipoCalculoController',
		data:{
			"TipoCalculoId" : TipoCalculo,
			"tipoConsulta" : "obtenerPorId"
		},
		type : 'POST',
		datatype : 'json',
		success : function(data){
			$("#TipoCalculoId").val(data.TipoCalculoId);
			$("#Nombre").val(data.Nombre);
		}
	});
}

function eliminar(TipoCalculo){
	var r = confirm("Seguro que desea eliminar");
	if(r == true){			
		TipoCalculoId = TipoCalculo;
		tipoConsulta="eliminar";			
		enviarDatos(TipoCalculoId,"",tipoConsulta);
	}
	Cargar();
	$("#msgPopup").hide();
}
	
function enviarDatos(TipoCalculoId,Nombre,tipoConsulta){
	$.ajax({
		url:'../AgriTipoCalculoController',
		data : {
			"TipoCalculoId" : TipoCalculoId,
			"Nombre" : Nombre,
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
	
function Cargar(){
	$('#configuracionTipoCalculo').children().remove();
	$.ajax({
		url:'../AgriTipoCalculoController',
		data:{'tipoConsulta':'encontrarTodos'},
		type:'post',
		datatype:'json',
		async: false,
		success: function(data){
			var TipoCalculoJSONArray = data.TipoCalculoJSONArray;
			$.each(TipoCalculoJSONArray, function(index){
				$('#configuracionTipoCalculo').append("<tr class='odd gradeX'>"+
						"<td relation='Nombre'>"+TipoCalculoJSONArray[index].Nombre+"</td>"+
						
						"<td width='175px'>"+									
						"<input type='hidden' id='TipoCalculoId' value='" + TipoCalculoJSONArray[index].TipoCalculoId + "'/>"+	
						" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+TipoCalculoJSONArray[index].TipoCalculoId+");'>" +
							" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
						" </button>" +									
						" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+TipoCalculoJSONArray[index].TipoCalculoId+");'>" +
						  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record' ></span> Eliminar" +
						" </button>" +									
					"</td>" +
				"</tr>");
			});
			$("#loading").remove();	
		}
	});
}
</script>
</head>
<body>
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
							<h4 class="modal-title" id="myModalLabel">Tipo Cálculo Agrícola</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El tipo de cálculo agrícola se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="TipoCalculoId">										
								<label>Nombre</label>
								<input type="text" class="form-control required" id="Nombre" validationMessage="Campo requerido!!!" required>
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
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionTipoCalculo" class="searchable">
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
	</div>
	<!-- Datatable -->	
</body>
</html>
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
    
<title>Tipo de Cultivo</title>
<script >
$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	

var tipoConsulta = "";
var TipoCultivoId = "";
var Nombre = "";
var Tipo = "";
var VigenciaDias="";
var codEnsurance="";
var codIntegracion="";
var codIntSucre="";
var codIntOtros="";
var precioAjuste="";
var unidadMedida="";
var precioAjuste2="";
var unidadMedida2="";

$(document).ready(function(){
	activarMenu("TipoCultivo");	
	$("#VigenciaDias").kendoNumericTextBox({
		format : '#',
		 decimals: 0,
		 min: 1
    });
	$("#codEnsurance").kendoNumericTextBox({
		format : '#',
		 decimals: 0,
	 	min: 1
    });
	$("#codIntegracion").kendoNumericTextBox({
		format : '#',
		 decimals: 0,
		 min: 1
    });
	$("#codIntSucre").kendoNumericTextBox({
		format : '#',
		 decimals: 0,
		 min: 1
    });
	$("#codIntOtros").kendoNumericTextBox({
		format : '#',
		 decimals: 0,
		 min: 1
    });
	
	$("#tasa").kendoNumericTextBox({
		format : '#.00',
		 decimals: 2
    });
	
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
	    	  	TipoCultivoId = $("#TipoCultivoId").val();
	    	  	Tipo = $("#Tipo").val();
				Nombre = $("#Nombre").val();
				VigenciaDias = $("#VigenciaDias").val();
				codEnsurance = $("#codEnsurance").val();
				codIntegracion = $("#codIntegracion").val();
				codIntSucre=$("#codIntSucre").val();
				codIntOtros=$("#codIntOtros").val();
				precioAjuste=$("#precioAjuste").val();
				unidadMedida = $("#unidadMedida").val();
				precioAjuste2=$("#precioAjuste2").val();
				unidadMedida2 = $("#unidadMedida2").val();
				/* nombrePlantilla = $("#nombrePlantilla").val(); */
				if(TipoCultivoId == "")
					tipoConsulta = "crear";
				else
					tipoConsulta = "editar";
				
				enviarDatos(TipoCultivoId,Nombre,Tipo,VigenciaDias,codEnsurance,codIntegracion,codIntSucre,codIntOtros,precioAjuste,unidadMedida,precioAjuste2,unidadMedida2,tipoConsulta);
	      }		 
		}		
	});
	
	$("#addButton").click(function(){
		limpiar();
	});
});
	
function actualizar(TipoCultivo){	
	limpiar();
	$.ajax({
		url:'../AgriTipoCultivoController',
		data:{
			"TipoCultivoId" : TipoCultivo,
			"tipoConsulta" : "obtenerPorId"
		},
		type : 'POST',
		datatype : 'json',
		success : function(data){
			$("#TipoCultivoId").val(data.TipoCultivoId);
			$("#Nombre").val(data.Nombre);
			$("#Tipo").val(data.Tipo);
			($("#VigenciaDias").data("kendoNumericTextBox")).value(data.VigenciaDias);
			($("#codEnsurance").data("kendoNumericTextBox")).value(data.codEnsurance);
			($("#codIntegracion").data("kendoNumericTextBox")).value(data.codIntegracion);			
			($("#codIntSucre").data("kendoNumericTextBox")).value(data.codIntSucre);
			($("#codIntOtros").data("kendoNumericTextBox")).value(data.codIntOtros);
			($("#tasa").data("kendoNumericTextBox")).value(data.tasa);
			$("#unidadMedida").val(data.unidadMedida);
			$("#precioAjuste").val(data.precioAjuste);
			$("#unidadMedida2").val(data.unidadMedida2);
			$("#precioAjuste2").val(data.precioAjuste2);
			
		}
	});
}

function eliminar(TipoCultivo){
	var r = confirm("Seguro que desea eliminar.");
	if(r == true){			
		TipoCultivoId = TipoCultivo;
		tipoConsulta="eliminar";			
		enviarDatos(TipoCultivoId,"","","","","","","","","","","",tipoConsulta);
	}
}
	
function enviarDatos(TipoCultivoId,Nombre,Tipo,VigenciaDias,codEnsurance,codIntegracion,codIntSucre,codIntOtros,precioAjuste,unidadMedida,precioAjuste2,unidadMedida2,tipoConsulta){
	var tasa= $("#tasa").val();
	$.ajax({
		url:'../AgriTipoCultivoController',
		data : {
			"TipoCultivoId" : TipoCultivoId,
			"Nombre" : Nombre,
			"Tipo" : Tipo,
			"VigenciaDias":VigenciaDias,
			"codEnsurance":codEnsurance,
			"codIntegracion":codIntegracion,
			"codIntSucre":codIntSucre,
			"codIntOtros":codIntOtros,
			"precioAjuste":precioAjuste,
			"unidadMedida":unidadMedida,
			"precioAjuste2":precioAjuste2,
			"unidadMedida2":unidadMedida2,
			"tipoConsulta" : tipoConsulta,
			"tasa" : tasa
		},
		type : 'POST',
		async : false,
		datatype : 'json',
		success : function(data){
			$("#msgPopup").show();
		}
	});
	Cargar();
}
	
function Cargar(){
	$('#configuracionTipoCultivo').children().remove();
	$.ajax({
		url:'../AgriTipoCultivoController',
		data:{'tipoConsulta':'encontrarTodos'},
		type:'post',
		datatype:'json',
		async: false,
		success: function(data){
			var TipoCultivoJSONArray = data.TipoCultivoJSONArray;
			$.each(TipoCultivoJSONArray, function(index){
				var vigencia ="";
				var codEns ="";
				var codInt ="";
				var codIntSucre="";
				var auxIntOtros="";				
				var precioAjuste="";
				var auxUnidadMedida = "";
				var auxPrecioAjuste2="";
				var auxUnidadMedida2 = "";
				var tasa="";
					if(typeof TipoCultivoJSONArray[index].VigenciaDias == "undefined" || TipoCultivoJSONArray[index].VigenciaDias == "")
						vigencia = "";
					else
						vigencia = TipoCultivoJSONArray[index].VigenciaDias;
					if(typeof TipoCultivoJSONArray[index].codEnsurance == "undefined" || TipoCultivoJSONArray[index].codEnsurance == "")
						codEns = "";
					else
						codEns = TipoCultivoJSONArray[index].codEnsurance;
					if(typeof TipoCultivoJSONArray[index].codIntegracion == "undefined" || TipoCultivoJSONArray[index].codIntegracion == "")
						codInt = "";
					else
						codInt = TipoCultivoJSONArray[index].codIntegracion;
					
					if(typeof TipoCultivoJSONArray[index].codIntSucre == "undefined" || TipoCultivoJSONArray[index].codIntSucre == "")
						codIntSucre = "";
					else
						codIntSucre = TipoCultivoJSONArray[index].codIntSucre;
					
					if(typeof TipoCultivoJSONArray[index].codIntOtros == "undefined" || TipoCultivoJSONArray[index].codIntOtros == "")
						auxIntOtros = "";
					else
						auxIntOtros = TipoCultivoJSONArray[index].codIntOtros;
					
					if(typeof TipoCultivoJSONArray[index].precioAjuste == "undefined" || TipoCultivoJSONArray[index].precioAjuste == "")
						precioAjuste = "";
					else
						precioAjuste = TipoCultivoJSONArray[index].precioAjuste;
					
					if(typeof TipoCultivoJSONArray[index].unidadMedida == "undefined" || TipoCultivoJSONArray[index].unidadMedida == "")
						auxUnidadMedida = "";
					else
						auxUnidadMedida = TipoCultivoJSONArray[index].unidadMedida;
					
					if(typeof TipoCultivoJSONArray[index].precioAjuste2 == "undefined" || TipoCultivoJSONArray[index].precioAjuste2 == "")
						auxPrecioAjuste2 = "";
					else
						auxPrecioAjuste2 = TipoCultivoJSONArray[index].precioAjuste2;
					
					if(typeof TipoCultivoJSONArray[index].unidadMedida2 == "undefined" || TipoCultivoJSONArray[index].unidadMedida2 == "")
						auxUnidadMedida2 = "";
					else
						auxUnidadMedida2 = TipoCultivoJSONArray[index].unidadMedida2;
					
					if(typeof TipoCultivoJSONArray[index].tasa == "undefined" || TipoCultivoJSONArray[index].tasa == "")
						tasa = "";
					else
						tasa = TipoCultivoJSONArray[index].tasa;
					
					
				$('#configuracionTipoCultivo').append("<tr class='odd gradeX'>"+
						"<td relation='Nombre'>"+TipoCultivoJSONArray[index].Nombre+"</td>"+
						"<td relation='Tipo'>"+TipoCultivoJSONArray[index].Tipo+"</td>"+
						"<td relation='VigenciaDias'>"+vigencia+"</td>"+
						"<td relation='CodigoEnsurance'>"+codEns+"</td>"+
						"<td relation='codigoIntegracion'>"+codInt+"</td>"+
						"<td relation='codIntSucre'>"+codIntSucre+"</td>"+
						"<td relation='codIntOtros'>"+auxIntOtros+"</td>"+						
						"<td relation='precioAjuste'>"+precioAjuste+"</td>"+
						"<td relation='precioAjuste'>"+auxUnidadMedida+"</td>"+
						"<td relation='precioAjuste'>"+auxPrecioAjuste2+"</td>"+
						"<td relation='precioAjuste'>"+auxUnidadMedida2+"</td>"+
						"<td relation='tasa'>"+tasa+"</td>"+
						"<td>"+									
						"<input type='hidden' id='TipoCultivoId' value='" + TipoCultivoJSONArray[index].TipoCultivoId + "'/>"+	
						" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+TipoCultivoJSONArray[index].TipoCultivoId+");'>" +
							" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
						" </button>" +									
						" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+TipoCultivoJSONArray[index].TipoCultivoId+");'>" +
						  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record' ></span> Eliminar" +
						" </button>" +									
					"</td>" +
				"</tr>");
			});
			$("#loading").remove();	
		}
	});
}
function limpiar(){
	$("#TipoCultivoId").val("");
	$("#Nombre").val("");
	$("#Tipo").val("");
	($("#VigenciaDias").data("kendoNumericTextBox")).value("");
	($("#codEnsurance").data("kendoNumericTextBox")).value("");
	($("#codIntegracion").data("kendoNumericTextBox")).value("");	
	($("#codIntSucre").data("kendoNumericTextBox")).value("");	
	($("#tasa").data("kendoNumericTextBox")).value("");	
	($("#codIntOtros").data("kendoNumericTextBox")).value("");
	$("#unidadMedida").val("");
	$("#precioAjuste").val("");
	$("#unidadMedida2").val("");
	$("#precioAjuste2").val("");
	$("#msgPopup").hide();
}

</script>
<style>
	@media only screen and (min-width: 1px)  {
		#dataTable {
			overflow-x: auto;
			display: block;
		}
	}
</style>
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
							<h4 class="modal-title" id="myModalLabel">Tipo de Cultivo Agrícola</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El tipo de cultivo se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden" class="form-control" id="TipoCultivoId">										
								<label>Nombre:</label>
								<input type="text" class="form-control required" id="Nombre" name="Nombre" validationMessage="Campo requerido!!!" required>
								<br />	
								<label>Tipo:</label>
									<select type="select" class="form-control required" id="Tipo" name="Tipo"  required="required">	
										<option value=''>Selecione una opción</option>	
										<option value='1'>PERENNE</option>	
										<option value='2'>NO PERENNE</option>						
									</select>
								<br />	
								<br />	
								<table style="width: 100%">
								<tr>
									<td style="width: 30%">
										<label>Vigencia (días)</label>
										<br />
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="VigenciaDias" id="VigenciaDias"
										class="datosTipo" validationMessage="Campo requerido!!!" required style="width: 100%"></input>
										<br />
										<br />
									</td>										
								</tr>
								<tr>
								<td style="width: 30%">
									<label>Código Ensurance</label>
									<br />
									<br />
								</td style="width: 70%">
									<td>
										<input type="text" name="codEnsurance" id="codEnsurance"
										class="datosTipo" validationMessage="Campo requerido!!!" required style="width: 100%"></input>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Código Integración(BNF))</label>
										<br />
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="codIntegracion" id="codIntegracion"
										class="datosTipo" style="width: 100%"></input>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Código Integración(Sucre)</label>
										<br />
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="codIntSucre" id="codIntSucre"
										class="datosTipo"  style="width: 100%"></input>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Código Integración(Otros)</label>
										<br />
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="codIntOtros" id="codIntOtros"
										class="datosTipo"  style="width: 100%"></input>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Precio de Ajuste</label>
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="precioAjuste" id="precioAjuste"
										class="form-control datosTipo"  style="width: 100%"></input>
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Unidad de Medida</label>
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="unidadMedida" id="unidadMedida"
										class="form-control datosTipo"></input>
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Precio de Ajuste 2</label>
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="precioAjuste2" id="precioAjuste2"
										class="form-control datosTipo"></input>
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Unidad de Medida 2</label>
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="unidadMedida2" id="unidadMedida2"
										class="form-control datosTipo"></input>
										<br />
									</td>
								</tr>
								<tr>
									<td style="width: 30%">
										<label>Tasa</label>
										<br />
										<br />
									</td>
									<td style="width: 70%">
										<input type="text" name="tasa" id="tasa"
										class="datosTipo"  style="width: 100%"></input>
										<br />
										<br />
									</td>
								</tr>
								</table>																				
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
										<th>Tipo</th>
										<th>Vigencia (días)</th>
										<th>Código Ensurance</th>
										<th>Código Integración <br>(BNF)</th>
										<th>Código Integración <br>(Sucre)</th>	
										<th>Código Integración <br>(Otros)</th>	
										<th>Precio de Ajuste </th>
										<th>Unidad de Medida </th>										 
										<th>Precio de Ajuste 2</th>
										<th>Unidad de Medida 2</th>	
										<th>Tasa</th>																										
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionTipoCultivo" class="searchable">
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
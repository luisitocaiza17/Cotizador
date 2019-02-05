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
<title>Pymes Ciudad Riesgo</title>
<script>

var plantillaList = "";
var puntosVentaList = "";

var provinciaList = "";
var provinciaList2 = "";
var ciudadList = "";
var ciudadList2 = "";
var tiposList = "";

$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	
	
var identificadorCot = "";
var Nombre = "";

$(document).ready(function(){
	activarMenu("PymeCiudadRiesgo");	
	
	$("#ciudadId").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
	ciudadList = $("#ciudadId").data(
	"kendoMultiSelect");
	
	$("#tiposId").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
	tiposList = $("#tiposId").data(
	"kendoMultiSelect");
	
	
	$("#ciudadId2").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
	ciudadList2 = $("#ciudadId2").data(
	"kendoMultiSelect");
	
	$("#provinciaId").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		animation : false,
		maxSelectedItems : 1
	});
    
    provinciaList = $("#provinciaId").data(
	"kendoMultiSelect");
    
    $("#provinciaId2").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		animation : false,
		maxSelectedItems : 1
	});
    
    provinciaList2 = $("#provinciaId2").data(
	"kendoMultiSelect");
	
	$("#Plantilla").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		animation : false,
		maxSelectedItems : 1
	});
	
	$("#usuarioNombre").prop('disabled', true);
	
	$("#PuntoVenta").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		animation : false,
		maxSelectedItems : 1
	});	

	ConsultarProvincia();
	ConsultarCiudadTodas();
	CargarTipos();
	Cargar();	
	
	$("#provinciaId").change(function(){	
		ConsultarCiudad();	
	});
	
	$("#provinciaId2").change(function(){	
		ConsultarCiudad2();	
	});
	
	
	$("#ConsultaBtn").bind({click: function(){
		Cargar();
		
		}
	});
	
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
	    	  identificadorCot = $("#PPV_id").val();
				
				if(identificadorCot === ""){
					tipoConsulta = "crear";
					
				}
				else
					tipoConsulta = "editar";
				
				enviarDatos(tipoConsulta);
	      }
		 Cargar();
		}
	});
	});
	
	$("formCrud").submit(function(event) {
	    event.preventDefault();
	    if (validator.validate()) {
	        status.text("Hooray! Your tickets has been booked!")
	            .removeClass("invalid")
	            .addClass("valid");
	    } else {
	        status.text("Oops! There is invalid data in the form.")
	            .removeClass("valid")
	            .addClass("invalid");
	    }
	});
	
	function CargarTipos(){
		$.ajax({
			url:'../PymeCiudadRiesgoController',
			data:{"tipoConsulta":"encontrarTipos"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				tiposList.dataSource.filter({});
				tiposList.setDataSource(data.listTipos);				
			}
		});
	}
	
	function ConsultarProvincia(){
		$.ajax({
			url:'../ProvinciaController',
			data:{"tipoConsulta":"encontrarTodos"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				provinciaList.dataSource.filter({});
				provinciaList.setDataSource(data.listadoProvincia);
				
				
				provinciaList2.dataSource.filter({});
				provinciaList2.setDataSource(data.listadoProvincia);
				
			}
		});
	}
	
	function ConsultarCiudad2(){
		var provincia= $("#provinciaId2 option:selected").val();
		$.ajax({
			url:'../CantonController',
			data:{	
				"tipoConsulta":"encontrarPorProvincia",
				"provincia" : provincia	
			},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				ciudadList2.dataSource.filter({});
				ciudadList2.setDataSource(data.cantones);				
			}
		});
	}
	
	function ConsultarCiudad(){
		var provincia= $("#provinciaId option:selected").val();
		$.ajax({
			url:'../CantonController',
			data:{	
				"tipoConsulta":"encontrarPorProvincia",
				"provincia" : provincia	
			},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				ciudadList.dataSource.filter({});
				ciudadList.setDataSource(data.cantones);				
			}
		});
	}
	
	function ConsultarCiudadTodas(){
		$.ajax({
			url:'../CantonController',
			data:{	
				"tipoConsulta":"encontrarTodosCombos"
			},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				ciudadList2.dataSource.filter({});
				ciudadList2.setDataSource(data.listadoCanton);				
			}
		});
	}
	
	function Cargar() {	
		var Provincia=$("#provinciaId option:selected").val();
		var Ciudad=$("#ciudadId option:selected").val();
		$('#dataTable_wrapper').hide();
	
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		$("#grid").kendoGrid({
			toolbar: ["excel"],
	        excel: {
	            fileName: "QBE_Parametro_PPV.xlsx",
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
	            		url: "../PymeCiudadRiesgoController",
	            		data: {
	            			"tipoConsulta":"buscarTodos",
	            			"Provincia":Provincia,
	            			"Ciudad":Ciudad
						}
	            	}
	            },
	            schema: {
	            	data: "Data",
	            	total: "Total",
	            }
	        },
	        columns: [
	  				{ field: "id", title: "ID.", width: "100px",headerAttributes: { style: "white-space: normal"},exportar: true, hidden: true},
	  				{ field: "provincia", title: "Provincia", width: "150px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "ciudad", title: "Ciudad.", width: "150px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "tipo", title: "Tipo de Riesgo.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " Detalle ", width: "110px"},
	  				{ command: { text: "Eliminar", click: EliminarEventoClick}, title: " Eliminar ", width: "110px"}
	  				],
	  				height: 500,
	            selectable: true,
	            resizable: true,
	            filterable: true,
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
        $("#PPV_id").val(dataItem.id);
        actualizar(dataItem.id);
    }
	
	function EliminarEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        eliminar(dataItem.id);
    }
	
	function eliminar(id){
		var r = confirm("Seguro que desea eliminar");
		if(r == true){			
			$.ajax({
				url:'../PymeCiudadRiesgoController',
				data:{
					"id" : id,
					"tipoConsulta" : "eliminar"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					var exito= data.success;
					if(exito=="true" || exito ===true){
						alert("Eliminado");
					}else{
						alert("Se produjo un error al guardar el usuario");
					}
				}
			});
		}
		Cargar();
		$("#msgPopup").hide();
	}
	
	
function actualizar(id){
	$.ajax({
		url:'../PymeCiudadRiesgoController',
		data:{
			"id" : id,
			"tipoConsulta" : "obtenerPorId"
		},
		type : 'POST',
		datatype : 'json',
		success : function(data){
			$("#id").val(data.id);
			$("#provinciaId2").data("kendoMultiSelect").value(data.provinciaId);
			$("#ciudadId2").data("kendoMultiSelect").value(data.ciudadId);
			$("#tiposId").data("kendoMultiSelect").value(data.tiposId);
		}
	});
}
	
function enviarDatos(tipoConsulta){
	 var provincia=$("#provinciaId2 option:selected").val();
	 var ciudad=$("#ciudadId2 option:selected").val();
	 var tipo=$("#tiposId option:selected").val();
	 var id=$("#PPV_id").val();
	 	 
	$.ajax({
		url:'../PymeCiudadRiesgoController',
		data : {
			"provinciaId" : provincia,
			"ciudadId" : ciudad,
			"tipodId" : tipo,
			"tipoConsulta" : tipoConsulta,
			"id" : id
		},
		type : 'POST',
		async : false,
		datatype : 'json',
		success : function(data){
			var exito= data.success;
			if(exito=="true" || exito ===true){
				$("#msgPopup").show();
			}else{
				alert("Se produjo un error al guardar el ParametroPorPuntoVenta :" +data.mensaje);
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
							<h4 class="modal-title" id="myModalLabel">Pymes Riesgos Ciudades</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Los datos se guardaton con &eacute;xito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="PPV_id">										
								<label>Provincia:</label>
								<br>
								<select id="provinciaId2" data-placeholder="Seleccione la provincia" style="width: 100%"></select>	
								<label>Ciudad:</label>					
								<select id="ciudadId2" data-placeholder="Seleccione la ciudad" style="width: 100%"></select>
								<br>
								<label>Tipo Riesgo:</label>	
								<select id="tiposId" name="Plantilla"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" >											
								</select>								
								<br>																													
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
	
	<!-- ventana consulta -->
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador
								Riesgos por Ciudad</td>

					</tr>
					<tr>
						<th style="width:180px">Busqueda por Ubicación:</th>
							<th style="width: 100px">Provincia:</th>
							<th style="width: 300px">
							<select id="provinciaId" data-placeholder="Seleccione la provincia" style="width: 100%"></select> 
						</th>
							<th style="width: 100px">Ciudad: </th>
							<th style="width: 300px"><select id="ciudadId" data-placeholder="Seleccione la ciudad" style="width: 100%"></select></th>						
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
							<button  class="btn btn-primary" id="btnAprobacionMasiva" >Aprobación Masiva</button>
						</th>
					</tr>					
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
	
	
	<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>
	
	<!-- Datatable -->
	<div id="grid"></div>
	</div>
	<!-- Datatable -->
	
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

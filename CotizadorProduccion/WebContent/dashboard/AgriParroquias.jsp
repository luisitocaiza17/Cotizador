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
<title>Usuarios Offline Agricola</title>
<script>
var provinciaList = "";
var cantonList = "";
var provinciaList2 = "";
var cantonList2 = "";


$(document).ready(function(){
		activarMenu("AgriParroquias");
		
		$("#provinciaId").kendoMultiSelect({
			dataTextField : "nombre",
			dataValueField : "codigo",
			animation : false,
			maxSelectedItems : 1
		});
	    
	    provinciaList = $("#provinciaId").data(
		"kendoMultiSelect");
	    
	    $("#cantonId").kendoMultiSelect({
			dataTextField : "nombre",
			dataValueField : "id",
			animation : false,
			maxSelectedItems : 1
		});
	    
	    cantonList = $("#cantonId").data(
		"kendoMultiSelect");
	    
	    $("#provinciaId2").kendoMultiSelect({
			dataTextField : "nombre",
			dataValueField : "codigo",
			animation : false,
			maxSelectedItems : 1
		});
	    
	    provinciaList2 = $("#provinciaId2").data(
		"kendoMultiSelect");
	    
	    $("#cantonId2").kendoMultiSelect({
			dataTextField : "nombre",
			dataValueField : "id",
			animation : false,
			maxSelectedItems : 1
		});
	    
	    cantonList2 = $("#cantonId2").data(
		"kendoMultiSelect");
		
	    ConsultarProvincia();
	    ConsultarCanton();
	    
	    $("#provinciaId").change(function(){	
			 var provinciaIdentificador = $("#provinciaId option:selected").val();
			$("select option:selected").each(function(){
				
				$.ajax({
					url : '../AgriParroquiasController',
					data : {
						"tipoConsulta":"CargarParroquias",
						"provincia" : provinciaIdentificador							
					},
					type : 'POST',
					datatype : 'json',
					success : function(data){
						cantonList.dataSource.filter({});
						cantonList.setDataSource(data.CantonesArray);
					}
				});
			});
		});
	    
	    $("#provinciaId2").change(function(){	
			 var provinciaIdentificador = $("#provinciaId2 option:selected").val();
			$("select option:selected").each(function(){
				
				$.ajax({
					url : '../AgriParroquiasController',
					data : {
						"tipoConsulta":"CargarParroquias",
						"provincia" : provinciaIdentificador							
					},
					type : 'POST',
					datatype : 'json',
					success : function(data){
						cantonList2.dataSource.filter({});
						cantonList2.setDataSource(data.CantonesArray);
					}
				});
			});
		});
	    
	    $("#save-record").bind({click: function(){
			Cargar();
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
		    	  identificadorCot = $("#UsuarioId").val();
					
					if(identificadorCot === ""){
						tipoConsulta = "crear";
						
					}
					else
						tipoConsulta = "editar";
					
					enviarDatos(tipoConsulta);
		      }
			}
		});
	    
	    $("#ConsultaBtn").click(function(){
	 		Cargar();
	 		$('#grid').show();
	 	});		    
	    
});
	
	
	
function enviarDatos(tipoConsulta){
	 var cantonId2=$("#cantonId2 option:selected").val();
	 var parroquiaNombre=$("#parroquiaNombre").val();
	 var parroquiaSBS=$("#parroquiaSBS").val();
	 var parroquiaEnsurance=$("#parroquiaEnsurance").val();
	 var Id=$("#UsuarioId").val();	 
	$.ajax({
		url:'../AgriParroquiasController',
		data : {
			"id" : Id,
			"cantonId" : cantonId2,
			"parroquiaNombre" : parroquiaNombre,
			"parroquiaSBS" : parroquiaSBS,
			"parroquiaEnsurance" : parroquiaEnsurance,
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
	
	function fnEventoClick(e) {
	    e.preventDefault();
	    var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
	    //alert("Cotización id:"+dataItem.codigo);
	    $('#add').modal('show');
	    actualizar(dataItem.id,dataItem.provinciaId,dataItem.cantonId,dataItem.parroquia,dataItem.parroquiaSbs,dataItem.codigoEnsurance);
	}
	
	function actualizar(idUsuario,provinciaId,cantonIdentificador,parroquia,parroquiaSBS,parroquiaEnsurance){
		$("#cantonId2").data("kendoMultiSelect").value(cantonIdentificador);	
		$("#provinciaId2").data("kendoMultiSelect").value(provinciaId);		
		$("#parroquiaNombre").val(parroquia);
		$("#parroquiaSBS").val(parroquiaSBS);
		$("#parroquiaEnsurance").val(parroquiaEnsurance);
		$("#UsuarioId").val(idUsuario);
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
				url:'../AgriParroquiasController',
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
	
	function ConsultarCanton(){
		$.ajax({
			url:'../AgriParroquiasController',
			data:{"tipoConsulta":"CargarParroquiasTodas"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				cantonList2.dataSource.filter({});
				cantonList2.setDataSource(data.CantonesArray);				
			}
		});
	}
	
	
	function Cargar() {	
		// Validaciones de las busquedas	
		var cantonId = $("#cantonId option:selected").val();
		var provinciaId=$("#provinciaId option:selected").val();
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		$("#grid").kendoGrid({
			toolbar: ["excel"],
            excel: {
                fileName: "AgriParroquias.xlsx",
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
                		url : '../AgriParroquiasController',
                		data: {
                			"cantonId":cantonId,
                			"provinciaId":provinciaId,
    						"tipoConsulta":"encontrarPorParametros"
    					}
                	}
                },
                schema: {
                	data: "Data",
                	total: "Total",
                }
            },
            columns: [
      				{ field: "id", title: "ID.", width: "30px",headerAttributes: { style: "white-space: normal"}},
      				{ field: "provincia", title: "Provincia.", width: "120px",headerAttributes: { style: "white-space: normal"}},
      				{ field: "canton", title: "Canton.", width: "120px",headerAttributes: { style: "white-space: normal"}},
      				{ field: "parroquia", title: "Parroquia", width: "120px",headerAttributes: { style: "white-space: normal"}},
      				{ field: "parroquiaSbs", title: "ParroquiaSBS", width: "120px",headerAttributes: { style: "white-space: normal"}},
      				{ field: "codigoEnsurance", title: "codigoEnsurance", width: "120px",headerAttributes: { style: "white-space: normal"}},
      				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " Detalle ", width: "110px"},
	  				{ command: { text: "Eliminar", click: EliminarEventoClick}, title: " Eliminar ", width: "110px"}
	  				
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
							<h4 class="modal-title" id="myModalLabel">Usuarios Offline Agr&iacute;cola</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El usuario agr&iacute;cola se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
							<input type="hidden"class="form-control" id="UsuarioId">	
								<label>Provincia</label>
									<select id="provinciaId2" data-placeholder="Seleccione una opción..."></select>								
								<label>Canton</label>
									<select id="cantonId2" data-placeholder="Seleccione una opción..."></select>	
								<label>Parroquia</label>
									<input type="text" class="form-control required" id="parroquiaNombre" validationMessage="Campo requerido!!!" required>
								<label>ParroquiaSBS</label>
									<input type="text" class="form-control required" id="parroquiaSBS" validationMessage="Campo requerido!!!" required>
								<label>Parroquia Ensurance</label>
									<input type="text" class="form-control required" id="parroquiaEnsurance" validationMessage="Campo requerido!!!" required>
																													
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
	
	
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table" style="width: 100%"> 
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador de Parroquias Agricola.</center></td>

					</tr>
								
					<tr>
						<th style="width: 20%">Busqueda por:</th>
						<th style="width: 40%">Tipo Provincia: <select id="provinciaId" data-placeholder="Seleccione una opción..."></select></th>	
						<th style="width: 40%">Tipo Canton: <select id="cantonId" data-placeholder="Seleccione una opción..."></select></th>						
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
						<th>&nbsp;</th>
					</tr>
					<tr>
					
						<th>&nbsp;</th>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere.....
								</div>
							</div></th>
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
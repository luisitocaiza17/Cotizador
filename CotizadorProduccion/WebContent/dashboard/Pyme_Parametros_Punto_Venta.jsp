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
<title>Parametro Por Punto Venta</title>
<script>
var notification;
var plantillaList = "";
var puntosVentaList = "";

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
	activarMenu("Pyme_Parametros_Punto_Venta");	
	
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

	/*Mensaje de error*/
	notification = $("#notification").kendoNotification({
    position: {
        pinned: true,
        top: 90,
        right: 35
    },
    autoHideAfter: 10000,
    hideOnClick: false,
    stacking: "down",
    templates: [ {
        type: "error",
        template: $("#errorTemplate").html()
		}]
    }).data("kendoNotification");

 $(document).one("kendo:pageUnload", function(){ });
 
	
	Cargar();
	CargarCombos();
	
	plantillaList = $("#Plantilla").data(
	"kendoMultiSelect");
	puntosVentaList = $("#PuntoVenta").data(
	"kendoMultiSelect");
	
	
	
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
	    	  identificadorCot = $("#PPV_id").val();
				
				if(identificadorCot === ""){
					tipoConsulta = "crear";
					
				}
				else
					tipoConsulta = "editar";
				
				enviarDatos(tipoConsulta);
	      }
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
	
	
	function CargarCombos() {
		$.ajax({
			url:'../PymeParametrosPuntoVentaController',
			data:{
				"tipoConsulta" : "cargarCombos"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data){
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				/*Cargo Puntos Venta*/
				puntosVentaList.dataSource.filter({});
				puntosVentaList.setDataSource(data.puntoVentaArray);
				/*Cargo el select plantillas*/
				plantillaList.dataSource.filter({});
				plantillaList.setDataSource(data.plantillaArray);				
			}
		});
	}
	
	function BusquedaCedula(){
		 var cedula=$("#identificacion").val();
		 $.ajax({
				url:'../PymeParametrosPuntoVentaController',
				data:{
					"tipoConsulta" : "cargarUsuario",
					"identificacion" : cedula
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					$("#usuarioNombre").val(data.nombreUsuario);									
				}
			});
		 
	}
	
	function Cargar() {	
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
	            		url: "../PymeParametrosPuntoVentaController",
	            		data: {
	            			"tipoConsulta":"buscarTodos"
						}
	            	}
	            },
	            schema: {
	            	data: "Data",
	            	total: "Total",
	            }
	        },
	        columns: [
	  				{ field: "parametroPuntoVentaId", title: "PPV Id.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "puntoVenta", title: "Punto Venta", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "entidad", title: "Entidad.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "PlantillaNombre", title: "Plantilla.", width: "200px",headerAttributes: { style: "white-space: normal"}},
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
        $("#PPV_id").val(dataItem.parametroPuntoVentaId);
        actualizar(dataItem.parametroPuntoVentaId);
    }
	
	function EliminarEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        eliminar(dataItem.parametroPuntoVentaId);
    }
	
	function eliminar(id){
		var r = confirm("Seguro que desea eliminar");
		if(r == true){			
			$.ajax({
				url:'../PymeParametrosPuntoVentaController',
				data:{
					"idPPV" : id,
					"tipoConsulta" : "eliminar"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					else{
						alert("Eliminado");
					}
				}
			});
		}
		Cargar();
		$("#msgPopup").hide();
	}
	
	
function actualizar(idUsuario){
	$.ajax({
		url:'../PymeParametrosPuntoVentaController',
		data:{
			"idPPV" : idUsuario,
			"tipoConsulta" : "obtenerPorId"
		},
		type : 'POST',
		datatype : 'json',
		success : function(data){
			if (data.success == false){
				 notification.show({
                      title: "ERROR",
                      message: data.error
                  }, "error");							
				return;
			}
			$("#idPPV").val(data.idPPV);
			$("#identificacion").val(data.identificacion);
			$("#usuarioNombre").val(data.nombres);
			$("#Plantilla").data("kendoMultiSelect").value(data.Plantilla);
			$("#PuntoVenta").data("kendoMultiSelect").value(data.PuntoVenta);
		}
	});
}
	
function enviarDatos(tipoConsulta){
	 var plantillaEnvio=$("#Plantilla option:selected").val();
	 var puntoVentaEnvio=$("#PuntoVenta option:selected").val();
	 var identificacion=$("#identificacion").val();
	 var IdPPV=$("#PPV_id").val();
	 	 
	$.ajax({
		url:'../PymeParametrosPuntoVentaController',
		data : {
			"plantillaEnvio" : plantillaEnvio,
			"puntoVentaEnvio" : puntoVentaEnvio,
			"identificacion" : identificacion,
			"tipoConsulta" : tipoConsulta,
			"IdPPV" : IdPPV
		},
		type : 'POST',
		async : false,
		datatype : 'json',
		success : function(data){
			
			if (data.success == false){
				 notification.show({
                      title: "ERROR",
                      message: data.error
                  }, "error");							
				return;
			}else{
				$("#msgPopup").show();
			}
		}
	});
}

</script>
<script id="errorTemplate" type="text/x-kendo-template">
  <div class="wrong-pass">
      <img src="../static/images/error-icon.png" width="60" height="60"/>
         <h3>#= title #</h3>
      <p>#= message #</p>
  </div>
</script>
<style>
                .demo-section p {
                    margin: 3px 0 3px;
                    line-height: 50px;
                }
                .demo-section .k-button {
                    width: 200px;
                }

                .k-notification {
                    border: 0;
                }
                /* Error template */
                .k-notification-error.k-group {
                    background: rgba(100%,0%,0%,.7);
                    color: #ffffff;
                }
                .wrong-pass {
                    width: 400px;
                    height: 50px;
                }
                .wrong-pass h3 {
                    font-size: 1em;
                    padding: 36px 14px 9px;
                }
                .wrong-pass img {
                    float: left;
                    margin: 30px 15px 30px 30px;
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
	<span id="notification" style="display:none;"></span>
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
							<h4 class="modal-title" id="myModalLabel">Parametro por Punto Venta</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Los datos se guardaton con &eacute;xito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="PPV_id">										
								<label>C&eacute;dula Usuario:</label>
									<input type="text" class="form-control required" id="identificacion" validationMessage="Campo requerido!!!" required>
								
								<label>Nombre Usuario</label>
									<input type="text" class="form-control required" id="usuarioNombre" validationMessage="Campo requerido!!!" required>
								
								<label>Plantilla Nombre</label>
								<select id="Plantilla" name="Plantilla"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required  onchange="BusquedaCedula();">											
								</select>
								
								<br>
								<label>Punto Venta</label>
								<select id="PuntoVenta" name="PuntoVenta"
									data-placeholder="Seleccione una opción..." validationMessage="Campo requerido!!!" required onchange="BusquedaCedula();">											
								</select>
								<br>							
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

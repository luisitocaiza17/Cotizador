<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<!-- <script src="../static/js/jquery.min.js"></script> -->
	
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
    
<title>Notificaciones PYMES - Cotizador QBE</title>
<script>

	$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	

	var notificacionId = "";
	var proceso = "";
	var notificaCliente = "";
	var notificaUsuario = "";
	var notificaInspector = "";
	var notificaEjecutivo = "";
	var emailInspector = "";
	var plantillaNombre = "";
	var tipoConsulta="";
	var notification;
	$(document).ready(function(){
		activarMenu("Notificaciones");	
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
		cargar();
		
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
		    	  
		    	  if(notificacionId == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "editar";
		    	  
		    	  notificacionId = $("#notificacionId").val();
		    	  proceso = $("#proceso").val();
		    	  notificacionCliente = $("#notificaCliente").val();
		    	  notificacionUsuario = $("#notificaUsuario").val();
		    	  notificacionInspector = $("#notificaInspector").val();
		    	  notificaEjecutivo = $("#notificaEjecutivo").val();
		    	  emailInspector = $("#emailInspector").val();
		    	  plantillaNombre = $("#plantillaNombre").val();
				  enviarDatos(notificacionId,proceso,notificacionCliente,notificacionUsuario,notificacionInspector,emailInspector,plantillaNombre,tipoConsulta,notificaEjecutivo);
		      }
			}
		});
	});
	
	function actualizar(notificacion){
		$.ajax({
			url:'../NotificacionController',
			data:{
				"notificacionId" : notificacion,
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
				$("#notificacionId").val(data.notificacionId);
				notificacionId=data.notificacionId;
				$("#proceso").val(data.proceso);
				if(data.notificacionCliente)
					$("#notificaCliente").val("1");
				else
					$("#notificaCliente").val("0");
				if(data.notificacionUsuario)
					$("#notificaUsuario").val("1");
				else
					$("#notificaUsuario").val("0");
				if(data.notificacionInspector)
					$("#notificaInspector").val("1");
				else
					$("#notificaInspector").val("0");
				if(data.notificacionEjecutivo)
					$("#notificaEjecutivo").val("1");
				else
					$("#notificaEjecutivo").val("0");
				$("#emailInspector").val(data.emailInspector);
				$("#plantillaNombre").val(data.plantillaNombre);
				
			}
		});
	}
	
	function eliminar(notificacion){
		var r = confirm("Seguro que desea eliminar");
		if(r == true){			
			notificacionId = notificacion;
			proceso="";
			notificacionCliente="";
			notificacionUsuario="";
			notificacionInspector="";
			notificacionEjecutivo="";
			emailInspector="";
			plantillaNombre="";
			tipoConsulta="eliminar";
			enviarDatos(notificacionId,proceso,notificacionCliente,notificacionUsuario,notificacionInspector,emailInspector,plantillaNombre,tipoConsulta,notificacionEjecutivo);
		}
		cargar();
	}
	
	function enviarDatos(notificacionId,proceso,notificacionCliente,notificacionUsuario,notificacionInspector,emailInspector,plantillaNombre,tipoConsulta,notificacionEjecutivo){
		$.ajax({
			url:'../NotificacionController',
			data : {
				"notificacionId" : notificacionId,
				"proceso" : proceso,
				"notificacionCliente" : notificacionCliente,
				"notificacionUsuario" : notificacionUsuario,
				"notificacionInspector" : notificacionInspector,
				"notificacionEjecutivo" : notificacionEjecutivo,
				"emailInspector" : emailInspector,
				"plantillaNombre" : plantillaNombre,
				"tipoConsulta" : tipoConsulta
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
				}	
				$("#msgPopup").show();
			}
		});
	}
	
	/*Inicio cargar datos en la tabla principal*/
	function cargar(){
		$.ajax({
			url: '../NotificacionController',
			data : {
				'tipoConsulta' : 'encontrarTodos'
			},
			type : 'post',
			datatype : 'json',
			async :false,
			success : function(data){
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}	
				var notificacionJSONArray = data.notificacionJSONArray;
				$.each(notificacionJSONArray, function(index){
					$('#configuracionCanal').append("<tr class='odd gradeX'>"+
							"<td relation='Proceso'>"+notificacionJSONArray[index].proceso+"</td>"+
							"<td width='175px'>"+									
							"<input type='hidden' id='notificacionId' value='" + notificacionJSONArray[index].notificacionId + "'/>"+	
							"<button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+notificacionJSONArray[index].notificacionId+");'>" +
							"<span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
							"</button>" +									
						"</td>" +
					"</tr>");
				});
				
				$("#loading").remove();
			}
		});
	}
	/*Fin cargar datos en la tabla principal*/
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
							<h4 class="modal-title" id="myModalLabel">Notificaciones</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La notificación se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden" class="form-control" id="notificacionId">
								<br/>
								<br/>
								<label>Proceso:</label>
								<input type="text" id="proceso"  required="required" disabled="disabled" style="width: 250px">
								<br/>
								<br/>
								<label>Notificar al Cliente?:</label>
								<select id="notificaCliente"  required="required">
									<option value="1">SI</option>
									<option value="0">NO</option>
								</select>
								<br/>
								<br/>
								<label>Notificar al Usuario?:</label>
								<select id="notificaUsuario"  required="required">
									<option value="1">SI</option>
									<option value="0">NO</option>
								</select>
								<br/>
								<br/>
								<label>Notificar al Inspector?:</label>
								<select id="notificaInspector"  required="required">
									<option value="1">SI</option>
									<option value="0">NO</option>
								</select>
								<br/>
								<br/>
								<label>Notificar al Ejecutivo?:</label>
								<select id="notificaEjecutivo"  required="required">
									<option value="1">SI</option>
									<option value="0">NO</option>
								</select>
								<br/>
								<br/>
								<label>Email Ejecutivo:</label>
								<input type="text" id="emailInspector"  required="required" style="width: 200px">
								<br/>
								<br/>
								<label>Plantilla de Email:</label>
								<input type="text" id="plantillaNombre"  required="required" style="width: 200px">
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
										<th>Proceso</th>															
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionCanal" class="searchable">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
		<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
		<link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
		<link rel="stylesheet" href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css">
		<link rel="stylesheet" href="../static/css/select2/select2.css">
		
		<script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
		<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
		<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
		<script src="../static/js/jquery.validate.js"></script>
		<script src="../static/js/bootstrap.min.js"></script>
		<script src="../static/js/util.js"></script>
		<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
		<script src="../static/js/jquery-dynamic-url/jquery.dynamic-url.js"></script>
		<script src="../static/js/select2.js"></script>
		
		<!--  	para el tooltipster -->
		<script src="../static/js/jquery-ui/jquery-ui.js"></script>
		<link href="../static/js/jquery-ui/jquery-ui.theme.css" rel="stylesheet" type="text/css" />
		<link href="../static/css/tooltipster.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
		<script type="text/javascript" src="../static/js/jquery.tooltipster.min.js"></script>



<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
	
	<script>
		var provinciaId = "";
		var ciudadId = "";
		var entidadId = "";
		//var matriz = 0;
		//var activo = 0;
		var tipoConsulta = "";
		//var arrTipoIdentificacion = new Array();
		//var arrCodigoTipoIdentificacion = new Object();
		var listaCiudad = "";
		var a = "";
		var b = "";
		var c = "";
		var ArrayInspectorDetalle = new Array();
		var auxCiudadId = "";
		var auxProvinciaId = "";
		var notification2;
		
		$(document).ready(function() {
			activarMenu("InspectorProvincia");
			$("#listaCiudad").kendoMultiSelect({
				dataTextField: "nombre",
	            dataValueField: "ciudadId",
	            animation: false,
	            maxSelectedItems: 1
			});
			a = $("#listaCiudad").data("kendoMultiSelect");
			
			$("#listaEntidad").kendoMultiSelect({
				dataTextField: "nombre",
	            dataValueField: "usuarioId",
	            animation: false,
	            //dataSource: listaEntidad,
	            maxSelectedItems: 1
	        });			    
			b = $("#listaEntidad").data("kendoMultiSelect");			
					    	
			$("#listaProvincia").kendoMultiSelect({			    		
	    		dataTextField: "provinciaNombre",
	            dataValueField: "provinciaId",
	            animation: false,
	           // dataSource: listaProvincia,
	            maxSelectedItems: 1
	        });
			c = $("#listaProvincia").data("kendoMultiSelect");
			
			/*Mensaje de error*/
			notification2 = $("#notification2").kendoNotification({
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
			
			$("#save-record").click(function(){				
											
				tipoConsulta = "crear";
				entidadId = $("#listaEntidad option:selected").val();
				if(entidadId >= 0){
					var contador = 0;
	                 $("#dataTableInspector2 tr").each(function (index) {
	                     auxProvinciaId = $(this).find("td").eq(0).html();
	                     auxCiudadId = $(this).find("td").eq(2).html();
	                     var datalleInspector = {
	                         provinciaId: auxProvinciaId,
	                         ciudadId: auxCiudadId
	                     };
	                     ArrayInspectorDetalle[contador] = datalleInspector;
	                     contador++;
	                  });
	              	if(contador <= 0){
	              		alert("Ingrese el detalle de la configuaración");
	              	}else{
	              		enviarDatos(entidadId, ArrayInspectorDetalle, tipoConsulta);
	              		ArrayInspectorDetalle = [];
	              	}
				}else{
					alert("Por favor ingrese el campo requerido");		
					b.focus();
				}		
			});
			
			$("#listaProvincia").change(function(){
				provinciaId = $("#listaProvincia option:selected").val();
				$.ajax({
					url : '../PymeInspectorProvinciaController',
					data : {
						"provinciaId" : provinciaId,
						"tipoConsulta" : "buscarPorProvincia"
						
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if (data.success == false){
							 notification2.show({
		                           title: "ERROR",
		                           message: data.error
		                       }, "error");							
							return;
						}
						listaCiudad = data.listaCiudad;				
						cargarCiudad();											
					}
				});
			});	
			
			
			$("#listaEntidad").change(function(){
				entidadId = $("#listaEntidad option:selected").val();
				actualizar(entidadId);
			});	
			
			$("#agregar").click(function(){
				
				var bandera = false;
				var auxProvinciaId = $("#listaProvincia option:selected").val();
				var auxProvinciaNombre = $("#listaProvincia option:selected").text();
				var auxCiudadId2 = $("#listaCiudad option:selected").val();
				var auxCiudadNombre = $("#listaCiudad option:selected").text();
				
				$("#dataTableInspector2 tr").each(function (index) {                    
                    auxCiudadId = $(this).find("td").eq(2).html();
                    if(auxCiudadId2 == auxCiudadId){
                    	bandera = true;
                    	return false;
                    }else{
                    	bandera = false;
                    }
                });
				if(bandera){
					alert("La ciudad ya esta en la lista.");
				}else{
					if((auxProvinciaId > 0) && (auxCiudadId2 > 0)){
						$("#dataTableInspector2").append("<tr class='odd gradeX'>" +
								" <td relation='provinciaId' style='display:none;' >"+ auxProvinciaId +"</td>" +
								" <td relation='provinciaNombre' style='width: 40%'>"+ auxProvinciaNombre+"</td>" +
								" <td relation='ciudadId' style='display:none;''>"+ auxCiudadId2 +"</td>" +
								" <td relation='ciudadNombre' style='width: 40%'>"+ auxCiudadNombre+"</td>" +
								" <td style='width: 20%'>" +	
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn eliminar'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +
										
								"</td>" +
							"</tr>");	
					}else{
						alert("Por favor seleccionar al menos una provincia y una ciudad.");
					}		
					$("#listaProvincia").data("kendoMultiSelect").value("");
					$("#listaCiudad").data("kendoMultiSelect").value("");
					
					 c.dataSource.filter({});
					//$("#listaProvincia").val();
					//$("#listaCiudad").val();			
					
				}				
				$(".eliminar").click(function(){
					$(this).parent().parent().remove();
				});
				
			});
			
			
			$("#addButton").click(function(){
				$("#dataTableInspector2").children().remove();
				//$("#listaEntidad").data("kendoMultiSelect").readonly(false);	
			});
			
			
		});	
		function cargarCiudad(){
	        a.dataSource.filter({});
	        a.setDataSource(listaCiudad);			
		}
			
			
		function cargar(){
			$("#listaEntidad").children().remove();
			$("#listaProvincia").children().remove();
			$("#dataTableContent").children().remove();
			$.ajax({
				url : '../PymeInspectorProvinciaController',
				data : {
					"tipoConsulta" : "buscarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if (data.success == false){
						 notification2.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					$("#loading").hide();
					var listaEntidad = data.listaEntidad;
					var listaInspector = data.listaInspector;	
					var listaProvincia = data.listaProvincia;
					
				 	b.dataSource.filter({});
			        b.setDataSource(listaEntidad);	
			        
			        c.dataSource.filter({});
			        c.setDataSource(listaProvincia);	
					
					/*$("#listaEntidad").kendoMultiSelect({
						dataTextField: "nombre",
			            dataValueField: "entidadId",
			            animation: false,
			            dataSource: listaEntidad,
			            maxSelectedItems: 1
			        });			    
					
					var listaProvincia = data.listaProvincia;				    	
					$("#listaProvincia").kendoMultiSelect({			    		
			    		dataTextField: "provinciaNombre",
			            dataValueField: "provinciaId",
			            animation: false,
			            dataSource: listaProvincia,
			            maxSelectedItems: 1
			        });*/
			    	 
			    	
					$.each(listaInspector, function(index){
						$("#dataTableContent").append("	<tr class='odd gradeX'>" +
								" <td relation='entidadId'>"+ listaInspector[index].nombreCompleto+"</td>" +
								" <td relation='nombreCompleto'>"+ listaInspector[index].login +"</td>" +
								" <td width='175px'>" +
								" <input type='hidden' value='"+ listaInspector[index].usuarioId +"'/>" +
									" <button type='button' class='btn btn-success btn-xs actualizar-btn' onClick='actualizar("+listaInspector[index].usuarioId+");' data-toggle='modal' data-target='#add'>" +
	  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
									" </button>" +
										" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onClick='eliminar("+listaInspector[index].usuarioId+");'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +
										
								"</td>" +
							"</tr>");					
					});		
				}
			});	
		}
		
		function actualizar(entiadadIdArg){
			$("#dataTableInspector2").children().remove();
			$.ajax({
				url : '../PymeInspectorProvinciaController',
				data : {
					"tipoConsulta" : "buscarPorId",
					"entidadId" : entiadadIdArg
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if (data.success == false){
						 notification2.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					$("#listaEntidad").data("kendoMultiSelect").value(data.entidadId);
					
					var detalleInspector = data.detalleInspector;
					/*if(detalleInspector.length > 0){
						$("#listaEntidad").data("kendoMultiSelect").readonly(true);
					}else{
						$("#listaEntidad").data("kendoMultiSelect").readonly(false);
					}*/
					
					$.each(detalleInspector, function(index){						
						$("#dataTableInspector2").append("	<tr class='odd gradeX'>" +
								" <td relation='provinicaId' style='display:none;' >"+ detalleInspector[index].provinciaId +"</td>" +
								" <td relation='nombreprovinica' style='width: 40%'>"+ detalleInspector[index].proviniciaNombre+"</td>" +
								" <td relation='ciudadId' style='display:none;' >"+ detalleInspector[index].ciudadId +"</td>" +
								" <td relation='nombreCiudad' style='width: 40%'>"+ detalleInspector[index].ciudadNombre+"</td>" +
								" <td style='width: 20%'>" +	
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn eliminar'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +
										
								"</td>" +
							"</tr>");
						
						$(".eliminar").click(function(){
							$(this).parent().parent().remove();
						});
					});		
				}
			});	
		}
		
		function eliminar(usuarioIdArg){
			var r = confirm("Esta seguro que desea eliminar la configuración");
			if(r){
				$.ajax({
					url : '../PymeInspectorProvinciaController',
					data : {
						"tipoConsulta" : "eliminar",
						"entidadId" : usuarioIdArg
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if (data.success == false){
							 notification2.show({
		                           title: "ERROR",
		                           message: data.error
		                       }, "error");							
							return;
						}
						$("#loading").show();
					}
				});	
				cargar();
			}
			
		}
		
		function enviarDatos(entidadId, configuracionArray, tipoConsulta){
			$.ajax({
				url:'../PymeInspectorProvinciaController',
				data:{
					"entidadId" :entidadId,
					"configuracionArray": JSON.stringify(configuracionArray),
					"tipoConsulta":tipoConsulta
				},
				type:'POST',
				datatype : 'json',
				success : function(data){
					if (data.success == false){
						 notification2.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					if(data.success)
						$("#msgPopup").show();
					else
						alert(data.error);
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
	<span id="notification2" style="display:none;"></span>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">INSPECTOR POR PROVINCIA</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Grabado con exito.</div>
							<div class="form-group">
									<label>Número de Identificación</label> 							
									<div id="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div>
									<label>Tipo de Identificación</label> 
									<select id="listaEntidad" data-placeholder="Seleccione una opción..." class="required">								  
									</select>	
									<table>
										<tr>
											<td style="width: 40%">
												<label>Provincia</label>
											</td>
											<td>
												<label>Ciudad</label>
											</td style="width: 40%">
											<td style="width: 20%"></td>
										</tr>
										<tr>
											<td style="width: 40%">
												<select id="listaProvincia" data-placeholder="Seleccione una opción...">								  							  
												</select>
											</td>
											<td style="width: 40%">
												<select id="listaCiudad" data-placeholder="Seleccione una opción...">								  								  
												</select>
											</td>
											<td style="width: 20%">
												<button type="button" class="btn btn-primary" id="agregar">Agregar</button>
											</td>
										</tr>
									</table>					
									
									<br>
									<br>
									<table class="table table-striped table-bordered table-hover"
										id="dataTableInspector1" style="font-size: x-small;">
										<thead>
											<tr>												
												<th style="display: none;">ProvinciaId</th>
												<th style='width: 40%'>Provincia</th>
												<th style="display: none;">CiudadId</th>
												<th style='width: 40%'>Ciudad</th>																		
												<th style='width: 20%'></th>
											</tr>
										</thead>
										<tbody id="dataTableInspector2" style="font-size: x-small;">	
										</tbody>
									</table>					
							</div>
						</div>
									
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar</button>
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
			<div class="table-responsive">
			<br>
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>
			<table class="table table-striped table-bordered table-hover id="dataTable">
					<thead>
						<tr>
							<th>Inspector</th>
							<th>Usuario</th>						
							<th></th>
						</tr>
					</thead>
						<tbody id="dataTableContent" class="searchable">
					
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
	<!-- Datatable -->	
	
</body>
</html>

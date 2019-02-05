<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
	<script src="../static/js/Kendo/kendo.all.min.js"></script>
	<script src="../static/js/Kendo/kendo.web.min.js"></script>	
	<title>Cobertura PYMEs - Cotizador QBE</title>
	
	<script>
	
	 $(function(){
	  	  $(".wrapper1").scroll(function(){
	  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  	  });
	  	  $(".wrapper2").scroll(function(){
	  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  	  });
	  	});
	 
	 	var tipoConsulta = "";
	 	var coberturaPymesId = "";
	 	var grupoCoberturaId = "";	 
	 	var nombre = "";
	 	var ramoId = "";
	 	var tipoCoberturaId = "";	 	 	
		var arrCoberturaPymes = new Array();
		var notification;
		
		$(document).ready(function(){
			activarMenu("CoberturasPymes");
			
			$(':input[type="number"]').numeric("");		
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
				
				$(".required").css("border", "1px solid #ccc");
				$(".required").each(function(index) {
					var cadena = $(this).val();
					if (cadena .length <= 0) {
						$(this).css("border", "1px solid red");
						alert("Por favor ingrese el campo requerido");
						$(this).focus();
						return false;
					}		
				});	
								
				coberturaPymesId = $("#coberturaPymesId").val();
			 	grupoCoberturaId = $("#grupoCoberturaId").val(); 
			 	nombre = $("#nombre").val();
			 	ramoId = $("#ramoId").val();
			 	tipoCoberturaId = $("#tipoCoberturaId").val();			 	
			 	
				if(coberturaPymesId=="" || coberturaPymesId== 0)
					tipoConsulta = "crear";
				else
					tipoConsulta = "actualizar";
				
				enviarDatos(coberturaPymesId,grupoCoberturaId,nombre,ramoId,tipoCoberturaId,tipoConsulta);
				}			
				
			});	
			
			$("#ramoId").change(function(){				
				$("select option:selected").each(function(){
					ramoId = $("#ramoId").val();
					$.ajax({
						url : '../PymeCoberturaController',
						data : {
							"tipoConsulta":"cargarGruposCobertura",
							"ramoId" : ramoId							
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
							$("#grupoCoberturaId").children().remove();
							$("#grupoCoberturaId").append("<option>Seleccione una opción</option>");
							var grupoCoberturaArr = data.grupoCoberturaArr;
							$.each(grupoCoberturaArr, function (index){								
								$("#grupoCoberturaId").append("<option value='"+ grupoCoberturaArr[index].gCoberturaId +"'>"+ grupoCoberturaArr[index].gCoberturaNombre +"</option>");
							});
						}
					});
				});
			});	
		});
		
		function actualizar(coberturaPId){			
			$.ajax({
				url : '../PymeCoberturaController',
				async : false,
				data : {
					"coberturaPymesId" : coberturaPId,
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
					$("#grupoCoberturaId").children().remove();
					$("#grupoCoberturaId").append("<option>Seleccione una opción</option>");
					var grupoCoberturaArr = data.grupoCoberturaArr;
					$.each(grupoCoberturaArr, function (index){								
						$("#grupoCoberturaId").append("<option value='"+ grupoCoberturaArr[index].gCoberturaId +"'>"+ grupoCoberturaArr[index].gCoberturaNombre +"</option>");
					});
					
					$("#coberturaPymesId").val(data.coberturaPymesId);
					$("#grupoCoberturaId").val(data.grupoCoberturaId);	 
					$("#nombre").val(data.nombre);
					$("#ramoId").val(data.ramoId);
					$("#tipoCoberturaId").val(data.tipoCoberturaId);					
					$("#tipoConsulta").val(data.tipoConsulta);
				}
			});			
		}
		
		function eliminar(coberturaId){
			var r = confirm("Seguro que desea eliminar");
			if(r == true){
				coberturaPymesId = coberturaId;
			 	grupoCoberturaId = "";	 
			 	nombre = "";
			 	ramoId = "";
			 	tipoCoberturaId = "";
			 	tipoConsulta = "eliminar";
			 	enviarDatos(coberturaPymesId,grupoCoberturaId,nombre,ramoId,tipoCoberturaId,tipoConsulta);			 	
			}
			cargar();
		}
		
		function enviarDatos(coberturaPymesId,grupoCoberturaId,nombre,ramoId,tipoCoberturaId,tipoConsulta){
			$.ajax({
				url : '../PymeCoberturaController',
				async : false,
				data : {
					"coberturaPymesId" : coberturaPymesId,
				 	"grupoCoberturaId" : grupoCoberturaId,	 
				 	"nombre" : nombre,
				 	"ramoId" : ramoId,
				 	"tipoCoberturaId" : tipoCoberturaId,				 	
				 	"tipoConsulta" : tipoConsulta
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
					$("#msgPopup").show();
				}
			});
		}
		
		function cargar(){
			$("#configuracionCanal").children().remove();
			$.ajax({
				url : '../PymeCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					var listadoCoberturaPymes = data.listadoCoberturaPymes;
					$.each(listadoCoberturaPymes, function (index){
						$("#configuracionCanal").append("<tr class='odd gradeX'>"+
								"<td relation='ramo'>"+listadoCoberturaPymes[index].ramoId+"</td>"+
								"<td relation='grupoCobertura' >" + listadoCoberturaPymes[index].grupoCoberturaId + "</td>"+
								"<td relation='tipoCobertura' >" + listadoCoberturaPymes[index].tipoCoberturaId + "</td>"+	
								"<td relation='nombre' >" + listadoCoberturaPymes[index].nombre + "</td>"+															
								"<td width='175px'>"+									
									"<input type='hidden' id='coberturaPymesId' value='" + listadoCoberturaPymes[index].coberturaPymesId + "'/>"+	
									" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+ listadoCoberturaPymes[index].coberturaPymesId +")'>" +
  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
									" </button>" +									
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+ listadoCoberturaPymes[index].coberturaPymesId +")'>" +
									  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +									
								"</td>" +
							"</tr>");		
					});				
					$("#loading").remove();	
					var ramoArr = data.ramoArr;
					$.each(ramoArr, function (index){
						$("#ramoId").append("<option value='"+ ramoArr[index].ramoId +"'>"+ ramoArr[index].ramoNombre +"</option>");
					});
					
					var tipoCoberturaArr = data.tipoCoberturaArr;
					$.each(tipoCoberturaArr, function (index){
						$("#tipoCoberturaId").append("<option value='"+ tipoCoberturaArr[index].tCoberturaId +"'>"+ tipoCoberturaArr[index].tCoberturaNombre +"</option>");
					});
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
							<h4 class="modal-title" id="myModalLabel">Cobertura PYMES</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La cobertura se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="coberturaPymesId">
								<label>Ramo</label>
								<select id="ramoId" class="form-control required">
								<option>Seleccione una opción.</option>
								</select>
								<label>Grupo Cobertura</label> 
								<select id="grupoCoberturaId" class="form-control required">
								<option>Seleccione una opción.</option>
								</select>
								<label>Tipo Cobertura</label>
								<select id="tipoCoberturaId" class="form-control required">
								<option>Seleccione una opción.</option>
								</select>
								<label>Nombre</label> 
								<input type="text"class="form-control required" id="nombre">																								
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
										<th>Ramo</th>
										<th>Grupo Cobertura</th>										
										<th>Tipo Cobertura</th>
										<th>Nombre</th>																	
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
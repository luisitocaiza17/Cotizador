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
	var parametroGrupoProductoId = "";
	var grupoPorProductoId = "";
	var mostrarValorEstructuras = "";
	var mostrarValorMueblesEnseres = "";
	var mostrarMaquinaria = "";
	var mostrarValorMercaderia = "";
	var mostrarValorInsumos = "";
	var mostrarValorEquipoHerramienta = "";
	var mostrarValorContenidos = "";
	var grupoPorProductoId = "";
	var limiteAsegurado = "";
	var primaMinima = "";
	var notification;
	
	$(document).ready(function(){
		activarMenu("ParametrosPymesGrupoProducto");
		
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
			
			parametroGrupoProductoId = $("#parametroGrupoProductoId").val();
			grupoPorProductoId = $("#grupoPorProductoId").val();
			
			if($("#mostrarValorEstructuras").is(":checked")){
				mostrarValorEstructuras = true;
			}else{
				mostrarValorEstructuras = false;
			}
			
			
			if($("#mostrarValorMueblesEnseres").is(":checked")){
				mostrarValorMueblesEnseres = true;
			}else{
				mostrarValorMueblesEnseres = false;
			}

			if($("#mostrarMaquinaria").is(":checked")){
				mostrarMaquinaria = true;
			}else{
				mostrarMaquinaria = false;
			}
			
			if($("#mostrarValorMercaderia").is(":checked")){
				mostrarValorMercaderia = true;
			}else{
				mostrarValorMercaderia = false;
			}
			
			if($("#mostrarValorInsumos").is(":checked")){
				mostrarValorInsumos = true;
			}else{
				mostrarValorInsumos = false;
			} 
			 
			if($("#mostrarValorEquipoHerramienta").is(":checked")){
				mostrarValorEquipoHerramienta = true;
			}else{
				mostrarValorEquipoHerramienta = false;
			}
			
			if($("#mostrarValorContenidos").is(":checked")){
				mostrarValorContenidos = true;
			}else{
				mostrarValorContenidos = false;
			}
			
			limiteAsegurado=$("#limite_asegurado").val();
			primaMinima=$("#prima_minima").val();
			
			if(parametroGrupoProductoId == "")
				tipoConsulta = "crear";
			else
				tipoConsulta = "actualizar";
			
			enviarDatos(parametroGrupoProductoId,grupoPorProductoId,mostrarValorEstructuras,mostrarValorMueblesEnseres,mostrarMaquinaria,mostrarValorMercaderia,
					mostrarValorInsumos,mostrarValorEquipoHerramienta,tipoConsulta,limiteAsegurado,primaMinima,mostrarValorContenidos);
			}
		});
	});
	
	
	function actualizar(parametroGP){
		$.ajax({
			url:'../PymeParametroXGrupoPorProductoController',
			async : false,
			data:{
				"parametroGrupoProductoId" : parametroGP,
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
				$("#parametroGrupoProductoId").val(data.parametroGrupoProductoId);
				$("#grupoPorProductoId").val(data.grupoPorProductoId);
				if(data.mostrarValorEstructuras){
					$("#mostrarValorEstructuras").attr('checked', true);
				}else{
					$("#mostrarValorEstructuras").attr('checked', false);
				}
				
				if(data.mostrarValorMueblesEnseres){
					$("#mostrarValorMueblesEnseres").attr('checked', true);
				}else{
					$("#mostrarValorMueblesEnseres").attr('checked', false);
				}
				
				if(data.mostrarMaquinaria){
					$("#mostrarMaquinaria").attr('checked', true);
				}else{
					$("#mostrarMaquinaria").attr('checked', false);
				}

				if(data.mostrarValorMercaderia){
					$("#mostrarValorMercaderia").attr('checked', true);
				}else{
					$("#mostrarValorMercaderia").attr('checked', false);
				}
				
				
				if(data.mostrarValorInsumos){
					$("#mostrarValorInsumos").attr('checked', true);
				}else{
					$("#mostrarValorInsumos").attr('checked', false);
				}
				
				if(data.mostrarValorEquipoHerramienta){
					$("#mostrarValorEquipoHerramienta").attr('checked', true);
				}else{
					$("#mostrarValorEquipoHerramienta").attr('checked', false);
				}				
				
				if(data.mostrarValorContenidos){
					$("#mostrarValorContenidos").attr('checked', true);
				}else{
					$("#mostrarValorContenidos").attr('checked', false);
				}
				
				$("#limite_asegurado").val(data.limiteAsegurado);
				$("#prima_minima").val(data.primaMinima);
			}
		});
	}
	
	function eliminar(parametroGP){
		var r = confirm("Seguro que desea eliminar");
		if(r == true){
			
			parametroGrupoProductoId = parametroGP;
			grupoPorProductoId = "";
			mostrarValorEstructuras = "";
			mostrarValorMueblesEnseres = "";
			mostrarMaquinaria = "";
			mostrarValorMercaderia = "";
			mostrarValorInsumos = "";
			mostrarValorEquipoHerramienta = "";
			mostrarValorContenidos = "";
			limiteAsegurado = "";
			tipoConsulta="eliminar";	
			primaMinima="";
			enviarDatos(parametroGrupoProductoId,grupoPorProductoId,mostrarValorEstructuras,mostrarValorMueblesEnseres,mostrarMaquinaria,mostrarValorMercaderia,
					mostrarValorInsumos,mostrarValorEquipoHerramienta,tipoConsulta,limiteAsegurado,primaMinima,mostrarValorContenidos);
		}
		cargar();
	}
	
	function enviarDatos(parametroGrupoProductoId,grupoPorProductoId,mostrarValorEstructuras,mostrarValorMueblesEnseres,mostrarMaquinaria,mostrarValorMercaderia,
			mostrarValorInsumos,mostrarValorEquipoHerramienta,tipoConsulta,limiteAsegurado, primaMinima, mostrarValorContenidos){
		$.ajax({
			url : '../PymeParametroXGrupoPorProductoController',
			data : {
				"parametroGrupoProductoId" : parametroGrupoProductoId,
				"grupoPorProductoId" : grupoPorProductoId,
				"mostrarValorEstructuras" : mostrarValorEstructuras,
				"mostrarValorMueblesEnseres" : mostrarValorMueblesEnseres,
				"mostrarMaquinaria" : mostrarMaquinaria,
				"mostrarValorMercaderia" : mostrarValorMercaderia,
				"mostrarValorInsumos" : mostrarValorInsumos,
				"mostrarValorEquipoHerramienta" : mostrarValorEquipoHerramienta,				
				"mostrarValorContenidos" : mostrarValorContenidos,
				"tipoConsulta" : tipoConsulta,
				"limiteAsegurado" : limiteAsegurado,
				"primaMinima" : primaMinima
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
	
	
	function cargar(){
		$("#parametroPorGrupo").children().remove();
		$("#grupoPorProductoId").children().remove();
		
		$.ajax({
			url: '../PymeParametroXGrupoPorProductoController',
			data : {
				"tipoConsulta" : "encontrarTodos"
			},
			type : 'post',			
			datatype : 'json',
			async : false,
			success : function(data){
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				var pymeParametroXGPPArray = data.pymeParametroXGPPArray;
				$.each(pymeParametroXGPPArray, function(index){										
					$("#parametroPorGrupo").append("<tr class='odd gradeX'>"+							
							"<td relation='grupoPorProducto'>"+pymeParametroXGPPArray[index].grupoPorProductoId+"</td>"+
							"<td relation='ValorEstructuras'>"+pymeParametroXGPPArray[index].mostrarValorEstructuras+"</td>"+
							"<td relation='ValorMueblesEnseres'>"+pymeParametroXGPPArray[index].mostrarValorMueblesEnseres+"</td>"+
							"<td relation='Maquinaria'>"+pymeParametroXGPPArray[index].mostrarMaquinaria+"</td>"+
							"<td relation='ValorMercaderia'>"+pymeParametroXGPPArray[index].mostrarValorMercaderia+"</td>"+
							"<td relation='ValorInsumos'>"+pymeParametroXGPPArray[index].mostrarValorInsumos+"</td>"+
							"<td relation='ValorEquipoHerramienta'>"+pymeParametroXGPPArray[index].mostrarValorEquipoHerramienta+"</td>"+
							"<td relation='ValorContenidos'>"+pymeParametroXGPPArray[index].mostrarValorContenidos+"</td>"+
							"<td width='175px'>"+									
							"<input type='hidden' id='coberturaPymesId' value='" + pymeParametroXGPPArray[index].parametroGrupoProductoId + "'/>"+	
							" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' onclick='actualizar("+pymeParametroXGPPArray[index].parametroGrupoProductoId+");' class='btn btn-success btn-xs actualizar-btn' >" +
								" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
							" </button>" +									
							" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+pymeParametroXGPPArray[index].parametroGrupoProductoId+");'>" +
							  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record' ></span> Eliminar" +
							" </button>" +									
						"</td>" +
					"</tr>");
				});				
				$("#loading").remove();	
				var grupoPPJSONArray = data.grupoPPJSONArray;
				$("#grupoPorProductoId").append("<option>Selecccione una opción</option>");
				$.each(grupoPPJSONArray, function(index){
					$("#grupoPorProductoId").append("<option value='"+grupoPPJSONArray[index].grupoPPId+"'>"+grupoPPJSONArray[index].grupoPPNombre+"</option>");
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
	
<title>Parametros por grupo por producto - Cotizador QBE</title>
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
							<h4 class="modal-title" id="myModalLabel">Parametros Por Grupo Por Producto</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Los parametros por grupo por producto se grabarón con exito.</div>
							<div class="form-group">
								<input type="hidden" class="form-control" id="parametroGrupoProductoId">
								<label>Grupo Por Producto</label> 
								<select class="form-control required" id="grupoPorProductoId">
								<option>Seleccione una opción.</option>
								</select>
								<br/>
								<label>Mostrar Valor Estructuras</label> 
								<input type='checkbox' id='mostrarValorEstructuras' /> <br/>
								<label>Mostrar Valor Muebles y Enseres</label> 
								<input type='checkbox' id='mostrarValorMueblesEnseres' /> <br/>
								<label>Mostar Valor Maquinaria</label>
								<input type='checkbox' id='mostrarMaquinaria' /> <br/>
								<label>Mostrar Valor Mercaderia</label> 
								<input type='checkbox' id='mostrarValorMercaderia' /> <br/>	
								<label>Mostrar Valor Insumos</label> 
								<input type='checkbox' id='mostrarValorInsumos' /> <br/>	
								<label>Mostrar Valor Equipos y Herramientas</label> 
								<input type='checkbox' id='mostrarValorEquipoHerramienta' /> <br/>	
								<label>Mostrar Valor Contenidos</label> 
								<input type='checkbox' id='mostrarValorContenidos' /> <br/>
								<label>L&iacute;mite Asegurado del Producto:</label>
								<input type="text" id="limite_asegurado" />	<br/>													
								<label>Prima M&iacute;nima:</label>
								<input type="text" id="prima_minima" />
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
			<div class="table-responsive">						
				<div class="panel panel-primary">
						<div class="panel-body">
						<div class="input-group"> <span class="input-group-addon">Filtro</span>
						    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
						</div>
						 <table class="table table-striped table-bordered table-hover"
							id="dataTable">
								<thead>
									<tr>
										<th>Grupo Por Producto</th>
										<th>Mostrar Valor Estructuras</th>
										<th>Mostrar Valor Muebles y Enseres</th>
										<th>Mostar Maquinaria</th>
										<th>Mostrar Valor Mercaderia</th>
										<th>Mostrar Valor Insumos</th>
										<th>Mostrar Valor Equipo Herramienta</th>	
										<th>Mostrar Valor Contenidos</th>
										<th> </th>
									</tr>	
								</thead>
								<tbody id="parametroPorGrupo" class="searchable">
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
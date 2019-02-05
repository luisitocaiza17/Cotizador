<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Punto de Venta - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<script src="../static/js/select2.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		<!-- Inicio Variables Globales -->
		var sucursal = "";
		var nombre = "";
		var descripcion = "";
		var agente = "";
		var activo = "";
		var tipoConsulta = "";

		var arrAgente = new Array();
		var arrCodigoAgente = new Array();
        var opcionesSelect2Agente= new Array();
        var opcionesSelect2AgenteFiltro= new Array();

        var arrSucursal = new Array();
		var arrCodigoSucursal = new Array();
        var opcionesSelect2Sucursal = new Array();
        var opcionesSelect2SucursalFiltro = new Array();
        
        var arrPtoVentaEnsurance = new Array();
		var arrCodigoPtoVentaEnsurance = new Array();
        var opcionesPtoVentaEnsurance = new Array();
        var opcionesPtoVentaEnsuranceFiltro = new Array();
        
        var arrMatriz = new Array();
		var arrCodigoMatriz = new Array();
        var opcionesSelect2Matriz= new Array();
        
		function cargarCombos()
		{
			$.ajax({
				url : '../PuntoVentaController',
				data : {
					"tipoConsulta" : "buscarFiltro"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {					
					
					$("#loading").fadeOut("slow");
					
					
					//Para Cargar Combos
					
					var listadoMatriz = data.listadoMatriz;
                    $.each(listadoMatriz, function (index) {
                    	opcionesSelect2Matriz[index]={"id":listadoMatriz[index].codigo,"text":listadoMatriz[index].nombre};
                    });
                    
                    $("#padreMatriz").select2({
        				data : opcionesSelect2Matriz,
        				placeholder : "Seleccione una Matriz"        				
        			});
					
					var listadoAgente = data.listadoAgente;
                    $.each(listadoAgente, function (index) {
                    	opcionesSelect2Agente[index]={"id":listadoAgente[index].codigo,"text":listadoAgente[index].nombre};
                    	opcionesSelect2AgenteFiltro[index]={"id":listadoAgente[index].codigo,"text":listadoAgente[index].nombre};
                    });
                    
                    $("#agente").select2({
        				data : opcionesSelect2Agente,
        				placeholder : "Seleccione un Agente"        				
        			});
                    
                    $("#filtro_agente").select2({
        				data : opcionesSelect2AgenteFiltro,
        				placeholder : "Seleccione un Agente"        				
        			});
                    
                    
					var listadoSucursal = data.listadoSucursal;
                    $.each(listadoSucursal, function (index) {
                    	opcionesSelect2Sucursal[index]={"id":listadoSucursal[index].codigo,"text":listadoSucursal[index].nombre};
                    	opcionesSelect2SucursalFiltro[index]={"id":listadoSucursal[index].codigo,"text":listadoSucursal[index].nombre};
                    });
                    
                    $("#sucursal").select2({
        				data : opcionesSelect2Sucursal,
        				placeholder : "Seleccione una Sucursal"        				
        			});
                    $("#filtro_sucursal").select2({
        				data : opcionesSelect2SucursalFiltro,
        				placeholder : "Seleccione una Sucursal"        				
        			});
                    //Punto Venta Ensurance
                    var listadoPuntoVentaEnsurance = data.listadoPuntoVentaEnsurance;
                    $.each(listadoPuntoVentaEnsurance, function (index) {
                    	opcionesPtoVentaEnsurance[index]={"id":listadoPuntoVentaEnsurance[index].codigo,"text":listadoPuntoVentaEnsurance[index].nombre};
                    	opcionesPtoVentaEnsuranceFiltro[index]={"id":listadoPuntoVentaEnsurance[index].codigo,"text":listadoPuntoVentaEnsurance[index].nombre};
                    });
                    
                    $("#codigoEnsurance").select2({
        				data : opcionesPtoVentaEnsurance,
        				placeholder : "Seleccione un Punto de Venta"        				
        			});
                    
                    $("#filtro_puntoVenta").select2({
        				data : opcionesPtoVentaEnsuranceFiltro,
        				placeholder : "Seleccione un Punto de Venta"        				
        			});
                    
                    
                    
				}
			});	
		}
        
		$(document).ready(function() {
			activarMenu("PuntoVenta");
			cargarCombos();
						
			
			$('#LimpiarBtn').click(function(){	 
 	    	 	$("#filtro_sucursal").select2("val","");
	    	 	$("#filtro_puntoVenta").select2("val","");
	    	 	$("#filtro_agente").select2("val","");
	    	 	$("#filtro_nombre").val("");
	    	 	$("#filtro_activo").prop('checked', true);
	    	 	//$("#filtro_matriz").prop('checked', true);
	 			$('#dataTableContent').html('');

	 			//cargarCombos();	
	    	 });
			
			$("#loading").fadeOut("slow"); 
			$("#cargando").fadeOut("slow");
			$('#ConsultaBtn').click(function(){
				$("#loading").fadeIn("slow");
				var filtroSucursal = $('#filtro_sucursal').val();
				var filtroPuntoVenta = $('#filtro_puntoVenta').val();
				var filtroAgente = $('#filtro_agente').val();
				var nombreFiltro = $("#filtro_nombre").val();
				var activoFiltro = 0;
				//var matrizFiltro = 0;
	    		if ($("#filtro_activo").is(':checked')) activoFiltro = 1;
	    		//if ($("#filtro_matriz").is(':checked')) matrizFiltro = 1;
				
	    		if(filtroSucursal == '' && filtroPuntoVenta == '' && filtroAgente == '' && nombreFiltro == '')
	    		{
	    			alert("Ingrese al menos un campo de busqueda");
			     	$("#loading").fadeOut("slow");
			     	return false;
	    			
	    		}
				
				$.ajax({
					url : '../PuntoVentaController',
					data : {
						"tipoConsulta" : "encontrarTodos",
						"filtroSucursal" : filtroSucursal,
						"filtroPuntoVenta" : filtroPuntoVenta,
						"filtroAgente" : filtroAgente,
						"nombreFiltro" : nombreFiltro,
						"activoFiltro" : activoFiltro
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {					
						
						$("#loading").fadeOut("slow");
						$('#dataTableContent').html('');
						
						
						
						
						if(data.numRegistros > 0){
							var listadoPuntoVenta = data.listadoPuntoVenta;
							$.each(listadoPuntoVenta, function(index){
								$("#dataTableContent").append("	<tr class='odd gradeX'>" +
										" <td relation='sucursal' hidden='hidden'>"+ listadoPuntoVenta[index].sucursal+"</td>" +									
										" <td relation='sucursalNombre'>"+ listadoPuntoVenta[index].sucursalNombre+"</td>" +									
										" <td relation='nombre'>"+ listadoPuntoVenta[index].nombre +"</td>" +
										" <td relation='descripcion'>"+ listadoPuntoVenta[index].descripcion +"</td>" +
										" <td relation='agente' hidden='hidden'>"+ listadoPuntoVenta[index].agente +"</td>" +
										" <td relation='agenteNombre'>"+ listadoPuntoVenta[index].agenteNombre +"</td>" +
										" <td relation='activo'>"+ listadoPuntoVenta[index].activo +"</td>" +
										" <td relation='codigoEnsurance' hidden='hidden'>"+ listadoPuntoVenta[index].codigoEnsurance +"</td>" +
										" <td relation='codigoEnsuranceNombre'>"+ listadoPuntoVenta[index].codigoEnsuranceNombre +"</td>" +
										" <td relation='matriz'>"+ listadoPuntoVenta[index].esMAtriz +"</td>" +
										" <td relation='matrizP'>"+ listadoPuntoVenta[index].matrizP +"</td>" +
										" <td relation='aplicaIVA12'>"+ listadoPuntoVenta[index].aplicaIVA12 +"</td>" +
										" <td relation='padreMatriz' style='display: none;'>"+ listadoPuntoVenta[index].matrizCodigo +"</td>" +
										" <td width='175px'>" +
											" <input type='hidden' value='"+ listadoPuntoVenta[index].codigo +"'/>" +
											" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
			  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
											" </button>" +
											" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
											  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
											" </button>" +
										"</td>" +
									"</tr>");
						
							});
							
							/* Inicio Controles Actualizar Registro*/
							$(".actualizar-btn").bind({click: function() {
									$("#addButton").trigger("click");
									$("#codigo").val($(this).parent().children().first().val());
									var elem = $(this).parent();
									var bandera = 1;
									do {
										elem = elem.prev();
										if (elem.is("td")){
											var elemCode = elem.attr("relation");
											elementType(elem.text(), elemCode, $("#"+elemCode).attr("type"));
											if(elemCode == 'matriz')
												if(elem.text() == 'No')
													$("#matriz").prop('checked', true);
												else
													$("#matriz").prop('checked', false);
										}else {
											bandera = 0;
										}
									} while (bandera == 1);
									
									$("#matriz").trigger("click");
								  }
							});
							/* Fin Controles Actualizar Registro*/
							
							/* Inicio Controles Elminar Registro */
							$(".eliminar-btn").bind({click: function() {
									var r = confirm("Seguro que desea eliminar el Punto de Venta " + $(this).parent().parent().children().first().text());
									if (r == true){
										codigo = $(this).parent().children().first().val();
										nombre = ""; agente = ""; activo = ""; codigoEnsurance="";
										sucursal = ""; descripcion = "";
										tipoConsulta = "eliminar";
										enviarDatos(codigoEnsurance, codigo, nombre, agente, activo, descripcion, sucursal, tipoConsulta);
								    	$(this).parent().parent().remove();
									}
								}
							});	
							/* Fin Controles Elminar Registro */
						}else{
							$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
						}
					}
				});		
			});
			
			
			
				
			
			/* Resetea el formulario cada vez que presiona el boton nuevo */
				$("#addButton").click(function(){
					$("#msgPopup").hide();
					$("#formCrud").get(0).reset();
					$("#sucursal").select2("val","");
					$("#agente").select2("val","");
					$("#codigoEnsurance").select2("val","");
					$("#padreMatriz").select2("val","");
					//cargarCombos();
				});

			/* Inicio Controles Grabar Resgistro*/
				$("#save-record").click(function() {
					var requerido = true;
					var matrizPadre = "";
					sucursal = $("#sucursal").select2("val");
					if(sucursal == undefined && requerido)
					{
						alert("Por favor seleccione una sucursal");
						requerido = false;
					}
					codigoEnsurance = $("#codigoEnsurance").select2("val");
					if(codigoEnsurance == undefined && requerido)
					{
						alert("Por favor seleccione un Pto Venta");
						requerido = false;
					}
						
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.length <= 0 && requerido) {
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							requerido = false;
							return false;
						}
					});
					
					if(!$("#matriz").is(':checked'))
					{
						matrizPadre = $("#padreMatriz").val();
						
					}
		
					if ($("#activo").is(':checked')) activo = 1;
					codigo = $("#codigo").val();
					nombre = $("#nombre").val();
					sucursal = $("#sucursal").select2("val");
					agente = $("#agente").select2("val");					
					descripcion = $("#descripcion").val();
					var aplicaIVA12=0;
					
					if ($("#aplicaIVA12").is(':checked')) aplicaIVA12 = 1;
					
					if(agente == null)
						agente = ' ';
					if (codigo == ""){
						tipoConsulta = "crear";
					}else{
						tipoConsulta = "actualizar";
					}					
					
					if(requerido)
						enviarDatos(codigoEnsurance, codigo, nombre, agente, activo, descripcion, sucursal, tipoConsulta, matrizPadre,aplicaIVA12);
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigoEnsurance, codigo, nombre, agente, activo, descripcion, sucursal, tipoConsulta, matrizPadre,aplicaIVA12){
				$.ajax({
					url : '../PuntoVentaController',
					data : {
						"codigoEnsurance" : codigoEnsurance,
						"codigo" : codigo,
						"nombre" : nombre,
						"agente" : agente,
						"activo" : activo,
						"descripcion" : descripcion,
						"sucursal" : sucursal,
						"matrizPadre" : matrizPadre,
						"tipoConsulta" : tipoConsulta,
						"aplicaIVA12":aplicaIVA12
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if(data.success)
							$("#msgPopup").show();
						else 
							alert(data.error);
					}
				});
			}
		});
			</script>
			<script type="text/javascript">			
			function validarNombre(){
				nombre = $("#nombre").val();						
	        	$.ajax({
					url : '../PuntoVentaController',
					data : {
						"nombre" : nombre,
						"tipoConsulta" : "verificacionNombrePuntoVenta"								
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var auxPuntoVenta = data.PuntoVenta;
						if (auxPuntoVenta.bandera == "verdad"){
						alert("Ya existe un Punto de Venta con ese Nombre");
						$("#nombre").val(" ");
						$("#nombre").focus();
						}
					}
				});	
	          }		
			$(function() {
			    $('#matriz').click(function(){
			    	console.log("valor: "+$(this).is(':checked'))
			        if ($(this).is(':checked')) {
			        	$('#esMatriz').hide();
			        	$("#padreMatriz").val("");
			        } else {
			            
			            $('#esMatriz').show();
			        }
			    });
			});
	</script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
/*				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
				else {
					com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
							.getAttribute("usuario");

					// Obtenemos el rol asociado al usuario
					com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
					com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
							.buscarPorUsuario(usuario);
					com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
					com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
					if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
							"Puntos de Venta")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}*/
%>

	<div class="row crud-nav-bar">
	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Punto de Venta</center></td>

					</tr>
					<tr>
						<th>Sucursal: <input style="width:100%" type="select2" class="required_select2" id="filtro_sucursal"></th>
						<th style="text-align: center;">Punto de Venta: <input style="width:100%" type="select2" class="required_select2" id="filtro_puntoVenta"></th>
						<th>Agente: <input style="width:100%" type="select2" class="required_select2" id="filtro_agente"></th>
					</tr>
					<tr>
						<th>Nombres: <input type="text" id="filtro_nombre"></th>
						<th>Activo: <input type="checkbox" value="activo"id="filtro_activo" checked></th>
						
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
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#add" id="addButton">
								<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
							</button>
						</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th><div id="cargando" style="position: fixed; left: 50%;">
								<div class="loading-indicator">
									<img src="../static/images/ajax-loader.gif" /><br />
									<br /> <span id="loading-msg">Cargando Espere...</span>
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>

				</tbody>
			</table>
		</div>
		
		

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Nuevo Punto de Venta</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El punto de venta se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Sucursal</label> 
								<input style="width:100%" type="select2" class="required_select2" id="sucursal"></br>
								<label>Nombre del Punto de venta</label> 
								<input type="text"class="form-control required" id="nombre" onblur="validarNombre()">
								<label>Descripción del Punto de venta</label> 
								<input type="text"class="form-control required" id="descripcion">	
								<label>Nombre del Agente</label>
								<input style="width:100%" type="select2" class="required_select2" id="agente"></br>
								<label>Pto Venta Ensurance</label>
								<input style="width:100%" type="select2" class="required_select2" id="codigoEnsurance"></br> 
								<div class="checkbox">
									<label> <input type="checkbox" value="activo"id="activo">Activo</label>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" id="aplicaIVA12">Aplica IVA 12</label>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" value="matriz" id="matriz">Matriz</label>
								</div>
								<div class="well" id = "esMatriz">
								<label>Matriz</label> 
								<input style="width:100%" type="select2" class="required_select2" id="padreMatriz"></br>
								</div>
								
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
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Sucursal</th>
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Agente</th>
							<th>Activo</th>
							<th>Pto Venta Ensurance</th>
							<th>Es Matriz</th>
							<th>Matriz</th>
							<th>Aplica IVA 12</th>
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
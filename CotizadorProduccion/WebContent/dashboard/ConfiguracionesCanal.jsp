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
	
<title>Configuraciones Canal - Cotizador QBE</title>

<script>
	
	 $(function(){
	  	  $(".wrapper1").scroll(function(){
	  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  	  });
	  	  $(".wrapper2").scroll(function(){
	  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  	  });
	  	});
	 
	 	var formaPagoXPVId = "";
	 	var vigenciaPolizaXPVId = "";
	 
	 	var puntoVentaId = "";
	 	var formaPagoId = "";
	 	var vigenciaId = "";
	 	
	 	
	 	var parametroXPVId = "";
	 	var emisionDirecta = "";
	 	var contenedorEnsuranceId = "";
	 	var plantillaEnsurancId = "";
	 	var tipoCanal = "";
						
		var tipoConsulta = "";
		var arrListadoPuntoVenta = new Array();
	    var arrListadoPuntoVentaCodigo = Object();
	    var arrListadoFormaPago = new Array();
	    var arrListadoFormaPagoCodigo = Object();
	    var arrListadoVigenciaPoliza = new Array();
	    var arrListadoVigenciaPolizaCodigo = Object();
	    
		
		$(document).ready(function(){
			activarMenu("ConfiguracionesCanal");
			cargarFormaPago();			
			cargarVigenciaPago();
			$(':input[type="number"]').numeric("");
			
			
			$.ajax({
				url : '../PuntoVentaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {
					var listadoPuntoVenta = data.listadoPuntoVenta;
					$.each(listadoPuntoVenta, function (index){
						$("#configuracionCanal").append("<tr class='odd gradeX'>"+
								"<td relation='puntoVentaNombre' style='width: 20%; text-align: left;' class='sorting' >" + listadoPuntoVenta[index].nombre + "</td>"+								
								"<td style='width: 20%; text-align: center;' class='sorting' >"+									
									"<input type='hidden' id = 'puntoVentaId' value='" + listadoPuntoVenta[index].codigo + "'/>"+	
									"<button type='button' class='btn btn-primary btn-xs actualizar-btn' onclick='cargarConfiguracion("+listadoPuntoVenta[index].codigo+");' id='addButton'>"+
							        	"<span hidden='hidden' class='glyphicon glyphicon-plus' ></span> &nbsp; Configurar"+
								    "</button>"+									
								"</td>" +
							"</tr>");		
					});						
					
					$("#loading").remove();		
					
				}
			});	
			
			/*Inicio controloes grabar*/
			
			$("#addFormaPagoXPV").bind({click: function(){	
				
				formaPagoXPVId = "";					
				puntoVentaId = $("#puntoVenta").val();
				formaPagoId = $("#formaPago").val();
				
				$("#formaPago").css("border", "1px solid #ccc");
				$("#formaPago").each(function(index) {
					var cadena = $("#formaPago").val();
					if (cadena == '0') {
						$(this).css("border", "1px solid red");
						alert("Por favor ingrese el campo requerido");
						$(this).focus();
						return false;
					}else{
						if(formaPagoXPVId=="")
							tipoConsulta = "crear";   
						else
							tipoConsulta = "editar";
						
						enviarFormaPagoXPV(puntoVentaId,formaPagoId,tipoConsulta,formaPagoXPVId);	
					}
				});		
										 
					cargarConfiguracion(puntoVentaId);					
				}				
			});
			
			
			$("#addVigenciaPolizaXPV").bind({click: function(){
				vigenciaPolizaXPVId = "";						
				puntoVentaId = $("#puntoVenta").val();
				vigenciaId = $("#vigenciaPoliza").val();
				
				$("#vigenciaPoliza").css("border", "1px solid #ccc");
				$("#vigenciaPoliza").each(function(index) {
					var cadena = $("#vigenciaPoliza").val();
					if (cadena == "0") {
						$(this).css("border", "1px solid red");
						alert("Por favor ingrese el campo requerido");
						$(this).focus();
						return false;
					}else{
						if(formaPagoXPVId=="")
							tipoConsulta = "crear";
						else
							tipoConsulta = "editar";
						
						enviarVigenciaPolizaXPV(puntoVentaId,vigenciaId,vigenciaPolizaXPVId,tipoConsulta);
					}							
					
				});	
				cargarConfiguracion(puntoVentaId);
				}			
				
			});	
			
			
			$("#addParametroXPV").bind({click: function(){
				parametroXPVId = $("#parametroXPVId").val();
				
				if($("#emisionDirecta").is(":checked")){
					emisionDirecta = 1;
				}else{
					emisionDirecta = 0;
				}
					
				
				
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
				
				puntoVentaId = $("#puntoVenta").val();
				contenedorEnsuranceId = $("#contenedorEnsuranceId").val();
				plantillaEnsurancId= $("#plantillaEnsurancId").val();
				tipoCanal= $("#tipoCanal").val();
				if(parametroXPVId=="")
					tipoConsulta = "crear";
				else
					tipoConsulta = "actualizar";
				
				enviarParametroXPV(parametroXPVId,puntoVentaId,emisionDirecta,contenedorEnsuranceId,plantillaEnsurancId,tipoCanal,tipoConsulta);
				
				cargarConfiguracion(puntoVentaId);
				}			
				
			});			
			/*Fin controloes grabar*/
		});	
		
		function eliminarFP(fPId){
			
				var r = confirm("Seguro que desea eliminar");
				if(r == true){
					formaPagoXPVId = fPId;					
					puntoVentaId = "";
					formaPagoId = "";
					tipoConsulta = "eliminar";
					
					enviarFormaPagoXPV(puntoVentaId,formaPagoId,tipoConsulta,formaPagoXPVId);
				}				
				
				cargarConfiguracion($("#puntoVenta").val());
			
			
		}
		
		function eliminarVP(vPId){
			
			var r = confirm("Seguro que desea eliminar");
			if(r == true){
				vigenciaPolizaXPVId = vPId;						
				puntoVentaId = "";
				vigenciaId = "";	
				tipoConsulta = "eliminar";
				
				enviarVigenciaPolizaXPV(puntoVentaId,vigenciaId,vigenciaPolizaXPVId,tipoConsulta);
			}				
				
			cargarConfiguracion($("#puntoVenta").val());
		}
		
		function enviarFormaPagoXPV(puntoVentaId,formaPagoId,tipoConsulta,formaPagoXPVId){
			$.ajax({
				url : '../FormaPagoXPuntoVentaController',
				async: false,
				data : {
					"codigo" : formaPagoXPVId,
					"puntoVentaId" : puntoVentaId,
					"formaPagoId" : formaPagoId,
					"tipoConsulta" : tipoConsulta					
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					
				}
			});
		}
		
		function enviarVigenciaPolizaXPV(puntoVentaId,vigenciaId,vigenciaPolizaXPVId,tipoConsulta){
			$.ajax({
				url : '../VigenciaPolizaXPuntoVentaController',
				async: false,
				data : {
					"tipoConsulta" : tipoConsulta,
					"codigo" : vigenciaPolizaXPVId,
					"puntoVentaId" : puntoVentaId,
					"vigenciaPolizaId" : vigenciaId						
					},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					
				}
			});
		}
		
		
		function enviarParametroXPV(parametroXPVId,puntoVentaId,emisionDirecta,contenedorEnsuranceId,plantillaEnsurancId,tipoCanal,tipoConsulta){
			$.ajax({
				url : '../ParametroXPuntoVentaController',
				async: false,
				data : {
					"tipoConsulta" : tipoConsulta,
					"parametroXPVId" : parametroXPVId,
					"puntoVentaId" : puntoVentaId,
					"emisionDirecta" : emisionDirecta,
					"contenedorEnsuranceId" : contenedorEnsuranceId,
					"plantillaEnsurancId" : plantillaEnsurancId,
					"tipoCanal" : tipoCanal
					},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#msgPopup").show();
				}
			});
		}
		
		
		/*Inicio controles cargar combos*/		
			
			function cargarFormaPago(){
				$.ajax({
					url : '../FormaPagoController',
					data : {
						"tipoConsulta" : "encontrarTodos"
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {										
						var listadoFormaPago = data.listadoFormaPago;									
						$.each(listadoFormaPago, function (index) {
							arrListadoFormaPago.push(listadoFormaPago[index].nombre);
							arrListadoFormaPagoCodigo[listadoFormaPago[index].nombre] = listadoFormaPago[index].codigo;
							$("#formaPago").append("<option value='" + listadoFormaPago[index].codigo + "'>" + listadoFormaPago[index].nombre + "</option>");
						});
					}
				});
			}
			
			function cargarVigenciaPago(){
				$.ajax({
					url : '../VigenciaPolizaController',
					data : {
						"tipoConsulta" : "encontrarTodos"
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {										
						var listadoVigenciaPoliza = data.listadoVigenciaPoliza;									
						$.each(listadoVigenciaPoliza, function (index) {
							arrListadoVigenciaPoliza.push(listadoVigenciaPoliza[index].nombre);
							arrListadoVigenciaPolizaCodigo[listadoVigenciaPoliza[index].nombre] = listadoVigenciaPoliza[index].codigo;
							$("#vigenciaPoliza").append("<option value='" + listadoVigenciaPoliza[index].codigo + "'>" + listadoVigenciaPoliza[index].nombre + "</option>");
						});
					}
				});
			}		
			
		
			/*Fin controles cargar combos*/
			
			
			function cargarConfiguracion(puntoVenta){	
				$('#panel_1').hide();
				$('#panel_2').show();
				$("#tbl_formaPago").empty();
				$("#tbl_VigenciaPoliza").empty();
				
					$.ajax({
							url : '../ConfiguracionCanalController',
							data : {
								"tipoConsulta" : "encontrarTodos",
								"puntoVentaId" : puntoVenta				
							},
							type : 'POST',
							datatype : 'json',
							success : function (data) {
								
								$(dataTableContent_1).append("<input type='hidden' id = 'puntoVenta' value='" + puntoVenta + "'/>");
								$(dataTableContent_2).append("<input type='hidden' id = 'puntoVenta' value='" + puntoVenta + "'/>");						
								
								var listadoConfiguracionCanal_1 = data.listadoConfiguracionCanal_1;
								$.each(listadoConfiguracionCanal_1, function (index){									
									$("#tbl_formaPago").append("<tr class='odd gradeX'>"+
											"<td relation='puntoVentaNombre' style='width: 20%; text-align: left;' class='sorting' >" + listadoConfiguracionCanal_1[index].formaPagoNombre + "</td>"+								
											"<td style='width: 20%; text-align: center;' class='sorting' >"+									
												"<input type='hidden' id = 'formaPagoXPVId' value='" + listadoConfiguracionCanal_1[index].formaPagoXPVId + "'/>"+												
												"<button type='button' class='btn btn-danger btn-xs eliminar-btn' id='eliminarFormaPago' onclick='eliminarFP("+listadoConfiguracionCanal_1[index].formaPagoXPVId+");'>" +
												"<span class='glyphicon glyphicon glyphicon-remove'></span> Eliminar" +
												"</button>"+									
											"</td>" +
										"</tr>");				
								});		
								
								var listadoConfiguracionCanal_2 = data.listadoConfiguracionCanal_2;
								$.each(listadoConfiguracionCanal_2, function (index){	
									$("#tbl_VigenciaPoliza").append("<tr class='odd gradeX'>"+
										"<td relation='puntoVentaNombre' style='width: 20%; text-align: left;' class='sorting' >" + listadoConfiguracionCanal_2[index].vigenciaPolizaNombre + "</td>"+								
										"<td style='width: 20%; text-align: center;' class='sorting' >"+									
											"<input type='hidden' id = 'vigenciaPolizaXPVId' value='" + listadoConfiguracionCanal_2[index].vigenciaPolizaXPVId + "' />"+
											"<button type='button' class='btn btn-danger btn-xs eliminar-btn' id='eliminarVigenciaPoliza' onclick='eliminarVP("+listadoConfiguracionCanal_2[index].vigenciaPolizaXPVId+"); '>" +
											"<span class='glyphicon glyphicon glyphicon-remove'></span> Eliminar" +
											"</button>"+									
										"</td>" +
									"</tr>");
								});		
							}
						});
						$.ajax({
							url : '../ParametroXPuntoVentaController',
							data : {
								"tipoConsulta" : "encontrarTodos",
								"puntoVentaId" : puntoVenta				
							},
							type : 'POST',
							datatype : 'json',
							success : function (data) {					
								
									$("#parametroXPVId").val(data.parametroXPVId);
									$("#puntoVenta").val(puntoVenta);
									if(data.emisionDirecta>0){
										$("#emisionDirecta").attr('checked', true);
									}else{
										$("#emisionDirecta").attr('checked', false);
									}
																				
									$("#contenedorEnsuranceId").val(data.contenedorEnsuranceId);
									$("#plantillaEnsurancId").val(data.plantillaEnsurancId);
									$("#tipoCanal").val(data.tipoCanal);
								
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
							"Configuraciones del Canal")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
	%>

<div class="content">
		<form id="wizard" action="" method="post">			
			<section id = 'panel_1' style='display:block;'>
				<h2>Punto de venta. </h2>		
				<fieldset class="border">
					<legend class="border">Datos sobre el punto de venta.</legend>	
					<div class="panel panel-primary">
						<div class="panel-body">
						<div class="input-group"> <span class="input-group-addon">Filtro</span>
						    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
						</div>
						 <table class="table table-striped table-bordered table-hover"
							id="dataTable">
								<thead>
									<tr>							
										<th>Canal</th>							
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
				</fieldset>
			</section>
			
			<section id = 'panel_2' style='display:none;'>
				<h2> Configuración canal. </h2>				
				<fieldset class="border">
					<legend class="border">Datos de la Configuración Canal.</legend>									
						<div class="panel-body">	
						<div class="alert alert-success" id="msgPopup">La configuración se grabo con exito.</div>
							<div class="form-group">
								<div class="panel panel-primary">
						<div class="panel-heading">Parametros por punto de venta.</div>
						<div class="panel-body">
								<table class="table table-striped table-bordered table-hover" id="tbl_ParametroXPV">
									<tr>									
									<td style='width: 25%'>
										<input type='hidden' class='form-control' id='parametroXPVId' value=''/>
										<label>Emisión directa: </label>
										<input type='checkbox'  id='emisionDirecta' />										
									</td>
									<td>
									<label>Tipo canal </label>
										<select type='select' class='form-control required' id='tipoCanal'>
										<option>Seleccione una opción</option>
										<option value='REASEGURADOR'>REASEGURADOR</option>
										<option value='DIRECTO'>DIRECTO</option>
									</select>
									</td></tr>
									<tr><td style='width: 25%'>
									<label>Código plantilla </label>									
									<input type="number" class="form-control required" id="plantillaEnsurancId"  required>
									</td>
									<td style="width: 25%">
											<label>Código contenedor </label>											
											<input type="number" class="form-control required" id="contenedorEnsuranceId"  required>
									</td>
								</tr>	
								<tr>
									<td colspan="2">
										<button type="button" class="btn btn-primary" id="addParametroXPV">Guardar
												Cambios</button>
									</td>
								</tr>							
								</table>	
								</div>
								</div>
							</div>										
							<div class="form-group">			
								<div class="panel panel-primary">
						<div class="panel-heading">Forma de pago por punto de venta.</div>
						<div class="panel-body">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>							
											<th>
												<select type="select" class="form-control required" id="formaPago"> 
										  			<option value='0'>Seleccione una forma de pago</option>                         	                           	
												</select>												 				
											</th>							
											<th id="dataTableContent_1">
												<button type='button' name='add-btn' class='btn btn-success btn-xs add-btn' id ='addFormaPagoXPV' >
													<span class='glyphicon glyphicon glyphicon-edit'></span> Añadir
												</button>
											</th>
										</tr>	
									</thead>									                   
				                </table>	
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>							
											<th>Listado de formas de pago</th>							
											<th> </th>
										</tr>	
									</thead>
									<tbody id="tbl_formaPago">							                 		 
									</tbody>                     
				                </table>	
				                </div>
				                </div>						                          	
							</div>							
							<div class="form-group">	
							<div class="panel panel-primary">
						<div class="panel-heading">Vigencias por punto de venta.</div>
						<div class="panel-body">							
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>							
											<th>
												<select type="select" class="form-control required" id="vigenciaPoliza">
													<option value='0'>Seleccione una vigencia</option>                            	                           	
												 </select>												 				
											</th>							
											<th  id="dataTableContent_2">
												<button type='button' name='add-btn' class='btn btn-success btn-xs add-btn' id ='addVigenciaPolizaXPV'>
													<span class='glyphicon glyphicon glyphicon-edit'></span> Añadir
												</button> 
											</th>
										</tr>	
									</thead>									                   
				                </table>	
								 <table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>							
											<th>Listado de Vigencias Póliza</th>							
											<th> </th>
										</tr>	
									</thead>
									<tbody id="tbl_VigenciaPoliza" class="searchable">							                 		 
									</tbody>                     
				                </table>	
				                </div>
				                </div>							                          	
							</div>
						
						<div>
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>													
						</div>					
						</div>

				</fieldset>
			</section>			
		</form>
	</div>
</body>
</html>
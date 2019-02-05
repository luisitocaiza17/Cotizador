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
<title>Deducible Ganadero - Cotizador QBE</title>

	<script>
	
	 $(function(){
	  	  $(".wrapper1").scroll(function(){
	  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  	  });
	  	  $(".wrapper2").scroll(function(){
	  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  	  });
	  	});
	 
		var codigo = "";
		var categoria = "";
		var deducible = "";
		var numeroSiniestro = "";
		var rangoFinal = "";
		var rangoInicial = "";
		var tipoProduccion = "";
		var valorPrima = "";
		var tipoConsulta = "";
		
		
		$(document).ready(function(){
			activarMenu("DeducibleGanadero");
			$("#valorPrima").numeric(".");
			$(':input[type="number"]').numeric("");
			$.ajax({
				url : '../DeducibleGanaderoController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){		
					
					$("#loading").remove();
					if(data.numRegistros > 0){
						var listadoDeducibleGanadero = data.listadoDeducibleGanadero;
						$.each(listadoDeducibleGanadero, function(index){
							$("#dataTableContent").append("<tr class='odd gradeX'>"+
							"<td relation='categoria'>" + listadoDeducibleGanadero[index].categoria + "</td>"+
							"<td relation='deducible'>" + listadoDeducibleGanadero[index].deducible + "</td>"+
							"<td relation='numeroSiniestro'>" + listadoDeducibleGanadero[index].numeroSiniestro + "</td>"+
							"<td relation='rangoInicial'>" + listadoDeducibleGanadero[index].rangoInicial + "</td>"+
							"<td relation='rangoFinal'>" + listadoDeducibleGanadero[index].rangoFinal + "</td>"+							
							"<td relation='tipoProduccion'>" + listadoDeducibleGanadero[index].tipoProduccion + "</td>"+
							"<td relation='valorPrima'>" + listadoDeducibleGanadero[index].valorPrima + "</td>"+
							"<td width='175px'>"+
								"<input type='hidden' value='" + listadoDeducibleGanadero[index].codigo + "'/>"+
								"<button type='button' name='actualizar-btn' class='btn btn-success btn-xs actualizar-btn'>"+
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
								$.ajax({
									url : '../DeducibleGanaderoController',
									data : {
										"tipoConsulta" : "obtenerPorID",
										"codigo":$("#codigo").val()
									},
									type : 'post',
									datatype : 'json',
									success : function (data) {
										$("#categoria").val(data.categoria);
										$("#deducible").val(data.deducible);
										$("#numeroSiniestro").val(data.numeroSiniestro);
										$("#rangoFinal").val(data.rangoFinal);
										$("#rangoInicial").val(data.rangoInicial);
										$("#tipoProduccion").val(data.tipoProduccion);
										$("#valorPrima").val(data.valorPrima);
									}
								});
								
							  } 
						});	
						/* Fin Controles Actualizar Registro*/
						
						/* Inicio Controles Elminar Registro */
						$(".eliminar-btn").bind({click: function(){
							var r = confirm("Seguro que desea eliminar " + $(this).parent().parent().children().first().text());
							if(r == true){
								codigo = $(this).parent().children().first().val();
								categoria = "";
								deducible = "";
								numeroSiniestro = "";
								rangoFinal = "";
								rangoInicial = "";
								tipoProduccion = "";
								valorPrima = "";
								tipoConsulta = "eliminar";
								
								enviarDatos(codigo,categoria,deducible,numeroSiniestro,rangoFinal,rangoInicial,tipoProduccion,valorPrima,tipoConsulta);
								$(this).parent().parent().remove();
							}
						}
					});
						/* Fin Controles Elminar Registro */
					}else{
						$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</tr>");
					}
				}
			});
			
			/* Inicio Controles Grabar Resgistro*/
			$("#save-record").click(function() {
				$(".required").css("border", "1px solid #ccc");
				$(".required").each(function(index) {
					var cadena = $(this).val();
					if (cadena.length <= 0) {
						$(this).css("border", "1px solid red");
						alert("Por favor ingrese el campo requerido");
						$(this).focus();
						return false;
				}
			});
			
				codigo =  $("#codigo").val();
				categoria = $("#categoria").val();
				deducible = $("#deducible").val();
				numeroSiniestro = $("#numeroSiniestro").val();
				rangoFinal = $("#rangoFinal").val();
				rangoInicial = $("#rangoInicial").val();
				tipoProduccion = $("#tipoProduccion").val();
				valorPrima = $("#valorPrima").val();
				if(codigo == ""){
					tipoConsulta = "crear";
				}else{
					tipoConsulta = "actualizar";
				}
				enviarDatos(codigo,categoria,deducible,numeroSiniestro,rangoFinal,rangoInicial,tipoProduccion,valorPrima,tipoConsulta);	
				
			});			
			/* Fin Controles Grabar Resgistro*/		
			
			function enviarDatos(codigo,categoria,deducible,numeroSiniestro,rangoFinal,rangoInicial,tipoProduccion,valorPrima,tipoConsulta){
				$.ajax({
					url : '../DeducibleGanaderoController',
					data : {
						"codigo" : codigo,
						"categoria" : categoria,
						"deducible" : deducible,
						"numeroSiniestro" : numeroSiniestro,
						"rangoFinal" : rangoFinal,
						"rangoInicial" : rangoInicial,
						"tipoProduccion" : tipoProduccion,
						"valorPrima" : valorPrima,			
						"tipoConsulta" : tipoConsulta						
					},					
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						$("#msgPopup").show();
					}
				});						
			}		
							
			/*Validar solo numeros*/
			
			/*Fin validar solo numeros*/			
			
		});
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
							"Deducibles")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
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
								<span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Nuevo Deducible Ganadero</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El deducible ganadero se grabo con exito.</div>
							<div class="form-group">
							
								<input type="hidden" class="form-control" id="codigo">								
								<label>Categoria</label> 								
								<select type="select" class="form-control required" id="categoria" required>
									<option>Seleccione una opcion</option>
                                	<option>I</option>
                                	<option>II</option>
                                	<option>III</option>
                                	<option>IV</option>
                                	<option>V</option>
								</select>
                            	<label>Deducible</label> 
								<input type="text" class="form-control required" id="deducible" placeholder="Número negativo" required>
								<label>Número Siniestro</label>
								<select type="select" class="form-control required" id="numeroSiniestro"requiered>
									<option>Seleccione una opción</option>
									<option>1ero.</option>
									<option>2do.</option>
									<option>3ro.</option>
									<option>4to.</option>
									<option>5to o más</option>
								</select> 
								<!-- <input type="text" class="form-control required" id="numeroSiniestro" placeholder="1ero, 2do, 3ro..." required> -->
								<label>Rango Inicial</label> 
								<input type="number" class="form-control required" id="rangoInicial" required/>
								<label>Rango Final</label> 
								<input type="number" class="form-control required" id="rangoFinal" required/>
								<label>Tipo de Produccion</label> 
								<select type="select" class="form-control required" id="tipoProduccion">
                                	<option>Seleccione una opción</option>
                                	<option>LECHE</option>
                                	<option>CARNE</option>
                            	</select>
                            	<label>Prima</label> 
								<input type="text" class="form-control required" id="valorPrima" required>                            	
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar
								Cambios</button>
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
		<div class="wrapper1">
  			<div class="div1"></div>
			</div>
			<div class="wrapper2">
			<div class="div2">
			<div style="overflow: auto;" class="table-responsive">
				<div class="input-group"> <span class="input-group-addon">Filtro</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>													
							<th>Categoria</th>						
							<th>Deducible</th>
							<th># Siniestro</th>
							<th>Desde</th>
							<th>Hasta</th>
							<th>Produccion</th>
							<th>Prima</th>
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
			</div></div>
		</div>
	</div>
	<!-- Datatable -->	

</body>
</html>
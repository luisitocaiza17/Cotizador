<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Modelo - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
	<script src="../static/js/select2.js"></script>	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		var codigo = "";
		var marca  = "";
		var nombre = "";
		var indiceDanoParcial = "";
		var tonelaje = "";
		var modeloGenerico = "";
		var claseVehiculo = "";
		var activo = "";
		var tipoConsulta = "";
		var arrNombre = new Array();
		var arrCodigo = new Object();
		
		var arrClaseVehiculo = new Array();
		var arrClaseVehiculoId = new Object();
		
		var arrModeloGenerico = new Array();
		var arrModeloGenericoId = new Object();
		
		$(document).ready(function() {
			activarMenu("Modelo");
			$.ajax({
				url : '../MarcaController',
				data : {
					"tipoConsulta" : "cargaSelect2"
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {

					var marcas2 = data.listadoMarca;
					var marcas=[];
					
					$.each(marcas2, function(index){
			    		arrNombre.push( marcas2[index].text );
		                arrCodigo[marcas2[index].text] = marcas2[index].id;
		                marcas[index]={"id":marcas2[index].text,"text":marcas2[index].text};
			    	});
					
					$('#marcaSearch').select2({
						data : marcas2,
						placeholder : 'Seleccione una marca'
					});
					
					$('#marca').select2({
						data : marcas,
						placeholder : 'Seleccione una marca'
					});
					
					
					//if (id != null)
					///	$('#marcaSearch').select2("val", id);
				}
			});
			
			$.ajax({
				url : '../ModeloController',
				data : {
					"tipoConsulta" : "cargaMantenimientoModelo"
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					var listadoModeloGenerico = data.listadoModeloGenerico;
					var listadoClaseVehiculo = data.listadoClaseVehiculo;

					var clasesVehiculo = [];
					var modelosGenericos = [];
					$.each(listadoModeloGenerico,function (index) {
						arrModeloGenerico.push(listadoModeloGenerico[index].nombre);
						arrModeloGenericoId[listadoModeloGenerico[index].nombre] = listadoModeloGenerico[index].codigo;
						modelosGenericos[index]={"id":listadoModeloGenerico[index].nombre,"text":listadoModeloGenerico[index].nombre};
					});

					$.each(listadoClaseVehiculo,function (index) {
						arrClaseVehiculo.push(listadoClaseVehiculo[index].nombre);
						arrClaseVehiculoId[listadoClaseVehiculo[index].nombre] = listadoClaseVehiculo[index].codigo;
						//clasesVehiculo[index]={"id":listadoClaseVehiculo[index].nombre,"text":listadoClaseVehiculo[index].nombre};
						$("#claseVehiculo").append('<option value="'+listadoClaseVehiculo[index].nombre+'">'+listadoClaseVehiculo[index].nombre+'</option>');
					});
					
					$('#modeloGenerico').select2({
						data : modelosGenericos,
						placeholder : 'Seleccione una Modelo Generico '
					});
					
				}
			});

							$('#marcaSearch').on('change',
									function(e) {
										cargarModelos($('#marcaSearch').select2('val'));
									});

							/* Inicio Controles Grabar Resgistro*/
							$("#save-record").click(
											function() {
												$(".required").css("border","1px solid #ccc");
												$(".required").each(function(index) {
																	var cadena = $(this).val();
																	if ($(this).next().attr("type") == "select2")
																		cadena = $(this).next().select2("val");
																	if (cadena.length <= 0) {
																		$(this).css("border","1px solid red");
																		$(this).focus();
																		return false;
																	}
																});

												if ($("#matriz").is(':checked'))
													matriz = 1;
												if ($("#activo").is(':checked'))
													activo = 1;
												codigo = $("#codigo").val();
												nombre = $("#nombre").val();
												marca = arrCodigo[$("#marca").select2('val')];
												if (codigo == "")
													tipoConsulta = "crear";
												else
													tipoConsulta = "actualizar";
												codigoEnsurance = $("#codigoEnsurance").val();
												indiceDanoParcial=$("#indiceDanoParcial").val();
												tonelaje=$("#tonelaje").val();
												modeloGenerico=arrModeloGenericoId[$("#modeloGenerico").select2("val")];
												claseVehiculo=arrClaseVehiculoId[$("#claseVehiculo").val()];		
												
												if(codigoEnsurance=="" && nombre=="" && indiceDanoParcial=="" &&  tonelaje=="")
													{
													alert("Ingrese al menos un valor ");
													}
												else
													{
												enviarDatos(codigoEnsurance, codigo, marca, nombre, indiceDanoParcial, tonelaje, modeloGenerico, claseVehiculo, activo, tipoConsulta);											
												$("#close-popup").trigger("click");
													}
												});
							/* Fin Controles Grabar Resgistro*/
							$("#close-popup").click(function(){
								 location.reload();
							});
							
						});
		
		function enviarDatos(codigoEnsurance, codigo, marca, nombre, indiceDanoParcial, tonelaje, modeloGenerico, claseVehiculo, activo, tipoConsulta) {
			$.ajax({
				url : '../ModeloController',
				data : {
					"codigoEnsurance" : codigoEnsurance,
					"codigo" : codigo,
					"marca" : marca,
					"nombre" : nombre,
					"activo" : activo,
					"indiceDanoParcial" : indiceDanoParcial,
					"tonelaje" : tonelaje,
					"modeloGenerico" : modeloGenerico,
					"claseVehiculo" : claseVehiculo,
					"tipoConsulta" : tipoConsulta
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#msgPopup").show();
				}
			});
		}

		function cargarModelos(marca) {
			$("#dataTableContent > tr").remove();
			$
					.ajax({
						url : '../ModeloController',
						data : {
							"tipoConsulta" : "cargaTodosPorMarca",
							"marca" : marca
						},
						type : 'post',
						datatype : 'json',
						success : function(data) {
							var listadoModelo = data.listadoModelo;
							if (listadoModelo.length > 0) {
								$.each(listadoModelo,function(index) {
													$("#dataTableContent").append(
																	"	<tr class='odd gradeX'>"
																			+ " <td relation='codigoEnsurance'>"
																			+ listadoModelo[index].codigoEnsurance
																			+ "</td>"
																			+ " <td relation='marca'>"
																			+ listadoModelo[index].marca
																			+ "</td>"
																			+ " <td relation='nombre'>"
																			+ listadoModelo[index].nombre
																			+ "</td>"
																			+ " <td relation='indiceDanoParcial'>"
																			+ listadoModelo[index].indiceDanoParcialFrecuencia
																			+ "</td>"
																			+ " <td relation='tonelaje'>"
																			+ listadoModelo[index].tonelaje
																			+ "</td>"
																			+ " <td relation='modeloGenerico'>"
																			+ listadoModelo[index].modeloGenerico
																			+ "</td>"
																			+ " <td relation='claseVehiculo'>"
																			+ listadoModelo[index].claseVehiculo
																			+ "</td>"
																			+ " <td relation='activo'>"
																			+ listadoModelo[index].activo
																			+ "</td>"
																			+ " <td width='175px'>"
																			+ " <input type='hidden' value='"+ listadoModelo[index].codigo +"'/>"
																			+ " <button type='button' class='btn btn-success btn-xs actualizar-btn'>"
																			+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																			+ " </button>"
																			+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>"
																			+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																			+ " </button>"
																			+ "</td>"
																			+ "</tr>");

												});

								$("#loading").fadeOut();

								var listadoModelo = data.listadoModelo;
								
								/* Inicio Controles Actualizar Registro*/
								$(".actualizar-btn").bind({click : function() {
														$("#addButton").trigger("click");
														$("#codigo").val($(this).parent().children().first().val());
														var elem = $(this).parent();
														var bandera = 1;
														do {
															elem = elem.prev();
															if (elem.is("td")) {
																var elemCode = elem.attr("relation");
																elementType(elem.text(),elemCode,$("#" + elemCode).attr("type"));
															} else {
																bandera = 0;
															}
														} while (bandera == 1);
													}
												});
								/* Fin Controles Actualizar Registro*/

								/* Inicio Controles Elminar Registro */
								$(".eliminar-btn").bind({click : function () {
										var r = confirm("Seguro que desea eliminar el Modelo "+ $(this).parent().parent().children().first().text());
										if (r == true) {
											codigo = $(this).parent().children().first().val();
											nombre = "";
											matriz = "";
											activo = "";
											codigoEnsurance = "";
											indiceDanoParcial = "";
											tonelaje = "";
											modeloGenerico = "";
											claseVehiculo = "";
											tipoConsulta = "eliminar";
											enviarDatos(codigoEnsurance, codigo, marca, nombre, indiceDanoParcial, tonelaje, modeloGenerico, claseVehiculo, activo, tipoConsulta);
											$(this).parent().parent().remove();
										}
									}
								});
								/* Fin Controles Elminar Registro */
							} else {
								$("#dataTableContent")
										.append(
												"<tr><td colspan='4'>No existen Registros</td></tr>");
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
							"Modelo")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton" >
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>
		
		<input style="width:90%" type="select" id="marcaSearch" name="marca" class="marca datosVehiculo cambioVehiculo ">

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
							<h4 class="modal-title" id="myModalLabel">Nuevo Modelo</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El Modelo se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Codigo Ensurance</label> 
								<input type="text"class="form-control required" id="codigoEnsurance">
								<label>Marca</label>
								<input type="select2" class="required" id="marca" style="width: 100%;">
								<label>Nombre</label> 
								<input type="text"class="form-control required" id="nombre">
								<label>Indice Daño Parcial</label> 
								<input type="number" min="0" class="form-control required" id="indiceDanoParcial" value="0">
								<label>Tonelaje</label> 
								<input type="number" min="0" class="form-control required" id="tonelaje"  value="0">
								<label>Modelo Genérico</label>
								<input type="select2" class="required" id="modeloGenerico" style="width: 100%;"><br>
								<label>Clase Vehículo</label>
								<select type="select" class="form-control required" id="claseVehiculo" style="width: 100%;">
									 <option>Seleccione una opcion</option>
								</select>								
								<div class="checkbox">
									<label> <input type="checkbox" value="activo"id="activo">Activo</label>
								</div>
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
				<div class="input-group"> <span class="input-group-addon">Filtro</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>
				<br>			
				<table class="table table-striped table-bordered table-hover" id="dataTable">
					<thead>
						<tr>
							<th>Codigo Ensunrace</th>
							<th>Marca</th>
							<th>Nombre</th>
							<th>Indice Daño Parcial</th>
							<th>Tonelaje</th>
							<th>Modelo Generico</th>
							<th>Clase Vehiculo</th>
							<th>Activo</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable">
					</tbody>



				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
</body>
</html>
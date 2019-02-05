<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usuario - CotizadorQBE</title>

    <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
    <!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
    
    <script src="../static/js/util.js"></script>
    <script src="../static/js/select2.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	
	    <!-- SEMANTIC UI -->
	<!-- <script src="../static/js/semantic-ui/semantic.js"></script>
	<link href="../static/js/semantic-ui/semantic.css" rel="stylesheet"> -->
	
	<style type="text/css">	
		.modal-dialog {
	    	width: 1000px;   
		}
	</style>
	
    <script>
        var codigo = "";
        var entidad = "";
        var login = "";
        var password = "";
        var rol = "";
        var puntoDeVenta = "";
        var activo = 0;
        var emite=0;
        var identificacion= "";
        var tipoConsulta = "";
        var mail = "";
        var arrTipoIdentificacion = new Array();
        var arrCodigoTipoIdentificacion = new Object();
        var arrRol = new Array();
        var arrCodigoRol = new Object();
        var arrEntidad = new Array();
        var arrCodigoEntidad = new Object();
        var opcionesSelect2Entidades= new Array();
        var arrPuntoDeVenta = new Array();
        var opcionesSelectRol = new Array();
        var opcionesSelectPuntoVenta = new Array();
        var arrCodigoPuntoDeVenta = new Object();
        var requerido = true;
       
        $(document).ready(function () {
        	
        	/*$('.ui.modal')
        	  .modal()
        	;*/
        	
            activarMenu("Usuario");
            $("#loading").fadeOut("slow"); 
            $("#cargando").fadeIn("slow");   
            $('#LimpiarBtn').click(function(){	 
            	$("#cargando").fadeIn("slow");   
	    	 	$("#filtro_puntoVenta").val(undefined);
	    	 	$("#filtro_rol").val(undefined);
	    	 	$("#filtro_login").val("");
	    	 	$("#filtro_identificacion").val("");
	    	 	$('#filtro_nombre').val("");
	 			$("#filtro_activo").prop('checked', true);
	 			$("#filter").val("");
	 			$('#dataTableContent').html('');
	 		
	 			$("#cargando").fadeOut("slow"); 
	    	 });
            
            $.ajax({
                url: '../UsuarioController',
                data: {
                    "tipoConsulta": "obtenerListas"
//                     "tipoEntidad": "usuarios"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {             	                    
                    
                    var listadoRol = data.listadoRol;
                    $.each(listadoRol, function (index) {
                    	arrRol.push(listadoRol[index].nombre);
                    	arrCodigoRol[listadoRol[index].nombre] = listadoRol[index].codigo;
                    	opcionesSelectRol[index]={"id":listadoRol[index].nombre,"text":listadoRol[index].nombre};
                    });
                    
                    $("#filtro_rol").select2({
        				data : opcionesSelectRol,
        				placeholder : "Escoja un Rol"        				
        			});
                    
                    var listadoPuntoDeVenta = data.listadoPuntoDeVenta;
                    $.each(listadoPuntoDeVenta, function (index) {
                    	arrPuntoDeVenta.push(listadoPuntoDeVenta[index].nombre);
                    	arrCodigoPuntoDeVenta[listadoPuntoDeVenta[index].nombre] = listadoPuntoDeVenta[index].codigo;
                    	opcionesSelectPuntoVenta[index]={"id":listadoPuntoDeVenta[index].nombre,"text":listadoPuntoDeVenta[index].nombre};
                    	
                    });
                    opcionesSelectPuntoVenta[opcionesSelectPuntoVenta.length]={"id":"SIN PUNTO DE VENTA","text":"SIN PUNTO DE VENTA"};
                    arrPuntoDeVenta.push("SIN PUNTO DE VENTA");
                	arrCodigoPuntoDeVenta["SIN PUNTO DE VENTA"] = "";
                	
                    $("#filtro_puntoVenta").select2({
        				data : opcionesSelectPuntoVenta,
        				placeholder : "Escoja un Punto de Venta"        				
        			});
                    
                    $("#rol").select2({
        				data : opcionesSelectRol,
        				placeholder : "Escoja un Rol"        				
        			});
                    
                    $("#puntoDeVenta").select2({
        				data : opcionesSelectPuntoVenta,
        				placeholder : "Escoja un Punto de Venta"        				
        			});
                    
                    var listadoTipoIdentificacion = data.listadoTipoIdentificacion;

                    $.each(listadoTipoIdentificacion, function (index) {
                        arrTipoIdentificacion.push(listadoTipoIdentificacion[index].nombre);
                        arrCodigoTipoIdentificacion[listadoTipoIdentificacion[index].nombre] = listadoTipoIdentificacion[index].codigo;
                        $("#tipoIdentificacion").append("<option value='" + listadoTipoIdentificacion[index].nombre + "'>" + listadoTipoIdentificacion[index].nombre + "</option>");
                    });
                    
                    $("#cargando").fadeOut("slow"); 
                }
            });
            
            
            $('#addButton').click(function(){
            	
            	$(".modal :input").val("").select2("val","");
            		
            });

            $('#ConsultaBtn').click(function(){
            	$("#loading").fadeIn("slow");   
            	
            	// Validaciones de las busquedas
	    		 var nombreFiltro = $("#filtro_nombre").val();
	    		 var identificacionFiltro = $("#filtro_identificacion").val();
	    		 var activoFiltro = 0;
	    		 var loginFiltro = $("#filtro_login").val();
	    		 var rolFiltro = arrCodigoRol[$("#filtro_rol").select2("val")];
	    		 var puntoVentaFiltro = arrCodigoPuntoDeVenta[$("#filtro_puntoVenta").select2("val")];
	    		 if(nombreFiltro == "" && identificacionFiltro == "" && loginFiltro == "" && rolFiltro == undefined && puntoVentaFiltro == undefined){
	    		 	alert("Ingrese al menos un campo de busqueda");
	    		 	$("#loading").fadeOut("slow");
	    		 	return false;
	    		 }
	    		 if ($("#filtro_activo").is(':checked')) activoFiltro = 1;
	    		 
            	$.ajax({
                    url: '../UsuarioController',
                    data: {
                    	"nombreFiltro" : nombreFiltro,
                    	"identificacionFiltro" : identificacionFiltro,
                    	"activoFiltro" : activoFiltro,
                    	"loginFiltro" : loginFiltro,
                    	"rolFiltro" : rolFiltro,
                    	"puntoVentaFiltro" : puntoVentaFiltro,
                        "tipoConsulta": "encontrarPorFiltro"
//                         "tipoEntidad": "usuarios"
                    },
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                    	$("#loading").fadeIn("slow");      
                    	$('#dataTableContent').html('');
                        if (data.numRegistros > 0) {


                            var listadoUsuario = data.listadoUsuario;
                            $.each(listadoUsuario, function (index) {
                            	var aux="	<tr class='odd gradeX'>" +
                            			" <td relation='identificacion'>" + listadoUsuario[index].identificacion + "</td>" +
                                    	" <td relation='nombre'>" + listadoUsuario[index].nombreCompleto + "</td>" +                         
                                        " <td relation='login'>" + listadoUsuario[index].login + "</td>"+
                                        " <td relation='password' style ='display:none;'>" + listadoUsuario[index].password + "</td>";

                                        aux+= " <td relation='activo'><center>" + listadoUsuario[index].activo + "</center></td>" +
                                        " <td relation='rol'>" + listadoUsuario[index].rol + "</td>" +
                                        " <td relation='puntoDeVenta'>" + listadoUsuario[index].puntoDeVenta + "</td>"+
                                        " <td width='175px'>" +                                    
                                        " <input type='hidden' value='" + listadoUsuario[index].codigo + "'/>" +
                                        " <button type='button' class='btn btn-success btn-xs actualizar-btn' onclick='$(\"#addButton\").trigger(\"click\"); buscarPorIdentificacion(\""+listadoUsuario[index].identificacion+"\")'>" +
                                        " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
                                        " </button>" +
                                        " <button type='button' class='btn btn-info btn-xs cambioEstado-btn'>" +
            						  	"<span class='glyphicon glyphicon glyphicon-refresh' id='cambioEstado-btn'>Activar/Desactivar</span>" +
            							" </button>" +
                                        "</td>" +
                                        "</tr>";
                            $("#dataTableContent").append(aux);
                           	
                           

                            });
                            
                              //Boton Cambio de Estado
                            $(".cambioEstado-btn").bind({click: function() {		
    								codigo = $(this).parent().children().first().val();
    								var activoaux = $(this).parent().prev().prev().prev().text();
    								 if (activoaux == "Si"){
    	  						    		activo = 0;
    	  						    		
    	  						    	}else {
    	  						    		activo = 1;
    	  						    	}
    	                               // codigo = "";
    	                                entidad = "";
    	                                login="";
    	                                password="";
    	                                rol="";
    	                                puntoDeVenta = "";
    	                                tipoConsulta = "cambioEstado";
    									enviarDatos(codigo,entidad,login,password,rol,puntoDeVenta, activo, tipoConsulta);
     								alert("Estado Cambiado");
     								location.reload();
     						}
    					});                       
                            

                            /* Inicio Controles Elminar Registro */
                            $(".eliminar-btn").bind({click: function () {
                                var r = confirm("Seguro que desea eliminar la Usuario " + $(this).parent().parent().children().first().text());
                                if (r == true) {
                                    codigo = $(this).parent().children().first().val();
                                    entidad = "";
                                    login="";
                                    password="";
                                    rol="";
                                    puntoDeVenta = "";
                                    activo="";
                                    tipoConsulta = "eliminar";
                                    enviarDatos(codigo, entidad, login, password, rol, puntoDeVenta, activo, tipoConsulta);
                                    $(this).parent().parent().remove();
                                }
                            }
                            });
                            
                           
                            /* Fin Controles Elminar Registro */
                        } else {
                            $("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
                        }
                        $("#loading").fadeOut("slow"); 
                    }
                });
            })
            
            

            /* Inicio Controles Grabar Resgistro*/
            $("#save-record").click(function () {
            	var valido=true;
                $(".required").each(function (index) {
                    var cadena = $(this).val();
                    if (cadena.length <= 0) {
                        $(this).css("border", "1px solid red");
                        alert("Por favor ingrese el campo requerido");
                        $(this).focus();
                        valido = false;
                        return false;
                    }
                });
                
                if(valido)
				{
                	identificacion=$("#identificacion").val();
                    rol = arrCodigoRol[$("#rol").select2("val")];
                    puntoDeVenta = arrCodigoPuntoDeVenta[$("#puntoDeVenta").select2("val")];
                    tipoIdentificacion=arrCodigoTipoIdentificacion[$("#tipoIdentificacion").val()];
                    nombre=$("#nombre").val();
                    apellido=$("#apellido").val();
                    mail=$("#mail").val();                    
                    var telefono = $("#telefono").val();
                    var extension = $("#extension").val();
                    var celular = $("#celular").val();                    
                    if ($("#activo").is(':checked')) activo = 1;
                    if ($("#emite").is(':checked')) emite = 1;
                    codigo = $("#codigo").val();
                    login = $("#login").val();
                    password = $("#password").val();
                    codigoEnsurance = $("#codigoEnsurance").val();
                    if (codigo == "")
                        tipoConsulta = "crear";
                    else
                        tipoConsulta = "actualizar";
                    enviarDatos(codigo,identificacion,tipoIdentificacion,nombre,apellido,login,password,rol,puntoDeVenta,mail, activo,emite, tipoConsulta,celular,telefono,extension,codigoEnsurance);
				}
            });
            /* Fin Controles Grabar Resgistro*/

		function enviarDatos(codigo,identificacion,tipoIdentificacion,nombre,apellido,login,password,rol,puntoDeVenta,mail, activo,emite, tipoConsulta,celular,telefono,extension,codigoEnsurance){				
    			$.ajax({
    				url : '../UsuarioController',
    				data : {
    					"codigo" : codigo,
    					"identificacion" : identificacion,
    					"mail" : mail,
    					"nombre" : nombre,
    					"apellido" : apellido,
    					"login":login,
    					"password":password,
    					"rol":rol,
    					"puntoDeVenta":puntoDeVenta,
    					"activo" : activo,
    					"emite" : emite,
    					"codigoEnsurance":codigoEnsurance,
    					"tipoConsulta" : "guardar",
    					"tipoIdentificacion":tipoIdentificacion,
    					"telefono" : telefono,
    					"extension" : extension,
    					"celular" : celular,
    				},
    				type : 'POST',
    				datatype : 'json',
    				success : function(data) {
    					if(data.success){
    						$("#msgPopup").show();
    					}
    					else
    						alert(data.error);
    				}
    			});
    		};                    
        });
        
        function buscarPorIdentificacion(identificacion) {        	
        		$("#codigoEnsurance").removeAttr("disabled","disabled");
        		$("#identificacion").val(identificacion);
        		$("#loading_identificacion").fadeIn();
        		if((identificacion+"").length>0)
        			$.ajax({
                url: '../UsuarioController',
                data: {
                    "identificacion": identificacion,
                    "tipoConsulta": "buscarPorIdentificacion"
                },
                type: 'POST',
                datatype: 'json',
                success: function(data) {
                    var usuario = data.usuario;
                    if (usuario != null) {
                        if (usuario.idEnsurance != null) {
                        	
                        	if (usuario.idEnsurance != "")
                            $("#codigoEnsurance").val(usuario.idEnsurance).attr("disabled","disabled");
                           
                        	$("#nombre").val(usuario.nombre);
                            $("#apellido").val(usuario.apellido);                            
                            $("#telefono").val(usuario.telefono);
                            $("#extension").val(usuario.extension);
                            $("#celular").val(usuario.celular);                            
                            $("#tipoIdentificacion").val(usuario.tipoIdentificacion);
                            if (usuario.entidadId != null && usuario.entidadId != "") {
                            	 $("#entidadId").val(usuario.entidadId);
                            	 $("#mail").val(usuario.mail);
                                  if (usuario.usuarioId != null && usuario.usuarioId != "") {
                                    $("#codigo").val(usuario.usuarioId);
                                    $("#login").val(usuario.login);
                                    $("#password").val(usuario.password);
                                    if (usuario.emite)
                                        $("#emite").prop("checked", true);
                                    if (usuario.activo)
                                        $("#activo").prop("checked", true);
                                    $("#rol").select2("val", usuario.rol);
                                    $("#puntoDeVenta").select2("val", usuario.puntoDeVenta);
                                }
                            }
                        } else {
                            if (usuario.entidadId != null && usuario.entidadId != "") {
                                $("#entidadId").val(usuario.entidadId);
                                $("#mail").val(usuario.mail);
                                $("#tipoIdentificacion").val(usuario.tipoIdentificacion);
                                $("#nombre").val(usuario.nombre);
                                $("#apellido").val(usuario.apellido);
                                $("#telefono").val(usuario.telefono);
                                $("#extension").val(usuario.extension);
                                $("#celular").val(usuario.celular);                                
                                if (usuario.usuarioId != null && usuario.usuarioId != "") {
                                    $("#codigo").val(usuario.usuarioId);
                                    $("#login").val(usuario.login);
                                    $("#password").val(usuario.password);
                                    if (usuario.emite)
                                        $("#emite").prop("checked", true);
                                    if (usuario.activo)
                                        $("#activo").prop("checked", true);
                                    
                                    $("#rol").select2("val", usuario.rol);
                                    $("#puntoDeVenta").select2("val", usuario.puntoDeVenta);
                                }
                            }
                        }
                    }

                    $("#loading_identificacion").fadeOut();
    				
                },
                error: function() {
                    $("#loading_identificacion").fadeOut();
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
							"Usuarios")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>

<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Usuario</center></td>

					</tr>
					<tr>
						<th>Punto de venta: <input style="width:100%"  type="select2" class=" required_select2" id="filtro_puntoVenta"></th>
						<th>Rol: <input style="width:100%" type="select2" class=" required_select2" id="filtro_rol"></th>
						<th>Login: <input type="text" id="filtro_login" class="form-control "></th>
					</tr>
					<tr>
						<th>Identificacion: <input type="text" id="filtro_identificacion" class="form-control "></th>
						<th>Nombres: <input type="text" id="filtro_nombre" class="form-control "></th>
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
	
		<!-- Button trigger modal -->

	</div>


<div class="row crud-nav-bar">
    

		<!-- Modal -->
		 
    <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="formCrud">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Nuevo Usuario</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">La Usuario se grabo con exito.</div>
                        
                        <div class="row">
					        <div class="col-md-4">
					            <input type="hidden" class="form-control" id="codigo">
	                            <input type="hidden" class="form-control" id="entidadId">
	                            <label>Identificación: *</label></br>
	                            <input style="width:100%" type="text" class="form-control required_select2" id="identificacion" onchange="buscarPorIdentificacion($(this).val())"></br>
	                            <label>Nombre del Usuario *</label></br>
	                            <input style="width:100%" type="select2" class="form-control required_select2" id="nombre"></br>
	                            <label>Apellido del Usuario</label>
	                            <input type="text" class="form-control required" id="apellido" onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)"> 
	                            <label>Codigo Ensurance</label> 
	                            <input type="text" class="form-control " id="codigoEnsurance" onkeypress="validarKeyPress(event,/[0-9]/)">
	                            <label>Mail</label> 
	                            <input type="text" class="form-control " id="mail" >	                            
					        </div>
					        <div class="col-md-4">
					        	<label>Tipo Identificación</label>
	                            <select type="select" class="form-control required" id="tipoIdentificacion">
	                                <option>Seleccione una opcion</option>
	                            </select>
	                            <label>Tel&eacute;fono Fijo :</label>
								<input type="text" class="form-control" id="telefono" onkeypress="validarKeyPress(event,/[0-9\s]/)" required maxlength="10" >
								<label>Extensi&oacute;n Tel&eacute;fono :</label>
								<input type="text" class="form-control required" id="extension" onkeypress="validarKeyPress(event,/[0-9\s]/)"  maxlength="10" >
								<label>Tel&eacute;fono Celular :</label>
								<input type="text" class="form-control required" id="celular" onkeypress="validarKeyPress(event,/[0-9\s]/)" required maxlength="10" >					        					        
					        </div>
					        <div class="col-md-4">
					            <label>Login</label>
	                            <input type="text" class="form-control required" id="login">								
	                            <label>Password</label>
	                            <input type="text" class="form-control required" id="password"> 	                            
	                            <label>Rol:</label>
								<input style="width:100%"  type="select2" class="required_select2" id="rol">	                            
	                            <label>Punto de Venta:</label>
								<input style="width:100%"  type="select2" class="required_select2" id="puntoDeVenta">	                            
	                            <div class="checkbox">
	                                <label> <input type="checkbox"  id="activo">Activo</label>
	                            </div>	                            
	                            <div class="checkbox">
	                                <label> <input type="checkbox" id="emite">Emite</label>
	                            </div>
					        </div>
					    </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="close-popup"
                                data-dismiss="modal">Cerrar
                        </button>
                        <button type="button" class="btn btn-primary" id="save-record">Guardar
                        </button>
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
            <div class="input-group"><span class="input-group-addon">Filtro</span>
                <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
            </div>
            <br>
            <table class="table table-striped table-bordered table-hover" id="dataTable">
                <thead>
                <tr>
                    <th>Identificación</th>
                    <th>Nombre</th>
                    <th>Login</th>
                    <th>Activo</th>
                    <th>Rol</th>
                    <th>Punto de Venta</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="dataTableContent" class="searchable">

                <div id="loading" style="position: fixed;left: 50%;">
                    <div class="loading-indicator">
                        <img src="../static/images/ajax-loader.gif"/><br/><br/>
                        <span id="loading-msg">Cargando Usuarios Espere...</span>
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
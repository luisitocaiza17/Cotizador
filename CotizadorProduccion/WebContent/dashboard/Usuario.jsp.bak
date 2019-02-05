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
    <link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
    <script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../static/js/util.js"></script>
    <script src="../static/js/select2.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        var codigo = "";
        var entidad = "";
        var login = "";
        var password = "";
        var rol = "";
        var puntoDeVenta = "";
        var activo = 0;
        var tipoConsulta = "";
        var arrTipoIdentificacion = new Array();
        var arrCodigoTipoIdentificacion = new Object();
        var arrRol = new Array();
        var arrCodigoRol = new Object();
        var arrEntidad = new Array();
        var arrCodigoEntidad = new Object();
        var opcionesSelect2Entidades= new Array();
        var arrPuntoDeVenta = new Array();
        var arrCodigoPuntoDeVenta = new Object();
        
       
        $(document).ready(function () {
            activarMenu("Usuario");
            $.ajax({
                url: '../UsuarioController',
                data: {
                    "tipoConsulta": "encontrarTodos"
//                     "tipoEntidad": "usuarios"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	$("#loading").fadeIn("slow");                	                    
                    var listadoTipoIdentificacion = data.listadoTipoIdentificacion;

                    $.each(listadoTipoIdentificacion, function (index) {
                        arrTipoIdentificacion.push(listadoTipoIdentificacion[index].nombre);
                        arrCodigoTipoIdentificacion[listadoTipoIdentificacion[index].nombre] = listadoTipoIdentificacion[index].codigo;
                        $("#tipoIdentificacion").append("<option value='" + listadoTipoIdentificacion[index].nombre + "'>" + listadoTipoIdentificacion[index].nombre + "</option>");
                    });
                    
                    var listadoEntidad = data.listadoEntidad;
                    $.each(listadoEntidad, function (index) {
                    	arrEntidad.push(listadoEntidad[index].nombre);
                    	arrCodigoEntidad[listadoEntidad[index].nombre] = listadoEntidad[index].codigo;
                       // $("#tipoIdentificacion").append("<option value='" + listadoTipoIdentificacion[index].nombre + "'>" + listadoTipoIdentificacion[index].nombre + "</option>");
                    	opcionesSelect2Entidades[index]={"id":listadoEntidad[index].nombre,"text":listadoEntidad[index].nombre};
                    });
                    
                    $("#nombre").select2({
        				data : opcionesSelect2Entidades,
        				placeholder : "Escoja una Entidad"        				
        			});
                    
                    var listadoRol = data.listadoRol;
                    $.each(listadoRol, function (index) {
                    	arrRol.push(listadoRol[index].nombre);
                    	arrCodigoRol[listadoRol[index].nombre] = listadoRol[index].codigo;
                    	$("#rol").append("<option value='" + listadoRol[index].nombre + "'>" + listadoRol[index].nombre + "</option>");
                       	// $("#tipoIdentificacion").append("<option value='" + listadoTipoIdentificacion[index].nombre + "'>" + listadoTipoIdentificacion[index].nombre + "</option>");
                    	//	opcionesSelect2Entidades[index]={"id":listadoEntidad[index].nombre,"text":listadoEntidad[index].nombre};
                    });
                    
                    var listadoPuntoDeVenta = data.listadoPuntoDeVenta;
                    $("#puntoDeVenta").append("<option value='-1'>SIN PUNTO DE VENTA</option>");
                    $.each(listadoPuntoDeVenta, function (index) {
                    	arrPuntoDeVenta.push(listadoPuntoDeVenta[index].nombre);
                    	arrCodigoPuntoDeVenta[listadoPuntoDeVenta[index].nombre] = listadoPuntoDeVenta[index].codigo;
                    	$("#puntoDeVenta").append("<option value='" + listadoPuntoDeVenta[index].nombre + "'>" + listadoPuntoDeVenta[index].nombre + "</option>");
                       	// $("#tipoIdentificacion").append("<option value='" + listadoTipoIdentificacion[index].nombre + "'>" + listadoTipoIdentificacion[index].nombre + "</option>");
                    	//	opcionesSelect2Entidades[index]={"id":listadoEntidad[index].nombre,"text":listadoEntidad[index].nombre};
                    });

                    
                    if (data.numRegistros > 0) {


                        var listadoUsuario = data.listadoUsuario;
                        $.each(listadoUsuario, function (index) {
                        	var aux="	<tr class='odd gradeX'>" +
                                    " <td relation='nombre'>" + listadoUsuario[index].nombreCompleto + "</td>" +
                                  //  " <td relation='apellido'>" + listadoUsuario[index].apellido + "</td>" +
                                  //  " <td relation='tipoIdentificacion'>" + listadoUsuario[index].tipoIdentificacion + "</td>" +
                                    //" <td relation='identificacion'>" + listadoUsuario[index].identificacion + "</td>" +
                                    //" <td relation='mail'>" + listadoUsuario[index].mail + "</td>" +
                                    " <td relation='login'>" + listadoUsuario[index].login + "</td>"+
                                    " <td relation='password' style ='display:none;'>" + listadoUsuario[index].password + "</td>";
                                    
                                 //   if(listadoUsuario[index].codigoEnsurance!=null)
                                   // 	aux+= " <td relation='codigoEnsurance'>" + listadoUsuario[index].codigoEnsurance + "</td>";
                                    //else
                                    	//aux+= " <td relation='codigoEnsurance'> </td>";

                                    aux+= " <td relation='activo'><center>" + listadoUsuario[index].activo + "</center></td>" +
                                    " <td relation='rol'>" + listadoUsuario[index].rol + "</td>" +
                                    " <td relation='puntoDeVenta'>" + listadoUsuario[index].puntoDeVenta + "</td>"+
                                    " <td width='175px'>" +                                    
                                    " <input type='hidden' value='" + listadoUsuario[index].codigo + "'/>" +
                                    " <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
                                    " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
                                    " </button>" +
//                                     " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
//                                     "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
//                                     " </button>" +
                                    " <button type='button' class='btn btn-info btn-xs cambioEstado-btn'>" +
        						  	"<span class='glyphicon glyphicon glyphicon-refresh' id='cambioEstado-btn'>Activar/Desactivar</span>" +
        							" </button>" +
                                    "</td>" +
                                    "</tr>";
                        $("#dataTableContent").append(aux);

                        });

                        /* Inicio Controles Actualizar Registro*/
                        $(".actualizar-btn").bind({click: function () {
                            $("#addButton").trigger("click");
                            $("#codigo").val($(this).parent().children().first().val());
                            var elem = $(this).parent();
                            var bandera = 1;
                            do {
                                elem = elem.prev();
                                if (elem.is("td")) {
                                    var elemCode = elem.attr("relation");
                                    elementType(elem.text(), elemCode, $("#" + elemCode).attr("type"));
                                } else {
                                    bandera = 0;
                                }
                            } while (bandera == 1);
                        }
                        });
                        /* Fin Controles Actualizar Registro*/
                        
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

            /* Inicio Controles Grabar Resgistro*/
            $("#save-record").click(function () {
                $(".required").css("border", "1px solid #ccc");
                $(".required").each(function (index) {
                    var cadena = $(this).val();
                    if (cadena.length <= 0) {
                        $(this).css("border", "1px solid red");
                        alert("Por favor ingrese el campo requerido");
                        $(this).focus();
                        return false;
                    }
                });

                if ($("#activo").is(':checked')) activo = 1;
                codigo = $("#codigo").val();
                entidad = arrCodigoEntidad[$("#nombre").select2("val")];
                login = $("#login").val();
                password = $("#password").val();
                rol = arrCodigoRol[$("#rol").val()];
                puntoDeVenta = arrCodigoPuntoDeVenta[$("#puntoDeVenta").val()];
                
                if (codigo == "")
                    tipoConsulta = "crear";
                else
                    tipoConsulta = "actualizar";
                enviarDatos(codigo,entidad,login,password,rol,puntoDeVenta, activo, tipoConsulta);
				$("#close-popup").trigger("click");
            });
            /* Fin Controles Grabar Resgistro*/

		function enviarDatos(codigo,entidad,login,password,rol,puntoDeVenta, activo, tipoConsulta){				
    			$.ajax({
    				url : '../UsuarioController',
    				data : {
    					"codigo" : codigo,
    					"entidadId":entidad,
    					"login":login,
    					"password":password,
    					"rol":rol,
    					"puntoDeVenta":puntoDeVenta,
    					"activo" : activo,
    					"tipoConsulta" : tipoConsulta,
//     					"tipoEntidad" : "usuarios"
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
    		};
            
            $("#identificacion").change(function(){
				$("#loading_identificacion").fadeIn();
				var identificacion=$("#identificacion").val();
				
				$.ajax({
					url : '../EntidadController',
					data : {
						"identificacion" : identificacion,
						"tipoConsulta" : "cargarPorIdentificacion"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var entidad=data.entidad;
						if(entidad.codigo!=null ){
						$("#codigoEnsurance").val(entidad.codigoEnsurance);
						$("#codigo").val(entidad.codigo);
						//$("#identificacion").val();
						$("#nombre").val(entidad.nombre);
						$("#apellido").val(entidad.apellido);
						$("#mail").val(entidad.mail);
						$("#tipoIdentificacion").val(entidad.tipoIdentificacion); }
						$("#loading_identificacion").fadeOut();
					},
					error:function(){
						$("#loading_identificacion").fadeOut();
					}
				});
				
			});
            
        });
    </script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
%>
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
                        <h4 class="modal-title" id="myModalLabel">Nuevo Usuario</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">La Usuario se grabo con exito.</div>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="codigo">
                            <label>Nombre del Usuario *</label></br>
                            <input style="width:100%" type="select2" class="required_select2" id="nombre"></br>
                            <!-- <label>Apellido del Usuario</label>
                            <input type="text" class="form-control required" id="apellido" onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)"> 
                            <label>Codigo Ensurance</label> 
                            <input type="text" class="form-control required" id="codigoEnsurance" onkeypress="validarKeyPress(event,/[0-9]/)">
                            <label>Tipo Identificación</label>
                            <select type="select" class="form-control required" id="tipoIdentificacion">
                                <option>Seleccione una opcion</option>
                            </select>
							
                            <label>Identificación</label>
                            <input type="text" class="form-control required" id="identificacion">
                             <div id="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div>
							-->
							 	
                            <label>Login</label>
                            <input type="text" class="form-control required" id="login">
							
                            <label>Password</label>
                            <input type="text" class="form-control required" id="password"> 
                            
                            <label>Rol:</label>
							<select type="select" class="form-control required" id="rol"></select>	
                            
                            <label>Punto de Venta:</label>
							<select type="select" class="form-control required" id="puntoDeVenta"></select>	
                            

                            <div class="checkbox">
                                <label> <input type="checkbox" value="activo" id="activo">Activo</label>
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
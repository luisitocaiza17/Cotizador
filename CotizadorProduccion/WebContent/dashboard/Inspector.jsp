<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>         
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <title>Inspector - CotizadorQBE</title>

    <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../static/css/loading.css" rel="stylesheet">

    <script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../static/js/util.js"></script>
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        var codigo = "";
        var nombre = "";
        var apellido = "";
        var identificacion = "";
        var activo = 0;
        var mail = "";
        var firma = "";
        var tipoConsulta = "";
        var usuario="";
        var sucursal="";
        var arrTipoInspector = new Array();
        var arrCodigoTipoInspector = new Object();
        var arrUsuario = new Array();
        var arrCodigoUsuario = new Object();
        var arrSucursal = new Array();
        var arrCodigoSucursal = new Object();
        
        $(document).ready(function () {
            activarMenu("Inspector");
            $.ajax({
                url: '../InspectorController',
                data: {
                    "tipoConsulta": "encontrarTodos",
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	if (data.numRegistros > 0) {
                    $("#loading").remove();
                    var listadoTipoInspector = data.listadoTipoInspector;
                    var listadoUsuario = data.listadoUsuario;
                    var listadoSucursal = data.listadoSucursal;
                    
                    $.each(listadoTipoInspector, function (index) {
                        arrTipoInspector.push(listadoTipoInspector[index].nombre);
                        arrCodigoTipoInspector[listadoTipoInspector[index].nombre] = listadoTipoInspector[index].codigo;
                        $("#tipoInspector").append("<option value='" + listadoTipoInspector[index].nombre + "'>" + listadoTipoInspector[index].nombre + "</option>");
                    });
                    
                    $.each(listadoUsuario, function (index) {
                        arrUsuario.push(listadoUsuario[index].nombre);
                        arrCodigoUsuario[listadoUsuario[index].nombre] = listadoUsuario[index].codigo;
                        $("#usuario").append("<option value='" + listadoUsuario[index].nombre + "' usuarioId='" + listadoUsuario[index].codigo + "'>" + listadoUsuario[index].nombre + "</option>");
                    });
                    
                    $.each(listadoSucursal, function (index) {
                        arrSucursal.push(listadoSucursal[index].nombre);
                        arrCodigoSucursal[listadoSucursal[index].nombre] = listadoSucursal[index].codigo;
                        $("#sucursal").append("<option value='" + listadoSucursal[index].nombre + "' sucursalId='" + listadoSucursal[index].codigo + "'>" + listadoSucursal[index].nombre + "</option>");
                    });
                    
                    	var listadoInspector = data.listadoInspector;                    	
                    	$.each(listadoInspector, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +									
									" <td relation='nombre'>" + listadoInspector[index].nombre + "</td>" +		
									" <td relation='tipoInspector'>" + listadoInspector[index].tipoInspector + "</td>" +									        
									" <td relation='valorKM'>" + listadoInspector[index].valorKM + "</td>" +
		                            " <td relation='mail1'>" + listadoInspector[index].mail1 + "</td>" +
		                            " <td relation='mail2'>" + listadoInspector[index].mail2 + "</td>" +
		                            " <td relation='mail3'>" + listadoInspector[index].mail3 + "</td>" +
		                            " <td relation='sucursal'>" + listadoInspector[index].sucursal + "</td>" +
		                            " <td relation='usuario'>" + listadoInspector[index].usuario + "</td>" +			                            
		                             " <td width='175px'>" +                             
		                            " <input type='hidden' value='" + listadoInspector[index].codigo + "'/>" +                            
		                            " <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		                            " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
		                            " </button>" +
		                             " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
		                             "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
		                             " </button>" +
		                            "</td>" +
		                            "</tr>");
						});
                    	
                    	listadoInspector = null;                 
                        
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

                        /* Inicio Controles Eliminar Registro */
                        $(".eliminar-btn").bind({click: function () {
                            var r = confirm("Seguro que desea eliminar el Inspector " + $(this).parent().parent().children().first().text());
                            if (r == true) {
                            	codigo = $(this).parent().children().first().val();
                                tipoInspector = "";
                                nombre = "";
                                valorKM = "";
                                mail1="";
                                mail2="";
                                mail3="";
                                sucursal = "";
                                usuario="";                                
                                tipoConsulta = "eliminar";
                                enviarDatos(codigo, tipoInspector, nombre, valorKM, mail1, mail2, mail3, usuario, sucursal, tipoConsulta);
                                $(this).parent().parent().remove();
                            }
                        }
                        });
                        /* Fin Controles Elminar Registro */
                    } else {
                        $("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
                    }
                }
            });

            /* Inicio Controles Grabar Resgistro*/
            $("#save-record").click(function () {
            	var retorno=true;
                $(".required").css("border", "1px solid #ccc");
                $(".required").each(function (index) {
                    var cadena = $(this).val();
                    if (cadena.length <= 0 && retorno) {
                        $(this).css("border", "1px solid red");
                        alert("Por favor ingrese el campo requerido");
                        $(this).focus();
                        retorno= false;
                    }
                });


                codigo = $("#codigo").val();
                tipoInspector = arrCodigoTipoInspector[$("#tipoInspector").val()];
                nombre = $("#nombre").val();
                valorKM = $("#valorKM").val();                
                mail1 = $("#mail1").val();
                mail2 = $("#mail2").val();
                mail3 = $("#mail3").val();
                usuario = arrCodigoUsuario[$("#usuario").val()];
                sucursal = arrCodigoSucursal[$("#sucursal").val()];              
                

                if (codigo == ""){
                	tipoConsulta = "crear";
                }                    
                else{
                	tipoConsulta = "actualizar";
                }  
                
                if(retorno)
                {
                	enviarDatos(codigo, tipoInspector, nombre, valorKM, mail1, mail2, mail3, usuario, sucursal, tipoConsulta);
                }
                	
               
            });
            /* Fin Controles Grabar Resgistro*/

            function enviarDatos(codigo, tipoInspector, nombre, valorKM, mail1, mail2, mail3, usuario, sucursal, tipoConsulta) {
                $.ajax({
                    url: '../InspectorController',
                    data: {                        
                        "codigo": codigo,
                        "tipoInspector": tipoInspector,
                        "nombre": nombre,                       
                        "valorKM": valorKM,
                        "mail1": mail1,
                        "mail2": mail2,
                        "mail3": mail3,
                        "sucursal": sucursal,
                        "usuario": usuario,
                        "tipoEntidad": "inspectores",
                        "tipoConsulta": tipoConsulta
                    },
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        $("#msgPopup").show();
//                         $("#close-popup").trigger("click");
                    }
                });
            }            

            $("#mail1").change(function(){
				var mail=$("#mail1").val();
				if(!IsEmail(mail))
					{
					alert("Ingrese un correo Valido!");
					$("#mail1").val('');
					}
								
			});
            $("#mail2").change(function(){
				var mail=$("#mail2").val();
				if(!IsEmail(mail))
					{
					alert("Ingrese un correo Valido!");
					$("#mail2").val('');
					}
								
			});
            $("#mail3").change(function(){
				var mail=$("#mail3").val();
				if(!IsEmail(mail))
					{
					alert("Ingrese un correo Valido!");
					$("#mail3").val('');
					}
								
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
							"Inspectores")) {
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
                        <button type="button" class="close" data-dismiss="modal" onclick="window.location.reload();">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Nuevo Inspector</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">El Inspector se grabo con exito.</div>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="codigo">                            
                            <label>Tipo Inspector</label>
                            <select type="select" class="form-control required" id="tipoInspector">
                                <option value=''>Seleccione una opcion</option>
                            </select>                                                     
							<label>Nombre del Inspector</label>
                            <input type="text" class="form-control required" id="nombre">
                            <label>Valor KM</label>
                            <input type="text" class="form-control" id="valorKM">
                            <label>Email 1</label>
                            <input type="text" class="form-control required" id="mail1">
                            <label>Email 2</label>
                            <input type="text" class="form-control" id="mail2">
                            <label>Email 3</label>
                            <input type="text" class="form-control" id="mail3">  
                            <label>Usuario</label>
                            <select type="select" class="form-control required" id="usuario">
                                <option value=''>Seleccione una opcion</option>
                            </select>
							<label>Sucursal</label>
                            <select type="select" class="form-control required" id="sucursal">
                                <option value=''>Seleccione una opcion</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="close-popup"
                                data-dismiss="modal">Cerrar
                        </button>
                        <button type="button" class="btn btn-primary" id="save-record" >Guardar
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
            <table class="table table-striped table-bordered table-hover"
                   id="dataTable">
                <thead>
                <tr>
                	<th>Nombre</th>             
                	<th>Tipo Inspector</th>                    
                    <th>Valor KM</th>
                    <th>Mail1</th>
                    <th>Mail2</th>
                    <th>Mail3</th>
                    <th>Sucursal</th>
                    <th>Usuario</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="dataTableContent" class="searchable">

                <div id="loading">
                    <div class="loading-indicator">
                        <img src="../static/images/ajax-loader.gif"/><br/><br/>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>         
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <title>Distancia Inspector - CotizadorQBE</title>

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
        var inspectorId = "";
        var inspectorNombre = "";
        var origen = "";
        var destino = "";
        var km_ida = "";
        var km_roundtrip = "";
        var tipoConsulta = "";
        
        function cargaInicial(){
        	$.ajax({
                url: '../DistanciaInspectorController',
                data: {
                    "tipoConsulta": "encontrarTodos",
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	if (data.success) {
                    $("#loading").remove();
                    var listadoInspector = data.listadoInspector;
                    var listadoOrigen = data.listadoOrigen;
                    var listadoDistanciaInspector=data.listadoDistanciaInspector;
                    
                    $('#inspector').select2({
						data : listadoInspector,
						placeholder : 'Seleccione un Inspector'
					});
                    

                    $('#origen').select2({
						data : listadoOrigen,
						placeholder : 'Seleccione un Origen'
					});
                    
                    $("#dataTableContent").html("");
                    
                    	$.each(listadoDistanciaInspector, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +									
									" <td relation='inspector' hidden='hidden' >" + listadoDistanciaInspector[index].inspectorId + "</td>" +		
									" <td relation='inspectorNombre'>" + listadoDistanciaInspector[index].inspectorNombre + "</td>" +									        
									" <td relation='origen'>" + listadoDistanciaInspector[index].origen + "</td>" +
		                            " <td relation='destino'>" + listadoDistanciaInspector[index].destino + "</td>" +
		                            " <td relation='km_ida'>" + listadoDistanciaInspector[index].kmIda + "</td>" +
		                            " <td relation='km_roundtrip'>" + listadoDistanciaInspector[index].kmRoundTrip + "</td>" +
		                            " <td width='175px'>" +                             
		                            " <input type='hidden' id='codigo' value='" + listadoDistanciaInspector[index].codigo + "'/>" +                            
		                            " <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		                            " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
		                            " </button>" +
		                             " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
		                             "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
		                             " </button>" +
		                            "</td>" +
		                            "</tr>");
						});
                    	
                    	
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
                            var r = confirm("Seguro que desea eliminar la Distancia del Inspector ");
                            if (r == true) {
                            	 codigo=$(this).parent().children().first().val();
                                 inspectorId = "";
                                inspectorNombre = "";
                                origen = "";
                                destino = "";
                                km_ida = "";
                                km_roundtrip = "";
                                tipoConsulta = "eliminar";
                                enviarDatos(codigo, inspectorId, origen, destino, km_ida, km_roundtrip, tipoConsulta);
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
        }
        
        $(document).ready(function () {
            activarMenu("DistanciaInspector");
            cargaInicial();

            /* Inicio Controles Grabar Resgistro*/
            $("#save-record").click(function () {
            	var retorno=true;
                $(".required").css("border", "1px solid #ccc");
                $(".required").each(function (index) {
                    var cadena = $(this).val();
                    if($(this).attr("class").indexOf("select2")>-1)
                    	cadena = $(this).select2("val");
                    
                    if (cadena.length <= 0 && retorno) {
                        $(this).css("border", "1px solid red");
                        alert("Por favor ingrese el campo requerido");
                        $(this).focus();
                        retorno= false;
                    }
                });


                codigo = $("#codigo").val();
                inspectorId = $("#inspector").select2("val");
                origen = $("#origen").select2("val");
                destino = $("#destino").val();                
                km_ida = $("#km_ida").val();
                km_roundtrip = $("#km_roundtrip").val();
                
                if (codigo == ""){
                	tipoConsulta = "crear";
                }                    
                else{
                	tipoConsulta = "actualizar";
                }  
                
                if(retorno)
                {
                	enviarDatos(codigo, inspectorId, origen, destino, km_ida, km_roundtrip, tipoConsulta);
                }
                	
               
            });
            /* Fin Controles Grabar Resgistro*/

            function enviarDatos(codigo, inspectorId, origen, destino, km_ida, km_roundtrip, tipoConsulta) {
                $.ajax({
                    url: '../DistanciaInspectorController',
                    data: {                        
                        "codigo": codigo,
                        "inspectorId": inspectorId,
                        "origen": origen,                       
                        "destino": destino,
                        "kmIda": km_ida,
                        "kmRoundtrip": km_roundtrip,
                        "tipoConsulta": tipoConsulta
                    },
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        $("#msgPopup").show();
                        cargaInicial();
                        $("#codigo").val("");
                        $("#inspector").select2("val","");
                        $("#origen").select2("val","");
                        $("#destino").val("");                
                        $("#km_ida").val("");
                        $("#km_roundtrip").val("");
//                         $("#close-popup").trigger("click");
                    }
                });
            }    
            
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
                        <h4 class="modal-title" id="myModalLabel">Nueva Distancia Inspector</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">La Distancia Inspector se grabo con exito.</div>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="codigo">                            
                            <label>Inspector</label>
                            <input type="select2" class=" required select2" id="inspector" style="width: 100%;">                                                     
							<label>Origen</label>
                            <input type="select2" class=" required select2" id="origen" style="width: 100%;">
                            <label>Destino</label>
                            <input type="text" class="form-control" id="destino">
                            <label>Km Ida</label>
                            <input type="number" class="form-control required" id="km_ida">
                            <label>Km RoundTrip</label>
                            <input type="number" class="form-control" id="km_roundtrip">
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
                	<th>Inspector</th>             
                	<th>Origen</th>                    
                    <th>Destino</th>
                    <th>Km Ida</th>
                    <th>Km RountTrip</th>
                    
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>         
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <title>Cobertura - CotizadorQBE</title>

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
    
    $(function(){
    	  $(".wrapper1").scroll(function(){
    	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
    	  });
    	  $(".wrapper2").scroll(function(){
    	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
    	  });
    	});
    
        var codigo = "";
        var nombre = "";
        var nemotecnico = "";
        var texto = "";
        var textoCotizador = "";
        var tipoCobertura = 0;
        var tipoTasa = "";
        var grupoCobertura = "";
        var tasaValor = "";
        var afectaGrupo=0;
        var afectaValorAsegurado=0;
        var seccion="";
        var orden="";
        var limite="";
        var esPredeterminada=0;
        var esPrimaFija=0;
        var esAdicional=0;
        var esTodoRiesgo=0;
        var esMasivo=0;
        var esPrincipal=0;
        var rebajaValorAsegurado="";
        var generaEndosoRasa=0;
        var esIndemnizable=0;
        var esLimiteSuma=0;
        var principalCobertura=0;
        var mostrarCotizador=0;
        var nombreComercial="";
        var arrTipoCobertura = new Array();
        var arrTipoCoberturaCodigo = new Object();
        var arrTipoTasa = new Array();
        var arrTipoTasaCodigo = new Object();
        var arrGrupoCobertura = new Array();
        var arrGrupoCoberturaCodigo = new Object();
        
        
        function cargarCoberturas(grupoCobertura){
        	$("#loading").fadeIn('slow');
        	$("#dataTableContent").html("");
            $.ajax({
                url: '../CoberturaController',
                data: {
                    "tipoConsulta": "encontrarPorGrupoCobertura",
                    "grupoCobertura":grupoCobertura
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	if (data.totalCoberturas > 0) {
                    $("#loading").remove();
                    
                    	var listadoCoberturas = data.listadoCobertura;                    	
                    	$.each(listadoCoberturas, function(index){
                    		var aux="	<tr class='odd gradeX'>" +
							" <td relation='nombre'>" + listadoCoberturas[index].nombre + "</td>" +
							" <td relation='nemotecnico'>" + listadoCoberturas[index].nemotecnico + "</td>" +
							" <td relation='tipoTasa'>" + listadoCoberturas[index].tipoTasa + "</td>" ;
							
							aux+=	" <td relation='tipoCobertura'>" + listadoCoberturas[index].tipoCobertura + "</td>" +
							" <td hidden='hidden' relation='texto'>" + listadoCoberturas[index].texto + "</td>" +
							" <td hidden='hidden' relation='textoCotizador'>" + listadoCoberturas[index].textoCotizador + "</td>" +
							" <td relation='grupoCobertura'>" + listadoCoberturas[index].grupoCobertura + "</td>" +
							" <td relation='tasaValor'>" + listadoCoberturas[index].tasaValor + "</td>" ;
							
							if(listadoCoberturas[index].afectaGrupo=='1')
								aux+=" <td relation='afectaGrupo'>Si</td>" ;
							else
								aux+=" <td relation='afectaGrupo'>No</td>" ;

							if(listadoCoberturas[index].afectaValorAsegurado=='1')
								aux+=" <td relation='afectaValorAsegurado'>Si</td>" ;
							else
								aux+=" <td relation='afectaValorAsegurado'>No</td>" ;

								aux+=" <td relation='seccion'>" + listadoCoberturas[index].seccion + "</td>" +
								" <td relation='orden'>" + listadoCoberturas[index].orden + "</td>" +
								" <td relation='limite'>" + listadoCoberturas[index].limite + "</td>" ;

								if(listadoCoberturas[index].esPredeterminada=='1')
									aux+=" <td relation='esPredeterminada'>Si</td>" ;
								else
									aux+=" <td relation='esPredeterminada'>No</td>" ;

								if(listadoCoberturas[index].esPrimaFija=='1')
									aux+=" <td relation='esPrimaFija'>Si</td>" ;
								else
									aux+=" <td relation='esPrimaFija'>No</td>" ;
										
								if(listadoCoberturas[index].esTodoRiesgo=='1')
									aux+=" <td relation='esTodoRiesgo'>Si</td>" ;
								else
									aux+=" <td relation='esTodoRiesgo'>No</td>" ;

								if(listadoCoberturas[index].esMasivo=='1')
									aux+=" <td relation='esMasivo'>Si</td>" ;
								else
									aux+=" <td relation='esMasivo'>No</td>" ;


								if(listadoCoberturas[index].esPrincipal=='1')
									aux+=" <td relation='esPrincipal'>Si</td>" ;
								else
									aux+=" <td relation='esPrincipal'>No</td>" ;
								
								if(listadoCoberturas[index].rebajaValorAsegurado=='1')
									aux+=" <td relation='rebajaValorAsegurado'>Si</td>" ;
								else
									aux+=" <td relation='rebajaValorAsegurado'>No</td>" ;
											
								if(listadoCoberturas[index].generaEndosoRasa=='1')
									aux+=" <td relation='generaEndosoRasa'>Si</td>" ;
								else
									aux+=" <td relation='generaEndosoRasa'>No</td>" ;

								if(listadoCoberturas[index].esIndemnizable=='1')
									aux+=" <td relation='esIndemnizable'>Si</td>" ;
								else
									aux+=" <td relation='esIndemnizable'>No</td>" ;

								if(listadoCoberturas[index].esLimiteSuma=='1')
										aux+=" <td relation='esLimiteSuma'>Si</td>" ;
									else
										aux+=" <td relation='esLimiteSuma'>No</td>" ;
								
								if(listadoCoberturas[index].principalCobertura=='1')
									aux+=" <td relation='principalCobertura'>Si</td>" ;
								else
									aux+=" <td relation='principalCobertura'>No</td>" ;
								
								if(listadoCoberturas[index].mostrarCotizador=='1')
									aux+=" <td relation='mostrarCotizador'>Si</td>" ;
								else
									aux+=" <td relation='mostrarCotizador'>No</td>" ;
								
								if(listadoCoberturas[index].nombreComercial!=null)
									aux+=" <td relation='nombreComercial'>" + listadoCoberturas[index].nombreComercial + "</td>" ;
								else
		                          aux+=" <td relation='nombreComercial'></td>" ;
	                            
		                          aux+="<td> <input type='hidden' value='" + listadoCoberturas[index].codigo + "'/>" +                            
	                            " <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
	                            " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
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

                        /* Inicio Controles Eliminar Registro */
                        $(".eliminar-btn").bind({click: function () {
                            var r = confirm("Seguro que desea eliminar el Empleado " + $(this).parent().parent().children().first().text());
                            if (r == true) {
                                codigo = $(this).parent().parent().children().first().text();
                                nombre = "";
                                nemotecnico = "";
                                texto = "";
                                tipoCobertura = 0;
                                tipoTasa = "";
                                grupoCobertura = "";
                                tasaValor = "";
                                afectaGrupo=0;
                                afectaValorAsegurado=0;
                                seccion="";
                                orden="";
                                limite="";
                                esPredeterminada=0;
                                esPrimaFija=0;
                                esTodoRiesgo=0;
                                esMasivo=0;
                                esPrincipal=0;
                                rebajaValorAsegurado="";
                                generaEndosoRasa=0;
                                esIndemnizable=0;
                                esLimiteSuma=0;
                                principalCobertura=0;
                                mostrarCotizador=0;
                                nombreComercial="";
                                textoCotizador = "";
                                tipoConsulta = "eliminar";
                                enviarDatos(codigo, nombre, nemotecnico, tipoTasa, tipoCobertura, grupoCobertura, tasaValor, afectaGrupo, afectaValorAsegurado, seccion, orden, limite, esPredeterminada, esPrimaFija, esTodoRiesgo, esMasivo, esPrincipal, rebajaValorAsegurado, generaEndosoRasa, esIndemnizable, esLimiteSuma, principalCobertura, mostrarCotizador, nombreComercial, texto, textoCotizador, tipoConsulta);
                                $(this).parent().parent().remove();
                            }
                        }
                        });
                        /* Fin Controles Elminar Registro */
                    } else {
                        $("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
                    }
                	
                	$("#loading").fadeOut('slow');
                }
            });


        }
        
        $(document).ready(function () {
        	$("#addButton").hide();
        	$("#loading").fadeOut('slow');
            activarMenu("Cobertura");
            
            $.ajax({
                url: '../GrupoCoberturaController',
                data: {
                    "tipoConsulta": "encontrarTodosSelect2"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	var listadoGrupoCoberturas=data.listadoGrupoCobertura;
					
					$('#grupoCoberturaSearch').select2({
						data : listadoGrupoCoberturas,
						placeholder : 'Seleccione un Grupo Cobertura'
					});
					
                }
            });
            
            $.ajax({
                url: '../TipoTasaController',
                data: {
                    "tipoConsulta": "encontrarTodos"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	var listadoTipoTasa=data.listadoTipoTasa;
					
                	 $.each(listadoTipoTasa, function (index) {
                         arrTipoTasa.push(listadoTipoTasa[index].nombre);
                         arrTipoTasaCodigo[listadoTipoTasa[index].nombre] = listadoTipoTasa[index].id;
                         $("#tipoTasa").append("<option value='" + listadoTipoTasa[index].nombre + "'>" + listadoTipoTasa[index].nombre + "</option>");
                     });
                	
                }
            });
            
            $.ajax({
                url: '../TipoCoberturaController',
                data: {
                    "tipoConsulta": "encontrarTodos"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	var listadoTipoCobertura=data.listadoTipoCobertura;
					
               	 $.each(listadoTipoCobertura, function (index) {
                        arrTipoCobertura.push(listadoTipoCobertura[index].nombre);
                        arrTipoCoberturaCodigo[listadoTipoCobertura[index].nombre] = listadoTipoCobertura[index].id;
                        $("#tipoCobertura").append("<option value='" + listadoTipoCobertura[index].nombre + "'>" + listadoTipoCobertura[index].nombre + "</option>");
                    });
               	
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
                nombre =$("#nombre").val();
                nemotecnico=$("#nemotecnico").val();
                tipoTasa =arrTipoTasaCodigo[$("#tipoTasa").val()];
                tipoCobertura =arrTipoCoberturaCodigo[$("#tipoCobertura").val()];
                grupoCobertura=$("#grupoCoberturaSearch").select2("val");
                tasaValor =$("#tasaValor").val();
                if ($("#afectaGrupo").is(':checked')) afectaGrupo = 1;
                if ($("#afectaValorAsegurado").is(':checked')) afectaValorAsegurado = 1;
                seccion=$("#seccion").val();
                orden=$("#orden").val();
                limite=$("#limite").val();
                if ($("#esPredeterminada").is(':checked')) esPredeterminada = 1;
                if ($("#primaFija").is(':checked')) esPrimaFija = 1;
                if ($("#todoRiesgo").is(':checked')) esTodoRiesgo = 1;
                if ($("#esMasivo").is(':checked')) esMasivo = 1;
                if ($("#esPrincipal").is(':checked')) esPrincipal = 1;
                if ($("#rebajaValorAsegurado").is(':checked')) rebajaValorAsegurado = 1;
                if ($("#generaEndosoRasa").is(':checked')) generaEndosoRasa = 1;
                if ($("#esIndemnizable").is(':checked')) esIndemnizable = 1;
                if ($("#esLimiteSuma").is(':checked')) esLimiteSuma = 1;
                if ($("#principalCobertura").is(':checked')) principalCobertura = 1;
                if ($("#mostrarCotizador").is(':checked')) mostrarCotizador = 1;
                nombreComercial=$("#nombreComercial").val();
                texto=$("#texto").val();
                textoCotizador=$("#textoCotizador").val();
                //firma = $("#firma").val();
                if (codigo == "")
                    tipoConsulta = "crear";
                else
                    tipoConsulta = "actualizar";
                
                enviarDatos(codigo, nombre, nemotecnico, tipoTasa, tipoCobertura, grupoCobertura, tasaValor, afectaGrupo, afectaValorAsegurado, seccion, orden, limite, esPredeterminada, esPrimaFija, esTodoRiesgo, esMasivo, esPrincipal, rebajaValorAsegurado, generaEndosoRasa, esIndemnizable, esLimiteSuma, principalCobertura, mostrarCotizador, nombreComercial, texto, textoCotizador, tipoConsulta);
               
            });
            /* Fin Controles Grabar Resgistro*/

            function enviarDatos(codigo, nombre, nemotecnico, tipoTasa, tipoCobertura, grupoCobertura, tasaValor, afectaGrupo, afectaValorAsegurado, seccion, orden, limite, esPredeterminada, primaFija, todoRiesgo, esMasivo, esPrincipal, rebajaValorAsegurado, generaEndosoRasa, esIndemnizable, esLimiteSuma, principalCobertura, mostrarCotizador, nombreComercial, texto, textoCotizador, tipoConsulta) {
                $.ajax({
                    url: '../CoberturaController',
                    data: {
                        "codigo": codigo, 
                        "nombre": nombre, 
                        "tipoTasa": tipoTasa,
                        "tipoCobertura": tipoCobertura, 
                        "grupoCobertura": grupoCobertura,
                        "tasaValor": tasaValor, 
                        "afectaGrupo": afectaGrupo, 
                        "afectaValorAsegurado": afectaValorAsegurado, 
                        "seccion": seccion,
                        "orden": orden,
                        "limite": limite,
                        "esPredeterminada": esPredeterminada,
                        "primaFija": primaFija,
                        "todoRiesgo": todoRiesgo,
                        "esMasivo": esMasivo,
                        "esPrincipal": esPrincipal,
                        "rebajaValorAsegurado": rebajaValorAsegurado,
                        "generaEndosoRasa": generaEndosoRasa,
                        "esIndemnizable": esIndemnizable,
                        "esLimiteSuma": esLimiteSuma,
                        "principalCobertura": principalCobertura,
                        "mostrarCotizador": mostrarCotizador,
                        "nombreComercial": nombreComercial,
                        "texto": texto,
                        "textoCotizador": textoCotizador,
                        "tipoConsulta": tipoConsulta
                    },
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) {
                        $("#msgPopup").show();
                        //$("#close-popup").trigger("click");
                        cargarCoberturas($("#grupoCoberturaSearch").select2("val"));
                    }
                });
            }
            
            $('#grupoCoberturaSearch').on('change',
					function(e) {
            	
            		cargarCoberturas($('#grupoCoberturaSearch').select2('val'));
            		$("#addButton").removeAttr("disabled");		
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
							"Cobertura")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>
<div class="row crud-nav-bar">
    <!-- Button trigger modal -->
    <button hidden="hidden" class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton" disabled="disabled"  onClick="$('#grupoCobertura').val($('#grupoCoberturaSearch').select2('data').text);">
        <span hidden="hidden" class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
    </button>
	<input style="width:90%" type="select" id="grupoCoberturaSearch" name="grupoCoberturaSearch" class="">
	
    <!-- Modal -->
    <div class="modal fade" id="add" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="formCrud">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" >
                            <span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Nueva Cobertura</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">La Cobertura se grabo con exito.</div>
                        <div class="form-group">
                        <div class="row">
                        	<div class="col-md-6">
                            <input type="hidden" class="form-control" id="codigo">
                            <label>Nombre</label>
                            <input type="text" class="form-control required" id="nombre" >
                            <label>Nemotecnico</label>
                            <input type="text" class="form-control " id="nemotecnico" >
                            <label>Nombre Comercial</label>
                            <input type="text" class="form-control required" id="nombreComercial">
                            <label>Tipo Tasa</label>
                            <select type="select" class="form-control required" id="tipoTasa">
                                <option>Seleccione una opcion</option>
                            </select>                            
							<label>Tipo Cobertura</label>
                            <select type="select" class="form-control required" id="tipoCobertura">
                                <option>Seleccione una opcion</option>
                            </select>
                           <!-- <label>Grupo Cobertura</label>
                           <input type="text" class="form-control required" id="grupoCobertura" disabled="disabled">
                             --> 
                            <label>Tasa Valor</label>
                            <input type="number" class="form-control " id="tasaValor" >
                            <div class="checkbox">
                                <label> <input type="checkbox"  id="afectaGrupo">Afecta Grupo</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="afectaValorAsegurado">Afecta Valor Asegurado</label>
                            </div>
                            <label>Sección</label>
                            <input type="number" min="1" step="1" class="form-control " id="seccion" >
                            <label>Orden</label>
                            <input type="number" min="1" step="1" class="form-control " id="orden" >
                            <label>Limite</label>
                            <input type="number"  min="0" class="form-control " id="limite" >
                            </div>
                            <div class="col-md-6">
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esPredeterminada">Predeterminada</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esPrimaFija">Prima Fija</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esTodoRiesgo">Todo Riesgo</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esMasivo">Masivo</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esPrincipal">Principal</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="rebajaValorAsegurado">Rebaja Valor Asegurado</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="generaEndosoRasa">Genera Endoso Rasa</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esIndemnizable">Es Indemnizable</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="esLimiteSuma">Es Límite Suma</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="principalCobertura">Principal Cobertura</label>
                            </div>
                            <div class="checkbox">
                                <label> <input type="checkbox" id="mostrarCotizador">Mostrar Cotizador</label>
                            </div>
							
                             <label>Texto</label>
                           <textarea type="textarea" style="resize:vertical;" class="form-control required" id="texto"> </textarea>
                           
                           <label>Texto Cotizador</label>
                           <textarea type="textarea" style="resize:vertical;" class="form-control required" id="textoCotizador"> </textarea>
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
    	<div class="wrapper1">
  			<div class="div1"></div>
			</div>
			<div class="wrapper2">
			<div class="div2">
        <div  style="overflow: auto;" class="table-responsive">
            <div class="input-group"><span class="input-group-addon">Filtro</span>
                <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
            </div>
            <table class="table table-striped table-bordered table-hover"
                   id="dataTable">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Nemotecnico</th>
                    <th>Tipo Tasa</th>
                    <th>Tipo Cobertura</th>
                    <th>Grupo Cobertura</th>
                    <th>Tasa Valor</th>
                    <th>Afecta Grupo</th>
                    <th>Afecta Valor Asegurado</th>
                    <th>Sección</th>
                    <th>Orden</th>
                    <th>Límite</th>
                    <th>Predeterminada</th>
                    <th>Prima Fija</th>
                    <th>Todo Riesgo</th>
                    <th>Masivo</th>
                    <th>Principal</th>
                    <th>Rebaja Valor Asegurado</th>
                    <th>Genera Endoso Rasa</th>
                    <th>Indemnizable</th>
                    <th>Límite Suma</th>
                    <th>Principal Cobertura</th>
                    <th>Mostrar</th>
                    <th>Nombre Comercial</th>
                    
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
    </div></div>
    </div>
</div>
<!-- Datatable -->
</body>
</html>
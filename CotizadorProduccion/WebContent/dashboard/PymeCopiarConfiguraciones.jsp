<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
<script src="../static/js/Kendo/jszip.min.js"></script>

<title>Copiar Configuraciones</title>
<script>
	var notification;

	function copiar(){
		$.ajax({
			url : '../PymeConfiguracionCoberturaController',
			data : {
				"tipoConsulta" : "copiarProducto",
				"grupoPorProductoId" : $("#productosOrigen").val(),
				"grupoPorProductoDestinoId" : $("#productosDestino").val(),
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}	
				else{
					alert("La copia se realizó con éxito");
					$("#productosOrigen").val("");
					$("#productosDestino").val("");
				}
				
			}
		});
	}

	function obtenerProductosPorGrupoProducto() {
		// Consultar listado de productos dentro de un grupos de productos
		$("#productosOrigen").empty();
		$("#productosDestino").empty();

		$.ajax({
			url : '../GrupoPorProductoController',

			data : {
				"tipoConsulta" : "encontrarTodosPorGrupo",
				"tipoObjeto" : "PYMES",
				"grupoProductoId" : "94"
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}	
				var listadoGrupos = data.listadoGruposPorProducto;
				$("#productosOrigen").append("<option value=''>Seleccione el producto Origen</option>");
				$("#productosDestino").append("<option value=''>Seleccione el producto Destino</option>");
				$.each(
					listadoGrupos,
					function(index) {
						$("#productosOrigen").append("<option value='" + listadoGrupos[index].id + "' >" + listadoGrupos[index].nombre + "</option>");
						$("#productosDestino").append("<option value='" + listadoGrupos[index].id + "' >" + listadoGrupos[index].nombre + "</option>");
					});
			}
		});
	}
	
	$(document).ready(function() {

		activarMenu("PymeCopiarConfiguraciones");
		/*Mensaje de error*/
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
	 	
		$("#loading").hide();
		obtenerProductosPorGrupoProducto();
	});
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
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>
	<span id="notification" style="display:none;"></span>
	<br/>
	<br/>
	<br/>
	<table>
		<tr>
			<td colspan="2">Está opción le permite copiar las configuraciones de un producto hacia otro producto.
			</td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2">Producto donde estan la configuraciones originales:</td>
		</tr>
		<tr>
			<td colspan="2"><select name="productosOrigen" id="productosOrigen"
							validationMessage="Campo requerido!!!"
							required></select></td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2">Producto a donde se copiaran las configuraciones:</td>
		</tr>
		<tr>
			<td colspan="2"><select name="productosDestino" id="productosDestino"
							validationMessage="Campo requerido!!!"
							required></select></td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2"><button id="btnCopiar" class="btn btn-primary"
					onclick="copiar()">Copiar Configuraciones Ahora</button></td>			
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<div id="loading">
			<div class="loading-indicator">
				<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
					id="loading-msg">Procesando, espere por favor...</span>
			</div>
		</div>
	</table>

</body>
</html>
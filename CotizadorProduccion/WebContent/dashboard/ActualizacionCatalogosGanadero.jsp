<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Actualizacion Catálogos Ganadero- CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<style type="text/css">
	
		.clickable
		{
		    cursor: pointer;
		}
		
		.clickable .glyphicon
		{
		    background: rgba(0, 0, 0, 0.15);
		    display: inline-block;
		    padding: 6px 12px;
		    border-radius: 4px
		}
		
		.panel-heading span
		{
		    margin-top: -23px;
		    font-size: 15px;
		    margin-right: -9px;
		}
		a.clickable { color: inherit; }
		a.clickable:hover { text-decoration:none; }
	</style>

	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/jquery-ui/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/css/jquery-ui.theme.css" />
	<link href="../static/css/loading.css" rel="stylesheet">
	<script src="../static/js/util.js"></script>
	<script>

		$(document).ready(function() {
			activarMenu("ActualizarCatalogo");
		});
		
		function actualizarProductosGanadero(){
			$("#actualizar_producto_ganadero").fadeOut("slow");
			$("#actualizando").fadeIn("slow");
			$.ajax({
				url : '../MantenimientoController',
				data : {					
					"tipoConsulta" : "actualizarProductosGanadero"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {
					$("#actualizando").fadeOut("slow");
					$("#actualizar_producto_ganadero").fadeIn("slow");
					
					if(data.success)
						alert("Se han actualizado los datos con exito!");
					else
						alert(data.error);
	
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
	%>

	<div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Catalogos Ganadero- VH</h3>
                </div>
                <div class="panel-body">                   
                
                <table class="table table-condensed">
					<thead>
						<tr>
							<th>Descripción</th>
							<th>Acción</th>
					    </tr>
				    </thead>
	        		<tbody>
		                <tr>
		                    <td>Actualización Productos</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_producto_ganadero" onclick='actualizarProductosGanadero()'>Actualizar</button></td>
		                </tr>
		                <tr id="actualizando" hidden="hidden" >
		                    <td><div ><span id="loading-msg"></span><img  src="../static/images/ajax-loader.gif" /></div></td>
		                    <td>Actualizando los datos, por favor espere...</td>
		                </tr>		               
	        		</tbody>
    			</table>
                </div>
        	</div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Catálogos - Varios</h3>
                </div>
                <div class="panel-body">
                </div>
            </div>
        </div>
    </div>

</body>
</html>
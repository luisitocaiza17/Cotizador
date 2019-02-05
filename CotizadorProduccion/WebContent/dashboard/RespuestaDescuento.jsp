<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Solicitud de Descuento - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	
	
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- Core CSS - Include with every page -->
    <link href="/CotizadorWeb/static/css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="/CotizadorWeb/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- Page-Level Plugin CSS - Blank -->

    <!-- SB Admin CSS - Include with every page -->
    <link href="/CotizadorWeb/static/css/sb-admin/sb-admin.css" rel="stylesheet">
    <link href="/CotizadorWeb/static/css/typeahead.css" rel="stylesheet">
    
        <!-- Core Scripts - Include with every page -->
    <script src="/CotizadorWeb/static/js/jquery.min.js"></script>
    <script src="/CotizadorWeb/static/js/bootstrap.min.js"></script>
    <script src="/CotizadorWeb/static/js/sb-admin/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/CotizadorWeb/static/js/typeahead.bundle.js"></script>
   
    <!-- Page-Level Plugin Scripts - Blank -->

    <!-- SB Admin Scripts - Include with every page -->
    <script src="/CotizadorWeb/static/js/sb-admin/sb-admin.js"></script>

    <!-- Page-Level Demo Scripts - Blank - Use for reference -->
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
	var QueryString;
		$(document).ready(function() {

			
			QueryString = function () {
				// This function is anonymous, is executed immediately and
				// the return value is assigned to QueryString!
				var query_string = {};
				var query = window.location.search.substring(1);
				var vars = query.split("&");
				for (var i = 0; i < vars.length; i++) {
					var pair = vars[i].split("=");
					// If first entry with this name
					if (typeof query_string[pair[0]] === "undefined") {
						query_string[pair[0]] = pair[1];
						// If second entry with this name
					} else if (typeof query_string[pair[0]] === "string") {
						var arr = [query_string[pair[0]], pair[1]];
						query_string[pair[0]] = arr;
						// If third or later entry with this name
					} else {
						query_string[pair[0]].push(pair[1]);
					}
				}
				return query_string;
			}
			();
			
			if(QueryString.sdid!=null){
				$.ajax({
					url : '../SolicitudDescuentoController',
					data : {
						"tipoConsulta" : "cargarPorIdRes",
						"id":QueryString.sdid
					},
					type : 'post',
					datatype : 'json',
					success : function (datos) {
						var solicitudDescuento=datos.solicitudDescuento;
							$("#dataTableContent").append(
									"<tr class='odd gradeX'>"																										
									+ "<td >"
									+ solicitudDescuento.cotizacionId
									+ "</td>"
									+ " <td id='estadoActual'>"
									+ solicitudDescuento.estado
									+ "</td>"
									+ " <td>"
									+ solicitudDescuento.descripcion
									+ "</td>"
									+ " <td>"
									+ solicitudDescuento.descuento
									+ "</td>"
									+ " <td>"
									+ solicitudDescuento.motivo
									+ "</td>"
									+ " <td>"
									+ solicitudDescuento.porcentaje
									+ "</td>"
									+ "<td >"
									+ solicitudDescuento.usuario
									+ "</td>"
									+ "<td >"
									+ solicitudDescuento.sucursal
									+ "</td>"
									+ "</tr>");
							var numeroVehiculos = datos.numeroVehiculos;
							var listadoVehiculosVH = datos.listadoVehiculos;
							for(var i=0;i<numeroVehiculos;i++){
								$("#dataTableContentVehiculos").append(
										"<tr class='odd gradeX'>"																										
										+ "<td >"
										+ listadoVehiculosVH[i].marca_modelo
										+ "</td>"
										+ " <td>"
										+ listadoVehiculosVH[i].ano_fabricacion
										+ "</td>"
										+ " <td>"
										+ listadoVehiculosVH[i].sucursal
										+ "</td>"
										+ " <td>"
										+ listadoVehiculosVH[i].suma_asegurada
										+ "</td>"
										+ " <td>"
										+ listadoVehiculosVH[i].prima_vehiculo.toFixed(2)
										+ "</td>"
										+ " <td>"
										+ listadoVehiculosVH[i].tasa_vehiculo.toFixed(2)
										+ "</td>"
										+ "<td >"
										+ listadoVehiculosVH[i].dispositivo_rastreo
										+ "</td>"										
										+ "</tr>");
							}
								
							if(solicitudDescuento.comentario!=null||solicitudDescuento.estado!='Pendiente'){
								$("#comentario").val(solicitudDescuento.comentario).attr('disabled','disabled');
								$("#autorizar").fadeOut('slow');
								$("#rechazar").fadeOut('slow');
								
							}
					}
				});
			}
			
		});
		
		function actualizarSolicitud(aprobada){
			var comentario=$("#comentario").val();
			$("#comentario").attr('disabled','disabled');
			$("#autorizar").fadeOut('slow');
			$("#rechazar").fadeOut('slow');
			$.ajax({
				url : '../SolicitudDescuentoController',
				data : {
					"tipoConsulta" : "actualizarEstadoRes",
					"id":QueryString.sdid,
					"comentario":comentario,
					"estado":aprobada
				},
				type : 'post',
				datatype : 'json',
				success : function (datos) {
					if(datos.success){
						$("#loading_solicitud_descuento").fadeOut('slow');
						$("#estadoActual").html(datos.estado);
						alert('Se proceso el descuento correctamente');
					}
					else{
						$("#loading_solicitud_descuento").fadeOut('slow');
						alert(datos.error);
						$("#comentario").removeAttr('disabled');
						$("#autorizar").fadeIn('slow');
						$("#rechazar").fadeIn('slow');
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
%>
	<div class="row crud-nav-bar">

	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
			<br>
				<div class="input-group"> <span class="input-group-addon">Filtro</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>
				<br>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Cotizacion</th>
							<th>Estado</th>
							<th>Descripcion</th>
							<th>Descuento</th>
							<th>Motivo</th>
							<th>Porcentaje</th>
							<th>Usuario Solicita</th>
							<th>Sucursal</th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable">
					
						
						
					</tbody>
				</table>
				<br>
				<table class="table table-striped table-bordered table-hover"
					id="dataTable1">
					<thead>
						<tr>
							<th>Marca - Modelo</th>				
							<th>Ano Fabricacion</th>
							<th>Sucursal</th>
							<th>Suma Asegurada</th>
							<th>Prima</th>
							<th>Tasa</th>	
							<th>Dispositivo Rastreo</th>	
						</tr>
					</thead>
					<tbody id="dataTableContentVehiculos" class="searchable">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
		
	<div class="tab-pane active" >
									<br>
									<table class="table table-striped table-bordered table-hover" id="dataTable">
										<thead>
											<tr>
												<th>Comentario</th>												
											</tr>
										</thead>
										<tbody id="descuentos">
											<tr>
												<td><textarea id="comentario" style="width: 90%; background-image: none; background-position: 0% 0%; background-repeat: repeat;" rows="10" class="datosVehiculo valid" aria-invalid="false" ></textarea></td>
											</tr>
											<tr>
												<td>
													<button type="button" class="btn btn-success btn-s" id="autorizar" onclick="actualizarSolicitud(1)">
														<span class="glyphicon glyphicon glyphicon-edit"></span>Autorizar
													</button>
													&nbsp;
													<button type="button" class="btn btn-danger btn-s" id="rechazar" onclick="actualizarSolicitud(0)" >
														<span class="glyphicon glyphicon glyphicon-refresh"></span>Rechazar
													</button>
													<div id="loading_solicitud_descuento" hidden="" style="display: none;">
														<span id="loading-msg">Enviando...</span><img src="../static/images/ajax-loader.gif">
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<!-- F i n   T e r c e r o   t a b -->

									<!-- I n i c i o   C u a r t o   t a b -->
								</div>
	
	
	
	
	
</body>
</html>

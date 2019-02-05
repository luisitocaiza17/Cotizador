<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
    <script src="../static/js/Kendo/kendo.all.min.js"></script>
    <script src="../static/js/Kendo/kendo.web.min.js"></script>
    <script src="../static/js/Kendo/jszip.min.js"></script>

<title>Reglas Agricola</title>
<script>
	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});

	var reglaId = "";
	var tipoCultivoId = "";
	var xcicloId = "";
	var tipoCalculoId = "";
	var provinciaId = "";
	var activo = "";
	var activoGeneral="";
	var cantonId = "";
	var costoProduccion = "";
	var tasa = "";
	var costoMantenimiento = "";
	var aceptabilidadDesde = "";
	var aceptabilidadHasta = "";
	var observaciones = "";
	var estado = "";
	
	var listCopy = "";
	
	var tipoCultivoList = "";
	var tipoCalculoList = "";
	var provinciaList = "";
	var cantonList = "";
	var activoList="";
	var activoGeneralList="";
	$(document).ready(
	function() {
	activarMenu("ReglasAgricola");
	
	;
	$("#CostoProduccion").kendoNumericTextBox({
        format: "c",
        min: 0,
        decimals: 2
    });
	$("#Tasa").kendoNumericTextBox({
		format : '#.00',
		 decimals: 2
    });
	$("#CostoMantenimiento").kendoNumericTextBox({
		format : '#.00',
        min: 0,
        decimals: 2
    });
	$("#AceptabilidadDesde").kendoDatePicker({format:"yyyy-MM-dd"});
	$("#AceptabilidadHasta").kendoDatePicker({format:"yyyy-MM-dd" });
    
    $("#reglaId").kendoNumericTextBox({
        format: "#",
        min: 0
    });
	    
    $("#cultivoId").kendoMultiSelect({
		dataTextField : "Nombre",
		dataValueField : "TipoCultivoId",
		animation : false,
		maxSelectedItems : 1
	});
    
    tipoCultivoList = $("#cultivoId").data(
	"kendoMultiSelect");
    
    $("#calculoId").kendoMultiSelect({
		dataTextField : "Nombre",
		dataValueField : "TipoCalculoId",
		animation : false,
		maxSelectedItems : 1
	});
    
    tipoCalculoList = $("#calculoId").data(
	"kendoMultiSelect");
    
    $("#activoGeneral").kendoMultiSelect({
		dataTextField : "valor",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
    activoGeneralList = $("#activoGeneral").data(
	"kendoMultiSelect");
    
    $("#activo").kendoMultiSelect({
		dataTextField : "valor",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
    activoList = $("#activo").data(
	"kendoMultiSelect");
    
    $("#provinciaId").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		animation : false,
		maxSelectedItems : 1
	});
    
    provinciaList = $("#provinciaId").data(
	"kendoMultiSelect");
    
    $("#cantonId").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "id",
		animation : false,
		maxSelectedItems : 1
	});
    
    cantonList = $("#cantonId").data(
	"kendoMultiSelect");
    
	ConsultarTipoCultivo();
	ConsultarCiclo();
	ConsultarTipoCalculo();
	ConsultarProvincia();	
	$("#activoGeneral").data("kendoMultiSelect").value('1');
	$("#save-recordCopy").click(function(){
		listCopy = "";
		$( ".copy" ).each(function( index ) {
			if($(this).is(':checked')){
				listCopy = listCopy + "," +$(this).parent().children("input:hidden").val();
			}
		});
		
		if(listCopy.length <= 1){
			alert("Seleccione al menos una regla a copiar.");			
			return false;
		}						
		
		tipoCalculoId = $("#TipoCalculo").val();
		provinciaId = $("#Provincia option:selected").val();
		cantonId = $("#Ciudad option:selected").val();
		observaciones = $("#Observaciones").val();
		tipoConsulta = "copiar";
		estado=$("#activo option:selected").val();
		if(provinciaId != "" && cantonId === ""){
			alert("Por favor debe seleccionar un cantón.");
			return false;
		}
		
		enviarDatos("","","",tipoCalculoId,provinciaId,cantonId,"","", "","","",observaciones,estado,listCopy,tipoConsulta);
		
	});
	
	
	$("#save-record").bind({
		click : function() {

		var validator = $("#formCrud").kendoValidator().data("kendoValidator");
		$(".required").css("border", "1px solid #ccc");
			if (validator.validate() === false) {
			$(".required").each(
			function(index) {
			var cadena = $(this).val();
			if (cadena.length <= 0) {
				$(this).css("border","1px solid red");
				alert("Por favor ingrese el campo requerido");
				$(this).focus();
				return false;
			}
			});
			} else {
				var Ciclo = $("#Ciclo").val();
				
				reglaId = $("#ReglaId").val();
				tipoCultivoId = $("#TipoCultivo").val();
				CicloId = $("#Ciclo").val();
				tipoCalculoId = $("#TipoCalculo").val();
				provinciaId = $("#Provincia").val();
				cantonId = $("#Ciudad").val();
				costoProduccion = $("#CostoProduccion").val();
				tasa = $("#Tasa").val();
				costoMantenimiento = $("#CostoMantenimiento").val();
				observaciones = $("#Observaciones").val();
				estado=$("#activo option:selected").val();
				if (reglaId == "")
					tipoConsulta = "crear";
				else
					tipoConsulta = "editar";

				enviarDatos(reglaId,tipoCultivoId,CicloId,tipoCalculoId,
				provinciaId,cantonId,costoProduccion,tasa, costoMantenimiento,
				"","",
				observaciones,estado,"",tipoConsulta);
				}
			}
	});
	
	
	$("#ConsultaBtn").click(function(){
		$("#buscando").show();		
		 		
		CargarGrid();	
		$("#buscando").hide();
	});		
	
	$("#Provincia").change(function(){	
		Provincia = $("#Provincia").val();
		$("select option:selected").each(function(){
			
			$.ajax({
				url : '../CantonController',
				data : {
					"tipoConsulta":"encontrarPorProvincia",
					"provincia" : Provincia							
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					$("#Ciudad").children().remove();
					$("#Ciudad").append("<option value=''>Seleccione una opción</option>");
					var listadoCiudades = data.cantones;
					$.each(listadoCiudades, function (index){								
						$("#Ciudad").append("<option value='"+ listadoCiudades[index].id +"'>"+ listadoCiudades[index].nombre +"</option>");
					});

					
					cantonList.dataSource.filter({});
					cantonList.setDataSource(data.cantones);
				}
			});
		});
	});
	
	$("#provinciaId").change(function(){	
		Provincia = $("#provinciaId option:selected").val();
		$("select option:selected").each(function(){
			
			$.ajax({
				url : '../CantonController',
				data : {
					"tipoConsulta":"encontrarPorProvincia",
					"provincia" : Provincia							
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					$("#Ciudad").children().remove();
					$("#Ciudad").append("<option value=''>Seleccione una opción</option>");
					var listadoCiudades = data.cantones;
					$.each(listadoCiudades, function (index){								
						$("#Ciudad").append("<option value='"+ listadoCiudades[index].id +"'>"+ listadoCiudades[index].nombre +"</option>");
					});
					
					cantonList.dataSource.filter({});
					cantonList.setDataSource(data.cantones);
				}
			});
		});
	});
	
	$("#copyAll").change(function(){		
		$( ".copy" ).each(function( index ) {				
			$(this).prop("checked", $("#copyAll").is(':checked')); 
		});		
	});
	
	$("#LimpiarBtn").click(function(){
		$("#configuracionRegla").children().remove();
		tipoCultivoList.value("");
		tipoCalculoList.value("");
		provinciaList.value("");
		cantonList.value("");
		($("#reglaId").data("kendoNumericTextBox")).value("");
		$("#ReglaId").val("");
	});
	
	
	$("#copyButton").click(function(){
		$("#myModalLabel").hide();
		$("#myModalLabelCopy").show();
		$("#msgNota").show();
		$("#save-record").hide();
		$("#save-recordCopy").show();
		$("#TablaValores").hide();	
		$("#TablaCosto").hide();
		$("#TablaCiclo").hide();
		$("#Ciclo").hide();
		
		$("#Ciclo").prop('disabled', true);		
				
		$("#ReglaId").val("");
		$("#TipoCultivo").prop('disabled', true);
		$("#Ciclo").val("");
		$("#TipoCalculo").val("");
		$("#Provincia").val("");
		$("#Ciudad").val("");
		($("#CostoProduccion").data("kendoNumericTextBox")).value("");
		//$("#CostoProduccion").val("");
		
		//$("#Tasa").val("");
		
		($("#CostoProduccion").data("kendoNumericTextBox")).value("");
		($("#CostoProduccion").data("kendoNumericTextBox")).enable(false);
		($("#CostoMantenimiento").data("kendoNumericTextBox")).value("");
		($("#CostoMantenimiento").data("kendoNumericTextBox")).enable(false);
		($("#Tasa").data("kendoNumericTextBox")).value("");
		($("#Tasa").data("kendoNumericTextBox")).enable(false);
		$("#Observaciones").val("");
		
	});
	
	$("#addButton").click(function(){
		$("#ReglaId").val("");
		$("#myModalLabel").show();
		$("#myModalLabelCopy").hide();
		$("#msgNota").hide();
		$("#TablaCosto").show();
		$("#TablaCiclo").show();
		$("#Ciclo").show();
		$("#TipoCultivo").prop('disabled', false);
		($("#CostoProduccion").data("kendoNumericTextBox")).enable(true);
		($("#CostoMantenimiento").data("kendoNumericTextBox")).enable(true);
		($("#Tasa").data("kendoNumericTextBox")).enable(true);
		$("#Ciclo").prop('disabled', false);		
		
		$("#save-record").show();
		$("#save-recordCopy").hide();
	});	
	
	
	$("#TipoCultivo").change(function(){
		var Tipo = this.options[this.selectedIndex].getAttribute("class");
		//$("select option:selected").each(function(){
			if (Tipo=="2"){
				$("#TablaValores").hide();
					($("#CostoMantenimiento").data("kendoNumericTextBox")).value("");
					//($("#UnidadPorHec").data("kendoNumericTextBox")).value("");
					//($("#PrecioPorUni").data("kendoNumericTextBox")).value("");
			}else {
				$("#TablaValores").show();
					($("#CostoMantenimiento").data("kendoNumericTextBox")).value("");
					//($("#UnidadPorHec").data("kendoNumericTextBox")).value("");
					//($("#PrecioPorUni").data("kendoNumericTextBox")).value("");
			}
			
		//});
	});
	
	$("#cultivoId").change(function(){
		var r = $("#cultivoId option:selected").val();
		if(typeof  r === 'undefined'){
			$("#copyButton").hide();
		}else{			
			//$("#TipoCultivo").children().remove();			
			//ConsultarTipoCultivo ();			
			$("#TipoCultivo").val(r);
			$("#copyButton").show();
			//$("#TipoCultivo").val(r);
		}
	});
});
	
	
	function CargarGrid() {	
		$("#configuracionRegla").children().remove();
		tipoCultivoId =$("#cultivoId option:selected").val();
		tipoCalculoId =$("#calculoId option:selected").val();
		provinciaId =$("#provinciaId option:selected").val();
		cantonId =$("#cantonId option:selected").val();
		var activoGeneralId =$("#activoGeneral option:selected").val();
		reglaId = ($("#reglaId").data("kendoNumericTextBox")).value();
		if(typeof tipoCultivoId === 'undefined' && typeof tipoCalculoId === 'undefined' && typeof provinciaId === 'undefined' && typeof cantonId === 'undefined'){
			if(reglaId === null){
				alert("Seleccione al menos una opción ");
				return false;			
			}			
		}
		$('#dataTable_wrapper').hide();
	
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		$("#grid").kendoGrid({
			toolbar: ["excel"],
	        excel: {
	            fileName: "AgriReglas.xlsx",
	            filterable: true,
	            allPages: true
	        },
			dataSource: {
	            type: "json",
	            serverPaging: true,
	            serverSorting: true,
	            serverFiltering: true,
	            filterable: {
                    mode: "row"
                },
	            pageSize: 20,
	            transport:{
	            	read: {
	            		url: "../AgriReglaController",
	            		data: {
	            			"tipoConsulta":"encontrarPorParametros"	,
	        				"ReglaId":reglaId,
	        				"TipoCultivoId":tipoCultivoId	,
	        				"TipoCalculoId":tipoCalculoId	,
	        				"ProvinciaId":provinciaId	,
	        				"CantonId":cantonId,
	        				"Estado":activoGeneralId
						}
	            	}
	            },
	            schema: {
	            	data: "Data",
	            	total: "Total",
	            }
	        },
	        columns: [
	  				{ field: "reglaId", title: "Regla ID.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "tipoCultivoId", title: "Tipo Cultivo ID", width: "80px",headerAttributes: { style: "white-space: normal"},headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
	  				{ field: "cultivoNombre", title: "Cultivo.", width: "250px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "cicloId", title: "Ciclo ID", width: "80px",headerAttributes: { style: "white-space: normal"},headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
	  				{ field: "cicloNombre", title: "Ciclo", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "tipoCalculoId", title: "Calculo ID",width: "80px",headerAttributes: { style: "white-space: normal"},headerAttributes: { style: "white-space: normal"}, exportar: true, hidden: true},
	  				{ field: "nombreCalculo", title: "Calculo.", width: "250px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "provinciaNombre", title: "Provincia", width: "200px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "cantonNombre", title: "Canton", width: "200px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "costoProduccion", title: "Costo Produccion.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "costoMantenimiento", title: "Costo Mantenimiento", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "tasa", title: "Tasa", width: "100px",headerAttributes: { style: "white-space: normal"}},
	  				{ field: "observaciones", title: "Observacion.", width: "250px",headerAttributes: { style: "white-space: normal"}},
	  				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " Detalle ", width: "110px"},
	  				{ command: { text: "Eliminar", click: EliminarEventoClick}, title: " Eliminar ", width: "110px"}
	  				],
	  				height: 500,
	            selectable: true,
	            resizable: true,
	            pageable: {
	                info: true,
	                numeric: false,
	                previousNext: false
	            },
	            scrollable: {
	                virtual: true
	            },
	        }); 
		
		
		var exportFlag=false;
		$("#grid").data("kendoGrid").bind("excelExport", function (e) {
			var columns = e.sender.columns;
			if (!exportFlag) {
	            jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.showColumn(this.field);
	                }
	            });
	            
	            //e.sender.showColumn("AgenteId");
	            e.preventDefault();
	            exportFlag = true;
	            setTimeout(function () {
	                e.sender.saveAsExcel();
	            }, 1000);
	        } else {
	        	jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.hideColumn(this.field);
	                }
	            });
	            exportFlag = false;
	        }
		});
	}
	
	function actualizar(Regla) {
		$("#myModalLabel").show();
		$("#myModalLabelCopy").hide();
		$("#msgNota").hide();
		$("#TablaCosto").show();
		$("#TablaCiclo").show();
		$("#Ciclo").show();
		$("#TipoCultivo").prop('disabled', false);
		($("#CostoProduccion").data("kendoNumericTextBox")).enable(true);
		($("#CostoMantenimiento").data("kendoNumericTextBox")).enable(true);
		($("#Tasa").data("kendoNumericTextBox")).enable(true);
		$("#Ciclo").prop('disabled', false);		
		var activoGeneralId =$("#activoGeneral option:selected").val();
		$("#save-record").show();
		$("#save-recordCopy").hide();
		
		$.ajax({
			url : '../AgriReglaController',
			data : {
				"ReglaId" : Regla,
				"tipoConsulta" : "obtenerPorId",
				"EstadoConsulta": activoGeneralId
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#ReglaId").val(data.ReglaId);
				$("#TipoCultivo").children().remove();
				$("#Ciclo").children().remove();
				$("#TipoCalculo").children().remove();
				$("#Provincia").children().remove();
				$("#Ciudad").children().remove();
				ConsultarTipoCultivo ();
				ConsultarCiclo();
				ConsultarTipoCalculo();
				ConsultarProvincia();
				$("#TipoCultivo").val(data.TipoCultivoId);
				$("#Ciclo").val(data.CicloId);
				$("#TipoCalculo").val(data.TipoCalculoId);
				$("#activo").data("kendoMultiSelect").value(data.activo);
				$("#Provincia").val(data.ProvinciaId);
				ConsultarCiudad();
				$("#Ciudad").val(data.CantonId);
				($("#CostoProduccion").data("kendoNumericTextBox")).value(data.CostoProduccion);
				($("#Tasa").data("kendoNumericTextBox")).value(data.Tasa);
				($("#CostoMantenimiento").data("kendoNumericTextBox")).value(data.CostoMantenimiento);
				$("#Observaciones").val(data.Observaciones);
				var Tipo = $('#TipoCultivo option:selected').attr('class');
				if (Tipo=="2"){
						$("#TablaValores").hide();
						}
				else {
						$("#TablaValores").show();
						}
			}
		});
		
		
		
		$("#Ciclo").change(function(){	
			cicloId = $("#Ciclo").val();			
		});		
	}
	
	function eliminar(Regla) {
		var r = confirm("Seguro que desea eliminar");
		if (r == true) {
			reglaId = Regla;
			tipoConsulta = "eliminar";
			enviarDatos(reglaId, "", "", "",
					"", "", "", "","", "", "","", "","", tipoConsulta);
		}
		CargarGrid();
		$("#msgPopup").hide();
	}
	
	function fnEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
        $('#add').modal('show');
        actualizar(dataItem.reglaId);
    }
	
	function EliminarEventoClick(e) {
        e.preventDefault();
        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));        
        eliminar(dataItem.reglaId);
    }
	
	function enviarDatos(ReglaId, TipoCultivoId, CicloId, TipoCalculoId,
			ProvinciaId, CantonId, CostoProduccion, Tasa, CostoMantenimiento,
			AceptabilidadDesde, AceptabilidadHasta,
			Observaciones, Estado,Copy, tipoConsulta) {
		$.ajax({
			url : '../AgriReglaController',
			data : {
				"ReglaId" : ReglaId,
				"TipoCultivoId" : TipoCultivoId,
				"CicloId" : CicloId,
				"TipoCalculoId" : TipoCalculoId,
				"ProvinciaId" : ProvinciaId,
				"CantonId" : CantonId,
				"CostoProduccion":CostoProduccion,
				"Tasa" : Tasa,
				"CostoMantenimiento" : CostoMantenimiento,
				"AceptabilidadDesde" : AceptabilidadDesde,
				"AceptabilidadHasta" : AceptabilidadHasta,
				"Observaciones" : Observaciones,
				"Estado" : Estado,
				"Copy":Copy,
				"tipoConsulta" : tipoConsulta
			},
			type : 'POST',
			async : false,
			datatype : 'json',
			success : function(data) {
				$("#msgPopup").show();
			}
		});
	}

		
	function ConsultarTipoCultivo (){
		//Consultar los tipo de cultivos
		$.ajax({
			url : '../AgriTipoCultivoController',
			data : {
				"tipoConsulta" : "encontrarTodos"
			},	
			async: false,
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var listadoTiposCultivo = data.TipoCultivoJSONArray;	 
				$("#TipoCultivo").append("<option value='' class=''>Seleccione una opcion</option>");
				$.each(listadoTiposCultivo, function (index) {
					$("#TipoCultivo").append("<option value='" + listadoTiposCultivo[index].TipoCultivoId + "' class='"+ listadoTiposCultivo[index].Tipo_ +"'>" + listadoTiposCultivo[index].Nombre + "</option>");					
				});
				
				tipoCultivoList.dataSource.filter({});
				tipoCultivoList.setDataSource(data.TipoCultivoJSONArray);	
				
			}
		});
	}
	
	
	function ConsultarTipoCalculo(){
		$.ajax({
			url:'../AgriTipoCalculoController',
			data:{"tipoConsulta":"encontrarTodos"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				var listadoTipoCalculo = data.TipoCalculoJSONArray;
				$("#TipoCalculo").append("<option value =''>Seleccione una opción</option>");
				$.each(listadoTipoCalculo, function(index){
					$("#TipoCalculo").append("<option value='"+ listadoTipoCalculo[index].TipoCalculoId+"'>"+listadoTipoCalculo[index].Nombre+"</option>");
				});
				
				tipoCalculoList.dataSource.filter({});
				tipoCalculoList.setDataSource(data.TipoCalculoJSONArray);
				
			}
		});
	}
	
	function ConsultarCiclo(){
		$.ajax({
			url:'../AgriCicloController',
			data:{"tipoConsulta":"encontrarTodos"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				var listadoCiclo = data.CicloJSONArray;
				$("#Ciclo").append("<option value =''>Seleccione una opción</option>");
				$.each(listadoCiclo, function(index){
					$("#Ciclo").append("<option value='"+ listadoCiclo[index].CicloId+"'>"+listadoCiclo[index].Nombre+"</option>");
				});	
				
				activoList.dataSource.filter({});
				activoList.setDataSource(data.ActivoJSONArray);
				
				activoGeneralList.dataSource.filter({});
				activoGeneralList.setDataSource(data.ActivoJSONArray);
			}
		});
	}
	
	function ConsultarProvincia(){
		$.ajax({
			url:'../ProvinciaController',
			data:{"tipoConsulta":"encontrarTodos"},
			async: false,
			type:'post',
			datatype:'json',
			success: function(data){
				var listadoProvincia = data.listadoProvincia;
				$("#Provincia").append("<option value =''>Seleccione una opción</option>");
				$.each(listadoProvincia, function(index){
					$("#Provincia").append("<option value='"+ listadoProvincia[index].codigo+"'>"+listadoProvincia[index].nombre+"</option>");
				});
				provinciaList.dataSource.filter({});
				provinciaList.setDataSource(data.listadoProvincia);
				
			}
		});
	}
	
	function ConsultarCiudad(){
		Provincia = $("#Provincia").val();
		$.ajax({
			url : '../CantonController',
			data : {
				"tipoConsulta":"encontrarPorProvincia",
				"provincia" : Provincia							
			},
			async: false,
			type : 'POST',
			datatype : 'json',
			success : function(data){
				$("#Ciudad").children().remove();
				$("#Ciudad").append("<option value=''>Seleccione una opción</option>");
				var listadoCiudades = data.cantones;
				$.each(listadoCiudades, function (index){								
					$("#Ciudad").append("<option value='"+ listadoCiudades[index].id +"'>"+ listadoCiudades[index].nombre +"</option>");
				});
			}
		});
	}	
	
</script>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>

	<div class="row crud-nav-bar">
	<div class="well">
			<table class="table" style="width: 100%"> 
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador de Reglas Agricola.</center></td>

					</tr>
					<tr>
						<th style="width: 20%">Busqueda por:</th>
						<th style="width: 40%"> Código:</th>
						<th style="width: 40%"> Activos:</th>
					</tr>
					<tr>
						<th style="width: 20%"></th>
						<th style="width: 40%"><input type="text" name="reglaId" id="reglaId"></input></th>
						<th style="width: 40%"><select id="activoGeneral" data-placeholder="Seleccione una opción..."></select></th>
					</tr>
					<tr>
						<th style="width: 20%">Busqueda por:</th>
						<th style="width: 40%">Tipo Cultivo: <select id="cultivoId" data-placeholder="Seleccione una opción..."></select></th>						
						<th style="width: 40%">Tipo Calculo: <select id="calculoId" data-placeholder="Seleccione una opción..."></select></th>						
					</tr>					
					<tr>
						<th style="width: 20%">Busqueda por:</th>
						<th style="width: 40%">Tipo Provincia: <select id="provinciaId" data-placeholder="Seleccione una opción..."></select></th>	
						<th style="width: 40%">Tipo Canton: <select id="cantonId" data-placeholder="Seleccione una opción..."></select></th>						
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
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>
				</tbody>
			</table>
		</div>
		
	<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add"
			id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>
		<button class="btn btn-primary" data-toggle="modal" data-target="#add"
			id="copyButton" style="display: none;">
			<span class="glyphicon glyphicon-copy"></span> &nbsp; Copiar
		</button>

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
							<h4 class="modal-title" id="myModalLabel">Reglas Agrícola</h4>
							<h4 class="modal-title" id="myModalLabelCopy" style="display: none">Datos copia.</h4>
						</div>
						<div class="modal-body">
						<div class="alert alert-success" id="msgPopup">La regla se grabo con éxito.</div>
						<div class="alert alert-warning" id="msgNota" style="display: none"><strong>Nota.- </strong> Al hacer un cambio se aplicara para todos los elementos seleccionados.</div>
						<input type="hidden" class="form-control" id="ReglaId">
						<div class="panel panel-primary">
							<div class="panel-heading">Datos Generales</div>
							<div class="panel-body">
								<table>
									<tr>
										<td>Tipo de Cultivo:</td>
										<td width="210px">
										<select style="width: 180px" name="TipoCultivo"
											id="TipoCultivo" class="datosRegla"
											validationMessage="Campo requerido!!!" required>
											</select></td>
										<td width="70px">Tipo de Cálculo:</td>
										<td width="190px"><select style="width: 180px" name="TipoCalculo"
											id="TipoCalculo" class="datosRegla"
											validationMessage="Campo requerido!!!" required></select></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>Provincia:</td>
										<td><select style="width: 180px" name="Provincia"
											id="Provincia" class="datosRegla"
											validationMessage="Campo requerido!!!" required>
										</select></td>
										<td>Cantón:</td>
										<td><select style="width: 180px" name="Ciudad"
											id="Ciudad" class="datosRegla"
											validationMessage="Campo requerido!!!" required>
										</select></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>Ciclo:</td>
										<td><select style="width: 180px" name="Ciclo"
											id="Ciclo" class="datosRegla">
										</select></td>
										
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">Datos Regla</div>
							<div class="panel-body">
								<table id="TablaCosto">
									<tr>
										<td width="90px">Costo de Producción:</td>
										<td width="180px">
											<input type="text"
															name="CostoProduccion" id="CostoProduccion"
															class="datosRegla" validationMessage="Campo requerido!!!" required></input>
											</td>
										<td width="120px">Tasa:</td>
										<td><input type="number" name="Tasa" id="Tasa"
											class="datosRegla" validationMessage="Campo requerido!!!" required></td>

									</tr>
									<tr>
										<td width="90px">Costo de Mantenimiento:</td>
										<td width="180px">
											<input type="text"
															name="CostoMantenimiento" id="CostoMantenimiento"
															class="datosRegla" validationMessage="Campo requerido!!!" required></input>
											</td>
										<td width="120px">Activo:</td>
										<td><select style="width: 180px" name="activo"
											id="activo" class="datosRegla">
										</select></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table>
									<tr>

										<td width="100px">Observaciones:</td>
										<td ><textarea cols="20" rows="3" style="width: 430px; "  name="Observaciones"
											id="Observaciones" class="datosRegla"></textarea></td>
									</tr>
								</table>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar Cambios</button>
							<button type="button" class="btn btn-primary" id="save-recordCopy" style="display: none">Guardar Cambios Copia</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	
	<!-- Datatable -->
	<div id="grid"></div>
	</div>
	<!-- Datatable -->
	
</div>
</body>
</html>
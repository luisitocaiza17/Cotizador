

function mostrarTipoIdentificacion() {
	var tipoIdentificacionId = $("#tipo_identificacion_principal").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#datosAdicionalesNatural").show();
		$("#datosAdicionalesJuridica").hide();
		$("#nombre_completo").val("");
	} else {
		$("#datosAdicionalesNatural").hide();
		$("#datosAdicionalesJuridica").show();
		$("#nombres").val("");
		$("#apellidos").val("");
	}
}

function cargarPorIdentificacion(formulario, valor) {
	var identificacion = valor;
	if ((identificacion.length >= 10 && $("#tipo_identificacion_principal").val()!=2)||(identificacion.length >= 5 && ($("#tipo_identificacion_principal").val()==2||$("#tipo_identificacion_principal").val()==""))) {
		$(".loading_identificacion").fadeIn();
		var entidad = "";
		
		$.ajax({
			url : '../EntidadController',
			data : {
				"identificacion" : identificacion,
				"tipoConsulta" : "cargarPorIdentificacionEnsurance"
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				var entidad = data.entidad;
				if (entidad.clienteIdEnsurance != null && entidad.entidadIdEnsurance != "") {
					if(formulario == "datosClienteInicio"){
						$("#codigoEntidadEnsurance").val(entidad.entidadIdEnsurance);
						$("#codigoClienteEnsurance").val(entidad.clienteIdEnsurance);
						$("#nombres").val(entidad.nombre);
						$("#primerApellido").val(entidad.apellido.split(" ")[0]);
						$("#segundoApellido").val(entidad.apellido.split(" ")[1]);
						$("#tipo_identificacion_principal").val(entidad.tipoIdentificacion);
					}
					
					if(formulario == "formasPagoDebitoBancario"){
						$("#bancoTitular").val(entidad.nombre + " " + entidad.apellido);
						$("#bancoTipoIdentificacion").val(entidad.tipoIdentificacion);
					}

					if(formulario == "formasPagoDebitoTarjetas"){
						$("#tarjetaTitular").val(entidad.nombre + " " + entidad.apellido);
						$("#tarjetaTipoIdentificacion").val(entidad.tipoIdentificacion);
					}
				}else{
					$("#nombres").val("");
					$("#apellidos").val("");
				}
				$(".loading_identificacion").fadeOut();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$(".loading_identificacion").fadeOut();
			}
		});			
	}
}

function calcularValorCobertura(valor, numero){
	//$("#"+id).parent().parent().next().children().find(".coberturaUbicacion"+numero).val(valor);
	var valorHurto = parseFloat(valor) * parseFloat(0.10);
	
	$("#hurto"+numero).next().val(valorHurto.toFixed(2));
	
}

function cargarCoberturasPorProducto(codigo){
	alert("Entra a la llamada para cargar las coberturas");
	$.ajax({
		url : '../ProductoController',
		data : {
			"tipoConsulta" : "buscarGrupoCoberturasPorProducto",
			"codigo": codigo
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoGrupoCobertura = data.listadoGrupoCobertura;
			var listadoCobertura = data.listadoCobertura;
			
			tablaCoberturas = "<table>";
			
			$.each(listadoGrupoCobertura, function(index){
				tablaCoberturas = tablaCoberturas + "<tr class='cabeceraTablaCoberturaPrincipalPymes'>"+
								  						"<td id="+ listadoGrupoCobertura[index].codigoGrupoCobertura +" class='coberturasAdicionales'>"+listadoGrupoCobertura[index].nombreGrupoCobertura+"</td>";
														for(j=1; j<=$("#numero_total_ubicaciones").val(); j++)
															tablaCoberturas = tablaCoberturas + "<td align='center' width='100px' ><span><b>Ubicaci&oacute;n " + j + "</b></span>"+"</td>";
				tablaCoberturas = tablaCoberturas +"<td></td></tr>";
				var listaCoberturasAnadidas = "";
				$.each(listadoCobertura, function(i){
					if(listadoGrupoCobertura[index].codigoGrupoCobertura == listadoCobertura[i].codigoGrupoCobertura){
						//if(listaCoberturasAnadidas.indexOf(listadoCobertura[i].codigo + ",") == "-1"){
							listaCoberturasAnadidas = listaCoberturasAnadidas + listadoCobertura[i].codigo + ", ";
							tablaCoberturas = tablaCoberturas + "<tr class='filaIngresoDatosCobertura'>"+
											  						"<td> &nbsp;&nbsp;&nbsp;&nbsp;"+listadoCobertura[i].nombre+"</td>";
																	for(j=1; j<=$("#numero_total_ubicaciones").val(); j++){
																		if(listadoCobertura[i].codigo == '1536598872215'){//El codigo corresponde a Hurto
																			tablaCoberturas = tablaCoberturas + "<td><input type='hidden' id='hurto"+j+"'><input type='text' onkeypress='return justNumbers(event);' id='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.trim().replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' name='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' cobertura='"+listadoCobertura[i].codigo+"' class='coberturaUbicacion"+ j +"' maxlength='10' size='10' style='width:100px;' readonly></td>";
																		}else if(listadoCobertura[i].codigo == '1536598870989'){ //El codigo corresponde a robo y/o asalto
																			var idCobertura = String(listadoGrupoCobertura[index].nombreGrupoCobertura.trim().replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j);
																			tablaCoberturas = tablaCoberturas + "<td>" +
																									"<input type='text' onkeypress='return justNumbers(event);' id='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.trim().replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' name='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' cobertura='"+listadoCobertura[i].codigo+"' class='coberturaUbicacion"+ j +"' maxlength='10' size='10' style='width:100px;' onchange='calcularValorCobertura(this.value,"+ j +")'>" +	
																								"</td>";
																		}else{
																			tablaCoberturas = tablaCoberturas + "<td><input type='text' onkeypress='return justNumbers(event);' id='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.trim().replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' name='"+ listadoGrupoCobertura[index].nombreGrupoCobertura.replace(/\s/g, "_") +"_"+ i +"_ubicacion_"+ j +"' cobertura='"+listadoCobertura[i].codigo+"' class='coberturaUbicacion"+ j +"' maxlength='10' size='10' style='width:100px;'></td>";
																		}
																	}
							tablaCoberturas = tablaCoberturas + "</tr>";
						//}
					}
				});
				tablaCoberturas = tablaCoberturas + "</tr>";
			});
			tablaCoberturas = tablaCoberturas + "</table>";
			
			$("#listadoCoberturasPymes").empty().append(tablaCoberturas);
		}
	});
}
/*
function cargarCoberturasPorGrupoCobertura(){
	$(".tipo_identificacion").each(function(index){
		alert($(this).attr('id'));
	});
	var coberturasPorGrupoCobertura = "";
	$.ajax({
		url : '../ProductoController',
		data : {
			"tipoConsulta" : "buscarCoberturasPorGrupoCobertura",
			"codigo": codigo
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoCobertura = data.listadoCobertura;
			
			alert(listadoCobertura);
			
			$.each(listadoCobertura, function(index){
				alert("El nombre de la cobertura " + listadoCobertura[index].nombre);
				coberturasPorGrupoCobertura = coberturasPorGrupoCobertura + "<tr>"+
								  												"<td> &nbsp;&nbsp;&nbsp;&nbsp;"+listadoCobertura[index].nombre+"</td>";
								  											"</tr>";
			});
		}
	});
	return coberturasPorGrupoCobertura;
}
*/
/*
 * 													$.each(listadoCobertura, function(i){
														tablaCoberturas = tablaCoberturas + "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;" + listadoCobertura[i].nombre + "</td></tr>";
													});
 * */

function cargarSucursales(seleccionada, numero) {
	$.ajax({
		url : '../SucursalController',
		data : {
			"tipoConsulta" : "encontrarSucursalesActivas",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">&nbsp;&nbsp;Seleccione</option>';
			
			for (var i = 0; i < data.sucursales.length; i++) {
				options += '<option value="' + data.sucursales[i].id + '">' + data.sucursales[i].nombre + '</option>';
			}
			$("#sucursales" + numero.toString()).html(options).val(seleccionada).attr('required','required');
			
		}
	});
}

function cargarPuntosVenta(seleccionada) {

	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProducto",
			"producto" : "productoVehiculo",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var sucursales = data.sucursales;
			var options = '';
			options = '<option value="">Seleccione un punto de venta</option>';
			var contador;
			for (var j = 0; j < sucursales.length; j++) {
				options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
				for (var i = 0; i < data.puntosVenta.length; i++) {
					if (data.puntosVenta[i].sucursal == sucursales[j].id) {
						options += '<option value="' + data.puntosVenta[i].id + '">&nbsp;&nbsp;&nbsp;&nbsp;' + data.puntosVenta[i].nombre + '</option>';
					}
				}
			}
			$("#punto_venta").html(options).val(seleccionada);
		}
	});

}

function cargarVigenciasPolizas(seleccionada) {

	$.ajax({
		url : '../VigenciaPolizaController',
		data : {
			"tipoConsulta" : "encontrarTodosActivos",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione una vigencia</option>';
			for (var i = 0; i < data.vigencias_poliza.length; i++) {
				options += '<option value="' + data.vigencias_poliza[i].id + '">' + data.vigencias_poliza[i].nombre + '</option>';
			}
			$("#vigencia").html(options).val(seleccionada);
		}
	});

}

function cargarAgentes(seleccionada) {

	$.ajax({
		url : '../AgenteController',
		data : {
			"tipoConsulta" : "consultarAgentes",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un agente</option>';
			for (var i = 0; i < data.agentes.length; i++) {
				options += '<option value="' + data.agentes[i].id + '">' + data.agentes[i].nombre + '</option>';
			}
			$("#agentes").html(options).val(seleccionada);
		}
	});

}


function cargarTiposIdentificacion(seleccionada,tipo,noChange) {
	$.ajax({
		url : '../TipoIdentificacionController',
		data : {
			"tipoConsulta" : "ObtenerTodos",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un tipo de identificacion </option>';
			for (var i = 0; i < data.tipoIdentificacion.length; i++) {
				options += '<option value="' + data.tipoIdentificacion[i].id + '">' + data.tipoIdentificacion[i].nombre + '</option>';
			}
			if(tipo!='')
				if(!noChange){
					$("#tipo_identificacion_"+tipo).html(options).val(seleccionada).trigger('change');}
				else{
					$("#tipo_identificacion_"+tipo).html(options).val(seleccionada);}
			else
				$(".tipo_identificacion").html(options).val(seleccionada);
		}
	});

}

function cargarFormasPago(seleccionada){
	$("#rowDetallePagos").hide();
	$.ajax({
		url : '../FormaPagoController',
		data : {
			"tipoConsulta" : "encontrarTodos",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoFormaPago = data.listadoFormaPago;
			var options = '';
			$.each(listadoFormaPago, function(index){
				options += '<option value="' + listadoFormaPago[index].codigo + '">' + listadoFormaPago[index].nombre + '</option>';
			});
			$("#cboFpFormaPago").empty().append(options);
			
			if (seleccionada > 0){
				cambioFormaPago(seleccionada, false);
				$("#cboFpFormaPago").val(seleccionada);
			}
			
			for(i=2014; i<=2019; i++)
				$("#tarjetaAnioExp").append('<option value="' + i + '">' + i + '</option>');
		}
	});

	$.ajax({
		url : '../InstitucionFinancieraController',
		data : {
			"tipoConsulta" : "encontrarTodos",
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoInstitucionFinanciera = datos.listadoInstitucionFinanciera;

			$("#banco_forma_pago").empty();
			$("#cboFpBanco").empty();				
			$.each(listadoInstitucionFinanciera, function (index) {		
                if(listadoInstitucionFinanciera[index].tarjeta == '0' && listadoInstitucionFinanciera[index].debito == '1'){
                	$("#bancoId").append("<option value='" + listadoInstitucionFinanciera[index].codigo + "' nemotecnico='"+ listadoInstitucionFinanciera[index].nemotecnico +"'>" + listadoInstitucionFinanciera[index].nombre + "</option>");
                }
                
                if(listadoInstitucionFinanciera[index].tarjeta == '1'  && listadoInstitucionFinanciera[index].debito == '1'){
                	$("#tarjetaId").append("<option value='" + listadoInstitucionFinanciera[index].codigo + "' nemotecnico='"+ listadoInstitucionFinanciera[index].nemotecnico +"'>" + listadoInstitucionFinanciera[index].nombre + "</option>");
                }	                
			});
			
			var fecha = new Date();
			var anio = fecha.getFullYear();
			var anioLimite = parseInt(anio) + 10;
				
			for(i=parseInt(anio); i<=anioLimite; i++){
				$("#cboFpAnio").append("<option value='" + i + "'>" + i + "</option>");
			}				
		}
	});		
}

/*
function cargarTablaCoberturas(seleccionada,numero,escogidas,paquete) {
	$.ajax({
		url : '../CoberturaController',
		data : {
			"tipoConsulta" : "encontrarTodos",
			"producto" : "productoVehiculo",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var tablaBlackCobertura = '<table id="coberturaTablaPaqueteBlack">';
			var tablaBlueCobertura = '<table id="coberturaTablaPaqueteBlue">';
			var tablaGoldCobertura = '<table id="coberturaTablaPaqueteGold">';
			var tablaPlatinumCobertura = '<table id="coberturaTablaPaquetePlatinum">';
			var tablaSinCobertura = '<table id="coberturaTablaSinPaquete">';
			var listaCoberturas=data.listadoCobertura;
			var coberturasPorPaquetes=data.coberturasPorPaquetes;
				
			$("#tabla_deducibles" + numero).hide();
			for(var i=0;i<listaCoberturas.length;i++){
				if(listaCoberturas[i].es_adicional == '2'){
						tablaBlackCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Black_Cobertura_'+numero+' datosCobertura" id="check_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'" />' + listaCoberturas[i].nombre + '</td>' +
						'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td style="padding-left:10px" colspan="5" align="right" class="valor_Black_Cobertura_tabla_'+numero+'" id="valor_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
						
						tablaBlueCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Blue_Cobertura_'+numero+' datosCobertura" id="check_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'" />' + listaCoberturas[i].nombre + '</td>' +
						'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td style="padding-left:10px" colspan="5" align="right" class="valor_Blue_Cobertura_tabla_'+numero+'" id="valor_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
						
						tablaGoldCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Gold_Cobertura_'+numero+' datosCobertura" id="check_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'" />' + listaCoberturas[i].nombre + '</td>' +
						'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td style="padding-left:10px" colspan="5" align="right" class="valor_Gold_Cobertura_tabla_'+numero+'" id="valor_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
						
						tablaPlatinumCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Platinum_Cobertura_'+numero+' datosCobertura" id="check_Platinum_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'" />' + listaCoberturas[i].nombre + '</td>' +
						'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td style="padding-left:10px" colspan="5" align="right" class="valor_Platinum_Cobertura_tabla_'+numero+'" id="valor_Platinum_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
						
						tablaSinCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_'+numero+' datosCobertura" id="check_Sin_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'" />' + listaCoberturas[i].nombre + '</td>' +
						'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_'+numero+'" id="valor_Sin_Cobertura_' + listaCoberturas[i].codigo + '_tabla_'+numero+'"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
				}
			}
			
			tablaBlackCobertura += '</table>';
			tablaBlueCobertura += '</table>';
			tablaGoldCobertura += '</table>';
			tablaPlatinumCobertura += '</table>';
			tablaSinCobertura += '</table>';

			$("#lista_coberturas").html(tablaBlackCobertura + tablaBlueCobertura + tablaGoldCobertura + tablaPlatinumCobertura + tablaSinCobertura);
			//falta escoger la seleccionada

			var coberturasBlack=$(".check_Black_Cobertura_"+numero);
			var coberturasBlue=$(".check_Blue_Cobertura_"+numero);
			var coberturasGold=$(".check_Gold_Cobertura_"+numero);
			var coberturasPlatinum=$(".check_Platinum_Cobertura_"+numero);		
			
			for(var j=0;j<coberturasBlack.length;j++){
				for(var k=0;k<coberturasPorPaquetes.length;k++){
					if(coberturasPorPaquetes[k].paquete_id==1){
						var check=$(coberturasBlack[j]).attr('id').replace('check_Black_Cobertura_','').replace('_tabla_'+numero,'');
						if(coberturasPorPaquetes[k].cobertura_id==check){
							$(coberturasBlack[j]).attr('checked','checked').attr('disabled','disabled');
						}
					}
				}
			}
			
			for(var j=0;j<coberturasBlue.length;j++){
				for(var k=0;k<coberturasPorPaquetes.length;k++){
					if(coberturasPorPaquetes[k].paquete_id==2){
						var check=$(coberturasBlue[j]).attr('id').replace('check_Blue_Cobertura_','').replace('_tabla_'+numero,'');
						if(coberturasPorPaquetes[k].cobertura_id==check){
							$(coberturasBlue[j]).attr('checked','checked').attr('disabled','disabled');
						}
					}
				}
			}
			
			for(var j=0;j<coberturasGold.length;j++){
				for(var k=0;k<coberturasPorPaquetes.length;k++){
					if(coberturasPorPaquetes[k].paquete_id==3){
						var check=$(coberturasGold[j]).attr('id').replace('check_Gold_Cobertura_','').replace('_tabla_'+numero,'');
						if(coberturasPorPaquetes[k].cobertura_id==check){
							$(coberturasGold[j]).attr('checked','checked').attr('disabled','disabled');
						}
					}
				}
			}
			
			for(var j=0;j<coberturasPlatinum.length;j++){
				for(var k=0;k<coberturasPorPaquetes.length;k++){
					if(coberturasPorPaquetes[k].paquete_id==4){
						var check=$(coberturasPlatinum[j]).attr('id').replace('check_Platinum_Cobertura_','').replace('_tabla_'+numero,'');
						if(coberturasPorPaquetes[k].cobertura_id==check){
							$(coberturasPlatinum[j]).attr('checked','checked').attr('disabled','disabled');
						}
					}
				}
			}
			
			var bandera = false;
			if(escogidas != null || paquete != null){
				var aux;
				var ids=[];
				$.each(escogidas,function(index){
					if(ids.indexOf(escogidas[index].coberturaId) == -1){
						ids[index]=escogidas[index].coberturaId;
						if(escogidas[index].nemotecnico == "TORI"){
							$("#coberturaTR_check" + numero).trigger("click").attr('checked','checked');
							$("#porcentaje_suma_asegurada" + numero).val(escogidas[index].sumaAsegurada);
							$("#monto_fijo" + numero).val(escogidas[index].montoFijo);
							$("#valor_siniestro" + numero).val(escogidas[index].valorSinisestro);
							bandera = true;
						}

						if(escogidas[index].nemotecnico == "DATO"){
							$("#coberturaDT_check" + numero).trigger("click").attr('checked','checked');
							$("#porcentaje_suma_aseguradaDT"  + numero).val(escogidas[index].sumaAsegurada);
							$("#coberturaTR_check" + numero).removeAttr('checked');
							bandera = true;
						}

						if(escogidas[index].nemotecnico == "RECI"){
							$("#coberturaRC_check"  + numero).trigger("click").attr('checked','checked');
							$("#coberturaTR_check" + numero).removeAttr('checked');
							bandera = true;
						}
					}
				});

				if(!bandera)
					$("#coberturaTR_check"  + numero).trigger("click").attr('checked','checked');
				
				if(paquete==1)
					aux="check_Black_Cobertura_";
				if(paquete==2)
					aux="check_Blue_Cobertura_";
				if(paquete==3)
					aux="check_Gold_Cobertura_";
				if(paquete==4)
					aux="check_Platinum_Cobertura_";
				if(paquete==null || paquete=='')
					aux="check_Sin_Cobertura_";
				
				var coberturasPorPaquete=$("."+aux+numero);
				
				$.each(coberturasPorPaquete,function(index){
					var chk=coberturasPorPaquete[index];
					if(ids.indexOf($(chk).attr('id').replace(aux,'').replace('_tabla_'+numero,''))!=-1){
						$(chk).attr('checked','checked');
					}
				});
			}
			$(".check_Black_Cobertura_1 .datosCobertura").bind({click: function(){
					editoUbicacion=true;
				}
			});
		}
	});
*/

function cargarMotivosDescuento(seleccionada) {

	$.ajax({
		url : '../MotivoDescuentoController',
		data : {
			"tipoConsulta" : "encontrarTodosActivos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un motivo del descuento </option>';
			for (var i = 0; i < data.listadoMotivoDescuento.length; i++) {
				options += '<option value="' + data.listadoMotivoDescuento[i].codigo + '">' + data.listadoMotivoDescuento[i].nombre + '</option>';
			}
			$("#motivo_descuento").html(options).val(seleccionada);

		}
	});

}

function cargarDescuentos(seleccionada) {

	$.ajax({
		url : '../DescuentoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoDescuento = data.listadoDescuento;
			var options = '<option value="">Seleccione un tipo de descuento descuento </option>';
			$.each(listadoDescuento, function (index) {
				options += "<option value=" + listadoDescuento[index].codigo + ">" + listadoDescuento[index].nombre + "</option>";
			});
			$("#codigo_descuento").html(options).val(seleccionada);
		}
	});

}

             	// Metodo para agregar un ubicacion 
                function guardarUbicacion(numero){     
                	 var numeroVeces = Number(numero);
                	 var modelo  = "";
                	 
                	 console.log("*************************** UBICACION NUMERO " + numeroVeces + " **************************");
                	 
                	 if($("#modelo_"+numeroVeces).select2('val') != "")
         			 	modelo = $('#modelo_'+numeroVeces.toString()).select2('data').text;
         			 
                	 var callePrincipal = $('#calle_principal'+numeroVeces.toString()).val();
         			 var numDireccion = $('#num_direccion'+numeroVeces.toString()).val();
         			 var calleSecundaria = $('#calle_secundaria'+numeroVeces.toString()).val();
         			 var nombrePredio = $('#nombre_predio'+numeroVeces.toString()).val();
         			 var numPredio = $('#num_predio'+numeroVeces.toString()).val();
         			 var sector = $("#sector"+numeroVeces.toString()).val();
         			 var provinciaUbicacion = $("#provinciaUbicacion"+numeroVeces.toString()).val();
         			 var ciudadUbicacion = $("#ciudadUbicacion"+numeroVeces.toString()).val();   			  
         			 var contabilidad = "0";
         			 var constitucion = "0";
         			 
         			 if($("#contabilidad").is(':checked')) contabilidad = "1";
         			 if($("#constituida").is(':checked')) constitucion = "1";

         			 if($("#wizard").valid()){
           	           	$.ajax({
  		              	    	  url: '../CotizacionPymesController',
  		              	    	  data: { 	
	  		              	    		  "tipoConsulta": "agregarUbicacionCotizacion",
	  		              	    		  "ubicacionId":$("#ubicacionId"+numeroVeces.toString()).val(),
	  		              	          	  "codigoUbicacionEnsurance": $("#codigoUbicacionEnsurance"+numeroVeces.toString()).val(),
	  		              	              "cotizacionId": $("#cotizacion_id").text(),
	  		              	              "callePrincipal":  $("#calle_principal"+numeroVeces.toString()).val(),
	  		              	              "calleSecundaria": $("#calle_secundaria"+numeroVeces.toString()).val(),
	  		              	              "nombrePredio": $("#nombre_predio"+numeroVeces.toString()).val(),
	  		              	              "numPredio": $('#num_predio'+numeroVeces.toString()).val(),
	  		              	              "numDireccion":$("#num_direccion"+numeroVeces.toString()).val(),
	  		              	              "sector": $('#sector'+numeroVeces.toString()).val(),
	  		              	              "provinciaUbicacion": $('#provincia_ubicacion'+numeroVeces.toString()).val(),
	  		              	              "ciudadUbicacion": $('#ciudad_ubicacion'+numeroVeces.toString()).val(),
	  		              	              "actividadEconomicaUbicacion": $("#actividad_economica_ubicacion").val(),
	  		              	              "descripcionSiniestro":$("#descripcionSiniestro").val(),
	  		              	              "valorSiniestro":$("#valorSiniestro").val(),
	  		              	      		  "contabilidad": contabilidad,
	  		              	      		  "constituida": constitucion,
	  		              				  "tipo_construccion": $("#tipo_construccion_ubicacion").val(),
	  		              				  "tipo_ocupacion": $("#tipo_ocupacion_ubicacion").val(),
	  		              				  "anioConstruccion":$("#anioConstruccion").val(),
	  		              				  "numPiso":$("#numPiso").val(),
	  		              				  "extintor":$("#extintor").val(),
	  		              				  "detectorHumo":$("#detectorHumo").val(),
	  		              				  "sprinklers":$("#sprinklers").val(),
	  		              				  "alarma":$("#alarma").val(),
	  		              				  "guardiania":$("#guardiania").val(),
	  		              				  "txtOtros":$("#txtOtros").val(),
  		              	              },
  		              	    	  type: 'post',          
  		              	          datatype:'json',
  		              	          success: function(data) {  
  		              	     		$("#ubicacionId"+numeroVeces.toString()).val(data.ubicacionId);     
  		              	     		//$('#extrasVehiculo'+numeroVeces.toString()).show();
  		              	     		//$('#eliminarVehiculo'+numeroVeces.toString()).show();
  		              	     		//ubicacionCotizacion();
  		              	          }
  		              	      	});
                	 		//}               	 		
         			   }
             	}

                // Funcion para validar obtener todos las ubicacion de la cotizacion
                function ubicacionCotizacion(){
                
                var listadoVehiculos = "";
                
            	$.ajax({
      				url : '../ObjetoPymesController',
      				data : {
      					"tipoConsulta": "obtenerObjetos",
      					"cotizacionId": $("#cotizacion_id").text(),
      				},
      				type : 'POST',
      				datatype : 'json',
      				success : function(data) {
      					var totalVehiculos = Number(data.numeroVehiculos);
      					$("#total_suma_asegurada_vh").val(data.valorAsegurado);
      					$("#prima_sin_impuestos_vh").val(data.valorPrima);
      					$("#super_bancos_vh").val(data.valorSuperBancos);
      					if(parseFloat(data.porcentajeDescuento) > 0)
      						$("#filaDescuento").show();
      					$("#porcentaje_descuento_vh").val(data.porcentajeDescuento);
      					$("#seguro_campesino_vh").val(data.valorSeguroCampesino);
      					$("#recargo_seguro_campesino_vh").val(data.valorRecargoCampesino);
      					$("#derecho_emision_vh").val(data.valorDerechosEmision);
      					$("#subtotal_vh").val(data.valorSubTotal);
      					$("#iva_vh").val(data.valorIva);
      					$(".total_vh").val(data.valorTotal);
      					$("#total_vh_origin").val(data.valorTotal);
      					$("#resumenTotalPago").children().first().empty().append("$" + data.valorTotal);
      					listadoVehiculos  += '<thead>'+
          				'<tr>'+
          					'<th class="sorting">Marca - Modelo</th>'+
          					'<th class="sorting">A&ntilde;o de fabricaci&oacute;n</th>'+
          					'<th class="sorting">Dispositivo de rastreo?</th>'+
          					'<th class="sorting">Sucursal QBE</th>'+
          					'<th class="sorting">Suma asegurada $</th>'+
							'<th class="sorting">Prima</th>'+
							'<th class="sorting">Extras</th>'+
          				'</tr>'+
          			'</thead>'+
					'<tbody>';
					
          			   for (var i = 0; i < totalVehiculos; i++) {      			                			          				 
          				 listadoVehiculos  += '<tr class="gradeA odd">'+
						'<td>'+data.ubicacionCotizacion[i].marca_modelo+'</td>'+
						'<td>'+data.ubicacionCotizacion[i].ano_fabricacion+'</td>'+
						'<td>'+data.ubicacionCotizacion[i].dispositivo_rastreo+'</td>'+
						'<td class="center">'+data.ubicacionCotizacion[i].sucursal+'</td>'+
						'<td class="center">'+parseFloat(data.ubicacionCotizacion[i].suma_asegurada).toFixed(2)+'</td>'+
						'<td class="center">'+parseFloat(data.ubicacionCotizacion[i].prima_ubicacion).toFixed(2)+'</td>'+
						'<td class="center"><a href="#" id="'+i+'" class="resumenExtras" vh="'+data.ubicacionCotizacion[i].codigo_ubicacion+'">Ver detalle</a></td>'+
						'</tr>'+
						'<tr class="detalleResumenExtras'+i+'">'+
							'<td colspan="8"></td>'+
						'</tr>';				
          				   
                		}
          			   
          			 listadoVehiculos  += '</tbody>';
          			 $("#tabla_ubicacion_cotizacion").empty().html(listadoVehiculos); 
          			 	
          			 $(".detalleResumenExtras").hide();          			 
           			 $(".resumenExtras").click(function(){
	          				var ubicacionCotizadorId = $(this).attr("vh");
	          				var elem = $(this);
	          				$.each($(".ubicacionCotizador"),function(){
	          					if ($(this).val() == ubicacionCotizadorId){
	          						var copiaTablaExtras = $("#extras_"+$(this).attr('numVehiculo'));
	          						elem.parent().parent().next().children().empty();
	          						copiaTablaExtras.clone().appendTo(elem.parent().parent().next().children());
	          						$(".detalleResumenExtras"+elem.attr("id")+" .eliminar-extra-btn").remove();
	          						elem.parent().parent().next().show("slow");
	          					}
	          				});
	          				
           			 });
           			 
           			 // Mostrar los textos de las coberturas
           			 var listadoCoberturas = '<thead>'+
      				'<tr>'+
      					'<th class="sorting">Tipo de Cobertura</th>'+
      					'<th class="sorting">Detalle de la cobertura</th>'+      				
      				'</tr>'+
      			'</thead>'+
				'<tbody>';
           			 for (var i = 0; i < totalVehiculos; i++) {
           				listadoCoberturas  += '<tr class="gradeA odd" colspan="2"> Veh&iacute;culo : '+data.ubicacionCotizacion[i].marca_modelo+' - '+data.ubicacionCotizacion[i].ano_fabricacion+'</tr>';

           				for (var j = 0; j < data.textosCoberturas.length; j++) {           				           					
	           				listadoCoberturas  += '<tr class="gradeA odd">'+
							'<td>'+data.textosCoberturas[j].tipo_cobertura_nombre+'</td>'+
							'<td>'+data.textosCoberturas[j].texto+'</td></tr>';				
           				}
                	}
           			listadoCoberturas  += '</tbody>';
          			 $("#tabla_textos_coberturas").empty().html(listadoCoberturas); 
           			 
           			 $(".loading-indicator").delay((20000*parseInt($('#numero_ubicacion').val()))).hide();
           			 $("#tabbable").delay(20000).show();
      				}
      			});	
                
                }
                
                // Metodo para crear una cotizacion                    
                function crearCotizacion()
                {
                	$.ajax({
             	    	  url: '../CotizacionPymesController',
             	    	  data: { 
             	             "tipoConsulta": "crear",
             	             "productoId": $("input[name='productoPymes']:checked").val(),
             	             "puntoVentaId": $("#punto_venta").val(),
             	             "vigenciaPoliza": $("#vigencia").val(),
             	             "tipoIdentificacion": $("#tipo_identificacion_principal").val(),
             	           	 "identificacion": $("#identificacion").val(),
             	             "nombres": $("#nombres").val(),
             	             "apellidos": $("#primerApellido").val() + " " + $("#segundoApellido").val(),
             	             "agenteId": $("#agentes").val(),
             	             "codigoEntidadEnsurance": $("#codigoEntidadEnsurance").val(),
             	             "codigoClienteEnsurance": $("#codigoClienteEnsurance").val(),
             	             "cotizacionId": $("#cotizacion_id").text(),
             	             "porcentajeComisionAgente": $("#porc_comision").val()
             	    	  },
             	    	  type: 'post',          
             	          datatype:'json',
             	          success: function(data) {
             	        	 $("#cotizacion_id").text(data.cotizacionId);             	             
             	          }
             	      });
                         
                }
         
              
           // M&eacute;todo para eliminar un ubicacion
              function eliminarUbicacion(numero){
            	  var num = numero;
            	  
        	      $.ajax({
         	    	  url: '../CotizacionPymesController',
         	    	  data: { 
         	             "tipoConsulta": "elminarObjetoDetalle",
         	             "producto": "productoVehiculo",
         	             "ubicacionId":$("#ubicacionId" + num).val(),
         	             "cotizacionId": $("#cotizacion_id").text()
         	              },
         	    	  type: 'post',          
         	          datatype:'json',
         	          success: function(data) {
         	        	 if(data.msgEliminarObjeto != ""){
         	        	      $('#myModal'+num).modal('hide');        	      
         	        	      var ubicacion = $("#numero_ubicacion").val();        	
         	        	      $('#clonar_ubicacion'+num).remove();
         	        	      var restarVehiculo = parseInt(ubicacion)-1;        	      
         	        	      $('#numero_ubicacion').val(restarVehiculo);         	        		 
         	        	 }
         	          }
         	      });
             }

              // Agregar dinamicamente uno o varios ubicacion - mediante el boton agregar ubicacion
              function agregarUbicacion(){
                  var ubicacion_clone = '<div id="clonar_ubicacionnumero" class="row clearfix">'+
											'<div class="col-lg-12 col-md-12 col-xs-12 column">'+
												'<div class="panel panel-primary">'+					  
												'<div class="panel-heading"><b>Datos Direcci&oacute;n ( numero )</b>'+
													'<div class="btn-group pull-right">'+
														'<a id="eliminarubicacionnumero" class="btn btn-default btn-sm glyphicon glyphicon-remove" data-toggle="modal" data-target="#myModalnumero" style="margin-left:5px;margin-right:5px;"><b>Eliminar Ubicaci&oacute;n</b></a>'+
													'</div>'+ 
													'<div class="clearfix"></div>'+
												'</div>'+
												'<div class="panel-body">'+	
								    				'<input type="hidden" id="ubicacionIdnumero" class="ubicacionCotizador" numubicacion="numero" name="ubicacionIdnumero" value="">'+
								    				'<input type="hidden" id="codigoUbicacionEnsurancenumero" name="codigoUbicacionEnsurancenumero">'+
								    	            '<table>'+
								    					'<tr>'+
								    						'<td><label><b>Calle Principal:*</b></label></td>'+
								    						'<td><input type="text" class="callePrincipal datosUbicacion" id="calle_principalnumero" name="calle_principalnumero" required="required"></td>'+
								    					'</tr>'+	
								    					'<tr>'+
								    						'<td><label><b>N&uacute;mero:*</b></label></td>'+
								    						'<td><input type="text" class="numDireccion datosUbicacion parametroCargarEnsurance" id="num_direccionnumero" name="num_direccionnumero" required="required"></td>'+
								    					'</tr>'+
								    					'<tr>'+
															'<td><label><b>Calle Secundaria:*</b></label></td>'+
															'<td><input style="width:90%" type="select" id="calle_secundarianumero" name="calle_secundarianumero" class="calleSecundaria datosUbicacion" required="required"></td>'+
														'</tr>'+						
														'<tr>'+
															'<td><label><b>Nombre del edificio o conjunto:</b></label></td>'+
															'<td><input style="width:90%" type="select" id="nombre_predionumero" name="nombre_predionumero" class="nombrePredio datosUbicacion">'+
														'</tr>'+		
								    					'<tr>'+
									  						'<td><label><b>N&uacute;mero de oficina o piso:</b></label></td>'+
									  						'<td><input type="text" name="num_predionumero" id="num_predionumero" class="numPredio datosUbicacion"></select></td>'+ 
														'</tr>'+	  						
														'<tr>'+
														'<td><label><b>Sector:*</b></label></td>'+
														'<td><input type="text" name="sectornumero" id="sectornumero" required="required" class="sector datosUbicacion"></select></td>'+ 
														'</tr>'+  						
								    					'<tr>'+
									  						'<td><label><b>Provincia:*</b></label></td>'+
									  						'<td><select id="provincia_ubicacionnumero" class="provincia datosUbicacion" required="required" onchange="cargarCiudadesPorProvincia(this.value, -1, numero);"></select></td>'+ 	
									  					'</tr>'+
									  					'<tr>'+
									  						'<td><label><b>Ciudad:*</b></label></td>'+
									  						'<td><select id="ciudad_ubicacionnumero" class="ciudad datosUbicacion" required="required"></select></td>'+
									  					'</tr>'+
									  				'</table>'+
								    				'<br>'+
								    				'<center>'+
								    				'<a id="guardarubicacionnumero" class="btn btn-primary glyphicon glyphicon-plus guardarUbicacion" onclick="guardarUbicacion(numero);" style="display:none;">Guardar Ubicaci&oacute;n</a>'+
								    				'</center>'+
								    			'</div>'+
								    		'</div>'+	    
						                	'<div class="modal" id="myModalnumero" tabindex="-1" role="dialog" aria-hidden="true">'+
						                	  '<div class="modal-dialog">'+
						                	    '<div class="modal-content">'+
						                	      '<div class="modal-header">'+                  	        
						                	        '<h4 class="modal-title">Eliminar Ubicaci&oacute;n ( numero ) de la cotizaci&oacute;n</h4>'+
						                	      '</div>'+
						                	      '<div class="modal-body">'+
						                	    	 '<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
						                	  		 '<a class="btn btn-primary" onclick="eliminarUbicacion(numero);">Eliminar</a>'+                  	    	
						                	      '</div>'+                  	      
						                	    '</div>'+
						                	    '</div>'+
						                	'</div>'+
                  						'</div>';

      		  	
              //se desabilita el campo de km recorridos
              var num_ubicacion = $('#numero_ubicacion').val();
      		  var numero;
      		  
      		  if(num_ubicacion == 0){
      			    ubicacion_clone = ubicacion_clone.replace(/numero/g, '1'); 
      		    	$('#lista_ubicacion').html(ubicacion_clone);
      		    	$("#numero_ubicacion").val("1");
      		    	numero = 1;
      		    	cargarActividades("","ubicacion");
      				cargarProvincias("-1", "ubicacion"+numero);
					cargarTipoConstruccion("", "ubicacion");
					cargarTipoOcupacion("", "ubicacion");
					$("#numero_total_ubicaciones").val(numero);
      		  }else{
      			  var nuevo_num_ubicacion = parseInt(num_ubicacion) +1;
      			  var num_anterior = nuevo_num_ubicacion -1;
      			  var callePrincipal = $('#calle_principal'+num_anterior).val();
      			  var numDireccion = $('#num_direccion'+num_anterior).val();
      			  var calleSecundaria = $('#calle_secundaria'+num_anterior).val();
      			  var sector = $('#sector'+num_anterior).val();
      			  var provinciaUbicacion = $('#provincia_ubicacion'+num_anterior).val();
      			  var ciudadUbicacion = $('#ciudad_ubicacion'+num_anterior).val();

      			  if (callePrincipal =="" || numDireccion =="" || calleSecundaria =="" || sector =="" || provinciaUbicacion =="" || ciudadUbicacion =="" ){
      				 return $("#wizard").valid();
      			  }else{ 
      				      				
      				ubicacion_clone = ubicacion_clone.replace(/numero/g, nuevo_num_ubicacion);     
      			    $('#clonar_ubicacion'+num_anterior).before(ubicacion_clone); 
      			  	$("#ciudad_ubicacion"+numero).attr("disabled","disabled");
     	      	
      			    // agregamos los valores de los combos dinamicamente 
      				if(nuevo_num_ubicacion>1){
      					cargaInicial(nuevo_num_ubicacion);
      					$("#numero_total_ubicaciones").val(nuevo_num_ubicacion);
      				}      			 	      			   
      			  	$("#numero_ubicacion").val(nuevo_num_ubicacion);
      			  	numero = nuevo_num_ubicacion;
      			  	
      				var options = '';  
      	      		options = '<option value="-1">Tiene ('+numero+') ubicaciones en la cotizaci&oacute;n</option>';
      	      		for (var i = 1; i < numero+1; i++) {      			
      	      			options += '<option value="#clonar_ubicacion'+ i +'"> Ubicaci&oacute;n ('+ i +')</option>';
      	      		}      		
      	      		$("#ubicacion_num").html(options); 
      				cargarProvincias("-1", "ubicacion"+numero);
      			  }
      		  }     
    			$(".datosUbicacion ").blur(function(){
      				editoUbicacion=true;
      			});

      			$(".datosUbicacion ").on('select2-blur', function(e) {
      				editoUbicacion=true;
      			});
            }
              
           // Enviar la cotizacion  
              function enviarCotizacion() {

          	      $.ajax({
          	    	  url: '../CotizacionController',
          	    	  data: { 
          	              "tipoConsulta": "enviarCotizacion",    
          	              "producto": "productoVehiculo",
          	               "cotizacionId": $("#cotizacion_id").text()
          	              },
          	    	  type: 'post',          
          	          datatype:'json',
          	          success: function(data) {
          	          }
          	      });
            	}
              
              // M&eacute;todo para habilitar o deshabilitar paquetes
              function habilitarDeshabilitarCheck(valor,event){
            	  var numeroVeces = Number(valor);
            	  var target = event.target || event.srcElement;
            	  
            	 /* $('.paquete'+numeroVeces.toString()).on('change', function() {
            		    $('.paquete'+numeroVeces.toString()).not(this).prop('checked', false);  
            		});*/
            	  $('.paquete'+numeroVeces.toString()).not(target).prop('checked', false);
              }
              
              
              // M&eacute;todo para validar la suma asegurada
              function validarSumaAsegurada(valor){
            	  var numeroVeces = Number(valor);
            	  var sumaAsegurada = Number($('#porcentaje_suma_asegurada'+numeroVeces.toString()).val());
            	  var valorDefectoSumaAsegurada = Number($('#suma_asegurada_valor_tr').val());
            	  if(sumaAsegurada < valorDefectoSumaAsegurada){
            	  	alert('La Suma Asegurada debe ser mayor o igual a: '+valorDefectoSumaAsegurada);
            	  }
              }
              
              // M&eacute;todo para validar % de siniestro
              function validarPorcentajeSiniestro(valor){
            	  var numeroVeces = Number(valor);
            	  var valorSiniestro= Number($('#valor_siniestro'+numeroVeces.toString()).val());
            	  var valorDefectoValorSiniestro = Number($('#siniestro_valor_tr').val());
            	  if(valorSiniestro < valorDefectoValorSiniestro){
            	  	alert('El Porcentaje del siniestro debe ser mayor o igual a: '+valorDefectoValorSiniestro);
            	  }
              }
/* 
              // Metodo para deshabilitar las coberturas
              function habilitarDeshabilitarCobertura(valor,event){
            	  var numeroVeces = Number(valor);
            	  var target = event.target || event.srcElement;
            	  var text= $(target).attr("name").split('_')[0].replace('cobertura','');
            	  var checked = $(target).is(':checked');
            	  
            	  if(text=='TR'&&checked){
            		//  $('#tabla_deducibles'+numeroVeces.toString()).fadeIn();
        			//  $('#tabla_deduciblesDT'+numeroVeces.toString()).fadeOut();
        			  $('#coberturaDT_check'+numeroVeces.toString()).prop('checked', false);  
            		  $('#coberturaRC_check'+numeroVeces.toString()).prop('checked', false);
            	  }
            	  if((text=='RC'||text=='DT')&&checked){
            		  //$('#tabla_deduciblesDT'+numeroVeces.toString()).fadeIn();
        			  //$('#tabla_deducibles'+numeroVeces.toString()).fadeOut();
        			  $('#coberturaTR_check'+numeroVeces.toString()).prop('checked', false);  
            	  }
            	  if($('#coberturaDT_check'+numeroVeces.toString()).is(':checked')){
            		  $('#tabla_deduciblesDT'+numeroVeces.toString()).fadeIn();
            	  }
            	  else{
            		  $('#tabla_deduciblesDT'+numeroVeces.toString()).fadeOut();
            	  }
            	  if($('#coberturaTR_check'+numeroVeces.toString()).is(':checked')){
            		  $('#tabla_deducibles'+numeroVeces.toString()).fadeIn();
            	  }
            	  else{
            		  $('#tabla_deducibles'+numeroVeces.toString()).fadeOut();
            	  }
            	
              }
*/              
 /*             
            // Mostrar Coberturas por Paquetes  
          	function mostrarPaqueteCobertura(numero,paquete){   
         		var numeroVeces = Number(numero);
         		var paqueteValor = paquete.toString();
         		
         		var coberturaTablaPaqueteBlack =  $("#coberturaTablaPaqueteBlack").html();
         		var coberturaTablaPaqueteBlue =  $("#coberturaTablaPaqueteBlue").html();
         		var coberturaTablaPaqueteGold =  $("#coberturaTablaPaqueteGold").html();
         		var coberturaTablaPaquetePlatinum =  $("#coberturaTablaPaquetePlatinum").html();  
         		var coberturaTablaSinPaquete =  $("#coberturaTablaSinPaquete").html();  
         		$("#coberturaTablaPaqueteBlack").empty();
         		$("#coberturaTablaPaqueteBlue").empty();
         		$("#coberturaTablaPaqueteGold").empty();
         		$("#coberturaTablaPaquetePlatinum").empty();
         		//evaldez

         		if(paqueteValor == "black"&&$('#paquete1_tabla_'+numeroVeces.toString()).html().trim()==""){
         		   $('#paquete1_tabla_'+numeroVeces.toString()).empty().html(coberturaTablaPaqueteBlack);
         		  }
         		
         		if(paqueteValor == "blue"&&$('#paquete2_tabla_'+numeroVeces.toString()).html().trim()==""){
         			$('#paquete2_tabla_'+numeroVeces.toString()).empty().html(coberturaTablaPaqueteBlue);
         			}
          	
         		if(paqueteValor == "gold"&&$('#paquete3_tabla_'+numeroVeces.toString()).html().trim()==""){
         			$('#paquete3_tabla_'+numeroVeces.toString()).empty().html(coberturaTablaPaqueteGold);
     				}
         		
         		if(paqueteValor == "platinum"&&$('#paquete4_tabla_'+numeroVeces.toString()).html().trim()==""){
         			$('#paquete4_tabla_'+numeroVeces.toString()).empty().html(coberturaTablaPaquetePlatinum);      
         			}
         		
         		if(paqueteValor == "sin"&&$('#paquete5_tabla_'+numeroVeces.toString()).html().trim()==""){
         			$('#paquete5_tabla_'+numeroVeces.toString()).empty().html(coberturaTablaSinPaquete);      
         			}
 		
            }
  */          
          	function solicitarClick(event,rangoInicial,rangoFinal) {
				//$("#addButton").trigger("click");
				var target = event.target || event.srcElement;
				var id=target.parentElement.children.item().value;
					$("#codigo_descuento").val(id);
					//$("#myPopup"+id).show();
					
					$("#titulo_solicitud_descuento").text('Porcentaje de descuento (del '+rangoInicial+'% al '+rangoFinal+'%): ');
					if($("#porcentaje_solicitud_descuento").val().length<=0||$("#porcentaje_solicitud_descuento").val()<rangoInicial||$("#porcentaje_solicitud_descuento").val()>rangoFinal)	
					$("#porcentaje_solicitud_descuento").val(rangoInicial);
					$("#porcentaje_solicitud_descuento").attr('min',rangoInicial);
					$("#porcentaje_solicitud_descuento").attr('max',rangoFinal);
					
					$("#dialog-descuento").dialog({//});
						  appentTo:target.parentElement,
						  dialogClass: 'no-close noTitleStuff',
						  position:{my: 'center', at: 'center', of: target},
						  closeOnEscape: true,
						  maxWidth:600,
					        maxHeight: 300,
					        width: 600,
					        height: 300,
					        modal: true,
						  open: function(event, ui) { $(".ui-dialog-titlebar-close", ui.dialog || ui).hide();
						  $(".ui-dialog-titlebar").hide();
						  $("#ui-id-1").hide();}
						});
					//$(".ui-dialog-titlebar-close").hide();
					$(".ui-dialog-titlebar-close").outerHeight( false );
		}
        
        function confirmarDescuento(event){
        	var descuentoId=$("#codigo_descuento").val().trim();
        	var porcentaje = Number($("#porcentaje_solicitud_descuento").val().trim());
        	var min = Number($("#porcentaje_solicitud_descuento").attr("min"));
        	var max = Number($("#porcentaje_solicitud_descuento").attr("max"));
        	var motivoId=$("#motivo_descuento").val().trim();
        	var descripcion=$("#descripcion_motivo_descuento").val().trim();
        	var punto_venta=$('#punto_venta').val().trim();
        	var cotizacion=$("#cotizacion_id").text().trim();
        	//var nombre=$("#titulo_solicitud_descuento").val().trim();

        	$(".msgerr").remove();
        	/*$("#codigo_descuento").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#porcentaje_solicitud_descuento").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#motivo_descuento").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#descripcion_motivo_descuento").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#punto_venta").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#cotizacion_id").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	$("#titulo_solicitud_descuento").css({"background": "rgb(255, 255, 255)","border": "1px solid #ccc","color": "#ccc"});
        	*/
        	if(descuentoId.length <= 0){
        		$("#codigo_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}

        	if((porcentaje+'').length <= 0){
        		$("#porcentaje_solicitud_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	if(porcentaje > max){
        		$("#porcentaje_solicitud_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>El valor es superior al máximo</b></span>");
        	}
        	if(porcentaje < min){
        		$("#porcentaje_solicitud_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>El valor es menor al m&iacute;nimo</b></span>");
        	}
        	if(motivoId.length <= 0){
        		$("#motivo_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	
        	if(descripcion.length <= 0){
        		$("#descripcion_motivo_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	
        	if(punto_venta.length <= 0){
        		$("#punto_venta").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	
        	if(cotizacion.length <= 0){
        		$("#cotizacion_id").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	 
        	if(nombre.length <= 0){
        		$("#titulo_solicitud_descuento").css({"background": "rgb(251, 227, 228)","border": "1px solid #fbc2c4","color": "#8a1f11"}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
        	}
        	
        	
        	if(descuentoId.length>0&&(porcentaje+'').length>0&&motivoId.length>0&&descripcion.length>0&&punto_venta.length>0&&descuentoId.length>0&&cotizacion.length>0&&porcentaje<=max&&porcentaje>=min){
        		$("#codigo_descuento").attr("disabled","disabled");
            	$("#porcentaje_solicitud_descuento").attr("disabled","disabled");
            	$("#motivo_descuento").attr("disabled","disabled");
            	$("#descripcion_motivo_descuento").attr("disabled","disabled");
            	$("#punto_venta").attr("disabled","disabled");
            	$("#cotizacion_id").attr("disabled","disabled");
            	$("#titulo_solicitud_descuento").attr("disabled","disabled");
        		agregarSolicitudDescuento(descripcion,porcentaje,descuentoId,punto_venta,cotizacion,motivoId);
        		var target = event.target || event.srcElement;
        		$(target).fadeOut("slow");//attr('disabled','disabled');
        		$("#estado_solicitud_descuento").show();
        	}

        	//$("#dialog-descuento").dialog("close");
        }
        
        function cancelarDescuentoClick(event){
        
        	$("#dialog-descuento").dialog("close");
        	$("#porcentaje_solicitud_descuento").val('');
        	$("#motivo_solicitud_descuento").val('');
        	$("#descripcion_motivo_descuento").val('');
			
        }
        
        function agregarSolicitudDescuento(descripcion,porcentaje,descuento,punto_venta,cotizacion,motivoId){        	
        	$.ajax({
     	    	  url: '../CotizacionController',
     	    	  data: { 
     	    		  //"nombre":nombre,
     	    		  "descripcion":descripcion,
     	    		  "porcentaje":porcentaje,
     	    		  "descuento":descuento,
     	    		  "cotizacion":cotizacion,
     	    		  "punto_venta":punto_venta,
     	    		  "motivoId":motivoId,
     	              "tipoConsulta": "agregarSolicitudDescuento"         	               		
     	              },
     	    	  type: 'post',          
     	          datatype:'json',
     	          success: function(data) { 
     	          }
     	      });        	
        }

//      Controles de formularios
        // Nomenclatura: 
        // - PN: Persona Natural
        // - PJ: Persona Juridica
        
        	function mostrarAdicionales(){
        		
        		//limpiaForm($("#datosAdicionalesNatural"));
        		//limpiaForm($("#datosAdicionalesJuridica"));
        	var opcion = $('#tipo_identificacion_principal').val();
        	
        	if(opcion == 1 || opcion == 2 ||opcion == 3 ){
        		$("#filaNatural").show();
        		$("#filaJuridica").hide();
        		$('#datosAdicionalesNatural').show();
        		$('#datosAdicionalesJuridica').hide();
        		//if(!cargadoDatosUPLA)
        		cargarDatosUPLANatural({"":""});
        	}else if (opcion == 4){
        		$("#filaNatural").hide();
        		$("#filaJuridica").show();
        		$('#datosAdicionalesJuridica').show();
        		$('#datosAdicionalesNatural').hide();
        		//if(!cargadoDatosUPLA)
        		cargarDatosUPLAJuridica({"":""});
        	}else{
        		$('#datosAdicionalesNatural').hide();
        		$('#datosAdicionalesJuridica').hide();
        	}
        	}


//metodo para buscar los productos en funcion del ramo
function cargarProductosPorRamo(nemotecnico, seleccionado){
	$.ajax({
		url : '../ProductoController',
		data : {
			"tipoConsulta" : "buscarPorNombreNemotecnico",
			"nemotecnico" : nemotecnico
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listaProductos = data.listaProductos;
			
			tablaProducto = "<table>";
			
			$.each(listaProductos, function(index){
				if(listaProductos[index].codigo == seleccionado){
					tablaProducto = tablaProducto + "<tr>"+
													"	<td><input type='radio' name='productoPymes' value='"+ listaProductos[index].codigo +"' id='"+ listaProductos[index].codigo +"' checked='checked'></td>"+
													"	<td>"+ listaProductos[index].nombre +"</td>"+
													"</tr>";
				}else{
					tablaProducto = tablaProducto + "<tr>"+
					"	<td><input type='radio' name='productoPymes' value='"+ listaProductos[index].codigo +"' id='"+ listaProductos[index].codigo +"'></td>"+
					"	<td>"+ listaProductos[index].nombre +"</td>"+
					"</tr>";
				}
			});
			tablaProducto = tablaProducto + "<table>";
			$("#productosPymes").append(tablaProducto);
		}
	});
}


//metodo para cargar una cotizacion que ya existe

function cargarPorId(id) {
	var etapa;
	$.ajax({
		url : '../CotizacionPymesController',
		data : {
			"tipoConsulta" : "encontrarPorId",
			"cotizacionId" : id
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {

			var datosCotizacion = data.datosCotizacion;
			if (datosCotizacion.etapa1 != null) {
				$("#punto_venta").removeAttr('required');
				$("#agentes").removeAttr('required');
				$("#porc_comision").removeAttr('required');
				$("#tipo_identificacion_principal").removeAttr('required');
				$("#identificacion").removeAttr('required');
				$("#nombres").removeAttr('required');
				$("#primerApellido").removeAttr('required');
				$("#segundoApellido").removeAttr('required');

				var etapa1 = datosCotizacion.etapa1;

				cargarProductosPorRamo("MIC", etapa1.productoId);				
				
				cargarPuntosVenta(etapa1.puntoVenta);

				cargarVigenciasPolizas(etapa1.vigenciaPoliza);

				cargarAgentes(etapa1.agente);

				$("#porc_comision").val(etapa1.porComisionAgente);

				$("#identificacion").val(etapa1.identificacion);

				$("#nombres").val(etapa1.nombres);

				$("#primerApellido").val(etapa1.apellidos.split(" ")[0]);
				$("#segundoApellido").val(etapa1.apellidos.split(" ")[1]);

				//if (data.datosCotizacion==null||data.datosCotizacion.datosUPLA == null)
				cargarTiposIdentificacion(etapa1.tipoIdentificacion,'principal',true);
				cargarFormasPago("");
				etapa = 1;
			}

			if (datosCotizacion.etapa2 != null) {
				$("a[href='#next']").click();
				var etapa2 = datosCotizacion.etapa2;
				var objetos = etapa2.objetos;
				
				if (objetos != null)
					for (var i = 0; i < objetos.length; i++) {
						var objeto = objetos[i];
						agregarUbicacion();
						//cargarDatosUbicacion(objeto.numero, objeto.id, objeto.callePrincipal, objeto.numeroDireccion, objeto.calleSecundaria, objeto.nombreEdificioConjunto, objeto.numeroOficinaPiso, objeto.sector, objeto.provinciaId, objeto.ciudadId);
						$("#ubicacionId"+ objeto.numero).val(objeto.id).removeAttr('required');					
						$("#calle_principal"+ objeto.numero).val(objeto.callePrincipal).removeAttr('required');
						$("#num_direccion"+ objeto.numero).val(objeto.numeroDireccion).removeAttr('required');
						$("#calle_secundaria"+ objeto.numero).val(objeto.calleSecundaria).removeAttr('required');
						$("#nombre_predio"+ objeto.numero).val(objeto.nombreEdificioConjunto).removeAttr('required');
						$("#num_predio"+ objeto.numero).val(objeto.numeroOficinaPiso).removeAttr('required');
						$("#sector"+ objeto.numero).val(objeto.sector).removeAttr('required');
						$("#provincia_ubicacion"+ objeto.numero).removeAttr('required');
						$("#ciudad_ubicacion"+ objeto.numero).removeAttr('required');
            	        $("#actividad_economica_ubicacion").removeAttr('required');
            	        $("#descripcionSiniestro").val(objeto.descripcion).removeAttr('required');
            	        $("#valorSiniestro").val(objeto.valorSiniestro).removeAttr('required');
            			$("#tipo_construccion_ubicacion").removeAttr('required');
            			$("#tipo_ocupacion_ubicacion").removeAttr('required');
            	        $("#txtOtros").val(objeto.otros).removeAttr('required');

            			if(objeto.contabilidad == "1")
            				$("#contabilidad").attr("checked","checked").removeAttr('required');
            			
            			if(objeto.constituida == "1")
            				$("#constituida").attr("checked","checked").removeAttr('required');
            			
            			if(objeto.anioConstruccion == "0")
            				$("#anioConstruccion").val("").removeAttr('required');
            			else
            				$("#anioConstruccion").val(objeto.anioConstruccion).removeAttr('required');

            			
            			$("#anioConstruccion").val(objeto.anioConstruccion).removeAttr('required');
            			$("#numPiso").val(objeto.numeroPisos).removeAttr('required');
            			if(objeto.extintores)
            				$("#extintor").val("1").trigger("change").removeAttr('required');
            			else
            				$("#extintor").val("0").trigger("change").removeAttr('required');

            			if(objeto.detectorHumo)
            				$("#detectorHumo").val("1").trigger("change").removeAttr('required');
            			else
            				$("#detectorHumo").val("0").trigger("change").removeAttr('required');
            			
            			if(objeto.sprinklers)
            				$("#sprinklers").val("1").trigger("change").removeAttr('required');
            			else
            				$("#sprinklers").val("0").trigger("change").removeAttr('required');
            			
            			if(objeto.alarmaMonitoreada)
            				$("#alarma").val("1").trigger("change").removeAttr('required');
            			else
            				$("#alarma").val("0").trigger("change").removeAttr('required');
            			
            			if(objeto.guardiana)
            				$("#guardiania").val("1").trigger("change").removeAttr('required');
            			else
            				$("#guardiania").val("0").trigger("change").removeAttr('required');
            			
						
						cargarProvincias(objeto.provinciaId, "ubicacion" + (i+1));
						cargarCiudadesPorProvincia(objeto.provinciaId, objeto.ciudadId, (i+1));
						cargarTipoConstruccion(objeto.tipoConstruccionId, "ubicacion");
						cargarTipoOcupacion(objeto.tipoOcupacionId, "ubicacion");
						cargarActividades(objeto.actividadEconomicaId, "ubicacion");
					}
				
				$("a[href='#next']").click();
				etapa = 2;
				
				//agregar atributos requeridos de la primera pantalla
				$("#calle_principal").attr('required','required');
				$("#num_direccion").attr('required','required');
				$("#calle_secundaria").attr('required','required');
				$("#nombre_predio").attr('required','required');
				$("#num_predio").attr('required','required');
				$("#sector").attr('required','required');
				$("#provincia_ubicacion").attr('required','required');
				$("#cuidad_ubicacion").attr('required','required');
			}else{
				agregarUbicacion();
//patoargu				cargarDatosUbicacion(1, "", "", "", "", "", "", "", "", "", "", "","","","","");
//patoargu				cargarTablaCoberturas("", 1, "", "");
			}

			if (datosCotizacion.etapa3 != null) {
				var etapa3 = datosCotizacion.etapa3;	
	     		$(".loading-indicator").delay((20000*parseInt($('#numero_ubicacion').val()))).hide();
	       		$("#tabbable").delay(20000).show();
			}
			cargarCoberturasPorProducto(etapa1.productoId);
			
			cargarSolicitudInspeccionPorId(id);
			
			
				var tipoIdentificacionId = data.datosCotizacion.etapa1.tipoIdentificacion;
				if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
					//natural
					$("#datosAdicionalesNatural").show();
					$("#datosAdicionalesJuridica").hide();
					if (data.datosCotizacion==null||data.datosCotizacion.datosUPLA == null||data.datosCotizacion.datosUPLA.datosUPLA==null){
						cargarDatosUPLANatural({"" : ""});}
					else{
						cargarDatosUPLANatural(data.datosCotizacion.datosUPLA.datosUPLA);}
				} else {
					//juridica
					$("#datosAdicionalesNatural").hide();
					$("#datosAdicionalesJuridica").show();
					if (data.datosCotizacion==null||data.datosCotizacion.datosUPLA == null||data.datosCotizacion.datosUPLA.datosUPLA==null)
						cargarDatosUPLAJuridica({"" : ""});
					else
						cargarDatosUPLAJuridica(data.datosCotizacion.datosUPLA.datosUPLA);
			}		
				$("a[href='#next']").click();

		}
	});

	return etapa;
}

//cargar nuevas cotizaciones

function cargaInicial(valor){
	var numeroVeces = Number(valor);
	
	// Consultar las sucursales  
	cargarSucursales("",numeroVeces);
		
	 if(numeroVeces == 1){

	  //Cargar productos pymes
	  //El parametro que pasa es el nombrenemotecnico del ramo
		 cargarProductosPorRamo("MIC");

	  // Consultar Puntos Venta x Producto 
		 cargarPuntosVenta("");
		 
      // Consultar las vigencia de las polizas
		 cargarVigenciasPolizas("");
	      
	  // Consultar los agentes  
	   	 cargarAgentes("");
	   	 
	  // Consultar los tipos de identificacion  
 	     cargarTiposIdentificacion("","");

 	  // Consultar los datos de las inspeccion
 	     cargarOrigenInspeccion();
 	     
 	  // Consultar las formas de pago
 	     cargarFormasPago("", true);
 	  
 	  // Consultar las coberturas del producto ubicacion
//patoargu 	     cargarTablaCoberturas("",numeroVeces,"",""); 	
 	      
 	  // Consultar los Motivos de Descuento
 	      cargarMotivosDescuento("");
 	      
 	  // Cargar descuentos
 	      cargarDescuentos("");
 	      
 	     $(".ui-dialog-titlebar").attr("style", $(".panel-heading").attr("style"))
 	    .addClass($(".panel-heading").attr("class"));
	 }
	
}


function cambiaDescuento() {

	var aux = $("#codigo_descuento option:selected").text();
	aux = aux.split('(')[1].replace(')', '').replace('%', '');
	var min = Number(aux.split('al')[0].trim());
	var max = Number(aux.split(' al ')[1].trim());

	$("#porcentaje_solicitud_descuento").val(min).attr('min', min).attr('max', max);

}


function limpiaForm(miForm) {
	// recorremos todos los campos que tiene el formulario
	$(':input', miForm).each(function() {
	var type = this.type;
	var tag = this.tagName.toLowerCase();
	//limpiamos los valores de los camposâ¦
	if (type == 'text' || type == 'password' || tag == 'textarea')
	this.value = "";
	// excepto de los checkboxes y radios, le quitamos el checked
	// pero su valor no debe ser cambiado
	else if (type == 'checkbox' || type == 'radio')
	this.checked = false;
	// los selects le ponemos el indice a -
	else if (tag == 'select')
	this.selectedIndex = -1;
	});
}


	function justNumbers(e)
	{
		var keynum = window.event ? window.event.keyCode : e.which;
		
		if ((keynum == 8) || (keynum == 46))
			return true;
		return /\d/.test(String.fromCharCode(keynum));
	}

	function digitoVerificadorTarjeta(digit){
	    if(digit > 9){
	        var tmp = digit.toString();
	        var d1 = parseInt(tmp.charAt(0));
	        var d2 = parseInt(tmp.charAt(1));
	        return (d1 + d2); 
	    } else {
	        return digit;
	    }
	}	
	
	function comprobarTarjetaValida(value){
		$("#tarjetaNumero").next().next().empty();

		if (/[^0-9-\s]+/.test(value)) return false;
	 
		// The Luhn Algorithm. It's so pretty.
		var nCheck = 0, nDigit = 0, bEven = false;
		value = value.replace(/\D/g, "");
	 
		for (var n = value.length - 1; n >= 0; n--) {
			var cDigit = value.charAt(n),
				  nDigit = parseInt(cDigit, 10);
	 
			if (bEven) {
				if ((nDigit *= 2) > 9) nDigit -= 9;
			}
	 
			nCheck += nDigit;
			bEven = !bEven;
		}

		if ((nCheck % 10) == 0){
			$("#tarjetaNumero").next().next().attr("style","color:green;").empty().append("<b>Tarjeta Verificada</b>");
		}
    	else{
    		$("#tarjetaNumero").next().next().attr("style","color:#8a1f11;").empty().append("<b>Tarjeta Incorrecta</b>");    		
    	}
	}

	function validarEmisorTarjeta(e, num)
	{
		$("#tarjetaNumero").parent().children().last().empty();
		if (e.keyCode >= 48 && e.keyCode <= 57) {
			    var charCount = num.length;
			    /* VALIDACION DE TIPO */
			    if(charCount == 1) {   
			        if(num == "4"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#visaIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			            $("#visaIcon").removeAttr("style");
			        }
			    }
			    if(charCount == 2){
			        if(num == "34" || num == "37"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#amexIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#amexIcon").removeAttr("style");
			        } else if( num == "51" || num == "55" || num == "53" || num == "50"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#mastercardIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#mastercardIcon").removeAttr("style");
			        } else if( num == "65" ){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#discoverIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#discoverIcon").removeAttr("style");
			        } else if( num == "36" || num == "38" || num == "39" ){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#dinersIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#dinersIcon").removeAttr("style");
			        }
			    }
			    if(charCount == 3){
			        if(num == "644" || num == "649"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#discoverIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#discoverIcon").removeAttr("style");
			        } else if( num == "300" || num == "305" || num == "309" ){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#dinersIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#dinersIcon").removeAttr("style");
			        }
			    }
			    if(charCount == 4){
			        if(num == "6011"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#discoverIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#discoverIcon").removeAttr("style");
			        }
			    }
			    if(charCount == 6){
			        if(num == "622126" || num == "622925"){
			        	$(".tarjetasIconos").each(function(){ $(this).attr("style","opacity: 0.2; filter: alpha(opacity=20);")});
			        	$("#tarjetaId [nemotecnico='"+ $("#discoverIcon").attr('nemotecnico') +"']").attr("selected", "selected");
			        	$("#discoverIcon").removeAttr("style");
			        }
			    }
			    /* !VALIDACION DE TIPO */
			return true;
		}else{
	        event.returnValue = false;
		}
	}	
//
function cambioFormaPago(valor, limpiar){
	$("#tarjetaNumero").next().next().hide();
	$("#msgPopupPagoTarjeta").hide();
	var formaPago = valor;

	if(limpiar){
		limpiaForm(forma_debitos);
		limpiaForm(forma_tarjeta);
		limpiaForm(forma_cuotas);
	}
	
	if(formaPago=='1'){
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$("#save-pagoContado").show();
	}
	
	if(formaPago=='2'){ /*Corresponde a Debitos Bancos*/
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeIn("slow");		
		
	}
	
	if(formaPago=='3'){ /*Corresponde a Debitos Tarjetas*/
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_tarjeta').fadeIn("slow");
	}
	
	if(formaPago=='4'){ /*Corresponde a Cuotas*/
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_cuotas').fadeIn("slow");
	}
}

function cambioPlazoPago(numCuotas){
	var valorTotal = parseFloat($("#total_vh_origin").val()); 
	$("#msgPopupPagoTarjeta").empty().delay( 800 );
	if(numCuotas == 12){
		valorTotal = valorTotal * parseFloat(1.0842);
		cuotas = valorTotal / numCuotas;
		$("#msgPopupPagoTarjeta").empty().append("El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2) + " con cuotas de $" + parseFloat(cuotas).toFixed(2)).show();
		$(".total_vh").val(parseFloat(valorTotal).toFixed(2));
	}else if (numCuotas < 12){
		cuotas = valorTotal / numCuotas;
		$("#msgPopupPagoTarjeta").empty().append("El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2) + " con cuotas de $" + parseFloat(cuotas).toFixed(2)).show();
		$(".total_vh").val(parseFloat(valorTotal).toFixed(2));
	}
	$("#resumenTotalPago").children().first().empty().append("$" + parseFloat(valorTotal).toFixed(2));
}

function calculaDiasPago(fecha){
	arrFecha = fecha.split("/");
    var newDay = parseInt(arrFecha[0]);
    var newMonth = parseInt(arrFecha[1]);
    var newYear = parseInt(arrFecha[2]);
    if (newMonth == 12){
    	newMonth = 1;
    	newYear += 1;
    }else{
    	newMonth += 1;
    }					        
    
    switch(newMonth){
       case 1: case 3: case 5: case 7: case 8: case 10: case 12:
           if (newDay == 30) 
        	   newDay = 31;
           break;
       case 4: case 6: case 9: case 11:
           if (newDay == 31) 
        	   newDay = 30;
           break;
       case 2:
           if(newDay > 28){
            if (newYear % 4 == 0){ 
                if((newYear % 100 != 0) || (newYear % 400 == 0)){
                	newDay = 29;
                }
                else{
                	newDay = 28;
                }
            }else{
            	newDay = 28;
                } 
           }
           break;
    }
    
    if(newMonth < 10){
    	newMonth = '0' + newMonth;
    }

    if(newDay < 10){
    	newDay = '0' + newDay;
    }
    
    var fechaNueva = newDay+'/'+newMonth+'/'+newYear; 
    return 	fechaNueva;
}

function verificarValorCuotaInicial(valor){
	$("#cboFpPlazo").val("0");
	$("#rowDetallePagos").hide();
	if(valor < 50){
		alert("Las cuotas a pagar no pueden ser menor a 50 dolares.");
		$("#txtcuotaInicial").val($("#total_vh").val());
		$("#cboFpPlazo").val("0");
	}
}

function calculaCuotas(numCuotas){
	if(parseFloat($("#txtcuotaInicial").val()) > parseFloat(50)){
		var valorDiferencia = parseFloat($("#total_vh").val()) - parseFloat($("#txtcuotaInicial").val());
		var valorCuotas = parseFloat(valorDiferencia).toFixed(2) / parseInt(numCuotas);		
		var fechaCuotaInicial = $("#fechaInicialPagos").val();
		
		if(valorCuotas < 50){
			numCuotas =  Math.floor(valorDiferencia / 50);
			valorCuotas = parseFloat(valorDiferencia).toFixed(2) / parseInt(numCuotas);	
			$("#cboFpPlazo").val(numCuotas).trigger("change");
			alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en maximo " + numCuotas + " cuotas");
		}

		$("#detallePagoCuotas").empty().append("<tr>" +
				"<td align='center'><b>1</b></td>"+
				"<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='Cuota Inicial' disabled='disabled'></td>" +
				"<td align='center'>"+parseFloat($("#txtcuotaInicial").val()).toFixed(2)+"</td>" +
				"<td align='center'>"+ fechaCuotaInicial +"</td>" +
		   "</tr>");

		var fechaCuota = calculaDiasPago(fechaCuotaInicial);
		var limiteCuotas = parseInt(numCuotas)+1;
		
		for(var i=1; i<limiteCuotas; i++){
		$("#detallePagoCuotas").append("<tr>" +
						"<td align='center'><b>"+(i+1)+"</b></td>" +
						"<td align='center'><input type='text' onkeypress='return justNumbers(event);' class='detalleChequesPagos' size='12' style='margin: 5px; padding: 5px;'></td>" +
						"<td align='center'>"+parseFloat(valorCuotas).toFixed(2)+"</td>" +
						"<td align='center'>"+fechaCuota+"</td>" +
				   "</tr>");
		fechaCuotaSiguiente = fechaCuota;
		fechaCuota = calculaDiasPago(fechaCuotaSiguiente);				
		}
		$("#rowDetallePagos").show();	
	}else{
		alert("Su cuota inicial debes ser minimo de $50.");
	}
}

function cargarDatosUPLANatural(datosNatural) {

	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';

	var optionsZona = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	optionsZona += '<option value="U">&nbsp;&nbsp;Urbana </option>';
	optionsZona += '<option value="R">&nbsp;&nbsp;Rural </option>';

	var tipoActividad = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	tipoActividad += '<option value="Privado">Empleado Privado</option>';
	tipoActividad += '<option value="Publico">Empleado Público</option>';
	tipoActividad += '<option value="Independiente">Independiente</option>';
	tipoActividad += '<option value="Jubilado">Jubilado</option>';

	var genero = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	genero += '<option value="M">Masculino</option>';
	genero += '<option value="F">Femenino</option>';
	
	if (datosNatural.lugarNacimiento != null)
		$("#lugar_nacimiento_natural").val(datosNatural.lugarNacimiento);
	else
		$("#lugar_nacimiento_natural").val("");

	if (datosNatural.fechaNacimiento != null){
		var dia=datosNatural.fechaNacimiento.date<10?'0'+datosNatural.fechaNacimiento.date:datosNatural.fechaNacimiento.date;
		var mes=datosNatural.fechaNacimiento.month<10?"0"+(datosNatural.fechaNacimiento.month+1):(datosNatural.fechaNacimiento.month+1);
		var anio=(1900+datosNatural.fechaNacimiento.year);
		var aux=''+anio+'-'+mes+'-'+dia;
		
		$("#fecha_nacimiento_natural").val(aux);}
	else
		$("#fecha_nacimiento_natural").val("");

	if (datosNatural.zonaDireccionCliente != null)
		$("#zona_direccion_natural").html(optionsZona).val(datosNatural.zonaDireccionCliente).attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_natural").html(optionsZona).val("").attr('required', 'required');

	if (datosNatural.provinciaDireccionCliente != null)
		cargarProvincias(datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
	else
		cargarProvincias("", "direccion_cliente_natural");

	if (datosNatural.zonaDireccionCliente == "R") {
		if (datosNatural.cantonDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones(datosNatural.cantonDireccionCliente,datosNatural.provinciaDireccionCliente,"direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones("", datosNatural.provinciaDireccionCliente,"direccion_cliente_natural");
		}
		
		if (datosNatural.parroquiaDireccionCliente != null) {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias(datosNatural.parroquiaDireccionCliente,datosNatural.cantonDireccionCliente,"direccion_cliente_natural");
		} else {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias("",datosNatural.cantonDireccionCliente,"direccion_cliente_natural");
		}
		
	} else {
		if (datosNatural.ciudadDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCiudades(datosNatural.ciudadDireccionCliente, datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCiudades("", datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		}
	}
	
	

	if (datosNatural.callePrincipalCliente != null)
		$("#calle_principal_direccion_natural").val(datosNatural.callePrincipalCliente);
	else
		$("#calle_principal_direccion_natural").val("");

	if (datosNatural.numeroDireccionCliente != null)
		$("#numero_direccion_natural").val(datosNatural.numeroDireccionCliente);
	else
		$("#numero_direccion_natural").val("");

	if (datosNatural.calleSecundariaCliente != null)
		$("#calle_secundaria_direccion_natural").val(datosNatural.calleSecundariaCliente);
	else
		$("#calle_secundaria_direccion_natural").val("");

	if (datosNatural.referenciaDireccionCliente != null)
		$("#referencia_direccion_natural").val(datosNatural.referenciaDireccionCliente);
	else
		$("#referencia_direccion_natural").val("");

	if (datosNatural.telefonoCliente != null)
		$("#telefono_cliente_natural").val(datosNatural.telefonoCliente);
	else
		$("#telefono_cliente_natural").val("");

	if (datosNatural.celularCliente != null)
		$("#celular_cliente_natural").val(datosNatural.celularCliente);
	else
		$("#celular_cliente_natural").val("");
	
	if (datosNatural.generoCliente != null)
		$("#genero_cliente_natural").html(genero).val(datosNatural.generoCliente);
	else
		$("#genero_cliente_natural").html(genero).val("");
	
	if (datosNatural.mail != null)
		$("#mail_cliente_natural").val(datosNatural.mail);
	else
		$("#mail_cliente_natural").val("");
	
	//actividad persona natural
	
	if (datosNatural.actividadCliente != null)
		cargarActividades(datosNatural.actividadCliente,"cliente_natural");
	else
		cargarActividades("","cliente_natural");

	if (datosNatural.tipoActividadCliente != null)
		$("#tipo_actividad_cliente_natural").html(tipoActividad).val(datosNatural.tipoActividadCliente);
	else
		$("#tipo_actividad_cliente_natural").html(tipoActividad).val("");

	if (datosNatural.cargoOcupaCliente != null)
		$("#cargo_ocupa_cliente_natural").val(datosNatural.cargoOcupaCliente);
	else
		$("#cargo_ocupa_cliente_natural").val("");

	if (datosNatural.tipoRamoContrata != null)
		cargarRamos(datosNatural.tipoRamoContrata,"contrata_cliente_natural");
	else
		cargarRamos("","contrata_cliente_natural");

	if (datosNatural.expuestoCliente != null)
		$("#expuesto_cliente_natural").html(options).val(datosNatural.expuesto?1:0);
	else
		$("#expuesto_cliente_natural").html(options).val("");

	if (datosNatural.cargoExpuestoCliente != null)
		$("#cargo_expuesto_cliente_natural").val(datosNatural.cargoExpuesta);
	else
		$("#cargo_expuesto_cliente_natural").val("");

	if (datosNatural.expuestoFamiliar != null)
		$("#expuesto_familiar_natural").html(options).val(datosNatural.expuestoFamiliar?1:0);
	else
		$("#expuesto_familiar_natural").html(options).val("");

	if (datosNatural.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_natural").val(datosNatural.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_natural").val("");

	if (datosNatural.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_natural").val(datosNatural.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_natural").val("");

	if (datosNatural.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_natural").val(datosNatural.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_natural").val("");

	if (datosNatural.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_natural").val(datosNatural.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_natural").val("");
	
	if (datosNatural.nombreConyuge != null)
		$("#nombre_conyuge_natural").val(datosNatural.nombreConyuge);
	else
		$("#nombre_conyuge_natural").val("");

	if (datosNatural.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionConyuge, "conyuge_natural");
	else
		cargarTiposIdentificacion("", "conyuge_natural");

	if (datosNatural.identificacionConyuge != null)
		$("#identificacion_conyuge_natural").val(datosNatural.identificacionConyuge);
	else
		$("#identificacion_conyuge_natural").val("");

	if (datosNatural.salarioMensual != null)
		$("#salario_mensual_natural").val(datosNatural.salarioMensual);
	else
		$("#salario_mensual_natural").val("");
	
	if (datosNatural.activos != null)
		$("#activos_natural").val(datosNatural.activos);
	else
		$("#activos_natural").val("");
	
	if (datosNatural.otrosIngresos != null)
		$("#otros_ingresos_natural").val(datosNatural.otrosIngresos);
	else
		$("#otros_ingresos_natural").val("");
	
	if (datosNatural.pasivos != null)
		$("#pasivos_natural").val(datosNatural.pasivos);
	else
		$("#pasivos_natural").val("");
	
	if (datosNatural.egresos != null)
		$("#egresos_natural").val(datosNatural.egresos);
	else
		$("#egresos_natural").val("");
	
	if (datosNatural.patrimonio != null)
		$("#patrimonio_natural").val(datosNatural.patrimonio);
	else
		$("#patrimonio_natural").val("");
	
	if (datosNatural.ingresoEgreso != null)
		$("#ingresos_egresos_natural").val(datosNatural.ingresoEgreso);
	else
		$("#ingresos_egresos_natural").val("");
	
	if (datosNatural.esAsegurado != null)
		$("#es_asegurado_natural").html(options).val(datosNatural.esAsegurado?1:0);
	else
		$("#es_asegurado_natural").html(options).val("");
	
	if (datosNatural.esBeneficiario != null)
		$("#es_beneficiario_natural").html(options).val(datosNatural.esBeneficiario?1:0);
	else
		$("#es_beneficiario_natural").html(options).val("");

	if (datosNatural.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionAsegurado, "asegurado_natural");
	else
		cargarTiposIdentificacion("", "asegurado_natural");
	
	if (datosNatural.identificacionAsegurado != null)
		$("#identificacion_asegurado_natural").val(datosNatural.identificacionAsegurado);
	else
		$("#identificacion_asegurado_natural").val("");
	
	if (datosNatural.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_natural").val(datosNatural.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_natural").val("");
	
	if (datosNatural.direccionAsegurado != null)
		$("#direccion_asegurado_natural").val(datosNatural.direccionAsegurado);
	else
		$("#direccion_asegurado_natural").val("");
	
	if (datosNatural.telefonoAsegurado != null)
		$("#telefono_asegurado_natural").val(datosNatural.telefonoAsegurado);
	else
		$("#telefono_asegurado_natural").val("");
	
	if (datosNatural.celularAsegurado != null)
		$("#celular_asegurado_natural").val(datosNatural.celularAsegurado);
	else
		$("#celular_asegurado_natural").val("");
	
	if (datosNatural.relacionAsegurado != null)
		$("#relacion_asegurado_natural").val(datosNatural.relacionAsegurado);
	else
		$("#relacion_asegurado_natural").val("");
	
	//beneficiario
	if (datosNatural.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionBeneficiario, "beneficiario_natural");
	else
		cargarTiposIdentificacion("", "beneficiario_natural");
	
	if (datosNatural.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_natural").val(datosNatural.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_natural").val("");
	
	if (datosNatural.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_natural").val(datosNatural.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_natural").val("");
	
	if (datosNatural.direccionBeneficiario != null)
		$("#direccion_beneficiario_natural").val(datosNatural.direccionBeneficiario);
	else
		$("#direccion_beneficiario_natural").val("");
	
	if (datosNatural.telefonoBeneficiario != null)
		$("#telefono_beneficiario_natural").val(datosNatural.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_natural").val("");
	
	if (datosNatural.celularBeneficiario != null)
		$("#celular_beneficiario_natural").val(datosNatural.celularBeneficiario);
	else
		$("#celular_beneficiario_natural").val("");
	
	if (datosNatural.relacionBeneficiario != null)
		$("#relacion_beneficiario_natural").val(datosNatural.relacionBeneficiario);
	else
		$("#relacion_beneficiario_natural").val("");

	cargadoDatosUPLA=true;
	
}

function cargarDatosUPLAJuridica(datosJuridica) {


	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';

	var optionsZona = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	optionsZona += '<option value="U">&nbsp;&nbsp;Urbana </option>';
	optionsZona += '<option value="R">&nbsp;&nbsp;Rural </option>';

	var tipoActividad = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	tipoActividad += '<option value="Privado">Empleado Privado</option>';
	tipoActividad += '<option value="Publico">Empleado Público</option>';
	tipoActividad += '<option value="Independiente">Independiente</option>';
	tipoActividad += '<option value="Jubilado">Jubilado</option>';
	
	if (datosJuridica.objetoSocial != null)
		$("#objeto_social_juridica").val(datosJuridica.objetoSocial);
	else
		$("#objeto_social_juridica").val("");
	
	if (datosJuridica.ciudadJuridica != null)
		$("#ciudad_juridica").val(datosJuridica.ciudadJuridica);
	else
		$("#ciudad_juridica").val("");
	
	if (datosJuridica.zonaDireccionMatriz != null)
		$("#zona_direccion_matriz_juridica").html(optionsZona).val(datosJuridica.zonaDireccionMatriz).attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_matriz_juridica").html(optionsZona).val("").attr('required', 'required');

	if (datosJuridica.provinciaDireccionMatriz != null)
		cargarProvincias(datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
	else
		cargarProvincias("", "direccion_matriz_juridica");

	if (datosJuridica.zonaDireccionMatriz == "R") {
		if (datosJuridica.cantonDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones(datosJuridica.cantonDireccionMatriz,datosJuridica.provinciaDireccionMatriz,"direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones("", datosJuridica.provinciaDireccionMatriz,"direccion_matriz_juridica");
		}
		
		if (datosJuridica.parroquiaDireccionMatriz != null) {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias(datosJuridica.parroquiaDireccionMatriz,datosJuridica.cantonDireccionMatriz,"direccion_matriz_juridica");
		} else {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias("",datosJuridica.cantonDireccionMatriz,"direccion_matriz_juridica");
		}
		
	} else {
		if (datosJuridica.ciudadDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCiudades(datosJuridica.ciudadDireccionMatriz, datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCiudades("", datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		}
	}
	
	

	if (datosJuridica.callePrincipalMatriz != null)
		$("#calle_principal_direccion_juridica").val(datosJuridica.callePrincipalMatriz);
	else
		$("#calle_principal_direccion_juridica").val("");

	if (datosJuridica.numeroDireccionMatriz != null)
		$("#numero_direccion_juridica").val(datosJuridica.numeroDireccionMatriz);
	else
		$("#numero_direccion_juridica").val("");

	if (datosJuridica.calleSecundariaMatriz != null)
		$("#calle_secundaria_direccion_juridica").val(datosJuridica.calleSecundariaMatriz);
	else
		$("#calle_secundaria_direccion_juridica").val("");

	if (datosJuridica.referenciaDireccionMatriz != null)
		$("#referencia_direccion_juridica").val(datosJuridica.referenciaDireccionMatriz);
	else
		$("#referencia_direccion_juridica").val("");
	
	if (datosJuridica.sucursalDireccion != null)
		$("#direccion_sucursal_juridica").val(datosJuridica.sucursalDireccion);
	else
		$("#direccion_sucursal_juridica").val("");

	if (datosJuridica.sucursalCiudad != null)
		$("#ciudad_sucursal_juridica").val(datosJuridica.sucursalCiudad);
	else
		$("#ciudad_sucursal_juridica").val("");
	
	if (datosJuridica.telefono != null)
		$("#telefono_juridica").val(datosJuridica.telefono);
	else
		$("#telefono_juridica").val("");

	if (datosJuridica.fax != null)
		$("#fax_juridica").val(datosJuridica.fax);
	else
		$("#fax_juridica").val("");
	
	//actividad persona natural
	
	if (datosJuridica.actividadJuridica != null)
		cargarActividades(datosJuridica.actividadJuridica,"juridica");
	else
		cargarActividades("","juridica");
	
	if (datosJuridica.nombresRepresentanteLegal != null)
		$("#nombres_representante_juridica").val(datosJuridica.nombresRepresentanteLegal);
	else
		$("#nombres_representante_juridica").val("");
	
	if (datosJuridica.apellidosRepresentanteLegal != null)
		$("#apellidos_representante_juridica").val(datosJuridica.apellidosRepresentanteLegal);
	else
		$("#apellidos_representante_juridica").val("");
	
	if (datosJuridica.tipoIdentificacionRepresentante != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionRepresentante, "representante_juridica");
	else
		cargarTiposIdentificacion("", "representante_juridica");

	if (datosJuridica.identificacionRepresentante != null)
		$("#identificacion_representante_juridica").val(datosJuridica.identificacionRepresentante);
	else
		$("#identificacion_representante_juridica").val("");

	if (datosJuridica.lugarNacimientoRepresentante != null)
		$("#lugar_nacimiento_representante_juridica").val(datosJuridica.lugarNacimientoRepresentante);
	else
		$("#lugar_nacimiento_representante_juridica").val("");

	if (datosJuridica.fechaNacimiento != null){
		var dia=datosJuridica.fechaNacimiento.date<10?'0'+datosJuridica.fechaNacimiento.date:datosJuridica.fechaNacimiento.date;
		var mes=datosJuridica.fechaNacimiento.month<10?"0"+(datosJuridica.fechaNacimiento.month+1):(datosJuridica.fechaNacimiento.month+1);
		var anio=(1900+datosJuridica.fechaNacimiento.year);
		var aux=''+anio+'-'+mes+'-'+dia;
		
		$("#fecha_nacimiento_representante_juridica").val(aux);}
	else
		$("#fecha_nacimiento_representante_juridica").val("");
	
	
	
	
	if (datosJuridica.residenciaRepresentante != null)
		$("#residencia_representante_juridica").val(datosJuridica.residenciaRepresentante);
	else
		$("#residencia_representante_juridica").val("");
	
	/*if (datosJuridica.paisRepresentante != null)
		$("#pais_representante_juridica").val(datosJuridica.paisRepresentante);
	else
		$("#pais_representante_juridica").val("");*/
	
	if (datosJuridica.provinciaRepresentante != null)
		cargarProvincias(datosJuridica.provinciaRepresentante, "representante_juridica");
		//$("#provincia_representante_juridica").val(datosJuridica.provinciaRepresentante);
	else
		cargarProvincias("", "representante_juridica");
		//$("#provincia_representante_juridica").val("");
	
	if (datosJuridica.ciudadRepresentante != null)
		cargarCiudades(datosJuridica.ciudadRepresentante, datosJuridica.provinciaRepresentante, "representante_juridica");
		//$("#ciudad_representante_juridica").val(datosJuridica.ciudadRepresentante);
	else
		//$("#ciudad_representante_juridica").val("");
		cargarCiudades('', datosJuridica.provinciaRepresentante, "representante_juridica");
	
	if (datosJuridica.telefonoRepresentante != null)
		$("#telefono_representante_juridica").val(datosJuridica.telefonoRepresentante);
	else
		$("#telefono_representante_juridica").val("");
	
	if (datosJuridica.celularRepresentante != null)
		$("#celular_representante_juridica").val(datosJuridica.celularRepresentante);
	else
		$("#celular_representante_juridica").val("");
	
	if (datosJuridica.expuestoRepresentante != null)
		$("#expuesto_representante_juridica").html(options).val(datosJuridica.expuestoRepresentante?1:0);
	else
		$("#expuesto_representante_juridica").html(options).val("");

	if (datosJuridica.cargoExpuesta != null)
		$("#cargo_expuesta_representante_juridica").val(datosJuridica.cargoExpuesta);
	else
		$("#cargo_expuesta_representante_juridica").val("");

	if (datosJuridica.expuestoFamiliar != null)
		$("#expuesto_familiar_juridica").html(options).val(datosJuridica.expuestoFamiliar?1:0);
	else
		$("#expuesto_familiar_juridica").html(options).val("");

	if (datosJuridica.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_juridico").val(datosJuridica.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_juridico").val("");

	if (datosJuridica.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_juridica").val(datosJuridica.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_juridica").val("");

	if (datosJuridica.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_juridica").val(datosJuridica.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_juridica").val("");

	if (datosJuridica.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_juridica").val(datosJuridica.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_juridica").val("");
	
	if (datosJuridica.nombreConyuge != null)
		$("#nombre_conyuge_juridica").val(datosJuridica.nombreConyuge);
	else
		$("#nombre_conyuge_juridica").val("");

	if (datosJuridica.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionConyuge, "conyugue_juridica");
	else
		cargarTiposIdentificacion("", "conyugue_juridica");

	if (datosJuridica.identificacionConyuge != null)
		$("#identificacion_conyuge_juridica").val(datosJuridica.identificacionConyuge);
	else
		$("#identificacion_conyuge_juridica").val("");

	if (datosJuridica.salarioMensual != null)
		$("#salario_mensual_juridica").val(datosJuridica.salarioMensual);
	else
		$("#salario_mensual_juridica").val("");
	
	if (datosJuridica.activos != null)
		$("#activos_juridica").val(datosJuridica.activos);
	else
		$("#activos_juridica").val("");
	
	if (datosJuridica.otrosIngresos != null)
		$("#otros_ingresos_juridica").val(datosJuridica.otrosIngresos);
	else
		$("#otros_ingresos_juridica").val("");
	
	if (datosJuridica.pasivos != null)
		$("#pasivos_juridica").val(datosJuridica.pasivos);
	else
		$("#pasivos_juridica").val("");
	
	if (datosJuridica.egresos != null)
		$("#egresos_juridica").val(datosJuridica.egresos);
	else
		$("#egresos_juridica").val("");
	
	if (datosJuridica.patrimonio != null)
		$("#patrimonio_juridica").val(datosJuridica.patrimonio);
	else
		$("#patrimonio_juridica").val("");
	
	if (datosJuridica.ingresoEgreso != null)
		$("#ingresos_egresos_juridica").val(datosJuridica.ingresoEgreso);
	else
		$("#ingresos_egresos_juridica").val("");
	
	if (datosJuridica.esAsegurado != null)
		$("#es_asegurado_juridica").html(options).val(datosJuridica.esAsegurado?1:0);
	else
		$("#es_asegurado_juridica").html(options).val("");
	
	if (datosJuridica.esBeneficiario != null)
		$("#es_beneficiario_juridica").html(options).val(datosJuridica.esBeneficiario?1:0);
	else
		$("#es_beneficiario_juridica").html(options).val("");

	if (datosJuridica.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionAsegurado, "asegurado_juridica");
	else
		cargarTiposIdentificacion("", "asegurado_juridica");
	
	if (datosJuridica.identificacionAsegurado != null)
		$("#identificacion_asegurado_juridica").val(datosJuridica.identificacionAsegurado);
	else
		$("#identificacion_asegurado_juridica").val("");
	
	if (datosJuridica.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_juridica").val(datosJuridica.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_juridica").val("");
	
	if (datosJuridica.direccionAsegurado != null)
		$("#direccion_asegurado_juridica").val(datosJuridica.direccionAsegurado);
	else
		$("#direccion_asegurado_juridica").val("");
	
	if (datosJuridica.telefonoAsegurado != null)
		$("#telefono_asegurado_juridica").val(datosJuridica.telefonoAsegurado);
	else
		$("#telefono_asegurado_juridica").val("");
	
	if (datosJuridica.celularAsegurado != null)
		$("#celular_asegurado_juridica").val(datosJuridica.celularAsegurado);
	else
		$("#celular_asegurado_juridica").val("");
	
	if (datosJuridica.relacionAsegurado != null)
		$("#relacion_asegurado_juridica").val(datosJuridica.relacionAsegurado);
	else
		$("#relacion_asegurado_juridica").val("");
	
	//beneficiario
	if (datosJuridica.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionBeneficiario, "beneficiario_juridica");
	else
		cargarTiposIdentificacion("", "beneficiario_juridica");
	
	if (datosJuridica.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_juridica").val(datosJuridica.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_juridica").val("");
	
	if (datosJuridica.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_juridica").val(datosJuridica.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_juridica").val("");
	
	if (datosJuridica.direccionBeneficiario != null)
		$("#direccion_beneficiario_juridica").val(datosJuridica.direccionBeneficiario);
	else
		$("#direccion_beneficiario_juridica").val("");
	
	if (datosJuridica.telefonoBeneficiario != null)
		$("#telefono_beneficiario_juridica").val(datosJuridica.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_juridica").val("");
	
	if (datosJuridica.celularBeneficiario != null)
		$("#celular_beneficiario_juridica").val(datosJuridica.celularBeneficiario);
	else
		$("#celular_beneficiario_juridica").val("");
	
	if (datosJuridica.relacionBeneficiario != null)
		$("#relacion_beneficiario_juridica").val(datosJuridica.relacionBeneficiario);
	else
		$("#relacion_beneficiario_juridica").val("");

	cargadoDatosUPLA=true;

}

function guardarDatosUPLANatural() {

	var lugarNacimiento = $("#lugar_nacimiento_natural").val();
	var fechaNacimiento = $("#fecha_nacimiento_natural").val();
	var zonaDireccionCliente =$("#zona_direccion_natural").val();
	var callePrincipalCliente = $("#calle_principal_direccion_natural").val();
	var calleSecundariaCliente = $("#calle_secundaria_direccion_natural").val();
	var numeroDireccionCliente = $("#numero_direccion_natural").val();
	var refenciaDireccionCliente = $("#referencia_direccion_natural").val();
	var provincia = $("#provincia_direccion_cliente_natural").val();
	var canton = $("#canton_direccion_cliente_natural").val();
	var parroquia = $("#parroquia_direccion_cliente_natural").val();
	var ciudad = $("#ciudad_direccion_cliente_natural").val();
	var telefono = $("#telefono_cliente_natural").val();
	var celular = $("#celular_cliente_natural").val();
	var actividad = $("#actividad_economica_cliente_natural").select2("val");
	var tipoActividad = $("#tipo_actividad_cliente_natural").val();
	var cargoOcupa = $("#cargo_ocupa_cliente_natural").val();
	var tipoRamoContrata = $("#ramo_contrata_cliente_natural").val();
	var expuesto = $("#expuesto_cliente_natural").val();
	var cargoExpuesta = $("#cargo_expuesto_cliente_natural").val();
	var expuestoFamiliar = $("#expuesto_familiar_natural").val();
	var parentescoFamiliar = $("#parentesco_expuesto_familiar_natural").val();
	var cargoFamiliar = $("#cargo_expuesto_familiar_natural").val();
	var apellidoPaternoConyuge = $("#apellido_paterno_conyuge_natural").val();
	var apellidoMaternoConyuge = $("#apellido_materno_conyuge_natural").val();
	var nombresConyuge = $("#nombre_conyuge_natural").val();
	var tipoIdentificacionConyuge = $("#tipo_identificacion_conyuge_natural").val();
	var identificacionConyuge = $("#identificacion_conyuge_natural").val();
	var salarioMensual = $("#salario_mensual_natural").val();
	var activo = $("#activos_natural").val();
	var otrosIngresos = $("#otros_ingresos_natural").val();
	var pasivos = $("#pasivos_natural").val();
	var egresos = $("#egresos_natural").val();
	var patrimonio = $("#patrimonio_natural").val();
	var ingresosEgresos = $("#ingresos_egresos_natural").val();
	var esAsegurado = $("#es_asegurado_natural").val();
	var esBeneficiario = $("#es_beneficiario_natural").val();
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado_natural").val();
	var identificacionAsegurado = $("#identificacion_asegurado_natural").val();
	var nombreAsegurado = $("#nombre_asegurado_natural").val();
	var domicilioAsegurado = $("#direccion_asegurado_natural").val();
	var telefonoAsegurado = $("#telefono_asegurado_natural").val();
	var celularAsegurado = $("#celular_asegurado_natural").val();
	var relacionAsegurado = $("#relacion_asegurado_natural").val();
	var tipoIdentificacionBeneficiario = $("#tipo_identificacion_beneficiario_natural").val();
	var identificacionBeneficiario = $("#identificacion_beneficiario_natural").val();
	var nombreBeneficiario = $("#nombre_beneficiario_natural").val();
	var domicilioBeneficiario = $("#direccion_beneficiario_natural").val();
	var telefonoBeneficiario = $("#telefono_beneficiario_natural").val();
	var celularBeneficiario = $("#celular_beneficiario_natural").val();
	var relacionBeneficiario = $("#relacion_beneficiario_natural").val();
	var identificacion = $("#identificacion").val();
	var genero = $("#genero_cliente_natural").val();
	var mail = $("#mail_cliente_natural").val();
	var cotizacion = $("#cotizacion_id").text();
	

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion":identificacion,
			"tipoConsulta" : "guardarDatosNatural",
			"lugarNacimiento" : lugarNacimiento,
			"fechaNacimiento" : fechaNacimiento,
			"zonaDireccionCliente":zonaDireccionCliente,
			"callePrincipalCliente" : callePrincipalCliente,
			"calleSecundariaCliente" : calleSecundariaCliente,
			"numeroDireccionCliente" : numeroDireccionCliente,
			"refenciaDireccionCliente" : refenciaDireccionCliente,
			"provincia" : provincia,
			"canton" : canton,
			"parroquia" : parroquia,
			"ciudad" : ciudad,
			"telefono" : telefono,
			"celular" : celular,
			"actividad" : actividad,
			"tipoActividad" : tipoActividad,
			"cargoOcupa" : cargoOcupa,
			"tipoRamoContrata" : tipoRamoContrata,
			"expuesto" : expuesto,
			"cargoExpuesta" : cargoExpuesta,
			"expuestoFamiliar" : expuestoFamiliar,
			"parentescoFamiliar" : parentescoFamiliar,
			"cargoFamiliar" : cargoFamiliar,
			"apellidoPaternoConyuge" : apellidoPaternoConyuge,
			"apellidoMaternoConyuge" : apellidoMaternoConyuge,
			"nombresConyuge" : nombresConyuge,
			"tipoIdentificacionConyuge" : tipoIdentificacionConyuge,
			"identificacionConyuge" : identificacionConyuge,
			"salarioMensual" : salarioMensual,
			"activo" : activo,
			"otrosIngresos" : otrosIngresos,
			"pasivos" : pasivos,
			"egresos" : egresos,
			"patrimonio" : patrimonio,
			"ingresosEgresos" : ingresosEgresos,
			"esAsegurado" : esAsegurado,
			"esBeneficiario" : esBeneficiario,
			"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
			"identificacionAsegurado" : identificacionAsegurado,
			"nombreAsegurado" : nombreAsegurado,
			"domicilioAsegurado" : domicilioAsegurado,
			"telefonoAsegurado" : telefonoAsegurado,
			"celularAsegurado" : celularAsegurado,
			"relacionAsegurado" : relacionAsegurado,
			"tipoIdentificacionBeneficiario" : tipoIdentificacionBeneficiario,
			"identificacionBeneficiario" : identificacionBeneficiario,
			"nombreBeneficiario" : nombreBeneficiario,
			"domicilioBeneficiario" : domicilioBeneficiario,
			"telefonoBeneficiario" : telefonoBeneficiario,
			"celularBeneficiario" : celularBeneficiario,
			"relacionBeneficiario" : relacionBeneficiario,
			"genero":genero,
			"mail":mail,
			"cotizacion":cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			
		}
	});
}


function guardarDatosUPLAJuridica() {

	var objetoSocial = $("#objeto_social_juridica").val();
	//var ciudad = $("#ciudad_direccion_matriz_juridica").val();
	var zonaDireccionMatriz =$("#zona_direccion_matriz_juridica").val();
	var provinciaDireccionMatriz = $("#provincia_direccion_matriz_juridica").val();
	var cantonDireccionMatriz = $("#canton_direccion_matriz_juridica").val();
	var parroquiaDireccionMatriz = $("#parroquia_direccion_matriz_juridica").val();
	var ciudadDireccionMatriz = $("#ciudad_direccion_matriz_juridica").val();
	var callePrincipalDireccion = $("#calle_principal_direccion_juridica").val();
	var numeroDireccion = $("#numero_direccion_juridica").val();
	var calleSecundariaDireccion = $("#calle_secundaria_direccion_juridica").val();
	var referenciaDireccion = $("#referencia_direccion_juridica").val();
	var direccionSucursal = $("#direccion_sucursal_juridica").val();
	var ciudadSucursal = $("#ciudad_sucursal_juridica").val();
	var telefono = $("#telefono_juridica").val();
	var fax = $("#fax_juridica").val();
	var actividad = $("#actividad_economica_juridica").select2("val");
	var nombresRepresentante = $("#nombres_representante_juridica").val();
	var apellidosRepresentante = $("#apellidos_representante_juridica").val();
	var tipoIdentificacionRepresentante = $("#tipo_identificacion_representante_juridica").val();
	var identificacionRepresentante = $("#identificacion_representante_juridica").val();
	var lugarNacimientoRepresentante = $("#lugar_nacimiento_representante_juridica").val();
	var fechaNacimientoRepresentante = $("#fecha_nacimiento_representante_juridica").val();
	var residenciaRepresentante = $("#residencia_representante_juridica").val();
	//var paisRepresentante = $("#pais_representante_juridica").val();
	var provinciaRepresentante = $("#provincia_representante_juridica").val();
	var ciudadRepresentante = $("#ciudad_representante_juridica").val();
	var telefonoRepresentante = $("#telefono_representante_juridica").val();
	var celularRepresentante = $("#celular_representante_juridica").val();
	var expuestoRepresentante = $("#expuesto_representante_juridica").val();
	var cargoExpuestaRepresentante = $("#cargo_expuesta_representante_juridica").val();
	var expuestoFamiliar = $("#expuesto_familiar_juridica").val();
	var parentescoExpuestoFamiliar = $("#parentesco_expuesto_familiar_juridico").val();
	var cargoExpuestoFamiliar = $("#cargo_expuesto_familiar_juridica").val();
	var apellidoPaternoConyuge = $("#apellido_paterno_conyuge_juridica").val();
	var apellidoMaternoConyuge = $("#apellido_materno_conyuge_juridica").val();
	var nombreConyuge = $("#nombre_conyuge_juridica").val();
	var tipoIdentificacionConyugue = $("#tipo_identificacion_conyugue_juridica").val();
	var identificacionConyuge = $("#identificacion_conyuge_juridica").val();
	var salarioMensual = $("#salario_mensual_juridica").val();
	var activos = $("#activos_juridica").val();
	var otrosIngresos = $("#otros_ingresos_juridica").val();
	var pasivos = $("#pasivos_juridica").val();
	var egresos = $("#egresos_juridica").val();
	var patrimonio = $("#patrimonio_juridica").val();
	var ingresosEgresos = $("#ingresos_egresos_juridica").val();
	var esAsegurado = $("#es_asegurado_juridica").val();
	var esBeneficiario = $("#es_beneficiario_juridica").val();
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado_juridica").val();
	var identificacionAsegurado = $("#identificacion_asegurado_juridica").val();
	var nombreAsegurado = $("#nombre_asegurado_juridica").val();
	var direccionAsegurado = $("#direccion_asegurado_juridica").val();
	var telefonoAsegurado = $("#telefono_asegurado_juridica").val();
	var celularAsegurado = $("#celular_asegurado_juridica").val();
	var relacionAsegurado = $("#relacion_asegurado_juridica").val();
	var tipoIdentificacionBeneficiario = $("#tipo_identificacion_beneficiario_juridica").val();
	var identificacionBeneficiario = $("#identificacion_beneficiario_juridica").val();
	var nombreBeneficiario = $("#nombre_beneficiario_juridica").val();
	var direccionBeneficiario = $("#direccion_beneficiario_juridica").val();
	var telefonoBeneficiario = $("#telefono_beneficiario_juridica").val();
	var celularBeneficiario = $("#celular_beneficiario_juridica").val();
	var relacionBeneficiario = $("#telefono_beneficiario_juridica").val();
	var identificacion = $("#identificacion").val();
	var genero = $("#genero_representante_juridico").val();
	var mail = $("#mail_representante_juridico").val();
	var cotizacion = $("#cotizacion_id").text();
	
	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion":identificacion,
			"tipoConsulta" : "guardarDatosJuridica",
			"objetoSocial":objetoSocial,
			"zonaDireccionMatriz":zonaDireccionMatriz,
			"provinciaDireccionMatriz":provinciaDireccionMatriz,
			"cantonDireccionMatriz":cantonDireccionMatriz,
			"parroquiaDireccionMatriz":parroquiaDireccionMatriz,
			"ciudadDireccionMatriz":ciudadDireccionMatriz,
			"callePrincipalDireccion":callePrincipalDireccion,
			"numeroDireccion":numeroDireccion,
			"calleSecundariaDireccion":calleSecundariaDireccion,
			"referenciaDireccion":referenciaDireccion,
			"direccionSucursal":direccionSucursal,
			"ciudadSucursal":ciudadSucursal,
			"telefono":telefono,
			"fax":fax,
			"actividad":actividad,
			"nombresRepresentante":nombresRepresentante,
			"apellidosRepresentante":apellidosRepresentante,
			"tipoIdentificacionRepresentante":tipoIdentificacionRepresentante,
			"identificacionRepresentante":identificacionRepresentante,
			"lugarNacimientoRepresentante":lugarNacimientoRepresentante,
			"fechaNacimientoRepresentante":fechaNacimientoRepresentante,
			"residenciaRepresentante":residenciaRepresentante,
			//"paisRepresentante":paisRepresentante,
			"provinciaRepresentante":provinciaRepresentante,
			"ciudadRepresentante":ciudadRepresentante,
			"telefonoRepresentante":telefonoRepresentante,
			"celularRepresentante":celularRepresentante,
			"expuestoRepresentante":expuestoRepresentante,
			"cargoExpuestaRepresentante":cargoExpuestaRepresentante,
			"expuestoFamiliar":expuestoFamiliar,
			"parentescoExpuestoFamiliar":parentescoExpuestoFamiliar,
			"cargoExpuestoFamiliar":cargoExpuestoFamiliar,
			"apellidoPaternoConyuge":apellidoPaternoConyuge,
			"apellidoMaternoConyuge":apellidoMaternoConyuge,
			"nombreConyuge":nombreConyuge,
			"tipoIdentificacionConyugue":tipoIdentificacionConyugue,
			"identificacionConyuge":identificacionConyuge,
			"salarioMensual":salarioMensual,
			"activos":activos,
			"otrosIngresos":otrosIngresos,
			"pasivos":pasivos,
			"egresos":egresos,
			"patrimonio":patrimonio,
			"ingresosEgresos":ingresosEgresos,
			"esAsegurado":esAsegurado,
			"esBeneficiario":esBeneficiario,
			"tipoIdentificacionAsegurado":tipoIdentificacionAsegurado,
			"identificacionAsegurado":identificacionAsegurado,
			"nombreAsegurado":nombreAsegurado,
			"direccionAsegurado":direccionAsegurado,
			"telefonoAsegurado":telefonoAsegurado,
			"celularAsegurado":celularAsegurado,
			"relacionAsegurado":relacionAsegurado,
			"tipoIdentificacionBeneficiario":tipoIdentificacionBeneficiario,
			"identificacionBeneficiario":identificacionBeneficiario,
			"nombreBeneficiario":nombreBeneficiario,
			"direccionBeneficiario":direccionBeneficiario,
			"telefonoBeneficiario":telefonoBeneficiario,
			"celularBeneficiario":celularBeneficiario,
			"relacionBeneficiario":relacionBeneficiario,
			"genero":genero,
			"mail":mail,
			"cotizacion":cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			
		}
	});
}

function guardarPago(valor) {
	var tipoConsulta = "crear";
	
	$("#msgPopupPagoGrabo").hide();
	$("#msgPopupPagoError").hide();
	if(parseInt($("#codigoPagoRegistrado").val()) > 0)
		tipoConsulta = "actualizar";
	
	if (valor == 'contado') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoPago" : $("#codigoPagoRegistrado").val(),
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : $("#banco_forma_pago").val(),
				"valor" : $("#total_vh").val(),
				"plazo": "1",
				"fechaPago": $("#fechaInicialPagos").val(),
				"formaPagoSeleccionada" : "contado",
				"tipoConsulta" : tipoConsulta
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
				$("#msgPopupPagoGrabo").show();
			}
		});
	}

	if (valor == 'banco') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoPago" : $("#codigoPagoRegistrado").val(),
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : $("#bancoId").val(),
				"tipoCuenta" : $("#bancoTipoCuenta").val(),
				"numCuenta" : $("#bancoNumeroCuenta").val(),
				"titular" : $("#bancoTitular").val(),
				"tipoIdentificacionId": $("#tipo_identificacion_banco").val(),
				"identificacion" : $("#bancoIdentificacion").val(),
				"plazo" : $("#bancoPlazo").val(),
				"fechaPago": $("#fechaInicialPagos").val(),
				"valor" : $("#total_vh").val(),
				"formaPagoSeleccionada" : "debitoBanco",
				"valor": $("#total_vh").val(),
				"tipoConsulta" : tipoConsulta
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
				$("#msgPopupPagoGrabo").show();
			}
		});
	}

	if (valor == 'tarjeta') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoPago" : $("#codigoPagoRegistrado").val(),
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : $("#tarjetaId").val(),
				"numCuenta" : $("#tarjetaNumero").val(),
				"titular" : $("#tarjetaTitular").val(),
				"tipoCuenta": 'T',
				"tipoIdentificacionId": $("#tipo_identificacion_tarjeta").val(),
				"identificacion" : $("#tarjetaIdentificacion").val(),
				"tarjetaAnioExp" : $("#tarjetaAnioExp").val(),
				"tarjetaMesExp" : $("#tarjetaMesExp").val(),
				"plazo" : $("#tarjetaPlazo").val(),
				"fechaPago": $("#fechaInicialPagos").val(),
				"valor" : $("#total_vh").val(),
				"formaPagoSeleccionada" : "debitoTarjeta",
				"tipoConsulta" : tipoConsulta
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
				$("#msgPopupPagoGrabo").show();
			}
		});
	}

	if (valor == 'cuota') {
		var listadoCheques = "";

		$(".detalleChequesPagos").each(function(){
			if($(this).val() != "")
				listadoCheques = listadoCheques + $(this).val() + ",";
			else
				listadoCheques = listadoCheques + " ,";
		});		

		$.ajax({
			url : '../PagoController',
			data : {
				"codigoPago" : $("#codigoPagoRegistrado").val(),
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago": $("#cboFpFormaPago").val(),
				"codigoInstFinanciera": $("#tarjetaId").val(),
				"numCuenta": $("#tarjetaNumero").val(),
				"titular": $("#tarjetaTitular").val(),
				"tipoCuenta": 'T',
				"tipoIdentificacionId": $("#tarjetaTipoIdentificacion").val(),
				"identificacion":$("#tarjetaIdentificacion").val(),
				"tarjetaAnioExp": $("#tarjetaAnioExp").val(),
				"tarjetaMesExp": $("#tarjetaMesExp").val(),
				"plazo": $("#cboFpPlazo").val(),
				"fechaPago": $("#fechaInicialPagos").val(),
				"valor": $("#total_vh").val(),
				"formaPagoSeleccionada": "cuotas",
				"tipoConsulta" : tipoConsulta,
				"cuotaInicial":$("#cuotaInicial").parent().next().text(),
				"listadoCheques":listadoCheques
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
				$("#msgPopupPagoGrabo").show();
			}
		});	
	}
}

function cargarProvincias(seleccionada, tipo) {
	var options = '<option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../ProvinciaController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoProvincia.length; i++)
				options += '<option value="' + datos.listadoProvincia[i].codigo + '">' + datos.listadoProvincia[i].nombre + '</option>';

			if (tipo == "")
				$(".provincia").append(options).val(seleccionada);
			else
				$("#provincia_" + tipo).append(options).val(seleccionada).trigger("click");
		}
	});

}

function cargarCiudadesPorProvincia(provincia, ciudadSeleccionada, tipo) {
	var options = '<option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : provincia
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.ciudades.length; i++)
				options += '<option value="' + datos.ciudades[i].id + '">' + datos.ciudades[i].nombre + '</option>';
			
			if (tipo == "")
				$(".ciudad").empty().removeAttr("disabled").append(options);
			else
				$("#ciudad_ubicacion" + tipo).empty().removeAttr("disabled").append(options).val(ciudadSeleccionada).trigger("click");
		}
	});

}


/*
function cargarCiudades(seleccionada, provincia, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	alert(seleccionada + " | " + provincia + " | " + tipo);

	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : provincia
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.ciudades.length; i++)
				options += '<option value="' + datos.ciudades[i].id + '">' + datos.ciudades[i].nombre + '</option>';

			if (tipo == "")
				$(".ciudad").html(options).val(seleccionada);
			else
				$("#ciudad_" + tipo).html(options).val(seleccionada);
		}
	});

}
*/
function cargarCantones(seleccionada, provincia, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : provincia
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.cantones.length; i++)
				options += '<option value="' + datos.cantones[i].id + '">' + datos.cantones[i].nombre + '</option>';

			if (tipo == "")
				$(".canton").html(options).val(seleccionada);
			else
				$("#canton_" + tipo).html(options).val(seleccionada);

		}
	});

}

function cargarParroquias(seleccionada, canton, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : canton
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoParroquia.length; i++)
				options += '<option value="' + datos.listadoParroquia[i].id + '">' + datos.listadoParroquia[i].nombre + '</option>';

			if (tipo == "")
				$(".parroquia").html(options).val(seleccionada);
			else
				$("#parroquia_" + tipo).html(options).val(seleccionada);
		}
	});
}

function cargarActividades(seleccionada, tipo) {
	//var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../ActividadEconomicaController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			
			if (tipo == "")
				$('.actividad_economica').select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
			else
				$("#actividad_economica_" + tipo).select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
		}
	});
}

function cargarTipoConstruccion(seleccionada, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../TipoConstruccionController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			
			if (tipo == "")
				$('.tipo_construccion').select2({
					data : datos.listadoTipoConstruccion,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
			else
				$("#tipo_construccion_" + tipo).select2({
					data : datos.listadoTipoConstruccion,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
		}
	});
}

function cargarTipoOcupacion(seleccionada, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../TipoOcupacionController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			
			if (tipo == "")
				$('.tipo_ocupacion').select2({
					data : datos.listadoTipoOcupacion,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
			else
				$("#tipo_ocupacion_" + tipo).select2({
					data : datos.listadoTipoOcupacion,
					placeholder : 'Seleccione una opcion'
				}).select2("val",seleccionada).attr("required","required");
		}
	});
}

function cambiaZonaDireccion(event,tipo){
	var target = event.target || event.srcElement;
	var seleccionada=$(target).val();
	if(seleccionada=="U"){
		if(tipo=='N'){
			$("#ciudad_cliente_label" ).fadeIn();
			$("#ciudad_cliente_select").fadeIn();
			$("#canton_cliente_label").fadeOut();
			$("#canton_cliente_select").fadeOut();
			$("#parroquia_cliente_label").fadeOut();
			$("#parroquia_cliente_select").fadeOut();
		}
		if(tipo=='J'){
			$("#ciudad_matriz_label" ).fadeIn();
			$("#ciudad_matriz_select").fadeIn();
			$("#canton_matriz_label").fadeOut();
			$("#canton_matriz_select").fadeOut();
			$("#parroquia_matriz_label").fadeOut();
			$("#parroquia_matriz_select").fadeOut();
			}
	}
	if(seleccionada=="R"){
		if(tipo=='N'){
			$("#canton_cliente_label").fadeIn();
			$("#canton_cliente_select").fadeIn();
			$("#parroquia_cliente_label").fadeIn();
			$("#parroquia_cliente_select").fadeIn();
			$("#ciudad_cliente_label" ).fadeOut();
			$("#ciudad_cliente_select").fadeOut();
		}
		if(tipo=='J'){
			$("#canton_matriz_label").fadeIn();
			$("#canton_matriz_select").fadeIn();
			$("#parroquia_matriz_label").fadeIn();
			$("#parroquia_matriz_select").fadeIn();
			$("#ciudad_matriz_label" ).fadeOut();
			$("#ciudad_matriz_select").fadeOut();
		}
	}
}


//probando validaciones pagos

function validacionBanco(){
	//los numeros de cuenta pertenecientes de qbe
	var cuentas = [];
	cuentas[0]='3039409904';
	cuentas[1]='3083207804';
	cuentas[2]='3162187904';
	cuentas[3]='3356072304';
	cuentas[4]='3387015504';
	cuentas[5]='02005008022';
	cuentas[6]='02005065840';
	cuentas[7]='02005072219';
	cuentas[8]='02005152484';
	cuentas[9]='02080003881';
	cuentas[10]='5005008235';
	cuentas[11]='0014001603';
	cuentas[12]='3506371';
	cuentas[13]='5926500007778';
	cuentas[14]='10086041184';
	cuentas[15]='1001504013';
	cuentas[16]='10154681';
	cuentas[17]='10260231';
	cuentas[18]='17060000443';
	cuentas[19]='0000632192';
	cuentas[20]='0082542035';
	cuentas[21]='36295998';
	cuentas[22]='052255667';
	cuentas[23]='1901010755933';
	cuentas[24]='2901040558';
	cuentas[25]='1009901985';
	
	
	for(var i = 0;i < cuentas.length;i++){
	if(i < 5){//pichincha ecuador
		if($("#bancoNumeroCuenta").val()==cuentas[i]&&$("#bancoId").val()=='254'){
		alert('El numero de cuenta escojido pertenece a QBE!');	
		$("#bancoNumeroCuenta").val("");
		}
	}
	
	if(i < 10 && i > 4){//produbanco
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='259'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 10){//bolivariano
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='32'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 11){//guayaquil
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='226'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	if(i == 12){//pacifico
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='252'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 13 || i == 14){//solidario
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='266'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 15){//promerica
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='9'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 16 || i == 17){//fomento
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='222'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 18){//capital
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='11'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 19){//internacional
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='232'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 20){//citibank
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='44'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 21){//citibank ny
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='42'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 22){//mutualista pichincha
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='247'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 23){//procredit
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='258'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 24){//banco de loja
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='15'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	
	if(i == 25){//banco pichincha panama
		if($("#bancoNumeroCuenta").val()==cuentas[i] && $("#bancoId").val()=='20'){
		alert('El numero de cuenta escojido pertenece a QBE!');
		$("#bancoNumeroCuenta").val('');
		}
	}
	}
	
	if($("#bancoNumeroCuenta").val().length>10 && $("#bancoId").val()=='254'){
		alert('El la cuenta debe tener m&acute;ximo 10 d&icute;gitos');
		$("#bancoNumeroCuenta").focus();
		}
}

function validaCuenta(){
	var retorno=true;
	var numero=$("#bancoNumeroCuenta").val();
	var tipo =$("#bancoTipoCuenta").val();
	if($("#bancoId").val()=='254')//pichincha
		if(!validaCuentasPichincha(numero,tipo)){
			alert('Cuenta no valida para el tipo de cuenta seleccionado');
			retorno=false;
			}
	if($("#bancoId").val()=='259')//produ
		if(!validaCuentasProdubanco(numero,tipo)){
			alert('Cuenta no valida para el tipo de cuenta seleccionado');
			retonro=false;
			}
			
			return retorno;
}

function cargarRamos(seleccionada, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../RamoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoRamo.length; i++)
				options += '<option value="' + datos.listadoRamo[i].codigo + '">' + datos.listadoRamo[i].nombre + '</option>';

			if (tipo == "")
				$(".ramo").html(options).val(seleccionada);
			else
				$("#ramo_" + tipo).html(options).val(seleccionada);
		}
	});
}

function validaCuentasPichincha(numero,tipo){
	var retorno=false;
	if(tipo=='Ahorro')//ahorros
	retorno=true;
	if(tipo=='Corriente')
	retorno=true;		
	return retorno;
}

function validaCuentasProdubanco(numero,tipo){
	var retorno=false;
	if(tipo=='Ahorro')//ahorros
	retorno=true;
	if(tipo=='Corriente')
	retorno=true;		
	return retorno;
}

function cambiaDispositivoRastreo(numero){
	var dispositivo=$("#disposito_rastreo"+numero).val();
	if(dispositivo=='1'){
		$("#porcentaje_suma_aseguradaDT"+numero).val(5);
		$("#porcentaje_suma_aseguradaDT"+numero).attr('min',5);
		}
	if(dispositivo=='0'){
		$("#porcentaje_suma_aseguradaDT"+numero).val(15);
		$("#porcentaje_suma_aseguradaDT"+numero).attr('min',15);
		}

}

function activarBotonPaquetes(numero,event){
	//evaldez
	var target = event.target || event.srcElement;
	var valor=$(target).val();
	if(valor>0){
		$("#paquete1_button"+numero).removeAttr("disabled");
		$("#paquete2_button"+numero).removeAttr("disabled");
		$("#paquete3_button"+numero).removeAttr("disabled");
		$("#paquete4_button"+numero).removeAttr("disabled");
		$("#paquete5_button"+numero).removeAttr("disabled");
		}
	else{
		$("#paquete1_button"+numero).attr("disabled","disabled");
		$("#paquete2_button"+numero).attr("disabled","disabled");
		$("#paquete3_button"+numero).attr("disabled","disabled");
		$("#paquete4_button"+numero).attr("disabled","disabled");
		$("#paquete5_button"+numero).removeAttr("disabled");
	}
}

function calcularValoresCoberturas(event,numero){
	var target = event.target || event.srcElement;
	var paquete=$(target).parent().parent().children().first().children().first().html();
	if(paquete=="Sin Paquete")
		paquete="Sin";
	
	var coberturas=$(".valor_"+paquete+"_Cobertura_tabla_"+numero);
	var suma=$("#suma_asegurada_"+numero).val();
	
	$(coberturas).each(function(index,element){
		var tasa=$(element).next();
		var tipoTasa=$(tasa).attr("class");
		var valorTasa=$(tasa).val();
		
		if(tipoTasa=='porcentual'){
			$(element).html("$"+(valorTasa*suma/100));
		}
		else{
			$(element).html("$"+(valorTasa));
		}
	});
}

function cambioTipoInspeccion(tipoInspeccion){
	$("#forma_inspeccion_externa").hide();
	if(tipoInspeccion == "externa"){
		$("#forma_inspeccion_externa").show();
	}
}

function cargarOrigenInspeccion(seleccionado){
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "encontrarOrigen"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoOrigenInspector = datos.listadoOrigenInspector;
			$("#origenInspeccion").append('<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>');
			$.each(listadoOrigenInspector,function(index){
				$("#origenInspeccion").append('<option value="' + listadoOrigenInspector[index].origen + '">' + listadoOrigenInspector[index].origen + '</option>');
			});		
			if(seleccionado!=null&&seleccionado!='')
				$("#origenInspeccion").val(seleccionado);
		}
	});	
}

function cargarDestinoInspeccion(origen,seleccionado){
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "encontrarDestinoByOrigen",
			"origen": origen
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoDestinoInspector = datos.listadoDestinoInspector;
			$("#destinoInspeccion").empty().append('<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>');
			$.each(listadoDestinoInspector,function(index){
				$("#destinoInspeccion").append('<option value="' + listadoDestinoInspector[index].destino + '">' + listadoDestinoInspector[index].destino + '</option>');
			});			
			if(seleccionado!=null&&seleccionado!='')
			$("#destinoInspeccion").val(seleccionado);
		}
	});	
}


function cargarProveedoresDisponibles(destino,seleccionado,origen){
	var origenEnviar=$("#origenInspeccion").val();
	if(origen!=null||origen=="")
		origenEnviar=origen;
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "buscarInspectoresDisponibles",
			"origen":origenEnviar ,
			"destino": destino
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoInspectoresDisponibles = datos.listadoInspectoresDisponibles;
			$("#detalleInspectoresDisponibles").empty().append('<tr style="background-color:#428bca; color:#ffffff;">'+
																	'<td>PROVEEDOR</td>'+
																	'<td>HORARIOS</td>'+
																	'<td>GASTOS</td>'+
																	'<td>TOTAL</td>'+
																	'<td>SELECCION</td>'+
																'</tr>');
			$.each(listadoInspectoresDisponibles,function(index){
				if(listadoInspectoresDisponibles[index].KmIda != "-1"){
					$("#detalleInspectoresDisponibles").append('<tr>'+
							'<td>' + listadoInspectoresDisponibles[index].nombreInspector + '</td>'+
							'<td>' + listadoInspectoresDisponibles[index].KmIda + '</td>'+
							'<td>' + listadoInspectoresDisponibles[index].KmRoundtrip + '</td>'+
							'<td>' + listadoInspectoresDisponibles[index].codigoInspector + '</td>'+
							'<td><input type="radio" id="proveedorSeleccionado" class="proveedorSeleccionado" name="proveedorSeleccionado" value="'+ listadoInspectoresDisponibles[index].codigoInspector +'"></td>'+
						'</tr>');
				}else{
					$("#detalleInspectoresDisponibles").append('<tr>'+
							'<td>' + listadoInspectoresDisponibles[index].nombreInspector + '</td>'+
							'<td colspan="4" style="font-size:small;">ESTE PROVEEDOR NO TIENE COBERTURA EN EL SECTOR</td>'+
						'</tr>');
				}
				
			});		
			if(seleccionado!=null&&seleccionado!=''){
				$.each($(".proveedorSeleccionado"),function(index){
					if($($(".proveedorSeleccionado")[index]).val()==seleccionado)
						$($(".proveedorSeleccionado")[index]).prop('checked', true);
				});
			}
		}
	});	
}

function guardarSolicitudInspeccion(){
	var bandera = true;
	$(".requeridoInspeccion").css("background-color","#ffffff");
	if($("#tipoInspeccion").val().trim() == "externa" && bandera){
		$(".requeridoInspeccion").each(function(){
			if($(this).val() == "" && bandera){
				alert("Por favor ingrese los campos requeridos");
				$(this).css("background-color","#fbc2c4");
				$(this).focus();
				bandera = false;
			}
		});
	}

	if($("#numContactoInspeccion").val().trim().substr(0,2) == "09" && bandera){
		if(parseInt($("#numContactoInspeccion").val().trim().length) != 10){
			alert("Un numero celular debe contener 10 digitos. Por favor ingrese un numero de celular valido");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color","#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
	}else{
		if(parseInt($("#numContactoInspeccion").val().trim().length) != 9 && bandera){
			alert("Un numero convencional debe contener 9 digitos. Por favor ingrese un numero convencional valido");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color","#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
		if($("#numContactoInspeccion").val().trim().substr(0,2) == "00" && bandera){
			alert("El n&acute;mero no puede empezar con 00");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color","#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
	}
	
	if(!$("#proveedorSeleccionado").is(':checked') && bandera){
		alert("Seleccione un proveedor para que realice la inspeccion");
		bandera = false;
	}
	var tipoConsulta="crear";
	if($("#solicitudInspeccionId").val()!="")
		tipoConsulta="actualizar";

	if(bandera)
		$.ajax({
			url : '../SolicitudInspeccionController',
			data : {
				"tipoConsulta" : tipoConsulta,
				"codigoInspector" : $("#proveedorSeleccionado").val(),
				"telfContacto": $("#numContactoInspeccion").val(),
				"origenInspeccion": $("#origenInspeccion").val(),
				"destinoInspeccion": $("#destinoInspeccion").val(),
				"valorInspeccion": $("#proveedorSeleccionado").parent().prev().text(),
				"codigoCotizacion":$("#cotizacion_id").text(),
				"codigo":$("#solicitudInspeccionId").val()
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				$("#msgPopupInspeccionGrabo").show();
			}
		});
}

function cargarSolicitudInspeccionPorId(id){
	
	$.ajax({
		url : '../SolicitudInspeccionController',
		data : {
			"tipoConsulta" : "encontrarPorCotizacionId",
			"codigoCotizacion":id
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var solicitudInspeccion=datos.solicitudInspeccion;
			if(solicitudInspeccion!=null){
				if(solicitudInspeccion.inspectorId==null)
				{//interna
					$("#tipoInspeccion").val('interna').trigger('change');
					$("#solicitudInspeccionId").val(solicitudInspeccion.id);
				}
				else{
					//externa
					$("#tipoInspeccion").val('externa').trigger('change');
					if(solicitudInspeccion.telefonoContacto)
						$("#numContactoInspeccion").val(solicitudInspeccion.telefonoContacto);
					if(solicitudInspeccion.origen)
						cargarOrigenInspeccion(solicitudInspeccion.origen);
					if(solicitudInspeccion.origen)
						cargarDestinoInspeccion(solicitudInspeccion.origen, solicitudInspeccion.destino);
					cargarProveedoresDisponibles(solicitudInspeccion.destino,solicitudInspeccion.inspectorId,solicitudInspeccion.origen);
					$("#solicitudInspeccionId").val(solicitudInspeccion.id);
				}
			}
		}
	});
		
	
}

function calcularAntiguedad(numero){
	var anio=$("#anio_fabricacion"+numero).val();
	var anioActual=new Date().getFullYear();
	var valor=anioActual-anio;
	if(valor==-1){
		$("#antiguedad_vh"+numero).val(0).attr('disabled','disabled');
	}
	else
		$("#antiguedad_vh"+numero).val(valor).attr('disabled','disabled');
}

function ceroKilometros(numero){
	var esCeroKilometros=$("#cero_kilometros"+numero).val();
	if(esCeroKilometros==1){
		$("#km_recorridos"+numero).attr('disabled','disabled').removeAttr('required');
	}
	if(esCeroKilometros==0){
		$("#km_recorridos"+numero).removeAttr('disabled').attr('required','required');
	}
	
}


function cambioDescargaCertificados() {

	var valor = $("#selectDescargas").val();
	if (valor == 1) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoCotizacion").fadeIn("slow");
		$("#enviar_certificadoCotizacion").fadeIn("slow");
		
	}
	if (valor == 2) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoSeguro").fadeIn("slow");
		
	}
	if (valor == 3) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoDebito").fadeIn("slow");
		
	}
	if (valor == 4) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoUPLA").fadeIn("slow");
		
	}
	if (valor == 5) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoPoliza").fadeIn("slow");
		
	}

}


function abrirCertificadoUPLA(){
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte="/static/reportes/CertificadosVehiculos/conozcaASuClienteNaturalVacio.jasper";
	
	if($("#tipo_identificacion_principal").val()=='4')
		pathReporte="/static/reportes/CertificadosVehiculos/conozcaASuClienteJuridicaVacio.jasper";
	
	
	var parametros={"parametros":{"COTIZACION_ID":cotizacion},"pathReporte":pathReporte};
	abrirReporte('POST', '../ReportesController',parametros,"_blank");
}

function abrirCertificadoDebito(){
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte="/static/reportes/CertificadosVehiculos/conozcaASuClienteNaturalVacio.jasper";
	
	if($("#tipo_identificacion_principal").val()=='4')
		pathReporte="/static/reportes/CertificadosVehiculos/conozcaASuClienteJuridicaVacio.jasper";
	
	
	var parametros={"parametros":{"COTIZACION_ID":cotizacion},"pathReporte":pathReporte};
	abrirReporte('POST', '../ReportesController',parametros,"_blank");
}

function abrirCertificado(){
	var cotizacion = $("#cotizacion_id").html().trim();
	
	var parametros={"parametros":{"COTIZACION":cotizacion},"pathReporte":"/static/reportes/CertificadoVhc.jasper"};
	abrirReporte('POST', '../ReportesController',parametros,"_blank");
}

function agregarCorreoCertificado(){
	var correo=$('#correoCertificado').val().trim();
	if(IsEmail(correo)){
	$('#correosCertificado tr:last').after('<tr><td class="correoAEnviarCertificado">'+correo+'</td><td><button type="button" class="btn btn-alert eliminarCorreoEnvioCertificados" >Eliminar</button></td></tr>');
	$('#correoCertificado').val('');
	}
	else
		alert('Ingrese un correo Valido');
	$(".eliminarCorreoEnvioCertificados").click(function(){
		$(this).parent().parent().remove();
	});
	
}

function enviarCertificados(){
	$("#loading_envio_cotizacion").fadeIn('slow');
	var correos=$(".correoAEnviarCertificado");
	var correosEnviar="";
	var cotizacion = $("#cotizacion_id").html().trim();
	var bandera=true;
	
	for (var index=0;index<correos.length;index++){
		if(IsEmail($(correos[index]).html().trim()))
			correosEnviar+=$(correos[index]).html().trim()+",";
		else{
			alert('Ingrese correos validos!');
			bandera=false;
			break;
			}
	}
	
	if(correosEnviar.trim().length==0){
		bandera=false;
		alert('Ingrese un correo!');
	}
	if(bandera)
	$.ajax({
		url : '../CotizacionController',
		data : {
			"correos":correosEnviar,
			"id":cotizacion,
			"tipoConsulta":"enviarCertificado"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			alert('Se enviaron los certificados');
			$("#correos_certificado").dialog('close');
			$("#loading_envio_cotizacion").fadeOut('slow');
			}
	});
}


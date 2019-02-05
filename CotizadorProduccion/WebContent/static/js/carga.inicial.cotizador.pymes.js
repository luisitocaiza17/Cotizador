var editoUbicacion=false;
	
function cargar(){
	//Se calcula la fecha de pago inicial
	
	var today = new Date();

	fechaPrimeraCuota = new Date(today.getTime() + (15 * 24 * 3600 * 1000));

	var dd = fechaPrimeraCuota.getDate();
	var mm = fechaPrimeraCuota.getMonth()+1; //January is 0!
	var yyyy = fechaPrimeraCuota.getFullYear();

	if(dd<10) {
	    dd='0'+dd;
	} 

	if(mm<10) {
	    mm='0'+mm;
	} 

	var fechaCuotaInicial = dd + "/" + mm + "/" + yyyy;
	$("#fechaInicialPagos").val(fechaCuotaInicial);
	
	$(".datosUbicacion ").blur(function(){
		editoUbicacion=true;
	});

	$(".datosUbicacion ").on('select2-blur', function(e) {
		editoUbicacion=true;
	});


		$('#datosAdicionalesJuridica').hide();
		$('#datosAdicionalesNatural').hide();
		$('#fechaNacimientoPN').datepicker();
		$('#fechaNacimientoPJ').datepicker();
		//Seccion Actividad Politica PN
		$('#lblCargoDesempena').hide();
		$('#cargoDesempena').hide();
		$('#lblParentesco').hide();
		$('#parentesco').hide();
		$('#lblCargoDesempenaFamiliar').hide();
		$('#cargoDesempenaFamiliar').hide();
		//Seccion Actividad Financiera PN
		$('#salarioMensual').val(0);
		$('#activosFinanciero').val(0);
		$('#pasivosFinanciero').val(0);
		$('#otrosIngresos').val(0);
		$('#egresos').val(0);
		$('#vNeto').val(0);
		$('#patrimonio').val(0);
		//Seccion Vinculos Existentes PN
		$("#vinculo1").hide();
		$("#vinculo2").hide();
		//Seccion Actividad Politica PJ
		$('#lblCargoDesempenaPJ').hide();
		$('#cargoDesempenaPJ').hide();
		$('#lblParentescoPJ').hide();
		$('#parentescoPJ').hide();
		$('#lblCargoDesempenaFamiliarPJ').hide();
		$('#cargoDesempenaFamiliarPJ').hide();
		//Seccion Actividad Financiera PJ
		$('#salarioMensualPJ').val(0);
		$('#activosFinancieroPJ').val(0);
		$('#pasivosFinancieroPJ').val(0);
		$('#otrosIngresosPJ').val(0);
		$('#egresosPJ').val(0);
		$('#vNetoPJ').val(0);
		$('#patrimonioPJ').val(0);
		//Seccion Vinculos Existentes PJ
		$("#vinculo3").hide();
		$("#vinculo4").hide();
		$("#estado_solicitud_descuento").hide();
		

		$(".parametroCargarEnsurance").change(function (e) {
			var theEvent = e || window.event;
			var target = $(theEvent.target);
			var aux = target.parent().prev().first().text().replace(':', '');
			var nombreParametro = "";
			var parametro = target.val();
			var numero = 0;

			if (aux == 'Placa') {
				nombreParametro = 'placa';
				numero = target.attr("id").substr(9);
			}
			if (aux == 'N&uacute;mero de Chasis') {
				nombreParametro = 'chasis';
				numero = target.attr("id").substr(10);
			}
			if (aux == 'N&uacute;mero de Motor') {
				nombreParametro = 'motor';
				numero = target.attr("id").substr(9);
			}

			$.ajax({
				url : '../ObjetoVehiculoController',
				data : {
					"tipoConsulta" : "cargarPorParametro",
					"parametro" : parametro,
					"nombreParametro" : nombreParametro
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					var vehiculo = data.vehiculo;
					if (vehiculo.codigo != null && vehiculo.codigo != "") {
						$("#color_" + numero).select2("val", vehiculo.color);
						$("#marca_" + numero).select2("val", vehiculo.marca);
						// $(".modelo").select2("val",vehiculo.modelo);

						cargarModelos(vehiculo.modelo, vehiculo.marca, numero);

					}
				}
			});

		});

		var QueryString = function () {
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
		
		$("#codigo_descuento").change(function () {

		 	var tipo = $("#codigo_descuento").val();
		 	var aux = $("#codigo_descuento option:selected").text();
		 	aux = aux.split('(')[1].replace(')', '').replace('%', '');
		 	var min = Number(aux.split('al')[0].trim());
		 	var max = Number(aux.split(' al ')[1].trim());

		 	$("#porcentaje_solicitud_descuento").val(min).attr('min', min).attr('max', max);

		 });  

		$("#wizard").steps({
			headerTag : "h2",
			bodyTag : "section",
			labels: {
		        finish: "Emitir",
		        next: "Siguiente",
		        previous: "Anterior",
		    },
			onStepChanging : function (event, currentIndex, newIndex) {
				//Se desahibilita el control de los campos requeridos cuando se va hacia atras
				if (currentIndex > newIndex){
					return true;
                }
				
				// Eventos al cambiar a la pestana Cliente
				if (newIndex === 1) {
					if (QueryString.id != null && QueryString != '')
						$("#cotizacion_id").text(QueryString.id);
					var cotizacionId = $("#cotizacion_id").text();
					// Validacion para que solo se cree una cotizacion
					if (currentIndex == 0){
						crearCotizacion();
						if ((QueryString.id == null || QueryString == '') && (cotizacionId == null || cotizacionId == '')){
							agregarUbicacion();
							//cargarDatosUbicacion(1, "", "", "", "", "", "", "", "", "", "", "","","","","");
						}
					 	// Cargar descuentos
					}
				}
				
				// Eventos al cambiar a la pestana Productos
				if (newIndex === 2) {
					$(".loading-indicator").show();
					$("#tabbable").hide();
					$.each($(".guardarUbicacion"), function (index) {
						if(editoUbicacion)
							$(this).trigger("click");
					});
					
					//cargarCoberturasPorProducto($("#productosPymes input[type='radio']:checked").val());
				}

				if (newIndex === 3) {
					if ($("#ROBO_DINERO_PERMANENCIA_0_ubicacion_1").val() != undefined)
						return false;
					
					var numeroUbicaciones = $("#numero_total_ubicaciones").val();
					
					for(var i=0; i<=parseInt(numeroUbicaciones); i++){
						var listaCoberturaId = "";
						var listaValor = "";
						var listaObjetoId = "";
						var listaCotizacionId = "";
						
						$(".coberturaUbicacion" + i).each(function(index){
							listaCoberturaId += $(this).attr('cobertura')+",";
							listaValor += $(this).val()+",";
							listaObjetoId += $("#ubicacionId"+i).val()+",";
							listaCotizacionId += $("#cotizacion_id").text()+",";
						});
					}
					
					$.ajax({
						url : '../CotizacionPymesController',
						data : {
							"tipoConsulta" : "grabarCoberturasUbicaciones",
							"listaCoberturaId":listaCoberturaId,
							"listaValor":listaValor,
							"listaCotizacionId":listaCotizacionId,
							"listaObjetoId":listaObjetoId,
							"productoId": $("input[name='productoPymes']:checked").val()
						},
						type : 'post',
						datatype : 'json',
						success : function (data) {
							$("#loading").hide();
							$("#tabbable").show();							
						}
					});					
					console.log(listaCoberturaId);
					console.log(listaValor);
					console.log(listaCotizacionId);
					console.log(listaObjetoId);
				}

				if (newIndex === 4) {
					if( parseInt($("#codigoPagoRegistrado").val()) == -1){
						$("#tabFormasPago").trigger("click");
						$("#msgPopupPagoError").show();
						return false;
					}else{
						$("#msgPopupPagoGrabo").hide();
					}
/*					
					var opcion = $('#tipo_identificacion_principal').val();
						if (opcion == 1 || opcion == 2 || opcion == 3) {
							guardarDatosUPLANatural();
						} else if (opcion == 4) {
							guardarDatosUPLAJuridica();
						}
*/
				}
				return $("#wizard").valid();
			},			
		});
			
		//carga de cotizaciones nuevas
		if(QueryString.id==null||QueryString==''){
			cargaInicial('1');
			cargarTiposIdentificacion('','');
			}
		else{
			cargarPorId(QueryString.id);
			ubicacionCotizacion();
		}

	$(".provincia").change(function(event){
		var theEvent = event || window.event;
		var target = $(theEvent.target);
		var tipo=target.attr("id").replace("provincia_","");
		cargarCiudades("",$(target).val(),tipo);
		cargarCantones("",$(target).val(),tipo);
		
	});
	
	$(".canton").change(function(event){
		var theEvent = event || window.event;
		var target = $(theEvent.target);
		var tipo=target.attr("id").replace("canton_","");
		cargarParroquias("",$(target).val(),tipo);
		
		
	});
	
}
package com.qbe.cotizador.servicios.QBE.agricolaSucre;

public class SolicitudEMIDTO {

	String numeroResolucion;
	String numeroFactura;
	String fechaEmision;
	String poliza;
	String anexo;
	Float montoPago;
	String fechaPago;
	String ordenPago;
	String fechaSiembra;
	String codigoFacilitador;
	double numHectFnanciadas;
	double numHectAseguradas;
	String provincia;
	String canton;
	String parroquia;
	String inversion;
	String cedula;
	String nombres;
	String apellido1; 
	String apellido2;
	String recinto;
	String referencia;
	String lote;
	String terrenoTecnificado;
	String montoAsegurado;
	String variedad;
	Float primaCalculada;
	String tipoEndoso;
	String causa;
	int tipoSubEndoso;//para el caso de aumentos y rebajas... 0) normal 1) cambio de hectareas.
	double numeroHectareasMov;// las hectareas que se aumentaron en ese caso especifico.
	
	
			
	public double getNumeroHectareasMov() {
		return numeroHectareasMov;
	}
	public void setNumeroHectareasMov(double numeroHectareasMov) {
		this.numeroHectareasMov = numeroHectareasMov;
	}
	public int getTipoSubEndoso() {
		return tipoSubEndoso;
	}
	public void setTipoSubEndoso(int tipoSubEndoso) {
		this.tipoSubEndoso = tipoSubEndoso;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getTipoEndoso() {
		return tipoEndoso;
	}
	public void setTipoEndoso(String tipoEndoso) {
		this.tipoEndoso = tipoEndoso;
	}
	public String getNumeroResolucion() {
		return numeroResolucion;
	}
	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getPoliza() {
		return poliza;
	}
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Float getMontoPago() {
		return montoPago;
	}
	public void setMontoPago(Float montoPago) {
		this.montoPago = montoPago;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getOrdenPago() {
		return ordenPago;
	}
	public void setOrdenPago(String ordenPago) {
		this.ordenPago = ordenPago;
	}
	public String getFechaSiembra() {
		return fechaSiembra;
	}
	public void setFechaSiembra(String fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}
	public String getCodigoFacilitador() {
		return codigoFacilitador;
	}
	public void setCodigoFacilitador(String codigoFacilitador) {
		this.codigoFacilitador = codigoFacilitador;
	}
	public double getNumHectFnanciadas() {
		return numHectFnanciadas;
	}
	public void setNumHectFnanciadas(double numHectFnanciadas) {
		this.numHectFnanciadas = numHectFnanciadas;
	}
	public double getNumHectAseguradas() {
		return numHectAseguradas;
	}
	public void setNumHectAseguradas(double numHectAseguradas) {
		this.numHectAseguradas = numHectAseguradas;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public String getParroquia() {
		return parroquia;
	}
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}
	public String getInversion() {
		return inversion;
	}
	public void setInversion(String inversion) {
		this.inversion = inversion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getTerrenoTecnificado() {
		return terrenoTecnificado;
	}
	public void setTerrenoTecnificado(String terrenoTecnificado) {
		this.terrenoTecnificado = terrenoTecnificado;
	}
	public String getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(String montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public String getVariedad() {
		return variedad;
	}
	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}
	public Float getPrimaCalculada() {
		return primaCalculada;
	}
	public void setPrimaCalculada(Float primaCalculada) {
		this.primaCalculada = primaCalculada;
	}
	
}

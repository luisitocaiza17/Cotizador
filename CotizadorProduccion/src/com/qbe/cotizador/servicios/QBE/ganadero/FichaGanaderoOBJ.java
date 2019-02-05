package com.qbe.cotizador.servicios.QBE.ganadero;


public class FichaGanaderoOBJ {

	private String accesoAlAgua;

	private boolean alimentacionCorte;

	private boolean alimentacionPastoreo;

	private boolean alimentacionSogueo;

	private int numerAnimalesVacunosEnFinca;

	private boolean asistenciaVeterinaria;

	private int asistenciaVeterinariaFrecuencia;

	private String veterinarioNombre;

	private String veterinariaTelefono;

	private String ubicacionFincaCantonId;

	private String otrasEnfermedades;

	private int nivelImportanciaEnfermedadFiebreleche;

	private int nivelImportanciaEnfermedadLesionubres;

	private int nivelImportanciaEnfermedadMastisis;

	private int nivelImportanciaEnfermedadNeumonias;

	private int nivelImportanciaEnfermedadOtras;

	private int nivelImportanciaEnfermedadPanadizo;

	private boolean ganaderiEsPrincipalIngresoEconomico;

	private int experienciaGanaderoAnios;

	private int fincaAltitudMSNM;

	private float topografiaHectareasPlana;

	private float topografiaHectareasPendiente1025;

	private float topografiaHectareasPendienteMas25;

	private int muerteTernerasPorAnio;

	private String muerteTernerasCuasa;

	private int muerteTernerosPorAnio;

	private String muerteTernerosCuasa;

	private int muerteToretesPorAnio;

	private String muerteToretesCuasa;

	private int muerteTorosPorAnio;

	private String muerteTorosCuasa;

	private int muerteVacasPorAnio;

	private String muerteVacasCuasa;

	private int muerteVaconasFierroPorAnio;

	private String muerteVaconasFierroCuasa;

	private int muerteVaconasMediaPorAnio;

	private String muerteVaconasMediaCuasa;

	private int muerteVaconasVientrePorAnio;

	private String muerteVaconasVientreCuasa;

	private boolean parasitosExternos;

	private int parasitosExternosTratamientoFrecuencia;

	private String parasitosExternosTratamiento;

	private boolean parasitosInternos;

	private int parasitosInternosTratamientoFrecucia;

	private String parasitosInternosTratamiento;

	private String ubicacionFincaParroquiaId;

	private float pastoHectareas;

	private String pastoObservaciones;

	private String pastoTipo;

	private float pastoVolumenToneladaAnio;

	private int ubicacionFincaProvinciaId;

	//COSTA, SIERRA Y ORIENTE
	private String region;

	//CARNE Y LECHE
	private String tipoProduccion;

	private String direccionCompletaFinca;

	private boolean vacunacionesAftosa;

	private boolean vacunacionesBrucelosis;

	private boolean vacunacionesIbrbvd;

	private boolean vacunacionesLeptospirosis;

	private String vacunacionesOtras;

	private boolean vacunacionesTriple;
	
	private GanadoVacunoOBJ[] listaGanadoVacuno;

	public String getAccesoAlAgua() {
		return accesoAlAgua;
	}

	public void setAccesoAlAgua(String accesoAlAgua) {
		this.accesoAlAgua = accesoAlAgua;
	}

	public boolean isAlimentacionCorte() {
		return alimentacionCorte;
	}

	public void setAlimentacionCorte(boolean alimentacionCorte) {
		this.alimentacionCorte = alimentacionCorte;
	}

	public boolean isAlimentacionPastoreo() {
		return alimentacionPastoreo;
	}

	public void setAlimentacionPastoreo(boolean alimentacionPastoreo) {
		this.alimentacionPastoreo = alimentacionPastoreo;
	}

	public boolean isAlimentacionSogueo() {
		return alimentacionSogueo;
	}

	public void setAlimentacionSogueo(boolean alimentacionSogueo) {
		this.alimentacionSogueo = alimentacionSogueo;
	}

	public int getNumerAnimalesVacunosEnFinca() {
		return numerAnimalesVacunosEnFinca;
	}

	public void setNumerAnimalesVacunosEnFinca(int numerAnimalesVacunosEnFinca) {
		this.numerAnimalesVacunosEnFinca = numerAnimalesVacunosEnFinca;
	}

	public boolean isAsistenciaVeterinaria() {
		return asistenciaVeterinaria;
	}

	public void setAsistenciaVeterinaria(boolean asistenciaVeterinaria) {
		this.asistenciaVeterinaria = asistenciaVeterinaria;
	}

	public int getAsistenciaVeterinariaFrecuencia() {
		return asistenciaVeterinariaFrecuencia;
	}

	public void setAsistenciaVeterinariaFrecuencia(
			int asistenciaVeterinariaFrecuencia) {
		this.asistenciaVeterinariaFrecuencia = asistenciaVeterinariaFrecuencia;
	}

	public String getVeterinarioNombre() {
		return veterinarioNombre;
	}

	public void setVeterinarioNombre(String veterinarioNombre) {
		this.veterinarioNombre = veterinarioNombre;
	}

	public String getVeterinariaTelefono() {
		return veterinariaTelefono;
	}

	public void setVeterinariaTelefono(String veterinariaTelefono) {
		this.veterinariaTelefono = veterinariaTelefono;
	}

	public String getUbicacionFincaCantonId() {
		return ubicacionFincaCantonId;
	}

	public void setUbicacionFincaCantonId(String ubicacionFincaCantonId) {
		this.ubicacionFincaCantonId = ubicacionFincaCantonId;
	}

	public String getOtrasEnfermedades() {
		return otrasEnfermedades;
	}

	public void setOtrasEnfermedades(String otrasEnfermedades) {
		this.otrasEnfermedades = otrasEnfermedades;
	}

	public int getNivelImportanciaEnfermedadFiebreleche() {
		return nivelImportanciaEnfermedadFiebreleche;
	}

	public void setNivelImportanciaEnfermedadFiebreleche(
			int nivelImportanciaEnfermedadFiebreleche) {
		this.nivelImportanciaEnfermedadFiebreleche = nivelImportanciaEnfermedadFiebreleche;
	}

	public int getNivelImportanciaEnfermedadLesionubres() {
		return nivelImportanciaEnfermedadLesionubres;
	}

	public void setNivelImportanciaEnfermedadLesionubres(
			int nivelImportanciaEnfermedadLesionubres) {
		this.nivelImportanciaEnfermedadLesionubres = nivelImportanciaEnfermedadLesionubres;
	}

	public int getNivelImportanciaEnfermedadMastisis() {
		return nivelImportanciaEnfermedadMastisis;
	}

	public void setNivelImportanciaEnfermedadMastisis(
			int nivelImportanciaEnfermedadMastisis) {
		this.nivelImportanciaEnfermedadMastisis = nivelImportanciaEnfermedadMastisis;
	}

	public int getNivelImportanciaEnfermedadNeumonias() {
		return nivelImportanciaEnfermedadNeumonias;
	}

	public void setNivelImportanciaEnfermedadNeumonias(
			int nivelImportanciaEnfermedadNeumonias) {
		this.nivelImportanciaEnfermedadNeumonias = nivelImportanciaEnfermedadNeumonias;
	}

	public int getNivelImportanciaEnfermedadOtras() {
		return nivelImportanciaEnfermedadOtras;
	}

	public void setNivelImportanciaEnfermedadOtras(
			int nivelImportanciaEnfermedadOtras) {
		this.nivelImportanciaEnfermedadOtras = nivelImportanciaEnfermedadOtras;
	}

	public int getNivelImportanciaEnfermedadPanadizo() {
		return nivelImportanciaEnfermedadPanadizo;
	}

	public void setNivelImportanciaEnfermedadPanadizo(
			int nivelImportanciaEnfermedadPanadizo) {
		this.nivelImportanciaEnfermedadPanadizo = nivelImportanciaEnfermedadPanadizo;
	}

	public boolean isGanaderiEsPrincipalIngresoEconomico() {
		return ganaderiEsPrincipalIngresoEconomico;
	}

	public void setGanaderiEsPrincipalIngresoEconomico(
			boolean ganaderiEsPrincipalIngresoEconomico) {
		this.ganaderiEsPrincipalIngresoEconomico = ganaderiEsPrincipalIngresoEconomico;
	}

	public int getExperienciaGanaderoAnios() {
		return experienciaGanaderoAnios;
	}

	public void setExperienciaGanaderoAnios(int experienciaGanaderoAnios) {
		this.experienciaGanaderoAnios = experienciaGanaderoAnios;
	}

	public int getFincaAltitudMSNM() {
		return fincaAltitudMSNM;
	}

	public void setFincaAltitudMSNM(int fincaAltitudMSNM) {
		this.fincaAltitudMSNM = fincaAltitudMSNM;
	}

	public float getTopografiaHectareasPlana() {
		return topografiaHectareasPlana;
	}

	public void setTopografiaHectareasPlana(float topografiaHectareasPlana) {
		this.topografiaHectareasPlana = topografiaHectareasPlana;
	}

	public float getTopografiaHectareasPendiente1025() {
		return topografiaHectareasPendiente1025;
	}

	public void setTopografiaHectareasPendiente1025(
			float topografiaHectareasPendiente1025) {
		this.topografiaHectareasPendiente1025 = topografiaHectareasPendiente1025;
	}

	public float getTopografiaHectareasPendienteMas25() {
		return topografiaHectareasPendienteMas25;
	}

	public void setTopografiaHectareasPendienteMas25(
			float topografiaHectareasPendienteMas25) {
		this.topografiaHectareasPendienteMas25 = topografiaHectareasPendienteMas25;
	}

	public int getMuerteTernerasPorAnio() {
		return muerteTernerasPorAnio;
	}

	public void setMuerteTernerasPorAnio(int muerteTernerasPorAnio) {
		this.muerteTernerasPorAnio = muerteTernerasPorAnio;
	}

	public String getMuerteTernerasCuasa() {
		return muerteTernerasCuasa;
	}

	public void setMuerteTernerasCuasa(String muerteTernerasCuasa) {
		this.muerteTernerasCuasa = muerteTernerasCuasa;
	}

	public int getMuerteTernerosPorAnio() {
		return muerteTernerosPorAnio;
	}

	public void setMuerteTernerosPorAnio(int muerteTernerosPorAnio) {
		this.muerteTernerosPorAnio = muerteTernerosPorAnio;
	}

	public String getMuerteTernerosCuasa() {
		return muerteTernerosCuasa;
	}

	public void setMuerteTernerosCuasa(String muerteTernerosCuasa) {
		this.muerteTernerosCuasa = muerteTernerosCuasa;
	}

	public int getMuerteToretesPorAnio() {
		return muerteToretesPorAnio;
	}

	public void setMuerteToretesPorAnio(int muerteToretesPorAnio) {
		this.muerteToretesPorAnio = muerteToretesPorAnio;
	}

	public String getMuerteToretesCuasa() {
		return muerteToretesCuasa;
	}

	public void setMuerteToretesCuasa(String muerteToretesCuasa) {
		this.muerteToretesCuasa = muerteToretesCuasa;
	}

	public int getMuerteTorosPorAnio() {
		return muerteTorosPorAnio;
	}

	public void setMuerteTorosPorAnio(int muerteTorosPorAnio) {
		this.muerteTorosPorAnio = muerteTorosPorAnio;
	}

	public String getMuerteTorosCuasa() {
		return muerteTorosCuasa;
	}

	public void setMuerteTorosCuasa(String muerteTorosCuasa) {
		this.muerteTorosCuasa = muerteTorosCuasa;
	}

	public int getMuerteVacasPorAnio() {
		return muerteVacasPorAnio;
	}

	public void setMuerteVacasPorAnio(int muerteVacasPorAnio) {
		this.muerteVacasPorAnio = muerteVacasPorAnio;
	}

	public String getMuerteVacasCuasa() {
		return muerteVacasCuasa;
	}

	public void setMuerteVacasCuasa(String muerteVacasCuasa) {
		this.muerteVacasCuasa = muerteVacasCuasa;
	}

	public int getMuerteVaconasFierroPorAnio() {
		return muerteVaconasFierroPorAnio;
	}

	public void setMuerteVaconasFierroPorAnio(int muerteVaconasFierroPorAnio) {
		this.muerteVaconasFierroPorAnio = muerteVaconasFierroPorAnio;
	}

	public String getMuerteVaconasFierroCuasa() {
		return muerteVaconasFierroCuasa;
	}

	public void setMuerteVaconasFierroCuasa(String muerteVaconasFierroCuasa) {
		this.muerteVaconasFierroCuasa = muerteVaconasFierroCuasa;
	}

	public int getMuerteVaconasMediaPorAnio() {
		return muerteVaconasMediaPorAnio;
	}

	public void setMuerteVaconasMediaPorAnio(int muerteVaconasMediaPorAnio) {
		this.muerteVaconasMediaPorAnio = muerteVaconasMediaPorAnio;
	}

	public String getMuerteVaconasMediaCuasa() {
		return muerteVaconasMediaCuasa;
	}

	public void setMuerteVaconasMediaCuasa(String muerteVaconasMediaCuasa) {
		this.muerteVaconasMediaCuasa = muerteVaconasMediaCuasa;
	}

	public int getMuerteVaconasVientrePorAnio() {
		return muerteVaconasVientrePorAnio;
	}

	public void setMuerteVaconasVientrePorAnio(int muerteVaconasVientrePorAnio) {
		this.muerteVaconasVientrePorAnio = muerteVaconasVientrePorAnio;
	}

	public String getMuerteVaconasVientreCuasa() {
		return muerteVaconasVientreCuasa;
	}

	public void setMuerteVaconasVientreCuasa(String muerteVaconasVientreCuasa) {
		this.muerteVaconasVientreCuasa = muerteVaconasVientreCuasa;
	}

	public boolean isParasitosExternos() {
		return parasitosExternos;
	}

	public void setParasitosExternos(boolean parasitosExternos) {
		this.parasitosExternos = parasitosExternos;
	}

	public int getParasitosExternosTratamientoFrecuencia() {
		return parasitosExternosTratamientoFrecuencia;
	}

	public void setParasitosExternosTratamientoFrecuencia(
			int parasitosExternosTratamientoFrecuencia) {
		this.parasitosExternosTratamientoFrecuencia = parasitosExternosTratamientoFrecuencia;
	}

	public String getParasitosExternosTratamiento() {
		return parasitosExternosTratamiento;
	}

	public void setParasitosExternosTratamiento(String parasitosExternosTratamiento) {
		this.parasitosExternosTratamiento = parasitosExternosTratamiento;
	}

	public boolean isParasitosInternos() {
		return parasitosInternos;
	}

	public void setParasitosInternos(boolean parasitosInternos) {
		this.parasitosInternos = parasitosInternos;
	}

	public int getParasitosInternosTratamientoFrecucia() {
		return parasitosInternosTratamientoFrecucia;
	}

	public void setParasitosInternosTratamientoFrecucia(
			int parasitosInternosTratamientoFrecucia) {
		this.parasitosInternosTratamientoFrecucia = parasitosInternosTratamientoFrecucia;
	}

	public String getParasitosInternosTratamiento() {
		return parasitosInternosTratamiento;
	}

	public void setParasitosInternosTratamiento(String parasitosInternosTratamiento) {
		this.parasitosInternosTratamiento = parasitosInternosTratamiento;
	}

	public String getUbicacionFincaParroquiaId() {
		return ubicacionFincaParroquiaId;
	}

	public void setUbicacionFincaParroquiaId(String ubicacionFincaParroquiaId) {
		this.ubicacionFincaParroquiaId = ubicacionFincaParroquiaId;
	}

	public float getPastoHectareas() {
		return pastoHectareas;
	}

	public void setPastoHectareas(float pastoHectareas) {
		this.pastoHectareas = pastoHectareas;
	}

	public String getPastoObservaciones() {
		return pastoObservaciones;
	}

	public void setPastoObservaciones(String pastoObservaciones) {
		this.pastoObservaciones = pastoObservaciones;
	}

	public String getPastoTipo() {
		return pastoTipo;
	}

	public void setPastoTipo(String pastoTipo) {
		this.pastoTipo = pastoTipo;
	}

	public float getPastoVolumenToneladaAnio() {
		return pastoVolumenToneladaAnio;
	}

	public void setPastoVolumenToneladaAnio(float pastoVolumenToneladaAnio) {
		this.pastoVolumenToneladaAnio = pastoVolumenToneladaAnio;
	}

	public int getUbicacionFincaProvinciaId() {
		return ubicacionFincaProvinciaId;
	}

	public void setUbicacionFincaProvinciaId(int ubicacionFincaProvinciaId) {
		this.ubicacionFincaProvinciaId = ubicacionFincaProvinciaId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTipoProduccion() {
		return tipoProduccion;
	}

	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

	public String getDireccionCompletaFinca() {
		return direccionCompletaFinca;
	}

	public void setDireccionCompletaFinca(String direccionCompletaFinca) {
		this.direccionCompletaFinca = direccionCompletaFinca;
	}

	public boolean isVacunacionesAftosa() {
		return vacunacionesAftosa;
	}

	public void setVacunacionesAftosa(boolean vacunacionesAftosa) {
		this.vacunacionesAftosa = vacunacionesAftosa;
	}

	public boolean isVacunacionesBrucelosis() {
		return vacunacionesBrucelosis;
	}

	public void setVacunacionesBrucelosis(boolean vacunacionesBrucelosis) {
		this.vacunacionesBrucelosis = vacunacionesBrucelosis;
	}

	public boolean isVacunacionesIbrbvd() {
		return vacunacionesIbrbvd;
	}

	public void setVacunacionesIbrbvd(boolean vacunacionesIbrbvd) {
		this.vacunacionesIbrbvd = vacunacionesIbrbvd;
	}

	public boolean isVacunacionesLeptospirosis() {
		return vacunacionesLeptospirosis;
	}

	public void setVacunacionesLeptospirosis(boolean vacunacionesLeptospirosis) {
		this.vacunacionesLeptospirosis = vacunacionesLeptospirosis;
	}

	public String getVacunacionesOtras() {
		return vacunacionesOtras;
	}

	public void setVacunacionesOtras(String vacunacionesOtras) {
		this.vacunacionesOtras = vacunacionesOtras;
	}

	public boolean isVacunacionesTriple() {
		return vacunacionesTriple;
	}

	public void setVacunacionesTriple(boolean vacunacionesTriple) {
		this.vacunacionesTriple = vacunacionesTriple;
	}

	public GanadoVacunoOBJ[] getListaGanadoVacuno() {
		return listaGanadoVacuno;
	}

	public void setListaGanadoVacuno(GanadoVacunoOBJ[] listaGanadoVacuno) {
		this.listaGanadoVacuno = listaGanadoVacuno;
	}
	
	
}

package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the OBJETO_GANADERO database table.
 * 
 */
@Entity
@Table(name="OBJETO_GANADERO")
@NamedQueries({
	@NamedQuery(name="ObjetoGanadero.buscarPorId", query="SELECT c FROM ObjetoGanadero c where c.id = :id"),
	@NamedQuery(name="ObjetoGanadero.buscarObjetoGanaderoPorCotizacionDetalle", query="SELECT c FROM ObjetoGanadero c where c.id = :id")
	
})
public class ObjetoGanadero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="ACCESO_AL_AGUA")
	private String accesoAlAgua;

	@Column(name="ALIMENTACION_CORTE")
	private String alimentacionCorte;

	@Column(name="ALIMENTACION_OTROS")
	private String alimentacionOtros;

	@Column(name="ALIMENTACION_PASTOREO")
	private String alimentacionPastoreo;

	@Column(name="ALIMENTACION_SOGUEO")
	private String alimentacionSogueo;

	@Column(name="ANIMALES_VACUNOS")
	private int animalesVacunos;

	@Column(name="ASISTENCIA_VETERINARIA")
	private String asistenciaVeterinaria;

	@Column(name="ASISTENCIA_VETERINARIA_FREC")
	private String asistenciaVeterinariaFrec;

	@Column(name="ASISTENCIA_VETERINARIA_PROF")
	private String asistenciaVeterinariaProf;

	@Column(name="ASISTENCIA_VETERINARIA_TELE")
	private String asistenciaVeterinariaTele;

	private BigInteger cantonid;

	@Column(name="ENFERMEDAD_CUAL")
	private String enfermedadCual;

	@Column(name="ENFERMEDAD_FIEBRELECHE")
	private int enfermedadFiebreleche;

	@Column(name="ENFERMEDAD_LESIONUBRES")
	private int enfermedadLesionubres;

	@Column(name="ENFERMEDAD_MASTISIS")
	private int enfermedadMastisis;

	@Column(name="ENFERMEDAD_NEUMONIAS")
	private int enfermedadNeumonias;

	@Column(name="ENFERMEDAD_OTRAS")
	private int enfermedadOtras;

	@Column(name="ENFERMEDAD_PANADIZO")
	private int enfermedadPanadizo;

	@Column(name="ESPRINCIPAL_INGRESO")
	private String esprincipalIngreso;

	@Column(name="EXPERIENCIA_GANADERO_ANIOS")
	private int experienciaGanaderoAnios;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name="FINCA_ALTITUD")
	private int fincaAltitud;

	@Column(name="FINCA_TOPOGRAFIA1")
	private float fincaTopografia1;

	@Column(name="FINCA_TOPOGRAFIA2")
	private float fincaTopografia2;

	@Column(name="FINCA_TOPOGRAFIA3")
	private float fincaTopografia3;

	@Column(name="MORTALIDAD_TERNERAS")
	private int mortalidadTerneras;

	@Column(name="MORTALIDAD_TERNERAS_CAUSA")
	private String mortalidadTernerasCausa;

	@Column(name="MORTALIDAD_TERNEROS")
	private int mortalidadTerneros;

	@Column(name="MORTALIDAD_TERNEROS_CAUSA")
	private String mortalidadTernerosCausa;

	@Column(name="MORTALIDAD_TORETES")
	private int mortalidadToretes;

	@Column(name="MORTALIDAD_TORETES_CAUSA")
	private String mortalidadToretesCausa;

	@Column(name="MORTALIDAD_TOROS")
	private int mortalidadToros;

	@Column(name="MORTALIDAD_TOROS_CAUSA")
	private String mortalidadTorosCausa;

	@Column(name="MORTALIDAD_VACAS")
	private int mortalidadVacas;

	@Column(name="MORTALIDAD_VACAS_CAUSA")
	private String mortalidadVacasCausa;

	@Column(name="MORTALIDAD_VACONASF")
	private int mortalidadVaconasf;

	@Column(name="MORTALIDAD_VACONASF_CAUSA")
	private String mortalidadVaconasfCausa;

	@Column(name="MORTALIDAD_VACONASM")
	private int mortalidadVaconasm;

	@Column(name="MORTALIDAD_VACONASM_CAUSA")
	private String mortalidadVaconasmCausa;

	@Column(name="MORTALIDAD_VACONASV")
	private int mortalidadVaconasv;

	@Column(name="MORTALIDAD_VACONASV_CAUSA")
	private String mortalidadVaconasvCausa;

	private int origen;

	@Column(name="PARASITOS_EXTERNOS")
	private String parasitosExternos;

	@Column(name="PARASITOS_EXTERNOS_FRECU")
	private String parasitosExternosFrecu;

	@Column(name="PARASITOS_EXTERNOS_TRATA")
	private String parasitosExternosTrata;

	@Column(name="PARASITOS_INTERNOS")
	private String parasitosInternos;

	@Column(name="PARASITOS_INTERNOS_FRECU")
	private String parasitosInternosFrecu;

	@Column(name="PARASITOS_INTERNOS_TRATA")
	private String parasitosInternosTrata;

	private BigInteger parroquiaid;

	@Column(name="PASTO_HECTAREAS")
	private float pastoHectareas;

	@Column(name="PASTO_OBSERVACIONES")
	private String pastoObservaciones;

	@Column(name="PASTO_TIPOID")
	private String pastoTipoid;

	@Column(name="PASTO_VOLUMNEANIO")
	private float pastoVolumneanio;

	private BigInteger provinciaid;

	private BigInteger puntoventaid;

	private String recinto;

	private String region;

	@Column(name="TIPO_PRODUCCION")
	private String tipoProduccion;

	private String ubicacion;

	private BigInteger usuarioid;

	@Column(name="VACUNACIONES_AFTOSA")
	private String vacunacionesAftosa;

	@Column(name="VACUNACIONES_BRUCELOSIS")
	private String vacunacionesBrucelosis;

	@Column(name="VACUNACIONES_IBRBVD")
	private String vacunacionesIbrbvd;

	@Column(name="VACUNACIONES_LEPTOSPIROSIS")
	private String vacunacionesLeptospirosis;

	@Column(name="VACUNACIONES_OTRAS")
	private String vacunacionesOtras;

	@Column(name="VACUNACIONES_TRIPLE")
	private String vacunacionesTriple;

	public ObjetoGanadero() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccesoAlAgua() {
		return this.accesoAlAgua;
	}

	public void setAccesoAlAgua(String accesoAlAgua) {
		this.accesoAlAgua = accesoAlAgua;
	}

	public String getAlimentacionCorte() {
		return this.alimentacionCorte;
	}

	public void setAlimentacionCorte(String alimentacionCorte) {
		this.alimentacionCorte = alimentacionCorte;
	}

	public String getAlimentacionOtros() {
		return this.alimentacionOtros;
	}

	public void setAlimentacionOtros(String alimentacionOtros) {
		this.alimentacionOtros = alimentacionOtros;
	}

	public String getAlimentacionPastoreo() {
		return this.alimentacionPastoreo;
	}

	public void setAlimentacionPastoreo(String alimentacionPastoreo) {
		this.alimentacionPastoreo = alimentacionPastoreo;
	}

	public String getAlimentacionSogueo() {
		return this.alimentacionSogueo;
	}

	public void setAlimentacionSogueo(String alimentacionSogueo) {
		this.alimentacionSogueo = alimentacionSogueo;
	}

	public int getAnimalesVacunos() {
		return this.animalesVacunos;
	}

	public void setAnimalesVacunos(int animalesVacunos) {
		this.animalesVacunos = animalesVacunos;
	}

	public String getAsistenciaVeterinaria() {
		return this.asistenciaVeterinaria;
	}

	public void setAsistenciaVeterinaria(String asistenciaVeterinaria) {
		this.asistenciaVeterinaria = asistenciaVeterinaria;
	}

	public String getAsistenciaVeterinariaFrec() {
		return this.asistenciaVeterinariaFrec;
	}

	public void setAsistenciaVeterinariaFrec(String asistenciaVeterinariaFrec) {
		this.asistenciaVeterinariaFrec = asistenciaVeterinariaFrec;
	}

	public String getAsistenciaVeterinariaProf() {
		return this.asistenciaVeterinariaProf;
	}

	public void setAsistenciaVeterinariaProf(String asistenciaVeterinariaProf) {
		this.asistenciaVeterinariaProf = asistenciaVeterinariaProf;
	}

	public String getAsistenciaVeterinariaTele() {
		return this.asistenciaVeterinariaTele;
	}

	public void setAsistenciaVeterinariaTele(String asistenciaVeterinariaTele) {
		this.asistenciaVeterinariaTele = asistenciaVeterinariaTele;
	}

	public BigInteger getCantonid() {
		return this.cantonid;
	}

	public void setCantonid(BigInteger cantonid) {
		this.cantonid = cantonid;
	}

	public String getEnfermedadCual() {
		return this.enfermedadCual;
	}

	public void setEnfermedadCual(String enfermedadCual) {
		this.enfermedadCual = enfermedadCual;
	}

	public int getEnfermedadFiebreleche() {
		return this.enfermedadFiebreleche;
	}

	public void setEnfermedadFiebreleche(int enfermedadFiebreleche) {
		this.enfermedadFiebreleche = enfermedadFiebreleche;
	}

	public int getEnfermedadLesionubres() {
		return this.enfermedadLesionubres;
	}

	public void setEnfermedadLesionubres(int enfermedadLesionubres) {
		this.enfermedadLesionubres = enfermedadLesionubres;
	}

	public int getEnfermedadMastisis() {
		return this.enfermedadMastisis;
	}

	public void setEnfermedadMastisis(int enfermedadMastisis) {
		this.enfermedadMastisis = enfermedadMastisis;
	}

	public int getEnfermedadNeumonias() {
		return this.enfermedadNeumonias;
	}

	public void setEnfermedadNeumonias(int enfermedadNeumonias) {
		this.enfermedadNeumonias = enfermedadNeumonias;
	}

	public int getEnfermedadOtras() {
		return this.enfermedadOtras;
	}

	public void setEnfermedadOtras(int enfermedadOtras) {
		this.enfermedadOtras = enfermedadOtras;
	}

	public int getEnfermedadPanadizo() {
		return this.enfermedadPanadizo;
	}

	public void setEnfermedadPanadizo(int enfermedadPanadizo) {
		this.enfermedadPanadizo = enfermedadPanadizo;
	}

	public String getEsprincipalIngreso() {
		return this.esprincipalIngreso;
	}

	public void setEsprincipalIngreso(String esprincipalIngreso) {
		this.esprincipalIngreso = esprincipalIngreso;
	}

	public int getExperienciaGanaderoAnios() {
		return this.experienciaGanaderoAnios;
	}

	public void setExperienciaGanaderoAnios(int experienciaGanaderoAnios) {
		this.experienciaGanaderoAnios = experienciaGanaderoAnios;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getFincaAltitud() {
		return this.fincaAltitud;
	}

	public void setFincaAltitud(int fincaAltitud) {
		this.fincaAltitud = fincaAltitud;
	}

	public float getFincaTopografia1() {
		return this.fincaTopografia1;
	}

	public void setFincaTopografia1(float fincaTopografia1) {
		this.fincaTopografia1 = fincaTopografia1;
	}

	public float getFincaTopografia2() {
		return this.fincaTopografia2;
	}

	public void setFincaTopografia2(float fincaTopografia2) {
		this.fincaTopografia2 = fincaTopografia2;
	}

	public float getFincaTopografia3() {
		return this.fincaTopografia3;
	}

	public void setFincaTopografia3(float fincaTopografia3) {
		this.fincaTopografia3 = fincaTopografia3;
	}

	public int getMortalidadTerneras() {
		return this.mortalidadTerneras;
	}

	public void setMortalidadTerneras(int mortalidadTerneras) {
		this.mortalidadTerneras = mortalidadTerneras;
	}

	public String getMortalidadTernerasCausa() {
		return this.mortalidadTernerasCausa;
	}

	public void setMortalidadTernerasCausa(String mortalidadTernerasCausa) {
		this.mortalidadTernerasCausa = mortalidadTernerasCausa;
	}

	public int getMortalidadTerneros() {
		return this.mortalidadTerneros;
	}

	public void setMortalidadTerneros(int mortalidadTerneros) {
		this.mortalidadTerneros = mortalidadTerneros;
	}

	public String getMortalidadTernerosCausa() {
		return this.mortalidadTernerosCausa;
	}

	public void setMortalidadTernerosCausa(String mortalidadTernerosCausa) {
		this.mortalidadTernerosCausa = mortalidadTernerosCausa;
	}

	public int getMortalidadToretes() {
		return this.mortalidadToretes;
	}

	public void setMortalidadToretes(int mortalidadToretes) {
		this.mortalidadToretes = mortalidadToretes;
	}

	public String getMortalidadToretesCausa() {
		return this.mortalidadToretesCausa;
	}

	public void setMortalidadToretesCausa(String mortalidadToretesCausa) {
		this.mortalidadToretesCausa = mortalidadToretesCausa;
	}

	public int getMortalidadToros() {
		return this.mortalidadToros;
	}

	public void setMortalidadToros(int mortalidadToros) {
		this.mortalidadToros = mortalidadToros;
	}

	public String getMortalidadTorosCausa() {
		return this.mortalidadTorosCausa;
	}

	public void setMortalidadTorosCausa(String mortalidadTorosCausa) {
		this.mortalidadTorosCausa = mortalidadTorosCausa;
	}

	public int getMortalidadVacas() {
		return this.mortalidadVacas;
	}

	public void setMortalidadVacas(int mortalidadVacas) {
		this.mortalidadVacas = mortalidadVacas;
	}

	public String getMortalidadVacasCausa() {
		return this.mortalidadVacasCausa;
	}

	public void setMortalidadVacasCausa(String mortalidadVacasCausa) {
		this.mortalidadVacasCausa = mortalidadVacasCausa;
	}

	public int getMortalidadVaconasf() {
		return this.mortalidadVaconasf;
	}

	public void setMortalidadVaconasf(int mortalidadVaconasf) {
		this.mortalidadVaconasf = mortalidadVaconasf;
	}

	public String getMortalidadVaconasfCausa() {
		return this.mortalidadVaconasfCausa;
	}

	public void setMortalidadVaconasfCausa(String mortalidadVaconasfCausa) {
		this.mortalidadVaconasfCausa = mortalidadVaconasfCausa;
	}

	public int getMortalidadVaconasm() {
		return this.mortalidadVaconasm;
	}

	public void setMortalidadVaconasm(int mortalidadVaconasm) {
		this.mortalidadVaconasm = mortalidadVaconasm;
	}

	public String getMortalidadVaconasmCausa() {
		return this.mortalidadVaconasmCausa;
	}

	public void setMortalidadVaconasmCausa(String mortalidadVaconasmCausa) {
		this.mortalidadVaconasmCausa = mortalidadVaconasmCausa;
	}

	public int getMortalidadVaconasv() {
		return this.mortalidadVaconasv;
	}

	public void setMortalidadVaconasv(int mortalidadVaconasv) {
		this.mortalidadVaconasv = mortalidadVaconasv;
	}

	public String getMortalidadVaconasvCausa() {
		return this.mortalidadVaconasvCausa;
	}

	public void setMortalidadVaconasvCausa(String mortalidadVaconasvCausa) {
		this.mortalidadVaconasvCausa = mortalidadVaconasvCausa;
	}

	public int getOrigen() {
		return this.origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public String getParasitosExternos() {
		return this.parasitosExternos;
	}

	public void setParasitosExternos(String parasitosExternos) {
		this.parasitosExternos = parasitosExternos;
	}

	public String getParasitosExternosFrecu() {
		return this.parasitosExternosFrecu;
	}

	public void setParasitosExternosFrecu(String parasitosExternosFrecu) {
		this.parasitosExternosFrecu = parasitosExternosFrecu;
	}

	public String getParasitosExternosTrata() {
		return this.parasitosExternosTrata;
	}

	public void setParasitosExternosTrata(String parasitosExternosTrata) {
		this.parasitosExternosTrata = parasitosExternosTrata;
	}

	public String getParasitosInternos() {
		return this.parasitosInternos;
	}

	public void setParasitosInternos(String parasitosInternos) {
		this.parasitosInternos = parasitosInternos;
	}

	public String getParasitosInternosFrecu() {
		return this.parasitosInternosFrecu;
	}

	public void setParasitosInternosFrecu(String parasitosInternosFrecu) {
		this.parasitosInternosFrecu = parasitosInternosFrecu;
	}

	public String getParasitosInternosTrata() {
		return this.parasitosInternosTrata;
	}

	public void setParasitosInternosTrata(String parasitosInternosTrata) {
		this.parasitosInternosTrata = parasitosInternosTrata;
	}

	public BigInteger getParroquiaid() {
		return this.parroquiaid;
	}

	public void setParroquiaid(BigInteger parroquiaid) {
		this.parroquiaid = parroquiaid;
	}

	public float getPastoHectareas() {
		return this.pastoHectareas;
	}

	public void setPastoHectareas(float pastoHectareas) {
		this.pastoHectareas = pastoHectareas;
	}

	public String getPastoObservaciones() {
		return this.pastoObservaciones;
	}

	public void setPastoObservaciones(String pastoObservaciones) {
		this.pastoObservaciones = pastoObservaciones;
	}

	public String getPastoTipoid() {
		return this.pastoTipoid;
	}

	public void setPastoTipoid(String pastoTipoid) {
		this.pastoTipoid = pastoTipoid;
	}

	public float getPastoVolumneanio() {
		return this.pastoVolumneanio;
	}

	public void setPastoVolumneanio(float pastoVolumneanio) {
		this.pastoVolumneanio = pastoVolumneanio;
	}

	public BigInteger getProvinciaid() {
		return this.provinciaid;
	}

	public void setProvinciaid(BigInteger provinciaid) {
		this.provinciaid = provinciaid;
	}

	public BigInteger getPuntoventaid() {
		return this.puntoventaid;
	}

	public void setPuntoventaid(BigInteger puntoventaid) {
		this.puntoventaid = puntoventaid;
	}

	public String getRecinto() {
		return this.recinto;
	}

	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTipoProduccion() {
		return this.tipoProduccion;
	}

	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public BigInteger getUsuarioid() {
		return this.usuarioid;
	}

	public void setUsuarioid(BigInteger usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getVacunacionesAftosa() {
		return this.vacunacionesAftosa;
	}

	public void setVacunacionesAftosa(String vacunacionesAftosa) {
		this.vacunacionesAftosa = vacunacionesAftosa;
	}

	public String getVacunacionesBrucelosis() {
		return this.vacunacionesBrucelosis;
	}

	public void setVacunacionesBrucelosis(String vacunacionesBrucelosis) {
		this.vacunacionesBrucelosis = vacunacionesBrucelosis;
	}

	public String getVacunacionesIbrbvd() {
		return this.vacunacionesIbrbvd;
	}

	public void setVacunacionesIbrbvd(String vacunacionesIbrbvd) {
		this.vacunacionesIbrbvd = vacunacionesIbrbvd;
	}

	public String getVacunacionesLeptospirosis() {
		return this.vacunacionesLeptospirosis;
	}

	public void setVacunacionesLeptospirosis(String vacunacionesLeptospirosis) {
		this.vacunacionesLeptospirosis = vacunacionesLeptospirosis;
	}

	public String getVacunacionesOtras() {
		return this.vacunacionesOtras;
	}

	public void setVacunacionesOtras(String vacunacionesOtras) {
		this.vacunacionesOtras = vacunacionesOtras;
	}

	public String getVacunacionesTriple() {
		return this.vacunacionesTriple;
	}

	public void setVacunacionesTriple(String vacunacionesTriple) {
		this.vacunacionesTriple = vacunacionesTriple;
	}

}
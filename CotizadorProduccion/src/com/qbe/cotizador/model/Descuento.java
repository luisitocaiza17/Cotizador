package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the DESCUENTO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Descuento.buscarPorId", query="SELECT c FROM Descuento c where c.id = :id"),
	@NamedQuery(name="Descuento.buscarPorGrupoAutorizacion", query="SELECT c FROM Descuento c where c.grupoAutorizacion =:grupoAutorizacion"),
	@NamedQuery(name="Descuento.buscarActivos", query="SELECT c FROM Descuento c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Descuento.buscarTodos", query="SELECT c FROM Descuento c")
})
public class Descuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="cargo_autoriza")
	private String cargoAutoriza;

	private String descripcion;

	private String nombre;

	@Column(name="rango_final")
	private String rangoFinal;

	@Column(name="rango_inicial")
    private String rangoInicial;

	@Column(name="aplica_tasa_minima")
    private boolean aplicaTasaMinima;

  //bi-directional many-to-many association to Cargo
	@ManyToMany
	@JoinTable(
		name="DESCUENTO_X_CARGO"
		, joinColumns={
			@JoinColumn(name="descuento_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cargo_id")
			}
		)
	private List<Cargo> cargos;

	//bi-directional many-to-one association to GrupoAutorizacion
	@ManyToOne
	@JoinColumn(name="grupo_autorizacion_id")
	private GrupoAutorizacion grupoAutorizacion;

	//bi-directional many-to-one association to SolicitudDescuento
	@OneToMany(mappedBy="descuento")
	private List<SolicitudDescuento> solicitudDescuentos;

	public Descuento() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getCargoAutoriza() {
		return this.cargoAutoriza;
	}

	public void setCargoAutoriza(String cargoAutoriza) {
		this.cargoAutoriza = cargoAutoriza;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRangoFinal() {
		return this.rangoFinal;
	}

	public void setRangoFinal(String rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	public String getRangoInicial() {
		return this.rangoInicial;
	}

	public void setRangoInicial(String rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	public List<Cargo> getCargos() {
		return this.cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public GrupoAutorizacion getGrupoAutorizacion() {
		return this.grupoAutorizacion;
	}

	public void setGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		this.grupoAutorizacion = grupoAutorizacion;
	}

	public List<SolicitudDescuento> getSolicitudDescuentos() {
		return this.solicitudDescuentos;
	}

	public void setSolicitudDescuentos(List<SolicitudDescuento> solicitudDescuentos) {
		this.solicitudDescuentos = solicitudDescuentos;
	}

    public boolean getAplicaTasaMinima() {
    return aplicaTasaMinima;
  }

  public void setAplicaTasaMinima(boolean aplicaTasaMinima) {
    this.aplicaTasaMinima = aplicaTasaMinima;
  }


	public SolicitudDescuento addSolicitudDescuento(SolicitudDescuento solicitudDescuento) {
		getSolicitudDescuentos().add(solicitudDescuento);
		solicitudDescuento.setDescuento(this);

		return solicitudDescuento;
	}

	public SolicitudDescuento removeSolicitudDescuento(SolicitudDescuento solicitudDescuento) {
		getSolicitudDescuentos().remove(solicitudDescuento);
		solicitudDescuento.setDescuento(null);

		return solicitudDescuento;
	}

}
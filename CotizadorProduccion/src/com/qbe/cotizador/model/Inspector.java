package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the INSPECTOR database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Inspector.buscarPorId", query="SELECT c FROM Inspector c where c.id = :id"),
	@NamedQuery(name="Inspector.buscarTodos", query="SELECT c FROM Inspector c"),
	@NamedQuery(name="Inspector.buscarPorTipo", query="SELECT c FROM Inspector c WHERE c.tipoInspector =:tipoInspector"),
	@NamedQuery(name="Inspector.buscarPorUsuario", query="SELECT c FROM Inspector c where c.usuario = :usuario")
})
public class Inspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	@Column(name="valor_km")
	private double valorKm;
	
	@Column(name="mail1")
	private String mail1;
	
	@Column(name="mail2")
	private String mail2;
	
	@Column(name="mail3")
	private String mail3;
	
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to DistanciaInspector
	@OneToMany(mappedBy="inspector")
	private List<DistanciaInspector> distanciaInspectors;

	//bi-directional many-to-one association to TipoInspector
	@ManyToOne
	@JoinColumn(name="tipo_inspector_id")
	private TipoInspector tipoInspector;

	//bi-directional many-to-one association to SolicitudInspeccion
	@OneToMany(mappedBy="inspector")
	private List<SolicitudInspeccion> solicitudInspeccions;

	//bi-directional many-to-one association to Sucursal
		@ManyToOne
		@JoinColumn(name="sucursal_id")
		private Sucursal sucursal;

	public Inspector() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValorKm() {
		return this.valorKm;
	}

	public void setValorKm(double valorKm) {
		this.valorKm = valorKm;
	}

	public List<DistanciaInspector> getDistanciaInspectors() {
		return this.distanciaInspectors;
	}

	public void setDistanciaInspectors(List<DistanciaInspector> distanciaInspectors) {
		this.distanciaInspectors = distanciaInspectors;
	}

	public DistanciaInspector addDistanciaInspector(DistanciaInspector distanciaInspector) {
		getDistanciaInspectors().add(distanciaInspector);
		distanciaInspector.setInspector(this);

		return distanciaInspector;
	}

	public DistanciaInspector removeDistanciaInspector(DistanciaInspector distanciaInspector) {
		getDistanciaInspectors().remove(distanciaInspector);
		distanciaInspector.setInspector(null);

		return distanciaInspector;
	}

	public TipoInspector getTipoInspector() {
		return this.tipoInspector;
	}

	public void setTipoInspector(TipoInspector tipoInspector) {
		this.tipoInspector = tipoInspector;
	}

	public List<SolicitudInspeccion> getSolicitudInspeccions() {
		return this.solicitudInspeccions;
	}

	public void setSolicitudInspeccions(List<SolicitudInspeccion> solicitudInspeccions) {
		this.solicitudInspeccions = solicitudInspeccions;
	}

	public SolicitudInspeccion addSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().add(solicitudInspeccion);
		solicitudInspeccion.setInspector(this);

		return solicitudInspeccion;
	}

	public SolicitudInspeccion removeSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().remove(solicitudInspeccion);
		solicitudInspeccion.setInspector(null);

		return solicitudInspeccion;
	}

	public String getMail1() {
		return this.mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return this.mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}
	
	public String getMail3() {
		return this.mail3;
	}

	public void setMail3(String mail3) {
		this.mail3 = mail3;
	}
	
	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
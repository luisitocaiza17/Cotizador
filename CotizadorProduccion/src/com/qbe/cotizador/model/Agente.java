package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the AGENTE database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Agente.buscarPorId", query="SELECT c FROM Agente c where c.id = :id"),
	@NamedQuery(name="Agente.buscarPorEntidadId", query="SELECT c FROM Agente c where c.entidad = :entidad"),
	@NamedQuery(name="Agente.buscarActivos", query="SELECT c FROM Agente c where c.activo = :valorActivo ORDER BY c.entidad.nombreCompleto ASC"),
	@NamedQuery(name="Agente.buscarTodos", query="SELECT c FROM Agente c")
})
public class Agente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="age_ensurance")
	private String ageEnsurance;

	@Column(name="comision_pymes")
	private double comisionPymes;

	@Column(name="comision_soat")
	private double comisionSoat;

	@Column(name="comision_variable")
	private boolean comisionVariable;

	@Column(name="comision_vh")
	private double comisionVh;

	private double comision1;

	private double comision2;

	private double comision3;

	private String credencial;

	@Column(name="ramo_multiriesgo")
	private boolean ramoMultiriesgo;

	//bi-directional many-to-one association to Entidad
	@ManyToOne
	private Entidad entidad;

	//bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(
		name="AGENTE_X_PRODUCTO"
		, joinColumns={
			@JoinColumn(name="agente_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="producto_id")
			}
		)
	private List<Producto> productos;

	public Agente() {
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

	public String getAgeEnsurance() {
		return this.ageEnsurance;
	}

	public void setAgeEnsurance(String ageEnsurance) {
		this.ageEnsurance = ageEnsurance;
	}

	public double getComisionPymes() {
		return this.comisionPymes;
	}

	public void setComisionPymes(double comisionPymes) {
		this.comisionPymes = comisionPymes;
	}

	public double getComisionSoat() {
		return this.comisionSoat;
	}

	public void setComisionSoat(double comisionSoat) {
		this.comisionSoat = comisionSoat;
	}

	public boolean getComisionVariable() {
		return this.comisionVariable;
	}

	public void setComisionVariable(boolean comisionVariable) {
		this.comisionVariable = comisionVariable;
	}

	public double getComisionVh() {
		return this.comisionVh;
	}

	public void setComisionVh(double comisionVh) {
		this.comisionVh = comisionVh;
	}

	public double getComision1() {
		return this.comision1;
	}

	public void setComision1(double comision1) {
		this.comision1 = comision1;
	}

	public double getComision2() {
		return this.comision2;
	}

	public void setComision2(double comision2) {
		this.comision2 = comision2;
	}

	public double getComision3() {
		return this.comision3;
	}

	public void setComision3(double comision3) {
		this.comision3 = comision3;
	}

	public String getCredencial() {
		return this.credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public boolean getRamoMultiriesgo() {
		return this.ramoMultiriesgo;
	}

	public void setRamoMultiriesgo(boolean ramoMultiriesgo) {
		this.ramoMultiriesgo = ramoMultiriesgo;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
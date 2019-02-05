package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the CARGO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cargo.buscarPorId", query="SELECT c FROM Cargo c where c.id=:id"),
	@NamedQuery(name="Cargo.buscarActivos", query="SELECT c FROM Cargo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Cargo.buscarPorCargoGenerico", query="SELECT c FROM Cargo c WHERE c.activo ='1' and c.nombreGenerico=:cargoGenerico"),
	@NamedQuery(name="Cargo.buscarPorCargo", query="SELECT c FROM Cargo c WHERE c.activo ='1' and c.nombre=:cargo"),
	@NamedQuery(name="Cargo.buscarTodos", query="SELECT c FROM Cargo c")
})
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="car_ensurance")
	private String carEnsurance;

	private String nombre;

	@Column(name="nombre_generico")
	private String nombreGenerico;

	//bi-directional many-to-many association to Descuento
	@ManyToMany(mappedBy="cargos")
	private List<Descuento> descuentos;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="cargo")
	private List<Empleado> empleados;

	public Cargo() {
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

	public String getCarEnsurance() {
		return this.carEnsurance;
	}

	public void setCarEnsurance(String carEnsurance) {
		this.carEnsurance = carEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreGenerico() {
		return this.nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public List<Descuento> getDescuentos() {
		return this.descuentos;
	}

	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setCargo(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setCargo(null);

		return empleado;
	}

}
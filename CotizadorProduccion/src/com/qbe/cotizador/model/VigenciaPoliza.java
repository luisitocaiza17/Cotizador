package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the VIGENCIA_POLIZA database table.
 * 
 */
@Entity
@Table(name="VIGENCIA_POLIZA")
@NamedQueries({
	@NamedQuery(name="VigenciaPoliza.buscarPorId", query="SELECT c FROM VigenciaPoliza c where c.id = :id"),
	@NamedQuery(name="VigenciaPoliza.buscarTodos", query="SELECT c FROM VigenciaPoliza c"),
	@NamedQuery(name="VigenciaPoliza.buscarActivos", query="SELECT c FROM VigenciaPoliza c WHERE c.activo =:valorActivo")
})
public class VigenciaPoliza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	private BigInteger valor;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="vigenciaPoliza")
	private List<Cotizacion> cotizacions;

	public VigenciaPoliza() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getValor() {
		return this.valor;
	}

	public void setValor(BigInteger valor) {
		this.valor = valor;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setVigenciaPoliza(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setVigenciaPoliza(null);

		return cotizacion;
	}

}
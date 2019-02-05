package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_PARAMETRO_X_GRUPO_POR_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="PYME_PARAMETRO_X_GRUPO_POR_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="PymeParametroXGrupoPorProducto.buscarTodos", query="SELECT p FROM PymeParametroXGrupoPorProducto p"),
	@NamedQuery(name="PymeParametroXGrupoPorProducto.buscarPorId", query="SELECT c FROM PymeParametroXGrupoPorProducto c WHERE c.parametroGrupoProductoId=:id"),
	@NamedQuery(name="PymeParametroXGrupoPorProducto.buscarPorGrupoPorProductoId", query="SELECT c FROM PymeParametroXGrupoPorProducto c WHERE c.grupoPorProductoId=:idGrupo")
})
public class PymeParametroXGrupoPorProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PARAMETRO_GRUPO_PRODUCTO_ID")
	private BigInteger parametroGrupoProductoId;

	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;

	@Column(name="MOSTRAR_MAQUINARIA")
	private Boolean mostrarMaquinaria;

	@Column(name="MOSTRAR_VALOR_EQUIPO_HERRAMIENTA")
	private Boolean mostrarValorEquipoHerramienta;

	@Column(name="MOSTRAR_VALOR_ESTRUCTURAS")
	private Boolean mostrarValorEstructuras;

	@Column(name="MOSTRAR_VALOR_INSUMOS")
	private Boolean mostrarValorInsumos;
	
	@Column(name="MOSTRAR_VALOR_CONTENIDOS")
	private Boolean mostrarValorContenidos;

	@Column(name="MOSTRAR_VALOR_MERCADERIA")
	private Boolean mostrarValorMercaderia;

	@Column(name="MOSTRAR_VALOR_MUEBLES_ENSERES")
	private Boolean mostrarValorMueblesEnseres;
	
	@Column(name="LIMITE_ASEGURADO")
	private double limiteAsegurado;
	
	@Column(name="PRIMA_MINIMA")
	private double primaMinima;

	public double getLimiteAsegurado() {
		return limiteAsegurado;
	}

	public void setLimiteAsegurado(double limiteAsegurado) {
		this.limiteAsegurado = limiteAsegurado;
	}

	public PymeParametroXGrupoPorProducto() {
	}

	public BigInteger getParametroGrupoProductoId() {
		return this.parametroGrupoProductoId;
	}

	public void setParametroGrupoProductoId(BigInteger parametroGrupoProductoId) {
		this.parametroGrupoProductoId = parametroGrupoProductoId;
	}

	public BigInteger getGrupoPorProductoId() {
		return this.grupoPorProductoId;
	}

	public void setGrupoPorProductoId(BigInteger grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public Boolean getMostrarMaquinaria() {
		return this.mostrarMaquinaria;
	}

	public void setMostrarMaquinaria(Boolean mostrarMaquinaria) {
		this.mostrarMaquinaria = mostrarMaquinaria;
	}

	public Boolean getMostrarValorEquipoHerramienta() {
		return this.mostrarValorEquipoHerramienta;
	}

	public void setMostrarValorEquipoHerramienta(Boolean mostrarValorEquipoHerramienta) {
		this.mostrarValorEquipoHerramienta = mostrarValorEquipoHerramienta;
	}

	public Boolean getMostrarValorEstructuras() {
		return this.mostrarValorEstructuras;
	}

	public void setMostrarValorEstructuras(Boolean mostrarValorEstructuras) {
		this.mostrarValorEstructuras = mostrarValorEstructuras;
	}

	public Boolean getMostrarValorInsumos() {
		return this.mostrarValorInsumos;
	}

	public void setMostrarValorInsumos(Boolean mostrarValorInsumos) {
		this.mostrarValorInsumos = mostrarValorInsumos;
	}

	public Boolean getMostrarValorMercaderia() {
		return this.mostrarValorMercaderia;
	}

	public void setMostrarValorMercaderia(Boolean mostrarValorMercaderia) {
		this.mostrarValorMercaderia = mostrarValorMercaderia;
	}

	public Boolean getMostrarValorMueblesEnseres() {
		return this.mostrarValorMueblesEnseres;
	}

	public void setMostrarValorMueblesEnseres(Boolean mostrarValorMueblesEnseres) {
		this.mostrarValorMueblesEnseres = mostrarValorMueblesEnseres;
	}

	public double getPrimaMinima() {
		return primaMinima;
	}

	public void setPrimaMinima(double primaMinima) {
		this.primaMinima = primaMinima;
	}

	public Boolean getMostrarValorContenidos() {
		return mostrarValorContenidos;
	}

	public void setMostrarValorContenidos(Boolean mostrarValorContenidos) {
		this.mostrarValorContenidos = mostrarValorContenidos;
	}	
}
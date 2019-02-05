package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the DISTANCIA_INSPECTOR database table.
 * 
 */
@Entity
@Table(name="DISTANCIA_INSPECTOR")
@NamedQueries({
	@NamedQuery(name="DistanciaInspector.buscarPorId", query="SELECT c FROM DistanciaInspector c where c.id = :id"),
	@NamedQuery(name="DistanciaInspector.buscarTodos", query="SELECT c FROM DistanciaInspector c"),
	@NamedQuery(name="DistanciaInspector.buscarCiudadOrigen", query="SELECT DISTINCT(c.origen) FROM DistanciaInspector c ORDER BY c.origen"),
	@NamedQuery(name="DistanciaInspector.buscarCiudadOrigenPorDestino", query="SELECT c FROM DistanciaInspector c WHERE c.origen =:origen_id order by c.destino"),
	@NamedQuery(name="DistanciaInspector.buscarDistanciasPorInspector", query="SELECT c FROM DistanciaInspector c WHERE c.origen =:origen_id AND c.destino =:destino_id AND c.inspector =:inspector")
})
public class DistanciaInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String destino;

	@Column(name="km_ida")
	private String kmIda;

	@Column(name="km_roundtrip")
	private String kmRoundtrip;

	private String origen;

	//bi-directional many-to-one association to Inspector
	@ManyToOne
	//@JoinColumn(name="inspector_id")
	private Inspector inspector;

	public DistanciaInspector() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getKmIda() {
		return this.kmIda;
	}

	public void setKmIda(String kmIda) {
		this.kmIda = kmIda;
	}

	public String getKmRoundtrip() {
		return this.kmRoundtrip;
	}

	public void setKmRoundtrip(String kmRoundtrip) {
		this.kmRoundtrip = kmRoundtrip;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Inspector getInspector() {
		return this.inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

}
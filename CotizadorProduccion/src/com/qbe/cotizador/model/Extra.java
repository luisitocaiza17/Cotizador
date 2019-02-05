package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;


/**
 * The persistent class for the EXTRAS database table.
 * 
 */
@Entity
@Table(name="EXTRAS")
@NamedQueries({
	@NamedQuery(name="Extra.buscarPorObjetoVehiculo", query="SELECT c FROM Extra c WHERE c.objetoVehiculo =:objetoVehiculo"),
	@NamedQuery(name="Extra.buscarPorId", query="SELECT c FROM Extra c WHERE c.id =:id")
})
public class Extra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	@Column(name="valor_asegurado")
	private double valorAsegurado;

	//bi-directional many-to-one association to TipoExtra
	@ManyToOne
	@JoinColumn(name="tipo_extra_id")
	private TipoExtra tipoExtra;

	//bi-directional many-to-one association to ObjetoVehiculo
	@ManyToOne
	@JoinColumn(name="objeto_vehiculo_id")
	private ObjetoVehiculo objetoVehiculo;

	public Extra() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValorAsegurado() {
		return this.valorAsegurado;
	}

	public void setValorAsegurado(double valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public TipoExtra getTipoExtra() {
		return this.tipoExtra;
	}

	public void setTipoExtra(TipoExtra tipoExtra) {
		this.tipoExtra = tipoExtra;
	}

	public ObjetoVehiculo getObjetoVehiculo() {
		return this.objetoVehiculo;
	}

	public void setObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		this.objetoVehiculo = objetoVehiculo;
	}
	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();

		retorno.put("descripcion", descripcion);
		retorno.put("valorAsegurado", valorAsegurado);
		retorno.put("tipoExtra", tipoExtra.getJSON());
		
		return retorno;
	}

}
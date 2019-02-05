package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the FORMA_PAGO database table.
 * 
 */
@Entity
@Table(name="FORMA_PAGO")
@NamedQueries({
	@NamedQuery(name="FormaPago.buscarPorId", query="SELECT c FROM FormaPago c where c.id =:id"),
	@NamedQuery(name="FormaPago.buscarTodos", query="SELECT c FROM FormaPago c")
})
public class FormaPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_ensurance")
	private String codigoEnsurance;

	private String nombre;

	//bi-directional many-to-one association to Pago
	@OneToMany(mappedBy="formaPago")
	private List<Pago> pagos;

	public FormaPago() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoEnsurance() {
		return this.codigoEnsurance;
	}

	public void setCodigoEnsurance(String codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setFormaPago(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setFormaPago(null);

		return pago;
	}

}
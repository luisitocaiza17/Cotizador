package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_ROL_MODULO database table.
 * 
 */
@Entity
@Table(name="TIPO_ROL_MODULO")
@NamedQueries({
	@NamedQuery(name="TipoRolModulo.buscarPorId", query="SELECT c FROM TipoRolModulo c where c.id = :id"),
	@NamedQuery(name="TipoRolModulo.buscarTodos", query="SELECT c FROM TipoRolModulo c")
})
public class TipoRolModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to OpcionMenuRol
	@OneToMany(mappedBy="tipoRolModulo")
	private List<OpcionMenuRol> opcionMenuRols;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	private Rol rol;

	public TipoRolModulo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OpcionMenuRol> getOpcionMenuRols() {
		return this.opcionMenuRols;
	}

	public void setOpcionMenuRols(List<OpcionMenuRol> opcionMenuRols) {
		this.opcionMenuRols = opcionMenuRols;
	}

	public OpcionMenuRol addOpcionMenuRol(OpcionMenuRol opcionMenuRol) {
		getOpcionMenuRols().add(opcionMenuRol);
		opcionMenuRol.setTipoRolModulo(this);

		return opcionMenuRol;
	}

	public OpcionMenuRol removeOpcionMenuRol(OpcionMenuRol opcionMenuRol) {
		getOpcionMenuRols().remove(opcionMenuRol);
		opcionMenuRol.setTipoRolModulo(null);

		return opcionMenuRol;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
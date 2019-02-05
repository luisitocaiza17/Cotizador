package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the TIPO_EXTRA database table.
 * 
 */
@Entity
@Table(name="TIPO_EXTRA")
@NamedQueries({
	@NamedQuery(name="TipoExtra.buscarPorId", query="SELECT c FROM TipoExtra c WHERE c.id=:id"),
	@NamedQuery(name="TipoExtra.buscarTodos", query="SELECT c FROM TipoExtra c"),
	@NamedQuery(name="TipoExtra.buscarPorIdEnsurance", query="SELECT c FROM TipoExtra c where c.tipExtEnsurance = :id"),
	@NamedQuery(name="TipoExtra.buscarActivos", query="SELECT c FROM TipoExtra c WHERE c.activo =:valorActivo")
})
public class TipoExtra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	@Column(name="tip_ext_ensurance")
	private String tipExtEnsurance;

	//bi-directional many-to-one association to Extra
	@OneToMany(mappedBy="tipoExtra")
	private List<Extra> extras;

	public TipoExtra() {
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

	public String getTipExtEnsurance() {
		return this.tipExtEnsurance;
	}

	public void setTipExtEnsurance(String tipExtEnsurance) {
		this.tipExtEnsurance = tipExtEnsurance;
	}

	public List<Extra> getExtras() {
		return this.extras;
	}

	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}

	public Extra addExtra(Extra extra) {
		getExtras().add(extra);
		extra.setTipoExtra(this);

		return extra;
	}

	public Extra removeExtra(Extra extra) {
		getExtras().remove(extra);
		extra.setTipoExtra(null);

		return extra;
	}

	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();

		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("activo", activo);
		
		return retorno;
	}
	
}
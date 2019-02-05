package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the GRUPO_USUARIO_AUTORIZACION database table.
 * 
 */
@Entity
@Table(name="GRUPO_USUARIO_AUTORIZACION")
@NamedQueries({
	@NamedQuery(name="GrupoUsuarioAutorizacion.buscarPorId", query="SELECT c FROM GrupoUsuarioAutorizacion c where c.id=:id"),
	@NamedQuery(name="GrupoUsuarioAutorizacion.buscarTodos", query="SELECT c FROM GrupoUsuarioAutorizacion c"),
	@NamedQuery(name="GrupoUsuarioAutorizacion.buscarPorEmpleado", query="SELECT c FROM GrupoUsuarioAutorizacion c where c.empleado =:empleado"),
	@NamedQuery(name="GrupoUsuarioAutorizacion.buscarPorGrupoAutorizacion", query="SELECT c FROM GrupoUsuarioAutorizacion c where c.grupoAutorizacion = :grupoAutorizacion"),
	@NamedQuery(name="GrupoUsuarioAutorizacion.buscarPorEmpleadoYGrupo", query="SELECT c FROM GrupoUsuarioAutorizacion c where c.grupoAutorizacion = :id and c.empleado = :empleado")
})
public class GrupoUsuarioAutorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to GrupoAutorizacion
	@ManyToOne
	@JoinColumn(name="grupo_autorizacion_id")
	private GrupoAutorizacion grupoAutorizacion;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	private Empleado empleado;

	public GrupoUsuarioAutorizacion() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GrupoAutorizacion getGrupoAutorizacion() {
		return this.grupoAutorizacion;
	}

	public void setGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		this.grupoAutorizacion = grupoAutorizacion;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
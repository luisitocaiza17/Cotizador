package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_DOCUMENTO database table.
 * 
 */
@Entity
@Table(name="TIPO_DOCUMENTO")
@NamedQueries({
	@NamedQuery(name="TipoDocumento.buscarPorId", query="SELECT c FROM TipoDocumento c where c.id = :id"),
	@NamedQuery(name="TipoDocumento.buscarTodos", query="SELECT c FROM TipoDocumento c"),
	@NamedQuery(name="TipoDocumento.buscarPorNombre", query="SELECT c FROM TipoDocumento c where c.nombre = :nombre"),
	@NamedQuery(name="TipoDocumento.buscarActivos", query="SELECT c FROM TipoDocumento c where c.activo=:valorActivo")
})
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	//bi-directional many-to-one association to DocumentoVisado
	@OneToMany(mappedBy="tipoDocumento")
	private List<DocumentoVisado> documentoVisados;

	public TipoDocumento() {
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

	public List<DocumentoVisado> getDocumentoVisados() {
		return this.documentoVisados;
	}

	public void setDocumentoVisados(List<DocumentoVisado> documentoVisados) {
		this.documentoVisados = documentoVisados;
	}

	public DocumentoVisado addDocumentoVisado(DocumentoVisado documentoVisado) {
		getDocumentoVisados().add(documentoVisado);
		documentoVisado.setTipoDocumento(this);

		return documentoVisado;
	}

	public DocumentoVisado removeDocumentoVisado(DocumentoVisado documentoVisado) {
		getDocumentoVisados().remove(documentoVisado);
		documentoVisado.setTipoDocumento(null);

		return documentoVisado;
	}

}
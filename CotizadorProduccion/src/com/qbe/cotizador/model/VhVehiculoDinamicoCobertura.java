package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VH_VEHICULO_DINAMICO_COBERTURA database table.
 * 
 */
@Entity
@Table(name="VH_VEHICULO_DINAMICO_COBERTURA")
@NamedQueries({
	@NamedQuery(name="VhVehiculoDinamicoCobertura.buscarPorCoberturaPrincipalId", query="SELECT c FROM VhVehiculoDinamicoCobertura c where c.coberturaPrincipal = :cobertura")
})
public class VhVehiculoDinamicoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	@JoinColumn(name="cobertura_principal_id")
	private Cobertura coberturaPrincipal;
	
	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	@JoinColumn(name="cobertura_secundaria_id")
	private Cobertura coberturaSecundaria;
	
	public VhVehiculoDinamicoCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cobertura getCoberturaPrincipal() {
		return coberturaPrincipal;
	}

	public void setCoberturaPrincipal(Cobertura coberturaPrincipal) {
		this.coberturaPrincipal = coberturaPrincipal;
	}

	public Cobertura getCoberturaSecundaria() {
		return coberturaSecundaria;
	}

	public void setCoberturaSecundaria(Cobertura coberturaSecundaria) {
		this.coberturaSecundaria = coberturaSecundaria;
	}
			
}
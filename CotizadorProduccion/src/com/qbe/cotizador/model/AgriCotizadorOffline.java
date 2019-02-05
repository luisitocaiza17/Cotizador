package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AGRI_COTIZADOR_OFFLINE")
@NamedQueries({
	@NamedQuery(name="AgriCotizadorOffline.buscarId", query="SELECT c FROM AgriCotizadorOffline c WHERE c.ID =:id"),
	@NamedQuery(name="AgriCotizadorOffline.buscarFecha", query="SELECT c FROM AgriCotizadorOffline c order by c.ID DESC")
})

public class AgriCotizadorOffline implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private BigInteger ID;
				
		@Lob
		@Column(name="OFFLINE")
	    private byte[] offline;
		
		@Temporal(TemporalType.DATE)
		@Column(name="FECHA")
		private Date fecha;

		

		public BigInteger getID() {
			return ID;
		}

		public void setID(BigInteger iD) {
			ID = iD;
		}

		public byte[] getOffline() {
			return offline;
		}

		public void setOffline(byte[] offline) {
			this.offline = offline;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		
}

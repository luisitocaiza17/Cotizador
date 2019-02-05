package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Usuario;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the MOVIMIENTO_COTIZACION database table.
 * 
 */
@Entity
@Table(name = "MOVIMIENTO_COTIZACION")
@NamedQueries({
@NamedQuery(name = "MovimientoCotizacion.buscarTodos", query = "SELECT m FROM MovimientoCotizacion m"),
@NamedQuery(name="MovimientoCotizacion.buscarPorCotizacion", query="SELECT c FROM MovimientoCotizacion c where c.cotizacionId = :cotizacionId"),
@NamedQuery(name="MovimientoCotizacion.buscarPorId", query="SELECT c FROM MovimientoCotizacion c where c.id = :id")
})
public class MovimientoCotizacion implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private String id;

  @Column(name = "cotizacion_id")
  private String cotizacionId;

  @Temporal(TemporalType.TIMESTAMP)
  private Date fecha;

  private String movimiento;

  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public MovimientoCotizacion() {}

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCotizacionId() {
    return this.cotizacionId;
  }

  public void setCotizacionId(String cotizacionId) {
    this.cotizacionId = cotizacionId;
  }

  public Date getFecha() {
    return this.fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getMovimiento() {
    return this.movimiento;
  }

  public void setMovimiento(String movimiento) {
    this.movimiento = movimiento;
  }

  public Usuario getUsuario() {
    return this.usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

}

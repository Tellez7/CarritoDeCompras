package com.mainsoft.main.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@OneToMany(mappedBy = "venta")
	private List<DetalleVenta> detalleVentas;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public Venta() {
	}

	public Venta(long idVenta, Date fecha, List<DetalleVenta> detalleVentas, Cliente cliente) {
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.detalleVentas = detalleVentas;
		this.cliente = cliente;
	}

	public long getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<DetalleVenta> getDetalleventas() {
		return this.detalleVentas;
	}

	public void setDetalleventas(List<DetalleVenta> detalleventas) {
		this.detalleVentas = detalleventas;
	}

	public DetalleVenta addDetalleventa(DetalleVenta detalleventa) {
		getDetalleventas().add(detalleventa);
		detalleventa.setVenta(this);

		return detalleventa;
	}

	public DetalleVenta removeDetalleventa(DetalleVenta detalleventa) {
		getDetalleventas().remove(detalleventa);
		detalleventa.setVenta(null);

		return detalleventa;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}

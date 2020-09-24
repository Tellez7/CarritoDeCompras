package com.mainsoft.main.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDetalleVenta;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto idProducto;

	@ManyToOne
	@JoinColumn(name = "idVenta")
	private Venta idVenta;

	public DetalleVenta() {
	}

	public DetalleVenta(long idDetalleVenta, Producto idProducto, Venta idVenta) {
		this.idDetalleVenta = idDetalleVenta;
		this.idProducto = idProducto;
		this.idVenta = idVenta;
	}

	public long getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(long idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public Venta getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Venta idVenta) {
		this.idVenta = idVenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package com.mainsoft.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	private String nombre;

	private double precio;

	@OneToMany(mappedBy = "producto")
	private List<DetalleVenta> detalleVentas;

	public Producto() {
	}

	public Producto(int idProducto, String nombre, int precio, List<DetalleVenta> detalleVentas) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.detalleVentas = detalleVentas;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<DetalleVenta> getDetalleventas() {
		return this.detalleVentas;
	}

	public void setDetalleventas(List<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

	public DetalleVenta addDetalleventa(DetalleVenta detalleVenta) {
		getDetalleventas().add(detalleVenta);
		detalleVenta.setProducto(this);

		return detalleVenta;
	}

	public DetalleVenta removeDetalleventa(DetalleVenta detalleVenta) {
		getDetalleventas().remove(detalleVenta);
		detalleVenta.setProducto(null);

		return detalleVenta;
	}

}

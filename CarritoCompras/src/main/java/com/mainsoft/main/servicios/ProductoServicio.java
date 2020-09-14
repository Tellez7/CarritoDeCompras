package com.mainsoft.main.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.Producto;
import com.mainsoft.main.repositorios.ProductoRepositorio;

@Service
public class ProductoServicio {

	@Autowired
	private ProductoRepositorio productoRepositorioDAO;

	public boolean crear(Producto producto) {
		try {
			productoRepositorioDAO.save(producto);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean actualizar(Producto producto) {
		try {
			productoRepositorioDAO.save(producto);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean eliminar(long id) {
		try {
			productoRepositorioDAO.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Producto obtener(long id) {
		try {
			return productoRepositorioDAO.findById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Producto> obtener() {
		try {
			return productoRepositorioDAO.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Producto> obtenerPorNombre(String nombre) {
		try {
			return productoRepositorioDAO.findByNombre(nombre);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Producto> obtenerPorPrecio(double precio) {
		try {
			return productoRepositorioDAO.findByPrecio(precio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Producto> obtenerPorNombreAndPrecio(String nombre, double precio) {
		try {
			return productoRepositorioDAO.findByNombreAndPrecio(nombre, precio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}

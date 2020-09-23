package com.mainsoft.main.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.Producto;
import com.mainsoft.main.exception.NotFoundException;
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

	public List<Producto> obtener() {
		try {
			return productoRepositorioDAO.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Producto obtenerPorID(long id) {
		try {
			if (productoRepositorioDAO.findById(id) == null) {
				throw new Exception();
			}
			return productoRepositorioDAO.findById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new NotFoundException("ID de producto no encontrado");
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

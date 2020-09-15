package com.mainsoft.main.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainsoft.main.entidades.Producto;
import com.mainsoft.main.servicios.ProductoServicio;

@RestController
@RequestMapping("/v1")
public class ProductoControlador {

	@Autowired
	ProductoServicio servicioProducto;

	@PutMapping("/producto")
	public boolean agregarProducto(@RequestBody Producto producto) {
		return servicioProducto.crear(producto);
	}

	@PostMapping("/producto")
	public boolean actualizarProducto(@RequestBody Producto producto) {
		return servicioProducto.actualizar(producto);
	}

	@DeleteMapping("/producto/{id}")
	public boolean eliminarProducto(@PathVariable("id") long id) {
		return servicioProducto.eliminar(id);
	}

	@GetMapping(value = "/productos")
	public List<Producto> obtenerProductos() {
		return servicioProducto.obtener();
	}

	@GetMapping(value = "/producto/{nombre}")
	public List<Producto> obtenerProductosPorNombre(@PathVariable("nombre") String nombre) {
		return servicioProducto.obtenerPorNombre(nombre);
	}

	@GetMapping(value = "/producto/{precio}")
	public List<Producto> obtenerProductosPorPrecio(@PathVariable("precio") double precio) {
		return servicioProducto.obtenerPorPrecio(precio);
	}

	@GetMapping(value = "/producto/{nombre}/{precio}")
	public List<Producto> obtenerProductosPorNombreAndPrecio(@PathVariable("nombre") String nombre,
			@PathVariable("precio") double precio) {
		return servicioProducto.obtenerPorNombreAndPrecio(nombre, precio);
	}
}

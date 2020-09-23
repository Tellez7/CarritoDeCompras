package com.mainsoft.main.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mainsoft.main.entidades.Producto;
import com.mainsoft.main.exception.ManejadorErrores;
import com.mainsoft.main.exception.NotFoundException;
import com.mainsoft.main.servicios.ProductoServicio;

@ControllerAdvice
@RestController
@RequestMapping("/v1")
public class ProductoControlador extends ResponseEntityExceptionHandler {

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

	@GetMapping(value = "/producto/{id}")
	public Producto obtenerProductosPorID(@PathVariable("id") long id) {
		return servicioProducto.obtenerPorID(id);
	}

	@GetMapping(value = "/producto/{nombre}/{precio}")
	public List<Producto> obtenerProductosPorNombreAndPrecio(@PathVariable("nombre") String nombre,
			@PathVariable("precio") double precio) {
		return servicioProducto.obtenerPorNombreAndPrecio(nombre, precio);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ManejadorErrores> handleNotFoundException(NotFoundException ex, WebRequest request) {
		ManejadorErrores exceptionResponse = new ManejadorErrores(HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getLocalizedMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND.getReasonPhrase() + " Exception");
		return new ResponseEntity<ManejadorErrores>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}

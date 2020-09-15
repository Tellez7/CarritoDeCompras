package com.mainsoft.main.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainsoft.main.entidades.Venta;
import com.mainsoft.main.servicios.VentaServicio;

@RestController
@RequestMapping("/v1")
public class VentaControlador {

	@Autowired
	VentaServicio servicioVenta;

	@PutMapping("/venta")
	public boolean agregarVenta(@RequestBody Venta venta) {
		return servicioVenta.crear(venta);
	}

	@GetMapping(value = "/ventas")
	public List<Venta> obtenerVentas() {
		return servicioVenta.obtener();
	}

	@GetMapping(value = "/venta/{fecha}")
	public List<Venta> obtenerVentasPorFecha(
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("nombre") Date fecha) {
		return servicioVenta.obtenerPorFecha(fecha);
	}
}

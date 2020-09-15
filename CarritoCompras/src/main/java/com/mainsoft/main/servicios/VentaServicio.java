package com.mainsoft.main.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.DetalleVenta;
import com.mainsoft.main.entidades.Venta;
import com.mainsoft.main.repositorios.VentaRepositorio;

import rx.Observable;

@Service
public class VentaServicio {

	@Autowired
	private VentaRepositorio ventaRepositorioDAO;

	public boolean crear(Venta venta) {
		try {
			ventaRepositorioDAO.save(venta);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public List<Venta> obtener() {
		try {
			return ventaRepositorioDAO.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Venta> obtenerPorFecha(Date fecha) {
		try {
			return ventaRepositorioDAO.findByFecha(fecha);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<DetalleVenta> listaDetalleVenta(long idVenta) {
		try {
			List<DetalleVenta> detalleVentas = new ArrayList<>();
			Observable<DetalleVenta> obs = Observable.from(detalleVentas);
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}

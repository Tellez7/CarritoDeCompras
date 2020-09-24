package com.mainsoft.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainsoft.main.entidades.DetalleVenta;

@Repository
public interface DetalleVentaRepositorio extends JpaRepository<DetalleVenta, Long> {

	public List<DetalleVenta> findAllByIdVenta_IdVenta(long idVenta);
}

package com.mainsoft.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainsoft.main.entidades.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
	
	public Producto findById(long id);

	public List<Producto> findByNombreAndPrecio(String nombre, double precio);
}

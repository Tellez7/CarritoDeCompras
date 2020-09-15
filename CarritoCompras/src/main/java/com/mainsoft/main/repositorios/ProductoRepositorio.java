package com.mainsoft.main.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainsoft.main.entidades.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Serializable> {
	
	public List<Producto> findByNombre(String nombre);

	public List<Producto> findByPrecio(double precio);

	public List<Producto> findByNombreAndPrecio(String nombre, double precio);
}

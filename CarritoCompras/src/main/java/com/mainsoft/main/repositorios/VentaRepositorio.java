package com.mainsoft.main.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainsoft.main.entidades.Venta;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Serializable> {

}

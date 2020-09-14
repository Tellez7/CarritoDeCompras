package com.mainsoft.main.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainsoft.main.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Serializable> {

	public Usuario findByUsuario(String usuario);
}

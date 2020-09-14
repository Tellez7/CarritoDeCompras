package com.mainsoft.main.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.Usuario;
import com.mainsoft.main.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorioDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		Usuario usuario = usuarioRepositorioDAO.findByUsuario(username);
		return new User(usuario.getUsuario(), encoder.encode(usuario.getContrasena()), usuario.isActivo(),
				usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), buildGrante(usuario.getRol()));
	}

	public List<GrantedAuthority> buildGrante(byte rol) {
		String[] roles = { "LECTOR", "USUARIO", "ADMINISTRADOR" };
		List<GrantedAuthority> auths = new ArrayList<>();
		for (int i = 0; i < rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}

}

package com.mainsoft.main.servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class UsuarioServicio implements UserDetailsService {

	private static final Logger LOGGER = Logger.getLogger(UsuarioServicio.class);

	public static String[] obtenerDatos() throws IOException {
		File file = ResourceUtils.getFile("classpath:usuarios.txt");
		String content = new String(Files.readAllBytes(file.toPath()));
		String usuarios[] = content.split("\\|");
		return usuarios;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PropertyConfigurator.configure("logs/login/log4j.properties");
		String usuario = "";
		String contrasena = "";
		boolean usuarioExiste = false;
		try {
			String usuarios[] = obtenerDatos();
			for (String us : usuarios) {
				String credenciales[] = us.split("\\,");
				String u = credenciales[0];
				String c = credenciales[1];
				if (username.equals(credenciales[0])) {
					usuario = u;
					contrasena = c;
					usuarioExiste = true;
				}
			}
			if (usuarioExiste) {
				LOGGER.debug("Usuario: " + username + ". Logueo Exitoso");
			} else {
				LOGGER.info("Usuario: " + username + ". Datos incorrectos en el logueo");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new User(usuario, encoder.encode(contrasena), buildGrante(3));
	}

	public List<GrantedAuthority> buildGrante(int rol) {
		String[] roles = { "LECTOR", "USUARIO", "ADMINISTRADOR" };
		List<GrantedAuthority> auths = new ArrayList<>();
		for (int i = 0; i < rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}

}

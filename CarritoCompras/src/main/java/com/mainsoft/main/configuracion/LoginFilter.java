package com.mainsoft.main.configuracion;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
	private static final String MSM_LOG_OK = "Logueo exitoso";
	private static final String MSM_LOG_FAILED = "datos incorrectos en el logueo";
	private static final String MSM_ERROR = "error";
	private static final String PATH_GENERAL_LOG = "LogGeneral.log";
	private static final String PATH_LOGIN_LOG = "logs/LogLoggin.log";

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		InputStream body = req.getInputStream();
		Usuario user = new ObjectMapper().readValue(body, Usuario.class);
		Authentication authentication = null;
		try {
			authentication = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					user.getUsuario(), user.getContrasena(), Collections.emptyList()));
			if (authentication.isAuthenticated()) {
				LOGGER.debug(MSM_LOG_OK + " - Usuario de logueo: " + user.getUsuario());
				logLogin();
			}
		} catch (Exception e) {
			if (e.getMessage().equals("Bad credentials")) {
				LOGGER.info(MSM_LOG_FAILED + " - Usuario de logueo: " + user.getUsuario());
				logLogin();
			} else {
				LOGGER.error(MSM_ERROR + " - Usuario de logueo: " + user.getUsuario());
				logLogin();
			}
			authentication = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					user.getUsuario(), user.getContrasena(), Collections.emptyList()));
		}
		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		JwtUtil.addAuthentication(res, auth.getName());
	}

	public static void logLogin() {
		try {
			File loginLog = new File(PATH_LOGIN_LOG);
			if (!loginLog.exists()) {
				loginLog.createNewFile();
			} else {
				List<String> lines = readFile(PATH_GENERAL_LOG);
				for (String line : lines) {
					if (line.contains(MSM_LOG_OK) || line.contains(MSM_LOG_FAILED)
							|| line.contains("ERROR " + MSM_ERROR)) {
						List<String> logLogin = readFile(PATH_LOGIN_LOG);
						boolean lineExist = false;
						for (String log : logLogin) {
							if (line.equals(log) && lineExist == false) {
								lineExist = true;
							}
						}
						if (lineExist == false) {
							writeFile(line, PATH_LOGIN_LOG);
						}
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void writeFile(String line, String filePath) {
		try {
			FileWriter myWriter = new FileWriter(filePath, true);
			myWriter.write(line + "\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> readFile(String filePath) {
		List<String> lines = null;
		try {
			Path path = Paths.get(filePath);
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}

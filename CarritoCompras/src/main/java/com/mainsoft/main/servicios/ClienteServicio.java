package com.mainsoft.main.servicios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.Cliente;
import com.mainsoft.main.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServicio.class);
	private static final String MSM_IDENTIFIER = "Cliente Servicio:";
	private static final String PATH_GENERAL_LOG = "LogGeneral.log";
	private static final String CLIENT_SERVICE_LOG = "logs/LogClienteServicio.log";

	@Autowired
	private ClienteRepositorio clienteRepositorioDAO;

	public boolean crear(Cliente cliente) {
		try {
			clienteRepositorioDAO.save(cliente);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOGGER.info(MSM_IDENTIFIER + " HttpStatus: " + HttpStatus.OK.value() + ". Message: Null Field"
					+ ". Code: false" + ". BackendMessage: " + e.getMessage());
			clientServiceLog();
			return false;
		}
	}

	public static void clientServiceLog() {
		try {
			File loginLog = new File(CLIENT_SERVICE_LOG);
			if (!loginLog.exists()) {
				loginLog.createNewFile();
			} else {
				List<String> lines = readFile(PATH_GENERAL_LOG);
				for (String line : lines) {
					if (line.contains(MSM_IDENTIFIER)) {
						List<String> logLogin = readFile(CLIENT_SERVICE_LOG);
						boolean lineExist = false;
						for (String log : logLogin) {
							if (line.equals(log) && lineExist == false) {
								lineExist = true;
							}
						}
						if (lineExist == false) {
							writeFile(line, CLIENT_SERVICE_LOG);
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
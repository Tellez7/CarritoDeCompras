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
import org.springframework.web.bind.annotation.PathVariable;

import com.mainsoft.main.entidades.Venta;
import com.mainsoft.main.repositorios.DetalleVentaRepositorio;
import com.mainsoft.main.repositorios.VentaRepositorio;

import rx.Observable;
import rx.Subscription;

@Service
public class VentaServicio {

	private static final Logger LOGGER = LoggerFactory.getLogger(VentaServicio.class);
	private static final String MSM_IDENTIFIER = "Venta Servicio:";
	private static final String PATH_GENERAL_LOG = "LogGeneral.log";
	private static final String PRODUCT_SERVICE_LOG = "logs/LogVentaServicio.log";

	@Autowired
	private VentaRepositorio ventaRepositorioDAO;

	@Autowired
	private DetalleVentaRepositorio detalleVentaRepositorioDAO;

	public boolean crear(Venta venta) {
		try {
			ventaRepositorioDAO.save(venta);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOGGER.info(MSM_IDENTIFIER + " HttpStatus: " + HttpStatus.OK.value() + ". Message: Null Field"
					+ ". Code: false" + ". BackendMessage: " + e.getMessage());
			productServiceLog();
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

	public Subscription obtenerDetalleVenta(@PathVariable long idVenta) {
		return Observable.just(detalleVentaRepositorioDAO.findAllByIdVenta_IdVenta(idVenta)).subscribe();
	}

	public static void productServiceLog() {
		try {
			File loginLog = new File(PRODUCT_SERVICE_LOG);
			if (!loginLog.exists()) {
				loginLog.createNewFile();
			} else {
				List<String> lines = readFile(PATH_GENERAL_LOG);
				for (String line : lines) {
					if (line.contains(MSM_IDENTIFIER)) {
						List<String> logLogin = readFile(PRODUCT_SERVICE_LOG);
						boolean lineExist = false;
						for (String log : logLogin) {
							if (line.equals(log) && lineExist == false) {
								lineExist = true;
							}
						}
						if (lineExist == false) {
							writeFile(line, PRODUCT_SERVICE_LOG);
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

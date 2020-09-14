package com.mainsoft.main.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainsoft.main.entidades.Cliente;
import com.mainsoft.main.servicios.ClienteServicio;

@RestController
@RequestMapping("/v1")
public class ClienteControlador {

	@Autowired
	ClienteServicio servicioCliente;

	@PutMapping("/cliente")
	public boolean agregarCliente(@RequestBody Cliente cliente) {
		return servicioCliente.crear(cliente);
	}

}

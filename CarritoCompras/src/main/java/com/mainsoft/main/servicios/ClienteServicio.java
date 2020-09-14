package com.mainsoft.main.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainsoft.main.entidades.Cliente;
import com.mainsoft.main.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {

	@Autowired
	private ClienteRepositorio clienteRepositorioDAO;

	public boolean crear(Cliente cliente) {
		try {
			clienteRepositorioDAO.save(cliente);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
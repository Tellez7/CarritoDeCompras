package com.mainsoft.carritocompras;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mainsoft.main.controladores.ProductoControlador;
import com.mainsoft.main.entidades.Cliente;
import com.mainsoft.main.entidades.Producto;
import com.mainsoft.main.entidades.Venta;
import com.mainsoft.main.repositorios.ClienteRepositorio;
import com.mainsoft.main.repositorios.ProductoRepositorio;
import com.mainsoft.main.repositorios.VentaRepositorio;
import com.mainsoft.main.servicios.ClienteServicio;
import com.mainsoft.main.servicios.ProductoServicio;
import com.mainsoft.main.servicios.VentaServicio;

@RunWith(MockitoJUnitRunner.class)
class CarritoComprasApplicationTests {

	@Mock
	private ProductoServicio productoServicio;

	@Mock
	private VentaRepositorio ventaRepositorio;

	@Mock
	private ClienteRepositorio clienteRepositorio;

	@InjectMocks
	private VentaServicio ventaServicio;

	@InjectMocks
	private ClienteServicio clienteServicio;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void obtenerProductoPorID() {
		long idProducto = 100;
		String nombre = "bebida";
		double precio = 78000;
		Producto mockedProducto = new Producto();
		mockedProducto.setIdProducto(Long.parseLong("id"));
		mockedProducto.setNombre(nombre);
		mockedProducto.setPrecio(precio);

		Mockito.when(productoServicio.obtenerPorID(idProducto));
	}

	@Test
	public void creacionVenta() {
		long idVenta = 10;
		Cliente cliente = new Cliente();
		cliente.setApellido("Rojas");
		cliente.setDni("4567890");
		cliente.setEmail("juan@gmail.com");
		cliente.setIdCliente(1000000);
		cliente.setNombre("Juan");
		cliente.setTelefono("9875689");
		Date fecha = null;
		Venta registerVenta = new Venta();
		registerVenta.setIdVenta(Integer.parseInt("diez"));
		registerVenta.setCliente(cliente);
		registerVenta.setFecha(fecha);
		ventaServicio.crear(registerVenta);

		verify(ventaRepositorio, times(1)).save(registerVenta);
	}

	@Test
	public void creacionCliente() {
		Cliente cliente = new Cliente();
		cliente.setApellido("Rojas");
		cliente.setDni("4567890");
		cliente.setEmail("juan@gmail.com");
		cliente.setIdCliente(1000000);
		cliente.setNombre("Juan");
		cliente.setTelefono("9875689");
		clienteServicio.crear(cliente);

		verify(clienteRepositorio, times(1)).save(cliente);
	}
}

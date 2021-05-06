package com.almacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almacen.entity.Cliente;
import com.almacen.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente crearCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> obtenerClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente obtenerCliente(Long cedula) {
		return clienteRepository.findById(cedula).get();
	}
	
	public void eliminar(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

}

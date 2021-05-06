package com.almacen.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almacen.entity.Cliente;
import com.almacen.service.ClienteService;

@RestController 
@RequestMapping(ClienteController.URL)
public class ClienteController {
	final static String URL = "api/cliente";
	
	@Autowired 
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
		if(cliente == null) {
			return ResponseEntity.badRequest().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		          .path("/{id}")
		          .buildAndExpand(cliente.getCedula())
		          .toUri();
		Cliente guardarCliente = clienteService.crearCliente(cliente);
		return ResponseEntity.created(uri)
		          .body(guardarCliente);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente, @PathVariable Long id){
		if(id == null) {
			return guardar(cliente);
			}
		Cliente buscarCliente= clienteService.obtenerCliente(id);
		buscarCliente.setDireccion(cliente.getDireccion());
		buscarCliente.setNombre(cliente.getNombre());
		buscarCliente.setCedula(cliente.getCedula());
		return ResponseEntity.ok(clienteService.crearCliente(buscarCliente));
	}
	
	@GetMapping("/obtener-todos")
	public ResponseEntity<List<Cliente>> obtenerTodos(){
		return ResponseEntity.ok(clienteService.obtenerClientes());
	}
	
	@GetMapping("/obtener-id/{id}")
	public ResponseEntity<Cliente> obtenerId(@PathVariable Long id){
		if(id == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(clienteService.obtenerCliente(id));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity eliminar(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.badRequest().build();
		}
		Cliente elimniarCliente=clienteService.obtenerCliente(id);
		clienteService.eliminar(elimniarCliente);
		return ResponseEntity.noContent().build();
			
	}
	
}

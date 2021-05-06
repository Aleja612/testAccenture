package com.almacen.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almacen.entity.Factura;
import com.almacen.service.FacturaService;

@RestController
	@RequestMapping(FacturaController.URL)
	public class FacturaController {
		final static String URL = "api/factura";
		
		@Autowired 
		private FacturaService facturaService;
		
		@PostMapping
		public ResponseEntity<Factura> guardar(@RequestBody Factura factura){
			if(factura == null) {
				return ResponseEntity.badRequest().build();
			}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			          .path("/{id}")
			          .buildAndExpand(factura.getIdFactura())
			          .toUri();
			Factura guardarFactura = facturaService.validarProductos(factura);
			return ResponseEntity.created(uri)
			          .body(guardarFactura);
		}
		
		@PutMapping("/actualizar-pedido/{id}")
		public ResponseEntity<Factura> actualizarPedido(@RequestBody Factura factura, @PathVariable Long id){
			if (id == null) {
				return ResponseEntity.badRequest().build();
			}
			Factura editarFactura = facturaService.validarEdicionProducto(factura, id);
			if (editarFactura.getFecha().isEqual(facturaService.obtenerFactura(id).getFecha())) {
				return ResponseEntity.ok(editarFactura);
			}
			return ResponseEntity.ok(editarFactura);
		}
		
		@DeleteMapping("/eliminar-pedido/{id}")
		public ResponseEntity<String> elimninarPedido(@PathVariable Long id){
			if(id==null) {
				return ResponseEntity.badRequest().build();
			}
			String eliminar=facturaService.validarEliminacionPedido(id);
			return ResponseEntity.ok(eliminar);
		}

}

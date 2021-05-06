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

import com.almacen.entity.Producto;
import com.almacen.service.ProductoService;

@RestController 
@RequestMapping(ProductoController.URL)
public class ProductoController {
		final static String URL = "api/producto";
		
		@Autowired 
		private ProductoService productoService;
		
		@PostMapping
		public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
			if(producto == null) {
				return ResponseEntity.badRequest().build();
			}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			          .path("/{id}")
			          .buildAndExpand(producto.getIdProducto())
			          .toUri();
			Producto guardarProducto = productoService.crearProducto(producto);
			return ResponseEntity.created(uri)
			          .body(guardarProducto);
		}
		
		@PutMapping("/actualizar/{id}")
		public ResponseEntity<Producto> actualizar(@RequestBody Producto producto, @PathVariable Long id){
			if(id == null) {
				return guardar(producto);
				}
			
			Producto buscarProducto= productoService.obtenerProducto(id);
			buscarProducto.setIdProducto(producto.getIdProducto());
			buscarProducto.setNombre(producto.getNombre());
			buscarProducto.setPrecio(producto.getPrecio());
			buscarProducto.setDescripcion(producto.getDescripcion());
			
			
			return ResponseEntity.ok(productoService.crearProducto(buscarProducto));
		}
		
		@GetMapping("/obtener-todos")
		public ResponseEntity<List<Producto>> obtenerTodos(){
			return ResponseEntity.ok(productoService.obtenerProductos());
		}
		
		@GetMapping("/obtener-id/{id}")
		public ResponseEntity<Producto> obtenerId(@PathVariable Long id){
			if(id == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(productoService.obtenerProducto(id));
		}
		
		@DeleteMapping("/eliminar/{id}")
		public ResponseEntity eliminar(@PathVariable Long id) {
			if(id == null) {
				return ResponseEntity.badRequest().build();
			}
			Producto elimniarProducto=productoService.obtenerProducto(id);
			productoService.eliminarProducto(elimniarProducto);
			return ResponseEntity.noContent().build();
				
		}
		
}

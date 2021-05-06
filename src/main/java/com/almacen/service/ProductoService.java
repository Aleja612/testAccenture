package com.almacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almacen.entity.Producto;
import com.almacen.repository.ProductoRespository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRespository productoRespository;
	
	public Producto crearProducto(Producto producto) {
		return productoRespository.save(producto);
	}
	
	public List<Producto> obtenerProductos(){
		return productoRespository.findAll();
	}
	public Producto obtenerProducto(Long id) {
		return productoRespository.findById(id).get();
	}
	public void eliminarProducto(Producto producto) {
		productoRespository.delete(producto);
	}
}

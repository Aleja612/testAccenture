package com.almacen.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almacen.entity.Factura;
import com.almacen.entity.Producto;
import com.almacen.repository.FacturaRepository;
import com.almacen.repository.ProductoRespository;

@Service
public class FacturaService {
	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ProductoRespository productoRespository;

	public Factura validarProductos(Factura factura) {
		Factura nuevaFactura = new Factura();
		double sumaProductos = 0;
		sumaProductos = sumaProductos(factura);
		if (sumaProductos <= 100000) {
			nuevaFactura.setDomicilios(5000);

		} else if (sumaProductos > 100000) {
			nuevaFactura.setDomicilios(0);
		}
		nuevaFactura.setCantidad(factura.getCantidad());
		nuevaFactura.setSubTotal(sumaProductos * 0.19);
		nuevaFactura.setTotal(nuevaFactura.getDomicilios() + nuevaFactura.getSubTotal() + sumaProductos);
		nuevaFactura.setCliente(factura.getCliente());
		nuevaFactura.getProductos()
				.addAll(productoRespository.findAllByIdProductoIn(buscarIds(factura.getProductos())));
		facturaRepository.save(nuevaFactura);

		return nuevaFactura;
	}

	public Factura validarEdicionProducto(Factura factura, long id) {
		Factura editarPedido = new Factura();
		LocalDateTime horaActual = LocalDateTime.now();
		int diferencia = factura.getFecha().getHour() - horaActual.getHour();
		Factura facturaEditar = obtenerFactura(id);
		if (diferencia <= 5) {
			double sumaNuevosProductos = sumaProductos(factura);
			double sumaOriginalProductos = sumaProductos(facturaEditar);
			if (sumaNuevosProductos >= sumaOriginalProductos) {
				editarPedido = validarProductos(factura);
				editarPedido.setFecha(factura.getFecha());
				return editarPedido;
			} else {
				return facturaEditar;
			}

		} else {
			return facturaEditar;
		}
	}

	private double sumaProductos(Factura factura) {
		double sumaProductos = 0;
		List<Producto> productos = productoRespository.findAllByIdProductoIn(buscarIds(factura.getProductos()));
		for (Producto producto : productos) {
			sumaProductos += producto.getPrecio() * factura.getCantidad();
		}
		return sumaProductos;
	}

	private List<Long> buscarIds(List<Producto> productos) {
		List<Long> ids = new ArrayList<>();
		for (Producto producto : productos) {
			ids.add(producto.getIdProducto());
		}
		return ids;
	}

	public Factura obtenerFactura(long id) {
		return facturaRepository.findById(id).get();
	}

	public String validarEliminacionPedido(Long idFactura) {
		Factura factura = obtenerFactura(idFactura);
		LocalDateTime horaActual = LocalDateTime.now();
		int diferencia = horaActual.getHour() - factura.getFecha().getHour();
		if (diferencia < 12) {
			facturaRepository.delete(factura);
			return "Su pedido ha sido eliminado exitosamente";
		} else {
			double penalizacion = factura.getTotal() * 0.10;
			facturaRepository.delete(factura);
			return "Su pedido ha sido eliminado exitosamente, su penalizacion por exceder el limite de tiempo es de: "
					+ penalizacion;
		}
	}
}

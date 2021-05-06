package com.almacen.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="factura")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idFactura")
public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura")
	private long idFactura;
	@Column(name="fecha")
	private LocalDateTime fecha = LocalDateTime.now();
	@Column(name="total")
	private double total;
	@Column(name="domicilios")
	private double domicilios;
	@Column (name="subtotal")
	private double subTotal;
	@Column(name="cantidad")
	private int cantidad;
	@ManyToOne
	@JoinColumn(name = "cedula", nullable = false)
	private Cliente cliente;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name="producto_factura",joinColumns = {@JoinColumn(name="id_factura")},
			inverseJoinColumns = {@JoinColumn(name="id_producto")}
			)
	List <Producto> productos = new ArrayList<>();
	
	
	
	public Factura() {
		
	}

	

	public Factura(long idFactura, LocalDateTime fecha, double total, double domicilios, double subTotal, int cantidad,
			Cliente cliente, List<Producto> productos) {
		super();
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.total = total;
		this.domicilios = domicilios;
		this.subTotal = subTotal;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.productos = productos;
	}



	public long getIdFactura() {
		return idFactura;
	}



	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}





	public LocalDateTime getFecha() {
		return fecha;
	}



	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public double getDomicilios() {
		return domicilios;
	}



	public void setDomicilios(double domicilios) {
		this.domicilios = domicilios;
	}



	public double getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", total=" + total + ", domicilios="
				+ domicilios + ", subTotal=" + subTotal + ", cantidad=" + cantidad + ", cliente=" + cliente
				+ ", productos=" + productos + "]";
	}



	

	
		
}

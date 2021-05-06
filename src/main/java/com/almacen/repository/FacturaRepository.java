package com.almacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almacen.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{

}

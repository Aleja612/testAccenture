package com.almacen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almacen.entity.Producto;

@Repository
public interface ProductoRespository extends JpaRepository<Producto, Long>{
	List<Producto> findAllByIdProductoIn(List<Long> ids);
}

CREATE TABLE IF NOT EXISTS cliente(
	cedula INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(150)
);
CREATE TABLE IF NOT EXISTS producto(
	id_producto INT AUTO_INCREMENT  PRIMARY KEY,
    nombre VARCHAR(100),
    precio double,
    descripcion VARCHAR(200)
);
CREATE TABLE IF NOT EXISTS factura(
	id_factura INT AUTO_INCREMENT  PRIMARY KEY,
    fecha date,
    total double,
    cedula int,
    domicilio double,
    subtotal double,
    cantidad int,
     FOREIGN KEY(cedula) REFERENCES cliente(cedula)
);
CREATE TABLE IF NOT EXISTS producto_factura(
	id_producto INT NOT NULL,
    id_factura INT NOT NULL,
    PRIMARY KEY(id_producto,id_factura),
    FOREIGN KEY(id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY(id_factura) REFERENCES factura(id_factura)
);
/*Francisco Javier  Aranda Barba*/
DROP DATABASE IF EXISTS BancoApp;
CREATE DATABASE BancoApp;
USE BancoAPP;
-- Creación de la tabla CLIENTES
CREATE TABLE CLIENTES (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50),
  telefono VARCHAR(20),
  direccion VARCHAR(100)
);

-- Creación de la tabla CUENTAS
CREATE TABLE CUENTAS (
  numero_cuenta INT AUTO_INCREMENT PRIMARY KEY,
  dni_cliente VARCHAR(10),
  situacion ENUM('activa', 'baja'),
  saldo DECIMAL(10, 2),
  FOREIGN KEY (dni_cliente) REFERENCES CLIENTES(dni) ON DELETE CASCADE
);

-- Creación de la tabla MOVIMIENTOS
CREATE TABLE MOVIMIENTOS (
  movimiento_id INT AUTO_INCREMENT PRIMARY KEY,
  numero_cuenta INT,
  importe DECIMAL(10, 2),
  fecha_hora DATETIME,
  tipo ENUM('ingreso', 'salida', 'transferencia enviada', 'transferencia recibida'),
  numero_cuenta_transferencia INT,
  concepto VARCHAR(200),
  FOREIGN KEY (numero_cuenta) REFERENCES CUENTAS(numero_cuenta) ON DELETE CASCADE,
  FOREIGN KEY (numero_cuenta_transferencia) REFERENCES CUENTAS(numero_cuenta) ON DELETE CASCADE
);

/*Inserción de datos en tabla clientes*/
INSERT INTO clientes VALUES ('15948759Z','Paco Martinez','951849693','C/ Carreteria 13');
INSERT INTO clientes VALUES ('74185295Q','Juan Guado','951454545','C/ Ferro 45');
INSERT INTO clientes VALUES ('85296396L','Maribel Ibarra','951968574','C/ Alama 16');
INSERT INTO clientes VALUES ('25814798M','Julia Fuentes','951121212','C/ Cura 6');
INSERT INTO clientes VALUES ('75386945B','Marta Santana','951202020','C/ Demostenes 12');
/*Inserción de datos en tabla cuentas*/
INSERT INTO cuentas VALUES (1,'15948759Z','activa',1500);
INSERT INTO cuentas VALUES (2,'74185295Q','baja',0);
INSERT INTO cuentas VALUES (3,'85296396L','activa',4000);
INSERT INTO cuentas VALUES (4,'25814798M','activa',4400);
INSERT INTO cuentas VALUES (5,'75386945B','activa',1000);
/*Inserción de datos en tabla movimientos*/
INSERT INTO movimientos VALUES (1,1,1500,'2022-12-15','ingreso',3,'Pago');
INSERT INTO movimientos VALUES (2,2,2500,'2023-12-15','salida',1,'Pago');
INSERT INTO movimientos VALUES (3,3,1500,'2023-2-15','transferencia enviada',4,'Pago');
INSERT INTO movimientos VALUES (4,4,500,'2023-1-1','transferencia recibida',2,'Pago');
INSERT INTO movimientos VALUES (5,5,50,'2023-2-5','ingreso',5,'Pago');

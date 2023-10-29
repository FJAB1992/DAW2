/*Francisco Javier Aranda Barba */
DROP DATABASE if exists ClubFutbol;
CREATE DATABASE IF NOT EXISTS ClubFutbol;
USE ClubFutbol;
CREATE TABLE Socios (
    NumSocio INT PRIMARY KEY AUTO_INCREMENT,
    DNI VARCHAR(9),
    fechaNacimiento DATE,
    nombre VARCHAR(50),
    CuentaPagos VARCHAR(50),
    AlCorrienteDePago BOOLEAN
);

CREATE TABLE Cuotas (
    IDcuota INT PRIMARY KEY AUTO_INCREMENT,
    NumSocio INT,
    Mes VARCHAR(20),
    Pagado BOOLEAN,
    FOREIGN KEY (NumSocio) REFERENCES Socios(NumSocio)
);

-- Insertar valores en la tabla Socios
INSERT INTO Socios (NumSocio, DNI, fechaNacimiento, nombre, CuentaPagos, AlCorrienteDePago)
VALUES
    (1, '11111111p', '1990-05-15', 'Juan Pérez', 'Cuenta1', 1),
    (2, '22222222l', '1998-10-20', 'María López', 'Cuenta2', 0),
    (3, '33333333m', '2005-02-08', 'Pedro Rodríguez', 'Cuenta3', 1),
    (4, '33322211d', '1992-07-03', 'Ana García', 'Cuenta4', 1),
    (5, '14725814j', '2014-11-25', 'Carlos Martínez', 'Cuenta5', 0);

-- Insertar valores en la tabla Cuotas
INSERT INTO Cuotas (NumSocio, Mes, Pagado)
VALUES
    (1, 'Enero 2023', 1),
    (2, 'Enero 2023', 1),
    (3, 'Enero 2023', 0),
    (4, 'Enero 2023', 1),
    (5, 'Enero 2023', 0);



SELECT NumSocio FROM Socios WHERE DNI = '11111111p';

INSERT INTO Cuotas (NumSocio, Mes, Pagado) VALUES (1, 'febrero 2023', false);
UPDATE Cuotas SET Pagado = true WHERE NumSocio = 1 AND Mes = 'febrero 2023';
SELECT * FROM Cuotas WHERE NumSocio = 1 AND Mes ='Enero 2023'  AND Pagado = 1;
SELECT * FROM Cuotas WHERE NumSocio = 1 AND Mes ='febrero 2023'  AND Pagado = 1;
SELECT Mes FROM Cuotas WHERE NumSocio = 1 AND Pagado = true;
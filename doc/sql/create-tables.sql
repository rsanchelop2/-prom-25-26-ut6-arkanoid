CREATE TABLE arkanoid.nivel (
    id INT NOT NULL AUTO_INCREMENT,
    filas INT NOT NULL,
    columnas INT NOT NULL,
    ladrillos TEXT NOT NULL,
    siguiente_nivel INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_siguiente_nivel
    FOREIGN KEY (siguiente_nivel)
    REFERENCES arkanoid.nivel(id)
    ON DELETE SET NULL
);
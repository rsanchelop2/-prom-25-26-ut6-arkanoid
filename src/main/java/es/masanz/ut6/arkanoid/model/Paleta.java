package es.masanz.ut6.arkanoid.model;

import javafx.scene.canvas.GraphicsContext;

import static es.masanz.ut6.arkanoid.conf.Const.*;

public class Paleta extends Sprite {

    public Paleta(double x, double y, int color, double anchura, double altura, double velocidadX, double velocidadY) {
        super(x, y, color, anchura, altura, velocidadX, velocidadY);
    }

    @Override
    public boolean mover(Nivel nivel) {
        x = x + velocidadX;
        y = y + velocidadY;
        if(x>(TAM_CASILLA * nivel.getColumnas() - anchura)){
            x = TAM_CASILLA * nivel.getColumnas() - anchura;
        } else if (x<0) {
            x = 0;
        }
        if(y>(TAM_CASILLA * nivel.getFilas() - altura)){
            y = TAM_CASILLA * nivel.getFilas() - altura;
        } else if (y<0) {
            y = 0;
        }
        return true;
    }

    @Override
    public void pintar(GraphicsContext gc) {
        gc.setFill(COLORES[color]);
        gc.fillRect(x, y, anchura, altura);
        gc.setStroke(COLORES[4]);
        gc.strokeRect(x, y, anchura, altura);
    }
}

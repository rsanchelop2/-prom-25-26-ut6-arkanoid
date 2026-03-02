package es.masanz.ut6.arkanoid.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

import static es.masanz.ut6.arkanoid.conf.Const.*;

public abstract class Potenciador extends Sprite {

    public Potenciador(double x, double y, int color, double anchura, double altura, double velocidadX, double velocidadY) {
        super(x, y, color, anchura, altura, 0, velocidadY);
    }

    @Override
    public boolean mover(Nivel nivel) {
        y = y + velocidadY;
        if(y>(TAM_CASILLA * nivel.getFilas() - altura)){
            return false;
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

    public abstract void aplicarEfecto(List<Sprite> sprites);
}

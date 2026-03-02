package es.masanz.ut6.arkanoid.model;

import java.util.List;

import static es.masanz.ut6.arkanoid.conf.Const.TAM_CASILLA;

/**
 * Potenciador que amplia la anchura de la paleta
 */
public class PotVelocidad extends Potenciador {

    public PotVelocidad(double x, double y) {
        this(x, y, 2, 1, 0.0, 0.5);
    }

    public PotVelocidad(double x, double y, double anchura, double altura, double velocidadX, double velocidadY) {
        super(x, y, 4, anchura * TAM_CASILLA, altura * TAM_CASILLA, velocidadX, velocidadY);
    }

    @Override
    public void aplicarEfecto(List<Sprite> sprites) {
        // TODO 08: Si alguno de los sprites es una bola, modificar su velocidad al gusto
    }
}

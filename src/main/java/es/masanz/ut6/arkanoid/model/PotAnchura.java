package es.masanz.ut6.arkanoid.model;

import java.util.List;

import static es.masanz.ut6.arkanoid.conf.Const.TAM_CASILLA;

/**
 * Potenciador que amplia la anchura de la paleta
 */
public class PotAnchura extends Potenciador {

    public PotAnchura(double x, double y) {
        this(x, y, 2, 1, 0.0, 0.5);
    }

    public PotAnchura(double x, double y, double anchura, double altura, double velocidadX, double velocidadY) {
        super(x, y, 3, anchura * TAM_CASILLA, altura * TAM_CASILLA, velocidadX, velocidadY);
    }

    @Override
    public void aplicarEfecto(List<Sprite> sprites) {
        // TODO 06: Si alguno de los sprites es una paleta, ampliar su anchura si esta no supera 10 veces TAM_CASILLA
    }
}

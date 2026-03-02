package es.masanz.ut6.arkanoid.conf;

import javafx.scene.paint.Color;

public class Const {

    // TODO 10: Configura estos parametros para conectar con tu BBDD - DONE
    public static final String ESQUEMA = "arkanoid";
    public static final String USER = "inter";
    public static final String PASS = "inter";

    public static final double TAM_CASILLA = 30;

    // TODO 09: Ampliar o reducir el catalogo de colores, se emplea en varios sitios
    public static final Color[] COLORES = {
            Color.BLACK,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.YELLOW
    };

    public static final boolean PINTAR_CUADRICULAS = true;
}

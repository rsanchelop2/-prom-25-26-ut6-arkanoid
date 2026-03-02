package es.masanz.ut6.arkanoid.model;

import java.util.ArrayList;
import java.util.List;

import static es.masanz.ut6.arkanoid.conf.Const.TAM_CASILLA;

public class Nivel {

    int id;
    int filas, columnas;
    List<Ladrillo> ladrillos;
    int siguienteNivel;

    public Nivel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public List<Ladrillo> getLadrillos() {
        return ladrillos;
    }

    public void setLadrillos(List<Ladrillo> ladrillos) {
        this.ladrillos = ladrillos;
    }

    public int getSiguienteNivel() {
        return siguienteNivel;
    }

    public void setSiguienteNivel(int siguienteNivel) {
        this.siguienteNivel = siguienteNivel;
    }

    public void generarLadrillosDesdeTexto(String ladrillosStr) {
        // TODO 03: En funcion del String recibido, cargar los ladrillos del nivel
    }

    public String generarTextoDesdeLadrillos(){
        // TODO 04: En funcion de los ladrillos del nivel, generar el String que los represente
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}

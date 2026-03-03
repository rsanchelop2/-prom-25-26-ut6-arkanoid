package es.masanz.ut6.arkanoid.model;

import es.masanz.ut6.arkanoid.dao.NivelDao;
import es.masanz.ut6.arkanoid.database.ConnectionManager;
import es.masanz.ut6.arkanoid.service.NivelService;

import java.util.ArrayList;
import java.util.Collections;
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
        int longitud = ladrillosStr.length();
        List<String> StrLadrillo = new ArrayList<>();

        List<Ladrillo> listaLadrillos = new ArrayList<Ladrillo>();

        for (int i = 0; i < ladrillosStr.length(); i++) {
            if (ladrillosStr.charAt(i) - '0' > 0) {
                int fila = i / filas;
                int columna = i - fila * columnas;
                Ladrillo ladrillo = new Ladrillo(columna,fila);
                int vidas = ladrillosStr.charAt(i) - '0';
                ladrillo.setVidas(vidas);
                listaLadrillos.add(ladrillo);
            }
        }
        ladrillos = listaLadrillos;

        //separarString(ladrillosStr);
    }
//    private List<String> separarString(String texto){
//        List<Integer> textoSeparado = new ArrayList<>();
//        List<Integer> textoPorCaracteres = new ArrayList<>();
//        int numeroCaracteresPorFila = obtenerCaracteresPorFila(texto);
//        int longitud = texto.length();
//        int contador = 0;
//
//
//        for (int i = 0; i < numeroCaracteresPorFila; i++) {
//            textoPorCaracteres.add()
//
//            if (contador <= 10){
//
//            }
//        }
//        for (int i = 0; i < longitud; i += 10) {
//            if (contador == numeroCaracteresPorFila){
//                contador = 0;
//            }
//            textoSeparado.add(texto.substring(i, Math.min(longitud, i + 10)));
//        }
//
//        return textoSeparado;
//    }
//
//    private int obtenerCaracteresPorFila(String texto) {
//        int longitud = texto.length();
//        if (longitud == 100) return 10;
//        if (longitud == 450 || longitud == 465) return 15;
//        return 0;
//    }

    public String generarTextoDesdeLadrillos(){
        // TODO 04: En funcion de los ladrillos del nivel, generar el String que los represente
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}

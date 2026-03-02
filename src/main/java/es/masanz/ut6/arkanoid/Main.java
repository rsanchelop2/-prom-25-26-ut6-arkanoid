package es.masanz.ut6.arkanoid;

import es.masanz.ut6.arkanoid.app.Arkanoid;
import es.masanz.ut6.arkanoid.app.EditorNiveles;
import es.masanz.ut6.arkanoid.database.ConnectionManager;
import javafx.application.Application;

import static es.masanz.ut6.arkanoid.conf.Const.*;

public class Main {

    public static void main(String[] args) {

        ConnectionManager.conectar(ESQUEMA, USER, PASS);

        Application.launch(Arkanoid.class, args);
        //Application.launch(EditorNiveles.class, args);

    }

}
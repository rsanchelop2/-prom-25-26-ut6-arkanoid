package es.masanz.ut6.arkanoid.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static es.masanz.ut6.arkanoid.conf.Const.COLORES;
import static es.masanz.ut6.arkanoid.conf.Const.TAM_CASILLA;

public class EditorNiveles extends Application {

    private int[][] matrizLogica;
    private Rectangle[][] matrizVisual;
    private GridPane contenedorCuadricula;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1a1a1a;");

        VBox panelControl = new VBox(10);
        panelControl.setPadding(new Insets(15));
        panelControl.setStyle("-fx-background-color: #333333;");

        TextField txtFilas = new TextField("10");
        TextField txtColumnas = new TextField("10");
        Button btnRegenerar = new Button("Regenerar Cuadrícula");
        Button btnGenerarInsert = new Button("Generar INSERT");

        panelControl.getChildren().addAll(
                new Label("Filas:"), txtFilas,
                new Label("Columnas:"), txtColumnas,
                btnRegenerar, btnGenerarInsert
        );

        panelControl.getChildren().filtered(n -> n instanceof Label)
                .forEach(n -> ((Label) n).setTextFill(Color.WHITE));

        root.setRight(panelControl);

        contenedorCuadricula = new GridPane();
        root.setCenter(contenedorCuadricula);

        btnRegenerar.setOnAction(e -> {
            try {
                int f = Integer.parseInt(txtFilas.getText());
                int c = Integer.parseInt(txtColumnas.getText());
                crearCuadricula(f, c);
            } catch (NumberFormatException ex) {
                System.out.println("Introduce números válidos");
            }
        });

        btnGenerarInsert.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for (int f = 0; f < matrizLogica.length; f++) {
                for (int c = 0; c < matrizLogica[f].length; c++) {
                    sb.append(matrizLogica[f][c]);
                    if(matrizLogica[f][c]>0){
                        sb.append(0);
                        c++;
                    }
                }
            }
            System.out.println("INSERT INTO arkanoid.nivel (filas, columnas, ladrillos, siguiente_nivel)");
            System.out.println("VALUES ('"+matrizLogica.length+"', '"+matrizLogica[0].length+"', '"+sb+"', 0);");
        });

        crearCuadricula(10, 10);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Editor niveles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void crearCuadricula(int filas, int columnas) {
        contenedorCuadricula.getChildren().clear();
        matrizLogica = new int[filas][columnas];
        matrizVisual = new Rectangle[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                matrizLogica[f][c] = 0;

                Rectangle rect = new Rectangle(TAM_CASILLA-1, TAM_CASILLA-1);
                rect.setFill(COLORES[0]);
                rect.setStroke(Color.DARKGRAY);

                final int filaActual = f;
                final int colActual = c;

                rect.setOnMouseClicked(e -> manejarClick(filaActual, colActual, columnas));

                matrizVisual[f][c] = rect;
                contenedorCuadricula.add(rect, c, f);
            }
        }

        primaryStage.sizeToScene();
    }

    private void manejarClick(int f, int c, int totalColumnas) {

        int nuevoIndice = (matrizLogica[f][c] + 1) % COLORES.length;
        matrizLogica[f][c] = nuevoIndice;
        matrizVisual[f][c].setFill(COLORES[nuevoIndice]);

        if (c + 1 < totalColumnas) {
            matrizLogica[f][c+1] = nuevoIndice;
            matrizVisual[f][c+1].setFill(COLORES[nuevoIndice]);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

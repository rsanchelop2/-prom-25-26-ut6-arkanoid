package es.masanz.ut6.arkanoid.app;

import es.masanz.ut6.arkanoid.model.*;
import es.masanz.ut6.arkanoid.service.NivelService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

import static es.masanz.ut6.arkanoid.conf.Const.*;

public class Arkanoid extends Application {

    private Map<String, List<Sprite>> sprites;
    private Sprite paleta;

    private Canvas lienzo;
    private Timeline timeline;
    private boolean pausado = false;

    private Nivel nivel;

    private Label lblPuntos;
    private Label lblVidas;
    private int puntos;
    private int vidas;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        HBox root = new HBox();
        Scene scene = new Scene(root);

        // TODO 11: inicializa el nivel
        nivel = null;

        lienzo = new Canvas();
        generarMapa();

        VBox panelInfo = new VBox(20);
        panelInfo.setStyle("-fx-background-color: #333; -fx-padding: 20;");
        panelInfo.setPrefWidth(200);

        puntos = 0;
        lblPuntos = new Label("Puntos: "+puntos);
        lblPuntos.setStyle("-fx-text-fill: white; -fx-font-size: 18;");

        vidas = 3;
        lblVidas = new Label("Vidas: "+vidas);
        lblVidas.setStyle("-fx-text-fill: white; -fx-font-size: 18;");

        panelInfo.getChildren().addAll(lblPuntos, lblVidas);

        root.getChildren().addAll(lienzo, panelInfo);

        scene.setOnMouseClicked(this::clicDeRaton);
        scene.setOnKeyPressed(this::teclaPulsada);
        scene.setOnKeyReleased(this::teclaSoltada);
        scene.setOnMouseMoved(this::moverConRaton);

        timeline = new Timeline(new KeyFrame(Duration.millis(7), e -> cicloDelJuego()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.setScene(scene);
        stage.setTitle("Arkanoid JavaFX");
        stage.show();
    }

    private void generarMapa() {
        // TODO 12: Inicializa el mapa de sprites. Debe estar compuesto por 3 listados:
        //  1 para "ladrillos", otro para "bolas" y otro para "potenciadores".
        //  Emplea los terminos entrecomillados como claves
        //  La lista de "potenciadores" debera estar vacia
        //  La lista de "bolas" debera contener unicamente la bola que esta aqui definida
        //  La lista de "ladrillos" la deberas obtener del nivel
        Bola bola = new Bola(nivel.getColumnas() / 2 * TAM_CASILLA, (nivel.getFilas()-4) * TAM_CASILLA);

        // NO TOCAR ESTAS LINEAS
        paleta = new Paleta(nivel.getColumnas()/2 - TAM_CASILLA*6/2, (nivel.getFilas()-3)*TAM_CASILLA, 4, TAM_CASILLA*6, TAM_CASILLA, 0, 0);
        lienzo.setHeight(nivel.getFilas() * TAM_CASILLA);
        lienzo.setWidth(nivel.getColumnas() * TAM_CASILLA);
        this.stage.sizeToScene();
    }

    private void cicloDelJuego() {

        moverLadrillos();
        List<Sprite> eliminarBolas = moverBolas();

        List<Sprite> eliminarPotenciadores = moverPotenciadores();
        eliminarPotenciadores(eliminarPotenciadores);

        List<Sprite> eliminarLadrillos = colisionesLadrillos();
        eliminarLadrillos(eliminarLadrillos);

        eliminarBolas(eliminarBolas);

        colisionBolas();

        paleta.mover(nivel);
        pintar();

        if(sprites.get("bolas").size()==0){
            vidas--;
            pausado = true;
            timeline.pause();
            if(vidas>0){
                pintarPausa("VIDA PERDIDA - Pulsa ESC para continuar");
                Bola bola = new Bola(nivel.getColumnas() / 2 * TAM_CASILLA, (nivel.getFilas()-4) * TAM_CASILLA);
                sprites.get("bolas").add(bola);
                sprites.get("potenciadores").clear();
                paleta = new Paleta(nivel.getColumnas()/2 - TAM_CASILLA*6/2, (nivel.getFilas()-3)*TAM_CASILLA, 4, TAM_CASILLA*6, TAM_CASILLA, 0, 0);
            } else {
                pintarPausa("GAME OVER");
            }
        }

        if(sprites.get("ladrillos").size()==0){
            pausado = true;
            timeline.pause();
            nivel = NivelService.obtenerNivel(nivel.getSiguienteNivel());
            generarMapa();
            pintar();
            pintarPausa("SIGUIENTE NIVEL - Pulsa ESC para continuar");
        }
    }

    private void moverLadrillos() {
        // TODO 13: Deberas mover todos los ladrillos
    }

    private List<Sprite> moverBolas() {
        // TODO 14: Deberas mover todas las bolas y devolver aquellas que no se puedan mover
        return null;
    }

    private List<Sprite> moverPotenciadores() {
        // TODO 15: Deberas mover todos los potenciadores y devolver aquellos que, o bien no se puedan mover
        //  o bien que despues de moverse esten en colision con la paleta.
        //  Adicionalmente, si entra en contacto con la paleta, se debera aplicar
        //  su efecto a todos los sprites que pueda afectar su potenciacion (a la paleta, bolas, etc)
        return null;
    }

    private void eliminarPotenciadores(List<Sprite> eliminarPotenciadores) {
        // TODO 16: Deberas eliminar los potenciadores indicados del mapa de sprites
    }

    private List<Sprite> colisionesLadrillos() {
        // TODO 17: Deberas analizar si hay colision entre los ladrillos y las bolas del juego.
        //  En caso de que haya colision, invoca al metodo hayColision de la bola con la que colisiona el ladrillo
        //  y al metodo morir del propio ladrillo. Deberas devolver todos los ladrillos que se mueran.
        //  OPCIONAL: Aqui se pueden ampliar los puntos del juego si se desea
        return null;
    }

    private void eliminarLadrillos(List<Sprite> eliminarLadrillos) {
        // TODO 18: Deberas eliminar los ladrillos indicados del mapa de sprites.
        //  Ademas, para cada ladrillo eliminado, se debera validar si genera un potenciador.
        //  En caso de generar uno, se debera incluir al listado de potenciadores del mapa de sprites
    }

    private void eliminarBolas(List<Sprite> eliminarBolas) {
        // TODO 19: Deberas eliminar las bolas indicadas del mapa de sprites.
    }

    private void colisionBolas() {
        // TODO 20: Deberas analizar si hay colision entre las bolas y la paleta del juego.
    }

    private void pintar() {

        GraphicsContext gc = lienzo.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());

        // primero pintamos el fondo y las cuadriculas del mapa
        for (int fila = 0; fila < nivel.getFilas(); fila++) {
            for (int col = 0; col < nivel.getColumnas(); col++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(col * TAM_CASILLA, fila * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
                if(PINTAR_CUADRICULAS){
                    gc.setStroke(Color.DARKGRAY);
                    gc.strokeRect(col * TAM_CASILLA, fila * TAM_CASILLA, TAM_CASILLA, TAM_CASILLA);
                }
            }
        }
        lblPuntos.setText("Puntos: "+puntos);
        lblVidas.setText("Vidas: "+vidas);

        pintarSprites(gc);

        paleta.pintar(gc);
    }

    private void pintarSprites(GraphicsContext gc) {
        // TODO 21: Deberas pintar todos los sprites del juego
    }

    private void pintarPausa(String msg) {
        GraphicsContext gc = lienzo.getGraphicsContext2D();
        gc.setFill(Color.rgb(0, 0, 0, 0.75));
        gc.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillText(msg,lienzo.getWidth()/2 - 100,lienzo.getHeight()/2);
    }

    private void clicDeRaton(MouseEvent event) {
//        double mouseX = event.getX();
//        double mouseY = event.getY();
//        System.out.println(mouseX+", "+mouseY);
    }

    private void teclaPulsada(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        if (code.equals(KeyCode.ESCAPE)) {
            pausado = !pausado;
            if(pausado){
                timeline.pause();
                pintarPausa("JUEGO EN PAUSA - Pulsa ESC");
            } else {
                timeline.play();
            }
        }

        if (!pausado) {
            if (code.equals(KeyCode.A) || code.equals(KeyCode.LEFT)) {
                paleta.setVelocidadX(-5);
            } else if (code.equals(KeyCode.D) || code.equals(KeyCode.RIGHT)) {
                paleta.setVelocidadX(5);
            } else if (code.equals(KeyCode.SPACE)) {
                // ALGUNA ACCION A REALIZAR CUANDO SE PULSA LA BARRA DEL ESPACIO, DISPARAR, POR EJEMPLO
            }
        }
    }

    private void teclaSoltada(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if(code.equals(KeyCode.A) || code.equals(KeyCode.LEFT)){
            paleta.setVelocidadX(0);
        } else if(code.equals(KeyCode.D) || code.equals(KeyCode.RIGHT)){
            paleta.setVelocidadX(0);
        } else if(code.equals(KeyCode.SPACE)){
            // DEJAR DE DISPARAR, POR EJEMPLO
        }
    }

    private void moverConRaton(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double nuevaX = mouseX - (paleta.getAnchura() / 2);
        if (nuevaX < 0) {
            nuevaX = 0;
        } else if (nuevaX + paleta.getAnchura() > lienzo.getWidth()) {
            nuevaX = lienzo.getWidth() - paleta.getAnchura();
        }
        paleta.setX(nuevaX);
    }
}
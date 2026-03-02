module es.masanz.ut6.arkanoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires jdk.xml.dom;
    requires java.sql;

    opens es.masanz.ut6.arkanoid to javafx.fxml;
    opens es.masanz.ut6.arkanoid.app to javafx.fxml;

    exports es.masanz.ut6.arkanoid;
    exports es.masanz.ut6.arkanoid.app;
}
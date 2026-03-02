package es.masanz.ut6.arkanoid.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;

public abstract class Sprite {

    protected int color;
    protected double x, y;
    protected double anchura, altura;
    protected double velocidadX, velocidadY;

    public Sprite(double x, double y, int color, double anchura, double altura, double velocidadX, double velocidadY) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.anchura = anchura;
        this.altura = altura;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
    }

    public boolean hayColision(Sprite otro){
        if(this.x > (otro.x + otro.anchura)){
            return false;
        }
        if((this.x + anchura) < otro.x){
            return false;
        }
        if(this.y > (otro.y + otro.altura)){
            return false;
        }
        if((this.y + altura) < otro.y){
            return false;
        }
        return true;
    }

    public abstract boolean mover(Nivel nivel);
    public abstract void pintar(GraphicsContext gc);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAnchura() {
        return anchura;
    }

    public void setAnchura(double anchura) {
        this.anchura = anchura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprite sprite = (Sprite) o;
        return Double.compare(x, sprite.x) == 0 && Double.compare(y, sprite.y) == 0 && Double.compare(anchura, sprite.anchura) == 0 && Double.compare(altura, sprite.altura) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, anchura, altura);
    }
}

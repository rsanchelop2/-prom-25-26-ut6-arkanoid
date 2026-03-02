package es.masanz.ut6.arkanoid.model;

import javafx.scene.canvas.GraphicsContext;

import static es.masanz.ut6.arkanoid.conf.Const.*;

public class Bola extends Sprite {

    public Bola(double x, double y) {
        super(x, y, 1, TAM_CASILLA, TAM_CASILLA, 1.5, 1.5);
        if(Math.random() > 0.5){
            velocidadX = velocidadX * (-1);
        }
        velocidadY = velocidadY * (-1);
    }

    public Bola(double x, double y, int color, double anchura, double altura, double velocidadX, double velocidadY) {
        super(x, y, color, anchura, altura, velocidadX, velocidadY);
    }

    @Override
    public boolean mover(Nivel nivel) {
        x = x + velocidadX;
        y = y + velocidadY;
        if(x>(TAM_CASILLA * nivel.getColumnas() - anchura)){
            x = TAM_CASILLA * nivel.getColumnas() - anchura;
            velocidadX = velocidadX * (-1);
        } else if (x<0) {
            x = 0;
            velocidadX = velocidadX * (-1);
        }
        if(y>(TAM_CASILLA * nivel.getFilas() - altura)){
            y = TAM_CASILLA * nivel.getFilas() - altura;
            velocidadY = velocidadY * (-1);
            return false;
        } else if (y<0) {
            y = 0;
            velocidadY = velocidadY * (-1);
        }
        return true;
    }

    @Override
    public void pintar(GraphicsContext gc) {
        gc.setFill(COLORES[color]);
        gc.fillOval(x, y, anchura, altura);
        gc.setStroke(COLORES[2]);
        gc.strokeOval(x, y, anchura, altura);
    }

    @Override
    public boolean hayColision(Sprite otro) {
        boolean hayColision = super.hayColision(otro);
        if(hayColision && otro instanceof Ladrillo){
            if(this.x - velocidadX > (otro.x + otro.anchura) && this.x <= (otro.x + otro.anchura)){
                // choco por la izquierda
                this.setX(otro.getX()+otro.getAnchura()+1);
                this.setVelocidadX(this.getVelocidadX() * (-1));
            }
            if((this.x + anchura) - velocidadX < otro.x && (this.x + anchura) >= otro.x){
                // choco por la derecha
                this.setX(otro.getX()-anchura-1);
                this.setVelocidadX(this.getVelocidadX() * (-1));
            }
            if(this.y - velocidadY > (otro.y + otro.altura) && this.y <= (otro.y + otro.altura)){
                // choco por abajo
                this.setY(otro.getY()+otro.getAltura()+1);
                this.setVelocidadY(this.getVelocidadY() * (-1));
            }
            if((this.y + altura)  - velocidadY < otro.y && (this.y + altura) >= otro.y){
                // choco por arriba
                this.setY(otro.getY()-altura-1);
                this.setVelocidadY(this.getVelocidadY() * (-1));
            }
        } else if(hayColision && otro instanceof Paleta){

            if(this.x - velocidadX > (otro.x + otro.anchura) && this.x <= (otro.x + otro.anchura)){
                // choco por la izquierda
                this.setX(otro.getX()+otro.getAnchura()+1);
                this.setVelocidadX(this.getVelocidadX() * (-1));
            }
            if((this.x + anchura) - velocidadX < otro.x && (this.x + anchura) >= otro.x){
                // choco por la derecha
                this.setX(otro.getX()-anchura-1);
                this.setVelocidadX(this.getVelocidadX() * (-1));
            }
            if(this.y - velocidadY > (otro.y + otro.altura) && this.y <= (otro.y + otro.altura)){
                // choco por abajo
                this.setY(otro.getY()+otro.getAltura()+1);
                this.setVelocidadY(this.getVelocidadY() * (-1));
            }
            if((this.y + altura)  - velocidadY < otro.y && (this.y + altura) >= otro.y){
                // choco por arriba
                // Colocar la bola justo encima de la paleta
                this.setY(otro.getY() - this.altura - 1);

                double centroPaleta = otro.getX() + otro.getAnchura() / 2;
                double centroBola = this.x + this.anchura / 2;

                // aprox entre -1 y 1
                double impacto = (centroBola - centroPaleta) / (otro.getAnchura() / 2);
                impacto = Math.max(-1, Math.min(1, impacto));

                double anguloMin = 10;
                double anguloMax = 80;
                double angulo = anguloMin + Math.abs(impacto) * (anguloMax - anguloMin);
                // Signo izquierda / derecha
                if (impacto < 0) {
                    angulo = -angulo;
                }

                // tito pitagoras
                double velocidad = Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);

                double rad = Math.toRadians(angulo);
                velocidadX = velocidad * Math.sin(rad);
                velocidadY = -velocidad * Math.cos(rad);
            }

        }
        return hayColision;
    }
}

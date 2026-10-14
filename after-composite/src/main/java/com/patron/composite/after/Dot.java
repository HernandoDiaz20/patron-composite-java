package com.patron.composite.after;

import java.util.Objects;

/**
 * Clase Hoja (Leaf) que representa un punto geométrico en el patrón Composite.
 * Implementa la interfaz {@link Graphic} y define el comportamiento directo de dibujo y movimiento.
 * Es un elemento terminal que no contiene elementos secundarios.
 */
public class Dot implements Graphic {
    private int x;
    private int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw() {
        System.out.println("  • Punto en las coordenadas (" + x + ", " + y + ")");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dot dot)) return false;
        return x == dot.x && y == dot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Dot{x=" + x + ", y=" + y + "}";
    }
}

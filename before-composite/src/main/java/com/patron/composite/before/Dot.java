package com.patron.composite.before;

/**
 * Representa una forma geométrica de tipo Punto en el diseño anterior al patrón Composite.
 * Al no existir una abstracción común, esta clase funciona de forma aislada e independiente.
 */
public class Dot {
    private int x;
    private int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

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
    public String toString() {
        return "Dot{x=" + x + ", y=" + y + "}";
    }
}

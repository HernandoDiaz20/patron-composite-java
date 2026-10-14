package com.patron.composite.before;

/**
 * Representa una forma geométrica de tipo Círculo en el diseño anterior al patrón Composite.
 * No comparte ninguna interfaz o clase base con las demás formas.
 */
public class Circle {
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void draw() {
        System.out.println("  • Círculo con centro en (" + x + ", " + y + ") y radio " + radius);
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{x=" + x + ", y=" + y + ", radius=" + radius + "}";
    }
}

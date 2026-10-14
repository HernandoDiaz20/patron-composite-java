package com.patron.composite.after;

import java.util.Objects;

/**
 * Clase Hoja (Leaf) que representa un círculo geométrico en el patrón Composite.
 * Implementa la interfaz {@link Graphic} de manera uniforme.
 */
public class Circle implements Graphic {
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("El radio del círculo no puede ser negativo.");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
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
        if (radius < 0) {
            throw new IllegalArgumentException("El radio del círculo no puede ser negativo.");
        }
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return x == circle.x && y == circle.y && radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, radius);
    }

    @Override
    public String toString() {
        return "Circle{x=" + x + ", y=" + y + ", radius=" + radius + "}";
    }
}

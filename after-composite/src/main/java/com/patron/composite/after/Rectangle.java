package com.patron.composite.after;

import java.util.Objects;

/**
 * Clase Hoja (Leaf) que representa un rectángulo geométrico en el patrón Composite.
 * Implementa la interfaz {@link Graphic} permitiendo operaciones homogéneas de dibujo y traslación.
 */
public class Rectangle implements Graphic {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("El ancho y el alto del rectángulo deben ser positivos.");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw() {
        System.out.println("  • Rectángulo en esquina superior izquierda (" + x + ", " + y 
                + ") con ancho " + width + " y alto " + height);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("El ancho del rectángulo debe ser positivo.");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("El alto del rectángulo debe ser positivo.");
        }
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle that)) return false;
        return x == that.x && y == that.y && width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "}";
    }
}

package com.patron.composite.before;

/**
 * Representa una forma geométrica de tipo Rectángulo en el diseño anterior al patrón Composite.
 * Al no existir polimorfismo común, sus métodos son propios y deben ser invocados de forma específica.
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void draw() {
        System.out.println("  • Rectángulo en esquina superior izquierda (" + x + ", " + y + ") con ancho " + width + " y alto " + height);
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
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "}";
    }
}

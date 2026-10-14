package com.patron.composite.after;

/**
 * Interfaz Componente (Component) del patrón de diseño Composite.
 * Define la abstracción común para todos los objetos del editor gráfico,
 * tanto formas primitivas (Leaf / Hoja) como agrupaciones complejas (Composite / Compuesto).
 */
public interface Graphic {

    /**
     * Desplaza el elemento gráfico según los desplazamientos relativos especificados.
     * En hojas, actualiza directamente sus coordenadas.
     * En compuestos, delega y propaga el desplazamiento a todos sus hijos.
     *
     * @param x desplazamiento en el eje horizontal (dx)
     * @param y desplazamiento en el eje vertical (dy)
     */
    void move(int x, int y);

    /**
     * Dibuja o renderiza el elemento gráfico en la salida correspondiente.
     */
    void draw();
}

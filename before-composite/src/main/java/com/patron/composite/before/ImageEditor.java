package com.patron.composite.before;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cliente de la aplicación gráfica antes de aplicar el patrón Composite.
 *
 * PROBLEMA DE DISEÑO:
 * 1. Violación del principio Open/Closed (OCP): Si se añade una nueva forma (por ejemplo, Triangle o Line),
 *    este cliente debe modificarse agregando nuevos bloques 'else if (item instanceof ...)' tanto en
 *    {@link #draw(Object)} como en {@link #move(Object, int, int)}.
 * 2. Alto acoplamiento: El cliente conoce todas las clases concretas (Dot, Circle, Rectangle, Group).
 * 3. Falta de polimorfismo: El cliente asume la carga de inspeccionar los tipos en tiempo de ejecución
 *    y de implementar manualmente la recursión para recorrer las estructuras anidadas.
 * 4. Inseguridad de tipos: Al trabajar con Object, los errores de tipo solo se detectan en tiempo de ejecución.
 */
public class ImageEditor {
    private final List<Object> elements = new ArrayList<>();

    public void addElement(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("No se puede agregar un elemento nulo al editor.");
        }
        elements.add(element);
    }

    public void removeElement(Object element) {
        elements.remove(element);
    }

    public List<Object> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public void clear() {
        elements.clear();
    }

    /**
     * Dibuja un elemento individual o un grupo comprobando manualmente su tipo en tiempo de ejecución.
     */
    public void draw(Object item) {
        if (item == null) {
            System.out.println("  [Aviso] Elemento nulo ignorado.");
            return;
        }

        if (item instanceof Dot dot) {
            dot.draw();
        } else if (item instanceof Circle circle) {
            circle.draw();
        } else if (item instanceof Rectangle rectangle) {
            rectangle.draw();
        } else if (item instanceof Group group) {
            System.out.println("┌── [Grupo: " + group.getName() + " (" + group.getChildren().size() + " elementos)]");
            for (Object child : group.getChildren()) {
                draw(child);
            }
            System.out.println("└── [Fin de Grupo: " + group.getName() + "]");
        } else {
            throw new IllegalArgumentException("Tipo de objeto no soportado por el editor gráfico: " 
                    + item.getClass().getName());
        }
    }

    /**
     * Desplaza un elemento o todos los elementos de un grupo de forma recursiva.
     */
    public void move(Object item, int dx, int dy) {
        if (item == null) {
            return;
        }

        if (item instanceof Dot dot) {
            dot.move(dx, dy);
        } else if (item instanceof Circle circle) {
            circle.move(dx, dy);
        } else if (item instanceof Rectangle rectangle) {
            rectangle.move(dx, dy);
        } else if (item instanceof Group group) {
            // Propagación manual y recursiva a cada hijo
            for (Object child : group.getChildren()) {
                move(child, dx, dy);
            }
        } else {
            throw new IllegalArgumentException("Tipo de objeto no soportado para movimiento: " 
                    + item.getClass().getName());
        }
    }

    /**
     * Dibuja todos los elementos registrados en el lienzo del editor.
     */
    public void drawAll() {
        System.out.println("=== Lienzo del Editor Gráfico (Sin Composite) ===");
        if (elements.isEmpty()) {
            System.out.println("  (Lienzo vacío)");
        } else {
            for (Object element : elements) {
                draw(element);
            }
        }
        System.out.println("=================================================");
    }

    /**
     * Desplaza todos los elementos registrados en el editor.
     */
    public void moveAll(int dx, int dy) {
        for (Object element : elements) {
            move(element, dx, dy);
        }
    }
}

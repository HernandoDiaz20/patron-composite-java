package com.patron.composite.after;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cliente (Client) en el patrón de diseño Composite.
 * Trabaja exclusivamente con la abstracción uniforme {@link Graphic}.
 *
 * VENTAJAS RESPECTO AL DISEÑO ANTERIOR:
 * 1. Cumplimiento del Principio Open/Closed (OCP): Se pueden agregar nuevos tipos de formas
 *    (como Triangle, Polygon o Image) implementando {@link Graphic} sin tocar ni una sola línea
 *    de esta clase.
 * 2. Cero condicionales de tipo: No existe ningún 'instanceof' ni casting en este cliente.
 * 3. Tratamiento uniforme: Para este editor, una forma básica y un grupo con 100 figuras anidadas
 *    son conceptualmente idénticos; ambos responden a {@link Graphic#draw()} y {@link Graphic#move(int, int)}.
 * 4. Alta cohesión y bajo acoplamiento: La responsabilidad de la recursión y delegación recae
 *    en {@link CompoundGraphic}, no en el cliente.
 */
public class ImageEditor {
    private final List<Graphic> elements = new ArrayList<>();

    public void add(Graphic graphic) {
        if (graphic == null) {
            throw new IllegalArgumentException("No se puede registrar un elemento gráfico nulo en el editor.");
        }
        elements.add(graphic);
    }

    public void remove(Graphic graphic) {
        elements.remove(graphic);
    }

    public List<Graphic> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public void clear() {
        elements.clear();
    }

    /**
     * Dibuja cualquier elemento gráfico a través de la abstracción común, sin importar si es
     * una hoja individual (Dot, Circle, Rectangle) o un grupo compuesto (CompoundGraphic).
     */
    public void draw(Graphic graphic) {
        if (graphic != null) {
            graphic.draw();
        }
    }

    /**
     * Desplaza cualquier elemento gráfico utilizando polimorfismo puro.
     */
    public void move(Graphic graphic, int x, int y) {
        if (graphic != null) {
            graphic.move(x, y);
        }
    }

    /**
     * Dibuja todos los elementos gráficos cargados en el lienzo del editor.
     */
    public void drawAll() {
        System.out.println("=== Lienzo del Editor Gráfico (Con Patrón Composite) ===");
        if (elements.isEmpty()) {
            System.out.println("  (Lienzo vacío)");
        } else {
            for (Graphic graphic : elements) {
                draw(graphic);
            }
        }
        System.out.println("========================================================");
    }

    /**
     * Desplaza uniformemente todos los elementos gráficos cargados en el editor.
     */
    public void moveAll(int x, int y) {
        for (Graphic graphic : elements) {
            move(graphic, x, y);
        }
    }
}

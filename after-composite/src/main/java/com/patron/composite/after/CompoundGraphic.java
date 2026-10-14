package com.patron.composite.after;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase Compuesta (Composite) en el patrón de diseño estructural Composite.
 * Implementa la interfaz {@link Graphic} y mantiene una colección tipada de elementos hijos.
 *
 * Características clave del patrón demostradas aquí:
 * 1. Colección fuertemente tipada {@code List<Graphic>}: admite indistintamente hojas (Dot, Circle, Rectangle)
 *    u otros compuestos (CompoundGraphic) sin perder seguridad de tipos en tiempo de compilación.
 * 2. Transparencia y Uniformidad: el cliente trata a este contenedor exactamente igual que a una hoja.
 * 3. Delegación y Polimorfismo: {@link #draw()} y {@link #move(int, int)} iteran sobre los hijos
 *    invocando el método de la interfaz sin utilizar condicionales de tipo (sin 'instanceof').
 * 4. Estructura recursiva de árbol: los compuestos pueden contener otros compuestos.
 */
public class CompoundGraphic implements Graphic {
    private String name;
    private final List<Graphic> children = new ArrayList<>();

    public CompoundGraphic() {
        this("Compuesto sin nombre");
    }

    public CompoundGraphic(String name) {
        this.name = Objects.requireNonNullElse(name, "Compuesto");
    }

    public CompoundGraphic(String name, Graphic... graphics) {
        this(name);
        if (graphics != null) {
            for (Graphic g : graphics) {
                add(g);
            }
        }
    }

    /**
     * Agrega un elemento hijo (hoja u otro compuesto) a la colección.
     * Incluye validación de nulos y prevención básica de auto-inclusión.
     */
    public void add(Graphic child) {
        if (child == null) {
            throw new IllegalArgumentException("No se puede agregar un componente gráfico nulo.");
        }
        if (child == this) {
            throw new IllegalArgumentException("Un objeto compuesto no puede contenerse a sí mismo directamente (evasión de ciclos).");
        }
        children.add(child);
    }

    /**
     * Elimina un elemento hijo de la colección.
     */
    public void remove(Graphic child) {
        children.remove(child);
    }

    /**
     * Retorna una vista inmutable de los elementos hijos.
     */
    public List<Graphic> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Propaga el desplazamiento a todos los hijos de forma uniforme y recursiva.
     * No requiere comprobación de tipos concretos gracias al polimorfismo.
     */
    @Override
    public void move(int x, int y) {
        for (Graphic child : children) {
            child.move(x, y);
        }
    }

    /**
     * Dibuja recursivamente todos los elementos contenidos en este compuesto.
     * El método se delega uniformemente a cada hijo.
     */
    @Override
    public void draw() {
        System.out.println("┌── [Compuesto: " + name + " (" + children.size() + " hijos)]");
        for (Graphic child : children) {
            child.draw();
        }
        System.out.println("└── [Fin Compuesto: " + name + "]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompoundGraphic{name='" + name + "', childrenCount=" + children.size() + "}";
    }
}

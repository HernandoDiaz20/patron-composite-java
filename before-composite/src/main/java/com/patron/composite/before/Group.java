package com.patron.composite.before;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una agrupación de elementos gráficos en el diseño previo a Composite.
 * Debido a la ausencia de una abstracción común compartida entre formas y grupos,
 * esta clase se ve forzada a utilizar una colección genérica {@code List<Object>}.
 *
 * Desventajas evidentes:
 * - Pérdida de seguridad de tipos (type safety): cualquier objeto ajeno podría ser insertado.
 * - El grupo no sabe cómo dibujarse ni cómo moverse a sí mismo sin ayuda externa.
 */
public class Group {
    private String name;
    private final List<Object> children = new ArrayList<>();

    public Group() {
        this("Grupo sin nombre");
    }

    public Group(String name) {
        this.name = name;
    }

    public void add(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("No se puede agregar un elemento nulo al grupo.");
        }
        children.add(element);
    }

    public void remove(Object element) {
        children.remove(element);
    }

    public List<Object> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{name='" + name + "', childrenCount=" + children.size() + "}";
    }
}

package com.patron.composite.before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias - Antes de Composite (Diseño con Acoplamiento)")
class BeforeCompositeTest {

    private ImageEditor editor;

    @BeforeEach
    void setUp() {
        editor = new ImageEditor();
    }

    @Test
    @DisplayName("Debe permitir crear formas geométricas y verificar sus coordenadas iniciales")
    void testCreateShapes() {
        Dot dot = new Dot(10, 20);
        Circle circle = new Circle(30, 40, 15);
        Rectangle rectangle = new Rectangle(50, 60, 100, 80);

        assertEquals(10, dot.getX());
        assertEquals(20, dot.getY());

        assertEquals(30, circle.getX());
        assertEquals(40, circle.getY());
        assertEquals(15, circle.getRadius());

        assertEquals(50, rectangle.getX());
        assertEquals(60, rectangle.getY());
        assertEquals(100, rectangle.getWidth());
        assertEquals(80, rectangle.getHeight());

        // Asegurar que draw() se ejecuta sin lanzar excepciones
        assertDoesNotThrow(dot::draw);
        assertDoesNotThrow(circle::draw);
        assertDoesNotThrow(rectangle::draw);
    }

    @Test
    @DisplayName("Debe permitir agregar y eliminar elementos en Group")
    void testGroupAddAndRemove() {
        Group group = new Group("Grupo Prueba");
        Dot dot = new Dot(1, 1);
        Circle circle = new Circle(2, 2, 5);

        group.add(dot);
        group.add(circle);

        assertEquals(2, group.getChildren().size());
        assertTrue(group.getChildren().contains(dot));
        assertTrue(group.getChildren().contains(circle));

        group.remove(dot);
        assertEquals(1, group.getChildren().size());
        assertFalse(group.getChildren().contains(dot));
    }

    @Test
    @DisplayName("El cliente debe poder recorrer y dibujar grupos con elementos individuales")
    void testClientTraverseGroupWithIndividualElements() {
        Group group = new Group("Grupo Simple");
        group.add(new Dot(5, 5));
        group.add(new Circle(10, 10, 8));
        group.add(new Rectangle(20, 20, 30, 40));

        editor.addElement(group);
        assertDoesNotThrow(() -> editor.drawAll());
    }

    @Test
    @DisplayName("El cliente debe procesar recursivamente grupos anidados")
    void testClientNestedGroupsRecursiveProcessing() {
        Group rootGroup = new Group("Grupo Raiz");
        Dot dot1 = new Dot(0, 0);
        rootGroup.add(dot1);

        Group subGroup = new Group("Subgrupo Anidado");
        Circle subCircle = new Circle(10, 10, 5);
        subGroup.add(subCircle);

        rootGroup.add(subGroup);

        editor.addElement(rootGroup);

        assertDoesNotThrow(() -> editor.draw(rootGroup));
        assertEquals(2, rootGroup.getChildren().size());
        assertEquals(1, subGroup.getChildren().size());
    }

    @Test
    @DisplayName("El movimiento ejecutado por el cliente debe propagarse recursivamente por toda la jerarquía")
    void testMovementPropagationAcrossNestedHierarchy() {
        Dot dot = new Dot(10, 10);
        Circle circle = new Circle(20, 20, 5);
        Rectangle rect = new Rectangle(30, 30, 15, 25);

        Group subGroup = new Group("Subgrupo");
        Dot nestedDot = new Dot(50, 50);
        subGroup.add(nestedDot);

        Group mainGroup = new Group("Grupo Principal");
        mainGroup.add(dot);
        mainGroup.add(circle);
        mainGroup.add(rect);
        mainGroup.add(subGroup);

        editor.addElement(mainGroup);

        // Desplazamiento relativo de dx = +10, dy = +5
        editor.moveAll(10, 5);

        // Verificamos que todas las coordenadas de las formas se actualizaron
        assertEquals(20, dot.getX());
        assertEquals(15, dot.getY());

        assertEquals(30, circle.getX());
        assertEquals(25, circle.getY());

        assertEquals(40, rect.getX());
        assertEquals(35, rect.getY());

        assertEquals(60, nestedDot.getX());
        assertEquals(55, nestedDot.getY());
    }

    @Test
    @DisplayName("Debe lanzar excepción al recibir un tipo no soportado, demostrando el riesgo de List<Object>")
    void testUnsupportedTypeFailure() {
        Group group = new Group("Grupo Peligroso");
        // Se puede agregar un String por falta de tipado estricto
        group.add("Elemento Invalido Tipo String");

        editor.addElement(group);

        assertThrows(IllegalArgumentException.class, () -> editor.drawAll(),
                "Debe lanzar IllegalArgumentException al intentar procesar un objeto no soportado");
    }
}

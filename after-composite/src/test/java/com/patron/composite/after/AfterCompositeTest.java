package com.patron.composite.after;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias - Después de Composite (Diseño con Patrón)")
class AfterCompositeTest {

    private ImageEditor editor;

    @BeforeEach
    void setUp() {
        editor = new ImageEditor();
    }

    @Test
    @DisplayName("Cada clase hoja (Dot, Circle, Rectangle) debe implementar Graphic y operar correctamente")
    void testLeafOperations() {
        Graphic dot = new Dot(10, 20);
        Graphic circle = new Circle(30, 40, 15);
        Graphic rect = new Rectangle(50, 60, 70, 80);

        // Verificamos polimorfismo con interfaz Graphic
        assertInstanceOf(Graphic.class, dot);
        assertInstanceOf(Graphic.class, circle);
        assertInstanceOf(Graphic.class, rect);

        // Operación de dibujo sin excepciones
        assertDoesNotThrow(dot::draw);
        assertDoesNotThrow(circle::draw);
        assertDoesNotThrow(rect::draw);

        // Desplazamiento relativo
        dot.move(5, -5);
        circle.move(10, 10);
        rect.move(-10, 20);

        Dot d = (Dot) dot;
        assertEquals(15, d.getX());
        assertEquals(15, d.getY());

        Circle c = (Circle) circle;
        assertEquals(40, c.getX());
        assertEquals(50, c.getY());
        assertEquals(15, c.getRadius());

        Rectangle r = (Rectangle) rect;
        assertEquals(40, r.getX());
        assertEquals(80, r.getY());
        assertEquals(70, r.getWidth());
        assertEquals(80, r.getHeight());
    }

    @Test
    @DisplayName("CompoundGraphic debe permitir agregar, eliminar hijos y validar argumentos")
    void testCompoundGraphicAddRemoveAndValidation() {
        CompoundGraphic group = new CompoundGraphic("Grupo A");
        Dot dot = new Dot(1, 2);
        Circle circle = new Circle(3, 4, 5);

        group.add(dot);
        group.add(circle);

        assertEquals(2, group.getChildren().size());
        assertTrue(group.getChildren().contains(dot));
        assertTrue(group.getChildren().contains(circle));

        group.remove(dot);
        assertEquals(1, group.getChildren().size());
        assertFalse(group.getChildren().contains(dot));

        // Validación de elemento nulo
        assertThrows(IllegalArgumentException.class, () -> group.add(null));

        // Prevención de auto-inclusión directa
        assertThrows(IllegalArgumentException.class, () -> group.add(group));
    }

    @Test
    @DisplayName("CompoundGraphic debe ejecutar draw() e invocar la representación de todos sus hijos")
    void testCompoundGraphicDraw() {
        CompoundGraphic group = new CompoundGraphic("Grupo Test");
        group.add(new Dot(10, 10));
        group.add(new Circle(20, 20, 5));

        assertDoesNotThrow(group::draw);
    }

    @Test
    @DisplayName("El movimiento de un grupo debe propagarse a todos sus descendientes, incluyendo grupos anidados")
    void testMovementPropagationAcrossNestedDescendants() {
        Dot dot = new Dot(10, 10);
        Circle circle = new Circle(20, 20, 10);
        Rectangle rect = new Rectangle(30, 30, 40, 50);

        CompoundGraphic subGroup = new CompoundGraphic("Subgrupo Anidado");
        Dot nestedDot1 = new Dot(100, 100);
        Dot nestedDot2 = new Dot(200, 200);
        subGroup.add(nestedDot1);
        subGroup.add(nestedDot2);

        CompoundGraphic rootGroup = new CompoundGraphic("Grupo Raiz");
        rootGroup.add(dot);
        rootGroup.add(circle);
        rootGroup.add(rect);
        rootGroup.add(subGroup);

        // Desplazamiento relativo: dx = +15, dy = +25
        rootGroup.move(15, 25);

        assertEquals(25, dot.getX());
        assertEquals(35, dot.getY());

        assertEquals(35, circle.getX());
        assertEquals(45, circle.getY());

        assertEquals(45, rect.getX());
        assertEquals(55, rect.getY());

        assertEquals(115, nestedDot1.getX());
        assertEquals(125, nestedDot1.getY());

        assertEquals(215, nestedDot2.getX());
        assertEquals(225, nestedDot2.getY());
    }

    @Test
    @DisplayName("El cliente debe tratar de manera uniforme objetos individuales y compuestos vía referencia Graphic")
    void testUniformTreatmentViaGraphicInterface() {
        Graphic leaf = new Dot(5, 5);
        Graphic composite = new CompoundGraphic("Contenedor", new Circle(10, 10, 5), new Rectangle(20, 20, 10, 10));

        // El editor manipula ambos a través de la misma interfaz
        editor.add(leaf);
        editor.add(composite);

        assertEquals(2, editor.getElements().size());

        // Operaciones idénticas sin distinguir el tipo
        assertDoesNotThrow(() -> editor.draw(leaf));
        assertDoesNotThrow(() -> editor.draw(composite));

        editor.moveAll(10, 10);

        assertEquals(15, ((Dot) leaf).getX());
        assertEquals(15, ((Dot) leaf).getY());
    }

    @Test
    @DisplayName("ImageEditor debe gestionar correctamente su colección y permitir limpiar el lienzo")
    void testImageEditorCollectionManagement() {
        Dot dot = new Dot(0, 0);
        editor.add(dot);
        assertEquals(1, editor.getElements().size());

        editor.remove(dot);
        assertEquals(0, editor.getElements().size());

        editor.add(dot);
        editor.clear();
        assertTrue(editor.getElements().isEmpty());

        assertThrows(IllegalArgumentException.class, () -> editor.add(null));
    }

    @Test
    @DisplayName("Verificar que la salida capturada de draw() refleje la estructura jerárquica")
    void testConsoleOutputContainsHierarchyInfo() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent));

            CompoundGraphic group = new CompoundGraphic("Figura Compleja");
            group.add(new Dot(1, 2));
            group.draw();

            String output = outContent.toString();
            assertTrue(output.contains("Figura Compleja"));
            assertTrue(output.contains("Punto en las coordenadas (1, 2)"));
        } finally {
            System.setOut(originalOut);
        }
    }
}

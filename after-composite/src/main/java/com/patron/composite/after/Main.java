package com.patron.composite.after;

/**
 * Demostración del patrón de diseño estructural Composite en el editor gráfico.
 *
 * Muestra cómo el cliente (ImageEditor) interactúa de forma homogénea con
 * hojas individuales (Dot, Circle, Rectangle) y compuestos (CompoundGraphic),
 * permitiendo composición recursiva y propagación polimórfica de operaciones.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("  DEMOSTRACIÓN: DESPUÉS DEL PATRÓN COMPOSITE (Diseño Uniforme)");
        System.out.println("======================================================================");
        System.out.println("En este diseño:");
        System.out.println("1. Todos los elementos implementan la interfaz común 'Graphic'.");
        System.out.println("2. CompoundGraphic gestiona una colección tipada List<Graphic>.");
        System.out.println("3. ImageEditor trata tanto a hojas como a compuestos de forma 100% polimórfica.");
        System.out.println("4. No se utiliza ningún 'instanceof' ni casting en el cliente ni en los compuestos.\n");

        ImageEditor editor = new ImageEditor();

        // 1. Creación de hojas individuales (Leaf)
        Graphic dot1 = new Dot(5, 10);
        Graphic circle1 = new Circle(15, 20, 5);
        Graphic rect1 = new Rectangle(30, 40, 10, 20);

        // 2. Creación de un compuesto anidado (Composite)
        CompoundGraphic subGroup = new CompoundGraphic("Subgrupo Detalles");
        Graphic leftEye = new Dot(13, 19);
        Graphic rightEye = new Dot(17, 19);
        subGroup.add(leftEye);
        subGroup.add(rightEye);

        // 3. Creación del compuesto principal que contiene hojas y el subgrupo
        CompoundGraphic mainGroup = new CompoundGraphic("Grupo Principal");
        mainGroup.add(dot1);
        mainGroup.add(circle1);
        mainGroup.add(rect1);
        mainGroup.add(subGroup); // Composición recursiva en árbol

        // 4. El cliente recibe el grupo principal a través de la interfaz Graphic
        editor.add(mainGroup);

        // 5. Primera operación de dibujo sobre el grupo principal
        System.out.println(">>> 1. ESTADO INICIAL: Invocando draw() a través del editor...");
        editor.drawAll();

        // 6. Operación de movimiento relativo
        int deltaX = 10;
        int deltaY = 15;
        System.out.println("\n>>> 2. OPERACIÓN DE MOVIMIENTO: Desplazando (" + deltaX + ", " + deltaY + ")...");
        System.out.println("La operación move() se propaga automáticamente por toda la jerarquía gracias a la delegación.");
        editor.moveAll(deltaX, deltaY);

        // 7. Segunda operación de dibujo para observar el cambio de coordenadas
        System.out.println("\n>>> 3. ESTADO FINAL: Invocando draw() tras el movimiento...");
        editor.drawAll();

        // 8. Demostración de tratamiento uniforme hoja vs compuesto
        System.out.println("\n>>> 4. DEMOSTRACIÓN DE UNIFORMIDAD (Hoja y Compuesto tratados idénticamente):");
        Graphic singleDot = new Dot(99, 99);
        System.out.println("Dibujando una hoja individual:");
        editor.draw(singleDot);
        System.out.println("Dibujando un subgrupo compuesto:");
        editor.draw(subGroup);

        System.out.println("\nDemostración finalizada exitosamente.");
    }
}

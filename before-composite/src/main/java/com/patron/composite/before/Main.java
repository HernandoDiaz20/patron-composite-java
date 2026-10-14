package com.patron.composite.before;

/**
 * Clase principal que ejecuta la demostración del diseño ANTES del patrón Composite.
 * Muestra el comportamiento del cliente ImageEditor manipulando formas individuales
 * y grupos anidados mediante verificación de tipos con 'instanceof'.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("  DEMOSTRACIÓN: ANTES DEL PATRÓN COMPOSITE (Diseño con Acoplamiento)");
        System.out.println("======================================================================");
        System.out.println("En este diseño:");
        System.out.println("1. Dot, Circle, Rectangle y Group no comparten interfaz común.");
        System.out.println("2. Group almacena 'Object', perdiendo seguridad de tipos.");
        System.out.println("3. ImageEditor debe verificar con 'instanceof' y recorrer manualmente la jerarquía.\n");

        ImageEditor editor = new ImageEditor();

        // 1. Creación de formas individuales
        Dot dot1 = new Dot(5, 10);
        Circle circle1 = new Circle(15, 20, 5);
        Rectangle rect1 = new Rectangle(30, 40, 10, 20);

        // 2. Creación de un subgrupo anidado
        Group subGroup = new Group("Subgrupo Detalles");
        Dot leftEye = new Dot(13, 19);
        Dot rightEye = new Dot(17, 19);
        subGroup.add(leftEye);
        subGroup.add(rightEye);

        // 3. Creación del grupo principal que contiene formas individuales y el subgrupo
        Group mainGroup = new Group("Grupo Principal");
        mainGroup.add(dot1);
        mainGroup.add(circle1);
        mainGroup.add(rect1);
        mainGroup.add(subGroup);

        editor.addElement(mainGroup);

        // 4. Primera operación de dibujo
        System.out.println(">>> 1. ESTADO INICIAL: Dibujando jerarquía de elementos...");
        editor.drawAll();

        // 5. Operación de movimiento
        int deltaX = 10;
        int deltaY = 15;
        System.out.println("\n>>> 2. OPERACIÓN DE MOVIMIENTO: Desplazando (" + deltaX + ", " + deltaY + ")...");
        System.out.println("El cliente ImageEditor recorre recursivamente el grupo usando 'instanceof'.");
        editor.moveAll(deltaX, deltaY);

        // 6. Segunda operación de dibujo para comprobar actualización
        System.out.println("\n>>> 3. ESTADO FINAL: Dibujando elementos tras el desplazamiento...");
        editor.drawAll();

        System.out.println("\nDemostración finalizada exitosamente.");
    }
}

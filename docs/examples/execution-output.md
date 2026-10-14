# Ejemplos de Ejecución y Salidas del Sistema

Este directorio contiene registros de ejecución real de ambas implementaciones del editor gráfico.

---

## 1. Salida de la Versión Antes del Patrón (`before-composite`)

Comando ejecutado:
```bash
mvn exec:java -pl before-composite
```

Salida capturada en consola:
```text
======================================================================
  DEMOSTRACIÓN: ANTES DEL PATRÓN COMPOSITE (Diseño con Acoplamiento)
======================================================================
En este diseño:
1. Dot, Circle, Rectangle y Group no comparten interfaz común.
2. Group almacena 'Object', perdiendo seguridad de tipos.
3. ImageEditor debe verificar con 'instanceof' y recorrer manualmente la jerarquía.

>>> 1. ESTADO INICIAL: Dibujando jerarquía de elementos...
=== Lienzo del Editor Gráfico (Sin Composite) ===
┌── [Grupo: Grupo Principal (4 elementos)]
  • Punto en las coordenadas (5, 10)
  • Círculo con centro en (15, 20) y radio 5
  • Rectángulo en esquina superior izquierda (30, 40) con ancho 10 y alto 20
┌── [Grupo: Subgrupo Detalles (2 elementos)]
  • Punto en las coordenadas (13, 19)
  • Punto en las coordenadas (17, 19)
└── [Fin de Grupo: Subgrupo Detalles]
└── [Fin de Grupo: Grupo Principal]
=================================================

>>> 2. OPERACIÓN DE MOVIMIENTO: Desplazando (10, 15)...
El cliente ImageEditor recorre recursivamente el grupo usando 'instanceof'.

>>> 3. ESTADO FINAL: Dibujando elementos tras el desplazamiento...
=== Lienzo del Editor Gráfico (Sin Composite) ===
┌── [Grupo: Grupo Principal (4 elementos)]
  • Punto en las coordenadas (15, 25)
  • Círculo con centro en (25, 35) y radio 5
  • Rectángulo en esquina superior izquierda (40, 55) con ancho 10 y alto 20
┌── [Grupo: Subgrupo Detalles (2 elementos)]
  • Punto en las coordenadas (23, 34)
  • Punto en las coordenadas (27, 34)
└── [Fin de Grupo: Subgrupo Detalles]
└── [Fin de Grupo: Grupo Principal]
=================================================

Demostración finalizada exitosamente.
```

---

## 2. Salida de la Versión Con Patrón Composite (`after-composite`)

Comando ejecutado:
```bash
mvn exec:java -pl after-composite
```

Salida capturada en consola:
```text
======================================================================
  DEMOSTRACIÓN: DESPUÉS DEL PATRÓN COMPOSITE (Diseño Uniforme)
======================================================================
En este diseño:
1. Todos los elementos implementan la interfaz común 'Graphic'.
2. CompoundGraphic gestiona una colección tipada List<Graphic>.
3. ImageEditor trata tanto a hojas como a compuestos de forma 100% polimórfica.
4. No se utiliza ningún 'instanceof' ni casting en el cliente ni en los compuestos.

>>> 1. ESTADO INICIAL: Invocando draw() a través del editor...
=== Lienzo del Editor Gráfico (Con Patrón Composite) ===
┌── [Compuesto: Grupo Principal (4 hijos)]
  • Punto en las coordenadas (5, 10)
  • Círculo con centro en (15, 20) y radio 5
  • Rectángulo en esquina superior izquierda (30, 40) con ancho 10 y alto 20
┌── [Compuesto: Subgrupo Detalles (2 hijos)]
  • Punto en las coordenadas (13, 19)
  • Punto en las coordenadas (17, 19)
└── [Fin Compuesto: Subgrupo Detalles]
└── [Fin Compuesto: Grupo Principal]
========================================================

>>> 2. OPERACIÓN DE MOVIMIENTO: Desplazando (10, 15)...
La operación move() se propaga automáticamente por toda la jerarquía gracias a la delegación.

>>> 3. ESTADO FINAL: Invocando draw() tras el movimiento...
=== Lienzo del Editor Gráfico (Con Patrón Composite) ===
┌── [Compuesto: Grupo Principal (4 hijos)]
  • Punto en las coordenadas (15, 25)
  • Círculo con centro en (25, 35) y radio 5
  • Rectángulo en esquina superior izquierda (40, 55) con ancho 10 y alto 20
┌── [Compuesto: Subgrupo Detalles (2 hijos)]
  • Punto en las coordenadas (23, 34)
  • Punto en las coordenadas (27, 34)
└── [Fin Compuesto: Subgrupo Detalles]
└── [Fin Compuesto: Grupo Principal]
========================================================

>>> 4. DEMOSTRACIÓN DE UNIFORMIDAD (Hoja y Compuesto tratados idénticamente):
Dibujando una hoja individual:
  • Punto en las coordenadas (99, 99)
Dibujando un subgrupo compuesto:
┌── [Compuesto: Subgrupo Detalles (2 hijos)]
  • Punto en las coordenadas (23, 34)
  • Punto en las coordenadas (27, 34)
└── [Fin Compuesto: Subgrupo Detalles]

Demostración finalizada exitosamente.
```

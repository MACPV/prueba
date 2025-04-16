# 💸 Simulador de Balance Financiero con OpenCSV (Java)

## 📝 Introducción

Este proyecto fue desarrollado como parte de un reto técnico. Su objetivo es **leer un archivo CSV que contiene transacciones financieras** y simular el resultado de operaciones bancarias, diferenciando ingresos ("crédito") y egresos ("débito").

A partir de estos datos, la aplicación:

- Calcula el total de créditos y débitos.
- Identifica la transacción de mayor monto.
- Determina si el balance final es positivo o negativo.
- Cuenta cuántas transacciones hay de cada tipo.

---

##  Instrucciones de Ejecución

### 1. Requisitos

- Java 8 o superior
- Maven (si se usa como gestor de dependencias)

### 2. Instalación de Dependencias

Si estás usando Maven, incluye la siguiente dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.10</version>
</dependency>
```

También se utiliza Lombok para reducir la escritura de getters y setters. Asegúrate de tenerlo en tu IDE y tu `pom.xml`:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

### 3. Ejecución del Programa

- Coloca el archivo `datitos.csv` dentro de `src/main/resources/`.
- Ejecuta la clase `Simulador.java` desde tu IDE o por línea de comandos con:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.entrevista.prueba.Simulador"
```

---

## Enfoque y Solución

- Se usó **OpenCSV** para mapear cada fila del archivo CSV a una clase tipo bean (`CsvObjetos`).
- Se crearon estructuras condicionales para separar los tipos de transacción (`crédito`, `débito`).
- Se usaron acumuladores para llevar el total de cada tipo.
- Se identificó la transacción de mayor monto mediante comparación continua.
- Se aplicó `getClass().getClassLoader().getResource()` para asegurar que el archivo se lea correctamente desde `resources`.

#### Decisiones de diseño:

- Uso de anotaciones `@CsvBindByName` para facilitar el mapeo directo entre CSV y objeto Java.
- Separación clara entre lógica de lectura y lógica de cálculo.
- Uso de Lombok para simplificar el código del modelo.

---

## 🗂️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── entrevista/
│   │           └── prueba/
│   │               ├── CsvObjetos.java     # Bean que representa cada fila del CSV
│   │               └── Simulador.java      # Lógica principal de la aplicación
│   └── resources/
│       └── datitos.csv                     # Archivo de entrada con transacciones
```

---

## Documentación y Calidad del Código

El código está **comentado** y escrito de forma clara y ordenada. Se utilizan nombres descriptivos para las variables, lo que facilita su comprensión.

### Ejemplo de comentarios en el código:

```java
// Verifica el tipo de transacción y actualiza los totales
if (co.getTipo().equalsIgnoreCase("débito")) {
    totalDebitos += co.getMonto();
    contDebito++;
}
```

También se muestra un mensaje de error amigable si no se encuentra el archivo o si el CSV está mal formado:

```java
catch (Exception e) {
    System.err.println("Error al leer el archivo :" + e.getMessage());
}
```

---

## Autor

Desarrollado por **Aldo Alberto Pintado Encajima** como solución a un reto técnico de programación Codeable en Java.

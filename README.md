# Sistema de Gestión de Concierto Masivo (SGCM)

Sistema de gestión de conciertos masivos desarrollado en Java, que aplica el framework de Colecciones de Java y una arquitectura por capas profesional. Simula un escenario real del entorno corporativo: registro de usuarios, venta de boletas, organización de estadios y control de ingresos a eventos musicales.

El proyecto demuestra dominio de **colecciones de Java** (List, Set, Map), **complejidad algorítmica (Big-O)**, **generics**, **Repository Pattern**, **Service Layer** y principios de **Clean Code** y **SOLID**.

---

## Objetivo del Proyecto

- Dominar la elección de colecciones de Java según criterios funcionales y de rendimiento (Big-O)
- Aplicar la arquitectura por capas (model, repository, services, util, enums, app) en un sistema real
- Implementar el patrón Repository con persistencia en memoria usando colecciones
- Practicar validaciones de dominio con excepciones y mensajes claros
- Consolidar buenas prácticas de Clean Code y encapsulamiento

---

## Arquitectura del Proyecto

Estructura modular por capas bajo el paquete raíz `com.cate.SGCM`:

```
src/com/cate/SGCM/
├── app/                    # Punto de entrada del sistema
│   └── Main.java
├── enums/                  # Enumeradores del dominio
│   ├── EstadoBoleta.java
│   ├── EstadoConcierto.java
│   ├── Genero.java
│   ├── GeneroMusical.java
│   └── TipoBoleta.java
├── model/                  # Entidades de negocio
│   ├── Banda.java
│   ├── Boleto.java
│   ├── Cancion.java
│   ├── Concierto.java
│   ├── ControlIngreso.java
│   ├── Estadio.java
│   ├── Silla.java
│   └── Usuario.java
├── repository/             # Persistencia en memoria (simula base de datos)
│   ├── ControlIngreso.java
│   └── RepositorioGenerico.java
├── services/               # Lógica de negocio
│   ├── IngresoService.java
│   ├── TaquillaVentaService.java
│   └── UsuarioService.java
└── util/                   # Clases de utilidad
    ├── GeneradorId.java
    └── ValidacionesAtributos.java
```

### Responsabilidad de cada capa

| Capa | Responsabilidad |
|---|---|
| `app` | Punto de entrada; orquesta los servicios y valida el flujo completo |
| `model` | Entidades de negocio y sus reglas de dominio (encapsulamiento) |
| `repository` | Persistencia con colecciones en memoria; aísla al resto del sistema del mecanismo de almacenamiento |
| `services` | Lógica de negocio pura, coordinando repositorios y aplicando validaciones |
| `enums` | Valores fijos del dominio (estados, géneros, tipos de boleta) |
| `util` | Utilidades genéricas (generación de IDs, validación de atributos) |

La dependencia entre capas fluye en un solo sentido: `app` → `services` → `repository` → `model`. Ninguna capa interna conoce a las capas que la invocan, lo que permite sustituir la persistencia en memoria por una base de datos real sin modificar los servicios.

### Repository Pattern

`RepositorioGenerico<T, ID>` implementa el CRUD sobre un `Map<ID, T>`, de modo que los repositorios de entidad reutilizan la misma lógica de persistencia en lugar de duplicarla:

| Operación | Complejidad | Implementación |
|---|---|---|
| `guardarInformacion` | O(1) promedio | `Map.put` |
| `buscarRegistro` | O(1) promedio | `Map.get` |
| `eliminarRegistro` | O(1) promedio | `Map.remove` |
| `listarRegistros` | O(n) | Copia defensiva de `Map.values()` a `ArrayList` |

El parámetro `ID` corresponde al identificador natural de cada entidad, lo que permite que el `Map` funcione como índice y no solo como almacenador.

---

## Contenidos Técnicos

### Modelo de Dominio

- **Usuario**: identificación, datos personales y género. Unicidad por `identificacion` mediante `equals` y `hashCode`.
- **Banda**: agrupación musical con género, año de fundación y canciones asociadas en un `Set<Cancion>`.
- **Cancion**: repertorio de cada banda (nombre, género musical, duración, año de lanzamiento).
- **Concierto**: evento programado con fecha, hora, estadio y banda asociadas.
- **Estadio**: recinto con disposición de sillas por categoría, generada a partir de la capacidad declarada.
- **Silla**: ubicación por fila y columna, con categoría y control de disponibilidad.
- **Boleto**: acceso clasificado por tipo y estado, con marcas de tiempo de creación, venta y cancelación.
- **ControlIngreso**: registro de acceso de un asistente al evento, con fecha de ingreso y boleto asociado.

### Colecciones Utilizadas

Cada colección responde a criterios funcionales **y** de rendimiento:

- **HashMap**: almacén interno de `RepositorioGenerico`. Inserción, búsqueda y eliminación en O(1) promedio, frente al O(n) que exigiría recorrer una lista de forma lineal.
- **ArrayList**: sillas del estadio y resultados de listado. Acceso por índice en O(1) e iteración eficiente con caché de CPU, además de conservar el orden de inserción.
- **HashSet**: canciones de cada banda. Inserción y búsqueda en O(1) promedio, garantizando la unicidad del repertorio por `hashCode` sin verificación adicional.
- **Set\<Usuario\>**: contrato de retorno del servicio de usuarios, coherente con la garantía de unicidad por identificación.

### Enumeradores

- `EstadoBoleta`: ACTIVO, VENDIDO, CANCELADO
- `EstadoConcierto`: PROGRAMADO, EN_CURSO, FINALIZADO, CANCELADO
- `Genero`: MASCULINO, FEMENINO
- `GeneroMusical`: POP, ROCK, JAZZ, ELECTRONICA, CLASICA, FOLK, HIP_HOP, BLUES
- `TipoBoleta`: VIP (zona "VIP", $150.00), GENERAL (zona "General", $50.00) — enum con atributos, que encapsula el precio y la zona de cada tipo de boleta

### Buenas Prácticas Implementadas

- Separación de responsabilidades en capas con dependencias en un solo sentido
- Repository Pattern genérico con persistencia en memoria
- Copia defensiva en los listados, para no exponer el estado interno del repositorio
- Validaciones centralizadas en `ValidacionesAtributos` con mensajes claros y tipado del campo
- Encapsulamiento con atributos privados y setters que validan antes de asignar
- Enumeraciones para valores fijos del dominio, con atributos donde el valor requiere información adicional
- Generación automática de IDs por entidad y códigos de boleto aleatorios
- `equals` y `hashCode` en las entidades que participan como elementos de colecciones
- `LocalDateTime` para marcas temporales y `StringBuilder` en las implementaciones de `toString`
- Constantes en `UPPER_SNAKE_CASE` para los parámetros de la generación de sillas

---

## Stack Tecnológico

- **Java 21**: Lenguaje de programación principal
- **IntelliJ IDEA**: IDE de desarrollo
- **Git**: Control de versiones
- **Sin Maven/Gradle**: Proyecto Java plano; compilación directa con `javac`

---

## Como Ejecutar el Proyecto

### Prerrequisitos

- JDK 21 o superior
- IntelliJ IDEA u otro IDE compatible con Java

### Compilacion por terminal

Desde la raíz del proyecto:

```bash
# Linux / macOS
find src -name "*.java" > sources.txt && javac -d out -encoding UTF-8 @sources.txt
java -cp out com.cate.SGCM.app.Main
```

```powershell
# Windows PowerShell
Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName } | Out-File sources.txt
javac -d out -encoding UTF-8 @sources.txt
java -cp out com.cate.SGCM.app.Main
```

### Ejecucion en IntelliJ IDEA

1. File -> Open -> Seleccionar el directorio del proyecto
2. Esperar a que IntelliJ indexe el proyecto
3. Abrir `src/com/cate/SGCM/app/Main.java`
4. Click derecho -> Run 'Main'

---

## Roadmap de Evolución

- [ ] Pruebas unitarias automatizadas con JUnit 5
- [ ] Paquete `exceptions` con excepciones propias del dominio
- [ ] Paquete `interfaces` con contratos entre capas
- [ ] Repositorios de entidad construidos sobre `RepositorioGenerico`
- [ ] Integración de la asignación de asientos en la venta de boletas
- [ ] Persistencia real con JDBC/JPA
- [ ] Exposición del sistema como API REST con Spring Boot

---

## Autora

**Caterine Salinas Bolanos** - Desarrolladora Java Junior

Repositorio desarrollado como portafolio tecnico y proceso de fortalecimiento en desarrollo Java profesional, enfocado en el dominio de Colecciones y arquitectura por capas.

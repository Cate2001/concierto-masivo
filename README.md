# Sistema de Gestión de Concierto Masivo (SGCM)

Sistema de gestión de conciertos masivos desarrollado en Java, que aplica el framework de Colecciones de Java y una arquitectura por capas profesional. Simula un escenario real del entorno corporativo: registro de usuarios, venta de boletas, organización de estadios y control de ingresos a eventos musicales.

El proyecto demuestra dominio de **colecciones de Java** (List, Set, Map), **complejidad algorítmica (Big-O)**, **Repository Pattern**, **Service Layer** y principios de **Clean Code** y **SOLID**.

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
│   ├── ControlEntrada.java
│   ├── Estadio.java
│   ├── Silla.java
│   └── Usuario.java
├── repository/             # Persistencia en memoria (simula base de datos)
│   ├── BandaRepository.java
│   ├── BoletoRepository.java
│   ├── ConciertoRepository.java
│   ├── ControlEntradaRepository.java
│   ├── EstadioRepository.java
│   └── UsuarioRepository.java
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
| `repository` | Simulación de persistencia con colecciones en memoria |
| `services` | Lógica de negocio pura, coordinando repositorios y aplicando validaciones |
| `enums` | Valores fijos del dominio (estados, géneros, tipos de boleta) |
| `util` | Utilidades genéricas (generación de IDs, validación de atributos) |

---

## Contenidos Técnicos

### Modelo de Dominio

- **Usuario**: identificación, datos personales y género. Registro único por identificación.
- **Banda**: agrupación musical con canciones asociadas.
- **Cancion**: repertorio de cada banda.
- **Concierto**: evento programado (PROGRAMADO, EN_CURSO, FINALIZADO).
- **Estadio**: recinto con disposición de sillas por categoría.
- **Silla**: ubicaciones organizadas por zona dentro del estadio.
- **Boleto**: compra de acceso clasificada por tipo (VIP $150.00, General $50.00) y estado (DISPONIBLE, VENDIDO).
- **ControlEntrada**: validación de acceso de asistentes en el día del evento

### Colecciones Utilizadas

- **ArrayList**: almacenamiento de bandas, boletas, conciertos y estadios en los repositorios. Acceso por índice en O(1) e iteración eficiente.
- **HashSet**: usuarios y bandas sin duplicados. Inserción y búsqueda en O(1) promedio, garantizando unicidad por `hashCode`.
- **LinkedHashSet**: mantiene orden de inserción de bandas conservando la unicidad del Set.

### Enumeradores

- `EstadoBoleta`: DISPONIBLE, VENDIDO
- `EstadoConcierto`: PROGRAMADO, EN_CURSO, FINALIZADO
- `Genero`: MASCULINO, FEMENINO
- `GeneroMusical`: POP, ROCK, JAZZ, ELECTRONICA, CLASICA, FOLK, HIP_HOP, BLUES
- `TipoBoleta`: VIP (zona "VIP", $150.00), GENERAL (zona "General", $50.00)

### Buenas Prácticas Implementadas

- Separación de responsabilidades en capas
- Repository Pattern con persistencia en memoria
- Validaciones de dominio con excepciones y mensajes claros
- Encapsulamiento con atributos privados y acceso controlado
- Enumeraciones para valores fijos del dominio
- Generación automática de IDs de entidades
- Manejo de duplicados mediante colecciones Set

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

```bash
javac -d out -encoding UTF-8 src/com/cate/SGCM/**/*.java
java -cp out com.cate.SGCM.app.Main
```

### Ejecucion en IntelliJ IDEA

1. File -> Open -> Seleccionar el directorio del proyecto
2. Esperar a que IntelliJ indexe el proyecto
3. Abrir `src/com/cate/SGCM/app/Main.java`
4. Click derecho -> Run 'Main'

---

## Metricas del Proyecto

- Total de archivos Java: **25**
- Entidades de negocio (model): **8**
- Repositorios en memoria: **6**
- Servicios de negocio: **3**
- Enumeradores: **5**
- Clases de utilidad: **2**
- Lineas de codigo: **~890** (sin lineas vacias)

---

## Estado de Desarrollo

- Arquitectura por capas completa (app, model, repository, services, util, enums)
- Registro de usuarios con validacion de unicidad
- Consulta de usuarios registrados
- Generacion de IDs y validacion de atributos de dominio

**Nota tecnica**: el codigo fue movido recientemente desde el proyecto `ejercicios-logica` a este repositorio independiente. La declaracion de paquetes (`com.cate.practica.nuevos.conceptos.colecciones.SGCM.*`) aun no se ha actualizado a la nueva estructura de directorios (`com.cate.SGCM.*`). Antes de compilar, la declaracion de paquetes debe sincronizarse con la ruta fisica de las clases.

---

## Roadmap de Mejoras Futuras

- [ ] Sincronizar declaracion de paquetes con la estructura de directorios
- [ ] Completar la logica de venta de boletas (TaquillaVentaService)
- [ ] Implementar control de ingresos por concierto (IngresoService)
- [ ] Agregar pruebas automaticas con JUnit 5
- [ ] Aplicar principios SOLID avanzados y patrones de diseno
- [ ] Agregar persistencia real con JDBC/JPA
- [ ] Exponer el sistema como API REST con Spring Boot

---

## Autora

**Caterine Salinas Bolanos** - Desarrolladora Java Junior

Repositorio desarrollado como portafolio tecnico y proceso de fortalecimiento en desarrollo Java profesional, enfocado en el dominio de Colecciones y arquitectura por capas.

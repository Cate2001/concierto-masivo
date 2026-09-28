# Estado del Proyecto — SGCM

Documento de seguimiento técnico. El `README.md` describe la arquitectura y el alcance del sistema; este archivo registra el **avance real**, los bloqueos y el trabajo pendiente.

- **Último commit analizado:** `df7054b` — *"Repositorio- Se elimino y agrego clases para el repositorio usando genericos"*
- **Entorno verificado:** `javac 21.0.12.1`

---

## 1. Estado de Compilación

El proyecto **no compila**. 8 errores por referencias a clases que fueron eliminadas en `df7054b` al migrar al repositorio genérico.

| Archivo | Línea | Referencia ausente |
|---|---|---|
| `app/Main.java` | 12, 13 | `UsuarioRepository`, `BoletoRepository` |
| `services/UsuarioService.java` | 11, 12, 14 | `UsuarioRepository`, `BoletoRepository` |
| `services/UsuarioService.java` | 42 | `boleto.buscarBoletosActivosUsuario(...)` |

### Bloqueo secundario en `UsuarioService`

`actualizarUsuario()` (línea 35) declara `boolean` sin `return`. Es un error de compilación que queda enmascarado por los anteriores y saltará en cuanto se resuelvan las referencias.

---

## 2. Métricas Reales

| Capa | Archivos |
|---|---|
| `app` | 1 |
| `enums` | 5 |
| `model` | 8 |
| `repository` | 2 |
| `services` | 3 |
| `util` | 2 |
| **Total** | **21** |

- Líneas de código: **1028** (838 sin líneas vacías)
- Repositorios por entidad (`UsuarioRepository`, `BoletoRepository`, `BandaRepository`, `ConciertoRepository`, `EstadioRepository`): **eliminados** y sustituidos por `RepositorioGenerico`

---

## 3. Estado por Capa

| Capa | Estado | Detalle |
|---|---|---|
| `model` | ⚠️ Parcial | Entidades mayormente implementadas; faltan getters, transiciones de estado e integración `Silla`/`Boleto` |
| `enums` | ✅ Completo | 5 enums funcionales y usados |
| `repository` | ❌ Incompleto | `RepositorioGenerico` funcional; `ControlIngreso` sin terminar; no existen repositorios por entidad |
| `services` | ⚠️ Incompleto | `UsuarioService` con 4 de 5 métodos; `TaquillaVentaService` e `IngresoService` vacíos |
| `util` | ⚠️ Con defecto | Funcionales, con un error en `ValidarEsPar` |
| `app` | ❌ Roto | No compila |

---

## 4. Detalle por Clase

| Clase | Estado | Observaciones |
|---|---|---|
| `Usuario` | ✅ Completo | Validaciones, `equals`/`hashCode` por identificación, `toString` |
| `Banda` | ⚠️ Con defectos | Ver sección 5 |
| `Cancion` | ✅ Completo | |
| `Concierto` | ⚠️ Parcial | El estado queda siempre en `PROGRAMADO`; falta getter de `estado` y no hay transiciones |
| `Estadio` | ⚠️ Parcial | Genera sillas por zona; falta getter de `sillasEstadio`; typo en `agregrarSillas()` |
| `Silla` | ⚠️ Parcial | Lógica de ocupar/desocupar correcta, pero la clase es *package-private* |
| `Boleto` | ⚠️ Con defectos | Ver sección 5 |
| `ControlIngreso` (model) | ✅ Completo | Registro de acceso con fecha y boleto asociado |
| `RepositorioGenerico` | ✅ Completo | CRUD sobre `HashMap` con copia defensiva |
| `ControlIngreso` (repository) | ❌ Mínimo | Ver sección 5 |
| `UsuarioService` | ⚠️ Parcial | `actualizarUsuario()` sin `return`; `eliminarUsuario()` depende de `BoletoRepository`; typo en `mostarUsuariosRegistrados()` |
| `TaquillaVentaService` | ❌ Vacío | Sin implementación |
| `IngresoService` | ❌ Vacío | Sin implementación |
| `GeneradorId` | ✅ Completo | Contadores por entidad + código alfanumérico de boleto con `SecureRandom` |
| `ValidacionesAtributos` | ⚠️ Con defecto | Ver sección 5 |

---

## 5. Defectos de Lógica

| # | Ubicación | Defecto |
|---|---|---|
| 1 | `ValidacionesAtributos.java:61` | `ValidarEsPar` evalúa `numero % 10 == 0`, es decir divisibilidad por 10, no paridad. Una capacidad 30 (par) sería rechazada. Además el nombre sigue convención `PascalCase` en una clase `final` de métodos `static` |
| 2 | `Banda.java:86` | `ValidacionesAtributos.validarNullVacio("Nombre canción", nombreCancion)` — argumentos invertidos: el valor ocupa la posición de `campo` y viceversa. El mensaje de excepción resultante no identifica el campo real |
| 3 | `Banda.java:71-83` | `eliminarCancion` invoca `canciones.remove()` sobre el mismo `HashSet` que está iterando, y lanza `IllegalArgumentException` para señalar una operación exitosa |
| 4 | `Banda.java:90` | `buscarCancion` devuelve `null` en vez de lanzar excepción, incoherente con el resto de validaciones de dominio |
| 5 | `Boleto.java:59` | `venderBoleta()` invoca `calcularPrecioBoleta()` y descarta el resultado; el precio final nunca se almacena |
| 6 | `Boleto.java:18,46-48` | El atributo `concierto` se declara pero nunca se asigna ni tiene getter; tampoco existe getter de `estadoBoleta` |
| 7 | `Silla.java:7` | `class Silla` sin modificador de acceso: no puede instanciarse ni consultarse desde `services` o `app` |
| 8 | `repository/ControlIngreso.java:9` | `List<ControlIngreso> almacen` se resuelve a la propia clase del repositorio, no a `model.ControlIngreso`. El repositorio se almacena a sí mismo |
| 9 | `repository/ControlIngreso.java:15-20` | `buscarRegistro()` sin implementar; `listarRegistros()` devuelve una lista vacía sin consultar el almacén |
| 10 | `app/Main.java:15,31` | Se registra un usuario con identificación `123456789` y se consulta `1123132324`, por lo que el flujo siempre cae en "El usuario no existe" |
| 11 | `ValidacionesAtributos.java:25-37` | `validarTelefono` y `validarEmail` no son usados por ninguna entidad |
| 12 | `Banda.java:15` | `HashSet` sin orden; `listarCanciones()` entrega un `ArrayList` cuyo orden no es el de inserción |

---

## 6. Funcionalidades Implementadas

- ✅ Arquitectura por capas (app, model, repository, services, util, enums)
- ✅ Repositorio genérico con CRUD completo sobre `HashMap`
- ✅ Validaciones de dominio centralizadas con mensajes claros
- ✅ Generación automática de IDs por entidad y códigos de boleto
- ✅ Generación de sillas del estadio por zona (VIP/General) a partir de la capacidad
- ✅ Gestión de canciones por banda (agregar, eliminar, buscar, listar)
- ✅ Transiciones de estado del boleto (activo → vendido / cancelado)
- ✅ Enums con atributos (`TipoBoleta` con zona y precio)
- ✅ `equals`/`hashCode` en entidades usadas como elemento de colección

---

## 7. Trabajo Pendiente

### Prioridad 1 — Desbloquear compilación

- [ ] Migrar `Main` y `UsuarioService` a `RepositorioGenerico`
- [ ] Definir el tipo de clave `ID` para `Usuario` (`identificacion` o `id` generado)
- [ ] Implementar o cambiar la firma de `UsuarioService.actualizarUsuario()`
- [ ] Decidir la estrategia para `mostarUsuariosRegistrados()`, que no existe en el CRUD genérico

### Prioridad 2 — Corregir defectos

- [ ] `ValidarEsPar`: verificar paridad en lugar de divisibilidad por 10
- [ ] `Banda.buscarCancion`: corregir argumentos y unificar el contrato de retorno
- [ ] `Banda.eliminarCancion`: evitar modificar la colección durante la iteración y no usar excepción como control de flujo
- [ ] `Boleto.venderBoleta`: persistir el precio calculado
- [ ] `Boleto`: agregar getters de `estadoBoleta` y `concierto`
- [ ] `Silla`: habilitar el acceso desde otras capas
- [ ] `repository.ControlIngreso`: resolver la colisión de nombre con la entidad
- [ ] `app/Main`: alinear la identificación consultada con la registrada

### Prioridad 3 — Completar funcionalidad

- [ ] Repositorios de entidad (Usuario, Boleto, Concierto, Estadio, Banda) sobre `RepositorioGenerico`
- [ ] Lógica de venta de boletas en `TaquillaVentaService`
- [ ] Control de ingresos por concierto en `IngresoService`
- [ ] Integración de `Silla` dentro de `Boleto` (asignación de asiento al comprar)
- [ ] Transiciones de estado en `Concierto` con validación de transiciones válidas
- [ ] Getters faltantes en `Estadio` y `Concierto`
- [ ] Preservar orden de inserción en las canciones de `Banda`

### Prioridad 4 — Infraestructura

- [ ] Pruebas unitarias con JUnit 5
- [ ] Paquete `exceptions` con excepciones propias del dominio
- [ ] Paquete `interfaces` con contratos entre capas
- [ ] Migración a Maven o Gradle
- [ ] Persistencia real con JDBC/JPA
- [ ] API REST con Spring Boot

---

## 8. Registro de Cambios de Este Documento

| Fecha | Commit | Cambio |
|---|---|---|
| — | `df7054b` | Creación del documento tras verificar el estado real del proyecto |

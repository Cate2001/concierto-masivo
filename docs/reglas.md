# Reglas del SGCM — registro vivo

Índice de las reglas de negocio y su estado de cobertura en el código. **No reproduce su texto**:
la definición normativa está en [ERS.md, §5](ERS.md#5-reglas-del-negocio).

## Estados

| Estado | Significado |
|---|---|
| **VIGENTE** | La regla está en la ERS y tiene implementación en el código. |
| **POR AÑADIR** | La regla está en la ERS, pero falta implementarla o la implementación es incompleta. |
| **OBSOLETA** | La regla existió en una versión anterior y se retiró. Se conserva solo para trazabilidad. |

La columna *Implementación* nombra el elemento que la aborda. `—` indica que no existe.
`(parcial)` indica que hay código relacionado, pero la regla no queda cubierta.

## Gestión de usuarios

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-01 | Identificación única por usuario | POR AÑADIR | `Usuario` · `GeneradorId` (parcial) |
| RN-01A | Edad mínima de 18 años | VIGENTE | `Usuario.setEdad` |
| RN-02 | Sin dos usuarios con la misma identificación | POR AÑADIR | — |
| RN-03 | Verificar duplicado al actualizar la identificación | POR AÑADIR | — |
| RN-04 | No eliminar usuario con boleto activo | VIGENTE | `UsuarioService.eliminarUsuario` |

## Gestión de bandas

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-05 | La banda puede existir sin canciones | VIGENTE | `Banda` (constructor) |
| RN-06 | No eliminar banda con conciertos asociados | POR AÑADIR | — |

## Gestión de canciones

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-07 | La canción puede existir sin banda | VIGENTE | `Cancion` (sin campo banda) |
| RN-08 | Una canción puede pertenecer a varias bandas | VIGENTE | `Banda.agregarCancion` |
| RN-08A | Sin canciones equivalentes en una banda | VIGENTE | Criterio en `Cancion.esEquivalenteA` · delegación en `Banda` (recorrido único) |
| RN-09 | No eliminar canción de banda en concierto | POR AÑADIR | — |

El criterio de equivalencia (nombre sin distinción de mayúsculas, año y género) se define solo en `Cancion`; `Banda` lo reutiliza para agregar y actualizar. La identidad sigue siendo el `id`: no hay `equals`/`hashCode` sobre campos editables ni lógica en `util`.

## Gestión de estadios

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-10 | Generar sillas al registrar el estadio | POR AÑADIR | `Estadio.organizarSillas` (parcial) |
| RN-10A | Capacidad ≥ 20 y múltiplo de 10 | VIGENTE | `Estadio.validarCapacidad` |
| RN-10B | Categoría VIP o GENERAL por silla | VIGENTE | `Estadio.organizarSillas` |
| RN-11 | Cambiar capacidad solo si todas las sillas están disponibles | POR AÑADIR | — |
| RN-12 | Rechazar el cambio si hay sillas ocupadas | POR AÑADIR | — |
| RN-13 | No eliminar estadio con conciertos asociados | POR AÑADIR | — |

## Gestión de conciertos

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-14 | Concierto con banda y estadio registrados | POR AÑADIR | `Concierto` (constructor) (parcial) |
| RN-14A | Estado actualizado automáticamente por fecha | POR AÑADIR | — |
| RN-14B | `PROGRAMADO` hasta la hora de inicio | POR AÑADIR | `Concierto` (constructor) (parcial) |
| RN-14C | `EN_CURSO` al alcanzar el inicio | POR AÑADIR | — |
| RN-14D | `FINALIZADO` tras la hora de fin | POR AÑADIR | — |
| RN-14E | La banda debe tener al menos una canción | POR AÑADIR | — |
| RN-14F | Precio base general ≥ 30.000 COP | VIGENTE | `Concierto.setPrecioGeneral` |
| RN-14G | Precio base VIP ≥ 60.000 COP | VIGENTE | `Concierto.setPrecioVip` |
| RN-15 | Eliminar solo sin boletos activos | POR AÑADIR | — |
| RN-16 | Modificar solo sin boletos activos | POR AÑADIR | `Concierto` (setters) (parcial) |
| RN-17 | Cambiar de estadio solo sin boletos activos | POR AÑADIR | `Concierto.setEstadio` (parcial) |
| RN-18 | Venta solo en `PROGRAMADO` | POR AÑADIR | — |
| RN-19 | Ingreso solo en `EN_CURSO` | POR AÑADIR | — |
| RN-20 | `FINALIZADO` no modificable | POR AÑADIR | — |
| RN-21 | Cancelar solo sin boletos activos | POR AÑADIR | `Concierto.cancelarConcierto` (parcial) |
| RN-22 | `CANCELADO` sin venta ni ingreso | POR AÑADIR | — |

## Gestión de boletos

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-23 | Compra solo en `PROGRAMADO` | POR AÑADIR | — |
| RN-23A | Dos categorías: GENERAL y VIP con precio propio | VIGENTE | `Boleto.obtenerPrecio` |
| RN-23B | Precio base congelado al emitir | VIGENTE | `Boleto.obtenerPrecio` |
| RN-24 | Seleccionar silla disponible antes de comprar | POR AÑADIR | — |
| RN-25 | Una silla, solo en un boleto activo | POR AÑADIR | — |
| RN-26 | La silla pasa a ocupada al comprar | POR AÑADIR | — |
| RN-27 | Cancelar el boleto libera la silla | POR AÑADIR | `Boleto.cancelarBoleto` (parcial) |

## Control de ingreso

| ID | Regla (resumen) | Estado | Implementación |
|---|---|---|---|
| RN-28 | Ingreso solo con boleto activo | POR AÑADIR | — |
| RN-29 | El boleto se usa una sola vez | POR AÑADIR | — |
| RN-30 | Rechazar ingreso con boleto cancelado | POR AÑADIR | — |
| RN-31 | Rechazar un segundo ingreso | POR AÑADIR | — |

## Reglas retiradas

| Regla | Motivo | Fecha |
|---|---|---|
| El boleto conserva una marca de venta (`VENDIDO`) | `EstadoBoleto` quedó en `ACTIVO` y `CANCELADO`; el boleto nace con el valor fijado. | 2026-11-17 |

## Reglas propuestas

Sin reglas propuestas registradas. Las decisiones aún sin tomar no se documentan aquí.

## Actualización

- Una regla pasa a **VIGENTE** cuando el código la cubre por completo.
- Una regla pasa a **POR AÑADIR** cuando se identifica en la ERS y aún no tiene cobertura.
- Una regla pasa a **OBSOLETA** cuando se retira; se elimina de su sección y se registra arriba,
  con el motivo y la fecha.
- El texto de la regla se cambia en la ERS, nunca aquí.

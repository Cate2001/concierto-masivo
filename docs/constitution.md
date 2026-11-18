# Constitución del proyecto SGCM

Principios y convenciones que gobiernan las decisiones de diseño del sistema.
No registra progreso, defectos ni decisiones particulares: esas viven en los documentos de
seguimiento, en `decisiones.md` y en `reglas.md`.

---

## 1. Principios de calidad

Orden de prioridad cuando dos criterios compiten:

1. **Corrección.** El código debe hacer lo que la especificación dice.
2. **Claridad.** El código se lee más veces de las que se escribe.
3. **Mantenibilidad.** Un cambio local no debe propagarse sin necesidad.
4. **Testabilidad.** Cada responsabilidad debe poder verificarse por separado.
5. **Adecuación al contexto.** La solución se ajusta al tamaño y propósito reales del proyecto.

## 2. Principios de diseño

- **Cohesión alta, acoplamiento bajo.** Cada tipo agrupa una sola responsabilidad.
- **Encapsulamiento.** El estado interno no se expone; se accede mediante métodos.
- **Separación de responsabilidades.** Validar, persistir, orquestar y presentar son tareas
  distintas.
- **SOLID como criterio, no como trámite.** Se aplica cuando resuelve un problema concreto.
- **Sin abstracciones prematuras.** No se introduce una interfaz, un genérico ni un patrón solo
  para demostrar que se conoce. Debe existir duplicación o responsabilidad común real.
- **Sin optimización prematura.** Se mide primero si el rendimiento es una restricción.
- **Sin diseño para escenarios hipotéticos.** La extensibilidad se justifica con evidencia del
  contexto actual.

## 3. Arquitectura y dependencias

- El sistema se organiza en capas: `app`, `services`, `repository`, `model`, más `dto`, `enums`,
  `exception` y `util` como paquetes de soporte.
- Las dependencias apuntan en un solo sentido: `app → services → repository → model`.
- Cada capa cumple la responsabilidad que le corresponde y no asume la de otra:

  | Capa | Responsabilidad |
  |---|---|
  | `app` | Entrada, presentación y parseo de texto. |
  | `services` | Reglas que cruzan dos entidades. |
  | `repository` | Acceso y copia defensiva de los datos. |
  | `model` | Invariantes de la entidad. |
  | `util` | Formato y operaciones auxiliares, sin reglas de negocio. |

- La validación vive en la capa más baja que tenga la información necesaria.

## 4. Convenciones de código

- Nombres descriptivos, consistentes y orientados a la responsabilidad, no a la implementación.
- Constantes, clases, métodos, variables, paquetes y enumeraciones siguen las convenciones
  estándar de Java.
- Un comentario explica **por qué** existe una decisión no evidente, no **qué** hace la línea.
- Se documenta con JavaDoc el contrato, el propósito y el comportamiento no evidente, con
  profundidad proporcional al tamaño y propósito del componente. No se documenta por plantilla.
- Las excepciones comunican errores concretos; no se capturan de forma indiscriminada ni se
  descarta su causa.
- Se prefiere código autoexplicativo sobre comentarios que repiten el código.

## 5. Convenciones de documentación

- La documentación vive dentro del repositorio, en Markdown, y se revisa como el código.
- La especificación es un documento vivo: se actualiza cuando el sistema cambia.
- Las reglas de negocio se escriben en forma EARS (evento, condición, sistema, respuesta) y se
  identifican con `RN-xx` para poder rastrearlas desde los casos de uso.
- Cada documento tiene un rol exclusivo:

  | Documento | Contenido |
  |---|---|
  | `README.md` | Producto y arquitectura. Público. |
  | `docs/ERS.md` | Requisitos funcionales, reglas y casos de uso. |
  | `docs/SDD.md` | Diseño técnico y de clases. |
  | `docs/constitution.md` | Principios y convenciones estables. |
  | `docs/decisiones.md` | Decisiones técnicas y su justificación. |
  | `docs/reglas.md` | Índice de reglas y su estado. |

- Los defectos, el progreso y las decisiones sin tomar no forman parte de esta documentación.
- Todo enlace debe apuntar a un archivo que exista en el repositorio.

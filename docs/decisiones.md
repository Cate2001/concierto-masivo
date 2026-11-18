# Decisiones técnicas del SGCM

Registro de decisiones ya tomadas y de su justificación. Se registra el **por qué**, no el estado
de ejecución ni los defectos. Una decisión se añade aquí cuando cambia una convención, una
arquitectura o la forma de documentar; no se registra cada cambio de código.

---

## AD-001 — Documentación como código en Markdown

- **Estado:** aceptada
- **Fecha:** 2026-11-17
- **Contexto:** los documentos funcional y técnico estaban en `.docx` fuera del repositorio. Git no
  puede mostrar diferencias de texto en ese formato, así que los cambios no eran revisables.
- **Decisión:** mantener la documentación dentro de `docs/`, en Markdown, con las imágenes en
  `docs/diagramas/`. Los `.docx` corregidos se conservan como referencia en `docs/originales/`.
- **Consecuencias:** los cambios se revisan en el diff y los enlaces entre documentos funcionan.
  Las imágenes se gestionan como archivos binarios separados.

## AD-002 — Especificación viviente sin Spec Kit completo

- **Estado:** aceptada
- **Fecha:** 2026-11-17
- **Contexto:** Spec Kit exige Python 3.11+ y `uv`, que no forman parte del entorno del proyecto.
  El proyecto tampoco tiene build tool ni dependencias.
- **Decisión:** adoptar la metodología Spec Development (especificación vivo o *spec-anchored*) de
  forma ligera, sin instalar el toolkit.
- **Consecuencias:** la spec se mantiene en `ERS.md` y `SDD.md`; las reglas se rastrean desde los
  casos de uso mediante identificadores `RN-xx`.

## AD-003 — Reglas de negocio en formato EARS

- **Estado:** aceptada
- **Fecha:** 2026-11-17
- **Contexto:** las reglas redactadas en prosa difícilmente permiten comprobar si el código las
  cumple o si dos reglas se contradicen.
- **Decisión:** escribir cada regla con estructura EARS y un identificador estable `RN-xx`.
- **Consecuencias:** cada regla es verificable de forma individual y se puede referenciar desde un
  caso de uso o desde `reglas.md` sin repetir su texto.

## AD-004 — Registro vivo de reglas en `reglas.md`

- **Estado:** aceptada
- **Fecha:** 2026-11-17
- **Contexto:** hacía falta un mecanismo para saber qué reglas existen, cuáles faltan por aplicar y
  cuáles se retiraron, sin convertir la ERS en un informe de estado.
- **Decisión:** mantener `docs/reglas.md` como índice con estado `VIGENTE`, `POR AÑADIR` y
  `OBSOLETA`, con una referencia a la definición completa en la ERS.
- **Consecuencias:** el índice se actualiza cuando cambia la cobertura de una regla; la ERS
  conserva solo la definición normativa.

## AD-005 — Repositorio genérico para todas las entidades

- **Estado:** aceptada
- **Fecha:** decisión previa a la migración documental
- **Contexto:** el modelo tiene varias entidades con las mismas operaciones de consulta, guardado y
  borrado.
- **Decisión:** usar `RepositorioGenerico<T, ID>` sobre un `Map<ID, T>` en lugar de un repositorio
  por entidad. El `ID` es el identificador natural, de modo que el `Map` actúa como índice.
- **Consecuencias:** un solo CRUD, con coste promedio O(1) por clave. Cambiar la persistencia no
  obliga a reescribir `services`. `listarRegistros()` devuelve una copia para no exponer el estado
  interno.

## AD-006 — Dependencia unidireccional entre capas

- **Estado:** aceptada
- **Fecha:** decisión previa a la migración documental
- **Contexto:** sin una regla explícita, las capas pueden referenciarse entre sí en ambos sentidos y
  el acoplamiento crece sin control.
- **Decisión:** las dependencias apuntan solo de `app` a `services`, de `services` a `repository` y
  de `repository` a `model`.
- **Consecuencias:** `model` no conoce a quien lo persiste ni a quien lo presenta. Un cambio en la
  interfaz no alcanza al dominio.

## AD-007 — Equivalencia de canciones separada de su identidad

- **Estado:** aceptada
- **Fecha:** 2026-11-18
- **Contexto:** la regla de duplicado (RN-08A) compara nombre, año y género. Meter ese criterio en
  `equals`/`hashCode` mezclaría identidad y equivalencia: el nombre es editable, esos campos son
  mutables y la identidad de `Cancion` ya es el `id`. Poner la comparación en `util` invertiría la
  dependencia `model → util`.
- **Decisión:** `Cancion` define el criterio en `esEquivalenteA(nombre, anio, genero)`; `Banda` delega
  en él y concentra en un único método privado el recorrido usado al agregar y al actualizar.
- **Consecuencias:** una sola definición del criterio y un solo recorrido, sin `equals`/`hashCode`
  sobre campos editables. Queda pendiente aplicarlo en el código. La regla normativa está en ERS
  RN-08A y el detalle en SDD §3.4 y §4.4.

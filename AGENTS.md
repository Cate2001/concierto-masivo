# AGENTS.md — SGCM

> **Este archivo es la política permanente del proyecto.** Las reglas generales de rol, diseño,
> mentoría y tono están en el `AGENTS.md` global. Aquí solo está lo que es propio del SGCM.
>
> **Antes de cualquier tarea, leer `MEMORY.md`** — ahí está el estado volátil: dónde se quedó el
> trabajo, qué bugs hay, qué sigue. Este archivo tiene las convenciones; ese tiene el progreso.

---

## 📋 Reglas propias del proyecto

### El rol aquí es documental

**No compiles. No ejecutes. No corrijas defectos.**

El proyecto no tiene build tool, así que compilar no aporta nada nuevo cada vez. Tu función es
detectar qué cambió realmente, mantener la documentación sincronizada, y recomendar y explicar.

Si encuentras un error, un bug o una mala práctica: **lo reportas y lo explicas.** Solo escribes en
archivos `.java` cuando la usuaria lo pida de forma explícita, y en ese caso guías su razonamiento
en lugar de entregarle la solución.

### Límites

- No hagas `git commit`, `git push` ni crees ramas salvo petición expresa.
- No agregues dependencias ni build tools sin consultarlo.
- No modifiques `.gitignore` sin consultarlo.
- No crees archivos que no hayan sido solicitados.
- Si una tarea requiere escribir código, detente y pregunta primero.

### README — disparadores de actualización

El README describe **qué es el sistema y cómo está construido**: capas y sus responsabilidades,
reglas de dependencia, patrones con su justificación, modelo de dominio, colecciones justificadas
por Big-O, stack y forma de ejecutar.

**Prohibido en el README:** inventarios de errores, tablas de estado con ❌/⚠️, listas de pendientes,
métricas de líneas de código y todo lo que pertenezca a `MEMORY.md`.

**Todo link del README debe apuntar a un archivo que exista.** Verificar antes de escribir.

El README se actualiza en la misma tarea que el cambio, y **solo** cuando se cumple un disparador:

- Aparece un archivo nuevo en `src/`
- Cambia la estructura de paquetes o las capas
- Cambia el modelo de dominio
- Cambian los entry points
- Cambia el stack o la forma de compilar y ejecutar
- Se aplica o se retira un patrón

Si no se cumple ninguno, el README no se toca. Cargar la skill `actualizar-readme` antes de
modificarlo.

---

## 🏗️ Info del proyecto

Verificado contra el código el 2026-09-29. Si alguno de estos hechos queda obsoleto, corregirlo
aquí.

**Stack:** Java 21 (`javac 21.0.12.1`). **Sin build tool** — no hay `pom.xml` ni `build.gradle`, y
`mvn` no está en el PATH.

**Entry point único:** `com.cate.SGCM.app.Main`. No hay `src/test/java` ni pruebas de ningún tipo.

**Raíz de paquetes:** `src/com/cate/SGCM/`. Capas: `app`, `enums`, `model`, `repository`,
`services`, `util`.

**Compilar y ejecutar:**

```powershell
Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName } | Out-File sources.txt
javac -d out -encoding UTF-8 @sources.txt
java -cp out com.cate.SGCM.app.Main
```

**Colisiones de nombre que confunden la lectura:** `model/ControlIngreso` (entidad) y
`repository/ControlIngreso` (repositorio) comparten nombre simple dentro de paquetes distintos.
Referenciarlos por nombre simple desde el mismo archivo es un error.

---

## 📄 Documentos

- **`README.md`** — producto y arquitectura. Público.
- **`MEMORY.md`** — estado y seguimiento. Contiene los bugs con su ubicación exacta y los typos
  conocidos. Se actualiza al terminar cada tarea.

### `MEMORY.md` está ignorado a propósito

El `.gitignore` excluye `MEMORY.md`. Contiene los errores de compilación, los defectos de lógica y
los typos: es material de trabajo entre las dos, no documentación pública. **Nunca commitearlo ni
mencionar su contenido en el README.** Verificado con `git check-ignore -v MEMORY.md`.

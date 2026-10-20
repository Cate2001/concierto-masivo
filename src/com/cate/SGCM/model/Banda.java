package com.cate.SGCM.model;

import com.cate.SGCM.dto.ActualizarCancionRequest;
import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.exception.CancionDuplicadaException;
import com.cate.SGCM.exception.CancionNoEncontradaException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.*;

/**
 * Agrupación musical propietaria de un repertorio de {@link Cancion}.
 * <p>
 * La colección de canciones no se expone ni se modifica por referencia: se consulta, agrega,
 * actualiza y elimina a través de los métodos de la clase, y es aquí donde se aplica la regla de
 * unicidad por nombre, año de lanzamiento y género. La identidad de la banda es su {@code id}
 * generado, no el nombre.
 */
public class Banda {
    private final int id;
    private String nombre;
    private int anioFundacion;
    private String nombreBajista;
    private final List<Cancion> canciones = new ArrayList<>();

    public Banda(String nombre, int anioFundacion, String nombreBajista) {
        this.id = GeneradorId.generarIdBanda();
        setNombre(nombre);
        setAnioFundacion(anioFundacion);
        setNombreBajista(nombreBajista);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        ValidacionesAtributos.validarNullVacio(nombre, "nombre");
        this.nombre = nombre;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        ValidacionesAtributos.validarPositivo(anioFundacion, "año de fundación");
        this.anioFundacion = anioFundacion;
    }

    public String getNombreBajista() {
        return nombreBajista;
    }

    public void setNombreBajista(String nombreBajista) {
        ValidacionesAtributos.validarNullVacio(nombreBajista, "nombre del bajista");
        this.nombreBajista = nombreBajista;
    }

    /**
     * Incorpora una canción al repertorio si no repite una ya registrada.
     * <p>
     * Dos canciones se consideran la misma cuando coinciden en nombre (sin distinguir mayúsculas),
     * año de lanzamiento y género musical.
     *
     * @param cancionNueva canción a incorporar; no puede ser {@code null}
     * @return {@code true} si se agregó, {@code false} si ya existía una equivalente
     * @throws NullPointerException si {@code cancionNueva} es {@code null}
     */
    public boolean agregarCancion(Cancion cancionNueva) {
        ValidacionesAtributos.validarObjetoNulo(cancionNueva, "canción");

        if (!canciones.isEmpty()) {
            for (Cancion cancion : canciones) {
                if (cancion.getNombre().equalsIgnoreCase(cancionNueva.getNombre()) &&
                        cancion.getAnioLanzamiento() == cancionNueva.getAnioLanzamiento() &&
                        cancion.getGeneroMusical().equals(cancionNueva.getGeneroMusical())) {
                    return false;
                }
            }
        }
        return canciones.add(cancionNueva);
    }

    public List<Cancion> listarCanciones() {
        return new ArrayList<>(canciones);
    }

    /**
     * Busca las canciones del repertorio cuyo nombre coincide con el indicado.
     *
     * @param nombreCancion nombre a buscar; no puede ser {@code null} ni vacío
     * @return lista con todas las coincidencias, o una lista vacía si no hay ninguna
     */
    public List<Cancion> buscarCancionesPorNombre(String nombreCancion) {
        ValidacionesAtributos.validarNullVacio(nombreCancion, "nombre de la canción");

        List<Cancion> cancionesEncontradas = new ArrayList<>();

        if (!canciones.isEmpty()) {
            for (Cancion cancion : canciones) {
                if (cancion.getNombre().equalsIgnoreCase(nombreCancion)) {
                    cancionesEncontradas.add(cancion);
                }
            }
        }
        return cancionesEncontradas;
    }

    /**
     * Actualiza de forma parcial una canción del repertorio, identificada por su {@code id}.
     * <p>
     * Es la única vía para modificar una canción ya registrada, y vuelve a comprobar la regla de
     * unicidad con los valores nuevos.
     *
     * @param idCancion          identificador de la canción a modificar
     * @param camposActualizados valores nuevos a aplicar
     * @return {@code true} si la actualización se aplicó
     * @throws IllegalArgumentException     si {@code idCancion} no es mayor que cero
     * @throws CancionNoEncontradaException si no existe una canción con ese id
     * @throws CancionDuplicadaException    si los valores nuevos duplicarían otra canción
     */
    public boolean actualizarCancion(int idCancion, ActualizarCancionRequest camposActualizados) throws CancionNoEncontradaException, CancionDuplicadaException {

        ValidacionesAtributos.validarPositivo(idCancion, "id de la canción");
        ValidacionesAtributos.validarObjetoNulo(camposActualizados, "petición de actualización");

        boolean cambioAlgo = false;

        Cancion cancionEncontrada = buscarCancionPorId(idCancion);
        if (cancionEncontrada == null) throw new CancionNoEncontradaException("No existe una canción con id " + idCancion);

        if (camposActualizados.getNuevaDuracion() != null) {
            cancionEncontrada.setDuracion(camposActualizados.getNuevaDuracion());
            cambioAlgo = true;
        }

        if (contieneCamposClave(camposActualizados)) {
            if (existeOtraEquivalente(camposActualizados, cancionEncontrada))
                throw new CancionDuplicadaException("Ya existe otra canción con ese nombre, año y género");

            actualizarCamposCancion(cancionEncontrada, camposActualizados);
            cambioAlgo = true;
        }

        return cambioAlgo;
    }

    private Cancion buscarCancionPorId(int idCancion) {
        if (!canciones.isEmpty()) {
            for (Cancion cancion : canciones) {
                if (idCancion == cancion.getId()) {
                    return cancion;
                }
            }
        }
        return null;
    }

    private boolean contieneCamposClave(ActualizarCancionRequest camposActualizados) {
        return camposActualizados.getNuevoNombre() != null ||
                camposActualizados.getNuevoGeneroMusical() != null ||
                camposActualizados.getNuevoAnioLanzamiento() != null;
    }

    private boolean existeOtraEquivalente(ActualizarCancionRequest camposActualizados, Cancion cancionOriginal) {
        String nombreFinal = camposActualizados.getNuevoNombre() != null
                ? camposActualizados.getNuevoNombre()
                : cancionOriginal.getNombre();

        int anioFinal = camposActualizados.getNuevoAnioLanzamiento() != null
                ? camposActualizados.getNuevoAnioLanzamiento()
                : cancionOriginal.getAnioLanzamiento();

        GeneroMusical generoFinal = camposActualizados.getNuevoGeneroMusical() != null
                ? camposActualizados.getNuevoGeneroMusical()
                : cancionOriginal.getGeneroMusical();

        for (Cancion cancion : canciones) {
            if (cancion.getId() == cancionOriginal.getId()) continue;
            if (cancion.getNombre().equalsIgnoreCase(nombreFinal) &&
                    cancion.getAnioLanzamiento() == anioFinal &&
                    cancion.getGeneroMusical().equals(generoFinal)) {

                return true;
            }
        }

        return false;
    }

    private void actualizarCamposCancion(Cancion cancionAActualizar, ActualizarCancionRequest camposActualizados) {

        if (camposActualizados.getNuevoNombre() != null) {
            cancionAActualizar.setNombre(camposActualizados.getNuevoNombre());
        }

        if (camposActualizados.getNuevoAnioLanzamiento() != null) {
            cancionAActualizar.setAnioLanzamiento(camposActualizados.getNuevoAnioLanzamiento());
        }

        if (camposActualizados.getNuevoGeneroMusical() != null) {
            cancionAActualizar.setGeneroMusical(camposActualizados.getNuevoGeneroMusical());
        }
    }


    /**
     * Elimina del repertorio la canción con el {@code id} indicado.
     *
     * @param idCancion identificador de la canción; debe ser mayor que cero
     * @return {@code true} si se eliminó, {@code false} si no existe una canción con ese id
     * @throws IllegalArgumentException si {@code idCancion} no es mayor que cero
     */
    public boolean eliminarCancion(int idCancion) {
        ValidacionesAtributos.validarPositivo(idCancion, "id de la canción");
        if (!canciones.isEmpty()) {
            for (Cancion cancion : canciones) {
                if (cancion.getId() == idCancion) {
                    return this.canciones.remove(cancion);
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Banda{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", anioFundacion=").append(anioFundacion);
        sb.append(", nombreBajista='").append(nombreBajista).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

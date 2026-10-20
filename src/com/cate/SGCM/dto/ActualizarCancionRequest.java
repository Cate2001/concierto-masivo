package com.cate.SGCM.dto;

import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.util.ValidacionesAtributos;

/**
 * Valores nuevos con los que actualizar una canción del repertorio.
 *
 * Cada campo es opcional y se valida al asignarse con las mismas comprobaciones que la entidad.
 * Es el vehículo que evita pasarle a {@code Banda.actualizarCancion} el objeto {@code Cancion}
 * completo: quien llama indica explícitamente qué va a cambiar, y no hay que deducir qué campos se
 * modificaron.
 */
public class ActualizarCancionRequest {
    private String nuevoNombre;
    private GeneroMusical nuevoGeneroMusical;
    private Integer nuevaDuracion;
    private Integer nuevoAnioLanzamiento;

    public void setNuevoNombre(String nuevoNombre) {
        ValidacionesAtributos.validarNullVacio(nuevoNombre, "nombre de la canción");
        this.nuevoNombre = nuevoNombre;
    }

    public void setNuevoGeneroMusical(GeneroMusical nuevoGeneroMusical) {
        ValidacionesAtributos.validarObjetoNulo(nuevoGeneroMusical, "género musical");
        this.nuevoGeneroMusical = nuevoGeneroMusical;
    }

    public void setNuevoDuracion(Integer nuevaDuracion) {
        ValidacionesAtributos.validarPositivo(nuevaDuracion, "duración");
        this.nuevaDuracion = nuevaDuracion;
    }

    public void setNuevoAnioLanzamiento(Integer nuevoAnioLanzamiento) {
        ValidacionesAtributos.validarPositivo(nuevoAnioLanzamiento, "año de lanzamiento");
        this.nuevoAnioLanzamiento = nuevoAnioLanzamiento;
    }

    public GeneroMusical getNuevoGeneroMusical() {
        return nuevoGeneroMusical;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public Integer getNuevoAnioLanzamiento() {
        return nuevoAnioLanzamiento;
    }

    public Integer getNuevaDuracion() {
        return nuevaDuracion;
    }
}

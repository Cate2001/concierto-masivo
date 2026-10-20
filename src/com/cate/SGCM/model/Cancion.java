package com.cate.SGCM.model;

import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

/**
 * Canción del repertorio de una {@link Banda}.
 *
 * Su identidad es el {@code id} generado, no el nombre: el nombre es editable, así que no puede
 * usarse como clave. Los setters protegen los invariantes de la clase (nombre no vacío, género no
 * nulo, duración y año positivos) y son de acceso de paquete para que la modificación pase siempre
 * por {@link Banda}.
 */
public class Cancion {
    private final int id;
    private String nombre;
    private GeneroMusical generoMusical;
    private int duracion;
    private int anioLanzamiento;

    public Cancion(String nombre, GeneroMusical generoMusical, int duracion, int anioLanzamiento) {
        this.id = GeneradorId.generarIdCancion();
        setNombre(nombre);
        setGeneroMusical(generoMusical);
        setDuracion(duracion);
        setAnioLanzamiento(anioLanzamiento);
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

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    void setGeneroMusical(GeneroMusical generoMusical) {
        ValidacionesAtributos.validarObjetoNulo(generoMusical, "género musical");
        this.generoMusical = generoMusical;
    }

    public int getDuracion() {
        return duracion;
    }

    void setDuracion(int duracion) {
        ValidacionesAtributos.validarPositivo(duracion, "duración");
        this.duracion = duracion;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    void setAnioLanzamiento(int anioLanzamiento) {
        ValidacionesAtributos.validarPositivo(anioLanzamiento, "año de lanzamiento");
        this.anioLanzamiento = anioLanzamiento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cancion{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", generoMusical=").append(generoMusical);
        sb.append(", duracion=").append(duracion);
        sb.append(", anioLanzamiento=").append(anioLanzamiento);
        sb.append('}');
        return sb.toString();
    }
}

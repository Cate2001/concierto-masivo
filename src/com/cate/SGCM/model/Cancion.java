package com.cate.SGCM.model;

import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

public class Cancion {
    private int id;
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
        ValidacionesAtributos.validarNullVacio(nombre, "Nombre");
        this.nombre = nombre;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        ValidacionesAtributos.validarObjetosNulo(generoMusical, "GeneroMusical");
        this.generoMusical = generoMusical;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        ValidacionesAtributos.validarNumeroNagativo(duracion, "Duracion");
        this.duracion = duracion;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        ValidacionesAtributos.validarNumeroNagativo(anioLanzamiento, "Año de Lanzamiento");
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

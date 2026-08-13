package com.cate.SGCM.model;

import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.LinkedHashSet;
import java.util.Set;

public class Banda implements Comparable<Banda> {
    private final int id;
    private String nombre;
    private GeneroMusical generoMusical;
    private int anioFundacion;
    private String nombreBajista;
    private final Set<Cancion> canciones = new LinkedHashSet<>();

    public Banda(String nombre, GeneroMusical generoMusical, int anioFundacion, String nombreBajista) {
        this.id = GeneradorId.generarIdBanda();
        setNombre(nombre);
        setGeneroMusical(generoMusical);
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

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        ValidacionesAtributos.validarNumeroNagativo(anioFundacion, "AnioFundacion");
        this.anioFundacion = anioFundacion;
    }

    public String getNombreBajista() {
        return nombreBajista;
    }

    public void setNombreBajista(String nombreBajista) {
        ValidacionesAtributos.validarNullVacio(nombreBajista, "NombreBajista");
        this.nombreBajista = nombreBajista;
    }

    //Funcion comparable
    public int compareTo(Banda o) {
        return this.nombre.compareTo(o.nombre);
    }

    public void agregarCancion(Cancion cancion) {
        ValidacionesAtributos.validarObjetosNulo(cancion, "Cancion");
        this.canciones.add(cancion);
    }
//estudiar colecciones y cambiar el topo de coleccion que estpy usando
    public void eliminarCancion(int idCancion) {
        ValidacionesAtributos.validarNumeroNagativo(idCancion, "IdCancion");
        if (!canciones.isEmpty()) {
            for (Cancion cancion1 : canciones) {
                if (cancion1.getId() == idCancion) {
                    this.canciones.remove(cancion1);
                    throw new IllegalArgumentException("Canción eliminada con éxito");
                }
            }
            throw new IllegalArgumentException("Canción no encontrada");
        }
        throw new IllegalArgumentException("No hay canciones agregadas");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Banda{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", generoMusical=").append(generoMusical);
        sb.append(", anioFundacion=").append(anioFundacion);
        sb.append(", nombreBajista='").append(nombreBajista).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

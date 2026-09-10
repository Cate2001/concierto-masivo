package com.cate.SGCM.model;

import com.cate.SGCM.enums.GeneroMusical;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.*;

public class Banda {
    private final int id;
    private String nombre;
    private GeneroMusical generoMusical;
    private int anioFundacion;
    private String nombreBajista;
    private final Set<Cancion> canciones = new HashSet<>();

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


    public void agregarCancion(Cancion cancion) {
        ValidacionesAtributos.validarObjetosNulo(cancion, "Cancion");
        this.canciones.add(cancion);
    }

    public void eliminarCancion(int idCancion) {
        ValidacionesAtributos.validarNumeroNagativo(idCancion, "IdCancion");
        if (!canciones.isEmpty()) {
            for (Cancion cancion : canciones) {
                if (cancion.getId() == idCancion) {
                    this.canciones.remove(cancion);
                    throw new IllegalArgumentException("Canción eliminada con éxito");
                }
            }
            throw new IllegalArgumentException("Canción no encontrada");
        }
        throw new IllegalArgumentException("No existen canciones registradas dentro del sistema");
    }

    public Cancion buscarCancion(String nombreCancion) {
        ValidacionesAtributos.validarNullVacio("Nombre canción", nombreCancion);
        for (Cancion cancion : canciones) {
            if (cancion.getNombre().equals(nombreCancion)) return cancion;
        }
        return null;
    }

    public List<Cancion> listarCanciones() {
        return new ArrayList<>(canciones);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Banda banda)) return false;
        return id == banda.id && Objects.equals(nombre, banda.nombre) && Objects.equals(nombreBajista, banda.nombreBajista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, nombreBajista);
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

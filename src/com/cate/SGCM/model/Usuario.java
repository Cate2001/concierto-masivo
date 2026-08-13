package com.cate.SGCM.model;

import com.cate.SGCM.enums.Genero;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.Objects;

public class Usuario {
    private final int id;
    private String nombre;
    private String apellido;
    private int edad;
    private Genero genero;
    private int identificacion;

    public Usuario(String nombre, String apellido, int edad, Genero genero, int identificacion) {
        this.id = GeneradorId.generarIdUsuario();
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);
        setGenero(genero);
        setIdentificacion(identificacion);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        ValidacionesAtributos.validarNullVacio(apellido, "Apellido");
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        ValidacionesAtributos.validarNumeroNagativo(edad, "Edad");
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        ValidacionesAtributos.validarObjetosNulo(genero, "Genero");
        this.genero = genero;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        ValidacionesAtributos.validarNumeroNagativo(identificacion, "Identificacion");
        this.identificacion = identificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return identificacion == usuario.identificacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identificacion);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", edad=").append(edad);
        sb.append(", genero=").append(genero);
        sb.append(", identificacion=").append(identificacion);
        sb.append('}');
        return sb.toString();
    }
}

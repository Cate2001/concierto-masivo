package com.cate.SGCM.model;

import com.cate.SGCM.enums.GeneroPersona;
import com.cate.SGCM.exception.EdadInvalidaException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

/**
 * Persona que compra boletos para un {@link Concierto}.
 *
 * Los datos de identificación (nombre, apellido, edad, género) se validan al asignarse; la
 * identificación debe ser un número positivo y la edad, la mínima del sistema.
 */
public class Usuario {
    private final int id;
    private String nombre;
    private String apellido;
    private int edad;
    private GeneroPersona generoPersona;
    private int identificacion;

    public Usuario(String nombre, String apellido, int edad, GeneroPersona generoPersona, int identificacion) {
        this.id = GeneradorId.generarIdUsuario();
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);
        setGenero(generoPersona);
        setIdentificacion(identificacion);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        ValidacionesAtributos.validarNullVacio(apellido, "apellido");
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Asigna la edad del usuario.
     *
     * @param edad edad en años; debe ser mayor o igual al mínimo permitido
     * @throws EdadInvalidaException si {@code edad} es menor que 18
     */
    public void setEdad(int edad) {
        if (edad < 18) throw new EdadInvalidaException("La edad mínima permitida es 18");
        this.edad = edad;
    }

    public GeneroPersona getGenero() {
        return generoPersona;
    }

    public void setGenero(GeneroPersona generoPersona) {
        ValidacionesAtributos.validarObjetoNulo(generoPersona, "género de la persona");
        this.generoPersona = generoPersona;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        ValidacionesAtributos.validarPositivo(identificacion, "identificación");
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", edad=").append(edad);
        sb.append(", generoPersona=").append(generoPersona);
        sb.append(", identificacion=").append(identificacion);
        sb.append('}');
        return sb.toString();
    }
}

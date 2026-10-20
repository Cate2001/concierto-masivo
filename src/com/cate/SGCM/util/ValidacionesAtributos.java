package com.cate.SGCM.util;

/**
 * Comprobaciones de formato que comparten todas las entidades.
 *
 * Solo valida lo que no depende del negocio: que un número no sea negativo o cero y que un objeto
 * no sea {@code null}. Las reglas de dominio (edad mínima, capacidad de un estadio, transiciones
 * de estado) viven en la clase a la que pertenecen, no aquí: si estuvieran en esta utilidad,
 * cambiar el negocio obligaría a tocar la capa equivocada.
 */
public final class ValidacionesAtributos {

    /**
     * Exige que un número sea mayor que cero.
     *
     * @param valor número a comprobar
     * @param campo etiqueta del atributo, que se usa en el mensaje de error
     * @throws IllegalArgumentException si {@code valor} es cero o negativo
     */
    public static void validarPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo ni 0");
        }
    }

    /**
     * Exige que un texto no sea {@code null} ni quede vacío.
     *
     * @param valor texto a comprobar
     * @param campo etiqueta del atributo, que se usa en el mensaje de error
     * @throws NullPointerException si {@code valor} es {@code null} o solo contiene espacios
     */
    public static void validarNullVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NullPointerException(campo + " no puede ser nulo ni vacío");
        }
    }

    /**
     * Exige que un objeto no sea {@code null}.
     *
     * @param <T>    tipo del objeto
     * @param objeto objeto a comprobar
     * @param campo  etiqueta del atributo, que se usa en el mensaje de error
     * @throws NullPointerException si {@code objeto} es {@code null}
     */
    public static <T> void validarObjetoNulo(T objeto, String campo) {
        if (objeto == null) {
            throw new NullPointerException(campo + " no puede ser nulo");
        }
    }
}

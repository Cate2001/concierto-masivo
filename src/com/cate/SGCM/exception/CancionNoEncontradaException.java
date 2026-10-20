package com.cate.SGCM.exception;

/**
 * Señala que no existe ninguna canción con el identificador buscado.
 *
 * Es una condición de negocio que quien llama puede prever, no un fallo de programación.
 */
public class CancionNoEncontradaException extends RuntimeException {
    public CancionNoEncontradaException(String message) {
        super(message);
    }
}

package com.cate.SGCM.exception;

/**
 * Señala que un estadio ya tiene su mapa de asientos generado y no debe regenerarse.
 */
public class SillasNoOrganizadasException extends RuntimeException {
    public SillasNoOrganizadasException(String message) {
        super(message);
    }
}

package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.util.GeneradorId;

/**
 * Asiento de un {@link Estadio}, con fila, columna y categoría de boleto.
 *
 * Nace disponible y su ocupación se gobierna con {@link #ocuparSilla()} y
 * {@link #desocuparSilla()}, que tratan el caso de "ya está en ese estado" de forma asimétrica:
 * ocupar devuelve {@code false}, desocupar lanza excepción.
 *
 * La clase es de acceso de paquete: solo el paquete {@code model} puede instanciarla y
 * consultarla.
 */
class Silla {
    private final int id;
    private final String fila;
    private final int columna;
    private final TipoBoleto categoria;
    private boolean disponible;

    public Silla(String fila, int columna, TipoBoleto categoria) {
        this.id = GeneradorId.generarIdSilla();
        this.fila = fila;
        this.columna = columna;
        this.categoria = categoria;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public String getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public TipoBoleto getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Libera el asiento para que pueda volver a ocuparse.
     *
     * @throws IllegalArgumentException si la silla ya estaba libre
     */
    public void desocuparSilla() {
        if (disponible) throw new IllegalArgumentException("La silla ya está desocupada");
        this.disponible = true;
    }

    /**
     * Marca el asiento como ocupado.
     *
     * @return {@code true} si estaba libre y se ocupó, {@code false} si ya estaba ocupado
     */
    public boolean ocuparSilla() {
        if (!disponible) return false;
        this.disponible = false;
        return true;
    }
}

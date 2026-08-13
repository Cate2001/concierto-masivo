package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

class Silla {
    private final int id;
    private final String fila;
    private final int columna;
    private final TipoBoleta categoria;
    private boolean disponible;

    public Silla(String fila, int columna, TipoBoleta categoria) {
        this.id = GeneradorId.generarIdSilla();
        ValidacionesAtributos.validarNullVacio(fila, "Fila");
        ValidacionesAtributos.validarNumeroNagativo(columna, "Columna");
        ValidacionesAtributos.validarObjetosNulo(categoria, "Categoria");
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

    public TipoBoleta getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    void desocuparSilla() {
        if (disponible) throw new IllegalArgumentException("La silla ya esta desocupada");
        this.disponible = true;
    }

    void ocuparSilla() {
        if (!disponible) throw new IllegalArgumentException("La silla ya esta ocupada");
        this.disponible = false;
    }
}

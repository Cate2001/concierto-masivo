package com.cate.SGCM.model;

import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

/**
 * Registro de la entrada de un asistente al recinto, a partir de un {@link Boleto}.
 *
 * Se crea ya marcado como ingresado y guarda el instante del acceso; es evidencia de que el
 * boleto se usó, no un estado editable.
 */
public class ControlIngreso {
    private int id;
    private LocalDateTime fechaIngreso;
    private Boleto boleto;
    private boolean ingresoRegistrado;

    public ControlIngreso(Boleto boleto) {
        ValidacionesAtributos.validarObjetoNulo(boleto, "boleto");
        this.id = GeneradorId.generarIdControlIngreso();
        this.fechaIngreso = LocalDateTime.now();
        this.boleto = boleto;
        this.ingresoRegistrado = true;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public boolean isIngresoRegistrado() {
        return ingresoRegistrado;
    }

    public int getId() {
        return id;
    }
}

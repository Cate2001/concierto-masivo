package com.cate.SGCM.model;

import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

public class ControlIngreso {
    private int id;
    private LocalDateTime fechaIngreso;
    private Boleto boleto;
    private boolean ingresoRegistrado;

    public ControlIngreso(Boleto boleto) {
        ValidacionesAtributos.validarObjetosNulo(boleto, "Boleto");
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

    public boolean getIngresoRegistrado() {
        return ingresoRegistrado;
    }

    public int getId() {
        return id;
    }
}

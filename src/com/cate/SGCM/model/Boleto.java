package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

public class Boleto {
    private final String codigo;
    private final LocalDateTime creadoEn;
    private LocalDateTime vendidoEn;
    private LocalDateTime canceladoEn;
    private EstadoBoleta estadoBoleta;
    private final double precioBase;
    private final TipoBoleta tipoBoleta;
    private final Usuario usuario;

    public Boleto(double precioBase, TipoBoleta tipoBoleta, Usuario usuario) {
        ValidacionesAtributos.validarDouble(precioBase, "PrecioBase");
        ValidacionesAtributos.validarObjetosNulo(usuario, "Usuario");
        this.codigo = GeneradorId.generarCodigoBoleto();
        this.creadoEn = LocalDateTime.now();
        this.vendidoEn = null;
        this.precioBase = precioBase;
        this.tipoBoleta = tipoBoleta;
        this.estadoBoleta = EstadoBoleta.DISPONIBLE;
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public TipoBoleta getTipoBoleta() {
        return tipoBoleta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    private double calcularPrecioBoleta() {
        return precioBase + getTipoBoleta().getPrecio();
    }

    public void venderBoleta() {
        calcularPrecioBoleta();
        this.vendidoEn = LocalDateTime.now();
        this.estadoBoleta = EstadoBoleta.VENDIDO;
    }
    public void cancelarBoleta() {
        this.estadoBoleta = EstadoBoleta.CANCELADO;
        this.canceladoEn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Boleto{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(", creadoEn=").append(creadoEn);
        sb.append(", vendidoEn=").append(vendidoEn);
        sb.append(", canceladoEn=").append(canceladoEn);
        sb.append(", estadoBoleta=").append(estadoBoleta);
        sb.append(", precioBase=").append(precioBase);
        sb.append(", tipoBoleta=").append(tipoBoleta);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}

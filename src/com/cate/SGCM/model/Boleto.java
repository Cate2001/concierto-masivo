package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.exception.PrecioBaseInsuficienteException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

/**
 * Boleto emitido para un {@link Concierto} a nombre de un {@link Usuario}.
 * <p>
 * se crea {@code ACTIVO}, pasa a {@code VENDIDO} al cobrarse y a {@code CANCELADO} si se anula.
 * El precio total solo tiene valor después de la venta. Cada boleto recibe un código único
 * generado en el constructor.
 */
public class Boleto {
    private final String codigo;
    private final LocalDateTime creadoEn;
    private LocalDateTime canceladoEn;
    private EstadoBoleto estadoBoleto;
    private double valor;
    private final Usuario usuario;
    private final Concierto concierto;
    private final Silla silla;

    //falta como voy a agregar la silla dentro de mi boleto y validar como se deben manejar los estados de manera correcta

    /**
     * Crea un boleto en estado {@code ACTIVO}.
     *
     * @param usuario titular del boleto; no puede ser {@code null}
     * @throws PrecioBaseInsuficienteException si {@code precioBase} es inferior al mínimo exigido
     * @throws NullPointerException            si {@code usuario} es {@code null}
     */
    public Boleto(Usuario usuario, Concierto concierto, Silla silla) {
        ValidacionesAtributos.validarObjetoNulo(usuario, "usuario");
        ValidacionesAtributos.validarObjetoNulo(concierto, "Concierto");
        ValidacionesAtributos.validarObjetoNulo(silla, "Silla");
        this.codigo = GeneradorId.generarCodigoBoleto();
        this.creadoEn = LocalDateTime.now();
        this.estadoBoleto = EstadoBoleto.ACTIVO;
        this.usuario = usuario;
        this.concierto = concierto;
        this.silla = silla;
        this.valor = obtenerPrecio();
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Concierto getConcierto() {
        return concierto;
    }

    public Silla getSilla() {
        return silla;
    }

    private double obtenerPrecio() {
        Categoria categoria = this.silla.getCategoria();
        if (Categoria.GENERAL.equals(categoria)) {
            return this.concierto.getPrecioGeneral();
        }
        return this.concierto.getPrecioVip();

    }

    /**
     * Anula el boleto y registra el momento de la cancelación.
     */
    public void cancelarBoleto() {
        this.estadoBoleto = EstadoBoleto.CANCELADO;
        this.canceladoEn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Boleto{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(", creadoEn=").append(creadoEn);
        sb.append(", canceladoEn=").append(canceladoEn);
        sb.append(", estadoBoleto=").append(estadoBoleto);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}

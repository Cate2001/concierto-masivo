package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.exception.PrecioBaseInsuficienteException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

/**
 * Boleto emitido para un {@link Concierto} a nombre de un {@link Usuario}.
 *
 * El ciclo de vida del boleto lo gobiernan {@link #venderBoleto()} y {@link #cancelarBoleto()}:
 * se crea {@code ACTIVO}, pasa a {@code VENDIDO} al cobrarse y a {@code CANCELADO} si se anula.
 * El precio total solo tiene valor después de la venta. Cada boleto recibe un código único
 * generado en el constructor.
 */
public class Boleto {
    private final String codigo;
    private final LocalDateTime creadoEn;
    private LocalDateTime vendidoEn;
    private LocalDateTime canceladoEn;
    private EstadoBoleto estadoBoleto;
    private final double precioBase;
    private double precioTotal;
    private final TipoBoleto tipoBoleto;
    private final Usuario usuario;
    private Concierto concierto;

    //falta como voy a agregar la silla dentro de mi boleto y validar como se deben manejar los estados de manera correcta

    /**
     * Crea un boleto en estado {@code ACTIVO}.
     *
     * @param precioBase precio base del boleto
     * @param tipoBoleto categoría del boleto
     * @param usuario    titular del boleto; no puede ser {@code null}
     * @throws PrecioBaseInsuficienteException si {@code precioBase} es inferior al mínimo exigido
     * @throws NullPointerException           si {@code usuario} es {@code null}
     */
    public Boleto(double precioBase, TipoBoleto tipoBoleto, Usuario usuario) {
        if (precioBase < 30000) throw new PrecioBaseInsuficienteException("El precio base del boleto debe ser de $30.000 COP o más");
        ValidacionesAtributos.validarObjetoNulo(usuario, "usuario");
        this.codigo = GeneradorId.generarCodigoBoleto();
        this.creadoEn = LocalDateTime.now();
        this.precioBase = precioBase;
        this.tipoBoleto = tipoBoleto;
        this.estadoBoleto = EstadoBoleto.ACTIVO;
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public double getPrecioBaseInsuficienteException() {
        return precioBase;
    }

    public TipoBoleto getTipoBoleto() {
        return tipoBoleto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    private void calcularPrecioTotal() {
        precioTotal = precioBase + getTipoBoleto().getPrecio();
    }

    /**
     * Marca el boleto como vendido y fija el precio total.
     *
     * El precio total es el precio base más el recargo del {@link TipoBoleto}; antes de llamar a
     * este método vale cero.
     */
    public void venderBoleto() {
        calcularPrecioTotal();
        this.vendidoEn = LocalDateTime.now();
        this.estadoBoleto = EstadoBoleto.VENDIDO;
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
        sb.append(", vendidoEn=").append(vendidoEn);
        sb.append(", canceladoEn=").append(canceladoEn);
        sb.append(", estadoBoleto=").append(estadoBoleto);
        sb.append(", precioTotal=").append(precioTotal);
        sb.append(", tipoBoleto=").append(tipoBoleto);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}

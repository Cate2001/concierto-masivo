package com.cate.SGCM.model;

import com.cate.SGCM.enums.EstadoConcierto;
import com.cate.SGCM.exception.PrecioBaseInsuficienteException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalDateTime;

/**
 * Evento musical celebrado en un {@link Estadio} e interpretado por una {@link Banda}.
 * <p>
 * Nace siempre en estado {@code PROGRAMADO} y desde ahí avanza por el ciclo
 * {@code PROGRAMADO → EN_CURSO → FINALIZADO}, o se corta por {@code CANCELADO}. El estado es de
 * solo lectura desde fuera: forma parte del ciclo de vida del evento, no de sus datos editables.
 */
public class Concierto {
    private final int id;
    private String nombre;
    private double precioGeneral;
    private double precioVip;
    private EstadoConcierto estado;
    private Estadio estadio;
    private Banda banda;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime canceladoEn;

    //agregar estados dentro del sistema
    //cuando se crea un concierto, su estado es PROGRAMADO
    //el dia que inica el concierto pasa a esatdo en curso
    //el dia que finaliza el concierto pasa a esatdo finalizado

    public Concierto(String nombre, double precioGeneral, double precioVip, LocalDateTime fechaInicio, LocalDateTime fechaFin, Estadio estadio, Banda banda) {
        this.id = GeneradorId.generarIdConcierto();
        this.estado = EstadoConcierto.PROGRAMADO;
        validarFecha(fechaInicio);
        validarFecha(fechaFin);

        setNombre(nombre);
        setPrecioGeneral(precioGeneral);
        setPrecioVip(precioVip);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        setEstadio(estadio);
        setBanda(banda);


    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        ValidacionesAtributos.validarNullVacio(nombre, "nombre");
        this.nombre = nombre;
    }

    public double getPrecioGeneral() {
        return precioGeneral;
    }

    public void setPrecioGeneral(double precioGeneral) {
        if (precioGeneral < 30000)
            throw new PrecioBaseInsuficienteException("El precio base del concierto debe ser de $30.000 COP o más");
        this.precioGeneral = precioGeneral;
    }

    public double getPrecioVip() {
        return precioVip;
    }

    public void setPrecioVip(double precioVip) {
        if (precioVip < 60000)
            throw new PrecioBaseInsuficienteException("El precio base del concierto debe ser de $30.000 COP o más");
        this.precioVip = precioVip;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        ValidacionesAtributos.validarObjetoNulo(estadio, "estadio");
        this.estadio = estadio;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        ValidacionesAtributos.validarObjetoNulo(banda, "banda");
        this.banda = banda;
    }

    public EstadoConcierto getEstado() {
        return estado;
    }

    private void validarFecha(LocalDateTime fecha) {
        if (fecha == null) {
            throw new NullPointerException("La fecha del concierto no puede ser nula");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La fecha debe ser igual o posterior a la fecha y hora actual.");
        }
    }

    public void reprogramarConcierto(LocalDateTime fechaInicioNueva) {
        validarFecha(fechaInicioNueva);
        if (getEstado() != EstadoConcierto.PROGRAMADO) {
            throw new IllegalStateException("No se puede reprogramar un concierto en estado " + getEstado() + "; sólo en PROGRAMADO");
        }

        this.fechaInicio = fechaInicioNueva;
    }

    private void cancelarConcierto() {
        if (getEstado() != EstadoConcierto.PROGRAMADO) {
            throw new IllegalStateException("No se puede cancelar un concierto en estado " + getEstado() + "; sólo en PROGRAMADO");
        }
        this.estado = EstadoConcierto.CANCELADO;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Concierto{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", estadio=").append(estadio);
        sb.append(", banda=").append(banda);
        sb.append('}');
        return sb.toString();
    }
}

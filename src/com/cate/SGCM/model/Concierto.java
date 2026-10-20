package com.cate.SGCM.model;

import com.cate.SGCM.enums.EstadoConcierto;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Evento musical celebrado en un {@link Estadio} e interpretado por una {@link Banda}.
 *
 * Nace siempre en estado {@code PROGRAMADO} y desde ahí avanza por el ciclo
 * {@code PROGRAMADO → EN_CURSO → FINALIZADO}, o se corta por {@code CANCELADO}. El estado es de
 * solo lectura desde fuera: forma parte del ciclo de vida del evento, no de sus datos editables.
 */
public class Concierto {
    private final int id;
    private String nombre;
    private EstadoConcierto estado;
    private Date fechaInicio;
    private LocalTime horaInicio;
    private Estadio estadio;
    private Banda banda;

    //agregar estados dentro del sistema
    //cuando se crea un concierto, su estado es PROGRAMADO
    //el dia que inica el concierto pasa a esatdo en curso
    //el dia que finaliza el concierto pasa a esatdo finalizado

    public Concierto(Date fechaInicio, String nombre, LocalTime horaInicio, Estadio estadio, Banda banda) {
        this.id = GeneradorId.generarIdConcierto();
        setFechaInicio(fechaInicio);
        setNombre(nombre);
        this.estado = EstadoConcierto.PROGRAMADO;
        setHoraInicio(horaInicio);
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Asigna la fecha de inicio del concierto.
     *
     * @param fechaInicio fecha del evento; no puede ser {@code null}
     * @throws NullPointerException si {@code fechaInicio} es {@code null}
     */
    public void setFechaInicio(Date fechaInicio) {
        validarFecha(fechaInicio);
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        ValidacionesAtributos.validarObjetoNulo(horaInicio, "hora de inicio");
        this.horaInicio = horaInicio;
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

    private void validarFecha(Date fecha) {
        if (fecha == null) {
            throw new NullPointerException("La fecha no puede ser nula");
        }

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            formato.setLenient(false);
            formato.format(fecha);

        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es válida");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Concierto{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", estadio=").append(estadio);
        sb.append(", banda=").append(banda);
        sb.append('}');
        return sb.toString();
    }
}

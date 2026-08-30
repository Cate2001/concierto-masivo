package com.cate.SGCM.model;

import com.cate.SGCM.enums.EstadoConcierto;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalTime;
import java.util.Date;

public class Concierto {
    private int id;
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
        ValidacionesAtributos.validarNullVacio(nombre, "Nombre");
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        ValidacionesAtributos.validarFechaObjeto(fechaInicio);
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        ValidacionesAtributos.validarObjetosNulo(horaInicio, "Hora");
        this.horaInicio = horaInicio;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        ValidacionesAtributos.validarObjetosNulo(estadio, "Estadio");
        this.estadio = estadio;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        ValidacionesAtributos.validarObjetosNulo(banda, "Banda");
        this.banda = banda;
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

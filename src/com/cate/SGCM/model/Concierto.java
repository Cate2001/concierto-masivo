package com.cate.SGCM.model;

import com.cate.SGCM.enums.EstadoBoleta;
import com.cate.SGCM.enums.EstadoConcierto;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.time.LocalTime;
import java.util.Date;

public class Concierto {
    private int id;
    private String nombre;
    private EstadoConcierto estado;
    private Date fecha;
    private LocalTime hora;
    private Estadio estadio;
    private Banda banda;

    //agregar estados dentro del sistema
    //cuando se crea un concierto, su estado es PROGRAMADO
    //el dia que inica el concierto pasa a esatdo en curso
    //el dia que finaliza el concierto pasa a esatdo finalizado

    public Concierto(Date fecha, String nombre, EstadoBoleta estado, LocalTime hora, Estadio estadio, Banda banda) {
        this.id = GeneradorId.generarIdConcierto();
        setFecha(fecha);
        setNombre(nombre);

        setHora(hora);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        ValidacionesAtributos.validarFechaObjeto(fecha);
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        ValidacionesAtributos.validarObjetosNulo(hora, "Hora");
        this.hora = hora;
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
        sb.append(", fecha=").append(fecha);
        sb.append(", hora=").append(hora);
        sb.append(", estadio=").append(estadio);
        sb.append(", banda=").append(banda);
        sb.append('}');
        return sb.toString();
    }
}

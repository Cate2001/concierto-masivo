package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.ArrayList;
import java.util.List;

public class Estadio {
    private final int id;
    private String nombre;
    private final int capacidad;
    private String ubicacion;
    private final List<Silla> sillasEstadio = new ArrayList<>();

    public Estadio(String nombre, int capacidad, String ubicacion) {
        this.id = GeneradorId.generarIdEstadio();
        ValidacionesAtributos.validarCapacidadEstadio(capacidad);
        setNombre(nombre);
        this.capacidad = capacidad;
        setUbicacion(ubicacion);
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

    public int getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        ValidacionesAtributos.validarNullVacio(ubicacion, "Ubicacion");
        this.ubicacion = ubicacion;
    }

    private void guardarSilla(String letraFila, int numeroSilla, TipoBoleta categoria) {
        Silla silla = new Silla(letraFila, numeroSilla, categoria);
        sillasEstadio.add(silla);
    }

    public void agregrarSillas() {
        ValidacionesAtributos.validarContenidoSillas(sillasEstadio.isEmpty());
        final int FILAS_VIP = (capacidad / 2) / 10;
        final int FILAS_GENERALES = capacidad / 10;
        final int SILLAS_POR_FILA = 10;
        final int NUMERO_INICIAL_SILLA = 1;
        int numeroSilla = NUMERO_INICIAL_SILLA;
        String[] LETRAS_FILAS = {"A", "B", "C"};
        final int CANTIDAD_LETRAS_FILA = LETRAS_FILAS.length;
        int indiceLetraActual = 0;
        int numeroFila = 0;
        String letraFila;
        boolean recorridoCompletoArr = false;

        for (int i = 0; i < FILAS_GENERALES; i++) {
            if (recorridoCompletoArr) {
                letraFila = LETRAS_FILAS[indiceLetraActual] + numeroFila;
            } else {
                letraFila = LETRAS_FILAS[indiceLetraActual];
            }
            for (int j = 0; j < SILLAS_POR_FILA; j++) {
                if (FILAS_VIP > i) {
                    guardarSilla(letraFila, numeroSilla, TipoBoleta.VIP);
                } else {
                    guardarSilla(letraFila, numeroSilla, TipoBoleta.GENERAL);
                }
                numeroSilla++;
            }
            numeroSilla = NUMERO_INICIAL_SILLA;
            if (indiceLetraActual >= CANTIDAD_LETRAS_FILA - 1) {
                recorridoCompletoArr = true;
                indiceLetraActual = 0;
                numeroFila++;
            } else {
                indiceLetraActual++;
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Estadio{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", capacidad=").append(capacidad);
        sb.append(", ubicacion='").append(ubicacion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

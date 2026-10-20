package com.cate.SGCM.model;

import com.cate.SGCM.enums.*;
import com.cate.SGCM.exception.CapacidadInsuficienteException;
import com.cate.SGCM.exception.CapacidadInvalidaException;
import com.cate.SGCM.exception.SillasNoOrganizadasException;
import com.cate.SGCM.util.GeneradorId;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.ArrayList;
import java.util.List;

/**
 * Recinto donde se celebra un {@link Concierto}.
 *
 * Al construirse genera el mapa de {@link Silla} correspondiente a su capacidad: las primeras
 * filas se reparten en categoría {@code VIP} y el resto en {@code GENERAL}. Cambiar la capacidad
 * descarta el mapa anterior y lo regenera.
 *
 * El reparto de asientos es responsabilidad exclusiva de la clase: desde fuera solo se consultan.
 */
public class Estadio {
    private final int id;
    private String nombre;
    private int capacidad;
    private String ubicacion;
    private final List<Silla> sillasEstadio = new ArrayList<>();

    public Estadio(String nombre, int capacidad, String ubicacion) {
        this.id = GeneradorId.generarIdEstadio();
        setCapacidad(capacidad);
        setNombre(nombre);
        setUbicacion(ubicacion);
        organizarSillas();
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

    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Cambia la capacidad del estadio y regenera su mapa de asientos.
     *
     * Descarta las sillas existentes y las vuelve a crear según la nueva capacidad, así que se
     * pierden las asignaciones anteriores.
     *
     * @param capacidad nueva capacidad; debe cumplir las reglas de capacidad del recinto
     */
    public void setCapacidad(int capacidad) {
        validarCapacidad(capacidad);
        if (!sillasEstadio.isEmpty()) sillasEstadio.clear();
        this.capacidad = capacidad;
        organizarSillas();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        ValidacionesAtributos.validarNullVacio(ubicacion, "ubicación");
        this.ubicacion = ubicacion;
    }

    private void validarCapacidad(int capacidad) {
        if (capacidad < 20) {
            throw new CapacidadInsuficienteException("La capacidad debe ser mayor o igual a 20");
        }
        if (capacidad % 10 != 0) {
            throw new CapacidadInvalidaException("La capacidad debe ser múltiplo de 10");
        }
    }

    private void agregarSilla(String letraFila, int numeroSilla, TipoBoleto categoria) {
        Silla silla = new Silla(letraFila, numeroSilla, categoria);
        sillasEstadio.add(silla);
    }

    private void organizarSillas() {
        if (!sillasEstadio.isEmpty())
            throw new SillasNoOrganizadasException("Las sillas del estadio ya se encuentran organizadas");
        final int filasVip = (capacidad / 2) / 10;
        final int filasGenerales = capacidad / 10;
        final int sillasPorFila = 10;
        final int numeroInicialSilla = 1;
        int numeroSilla = numeroInicialSilla;
        String[] letrasFilas = {"A", "B", "C"};
        final int cantidadLetrasFilas = letrasFilas.length;
        int indiceLetraActual = 0;
        int numeroFila = 0;
        String letraFila;
        boolean llegoAlFinalDeLasLetras = false;

        for (int i = 0; i < filasGenerales; i++) {
            if (llegoAlFinalDeLasLetras) {
                letraFila = letrasFilas[indiceLetraActual] + numeroFila;
            } else {
                letraFila = letrasFilas[indiceLetraActual];
            }
            for (int j = 0; j < sillasPorFila; j++) {
                if (filasVip > i) {
                    agregarSilla(letraFila, numeroSilla, TipoBoleto.VIP);
                } else {
                    agregarSilla(letraFila, numeroSilla, TipoBoleto.GENERAL);
                }
                numeroSilla++;
            }
            numeroSilla = numeroInicialSilla;
            if (indiceLetraActual >= cantidadLetrasFilas - 1) {
                llegoAlFinalDeLasLetras = true;
                indiceLetraActual = 0;
                numeroFila++;
            } else {
                indiceLetraActual++;
            }
        }
    }

    /**
     * Devuelve una copia del mapa de asientos del estadio.
     *
     * @return lista de sillas; modificarla no afecta al estadio
     */
    public List<Silla> listarSillas(){
        return new ArrayList<>(sillasEstadio);
    }

    /**
     * Reserva un asiento del estadio.
     *
     * Punto de entrada previsto para la selección de asientos desde la venta; todavía no aplica
     * ninguna lógica de selección y siempre devuelve {@code true}.
     *
     * @return {@code true} siempre, por ahora
     */
    public boolean seleccionarSilla(){
        //falta implementar, no recuerdo como y por que, verificar eso
       return true;

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

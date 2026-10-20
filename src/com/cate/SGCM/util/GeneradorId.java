package com.cate.SGCM.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Generador de identificadores para las entidades del sistema.
 *
 * Cada tipo de entidad tiene su propio contador secuencial, así que dos entidades distintas
 * pueden compartir un mismo número sin confundirse. El primer id asignado es 1. Los contadores
 * son estáticos y no están pensados para concurrencia.
 */
public final class GeneradorId {

    private static int contadorIdEstadio = 0;
    private static int contadorIdBanda = 0;
    private static int contadorIdSilla = 0;
    private static int contadorIdUsuario = 0;
    private static int contadorIdCancion = 0;
    private static int contadorIdBoleto = 0;
    private static int contadorIdConcierto = 0;
    private static int contadorIdControlIngreso = 0;
    private static final Random RANDOM = new SecureRandom();
    private static final String CARACTERES_ALFANUMERICOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static int generarIdEstadio() {
        return ++contadorIdEstadio;
    }

    public static int generarIdBanda() {
        return ++contadorIdBanda;
    }

    public static int generarIdSilla() {
        return ++contadorIdSilla;
    }

    public static int generarIdUsuario() {
        return ++contadorIdUsuario;
    }

    public static int generarIdCancion() {
        return ++contadorIdCancion;
    }

    public static int generarIdConcierto() {
        return ++contadorIdConcierto;
    }

    public static int generarIdControlIngreso() {
        return ++contadorIdControlIngreso;
    }

    /**
     * Genera el código legible de un boleto, por ejemplo {@code BLT-000001-4XQZ}.
     *
     * El número de secuencia es único por boleto; los cuatro caracteres aleatorios solo evitan
     * que dos boletos consecutivos se lean como un bloque continuo.
     *
     * @return código del boleto
     */
    public static String generarCodigoBoleto() {
        int secuencia = ++contadorIdBoleto;
        StringBuilder codigo = new StringBuilder();
        codigo.append("BLT");
        codigo.append("-");
        codigo.append(String.format("%06d", secuencia));
        codigo.append("-");
        
        for (int i = 0; i < 4; i++) {
            int indice = RANDOM.nextInt(CARACTERES_ALFANUMERICOS.length());
            codigo.append(CARACTERES_ALFANUMERICOS.charAt(indice));
        }
        
        return codigo.toString();
    }
}

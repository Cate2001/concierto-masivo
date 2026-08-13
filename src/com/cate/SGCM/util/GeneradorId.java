package com.cate.SGCM.util;

import java.security.SecureRandom;
import java.util.Random;

public final class GeneradorId {

    private static int contadorIdEstadio = 0;
    private static int contadorIdBanda = 0;
    private static int contadorIdSilla = 0;
    private static int contadorIdUsuario = 0;
    private static int contadorIdCancion = 0;
    private static int contadorIdBoleto = 0;
    private static int contadorIdConcierto = 0;
    private static final Random random = new SecureRandom();
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

    public static String generarCodigoBoleto() {
        int secuencia = ++contadorIdBoleto;
        StringBuilder codigo = new StringBuilder();
        codigo.append("BLT");
        codigo.append("-");
        codigo.append(String.format("%06d", secuencia));
        codigo.append("-");
        
        for (int i = 0; i < 4; i++) {
            int indice = random.nextInt(CARACTERES_ALFANUMERICOS.length());
            codigo.append(CARACTERES_ALFANUMERICOS.charAt(indice));
        }
        
        return codigo.toString();
    }
}

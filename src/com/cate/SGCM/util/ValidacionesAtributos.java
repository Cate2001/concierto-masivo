package com.cate.SGCM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ValidacionesAtributos {
    public static void validarNumeroNagativo(int numero, String campo) {
        if (numero <= 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo o igual a 0");
        }
    }

    public static void validarDouble(double numero, String campo) {
        if (numero < 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo");
        }
    }

    public static void validarNullVacio(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede ser nulo o vacio");
        }
    }

    public static void validarTelefono(String telefono) {
        validarNullVacio(telefono, "Telefono");
        if (telefono.length() != 10) {
            throw new IllegalArgumentException("El telefono debe tener 10 digitos");
        }
    }

    public static void validarEmail(String email) {
        validarNullVacio(email, "Email");
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no es valido");
        }
    }

    public static void validarFechaObjeto(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            formato.setLenient(false);
            formato.format(fecha);

        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no es valida");
        }
    }

    public static <T> void validarObjetosNulo(T objeto, String campo) {
        if (objeto == null) {
            throw new IllegalArgumentException(
                    campo + " no puede ser nulo");
        }
    }

    private static boolean ValidarEsPar(int numero) {
        return numero % 10 == 0;
    }

    public static void validarCapacidadEstadio(int capacidad) {
        validarNumeroNagativo(capacidad, "Capacidad");
        if (capacidad < 20) {
            throw new IllegalArgumentException("La capacidad debe ser mayor o igual a 20");
        }
        if (!ValidarEsPar(capacidad)) {
            throw new IllegalArgumentException("La capacidad debe ser par");
        }
    }

    public static void validarContenidoSillas(boolean sinContenido) {
        if (!sinContenido) throw new IllegalArgumentException("Las sillas del estadio ya se encuentran organizadas");
    }
}

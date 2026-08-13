package com.cate.SGCM.enums;

public enum TipoBoleta {

    VIP("VIP", 150.00),
    GENERAL("General", 50.00);

    private final String nombreZona;
    private final double precio;

    TipoBoleta(String nombreZona, double precio) {
        this.nombreZona = nombreZona;
        this.precio = precio;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public double getPrecio() {
        return precio;
    }
}

package com.cate.SGCM.enums;

public enum TipoBoleto {

    VIP(150000),
    GENERAL(50000);

    private final double precio;

    TipoBoleto(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}

package com.cate.SGCM.repository;

import com.cate.SGCM.model.Boleto;

import java.util.ArrayList;
import java.util.List;

public class BoletoRepository {
    private List<Boleto> boletos = new ArrayList<>();

    public void addBoleto(Boleto boleto) {
        boletos.add(boleto);
    }

    public List<Boleto> listarTodos() {
        if (!boletos.isEmpty()){
            return new ArrayList<>(boletos);
        }
       return null;
    }

    public Boleto buscarPorCodigo(String codigo) {
        if (!boletos.isEmpty()) {
            for (Boleto boleto : boletos) {
                if (boleto.getCodigo().equals(codigo)) {
                    return boleto;
                }
            }
        }
        return null;
    }

    public boolean buscarBoletosActivosUsuario(int identificacion) {
        if (!boletos.isEmpty()) {
            for (Boleto boletoActual : boletos) {
                if (boletoActual.getUsuario().getIdentificacion() == identificacion) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminarBoleto(String codigoBoleto) {
        Boleto buscarBoleto = buscarPorCodigo(codigoBoleto);
        if (buscarBoleto != null){
            boletos.remove(buscarBoleto);
            return true;
        }
        return false;
    }
}

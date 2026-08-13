package com.cate.SGCM.repository;

import com.cate.SGCM.model.Banda;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.ArrayList;
import java.util.List;

public class BandaRepository {
    private List<Banda> bandas = new ArrayList<>();

    public void addBanda(Banda banda) {
        ValidacionesAtributos.validarObjetosNulo(banda, "Banda");
        bandas.add(banda);
    }

    public List<Banda> listarTodos() {
        return new ArrayList<>(bandas);
    }

    public Banda buscarPorId(int id) {
        for (Banda banda : bandas) {
            if (banda.getId() == id) {
                return banda;
            }
        }
        return null;
    }
}

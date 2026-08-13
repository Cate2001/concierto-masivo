package com.cate.SGCM.repository;

import com.cate.SGCM.model.Estadio;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.ArrayList;
import java.util.List;

public class EstadioRepository {
    private List<Estadio> estadios = new ArrayList<>();

    public void addEstadio(Estadio estadio) {
        ValidacionesAtributos.validarObjetosNulo(estadio, "Estadio");
        estadios.add(estadio);
    }

    public List<Estadio> listarTodos() {
        return new ArrayList<>(estadios);
    }

    public Estadio buscarPorId(int id) {
        for (Estadio estadio : estadios) {
            if (estadio.getId() == id) {
                return estadio;
            }
        }
        return null;
    }
}

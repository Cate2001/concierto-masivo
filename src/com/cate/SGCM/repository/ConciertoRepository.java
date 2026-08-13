package com.cate.SGCM.repository;

import com.cate.SGCM.model.Concierto;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.ArrayList;
import java.util.List;

public class ConciertoRepository {
    private List<Concierto> conciertos = new ArrayList<>();

    public void addConcierto(Concierto concierto) {
        ValidacionesAtributos.validarObjetosNulo(concierto, "Concierto");
        conciertos.add(concierto);
    }

    public List<Concierto> listarTodos() {
        return new ArrayList<>(conciertos);
    }
}

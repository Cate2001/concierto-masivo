package com.cate.SGCM.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioGenerico <T, ID>{
    private final Map<ID, T> almacen = new HashMap<>();

    public T guardarInformacion(ID clave, T valor) {
        return almacen.put(clave, valor);
    }

    public T buscarRegistro(ID idRegistro) {
        return almacen.get(idRegistro);
    }

    public List<T> listarRegistros() {
        return new ArrayList<>(almacen.values());
    }

    public T eliminarRegistro(ID idRegistro) {
        return almacen.remove(idRegistro);
    }

}

package com.cate.SGCM.repository;

import java.util.ArrayList;
import java.util.List;

//Falta por terminar las operacione crud de control de ingreso

public class ControlIngreso {
    List<ControlIngreso> almacen = new ArrayList<>();

    public void guardarRegistro(ControlIngreso controlIngreso) {
        almacen.add(controlIngreso);
    }

    public void buscarRegistro(){

    }
    public List<ControlIngreso> listarRegistros(){
        return new ArrayList<>();

    }
}

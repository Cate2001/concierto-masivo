package com.cate.SGCM.services;


import com.cate.SGCM.repository.BoletoRepository;
import com.cate.SGCM.repository.UsuarioRepository;
import com.cate.SGCM.model.*;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.Set;

public class UsuarioService {
    //registrar un usuario para que pueda existir en el sistema y alamcenarlo en una coleccion
    private final UsuarioRepository repositoryUsuario;
    private final BoletoRepository boleto;

    public UsuarioService(UsuarioRepository repositoryUsuario, BoletoRepository boleto) {
        ValidacionesAtributos.validarObjetosNulo(repositoryUsuario, "Repository");
        ValidacionesAtributos.validarObjetosNulo(boleto, "Boleto");
        this.repositoryUsuario = repositoryUsuario;
        this.boleto = boleto;
    }

    public boolean registrarUsuario(Usuario usuarioPorGuardar) {
        ValidacionesAtributos.validarObjetosNulo(usuarioPorGuardar, "Usuario");
        return repositoryUsuario.guardarUsuario(usuarioPorGuardar);
    }

    public Usuario buscarUsuario(int identificacionUsuario) {
        ValidacionesAtributos.validarNumeroNagativo(identificacionUsuario, "Identificacion");
        return repositoryUsuario.buscarUsuario(identificacionUsuario);
    }

    public Set<Usuario> mostarUsuariosRegistrados() {
        return repositoryUsuario.mostrarUsuarios();
    }

    public boolean actualizarUsuario(){
        //implemenmtar metodo actualizar usaurio

    }

    public boolean eliminarUsuario(int identificacion) {
        ValidacionesAtributos.validarNumeroNagativo(identificacion, "identificación");
        boolean tieneBoletosUsuario = boleto.buscarBoletosActivosUsuario(identificacion);
        if (!tieneBoletosUsuario) {
            return repositoryUsuario.eliminarUsuario(identificacion);
        }
        return false;
    }
}

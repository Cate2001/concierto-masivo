package com.cate.SGCM.services;


import com.cate.SGCM.model.*;
import com.cate.SGCM.util.ValidacionesAtributos;

import java.util.Set;

public class UsuarioService {
    //registrar un usuario para que pueda existir en el sistema y alamcenarlo en una coleccion
    private final UsuarioRepository repositoryUsuario;
    private final BoletoRepository boleto;

    public UsuarioService(UsuarioRepository repositoryUsuario, BoletoRepository boleto) {
        ValidacionesAtributos.validarObjetoNulo(repositoryUsuario, "repositorio de usuarios");
        ValidacionesAtributos.validarObjetoNulo(boleto, "repositorio de boletos");
        this.repositoryUsuario = repositoryUsuario;
        this.boleto = boleto;
    }

    public boolean registrarUsuario(Usuario usuarioPorGuardar) {
        ValidacionesAtributos.validarObjetoNulo(usuarioPorGuardar, "usuario");
        return repositoryUsuario.guardarUsuario(usuarioPorGuardar);
    }

    public Usuario buscarUsuario(int identificacionUsuario) {
        ValidacionesAtributos.validarPositivo(identificacionUsuario, "identificación");
        return repositoryUsuario.buscarUsuario(identificacionUsuario);
    }

    public Set<Usuario> mostarUsuariosRegistrados() {
        return repositoryUsuario.mostrarUsuarios();
    }

    public boolean actualizarUsuario(){
        //implemenmtar metodo actualizar usaurio

    }

    public boolean eliminarUsuario(int identificacion) {
        ValidacionesAtributos.validarPositivo(identificacion, "identificación");
        boolean tieneBoletosUsuario = boleto.buscarBoletosActivosUsuario(identificacion);
        if (!tieneBoletosUsuario) {
            return repositoryUsuario.eliminarUsuario(identificacion);
        }
        return false;
    }
}

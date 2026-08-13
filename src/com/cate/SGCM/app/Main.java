package com.cate.SGCM.app;

import com.cate.SGCM.repository.*;
import com.cate.SGCM.enums.*;
import com.cate.SGCM.model.*;
import com.cate.SGCM.services.*;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        BoletoRepository boletoRepository = new BoletoRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository, boletoRepository);
        Usuario usuario = new Usuario("Leidy", "Lorena", 23, Genero.FEMENINO, 123456789);

        boolean agregarUsuario = false;
        try {
            agregarUsuario = usuarioService.registrarUsuario(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (agregarUsuario) {
            System.out.println("Usuario registrado exitosamente");
        } else {
            System.out.println("El usuario ya existe");
        }

        Usuario encontrarUsuario = null;
        try {
            encontrarUsuario = usuarioService.buscarUsuario(1123132324);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (encontrarUsuario == null) {
            System.out.println("El usuario no existe");
        } else {
            System.out.println(encontrarUsuario);
        }

        Set<Usuario> usuarios = usuarioService.mostarUsuariosRegistrados();
        if (usuarios.isEmpty()){
            System.out.println("No existen usuarios registrados");
        }else{
            System.out.println(usuarios);
        }


    }
}

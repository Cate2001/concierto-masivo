package com.cate.SGCM.repository;

import com.cate.SGCM.model.Usuario;

import java.util.*;

public class UsuarioRepository {
    private Set<Usuario> usuarios = new HashSet<>();

    public boolean guardarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    public Usuario buscarUsuario(int identificacion) {
        if (!usuarios.isEmpty()) {
            for (Usuario usuarioActual : usuarios) {
                if (identificacion == usuarioActual.getIdentificacion()) {
                    return usuarioActual;
                }
            }
        }
        return null;
    }

    public Set<Usuario> mostrarUsuarios() {
        return Collections.unmodifiableSet(usuarios);
    }

    public boolean eliminarUsuario(int identificacion) {
        Usuario usuario = buscarUsuario(identificacion);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }


}

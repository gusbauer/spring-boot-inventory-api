package com.hibernate.ferreteria.Servicios;

import com.hibernate.ferreteria.Repositorios.Repo_usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private Repo_usuarios repoUsuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Al devolver el objeto directamente, Spring Security usa los métodos que añadimos en Usuario.java
        return repoUsuarios.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}
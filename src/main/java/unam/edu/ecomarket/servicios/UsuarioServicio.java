package unam.edu.ecomarket.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.repositorios.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepositorio.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El usuario con este email ya está registrado");
        }

        // Cifrar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));


        return usuarioRepositorio.save(usuario);
    }



}


package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.repositorios.UsuarioRepositorio;


@Service
public class UsuarioServicio  {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepositorio.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El usuario con este email ya está registrado");
        }
        // Cifrar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepositorio.save(usuario);
    }

    public Usuario loginUsuario(String correo, String password){
        Usuario usuario = usuarioRepositorio.findByCorreo(correo);
        if(usuario != null){
            if(passwordEncoder.matches(password, usuario.getPassword())){
                return usuario;
            }
        }
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }



}


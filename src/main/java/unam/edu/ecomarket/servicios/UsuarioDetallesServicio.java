package unam.edu.ecomarket.servicios;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.repositorios.UsuarioRepositorio;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioDetallesServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ENTRO A USUARIO DETALLES SERVICIO");
        Usuario usuario = usuarioRepositorio.findByCorreo(username);
        if (usuario == null) {
            System.out.println("USUARIO NO ENCONTRADO: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        System.out.println("USUARIO ENCONTRADO: " + usuario.getCorreo());
        return usuario;
    }
}

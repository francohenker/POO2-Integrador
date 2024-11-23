package unam.edu.ecomarket.controladores;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.servicios.UsuarioServicio;


@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/register")
    public String home() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Usuario usuario) {
        try {
//            Usuario nuevoUsuario2 = new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword());
            Usuario nuevoUsuario = usuarioServicio.registrarUsuario(new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword()));
            System.out.println(usuario.getIdUsuario());
            if (usuario.getIdUsuario() == null) {
                return "register";
            }
            return "login";
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}



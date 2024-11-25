package unam.edu.ecomarket.controladores;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.servicios.UsuarioServicio;


@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/register")
    public String home(HttpSession session) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioServicio.registrarUsuario(new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword()));
            System.out.println(usuario.getIdUsuario());
            if (nuevoUsuario.getIdUsuario() == null) {
                return "register";
            }

            return "login";
        } catch (Exception e) {
            return "register";

        }
    }







}



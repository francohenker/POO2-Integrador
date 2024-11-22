package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.servicios.UsuarioServicio;


@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registro")
    public String registrarUsuario(@RequestBody String email, String password) {
        try {
            Usuario usuario = new Usuario(email, password);
            Usuario nuevoUsuario = usuarioServicio.registrarUsuario(usuario);
//            return ResponseEntity.ok(nuevoUsuario);
            return "login";
        } catch (RuntimeException e) {
            System.out.println("error: " + e);
//            return ResponseEntity.badRequest().body(e.getMessage());
            return "login";
        }
    }

}



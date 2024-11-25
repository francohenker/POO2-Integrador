package unam.edu.ecomarket.controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.servicios.UsuarioServicio;

@Controller
public class LoginControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private HttpSession session;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
//
//    @PostMapping("/login")
//    public String loginPost(HttpSession session, @RequestParam String correo, @RequestParam String password) throws Exception {
//        try{
//            session.getAttribute("usuario").toString();
//            Usuario usuario = usuarioServicio.loginUsuario(correo, password);
//            session.setAttribute("usuario", usuario);
//
//        } catch (Exception e) {
//            return "login";
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("usuario");
        return "login";
    }
}






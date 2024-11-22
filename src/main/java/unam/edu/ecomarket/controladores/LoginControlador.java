package unam.edu.ecomarket.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginControlador {
    @GetMapping("/login")
    public String login() {
        System.out.println("ENTRO A LOGIN DESDE EL CONTROLADOR");
        return "login";
    }


}

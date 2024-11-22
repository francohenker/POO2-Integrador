package unam.edu.ecomarket.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class publico {
    @GetMapping("/register")
    public String home() {
        return "register";
    }
}

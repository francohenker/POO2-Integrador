package unam.edu.ecomarket.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {
    @GetMapping({"/home", "/home/"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping({"/admin", "/admin/"})
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public String admin(Model model) {
        model.addAttribute("contenidoAdmin", "/admin/home");
        return "admin/adminPage";
    }
}

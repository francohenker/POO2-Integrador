package unam.edu.ecomarket.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {
    @GetMapping({"/home", "/home/"})
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().toString();
        System.out.println("Usuario: " + username);
        System.out.println("Rol: " + role);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "index";
    }

    @GetMapping({"/admin", "/admin/"})
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    public String admin(Model model) {
        model.addAttribute("contenidoAdmin", "/admin/home");
        return "admin/adminPage";
    }
}

package unam.edu.ecomarket.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unam.edu.ecomarket.modelo.Categoria;

import java.util.List;

@Controller
@RequestMapping("/admin/paquete")
public class PaqueteControlador {

    @GetMapping
    public String paquetes(Model modelo) {
        modelo.addAttribute("contenidoAdmin", "/admin/viewPaquetes");
        return "/admin/adminPage";
    }

    @GetMapping("/crear")
    public String crearPaquete(Model model) {
        model.addAttribute("contenidoAdmin", "/admin/addPaquetes");
        return "/admin/adminPage";
    }
}

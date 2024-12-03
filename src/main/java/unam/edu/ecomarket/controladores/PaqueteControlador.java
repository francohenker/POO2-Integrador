package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.servicios.PaqueteServicio;

import java.util.List;

@Controller
@RequestMapping("/admin/paquete")
public class PaqueteControlador {

    @Autowired
    private PaqueteServicio paqueteServicio;

    @GetMapping
    public String paquetes(Model modelo) {
        List<Paquete> paquetes = paqueteServicio.obtenerTodosLosPaquetes();
        modelo.addAttribute("paquetes", paquetes);
        modelo.addAttribute("contenidoAdmin", "/admin/viewPaquetes");
        return "/admin/adminPage";
    }

    @GetMapping("/crear")
    public String crearPaquete(Model model) {
        model.addAttribute("contenidoAdmin", "/admin/addPaquetes");
        return "/admin/adminPage";
    }
}

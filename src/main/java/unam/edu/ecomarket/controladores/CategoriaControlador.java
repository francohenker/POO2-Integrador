package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.servicios.CategoriaServicio;

import java.util.List;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaControlador {
    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping
    public String categoria(Model modelo) {
        modelo.addAttribute("contenidoAdmin", "/admin/viewCategory");
        return "/admin/adminPage";
    }

    @GetMapping("/crear")
    public String crearCategoria(Model model) {
        List<Categoria> categorias = categoriaServicio.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("contenidoAdmin", "/admin/addCategory");
        return "/admin/adminPage";
    }
}

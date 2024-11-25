package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Imagen;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.CategoriaServicio;

import java.io.IOException;
import java.util.ArrayList;
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

    @PostMapping
    public String agregarCategoria(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "padre", required = false) Long padre)
    {
        try {
            Categoria categoriaPadre = null;
            if (padre != null) {
                categoriaPadre = categoriaServicio.obtenerCategoriaPorId(padre);
            }
            Categoria categoria = new Categoria(nombre, descripcion, categoriaPadre);
            categoriaServicio.agregarCategoria(categoria);

            return "redirect:/admin/categoria";
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar categoria: " + e);
            return "redirect:/admin/categoria/crear";
        }
    }
}

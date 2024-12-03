package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.PaqueteServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;

import java.util.List;

@Controller
@RequestMapping("/admin/paquete")
public class PaqueteControlador {

    @Autowired
    private PaqueteServicio paqueteServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public String paquetes(Model modelo) {
        List<Paquete> paquetes = paqueteServicio.obtenerTodosLosPaquetes();
        modelo.addAttribute("paquetes", paquetes);
        modelo.addAttribute("contenidoAdmin", "/admin/viewPaquetes");
        return "/admin/adminPage";
    }

    @GetMapping("/crear")
    public String crearPaquete(Model model) {
        List<Producto> productos = productoServicio.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("contenidoAdmin", "/admin/addPaquetes");
        return "/admin/adminPage";
    }

    @PostMapping
    public String agregarPaquete(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "productos") List<Integer> productosIds
    ) {
        List<Producto> productos = productoServicio.obtenerProductosPorIds(productosIds);
        Paquete paquete = new Paquete();
        paquete.setNombre(nombre);
        paquete.setItems(productos);
        paqueteServicio.agregarPaquete(paquete);
        return "redirect:/admin/paquete";
    }

    @GetMapping("/{id}/editar")
    public String editarPaquete(@PathVariable Integer id, Model model) {
        Paquete paquete = paqueteServicio.obtenerPaquetePorId(id);
        List<Producto> productos = productoServicio.obtenerTodosLosProductos();
        List<Integer> selectedProductIds = paquete.getItems().stream().map(Producto::getId).toList();
        model.addAttribute("paquete", paquete);
        model.addAttribute("productos", productos);
        model.addAttribute("selectedProductIds", selectedProductIds);
        model.addAttribute("contenidoAdmin", "/admin/editPaquete");
        return "/admin/adminPage";
    }

    @PutMapping("/{id}")
    public String actualizarPaquete(
            @PathVariable Integer id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "productos") List<Integer> productosIds
    ) {
        List<Producto> productos = productoServicio.obtenerProductosPorIds(productosIds);
        Paquete paquete = paqueteServicio.obtenerPaquetePorId(id);
        paquete.setNombre(nombre);
        paquete.setItems(productos);
        paqueteServicio.actualizarPaquete(paquete);
        return "redirect:/admin/paquete";
    }

    @DeleteMapping("/{id}")
    public String borrarPaquete(@PathVariable Integer id) {
        paqueteServicio.borrarPaquetePorId(id);
        return "redirect:/admin/paquete";
    }
}

package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.servicios.CarritoServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;
import org.springframework.ui.Model;
import unam.edu.ecomarket.modelo.Producto;

import java.util.ArrayList;
import java.util.List;


@Controller()
@RequestMapping({"/producto", "/producto/"})
public class ProductoControladorCliente {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CarritoServicio carritoServicio;

    @GetMapping
    public String producto(){
        return "producto";
    }

    @GetMapping("/{id}")
    public String verProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("imagenes", producto.getImagenes());
        return "producto";
    }

    @PostMapping("/agregarAlCarrito")
    public String agregarAlCarrito(@RequestParam Integer id) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        carritoServicio.agregarProducto(producto);
        return "redirect:/producto/" + id;
    }

    @GetMapping("/images")
    public String mostrarProducto(Model model) {
        List<String> imagenes = obtenerImagenesDelProducto();
        model.addAttribute("imagenes", imagenes);
        return "producto";
    }

    private List<String> obtenerImagenesDelProducto() {
        // Lógica para recuperar las rutas de las imágenes
        List<String> imagenes = new ArrayList<>();
        imagenes.add("/images/1733227506419perro.jpeg");
        imagenes.add("/images/2.png");
        imagenes.add("/images/3.png");
        return imagenes;
    }






}

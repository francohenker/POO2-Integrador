package unam.edu.ecomarket.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.ProductoServicio;

/**
 * Clase controlador para los productos en ecomarket.
 */

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public String productos(Model modelo, Categoria categoria) {
        var productos = productoServicio.obtenerProductos(categoria);
        modelo.addAttribute("producto", productos);
        return "productos";
    }


    @PostMapping
    public String agregarProducto(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "precio") double precio,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(required = false) byte[] imagen,
            @RequestParam(name = "stock", required = false) Integer stock
            )
    {
        Categoria categoriaNombre  = new Categoria(categoria, "");
        try {
            Producto producto = new Producto(nombre, descripcion, categoriaNombre, precio, stock, imagen);
            productoServicio.agregarProducto(producto);

        } catch (IllegalArgumentException e) {
            return "productos";
        }
        return "productos";
    }










}


package unam.edu.ecomarket.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Imagen;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.ProductoServicio;

import java.io.IOException;

/**
 * Clase controlador para los productos en ecomarket.
 */

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    /*
    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }
     */

    @GetMapping
    public String productos(Model modelo, Categoria categoria) {
        var productos = productoServicio.obtenerProductos(categoria);
        modelo.addAttribute("producto", productos);
        return "productos";
    }

    @GetMapping("/agregar")
    public String insertarProductoDePrueba() {
        productoServicio.insertarProductoDePrueba();
        return "redirect:/productos";
    }


    @PostMapping
    public String agregarProducto(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "precio") double precio,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "imagen", required = false) MultipartFile imagen,
            @RequestParam(name = "stock", required = false) Integer stock
            )
    {
        // Categoria categoriaNombre  = new Categoria(categoria, "");
        try {
            Categoria categoriaObj = productoServicio.obtenerCategoriaPorNombre(categoria);
            String rutaImagen = productoServicio.guardarImagen(imagen);
            Imagen nuevaImagen = new Imagen(rutaImagen);
            Producto producto = new Producto(nombre, descripcion, categoriaObj, precio, stock, nuevaImagen);
            productoServicio.agregarProducto(producto);
            return "redirect:/productos";
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen: " + e);
            return "productos";
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar producto: " + e);
            return "productos";
        }
    }










}


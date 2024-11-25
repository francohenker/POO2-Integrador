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
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controlador para los productos en ecomarket.
 */

@Controller
@RequestMapping("/admin/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    /*
    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }
     */

    @GetMapping
    public String productos(Model modelo) {
        modelo.addAttribute("contenidoAdmin", "/admin/viewProducts");
        return "/admin/adminPage";
    }

    @GetMapping("/crear")
    public String crearProducto(Model model) {
        List<Categoria> categorias = productoServicio.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("contenidoAdmin", "/admin/addProducts");
        return "/admin/adminPage";
    }


    @PostMapping
    public String agregarProducto(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "precio") double precio,
            @RequestParam(name = "categoria") Long categoria,
            @RequestParam(name = "imagenes", required = false) MultipartFile[] imagenes,
            @RequestParam(name = "stock", required = false) Integer stock
            )
    {
        try {
            Categoria categoriaObj = productoServicio.obtenerCategoriaPorId(categoria);
            Producto producto = new Producto(nombre, descripcion, categoriaObj, precio, stock, new ArrayList<>());

            if (imagenes != null) {
                for (MultipartFile imagen : imagenes) {
                    String rutaImagen = productoServicio.guardarImagen(imagen);
                    Imagen nuevaImagen = new Imagen(rutaImagen);
                    nuevaImagen.setProducto(producto);
                    producto.getImagenes().add(nuevaImagen);
                }
            }
            productoServicio.agregarProducto(producto);
            return "redirect:/admin/producto";
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen: " + e);
            return "agregarProducto";
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar producto: " + e);
            return "agregarProducto";
        }
    }










}


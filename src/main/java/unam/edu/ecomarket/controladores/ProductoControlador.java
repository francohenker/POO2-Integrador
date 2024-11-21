package unam.edu.ecomarket.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String productos(Model modelo) {
        var productos = productoServicio.obtenerProductos(Categoria.VACIO);
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

        Producto producto = new Producto(nombre, descripcion, Categoria.VACIO, precio, stock, imagen);
        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
            producto.setCategoria(categoriaEnum);
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria no valida");
            return "productos";
        }
        productoServicio.agregarProducto(producto);
        return "productos";
    }










}


package unam.edu.ecomarket.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.ProductoServicio;

/**
 * Clase controlador para los productos en ecomarket.
 */

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping("/productos")
    public String productos(Model modelo) {
        var productos = productoServicio.obtenerProductos(Categoria.VACIO);
        modelo.addAttribute("producto", productos);
        return "productos";
    }


    @PostMapping("/productos")
    public String agregarProducto(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "precio") double precio,
            @RequestParam(name = "categoria")  String categoria
            )
    {


        Producto producto = new Producto(nombre, descripcion, Categoria.VACIO, precio);

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


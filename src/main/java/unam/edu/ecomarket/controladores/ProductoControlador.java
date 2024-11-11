package unam.edu.ecomarket.controladores;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.ProductoServicio;

import javax.naming.Binding;

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
    public String agregarProducto(@Valid Producto producto, Categoria categoria, BindingResult resultado) {
        if(resultado.hasErrors()) {
            return "productos";
        }
        productoServicio.agregarProducto(producto);
        return "productos";
    }











}


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
}

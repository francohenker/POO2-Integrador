package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.CarritoServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;

@Controller
@RequestMapping("/cart")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("productos", carritoServicio.obtenerProductosEnCarrito());
        return "carritoCompras";
    }

    @PostMapping
    public String carrito(Model model){
        return "carritoCompras";
    }


    @PostMapping("/agregarAlCarrito")
    public String agregarAlCarrito(@RequestParam Integer id, @RequestParam Integer cantidad) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        carritoServicio.agregarProducto(producto, cantidad);
        return "redirect:/producto/" + id;
    }


    @PostMapping("/eliminarDelCarrito")
    public String eliminarDelCarrito(@RequestParam Integer id) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        carritoServicio.eliminarProducto(producto);
        return "redirect:/cart";
    }
}

package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.servicios.CarritoServicio;

@Controller
@RequestMapping("/cart")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("productos", carritoServicio.obtenerProductosEnCarrito());
        return "carritoCompras";
    }

    @PostMapping
    public String carrito(Model model){

        return "carritoCompras";
    }

    @PostMapping("/incrementar")
    public String incrementarCantidad(@RequestParam Integer id) {
        carritoServicio.incrementarCantidad(id);
        return "redirect:/carrito";
    }

    @PostMapping("/decrementar")
    public String decrementarCantidad(@RequestParam Integer id) {
        carritoServicio.decrementarCantidad(id);
        return "redirect:/carrito";
    }
}

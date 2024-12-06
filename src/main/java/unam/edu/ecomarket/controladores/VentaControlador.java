package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.servicios.VentaServicio;

@Controller
public class VentaControlador {

    @Autowired
    private VentaServicio ventaServicio;

    @PostMapping("/venta")
    public String realizarVenta(@AuthenticationPrincipal Usuario usuario,
                                 @RequestParam("metodo_pago") String metodoPago,
                                 Model model) {
        ventaServicio.realizarVenta(usuario, metodoPago);
        return "redirect:/home";
    }
}

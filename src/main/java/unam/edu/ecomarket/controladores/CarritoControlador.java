package unam.edu.ecomarket.controladores;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unam.edu.ecomarket.modelo.DetalleOrden;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.repositorios.DetalleOrdenRepositorio;
import unam.edu.ecomarket.repositorios.UsuarioRepositorio;
import unam.edu.ecomarket.servicios.CarritoServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private DetalleOrdenRepositorio detalleOrdenRepositorio;

    @GetMapping
    public String verCarrito(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("detalles", carritoServicio.obtenerProductos(usuarioRepositorio.findByCorreo(username)));
        return "carritoCompras";
    }

    @PostMapping
    public String carrito(Model model){
        return "carritoCompras";
    }

    @PostMapping("/agregarAlCarrito")
    public String agregarAlCarrito(Model model, @RequestParam Integer id, @RequestParam Integer cantidad) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        Producto producto = productoServicio.obtenerProductoPorId(id);
        carritoServicio.agregarProducto(producto, cantidad, usuarioRepositorio.findByCorreo(username));
        return "redirect:/producto/" + id;
    }

//    @Transactional
    @PostMapping("/eliminarDelCarrito")
    public String eliminarDelCarrito(@RequestParam Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        DetalleOrden detalle = detalleOrdenRepositorio.findById(id);
        carritoServicio.eliminarDetalle(detalle, usuarioRepositorio.findByCorreo(username));
        return "redirect:/cart";
    }
}



















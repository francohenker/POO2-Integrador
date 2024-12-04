package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.Descuento;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.modelo.descuento.DescuentoFijo;
import unam.edu.ecomarket.modelo.descuento.DescuentoPorcentual;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.repositorios.DescuentoRepositorio;
import unam.edu.ecomarket.servicios.DescuentoServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/descuentos")
public class DescuentoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private DescuentoRepositorio descuentoRepositorio;

    @Autowired
    private DescuentoServicio descuentoServicio;

    @GetMapping
    public String mostrarFormularioDescuentos(Model model) {
        List<ProductoItem> productoItems = productoServicio.obtenerTodosItemProductos();
        List<Descuento> descuentos = descuentoRepositorio.findAll();
        model.addAttribute("productoItems", productoItems);
        model.addAttribute("descuentos", descuentos);
        model.addAttribute("contenidoAdmin", "/admin/descuentos");
        return "/admin/adminPage";
    }

    @PostMapping("/aplicar")
    public String aplicarDescuento(@RequestParam Integer productoId, @RequestParam Integer descuentoId, Model model) {
        Optional<ProductoItem> itemOpt = productoServicio.obtenerProductoItemPorId(productoId);
        Optional<Descuento> descuentoOpt = descuentoRepositorio.findById(descuentoId);

        if (itemOpt.isPresent() && descuentoOpt.isPresent()) {
            ProductoItem item = itemOpt.get();
            Descuento descuento = descuentoOpt.get();
            DescuentoStrategy descuentoStrategy;

            if (descuento.getTipo() == TipoDescuento.PORCENTUAL) {
                descuentoStrategy = new DescuentoPorcentual(descuento.getValor());
            } else {
                descuentoStrategy = new DescuentoFijo(descuento.getValor());
            }

            double precioConDescuento = descuentoServicio.calcularConDescuento(item, descuentoStrategy, descuento.getTipo(), descuento.getValor());
            model.addAttribute("precioConDescuento", precioConDescuento);
            model.addAttribute("productoItem", item);
        }

        return "redirect:/admin/descuentos/productosConDescuentos";
    }

    @GetMapping("/productosConDescuentos")
    public String mostrarProductosConDescuentos(Model model) {
        List<ProductoItem> productoItems = productoServicio.obtenerTodosItemProductos();
        model.addAttribute("productoItems", productoItems);
        return "admin/productosConDescuentos";
    }
}
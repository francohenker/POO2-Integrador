package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.modelo.descuento.DescuentoFijo;
import unam.edu.ecomarket.modelo.descuento.DescuentoPorcentual;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.servicios.DescuentoServicio;
import unam.edu.ecomarket.servicios.PaqueteServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/descuentos")
public class DescuentoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private PaqueteServicio paqueteServicio;

    @Autowired
    private DescuentoServicio descuentoServicio;

    @GetMapping
    public String mostrarProductosConDescuentos(Model model) {
        List<Producto> productosConDescuento = productoServicio.obtenerProductosConDescuento();
        List<Paquete> paquetesConDescuento = paqueteServicio.obtenerPaquetesConDescuento();

        model.addAttribute("productosConDescuento", productosConDescuento);
        model.addAttribute("paquetesConDescuento", paquetesConDescuento);

        model.addAttribute("contenidoAdmin", "/admin/viewDiscount");
        return "admin/adminPage";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDescuentos(Model model) {
        List<ProductoItem> productoItems = productoServicio.obtenerTodosItem();
        model.addAttribute("productoItems", productoItems);
        model.addAttribute("contenidoAdmin", "/admin/addDiscount");
        return "/admin/adminPage";
    }

    @PostMapping("/aplicar")
    public String aplicarDescuento(@RequestParam Integer productoId,
                                   @RequestParam TipoDescuento tipoDescuento,
                                   @RequestParam Double valorDescuento,
                                   Model model) {
        Optional<ProductoItem> itemOpt = productoServicio.obtenerProductoItemPorId(productoId);

        if (itemOpt.isEmpty()){
            itemOpt = paqueteServicio.obtenerPaqueteItemPorId(productoId);
        }

        if (itemOpt.isPresent()) {
            ProductoItem item = itemOpt.get();
            DescuentoStrategy descuentoStrategy;

            if (tipoDescuento == TipoDescuento.PORCENTUAL) {
                descuentoStrategy = new DescuentoPorcentual(valorDescuento);
            } else {
                descuentoStrategy = new DescuentoFijo(valorDescuento);
            }

            double precioConDescuento = descuentoServicio.calcularConDescuento(item, descuentoStrategy, tipoDescuento, valorDescuento);

            model.addAttribute("precioConDescuento", precioConDescuento);
            model.addAttribute("productoItem", item);
        }
        return "redirect:/admin/descuentos";
    }


    @DeleteMapping("/eliminar")
    public String eliminarDescuento(@RequestParam Integer productoId,
                                    Model model) {
        Optional<ProductoItem> itemOpt = productoServicio.obtenerProductoItemPorId(productoId);

        //Para paquetes
        if (itemOpt.isEmpty()) {
            itemOpt = paqueteServicio.obtenerPaqueteItemPorId(productoId);
        }

        //Para productos
        if (itemOpt.isPresent()) {
            ProductoItem item = itemOpt.get();
            descuentoServicio.eliminarDescuento(item);
        }

        return "redirect:/admin/descuentos";
    }




}
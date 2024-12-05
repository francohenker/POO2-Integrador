package unam.edu.ecomarket.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.Descuento;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.modelo.descuento.DescuentoFijo;
import unam.edu.ecomarket.modelo.descuento.DescuentoPorcentual;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.repositorios.DescuentoRepositorio;
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
    private DescuentoRepositorio descuentoRepositorio;

    @Autowired
    private DescuentoServicio descuentoServicio;

    @GetMapping("/crear")
    public String mostrarFormularioDescuentos(Model model) {
        List<ProductoItem> productoItems = productoServicio.obtenerTodosItem();
        List<Descuento> descuentos = descuentoRepositorio.findAll();
        model.addAttribute("productoItems", productoItems);
        model.addAttribute("descuentos", descuentos);
        model.addAttribute("contenidoAdmin", "/admin/addDiscount");
        return "/admin/adminPage";
    }

    @PostMapping("/aplicar")
    public String aplicarDescuento(@RequestParam Integer productoId, @RequestParam Integer descuentoId, Model model) {
        Optional<ProductoItem> itemOpt = productoServicio.obtenerProductoItemPorId(productoId);
        Optional<Descuento> descuentoOpt = descuentoRepositorio.findById(descuentoId);
        String tipoProducto = "Producto";

        System.out.println("Producto ID: " + productoId);
        System.out.println("Descuento ID: " + descuentoId);
        System.out.println("Item: " + itemOpt);

        if (itemOpt.isEmpty()){
            itemOpt = paqueteServicio.obtenerPaqueteItemPorId(productoId);
            tipoProducto = "Paquete";
            System.out.println("Item: " + itemOpt);
        }

        if (itemOpt.isPresent() && descuentoOpt.isPresent()) {
            System.out.println("Linea 1");
            ProductoItem item = itemOpt.get();
            System.out.println("Linea 2");
            Descuento descuento = descuentoOpt.get();
            System.out.println("Linea 3");
            DescuentoStrategy descuentoStrategy;

            System.out.println("Linea 4");
            if (descuento.getTipo() == TipoDescuento.PORCENTUAL) {
                System.out.println("Linea 5");
                descuentoStrategy = new DescuentoPorcentual(descuento.getValor());
            } else {
                System.out.println("Linea 6");
                descuentoStrategy = new DescuentoFijo(descuento.getValor());
            }

            System.out.println("Linea 7");
            System.out.println("Producto Item: " + item);
            System.out.println("Descuento: " + descuentoStrategy);
            System.out.println("Tipo de descuento: " + descuento.getTipo());
            System.out.println("Valor de descuento: " + descuento.getValor());

            double precioConDescuento = descuentoServicio.calcularConDescuento(item, descuentoStrategy, descuento.getTipo(), descuento.getValor(), tipoProducto);
            System.out.println("Linea 8");
            System.out.println("Precio con descuento: " + precioConDescuento);

            model.addAttribute("precioConDescuento", precioConDescuento);
            System.out.println("Linea 9");
            model.addAttribute("productoItem", item);
        }

        return "redirect:/admin/descuentos/productosConDescuentos";
    }

    @GetMapping("/productosConDescuentos")
    public String mostrarProductosConDescuentos(Model model) {
        List<Producto> productosConDescuento = productoServicio.obtenerProductosConDescuento();

        List<Paquete> paquetesConDescuento = paqueteServicio.obtenerPaquetesConDescuento();

        System.out.println("Productos con descuento: " + productosConDescuento);
        System.out.println("Paquetes con descuento: " + paquetesConDescuento);

        model.addAttribute("productosConDescuento", productosConDescuento);
        model.addAttribute("paquetesConDescuento", paquetesConDescuento);
        return "admin/productosConDescuentos";
    }
}
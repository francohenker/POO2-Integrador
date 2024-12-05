package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.repositorios.PaqueteRepositorio;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;

@Service
public class DescuentoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private PaqueteRepositorio paqueteRepositorio;

    public double calcularConDescuento(ProductoItem item, DescuentoStrategy descuentoStrategy, TipoDescuento tipoDescuento, double valorDescuento) {
        double precioConDescuento = descuentoStrategy.aplicarDescuento(item.getPrecio());
        item.setPrecioConDescuento(precioConDescuento);
        item.setTipoDescuentoAplicado(tipoDescuento);
        item.setValorDescuentoAplicado(valorDescuento);

        if (item instanceof Producto) {
            productoRepositorio.save((Producto) item);
        } else if (item instanceof Paquete) {
            Paquete paquete = (Paquete) item;
            paquete.setPrecioConDescuento(precioConDescuento);
            paquete.setTipoDescuentoAplicado(tipoDescuento);
            paquete.setValorDescuentoAplicado(valorDescuento);
            paqueteRepositorio.save(paquete);
        }
        return precioConDescuento;
    }

    public void eliminarDescuento(ProductoItem item) {
        item.setPrecioConDescuento(null);
        item.setTipoDescuentoAplicado(null);
        item.setValorDescuentoAplicado(null);

        if (item instanceof Producto) {
            productoRepositorio.save((Producto) item);
        } else if (item instanceof Paquete) {
            paqueteRepositorio.save((Paquete) item);
        }
    }
}
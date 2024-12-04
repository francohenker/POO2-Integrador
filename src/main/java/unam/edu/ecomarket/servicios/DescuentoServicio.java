package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;

@Service
public class DescuentoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public double calcularConDescuento(ProductoItem item, DescuentoStrategy descuentoStrategy, TipoDescuento tipoDescuento, double valorDescuento) {
        double precioConDescuento = descuentoStrategy.aplicarDescuento(item.getPrecio());
        item.setPrecioConDescuento(precioConDescuento);
        item.setTipoDescuentoAplicado(tipoDescuento);
        item.setValorDescuentoAplicado(valorDescuento);
        productoRepositorio.save((Producto) item);
        return precioConDescuento;
    }
}
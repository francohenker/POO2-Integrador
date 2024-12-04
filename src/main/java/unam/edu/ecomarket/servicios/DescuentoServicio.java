package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.Descuento;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;

@Service
public class DescuentoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public double calcularConDescuento(ProductoItem item, Descuento descuento) {
        double precioConDescuento;
        if (item instanceof Producto) {
            Producto producto = (Producto) item;
            if (descuento.getTipo() == TipoDescuento.PORCENTUAL) {
                precioConDescuento = producto.getPrecio() * (1 - descuento.getValor() / 100);
            } else {
                precioConDescuento = producto.getPrecio() - descuento.getValor();
            }
            producto.setPrecioConDescuento(precioConDescuento);
            productoRepositorio.save(producto);
            return precioConDescuento;
        }
        throw new IllegalArgumentException("Tipo de ProductoItem no soportado");
    }
}
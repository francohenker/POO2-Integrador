package unam.edu.ecomarket.servicios;

import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoServicio {

    private ArrayList<Producto> productosEnCarrito = new ArrayList<>();

    public void agregarProducto(Producto producto, Integer cantidad) {
        producto.setCantidad(cantidad);
        productosEnCarrito.add(producto);
    }

    public List<Producto> obtenerProductosEnCarrito() {
        return productosEnCarrito;
    }

    public void incrementarCantidad(Integer id) {
        for (Producto p : productosEnCarrito) {
            if (p.getId().equals(id) && p.getStock() < p.getCantidad()) {
                p.setCantidad(p.getCantidad() + 1);
                return;
            }
        }
    }

    public void decrementarCantidad(Integer id) {
        for (Producto p : productosEnCarrito) {
            if (p.getId().equals(id) && p.getStock() > 1) {
                p.setCantidad(p.getCantidad() - 1);
                return;
            }
        }
    }


}

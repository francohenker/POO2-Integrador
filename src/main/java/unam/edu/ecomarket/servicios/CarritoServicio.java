package unam.edu.ecomarket.servicios;

import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoServicio {

    private List<Producto> productosEnCarrito = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productosEnCarrito.add(producto);
    }

    public List<Producto> obtenerProductosEnCarrito() {
        return productosEnCarrito;
    }

//    public void agregarProducto(Producto producto) {
//        for (Producto p : productosEnCarrito) {
//            if (p.getId().equals(producto.getId())) {
//                p.setStock(p.getStock() + 1);
//                return;
//            }
//        }
//        producto.setStock(1);
//        productosEnCarrito.add(producto);
//    }

    public void incrementarCantidad(Integer id) {
        for (Producto p : productosEnCarrito) {
            if (p.getId().equals(id)) {
                p.setStock(p.getStock() + 1);
                return;
            }
        }
    }

    public void decrementarCantidad(Integer id) {
        for (Producto p : productosEnCarrito) {
            if (p.getId().equals(id) && p.getStock() > 1) {
                p.setStock(p.getStock() - 1);
                return;
            }
        }
    }


}

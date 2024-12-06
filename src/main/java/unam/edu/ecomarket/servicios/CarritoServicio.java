package unam.edu.ecomarket.servicios;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.DetalleOrden;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.repositorios.DetalleOrdenRepositorio;
import unam.edu.ecomarket.repositorios.OrdenRepositorio;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private DetalleOrdenRepositorio detalleOrdenRepositorio;

    private ArrayList<Producto> productosEnCarrito = new ArrayList<>();

    public void agregarProducto(Producto producto, Integer cantidad) {
        List<Producto> productoEnCarrito = obtenerProductosEnCarrito();
        int cant = 0;
        if(productoEnCarrito.contains(producto)){
            cant = productoEnCarrito.get(productoEnCarrito.indexOf(producto)).getCantidad();
            eliminarProducto(producto);
        }

        producto.setCantidad(cantidad + cant);
        productosEnCarrito.add(producto);

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(producto);
        detalleOrden.setCantidad(cantidad);
        detalleOrdenRepositorio.save(detalleOrden);

    }

    @Transactional
    public void eliminarProducto(Producto producto) {
        productosEnCarrito.remove(producto);
        detalleOrdenRepositorio.removeByProductoId(producto.getId());
    }

    public List<Producto> obtenerProductosEnCarrito() {
        List<DetalleOrden> productos = detalleOrdenRepositorio.findAll();
        List<Producto> productosEnCarrito = new ArrayList<>();
        for (DetalleOrden detalleOrden : productos) {
            productosEnCarrito.add(detalleOrden.getProducto());
        }
        return productosEnCarrito;


    }



}

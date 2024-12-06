package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.DetalleOrden;
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
        producto.setCantidad(cantidad);
        productosEnCarrito.add(producto);

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(producto);
        detalleOrden.setCantidad(cantidad);
        detalleOrdenRepositorio.save(detalleOrden);
    }

    public void eliminarProducto(Producto producto) {
        productosEnCarrito.remove(producto);
//        ordenRepositorio.removeById(producto.getId());
//        ordenRepositorio.remove

        detalleOrdenRepositorio.removeById(producto.getId());
    }

    public List<Producto> obtenerProductosEnCarrito() {
//        return ordenRepositorio.findAll();
        return productosEnCarrito;
    }



}

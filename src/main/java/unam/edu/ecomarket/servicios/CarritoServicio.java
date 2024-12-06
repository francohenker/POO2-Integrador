package unam.edu.ecomarket.servicios;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.DetalleOrden;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.Usuario;
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

    public void agregarProducto(Producto producto, Integer cantidad, Usuario usuario) {
        List<Orden> orden = obtenerProductosEnCarrito(usuario);
        List<DetalleOrden> detalleProductoEnCarrito;
        if(orden.size() == 0){
            detalleProductoEnCarrito = new ArrayList<>();
        }else{
            detalleProductoEnCarrito = orden.get(0).getDetalleOrden();
        }

        int cant = 0;

        for(DetalleOrden detalleOrden : detalleProductoEnCarrito){
            if(detalleProductoEnCarrito.contains(producto)){
                cant = detalleProductoEnCarrito.get(detalleProductoEnCarrito.indexOf(detalleOrden)).getCantidad();
                eliminarDetalle(detalleOrden, usuario);
            }
        }

//        if(detalleProductoEnCarrito.contains(producto)){
//            cant = detalleProductoEnCarrito.get(detalleProductoEnCarrito.indexOf(detalleOrden)).getCantidad();
//            eliminarDetalle(detalleOrden);
//        }
//
//        detalleOrden.setCantidad(cantidad + cant);
//        productosEnCarrito.add(detalleOrden.getProducto());

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(producto);
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        if(producto.getPrecioConDescuento() != null) {
            detalleOrden.setDescuento(producto.getPrecioConDescuento());
            detalleOrden.setTipoDescuento("Descuento");
        }

        detalleProductoEnCarrito.add(detalleOrden);

        if(orden.isEmpty()){
            orden.add(new Orden());
            orden.get(0).setUsuario(usuario);
        }
        orden.get(0).setDetalleOrden(detalleProductoEnCarrito);

        detalleOrdenRepositorio.save(detalleOrden);
        detalleOrden.setOrden(orden.get(0));
        ordenRepositorio.save(orden.get(0));
    }

    @Transactional
    public void eliminarDetalle(DetalleOrden detalleOrden, Usuario usuario) {
        List<Orden> orden = obtenerProductosEnCarrito(usuario);
        List<DetalleOrden> detalleProductoEnCarrito = orden.get(0).getDetalleOrden();
        detalleProductoEnCarrito.remove(detalleOrden);
//        productosEnCarrito.remove(producto);
//        detalleOrdenRepositorio.removeByProductoId(producto.getId());
        orden.get(0).setDetalleOrden(detalleProductoEnCarrito);
        ordenRepositorio.save(orden.get(0));

    }



    public List<Orden> obtenerProductosEnCarrito(Usuario user) {
        List<Orden> orden = ordenRepositorio.findByUsuarioN(user);
        return orden.size() > 0 ? orden : new ArrayList<>();

//        List<DetalleOrden> productos = detalleOrdenRepositorio.findAll();
//        List<Producto> productosEnCarrito = new ArrayList<>();
//        for (DetalleOrden detalleOrden : productos) {
//            productosEnCarrito.add(detalleOrden.getProducto());
//        }
//        return productosEnCarrito;


    }

    public List<DetalleOrden> obtenerProductos(Usuario user) {
        List<Orden> orden = ordenRepositorio.findByUsuarioN(user);
        if(orden.isEmpty()){
            return new ArrayList<>();
        }
        return orden.get(0).getDetalleOrden();


    }


}

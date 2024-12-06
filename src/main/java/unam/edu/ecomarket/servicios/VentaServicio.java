package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.modelo.Usuario;
import unam.edu.ecomarket.repositorios.OrdenRepositorio;

import java.time.LocalDate;

@Service
public class VentaServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    public void realizarVenta (Usuario usuario, String tipoPago) {
        // Buscar la orden con tipoPago 'seleccionar' y el usuario logueado
        Orden orden = ordenRepositorio.findByUsuarioAndTipoPago(usuario, "seleccionar");


        if (orden != null) {
            // Actualizar la fecha y el tipo de pago
            orden.setFechaOrden(LocalDate.now());
            orden.setTipoPago(tipoPago);
            ordenRepositorio.save(orden);

            // Actualizar los detalles de la orden
            orden.getDetalleOrden().forEach(detalleOrden -> {
                System.out.println("Producto ID: " + detalleOrden.getProducto().getId());
                System.out.println("Valor Descuento Aplicado: " + detalleOrden.getProducto().getValorDescuentoAplicado());
                System.out.println("Tipo Descuento Aplicado: " + detalleOrden.getProducto().getTipoDescuentoAplicado());

                detalleOrden.setCantidad(detalleOrden.getCantidad());
                detalleOrden.setPrecio(detalleOrden.getProducto().getPrecio());
                detalleOrden.setDescuento(detalleOrden.getProducto().getValorDescuentoAplicado());
                detalleOrden.setTipoDescuento(detalleOrden.getProducto().getTipoDescuentoAplicado().toString());
            });

            ordenRepositorio.save(orden);
        }
    }
}

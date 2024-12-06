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
        // Buscar la orden con tipoPago null y el usuario logueado
        Orden orden = ordenRepositorio.findByUsuarioAndTipoPagoIsNull(usuario);

        if (orden != null) {
            // Actualizar la fecha y el tipo de pago
            orden.setFechaOrden(LocalDate.now());
            orden.setTipoPago(tipoPago);
            ordenRepositorio.save(orden);

            // Actualizar los detalles de la orden
            orden.getDetalleOrden().forEach(detalle -> {
                detalle.setCantidad(detalle.getProducto().getCantidad());
                System.out.println("Desde el servicio de venta, cantidad: " + detalle.getProducto().getCantidad());
                detalle.setPrecio(detalle.getProducto().getPrecio());
                System.out.println("Desde el servicio de venta, precio: " + detalle.getProducto().getPrecio());
                detalle.setDescuento(detalle.getProducto().getValorDescuentoAplicado());
                System.out.println("Desde el servicio de venta, descuento: " + detalle.getProducto().getValorDescuentoAplicado());
                detalle.setTipoDescuento(detalle.getProducto().getTipoDescuentoAplicado().name());
                System.out.println("Desde el servicio de venta, tipo de descuento aplicado: " + detalle.getProducto().getTipoDescuentoAplicado().name());
            });

            ordenRepositorio.save(orden);
        }
    }
}

package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * La clase Orden representa una orden de compra en ecomarket.
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Orden {
    /**
     * Identificador unico de la orden.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Fecha en la que se realizo la orden.
     */
    @NotBlank
    @Column(nullable = false)
    private LocalDate fechaOrden = LocalDate.now();

    /**
     * Detalle de un pedido.
     */
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrden> detalleOrden;

    /**
     * Agrega un detalle a la orden.
     *
     * @param detalleOrden El producto del pedido a agregar.
     */

    public void agregarDetalle(DetalleOrden detalleOrden) {
        this.detalleOrden.add(detalleOrden);
        detalleOrden.setOrden(this);
    }

    /**
     * Elimina un detalle de la orden.
     *
     * @param detalleOrden El producto del pedido a eliminar.
     */
    public void eliminarDetalle(DetalleOrden detalleOrden) {
        this.detalleOrden.remove(detalleOrden);
        detalleOrden.setOrden(null);
    }


}

package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase DetalleOrden que representa el detalle de una orden en ecomarket.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class DetalleOrden {
    /**
     * Identificador unico del detalle de la orden.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    /**
     * La cantidad de productos en el detalle de la orden.
     */
    @NotBlank
    private Integer cantidad;
    /**
     * El precio del detalle de la orden.
     */

    private double descuento;

    private String tipoDescuento;

    @NotBlank
    private double precio;

    /**
     * El producto del detalle de la orden.
     */
    @OneToOne
    private Producto producto;

    /**
     * La orden asociada al detalle.
     */
    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}
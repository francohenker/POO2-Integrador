package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase que representa una venta en ecomarket.
 */
@Entity
@Getter @NoArgsConstructor
public class Venta {

    /**
     * Constructor para crear una nueva venta.
     *
     * @param orden La orden de la venta.
     */
    public Venta(Orden orden) {
        this.orden = orden ;
    }

    /**
     * Identificador unico de la venta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Fecha en la que se realizo la venta.
     */
    @Column(nullable = false)
    private LocalDate fechaVenta = LocalDate.now();

    /**
     * El tipo de pago de la venta.
     */
    @Column(nullable = false)
    private String tipoPago = "Seleccionar";

    /**
     * La orden asociada a la venta.
     */
    @OneToOne
    private Orden orden;





}

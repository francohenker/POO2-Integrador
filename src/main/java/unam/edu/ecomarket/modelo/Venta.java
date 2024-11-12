package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter @NoArgsConstructor
public class Venta {

    public Venta(Orden orden) {
        this.orden = orden;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaVenta = LocalDate.now();

    @Column(nullable = false)
    private String tipoPago = "Seleccionar";

    @OneToOne
    private Orden orden;





}

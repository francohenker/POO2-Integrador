package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    private Integer cantidad;

    @NotBlank
    private double precio;

    @OneToOne
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}

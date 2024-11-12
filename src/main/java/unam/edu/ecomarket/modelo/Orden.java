package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private LocalDate fechaOrden = LocalDate.now();

    @NotBlank
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrden> detalleOrden;


    public void agregarDetalle(DetalleOrden detalleOrden) {
        this.detalleOrden.add(detalleOrden);
        detalleOrden.setOrden(this);
    }

    public void eliminarDetalle(DetalleOrden detalleOrden) {
        this.detalleOrden.remove(detalleOrden);
        detalleOrden.setOrden(null);
    }


}

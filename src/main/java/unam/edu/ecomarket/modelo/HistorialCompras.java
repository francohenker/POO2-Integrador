package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class HistorialCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Relación con Usuario (un usuario puede tener muchos historiales de compra)

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;  // Relación con Orden (cada historial está vinculado a una orden específica)

    // Constructor adicional
    public HistorialCompras(LocalDate fechaCompra, Usuario usuario, Orden orden) {
        this.fechaCompra = fechaCompra;
        this.usuario = usuario;
        this.orden = orden;
    }
}

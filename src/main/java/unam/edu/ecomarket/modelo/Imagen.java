package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String ruta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Imagen(String ruta) {
        this.ruta = ruta;
    }
}
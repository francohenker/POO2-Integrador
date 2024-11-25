package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Categoria {
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria(String nombre, String descripcion, Categoria padre) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.padre = padre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "padre")
    private List<Categoria> subcategorias;

    @ManyToOne
    private Categoria padre;


}

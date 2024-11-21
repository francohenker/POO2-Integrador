package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jdk.jfr.StackTrace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Categoria {
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


    private List<Categoria> subcategorias;


}

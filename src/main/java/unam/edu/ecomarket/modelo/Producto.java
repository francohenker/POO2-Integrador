package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;


    private Categoria categoria;

    @NotBlank
    @Column(nullable = false)
    private double precio;






}

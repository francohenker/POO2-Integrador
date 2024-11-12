package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad que representa el producto en ecomarket.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {
    /**
     * Constructor para crear un nuevo producto.
     *
     * @param nombre      El nombre del producto.
     * @param descripcion La descripcion del producto.
     * @param categoria   La categoria del producto.
     * @param precio      El precio del producto.
     */
    public Producto(String nombre, String descripcion, Categoria categoria, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    /**
     * El identificador unico del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * El nombre del producto.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    /**
     * La descripcion del producto.
     */
    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;

    /**
     * La categoria del producto.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    /**
     * El precio del producto.
     */
    @NotBlank
    @Column(nullable = false)
    private double precio;

    /**
     * La cantidad de stock del producto.
     */
    @NotBlank
    @Column(nullable = false)
    private Integer stock = 0;

    /**
     * La imagen del producto.
     */
    @Lob
    private byte[] imagen;





}

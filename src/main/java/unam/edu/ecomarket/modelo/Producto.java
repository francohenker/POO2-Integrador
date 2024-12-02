package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase entidad que representa el producto en ecomarket.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producto extends ProductoItem{
    /**
     * Constructor para crear un nuevo producto.
     *
     * @param nombre      El nombre del producto.
     * @param descripcion La descripcion del producto.
     * @param categoria   La categoria del producto.
     * @param precio      El precio del producto.
     */
    public Producto(String nombre, String descripcion, Categoria categoria, double precio, Integer stock, List<Imagen> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.imagenes = imagenes;
        this.stock = (stock != null) ? stock : 0;
    }
    /**
     * El identificador unico del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

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
    @ManyToOne
    @JoinColumn(name = "categoria_id")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
    @Size(max = 10, message = "No se pueden agregar más de 10 imágenes a un producto")
    private List<Imagen> imagenes = new ArrayList<>();


    @Override
    public double calcularPrecio() {
        return this.precio;
    }



}

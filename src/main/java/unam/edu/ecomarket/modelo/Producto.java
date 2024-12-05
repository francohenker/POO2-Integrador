package unam.edu.ecomarket.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase entidad que representa el producto en ecomarket.
 */
@Entity
@DiscriminatorValue("producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto extends ProductoItem {
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotBlank
    @Column(nullable = false)
    private double precio;

    @NotBlank
    @Column(nullable = false)
    private Integer stock = 0;

    @NotBlank
    private Integer cantidad = 1;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
    @Size(max = 10, message = "No se pueden agregar más de 10 imágenes a un producto")
    private List<Imagen> imagenes = new ArrayList<>();

    public Producto(String nombre, String descripcion, Categoria categoria, double precio, Integer stock, List<Imagen> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.imagenes = (imagenes != null) ? new ArrayList<>(imagenes) : new ArrayList<>();
        this.stock = (stock != null) ? stock : 0;
    }
    @Override
    public double calcularPrecio() {
        return this.precio;
    }

    @Override
    public String getTipo() {
        return "Producto";
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }
}

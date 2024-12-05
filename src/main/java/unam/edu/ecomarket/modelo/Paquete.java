package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("paquete")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Paquete extends ProductoItem {
    private String nombre;

    private String descripcion = "";
    private double precio = 0.0;
    private Integer stock = 0;

    @ManyToMany
    @JoinTable(
            name = "paquete_items",
            joinColumns = @JoinColumn(name = "paquete_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> items = new ArrayList<>();

    public Paquete(String nombre, List<Producto> items) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = 0.0;
        this.stock = 0;
        this.items = items;
    }

    public void agregarItem(Producto item) {
        this.items.add(item);
    }

    public void eliminarItem(Producto item) {
        this.items.remove(item);
    }

    @Override
    public double calcularPrecio() {
        return items.stream().mapToDouble(Producto::calcularPrecio).sum();
    }

    @Override
    public String getTipo() {
        return "paquete";
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }
}


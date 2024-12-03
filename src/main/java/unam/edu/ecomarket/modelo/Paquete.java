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

    @ManyToMany
    @JoinTable(
            name = "paquete_items",
            joinColumns = @JoinColumn(name = "paquete_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> items = new ArrayList<>();

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
}


package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Paquete extends ProductoItem {
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "paquete_items",
            joinColumns = @JoinColumn(name = "paquete_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_item_id")
    )
    private List<ProductoItem> items = new ArrayList<>();

    public void agregarItem(ProductoItem item) {
        this.items.add(item);
    }

    public void eliminarItem(ProductoItem item) {
        this.items.remove(item);
    }

    @Override
    public double calcularPrecio() {
        return items.stream().mapToDouble(ProductoItem::calcularPrecio).sum();
    }
}


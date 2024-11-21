package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Paquete extends ProductoItem {
    private String nombre;

    @OneToMany
    private List<ProductoItem> items;

    @Override
    public double calcularPrecio() {
        return items.stream()
                .mapToDouble(ProductoItem::calcularPrecio)
                .sum();
    }
}


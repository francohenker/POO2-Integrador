package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import unam.edu.ecomarket.modelo.descuento.Descuento;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class ProductoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private Double precioConDescuento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TipoDescuento tipoDescuentoAplicado;

    @Column(nullable = true)
    private Double valorDescuentoAplicado;

    public abstract String getTipo();

    public abstract double getPrecio();

    public abstract double calcularPrecio();
}

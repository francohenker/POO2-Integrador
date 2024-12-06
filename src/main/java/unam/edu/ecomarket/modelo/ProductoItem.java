package unam.edu.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unam.edu.ecomarket.modelo.descuento.TipoDescuento;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@Getter @Setter
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

    public double calcularPrecioConDescuento() {
        if (tipoDescuentoAplicado == TipoDescuento.PORCENTUAL) {
            return getPrecio() * (1 - valorDescuentoAplicado / 100);
        } else if (tipoDescuentoAplicado == TipoDescuento.FIJO) {
            return getPrecio() - valorDescuentoAplicado;
        }
        return getPrecio();
    }
}

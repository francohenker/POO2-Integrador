package unam.edu.ecomarket.modelo.descuento;

public class DescuentoPorcentual implements DescuentoStrategy {
    private final double porcentaje;

    public DescuentoPorcentual(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicarDescuento(double precioBase) {
        return precioBase - (precioBase * porcentaje / 100);
    }
}

package unam.edu.ecomarket.modelo.descuento;

public class DescuentoFijo implements DescuentoStrategy {
    private final double montoFijo;

    public DescuentoFijo(double montoFijo) {
        this.montoFijo = montoFijo;
    }

    @Override
    public double aplicarDescuento(double precioBase) {
        return Math.max(0, precioBase - montoFijo);
    }
}
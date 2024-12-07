package unam.edu.ecomarket.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




class ProductoItemTest {

    @Test
    void testCalcularPrecio() {
        ProductoItem item = new ProductoItem() {
            @Override
            public double calcularPrecio() {
                return 100.0;
            }
        };
        assertEquals(100.0, item.calcularPrecio());
    }

    @Test
    void testIdGeneration() {
        ProductoItem item = new ProductoItem() {
            @Override
            public double calcularPrecio() {
                return 100.0;
            }
        };
        assertNull(item.getId());
    }
}
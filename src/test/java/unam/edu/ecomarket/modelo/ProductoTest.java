package unam.edu.ecomarket.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoTest {
    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
    }

    @Test
    public void testAsignarNombreYDescripcion() {
        producto.setNombre("Manzana Orgánica");
        producto.setDescripcion("Una manzana fresca y ecológica.");

        var actualNombre = producto.getNombre();
        var actualDescripcion = producto.getDescripcion();

        assertEquals("Manzana Orgánica", actualNombre);
        assertEquals("Una manzana fresca y ecológica.", actualDescripcion);
    }

    @Test
    public void testAsignarPrecioYStock() {
        producto.setPrecio(10.5);
        producto.setStock(100);

        var actualPrecio = producto.getPrecio();
        var actualStock = producto.getStock();

        assertEquals(10.5, actualPrecio);
        assertEquals(100, actualStock);
    }

    @Test
    public void testRelacionProductoCategoria() {
        Categoria categoria = new Categoria("Frutas", "Productos frescos y naturales");
        producto.setCategoria(categoria);

        var actualCategoria = producto.getCategoria();

        assertEquals("Frutas", actualCategoria.getNombre());
        assertEquals("Productos frescos y naturales", actualCategoria.getDescripcion());
    }

    @Test
    public void testAgregarImagenes() {
        Imagen imagen1 = new Imagen("ruta/imagen1.jpg");
        Imagen imagen2 = new Imagen("ruta/imagen2.jpg");

        // Establecemos la relación con el producto
        imagen1.setProducto(producto);
        imagen2.setProducto(producto);

        producto.getImagenes().addAll(List.of(imagen1, imagen2));

        var actualImagenes = producto.getImagenes();
        assertEquals(2, actualImagenes.size());
        assertEquals("ruta/imagen1.jpg", actualImagenes.get(0).getRuta());
        assertEquals("ruta/imagen2.jpg", actualImagenes.get(1).getRuta());
    }
}

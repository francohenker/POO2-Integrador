package unam.edu.ecomarket.repositorios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;






@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductoRepositorioTest {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Test
    public void testFindAllByCategoria() {
        Categoria categoria = new Categoria("Electronics", "Some description");

        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Laptop");
        producto1.setCategoria(categoria);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Smartphone");
        producto2.setCategoria(categoria);

        productoRepositorio.save(producto1);
        productoRepositorio.save(producto2);

        List<Producto> productos = productoRepositorio.findAllByCategoria(categoria);

        assertThat(productos).hasSize(2).contains(producto1, producto2);
    }

    @Test
    public void testDeleteProductoById() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Laptop");

        productoRepositorio.save(producto);

        productoRepositorio.deleteProductoById(1);

        Producto deletedProducto = productoRepositorio.findById(1).orElse(null);

        assertThat(deletedProducto).isNull();
    }
}
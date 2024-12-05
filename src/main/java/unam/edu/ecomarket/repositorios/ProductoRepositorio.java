package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

    List<Producto> findByPrecioConDescuentoGreaterThan(double precioConDescuento);

    List<Producto> findAllByCategoria(Categoria categoria);

    Producto deleteProductoById(Integer id);
}

package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.ProductoItem;

@Repository
public interface ProductoItemRepositorio extends JpaRepository<ProductoItem, Integer> {

}

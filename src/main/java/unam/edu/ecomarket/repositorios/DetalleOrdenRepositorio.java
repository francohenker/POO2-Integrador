package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.DetalleOrden;

import java.util.Optional;

@Repository
public interface DetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer> {

    Optional<DetalleOrden> findById(Integer id);

    void removeById(Integer id);
}

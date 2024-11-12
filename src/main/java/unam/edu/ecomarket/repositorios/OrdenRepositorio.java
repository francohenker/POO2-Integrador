package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.Orden;

import java.util.Optional;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    Optional<Orden> findById(Integer id);
}

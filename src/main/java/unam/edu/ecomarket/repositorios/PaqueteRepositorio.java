package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.Paquete;

@Repository
public interface PaqueteRepositorio extends JpaRepository<Paquete, Integer> {
}

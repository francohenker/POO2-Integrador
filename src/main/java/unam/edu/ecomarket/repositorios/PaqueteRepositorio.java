package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.Paquete;

import java.util.List;

@Repository
public interface PaqueteRepositorio extends JpaRepository<Paquete, Integer> {
    List<Paquete> findByPrecioConDescuentoGreaterThan(double precioConDescuento);
}

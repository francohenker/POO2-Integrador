package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import unam.edu.ecomarket.modelo.descuento.Descuento;

public interface DescuentoRepositorio extends JpaRepository<Descuento, Integer> {
}
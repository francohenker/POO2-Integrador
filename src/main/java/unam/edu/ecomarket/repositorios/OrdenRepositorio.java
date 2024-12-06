package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.modelo.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    Optional<Orden> findById(Integer id);

    void removeById(Integer id);

    List<Orden> findByUsuario(Usuario usuario);

    @Query("SELECT o FROM Orden o WHERE o.usuario = :usuario AND o.tipoPago = 'Seleccionar'")
    List<Orden> findByUsuarioN(Usuario usuario);
    Orden findByUsuarioAndTipoPagoIsNull(Usuario usuario);

}

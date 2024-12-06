package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT o FROM Orden o WHERE o.usuario = :usuario AND o.tipoPago = 'seleccionar'")
    List<Orden> findByUsuarioN(Usuario usuario);

    @Query("SELECT o FROM Orden o WHERE o.usuario = :usuario AND o.tipoPago = :tipoPago")
    Orden findByUsuarioAndTipoPago(@Param("usuario") Usuario usuario, @Param("tipoPago") String tipoPago);


}

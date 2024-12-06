package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import unam.edu.ecomarket.modelo.DetalleOrden;
import unam.edu.ecomarket.modelo.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer> {

    Optional<DetalleOrden> findById(Integer id);

    void removeById(Integer id);

//    List<DetalleOrden> findByUser(Usuario usuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM DetalleOrden d WHERE d.producto.id = :productoId")
    void removeByProductoId(Integer productoId);
}

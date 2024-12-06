package unam.edu.ecomarket.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import unam.edu.ecomarket.modelo.DetalleOrden;


@Repository
public interface DetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer> {

    DetalleOrden findById(Long id);

    void removeById(Integer id);


    @Modifying
    @Transactional
    @Query("DELETE FROM DetalleOrden d WHERE d.producto.id = :productoId")
    void removeByProductoId(Integer productoId);
}

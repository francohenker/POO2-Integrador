package unam.edu.ecomarket.servicios;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.repositorios.OrdenRepositorio;

import java.util.List;

@Service
@NoArgsConstructor
public class OrdenServicio {

    @Autowired
    OrdenRepositorio ordenRepositorio;

    public OrdenServicio(OrdenRepositorio ordenRepositorio) {
        this.ordenRepositorio = ordenRepositorio;
    }

    public List<Orden> listarOrdenes() {
        return ordenRepositorio.findAll();
    }

    public Orden obtenerOrdenPorId(Integer id) {
        return ordenRepositorio.findById(id).get();
    }

    public Orden crearOrden(Orden orden) {
        return ordenRepositorio.save(orden);
    }




}

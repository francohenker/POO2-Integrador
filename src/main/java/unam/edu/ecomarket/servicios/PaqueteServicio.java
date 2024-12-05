package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.repositorios.PaqueteRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteServicio {

    @Autowired
    private PaqueteRepositorio paqueteRepositorio;


    public List<Paquete> obtenerTodosLosPaquetes() {
        return paqueteRepositorio.findAll();
    }

    public Paquete agregarPaquete(Paquete paquete) {
        return paqueteRepositorio.save(paquete);
    }

    public Paquete obtenerPaquetePorId(Integer id) {
        return paqueteRepositorio.findById(id).orElse(null);
    }

    public void borrarPaquete(Paquete paquete) {
        paqueteRepositorio.delete(paquete);
    }

    public void borrarPaquetePorId(Integer id) {
        paqueteRepositorio.deleteById(id);
    }

    public Paquete actualizarPaquete(Paquete paquete) {
        return paqueteRepositorio.save(paquete);
    }

   public List<ProductoItem> obtenerTodosLosItemsPaquetes() {
        return paqueteRepositorio.findAll().stream()
                .map(paquete -> (ProductoItem) paquete)
                .collect(Collectors.toList());
    }
}
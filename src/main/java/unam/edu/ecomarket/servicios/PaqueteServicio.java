package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Paquete;
import unam.edu.ecomarket.repositorios.PaqueteRepositorio;

import java.util.List;

@Service
public class PaqueteServicio {

    @Autowired
    private PaqueteRepositorio paqueteRepositorio;


    public List<Paquete> obtenerTodosLosPaquetes() {
        return paqueteRepositorio.findAll();
    }
}
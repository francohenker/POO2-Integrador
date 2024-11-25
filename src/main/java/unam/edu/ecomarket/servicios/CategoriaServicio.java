package unam.edu.ecomarket.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.repositorios.CategoriaRepositorio;

import java.util.List;

@Service
public class CategoriaServicio {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepositorio.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
    }
}

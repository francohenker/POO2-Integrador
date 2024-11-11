package unam.edu.ecomarket.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;

import java.util.List;

@Service
public class ProductoServicio {
    @Autowired
    ProductoRepositorio personaRepositorio;

    public ProductoServicio() {
    }

    public List<Producto> obtenerProductos(Categoria categoria) {
        return personaRepositorio.findAllByCategoria(categoria);
    }


    public void agregarProducto(Producto producto) {
        personaRepositorio.save(producto);
    }
}

package unam.edu.ecomarket.servicios;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;

import java.util.List;

@Service
@NoArgsConstructor
public class ProductoServicio {
    @Autowired
    ProductoRepositorio personaRepositorio;

    public ProductoServicio(ProductoRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    public List<Producto> obtenerProductos(Categoria categoria) {
        return personaRepositorio.findAllByCategoria(categoria);
    }


    public void agregarProducto(Producto producto) {
        personaRepositorio.save(producto);
    }
}

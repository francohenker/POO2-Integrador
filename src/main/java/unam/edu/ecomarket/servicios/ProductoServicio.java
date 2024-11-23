package unam.edu.ecomarket.servicios;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Imagen;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;
import unam.edu.ecomarket.repositorios.CategoriaRepositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductoServicio {

    private final Path rootLocation = Paths.get("src/main/resources/static/images/productos");

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> obtenerProductos(Categoria categoria) {
        return productoRepositorio.findAllByCategoria(categoria);
    }

    public String guardarImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Fallo al almacenar archivo vacío " + file.getOriginalFilename());
        }
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        return "/images/productos/" + file.getOriginalFilename();
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public Categoria obtenerCategoriaPorNombre(String nombre) {
        return categoriaRepositorio.findByNombre(nombre);
    }

    public void insertarProductoDePrueba() {
        Categoria categoria = categoriaRepositorio.findByNombre("Electrónica");
        if (categoria == null) {
            categoria = new Categoria("Electrónica", "Productos electrónicos");
            categoriaRepositorio.save(categoria);
        }

        Imagen imagen = new Imagen("/images/productos/ejemplo.jpg");
        Producto producto = new Producto("Producto de Prueba", "Descripción del producto de prueba", categoria, 99.99, 10, imagen);
        productoRepositorio.save(producto);
    }

}

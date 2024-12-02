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

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    public List<Producto> obtenerProductos(Categoria categoria) {
        return productoRepositorio.findAllByCategoria(categoria);
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    public String guardarImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío.");
        }
        String filename = System.currentTimeMillis() + file.getOriginalFilename();
        Path destinationFile = this.rootLocation.resolve(
                Paths.get(filename))
                .normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new RuntimeException("No se puede almacenar el archivo fuera del directorio actual.");
        }
        Files.createDirectories(this.rootLocation);
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile);
        }
        return destinationFile.toString();
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }



    /*
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
     */



}

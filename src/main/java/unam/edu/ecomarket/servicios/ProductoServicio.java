package unam.edu.ecomarket.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Imagen;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.modelo.ProductoItem;
import unam.edu.ecomarket.modelo.descuento.DescuentoStrategy;
import unam.edu.ecomarket.modelo.descuento.DescuentoFijo;
import unam.edu.ecomarket.modelo.descuento.DescuentoPorcentual;
import unam.edu.ecomarket.repositorios.ProductoItemRepositorio;
import unam.edu.ecomarket.repositorios.ProductoRepositorio;
import unam.edu.ecomarket.repositorios.CategoriaRepositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServicio {

    private final Path rootLocation = Paths.get("src/main/resources/static/images/productos");

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ProductoItemRepositorio productoItemRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    public List<Producto> obtenerProductosConDescuento() {
        System.out.println("Obteniendo productos con descuento");
        return productoRepositorio.findAll().stream()
                .filter(producto -> producto.getPrecioConDescuento() != null)
                .toList();
    }

    public List<ProductoItem> obtenerTodosItem() {
        return productoItemRepositorio.findAll();
    }

    public Optional<ProductoItem> obtenerProductoItemPorId(Integer id) {
        return productoRepositorio.findById(id).map(producto -> (ProductoItem) producto);
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    public List<Producto> obtenerProductosPorIds(List<Integer> ids) {
        return productoRepositorio.findAllById(ids);
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
        return "/images/productos/" + filename;
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public void aplicarDescuento(Integer id, String tipo, double valor) {
        Optional<Producto> productoOpt = productoRepositorio.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            DescuentoStrategy descuento;
            if ("PORCENTUAL".equals(tipo)) {
                descuento = new DescuentoPorcentual(valor);
            } else {
                descuento = new DescuentoFijo(valor);
            }
            producto.setPrecio(descuento.aplicarDescuento(producto.getPrecio()));
            productoRepositorio.save(producto);
        }
    }

    @Transactional
    public void borrarProducto(Integer id) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        // Eliminar archivos de imágenes
        for (Imagen imagen : producto.getImagenes()) {
            Path rutaImagen = Paths.get(imagen.getRuta());
            try {
                Files.deleteIfExists(rutaImagen);
            } catch (IOException e) {
                System.out.println("Error al eliminar la imagen: " + e.getMessage());
            }
        }
        productoRepositorio.delete(producto);
    }



}

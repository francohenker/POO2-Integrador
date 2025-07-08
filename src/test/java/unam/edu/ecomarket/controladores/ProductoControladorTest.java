package unam.edu.ecomarket.controladores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.modelo.Producto;
import unam.edu.ecomarket.servicios.CategoriaServicio;
import unam.edu.ecomarket.servicios.ProductoServicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;






public class ProductoControladorTest {

    @Mock
    private ProductoServicio productoServicio;

    @Mock
    private CategoriaServicio categoriaServicio;

    @Mock
    private Model model;

    @InjectMocks
    private ProductoControlador productoControlador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProductos() {
        List<Producto> productos = new ArrayList<>();
        when(productoServicio.obtenerTodosLosProductos()).thenReturn(productos);

        String viewName = productoControlador.productos(model);

        verify(model).addAttribute("productos", productos);
        verify(model).addAttribute("contenidoAdmin", "/admin/viewProducts");
        assertEquals("/admin/adminPage", viewName);
    }

    @Test
    public void testCrearProducto() {
        List<Categoria> categorias = new ArrayList<>();
        when(categoriaServicio.obtenerTodasLasCategorias()).thenReturn(categorias);

        String viewName = productoControlador.crearProducto(model);

        verify(model).addAttribute("categorias", categorias);
        verify(model).addAttribute("contenidoAdmin", "/admin/addProducts");
        assertEquals("/admin/adminPage", viewName);
    }

    @Test
    public void testAgregarProducto() throws IOException {
        String nombre = "Producto1";
        String descripcion = "Descripcion1";
        double precio = 100.0;
        Long categoriaId = 1L;
        MockMultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);
        Integer stock = 10;

        Categoria categoria = new Categoria();
        when(categoriaServicio.obtenerCategoriaPorId(categoriaId)).thenReturn(categoria);

        String viewName = productoControlador.agregarProducto(nombre, descripcion, precio, categoriaId, imagen, stock);

        verify(productoServicio).agregarProducto(any(Producto.class));
        assertEquals("redirect:/admin/producto", viewName);
    }

    @Test
    public void testEditarProducto() {
        Integer id = 1;
        Producto producto = new Producto();
        List<Categoria> categorias = new ArrayList<>();
        when(productoServicio.obtenerProductoPorId(id)).thenReturn(producto);
        when(categoriaServicio.obtenerTodasLasCategorias()).thenReturn(categorias);

        String viewName = productoControlador.editarProducto(id, model);

        verify(model).addAttribute("producto", producto);
        verify(model).addAttribute("categorias", categorias);
        verify(model).addAttribute("imagenes", producto.getImagenes());
        verify(model).addAttribute("contenidoAdmin", "/admin/editProduct");
        assertEquals("/admin/adminPage", viewName);
    }

    @Test
    public void testActualizarProducto() throws IOException {
        Integer id = 1;
        String nombre = "Producto1";
        String descripcion = "Descripcion1";
        double precio = 100.0;
        Long categoriaId = 1L;
        MockMultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);
        Integer stock = 10;

        Categoria categoria = new Categoria();
        Producto producto = new Producto();
        when(categoriaServicio.obtenerCategoriaPorId(categoriaId)).thenReturn(categoria);
        when(productoServicio.obtenerProductoPorId(id)).thenReturn(producto);

        String viewName = productoControlador.actualizarProducto(id, nombre, descripcion, precio, categoriaId, imagen, stock);

        verify(productoServicio).agregarProducto(any(Producto.class));
        assertEquals("redirect:/admin/producto", viewName);
    }

    @Test
    public void testBorrarProducto() {
        Integer id = 1;

        String viewName = productoControlador.borrarProducto(id);

        verify(productoServicio).borrarProducto(id);
        assertEquals("redirect:/admin/producto", viewName);
    }
}
package unam.edu.ecomarket.controladores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import unam.edu.ecomarket.modelo.Categoria;
import unam.edu.ecomarket.servicios.CategoriaServicio;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;






public class CategoriaControladorTest {

    @Mock
    private CategoriaServicio categoriaServicio;

    @Mock
    private Model model;

    @InjectMocks
    private CategoriaControlador categoriaControlador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCategoria() {
        List<Categoria> categorias = new ArrayList<>();
        when(categoriaServicio.obtenerTodasLasCategorias()).thenReturn(categorias);

        String viewName = categoriaControlador.categoria(model);

        verify(model, times(1)).addAttribute("categorias", categorias);
        verify(model, times(1)).addAttribute("contenidoAdmin", "/admin/viewCategory");
        assertEquals("/admin/adminPage", viewName);
    }

    @Test
    public void testCrearCategoria() {
        List<Categoria> categorias = new ArrayList<>();
        when(categoriaServicio.obtenerTodasLasCategorias()).thenReturn(categorias);

        String viewName = categoriaControlador.crearCategoria(model);

        verify(model, times(1)).addAttribute("categorias", categorias);
        verify(model, times(1)).addAttribute("contenidoAdmin", "/admin/addCategory");
        assertEquals("/admin/adminPage", viewName);
    }

    @Test
    public void testAgregarCategoria() {
        String nombre = "Categoria 1";
        String descripcion = "Descripcion 1";
        Long padre = null;

        String viewName = categoriaControlador.agregarCategoria(nombre, descripcion, padre);

        verify(categoriaServicio, times(1)).agregarCategoria(any(Categoria.class));
        assertEquals("redirect:/admin/categoria", viewName);
    }

    @Test
    public void testAgregarCategoriaConPadre() {
        String nombre = "Categoria 1";
        String descripcion = "Descripcion 1";
        Long padre = 1L;
        Categoria categoriaPadre = new Categoria();
        when(categoriaServicio.obtenerCategoriaPorId(padre)).thenReturn(categoriaPadre);

        String viewName = categoriaControlador.agregarCategoria(nombre, descripcion, padre);

        verify(categoriaServicio, times(1)).obtenerCategoriaPorId(padre);
        verify(categoriaServicio, times(1)).agregarCategoria(any(Categoria.class));
        assertEquals("redirect:/admin/categoria", viewName);
    }

    @Test
    public void testAgregarCategoriaConError() {
        String nombre = "Categoria 1";
        String descripcion = "Descripcion 1";
        Long padre = null;
        doThrow(new IllegalArgumentException()).when(categoriaServicio).agregarCategoria(any(Categoria.class));

        String viewName = categoriaControlador.agregarCategoria(nombre, descripcion, padre);

        assertEquals("redirect:/admin/categoria/crear", viewName);
    }
}
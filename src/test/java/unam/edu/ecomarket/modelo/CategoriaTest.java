package unam.edu.ecomarket.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    private Categoria categoriaPrincipal;
    private Categoria subcategoria;

    @BeforeEach
    void setUp() {
        // Crear categoría principal
        categoriaPrincipal = new Categoria("Electrónica", "Productos electrónicos como teléfonos y televisores");

        // Crear subcategoría y establecer relación con la categoría principal
        subcategoria = new Categoria("Teléfonos", "Dispositivos móviles y accesorios", categoriaPrincipal);

        // Asignar subcategorías a la categoría principal
        List<Categoria> subcategorias = new ArrayList<>();
        subcategorias.add(subcategoria);
        categoriaPrincipal.setSubcategorias(subcategorias);
    }

    @Test
    void testCrearCategoriaConNombreYDescripcion() {
        Categoria nuevaCategoria = new Categoria("Ropa", "Vestimenta para todas las edades");
        assertNotNull(nuevaCategoria, "La categoría no debería ser nula");
        assertEquals("Ropa", nuevaCategoria.getNombre(), "El nombre de la categoría no coincide");
        assertEquals("Vestimenta para todas las edades", nuevaCategoria.getDescripcion(), "La descripción de la categoría no coincide");
    }

    @Test
    void testCrearCategoriaConPadre() {
        assertNotNull(subcategoria, "La subcategoría no debería ser nula");
        assertEquals("Teléfonos", subcategoria.getNombre(), "El nombre de la subcategoría no coincide");
        assertEquals("Dispositivos móviles y accesorios", subcategoria.getDescripcion(), "La descripción de la subcategoría no coincide");
        assertNotNull(subcategoria.getPadre(), "La subcategoría debería tener una categoría padre");
        assertEquals("Electrónica", subcategoria.getPadre().getNombre(), "El nombre de la categoría padre no coincide");
    }

    @Test
    void testAsignarSubcategorias() {
        List<Categoria> subcategorias = categoriaPrincipal.getSubcategorias();
        assertNotNull(subcategorias, "La lista de subcategorías no debería ser nula");
        assertFalse(subcategorias.isEmpty(), "La lista de subcategorías no debería estar vacía");
        assertEquals(1, subcategorias.size(), "La lista de subcategorías debería tener un elemento");
        assertEquals("Teléfonos", subcategorias.get(0).getNombre(), "El nombre de la subcategoría no coincide");
    }

    @Test
    void testValidarRestriccionesDeTamañoNombreDescripcion() {
        Categoria categoriaValida = new Categoria("Casa", "Productos para el hogar");
        assertTrue(categoriaValida.getNombre().length() >= 2 && categoriaValida.getNombre().length() <= 50, 
                   "El nombre debería estar entre 2 y 50 caracteres");
        assertTrue(categoriaValida.getDescripcion().length() >= 2 && categoriaValida.getDescripcion().length() <= 255, 
                   "La descripción debería estar entre 2 y 255 caracteres");
    }

    @Test
    void testRelacionesBidireccionales() {
        assertEquals(categoriaPrincipal, subcategoria.getPadre(), 
                     "La relación padre-hijo no está configurada correctamente");
        assertTrue(categoriaPrincipal.getSubcategorias().contains(subcategoria), 
                   "La categoría principal no contiene la subcategoría en su lista");
    }

    @Test
    void testCategoriaSinSubcategorias() {
        Categoria categoriaSinHijos = new Categoria("Deportes", "Equipamiento deportivo");
        assertNull(categoriaSinHijos.getSubcategorias(), 
                   "La categoría sin subcategorías debería tener una lista nula o vacía");
    }


}

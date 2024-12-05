package unam.edu.ecomarket.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unam.edu.ecomarket.modelo.Orden;
import unam.edu.ecomarket.servicios.OrdenServicio;

import java.util.List;

@Controller
@RequestMapping("/orden")
public class OrdenControlador {

    @Autowired
    private OrdenServicio ordenServicio;

    @GetMapping
    public List<Orden> listarOrdenes() {
        return ordenServicio.listarOrdenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrden(@PathVariable Integer id) {
        Orden orden = ordenServicio.obtenerOrdenPorId(id);
        if (orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Orden crearOrden(@RequestBody Orden orden) {
        return ordenServicio.crearOrden(orden);
    }










}

package com.example.gestion_productos_reactivo.controller;

import com.example.gestion_productos_reactivo.model.Producto;
import com.example.gestion_productos_reactivo.service.ProductoService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Flux<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> obtenerProductoPorId(@PathVariable String id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
public Mono<ResponseEntity<Producto>> crearProducto(@RequestBody Producto producto) {
    return productoService.crearProducto(producto)
            .map(p -> ResponseEntity
                    .created(URI.create("/api/productos/" + p.getId()))
                    .body(p));
}

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarProducto(@PathVariable String id) {
        return productoService.eliminarProducto(id);
    }
}
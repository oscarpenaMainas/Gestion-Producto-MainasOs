package com.example.gestion_productos_reactivo.controller;

import com.example.gestion_productos_reactivo.model.Producto;
import com.example.gestion_productos_reactivo.repository.ProductoRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll().block();
    }

    @Test
void crearProducto_DebeRetornarProductoCreado() {
    Producto producto = new Producto(null, "Monitor", 199.99, 15);

    webTestClient.post()
            .uri("/api/productos")
            .bodyValue(producto)
            .exchange()
            .expectStatus().isCreated()  // Ahora espera 201 CREATED
            .expectHeader().exists("Location")  // Verifica que se creó la cabecera Location
            .expectBody(Producto.class)
            .value(p -> {
                assertNotNull(p.getId());
                assertEquals("Monitor", p.getNombre());
                assertEquals(199.99, p.getPrecio());
            });
}
}
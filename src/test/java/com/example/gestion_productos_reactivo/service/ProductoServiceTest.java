package com.example.gestion_productos_reactivo.service;

import com.example.gestion_productos_reactivo.model.Producto;
import com.example.gestion_productos_reactivo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void listarProductos_DebeRetornarFluxDeProductos() {
        Producto producto1 = new Producto("1", "Laptop", 999.99, 10);
        Producto producto2 = new Producto("2", "Mouse", 19.99, 50);

        when(productoRepository.findAll()).thenReturn(Flux.just(producto1, producto2));

        Flux<Producto> resultado = productoService.listarProductos();

        StepVerifier.create(resultado)
                .expectNext(producto1)
                .expectNext(producto2)
                .verifyComplete();
    }

    @Test
    void obtenerProductoPorId_DebeRetornarMonoProducto() {
        Producto producto = new Producto("1", "Teclado", 49.99, 20);
        
        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        Mono<Producto> resultado = productoService.obtenerProductoPorId("1");

        StepVerifier.create(resultado)
                .expectNext(producto)
                .verifyComplete();
    }
}

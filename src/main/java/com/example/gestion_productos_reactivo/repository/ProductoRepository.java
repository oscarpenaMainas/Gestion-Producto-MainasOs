package com.example.gestion_productos_reactivo.repository;

import com.example.gestion_productos_reactivo.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
    Mono<Producto> findByNombre(String nombre);
}
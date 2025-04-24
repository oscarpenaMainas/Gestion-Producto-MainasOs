package com.example.gestion_productos_reactivo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productos")
public class Producto {
    @Id
    private String id;
    private String nombre;
    private Double precio;
    private Integer stock;
}
package com.emergente.mongo.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Articulo {

    @Id
    private String id;
}

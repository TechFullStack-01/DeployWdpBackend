package com.wdpvendas.springJwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subcategorias")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
}

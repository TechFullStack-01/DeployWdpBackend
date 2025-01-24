package com.wdpvendas.springJwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cores")
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}

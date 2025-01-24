package com.wdpvendas.springJwt.entity;

import com.wdpvendas.springJwt.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
}

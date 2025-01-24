package com.wdpvendas.springJwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "detalhes_produto")
@Data
public class DetalhesProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Produto produto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}

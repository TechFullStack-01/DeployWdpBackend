package com.wdpvendas.springJwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagens_produto")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String tipoArquivo;

    @Lob
    private byte[] dados;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
}


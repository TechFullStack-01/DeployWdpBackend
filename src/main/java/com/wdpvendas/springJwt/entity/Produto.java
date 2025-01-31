package com.wdpvendas.springJwt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    // @Column(nullable = false)
    private String codigoSKU;
    // @Column(nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;
    
    private Integer unidadesVendidas;

    private Integer quantidadeEstoque;

    private String categoria;
    
    
    @ElementCollection
    @CollectionTable(name = "produto_subcategorias", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "subCategorias")
    private List<String> subCategorias;
    
    // @Column(nullable = false)
    private Integer preco;
    
    private Integer precoPromocional;
    
    private Integer avaliacao;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="produto_id")
    @JsonManagedReference
    private List<DetalhesProduto> detalhesProduto = new ArrayList<>();


    /*
    @ElementCollection
    @CollectionTable(name = "produto_detalhesProduto", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "detalhesProduto")
    private List<DetalhesProduto> detalhesProduto;

     */
    
    
    @ElementCollection
    @CollectionTable(name = "produto_cores", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "cor")
    private List<String> cores;
    
    @ElementCollection
    @CollectionTable(name = "produto_tamanhos", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tamanho")
    private List<String> tamanhos;
    
    @ElementCollection
    private List<String> imagensPath = new ArrayList<>();
    
    
}
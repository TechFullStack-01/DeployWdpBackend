package com.wdpvendas.springJwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Column(nullable = false)
    private String categoria;

    //@Column(nullable = false)
    private String imagemDesktop;

    //@Column(nullable = false)
    private String imagemMobile;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}

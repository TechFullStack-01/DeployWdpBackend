package com.wdpvendas.springJwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wdpvendas.springJwt.entity.Endereco;
import jakarta.persistence.*;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "genero")
    private String genero;

    @Column(name = "username")
    private String username;

    @Column(name = "data_nasc")
    private String data_nasc;

    @Column(name = "celular")
    private String celular;

    @Column(name = "tel_fixo")
    private String tel_fixo;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Endereco> enderecos = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data_cadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;

    // *****************************************************************************************

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}

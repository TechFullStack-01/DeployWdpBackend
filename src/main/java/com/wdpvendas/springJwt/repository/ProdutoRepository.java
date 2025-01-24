package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Métodos customizados podem ser adicionados aqui, se necessário.
    @Query("select p from Produto p where p.categoria= ?1")
    List<Produto> findByCategoria(String categoria);

}

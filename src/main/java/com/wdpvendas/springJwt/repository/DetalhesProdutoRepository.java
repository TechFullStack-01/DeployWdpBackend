package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.DetalhesProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalhesProdutoRepository extends JpaRepository<DetalhesProduto, Long> {
}

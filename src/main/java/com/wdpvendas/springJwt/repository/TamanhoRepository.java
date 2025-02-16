package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {
}

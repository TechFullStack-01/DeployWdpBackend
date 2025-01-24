package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByUsuarioId(Long usuarioId);
}

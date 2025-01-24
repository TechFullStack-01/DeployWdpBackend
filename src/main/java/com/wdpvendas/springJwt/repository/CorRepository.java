package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
}

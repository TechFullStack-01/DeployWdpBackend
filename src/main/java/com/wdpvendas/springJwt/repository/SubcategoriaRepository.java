package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
}

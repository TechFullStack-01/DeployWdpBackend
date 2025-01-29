package com.wdpvendas.springJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wdpvendas.springJwt.entity.Banner;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    @Query("select b from Banner b where b.categoria= ?1")

    List<Banner> findByCategoriaIgnoreCase(String categoria);
}
package com.wdpvendas.springJwt.repository;

import com.wdpvendas.springJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u")
    List<User> findAllDistinct();

}

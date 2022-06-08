package com.los_acantilados.carta.carta.auth.repository;

import com.los_acantilados.carta.carta.auth.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Bean
    UserEntity findByUsername(String username);
}

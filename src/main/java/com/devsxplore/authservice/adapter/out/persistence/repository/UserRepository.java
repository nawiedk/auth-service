package com.devsxplore.authservice.adapter.out.persistence.repository;

import com.devsxplore.authservice.adapter.out.persistence.jpaentity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    List<UserJpaEntity> findByUsername(String username);

    List<UserJpaEntity> findByEmail(String email);

    List<UserJpaEntity> findByPassword(String password);
}

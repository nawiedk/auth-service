package com.devsxplore.authservice.adapter.out.persistence.repository;

import com.devsxplore.authservice.adapter.out.persistence.jpaentity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleJpaEntity, Long> {
    Optional<RoleJpaEntity> findByName(String name);

    void deleteByName(String name);
}

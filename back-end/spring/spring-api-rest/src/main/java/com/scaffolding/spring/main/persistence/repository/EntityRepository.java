package com.scaffolding.spring.main.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scaffolding.spring.main.persistence.domain.EntityDAO;

public interface EntityRepository extends JpaRepository<EntityDAO, Long> {

}
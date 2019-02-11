package com.scaffolding.spring.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scaffolding.spring.model.EntityModel;

public interface EntityRepository extends JpaRepository<EntityModel, Long> {

}
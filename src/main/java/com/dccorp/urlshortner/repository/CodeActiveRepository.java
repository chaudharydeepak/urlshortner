package com.dccorp.urlshortner.repository;

import com.dccorp.urlshortner.entity.CodeActiveEntity;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CodeActiveRepository extends JpaRepository<CodeActiveEntity, Long> {
}

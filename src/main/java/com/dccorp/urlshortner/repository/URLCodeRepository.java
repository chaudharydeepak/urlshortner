package com.dccorp.urlshortner.repository;

import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface URLCodeRepository extends JpaRepository<UrlCodeMappingEntity, Long> {
    boolean existsByCode(String code);

    UrlCodeMappingEntity findByCode(String code);
}

package com.dccorp.urlshortner.repository;

import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLCodeRepository extends CrudRepository<UrlCodeMappingEntity, Long> {
    
}

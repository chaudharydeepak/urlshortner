package com.dccorp.urlshortner.repository;

import com.dccorp.urlshortner.entity.CodeActiveEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeActiveRepository extends CrudRepository<CodeActiveEntity, Long> {
}

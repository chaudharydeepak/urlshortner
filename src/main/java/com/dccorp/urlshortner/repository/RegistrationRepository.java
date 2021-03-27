package com.dccorp.urlshortner.repository;

import com.dccorp.urlshortner.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<AppUser, Long> {

     AppUser findByuserName(String username);
}

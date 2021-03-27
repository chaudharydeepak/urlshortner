package com.dccorp.urlshortner.controller;

import com.dccorp.urlshortner.entity.AppUser;
import com.dccorp.urlshortner.repository.RegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

   // @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody AppUser user) {
        log.info("registration requested by: {} {}", user.getFirstName(), user.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        registrationRepository.save(user);
    }
}

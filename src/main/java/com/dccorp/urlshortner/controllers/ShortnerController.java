package com.dccorp.urlshortner.controllers;

import com.dccorp.urlshortner.domin.ServiceRequest;
import com.dccorp.urlshortner.domin.ServiceResponse;
import com.dccorp.urlshortner.service.ShortnerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SHORTNER_CONTROLLER")
@RestController
public class ShortnerController {

    ShortnerService shortnerService;

    public ShortnerController(ShortnerService shortnerService) {
        this.shortnerService = shortnerService;
    }

    @PostMapping("/url")
    public ResponseEntity<ServiceResponse> createShortURL(@RequestBody ServiceRequest urlRequest) {
        log.info("executing contoller: {}", urlRequest);
        return new ResponseEntity<>(shortnerService.createShortCode(urlRequest), HttpStatus.CREATED);
    }

}

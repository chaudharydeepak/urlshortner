package com.dccorp.urlshortner.controllers;

import com.dccorp.urlshortner.domin.ServiceRequest;
import com.dccorp.urlshortner.domin.ServiceResponse;
import com.dccorp.urlshortner.service.ShortnerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/url/{destination}")
    public ResponseEntity<ServiceResponse> getCodeInfo(@PathVariable("destination") String destination) {
        log.info("looking for: {}", destination);
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setRespObj(shortnerService.getShortCode(destination));
        return new ResponseEntity<>(serviceResponse, HttpStatus.FOUND);
    }

    @PutMapping("/url")
    public ResponseEntity<ServiceResponse> updateShortURL(@RequestBody ServiceRequest urlRequest) {
        log.info("executing contoller to update: {}", urlRequest);
        ServiceResponse serviceResponse = new ServiceResponse();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/url")
    public ResponseEntity<ServiceResponse> deleteShortURL(@RequestBody ServiceRequest urlRequest) {
        log.info("executing contoller to update: {}", urlRequest);
        ServiceResponse serviceResponse = new ServiceResponse();
        return new ResponseEntity<>(serviceResponse, HttpStatus.NO_CONTENT);
    }

}

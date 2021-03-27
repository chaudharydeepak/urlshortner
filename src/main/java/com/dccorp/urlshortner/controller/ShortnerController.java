package com.dccorp.urlshortner.controller;

import com.dccorp.urlshortner.domain.ServiceRequest;
import com.dccorp.urlshortner.domain.ServiceResponse;
import com.dccorp.urlshortner.service.ShortnerService;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j(topic = "SHORTNER_CONTROLLER")
@RestController
public class ShortnerController {

    ShortnerService shortnerService;

    public ShortnerController(ShortnerService shortnerService) {
        this.shortnerService = shortnerService;
    }

    @Bean
    public Function<ServiceRequest, ResponseEntity<ServiceResponse>> create() {
        return (serviceRequest) -> new ResponseEntity<>(shortnerService.createShortCode(serviceRequest), HttpStatus.CREATED);
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
        log.info("executing controller to update: {}", urlRequest);
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

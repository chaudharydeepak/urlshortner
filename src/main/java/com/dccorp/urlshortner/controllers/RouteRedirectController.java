package com.dccorp.urlshortner.controllers;

import java.net.URI;

import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;
import com.dccorp.urlshortner.repository.URLCodeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RouteRedirectController")
@RestController
public class RouteRedirectController {

    URLCodeRepository urlCodeRepository;

    public RouteRedirectController(URLCodeRepository urlCodeRepository) {
        this.urlCodeRepository = urlCodeRepository;
    }

    @GetMapping("/go/{destination}")
    public ResponseEntity<Object> doRedirect(@PathVariable("destination") String destination) {
        // check if destination exist
        // check if still active
        // check if in preset date range
        UrlCodeMappingEntity codeMappingEntity = urlCodeRepository.findByCode(destination);
        log.info("should send to: {}", codeMappingEntity.getUrl());
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(codeMappingEntity.getUrl())).build();
    }
}

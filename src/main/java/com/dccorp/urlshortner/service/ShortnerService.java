package com.dccorp.urlshortner.service;

import java.time.LocalDateTime;

import com.dccorp.urlshortner.domin.ServiceRequest;
import com.dccorp.urlshortner.domin.ServiceResponse;
import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;
import com.dccorp.urlshortner.repository.URLCodeRepository;

import org.springframework.stereotype.Service;

@Service
public class ShortnerService {

    URLCodeRepository codeRepository;

    public ShortnerService(URLCodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public ServiceResponse createShortCode(ServiceRequest serviceRequest) {
        UrlCodeMappingEntity urlCodeMapping = new UrlCodeMappingEntity();
        urlCodeMapping.setCode(serviceRequest.getRequestedShortCode());
        urlCodeMapping.setUrl(serviceRequest.getRequestURL());
        urlCodeMapping.setCreated_date((LocalDateTime.now()));
        urlCodeMapping.setUpdated_date((LocalDateTime.now()));
        urlCodeMapping.setRequested_by("user1");
        
        codeRepository.save(urlCodeMapping);

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setURL(serviceRequest.getRequestURL());
        serviceResponse.setShortURL(serviceRequest.getRequestedShortCode());
        return serviceResponse;
    }

}

package com.dccorp.urlshortner.service;

import com.dccorp.urlshortner.domain.ServiceRequest;
import com.dccorp.urlshortner.domain.ServiceResponse;
import com.dccorp.urlshortner.entity.CodeActiveEntity;
import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;
import com.dccorp.urlshortner.repository.CodeActiveRepository;
import com.dccorp.urlshortner.repository.URLCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.time.LocalDateTime;

@Slf4j(topic = "ShortnerService")
@Service
public class ShortnerService {

    URLCodeRepository codeRepository;
    CodeActiveRepository codeActiveRepository;

    public ShortnerService(URLCodeRepository codeRepository, CodeActiveRepository codeActiveRepository) {
        this.codeRepository = codeRepository;
        this.codeActiveRepository = codeActiveRepository;
    }



    public ServiceResponse createShortCode(ServiceRequest serviceRequest) {
        UrlCodeMappingEntity urlCodeMapping = new UrlCodeMappingEntity();
        urlCodeMapping.setCode(serviceRequest.getRequestedShortCode());
        urlCodeMapping.setUrl(serviceRequest.getRequestURL());
        urlCodeMapping.setCreatedDate((LocalDateTime.now()));
        urlCodeMapping.setUpdatedDate((LocalDateTime.now()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        urlCodeMapping.setRequestedBy(currentPrincipalName);
        UrlCodeMappingEntity codeMappingEntityResponse = codeRepository.save(urlCodeMapping);

        CodeActiveEntity codeActiveEntity = new CodeActiveEntity();
        codeActiveEntity.setId(codeMappingEntityResponse.getId());
        codeActiveEntity.setCreatedDate((LocalDateTime.now()));
        codeActiveEntity.setEnddate((LocalDateTime.now().plusWeeks(1)));
        CodeActiveEntity codeActiveEntityResponse = codeActiveRepository.save(codeActiveEntity);

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setURL(serviceRequest.getRequestURL());
        serviceResponse.setShortURL(serviceRequest.getRequestedShortCode());
        serviceResponse.setStartDate(codeActiveEntityResponse.getCreatedDate());
        serviceResponse.setEndDate(codeActiveEntityResponse.getEnddate());
        log.info(" ---> {}", serviceResponse);
        return serviceResponse;
    }

    public UrlCodeMappingEntity getShortCode(String destination) {
        return codeRepository.findByCode(destination);
    }

}

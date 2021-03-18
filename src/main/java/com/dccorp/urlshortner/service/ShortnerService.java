package com.dccorp.urlshortner.service;

import com.dccorp.urlshortner.domain.ServiceRequest;
import com.dccorp.urlshortner.domain.ServiceResponse;
import com.dccorp.urlshortner.entity.CodeActiveEntity;
import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;
import com.dccorp.urlshortner.repository.CodeActiveRepository;
import com.dccorp.urlshortner.repository.URLCodeRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j(topic = "ShortnerService")
@Service
//@Transactional
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
        urlCodeMapping.setRequestedBy("user1");
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

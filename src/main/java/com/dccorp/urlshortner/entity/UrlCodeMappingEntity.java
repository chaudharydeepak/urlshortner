package com.dccorp.urlshortner.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "url_code_mapping")
public class UrlCodeMappingEntity {

    @Id
    @GeneratedValue
    Long id ;

    String url ;
    String code ;
    String requested_by; 

    //@Temporal(TemporalType.DATE)
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    LocalDateTime created_date ;

    //@Temporal(TemporalType.DATE)
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    LocalDateTime updated_date ;
}

package com.dccorp.urlshortner.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "url_code_mapping")
public class UrlCodeMappingEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String url;
    String code;

    @Column(name = "requested_by")
    String requestedBy;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    LocalDateTime updatedDate;
}

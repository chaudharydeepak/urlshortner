package com.dccorp.urlshortner.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "code_active")
public class CodeActiveEntity implements Serializable {

    String Id;
    @Column(name = "start_date", columnDefinition = "TIMESTAMP")
    LocalDateTime createdDate;
    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    LocalDateTime enddate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dummyID")
    private Long dummyID;

}

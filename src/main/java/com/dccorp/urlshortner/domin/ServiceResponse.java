package com.dccorp.urlshortner.domin;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ServiceResponse {
    String shortURL;
    String URL;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Object respObj;
}

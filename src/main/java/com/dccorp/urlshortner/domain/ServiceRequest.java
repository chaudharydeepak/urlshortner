package com.dccorp.urlshortner.domain;

import lombok.Data;

@Data
public class ServiceRequest {
    String requestURL;
    String requestedShortCode;
}

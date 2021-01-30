package com.dccorp.urlshortner.domin;

import lombok.Data;

@Data
public class ServiceRequest {
    String requestURL;
    String requestedShortCode;
}

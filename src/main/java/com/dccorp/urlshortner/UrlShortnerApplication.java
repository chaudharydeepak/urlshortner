package com.dccorp.urlshortner;

import com.dccorp.urlshortner.domain.ServiceResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeHints;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@TypeHints({
        @TypeHint(types = HttpStatus.class),
        @TypeHint(types = ServiceResponse.class)
}
)

public class UrlShortnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }

}

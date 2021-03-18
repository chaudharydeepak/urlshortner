package com.dccorp.urlshortner.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dccorp.urlshortner.UrlShortnerApplication;
import com.dccorp.urlshortner.entity.UrlCodeMappingEntity;
import com.dccorp.urlshortner.service.ShortnerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UrlShortnerApplication.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ShortnerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShortnerService shortnerService;

    @Test
    public void doTest() throws Exception {
        UrlCodeMappingEntity codeMappingEntity = new UrlCodeMappingEntity();

        given(shortnerService.getShortCode("test")).willReturn(codeMappingEntity);

        mvc.perform(get("/url/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}

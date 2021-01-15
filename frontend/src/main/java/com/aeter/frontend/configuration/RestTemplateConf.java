package com.aeter.frontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConf {
    @Bean(name = "appRestClient")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

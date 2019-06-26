package com.example.shardingjdbcdemo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:datasource.properties"})
@Component
@Data
public class DatasourceConfig {

    @Value("${className1}")
    private String className1;

    @Value("${url1}")
    private String url1;

    @Value("${user1}")
    private String user1;

    @Value("${password1}")
    private String password1;

    @Value("${className2}")
    private String className2;

    @Value("${url2}")
    private String url2;

    @Value("${user2}")
    private String user2;

    @Value("${password2}")
    private String password2;

    @Value("${className3}")
    private String className3;

    @Value("${url3}")
    private String url3;

    @Value("${user3}")
    private String user3;

    @Value("${password3}")
    private String password3;

    @Value("${className4}")
    private String className4;

    @Value("${url4}")
    private String url4;

    @Value("${user4}")
    private String user4;

    @Value("${password4}")
    private String password4;

}
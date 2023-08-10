package com.jw.microservicesfront.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class TestDto {

    @Value("test")
    private String test;
}

package com.jw.microservicesfront;

import com.jw.microservicesfront.controller.dto.TestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUnit {

    @Autowired
    TestDto autowiredDto;

    @Test
    public void TestDtoValueTest(){
        TestDto dto = new TestDto();
        System.out.println("initialize with new dto : "+dto.getTest());

        System.out.println("bean injected dto : " + autowiredDto.getTest());

    }
}

package com.jw.microservicesfront.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.microservicesfront.controller.dto.UserDto;
import com.jw.microservicesfront.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private final WebClient webClient;

    public ApiController(){
        webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /** 회원 가입 요청
     *  localhost:9090/api/signup
     *
     * - RequestBody UserDto dto
     * - Response
     * */
    @PostMapping ("/user/signup")
    public Mono<User> requestSignIn(@RequestBody UserDto dto) throws JsonProcessingException {

        logger.info("signup request dto: {}", dto.toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        return webClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonString))
                .retrieve()
                .bodyToMono(User.class);
    }


    /** 로그인 인증 (토큰 발급)
     * localhost:9090/api/user/{username}
     *
     * - RequestParam String username
     * - Response
     *
     * */






}

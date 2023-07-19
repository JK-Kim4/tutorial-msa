package com.jw.jwtgateway.controller;

import com.jw.jwtgateway.controller.dto.UserDto;
import com.jw.jwtgateway.entity.User;
import com.jw.jwtgateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto dto){
        return ResponseEntity.ok(userService.signup(dto));

    }

}

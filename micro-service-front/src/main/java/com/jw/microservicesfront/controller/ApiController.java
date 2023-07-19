package com.jw.microservicesfront.controller;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    /*@GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<User> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyuserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }*/



}

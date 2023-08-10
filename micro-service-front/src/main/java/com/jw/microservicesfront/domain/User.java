package com.jw.microservicesfront.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter @Setter
@ToString
public class User {

    private Long userId;

    private String username;

    private String password;

    private String nickname;


    private boolean activated;

    private Set<Authority> authorities;
}

package com.kuji.kujiauthenticationserver.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Builder
    public Member(Long memberId, String account, String password, String name){
        this.id = memberId;
        this.account = account;
        this.password = password;
        this.name = name;
    }

    @Id
    @Column(name = "member_id")
    private Long id;

    @Column
    private String account;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;
}

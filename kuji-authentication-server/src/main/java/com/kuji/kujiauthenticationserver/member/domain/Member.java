package com.kuji.kujiauthenticationserver.member.domain;

import com.kuji.kujiauthenticationserver.member.domain.enum_type.Platform;
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
    public Member(
            Long memberId, String email,
            String password, String name,
            String authPlatform, String authCode){
        this.id = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authPlatform = Platform.valueOf(authPlatform);
        this.authCode = authCode;
    }


    @Id
    @Column(name = "member_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Platform authPlatform;

    @Column
    private String authCode;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;
}

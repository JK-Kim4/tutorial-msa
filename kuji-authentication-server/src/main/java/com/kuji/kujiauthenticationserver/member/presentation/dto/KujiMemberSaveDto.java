package com.kuji.kujiauthenticationserver.member.presentation.dto;

import com.kuji.kujiauthenticationserver.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class KujiMemberSaveDto {

    private Long memberId;
    private String email;
    private String password;
    private String name;

    private String authPlatform;
    private String authCode;

    public Member toEntity(){
        return Member.builder()

                    .password(this.password)
                    .name(this.name)
                .build();
    }
}

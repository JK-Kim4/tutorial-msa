package com.kuji.kujiauthenticationserver.member.application.dto;

import com.kuji.kujiauthenticationserver.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class MemberSaveDto {

    private Long memberId;
    private String account;
    private String password;
    private String name;

    public Member toEntity(){
        return Member.builder()
                    .account(this.account)
                    .password(this.password)
                    .name(this.name)
                .build();
    }
}

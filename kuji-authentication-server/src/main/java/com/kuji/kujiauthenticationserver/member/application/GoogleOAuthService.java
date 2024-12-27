package com.kuji.kujiauthenticationserver.member.application;

import com.kuji.kujiauthenticationserver.member.domain.Member;
import com.kuji.kujiauthenticationserver.member.domain.enum_type.Platform;
import org.springframework.stereotype.Service;

@Service
public class GoogleOAuthService implements OAuthService {
    @Override
    public Platform supports() {
        return Platform.GOOGLE;
    }

    @Override
    public Member toEntityMember(String code, Platform platform) {
        return null;
    }
}

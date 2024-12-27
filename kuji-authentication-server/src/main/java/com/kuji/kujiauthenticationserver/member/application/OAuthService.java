package com.kuji.kujiauthenticationserver.member.application;

import com.kuji.kujiauthenticationserver.member.domain.Member;
import com.kuji.kujiauthenticationserver.member.domain.enum_type.Platform;

public interface OAuthService {
    Platform supports();

    Member toEntityMember(String code, Platform platform);
}

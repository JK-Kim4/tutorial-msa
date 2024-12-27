package com.kuji.kujiauthenticationserver.member.presentation.oauth_properties;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Getter @ToString
public class NaverProperties {

    @Value("${oauth.naver.oauth-uri}")
    private String oauthUri;

    @Value("${oauth.naver.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.naver.client-id}")
    private String clientId;

    @Value("${oauth.naver.client-secret}")
    private String clientSecret;


    public String getAuthRequestUri(String responseType) throws UnsupportedEncodingException {

        UUID uuid = UUID.randomUUID();

        //naver 로그인
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(this.oauthUri)
                .queryParam("response_type", responseType)
                .queryParam("redirect_uri", URLEncoder.encode(this.redirectUri, "UTF-8"))
                .queryParam("state", URLEncoder.encode(uuid.toString(), "UTF-8"))
                .queryParam("client_id", this.clientId)
                .build();

        return uriComponents.toUriString();
    }


}

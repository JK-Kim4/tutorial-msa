package com.kuji.kujiauthenticationserver.member.presentation;

import com.kuji.kujiauthenticationserver.member.application.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthRouter {

    private final List<OAuthService> oAuthServices;

    @GetMapping("/login/{platform}")
    public void oauthLoginPageRedirect (
            @PathVariable(name = "platform") String platform,
            HttpServletResponse response) throws IOException {

        for(OAuthService oAuthService : oAuthServices) {
            System.out.println(oAuthService.supports());
        }

    }
}

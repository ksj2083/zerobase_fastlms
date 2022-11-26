package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.model.LoginHistoryInput;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String userAgent = request.getHeader("user-agent");
        String clientIp = request.getHeader("X-FORWARDED-FOR");

        LoginHistoryInput parameter = new LoginHistoryInput();
        parameter.setUserId(authentication.getName());
        parameter.setUserAgent(userAgent);
        parameter.setClientIp(clientIp);

        memberService.recordLoginHistory(parameter);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}

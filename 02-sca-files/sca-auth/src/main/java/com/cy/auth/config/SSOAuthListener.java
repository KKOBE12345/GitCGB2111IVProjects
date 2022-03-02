package com.cy.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class SSOAuthListener extends DefaultAuthenticationEventPublisher {
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {

        super.publishAuthenticationSuccess(authentication);
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        log.info("{} login success 66666666666  {}",principal.getUsername(), LocalDateTime.now());
//        remoteLogService.saveLog(xxxxx);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        super.publishAuthenticationFailure(exception, authentication);
//        Object principal = authentication.getPrincipal();
        log.info("{} login fail  9999999999 {}",exception, LocalDateTime.now());


    }
}

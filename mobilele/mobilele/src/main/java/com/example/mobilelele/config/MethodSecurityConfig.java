package com.example.mobilelele.config;

import com.example.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private ApplicationMethodSecurityExpressionHandler applicationMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return applicationMethodSecurityExpressionHandler;
    }

    @Bean
    public ApplicationMethodSecurityExpressionHandler createExpressionHandler(OfferService offerService) {
        return new ApplicationMethodSecurityExpressionHandler(offerService);
    }
}

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
    private MobileleMethodSecurityExpressionHandler mobileleMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return mobileleMethodSecurityExpressionHandler;
    }

    @Bean
    public MobileleMethodSecurityExpressionHandler createExpressionHandler(OfferService offerService) {
        return new MobileleMethodSecurityExpressionHandler(offerService);
    }
}

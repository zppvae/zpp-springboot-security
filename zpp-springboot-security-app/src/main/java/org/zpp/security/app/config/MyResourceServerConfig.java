package org.zpp.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.social.security.SpringSocialConfigurer;
import org.zpp.security.app.social.openid.OpenIdAuthenticationSecurityConfig;

import org.zpp.security.core.authentication.FormAuthenticationConfig;
import org.zpp.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.zpp.security.core.authorize.AuthorizeConfigManager;
import org.zpp.security.core.component.MyWebResponseExceptionTranslator;
import org.zpp.security.core.component.SecurityAuthenticationEntryPoint;
import org.zpp.security.core.validate.code.ValidateCodeSecurityConfig;

/**
 * @author zpp
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                	.and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

        http.exceptionHandling()
             .authenticationEntryPoint(securityAuthenticationEntryPoint);

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        ((OAuth2AuthenticationEntryPoint) authenticationEntryPoint).setExceptionTranslator(new MyWebResponseExceptionTranslator());
        resources.authenticationEntryPoint(authenticationEntryPoint);
    }
}